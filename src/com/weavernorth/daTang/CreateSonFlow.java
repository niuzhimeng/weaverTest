package com.weavernorth.daTang;

import com.weavernorth.daTang.vo.DetailVo;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.general.TimeUtil;
import weaver.soa.workflow.request.RequestInfo;
import weaver.soa.workflow.request.RequestService;
import weaver.workflow.action.BaseAction;
import weaver.workflow.webservices.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创建子流程
 */
public class CreateSonFlow extends BaseAction {

    private static final String WORK_FLOW_ID = "504";//子流程id

    @Override
    public String execute(RequestInfo request) {
        int formid = request.getRequestManager().getFormid();
        String tableName = "";
        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("SELECT tablename FROM workflow_bill WHERE id = '" + formid + "'");
        if (recordSet.next()) {
            tableName = recordSet.getString("tablename");
        }
        String requestId = request.getRequestid();
        //当前操作类型 submit：提交  reject：退回
        String src = request.getRequestManager().getSrc();
        if ("reject".equals(src)) {
            return "1";
        }

        this.writeLog("====================根据明细表创建子流程执行 " + TimeUtil.getCurrentTimeString() + ", tableName: " + tableName);
        String creatorId = ""; // 创建人id
        String bm = ""; // 部门id
        String nf = ""; // 年份
        String yf = ""; // 月份

        recordSet.executeQuery("select * from " + tableName + " where requestId = '" + requestId + "'");
        if (recordSet.next()) {
            creatorId = recordSet.getString("tbr");
            bm = recordSet.getString("bm");
            nf = recordSet.getString("nf");
            yf = recordSet.getString("yf");
        }

        //本次创建子流程的 明细表id集合
        List<String> benCiCreatedDetailId = new ArrayList<String>();

        //查找已创建子流程的明细表id
        List<String> createdDetailIdList = new ArrayList<String>();
        RecordSet createdRecordSet = new RecordSet();
        createdRecordSet.executeQuery("SELECT * FROM uf_MainAndDetail where main_requestId = '" + requestId + "' and my_type = 'person'");
        while (createdRecordSet.next()) {
            createdDetailIdList.add(createdRecordSet.getString("detail_id"));
        }

        String sql = "select d.* from " + tableName + "_dt1 d left join " + tableName + " m on m.id = d.mainid where m.requestId = '" + requestId + "'";
        this.writeLog("查询sql： " + sql);
        recordSet.executeQuery(sql);
        //项目经理 - 明细行
        Map<String, List<DetailVo>> listMap = new HashMap<String, List<DetailVo>>();
        String xmjl;//项目经理
        while (recordSet.next()) {
            if (createdDetailIdList.contains(recordSet.getString("id"))) {
                continue;
            }
            benCiCreatedDetailId.add(recordSet.getString("id"));
            DetailVo detailVo = new DetailVo();
            detailVo.setId(recordSet.getString("id"));

            detailVo.setEjbm(recordSet.getString("ejbm"));
            detailVo.setSjbm(recordSet.getString("sjbm"));
            detailVo.setXm(recordSet.getString("xm"));
            detailVo.setGh(recordSet.getString("gh"));
            detailVo.setRylb(recordSet.getString("rylb"));

            detailVo.setBmgg(recordSet.getString("bmgg"));
            detailVo.setXmbh(recordSet.getString("xmbh"));
            detailVo.setXmmc(recordSet.getString("xmmc"));
            detailVo.setYwfx(recordSet.getString("ywfx"));
            detailVo.setGsqzzj(recordSet.getString("gsqzzj"));

            detailVo.setSm(recordSet.getString("sm"));
            xmjl = recordSet.getString("xmjl");
            detailVo.setXmjl(xmjl);

            if (listMap.containsKey(xmjl)) {
                listMap.get(xmjl).add(detailVo);
            } else {
                List<DetailVo> list = new ArrayList<DetailVo>();
                list.add(detailVo);
                listMap.put(xmjl, list);
            }
        }

        //创建子流程
        try {
            List<String> createdSonFlowList = new ArrayList<String>();//已创建子流程集合
            for (Map.Entry<String, List<DetailVo>> entry : listMap.entrySet()) {
                WorkflowRequestTableField[] mainField = new WorkflowRequestTableField[15]; //主表行对象
                int i = 0;
                mainField[i] = new WorkflowRequestTableField();
                mainField[i].setFieldName("tbr");//填报人
                mainField[i].setFieldValue(creatorId); // 字段值
                mainField[i].setView(true); //字段是否可见
                mainField[i].setEdit(true); //字段是否可编辑

                i++;
                mainField[i] = new WorkflowRequestTableField();
                mainField[i].setFieldName("bm");
                mainField[i].setFieldValue(bm);
                mainField[i].setView(true);
                mainField[i].setEdit(true);

                i++;
                mainField[i] = new WorkflowRequestTableField();
                mainField[i].setFieldName("tbsd");
                mainField[i].setFieldValue(TimeUtil.getCurrentTimeString());
                mainField[i].setView(true);
                mainField[i].setEdit(true);

                i++;
                mainField[i] = new WorkflowRequestTableField();
                mainField[i].setFieldName("nf");
                mainField[i].setFieldValue(nf);
                mainField[i].setView(true);
                mainField[i].setEdit(true);

                i++;
                mainField[i] = new WorkflowRequestTableField();
                mainField[i].setFieldName("yf");
                mainField[i].setFieldValue(yf);
                mainField[i].setView(true);
                mainField[i].setEdit(true);

                WorkflowRequestTableRecord[] mainRecord = new WorkflowRequestTableRecord[1];// 主字段只有一行数据
                mainRecord[0] = new WorkflowRequestTableRecord();
                mainRecord[0].setWorkflowRequestTableFields(mainField);

                WorkflowMainTableInfo workflowMainTableInfo = new WorkflowMainTableInfo();
                workflowMainTableInfo.setRequestRecords(mainRecord);

                //=======================明细表=================
                WorkflowDetailTableInfo[] detailTableInfos = new WorkflowDetailTableInfo[1];// 明细表数组
                // 明细表1start
                WorkflowRequestTableRecord[] detailRecord = new WorkflowRequestTableRecord[entry.getValue().size()];//明细表对象(行的数组)
                int j = 0;//行数
                for (DetailVo detailVo : entry.getValue()) {
                    WorkflowRequestTableField[] detailField1 = new WorkflowRequestTableField[15]; // 行对象（列的数组）
                    i = 0;
                    detailField1[i] = new WorkflowRequestTableField();
                    detailField1[i].setFieldName("ejbm");
                    detailField1[i].setFieldValue(detailVo.getEjbm());
                    detailField1[i].setView(true);
                    detailField1[i].setEdit(true);

                    i++;
                    detailField1[i] = new WorkflowRequestTableField();
                    detailField1[i].setFieldName("sjbm");
                    detailField1[i].setFieldValue(detailVo.getSjbm());
                    detailField1[i].setView(true);
                    detailField1[i].setEdit(true);

                    i++;
                    detailField1[i] = new WorkflowRequestTableField();
                    detailField1[i].setFieldName("xm");
                    detailField1[i].setFieldValue(detailVo.getXm());
                    detailField1[i].setView(true);
                    detailField1[i].setEdit(true);

                    i++;
                    detailField1[i] = new WorkflowRequestTableField();
                    detailField1[i].setFieldName("gh");
                    detailField1[i].setFieldValue(detailVo.getGh());
                    detailField1[i].setView(true);
                    detailField1[i].setEdit(true);

                    i++;
                    detailField1[i] = new WorkflowRequestTableField();
                    detailField1[i].setFieldName("rylb");
                    detailField1[i].setFieldValue(detailVo.getRylb());
                    detailField1[i].setView(true);
                    detailField1[i].setEdit(true);

                    i++;
                    detailField1[i] = new WorkflowRequestTableField();
                    detailField1[i].setFieldName("bmgg");
                    detailField1[i].setFieldValue(detailVo.getBmgg());
                    detailField1[i].setView(true);
                    detailField1[i].setEdit(true);

                    i++;
                    detailField1[i] = new WorkflowRequestTableField();
                    detailField1[i].setFieldName("xmbh");
                    detailField1[i].setFieldValue(detailVo.getXmbh());
                    detailField1[i].setView(true);
                    detailField1[i].setEdit(true);

                    i++;
                    detailField1[i] = new WorkflowRequestTableField();
                    detailField1[i].setFieldName("xmmc");
                    detailField1[i].setFieldValue(detailVo.getXmmc());
                    detailField1[i].setView(true);
                    detailField1[i].setEdit(true);

                    i++;
                    detailField1[i] = new WorkflowRequestTableField();
                    detailField1[i].setFieldName("ywfx");
                    detailField1[i].setFieldValue(detailVo.getYwfx());
                    detailField1[i].setView(true);
                    detailField1[i].setEdit(true);

                    i++;
                    detailField1[i] = new WorkflowRequestTableField();
                    detailField1[i].setFieldName("gsqzzj");
                    detailField1[i].setFieldValue(detailVo.getGsqzzj());
                    detailField1[i].setView(true);
                    detailField1[i].setEdit(true);

                    i++;
                    detailField1[i] = new WorkflowRequestTableField();
                    detailField1[i].setFieldName("sm");
                    detailField1[i].setFieldValue(detailVo.getSm());
                    detailField1[i].setView(true);
                    detailField1[i].setEdit(true);

                    i++;
                    detailField1[i] = new WorkflowRequestTableField();
                    detailField1[i].setFieldName("xmjl");
                    detailField1[i].setFieldValue(detailVo.getXmjl());
                    detailField1[i].setView(true);
                    detailField1[i].setEdit(true);

                    i++;
                    detailField1[i] = new WorkflowRequestTableField();
                    detailField1[i].setFieldName("main_detail_id");
                    detailField1[i].setFieldValue(detailVo.getId());
                    detailField1[i].setView(true);
                    detailField1[i].setEdit(true);

                    detailRecord[j] = new WorkflowRequestTableRecord();
                    detailRecord[j].setWorkflowRequestTableFields(detailField1);
                    j++;
                }
                detailTableInfos[0] = new WorkflowDetailTableInfo();
                detailTableInfos[0].setWorkflowRequestTableRecords(detailRecord);

                //====================================流程基本信息录入
                WorkflowBaseInfo workflowBaseInfo = new WorkflowBaseInfo();
                workflowBaseInfo.setWorkflowId(WORK_FLOW_ID);// 流程id

                WorkflowRequestInfo workflowRequestInfo = new WorkflowRequestInfo();// 流程基本信息
                workflowRequestInfo.setCreatorId(creatorId);// 创建人id
                workflowRequestInfo.setRequestLevel("0");// 0 正常，1重要，2紧急
                workflowRequestInfo.setRequestName("工时审批（子流程） " + TimeUtil.getCurrentTimeString());// 流程标题
                workflowRequestInfo.setWorkflowBaseInfo(workflowBaseInfo);
                workflowRequestInfo.setWorkflowMainTableInfo(workflowMainTableInfo);// 添加主表字段数据
                workflowRequestInfo.setWorkflowDetailTableInfos(detailTableInfos);// 添加明细表字段数据

                //创建流程的类
                WorkflowServiceImpl service = new WorkflowServiceImpl();
                this.writeLog("准备创建流程===============");

                Class<? extends WorkflowServiceImpl> aClass = service.getClass();
                Method method1 = aClass.getDeclaredMethod("getActiveWorkflowRequestInfo", WorkflowRequestInfo.class);
                method1.setAccessible(true);
                workflowRequestInfo = (WorkflowRequestInfo) method1.invoke(service, workflowRequestInfo);

                this.writeLog("准备创建流程===============");

                Method method2 = aClass.getDeclaredMethod("toRequestInfo", WorkflowRequestInfo.class);
                method2.setAccessible(true);
                RequestInfo requestInfo = (RequestInfo) method2.invoke(service, workflowRequestInfo);
                if (requestInfo.getCreatorid() == null || requestInfo.getCreatorid().isEmpty()) {
                    requestInfo.setCreatorid(creatorId);
                }

                if (!requestInfo.getCreatorid().equals(creatorId)) {
                    requestInfo.setCreatorid(creatorId);
                }
                this.writeLog("准备创建流程===============");
                //requestInfo.setIsNextFlow("0");
                RequestService requestService = new RequestService();
                String returnStr = requestService.createRequest(requestInfo);
                createdSonFlowList.add(returnStr);
                this.writeLog("创建流程完毕=============== " + returnStr);
            }
            insertRecord(requestId, "person", benCiCreatedDetailId);
            insertRecord(requestId, "flow", createdSonFlowList);
        } catch (Exception e) {
            this.writeLog("CreateSonFlow创建子流程异常： " + e);
        }
        this.writeLog("====================根据明细表创建子流程完成 " + TimeUtil.getCurrentTimeString());
        return "1";
    }

    /**
     * 插入关系映射表
     *
     * @param requestId 主流程id
     * @param myType    数据种类
     * @param list      已创建人员或子流程id的集合
     */
    private void insertRecord(String requestId, String myType, List<String> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        ConnStatement statement = new ConnStatement();
        try {
            statement.setStatementSql("insert into uf_MainAndDetail(main_requestId, detail_id, my_type) " +
                    "values(?, ?, ?)");
            for (String value : list) {
                statement.setString(1, requestId);
                statement.setString(2, value);
                statement.setString(3, myType);
                statement.executeUpdate();
            }
        } catch (Exception e) {
            this.writeLog("insertRecord异常： " + e);
        } finally {
            statement.close();
        }
    }
}
