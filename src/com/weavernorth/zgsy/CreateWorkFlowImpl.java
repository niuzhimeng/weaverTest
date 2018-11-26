package com.weavernorth.zgsy;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.soa.workflow.WorkFlowInit;
import weaver.soa.workflow.request.RequestInfo;
import weaver.soa.workflow.request.RequestService;
import weaver.workflow.webservices.WorkflowRequestInfo;
import weaver.workflow.webservices.WorkflowServiceImplXml;
import weaver.workflow.webservices.XmlUtil;

import java.lang.reflect.Method;
import java.util.List;

public class CreateWorkFlowImpl implements CreateWorkFlow {

    private BaseBean baseBean = new BaseBean();

    @Override
    public String doCreateWorkflow(String requestXml) {
        baseBean.writeLog("处理前的xml============》 " + requestXml);
        String returnStr = "";
        try {
            Document doc = DocumentHelper.parseText(requestXml);
            Element rootElt = doc.getRootElement();
            //获取流程创建人编号
            String creatorId = rootElt.elementText("creatorId").trim();

            RecordSet recordSet = new RecordSet();
            recordSet.execute("select id , lastname, departmentid from hrmresource where workcode =  '" + creatorId + "'");
            recordSet.next();

            String id = recordSet.getString("id");
            String lastname = recordSet.getString("lastname");
            String departmentid = recordSet.getString("departmentid");


            RecordSet twoSet = new RecordSet();
            twoSet.execute("select supdepid from hrmdepartment where id = '" + departmentid + "'");
            twoSet.next();
            String departmentid2 = twoSet.getString("supdepid");

            baseBean.writeLog("创建人id-》 " + id);
            baseBean.writeLog("创建人名字-》 " + lastname);
            baseBean.writeLog("创建人一级部门id-》 " + departmentid);
            baseBean.writeLog("创建人二级部门id-》 " + departmentid2);

            //拼接流程标题
            String requestName = "SW-07 订单出库通知单-" + lastname + "-" + weaver.general.TimeUtil.getCurrentDateString();

            //设置OA里的属性
            rootElt.element("requestName").setText(requestName);
            rootElt.element("creatorId").setText(id);
            rootElt.element("workflowBaseInfo").element("workflowId").setText("89");//正式89
            List elements = rootElt.element("workflowMainTableInfo").element("requestRecords").element("weaver.workflow.webservices.WorkflowRequestTableRecord")
                    .element("workflowRequestTableFields").elements("weaver.workflow.webservices.WorkflowRequestTableField");
            Element element;
            String fieldName;
            baseBean.writeLog("开始执行循环==================");
            for (Object tableObj : elements) {
                element = (Element) tableObj;
                fieldName = element.elementText("fieldName").trim();
                if ("resourceId".equals(fieldName)) {
                    //申请人 = 流程创建人
                    element.element("fieldValue").setText(id);
                    baseBean.writeLog("循环内部  申请人id-》 " + id);
                } else if ("departmentId".equals(fieldName)) {
                    //一级部门
                    baseBean.writeLog("循环内部  设置一级部门");
                    element.element("fieldValue").setText(departmentid);
                } else if ("departmentId2".equals(fieldName)) {
                    //二级部门
                    baseBean.writeLog("循环内部  设置二级部门");
                    element.element("fieldValue").setText(departmentid2);
                }
            }
            baseBean.writeLog("处理后的xml============》 " + doc.asXML());
            //创建流程的类
            WorkflowServiceImplXml workflowServiceImplXml = new WorkflowServiceImplXml();
           // returnStr = workflowServiceImplXml.doCreateWorkflowRequest(doc.asXML(), Integer.parseInt(id));
            baseBean.writeLog("准备创建流程===============");
            XmlUtil xmlutil1 = XmlUtil.getInstance();
            WorkflowRequestInfo var3 = (WorkflowRequestInfo)xmlutil1.xmlToObject(doc.asXML());

            Class<? extends WorkflowServiceImplXml> aClass = workflowServiceImplXml.getClass();
            Method method1 = aClass.getDeclaredMethod("getActiveWorkflowRequestInfo", WorkflowRequestInfo.class);
            method1.setAccessible(true);
            var3 = (WorkflowRequestInfo) method1.invoke(workflowServiceImplXml,var3);


            Method method2 = aClass.getDeclaredMethod("toRequestInfo", WorkflowRequestInfo.class);
            method2.setAccessible(true);
            RequestInfo requestInfo = (RequestInfo) method2.invoke(workflowServiceImplXml,var3);
            if (requestInfo.getCreatorid() == null || requestInfo.getCreatorid().isEmpty()) {
                requestInfo.setCreatorid(id);
            }

            if (!requestInfo.getCreatorid().equals(id)) {
                requestInfo.setCreatorid(id);
            }

            requestInfo.setIsNextFlow("0");
            RequestService requestService = new RequestService();
            returnStr = requestService.createRequest(requestInfo);
            baseBean.writeLog("创建流程完毕===============");
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnStr;
    }

}
