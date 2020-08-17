package com.weavernorth.tezhong;

import weaver.conn.RecordSet;
import weaver.general.TimeUtil;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 创建批次号
 */
public class CreateBatchNumber extends BaseAction {

    @Override
    public String execute(RequestInfo requestInfo) {
        String requestId = requestInfo.getRequestid();
        String operateType = requestInfo.getRequestManager().getSrc();
        int formId = requestInfo.getRequestManager().getFormid();
        String tableName = "";
        RecordSet recordSet = new RecordSet();
        RecordSet updateSet = new RecordSet();
        recordSet.executeQuery("SELECT tablename FROM workflow_bill WHERE id = " + formId);
        if (recordSet.next()) {
            tableName = recordSet.getString("tablename");
        }

        this.writeLog("创建批次号 Start requestid=" + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {
            // 查询主表
            recordSet.executeQuery("select * from " + tableName + " where requestid = " + requestId);
            recordSet.next();

            String currYear = TimeUtil.getCurrentDateString().substring(0, 4); // 当前年份

            String sqlx = recordSet.getString("sqlx"); // 申请类型
            String sfjm = recordSet.getString("sfjm"); // 是否军贸
            String xh = recordSet.getString("xh"); // 型号
            String cpdh = recordSet.getString("cpdh"); // 产品代号
            String pch = recordSet.getString("pch"); // 批次号

            int sl = recordSet.getInt("sl"); // 数量
            int lsh = "".equals(recordSet.getString("lsh")) ? 0 : recordSet.getInt("lsh"); // 最大流水号

            // Hd+4位当前年份+批次号+3位流水-Hd+4位当前年份+批次号+3位流水
            String hdStr = "";
            if ("0".equals(sfjm)) {
                hdStr = "Hd";
            }

            String batchNumberTemp = hdStr + currYear + pch;
            int endNum = lsh + sl;

            // 流水号进行补0
            String lshStr = String.format("%03d", lsh + 1);
            String endNumStr = String.format("%03d", endNum);

            String BatchNumEnd = batchNumberTemp + lshStr + "-" + batchNumberTemp + endNumStr;

            String updateSql = "update " + tableName + " set bh = '" + BatchNumEnd + "' where requestid = " + requestId;
            this.writeLog("更新编号语句： " + updateSql);
            updateSet.executeUpdate(updateSql);

            // 更新流水号序列表
            String lshTable = "select * from uf_lshjlb where cpdh = '" + cpdh + "' and pch = '" + pch + "'";
            recordSet.executeQuery(lshTable);
            this.writeLog("查询序列号表sql: " + lshTable);
            if (recordSet.next()) {
                this.writeLog("更新序列号表");
                updateSet.executeUpdate("update uf_lshjlb set lsh = ? where cpdh = ? and pch = ?",
                        endNum, cpdh, pch);
            } else {
                this.writeLog("新增序列号表");
                updateSet.executeUpdate("insert into uf_lshjlb(cpdh, pch, lsh, sqlx) values(?,?,?,?)",
                        cpdh, pch, endNum, sqlx);
            }

            this.writeLog("创建批次号 End ===============");
        } catch (Exception e) {
            this.writeLog("创建批次号 异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("创建批次号 异常： " + e);
            return "0";
        }

        return "1";
    }
}
