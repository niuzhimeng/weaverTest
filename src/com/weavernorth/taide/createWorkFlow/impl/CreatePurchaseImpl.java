package com.weavernorth.taide.createWorkFlow.impl;

import com.alibaba.fastjson.JSON;
import com.weavernorth.taide.createWorkFlow.CreatePurchase;
import com.weavernorth.taide.createWorkFlow.vo.DetailTable;
import com.weavernorth.taide.createWorkFlow.vo.MainTable;
import com.weavernorth.taide.createWorkFlow.vo.Result;
import com.weavernorth.taide.util.ConnUtil;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.soa.workflow.request.RequestInfo;
import weaver.soa.workflow.request.RequestService;
import weaver.workflow.webservices.*;

import java.lang.reflect.Method;

/**
 * 08-SAP采购申请单 创建流程
 */
public class CreatePurchaseImpl implements CreatePurchase {

    private BaseBean baseBean = new BaseBean();

    @Override
    public Result createWorkFlow(MainTable mainTable) {
        String receive = JSON.toJSONString(mainTable);
        baseBean.writeLog("08-SAP采购申请单 创建流程，接收到信息：" + TimeUtil.getCurrentTimeString() + ": " + receive);

        String currentDateString = TimeUtil.getCurrentDateString(); // 当前日期
        String workCode = mainTable.getWorkCode(); // 工号
        Result result = new Result(); // 返回信息对象
        RecordSet recordSet = new RecordSet();
        try {
            recordSet.executeQuery("select * from hrmresource where workcode = '" + workCode + "'");
            if (!recordSet.next()) {
                result.setNumber(-1);
                result.setMessage("工号：" + workCode + ", 的人员不存在。");
                return result;
            }

            String creatorId = recordSet.getString("id"); // 创建人id
            String lastName = recordSet.getString("lastname"); // 姓名
            String workFlowId = String.valueOf(ConnUtil.getModeIdByType(9)); // 流程id

            WorkflowRequestTableField[] mainField = new WorkflowRequestTableField[10]; //主表行对象

            int i = 0;
            mainField[i] = new WorkflowRequestTableField();
            mainField[i].setFieldName("sqxm");// 字段名
            mainField[i].setFieldValue(creatorId); // 申请人
            mainField[i].setView(true); //字段是否可见
            mainField[i].setEdit(true); //字段是否可编辑

            i++;
            mainField[i] = new WorkflowRequestTableField();
            mainField[i].setFieldName("sqrbm");
            mainField[i].setFieldValue(recordSet.getString("departmentid")); // 申请人部门
            mainField[i].setView(true);
            mainField[i].setEdit(true);

            i++;
            mainField[i] = new WorkflowRequestTableField();
            mainField[i].setFieldName("sqrq");
            mainField[i].setFieldValue(currentDateString); // 申请日期
            mainField[i].setView(true);
            mainField[i].setEdit(true);

            i++;
            mainField[i] = new WorkflowRequestTableField();
            mainField[i].setFieldName("BANFN");
            mainField[i].setFieldValue(mainTable.getBANFN()); // 采购申请号
            mainField[i].setView(true);
            mainField[i].setEdit(true);

            i++;
            mainField[i] = new WorkflowRequestTableField();
            mainField[i].setFieldName("BSART");
            mainField[i].setFieldValue(mainTable.getBSART()); // 请购凭证类型
            mainField[i].setView(true);
            mainField[i].setEdit(true);

            i++;
            mainField[i] = new WorkflowRequestTableField();
            mainField[i].setFieldName("sqgh");
            mainField[i].setFieldValue(workCode); // 申请工号
            mainField[i].setView(true);
            mainField[i].setEdit(true);

            WorkflowRequestTableRecord[] mainRecord = new WorkflowRequestTableRecord[1];// 主字段只有一行数据
            mainRecord[0] = new WorkflowRequestTableRecord();
            mainRecord[0].setWorkflowRequestTableFields(mainField);

            WorkflowMainTableInfo workflowMainTableInfo = new WorkflowMainTableInfo();
            workflowMainTableInfo.setRequestRecords(mainRecord);

            //==========================================明细字段
            WorkflowDetailTableInfo[] detailTableInfos = new WorkflowDetailTableInfo[1];// 明细表数组

            // ==================================== 明细表1start
            DetailTable[] detailTables = mainTable.getDetailTables(); // 明细表数组
            int length = detailTables.length;
            WorkflowRequestTableRecord[] detailRecord = new WorkflowRequestTableRecord[length];//明细表行数组
            DetailTable detailTable;
            for (int j = 0; j < length; j++) {
                detailTable = detailTables[j];
                WorkflowRequestTableField[] detailField1 = new WorkflowRequestTableField[12]; // 明细表列数组，每行12个字段
                i = 0;
                detailField1[i] = new WorkflowRequestTableField();
                detailField1[i].setFieldName("BANFN");
                detailField1[i].setFieldValue(detailTable.getBANFN()); // 采购申请号
                detailField1[i].setView(true);
                detailField1[i].setEdit(true);

                i++;
                detailField1[i] = new WorkflowRequestTableField();
                detailField1[i].setFieldName("BNFPO");
                detailField1[i].setFieldValue(detailTable.getBNFPO()); // 行项目
                detailField1[i].setView(true);
                detailField1[i].setEdit(true);

                i++;
                detailField1[i] = new WorkflowRequestTableField();
                detailField1[i].setFieldName("KNTTP");
                detailField1[i].setFieldValue(detailTable.getKNTTP()); // 科目分配类别
                detailField1[i].setView(true);
                detailField1[i].setEdit(true);

                i++;
                detailField1[i] = new WorkflowRequestTableField();
                detailField1[i].setFieldName("MATNR");
                detailField1[i].setFieldValue(detailTable.getMATNR()); // 物料编码
                detailField1[i].setView(true);
                detailField1[i].setEdit(true);

                i++;
                detailField1[i] = new WorkflowRequestTableField();
                detailField1[i].setFieldName("WERKS");
                detailField1[i].setFieldValue(detailTable.getWERKS()); // 工厂
                detailField1[i].setView(true);
                detailField1[i].setEdit(true);

                i++;
                detailField1[i] = new WorkflowRequestTableField();
                detailField1[i].setFieldName("MENGE");
                detailField1[i].setFieldValue(detailTable.getMENGE()); // 申请数量
                detailField1[i].setView(true);
                detailField1[i].setEdit(true);

                i++;
                detailField1[i] = new WorkflowRequestTableField();
                detailField1[i].setFieldName("MEINS");
                detailField1[i].setFieldValue(detailTable.getMEINS()); // 基本计量单位
                detailField1[i].setView(true);
                detailField1[i].setEdit(true);

                i++;
                detailField1[i] = new WorkflowRequestTableField();
                detailField1[i].setFieldName("AFNAM");
                detailField1[i].setFieldValue(detailTable.getAFNAM()); // 申请人
                detailField1[i].setView(true);
                detailField1[i].setEdit(true);

                i++;
                detailField1[i] = new WorkflowRequestTableField();
                detailField1[i].setFieldName("TEXT01");
                detailField1[i].setFieldValue(detailTable.getTEXT01()); // 需求项目文本
                detailField1[i].setView(true);
                detailField1[i].setEdit(true);

                i++;
                detailField1[i] = new WorkflowRequestTableField();
                detailField1[i].setFieldName("TEXT02");
                detailField1[i].setFieldValue(detailTable.getTEXT02()); // 研发项目文本
                detailField1[i].setView(true);
                detailField1[i].setEdit(true);

                i++;
                detailField1[i] = new WorkflowRequestTableField();
                detailField1[i].setFieldName("LFDAT");
                detailField1[i].setFieldValue(detailTable.getLFDAT()); // 需求日期
                detailField1[i].setView(true);
                detailField1[i].setEdit(true);

                i++;
                detailField1[i] = new WorkflowRequestTableField();
                detailField1[i].setFieldName("LIFNR");
                detailField1[i].setFieldValue(detailTable.getLIFNR()); // 所需供应商
                detailField1[i].setView(true);
                detailField1[i].setEdit(true);

                detailRecord[j] = new WorkflowRequestTableRecord();
                detailRecord[j].setWorkflowRequestTableFields(detailField1);
            }
            detailTableInfos[0] = new WorkflowDetailTableInfo();
            detailTableInfos[0].setWorkflowRequestTableRecords(detailRecord);

            //====================================流程基本信息录入
            WorkflowBaseInfo workflowBaseInfo = new WorkflowBaseInfo();
            workflowBaseInfo.setWorkflowId(workFlowId);// 流程id

            WorkflowRequestInfo workflowRequestInfo = new WorkflowRequestInfo();// 流程基本信息
            workflowRequestInfo.setCreatorId(creatorId);// 创建人id
            workflowRequestInfo.setRequestLevel("0");// 0 正常，1重要，2紧急
            workflowRequestInfo.setRequestName("08-SAP采购申请单" + lastName + "-" + currentDateString);// 流程标题
            workflowRequestInfo.setWorkflowBaseInfo(workflowBaseInfo);
            workflowRequestInfo.setWorkflowMainTableInfo(workflowMainTableInfo);// 添加主表字段数据
            workflowRequestInfo.setWorkflowDetailTableInfos(detailTableInfos);// 添加明细表字段数据

            //创建流程的类
            WorkflowServiceImpl service = new WorkflowServiceImpl();
            //service.doCreateWorkflowRequest()
            baseBean.writeLog("准备创建流程===============");

            Class<? extends WorkflowServiceImpl> aClass = service.getClass();
            Method method1 = aClass.getDeclaredMethod("getActiveWorkflowRequestInfo", WorkflowRequestInfo.class);
            method1.setAccessible(true);
            workflowRequestInfo = (WorkflowRequestInfo) method1.invoke(service, workflowRequestInfo);

            Method method2 = aClass.getDeclaredMethod("toRequestInfo", WorkflowRequestInfo.class);
            method2.setAccessible(true);
            RequestInfo requestInfo = (RequestInfo) method2.invoke(service, workflowRequestInfo);
            if (requestInfo.getCreatorid() == null || requestInfo.getCreatorid().isEmpty()) {
                requestInfo.setCreatorid(creatorId);
            }

            if (!requestInfo.getCreatorid().equals(creatorId)) {
                requestInfo.setCreatorid(creatorId);
            }

            requestInfo.setIsNextFlow("0");
            RequestService requestService = new RequestService();
            String returnId = requestService.createRequest(requestInfo);
            int returnInt = Integer.parseInt(returnId);
            result.setNumber(returnInt);

            if (returnInt > 0) {
                result.setMessage("流程创建成功");
            } else if (returnInt == -1) {
                result.setMessage("流程创建失败");
            } else if (returnInt == -2) {
                result.setMessage("没有创建权限");
            } else if (returnInt == -3) {
                result.setMessage("流程创建失败");
            } else if (returnInt == -4) {
                result.setMessage("字段或表名不正确");
            } else if (returnInt == -5) {
                result.setMessage("更新流程级别失败");
            } else if (returnInt == -6) {
                result.setMessage("无法创建流程待办任务");
            } else if (returnInt == -7) {
                result.setMessage("流程下一节点出错，请检查流程的配置，在OA中发起流程进行测试");
            } else if (returnInt == -8) {
                result.setMessage("流程节点自动赋值操作错误");
            }

            baseBean.writeLog("08-SAP采购申请单 创建流程END");
        } catch (Exception e) {
            baseBean.writeLog("08-SAP采购申请单 创建流程异常： " + e);
        }
        return result;
    }
}
