package com.weavernorth.taide.kaoQin500.wlly;


import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.weavernorth.taide.kaoQin500.wlly.myWeb.*;
import com.weavernorth.taide.util.ConnUtil;
import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 07-领料申请单 OA -> SAP
 */
public class WllyActionPoqas extends BaseAction {

    private Gson gson = new Gson();

    @Override
    public String execute(RequestInfo requestInfo) {
        String requestId = requestInfo.getRequestid();
        String operatetype = requestInfo.getRequestManager().getSrc();
        String tableName = requestInfo.getRequestManager().getBillTableName();
        this.writeLog("07-poqas物料申请单 OA -> SAP start requestid --- " + requestId + "  operatetype --- " + operatetype + "   fromTable --- " + tableName);
        if (operatetype.equals("submit")) {
            try {
                RecordSet recordSet = new RecordSet();
                String mainSql = "select * from " + tableName + " where requestid = '" + requestId + "'";
                recordSet.executeQuery(mainSql);

                // 查询明细表
                RecordSet mxSet = new RecordSet();
                String sql = "select d.* from " + tableName + "_dt1 d left join " + tableName + " m on m.id = d.mainid where m.requestId = '" + requestId + "'";
                this.writeLog("明细表查询sql： " + sql);
                mxSet.executeQuery(sql);
                if (recordSet.next()) {
                    String lcbh = recordSet.getString("lcbh"); // 流程编号
                    String ydlx = recordSet.getString("BWART");// 移动类型
                    // 根据【移动类型】决定【内部订单】去什么值
                    String nbdd = ""; // 内部订单
                    if ("Z61".equals(ydlx)) {
                        nbdd = recordSet.getString("AUFNR1"); // 研发中心项目号
                    } else if ("Z63".equals(ydlx)) {
                        nbdd = recordSet.getString("AUFNR2"); // 在建工程号
                    } else if ("201".equals(ydlx)) {
                        nbdd = ""; // 设为空
                    }
                    // 主表
                    DT_MMI002_ININPUTHEADER datas = new DT_MMI002_ININPUTHEADER();
                    datas.setWERKS(recordSet.getString("gcdm")); // 工厂
                    datas.setBWART(ydlx);
                    datas.setKOSTL(recordSet.getString("KOSTL")); // 成本中心
                    datas.setAUFNR(nbdd);
                    datas.setPS_PSP_PNR(recordSet.getString("PS_PSP_PNR")); // WBS元素
                    datas.setWEMPF(lcbh); // OA领料单号 - 流程编号
                    datas.setREMARK1(lcbh); // 领料申请抬头标识 - 流程编号

                    // 明细表拼接
                    DT_MMI002_ININPUTHEADERITEM[] mingXiDatas = new DT_MMI002_ININPUTHEADERITEM[mxSet.getCounts()];
                    int i = 0;
                    while (mxSet.next()) {
                        DT_MMI002_ININPUTHEADERITEM mxData = new DT_MMI002_ININPUTHEADERITEM();
                        mxData.setREMARK1(lcbh); // 流程编号
                        mxData.setMATNR(mxSet.getString("MATNR")); // 物料编码
                        mxData.setERFMG(mxSet.getString("ERFMG")); // 申请数量
                        mxData.setMEINS(mxSet.getString("MEINS")); // 基本计量单位
                        mxData.setLGORT(mxSet.getString("LGORT")); // 库存地点

                        mxData.setCHARG(mxSet.getString("CHARG")); // 批次
                        mxData.setBDTER(changeDays(mxSet.getString("BDTER"))); // 需求日期

                        mxData.setAdditional1("");
                        mxData.setAdditional2("");
                        mxData.setAdditional3("");
                        mxData.setAdditional4("");
                        mxData.setAdditional5("");
                        mingXiDatas[i] = mxData;
                        i++;
                    }

                    // 设置明细表
                    datas.setITEM(mingXiDatas);
                    // 拼接数组
                    DT_MMI002_ININPUTHEADER[] dt_mmi002_ininputheaders = new DT_MMI002_ININPUTHEADER[1];
                    dt_mmi002_ininputheaders[0] = datas;

                    // 拼接最外层对象
                    DT_MMI002_IN dtMmi002In = new DT_MMI002_IN();
                    dtMmi002In.setINTF_ID("");
                    dtMmi002In.setSrc_System("OA");
                    dtMmi002In.setDest_System("");
                    dtMmi002In.setCompany_Code("");
                    dtMmi002In.setSend_Time("");
                    dtMmi002In.setINPUT(dt_mmi002_ininputheaders);

                    String sendJson = gson.toJson(dtMmi002In);
                    this.writeLog("发送json： " + sendJson);
                    // 调用接口
                    DT_MMI002_OUTOUTPUT[] returns = WllyUtil.execute(dtMmi002In);
                    this.writeLog("领料申请单返回信息： " + gson.toJson(returns));

                    StringBuilder builder = new StringBuilder();
                    StringBuilder logBuilder = new StringBuilder();
                    for (DT_MMI002_OUTOUTPUT en : returns) {
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
                        requestInfo.getRequestManager().setMessagecontent("sap返回消息：--- " + JSON.toJSONString(returns));
                        return "0";
                    }

                    //推送成功后，更新回写数据
                    String banfn = returns[0].getRSNUM();
                    String updateSql = "update " + tableName + " set sapyldh = '" + banfn + "' where requestid = " + requestId;
                    this.writeLog("领料申请更新sql：" + updateSql);
                    recordSet.execute(updateSql);

                }

                this.writeLog("07-领料申请单 OA -> SAP end ===================");
            } catch (Exception e) {
                this.writeLog("07-领料申请单 OA -> SAP 异常： " + e);
                requestInfo.getRequestManager().setMessageid("110000");
                requestInfo.getRequestManager().setMessagecontent("07-领料申请单 OA -> SAP 异常： " + e);
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
