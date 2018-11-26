package com.weavernorth.hongxing.yuSuan;

import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 费用支付归档
 */
public class FeiYongZf_gd extends BaseAction {

    private BaseBean baseBean = new BaseBean();

    @Override
    public String execute(RequestInfo requestInfo) {
        baseBean.writeLog("费用支付归档前点接口执行---------------------" + TimeUtil.getCurrentTimeString());
        String tableName = requestInfo.getRequestManager().getBillTableName();
        String requestId = requestInfo.getRequestid();
        try {
            RecordSet recordSet = new RecordSet();
            recordSet.executeSql("select * from " + tableName + " where requestid = '" + requestId + "'");
            String fysqbh = ""; //费用申请编号
            double zfje = 0;//支付金额
            if (recordSet.next()) {
                fysqbh = recordSet.getString("fysqbh");
                zfje = recordSet.getDouble("zfje")< 0 ? 0 : recordSet.getDouble("zfje");
            }

            double spzje = 0;//审批中金额
            double yzfje = 0;//已支付金额
            recordSet.executeSql("select * from uf_sqzf where fysqbh = '" + fysqbh + "'");
            if (recordSet.next()) {
                spzje = recordSet.getDouble("spzje") < 0 ? 0 : recordSet.getDouble("spzje");
                yzfje = recordSet.getDouble("yzfje") < 0 ? 0 : recordSet.getDouble("yzfje");
            }
            yzfje += zfje;
            spzje -= zfje;

            recordSet.executeSql("update uf_sqzf set yzfje = '" + yzfje + "', spzje = '" + spzje + "' where fysqbh = '" + fysqbh + "'");
        } catch (Exception e) {
            baseBean.writeLog("费用支付归档前点接口异常： " + e);
        }
        return "1";
    }
}
