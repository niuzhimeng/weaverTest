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

public class DealhistoryforRequestid extends BaseCronJob {
    RequestManager requestManager;

    private Log log = LogFactory.getLog(DealhistoryforRequestid.class.getName());

    public DealhistoryforRequestid() {
    }


    public void execute() {

        RecordSet rs = new RecordSet();
        BaseBean bb = new BaseBean();
        bb.writeLog("-进入文件---DealhistoryforRequestid-----");


        String tablename = "";


        //  System.currentTimeMillis();当前时间的毫秒值


        Date d = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(d);
        log.info("当期日期" + dateNowStr);
        SimpleDateFormat sdftime = new SimpleDateFormat("HH:mm:ss");

        String dateNowStrtime = sdf.format(d);
        log.info("当期时间" + dateNowStrtime);
        //请求id
        String requestidarr = bb.getPropValue("pdfrequestid", "requestid");
        log.info("配置文件中配置的所有流程id" + requestidarr);
        if (!"".equals(requestidarr) && requestidarr != null) {
            String[] requestidarrsp = requestidarr.split(",");
            for (int i = 0; i < requestidarrsp.length; i++) {

                //循环得到配置文件中每个requestid
                String requestid = requestidarrsp[i];
                //查找workflowid的sql
                String workflowsql = "select workflowid from workflow_requestbase  where requestid=" + requestid;
                log.info("查找workflowid的sql==" + workflowsql);
                rs.execute(workflowsql);
                rs.next();
                //得到的workflowid
                String workflowid = rs.getString("workflowid");


                log.info("循环流程的id" + requestid);
                //requestid对应的表名
                String tabsql = "select tablename  from workflow_bill where id = (select formid from workflow_base where id="+workflowid+" and isbill=1)";


                rs.execute(tabsql);
                rs.next();
                tablename = rs.getString("tablename");

                String ksdate = bb.getPropValue("pdfrequestid", "ksdate");
                String kstime = bb.getPropValue("pdfrequestid", "kstime");
                String sqlstr = "select * from  " + tablename + " a ,workflow_requestbase b  where a.requestid=b.requestid and b.currentnodetype=3  and b.lastoperatedate>='" + ksdate + "' and  b.lastoperatetime>='" + kstime + "' and b.lastoperatedate<='" + dateNowStr + "' and a.requestid=" + requestid;

                rs.execute(sqlstr);
                while (rs.next()) {
                    WorkflowToDocOrPdf workflowToDocOrPdf = new WorkflowToDocOrPdf();
                    int userid = rs.getInt("creater");
                    log.info(rs.getString("requestid") + "请求id");

                    log.info(rs.getString("requestname") + "流程名称");
                    String requestmark = rs.getString("requestmark");

                    RequestInfo request = new RequestInfo();

                    request.setDescription(requestmark);
                    request.setLastoperator(String.valueOf(userid));
                    request.setRequestid(requestid);
                    request.setWorkflowid(workflowid);

                    // workflowToDocOrPdf.Start(rs.getString("requestid"), String.valueOf(userid), requestmark, workflowid);


                    RequestManager requestManager = getRequestManager(this.requestManager, rs.getString("requestid"));
                    requestManager.setBilltablename(tablename);


                    request.setWorkflowid(workflowid);
                    request.setRequestid(rs.getString("requestid"));
                    request.setRequestManager(requestManager);
                    String reflag = workflowToDocOrPdf.execute(request);
//                    log.info(workflowid + "workflowid流程id22222");
//                    OAPDF2archivesActionbak OAPDF2archives = new OAPDF2archivesActionbak();
//                    String returnRes = OAPDF2archives.execute(request);
                    log.info(reflag + "==流程批量生成文档完成");


                }


                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    public RequestManager getRequestManager() {
        return requestManager;
    }

    public void setRequestManager(RequestManager requestManager) {
        this.requestManager = requestManager;
    }


    /**
     * 判断requestmanager是否为空
     *
     * @param rmanager
     */
    public RequestManager getRequestManager(RequestManager rmanager, String requestid) {

        if (rmanager == null) {
            rmanager = new RequestManager();
            RecordSet rs = new RecordSet();
            String sql = "select * from workflow_requestbase where requestid = " + requestid;
            rs.executeSql(sql);
            while (rs.next()) {
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
