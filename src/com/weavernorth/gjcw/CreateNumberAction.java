package com.weavernorth.gjcw;

import com.alibaba.fastjson.JSONObject;
import com.weaver.general.TimeUtil;
import weaver.conn.RecordSet;
import weaver.general.Util;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

import java.util.HashMap;
import java.util.Map;

/**
 * 抵押登记入库流程生成编码Action
 * 如编号位数发生变化，需要修改sql中的截取
 */
public class CreateNumberAction extends BaseAction {

    /**
     * 明细表名称 例：_dt1
     */
    private String dtName;
    /**
     * 编码字段名
     */
    private String bmName;
    /**
     * 抵押物类别字段名
     */
    private String dywName;

    @Override
    public String execute(RequestInfo requestInfo) {
        String requestId = requestInfo.getRequestid();
        String operateType = requestInfo.getRequestManager().getSrc();
        int formId = requestInfo.getRequestManager().getFormid();
        String tableName = "";
        RecordSet updateSet = new RecordSet();
        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("SELECT tablename FROM workflow_bill WHERE id = '" + formId + "'");
        if (recordSet.next()) {
            tableName = recordSet.getString("tablename");
        }
        // 当前年份
        int currentYear = Util.getIntValue(TimeUtil.getCurrentDateString().substring(0, 4));
        String currentDate = TimeUtil.getCurrentDateString().replace("-", "");
        this.writeLog("抵押登记入库流程生成编码Action Start requestid --- " + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {
            // 判断年份是否增加
            recordSet.executeQuery("select nf from uf_dywlx");
            recordSet.next();
            int tableNf = recordSet.getInt("nf");
            if (currentYear > tableNf) {
                this.writeLog("编码归零，年份更新为： " + currentYear);
                updateSet.executeUpdate("update uf_dywlx set nf = " + currentYear + ", dqbh = 0");
            }

            // 编号 - 代字，当前编号
            Map<String, String> bhMap = new HashMap<String, String>();
            // 查询后台编号信息
            recordSet.executeQuery("select * from uf_dywlx");
            while (recordSet.next()) {
                bhMap.put(recordSet.getString("bm"), recordSet.getString("dz") + "," + recordSet.getString("dqbh"));
            }
            this.writeLog("编号信息map：" + JSONObject.toJSONString(bhMap));

            // 查询主表
            recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");
            recordSet.next();
            int mainId = recordSet.getInt("id");

            String[] dtNames = dtName.split(",");
            for (String name : dtNames) {
                this.writeLog("明细表： " + name + " 循环开始");
                String mxSelectSql = "select * from " + tableName + name + " where mainid = '" + mainId + "'";
                recordSet.executeQuery(mxSelectSql);
                while (recordSet.next()) {
                    String dywlb = recordSet.getString(dywName);
                    if (dywlb != null && !"".equalsIgnoreCase(dywlb)) {
                        String[] split = bhMap.get(dywlb).split(",");
                        int currentBh = Integer.parseInt(split[1]) + 1;
                        // 补0
                        String format = String.format("%04d", currentBh);
                        // 更新map
                        bhMap.put(dywlb, split[0] + "," + currentBh);
                        // 插入编码
                        updateSet.executeUpdate("update " + tableName + name + " set " + bmName + " = '" + split[0] + currentDate + format + "' " +
                                " where id = " + recordSet.getInt("id"));
                    }
                }
            }
            this.writeLog("更新编号记录表 uf_dywlx");
            for (Map.Entry<String, String> entry : bhMap.entrySet()) {
                String[] split = entry.getValue().split(",");
                updateSet.executeUpdate("update uf_dywlx set dqbh = '" + split[1] + "' where bm = '" + entry.getKey() + "'");
            }

            this.writeLog("抵押登记入库流程生成编码Action End ===============");
        } catch (Exception e) {
            this.writeLog("抵押登记入库流程生成编码Action 异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("抵押登记入库流程生成编码Action 异常： " + e);
            return "0";
        }

        return "1";
    }

    public String getDywName() {
        return dywName;
    }

    public void setDywName(String dywName) {
        this.dywName = dywName;
    }

    public String getDtName() {
        return dtName;
    }

    public void setDtName(String dtName) {
        this.dtName = dtName;
    }

    public String getBmName() {
        return bmName;
    }

    public void setBmName(String bmName) {
        this.bmName = bmName;
    }
}
