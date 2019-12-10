package com.weavernorth.workflow;

import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;
import com.weaver.general.BaseBean;
import com.weaver.integration.datesource.SAPInterationOutUtil;
import com.weaver.integration.log.LogInfo;
import com.weavernorth.util.LogUtil;
import com.weavernorth.workflow.payment.SAPUtil;
import weaver.conn.RecordSet;
import weaver.formmode.interfaces.action.BaseAction;
import weaver.general.Util;
import weaver.hrm.User;
import weaver.soa.workflow.request.RequestInfo;

/**
 * 节点后附加操作,推送费用报销流程数据,生成会计凭证
 *
 * @author wgh
 */
public class WfCost_SAP extends BaseAction {
    // 获取配置的SAP数据源的id值
    private String strPoolid = SAPUtil.getIdbyPoolName(new BaseBean().getPropValue("sapparam1", "dataName"));
    private LogInfo li = new LogInfo();
    private JCO.Function function = null;
    private JCO.Client jcoClient = null;
    private SAPInterationOutUtil sou = new SAPInterationOutUtil();

    /**
     * 流程节点后附加操作
     */
    public String execute(RequestInfo request) {
        LogUtil.doWriteLog("======费用报销流程节点后附加操作：生成会计凭证号接口======");
        String strMsg = Voucher_SAP(request);
        LogUtil.doWriteLog("sap返回信息strMsg:" + strMsg);
        String strtype = strMsg.split(",")[0];
        this.writeLog("strtype:" + strtype);
        String strMessage = strMsg.split(",")[1];
        if ("E".equals(strtype)) {
            request.getRequestManager().setMessageid("12500");
            request.getRequestManager().setMessagecontent("SAP返回报错信息:" + strMessage);
            //add by lyl 20180904 此处不知为何没有阻断流程 在此改为阻断流程方式 start
            return FAILURE_AND_CONTINUE;
            //add by lyl 20180904 此处不知为何没有阻断流程 在此改为阻断流程方式 end
        }
        return SUCCESS;
    }

