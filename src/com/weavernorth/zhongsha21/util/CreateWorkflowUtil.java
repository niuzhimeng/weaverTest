package com.weavernorth.zhongsha21.util;

import weaver.general.BaseBean;
import weaver.workflow.webservices.*;

public class CreateWorkflowUtil {

    private static final BaseBean baseBean = new BaseBean();

    /**
     * 创建提醒流程
     *
     * @param creatorId 流程创建人
     */
    public static void createFlow(String creatorId) {
        try {
            WorkflowRequestTableField[] mainField = new WorkflowRequestTableField[10]; //主表行对象

            int i = 0;
            mainField[i] = new WorkflowRequestTableField();
            mainField[i].setFieldName("sqxm");// 字段名
            mainField[i].setFieldValue(""); // 申请人
            mainField[i].setView(true); //字段是否可见
            mainField[i].setEdit(true); //字段是否可编辑

            i++;
            mainField[i] = new WorkflowRequestTableField();
            mainField[i].setFieldName("sqrbm");
            mainField[i].setFieldValue(""); // 申请人部门
            mainField[i].setView(true);
            mainField[i].setEdit(true);

            WorkflowRequestTableRecord[] mainRecord = new WorkflowRequestTableRecord[1];// 主字段只有一行数据
            mainRecord[0] = new WorkflowRequestTableRecord();
            mainRecord[0].setWorkflowRequestTableFields(mainField);

            WorkflowMainTableInfo workflowMainTableInfo = new WorkflowMainTableInfo();
            workflowMainTableInfo.setRequestRecords(mainRecord);


            //====================================流程基本信息录入
            WorkflowBaseInfo workflowBaseInfo = new WorkflowBaseInfo();
            workflowBaseInfo.setWorkflowId(ZhShaConfig.TI_XING_FLOW_ID.getValue());// 流程id

            WorkflowRequestInfo workflowRequestInfo = new WorkflowRequestInfo();// 流程基本信息
            workflowRequestInfo.setCreatorId(creatorId);// 创建人id
            workflowRequestInfo.setRequestLevel("0");// 0 正常，1重要，2紧急
            workflowRequestInfo.setRequestName("");// 流程标题
            workflowRequestInfo.setWorkflowBaseInfo(workflowBaseInfo);
            workflowRequestInfo.setWorkflowMainTableInfo(workflowMainTableInfo);// 添加主表字段数据
            workflowRequestInfo.setWorkflowDetailTableInfos(new WorkflowDetailTableInfo[0]);// 添加明细表字段数据
            workflowRequestInfo.setIsnextflow("0");

            //创建流程的类
            WorkflowServiceImpl service = new WorkflowServiceImpl();
            String requestid = service.doCreateWorkflowRequest(workflowRequestInfo, Integer.parseInt(creatorId));
            baseBean.writeLog("创建流程完毕=============== " + requestid);
        } catch (Exception e) {
            baseBean.writeLog("创建提醒流程异常： " + e);
        }
    }
}
