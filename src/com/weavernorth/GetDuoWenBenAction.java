package com.weavernorth;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 获取多行文本测试
 */
public class GetDuoWenBenAction extends BaseAction {
    private Pattern pattern = Pattern.compile(">(.*?)<");

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

        this.writeLog("获取多行文本测试 Start requestid=" + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {
            // 查询主表
            recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");
            if (recordSet.next()) {
                String wby = recordSet.getString("wby");
                this.writeLog("wby: " + wby);
                Element root = DocumentHelper.createElement("GLVoucherList");
                Element SupplierList = root.addElement("VoucherList");
                SupplierList.addElement("GLVoucherHead").setText(wby);

                this.writeLog("root: " + root.asXML());

            }

            this.writeLog("获取多行文本测试 End ===============");
        } catch (Exception e) {
            this.writeLog("获取多行文本测试 异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("获取多行文本测试 异常： " + e);
            return "0";
        }

        return "1";
    }

    private String clearHtml(String content) {
        String returnStr = "";
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            returnStr = matcher.group(1);
        }

        return returnStr;
    }
}
