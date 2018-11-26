package com.weavernorth.hongxing.yuSuan;

import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 费用申请退回至第一节点
 */
public class FeiYongSq_th extends BaseAction {

    private BaseBean baseBean = new BaseBean();

    @Override
    public String execute(RequestInfo requestInfo) {
        baseBean.writeLog("费用申请退回第一节点接口执行---------------------" + TimeUtil.getCurrentTimeString());
        try {
            String tableName = requestInfo.getRequestManager().getBillTableName();
            String requestId = requestInfo.getRequestid();
            RecordSet recordSet = new RecordSet();
            recordSet.executeSql("select * from " + tableName + " where requestid = '" + requestId + "'");
            String lxbm = ""; //立项编码
            double bcsqje = 0;//本次申请金额
            if (recordSet.next()) {
                lxbm = recordSet.getString("lxbm");
                bcsqje = recordSet.getDouble("bcsqje") < 0 ? 0 : recordSet.getDouble("bcsqje");
            }

            double spzje = 0;//审批中金额
            double kyje = 0;//可用金额
            recordSet.executeSql("select * from uf_lxsq where lxbm = '" + lxbm + "'");
            if (recordSet.next()) {
                kyje = recordSet.getDouble("kyje") < 0 ? 0 : recordSet.getDouble("kyje");
                spzje = recordSet.getDouble("spzje") < 0 ? 0 : recordSet.getDouble("spzje");
            }
            kyje += bcsqje;
            spzje -= bcsqje;
            recordSet.executeSql("update uf_lxsq set spzje = '" + spzje + "', kyje = '" + kyje + "' where lxbm = '" + lxbm + "'");
        } catch (Exception e) {
            baseBean.writeLog("费用申请退回第一节点接口异常： " + e);
        }
        return "1";
    }
}
