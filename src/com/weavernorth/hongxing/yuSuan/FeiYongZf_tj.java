package com.weavernorth.hongxing.yuSuan;

import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 费用支付提交
 */
public class FeiYongZf_tj extends BaseAction {

    private BaseBean baseBean = new BaseBean();
    private ConnStatement statement = new ConnStatement();

    @Override
    public String execute(RequestInfo requestInfo) {
        baseBean.writeLog("费用支付提交接口执行---------------------" + weaver.general.TimeUtil.getCurrentTimeString());
        String tableName = requestInfo.getRequestManager().getBillTableName();
        String requestId = requestInfo.getRequestid();
        try {
            RecordSet recordSet = new RecordSet();
            recordSet.executeSql("select * from " + tableName + " where requestid = '" + requestId + "'");
            String fysqbh = ""; //费用申请编码
            double zfje = 0;//支付金额
            if (recordSet.next()) {
                fysqbh = recordSet.getString("fysqbh");
                zfje = recordSet.getDouble("zfje") < 0 ? 0 : recordSet.getDouble("zfje");
            }
            double spzje = 0;//审批中金额
            double kyje = 0;//可用金额
            recordSet.executeSql("select * from uf_sqzf where fysqbh = '" + fysqbh + "'");
            if (recordSet.next()) {
                kyje = recordSet.getDouble("kyje") < 0 ? 0 : recordSet.getDouble("kyje");
                spzje = recordSet.getDouble("spzje") < 0 ? 0 : recordSet.getDouble("spzje");
            }
            spzje += zfje;
            kyje -= zfje;

            String sql = "update uf_sqzf set spzje = ?, kyje = ? where fysqbh = ?";
            statement.setStatementSql(sql);
            statement.setString(1, Double.toString(spzje));
            statement.setString(2, Double.toString(kyje));
            statement.setString(3, fysqbh);
            statement.executeUpdate();
        } catch (Exception e) {
            this.writeLog("费用支付提交接口异常 :" + e);
        } finally {
            statement.close();
        }

        return "1";
    }
}
