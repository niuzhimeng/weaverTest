package com.weavernorth.hongxing.yuSuan;

import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 费用申请提交
 */
public class FeiYongSq_tj extends BaseAction {
    private BaseBean baseBean = new BaseBean();

    @Override
    public String execute(RequestInfo requestInfo) {
        baseBean.writeLog("费用申请提交接口执行---------------------" + weaver.general.TimeUtil.getCurrentTimeString());
        RecordSet recordSet = new RecordSet();

        ConnStatement statement = new ConnStatement();
        try {
            int formid = requestInfo.getRequestManager().getFormid();
            String tableName = "";
            recordSet.executeQuery("SELECT tablename FROM workflow_bill WHERE id = '" + formid + "'"); // 均可获取表名
            if (recordSet.next()) {
                tableName = recordSet.getString("tablename");
            }
            String requestId = requestInfo.getRequestid();

            recordSet.executeSql("select * from " + tableName + " where requestid = '" + requestId + "'");
            String lxbm = ""; //立项编码
            double bcsqje = 0;//本次申请金额

            if (recordSet.next()) {
                lxbm = recordSet.getString("lxbm");
                bcsqje = recordSet.getDouble("bcsqje") < 0 ? 0 : recordSet.getDouble("bcsqje");
            }

            this.writeLog("立项编码： "+ lxbm);
            this.writeLog("本次申请金额： "+ bcsqje);

            double kyje = 0;//可用金额
            double spzje = 0;//审批中金额
            recordSet.executeSql("select * from uf_lxsq where lxbm = '" + lxbm + "'");
            if (recordSet.next()) {
                kyje = recordSet.getDouble("kyje") < 0 ? 0 : recordSet.getDouble("kyje");
                spzje = recordSet.getDouble("spzje") < 0 ? 0 : recordSet.getDouble("spzje");
            }

            this.writeLog("可用金额： "+ kyje);
            this.writeLog("审批中金额： "+ spzje);

            spzje += bcsqje;
            kyje -= bcsqje;

            this.writeLog("计算后可用金额： "+ kyje);
            this.writeLog("计算后审批中金额： "+ spzje);

            String sql = "update uf_lxsq  set spzje = ?, kyje = ? where lxbm = ?";
            statement.setStatementSql(sql);
            statement.setString(1, Double.toString(spzje));
            statement.setString(2, Double.toString(kyje));
            statement.setString(3, lxbm);
            statement.executeUpdate();
        } catch (Exception e) {
            this.writeLog("费用申请提交接口异常 :" + e);
        } finally {
            statement.close();
        }

        return "1";
    }
}
