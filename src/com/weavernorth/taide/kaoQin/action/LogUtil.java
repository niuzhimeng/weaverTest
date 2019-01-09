package com.weavernorth.taide.kaoQin.action;

import com.weavernorth.taide.kaoQin.action.myWeb.DT_HR0002_OUTRet_Msg;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;

/**
 * 考勤插入日志
 */
class LogUtil {

    private static final Integer modeId = 684; // 建模表id
    private static ModeRightInfo moderightinfo = new ModeRightInfo();
    private static BaseBean baseBean = new BaseBean();

    /**
     * 插入日志表
     *
     * @param returns sap返回数组
     * @param workBh  流程编号
     */
    static void insertLog(DT_HR0002_OUTRet_Msg[] returns, String workBh) {
        if (returns != null && returns.length > 0) {
            String currentTimeString = TimeUtil.getCurrentTimeString();
            DT_HR0002_OUTRet_Msg aReturn = returns[0];
            ConnStatement statement = new ConnStatement();
            String insertSql = "insert into uf_certificate_log(lcbh, pfxx, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime)" +
                    "values (?,?, ?,?,?,?,?)";
            try {
                statement.setStatementSql(insertSql);

                statement.setString(1, workBh); // 	流程编号
                statement.setString(2, aReturn.getMSG_TYPE() + ": " + aReturn.getMESSAGE()); // 凭证返回信息

                statement.setInt(3, modeId);//模块id
                statement.setString(4, "1");//创建人id
                statement.setString(5, "0");//一个默认值0
                statement.setString(6, com.weaver.general.TimeUtil.getCurrentTimeString().substring(0, 10));
                statement.setString(7, com.weaver.general.TimeUtil.getCurrentTimeString().substring(11));
                statement.executeUpdate();
            } catch (Exception e) {
                baseBean.writeLog("LogUtil 考勤日志插入异常： " + e);
            } finally {
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
            }
        } else {
            baseBean.writeLog("LogUtil 考勤日志，sap返回数据为空");
        }
    }
}
