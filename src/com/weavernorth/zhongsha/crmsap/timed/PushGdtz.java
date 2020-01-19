package com.weavernorth.zhongsha.crmsap.timed;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoTable;
import com.weaver.general.BaseBean;
import com.weavernorth.zhongsha.crmsap.ZhsPoolThree;
import com.weavernorth.zhongsha.util.ZsConnUtil;
import weaver.conn.RecordSet;
import weaver.general.TimeUtil;
import weaver.general.Util;
import weaver.interfaces.schedule.BaseCronJob;

public class PushGdtz extends BaseCronJob {

    private RecordSet connSet = new RecordSet();
    private BaseBean baseBean = new BaseBean();
    private String exeDate = "";

    public PushGdtz() {
    }

    public PushGdtz(String exeDate) {
        this.exeDate = exeDate;
    }

    @Override
    public void execute() {
        baseBean.writeLog("PushGdtz推送工单台账Start=========== " + TimeUtil.getCurrentTimeString());
        RecordSet recordSet = new RecordSet();
        try {
            String selectDate = "";
            if (!"".equals(exeDate)) {
                selectDate = exeDate;
            } else {
                String currentDateString = TimeUtil.getCurrentDateString();
                selectDate = TimeUtil.dateAdd(currentDateString, -1);
            }

            JCoDestination jCoDestination = ZhsPoolThree.getJCoDestination();
            jCoDestination.ping();
            baseBean.writeLog("ping 通=====");
            JCoFunction function = jCoDestination.getRepository().getFunction("ZFM_CRM_OA_INF_PMORD_RE");

            // 获取输入表
            JCoTable insertTable = function.getTableParameterList().getTable("IT_PMORD_R");

            recordSet.executeQuery("select * from uf_gdtzmxb where rq = '" + selectDate + "' and tbzt != 'S'");
            int counts = recordSet.getCounts();
            baseBean.writeLog("工单台账推送数据数量： " + counts + ", 数据日期： " + selectDate);

            int i = 0;
            while (recordSet.next()) {
                insertTable.appendRow();
                insertTable.setRow(i);
                insertTable.setValue("AUFNR", getSysByFiled("gdh", "uf_gdtz", "id", recordSet.getString("gdh"))); // 工单号
                insertTable.setValue("VORNR", recordSet.getString("gxh")); // 工序
                insertTable.setValue("LTXA1", recordSet.getString("gxms")); // 工序描述
                insertTable.setValue("ISDD", recordSet.getString("sjksrq")); // 实际开始日期
                insertTable.setValue("IEDD", recordSet.getString("sjjsrq")); // 实际结束日期

                insertTable.setValue("ISMNW", recordSet.getString("zgs")); // 总工时
                insertTable.setValue("LIFNR", getSysByFiled("crmcode", "crm_customerinfo",
                        "id", recordSet.getString("xzsgdw"))); // 施工单位

                i++;
            }

            // 调用sap接口
            function.execute(jCoDestination);
            baseBean.writeLog("调用接口结束===========");
            JCoParameterList list = function.getTableParameterList();
            JCoTable returnTable = list.getTable("IT_OUTPUT_MSG");

            int numRows = returnTable.getNumRows();
            baseBean.writeLog("headList返回表行数： " + numRows);
            RecordSet updateSet = new RecordSet();
            String updateSq = "update uf_gdtzmxb set tbzt = ?, tbbz = ? where gdh = ?";
            int successCount = 0;
            int errorCount = 0;
            for (int j = 0; j < numRows; j++) {
                returnTable.setRow(j);
                String ddh = returnTable.getString("AUFNR"); // 订单号
                String hxzt = Util.null2String(returnTable.getString("ZSTATE")).trim(); // 回写订单状态
                String xxwb = returnTable.getString("ZMSG"); // 消息文本
                baseBean.writeLog("订单号: " + ddh + ", 回写订单状态: " + hxzt + ", 消息文本: " + xxwb);
                updateSet.executeUpdate(updateSq, hxzt, xxwb, ddh);
                if ("S".equalsIgnoreCase(hxzt)) {
                    successCount++;
                } else {
                    errorCount++;
                }
            }

            // 插入日志表
            ZsConnUtil.insertTimedLog("uf_gdtzmxb", "工单台账推送给sap执行完成，共计 " + counts + "条, " +
                            "成功 " + successCount + "条, 失败 " + errorCount + " 条。",
                    counts, "工单台账明细数据推送");
        } catch (Exception e) {
            baseBean.writeLog("PushGdtz推送工单台账error: " + e);
        }

        baseBean.writeLog("PushGdtz推送工单台账执行End=========== " + TimeUtil.getCurrentTimeString());
    }

    public String getExeDate() {
        return exeDate;
    }

    public void setExeDate(String exeDate) {
        this.exeDate = exeDate;
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
