package com.weavernorth.zhongsha21.action;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;
import com.weavernorth.zhongsha21.util.ZhsPoolThreeTest;
import weaver.conn.RecordSet;
import weaver.general.Util;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 付款印鉴审批推送sap
 */
public class FuKuanAction extends BaseAction {

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

        this.writeLog("付款印鉴审批 Start requestid=" + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);

        try {
            JCoDestination jCoDestination = ZhsPoolThreeTest.getJCoDestination();
            jCoDestination.ping();
            this.writeLog("ping 通=====");
            JCoFunction function = jCoDestination.getRepository().getFunction("ZCM_EHQ00440");
            this.writeLog("function========== " + function);

            // 查询主表
            recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");
            recordSet.next();
            String mainId = recordSet.getString("id");
            String dbdh = recordSet.getString("dbdh"); // 打包单号
            String dbspjg = recordSet.getString("dbspjg"); // 打包审批结果
            String dbspjgVal = "0".equals(dbspjg) ? "02" : "03";
            this.writeLog("打包单号: " + dbdh + ", 打包审批结果: " + dbspjg + ", 打包审批结果转换后： " + dbspjgVal);

            JCoTable it_header = function.getTableParameterList().getTable("IT_HEADER");
            it_header.appendRow();
            it_header.setRow(0);
            it_header.setValue("ZAPPN", dbdh); // 审批编号
            it_header.setValue("ZAPPS", dbspjgVal); // 审批编号审批结果

            // 输入表
            JCoTable itMatkl = function.getTableParameterList().getTable("IT_ITEM");
            // 查询明细表
            recordSet.executeQuery("select * from " + tableName + "_dt1 where mainid = ?", mainId);
            int i = 0;
            while (recordSet.next()) {
                itMatkl.appendRow();
                itMatkl.setRow(i);
                itMatkl.setValue("ZOAID", recordSet.getString("OAfkdh")); // OA单号
                itMatkl.setValue("ZSTAT", "0".equals(recordSet.getString("spyj")) ? "06" : "07"); //OA单审批结果

                i++;
            }
            // 调用sap接口
            function.execute(jCoDestination);
            this.writeLog("调用接口结束===========");
            JCoTable et_return = function.getTableParameterList().getTable("ET_RETURN");
            int numRows = et_return.getNumRows();
            for (int j = 0; j < numRows; j++) {
                et_return.setRow(j);
                String zmstp = et_return.getString("ZMSTP"); // 消息类型
                String zmseg = Util.null2String(et_return.getString("ZMSEG")).trim(); // 消息文本
                this.writeLog("消息类型: " + zmstp + ", 消息文本: " + zmseg);
                if ("E".equalsIgnoreCase(zmstp)) {
                    requestInfo.getRequestManager().setMessageid("110000");
                    requestInfo.getRequestManager().setMessagecontent("付款印鉴审批 Error： " + "消息类型: " + zmstp + ", 消息文本: " + zmseg);
                    return "0";
                }
            }

            this.writeLog("付款印鉴审批 End ===============");
        } catch (Exception e) {
            this.writeLog("付款印鉴审批 Error： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("付款印鉴审批 Error： " + e);
            return "0";
        }

        return "1";
    }

}
