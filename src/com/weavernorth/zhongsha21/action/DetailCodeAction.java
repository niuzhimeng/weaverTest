package com.weavernorth.zhongsha21.action;

import com.alibaba.fastjson.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.Util;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资金安排明细表编号
 */
public class DetailCodeAction extends BaseAction {

    @Override
    public String execute(RequestInfo requestInfo) {
        String requestId = requestInfo.getRequestid();
        String operateType = requestInfo.getRequestManager().getSrc();
        int formId = requestInfo.getRequestManager().getFormid();
        String tableName = "";
        RecordSet updatedSet = new RecordSet();
        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("SELECT tablename FROM workflow_bill WHERE id = " + formId);
        if (recordSet.next()) {
            tableName = recordSet.getString("tablename");
        }

        this.writeLog("资金安排明细表编号 + 审批人赋值 Start requestid=" + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {
            Map<String, String[]> qxMap = new HashMap<String, String[]>();
            recordSet.executeQuery("select * from uf_qxsbb");
            while (recordSet.next()) {
                String[] strs = new String[2];
                strs[0] = recordSet.getString("je"); // 金额
                strs[1] = recordSet.getString("spr"); // 审批人
                qxMap.put(recordSet.getString("id"), strs);
            }
            this.writeLog("流程权限map： " + JSONObject.toJSONString(qxMap));

            // 查询主表
            recordSet.executeQuery("select * from " + tableName + " where requestid = " + requestId);
            recordSet.next();
            String mainId = recordSet.getString("id");
            String zjapxh = recordSet.getString("zjapxh"); // 资金安排序号
            this.writeLog("资金安排序号： " + zjapxh);

            List<String> spjbList = new ArrayList<String>(); // 所有审批级别的集合

            String updateSql = "update " + tableName + "_dt1 set jybh = ?, spjb = ? where id = ?";
            recordSet.executeQuery("select * from " + tableName + "_dt1 where mainid = '" + mainId + "'");
            int i = 1;
            while (recordSet.next()) {
                String id = recordSet.getString("id");
                String xm = recordSet.getString("xm"); // 项目
                double je = recordSet.getDouble("je") < 0 ? 0 : recordSet.getDouble("je"); // 金额

                String[] strings = qxMap.get(xm);
                this.writeLog("当期项目： " + xm + ", 标准： " + JSONObject.toJSONString(strings));
                double jeValue = Util.getDoubleValue(strings[0], 0); // 金额标准
                String spr = strings[1]; // 审批人标准（可能需要根据金额做判断）
                if (jeValue > 0) {
                    // 进行金额判断
                    if (je >= jeValue) {
                        spr = "0"; // CFO
                    } else {
                        spr = "1"; // 财务经理
                    }
                }
                spjbList.add(spr);
                updatedSet.executeUpdate(updateSql,
                        zjapxh + "-" + String.format("%03d", i), spr, id);
                i++;
            }

            this.writeLog("更新主表最高审批权限=====");
            String zgspjb = spjbList.contains("0") ? "0" : "1"; // 最高审批级别，只要包含CFO 就设为0
            updatedSet.executeUpdate("update " + tableName + " set zgspjb = ? where requestid = ?", zgspjb, requestId);

            this.writeLog("资金安排明细表编号 + 审批人赋值 End ===============");
        } catch (Exception e) {
            this.writeLog("资金安排明细表编号 + 审批人赋值 异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("资金安排明细表编号 异常： " + e);
            return "0";
        }

        return "1";
    }
}
