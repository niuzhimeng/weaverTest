//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.weaver.interfaces.workflow.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.interfaces.schedule.BaseCronJob;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.request.RequestManager;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Dealhistory extends BaseCronJob {
    RequestManager requestManager;
    private Log log = LogFactory.getLog(Dealhistory.class.getName());

    public Dealhistory() {
    }

    public void execute() {
        RecordSet rs = new RecordSet();
        BaseBean bb = new BaseBean();
        bb.writeLog("-进入文件---Dealhistory-----");
        String tablename = "";
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(d);
        this.log.info("当期日期" + dateNowStr);
        new SimpleDateFormat("HH:mm:ss");
        String dateNowStrtime = sdf.format(d);
        this.log.info("当期时间" + dateNowStrtime);
        String requestidarr = bb.getPropValue("pdfworkflowid", "requestid");
        this.log.info("配置文件中配置的所有流程id" + requestidarr);
        if (!"".equals(requestidarr) && requestidarr != null) {
            String[] requestidarrsp = requestidarr.split(",");

            for(int i = 0; i < requestidarrsp.length; ++i) {
                String requestid = requestidarrsp[i];
                String workflowsql = "select workflowid from workflow_requestbase  where requestid=" + requestid;
                this.log.info("查找workflowid的sql==" + workflowsql);
                rs.execute(workflowsql);
                rs.next();
                String workflowid = rs.getString("workflowid");
                this.log.info("循环流程的id" + requestid);
                String tabsql = "select tablename  from workflow_bill where id = (select formid from workflow_base where id=(select workflowid from workflow_requestbase  where requestid=" + requestid + ") and isbill=1)";
                this.log.info("查找流程的表");
                rs.execute(tabsql);
                rs.next();
                tablename = rs.getString("tablename");
                this.log.info("表数据" + tablename);
                String ksdate = bb.getPropValue("pdfworkflowid", "ksdate");
                String kstime = bb.getPropValue("pdfworkflowid", "kstime");
                String sqlstr = "select * from  " + tablename + " a ,workflow_requestbase b  where a.requestid=b.requestid and b.currentnodetype=3  and b.lastoperatedate>='" + ksdate + "' and  b.lastoperatetime>='" + kstime + "' and b.lastoperatedate<='" + dateNowStr + "' and a.requesid=" + requestid;
                this.log.info("查数据的sql" + sqlstr);
                rs.execute(sqlstr);

                while(rs.next()) {
                    WorkflowToDocOrPdf workflowToDocOrPdf = new WorkflowToDocOrPdf();
                    int userid = rs.getInt("creater");
                    this.log.info(rs.getString("requestid") + "请求id");
                    this.log.info(userid + "申请人");
                    this.log.info(rs.getString("requestname") + "流程名称");
                    String requestmark = rs.getString("requestmark");
                    RequestInfo request = new RequestInfo();
                    this.log.info(requestmark + "流程名称-------");
                    this.log.info(requestid + "请求id==requestid");
                    request.setDescription(requestmark);
                    request.setLastoperator(String.valueOf(userid));
                    request.setRequestid(requestid);
                    request.setWorkflowid(workflowid);
                    RequestManager requestManager = this.getRequestManager(this.requestManager, rs.getString("requestid"));
                    requestManager.setBilltablename(tablename);
                    this.log.info(requestmark + "流程编号22222-------");
                    request.setWorkflowid(workflowid);
                    request.setRequestid(rs.getString("requestid"));
                    request.setRequestManager(requestManager);
                    String reflag = workflowToDocOrPdf.execute(request);
                    this.log.info(reflag + "==流程批量生成文档的标志");
                }
            }
        }

    }

    public RequestManager getRequestManager() {
        return this.requestManager;
    }

    public void setRequestManager(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    public RequestManager getRequestManager(RequestManager rmanager, String requestid) {
        if (rmanager == null) {
            rmanager = new RequestManager();
            RecordSet rs = new RecordSet();
            String sql = "select * from workflow_requestbase where requestid = " + requestid;
            rs.executeSql(sql);

            while(rs.next()) {
                int _requestId = rs.getInt("requestid");
                int workflowId = rs.getInt("workflowid");
                int creater = rs.getInt("creater");
                String requestname = rs.getString("requestname");
                String requestlevel = rs.getString("requestlevel");
                String messagetype = rs.getString("messagetype");
                rmanager.setRequestid(_requestId);
                rmanager.setWorkflowid(workflowId);
                rmanager.setCreater(creater);
                rmanager.setRequestname(requestname);
                rmanager.setRequestlevel(requestlevel);
                rmanager.setMessageType(messagetype);
            }
        }

        return rmanager;
    }
}
