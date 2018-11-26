package com.weavernorth.shizheng.aciton;

import com.weaver.general.TimeUtil;
import weaver.conn.RecordSet;
import weaver.conn.RecordSetTrans;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 市政一处流程编号修改
 */
public class ShiZheng1 extends BaseAction {
    @Override
    public String execute(RequestInfo requestInfo) {
        this.writeLog("-----------------------市政一处流程编号修改执行----------------------------------" + TimeUtil.getCurrentTimeString());
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

        if ("1".equals(wjlx)) {
            //党委文件
            if ("8".equals(wjzlx)) {
                if ("0".equals(xwfs)) {
                    this.writeLog("党委文件 - 党委文件 - 上行文");
                } else {
                    this.writeLog("党委文件 - 党委文件 - 下行文");
                }
            } else if ("5".equals(wjzlx)) {
                this.writeLog("党委文件 - 党委会-会议纪要");
            } else if ("6".equals(wjzlx)) {
                this.writeLog("党委文件 - 党委专题会-会议纪要");
            }

        } else if ("2".equals(wjlx)) {
            //纪委文件
            if ("0".equals(xwfs)) {
                this.writeLog("纪委文件 - 纪委文件 - 上行文");
            } else {
                this.writeLog("纪委文件 - 纪委文件 - 下行文");
            }

        } else if ("3".equals(wjlx)) {
            //行政文件
            if ("0".equals(wjzlx)) {
                if ("0".equals(xwfs)) {
                    this.writeLog("行政文件 - 行政文件 - 上行文");
                    wh = whstr[0] + bmdz + whstr[1];
                    updateRequestName(tableName, wh, requestId, requestInfo);
                } else {
                    this.writeLog("行政文件 - 行政文件 - 下行文");
                    wh = whstr[0] + bmdz + whstr[1];
                    updateRequestName(tableName, wh, requestId, requestInfo);
                }
            } else if ("1".equals(wjzlx)) {
                this.writeLog("行政文件 - 行政文件 - 函");
            } else if ("2".equals(wjzlx)) {
                this.writeLog("行政文件 - 经理办公会-会议纪要");
            } else if ("3".equals(wjzlx)) {
                this.writeLog("行政文件 - 专题会-会议纪要");
            }
        }

        return "1";
    }

    private void updateRequestName(String tableName, String wh, String requestId, RequestInfo requestInfo) {
        //更新流程表单
        RecordSet rs = new RecordSet();
        String updateSql = "update " + tableName + " set wh = '" + wh + "'  where requestid = '" + requestId + "'";
        rs.execute(updateSql);
        //更新请求名称
        RecordSetTrans updateWorkFlowSet = requestInfo.getRsTrans();
        String updateWorkFlow = "update workflow_requestbase set requestmark = '" + wh + "' WHERE requestid = '" + requestId + "'";
        try {
            updateWorkFlowSet.executeSql(updateWorkFlow);
        } catch (Exception e) {
            this.writeLog("RecordSetTrans报错" + TimeUtil.getCurrentTimeString());
        }
    }
}
