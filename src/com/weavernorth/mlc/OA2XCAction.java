package com.weavernorth.mlc;

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
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 高恩琪(EBU-8 mobile 15090115523) on 2019/1/18.
 */
public class OA2XCAction extends BaseBean implements Action {
    //日期减三天
    public String DateCut(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = null;
        try {
            dt = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.DATE, -3);// 日期减三天
        return sdf.format(rightNow.getTime());
    }

    //日期加三天
    public String DateAdd(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = null;
        try {
            dt = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.DATE, +3);// 日期加三天
        return sdf.format(rightNow.getTime());
    }

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
            String mid = rs.getString("id");
            writeLog("主表ID-------------->" + mid);
            String sqdh = rs.getString("sqdh");
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

            //明细表1----------------- 交通费
            FlightEndorsementDetail[] flightEndorsementDetails = new FlightEndorsementDetail[rs1.getCounts()];
            int i = 0;
            while (rs1.next()) {
                String startdate = rs1.getString("ksrq");
                this.writeLog("************" + startdate);
                String enddate = rs1.getString("jsrq");
                this.writeLog("************" + enddate);
                String jtgj = rs1.getString("jtgj"); // 交通工具
                this.writeLog("************" + jtgj);
                String cfd = rs1.getString("cfd");
                this.writeLog("************" + cfd);
                String mdd = rs1.getString("mdd");
                this.writeLog("************" + mdd);
                if (jtgj.equals("0")) {
                    flightEndorsementDetails[i] = new FlightEndorsementDetail();
                    flightEndorsementDetails[i].setCurrency(CurrencyType.UnKnow);
                    flightEndorsementDetails[i].setFlightWay(FlightWayType.RoundTrip);
                    flightEndorsementDetails[i].setDepartDateBegin(DateCut(startdate));
                    flightEndorsementDetails[i].setDepartDateEnd(DateAdd(enddate));
                    flightEndorsementDetails[i].setReturnDateBegin(DateCut(startdate));//出发结束时间
                    flightEndorsementDetails[i].setReturnDateEnd(DateAdd(enddate));//出发结束时间
                    flightEndorsementDetails[i].setDepartCityCodes(new String[]{cfd});//出发城市 暂写死上海
                    flightEndorsementDetails[i].setArrivalCityCodes(new String[]{mdd});//到达城市 暂写死深圳
                    // 出行人
                    PassengerDetail[] flightPassengerDetail = new PassengerDetail[1];
                    flightPassengerDetail[0] = new PassengerDetail();
                    flightPassengerDetail[0].setName(lastname);// 有外籍人员？
                    flightEndorsementDetails[i].setPassengerList(flightPassengerDetail);
                }
                i++;
            }

            //明细表2----------------- 住宿费
            HotelEndorsementDetail[] hotelEndorsementDetails = new HotelEndorsementDetail[rs3.getCounts()];
            int j = 0;
            while (rs3.next()) {
                String startdate1 = rs3.getString("rzrq");
                this.writeLog("************" + startdate1);
                String enddate1 = rs3.getString("ldrq");
                this.writeLog("************" + enddate1);
                String rzcs = rs3.getString("rzcs");
                this.writeLog("************" + rzcs);

                hotelEndorsementDetails[j] = new HotelEndorsementDetail();
                hotelEndorsementDetails[j].setProductType(HotelProductTypeEnum.Domestic);
                hotelEndorsementDetails[j].setCheckInDateBegin(DateCut(startdate1));//入住开始时间
                hotelEndorsementDetails[j].setCheckInDateEnd(DateAdd(enddate1));//入住结束时间
                hotelEndorsementDetails[j].setCheckOutDateBegin(DateCut(startdate1));//离店开始时间
                hotelEndorsementDetails[j].setCheckOutDateEnd(DateAdd(enddate1));//离店结束时间
                hotelEndorsementDetails[j].setCheckInCityCodes(new String[]{rzcs});

                PassengerDetail[] flightPassengerDetail1 = new PassengerDetail[1];
                flightPassengerDetail1[0] = new PassengerDetail();
                flightPassengerDetail1[0].setName(lastname);// 有外籍人员？
                hotelEndorsementDetails[j].setPassengerList(flightPassengerDetail1);
                j++;

            }

            request.setFlightEndorsementDetails(flightEndorsementDetails);
            request.setHotelEndorsementDetails(hotelEndorsementDetails);

            this.writeLog("***********" + request);

            try {
                SetApprovalResponse res = approvalLocator.getws().setApproval(request);
                this.writeLog("携程返回信息： " + res.getStatus().getMessage());
            } catch (Exception e) {
                this.writeLog("携程预推送信息异常： " + e);
            }
            this.writeLog("携程预推送信息 end=============" + TimeUtil.getCurrentTimeString() + " request: " + requestid);

        }

        return SUCCESS;
    }
}
