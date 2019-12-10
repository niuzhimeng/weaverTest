package com.weavernorth.jiajiezb.hr.timed;

import com.alibaba.fastjson.JSONObject;
import com.weavernorth.jiajiezb.hr.util.JiaJieConfigInfo;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.general.Util;
import weaver.interfaces.schedule.BaseCronJob;
import weaver.workflow.webservices.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 定时触发【JT_试用期转正提醒】流程
 */
public class SyqZzTriggerFlow extends BaseCronJob {

    private BaseBean baseBean = new BaseBean();
    private RecordSet recordSet = new RecordSet();
    private RecordSet connSet = new RecordSet();
    private WorkflowServiceImpl service = new WorkflowServiceImpl();

    @Override
    public void execute() {
        baseBean.writeLog("定时触发【JT_试用期转正提醒】 Start： " + TimeUtil.getCurrentTimeString());
        RecordSet mainRecordSet = new RecordSet();
        RecordSet triggerSet = new RecordSet();
        String currentDateString = TimeUtil.getCurrentDateString();
        try {
            // 指定分部下，试用期的员工
            String standardSql = "SELECT h.id, h.jobtitle, h.workcode, h.departmentid, h.probationenddate " +
                    " FROM HRMRESOURCE h WHERE h.STATUS = 0 AND h.subcompanyid1 ='" + JiaJieConfigInfo.ZONG_BU_ID.getValue() + "'";
            baseBean.writeLog("指定分部下，试用期的员工sql: " + standardSql);
            mainRecordSet.executeQuery(standardSql);

            Map<String, String> flowPerMap = new HashMap<String, String>();
            while (mainRecordSet.next()) {
                String id = mainRecordSet.getString("id");
                triggerSet.executeQuery("select id from " + JiaJieConfigInfo.TRIGGER_TABLE_NAME.getValue() +
                        " where person_name = '" + id + "' and flow_type = '0'");
                if (triggerSet.next()) {
                    continue;
                }
                // 试用期结束日期
                String probationenddate = Util.null2String(mainRecordSet.getString("probationenddate"));
                if ("".equals(probationenddate)) {
                    continue;
                }
                String dateAdd = TimeUtil.dateAdd(probationenddate, -19);
                if (currentDateString.equals(dateAdd)) {
                    // 触发流程
                    WorkflowRequestTableField[] mainField = new WorkflowRequestTableField[20]; //主表行对象

                    int i = 0;
                    mainField[i] = new WorkflowRequestTableField();
                    mainField[i].setFieldName("ygxm");// 字段名
                    mainField[i].setFieldValue(mainRecordSet.getString("id")); // 姓名
                    mainField[i].setView(true); //字段是否可见
                    mainField[i].setEdit(true); //字段是否可编辑

                    i++;
                    mainField[i] = new WorkflowRequestTableField();
                    mainField[i].setFieldName("ygbh");
                    mainField[i].setFieldValue(mainRecordSet.getString("workcode")); // 员工编号
                    mainField[i].setView(true);
                    mainField[i].setEdit(true);

                    i++;
                    mainField[i] = new WorkflowRequestTableField();
                    mainField[i].setFieldName("bm");
                    mainField[i].setFieldValue(mainRecordSet.getString("departmentid")); // 部门
                    mainField[i].setView(true);
                    mainField[i].setEdit(true);

                    i++;
                    mainField[i] = new WorkflowRequestTableField();
                    mainField[i].setFieldName("gw");
                    mainField[i].setFieldValue(mainRecordSet.getString("jobtitle")); // 岗位
                    mainField[i].setView(true);
                    mainField[i].setEdit(true);

                    // 查询自定义字段
                    String[] cusFiled = getCusFiled(id);
                    baseBean.writeLog("自定义字段数组： " + JSONObject.toJSONString(cusFiled));
                    i++;
                    mainField[i] = new WorkflowRequestTableField();
                    mainField[i].setFieldName("zj");
                    mainField[i].setFieldValue(cusFiled[0]); // 职级
                    mainField[i].setView(true);
                    mainField[i].setEdit(true);

                    i++;
                    mainField[i] = new WorkflowRequestTableField();
                    mainField[i].setFieldName("gwlx");
                    mainField[i].setFieldValue(cusFiled[1]); // 岗位类型
                    mainField[i].setView(true);
                    mainField[i].setEdit(true);

                    i++;
                    mainField[i] = new WorkflowRequestTableField();
                    mainField[i].setFieldName("bgdd");
                    mainField[i].setFieldValue(cusFiled[2]); // 办公地点
                    mainField[i].setView(true);
                    mainField[i].setEdit(true);

                    i++;
                    mainField[i] = new WorkflowRequestTableField();
                    mainField[i].setFieldName("lb");
                    mainField[i].setFieldValue(getSysByFiled(mainRecordSet.getString("departmentid"))); // 类别
                    mainField[i].setView(true);
                    mainField[i].setEdit(true);

                    i++;
                    mainField[i] = new WorkflowRequestTableField();
                    mainField[i].setFieldName("jhzzrq");
                    mainField[i].setFieldValue(TimeUtil.dateAdd(mainRecordSet.getString("probationenddate"), 1)); // 计划转正日期
                    mainField[i].setView(true);
                    mainField[i].setEdit(true);

                    WorkflowRequestTableRecord[] mainRecord = new WorkflowRequestTableRecord[1];// 主字段只有一行数据
                    mainRecord[0] = new WorkflowRequestTableRecord();
                    mainRecord[0].setWorkflowRequestTableFields(mainField);

                    WorkflowMainTableInfo workflowMainTableInfo = new WorkflowMainTableInfo();
                    workflowMainTableInfo.setRequestRecords(mainRecord);

                    //====================================流程基本信息录入
                    WorkflowBaseInfo workflowBaseInfo = new WorkflowBaseInfo();
                    workflowBaseInfo.setWorkflowId(JiaJieConfigInfo.JT_SYQZZ_FLOW_ID.getValue());// 流程id

                    WorkflowRequestInfo workflowRequestInfo = new WorkflowRequestInfo();// 流程基本信息
                    workflowRequestInfo.setCreatorId("1");// 创建人id
                    workflowRequestInfo.setRequestLevel("0");// 0 正常，1重要，2紧急
                    workflowRequestInfo.setRequestName("JT_试用期转正提醒" + TimeUtil.getCurrentTimeString());// 流程标题
                    workflowRequestInfo.setWorkflowBaseInfo(workflowBaseInfo);
                    workflowRequestInfo.setWorkflowMainTableInfo(workflowMainTableInfo);// 添加主表字段数据

                    //创建流程的类
                    String requestId = service.doCreateWorkflowRequest(workflowRequestInfo, 1);
                    flowPerMap.put(requestId, id);
                }

            }
            addCount(flowPerMap);
            baseBean.writeLog("【JT_试用期转正提醒】创建流程完毕===============requestid-person :" + JSONObject.toJSONString(flowPerMap));
        } catch (Exception e) {
            baseBean.writeLog("定时触发【JT_试用期转正提醒】 Error: " + e);
        }
        baseBean.writeLog("定时触发【JT_试用期转正提醒】 End： " + TimeUtil.getCurrentTimeString());
    }

