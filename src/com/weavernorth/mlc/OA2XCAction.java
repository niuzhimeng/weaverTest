package com.weavernorth.mlc;

import com.google.gson.Gson;
import com.weavernorth.mlc.webclient.*;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.integration.util.HTTPUtil;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class OA2XCAction extends BaseBean implements Action {

    private Gson gson = new Gson();

    @Override
    public String execute(RequestInfo requestInfo) {

        String requestid = requestInfo.getRequestid();
        this.writeLog("携程预推送信息 start=============" + TimeUtil.getCurrentTimeString() + " request: " + requestid);

        //获取ticket
        String ticketUrl = "https://ct.ctrip.com/corpservice/authorize/getticket";
        String appKey = "obk_mljr";
        String appSecurity = "obk_mljr";
        JSONObject map = new JSONObject();
        map.put("appKey", appKey);
        map.put("appSecurity", appSecurity);
        String ticket = HTTPUtil.doPost(ticketUrl, map);
        this.writeLog("ticket ***************************" + ticket);

        JSONObject object1 = JSONObject.fromObject(ticket);
        String token = object1.getString("Token");
        this.writeLog("Token *************************" + token);

        //从OA表单中取数据
        RecordSet rs = new RecordSet();
        rs.execute("select * from formtable_main_49 where requestid='" + requestid + "'");
        if (rs.next()) {
            try {
                String mid = rs.getString("id");
                writeLog("主表ID-------------->" + mid);
                String sqdh = rs.getString("sqdh");
                this.writeLog("申请单号: " + sqdh);
                String sqrgh = rs.getString("sqrgh");
                String sqr = rs.getString("sqr");
                //取人力资源表
                RecordSet rs2 = new RecordSet();
                rs2.execute("select * from hrmresource where id ='" + sqr + "'");
                rs2.next();
                String lastname = rs2.getString("lastname");
                this.writeLog("lastname************" + lastname);

                //调用提前审批接口
                ApprovalLocator approvalLocator = new ApprovalLocator();
                SetApprovalRequest request = new SetApprovalRequest();
                request.setAuth(new Authentification("obk_mljr", token));
                request.setApprovalNumber(sqdh);
                request.setEmployeeID(sqrgh);
                request.setStatus(1);

                RecordSet rs1 = new RecordSet();
                rs1.execute("select * from formtable_main_49_dt2 where mainid='" + mid + "'");
                RecordSet rs3 = new RecordSet();
                rs3.execute("select * from formtable_main_49_dt3 where mainid='" + mid + "'");

                //明细表1====================== 交通费 ============================
                int jtfCounts = rs1.getCounts(); // 交通费行数
                this.writeLog("交通费行数： " + jtfCounts);
                FlightEndorsementDetail[] flightEndorsementDetails = new FlightEndorsementDetail[0];
                if (jtfCounts > 0) {

                    List<String> allList = new ArrayList<String>(); // 出发地 + 目的地数组

                    List<String> beginStartDateList = new ArrayList<String>(); // 开始日期集合

                    while (rs1.next()) {
                        beginStartDateList.add(rs1.getString("ksrq")); // 开始日期
                        beginStartDateList.add(rs1.getString("jsrq")); // 结束日期

                        allList.add(rs1.getString("cfd")); // 出发地
                        allList.add(rs1.getString("mdd")); // 目的地
                    }
                    String[] allArrays = new String[allList.size()];
                    allList.toArray(allArrays);

                    // 日期排序
                    Collections.sort(beginStartDateList);

                    flightEndorsementDetails = new FlightEndorsementDetail[1];
                    flightEndorsementDetails[0] = new FlightEndorsementDetail();
                    flightEndorsementDetails[0].setCurrency(CurrencyType.UnKnow);
                    flightEndorsementDetails[0].setFlightWay(FlightWayType.RoundTrip);
                    flightEndorsementDetails[0].setDepartDateBegin(DateCut(beginStartDateList.get(0))); // 开始日期最小的
                    flightEndorsementDetails[0].setDepartDateEnd(DateAdd(beginStartDateList.get(beginStartDateList.size() - 1))); // 开始日期最大的
                    flightEndorsementDetails[0].setReturnDateBegin(DateCut(beginStartDateList.get(0))); //结束时间最小的
                    flightEndorsementDetails[0].setReturnDateEnd(DateAdd(beginStartDateList.get(beginStartDateList.size() - 1))); //结束时间最大的
                    flightEndorsementDetails[0].setDepartCityCodes(allArrays); // 出发城市数组
                    flightEndorsementDetails[0].setArrivalCityCodes(allArrays); //到达城市数组
                    // 出行人
                    PassengerDetail[] flightPassengerDetail = new PassengerDetail[1];
                    flightPassengerDetail[0] = new PassengerDetail();
                    flightPassengerDetail[0].setName(lastname);// 有外籍人员？
                    flightEndorsementDetails[0].setPassengerList(flightPassengerDetail);
                }
                this.writeLog("交通费发送数组：" + gson.toJson(flightEndorsementDetails));

                //明细表2 ======================  住宿费 ======================
                int zsfCounts = rs3.getCounts();
                this.writeLog("住宿费行数： " + zsfCounts);
                HotelEndorsementDetail[] hotelEndorsementDetails = new HotelEndorsementDetail[0];
                if (zsfCounts > 0) {
                    String[] ruZhuCity = new String[zsfCounts]; // 入住城市数组

                    List<String> rzStartDateList = new ArrayList<String>(); // 开始日期集合

                    int j = 0;
                    while (rs3.next()) {
                        rzStartDateList.add(rs3.getString("rzrq"));
                        rzStartDateList.add(rs3.getString("ldrq"));

                        ruZhuCity[j] = rs3.getString("rzcs");
                        j++;
                    }
                    // 日期排序
                    Collections.sort(rzStartDateList);

                    hotelEndorsementDetails = new HotelEndorsementDetail[1];
                    hotelEndorsementDetails[0] = new HotelEndorsementDetail();
                    hotelEndorsementDetails[0].setProductType(HotelProductTypeEnum.Domestic);
                    hotelEndorsementDetails[0].setCheckInDateBegin(DateCut(rzStartDateList.get(0))); // 入住开始时间
                    hotelEndorsementDetails[0].setCheckInDateEnd(DateAdd(rzStartDateList.get(rzStartDateList.size() - 1))); // 入住结束时间
                    hotelEndorsementDetails[0].setCheckOutDateBegin(DateCut(rzStartDateList.get(0))); // 离店开始时间
                    hotelEndorsementDetails[0].setCheckOutDateEnd(DateAdd(rzStartDateList.get(rzStartDateList.size() - 1))); // 离店结束时间
                    hotelEndorsementDetails[0].setCheckInCityCodes(ruZhuCity); // 入住城市数组

                    PassengerDetail[] flightPassengerDetail1 = new PassengerDetail[1];
                    flightPassengerDetail1[0] = new PassengerDetail();
                    flightPassengerDetail1[0].setName(lastname);// 有外籍人员？
                    hotelEndorsementDetails[0].setPassengerList(flightPassengerDetail1);
                }
                this.writeLog("住宿费发送数组：" + gson.toJson(hotelEndorsementDetails));

                request.setFlightEndorsementDetails(flightEndorsementDetails);
                request.setHotelEndorsementDetails(hotelEndorsementDetails);

                SetApprovalResponse res = approvalLocator.getws().setApproval(request);
                this.writeLog("携程返回信息new： " + gson.toJson(res.getStatus()));
            } catch (Exception e) {
                this.writeLog("携程预推送信息异常new： " + e);
                requestInfo.getRequestManager().setMessageid("110000");
                requestInfo.getRequestManager().setMessagecontent("接口异常，请联系管理员。");
                return "0";
            }
            this.writeLog("携程预推送信息 end=============" + TimeUtil.getCurrentTimeString() + " request: " + requestid);

        }

        return SUCCESS;
    }

    //日期减三天
    private String DateCut(String str) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = sdf.parse(str);

        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.DATE, -3);// 日期减三天
        return sdf.format(rightNow.getTime());
    }

    //日期加三天
    private String DateAdd(String str) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = sdf.parse(str);

        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.DATE, +3);// 日期加三天
        return sdf.format(rightNow.getTime());
    }

}
