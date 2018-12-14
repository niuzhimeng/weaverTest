package com.weavernorth.huaLian;

import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 变更部门人数 - 新增或减少人数
 */
public class UpdatePerCountAdd extends BaseAction {

    private BaseBean baseBean = new BaseBean();

    @Override
    public String execute(RequestInfo requestInfo) {
        baseBean.writeLog("变更部门人数 - 新增或减少人数start" + TimeUtil.getCurrentTimeString());
        String tableName = requestInfo.getRequestManager().getBillTableName();
        String requestId = requestInfo.getRequestid();
        try {
            RecordSet recordSet = new RecordSet();
            recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");
            //本次需求人数
            String xzjsgwsl = "0";
            //一级部门
            int yjbm = 0;
            if (recordSet.next()) {
                xzjsgwsl = recordSet.getString("xzjsgwsl");
                yjbm = recordSet.getInt("yjbm");
            }

            baseBean.writeLog("获取到的一级部门： " + yjbm);

            //可申请人数
            int ksqrs = 0;
            //可编制人员总数
            int kbzrszs = 0;
            recordSet.executeQuery("select * from uf_rsbzxx where bm = " + yjbm);
            if (recordSet.next()) {
                ksqrs = recordSet.getInt("ksqrs") < 0 ? 0 : recordSet.getInt("ksqrs");
                kbzrszs = recordSet.getInt("kbzrszs") < 0 ? 0 : recordSet.getInt("kbzrszs");
                baseBean.writeLog("可编制人员总数: " + kbzrszs);
                baseBean.writeLog("可申请总数: " + ksqrs);
            }
            baseBean.writeLog("本次需求人数： " + Integer.parseInt(xzjsgwsl));
            kbzrszs += Integer.parseInt(xzjsgwsl);
            ksqrs += Integer.parseInt(xzjsgwsl);

            String sql = "update uf_rsbzxx set kbzrszs = '" + kbzrszs + "', ksqrs = '" + ksqrs + "' where bm = '" + yjbm + "'";
            baseBean.writeLog("变更部门人数执行sql： " + sql);
            recordSet.executeSql(sql);
        } catch (Exception e) {
            baseBean.writeLog("变更部门人数 - 提交Exception: " + e);
        }
        return "1";
    }
}
