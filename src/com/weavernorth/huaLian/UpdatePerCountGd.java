package com.weavernorth.huaLian;

import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 变更部门人数 - 归档
 */
public class UpdatePerCountGd extends BaseAction {

    private BaseBean baseBean = new BaseBean();

    @Override
    public String execute(RequestInfo requestInfo) {
        baseBean.writeLog("变更部门人数 - 归档start" + TimeUtil.getCurrentTimeString());
        String tableName = requestInfo.getRequestManager().getBillTableName();
        String requestId = requestInfo.getRequestid();
        try {
            RecordSet recordSet = new RecordSet();
            recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");
            // 本次需求人数
            int xqrs = 0;
            // 一级部门
            int yjbm = 0;
            if (recordSet.next()) {
                xqrs = recordSet.getInt("xqrs") < 0 ? 0 : recordSet.getInt("xqrs");
                yjbm = recordSet.getInt("yjbm");
            }

            // 在编人数
            int kbzrszs = 0;
            // 申请中人数
            int sqzrs = 0;
            recordSet.executeQuery("select * from uf_rsbzxx where bm = " + yjbm);
            if (recordSet.next()) {
                kbzrszs = recordSet.getInt("kbzrszs") < 0 ? 0 : recordSet.getInt("kbzrszs");
                sqzrs = recordSet.getInt("sqzrs") < 0 ? 0 : recordSet.getInt("sqzrs");
            }
            sqzrs -= xqrs;
            kbzrszs -= xqrs;
            recordSet.executeSql("update uf_rsbzxx set sqzrs = '" + sqzrs + "', kbzrszs = '" + kbzrszs + "' where bm = '" + yjbm + "'");
        } catch (Exception e) {
            baseBean.writeLog("变更部门人数 - 归档Exception: " + e);
        }

        return "1";
    }
}
