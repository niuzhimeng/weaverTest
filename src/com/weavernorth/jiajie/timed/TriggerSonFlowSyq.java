package com.weavernorth.jiajie.timed;

import com.alibaba.fastjson.JSONObject;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.general.Util;
import weaver.interfaces.schedule.BaseCronJob;
import weaver.workflow.webservices.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 定时触发【试用期到期提醒】流程
 */
public class TriggerSonFlowSyq extends BaseCronJob {

    // 管理员id
    private static final String SYSADMIN = "5907";
    // 分部id
    private static final String FEN_BU_ID = "41";
    // 试用期到期提醒流程id
    private static final String SON_FLOW_ID = "13502";
    // 调入岗位名称
    private static final String GWMC_FIELD = "field12";
    // 调入岗位地图
    private static final String GWDT_FIELD = "field9";
    // 调入岗位类别
    private static final String GWLB_FIELD = "field10";
    // 调入岗位级别
    private static final String GWJB_FIELD = "field11";
    // 入职日期
    private static final String RZ_FIELD = "field21";
    // 记录建模表名
    private static final String UF_TABLE_NAME = "uf_triggered";
    // 记录建模-模块id
    private static final String UF_TABLE_ID = "20564";

    private BaseBean baseBean = new BaseBean();
    private RecordSet recordSet = new RecordSet();
    private WorkflowServiceImpl service = new WorkflowServiceImpl();
    private static ModeRightInfo moderightinfo = new ModeRightInfo();

    @Override
    public void execute() {
        baseBean.writeLog("TriggerSonFlow定时触【试用期到期提醒】 Start： " + TimeUtil.getCurrentTimeString());
        RecordSet mainRecordSet = new RecordSet();
        RecordSet triggerSet = new RecordSet();
        String currentDateString = TimeUtil.getCurrentDateString();
        try {
            // 指定分部下，试用期的员工
            String standardSql = "SELECT\n" +
                    "\th.id,\n" +
                    "\th.workcode,\n" +
                    "\th.departmentid,\n" +
                    "\tprobationenddate \n" +
                    "FROM\n" +
                    "\tHRMRESOURCE h \n" +
                    "WHERE\n" +
                    "\th.STATUS = 0 \n" +
                    "\tAND h.subcompanyid1 = " + FEN_BU_ID;
            baseBean.writeLog("指定分部下，试用期的员工sql: " + standardSql);
            mainRecordSet.executeQuery(standardSql);

            Map<String, String> flowPerMap = new HashMap<String, String>();
            while (mainRecordSet.next()) {
                String id = mainRecordSet.getString("id");
                triggerSet.executeQuery("select id from " + UF_TABLE_NAME + " where person_name = '" + id + "' and flow_type = '1'");
                if (triggerSet.next()) {
                    continue;
                }
                // 试用期结束日期
                String probationenddate = Util.null2String(mainRecordSet.getString("probationenddate"));
                if ("".equals(probationenddate)) {
                    continue;
                }
                String dateAdd = TimeUtil.dateAdd(probationenddate, -14);
                if (currentDateString.equals(dateAdd)) {
                    // 触发流程
                    WorkflowRequestTableField[] mainField = new WorkflowRequestTableField[20]; //主表行对象

                    int i = 0;
                    mainField[i] = new WorkflowRequestTableField();
                    mainField[i].setFieldName("xm");// 字段名
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

                    // 查询自定义字段
                    String[] cusFiled = getCusFiled(id);
                    baseBean.writeLog("自定义字段数组： " + JSONObject.toJSONString(cusFiled));
                    i++;
                    mainField[i] = new WorkflowRequestTableField();
                    mainField[i].setFieldName("gwmcc");
                    mainField[i].setFieldValue(cusFiled[0]); // 岗位名称
                    mainField[i].setView(true);
                    mainField[i].setEdit(true);

                    i++;
                    mainField[i] = new WorkflowRequestTableField();
                    mainField[i].setFieldName("gwlb");
                    mainField[i].setFieldValue(cusFiled[1]); // 岗位类别
                    mainField[i].setView(true);
                    mainField[i].setEdit(true);

                    i++;
                    mainField[i] = new WorkflowRequestTableField();
                    mainField[i].setFieldName("gwjb");
                    mainField[i].setFieldValue(cusFiled[2]); // 岗位级别
                    mainField[i].setView(true);
                    mainField[i].setEdit(true);

                    i++;
                    mainField[i] = new WorkflowRequestTableField();
                    mainField[i].setFieldName("rzrq");
                    mainField[i].setFieldValue(cusFiled[3]); // 入职日期
                    mainField[i].setView(true);
                    mainField[i].setEdit(true);

                    i++;
                    mainField[i] = new WorkflowRequestTableField();
                    mainField[i].setFieldName("gwdt");
                    mainField[i].setFieldValue(cusFiled[4]); // 岗位地图
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
                    workflowBaseInfo.setWorkflowId(SON_FLOW_ID);// 流程id

                    WorkflowRequestInfo workflowRequestInfo = new WorkflowRequestInfo();// 流程基本信息
                    workflowRequestInfo.setCreatorId(SYSADMIN);// 创建人id
                    workflowRequestInfo.setRequestLevel("0");// 0 正常，1重要，2紧急
                    workflowRequestInfo.setRequestName("试用期到期提醒" + currentDateString);// 流程标题
                    workflowRequestInfo.setWorkflowBaseInfo(workflowBaseInfo);
                    workflowRequestInfo.setWorkflowMainTableInfo(workflowMainTableInfo);// 添加主表字段数据

                    //创建流程的类
                    String requestId = service.doCreateWorkflowRequest(workflowRequestInfo, Integer.parseInt(SYSADMIN));
                    flowPerMap.put(requestId, id);
                }

            }
            addCount(flowPerMap);
            baseBean.writeLog("【试用期到期提醒】创建流程完毕===============requestid-person :" + JSONObject.toJSONString(flowPerMap));
        } catch (Exception e) {
            baseBean.writeLog("TriggerSonFlow定时触【试用期到期提醒】 Error: " + e);
        }
        baseBean.writeLog("TriggerSonFlow定时触【试用期到期提醒】 End： " + TimeUtil.getCurrentTimeString());
    }

