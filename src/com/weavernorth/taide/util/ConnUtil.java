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
     */
    public static void insertLogCoon(String logStr, String workBh, String sendJson) {
        try {
            insertLogCoonReal(logStr, workBh, sendJson);
        } catch (Exception e) {
            try {
                insertLogCoonReal(logStr, workBh, "json过大，请前往日志查看。");
            } catch (Exception e1) {
                baseBean.writeLog("ConnUtil insertLogCoon异常： " + e1);
            }
        }

    }

    private static void insertLogCoonReal(String logStr, String workBh, String sendJson) throws Exception {
        // 建模表id
        int modeId = getModeIdByType(5);

        if (logStr != null && logStr.length() > 0) {
            String currentTimeString = TimeUtil.getCurrentTimeString();

            ConnStatement statement = new ConnStatement();
            String insertSql = "insert into uf_certificate_log(lcbh, pfxx, jasonxx, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime)" +
                    "values (?,?,?, ?,?,?,?,?)";

            statement.setStatementSql(insertSql);

            statement.setString(1, workBh); // 	流程编号
            statement.setString(2, logStr); // 凭证返回信息
            statement.setString(3, sendJson); // 发送json信息

            statement.setInt(4, modeId);//模块id
            statement.setString(5, "1");//创建人id
            statement.setString(6, "0");//一个默认值0
            statement.setString(7, com.weaver.general.TimeUtil.getCurrentTimeString().substring(0, 10));
            statement.setString(8, com.weaver.general.TimeUtil.getCurrentTimeString().substring(11));
            statement.executeUpdate();

            statement.close();
            //赋权
            moderightinfo.setNewRight(true);
            RecordSet maxSet = new RecordSet();
            maxSet.executeSql("select id from uf_certificate_log where MODEDATACREATEDATE || ' ' || MODEDATACREATEDATE >= '" + TimeUtil.timeAdd(currentTimeString, -60) + "'");

            int maxId;
            while (maxSet.next()) {
                maxId = maxSet.getInt("id");
                moderightinfo.editModeDataShare(1, modeId, maxId);//创建人id， 模块id， 该条数据id
            }

        } else {
            baseBean.writeLog("LogUtil 考勤日志，sap返回数据为空");
        }
    }

}
