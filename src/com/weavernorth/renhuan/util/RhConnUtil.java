package com.weavernorth.renhuan.util;

import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;

import java.util.Map;

public class RhConnUtil {

    private static ModeRightInfo moderightinfo = new ModeRightInfo();
    private static BaseBean baseBean = new BaseBean();
    /**
     * 日志模块id
     */
    private static final Integer LOG_MODE_ID = 34;

    /**
     * 发票信息插入建模表
     *
     * @param fpMap 发票编号 -  发票金额
     */
    public static void insertTimedLog(Map<String, String> fpMap) {
        String currentTimeString = TimeUtil.getCurrentTimeString();
        ConnStatement statement = new ConnStatement();
        String insertSql = "insert into uf_difpcc(fph, fpje, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime)" +
                "values (?,?,  ?,?,?,?,?)";
        try {
            statement.setStatementSql(insertSql);
            for (Map.Entry<String, String> map : fpMap.entrySet()) {
                if ("".equals(map.getKey())) {
                    continue;
                }
                // 发票号
                statement.setString(1, map.getKey());
                // 发票金额
                statement.setString(2, map.getValue());

                //模块id
                statement.setInt(3, LOG_MODE_ID);
                //创建人id
                statement.setString(4, "1");
                //一个默认值0
                statement.setString(5, "0");
                statement.setString(6, com.weaver.general.TimeUtil.getCurrentTimeString().substring(0, 10));
                statement.setString(7, com.weaver.general.TimeUtil.getCurrentTimeString().substring(11));
                statement.executeUpdate();
            }
        } catch (Exception e) {
            baseBean.writeLog("插入发票信息, uf_difpcc 异常： " + e);
        } finally {
            statement.close();
            //赋权
            moderightinfo.setNewRight(true);
            RecordSet maxSet = new RecordSet();
            maxSet.executeSql("select id from uf_difpcc where MODEDATACREATEDATE + MODEDATACREATETIME >= '" + TimeUtil.timeAdd(currentTimeString, -10) + "'");

            int maxId;
            while (maxSet.next()) {
                maxId = maxSet.getInt("id");
                // 创建人id， 模块id， 该条数据id
                moderightinfo.rebuildModeDataShareByEdit(1, LOG_MODE_ID, maxId);
            }
        }
    }


}