    /**
     * 生成会计凭证方法
     *
     * @return
     */
    /// 如果是项目报销   成本中心不传，传wbs的字段给  PSRNR_Z    公司报销的 单人主表成本中心     多人dt2的成本中心
    public String Voucher_SAP(RequestInfo request) {
        User usr = request.getRequestManager().getUser();
        RecordSet RsSelectTabel = new RecordSet();
        RecordSet RsStructure = new RecordSet();
        RecordSet RsTable_main = new RecordSet();
        RecordSet RsTable_1 = new RecordSet();
        RecordSet RsTable_2 = new RecordSet();
        // 当前操作人id
        String currentLoginId = usr.getLoginid();
        // SAP返回值信息
        String strMsg = "";
        // SAP返回类型
        String strType = "";
        // SAP返回会计凭证号
        String strFictitiousVoucherNum = "";
        // workflowid
        String workflowid = request.getWorkflowid();
        // requestid
        String requestid = request.getRequestid();
        // 凭证日期
        String BLDAT = "";
        // 过账日期
        String PSTNG_DATE = "";
        // 凭证类型
        String BLART = "";
        // 过账期间
        String MONAT = "";
        // 财年
        String GJAHR = "";
        // 凭证抬头文本
        String BKTXT = "";
        // 流程表名
        String strTablename = "";
        // 获取表名
        String strSearchFormid = "select tablename from workflow_bill where id=(select formid from workflow_base where id=" + workflowid + ")";
        RsSelectTabel.executeSql(strSearchFormid);
        if (RsSelectTabel.next()) {
            strTablename = Util.null2String(RsSelectTabel.getString("tablename"));
        }
        // 查询表单数据
        String strSelectSql = "select * from " + strTablename + " where requestid=" + requestid;
        LogUtil.doWriteLog("==查询主表数据==" + strSelectSql);
        RsStructure.execute(strSelectSql);
        // 只获取输入结构数据
        if (RsStructure.first()) {
            // 凭证日期
            BLDAT = RsStructure.getString("pzrq");
            // 过账日期
            PSTNG_DATE = RsStructure.getString("gzrq");
            // 凭证类型
            BLART = RsStructure.getString("pzlx");
            // 过账期间
            MONAT = RsStructure.getString("gzqj");
            // 财年
            GJAHR = (RsStructure.getString("pzrq")).substring(0, 4);
            // 凭证抬头文本
            BKTXT = RsStructure.getString("pzttwb");
        }
        LogUtil.doWriteLog("==当前操作人loginid==" + currentLoginId);
        LogUtil.doWriteLog("==凭证日期==" + BLDAT);
        LogUtil.doWriteLog("==过账日期==" + PSTNG_DATE);
        LogUtil.doWriteLog("==凭证类型==" + BLART);
        LogUtil.doWriteLog("==过账期间==" + MONAT);
        LogUtil.doWriteLog("==财年==" + GJAHR);
        LogUtil.doWriteLog("==凭证抬头文本==" + BKTXT);
        // 连接sap
        jcoClient = (JCO.Client) sou.getConnection(strPoolid, li);
        LogUtil.debugLog("==报销流程生成会计凭证SAP接口==连接sap的连接状态==" + jcoClient.getState());
        JCO.Repository mRepository = new JCO.Repository("Repository", jcoClient);
        // 生成会计凭证的函数名
        IFunctionTemplate ft = mRepository.getFunctionTemplate("ZRFC_BXPZ_OA");
        function = new JCO.Function(ft);
        // 输入结 构
        JCO.Structure structure_HEADER = function.getImportParameterList().getStructure("HEADER");
        structure_HEADER.setValue(BLDAT, "BLDAT");
        structure_HEADER.setValue(PSTNG_DATE, "PSTNG_DATE");
        structure_HEADER.setValue(BLART, "BLART");
        structure_HEADER.setValue(MONAT, "MONAT");
        structure_HEADER.setValue(GJAHR, "GJAHR");
        structure_HEADER.setValue("RMB", "WAERS");
        structure_HEADER.setValue(BKTXT, "BKTXT");
        structure_HEADER.setValue(currentLoginId, "USNAM");
        // 输入表
        JCO.Table tablein_T_KJPZ = function.getTableParameterList().getTable("T_KJPZ");
        // 查询报销人数
        String strPushSapSql_main = "select * from formtable_main_85 where requestid=" + requestid;
        RsTable_main.execute(strPushSapSql_main);
        LogUtil.doWriteLog("==查询是否报销人本人==" + strPushSapSql_main);
        // 判断循环次数
        int intnum = 0;
        if (RsTable_main.first()) {
            // 是否存在特殊记账码
            String strSpecialCode = "";
            // 总账科目记帐代码
            String strSubjectCode = "40";
            String sqrsfwbxr = "";
            //是否为单人报销
            String sfdrbx = "";

            //报销类型
            String bxlx = "";

            if ("0".equals(RsTable_main.getString("sfcztbjzm"))) {
                strSpecialCode = RsTable_main.getString("tbjzm");
                strSubjectCode = RsTable_main.getString("jfjzm");
            }
            sqrsfwbxr = RsTable_main.getString("sqrsfwbxr");
            //是否为单人报销
            sfdrbx = RsTable_main.getString("sfdrbx");
            bxlx = RsTable_main.getString("bxlx");
            LogUtil.doWriteLog("==报销类型==" + bxlx);
            LogUtil.doWriteLog("==是否存在特殊记账码==" + RsTable_main.getString("sfcztbjzm"));
            LogUtil.doWriteLog("==特别记账码==" + strSpecialCode);
            LogUtil.doWriteLog("==总账科目记帐代码==" + strSubjectCode);
            LogUtil.doWriteLog("==是否为报销人本人==" + sqrsfwbxr);
            LogUtil.doWriteLog("==申请人为本人提交报销流程==");
            // 单人报销，只参与一次循环    G 人员信息  Z 科目信息   s  税  发票信息
            if ("0".equals(sqrsfwbxr)) {
                intnum = 1;
                tablein_T_KJPZ.appendRow();
                LogUtil.doWriteLog("==单人报销，只推送一次循环1==");
                // 1 供应商记帐代码
                tablein_T_KJPZ.setValue("31", "BSCHL_G");
                LogUtil.doWriteLog("==单人报销，只推送一次循环2==");
                // 2 供应商编码
                tablein_T_KJPZ.setValue(Util.null2String(RsTable_main.getString("gysbh1")), "LIFNR_G");
                LogUtil.doWriteLog("==单人报销，只推送一次循环3==");
                // 3 凭证货币金额
                tablein_T_KJPZ.setValue(Util.null2String(RsTable_main.getString("bxzje1")), "WRBTR_G");
                LogUtil.doWriteLog("==单人报销，只推送一次循环4==");
                // 4 业务范围
                tablein_T_KJPZ.setValue("3000", "GSBER_G");
                LogUtil.doWriteLog("==单人报销，只推送一次循环5==");
                // 5 分配编号
                tablein_T_KJPZ.setValue(Util.null2String(RsTable_main.getString("fprq")).replaceAll("-", ""), "ZUONR_G");
                LogUtil.doWriteLog("==单人报销，只推送一次循环6==");
                // 6 项目文本
                tablein_T_KJPZ.setValue(Util.null2String(RsTable_main.getString("zy1")), "SGTXT_G");
                LogUtil.doWriteLog("==单人报销，只推送一次循环7==");
                // 7 基准日期
                tablein_T_KJPZ.setValue(Util.null2String(RsTable_main.getString("fprq")).replaceAll("-", ""), "ZFBDT_G");
                LogUtil.doWriteLog("==单人报销，只推送一次循环8==");
                // 8 总账科目记帐代码
                tablein_T_KJPZ.setValue(strSubjectCode, "BSCHL_Z");
                LogUtil.doWriteLog("==单人报销，只推送一次循环9==");
                // 9 特殊总账标识 至于是是的时候才传
                if ("0".equals(RsTable_main.getString("sfcztbjzm"))) {
                    tablein_T_KJPZ.setValue(strSpecialCode, "UMSKZ_Z");
                }
                // 10 总账科目号
                String fykmm = RsTable_main.getString("fykmm");
                LogUtil.doWriteLog("==单人报销，只推送一次循环10==");
                if (fykmm.equals("2181020000")) {
                    tablein_T_KJPZ.setValue("97990001", "LIFNR_Z");
                    tablein_T_KJPZ.setValue(BLDAT, "ZFBDT_Z");
//                    //科目码
//                    tablein_T_KJPZ.setValue("2171010700", "HKONT_G");
                    //基金中心
                    tablein_T_KJPZ.setValue("7900900001", "FISTL_G");
                } else {
                    // 13 成本中心 是项目报销或者是pc项目报销时  不传成本中心  传wbs
                    tablein_T_KJPZ.setValue(Util.null2String(RsTable_main.getString("fykmm")), "HKONT_Z");
                    if (bxlx.equals("1") || bxlx.equals("2") || bxlx.equals("3")) {
                        tablein_T_KJPZ.setValue(Util.null2String(RsTable_main.getString("wbs")), "PSRNR_Z");
                    } else {
                        tablein_T_KJPZ.setValue(Util.null2String(RsTable_main.getString("cbzxm")), "KOSTL_Z");
                    }
                }
                LogUtil.doWriteLog("==单人报销，只推送一次循环11==");
                // 11 凭证货币金额
                tablein_T_KJPZ.setValue(Util.null2String(RsTable_main.getString("jebhs1")), "WRBTR_Z");
                LogUtil.doWriteLog("==单人报销，只推送一次循环12==");
                // 12 业务范围
                tablein_T_KJPZ.setValue("3000", "GSBER_Z");
                LogUtil.doWriteLog("==单人报销，只推送一次循环13==");
                LogUtil.doWriteLog("==单人报销，只推送一次循环14==");
                // 14 利润中心
                tablein_T_KJPZ.setValue("", "PRCTR_Z");
                LogUtil.doWriteLog("==单人报销，只推送一次循环15==");
                // 15 WBS元素
                //tablein_T_KJPZ.setValue(Util.null2String(RsTable_main.getString("wbs")),"PSRNR_Z");
                // 16 分配编号
                tablein_T_KJPZ.setValue(Util.null2String(RsTable_main.getString("fprq")).replaceAll("-", ""), "ZUONR_Z");
                LogUtil.doWriteLog("==单人报销，只推送一次循环16==");
                // 17 项目文本
                tablein_T_KJPZ.setValue(Util.null2String(RsTable_main.getString("zy1")), "SGTXT_Z");
                LogUtil.doWriteLog("==供应商编码==" + RsTable_main.getString("gysbh1"));
                LogUtil.doWriteLog("==凭证货币金额==" + RsTable_main.getString("bxzje1"));
                LogUtil.doWriteLog("==项目文本==" + RsTable_main.getString("zy1"));
                LogUtil.doWriteLog("==总账科目记帐代码==" + strSubjectCode);
                LogUtil.doWriteLog("==特殊总账标识==" + strSpecialCode);
                LogUtil.doWriteLog("==总账科目号==" + RsTable_main.getString("fykmm"));
                LogUtil.doWriteLog("==凭证货币金额==" + RsTable_main.getString("jebhs1"));
                LogUtil.doWriteLog("==成本中心==" + RsTable_main.getString("cbzxm"));
                LogUtil.doWriteLog("==WBS元素==" + RsTable_main.getString("wbs"));
                LogUtil.doWriteLog("==项目文本==" + RsTable_main.getString("zy1"));
                LogUtil.doWriteLog("==总账科目号fykmm==" + RsTable_main.getString("fykmm"));
                // 增加 税的数据
                if (Invoice(requestid)) {
                    // 查询税的数据
                    String strPushSapSql_2 = "select fm.fprq,fmdt4.jzm,fmdt4.kmm,fmdt4.wb,fmdt4.jjzx,fmdt4.je3 from formtable_main_85 fm,formtable_main_85_dt4 fmdt4 where fm.requestid='" + requestid + "' and fm.id= fmdt4.mainid  and fmdt4.cbzxm=''";
                    LogUtil.debugLog("========特殊情况的记账代码的处理=======>" + strPushSapSql_2);
                    RsTable_2.execute(strPushSapSql_2);
                    int count = RsTable_2.getColCounts();
                    int i = 1;
                    while (RsTable_2.next()) {
                        //特殊情况判断
                        if (i == count) {
                            // 18 税记帐代码
                            String jzdm = Util.null2String(RsTable_2.getString("jzm"));
                            //add by lylong  -------------------begin
                            if (fykmm.equals("2181020000")) {
                                tablein_T_KJPZ.appendRows(i);
//                                //基金中心
//                                tablein_T_KJPZ.setValue(Util.null2String(RsTable_2.getString("jjzx")), "FISTL_G");
                            }
                            //add by lylong  -------------------end
                            //                    if (jzdm.equals("50"))
                            //                    {
                            //                        tablein_T_KJPZ.appendRows(i);
                            //                        //科目码
                            //                        tablein_T_KJPZ.setValue("2171010700", "HKONT_G");
                            //                        //基金中心
                            //                        tablein_T_KJPZ.setValue(Util.null2String(RsTable_2.getString("jjzx")), "FISTL_G");
                            //                    }
                            /*//40
                            tablein_T_KJPZ.setValue(Util.null2String(RsTable_2.getString("jzm")), "BSCHL_S");
                            // 19 税总分类帐帐目
                            tablein_T_KJPZ.setValue(Util.null2String(RsTable_2.getString("kmm")), "HKONT_S");
                            // 20 凭证货币金额  取dt4 税额
                            tablein_T_KJPZ.setValue(Util.null2String(RsTable_2.getString("je3")), "WRBTR_S");
                            LogUtil.debugLog("=====明细税额=========>" + RsTable_2.getString("je3"));
                            // 21 业务范围
                            tablein_T_KJPZ.setValue("3000", "GSBER_S");
                            // 22 分配编号  分配日棋
                            tablein_T_KJPZ.setValue(Util.null2String(RsTable_2.getString("fprq")).replaceAll("-", ""), "ZUONR_S");
                            // 23 项目文本
                            tablein_T_KJPZ.setValue(delHtml(Util.null2String(RsTable_2.getString("wb"))), "SGTXT_S");*/
                            i++;
                        }
                    }
                    //税额改为查询dt5的信息
                    String sexgchaxun = "select fm.fprq,fmdt5.sj1,fm.zy1 from formtable_main_85 fm,formtable_main_85_dt5 fmdt5 where fm.requestid='" + requestid + "' and fm.id= fmdt5.mainid ";
                    RecordSet rssechaxun = new RecordSet();
                    //LogUtil.debugLog("查询的sql==="+sexgchaxun);

                    rssechaxun.execute(sexgchaxun);
                    //LogUtil.debugLog("得到的查询出的数据的数量aaaababbb==="+count);
                    count = rssechaxun.getColCounts();
                    //	rssechaxun.getC

                    //  LogUtil.debugLog("得到的查询出的数据的数量==="+count);
                    int j = 1;
                    while (rssechaxun.next()) {

                        //                LogUtil.debugLog("count的值===" + count);
                        //                LogUtil.debugLog("j的值===" + j);
                        //
                        //                LogUtil.debugLog("j的值===" + j + "判断===" + (count < j));
                        // if (count < j)
                        //            {
                        //                LogUtil.debugLog("是否进入count<j"); break;
                        //            }
                        tablein_T_KJPZ.appendRows(i);
                        //记账码
                        tablein_T_KJPZ.setValue("40", "BSCHL_S");
                        // 19 税总分类帐帐目   科目码
                        tablein_T_KJPZ.setValue("2171010100", "HKONT_S");
                        // 20 凭证货币金额
                        tablein_T_KJPZ.setValue(Util.null2String(rssechaxun.getString("sj1")), "WRBTR_S");
                        LogUtil.debugLog("=====明细税额=========>" + rssechaxun.getString("sj1"));
                        // 21 业务范围
                        tablein_T_KJPZ.setValue("3000", "GSBER_S");
                        // 22 分配编号  分配日期主表的
                        tablein_T_KJPZ.setValue(Util.null2String(rssechaxun.getString("fprq")).replaceAll("-", ""), "ZUONR_S");
                        // 23 项目文本   主表摘要
                        tablein_T_KJPZ.setValue(delHtml(Util.null2String(rssechaxun.getString("zy1"))), "SGTXT_S");
                        j++;
                    }
                }
            }
            // 根据报销明细有多少次循环
            else {
                if ("0".equals(sfdrbx)) {
                    intnum = 1;
                    LogUtil.doWriteLog("==单人报销，只推送一次循环==");
                    tablein_T_KJPZ.appendRow();
                    LogUtil.doWriteLog("==单人报销，只推送一次循环1==");
                    // 1 供应商记帐代码
                    tablein_T_KJPZ.setValue("31", "BSCHL_G");
                    LogUtil.doWriteLog("==单人报销，只推送一次循环2==");
                    // 2 供应商编码
                    tablein_T_KJPZ.setValue(Util.null2String(RsTable_main.getString("gysbh1")), "LIFNR_G");
                    LogUtil.doWriteLog("==单人报销，只推送一次循环3==");
                    // 3 凭证货币金额
                    tablein_T_KJPZ.setValue(Util.null2String(RsTable_main.getString("bxzje1")), "WRBTR_G");
                    LogUtil.doWriteLog("==单人报销，只推送一次循环4==");
                    // 4 业务范围
                    tablein_T_KJPZ.setValue("3000", "GSBER_G");
                    LogUtil.doWriteLog("==单人报销，只推送一次循环5==");
                    // 5 分配编号
                    tablein_T_KJPZ.setValue(Util.null2String(RsTable_main.getString("fprq")).replaceAll("-", ""), "ZUONR_G");
                    LogUtil.doWriteLog("==单人报销，只推送一次循环6==");
                    // 6 项目文本
                    tablein_T_KJPZ.setValue(Util.null2String(RsTable_main.getString("zy1")), "SGTXT_G");
                    LogUtil.doWriteLog("==单人报销，只推送一次循环7==");
                    // 7 基准日期
                    tablein_T_KJPZ.setValue(Util.null2String(RsTable_main.getString("fprq")).replaceAll("-", ""), "ZFBDT_G");
                    LogUtil.doWriteLog("==单人报销，只推送一次循环8==");
                    // 8 总账科目记帐代码
                    tablein_T_KJPZ.setValue(strSubjectCode, "BSCHL_Z");
                    LogUtil.doWriteLog("==单人报销，只推送一次循环9==");
                    // 9 特殊总账标识 至于是是的时候才传
                    if ("0".equals(RsTable_main.getString("sfcztbjzm"))) {
                        tablein_T_KJPZ.setValue(strSpecialCode, "UMSKZ_Z");
                    }
                    // 10 总账科目号
                    String fykmm = RsTable_main.getString("fykmm");
                    LogUtil.doWriteLog("==单人报销，只推送一次循环10==");
                    if (fykmm.equals("2181020000")) {
                        //特殊记账码情况要传基金中心 且不传wbs 和成本中心
                        tablein_T_KJPZ.setValue("97990001", "LIFNR_Z");
                        tablein_T_KJPZ.setValue(BLDAT, "ZFBDT_Z");
                        //基金中心
                        tablein_T_KJPZ.setValue("7900900001", "FISTL_G");
                    } else {
//                        //科目码
//                        tablein_T_KJPZ.setValue("2171010700", "HKONT_G");
                        // 13 成本中心 是项目报销或者是pc项目报销时  不传成本中心  传wbs
                        tablein_T_KJPZ.setValue(Util.null2String(RsTable_main.getString("fykmm")), "HKONT_Z");
                        if (bxlx.equals("1") || bxlx.equals("2") || bxlx.equals("3")) {
                            tablein_T_KJPZ.setValue(Util.null2String(RsTable_main.getString("wbs")), "PSRNR_Z");
                        } else {
                            tablein_T_KJPZ.setValue(Util.null2String(RsTable_main.getString("cbzxm")), "KOSTL_Z");
                        }

                    }
                    LogUtil.doWriteLog("==单人报销，只推送一次循环11==");
                    // 11 凭证货币金额
                    tablein_T_KJPZ.setValue(Util.null2String(RsTable_main.getString("jebhs1")), "WRBTR_Z");
                    LogUtil.doWriteLog("==单人报销，只推送一次循环12==");
                    // 12 业务范围
                    tablein_T_KJPZ.setValue("3000", "GSBER_Z");
                    LogUtil.doWriteLog("==单人报销，只推送一次循环13==");
                    LogUtil.doWriteLog("==单人报销，只推送一次循环14==");
                    // 14 利润中心
                    tablein_T_KJPZ.setValue("", "PRCTR_Z");
                    LogUtil.doWriteLog("==单人报销，只推送一次循环15==");
                    // 15 WBS元素
                    //tablein_T_KJPZ.setValue(Util.null2String(RsTable_main.getString("wbs")),"PSRNR_Z");
                    // 16 分配编号
                    tablein_T_KJPZ.setValue(Util.null2String(RsTable_main.getString("fprq")).replaceAll("-", ""), "ZUONR_Z");
                    LogUtil.doWriteLog("==单人报销，只推送一次循环16==");
                    // 17 项目文本
                    tablein_T_KJPZ.setValue(Util.null2String(RsTable_main.getString("zy1")), "SGTXT_Z");
                    LogUtil.doWriteLog("==供应商编码==" + RsTable_main.getString("gysbh1"));
                    LogUtil.doWriteLog("==凭证货币金额==" + RsTable_main.getString("bxzje1"));
                    LogUtil.doWriteLog("==项目文本==" + RsTable_main.getString("zy1"));
                    LogUtil.doWriteLog("==总账科目记帐代码==" + strSubjectCode);
                    LogUtil.doWriteLog("==特殊总账标识==" + strSpecialCode);
                    LogUtil.doWriteLog("==总账科目号==" + RsTable_main.getString("fykmm"));
                    LogUtil.doWriteLog("==凭证货币金额==" + RsTable_main.getString("jebhs1"));
                    LogUtil.doWriteLog("==成本中心==" + RsTable_main.getString("cbzxm"));
                    LogUtil.doWriteLog("==WBS元素==" + RsTable_main.getString("wbs"));
                    LogUtil.doWriteLog("==项目文本==" + RsTable_main.getString("zy1"));
                    LogUtil.doWriteLog("==总账科目号fykmm==" + RsTable_main.getString("fykmm"));
                    // 增加 税的数据
                    if (Invoice(requestid)) {
                        // 查询税的数据
                        String strPushSapSql_2 = "select fm.fprq,fmdt4.jzm,fmdt4.kmm,fmdt4.wb,fmdt4.jjzx,fmdt4.je3 from formtable_main_85 fm,formtable_main_85_dt4 fmdt4 where fm.requestid='" + requestid + "' and fm.id= fmdt4.mainid  and fmdt4.cbzxm=''";
                        LogUtil.debugLog("========特殊情况的记账代码的处理=======>" + strPushSapSql_2);
                        RsTable_2.execute(strPushSapSql_2);
                        int count = RsTable_2.getColCounts();
                        int i = 1;
                        while (RsTable_2.next()) {
                            //特殊情况判断
                            if (i == count) {
                                // 18 税记帐代码
                                String jzdm = Util.null2String(RsTable_2.getString("jzm"));
                                if (fykmm.equals("2181020000")) {
                                    tablein_T_KJPZ.appendRows(i);
                                    //基金中心
//                                    tablein_T_KJPZ.setValue("7900900001", "FISTL_G");
                                }
                                //								if (jzdm.equals("50")) {
                                //									tablein_T_KJPZ.appendRows(i);
                                //									//科目码
                                //									tablein_T_KJPZ.setValue("2171010700", "HKONT_G");
                                //									//基金中心
                                //									tablein_T_KJPZ.setValue(Util.null2String(RsTable_2.getString("jjzx")), "FISTL_G");
                                //								}
                                    /*//40
                                    tablein_T_KJPZ.setValue(Util.null2String(RsTable_2.getString("jzm")),"BSCHL_S");
                                    // 19 税总分类帐帐目
                                    tablein_T_KJPZ.setValue(Util.null2String(RsTable_2.getString("kmm")),"HKONT_S");
                                    // 20 凭证货币金额  取dt4 税额
                                    tablein_T_KJPZ.setValue(Util.null2String(RsTable_2.getString("je3")),"WRBTR_S");
                                    LogUtil.debugLog("=====明细税额=========>"+RsTable_2.getString("je3"));
                                    // 21 业务范围
                                    tablein_T_KJPZ.setValue("3000", "GSBER_S");
                                    // 22 分配编号  分配日棋
                                    tablein_T_KJPZ.setValue(Util.null2String(RsTable_2.getString("fprq")).replaceAll("-", ""), "ZUONR_S");
                                    // 23 项目文本
                                    tablein_T_KJPZ.setValue(delHtml(Util.null2String(RsTable_2.getString("wb"))),"SGTXT_S");*/
                                i++;
                            }
                        }
                        //税额改为查询dt5的信息
                        String sexgchaxun = "select fm.fprq,fmdt5.sj1,fm.zy1 from formtable_main_85 fm,formtable_main_85_dt5 fmdt5 where fm.requestid='" + requestid + "' and fm.id= fmdt5.mainid ";
                        RecordSet rssechaxun = new RecordSet();
                        //LogUtil.debugLog("查询的sql==="+sexgchaxun);

                        rssechaxun.execute(sexgchaxun);
                        //LogUtil.debugLog("得到的查询出的数据的数量aaaababbb==="+count);
                        count = rssechaxun.getColCounts();
                        //	rssechaxun.getC

                        //  LogUtil.debugLog("得到的查询出的数据的数量==="+count);
                        int j = 1;
                        while (rssechaxun.next()) {

                            //                        LogUtil.debugLog("count的值==="+count);
                            //                        LogUtil.debugLog("j的值==="+j);
                            //
                            //                        LogUtil.debugLog("j的值==="+j+"判断==="+(count<j));
                            //						if(count<j){
                            //                            LogUtil.debugLog("是否进入count<j");
                            //							break;
                            //						}
                            tablein_T_KJPZ.appendRows(i);
                            //记账码
                            tablein_T_KJPZ.setValue("40", "BSCHL_S");
                            // 19 税总分类帐帐目   科目码
                            tablein_T_KJPZ.setValue("2171010100", "HKONT_S");
                            // 20 凭证货币金额
                            tablein_T_KJPZ.setValue(Util.null2String(rssechaxun.getString("sj1")), "WRBTR_S");
                            LogUtil.debugLog("=====明细税额=========>" + rssechaxun.getString("sj1"));
                            // 21 业务范围
                            tablein_T_KJPZ.setValue("3000", "GSBER_S");
                            // 22 分配编号  分配日期主表的
                            tablein_T_KJPZ.setValue(Util.null2String(rssechaxun.getString("fprq")).replaceAll("-", ""), "ZUONR_S");
                            // 23 项目文本   主表摘要
                            tablein_T_KJPZ.setValue(delHtml(Util.null2String(rssechaxun.getString("zy1"))), "SGTXT_S");
                            j++;
                        }
                    }
                } else {
                    LogUtil.doWriteLog("==多人报销==");
                    // 查询报销人数
                    String strPushSapSql_1 = "select fmdt2.cbzxm,fmdt2.bxzje,* from formtable_main_85 fm,formtable_main_85_dt2 fmdt2 where fm.requestid='" + requestid + "' and fm.id= fmdt2.mainid";
                    RsTable_1.execute(strPushSapSql_1);
                    LogUtil.doWriteLog("==查询报销相关信息==" + strPushSapSql_1);
                    while (RsTable_1.next()) {
                        tablein_T_KJPZ.appendRow();
                        // 1 供应商记帐代码
                        tablein_T_KJPZ.setValue("31", "BSCHL_G");
                        // 2 供应商编码  明细2 供应商编码
                        tablein_T_KJPZ.setValue(Util.null2String(RsTable_1.getString("gysbh")), "LIFNR_G");
                        // 3 凭证货币金额
                        tablein_T_KJPZ.setValue(Util.null2String(RsTable_1.getString("bxzje")), "WRBTR_G");
                        // 4 业务范围
                        tablein_T_KJPZ.setValue("3000", "GSBER_G");
                        // 5 分配编号
                        tablein_T_KJPZ.setValue(Util.null2String(RsTable_1.getString("fprq")).replaceAll("-", ""), "ZUONR_G");
                        // 6 项目文本
                        tablein_T_KJPZ.setValue(Util.null2String(RsTable_1.getString("zy")), "SGTXT_G");
                        // 7 基准日期
                        tablein_T_KJPZ.setValue(Util.null2String(RsTable_1.getString("fprq")).replaceAll("-", ""), "ZFBDT_G");
                        // 8 总账科目记帐代码
                        tablein_T_KJPZ.setValue(strSubjectCode, "BSCHL_Z");
                        // 9 特殊总账标识
                        if ("0".equals(RsTable_main.getString("sfcztbjzm"))) {
                            tablein_T_KJPZ.setValue(strSpecialCode, "UMSKZ_Z");
                        }
                        String fykmm = Util.null2String(RsTable_1.getString("fykmm"));
                        if (fykmm.equals("2181020000")) {
                            //特殊记账码情况要传基金中心 且不传wbs 和成本中心
                            tablein_T_KJPZ.setValue("97990001", "LIFNR_Z");
                            tablein_T_KJPZ.setValue(BLDAT, "ZFBDT_Z");
                            //基金中心
                            tablein_T_KJPZ.setValue("7900900001", "FISTL_G");
                        } else {
                            // 10 总账科目号   主表
                            tablein_T_KJPZ.setValue(Util.null2String(RsTable_1.getString("fykmm")), "HKONT_Z");
                            // 15 WBS元素
                            if (bxlx.equals("1") || bxlx.equals("2") || bxlx.equals("3")) {
                                tablein_T_KJPZ.setValue(Util.null2String(RsTable_1.getString("wbs")), "PSRNR_Z");
                            } else {
                                tablein_T_KJPZ.setValue(Util.null2String(RsTable_1.getString("cbzxm")), "KOSTL_Z");
                            }
                        }
                        // 11 凭证货币金额
                        tablein_T_KJPZ.setValue(Util.null2String(RsTable_1.getString("bxzje")), "WRBTR_Z");
                        // 12 业务范围
                        tablein_T_KJPZ.setValue("3000", "GSBER_Z");
                        // 13 成本中心
                        //tablein_T_KJPZ.setValue(Util.null2String(RsTable_1.getString("cbzxm")),"KOSTL_Z");
                        // 14 利润中心
                        tablein_T_KJPZ.setValue("", "PRCTR_Z");
                        //tablein_T_KJPZ.setValue(Util.null2String(RsTable_1.getString("wbs")),"PSRNR_Z");
                        // 16 分配编号
                        tablein_T_KJPZ.setValue(Util.null2String(RsTable_1.getString("fprq")).replaceAll("-", ""), "ZUONR_Z");
                        // 17 项目文本
                        tablein_T_KJPZ.setValue(delHtml(Util.null2String(RsTable_1.getString("zy"))), "SGTXT_Z");
                    }
                }
            }
        }
        LogUtil.debugLog("推送对象-------" + tablein_T_KJPZ.toString());
        // 执行输入
        jcoClient.execute(function);
        // 输出的表名
        JCO.Table table_out = function.getTableParameterList().getTable("T_RETURN");
        LogUtil.doWriteLog("==获取输出行数==" + table_out.getNumRows());
        boolean retFlag = true;
        String reterrorMessage = "";
        int num = 1;//记录错误条数页面显示优化
        for (int i = 0; i < table_out.getNumRows(); i++) {
            table_out.setRow(i);
            strType = Util.null2String(table_out.getString("TYPE"));
            strMsg = Util.null2String(table_out.getString("MESSAGE"));
            LogUtil.doWriteLog("===费用报销会计凭证接口返回值==strType===" + strType + "===ID==" + table_out.getString("ID") + "===NUMBER===" + table_out.getString("NUMBER") + "===strMsg====" + strMsg + "====凭证号=====" + strFictitiousVoucherNum);
            if ("E".equals(strType)) {
                retFlag = false;
                reterrorMessage += "\n(" + num + ")" + strMsg;
                num++;
            }
        }
        //返回为0行时候执行
        if (table_out.getNumRows() == 0) {
            //发票凭证的凭证编号
            strFictitiousVoucherNum = function.getExportParameterList().getString("E_BELNR");
            setwfdata(strFictitiousVoucherNum, requestid);
        }
        if (!retFlag) {
            return "E," + reterrorMessage;
        }
        return strType + "," + strMsg;
    }

