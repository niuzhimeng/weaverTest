package com.weavernorth.voucher.util;

import weaver.common.DateUtil;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.general.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: EAS 推送凭证 日志 工具类
 * @author: DJC
 * @date: 2019.11.18
 * @version: 1.0
 */
public class VouchLogUtil extends BaseBean {

    /**
     * @description: 新增推送凭证日志记录
     * @param: requestID  流程id
     * userid	      人员id
     * state	     推送状态
     * desc		     描述信息
     * vouce_code 凭证编码
     */
    public void insertVouchRecord(String requestid, int userid, String state, String desc, String vouce_code) {

        try {
            RecordSet rs = new RecordSet();
            //模块id
            rs.executeQuery("Select id from modeinfo where formid = (select id from workflow_bill where tablename = 'uf_voucherlog')");
            rs.next();
            int modeinfoId = rs.getInt(1);
            writeLog("insertVouchRecord: modeinfoId-----" + modeinfoId);
            String sqlo = "INSERT INTO uf_voucherlog (account_date,state,description,voucher_number,request_number,userid,modedatacreater,modedatacreatertype," +
                    "modedatacreatedate,modedatacreatetime,formmodeid) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            rs.executeUpdate(sqlo, DateUtil.getCurrentDate(), state, desc, vouce_code, requestid, userid, userid, 0, DateUtil.getCurrentDate(), TimeUtil.getOnlyCurrentTimeString(), modeinfoId);
            writeLog("insertVouchRecord: SQL-----" + sqlo);
            //权限重构
            rs.executeQuery("select max(id) from uf_voucherlog");
            rs.next();
            int id = rs.getInt(1);
            writeLog("insertVouchRecord: id-----" + id);
            //创建人id，模块id，一条具体数据的自增id
            ModeRightInfo ModeRightInfo = new ModeRightInfo();
            ModeRightInfo.rebuildModeDataShareByEdit(userid, modeinfoId, id);

        } catch (Exception e) {
            e.printStackTrace();
            writeLog("insertVouchRecord	error: " + e);
        }

    }

    /**
     * @description: 更新推送凭证日志失败记录
     * @param: requestID  流程id
     * description  推送凭证的状态描述
     */
    public void updataFailRecord(String requestid, String description) {
        writeLog("updataFailRecord: " + requestid + "---" + description);
        try {
            RecordSet rs = new RecordSet();
            String sqlo = "update uf_voucherlog set state=1,description ='" + description + "' where request_number=" + requestid;
            rs.executeUpdate(sqlo);
        } catch (Exception e) {
            e.printStackTrace();
            writeLog("updataFailRecord	error: " + e);
        }
    }

    /**
     * @description: 更新推送凭证日志成功记录
     * @param: requestID        流程id
     * description  	推送凭证的状态描述
     * voucher_number	凭证编码
     */
    public void updataSuccessRecord(String requestid, String description, String voucher_number) {
        writeLog("updataSuccessRecord: " + requestid + "---" + description + "---" + voucher_number);
        try {
            RecordSet rs = new RecordSet();
            String sqlo = "update uf_voucherlog set state=0,voucher_number='" + voucher_number + "',description ='" + description + "' where request_number=" + requestid;
            rs.executeUpdate(sqlo);
        } catch (Exception e) {
            e.printStackTrace();
            writeLog("updataSuccessRecord	error: " + e);
        }
    }

    /**
     * @description: 查询凭证日志表中是否存在该条记录
     * @param: requestid  流程id
     */
    public boolean logIsExist(String requestid) {
        writeLog("logIsExist: " + requestid);
        try {
            RecordSet rs = new RecordSet();
            String sqlo = "SELECT id FROM uf_voucherlog WHERE request_number = ?";
            rs.executeQuery(sqlo, requestid);
            boolean flag = rs.next();
            writeLog("logIsExist	flag: " + flag);
            if (flag) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            writeLog("logIsExist	error: " + e);
            return false;
        }
    }

