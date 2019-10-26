package com.weavernorth.jiajie.invoiceaction;

import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.general.Util;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

public class InvoiceActionTj extends BaseAction {
    /**
     * 明细表（格式：_dt1）
     */
    private String mxb;
    /**
     * 发票字段名
     */
    private String fpzd;

    /**
     * 模块id
     */
    private static final Integer LOG_MODE_ID = 16064;
    private static ModeRightInfo moderightinfo = new ModeRightInfo();
    private static BaseBean baseBean = new BaseBean();

    @Override
    public String execute(RequestInfo requestInfo) {
        String requestId = requestInfo.getRequestid();
        String operateType = requestInfo.getRequestManager().getSrc();
        int formId = requestInfo.getRequestManager().getFormid();
        String tableName = "";
        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("SELECT tablename FROM workflow_bill WHERE id = '" + formId + "'");
        if (recordSet.next()) {
            tableName = recordSet.getString("tablename");
        }

        this.writeLog("发票验证接口提交Start requestid --- " + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {
            recordSet.executeQuery("select id from " + tableName + " where requestid = '" + requestId + "'");
            recordSet.next();
            int mainId = recordSet.getInt("id");

            String mxSql = "select * from " + tableName + mxb + " where mainid = " + mainId;
            this.writeLog("明细表查询sql： " + mxSql);
            recordSet.executeQuery(mxSql);
            StringBuilder fpBuilder = new StringBuilder();
            while (recordSet.next()) {
                String trim = Util.null2String(recordSet.getString(fpzd)).replaceAll("\\s*", "");
                // 过滤空的发票号码
                if (trim.length() > 0) {
                    fpBuilder.append("'").append(trim).append("',");
                }
            }

            this.writeLog("fpBuilder: " + fpBuilder.toString());
            if (fpBuilder.length() <= 0) {
                this.writeLog("表单未填写发票号");
                return "1";
            }
            fpBuilder.deleteCharAt(fpBuilder.length() - 1);
            String fpStr = fpBuilder.toString();
            this.writeLog("表单发票号码： " + fpStr);

            RecordSet fpSet = new RecordSet();
            StringBuilder errBuilder = new StringBuilder();
            String fpycSql = "select fph from uf_fpyc where fph in (" + fpStr + ")";
            this.writeLog("发票验重sql： " + fpycSql);
            fpSet.executeQuery(fpycSql);
            while (fpSet.next()) {
                errBuilder.append(fpSet.getString("fph")).append(", ");
            }

            this.writeLog("重复发票号： " + errBuilder.toString());
            if (errBuilder.length() > 0) {
                errBuilder.deleteCharAt(errBuilder.length() - 2);
                requestInfo.getRequestManager().setMessageid("110000");
                requestInfo.getRequestManager().setMessagecontent("发票号 " + errBuilder.toString() + " 重复。");
                return "0";
            }

            // 插入发票表
            fpStr = fpStr.replace("'", "");
            String[] split = fpStr.split(",");
            insertInvoiceTable(split, requestId);

            this.writeLog("发票验证接口提交End ===============");
        } catch (Exception e) {
            this.writeLog("发票验证接口提交异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("发票验证接口提交异常： " + e);
            return "0";
        }

        return "1";
    }

    private static void insertInvoiceTable(String[] invoices, String requestid) {
        String currentTimeString = TimeUtil.getCurrentTimeString();
        ConnStatement statement = new ConnStatement();
        String insertSql = "insert into uf_fpyc(fprequestid, fph, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime)" +
                "values (?,?,  ?,?,?,?,?)";
        try {
            String subDate = com.weaver.general.TimeUtil.getCurrentTimeString().substring(0, 10);
            String subTime = com.weaver.general.TimeUtil.getCurrentTimeString().substring(11);
            statement.setStatementSql(insertSql);
            for (String invoice : invoices) {
                statement.setString(1, requestid);
                statement.setString(2, invoice);
                //模块id
                statement.setInt(3, LOG_MODE_ID);
                //创建人id
                statement.setString(4, "1");
                //一个默认值0
                statement.setString(5, "0");
                statement.setString(6, subDate);
                statement.setString(7, subTime);
                statement.executeUpdate();
            }

        } catch (Exception e) {
            baseBean.writeLog("插入uf_fpyc异常： " + e);
        } finally {
            statement.close();
            //赋权
            moderightinfo.setNewRight(true);
            RecordSet maxSet = new RecordSet();
            maxSet.executeSql("select id from uf_fpyc where MODEDATACREATEDATE || MODEDATACREATETIME >= '" + TimeUtil.timeAdd(currentTimeString, -10) + "'");

            int maxId;
            while (maxSet.next()) {
                maxId = maxSet.getInt("id");
                // 创建人id， 模块id， 该条数据id
                moderightinfo.editModeDataShare(1, LOG_MODE_ID, maxId);
            }
        }
    }

    public String getMxb() {
        return mxb;
    }

    public void setMxb(String mxb) {
        this.mxb = mxb;
    }

    public String getFpzd() {
        return fpzd;
    }

    public void setFpzd(String fpzd) {
        this.fpzd = fpzd;
    }
}
