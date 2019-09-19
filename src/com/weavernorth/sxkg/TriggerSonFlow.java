package com.weavernorth.sxkg;

import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.interfaces.schedule.BaseCronJob;
import weaver.workflow.webservices.*;

/**
 * 定时触发销假流程(BGS-17 领导干部离京外出请假审批 -> 销假)
 */
public class TriggerSonFlow extends BaseCronJob {

    private BaseBean baseBean = new BaseBean();
    /**
     * 主流程表单名称
     */
    private static final String MAIN_TABLE_NAME = "formtable_main_208";
    /**
     * 销假流程id
     */
    private static final String SON_FLOW_ID = "62534";

    private RecordSet updateRecordSet = new RecordSet();
    private WorkflowServiceImpl service = new WorkflowServiceImpl();

    @Override
    public void execute() {
        baseBean.writeLog("TriggerSonFlow定时触发销假流程 Start： " + TimeUtil.getCurrentTimeString());
        RecordSet mainRecordSet = new RecordSet();
        String currentDateString = TimeUtil.getCurrentDateString();
        try {
            // 查询不在第一节点，且没有触发过子流程的 请假流程
            String standardSql = "select f.* from " + MAIN_TABLE_NAME + " f left join workflow_requestbase w " +
                    "on w.requestid = f.requestId where w.currentnodetype != 0 and (f.cfxj != 0 or f.cfxj is null) and fhrq = '" + currentDateString + "'";
            baseBean.writeLog("查询满足触发条件的主流程sql: " + standardSql);
            mainRecordSet.executeQuery(standardSql);
            baseBean.writeLog("可触发主流程数量：" + mainRecordSet.getCounts());

            RecordSet lastNameSet = new RecordSet();
            StringBuilder stringBuilder = new StringBuilder();
            while (mainRecordSet.next()) {
                baseBean.writeLog("创建流程开始===============");
                String xmVal = "";
                WorkflowRequestTableField[] mainField = new WorkflowRequestTableField[20]; //主表行对象

                int i = 0;
                mainField[i] = new WorkflowRequestTableField();
                mainField[i].setFieldName("sqbm");// 字段名
                mainField[i].setFieldValue(mainRecordSet.getString("sqbm")); // 申请部门
                mainField[i].setView(true); //字段是否可见
                mainField[i].setEdit(true); //字段是否可编辑

                i++;
                mainField[i] = new WorkflowRequestTableField();
                mainField[i].setFieldName("sqdw");
                mainField[i].setFieldValue(mainRecordSet.getString("sqdw")); // 申请单位
                mainField[i].setView(true);
                mainField[i].setEdit(true);

                i++;
                mainField[i] = new WorkflowRequestTableField();
                mainField[i].setFieldName("xm");
                String xm = mainRecordSet.getString("xm");
                lastNameSet.executeQuery("select lastname from hrmresource where id = " + xm);
                lastNameSet.next();
                xmVal = lastNameSet.getString("lastname");
                mainField[i].setFieldValue(xm); // 姓名
                mainField[i].setView(true);
                mainField[i].setEdit(true);

                i++;
                mainField[i] = new WorkflowRequestTableField();
                mainField[i].setFieldName("zw");
                mainField[i].setFieldValue(mainRecordSet.getString("zw")); // 职务
                mainField[i].setView(true);
                mainField[i].setEdit(true);

                i++;
                mainField[i] = new WorkflowRequestTableField();
                mainField[i].setFieldName("syjmdd");
                mainField[i].setFieldValue(mainRecordSet.getString("syjmdd")); // 外出事由及目的地
                mainField[i].setView(true);
                mainField[i].setEdit(true);

                i++;
                mainField[i] = new WorkflowRequestTableField();
                mainField[i].setFieldName("mdd");
                mainField[i].setFieldValue(mainRecordSet.getString("mdd")); // 目的地
                mainField[i].setView(true);
                mainField[i].setEdit(true);

                i++;
                mainField[i] = new WorkflowRequestTableField();
                mainField[i].setFieldName("cfrq");
                mainField[i].setFieldValue(mainRecordSet.getString("cfrq")); // 出发日期
                mainField[i].setView(true);
                mainField[i].setEdit(true);

                i++;
                mainField[i] = new WorkflowRequestTableField();
                mainField[i].setFieldName("fhrq");
                mainField[i].setFieldValue(mainRecordSet.getString("fhrq")); // 返回日期
                mainField[i].setView(true);
                mainField[i].setEdit(true);

                i++;
                mainField[i] = new WorkflowRequestTableField();
                mainField[i].setFieldName("cxfs");
                mainField[i].setFieldValue(mainRecordSet.getString("cxfs")); // 出行方式
                mainField[i].setView(true);
                mainField[i].setEdit(true);

                i++;
                mainField[i] = new WorkflowRequestTableField();
                mainField[i].setFieldName("lxdh");
                mainField[i].setFieldValue(mainRecordSet.getString("lxdh")); // 	联系电话
                mainField[i].setView(true);
                mainField[i].setEdit(true);

                i++;
                mainField[i] = new WorkflowRequestTableField();
                mainField[i].setFieldName("xcsm");
                mainField[i].setFieldValue(mainRecordSet.getString("xcsm")); // 	行程说明
                mainField[i].setView(true);
                mainField[i].setEdit(true);

                i++;
                mainField[i] = new WorkflowRequestTableField();
                mainField[i].setFieldName("dbld");
                mainField[i].setFieldValue(mainRecordSet.getString("dbld")); // 	代主持工作领导姓名
                mainField[i].setView(true);
                mainField[i].setEdit(true);

                i++;
                mainField[i] = new WorkflowRequestTableField();
                mainField[i].setFieldName("dbzw");
                mainField[i].setFieldValue(mainRecordSet.getString("dbzw")); // 	代主持工作领导职务
                mainField[i].setView(true);
                mainField[i].setEdit(true);

                i++;
                mainField[i] = new WorkflowRequestTableField();
                mainField[i].setFieldName("bgdh");
                mainField[i].setFieldValue(mainRecordSet.getString("bgdh")); // 	办公电话
                mainField[i].setView(true);
                mainField[i].setEdit(true);

                i++;
                mainField[i] = new WorkflowRequestTableField();
                mainField[i].setFieldName("yddh");
                mainField[i].setFieldValue(mainRecordSet.getString("yddh")); // 	移动电话
                mainField[i].setView(true);
                mainField[i].setEdit(true);

                i++;
                mainField[i] = new WorkflowRequestTableField();
                mainField[i].setFieldName("fj");
                mainField[i].setFieldValue(mainRecordSet.getString("fj")); // 	附件
                mainField[i].setView(true);
                mainField[i].setEdit(true);

                WorkflowRequestTableRecord[] mainRecord = new WorkflowRequestTableRecord[1];// 主字段只有一行数据
                mainRecord[0] = new WorkflowRequestTableRecord();
                mainRecord[0].setWorkflowRequestTableFields(mainField);

                WorkflowMainTableInfo workflowMainTableInfo = new WorkflowMainTableInfo();
                workflowMainTableInfo.setRequestRecords(mainRecord);

                //====================================流程基本信息录入
                WorkflowBaseInfo workflowBaseInfo = new WorkflowBaseInfo();
                workflowBaseInfo.setWorkflowId(SON_FLOW_ID);// 流程id

                WorkflowRequestInfo workflowRequestInfo = new WorkflowRequestInfo();// 流程基本信息
                workflowRequestInfo.setCreatorId(xm);// 创建人id
                workflowRequestInfo.setRequestLevel("0");// 0 正常，1重要，2紧急
                workflowRequestInfo.setRequestName("销假流程" + "-" + xmVal + "-" + currentDateString);// 流程标题
                workflowRequestInfo.setWorkflowBaseInfo(workflowBaseInfo);
                workflowRequestInfo.setWorkflowMainTableInfo(workflowMainTableInfo);// 添加主表字段数据
                workflowRequestInfo.setIsnextflow("0");

                //创建流程的类
                String requestId = service.doCreateWorkflowRequest(workflowRequestInfo, Integer.parseInt(xm));
                stringBuilder.append(requestId).append(", ");

                // 更新触发标记
                addCount(mainRecordSet.getString("requestId"));
            }

            baseBean.writeLog("销假申请创建流程完毕===============requestid :" + stringBuilder.toString());
        } catch (Exception e) {
            baseBean.writeLog("TriggerSonFlow定时触发销假流程 Error: " + e);
        }
        baseBean.writeLog("TriggerSonFlow定时触发销假流程 End： " + TimeUtil.getCurrentTimeString());
    }


    /**
     * 更新触发次数
     */
    private void addCount(String requestId) {
        updateRecordSet.executeUpdate("update " + MAIN_TABLE_NAME + " set cfxj = 0 where requestid = " + requestId);
    }

}
