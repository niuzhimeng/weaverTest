package com.weavernorth.zhongsha.crmsap.timed;

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
import weaver.general.Util;
import weaver.interfaces.schedule.BaseCronJob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetGdtz extends BaseCronJob {

    private BaseBean baseBean = new BaseBean();
    private static final Integer mainModeId = 71; // 正式环境
    private static final Integer detailModeId = 68; // 正式环境

//    private static final Integer mainModeId = 69; // 测试
//    private static final Integer detailModeId = 79; // 测试

    private RecordSet connSet = new RecordSet();
    private String version; // 版本

    @Override
    public void execute() {
        baseBean.writeLog("GetGdtz获取工单台账Start=========== " + TimeUtil.getCurrentTimeString());
        try {
            JCoDestination jCoDestination = ZhsPoolThree.getJCoDestination();
            jCoDestination.ping();
            baseBean.writeLog("ping 通=====");
            JCoFunction function = jCoDestination.getRepository().getFunction("ZFM_CRM_OA_INF_PMORD_GET");

            // 版本（默认为空， 手动触发时，由前台传入）
            baseBean.writeLog("发送版本： " + this.version);
            function.getImportParameterList().setValue("IV_REVNR", version);
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
            recordSet.executeQuery("select gdh from uf_gdtz");
            while (recordSet.next()) {
                mainList.add(recordSet.getString("gdh"));
            }

            List<String> detailList = new ArrayList<String>();
            recordSet.executeQuery("select gxh, gdhwb from uf_gdtzmxb");
            while (recordSet.next()) {
                detailList.add(recordSet.getString("gxh") + recordSet.getString("gdhwb"));
            }

            recordSet.executeQuery("select loginid, id from hrmresource where loginid != ''");
            Map<String, String> loginIdMap = new HashMap<String, String>();
            while (recordSet.next()) {
                loginIdMap.put(Util.null2String(recordSet.getString("loginid")).toLowerCase(), recordSet.getString("id"));
            }

            String mainSqlInsert = "insert into uf_gdtz(gdh, bbh, gdms, xtzt, xttj, " +
                    "pmzylx, gnwz, wzms, zz, sbhms, " +
                    "sgkssj, sgjssj, gsfy, cjr, cjsj, " +
                    "jhhm, yxj, " +
                    "formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime)" +
                    " values(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?, ?,?,?,?,?)";
            String mainSqlUpdate = "update uf_gdtz set gdh = ?, bbh = ?, gdms = ?, xtzt = ?, xttj = ?, " +
                    "pmzylx = ?, gnwz = ?, wzms = ?, zz = ?, sbhms = ?, " +
                    "sgkssj = ?, sgjssj = ?, gsfy = ?, cjr = ?, cjsj = ?, " +
                    "jhhm = ?, yxj = ? " +
                    "where gdh = ?";
            Object[] mains = new String[22];
            RecordSet insertSet = new RecordSet();
            String mainCurrentTimeString = TimeUtil.getCurrentTimeString();
            int mainInsertCount = 0;
            int mainUpdateCount = 0;
            List<String> noUseList = new ArrayList<String>(); // 不做插入的（明细表使用）
            for (int i = 0; i < numRows; i++) {
                headList.setRow(i);
                boolean ifInsert = true;
                String txt04 = Util.null2String(headList.getString("TXT04")).trim();
                String aufpl = headList.getString("AUFPL");
                if (!"下达".equals(txt04) && !"重批".equals(txt04)) {
                    noUseList.add(aufpl);
                    ifInsert = false;
                }
                mains[0] = headList.getString("AUFNR"); // 工单号
                mains[1] = headList.getString("REVNR"); // 版本号
                mains[2] = headList.getString("KTEXT"); // 工单描述
                mains[3] = txt04; // 系统状态
                mains[4] = headList.getString("ANLZU"); // 系统条件

                mains[5] = headList.getString("ILART"); // PM作业类型
                mains[6] = headList.getString("TPLNR"); // 功能位置
                mains[7] = headList.getString("PLTXT"); // 功能位置描述
                mains[8] = headList.getString("EQUNR"); // 装置
                mains[9] = headList.getString("EQKTX"); // 设备号描述

                mains[10] = headList.getString("GSTRP"); // 施工时间开始
                mains[11] = headList.getString("GLTRP"); // 施工时间结束
                mains[12] = headList.getString("WRT04"); // 估算费用
                mains[13] = Util.null2String(loginIdMap.get(Util.null2String(headList.getString("ERNAM")).toLowerCase())); // 创建人
                mains[14] = headList.getString("ERDAT"); // 创建时间

                mains[15] = aufpl; // 计划号-关联键
                mains[16] = headList.getString("PRIOK"); // 优先级

                mains[17] = String.valueOf(mainModeId);
                mains[18] = "1";
                mains[19] = "0";
                mains[20] = mainCurrentTimeString.substring(0, 10);
                mains[21] = mainCurrentTimeString.substring(11);
                if (mainList.contains(headList.getString("AUFNR"))) {
                    // 更新
                    insertSet.executeUpdate(mainSqlUpdate, mains[0], mains[1], mains[2], mains[3], mains[4],
                            mains[5], mains[6], mains[7], mains[8], mains[9],
                            mains[10], mains[11], mains[12], mains[13], mains[14],
                            mains[15], mains[16], mains[0]);
                    mainUpdateCount++;
                } else {
                    // 新增
                    if (ifInsert) {
                        insertSet.executeUpdate(mainSqlInsert, mains);
                        mainInsertCount++;
                    }

                }
            }

            this.fuQuan(mainCurrentTimeString, "uf_gdtz", mainModeId);
            baseBean.writeLog("明细数据开始====================");
            String detailSqlInsert = "insert into uf_gdtzmxb(jhgylx, js, gxh, gxms, jhfy, " +
                    "jbksrq, jbjsrq, gdhwb, gdh, " +
                    "formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime ) values(?,?,?,?,?, ?,?,?,?, ?,?,?,?,?)";
            String detailSqlUpdate = "update uf_gdtzmxb set jhgylx = ?, js = ?, gxh = ?, gxms = ?, jhfy = ?, " +
                    "jbksrq = ?, jbjsrq = ?, gdhwb = ?, gdh = ? " +
                    "where gxh = ? and gdhwb = ?";
            Object[] details = new String[14];
            String detailCurrentTimeString = TimeUtil.getCurrentTimeString();
            int detailInsertCount = 0;
            int detailUpdateCount = 0;
            for (int i = 0; i < tableRows; i++) {
                tableList.setRow(i);
                String vornr = tableList.getString("VORNR");
                String aufnr = tableList.getString("AUFNR");
                String aufpl = tableList.getString("AUFPL");

                details[0] = aufpl; // 计划工艺路线号-关联键
                details[1] = tableList.getString("APLZL"); // 计数
                details[2] = vornr; // 工序号
                details[3] = tableList.getString("LTXA1"); // 工序描述
                details[4] = tableList.getString("PREIS"); // 计划费用

                details[5] = tableList.getString("SSAVD"); // 基本开始日期
                details[6] = tableList.getString("SSEDD"); // 基本结束日期
                details[7] = aufnr; // 工单号
                details[8] = getSysByFiled("id", "uf_gdtz", "gdh", aufnr); // 主表id

                details[9] = String.valueOf(detailModeId);
                details[10] = "1";
                details[11] = "0";
                details[12] = detailCurrentTimeString.substring(0, 10);
                details[13] = detailCurrentTimeString.substring(11);
                if (detailList.contains(vornr + aufnr)) {
                    // 更新
                    insertSet.executeUpdate(detailSqlUpdate, details[0], details[1], details[2], details[3], details[4],
                            details[5], details[6], details[7], details[8],
                            vornr, aufnr);
                    detailUpdateCount++;
                } else {
                    // 新增
                    if (!noUseList.contains(aufpl)) {
                        insertSet.executeUpdate(detailSqlInsert, details);
                        detailInsertCount++;
                    }

                }
            }

            this.fuQuan(detailCurrentTimeString, "uf_gdtzmxb", detailModeId);
            // 插入日志表
            ZsConnUtil.insertTimedLog("uf_gdtz", "获取工单台账主表数据更新成功，共计 " + numRows + "条，" +
                    "新增 " + mainInsertCount + " 条，更新 " + mainUpdateCount + " 条", numRows, "工单台账主表数据");
            ZsConnUtil.insertTimedLog("uf_gdtzmxb", "获取工单台账明细表数据更新成功，共计 " + tableRows + "条， " +
                    "新增 " + detailInsertCount + " 条，更新 " + detailUpdateCount + " 条", tableRows, "工单台账明细表数据");
        } catch (Exception e) {
            baseBean.writeLog("GetGdtz获取工单台账error: " + e);
        }

        baseBean.writeLog("GetGdtz获取工单台账执行End=========== " + TimeUtil.getCurrentTimeString());
    }

    private void fuQuan(String currentTimeString, String tableName, int modeId) {
        try {

            ModeRightInfo moderightinfo = new ModeRightInfo();
            moderightinfo.setNewRight(true);
            RecordSet maxSet = new RecordSet();
            maxSet.executeSql("select id from " + tableName + " where MODEDATACREATEDATE + ' ' + MODEDATACREATETIME >= '" + currentTimeString + "'");

            while (maxSet.next()) {
                int maxId = maxSet.getInt("id");
                moderightinfo.rebuildModeDataShareByEdit(1, modeId, maxId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            new BaseBean().writeLog("GetGdtz数据授权异常： " + e);
        }

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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