    /**
     * 查询自定义字段
     *
     * @param id 人员id
     */
    private String[] getCusFiled(String id) {
        String[] returns = new String[5];
        recordSet.executeQuery("select " + GWMC_FIELD + ", " + GWLB_FIELD + ", " + GWJB_FIELD + ", " + RZ_FIELD + ", " + GWDT_FIELD +
                " from CUS_FIELDDATA where id = " + id);
        while (recordSet.next()) {
            String gwmc = Util.null2String(recordSet.getString(GWMC_FIELD));
            String gwlb = Util.null2String(recordSet.getString(GWLB_FIELD));
            String gwjb = Util.null2String(recordSet.getString(GWJB_FIELD));
            String rz = Util.null2String(recordSet.getString(RZ_FIELD));
            String gwdt = Util.null2String(recordSet.getString(GWDT_FIELD));
            if (!"".equals(gwmc)) {
                returns[0] = gwmc;
            }
            if (!"".equals(gwlb)) {
                returns[1] = gwlb;
            }
            if (!"".equals(gwjb)) {
                returns[2] = gwjb;
            }
            if (!"".equals(rz)) {
                returns[3] = rz;
            }
            if (!"".equals(gwdt)) {
                returns[4] = gwdt;
            }
        }
        return returns;
    }

    /**
     * 更新触发次数
     */
    private void addCount(Map<String, String> map) {
        try {

            String currentTimeString = TimeUtil.getCurrentTimeString();
            String currentDate = currentTimeString.substring(0, 10);
            String currentTime = currentTimeString.substring(11);
            RecordSet updateRecordSet = new RecordSet();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (Util.getIntValue(entry.getKey()) > 0) {
                    updateRecordSet.executeUpdate("insert into  " + UF_TABLE_NAME + "(creator_requestid, person_name, flow_type," +
                                    "formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) values (?,?,?, ?,?,?,?,?)",
                            entry.getKey(), entry.getValue(), "1", UF_TABLE_ID, 1, 0, currentDate, currentTime);
                }
            }

            moderightinfo.setNewRight(true);
            RecordSet maxSet = new RecordSet();
            maxSet.executeSql("select id from " + UF_TABLE_NAME + " where MODEDATACREATEDATE || MODEDATACREATETIME >= '" + currentTimeString + "'");

            int maxId;
            while (maxSet.next()) {
                maxId = maxSet.getInt("id");
                // 创建人id， 模块id， 该条数据id
                moderightinfo.editModeDataShare(1, Util.getIntValue(UF_TABLE_ID), maxId);
            }
        } catch (Exception e) {
            new BaseBean().writeLog("触发试用期流程，更新触发次数异常： " + e);
        }
    }

}
