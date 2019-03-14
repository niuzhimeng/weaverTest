package com.weavernorth.mlc;

import com.weavernorth.mlc.webclient.*;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.integration.util.HTTPUtil;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
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
        } catch ( ParseException e ) {
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
        } catch ( ParseException e ) {
            e.printStackTrace();
        }
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.DATE, +3);// 日期加三天
        return sdf.format(rightNow.getTime());
    }
    @Override
    public String execute(RequestInfo requestInfo) {
        String Requestid = requestInfo.getRequestid();

        //获取ticket
        String ticketUrl = "https://ct.ctrip.com/corpservice/authorize/getticket";
        String appKey = "obk_mljr";
        String appSecurity = "obk_mljr";
        JSONObject map = new JSONObject();
        map.put("appKey", appKey);
        map.put("appSecurity", appSecurity);
        String ticket = HTTPUtil.doPost(ticketUrl, map);
        this.writeLog("*******************************" + ticket);
        JSONObject object1 = JSONObject.fromObject(ticket);
        String contentcode2 = object1.getString("Token");
        this.writeLog("****************************" + contentcode2);
        //从OA表单中取数据
        RecordSet rs = new RecordSet();
        rs.execute("select * from formtable_main_49 where requestid='" + Requestid + "'");
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
            this.writeLog("************"+lastname);
            //调用提前审批接口
            ApprovalLocator approvalLocator = new ApprovalLocator();
            SetApprovalRequest request = new SetApprovalRequest();
            request.setAuth(new Authentification("obk_mljr", contentcode2));
            request.setApprovalNumber(sqdh);
            request.setEmployeeID(sqrgh);
            request.setStatus(1);
            RecordSet rs1 = new RecordSet();
            rs1.execute("select * from formtable_main_49_dt2 where mainid='" + mid + "'");
            RecordSet rs3 = new RecordSet();
            rs3.execute("select * from formtable_main_49_dt3 where mainid='" + mid + "'");
            //明细表1----------------- 交通费
            while (rs1.next()) {
                String startdate = rs1.getString("ksrq");
                this.writeLog("************"+startdate);
                String enddate = rs1.getString("jsrq");
                this.writeLog("************"+enddate);
                String jtgj = rs1.getString("jtgj");
                this.writeLog("************"+jtgj);
                String cfd = rs1.getString("cfd");
                this.writeLog("************"+cfd);
                String mdd = rs1.getString("mdd");
                this.writeLog("************"+mdd);
                if (jtgj.equals("0")) {
                    FlightEndorsementDetail[] flightEndorsementDetails = new FlightEndorsementDetail[1];
                    flightEndorsementDetails[0] = new FlightEndorsementDetail();
                    flightEndorsementDetails[0].setCurrency(CurrencyType.UnKnow);
                    flightEndorsementDetails[0].setFlightWay(FlightWayType.RoundTrip);
                    flightEndorsementDetails[0].setDepartDateBegin(DateCut(startdate));
                    flightEndorsementDetails[0].setDepartDateEnd(DateAdd(enddate));
                    flightEndorsementDetails[0].setReturnDateBegin(DateCut(startdate));//出发结束时间
                    flightEndorsementDetails[0].setReturnDateEnd(DateAdd(enddate));//出发结束时间
                    flightEndorsementDetails[0].setDepartCityCodes(new String[]{cfd});//出发城市 暂写死上海
                    flightEndorsementDetails[0].setArrivalCityCodes(new String[]{mdd});//到达城市 暂写死深圳
                    // 出行人
                    PassengerDetail[] flightPassengerDetail = new PassengerDetail[1];
                    flightPassengerDetail[0] = new PassengerDetail();
                    flightPassengerDetail[0].setName(lastname);// 有外籍人员？
                    flightEndorsementDetails[0].setPassengerList(flightPassengerDetail);
                    request.setFlightEndorsementDetails(flightEndorsementDetails);
                    //明细表2----------------- 住宿费
                    while (rs3.next()) {
                        String startdate1 = rs3.getString("rzrq");
                        this.writeLog("************"+startdate1);
                        String enddate1 = rs3.getString("ldrq");
                        this.writeLog("************"+enddate1);
                        String rzcs = rs3.getString("rzcs");
                        this.writeLog("************"+rzcs);
                        HotelEndorsementDetail[] hotelEndorsementDetails = new HotelEndorsementDetail[1];
                        hotelEndorsementDetails[0] = new HotelEndorsementDetail();
                        hotelEndorsementDetails[0].setProductType(HotelProductTypeEnum.Domestic);
                        hotelEndorsementDetails[0].setCheckInDateBegin(DateCut(startdate1));//入住开始时间
                        hotelEndorsementDetails[0].setCheckInDateEnd(DateAdd(enddate1));//入住结束时间
                        hotelEndorsementDetails[0].setCheckOutDateBegin(DateCut(startdate1));//离店开始时间
                        hotelEndorsementDetails[0].setCheckOutDateEnd(DateAdd(enddate1));//离店结束时间
                        hotelEndorsementDetails[0].setCheckInCityCodes(new String[]{rzcs});
                        PassengerDetail[] flightPassengerDetail1 = new PassengerDetail[1];
                        flightPassengerDetail1[0] = new PassengerDetail();
                        flightPassengerDetail1[0].setName(lastname);// 有外籍人员？
                        hotelEndorsementDetails[0].setPassengerList(flightPassengerDetail1);
                        request.setHotelEndorsementDetails(hotelEndorsementDetails);
                        this.writeLog("***********"+request);

                    }
                }
            }
            SetApprovalResponse res = null;
            try {
                res = approvalLocator.getws().setApproval(request);
            } catch ( RemoteException e ) {
                e.printStackTrace();
            } catch ( ServiceException e ) {
                e.printStackTrace();
            }
            this.writeLog("********************************" + res.getStatus().getMessage());

        }

        return SUCCESS;
    }
}