    /**
     * 查询自定义字段
     *
     * @param id 人员id
     */
    private String[] getCusFiled(String id) {
        String[] returns = new String[3];
        recordSet.executeQuery("select " + JiaJieConfigInfo.ZHI_JI.getValue() + ", " + JiaJieConfigInfo.GWLX.getValue() + ", " +
                JiaJieConfigInfo.BGDD.getValue() +
                " from CUS_FIELDDATA where id = " + id);
        while (recordSet.next()) {
            String zj = Util.null2String(recordSet.getString(JiaJieConfigInfo.ZHI_JI.getValue()));
            String gwlx = Util.null2String(recordSet.getString(JiaJieConfigInfo.GWLX.getValue()));
            String bgdd = Util.null2String(recordSet.getString(JiaJieConfigInfo.BGDD.getValue()));

            if (!"".equals(zj)) {
                returns[0] = zj;
            }
            if (!"".equals(gwlx)) {
                returns[1] = gwlx;
            }
            if (!"".equals(bgdd)) {
                returns[2] = bgdd;
            }
        }
        return returns;
    }

    /**
     * 更新触发次数
     */
    private void addCount(Map<String, String> map) {
        try {
            RecordSet updateRecordSet = new RecordSet();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (Util.getIntValue(entry.getKey()) > 0) {
                    updateRecordSet.executeUpdate("insert into  " + JiaJieConfigInfo.TRIGGER_TABLE_NAME.getValue() +
                                    "(creator_requestid, person_name, flow_type) values (?,?,?)",
                            entry.getKey(), entry.getValue(), "0");
                }
            }
        } catch (Exception e) {
            new BaseBean().writeLog("触发试用期流程，更新触发次数异常： " + e);
        }
    }

    /**
     * 根据某一字段查另一个字段
     */
    private String getSysByFiled(String selField) {
        String returnStr = "";
        connSet.executeQuery("select JT_BMLB from HrmDepartmentdefined where DEPID = '" + selField + "'");
        if (connSet.next()) {
            returnStr = connSet.getString("JT_BMLB");
        }
        return returnStr;
    }
}
