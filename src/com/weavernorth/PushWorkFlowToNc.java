package com.weavernorth;

import com.weaver.general.BaseBean;
import com.weaver.general.TimeUtil;
import com.weaver.general.Util;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import weaver.conn.RecordSet;
import weaver.interfaces.schedule.BaseCronJob;

import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;

/**
 * 定时任务推送流程基本信息给NC
 */
public class PushWorkFlowToNc extends BaseCronJob {
    private BaseBean baseBean = new BaseBean();
    private static final String URL = "http://10.31.1.217:9080/uapws/service/OAProcessService";
    private static final String[] addUpdates = {"title", "oaProcessId", "applyPsnId", "applyTime", "sendTime"};
    private static final String[] deletes = {"oaProcessId"};

    @Override
    public void execute() {
        try {
            baseBean.writeLog("推送流程定时任务执行================start");

            //查找执行插入操作的流程
            RecordSet insertSet = new RecordSet();
            insertSet.execute("SELECT WE.REQUESTNAMENEW,WE.REQUESTID,HS.WORKCODE,WE.CREATEDATE || ' ' || WE.CREATETIME CREATEDATETIME FROM WORKFLOW_REQUESTBASE WE LEFT JOIN UF_RECORD_INSERT RI ON WE.REQUESTID = RI.REQUESTID LEFT JOIN HRMRESOURCE HS ON HS.ID = WE.CREATER WHERE RI.REQUESTID IS NULL AND HS.WORKCODE IS NOT NULL AND WE.LASTOPERATEDATE || ' ' || WE.LASTOPERATETIME > ( SELECT PUSH_TIME FROM UF_LAST_PUSH_TIME )");

            //查找执行更新操作的流程
            RecordSet updateSet = new RecordSet();
            updateSet.execute("SELECT WE.REQUESTNAMENEW,WE.REQUESTID,HS.WORKCODE,WE.CREATEDATE || ' ' || WE.CREATETIME CREATEDATETIME FROM WORKFLOW_REQUESTBASE WE LEFT JOIN UF_RECORD_INSERT RI ON WE.REQUESTID = RI.REQUESTID LEFT JOIN HRMRESOURCE HS ON HS.ID = WE.CREATER WHERE RI.REQUESTID IS NOT NULL AND HS.WORKCODE IS NOT NULL AND WE.LASTOPERATEDATE || ' ' || WE.LASTOPERATETIME > ( SELECT PUSH_TIME FROM UF_LAST_PUSH_TIME )");

            //查找可删除的流程
            RecordSet deleteSet = new RecordSet();
            deleteSet.execute("SELECT REQUEST_ID FROM WORKFLOW_REQUESTDELETELOG WHERE OPERATE_DATE || ' ' || OPERATE_TIME > ( SELECT PUSH_TIME FROM UF_LAST_PUSH_TIME )");

            //更新LAST_PUSH_TIME表中的时间
            RecordSet updateTime = new RecordSet();
            updateTime.execute("UPDATE UF_LAST_PUSH_TIME SET PUSH_TIME = '" + TimeUtil.getCurrentTimeString() + "'");

            StringBuilder insertSql = new StringBuilder("INSERT ALL ");

            Call insertCall = getCall("insert", addUpdates);
            String insertResult;
            int insertSuccess = 0;
            int insertErr = 0;
            while (insertSet.next()) {
                // 给方法传递参数，并且调用方法
                insertResult = (String) insertCall.invoke(new Object[]{insertSet.getString("REQUESTNAMENEW"), insertSet.getString("REQUESTID"),
                        Util.null2String(insertSet.getString("WORKCODE")), insertSet.getString("CREATEDATETIME"), TimeUtil.getCurrentTimeString()});
                if (insertResult.contains("NOK")) {
                    baseBean.writeLog("NC插入失败，错误信息 =========》" + insertResult);
                    insertErr++;
                } else {
                    insertSuccess++;
                    insertSql.append("INTO UF_RECORD_INSERT (REQUESTID)VALUES('").append(insertSet.getString("REQUESTID")).append("')");
                }
            }

            insertSql.append("SELECT 1 FROM DUAL");
            if (insertSql.length() > 35) {
                //将已插入的流程id存入表
                insertSet.execute(insertSql.toString());
            }
            baseBean.writeLog("可插入条数-》》 " + insertSet.getCounts());
            if (insertSet.getCounts() > 0) {
                baseBean.writeLog("插入成功： " + insertSuccess + " 条");
                baseBean.writeLog("插入失败： " + insertErr + " 条");
            }

            //============================更新============================

            Call updateCall = getCall("update", addUpdates);
            String updateResult;
            int updateSuccess = 0;
            int updateErr = 0;
            while (updateSet.next()) {
                // 给方法传递参数，并且调用方法
                updateResult = (String) updateCall.invoke(new Object[]{updateSet.getString("REQUESTNAMENEW"), updateSet.getString("REQUESTID"),
                        Util.null2String(updateSet.getString("WORKCODE")), updateSet.getString("CREATEDATETIME"), TimeUtil.getCurrentTimeString()});
                if (updateResult.contains("NOK")) {
                    baseBean.writeLog("NC更新失败，错误信息==========》" + updateResult);
                    updateErr++;
                } else {
                    updateSuccess++;
                }
            }
            baseBean.writeLog("可更新条数-》》 " + updateSet.getCounts());
            if (updateSet.getCounts() > 0) {
                baseBean.writeLog("更新成功： " + updateSuccess + " 条");
                baseBean.writeLog("更新失败： " + updateErr + " 条");
            }

            //============================删除============================
            Call deleteCall = getCall("delete", deletes);
            String deleteResult;
            int deleteSuccess = 0;
            int deleteErr = 0;
            while (deleteSet.next()) {
                // 给方法传递参数，并且调用方法
                deleteResult = (String) deleteCall.invoke(new Object[]{deleteSet.getString("REQUEST_ID")});
                if (deleteResult.contains("NOK")) {
                    deleteErr++;
                    baseBean.writeLog("NC删除失败，错误信息=========》" + deleteResult);
                } else {
                    deleteSuccess++;
                }
            }
            baseBean.writeLog("可删除条数-》》 " + deleteSet.getCounts());
            if (deleteSet.getCounts() > 0) {
                baseBean.writeLog("删除成功： " + deleteSuccess + " 条");
                baseBean.writeLog("删除失败： " + deleteErr + " 条");
            }

            baseBean.writeLog("推送流程定时任务结束================end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Call对象
     *
     * @param methodName 要调用的方法名
     */
    private Call getCall(String methodName, String[] paramList) {
        Service service = new Service();
        Call call = null;
        try {
            call = (Call) service.createCall();
            call.setTargetEndpointAddress(URL);
            // WSDL里面描述的接口名称(要调用的方法)
            call.setOperationName(methodName);
            for (String paramName : paramList) {
                call.addParameter(paramName, XMLType.XSD_STRING, ParameterMode.IN);
            }
            // 设置被调用方法的返回值类型
            call.setReturnType(XMLType.XSD_STRING);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return call;
    }

}
