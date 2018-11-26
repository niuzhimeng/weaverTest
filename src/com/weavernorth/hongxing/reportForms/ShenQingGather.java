package com.weavernorth.hongxing.reportForms;

import weaver.conn.RecordSetDataSource;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.TimeUtil;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 申请流程归档，更新统计报表
 */
public class ShenQingGather extends BaseAction {

    private ModeRightInfo modeRightInfo = new ModeRightInfo();

    @Override
    public String execute(RequestInfo requestInfo) {
        this.writeLog("================申请流程归档，更新统计报表==============");
        //当前年份
        int currentYear = Integer.parseInt(TimeUtil.getCurrentDateString().substring(0, 4));
        String tableName = requestInfo.getRequestManager().getBillTableName();
        String requestId = requestInfo.getRequestid();
        try {
            RecordSetDataSource recordSet = new RecordSetDataSource("orcl");
            String selectSql = "select * from " + tableName + " where requestid = '" + requestId + "'";
            recordSet.executeSql(selectSql);
            double bcsqje = 0;//本次申请金额
            int fylb = 0;//费用类别
            if (recordSet.next()) {
                bcsqje = recordSet.getDouble("bcsqje") < 0 ? 0 : recordSet.getDouble("bcsqje");
                fylb = recordSet.getInt("fylb");
            }
            this.writeLog("费用类别：" + fylb);
            this.writeLog("本次申请金额：" + bcsqje);

            recordSet.executeSql("select * from uf_scfyzzbb where nd = '" + currentYear + "' and fylb = '" + fylb + "'");
            if (recordSet.next()) {
                int id = recordSet.getInt("id");//数据id
                int modeId = recordSet.getInt("formmodeid");//模块id

                double ylx = recordSet.getDouble("ylx") < 0 ? 0 : recordSet.getDouble("ylx");//已立项金额
                double ysq = recordSet.getDouble("ysq") < 0 ? 0 : recordSet.getDouble("ysq");//之前已申请金额
                double ysqAll = ysq + bcsqje;//已申请总金额
                double wsq = ylx - ysqAll;//未申请

                String sql = "update uf_scfyzzbb set ysq = '" + ysqAll + "', wsq = '" + wsq + "' where nd = '" + currentYear + "' and fylb = '" + fylb + "'";
                this.writeLog("更新报表sql： " + sql);

                //赋权
                modeRightInfo.setNewRight(true);
                modeRightInfo.editModeDataShare(1, modeId, id);//创建人id， 模块id， 该条数据id
                recordSet.executeSql(sql);
            }
        } catch (Exception e) {
            this.writeLog("申请流程归档，更新统计报表异常： " + e);
        }
        return "1";
    }
}
