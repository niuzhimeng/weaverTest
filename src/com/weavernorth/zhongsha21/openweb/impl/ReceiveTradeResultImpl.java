package com.weavernorth.zhongsha21.openweb.impl;

import com.alibaba.fastjson.JSONObject;
import com.weavernorth.zhongsha21.openweb.ReceiveSapInfo;
import com.weavernorth.zhongsha21.openweb.vo.*;
import com.weavernorth.zhongsha21.util.ZhShaConfig;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.general.Util;
import weaver.workflow.webservices.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 交易结果回传oa，并更新oa台账
 */
public class ReceiveTradeResultImpl implements ReceiveSapInfo {

    private final BaseBean baseBean = new BaseBean();

    private static final Map<String, String> splMap = new HashMap<String, String>();

    static {
        splMap.put("A", "0");
        splMap.put("A+B", "1");
        splMap.put("A+A", "2");
        splMap.put("B+B", "3");
        splMap.put("A+B+B", "4");
    }

    @Override
    public ResultVO receiveTradeResult(TradeResultVO[] tradeResultVO) {
        RecordSet recordSet = new RecordSet();
        ResultVO resultVO1 = new ResultVO();
        try {
            baseBean.writeLog("交易结果回传oa Start============== " + JSONObject.toJSONString(tradeResultVO));

            for (TradeResultVO resultVO : tradeResultVO) {
                baseBean.writeLog("交易编号：" + resultVO.getZOAID() + ", 公司代码: " + resultVO.getBUKRS() +
                        ", 付款状态: " + resultVO.getZFKZT());
                String zfkzt = resultVO.getZFKZT(); // 付款状态
                String ztVal = "0";
                if ("S".equalsIgnoreCase(zfkzt)) {
                    ztVal = "1";
                } else if ("E".equalsIgnoreCase(zfkzt)) {
                    ztVal = "2";
                }

                recordSet.executeUpdate("update uf_tdlb set zxjd = ? where dh = ?", ztVal, resultVO.getZOAID());
            }

            resultVO1.setCode("S");
            resultVO1.setMessage("交易结果更新成功");
            baseBean.writeLog("交易结果回传oaEnd==============");
        } catch (Exception e) {
            resultVO1.setCode("E");
            resultVO1.setMessage("交易结果更新异常");
            baseBean.writeLog("资金安排流程交易结果 异常" + e);
        }
        return resultVO1;
    }

