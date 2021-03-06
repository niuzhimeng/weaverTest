package com.weavernorth.taide.kaoQin500.action;

import com.weavernorth.taide.kaoQin500.action.myWeb.DT_HR0002_OUTRet_Msg;
import com.weavernorth.taide.util.ConnUtil;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;

/**
 * 考勤插入日志
 */
class LogUtil {
    private static ModeRightInfo moderightinfo = new ModeRightInfo();
    private static BaseBean baseBean = new BaseBean();

    /**
     * 插入日志表
     *
     * @param returns sap返回数组
     * @param workBh  流程编号
     * @param flag    返回标记（E OR S）
     */
    static void insertLog(DT_HR0002_OUTRet_Msg[] returns, String workBh, String sendJson, String flag) {
        try {
            insertLogReal(returns, workBh, sendJson, flag);
        } catch (Exception e) {
            try {
                insertLogReal(returns, workBh, "json过大，请前往日志查看。", flag);
            } catch (Exception e1) {
                baseBean.writeLog("LogUtil insertLog异常： " + e1);
            }
        }

    }

    private static void insertLogReal(DT_HR0002_OUTRet_Msg[] returns, String workBh, String sendJson, String flag) throws Exception {
        // 建模表id
        int modeId = ConnUtil.getModeIdByType(5);

        if (returns != null && returns.length > 0) {
            String currentTimeString = TimeUtil.getCurrentTimeString();
            DT_HR0002_OUTRet_Msg aReturn = returns[0];
            ConnStatement statement = new ConnStatement();
            String insertSql = "insert into uf_certificate_log(lcbh, pfxx, jasonxx, fhzt, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime)" +
                    "values (?,?,?,?,  ?,?,?,?,?)";

            statement.setStatementSql(insertSql);

            statement.setString(1, workBh); // 	流程编号
            statement.setString(2, aReturn.getMSG_TYPE() + ": " + aReturn.getMESSAGE()); // 凭证返回信息
            statement.setString(3, sendJson); // 发送json信息
            statement.setString(4, flag); // 发送json信息

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
            //String sql = "select id from uf_certificate_log where MODEDATACREATEDATE || ' ' || MODEDATACREATETIME >= '" + TimeUtil.timeAdd(currentTimeString, -20) + "'";
            String sql = "select id from uf_certificate_log where lcbh = '" + workBh + "'";
            maxSet.executeSql(sql);

            baseBean.writeLog("建模表赋权数据量： " + maxSet.getCounts());
            baseBean.writeLog("赋权查询sql： " + sql);
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
