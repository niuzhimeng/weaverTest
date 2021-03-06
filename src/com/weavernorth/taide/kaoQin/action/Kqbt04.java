package com.weavernorth.taide.kaoQin.action;

import com.google.gson.Gson;
import com.weavernorth.taide.kaoQin.action.myWeb.*;
import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 04-总部员工考勤补贴
 */
public class Kqbt04 extends BaseAction {

    private String bh = ""; // 流程编号

    @Override
    public String execute(RequestInfo requestInfo) {
        String requestId = requestInfo.getRequestid();
        String operatetype = requestInfo.getRequestManager().getSrc();
        String tableName = requestInfo.getRequestManager().getBillTableName();
        this.writeLog("04-总部员工考勤补贴 start requestid --- " + requestId + "  operatetype --- " + operatetype + "   fromTable --- " + tableName);
        if (operatetype.equals("submit")) {
            try {
                RecordSet recordSet = new RecordSet();
                String mainSql = "select * from " + tableName + " where requestid = '" + requestId + "'";
                recordSet.executeQuery(mainSql);
                if (recordSet.next()) {
                    String lclx = recordSet.getString("lclx"); // 操作类型 MOD DEL INS
                    String czlxStr = "INS";
                    if ("1".equals(lclx)) {
                        czlxStr = "MOD";
                    } else if ("2".equals(lclx)) {
                        czlxStr = "DEL";
                    }

                    bh = recordSet.getString("bh"); // 流程编号
                    // 拼接对象
                    DT_HR0002_ININPUT dt_hr0002_ininput = new DT_HR0002_ININPUT();
                    dt_hr0002_ininput.setTYPE("2010"); // 设置接口类型 '2002'."人事公出；'2001'."人事请假；'2005'."人事加班； '2010'."补贴次数

                    DT_HR0002_ININPUTPT2002[] datas = new DT_HR0002_ININPUTPT2002[1];
                    DT_HR0002_ININPUTPT2002 dt_hr0002_ininputpt2002 = new DT_HR0002_ININPUTPT2002();
                    dt_hr0002_ininputpt2002.setOPTION(czlxStr);
                    dt_hr0002_ininputpt2002.setZATTEND_ID(requestId); // 单据号 - requestId
                    dt_hr0002_ininputpt2002.setPERNR(recordSet.getString("gh")); // 人员编号
                    dt_hr0002_ininputpt2002.setAWART(recordSet.getString("btdm")); // 补贴代码
                    dt_hr0002_ininputpt2002.setBEGDA(changeDays(recordSet.getString("AB401_2"))); // 开始日期

                    dt_hr0002_ininputpt2002.setENDDA(changeDays(recordSet.getString("AB401_2"))); // 结束日期
                    dt_hr0002_ininputpt2002.setBEGUZ(""); // 开始时间
                    dt_hr0002_ininputpt2002.setENDUZ(""); // 结束时间
                    dt_hr0002_ininputpt2002.setZattend_WHY(""); // 加班事由
                    dt_hr0002_ininputpt2002.setSTDAZ(recordSet.getString("AB403_2")); // 补贴次数

                    dt_hr0002_ininputpt2002.setAdditional1("");
                    dt_hr0002_ininputpt2002.setAdditional2("");
                    dt_hr0002_ininputpt2002.setAdditional3("");
                    dt_hr0002_ininputpt2002.setAdditional4("");
                    datas[0] = dt_hr0002_ininputpt2002;

                    dt_hr0002_ininput.setPT2002(datas); // 设置入参数组

                    // 最外层对象
                    DT_HR0002_IN dt_hr0002_in = new DT_HR0002_IN();
                    dt_hr0002_in.setINTF_ID("");
                    dt_hr0002_in.setSrc_System("OA");
                    dt_hr0002_in.setDest_System("");
                    dt_hr0002_in.setCompany_Code("");
                    dt_hr0002_in.setSend_Time("");
                    dt_hr0002_in.setINPUT(dt_hr0002_ininput);

                    // 修改操作，先删除再新增，调用两次接口
                    if ("MOD".equals(czlxStr)) {
                        // 删除
                        String ylc = recordSet.getString("ylc");// 原流程id

                        DT_HR0002_ININPUT input = dt_hr0002_in.getINPUT();
                        DT_HR0002_ININPUTPT2002[] pt2002 = input.getPT2002();
                        pt2002[0].setOPTION("DEL");
                        pt2002[0].setZATTEND_ID(ylc);
                        String delJson = new Gson().toJson(dt_hr0002_in);
                        this.writeLog("DEL发送json： " + delJson);

                        // 调用接口
                        DT_HR0002_OUTRet_Msg[] returns = PushKqWorkFlowUtil.execute(dt_hr0002_in);
                        StringBuilder builder = insertLog(returns, "DEL json： " + delJson);
                        if (builder.length() > 0) {
                            this.writeLog("流程终止， builder: " + builder.toString());
                            requestInfo.getRequestManager().setMessageid("110000");
                            requestInfo.getRequestManager().setMessagecontent("sap返回消息：--- " + builder.toString());
                            return "0";
                        }

                        // 新增
                        DT_HR0002_ININPUT insInput = dt_hr0002_in.getINPUT();
                        DT_HR0002_ININPUTPT2002[] pt20021 = insInput.getPT2002();
                        pt20021[0].setOPTION("INS");
                        pt20021[0].setZATTEND_ID(requestId);
                        String insJson = new Gson().toJson(dt_hr0002_in);
                        this.writeLog("INS发送json： " + insJson);

                        // 调用接口
                        DT_HR0002_OUTRet_Msg[] insReturns = PushKqWorkFlowUtil.execute(dt_hr0002_in);
                        StringBuilder insBuilder = insertLog(insReturns, "INS json： " + insJson);
                        if (insBuilder.length() > 0) {
                            this.writeLog("流程终止， builder: " + insBuilder.toString());
                            requestInfo.getRequestManager().setMessageid("110000");
                            requestInfo.getRequestManager().setMessagecontent("sap返回消息：--- " + insBuilder.toString());
                            return "0";
                        }

                    } else if ("DEL".equals(czlxStr)) {
                        // 删除
                        String ylc = recordSet.getString("ylc");// 原流程id

                        DT_HR0002_ININPUT input = dt_hr0002_in.getINPUT();
                        DT_HR0002_ININPUTPT2002[] pt2002 = input.getPT2002();
                        pt2002[0].setOPTION("DEL");
                        pt2002[0].setZATTEND_ID(ylc);
                        String delJson = new Gson().toJson(dt_hr0002_in);
                        this.writeLog("DEL发送json： " + delJson);

                        // 调用接口
                        DT_HR0002_OUTRet_Msg[] returns = PushKqWorkFlowUtil.execute(dt_hr0002_in);
                        StringBuilder builder = insertLog(returns, "DEL json： " + delJson);
                        if (builder.length() > 0) {
                            this.writeLog("流程终止， builder: " + builder.toString());
                            requestInfo.getRequestManager().setMessageid("110000");
                            requestInfo.getRequestManager().setMessagecontent("sap返回消息：--- " + builder.toString());
                            return "0";
                        }
                    } else {
                        // 新增接口
                        String sendJson = new Gson().toJson(dt_hr0002_in);
                        this.writeLog("新增操作发送json： " + sendJson);
                        DT_HR0002_OUTRet_Msg[] insReturns = PushKqWorkFlowUtil.execute(dt_hr0002_in);
                        StringBuilder insBuilder = insertLog(insReturns, "send json： " + sendJson);
                        if (insBuilder.length() > 0) {
                            this.writeLog("流程终止， builder: " + insBuilder.toString());
                            requestInfo.getRequestManager().setMessageid("110000");
                            requestInfo.getRequestManager().setMessagecontent("sap返回消息：--- " + insBuilder.toString());
                            return "0";
                        }
                    }
                }
                this.writeLog("04-总部员工考勤补贴 end ===================");
            } catch (Exception e) {
                this.writeLog("04-总部员工考勤补贴 异常： " + e);
                requestInfo.getRequestManager().setMessageid("110000");
                requestInfo.getRequestManager().setMessagecontent("接口异常，请联系管理员。");
                return "0";
            }
        }

        return "1";
    }

    /**
     * 日期去掉横杠
     */
    private String changeDays(String myDays) {
        if (myDays != null) {
            myDays = myDays.replace("-", "");
        } else {
            myDays = "00000000";
        }
        return myDays;
    }

    private StringBuilder insertLog(DT_HR0002_OUTRet_Msg[] returns, String sendJson) {
        StringBuilder builder = new StringBuilder();
        for (DT_HR0002_OUTRet_Msg en : returns) {
            if ("E".equals(en.getMSG_TYPE())) {
                builder.append(en.getMSG_TYPE()).append(": ").append(en.getMESSAGE()).append("</br>");
            }
            this.writeLog("sap返回信息： " + en.getMSG_TYPE() + ": " + en.getMESSAGE());
        }

        // 返回标记
        String flag = "S";
        if (builder.length() > 0) {
            flag = "E";
        }
        // 将返回信息插入日志
        LogUtil.insertLog(returns, bh, sendJson, flag);
        return builder;
    }

}
