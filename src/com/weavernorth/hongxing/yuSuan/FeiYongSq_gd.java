package com.weavernorth.hongxing.yuSuan;

import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 费用申请归档
 */
public class FeiYongSq_gd extends BaseAction {

    private BaseBean baseBean = new BaseBean();

    @Override
    public String execute(RequestInfo requestInfo) {
        baseBean.writeLog("费用申请归档前点接口执行---------------------" + TimeUtil.getCurrentTimeString());
        try {
            RecordSet recordSet = new RecordSet();
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

            double spzje = 0;//审批中金额
            double yyje = 0;//已用金额
            recordSet.executeSql("select * from uf_lxsq where lxbm = '" + lxbm + "'");
            if (recordSet.next()) {
                spzje = recordSet.getDouble("spzje") < 0 ? 0 : recordSet.getDouble("spzje");
                yyje = recordSet.getDouble("yyje") < 0 ? 0 : recordSet.getDouble("yyje");
            }
            yyje += bcsqje;
            spzje -= bcsqje;

            recordSet.executeSql("update uf_lxsq set yyje = '" + yyje + "', spzje = '" + spzje + "' where lxbm = '" + lxbm + "'");
        } catch (Exception e) {
            baseBean.writeLog("费用申请归档前点接口异常： " + e);
        }
        return "1";
    }
}
