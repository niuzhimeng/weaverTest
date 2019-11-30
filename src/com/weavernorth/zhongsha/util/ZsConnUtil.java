package com.weavernorth.zhongsha.util;

import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;

public class ZsConnUtil {

    private static ModeRightInfo moderightinfo = new ModeRightInfo();
    private static BaseBean baseBean = new BaseBean();

    /**
     * 定时任务模块id
     */
    private static final Integer LOG_MODE_ID = 69;

    /**
     * 插入定时任务日志
     */
    public static void insertTimedLog(String tableName, String logTxt, int logNum, String logType) {
        String currentTimeString = TimeUtil.getCurrentTimeString();
        ConnStatement statement = new ConnStatement();
        String insertSql = "insert into uf_org_log(tableName, logTxt, logNum, logType, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime)" +
                "values (?,?,?,?,  ?,?,?,?,?)";
        try {
            statement.setStatementSql(insertSql);
            // 表名
            statement.setString(1, tableName);
            // 日志信息
            statement.setString(2, logTxt);
            // 插入数量
            statement.setInt(3, logNum);
            // 日志类型
            statement.setString(4, logType);

            //模块id
            statement.setInt(5, LOG_MODE_ID);
            //创建人id
            statement.setString(6, "1");
            //一个默认值0
            statement.setString(7, "0");
            statement.setString(8, com.weaver.general.TimeUtil.getCurrentTimeString().substring(0, 10));
            statement.setString(9, com.weaver.general.TimeUtil.getCurrentTimeString().substring(11));
            statement.executeUpdate();
        } catch (Exception e) {
            baseBean.writeLog("插入定时任务日志, insertTimedLog 异常： " + e);
        } finally {
            statement.close();
            //赋权
            moderightinfo.setNewRight(true);
            RecordSet maxSet = new RecordSet();
            maxSet.executeSql("select id from uf_org_log where MODEDATACREATEDATE + MODEDATACREATETIME >= '" + currentTimeString + "'");

            int maxId;
            while (maxSet.next()) {
                maxId = maxSet.getInt("id");
                // 创建人id， 模块id， 该条数据id
                moderightinfo.rebuildModeDataShareByEdit(1, LOG_MODE_ID, maxId);
            }
        }
    }

}
