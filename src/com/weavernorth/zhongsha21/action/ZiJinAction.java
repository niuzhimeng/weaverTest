package com.weavernorth.zhongsha21.action;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoTable;
import com.weavernorth.zhongsha21.util.CreateWorkflowUtil;
import com.weavernorth.zhongsha21.util.ZhsPoolThreeTest;
import weaver.conn.RecordSet;
import weaver.general.Util;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

import java.util.ArrayList;
import java.util.List;

/**
 * 资金安排审批流程推送sap
 */
public class ZiJinAction extends BaseAction {


    @Override
    public String execute(RequestInfo requestInfo) {
        String requestId = requestInfo.getRequestid();
        String operateType = requestInfo.getRequestManager().getSrc();
        int formId = requestInfo.getRequestManager().getFormid();
        String tableName = "";
        RecordSet recordSet = new RecordSet();
        RecordSet detailSet = new RecordSet();
        recordSet.executeQuery("SELECT tablename FROM workflow_bill WHERE id = '" + formId + "'");
        if (recordSet.next()) {
            tableName = recordSet.getString("tablename");
        }

        this.writeLog("资金安排审批流程 Start requestid=" + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {

            JCoDestination jCoDestination = ZhsPoolThreeTest.getJCoDestination();
            jCoDestination.ping();
            this.writeLog("ping 通=====");
            JCoFunction function = jCoDestination.getRepository().getFunction("ZCM_EHQ00410");
            this.writeLog("function========== " + function);
            JCoParameterList importParameterList = function.getImportParameterList();

            // 查询主表
            recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");
            recordSet.next();
            String mainId = recordSet.getString("id");
            String tjr = recordSet.getString("tjr"); // 提交人

            detailSet.executeQuery("select * from " + tableName + "_dt1 where mainid = '" + mainId + "'");

            StringBuilder stringBuilder = new StringBuilder();// 存放错误信息 单号 - 错误信息
            List<String> oaList = new ArrayList<String>(); // 存放已推送过的oa单号，避免在异常提醒流程中 重复提示
            int i = 1;
            while (detailSet.next()) {
                // 拼接参数
                String zch = detailSet.getString("zch"); // 转出行
                String[] mOdeData = getModeData(zch);
                String zwjc = Util.null2String(mOdeData[5]); // 中文简称 只有【工行】才传递
                if (!zwjc.startsWith("工行")) {
                    continue;
                }
                importParameterList.setValue("I_BANKS1", mOdeData[0]); // 银行国家代码
                importParameterList.setValue("I_BANKL1", mOdeData[1]); // 银行编号
                importParameterList.setValue("I_BANKN1", mOdeData[2]); // 银行账户号码(全)
                importParameterList.setValue("I_BANKA1", mOdeData[3]); // 转出行银行名称
                importParameterList.setValue("I_KOINH1", mOdeData[4]); // 帐户持有人姓名

                importParameterList.setValue("I_ZOAID", detailSet.getString("jybh")); // 交易编号
                importParameterList.setValue("I_BUKRS", "9009"); // 公司代码
                importParameterList.setValue("I_ZDEPT", getSysByFiled("departmentcode", "hrmdepartment", detailSet.getString("bm"))); // 申请部门
                importParameterList.setValue("I_ZJJSX", detailSet.getString("yt")); // 用途
                importParameterList.setValue("I_ZPAYF", "Z"); // 付款类型

                String zrh = detailSet.getString("zrh"); // 转入行
                mOdeData = getModeData(zrh);
                importParameterList.setValue("I_BANKS", mOdeData[0]); // 银行国家代码
                importParameterList.setValue("I_BANKL", mOdeData[1]); // 银行编号
                importParameterList.setValue("I_BANKN", mOdeData[2]); // 银行账户号码(全)
                importParameterList.setValue("I_BANKA", mOdeData[3]); // 转出行银行名称
                importParameterList.setValue("I_KOINH", mOdeData[4]); // 帐户持有人姓名

                importParameterList.setValue("I_WRBTR", detailSet.getString("je")); // 金额
                importParameterList.setValue("I_WAERS", "RMB"); // 货币码
                importParameterList.setValue("I_ZPAYD", detailSet.getString("yjzxsj")); // 预计执行时间
                importParameterList.setValue("I_ZCREM", getSysByFiled("workcode", "hrmresource", detailSet.getString("sqr"))); // 申请人

                // 调用sap接口
                function.execute(jCoDestination);
                this.writeLog("第 " + i + " 次调用接口结束===========");
                JCoTable t_return = function.getTableParameterList().getTable("T_RETURN");
                int numRows = t_return.getNumRows();
                for (int j = 0; j < numRows; j++) {
                    t_return.setRow(j);
                    String zoaid = Util.null2String(t_return.getString("ZOAID")).trim(); // OA单号
                    String zmstp = Util.null2String(t_return.getString("ZMSTP")).trim(); // 消息类型
                    String zmseg = t_return.getString("ZMSEG"); // 消息文本
                    this.writeLog("OA单号: " + zoaid + ", 消息类型: " + zmstp + ", 消息文本: " + zmseg);
                    if ("E".equalsIgnoreCase(zmstp) && !oaList.contains(zoaid)) {
                        oaList.add(zoaid);
                        stringBuilder.append("OA单号: ").append(zoaid).append(", 消息文本: ").append(zmseg).append(" | ");
                    }
                }

                i++;
            }
            if (stringBuilder.length() > 0) {
                this.writeLog("存在失败记录，触发失败提醒流程: " + stringBuilder.toString());
                CreateWorkflowUtil.createFlow("1", requestId, stringBuilder.toString(), tjr);
            }

            this.writeLog("资金安排审批流程 End ===============");
        } catch (Exception e) {
            this.writeLog("资金安排审批流程 Error： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("资金安排审批流程 Error： " + e);
            return "0";
        }

        return "1";
    }

    /**
     * 根据某一字段查另一个字段
     *
     * @param resultField 查询的字段名
     * @param tableName   查询表名
     * @param selField    条件字段名
     */
    private String getSysByFiled(String resultField, String tableName, String selField) {
        RecordSet recordSet = new RecordSet();
        String returnStr = "";
        recordSet.executeQuery("select " + resultField + " from " + tableName + " where id = '" + selField + "'");
        if (recordSet.next()) {
            returnStr = recordSet.getString(resultField);
        }
        return returnStr;
    }

    private String[] getModeData(String id) {
        String[] returnStrs = new String[6];
        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("select * from uf_thzhxxb where id = '" + id + "'");
        if (recordSet.next()) {
            returnStrs[0] = "CN";
            returnStrs[1] = recordSet.getString("yhdm");
            returnStrs[2] = recordSet.getString("yhzh");
            returnStrs[3] = recordSet.getString("zwqc");
            returnStrs[4] = recordSet.getString("hm");
            returnStrs[5] = recordSet.getString("zwjc");
        }
        return returnStrs;
    }


}
