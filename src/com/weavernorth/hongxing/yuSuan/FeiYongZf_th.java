package com.weavernorth.hongxing.yuSuan;

import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 费用支付退回至第一节点
 */
public class FeiYongZf_th extends BaseAction {

    private BaseBean baseBean = new BaseBean();

    @Override
    public String execute(RequestInfo requestInfo) {
        baseBean.writeLog("费用支付退回第一节点接口执行---------------------" + TimeUtil.getCurrentTimeString());

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
            kyje += zfje;
            spzje -= zfje;

            recordSet.executeSql("update uf_sqzf set spzje = '" + spzje + "', kyje = '" + kyje + "' where fysqbh = '" + fysqbh + "'");
        } catch (Exception e) {
            baseBean.writeLog("费用支付退回第一节点接口异常：" + e);
        }
        return "1";
    }
}
