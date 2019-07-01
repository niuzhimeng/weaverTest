package com.weavernorth.workflow;

import com.weavernorth.util.LogUtil;
import weaver.conn.RecordSet;
import weaver.conn.RecordSetTrans;
import weaver.formmode.interfaces.action.BaseAction;
import weaver.general.Util;
import weaver.soa.workflow.request.RequestInfo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UnifiedNum extends BaseAction {

    @Override
    public String execute(RequestInfo request) {
        LogUtil.doWriteLog("====节点后附加操作，合同签订流程统一流水操作====");
        RecordSet RsSelectTabel = new RecordSet();
        RecordSet rs = new RecordSet();

        String strUnifiedNum_Old = "";

        String strMaxNum_Old = "";

        String requestid = request.getRequestid();

        String workflowid = request.getWorkflowid();

        String strTablename = "";
        RecordSetTrans trs = request.getRequestManager().getRsTrans();
        RecordSetTrans trs1 = request.getRsTrans();
        String strSearchFormid = "select tablename from workflow_bill where id=(select formid from workflow_base where id=" +
                workflowid + ")";
        RsSelectTabel.executeSql(strSearchFormid);
        if (RsSelectTabel.next()) {
            strTablename = Util.null2String(RsSelectTabel
                    .getString("tablename"));
        }

        // 合同签订流程
        if ("137".equals(workflowid)) {
            String strUnifiedNum = "";

            String sql = "select * from " + strTablename + " where requestid='" +
                    requestid + "'";
            rs.execute(sql);
            if (rs.first()) {
                strUnifiedNum_Old = Util.null2String(rs.getString("htbh"));
                strMaxNum_Old = getMaxLSH(strTablename);
                strUnifiedNum = getCurrentYear() +
                        getCurrentMonth() +
                        "-" +
                        getShortName("7362", rs.getString("zjlx")) +
                        "-" +
                        getShortName("7501", rs.getString("htlb")) +
                        "-" +
                        getShortName("7358", rs.getString("cbbm"), workflowid) +
                        "-" + AddContNum(strMaxNum_Old) + "-0";
                LogUtil.doWriteLog(strUnifiedNum_Old + "==合同编号==" +
                        strUnifiedNum);
            }
            if ("".equals(strUnifiedNum_Old)) {
                LogUtil.doWriteLog("===调用更新操作===" + strMaxNum_Old);
                //更新建模中合同编号
                updateMode(requestid, strUnifiedNum);
                UpdateUnfiedNum(trs1, strUnifiedNum, AddContNum(strMaxNum_Old),
                        strTablename, requestid);
            }

        }

        //框架合同
        else if ("230".equals(workflowid)) {
            String strUnifiedNum = "";
            String sql = "select * from " + strTablename + " where requestid='" +
                    requestid + "'";
            rs.execute(sql);
            if (rs.first()) {
                strUnifiedNum_Old = Util.null2String(rs.getString("htbh"));
                strMaxNum_Old = getMaxLSH(strTablename);
                strUnifiedNum = getCurrentYear() +
                        getCurrentMonth() +
                        "-" +
                        getShortName("12550", rs.getString("zjlx")) +
                        "-" +
                        getShortName("12580", rs.getString("htlb")) +
                        "-" +
                        getShortName("12546", rs.getString("cbbm"), workflowid) +
                        "-" + AddContNum(strMaxNum_Old) + "-0";
                LogUtil.doWriteLog(strUnifiedNum_Old + "==合同编号==" +
                        strUnifiedNum);
            }
            if ("".equals(strUnifiedNum_Old)) {
                LogUtil.doWriteLog("===调用更新操作===" + strMaxNum_Old);
                //更新建模中合同编号
                updateMode(requestid, strUnifiedNum);
                UpdateUnfiedNum(trs1, strUnifiedNum, AddContNum(strMaxNum_Old), strTablename, requestid);

            }


        }
        //pc 合同签订  V1:154 V2:255
        else if ("255".equals(workflowid)) {
            String strUnifiedNum = "";

            String sql = "select * from " + strTablename + " where requestid='" +
                    requestid + "'";
            rs.execute(sql);
            if (rs.first()) {
                strUnifiedNum_Old = Util.null2String(rs.getString("htbh"));
                strMaxNum_Old = getMaxLSH(strTablename);
                strUnifiedNum = getCurrentYear() +
                        getCurrentMonth() +
                        "-" +
                        getShortName("9603", rs.getString("zjlx")) +
                        "-" +
                        getShortName("10243", rs.getString("htlb")) +
                        "-" +
                        getShortName("9599", rs.getString("cbbm"), workflowid) +
                        "-" + AddContNum(strMaxNum_Old) + "-0";
                LogUtil.doWriteLog(strUnifiedNum_Old + "==合同编号==" +
                        strUnifiedNum);
            }
            if ("".equals(strUnifiedNum_Old)) {
                LogUtil.doWriteLog("===调用更新操作===" + strMaxNum_Old);
                //更新建模中合同编号
                updateMode(requestid, strUnifiedNum);
                UpdateUnfiedNum(trs1, strUnifiedNum, AddContNum(strMaxNum_Old),
                        strTablename, requestid);
            }
        }

        // DBN合同签订流程
        else if ("364".equals(workflowid)) {
            LogUtil.doWriteLog("DBN合同签订流程统一流水操作==== START");
            String strUnifiedNum = "";

            String sql = "select * from " + strTablename + " where requestid='" + requestid + "'";
            rs.execute(sql);
            if (rs.first()) {
                strUnifiedNum_Old = Util.null2String(rs.getString("htbh"));
                strMaxNum_Old = getMaxLSH(strTablename);
                strUnifiedNum = getCurrentYear() +
                        getCurrentMonth() +
                        "-" +
                        getShortName("14130", rs.getString("zjlx")) +
                        "-" +
                        getShortName("14212", rs.getString("htlb")) +
                        "-" +
                        getShortName("14126", rs.getString("cbbm"), workflowid) +
                        "-" + AddContNum(strMaxNum_Old) + "-0";
                LogUtil.doWriteLog(strUnifiedNum_Old + "==合同编号==" +
                        strUnifiedNum);
                LogUtil.doWriteLog("strUnifiedNum=== " + strUnifiedNum);
            }
            if ("".equals(strUnifiedNum_Old)) {
                LogUtil.doWriteLog("===调用更新操作===" + strMaxNum_Old);
                //更新建模中合同编号
                updateMode(requestid, strUnifiedNum);
                UpdateUnfiedNum(trs1, strUnifiedNum, AddContNum(strMaxNum_Old),
                        strTablename, requestid);
            }
            LogUtil.doWriteLog("DBN合同签订流程统一流水操作==== END");
        }

        return "1";
    }

    public static String getMaxLSH(String tablename) {
        RecordSet rs = new RecordSet();

        String strLSH = "";

        String sql = "select max(fm.lsh_kf) lsh from (select lsh_kf from formtable_main_79 fm1 where substring(htbh,0,3)='" + getCurrentYear() +
                "' union all " +
                "select lsh_kf from formtable_main_52 fm2 where substring(htbh,0,3)='" + getCurrentYear() +
                "' union all " +
                "select lsh_kf from formtable_main_205 fm2 where substring(htbh,0,3)='" + getCurrentYear() + "' ) fm";
        LogUtil.doWriteLog("查询最大流水号的sql： " + sql);
        rs.execute(sql);
        if (rs.first()) {
            strLSH = Util.null2String(rs.getString("lsh"));
            if ("".equals(strLSH)) {
                strLSH = "0000";
            }
            LogUtil.doWriteLog("==原有最大流水号==" + strLSH);
        }
        return strLSH;
    }

    public static String AddContNum(String ContNum) {
        String strResult = "";

        if (ContNum.equals("")) {
            ContNum = "0000";
        }

        int s =
                (getMaxCodeSeq("20" + getCurrentYear()) >
                        Integer.parseInt(ContNum) ? getMaxCodeSeq("20" + getCurrentYear()) :
                        Integer.parseInt(ContNum)) + 1;
        strResult = String.valueOf(s);
        if (strResult.length() == 1) {
            strResult = "000" + strResult;
        } else if (strResult.length() == 2) {
            strResult = "00" + strResult;
        } else if (strResult.length() == 3) {
            strResult = "0" + strResult;
        }

        LogUtil.doWriteLog("==原有最大流水号+1==" + strResult);
        return strResult;
    }

    public static String getCurrentYear() {
        SimpleDateFormat sdf = new SimpleDateFormat("yy");
        Date date = new Date();
        return sdf.format(date);
    }

    public static String getCurrentMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        Date date = new Date();
        return sdf.format(date);
    }

    public static int getMaxCodeSeq(String year) {
        RecordSet rs = new RecordSet();

        int intCode = 0;

        String sql = "select max(sequenceId ) sequenceId from workflow_codeSeq where yearid='" +
                year + "' and (workflowid=137 or workflowid=255)";
        rs.execute(sql);
        if (rs.first()) {
            intCode = rs.getInt("sequenceId");
        }
        return intCode;
    }

    public static String getShortName(String fieldid, String fieldValue) {
        RecordSet rs = new RecordSet();

        String strMsg = "";
        String sql = "select shortNameSetting from workflow_shortNameSetting where fieldid='" +
                fieldid + "' and fieldValue='" + fieldValue + "'";
        rs.execute(sql);
        if (rs.first()) {
            strMsg = rs.getString("shortNameSetting");
        }
        return strMsg;
    }

    public static String getShortName(String fieldid, String fieldValue, String workflowid) {
        RecordSet rs = new RecordSet();

        String strMsg = "";
        String sql = "select abbr from workflow_deptAbbr where fieldId='" +
                fieldid + "' and fieldValue='" + fieldValue + "'";
        rs.execute(sql);
        if (rs.first()) {
            strMsg = rs.getString("abbr");
        }
        return strMsg;
    }

    public boolean updateMode(String requestid, String UnfiedNum) {
        boolean reVal = false;
        if (!requestid.equals("") && !UnfiedNum.equals("")) {
            RecordSet rs1 = new RecordSet();
            String updateSql = " update uf_httz set htbh = '" + UnfiedNum + "' where ylc='" + requestid + "'";
            LogUtil.doWriteLog("updateSql(更新建模中编号)：" + updateSql);
            if (rs1.executeSql(updateSql)) {
                reVal = true;
                LogUtil.doWriteLog("更新成功");
            } else {
                LogUtil.doWriteLog("更新失败");
            }

        }
        return reVal;
    }

    public static void UpdateUnfiedNum(RecordSetTrans trans, String UnfiedNum, String Num, String tablename, String requestid) {
        RecordSet rs1 = new RecordSet();
        RecordSet rs2 = new RecordSet();
        RecordSet rs3 = new RecordSet();

        String sql1 = "update " + tablename + " set lsh_kf = '" + Num +
                "' where requestid='" + requestid + "'";
        rs1.execute(sql1);
        LogUtil.doWriteLog("==修改最大流水号==" + sql1);

        String sql2 = "update " + tablename + " set htbh = '" + UnfiedNum +
                "' where requestid='" + requestid + "'";
        rs2.execute(sql2);
        LogUtil.doWriteLog("==赋值流程表单上的合同编号==" + sql2);

        String sql3 = "update workflow_requestbase set requestmark='" + UnfiedNum + "' where requestid='" + requestid + "'";
        LogUtil.doWriteLog("==流程基础表的合同编号==" + sql3);
        try {
            if (trans != null) {
                LogUtil.doWriteLog("==Trans==");
                trans.executeSql(sql3);
            } else {
                LogUtil.doWriteLog("==rs3==");
                rs3.executeSql(sql3);
            }

        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.doWriteLog("==赋值系统保存的合同编号异常==" + e);
        }
        LogUtil.doWriteLog("==赋值系统保存的合同编号==" + sql3);
    }
}