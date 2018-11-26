package com.weavernorth.hongxing.reportForms;

import weaver.conn.RecordSetDataSource;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.TimeUtil;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 立项流程归档，更新统计报表
 */
public class LiXiangGather extends BaseAction {

    private ModeRightInfo modeRightInfo = new ModeRightInfo();

    @Override
    public String execute(RequestInfo requestInfo) {
        this.writeLog("================立项流程归档，更新统计报表==============");
        //当前年份
        int currentYear = Integer.parseInt(TimeUtil.getCurrentDateString().substring(0, 4));
        String tableName = requestInfo.getRequestManager().getBillTableName();
        String requestId = requestInfo.getRequestid();
        try {
            RecordSetDataSource recordSet = new RecordSetDataSource("orcl");
            String selectSql = "select * from " + tableName + " where requestid = '" + requestId + "'";
            recordSet.executeSql(selectSql);
            double lxje = 0;//本次立项金额
            int fylx1 = 0;//费用类别
            if (recordSet.next()) {
                lxje = recordSet.getDouble("lxje") < 0 ? 0 : recordSet.getDouble("lxje");
                fylx1 = recordSet.getInt("fylx1");
            }
            this.writeLog("费用类别：" + fylx1);
            this.writeLog("本次立项金额：" + lxje);

            recordSet.executeSql("select * from uf_scfyzzbb where nd = '" + currentYear + "' and fylb = '" + fylx1 + "'");
            if (recordSet.next()) {
                int id = recordSet.getInt("id");
                int modeId = recordSet.getInt("formmodeid");
                double ndys = recordSet.getDouble("ndys");//年度预算
                double ylx = recordSet.getDouble("ylx") < 0 ? 0 : recordSet.getDouble("ylx");//之前已立项金额
                double ylxAll = lxje + ylx;
                double wlx = ndys - ylxAll;//未立项
                String sql = "update uf_scfyzzbb set ylx = '" + ylxAll + "', wlx = '" + wlx + "' where nd = '" + currentYear + "' and fylb = '" + fylx1 + "'";
                this.writeLog("更新报表sql： " + sql);

                //赋权
                modeRightInfo.setNewRight(true);
                modeRightInfo.editModeDataShare(1, modeId, id);//创建人id， 模块id， 该条数据id
                recordSet.executeSql(sql);
            }
        } catch (Exception e) {
            this.writeLog("立项流程归档，更新统计报表异常： " + e);
        }
        return "1";
    }
}
