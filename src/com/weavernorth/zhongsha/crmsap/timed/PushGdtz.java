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
import weaver.interfaces.schedule.BaseCronJob;

public class PushGdtz extends BaseCronJob {

    private BaseBean baseBean = new BaseBean();
    private String exeDate = "";

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

            recordSet.executeQuery("select * from uf_gdtzmxb where rq = '" + selectDate + "'");
            int counts = recordSet.getCounts();
            baseBean.writeLog("工单台账推送数据数量： " + counts + ", 数据日期： " + selectDate);

            int i = 0;
            while (recordSet.next()) {
                insertTable.appendRow();
                insertTable.setRow(i);
                insertTable.setValue("AUFNR", recordSet.getString("gdh")); // 工单号
                insertTable.setValue("VORNR", recordSet.getString("gxh")); // 工序
                insertTable.setValue("ISDD", recordSet.getString("sjksrq")); // 工作开始
                insertTable.setValue("IEDD", recordSet.getString("sjjsrq")); // 工作结束
                insertTable.setValue("ISMNW", recordSet.getString("rgs")); // 人工时

                insertTable.setValue("IDAUR", recordSet.getString("zgs")); // 总小时
                insertTable.setValue("LIFNR", recordSet.getString("sgdw")); // 施工单位

                i++;
            }

            // 调用sap接口
            function.execute(jCoDestination);
            baseBean.writeLog("调用接口结束===========");
            JCoParameterList list = function.getTableParameterList();
            JCoTable returnTable = list.getTable("IT_OUTPUT_MSG");

            int numRows = returnTable.getNumRows();
            baseBean.writeLog("headList返回表行数： " + numRows);
            for (int j = 0; j < numRows; j++) {
                returnTable.setRow(j);
                baseBean.writeLog("订单号：" + returnTable.getString("AUFNR"));
                baseBean.writeLog("活动编号：" + returnTable.getString("VORNR"));
                baseBean.writeLog("回写订单状态：" + returnTable.getString("ZSTATE"));
                baseBean.writeLog("消息文本：" + returnTable.getString("ZMSG"));
                baseBean.writeLog("回写供应商状态：" + returnTable.getString("ZSTATE_V"));
                baseBean.writeLog("消息文本：" + returnTable.getString("ZMSG_V"));
            }

            // 插入日志表
            ZsConnUtil.insertTimedLog("uf_gdtzmxb", "工单台账推送给sap执行完成，共计 " + counts + "条", counts, "工单台账明细数据推送");
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
}
