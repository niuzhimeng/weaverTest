package com.weavernorth.taide.kaoQin.action;

import com.alibaba.fastjson.JSON;
import com.weavernorth.taide.kaoQin.action.myWeb.*;
import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 01-总部员工加班申请
 */
public class Jbsq01 extends BaseAction {

    @Override
    public String execute(RequestInfo requestInfo) {
        String requestId = requestInfo.getRequestid();
        String operatetype = requestInfo.getRequestManager().getSrc();
        String tableName = requestInfo.getRequestManager().getBillTableName();
        this.writeLog("01-总部员工加班申请 start requestid --- " + requestId + "  operatetype --- " + operatetype + "   fromTable --- " + tableName);
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

                    String bh = recordSet.getString("bh"); // 流程编号
                    // 拼接对象
                    DT_HR0002_ININPUT dt_hr0002_ininput = new DT_HR0002_ININPUT();
                    dt_hr0002_ininput.setTYPE("2005"); // 设置接口类型 '2002'."人事公出；'2001'."人事请假；'2005'."人事加班； '2010'."补贴次数

                    DT_HR0002_ININPUTPT2002[] datas = new DT_HR0002_ININPUTPT2002[1];
                    DT_HR0002_ININPUTPT2002 dt_hr0002_ininputpt2002 = new DT_HR0002_ININPUTPT2002();
                    dt_hr0002_ininputpt2002.setOPTION(czlxStr);
                    dt_hr0002_ininputpt2002.setZATTEND_ID(requestId); // 单据号 - requestId
                    dt_hr0002_ininputpt2002.setPERNR(recordSet.getString("gh")); // 人员编号
                    dt_hr0002_ininputpt2002.setAWART(recordSet.getString("txdm")); // 加班报酬类型 - 是否调休代码
                    dt_hr0002_ininputpt2002.setBEGDA(changeDays(recordSet.getString("jbsj"))); // 开始日期

                    dt_hr0002_ininputpt2002.setENDDA(changeDays(recordSet.getString("jsrq"))); // 结束日期
                    dt_hr0002_ininputpt2002.setBEGUZ(changeTime(recordSet.getString("ksxx"), recordSet.getString("ksfz"))); // 开始时间
                    dt_hr0002_ininputpt2002.setENDUZ(changeTime(recordSet.getString("jsxx"), recordSet.getString("jsfz"))); // 结束时间
                    dt_hr0002_ininputpt2002.setZattend_WHY(""); // 加班事由
                    dt_hr0002_ininputpt2002.setSTDAZ(""); // 加班时数

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

                    String sendJson = JSON.toJSONString(dt_hr0002_in);
                    this.writeLog("发送json： " + sendJson);

                    // 调用接口
                    DT_HR0002_OUTRet_Msg[] returns = PushKqWorkFlowUtil.execute(dt_hr0002_in);

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

                    if (builder.length() > 0) {
                        this.writeLog("流程终止， builder: " + builder.toString());
                        requestInfo.getRequestManager().setMessageid("110000");
                        requestInfo.getRequestManager().setMessagecontent("sap返回消息：--- " + builder.toString());
                        return "0";
                    }
                }
                this.writeLog("01-总部员工加班申请 end ===================");
            } catch (Exception e) {
                this.writeLog("01-总部员工加班申请 异常： " + e);
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

    /**
     * 时间格式转行
     *
     * @param hour   小时
     * @param minute 分钟
     */
    private String changeTime(String hour, String minute) {
        if (hour.length() == 1) {
            hour = "0" + hour;
        }
        if ("0".equals(minute)) {
            minute = "00";
        } else if ("1".equals(minute)) {
            minute = "30";
        }
        this.writeLog("拼接后的时间： " + hour + minute + "00");
        return hour + minute + "00";
    }
}
