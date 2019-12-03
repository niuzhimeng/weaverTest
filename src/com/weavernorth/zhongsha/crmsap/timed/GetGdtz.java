package com.weavernorth.zhongsha.crmsap.timed;

import com.alibaba.fastjson.JSONObject;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoTable;
import com.weaver.general.BaseBean;
import com.weavernorth.zhongsha.crmsap.ZhsPoolThree;
import com.weavernorth.zhongsha.util.ZsConnUtil;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.TimeUtil;
import weaver.interfaces.schedule.BaseCronJob;

import java.util.ArrayList;
import java.util.List;

public class GetGdtz extends BaseCronJob {

    private BaseBean baseBean = new BaseBean();
    private static final Integer mainModeId = 67;
    private static final Integer detailModeId = 68;

    @Override
    public void execute() {
        baseBean.writeLog("GetGdtz获取工单台账Start=========== " + TimeUtil.getCurrentTimeString());
        try {
            JCoDestination jCoDestination = ZhsPoolThree.getJCoDestination();
            jCoDestination.ping();
            baseBean.writeLog("ping 通=====");
            JCoFunction function = jCoDestination.getRepository().getFunction("ZFM_CRM_OA_INF_PMORD_GET");

            // 调用sap接口
            function.execute(jCoDestination);
            baseBean.writeLog("调用接口结束===========");
            JCoParameterList list = function.getTableParameterList();
            JCoTable headList = list.getTable("LT_ORDH_OUTPUT");
            JCoTable tableList = list.getTable("LT_ORDP_OUTPUT");

            int numRows = headList.getNumRows();
            int tableRows = tableList.getNumRows();
            baseBean.writeLog("headList返回表行数： " + numRows);
            baseBean.writeLog("tableList返回表行数： " + tableRows);

            // 查询重复数据， 不做操作
            RecordSet recordSet = new RecordSet();
            List<String> mainList = new ArrayList<String>();
            recordSet.executeQuery("select jhhm from uf_gdtz");
            while (recordSet.next()) {
                mainList.add(recordSet.getString("jhhm"));
            }

            List<String> detailList = new ArrayList<String>();
            recordSet.executeQuery("select jhgylx, js from uf_gdtzmxb");
            while (recordSet.next()) {
                detailList.add(recordSet.getString("jhgylx") + recordSet.getString("js"));
            }

            String mainSql = "insert into uf_gdtz(gdh, bbh, gdms, xtzt, xttj, " +
                    "pmzylx, zz, sgkssj, sgjssj, gsfy, " +
                    "jhhm, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime)" +
                    " values(?,?,?,?,?, ?,?,?,?,?, ?, ?,?,?,?,?)";
            Object[] mains = new String[16];
            RecordSet insertSet = new RecordSet();
            String mainCurrentTimeString = TimeUtil.getCurrentTimeString();
            for (int i = 0; i < numRows; i++) {
                headList.setRow(i);
                if (mainList.contains(headList.getString("AUFPL"))) {
                    continue;
                }
                mains[0] = headList.getString("AUFNR"); // 工单号
                mains[1] = headList.getString("REVNR"); // 版本号
                mains[2] = headList.getString("KTEXT"); // 工单描述
                mains[3] = headList.getString("TXT04"); // 系统状态
                mains[4] = headList.getString("ANLZU"); // 系统条件

                mains[5] = headList.getString("ILART"); // PM作业类型
                mains[6] = headList.getString("EQUNR"); // 设备号
                mains[7] = headList.getString("GSTRP"); // 施工时间开始
                mains[8] = headList.getString("GLTRP"); // 施工时间结束
                mains[9] = headList.getString("WRT04"); // 估算费用

                mains[10] = (headList.getString("AUFPL")); // 计划工艺路线号-关联键
                mains[11] = String.valueOf(mainModeId);
                mains[12] = "1";
                mains[13] = "0";
                mains[14] = mainCurrentTimeString.substring(0, 10);
                mains[15] = mainCurrentTimeString.substring(11);
                insertSet.executeUpdate(mainSql, mains);
            }

            this.fuQuan(mainCurrentTimeString, "uf_gdtz", mainModeId);
            baseBean.writeLog("明细数据开始====================");
            String detailSql = "insert into uf_gdtzmxb(jhgylx, js, gxh, gxms, jhfy, " +
                    "jbksrq, jbjsrq, gdh," +
                    "formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime ) values(?,?,?,?,?, ?,?,?, ?,?,?,?,?)";
            Object[] details = new String[13];
            String detailCurrentTimeString = TimeUtil.getCurrentTimeString();
            for (int i = 0; i < tableRows; i++) {
                tableList.setRow(i);
                String aufpl = tableList.getString("AUFPL");
                String aplzl = tableList.getString("APLZL");
                if (detailList.contains(aufpl + aplzl)) {
                    continue;
                }
                details[0] = aufpl; // 计划工艺路线号-关联键
                details[1] = aplzl; // 计数
                details[2] = tableList.getString("VORNR"); // 工序号
                details[3] = tableList.getString("LTXA1"); // 工序描述
                details[4] = tableList.getString("PREIS"); // 计划费用

                details[5] = tableList.getString("SSAVD"); // 基本开始日期
                details[6] = tableList.getString("SSEDD"); // 基本结束日期
                details[7] = tableList.getString("AUFNR"); // 工单号

                details[8] = String.valueOf(detailModeId);
                details[9] = "1";
                details[10] = "0";
                details[11] = detailCurrentTimeString.substring(0, 10);
                details[12] = detailCurrentTimeString.substring(11);
                baseBean.writeLog("details=======" + JSONObject.toJSONString(details));
                insertSet.executeUpdate(detailSql, details);
            }

            this.fuQuan(detailCurrentTimeString, "uf_gdtzmxb", detailModeId);
            // 插入日志表
            ZsConnUtil.insertTimedLog("uf_gdtz", "获取工单台账主表数据更新成功，共计 " + numRows + "条", numRows, "工单台账主表数据");
            ZsConnUtil.insertTimedLog("uf_gdtzmxb", "获取工单台账明细表数据更新成功，共计 " + tableRows + "条", tableRows, "工单台账明细表数据");
        } catch (Exception e) {
            baseBean.writeLog("GetGdtz获取工单台账error: " + e);
        }

        baseBean.writeLog("GetGdtz获取工单台账执行End=========== " + TimeUtil.getCurrentTimeString());
    }

    private void fuQuan(String currentTimeString, String tableName, int modeId) {
        ModeRightInfo moderightinfo = new ModeRightInfo();
        moderightinfo.setNewRight(true);
        RecordSet maxSet = new RecordSet();
        maxSet.executeSql("select id from " + tableName + " where MODEDATACREATEDATE + ' ' + MODEDATACREATETIME >= '" + currentTimeString + "'");

        while (maxSet.next()) {
            int maxId = maxSet.getInt("id");
            moderightinfo.editModeDataShare(1, modeId, maxId);
        }

    }

}