    /**
     * 向表单中插入会计凭证号
     *
     * @param FictitiousVoucherNum
     */
    public static void setwfdata(String FictitiousVoucherNum, String requestid) {
        RecordSet RsUpdate = new RecordSet();
        // 向表单中插入会计凭证号Sql
        String strUpdateSql = "update formtable_main_85 set kjpzh = '" + FictitiousVoucherNum + "' where requestid=" + requestid;
        RsUpdate.execute(strUpdateSql);
        LogUtil.doWriteLog("===向费用报销表单中插入会计凭证号==" + strUpdateSql);
    }

    /**
     * @param requestid
     * @return
     */
    public static Boolean Invoice(String requestid) {
        RecordSet rs = new RecordSet();
        // 返回值
        Boolean booInvoice = false;
        // 查询是否存在专票
        String InvoiceSql = "select 1 from formtable_main_85 fm,formtable_main_85_dt5 fmdt5 where fm.requestid='" + requestid + "' and fm.id= fmdt5.mainid";
        rs.execute(InvoiceSql);
        LogUtil.doWriteLog("==查询是否存在专票==" + InvoiceSql);
        if (rs.first()) {
            booInvoice = true;
        }
        return booInvoice;
    }

    public String delHtml(String inputString) {
        String htmlStr = new weaver.workflow.mode.FieldInfo().toExcel(inputString); // 含html标签的字符串
        htmlStr = Util.StringReplace(htmlStr, "&dt;&at;", "<br>");
        htmlStr = Util.StringReplace(htmlStr, "<script>initFlashVideo();</script>", "");
        String textStr = "";
        java.util.regex.Pattern p_script;
        java.util.regex.Matcher m_script;
        java.util.regex.Pattern p_html;
        java.util.regex.Matcher m_html;

        try {
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

            String regEx_script = "<[/s]*?script[^>]*?>[/s/S]*?<[/s]*?//[/s]*?script[/s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[/s/S]*?<//script>

            p_script = java.util.regex.Pattern.compile(regEx_script, java.util.regex.Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签

            p_html = java.util.regex.Pattern.compile(regEx_html, java.util.regex.Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签

            textStr = htmlStr;

        } catch (Exception e) {
            //System.err.println("Html2Text:" + e.getMessage());
        }

        return Util.HTMLtoTxt(textStr).replaceAll("%nbsp;", "").replaceAll("%nbsp", "").trim();// 返回文本字符串

    }

    public static void main(String[] args) {
        WfCost_SAP w = new WfCost_SAP();
        String s = w.delHtml("<html>&nbsp;</html>");
        System.out.println(s);
    }

}
