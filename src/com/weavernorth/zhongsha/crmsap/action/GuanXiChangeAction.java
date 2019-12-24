package com.weavernorth.zhongsha.crmsap.action;

import com.alibaba.fastjson.JSONObject;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;
import com.weavernorth.zhongsha.crmsap.ZhsPoolThree;
import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 供应商物料关系变更
 */
public class GuanXiChangeAction extends BaseAction {

    private RecordSet connSet = new RecordSet();

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

        this.writeLog("供应商物料关系变更 Start requestid=" + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {

            JCoDestination jCoDestination = ZhsPoolThree.getJCoDestination();
            jCoDestination.ping();
            this.writeLog("ping 通=====");
            JCoFunction function = jCoDestination.getRepository().getFunction("ZFM_CRM_OA_INF_ZMRHQ_UP");
            this.writeLog("function========== " + function);

            // 查询主表
            recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");
            recordSet.next();
            String mainId = recordSet.getString("id");

            // 输入结构
            String gys = recordSet.getString("gysmc");
            String gysdm = getSysByFiled("sapdm", "uf_crm_gysxx", "id", gys);
            String cgzz = recordSet.getString("cgzz");
            function.getImportParameterList().setValue("IV_LIFNR", gysdm); // 供应商
            function.getImportParameterList().setValue("IV_EKORG", cgzz); // 采购组织
            this.writeLog("供应商id: " + gys + ", 供应商代码：" + gysdm + ", 采购组织: " + cgzz);

            // 输入表
            JCoTable itMatkl = function.getTableParameterList().getTable("IT_MATKL");

            recordSet.executeQuery("select * from " + tableName + "_dt1 where mainid = '" + mainId + "'");
            int i = 0;
            while (recordSet.next()) {
                itMatkl.appendRow();
                itMatkl.setRow(i);
                itMatkl.setValue("MATKL", recordSet.getString("wlz")); // 物料组
                itMatkl.setValue("CHANGE_IND", "0".equals(recordSet.getString("czlx")) ? "I" : "D"); // 变更类型 (I-新建, D-删除)

                i++;
            }

            // 调用sap接口
            function.execute(jCoDestination);
            this.writeLog("调用接口结束===========");
            // 输出表
            JCoTable itOutputMsg = function.getTableParameterList().getTable("IT_OUTPUT_MSG");
            int numRows = itOutputMsg.getNumRows();
            for (int j = 0; j < numRows; j++) {
                itOutputMsg.setRow(j);
                String zstate = itOutputMsg.getString("ZSTATE");// 状态
                String lifnr = itOutputMsg.getString("LIFNR"); // 供应商编号
                String ekorg = itOutputMsg.getString("EKORG"); // 采购组织
                String matkl = itOutputMsg.getString("MATKL");// 物料组
                String bz = itOutputMsg.getString("ZMSEG"); // 备注
                String changeInd = itOutputMsg.getString("CHANGE_IND"); // 变更类型

                JSONObject jsonObject = new JSONObject(true);
                jsonObject.put("状态", zstate);
                jsonObject.put("供应商编号", lifnr);
                jsonObject.put("采购组织", ekorg);
                jsonObject.put("物料组", matkl);
                jsonObject.put("变更类型", changeInd);
                jsonObject.put("备注", bz);
                this.writeLog("返回信息： " + jsonObject.toJSONString());

                if (!"S".equalsIgnoreCase(zstate)) {
                    requestInfo.getRequestManager().setMessageid("120002");
                    requestInfo.getRequestManager().setMessagecontent("供应商物料关系变更异常，sap返回信息： " + jsonObject.toJSONString());
                    return "0";
                }
            }
            this.writeLog("供应商物料关系变更 End ===============");
        } catch (Exception e) {
            this.writeLog("供应商物料关系变更 异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("供应商物料关系变更 异常： " + e);
            return "0";
        }

        return "1";
    }

    /**
     * 根据某一字段查另一个字段
     *
     * @param resultField 查询的字段名
     * @param tableName   查询表名
     * @param selField    条件字段名
     */
    private String getSysByFiled(String resultField, String tableName, String whereId, String selField) {
        String returnStr = "";
        connSet.executeQuery("select " + resultField + " from " + tableName + " where " + whereId + " = '" + selField + "'");
        if (connSet.next()) {
            returnStr = connSet.getString(resultField);
        }
        return returnStr;
    }
}
