package com.weavernorth.daTang;

import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.general.TimeUtil;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 子流程归档，更新主流程明细表数据
 */
public class UpdateMainFlow extends BaseAction {

    private static final String MAIN_DETAIL_NAME = "formtable_main_322_dt1";//主流程表名

    @Override
    public String execute(RequestInfo request) {
        this.writeLog("====================子流程更新主流程明细表action执行 " + TimeUtil.getCurrentTimeString());
        String tableName = request.getRequestManager().getBillTableName();
        String requestId = request.getRequestid();
        //当前操作类型 submit：提交  reject：退回
        String src = request.getRequestManager().getSrc();
        if ("reject".equals(src)) {
            return "1";
        }

        RecordSet recordSet = new RecordSet();
        String sql = "select d.* from " + tableName + "_dt1 d left join " + tableName + " m on m.id = d.mainid where m.requestId = '" + requestId + "'";
        this.writeLog("查询sql： " + sql);
        recordSet.executeQuery(sql);
        ConnStatement connStatement = new ConnStatement();
        String updateSql = "update " + MAIN_DETAIL_NAME + " set ejbm = ?, sjbm = ?, xm = ?, gh = ?, rylb = ?," +
                "bmgg = ?, xmbh = ?, xmmc = ?, ywfx = ?, gsqzzj = ?, " +
                "sm = ? where id = ?";
        this.writeLog("更新主流程的sql： " + updateSql);
        try {
            connStatement.setStatementSql(updateSql);
            while (recordSet.next()) {
                connStatement.setString(1, recordSet.getString("ejbm"));
                connStatement.setString(2, recordSet.getString("sjbm"));
                connStatement.setString(3, recordSet.getString("xm"));
                connStatement.setString(4, recordSet.getString("gh"));
                connStatement.setString(5, recordSet.getString("rylb"));

                connStatement.setString(6, recordSet.getString("bmgg"));
                connStatement.setString(7, recordSet.getString("xmbh"));
                connStatement.setString(8, recordSet.getString("xmmc"));
                connStatement.setString(9, recordSet.getString("ywfx"));
                connStatement.setString(10, recordSet.getString("gsqzzj"));

                connStatement.setString(11, recordSet.getString("sm"));
                connStatement.setString(12, recordSet.getString("main_detail_id"));
                connStatement.executeUpdate();
            }
        } catch (Exception e) {
            this.writeLog("UpdateMainFlow 更新主流程异常：" + e);
        } finally {
            connStatement.close();
        }
        this.writeLog("====================子流程更新主流程明细表action完成 " + TimeUtil.getCurrentTimeString());
        return "1";
    }
}
