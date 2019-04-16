package com.weavernorth.taide.kaoQin300.qingGou;

import com.google.gson.Gson;
import com.weaver.general.TimeUtil;
import com.weavernorth.taide.kaoQin300.qingGou.myWeb.DT_MMI001_IN;
import com.weavernorth.taide.kaoQin300.qingGou.myWeb.DT_MMI001_ININPUT;
import com.weavernorth.taide.kaoQin300.qingGou.myWeb.DT_MMI001_OUTOUTPUT;
import com.weavernorth.taide.kaoQin300.qingGou.myWeb.QingGouUtil;
import com.weavernorth.taide.util.ConnUtil;
import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 资产类 action
 */
public class PropertyAction extends BaseAction {

    private Gson gson = new Gson();

    @Override
    public String execute(RequestInfo requestInfo) {
        String requestId = requestInfo.getRequestid();
        String operatetype = requestInfo.getRequestManager().getSrc();
        int formId = requestInfo.getRequestManager().getFormid();
        String tableName = "";
        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("SELECT tablename FROM workflow_bill WHERE id = '" + formId + "'");
        if (recordSet.next()) {
            tableName = recordSet.getString("tablename");
        }
        this.writeLog("资产action 执行 " + TimeUtil.getCurrentTimeString() + " requestid: " + requestId + " tableName: " + tableName);

        String lcbh = "";
        String sendJson = "";

        if (operatetype.equals("submit")) {
            try {
                recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");
                recordSet.next();
                lcbh = recordSet.getString("bh");// 流程编号
                String BSART = recordSet.getString("BSART");// 请购凭证类型
                String workCode = recordSet.getString("sqgh");// 申请人工号

                // 查询明细表
                RecordSet detailSet = new RecordSet();
                String sql = "select d.* from " + tableName + "_dt1 d left join " + tableName + " m on m.id = d.mainid where m.requestId = '" + requestId + "'";
                detailSet.executeQuery(sql);
                DT_MMI001_ININPUT[] dt_mmi001_ininputs = new DT_MMI001_ININPUT[detailSet.getCounts()];
                int i = 0;
                while (detailSet.next()) {
                    String KNTTP = detailSet.getString("KNTTP");// 科目分配类别
                    String wlz = detailSet.getString("wlz");// 物料组
                    String gc = detailSet.getString("gc"); // 工厂
                    String cgz = detailSet.getString("cgz"); // 采购组
                    String xqsl = detailSet.getString("xqsl"); // 需求数量

                    String jldw = detailSet.getString("jldw"); // 计量单位
                    String zcbh = detailSet.getString("zcbh"); // 固定资产号
                    String gmrq = detailSet.getString("gmrq"); // 需求日期
                    String zcmc = detailSet.getString("zcmc"); // 资产名称

                    DT_MMI001_ININPUT dt_mmi001_ininput = new DT_MMI001_ININPUT();
                    dt_mmi001_ininput.setREMARK1(detailSet.getString("id")); // 请购单单号标识
                    dt_mmi001_ininput.setBSART(BSART); // 请购凭证类型
                    dt_mmi001_ininput.setKNTTP(KNTTP); // 科目分配类别
                    dt_mmi001_ininput.setMATNR(""); // 物料编码
                    dt_mmi001_ininput.setTXZ01(zcmc); // 描述文本

                    dt_mmi001_ininput.setMATKL(wlz); // 物料组
                    dt_mmi001_ininput.setWERKS(gc); // 工厂
                    dt_mmi001_ininput.setEKGRP(cgz); // 采购组
                    dt_mmi001_ininput.setMENGE(xqsl); // 申请数量
                    dt_mmi001_ininput.setMEINS(jldw); // 基本计量单位

                    dt_mmi001_ininput.setAFNAM(workCode); // 申请人
                    dt_mmi001_ininput.setANLN1(zcbh); // 固定资产号
                    dt_mmi001_ininput.setTEXT01(""); //需求项目文本
                    dt_mmi001_ininput.setTEXT02(""); // 研发项目文本
                    dt_mmi001_ininput.setBEDNR(requestId); // BEDNR

                    dt_mmi001_ininput.setLFDAT(changeDays(gmrq)); // 需求日期
                    dt_mmi001_ininput.setLIFNR(""); // 所需供应商

                    dt_mmi001_ininput.setAdditional1("");
                    dt_mmi001_ininput.setAdditional2("");
                    dt_mmi001_ininput.setAdditional3("");
                    dt_mmi001_ininput.setAdditional4("");
                    dt_mmi001_ininput.setAdditional5("");

                    dt_mmi001_ininputs[i] = dt_mmi001_ininput;
                    i++;
                }

                // 拼接最外层对象
                DT_MMI001_IN dt_mmi001_in = new DT_MMI001_IN();
                dt_mmi001_in.setINTF_ID("");
                dt_mmi001_in.setSrc_System("OA");
                dt_mmi001_in.setDest_System("");
                dt_mmi001_in.setCompany_Code("");
                dt_mmi001_in.setSend_Time("");
                dt_mmi001_in.setINPUT(dt_mmi001_ininputs);

                sendJson = new Gson().toJson(dt_mmi001_in);
                this.writeLog("发送json： " + sendJson);

                // 调用接口
                DT_MMI001_OUTOUTPUT[] returns = QingGouUtil.execute(dt_mmi001_in);
                this.writeLog("sap 返回数组： " + gson.toJson(returns));

                StringBuilder builder = new StringBuilder();
                StringBuilder logBuilder = new StringBuilder();
                for (DT_MMI001_OUTOUTPUT en : returns) {
                    if ("E".equals(en.getTYPE())) {
                        builder.append(en.getTYPE()).append(": ").append(en.getMESSAGE()).append("</br>");
                    }
                    logBuilder.append("sap返回信息： ").append(en.getTYPE()).append(": ").append(en.getMESSAGE()).append(";");
                    this.writeLog("sap返回信息： " + en.getTYPE() + ": " + en.getMESSAGE());
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
                    requestInfo.getRequestManager().setMessagecontent("sap返回消息：--- " + gson.toJson(returns));
                    return "0";
                }

                //推送成功后，更新回写数据
                String banfn = returns[0].getBANFN();
                detailSet.execute("update " + tableName + " set BANFN = '" + banfn + "' where requestid = " + requestId);

            } catch (Exception e) {
                this.writeLog("资产action 异常： " + e);
                ConnUtil.insertLogCoon("接口异常", lcbh, sendJson, "E");
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
}
