package com.weavernorth.taide.kaoQin.ygChange;


import com.google.gson.Gson;
import com.weavernorth.taide.kaoQin.ygChange.myWeb.*;
import com.weavernorth.taide.util.ConnUtil;
import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 02-员工转岗 OA -> SAP
 */
public class ZhuanGangAction extends BaseAction {

    @Override
    public String execute(RequestInfo requestInfo) {
        String requestId = requestInfo.getRequestid();
        String operatetype = requestInfo.getRequestManager().getSrc();
        String tableName = requestInfo.getRequestManager().getBillTableName();
        this.writeLog("02-员工转岗 OA -> SAP start requestid --- " + requestId + "  operatetype --- " + operatetype + "   fromTable --- " + tableName);
        if (operatetype.equals("submit")) {
            try {
                RecordSet recordSet = new RecordSet();
                String mainSql = "select * from " + tableName + " where requestid = '" + requestId + "'";
                recordSet.executeQuery(mainSql);
                recordSet.next();

                String lcbh = recordSet.getString("lcbh");// 流程编号
                String sqgh = recordSet.getString("sqgh"); // 工号
                String gwbdrq = recordSet.getString("gwbdrq");// 岗位变动日期
                String czlx = recordSet.getString("czlx");// 操作类型
                String bdlxdm = recordSet.getString("bdlxdm");// 变动类型代码

                String rszfw = recordSet.getString("rszfw");// 调动范围
                String drhbm = recordSet.getString("drhbm");// 调入后岗位编码
                String drhsjbm = recordSet.getString("drhsjbm");// 调入后直接上级工号

                // 拼接对象
                DT_HRI001_INDATAITEMS dt_hri001_indataitems1 = new DT_HRI001_INDATAITEMS();
                dt_hri001_indataitems1.setPERNR(sqgh); //工号
                dt_hri001_indataitems1.setBEGDA(changeDays(gwbdrq)); // 开始日期
                dt_hri001_indataitems1.setMASSN(czlx); // 操作类型
                dt_hri001_indataitems1.setMASSG(bdlxdm); // 操作原因
                dt_hri001_indataitems1.setBTRTL(rszfw); // 人事子范围
                dt_hri001_indataitems1.setPLANS(drhbm); // 职位
                dt_hri001_indataitems1.setZZJSJ(drhsjbm); // 直接上级编号
                dt_hri001_indataitems1.setZLZLX(""); // 离职类型
                dt_hri001_indataitems1.setZLZYY(""); // 离职原因
                dt_hri001_indataitems1.setZBEIZ(""); // 备注

                dt_hri001_indataitems1.setAdditional1("");
                dt_hri001_indataitems1.setAdditional2("");
                dt_hri001_indataitems1.setAdditional3("");
                dt_hri001_indataitems1.setAdditional4("");

                DT_HRI001_INDATAITEMS[] dt_hri001_indataitems = new DT_HRI001_INDATAITEMS[1];
                dt_hri001_indataitems[0] = dt_hri001_indataitems1;

                // 默认值对象
                DT_HRI001_INSENDER dt_hri001_insender = new DT_HRI001_INSENDER();
                dt_hri001_insender.setINTF_ID("");
                dt_hri001_insender.setSrc_System("OA");
                dt_hri001_insender.setDest_System("");
                dt_hri001_insender.setCompany_Code("");
                dt_hri001_insender.setSend_Time("");

                // 最外层对象
                DT_HRI001_IN dt_hri001_in = new DT_HRI001_IN();
                dt_hri001_in.setSENDER(dt_hri001_insender);
                dt_hri001_in.setDATA(dt_hri001_indataitems);

                String sendJson = new Gson().toJson(dt_hri001_in);
                this.writeLog("发送json： " + sendJson);
                // 调用接口
                DT_HRI001_OUTRETURN[] returns = YgChangeUtil.execute(dt_hri001_in);

                this.writeLog("sap 返回数组： " + new Gson().toJson(returns));

                StringBuilder builder = new StringBuilder();
                StringBuilder logBuilder = new StringBuilder();
                for (DT_HRI001_OUTRETURN en : returns) {
                    if ("E".equals(en.getMSG_TYPE())) {
                        builder.append(en.getMSG_TYPE()).append(": ").append(en.getMESSAGE()).append("</br>");
                    }
                    logBuilder.append("sap返回信息： ").append(en.getMSG_TYPE()).append(": ").append(en.getMESSAGE()).append(";");
                    this.writeLog("sap返回信息： " + en.getMSG_TYPE() + ": " + en.getMESSAGE());
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

                this.writeLog("02-员工转岗 OA -> SAP end ===================");
            } catch (Exception e) {
                this.writeLog("02-员工转岗 OA -> SAP 异常： " + e);
                requestInfo.getRequestManager().setMessageid("110000");
                requestInfo.getRequestManager().setMessagecontent("02-员工转岗 OA -> SAP 异常： " + e);
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