    @Override
    public ResultVO createFlowOfPaiKuan(MainTable mainTable) {
        baseBean.writeLog("创建排款流程Start===============接收参数： " + JSONObject.toJSONString(mainTable));
        RecordSet recordSet = new RecordSet();
        ResultVO resultVO1 = new ResultVO();
        String creatorId = ""; // 创建人
        String creatorName = ""; // 创建人姓名
        String message = "";
        int returnInt;
        try {
            String zcomu = Util.null2String(mainTable.getZCOMU()).toLowerCase(); // 创建人
            recordSet.executeQuery("select id, lastname from hrmresource where loginid = ?", zcomu);
            if (recordSet.next()) {
                creatorId = recordSet.getString("id");
                creatorName = recordSet.getString("lastname");
            } else {
                resultVO1.setCode("E");
                resultVO1.setMessage("流程创建人不存在");
                return resultVO1;
            }

            WorkflowRequestTableField[] mainField = new WorkflowRequestTableField[10]; //主表行对象

            int i = 0;
            mainField[i] = new WorkflowRequestTableField();
            mainField[i].setFieldName("tjr"); // 字段名
            mainField[i].setFieldValue(creatorId); // 申请人
            mainField[i].setView(true);
            mainField[i].setEdit(true);

            i++;
            mainField[i] = new WorkflowRequestTableField();
            mainField[i].setFieldName("rq");
            mainField[i].setFieldValue(dataChange(mainTable.getERDAT())); // 日期
            mainField[i].setView(true);
            mainField[i].setEdit(true);

            i++;
            mainField[i] = new WorkflowRequestTableField();
            mainField[i].setFieldName("dbdh");
            mainField[i].setFieldValue(mainTable.getZAPPN()); // 打包单号
            mainField[i].setView(true);
            mainField[i].setEdit(true);

            i++;
            mainField[i] = new WorkflowRequestTableField();
            mainField[i].setFieldName("spl");
            mainField[i].setFieldValue(splMap.get(Util.null2String(mainTable.getZTEXT()).trim())); // 审批流
            mainField[i].setView(true);
            mainField[i].setEdit(true);

            i++;
            mainField[i] = new WorkflowRequestTableField();
            mainField[i].setFieldName("hjje");
            mainField[i].setFieldValue(mainTable.getZPAYN()); // 金额
            mainField[i].setView(true);
            mainField[i].setEdit(true);

            i++;
            mainField[i] = new WorkflowRequestTableField();
            mainField[i].setFieldName("dbspjg");
            mainField[i].setFieldValue("0"); // 打包审批结果
            mainField[i].setView(true);
            mainField[i].setEdit(true);

            WorkflowRequestTableRecord[] mainRecord = new WorkflowRequestTableRecord[1]; // 主字段只有一行数据
            mainRecord[0] = new WorkflowRequestTableRecord();
            mainRecord[0].setWorkflowRequestTableFields(mainField);

            WorkflowMainTableInfo workflowMainTableInfo = new WorkflowMainTableInfo();
            workflowMainTableInfo.setRequestRecords(mainRecord);
            //==========================================明细字段
            WorkflowDetailTableInfo[] detailTableInfos = new WorkflowDetailTableInfo[1]; // 明细表数组

            // ==================================== 明细表1start
            DetailTable[] detailTableList = mainTable.getDetailTables(); // 明细表数组
            int length = detailTableList.length;
            WorkflowRequestTableRecord[] detailRecord = new WorkflowRequestTableRecord[length]; //明细表行数组
            int j = 0;
            for (DetailTable detailTable : detailTableList) {
                WorkflowRequestTableField[] detailField1 = new WorkflowRequestTableField[17]; // 明细表列数组，每行17个字段

                String[] returns = getMode(detailTable.getZOAID());
                i = 0;
                detailField1[i] = new WorkflowRequestTableField();
                detailField1[i].setFieldName("OAfkdh");
                detailField1[i].setFieldValue(returns[0]); // OA单号
                detailField1[i].setView(true);
                detailField1[i].setEdit(true);

                i++;
                detailField1[i] = new WorkflowRequestTableField();
                detailField1[i].setFieldName("xglc");
                detailField1[i].setFieldValue(returns[1]); // 相关流程
                detailField1[i].setView(true);
                detailField1[i].setEdit(true);

                i++;
                detailField1[i] = new WorkflowRequestTableField();
                detailField1[i].setFieldName("bm");
                detailField1[i].setFieldValue(returns[2]); // 部门
                detailField1[i].setView(true);
                detailField1[i].setEdit(true);

                i++;
                detailField1[i] = new WorkflowRequestTableField();
                detailField1[i].setFieldName("ywlx");
                detailField1[i].setFieldValue(returns[3]); // 业务类型
                detailField1[i].setView(true);
                detailField1[i].setEdit(true);

                i++;
                detailField1[i] = new WorkflowRequestTableField();
                detailField1[i].setFieldName("htbh");
                detailField1[i].setFieldValue(returns[4]); // 合同编号
                detailField1[i].setView(true);
                detailField1[i].setEdit(true);

                i++;
                detailField1[i] = new WorkflowRequestTableField();
                detailField1[i].setFieldName("bmstr");
                detailField1[i].setFieldValue(detailTable.getZDEPT()); // 部门
                detailField1[i].setView(true);
                detailField1[i].setEdit(true);

                i++;
                detailField1[i] = new WorkflowRequestTableField();
                detailField1[i].setFieldName("ywlx");
                detailField1[i].setFieldValue(detailTable.getZANLF()); // 业务类型
                detailField1[i].setView(true);
                detailField1[i].setEdit(true);

                i++;
                detailField1[i] = new WorkflowRequestTableField();
                detailField1[i].setFieldName("htbh");
                detailField1[i].setFieldValue(detailTable.getZHTBH()); // 合同编号
                detailField1[i].setView(true);
                detailField1[i].setEdit(true);

                i++;
                detailField1[i] = new WorkflowRequestTableField();
                detailField1[i].setFieldName("fkrq");
                detailField1[i].setFieldValue(dataChange(detailTable.getZPAYD())); // 付款日期
                detailField1[i].setView(true);
                detailField1[i].setEdit(true);

                i++;
                detailField1[i] = new WorkflowRequestTableField();
                detailField1[i].setFieldName("gysmc");
                detailField1[i].setFieldValue(detailTable.getKOINH()); // 供应商名称
                detailField1[i].setView(true);
                detailField1[i].setEdit(true);

                i++;
                detailField1[i] = new WorkflowRequestTableField();
                detailField1[i].setFieldName("jjsx");
                detailField1[i].setFieldValue(detailTable.getZJJSX()); // 经济事项
                detailField1[i].setView(true);
                detailField1[i].setEdit(true);

                i++;
                detailField1[i] = new WorkflowRequestTableField();
                detailField1[i].setFieldName("yfje");
                detailField1[i].setFieldValue(detailTable.getZPAYN()); // 应付金额
                detailField1[i].setView(true);
                detailField1[i].setEdit(true);

                i++;
                detailField1[i] = new WorkflowRequestTableField();
                detailField1[i].setFieldName("hbdw");
                detailField1[i].setFieldValue(detailTable.getWAERS()); // 货币单位
                detailField1[i].setView(true);
                detailField1[i].setEdit(true);

                i++;
                detailField1[i] = new WorkflowRequestTableField();
                detailField1[i].setFieldName("spyj");
                detailField1[i].setFieldValue("0"); // 审批意见
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
            workflowBaseInfo.setWorkflowId(ZhShaConfig.FU_KUAN_FLOW_ID.getValue());// 流程id

            WorkflowRequestInfo workflowRequestInfo = new WorkflowRequestInfo();// 流程基本信息
            workflowRequestInfo.setCreatorId(creatorId);// 创建人id
            workflowRequestInfo.setRequestLevel("0");// 0 正常，1重要，2紧急
            workflowRequestInfo.setRequestName("付款印鉴审批-" + creatorName + "-" + TimeUtil.getCurrentDateString());// 流程标题
            workflowRequestInfo.setWorkflowBaseInfo(workflowBaseInfo);
            workflowRequestInfo.setWorkflowMainTableInfo(workflowMainTableInfo);// 添加主表字段数据
            workflowRequestInfo.setWorkflowDetailTableInfos(detailTableInfos);// 添加明细表字段数据
            workflowRequestInfo.setIsnextflow("0");

            //创建流程的类
            WorkflowServiceImpl service = new WorkflowServiceImpl();
            String requestId = service.doCreateWorkflowRequest(workflowRequestInfo, Integer.parseInt(creatorId));
            baseBean.writeLog("创建流程完毕=============== " + requestId);
            returnInt = Util.getIntValue(requestId, -9);

            if (returnInt > 0) {
                message = "流程创建成功";
            } else if (returnInt == -1) {
                message = "流程创建失败";
            } else if (returnInt == -2) {
                message = "没有创建权限";
            } else if (returnInt == -3) {
                message = "流程创建失败";
            } else if (returnInt == -4) {
                message = "字段或表名不正确";
            } else if (returnInt == -5) {
                message = "更新流程级别失败";
            } else if (returnInt == -6) {
                message = "无法创建流程待办任务";
            } else if (returnInt == -7) {
                message = "流程下一节点出错，请检查流程的配置，在OA中发起流程进行测试";
            } else if (returnInt == -8) {
                message = "流程节点自动赋值操作错误";
            }
        } catch (Exception e) {
            resultVO1.setCode("E");
            resultVO1.setMessage("创建提醒流程异常");
            baseBean.writeLog("创建提醒流程异常： " + e);
            return resultVO1;
        }

        if (returnInt > 0) {
            resultVO1.setCode("S");
            resultVO1.setRequestId(String.valueOf(returnInt));
        } else {
            resultVO1.setCode("E");
        }
        resultVO1.setMessage(message);
        return resultVO1;
    }

    private String[] getMode(String zoaid) {
        String[] returns = new String[5];
        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("select id, lcid, bm, ywlx, htbh from uf_tdlb where dh = '" + zoaid + "'");
        if (recordSet.next()) {
            returns[0] = recordSet.getString("id");
            returns[1] = recordSet.getString("lcid");
            returns[2] = recordSet.getString("bm");
            returns[3] = recordSet.getString("ywlx");
            returns[4] = recordSet.getString("htbh");
        }

        return returns;
    }

    @Override
    public ResultVO paymentWithdraw(PaymentWithdrawVO[] paymentWithdrawVO) {
        RecordSet recordSet = new RecordSet();
        RecordSet updateSet = new RecordSet();
        ResultVO resultVO1 = new ResultVO();
        try {
            baseBean.writeLog("付款申请撤回数据回传oa Start============== " + JSONObject.toJSONString(paymentWithdrawVO));

            for (PaymentWithdrawVO withdrawVO : paymentWithdrawVO) {
                String zappn = withdrawVO.getZAPPN(); // 打包单号
                String zoaid = withdrawVO.getZOAID();// 审批编号
                String zchyy = withdrawVO.getZCHYY();// 撤回原因

                recordSet.executeQuery("select id from " + ZhShaConfig.FU_KUAN_TABLE_NAME.getValue() + " where dbdh = ? order by id desc", zappn);
                if (recordSet.next()) {
                    String id = recordSet.getString("id");
                    baseBean.writeLog("主表数据id： " + id);
                    updateSet.executeUpdate("update " + ZhShaConfig.FU_KUAN_TABLE_NAME.getValue() + "_dt1 " +
                            " set spyj = 2, bz = ? where OAfkdh = ? and mainid = ?", zchyy, zoaid, id);
                }
            }

            resultVO1.setCode("S");
            resultVO1.setMessage("付款申请撤回数据回传oa成功");
            baseBean.writeLog("付款申请撤回数据回传oaEnd==============");
        } catch (Exception e) {
            resultVO1.setCode("E");
            resultVO1.setMessage("付款申请撤回数据回传oa异常");
            baseBean.writeLog("付款申请撤回数据回传oa 异常" + e);
        }
        return resultVO1;

    }


    /**
     * yyyyMMdd -> yyyy-MM-dd
     */
    private String dataChange(String rq) {
        rq = Util.null2String(rq);
        String oaRq = "";
        if (rq.length() == 8) {
            String year = rq.substring(0, 4);
            String month = rq.substring(4, 6);
            String day = rq.substring(6);
            oaRq = year + "-" + month + "-" + day;
        }

        return oaRq;
    }

    /**
     * 根据某一字段查另一个字段
     *
     * @param resultField 查询的字段名
     * @param tableName   查询表名
     * @param selField    条件字段名
     */
    private String getSysByFiled(String resultField, String tableName, String whereId, String selField) {
        RecordSet connSet = new RecordSet();
        String returnStr = "";
        connSet.executeQuery("select " + resultField + " from " + tableName + " where " + whereId + " = '" + selField + "'");
        if (connSet.next()) {
            returnStr = connSet.getString(resultField);
        }
        return returnStr;
    }
}
