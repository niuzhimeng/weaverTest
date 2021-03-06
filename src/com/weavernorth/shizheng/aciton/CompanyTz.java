package com.weavernorth.shizheng.aciton;

import com.weaver.general.TimeUtil;
import weaver.conn.RecordSet;
import weaver.conn.RecordSetTrans;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 投资公司程编号修改
 */
public class CompanyTz extends BaseAction {
    @Override
    public String execute(RequestInfo requestInfo) {
        this.writeLog("-----------------------投资公司流程编号修改执行----------------------------------" + TimeUtil.getCurrentTimeString());
        String tableName = requestInfo.getRequestManager().getBillTableName();
        String requestId = requestInfo.getRequestid();

        String wh = "";//文号
        String wjlx = "";//文件类型
        String wjzlx = "";//文件子类型
        String zbbm = "";//主办部门
        String bmdz = "";//部门代字
        String xwfs = "";//行文方式

        RecordSet rs = new RecordSet();
        String selectSql = "select xwfs, wjlx, wh, wjzlx, zbbm from " + tableName + " where requestid = '" + requestId + "'";
        rs.execute(selectSql);
        if (rs.next()) {
            wh = rs.getString("wh").trim();
            wjlx = rs.getString("wjlx").trim();
            wjzlx = rs.getString("wjzlx").trim();
            zbbm = rs.getString("zbbm").trim();
            xwfs = rs.getString("xwfs").trim();
        }
        String selectDepSql = "select bmdz from hrmdepartmentdefined  where deptid = " + zbbm;
        rs.execute(selectDepSql);
        if (rs.next()) {
            bmdz = rs.getString("bmdz");
        }
        bmdz = "办";
        this.writeLog("文号 wh====================nzm---> " + wh);
        this.writeLog("文件类型 wjlx====================nzm---> " + wjlx);
        this.writeLog("文件子类型 wjzlx====================nzm---> " + wjzlx);
        this.writeLog("主办部门 zbbm====================nzm---> " + zbbm);
        this.writeLog("---- 部门代字 bmdz----" + bmdz);
        if(wh.contains(bmdz)){
            return "1";
        }
        int i = wh.lastIndexOf("〔");
        String[] whstr = {wh.substring(0, i - 1), wh.substring(i - 1)};

         if ("3".equals(wjlx)) {
            //行政文件
            if ("0".equals(wjzlx)) {
                if ("0".equals(xwfs)) {
                    this.writeLog("行政文件 - 行政文件 - 上行文");
                    wh = whstr[0] + bmdz + whstr[1];
                } else {
                    this.writeLog("行政文件 - 行政文件 - 下行文");
                    wh = whstr[0] + bmdz + whstr[1];
                }
            }
        }

        //更新流程表单
        RecordSet rs1 = new RecordSet();
        String updateSql = "update " + tableName + " set wh = '" + wh + "'  where requestid = '" + requestId + "'";
        rs1.execute(updateSql);
        //更新请求名称
        RecordSetTrans updateWorkFlowSet = requestInfo.getRsTrans();
        String updateWorkFlow = "update workflow_requestbase set requestmark = '" + wh + "' WHERE requestid = '" + requestId + "'";
        try {
            updateWorkFlowSet.executeSql(updateWorkFlow);
        } catch (Exception e) {
            this.writeLog("RecordSetTrans报错" + TimeUtil.getCurrentTimeString());
        }
        return "1";
    }
}
