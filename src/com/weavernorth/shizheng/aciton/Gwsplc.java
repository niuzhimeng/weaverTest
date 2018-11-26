package com.weavernorth.shizheng.aciton;

import com.weaver.general.TimeUtil;
import weaver.conn.RecordSet;
import weaver.conn.RecordSetTrans;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 市政生成编号
 */
public class Gwsplc extends BaseAction {

    @Override
    public String execute(RequestInfo requestInfo) {
        this.writeLog("-----------------------编码接口执行----------------------------------" + TimeUtil.getCurrentTimeString());
        String wh = "";
        int wjzlx = 0;
        int zbbm = 0;
        String bmdz = "";
        String tablename = requestInfo.getRequestManager().getBillTableName();
        String requestid = requestInfo.getRequestid();
        RecordSet rs = new RecordSet();
        String selectSql = "select wjlx, wh, wjzlx, zbbm from " + tablename + " where requestid = '" + requestid + "'";
        rs.execute(selectSql);
        if (rs.next()) {
            wh = rs.getString("wh");//文号
            wjzlx = rs.getInt("wjzlx");//文件子类型
            zbbm = rs.getInt("zbbm");//主办部门
        }

        this.writeLog("wh====================nzm---> " + wh);
        this.writeLog("wjzlx====================nzm---> " + wjzlx);
        this.writeLog("zbbm====================nzm---> " + zbbm);
        String selectDepSql = "select bmdz from hrmdepartmentdefined  where deptid = " + zbbm;
        rs.execute(selectDepSql);
        if (rs.next()) {
            bmdz = rs.getString("bmdz");
        }

        this.writeLog("----bmdz----" + bmdz);
        int i = wh.lastIndexOf("〔");
        String[] whstr = {wh.substring(0, i - 1), wh.substring(i - 1)};

        if (wjzlx == 0 && !whstr[0].contains(bmdz)) {
            wh = whstr[0] + bmdz + whstr[1];
        } else if (wjzlx != 1 && wjzlx != 2) {
            if (wjzlx == 3) {
                wh = whstr[0] + "纪" + bmdz + whstr[1].substring(1);
            } else if (wjzlx != 4 && wjzlx != 5) {
                if (wjzlx == 6 && !whstr[0].contains(bmdz)) {
                    if ("办".equals(bmdz)) {
                        wh = whstr[0] + "委专纪" + bmdz + whstr[1].substring(1);
                    } else {
                        //组织人事部  -党委文件-专题会-会议纪要
                        wh = whstr[0] + "委纪" + bmdz + whstr[1].substring(1);
                    }
                } else if ((wjzlx != 7 || whstr[0].contains(bmdz)) && wjzlx != 8) {
                    requestInfo.getRequestManager().setMessageid("10000");
                    requestInfo.getRequestManager().setMessagecontent("错误提示：文件子类型出错!");
                    return "1";
                }
            } else if (wjzlx == 5) {
                //办公室  -党委文件-专题会-会议纪要 多个部门发起
                //wh = whstr[0].substring(0, whstr[0].length() - 1) + "专纪" + bmdz + whstr[1].substring(1);
            }
        }
        RecordSetTrans updateWorkFlowSet = requestInfo.getRsTrans();
        String updateSql = "update " + tablename + " set wh = '" + wh + "'  where requestid = '" + requestid + "'";
        String updateWorkFlow = "update workflow_requestbase set requestmark = '" + wh + "' WHERE requestid = '" + requestid + "'";

        this.writeLog(updateSql);
        this.writeLog(updateWorkFlow);

        boolean flag = rs.execute(updateSql);
        this.writeLog("更新workflow_requestbase开始");
        try {
            //updateWorkFlowSet.setAutoCommit(false);
            updateWorkFlowSet.executeSql(updateWorkFlow);
            //updateWorkFlowSet.commit();
        } catch (Exception e) {
            //updateWorkFlowSet.rollback();
            this.writeLog("RecordSetTrans报错" + TimeUtil.getCurrentTimeString());
        }
        this.writeLog("更新workflow_requestbase结束");
        if (!flag) {
            this.writeLog("----123333111----");
            requestInfo.getRequestManager().setMessageid("10000");
            requestInfo.getRequestManager().setMessagecontent("错误提示：发文编号修改失败!");
            return "1";
        } else {
            return "1";
        }
    }
}
