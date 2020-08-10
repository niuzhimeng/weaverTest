package com.weavernorth.dadiyingyuan.feeReimburse.feiyongbaoxiaodan;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.weavernorth.dadiyingyuan.feeReimburse.feeBaseReimburseBean.feeReimburseExpenseListBean;
import com.weavernorth.dadiyingyuan.feeReimburse.feeBaseReimburseBean.feeReimburseRecListBean;
import com.weavernorth.dadiyingyuan.feeReimburse.feeBaseReimburseBean.feeReimburseRevLoanBean;
import com.weavernorth.dadiyingyuan.feeReimburse.feeReimburseClient.OaReimService_PortType;
import com.weavernorth.dadiyingyuan.feeReimburse.feeReimburseClient.OaReimService_ServiceLocator;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
/**
*@program: currentMoneyReimburseForHeadquarters
*@description: 现金报销单（总部/媒体）
*@author: chchq
*@updateDate: 2019/6/21
*/
public class currentMoneyReimburseForHeadquarters extends BaseAction {
    @Override
    public String execute(RequestInfo request) {
        //1、获取流程表单数据
        String requestid = request.getRequestid();//获取流程请求id
        String tableName = request.getRequestManager().getBillTableName();//获取流程表单名称
        String workflowid = request.getWorkflowid();//获取流程id
        String operator = request.getRequestManager().getSrc();//获取流程状态
        String requestname = request.getRequestManager().getRequestname();//请求标题
        String lastoperator = request.getLastoperator();//获取当前节点操作者
        //获取当前时间
        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String format = df.format(day);
        RecordSet rs = new RecordSet();//主表信息
        RecordSet detailRs=new RecordSet();//明细表1信息
        RecordSet detailRsTwo=new RecordSet();//明细表2信息

        //新建expenseList集合
        List arrayListexpenseList= new ArrayList();
        //新建recList集合
        List arrayListRecList= new ArrayList();
        //新建revLoan集合
        List arrayListRevLoan= new ArrayList();
        String s="";

        //方法执行结果
        String result = "";
        //推送结果
        String tsjg = "";
        //修改推送结果sql
        String updateTsjgSql = "update "+tableName+" SET tsjg = ? where requestid = ?  ";
        ConnStatement con = new ConnStatement();
        this.writeLog("currentMoneyReimburseForHeadquarters 现金报销单（总部/媒体）--请求id："+requestid+"--start");

        if("submit".equals(operator)) {
            try {
                //获取主表信息
                rs.execute("select * from " + tableName + " where requestId='" + requestid + "'");//获取流程主表数据
                this.writeLog("查询主表单获取数据-----" + "select * from " + tableName + " where requestId='" + requestid + "'");
                rs.next();
                //获取主表数据
                String pkOaHead = requestid;//requestid---pkOaHead
                String number = rs.getString("dh");//单号---number
                this.writeLog("单号："+number);
                String applier = rs.getString("sqr");//申请人---applier
                String appDate = rs.getString("sqrq");//	申请日期---appDate
                //String payDept = rs.getString("fycdcode");//	费用承担部门---payDept
                String payOrgId = rs.getString("fyzfgs");//	费用支付公司
                //String payType = rs.getString("zffs");//支付方式---payType
                String describ = rs.getString("sy");//	事由---describ
                int attendCount = rs.getInt("fjs");//附件数---attendCount
                String auditor = lastoperator;//lastoperator---auditor
                String auditTime = format;//format---auditTime
                String creator = rs.getString("sqr");//申请人---creator
                String originatTime = rs.getString("sqrq");//	申请日期---originatTime
                String oaBizType = "现金报销单(总部/媒体)";//requestname---oaBizType

                //明细表字段
                String payEntryDeptId = rs.getString("fycdbm");//费用承担部门---费用支付部门
                String payEntryOrgId = rs.getString("fycdgs");//费用承担公司---费用支付公司
                //String payEntryType = rs.getString("zffs");//支付方式---payEntryType
                String recPerson = rs.getString("skrmc");//收款人名称---recPerson
                String recCity = rs.getString("khhdq");//开户行地区---recCity
                String recBankAccount = rs.getString("yxzh");//银行账号---recBankAccount
                String bank = rs.getString("khyh");//	开户银行---bank
                String recAmtValue = rs.getString("sfje");//	实付金额---recAmt
                //2019.4.4原借款金额
                String brrowAmtValue = rs.getString("yjkje");//	原借款金额---brrowAmt
                //处理千分位问题
                double brrowAmtV = Double.valueOf(brrowAmtValue.replace(",", "")).doubleValue() < 0 ? 0 : Double.valueOf(brrowAmtValue.replace(",", "")).doubleValue();
                NumberFormat numberFormat20 = NumberFormat.getNumberInstance();
                numberFormat20.setGroupingUsed(false); //设置了以后不会有千分位，如果不设置，默认是有的
                String brrowAmt = numberFormat20.format(brrowAmtV);
                //影院
                String zaijianId = rs.getString("yy");//影院---zaijian
                String id = rs.getString("id");//id
                //2019.4.4新增押金性质和产品档案
                //cpda--产品档案
                String productId = rs.getString("cpda");//产品档案---product
                String longnumberCp = "";
                if ("null".equals(productId) || productId.trim().length() == 0) {
                    longnumberCp = "";
                } else {
                    //增加产品
                    RecordSet cpID = new RecordSet();
                    String cpIdSql = "select longnumber from uf_hsxmlbmxb where id=" + productId;
                    cpID.execute(cpIdSql);
                    cpID.next();
                    longnumberCp = cpID.getString("longnumber");
                    this.writeLog("产品档案---->" + longnumberCp);
                }
                //押金性质
                String yJXZ = rs.getString("yjxz");//押金性质--yajin
                this.writeLog("押金性质ID--->" + yJXZ);
                String longnumberYj = "";
                if ("null".equals(yJXZ) || yJXZ.trim().length() == 0) {
                    longnumberYj = "";
                } else {
                    //增加产品
                    RecordSet yjID = new RecordSet();
                    String yjIdSql = "select longnumber from uf_hsxmlbmxb where id=" + yJXZ;
                    yjID.execute(yjIdSql);
                    yjID.next();
                    longnumberYj = yjID.getString("longnumber");
                    this.writeLog("产品档案---->" + longnumberYj);
                }
                //影院
                String zaijian = "";
                if ("null".equals(zaijianId) || zaijianId.trim().length() == 0) {
                    zaijian = "";//没填值则为""
                } else {
                    //增加影院
                    RecordSet zaijianYY = new RecordSet();
                    String zJYYSql = "select longnumber from uf_hsxmlbmxb where id=" + zaijianId;
                    zaijianYY.execute(zJYYSql);
                    zaijianYY.next();
                    zaijian = zaijianYY.getString("longnumber");
                    this.writeLog("影院---->" + zaijian);
                }

                //新增字段
                String zhifu = rs.getString("zfklb");//支付款类型N---zhifu
                this.writeLog("实付金额------>" + recAmtValue);

                //处理千分位问题
                double recAmtV = Double.valueOf(recAmtValue.replace(",", "")).doubleValue() < 0 ? 0 : Double.valueOf(recAmtValue.replace(",", "")).doubleValue();
                NumberFormat numberFormat1 = NumberFormat.getNumberInstance();
                numberFormat1.setGroupingUsed(false); //设置了以后不会有千分位，如果不设置，默认是有的
                String recAmt = numberFormat1.format(recAmtV);
                this.writeLog("recAmt---->" + recAmt);
                //获取申请人邮箱
                RecordSet rsEmail = new RecordSet();//主标信息
                String emailSql = "select email from HrmResource where id= " + applier + "";
                rsEmail.execute(emailSql);
                rsEmail.next();
                String email = rsEmail.getString("email");
                String emailBefore = email.substring(0, email.indexOf("@"));//截取@之前的字符串
                int length = emailBefore.length();
                String emailAfter = email.substring(length);
                String applierCode = "";
                if (emailAfter.equals("@dadimedia")) {
                    applierCode = emailBefore;
                } else {
                    applierCode = email;
                }

                //获取当前操作人邮箱
                RecordSet rsAuditorEmail = new RecordSet();//主标信息
                String emailAuditorSql = "select email from HrmResource where id= " + lastoperator + "";
                rsAuditorEmail.execute(emailAuditorSql);
                rsAuditorEmail.next();
                String emailAuditor = rsAuditorEmail.getString("email");
                String emailAuditorBefore = emailAuditor.substring(0, emailAuditor.indexOf("@"));//截取@之前的字符串
                int lengthAuditor = emailAuditorBefore.length();
                String emailAuditorAfter = email.substring(lengthAuditor);
                String auditorCode = "";
                if (emailAuditorAfter.equals("@dadimedia")) {
                    auditorCode = emailAuditorBefore;
                } else {
                    auditorCode = emailAuditor;
                }

                //查询费用支付公司
                RecordSet feeOutOrg = new RecordSet();
                this.writeLog("查询费用支付公司--->" + "select code from uf_zzb where id=" + payOrgId);
                String queryFeeOutOrg = "select code from uf_zzb where id=" + payOrgId;
                feeOutOrg.execute(queryFeeOutOrg);
                feeOutOrg.next();
                String payOrg = feeOutOrg.getString("code");
                this.writeLog("费用支付公司---" + payOrg);


                //查询费用承担部门和承担公司code
                RecordSet feeAcceptDept = new RecordSet();
                this.writeLog("查询承担部门--->" + "select code from uf_zzb where id=" + payEntryDeptId);
                String queryDept = "select code from uf_zzb where id=" + payEntryDeptId;
                feeAcceptDept.execute(queryDept);
                feeAcceptDept.next();
                String payEntryDept = feeAcceptDept.getString("code");
                this.writeLog("承担部门---" + payEntryDept);

                //查询承担公司
                RecordSet feeAcceptOrg = new RecordSet();
                this.writeLog("查询承担公司--->" + "select code from uf_zzb where id=" + payEntryOrgId);
                String queryOrg = "select code from uf_zzb where id=" + payEntryOrgId;
                feeAcceptOrg.execute(queryOrg);
                feeAcceptOrg.next();
                String payEntryOrg = feeAcceptOrg.getString("code");
                this.writeLog("承担公司---" + payEntryOrg);

                detailRs.execute("select * from formtable_main_250_dt1   where mainid=" + id + "");//获取对应的明细表数据
                this.writeLog("明细表1信息" + "select * from formtable_main_250_dt1   where mainid=" + id + "");
                String expenseTypeCode = "";
                //明细表1信息
                while (detailRs.next()) {
                    String pkOaBody = detailRs.getString("id");//id---pkOaBody
                    String expenseTypeId = detailRs.getString("fylx");//费用类别---expenseType
                    //String purpose = detailRs.getString("yt");//用途---purpose
                    String purpose = "null".equals(detailRs.getString("yt")) || detailRs.getString("yt").trim().length() == 0 ? "" : detailRs.getString("yt");

                    String curDate = detailRs.getString("fsrq");//发生日期---curDate
                    String taxAmtValue = detailRs.getString("se");//se税额

                    //新增字段
                    String jiaonaId = detailRs.getString("jnd");//缴纳地N---jiaona
                    String jiaona = "";
                    if ("null".equals(jiaonaId) || jiaonaId.trim().length() == 0) {
                        jiaona = "";//没填值则为""
                    } else {
                        //增加缴纳地
                        RecordSet Jnd = new RecordSet();
                        String JnaSql = "select longnumber from uf_hsxmlbmxb where id=" + jiaonaId;
                        Jnd.execute(JnaSql);
                        Jnd.next();
                        jiaona = Jnd.getString("longnumber");
                        this.writeLog("缴纳地---->" + jiaona);
                    }
                    //2019.4.4新增客户、供应商
                    //查询供应商code和账号--
                    String gysId = detailRs.getString("gys");//供应商---gys
                    String recSuplier = "";
                    if ("null".equals(gysId) || gysId.trim().length() == 0) {
                        recSuplier = "";
                    } else {
                        RecordSet recCode = new RecordSet();
                        String recSql = "select code from uf_gysb where id=" + gysId;
                        this.writeLog("供应商查询--->" + "select code from uf_gysb where id=" + gysId);
                        recCode.execute(recSql);
                        recCode.next();
                        recSuplier = recCode.getString("code");
                        this.writeLog("供应商code---->" + recSuplier);
                    }
                    //客户
                    //查询客户银行和账号
                    String khId = detailRs.getString("kh");//客户---kh
                    String retailCus = "";
                    if ("null".equals(khId) || khId.trim().length() == 0) {
                        retailCus = "";
                    } else {
                        RecordSet retailCode = new RecordSet();
                        String retailql = "select code from uf_khb where id=" + khId;
                        this.writeLog("客户账号---->" + "select code from uf_khb where id=" + khId);
                        retailCode.execute(retailql);
                        retailCode.next();
                        retailCus = retailCode.getString("code");
                        this.writeLog("客户code--->" + retailCus);
                    }

                    //String exchangeRate
                    //String localAmtNoRate
                    //String rate
                    //String taxAmt
                    String localAmtValue = detailRs.getString("sqje");//申请金额---localAmt
                    //String amtApprovedNoRateLocal
                    //String approvedTaxAmt
                    String amtApprovedLocalValue = detailRs.getString("hdje");//核定金额---amtApprovedLocal
                    this.writeLog("申请金额--->" + amtApprovedLocalValue);
                    //处理千分位问题
                    double localAmtV = Double.valueOf(localAmtValue.replace(",", "")).doubleValue() < 0 ? 0 : Double.valueOf(localAmtValue.replace(",", "")).doubleValue();
                    NumberFormat numberFormat2 = NumberFormat.getNumberInstance();
                    numberFormat2.setGroupingUsed(false); //设置了以后不会有千分位，如果不设置，默认是有的
                    String localAmt = numberFormat2.format(localAmtV);
                    this.writeLog("localAmt--->" + localAmt);
                    //处理千分位问题
                    double amtApprovedLocalV = Double.valueOf(amtApprovedLocalValue.replace(",", "")).doubleValue() < 0 ? 0 : Double.valueOf(amtApprovedLocalValue.replace(",", "")).doubleValue();
                    NumberFormat numberFormat3 = NumberFormat.getNumberInstance();
                    numberFormat3.setGroupingUsed(false); //设置了以后不会有千分位，如果不设置，默认是有的
                    String amtApprovedLocal = numberFormat3.format(amtApprovedLocalV);
                    this.writeLog("amtApprovedLocal--->" + amtApprovedLocal);
                    //处理千分位问题
                    /*double taxAmtV = Double.valueOf(taxAmtValue.replace(",", "")).doubleValue() <0? 0:Double.valueOf(taxAmtValue.replace(",", "")).doubleValue();
                    NumberFormat numberFormat4 = NumberFormat.getNumberInstance();
                    numberFormat4.setGroupingUsed(false); //设置了以后不会有千分位，如果不设置，默认是有的
                    String taxAmt = numberFormat4.format(taxAmtV);
                    this.writeLog("taxAmt---->"+taxAmt);*/
                    /*查询费用类型编码*/
                    RecordSet feeTypeCode = new RecordSet();
                    this.writeLog("查询费用类型" + "select code from uf_fylxb where id=" + expenseTypeId);
                    String queryFeeType = "select code from uf_fylxb where id=" + expenseTypeId;
                    feeTypeCode.execute(queryFeeType);
                    feeTypeCode.next();
                    expenseTypeCode = feeTypeCode.getString("code");//费用类型code
                    this.writeLog("expenseTypeCode--->" + expenseTypeCode);

                    feeReimburseExpenseListBean feeReimburseExpenseListBean = new feeReimburseExpenseListBean();
                    feeReimburseExpenseListBean.setPkOaBody(id);
                    feeReimburseExpenseListBean.setExpenseType(expenseTypeCode);
                    feeReimburseExpenseListBean.setPurpose(purpose);
                    feeReimburseExpenseListBean.setCurDate(curDate);
                    feeReimburseExpenseListBean.setExchangeRate(1.0);
                    feeReimburseExpenseListBean.setLocalAmtNoRate("0.0");
                    feeReimburseExpenseListBean.setRate("0.0");
                    feeReimburseExpenseListBean.setTaxAmt("0.0");
                    feeReimburseExpenseListBean.setLocalAmt(amtApprovedLocal);
                    feeReimburseExpenseListBean.setAmtApprovedNoRateLocal("0.0");
                    feeReimburseExpenseListBean.setApprovedTaxAmt("0.0");
                    feeReimburseExpenseListBean.setAmtApprovedLocal(amtApprovedLocal);
                    feeReimburseExpenseListBean.setPayEntryDept(payEntryDept);
                    feeReimburseExpenseListBean.setPayEntryOrg(payEntryOrg);
                    //新增字段
                    feeReimburseExpenseListBean.setProduct(longnumberCp);
                    feeReimburseExpenseListBean.setYajin(longnumberYj);
                    feeReimburseExpenseListBean.setCustomer(retailCus);
                    feeReimburseExpenseListBean.setSupplier(recSuplier);
                    feeReimburseExpenseListBean.setZaijian(zaijian);
                    feeReimburseExpenseListBean.setFeiyong("");
                    feeReimburseExpenseListBean.setJiaona(jiaona);
                    feeReimburseExpenseListBean.setZhifu(zhifu);
                    feeReimburseExpenseListBean.setBudgetAmountOri(0.0);
                    arrayListexpenseList.add(feeReimburseExpenseListBean);
                }
                //明细表2信息
                detailRsTwo.execute("select * from formtable_main_250_dt2   where mainid=" + id + "");//获取对应的明细表数据
                this.writeLog("明细表2信息" + "select * from formtable_main_250_dt2  where mainid=" + id + "");
                while (detailRsTwo.next()) {
                    String loanNumberId = detailRsTwo.getString("jkdh");//借款单号---loanNumber
                    //String pkOaHeadBrrow = detailRsTwo.getString("qqid");//明细表2---qqid(借款单requestid)(新增字段)
                    //String pkOaBodyTwo = detailRsTwo.getString("id");//名细表2id---pkOaBody
                    String loanDate = detailRsTwo.getString("jkrq");//借款日期---loanDate
                    String loanReason = detailRsTwo.getString("jksy");//借款事由---loanReason
                    String expenseTypeTwo = expenseTypeCode;//费用类型code
                    String balanceValue = detailRsTwo.getString("jkye");//借款余额---balance
                    String amountValue = detailRsTwo.getString("cjkje");//冲借款金额---amount
                    //String pkOaHeadTwo = detailRsTwo.getString("fhid");//借款单请求id
                    //String pkOaBodyTwo = detailRsTwo.getString("mxid");//借款单明细id

                    /*//处理千分位问题
                    double balanceV = Double.valueOf(balanceValue.replace(",", "")).doubleValue() <0? 0:Double.valueOf(balanceValue.replace(",", "")).doubleValue();
                    NumberFormat numberFormat5 = NumberFormat.getNumberInstance();
                    numberFormat5.setGroupingUsed(false); //设置了以后不会有千分位，如果不设置，默认是有的
                    String balance = numberFormat5.format(balanceV);
                    this.writeLog("balance--->"+balance);
                    //处理千分位问题
                    double amountV = Double.valueOf(amountValue.replace(",", "")).doubleValue() <0? 0:Double.valueOf(amountValue.replace(",", "")).doubleValue();
                    NumberFormat numberFormat6 = NumberFormat.getNumberInstance();
                    numberFormat6.setGroupingUsed(false); //设置了以后不会有千分位，如果不设置，默认是有的
                    String amount = numberFormat6.format(amountV);
                    this.writeLog("amount--->"+amount);
                    //查询报销单
                    RecordSet recordSetRiemForm = new RecordSet();
                    String sqlRiem = "select jkdh,fhid,mxid from uf_bxkjjkg where id=" + loanNumberId;
                    recordSetRiemForm.execute(sqlRiem);
                    recordSetRiemForm.next();
                    String loanNumber = recordSetRiemForm.getString("jkdh");
                    String fhid = recordSetRiemForm.getString("fhid");//借款单requestid
                    String mxid = recordSetRiemForm.getString("mxid");//借款单明细id
                    this.writeLog("流程单号jkdh---->" + loanNumber);
                    this.writeLog("借款单requestid---->" + fhid);
                    this.writeLog("借款单明细id----->" + mxid);
                    this.writeLog("单号--->"+loanNumber);*/

                    feeReimburseRevLoanBean feeReimburseRevLoanBean = new feeReimburseRevLoanBean();
                   /* feeReimburseRevLoanBean.setPkOaHead(fhid);
                    feeReimburseRevLoanBean.setPkOaBody(mxid);
                    feeReimburseRevLoanBean.setLoanNumber(loanNumber);
                    feeReimburseRevLoanBean.setLoanDate(loanDate);
                    feeReimburseRevLoanBean.setLoanReason(loanReason);
                    feeReimburseRevLoanBean.setExpenseType(expenseTypeTwo);
                    feeReimburseRevLoanBean.setBalance(balance);
                    feeReimburseRevLoanBean.setAmount(amount);
                    arrayListRevLoan.add(feeReimburseRevLoanBean);*/
                }

                feeReimburseRecListBean feeReimburseRecListBean = new feeReimburseRecListBean();
                //feeReimburseRecListBean.setPayEntryType(payEntryType);
                feeReimburseRecListBean.setRecPerson(recPerson);
                feeReimburseRecListBean.setRecCity(recCity);
                feeReimburseRecListBean.setRecBankAccount(recBankAccount);
                feeReimburseRecListBean.setBank(bank);
                feeReimburseRecListBean.setRecAmt(recAmt);
                arrayListRecList.add(feeReimburseRecListBean);
                //拼接json
                Gson gson = new Gson();
                Map map = new TreeMap<String, Object>();
                map.put("pkOaHead", pkOaHead);
                map.put("number", number);
                map.put("appDate", appDate);
                map.put("applier", applierCode);
                map.put("payOrg", payOrg);
                //map.put("payDept",payDept );
                //map.put("payType", payType);
                map.put("describ", describ);
                map.put("attendCount", attendCount);
                map.put("auditor", auditorCode);
                map.put("auditTime", auditTime);
                map.put("creator", applierCode);
                map.put("originatTime", originatTime);
                //流程数据库表单名称
                map.put("oaTabBaseName", tableName);
                map.put("oaBizType", oaBizType);
                map.put("brrowAmt", brrowAmt);//原借款金额
                //添加expenseList信息
                map.put("expenseList", arrayListexpenseList);
                //添加recList信息
                map.put("recList", arrayListRecList);
                //添加revLoan信息
                map.put("revLoan", arrayListRevLoan);
                String json = gson.toJson(map);
                this.writeLog("请求id："+requestid+"，请求参数---->" + json);
                //调用费用报销接口
                OaReimService_ServiceLocator oRSL = new OaReimService_ServiceLocator();
                OaReimService_PortType oRSIP = oRSL.getOaReimServiceImplPort();
                s = oRSIP.receiveOaReim(json);
                this.writeLog("请求id："+requestid+"，金蝶返回值------>" + s);
                JsonObject asJsonObject = new JsonParser().parse(s).getAsJsonObject();//获取jsonObject里的具体对象属性
                String c = asJsonObject.get("c").getAsString();//获取staff里的对象
                String msg = asJsonObject.get("m").getAsString();//获取staff里的对象

                if ("1020000".equals(c)) {
                    this.writeLog("请求id："+requestid+"，调用成功");
                    result = SUCCESS;
                    tsjg = "0";
                } else {
                    this.writeLog("请求id："+requestid+"，错误信息详情" + msg);
                    //控制流程流转，增加以下两行，流程不会向下流转，表单上显示返回的自定义错误信息
                    request.getRequestManager().setMessagecontent("金蝶数据保存出错,请联系相关人员处理，错误信息详情："+msg);
                    request.getRequestManager().setMessageid("11111"+requestid+"55555");
                    result = Action.FAILURE_AND_CONTINUE;
                    tsjg = "1";
                }
            } catch (Exception e) {
                this.writeLog("请求id："+requestid+"，错误："+e);
                //控制流程流转，增加以下两行，流程不会向下流转，表单上显示返回的自定义错误信息
                request.getRequestManager().setMessagecontent("代码异常。"+"错误信息详情：" + e);
                request.getRequestManager().setMessageid("11111"+requestid+"55555");
                result = Action.FAILURE_AND_CONTINUE;
                tsjg = "2";
            }
            try {
                con.setStatementSql(updateTsjgSql);
                con.setString(1, tsjg);
                con.setString(2, requestid);
                con.executeUpdate();
                con.close();
            } catch (Exception e) {
                this.writeLog("修改推送结果异常，请求id："+requestid+"，错误："+e);
            }
        }
        this.writeLog("请求id："+requestid+"，推送结果："+tsjg+",流程执行结果："+result);
        this.writeLog("currentMoneyReimburseForHeadquarters 现金报销单（总部/媒体）--请求id："+requestid+"--end");
        return result;
    }
}
