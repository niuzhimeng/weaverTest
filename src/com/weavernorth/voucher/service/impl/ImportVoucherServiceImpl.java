package com.weavernorth.voucher.service.impl;

import _197._183._104._47.ormrpc.services.WSWSVoucher.WSWSVoucherSrvProxyServiceLocator;
import com.weaver.general.BaseBean;
import com.weaver.general.Util;
import com.weavernorth.voucher.biz.*;
import com.weavernorth.voucher.service.ImportVoucherService;
import com.weavernorth.voucher.util.EasLogOutUtil;
import com.weavernorth.voucher.util.EasloginUtil;
import com.weavernorth.voucher.util.VouchLogUtil;
import weaver.conn.RecordSet;
import wsvoucher.client.WSWSVoucher;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: EAS集成    推送凭证信息
 * @author: DJC
 * @date: 2019.11.19
 * @version: 1.0
 */
public class ImportVoucherServiceImpl extends BaseBean implements ImportVoucherService {

    @Override
    public String importVoucher(String requestid, String workflowid, int userid, String tableName) {
        EasloginUtil loginUtil = new EasloginUtil();
        EasLogOutUtil logoutUtil = new EasLogOutUtil();
        VouchLogUtil logUtil = new VouchLogUtil();
        String cost_workflowid = Util.null2String(getPropValue("Eas_WorkflowId", "cost_workflowid")).trim();
        String trip_workflowid = Util.null2String(getPropValue("Eas_WorkflowId", "trip_workflowid")).trim();
        String loan_workflowid = Util.null2String(getPropValue("Eas_WorkflowId", "loan_workflowid")).trim();
        String repayment_workflowid = Util.null2String(getPropValue("Eas_WorkflowId", "repayment_workflowid")).trim();
        String travel_workflowid = Util.null2String(getPropValue("Eas_WorkflowId", "travel_workflowid")).trim();

        // 获取流程活各个版本id
        List<String> cost_workflowidList = getActiveVersionList(cost_workflowid);
        List<String> trip_workflowidList = getActiveVersionList(trip_workflowid);
        List<String> loan_workflowidList = getActiveVersionList(loan_workflowid);
        List<String> repayment_workflowidList = getActiveVersionList(repayment_workflowid);
        List<String> travel_workflowidList = getActiveVersionList(travel_workflowid);

        try {
            writeLog("-----------importVoucher start----------" + workflowid + tableName);

            if (loginUtil.IsLoginEas()) {
                WSWSVoucher[] vouchers = null;
                if (cost_workflowidList.contains(workflowid)) {
                    vouchers = new CostVocherBiz().costInfo(requestid, userid);
                } else if (trip_workflowidList.contains(workflowid)) {
                    vouchers = new TripVoucherBiz().tripInfo(requestid, userid);
                } else if (loan_workflowidList.contains(workflowid)) {
                    vouchers = new LoanVoucherBiz().loanInfo(requestid, userid);
                } else if (repayment_workflowidList.contains(workflowid)) {
                    vouchers = new RepaymentVoucherBiz().repaymentInfo(requestid, userid);
                } else if (travel_workflowidList.contains(workflowid)) {
                    vouchers = new TravelVoucherBiz().travelInfo(requestid, userid);
                }
                // 第一个参数为是否保存，选择0即可
                // 需引入现金流量时，最后二个参数为1，否则为0
                // 需直接生成提交状态的凭证时，第三个参数为1，否则为0
                WSWSVoucherSrvProxyServiceLocator locator = new WSWSVoucherSrvProxyServiceLocator();
                String[][] results = locator.getWSWSVoucher().importVoucher(vouchers, 0, 1);
                String error1 = results[0][4];
                String error2 = results[0][5];
                writeLog("----------------推送数据结束------------");
                if ("0000".equalsIgnoreCase(error1) || error1 == "0000") {
                    String code = results[0][6];
                    writeLog("----------凭证编号----------" + code);
                    logUtil.insertVouchRecord(requestid, userid, "0", "正确编码：" + error1 + error2, code);
                    logUtil.updataWorkflowVoucherLog(requestid, tableName, "正确编码：" + error1 + error2, "0", code);
                    writeLog("-----------importVoucher end----------");
                    logoutUtil.IsLogOutEas();
                    writeLog("-----------LogOutEas end----------");
                    return "0000";
                } else {
                    logUtil.insertVouchRecord(requestid, userid, "1", "错误编码：" + error1 + error2, "null");
                    logUtil.updataWorkflowVoucherLog(requestid, tableName, "错误编码：" + error1 + error2, "1", "");
                    logoutUtil.IsLogOutEas();
                    writeLog("-----------LogOutEas end----------");
                    return "错误编码：" + error1 + error2;
                }
            } else {
                writeLog("-----------EAS Login fail----------");
                logUtil.insertVouchRecord(requestid, userid, "1", "EAS账号登陆异常!", "null");
                logUtil.updataWorkflowVoucherLog(requestid, tableName, "EAS账号登陆异常!", "1", "null");
                return "error:EAS账号登陆异常!";
            }
        } catch (Exception e) {
            e.printStackTrace();
            writeLog("-----------error:接口异常----------------" + e);
            logUtil.insertVouchRecord(requestid, userid, "1", "error:接口解析异常!", "null");
            logUtil.updataWorkflowVoucherLog(requestid, tableName, "error:接口解析异常!", "1", "null");
            return "error:接口异常" + e;
        }
    }

    /**
     * 根据流程id获取各个版本流程id集合
     */
    private List<String> getActiveVersionList(String workflowId) {
        List<String> list = new ArrayList<String>();
        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("select wfversionid from workflow_versioninfo where wfid = ?", workflowId);
        while (recordSet.next()) {
            list.add(recordSet.getString("wfversionid"));
        }
        return list;

    }

}
