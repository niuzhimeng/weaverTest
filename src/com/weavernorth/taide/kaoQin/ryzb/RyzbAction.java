package com.weavernorth.taide.kaoQin.ryzb;

import com.google.gson.Gson;
import com.weavernorth.taide.kaoQin.ryzb.myWeb.*;
import com.weavernorth.taide.util.ConnUtil;
import weaver.conn.RecordSet;
import weaver.general.TimeUtil;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 人员增补流程
 */
public class RyzbAction extends BaseAction {

    private Gson gson = new Gson();

    @Override
    public String execute(RequestInfo requestInfo) {
        String requestId = requestInfo.getRequestid();
        String operatetype = requestInfo.getRequestManager().getSrc();
        String tableName = requestInfo.getRequestManager().getBillTableName();
        this.writeLog("人员增补流程 start requestid --- " + requestId + "  operatetype --- " + operatetype + "   fromTable --- " + tableName + ", " + TimeUtil.getCurrentTimeString());
        if (operatetype.equals("submit")) {
            try {
                String currentDateString = TimeUtil.getCurrentDateString().replace("-", "");
                RecordSet recordSet = new RecordSet();
                String mainSql = "select * from " + tableName + " where requestid = '" + requestId + "'";
                recordSet.executeQuery(mainSql);
                if (recordSet.next()) {
                    String lcbh = recordSet.getString("lcbh");// 流程编号

                    String zbgwbm = recordSet.getString("zbgwbm"); // 增补岗位编码
                    String zpyydm = recordSet.getString("zpyydm");// 招聘原因代码

                    // 拼接对象
                    DT_HRI008_INDATAITEMS[] dt_hri008_indataitems = new DT_HRI008_INDATAITEMS[1];
                    DT_HRI008_INDATAITEMS dt_hri008_indataitems1 = new DT_HRI008_INDATAITEMS();
                    dt_hri008_indataitems1.setOBJID(zbgwbm); //  对象ID
                    dt_hri008_indataitems1.setISTAT("1"); // 计划状态
                    dt_hri008_indataitems1.setBEGDA(currentDateString); // 开始日期
                    dt_hri008_indataitems1.setENDDA("99991231"); // 结束日期
                    dt_hri008_indataitems1.setZGWXNBZ(currentDateString); // 增补审批日期
                    dt_hri008_indataitems1.setZZPYY(zpyydm); // 招聘原因

                    dt_hri008_indataitems1.setAdditional1("");
                    dt_hri008_indataitems1.setAdditional2("");
                    dt_hri008_indataitems1.setAdditional3("");
                    dt_hri008_indataitems1.setAdditional4("");
                    dt_hri008_indataitems1.setAdditional5("");

                    dt_hri008_indataitems[0] = dt_hri008_indataitems1;

                    DT_HRI008_INSENDER dt_hri008_insender = new DT_HRI008_INSENDER();
                    dt_hri008_insender.setINTF_ID("");
                    dt_hri008_insender.setSrc_System("OA");
                    dt_hri008_insender.setDest_System("");
                    dt_hri008_insender.setCompany_Code("");
                    dt_hri008_insender.setSend_Time("");

                    // 最外层对象
                    DT_HRI008_IN dt_hri008_in = new DT_HRI008_IN();
                    dt_hri008_in.setSENDER(dt_hri008_insender);
                    dt_hri008_in.setDATA(dt_hri008_indataitems);

                    String sendJson = gson.toJson(dt_hri008_in);
                    this.writeLog("发送json： " + sendJson);

                    // 调用接口
                    DT_HRI008_OUTRETURN[] returns = RyzbUtil.execute(dt_hri008_in);
                    this.writeLog("sap 返回数组： " + gson.toJson(returns));

                    StringBuilder builder = new StringBuilder();
                    StringBuilder logBuilder = new StringBuilder();
                    for (DT_HRI008_OUTRETURN en : returns) {
                        if ("E".equals(en.getMSG_TYP())) {
                            builder.append(en.getMSG_TYP()).append(": ").append(en.getMESSAGE()).append("</br>");
                        }
                        logBuilder.append("sap返回信息： ").append(en.getMSG_TYP()).append(": ").append(en.getMESSAGE()).append(";");
                        this.writeLog("sap返回信息： " + en.getMSG_TYP() + ": " + en.getMESSAGE());
                    }

                    // 返回标记
                    String flag = "S";
                    if (builder.length() > 0) {
                        flag = "E";
                    }

                    // 将返回信息插入日志
                    ConnUtil.insertLogCoon(logBuilder.toString(), lcbh, sendJson, flag);

                    if (builder.length() > 0) {
                        this.writeLog("流程终止， builder: " + builder.toString());
                        requestInfo.getRequestManager().setMessageid("110000");
                        requestInfo.getRequestManager().setMessagecontent("sap返回消息：--- " + new Gson().toJson(returns));
                        return "0";
                    }
                    this.writeLog("人员增补流程  OA -> SAP end =================== " + TimeUtil.getCurrentTimeString());
                }
            } catch (Exception e) {
                this.writeLog("人员增补流程 异常： " + e);
                requestInfo.getRequestManager().setMessageid("110000");
                requestInfo.getRequestManager().setMessagecontent("人员增补流程接口异常，请联系管理员。");
                return "0";
            }

        }

        return "1";
    }
}
