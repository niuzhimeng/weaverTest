package com.weavernorth.taide.kaoQin.htxq;

import com.google.gson.Gson;
import com.weavernorth.taide.kaoQin.htxq.myWeb.*;
import com.weavernorth.taide.util.ConnUtil;
import weaver.conn.RecordSet;
import weaver.general.TimeUtil;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 合同续签流程
 */
public class HtxqAction extends BaseAction {

    private Gson gson = new Gson();

    @Override
    public String execute(RequestInfo requestInfo) {
        String requestId = requestInfo.getRequestid();
        String operatetype = requestInfo.getRequestManager().getSrc();
        String tableName = requestInfo.getRequestManager().getBillTableName();
        this.writeLog("合同续签流程 start requestid --- " + requestId + "  operatetype --- " + operatetype + "   fromTable --- " + tableName + ", " + TimeUtil.getCurrentTimeString());
        if (operatetype.equals("submit")) {
            try {
                RecordSet recordSet = new RecordSet();
                String mainSql = "select * from " + tableName + " where requestid = '" + requestId + "'";
                recordSet.executeQuery(mainSql);
                if (recordSet.next()) {
                    String bh = recordSet.getString("bh"); // 流程编号

                    String sqgh = recordSet.getString("sqgh"); // 申请工号
                    String xqhtksrq = recordSet.getString("xqhtksrq"); // 续签合同开始日期
                    String xqlxdm = recordSet.getString("xqlxdm"); // 续签类型代码
                    String xqhtjsrq; // 续签合同结束日期
                    String qddw = recordSet.getString("qddw"); // 签订单位

                    int xqlx = recordSet.getInt("xqlx"); // 续签类型

                    String zsrq = "00000000"; // 结束日期
                    String[] split = xqhtksrq.split("-");

                    if (xqlx == 2 || xqlx == 4 || xqlx == 5) {
                        // 结束日期取页面
                        zsrq = recordSet.getString("xqhtjsrq");
                    } else if (xqlx == 1) {
                        zsrq = "9999-12-31";
                    } else if (xqlx == 0) {
                        // 新起始 + 3年
                        int i = Integer.parseInt(split[0]) + 3;
                        zsrq = i + split[1] + split[2];
                    } else if (xqlx == 3) {
                        // 新起始 + 1年
                        int i = Integer.parseInt(split[0]) + 1;
                        zsrq = i + split[1] + split[2];
                    }

                    // 是除无固定期限外不取值。其他都 = 结束日期
                    if (xqlx == 1) {
                        xqhtjsrq = "";
                    } else {
                        xqhtjsrq = zsrq;
                    }

                    // 拼接对象
                    DT_HRI010_INDATAITEMS[] dt_hri008_indataitems = new DT_HRI010_INDATAITEMS[1];
                    DT_HRI010_INDATAITEMS indataitems = new DT_HRI010_INDATAITEMS();
                    indataitems.setPERNR(sqgh); // 工号
                    indataitems.setBEGDA(changeDays(xqhtksrq)); // 开始日期
                    indataitems.setENDDA(changeDays(zsrq)); // 结束日期
                    indataitems.setCTNUM(""); // 合同编号（空）
                    indataitems.setCTTYP(xqlxdm); // 合同类型

                    indataitems.setCTEDT(changeDays(xqhtjsrq)); // 有效截止日期
                    indataitems.setWTTKL(""); // 竞争条款
                    indataitems.setPRBZT(""); // 试用期
                    indataitems.setPRBEH(""); // 计量单位
                    indataitems.setZHTDW(qddw); // 合同签订单位

                    indataitems.setAdditional1("");
                    indataitems.setAdditional2("");
                    indataitems.setAdditional3("");
                    indataitems.setAdditional4("");
                    indataitems.setAdditional5("");

                    dt_hri008_indataitems[0] = indataitems;

                    DT_HRI010_INSENDER dt_hri008_insender = new DT_HRI010_INSENDER();
                    dt_hri008_insender.setINTF_ID("");
                    dt_hri008_insender.setSrc_System("OA");
                    dt_hri008_insender.setDest_System("");
                    dt_hri008_insender.setCompany_Code("");
                    dt_hri008_insender.setSend_Time("");

                    // 最外层对象
                    DT_HRI010_IN dtHri010In = new DT_HRI010_IN();
                    dtHri010In.setSENDER(dt_hri008_insender);
                    dtHri010In.setDATA(dt_hri008_indataitems);

                    String sendJson = gson.toJson(dtHri010In);
                    this.writeLog("发送json： " + sendJson);

                    // 调用接口
                    DT_HRI010_OUTRETURN[] returns = HtxqUtil.execute(dtHri010In);
                    this.writeLog("sap 返回数组： " + gson.toJson(returns));

                    StringBuilder builder = new StringBuilder();
                    StringBuilder logBuilder = new StringBuilder();
                    for (DT_HRI010_OUTRETURN en : returns) {
                        if ("E".equals(en.getInputResult())) {
                            builder.append(en.getInputResult()).append(": ").append(en.getReason()).append("</br>");
                        }
                        logBuilder.append("sap返回信息： ").append(en.getInputResult()).append(": ").append(en.getReason()).append(";");
                        this.writeLog("sap返回信息： " + en.getInputResult() + ": " + en.getReason());
                    }

                    // 返回标记
                    String flag = "S";
                    if (builder.length() > 0) {
                        flag = "E";
                    }

                    // 将返回信息插入日志
                    ConnUtil.insertLogCoon(logBuilder.toString(), bh, sendJson, flag);

                    if (builder.length() > 0) {
                        this.writeLog("流程终止， builder: " + builder.toString());
                        requestInfo.getRequestManager().setMessageid("110000");
                        requestInfo.getRequestManager().setMessagecontent("sap返回消息：--- " + new Gson().toJson(returns));
                        return "0";
                    }
                    this.writeLog("合同续签流程  OA -> SAP end =================== " + TimeUtil.getCurrentTimeString());
                }
            } catch (Exception e) {
                this.writeLog("合同续签流程 异常： " + e);
                requestInfo.getRequestManager().setMessageid("110000");
                requestInfo.getRequestManager().setMessagecontent("合同续签流程接口异常，请联系管理员。");
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

}
