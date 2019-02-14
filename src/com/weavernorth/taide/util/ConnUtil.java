package com.weavernorth.taide.util;

import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;

public class ConnUtil {

    private static ModeRightInfo moderightinfo = new ModeRightInfo();
    private static BaseBean baseBean = new BaseBean();

    /**
     * 获取建模id
     *
     * @param type 数据类型
     * @return 建模id
     */
    public static Integer getModeIdByType(int type) {
        RecordSet recordSet = new RecordSet();
        int id = 0;
        recordSet.executeQuery("select jmid from  uf_loginInfo WHERE DATATYPE = " + type);
        if (recordSet.next()) {
            id = recordSet.getInt("jmid");
        }
        return id;
    }

    /**
     * 插入日志 - 通用
     *
     * @param logStr   日志信息
     * @param workBh   流程编号
     * @param sendJson 发送json
     * @param flag     返回标记（E OR S）
     */
    public static void insertLogCoon(String logStr, String workBh, String sendJson, String flag) {
        try {
            insertLogCoonReal(logStr, workBh, sendJson, flag);
        } catch (Exception e) {
            baseBean.writeLog("ConnUtil insertLogCoon异常： " + e);
        }
    }

    private static void insertLogCoonReal(String logStr, String workBh, String sendJson, String flag) throws Exception {
        // 建模表id
        int modeId = getModeIdByType(5);

        if (logStr != null && logStr.length() > 0) {
            String currentTimeString = TimeUtil.getCurrentTimeString();

            ConnStatement statement = new ConnStatement();
            String insertSql = "insert into uf_certificate_log(lcbh, pfxx, jasonxx, fhzt, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime)" +
                    "values (?,?,?,?,  ?,?,?,?,?)";

            statement.setStatementSql(insertSql);

            statement.setString(1, workBh); // 	流程编号
            statement.setString(2, logStr); // 凭证返回信息
            statement.setString(3, sendJson); // 发送json信息
            statement.setString(4, flag); // 返回状态

            statement.setInt(5, modeId);//模块id
            statement.setString(6, "1");//创建人id
            statement.setString(7, "0");//一个默认值0
            statement.setString(8, com.weaver.general.TimeUtil.getCurrentTimeString().substring(0, 10));
            statement.setString(9, com.weaver.general.TimeUtil.getCurrentTimeString().substring(11));
            statement.executeUpdate();

            statement.close();
            //赋权
            moderightinfo.setNewRight(true);
            RecordSet maxSet = new RecordSet();
            String sql = "select id from uf_certificate_log where lcbh = '" + workBh + "'";
            maxSet.executeSql(sql);

            int maxId;
            while (maxSet.next()) {
                maxId = maxSet.getInt("id");
                moderightinfo.editModeDataShare(1, modeId, maxId);//创建人id， 模块id， 该条数据id
            }

        } else {
            baseBean.writeLog("LogUtil 考勤日志，sap返回数据为空");
        }
    }

    /**
     * 插入定时任务日志
     *
     * @param tableName 表名
     * @param logStr    日志信息
     * @param nums      插入数量
     */
    public static void insertTimedLog(String tableName, String logStr, int nums) {
        int logModeId = ConnUtil.getModeIdByType(8);
        String currentTimeString = TimeUtil.getCurrentTimeString();
        ConnStatement statement = new ConnStatement();
        String insertSql = "insert into uf_dep_log(TABLE_NAME, logtxt, LOGNUM, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime)" +
                "values (?,?,?,  ?,?,?,?,?)";
        try {

            statement.setStatementSql(insertSql);

            statement.setString(1, tableName); // 表名
            statement.setString(2, logStr); // 日志信息
            statement.setInt(3, nums); // 插入数量

            statement.setInt(4, logModeId);//模块id
            statement.setString(5, "1");//创建人id
            statement.setString(6, "0");//一个默认值0
            statement.setString(7, com.weaver.general.TimeUtil.getCurrentTimeString().substring(0, 10));
            statement.setString(8, com.weaver.general.TimeUtil.getCurrentTimeString().substring(11));
            statement.executeUpdate();
        } catch (Exception e) {
            baseBean.writeLog("插入定时任务日志, insertTimedLog 异常： " + e);
        } finally {
            statement.close();
            //赋权
            moderightinfo.setNewRight(true);
            RecordSet maxSet = new RecordSet();
            maxSet.executeSql("select id from uf_dep_log where MODEDATACREATEDATE || ' ' || MODEDATACREATEDATE >= '" + TimeUtil.timeAdd(currentTimeString, -10) + "'");

            int maxId;
            while (maxSet.next()) {
                maxId = maxSet.getInt("id");
                moderightinfo.editModeDataShare(1, logModeId, maxId);//创建人id， 模块id， 该条数据id
            }
        }
        statement.close();
    }

}