    /**
     * @description: 是否推送凭证
     * @param: requestid  流程id
     * tableName  表单名
     */
    public boolean isPushVoucher(String requestid, String tableName) {
        writeLog("isPushVoucher: " + requestid + "--" + tableName);
        try {
            RecordSet rs = new RecordSet();
            String sqlo = "SELECT ispush FROM " + tableName + " WHERE requestid = " + requestid;
            rs.executeQuery(sqlo);
            rs.next();
            String isPush = Util.null2String(rs.getString("ispush"));
            if (isPush == "0" || "0".equalsIgnoreCase(isPush)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            writeLog("isPushVoucher	error: " + e);
            return false;
        }
    }

    /**
     * @description: 是否推送凭证
     * @param: requestid  流程id
     * tableName  表单名
     * workflowId 流程类型workflowId
     */
    public boolean isAccountExist(String requestid, String tableName, String workflowid) {
        writeLog("isAccountExist----------" + requestid + "--" + tableName + "---" + workflowid);
        try {
            RecordSet rs = new RecordSet();
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

            String account = "";
            if (cost_workflowidList.contains(workflowid)) {
                //获取公司名称
                String sqlo = "SELECT sqdw FROM " + tableName + " WHERE requestid = " + requestid;
                rs.executeQuery(sqlo);
                rs.next();
                String sqdw = Util.null2String(rs.getString("sqdw"));//申请单位
                account = getAccountName(sqdw);
            } else if (trip_workflowidList.contains(workflowid)) {
                String sqlo = "SELECT sqdw2 FROM " + tableName + " WHERE requestid = " + requestid;
                rs.executeQuery(sqlo);
                rs.next();
                String sqdw = Util.null2String(rs.getString("sqdw2"));//申请单位
                account = getAccountName(sqdw);
            } else if (loan_workflowidList.contains(workflowid)) {
                String sqlo = "SELECT sqdw3 FROM " + tableName + " WHERE requestid = " + requestid;
                rs.executeQuery(sqlo);
                rs.next();
                String sqdw = Util.null2String(rs.getString("sqdw3"));//申请单位
                account = getAccountName(sqdw);
            } else if (repayment_workflowidList.contains(workflowid)) {
                String sqlo = "SELECT hkdw1 FROM " + tableName + " WHERE requestid = " + requestid;
                rs.executeQuery(sqlo);
                rs.next();
                String hkdw = Util.null2String(rs.getString("hkdw1"));//还款单位
                account = getXlkAccountName(hkdw);
            } else if (travel_workflowidList.contains(workflowid)) {
                String sqlo = "SELECT sqdw FROM " + tableName + " WHERE requestid = " + requestid;
                rs.executeQuery(sqlo);
                rs.next();
                String sqdw = Util.null2String(rs.getString("sqdw"));//申请单位
                account = getXlkAccountName(sqdw);
            }
            writeLog("公司名称-----account---------" + account);
            String sqlt = "select * from uf_account where isseal = 1 and oa_name = '" + account + "'";
            rs.executeQuery(sqlt);
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            writeLog("isAccountExist----error---------" + e);
            return false;
        }
    }

    /**
     * @description: 查询浏览按钮 公司名称
     * @param: sqdw  申请单位id
     */
    private String getAccountName(String sqdw) {
        String name = null;
        try {
            RecordSet rs = new RecordSet();
            String sql = "select dwmc1 from uf_zhxxdj where id = " + sqdw;
            rs.executeQuery(sql);
            rs.next();
            name = Util.null2String(rs.getString("dwmc1"));
            return name;
        } catch (Exception e) {
            e.printStackTrace();
            writeLog("获取公司名称----error---------" + e);
            return name;
        }
    }

    /**
     * @description: 查询下拉框 公司名称
     * @param: sqdw  申请单位下拉框id
     */
    private String getXlkAccountName(String sqdw) {
        String name = null;
        try {
            String fieldid = getPropValue("Eas_Infos", "fieldid");
            RecordSet rs = new RecordSet();
            String sql = "select selectname from workflow_selectitem "
                    + "where fieldid = " + fieldid + " and selectvalue = " + sqdw;
            rs.executeQuery(sql);
            rs.next();
            name = Util.null2String(rs.getString("selectname"));
            return name;
        } catch (Exception e) {
            e.printStackTrace();
            writeLog("获取下拉框公司名称----error---------" + e);
            return name;
        }
    }

    /**
     * @description: 更新流程凭证推送结果字段
     * @param: requestid    流程id
     * tableName	表单名
     * description	推送凭证的状态描述
     * status		推送状态
     * voucherCode	凭证编号
     */
    public void updataWorkflowVoucherLog(String requestid, String tableName, String description, String status, String voucherCode) {
        writeLog("updataWorkflowVoucherLog: " + requestid + "---" + description + "---" + tableName);
        writeLog("updataWorkflowVoucherLog: " + status + "---" + voucherCode);
        try {
            RecordSet rs = new RecordSet();
            String sqlo = "update " + tableName + " set status=" + status + ",remark='" + description + "',vouchercode='" + voucherCode + "' where requestid=" + requestid;
            rs.executeUpdate(sqlo);
        } catch (Exception e) {
            e.printStackTrace();
            writeLog("updataSuccessRecord	error: " + e);
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
