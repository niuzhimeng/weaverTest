package com.weavernorth.dadiyingyuan.feeOutForm.feiyongzhichudan;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.weavernorth.dadiyingyuan.feeOutForm.feeOutBean.feeOutExpenseList;
import com.weavernorth.dadiyingyuan.feeOutForm.feeOutClient.OaExpendService_PortType;
import com.weavernorth.dadiyingyuan.feeOutForm.feeOutClient.OaExpendService_ServiceLocator;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @program: feeOutForPurchasingCenterPaymentRequest
 * @description: 采购中心付款申请单
 * @author: chchq
 * @create: 2019/6/21
 */
public class feeOutForPurchasingCenterPaymentRequest extends BaseAction {
    @Override
    public String execute(RequestInfo request) {

        //1、获取流程表单数据
        String requestid = request.getRequestid();//获取流程请求id
        String tableName = getPropValue("workFlowTableName", "feeOutForPurchasing");
        String workflowid = request.getWorkflowid();//获取流程id
        String operator = request.getRequestManager().getSrc();//获取流程状态
        String requestname = request.getRequestManager().getRequestname();//请求标题
        String lastoperator = request.getLastoperator();//获取当前节点操作者
        //获取当前时间
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String format = df.format(day);
        RecordSet rs = new RecordSet();//主表信息
        RecordSet detailRs = new RecordSet();//明细表1信息

        List arrayFeeOutExpenseList = new ArrayList();
        String s = "";

        //方法执行结果
        String result = "";
        //推送结果
        String tsjg = "";
        //修改推送结果sql
        String updateTsjgSql = "update " + tableName + " SET tsjg = ? where requestid = ?  ";
        ConnStatement con = new ConnStatement();
        this.writeLog("feeOutForPurchasingCenterPaymentRequest 采购中心付款申请单--请求id：" + requestid + "--start");

        if ("submit".equals(operator)) {
            try {
                //获取主表信息
                rs.execute("select * from " + tableName + " where requestId='" + requestid + "'");//获取流程主表数据
                this.writeLog("查询主表单获取数据-----" + "select * from " + tableName + " where requestId='" + requestid + "'");
                rs.next();
                String pkOaHead = requestid;//requestid---pkOaHead
                String number = rs.getString("dh");//单号---number
                this.writeLog("单号：" + number);
                String applier = rs.getString("sqr");//报销人---applier
                String appDate = rs.getString("sqrq");//申请日期---appDate
                //String payDept = rs.getString("fyzfcode");//费用支付部门---payDept
                String payOrgId = rs.getString("fyzfgsXY");//费用支付公司---payOrg
                //String payType = rs.getString("zffs");//支付方式---payType
                String describ = rs.getString("sy");//	事由---describ
                String recTypeId = rs.getString("skrlx");//收款人类型---recType

                String recSuplierId = rs.getString("gysmc");//	供应商code---recSuplier
                String recBank = rs.getString("khyh");//	开户银行---recBank
                String recBankAccountId = rs.getString("gysyxzh");//供应商帐号---recBankAccount

                String retailCusId = rs.getString("khmc");//	客户code---retailCus
                String retailBank = recBank;
                String retailBankAccountId = rs.getString("khyxzh");//客户账号---retailBankAccount

                String auditor = lastoperator;//lastoperator---auditor
                String auditTime = format;//format---auditTime
                String creator = rs.getString("sqr");//申请人---creator
                String originatTime = rs.getString("sqrq");//申请日期---originatTime
                //String contractNo=rs.getString("htbh");//合同编号---contractNo
                String contractNo = "null".equals(rs.getString("htbh")) || rs.getString("htbh").trim().length() == 0 ? "" : rs.getString("htbh");

                //String contractName=rs.getString("htmc");//合同名称---contractName
                String contractName = "null".equals(rs.getString("htmc")) || rs.getString("htmc").trim().length() == 0 ? "" : rs.getString("htmc");

                String contractType = "";//合同类型

                //String contractAmtValue=rs.getString("htje");//合同金额---contractAmt
                String contractAmtValue = "null".equals(rs.getString("htje")) || rs.getString("htje").trim().length() == 0 ? "" : rs.getString("htje");

                //String paiedAmtValue=rs.getString("zqljyfje");//之前累计已付金额 (不含本次)---paiedAmt
                String paiedAmtValue = "null".equals(rs.getString("zqljyfje")) || rs.getString("zqljyfje").trim().length() == 0 ? "" : rs.getString("zqljyfje");

                //String unPayAmtValue=rs.getString("bchswfkje");//本次后尚未付款金额---unPayAmt
                String unPayAmtValue = "null".equals(rs.getString("bchswfkje")) || rs.getString("bchswfkje").trim().length() == 0 ? "" : rs.getString("bchswfkje");

                int attendCount = rs.getInt("fjs");//fjs	附件数
                String kDTextFieldValue = rs.getString("kxjd");//款项阶段---kDTextField
                String kDNumberTextFieldValue = rs.getString("zbj1");//质保金---kDNumberTextField
                //String tzffscode = rs.getString("zffscode");//xinzeng
                String oaBizType = "采购中心付款申请单";//requestname---oaBizType

                //去掉金额千分位
                double contractAmt = 0.0;
                double paiedAmt = 0.0;
                double unPayAmt = 0.0;
                double kDNumberTextField = 0.0;
                try {
                    contractAmt = new DecimalFormat().parse(contractAmtValue).doubleValue() < 0 ? 0 : new DecimalFormat().parse(contractAmtValue).doubleValue();
                    paiedAmt = new DecimalFormat().parse(paiedAmtValue).doubleValue() < 0 ? 0 : new DecimalFormat().parse(paiedAmtValue).doubleValue();
                    unPayAmt = new DecimalFormat().parse(unPayAmtValue).doubleValue() < 0 ? 0 : new DecimalFormat().parse(unPayAmtValue).doubleValue();
                    kDNumberTextField = new DecimalFormat().parse(kDNumberTextFieldValue).doubleValue() < 0 ? 0 : new DecimalFormat().parse(kDNumberTextFieldValue).doubleValue();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                this.writeLog("hetongjine---->" + contractAmt);
                this.writeLog("之前累计已付金额 (不含本次)---->" + paiedAmt);
                this.writeLog("本次后尚未付款金额---->" + unPayAmt);

                //款项阶段中文
                String kDTextField = "";
                if ("null".equals(kDTextFieldValue) || kDTextFieldValue.trim().length() == 0) {
                    kDTextField = "";
                } else {
                    if (kDTextFieldValue.equals("0")) {
                        kDTextField = "预付款";
                    } else if (kDTextFieldValue.equals("1")) {
                        kDTextField = "结算款";
                    } else if (kDTextFieldValue.equals("2")) {
                        kDTextField = "质保金";
                    }
                }

                //新增字段
                //String jiaona = rs.getString("jnd");//jnd---缴纳地N---jiaona
                String zaijianId = rs.getString("yy");//影院N---yy---zaijian
                //String yajin = rs.getString("yjxz");//押金性质N---yy---yajin
                String gongcheng = rs.getString("gcxm");//工程项目N---gcxm---gongcheng
                //String feiyong = rs.getString("fyxm");//工程项目N---fyxm---feiyong
                String feiyong = "null".equals(rs.getString("fyxm")) || rs.getString("fyxm").trim().length() == 0 ? "" : rs.getString("fyxm");

                String zhifu = rs.getString("zfklb");//支付款类别N---zfklb---zhifu
                String id = rs.getString("id");//id

                //在建影院
                String zaijian = "";
                if ("null".equals(zaijianId) || zaijianId.trim().length() == 0) {
                    zaijian = "";//没填值则为""
                } else {
                    //增加影院--
                    RecordSet zaijianYY = new RecordSet();
                    String zJYYSql = "select longnumber from uf_hsxmlbmxb where id=" + zaijianId;
                    zaijianYY.execute(zJYYSql);
                    zaijianYY.next();
                    zaijian = zaijianYY.getString("longnumber");
                    this.writeLog("影院-------->" + zaijian);
                }

                /*//增加支付款类别
                RecordSet feeType = new RecordSet();
                String feeTypeSql="select longnumber from uf_hsxmlbmxb where id='"+zhifu+"'";
                feeType.execute(feeTypeSql);
                feeType.next();
                String longnumber = feeType.getString("longnumber");
                this.writeLog("支付款类别--->"+longnumber);*/
                //增加gongchengxiangmu
                String gongchengValue = "";
                if ("null".equals(gongcheng) || gongcheng.trim().length() == 0) {
                    gongchengValue = "";//没填值则为""
                } else {
                    RecordSet GcType = new RecordSet();
                    String Gc = "select longnumber from uf_hsxmlbmxb where id='" + gongcheng + "'";
                    GcType.execute(Gc);
                    GcType.next();
                    gongchengValue = GcType.getString("longnumber");
                    this.writeLog("gongchengValue--->" + gongchengValue);
                }

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

                //收款人类型
                String recType = "";
                if (recTypeId.equals("0")) {
                    recType = "供应商";
                } else {
                    recType = "客户";
                }
                String recSuplier = "";
                String recBankAccount = "";
                String retailCus = "";
                String retailBankAccount = "";
                if (recTypeId.equals("0")) {
                    //查询供应商code和账号
                    RecordSet recCode = new RecordSet();
                    String recSql = "select code from uf_gysb where id=" + recSuplierId;
                    this.writeLog("供应商查询--->" + "select code from uf_gysb where id=" + recSuplierId);
                    recCode.execute(recSql);
                    recCode.next();
                    recSuplier = recCode.getString("code");
                    this.writeLog("供应商code--->" + recSuplier);
                    //供应商账号
                    if ("null".equals(recBankAccountId) || recBankAccountId.trim().length() == 0) {
                        recBankAccount = "";
                        retailBank = "";
                    } else {
                        //供应商账号
                        RecordSet recBankAccountCode = new RecordSet();
                        String recBankSql = "select account from uf_gysyhb where id=" + recBankAccountId;
                        this.writeLog("供应商账号---->" + "select account from uf_gysyhb where id=" + recBankAccountId);
                        recBankAccountCode.execute(recBankSql);
                        recBankAccountCode.next();
                        recBankAccount = recBankAccountCode.getString("account");
                        retailBank = "";
                    }
                } else {
                    //查询客户银行和账号
                    RecordSet retailCode = new RecordSet();
                    String retailql = "select code from uf_khb where id=" + retailCusId;
                    this.writeLog("客户账号----->" + "select code from uf_khb where id=" + retailCusId);
                    retailCode.execute(retailql);
                    retailCode.next();
                    retailCus = retailCode.getString("code");
                    this.writeLog("客户code--->" + retailCus);
                    //客户账号
                    if ("null".equals(retailBankAccountId) || retailBankAccountId.trim().length() == 0) {
                        retailBankAccount = "";
                        recBank = "";
                    } else {
                        //客户账号
                        RecordSet retailBankAccountCode = new RecordSet();
                        String retailBankAccountSql = "select account from uf_khyhb where id=" + retailBankAccountId;
                        this.writeLog("客户账号---->" + "select account from uf_khyhb where id=" + retailBankAccountId);
                        retailBankAccountCode.execute(retailBankAccountSql);
                        retailBankAccountCode.next();
                        retailBankAccount = retailBankAccountCode.getString("account");
                        this.writeLog("客户账号---->" + retailBankAccount);
                        recBank = "";
                    }
                }

                //查询费用支付公司
                RecordSet feeOutOrg = new RecordSet();
                this.writeLog("查询费用支付公司--->" + "select code from uf_zzb where id=" + payOrgId);
                String queryFeeOutOrg = "select code from uf_zzb where id=" + payOrgId;
                feeOutOrg.execute(queryFeeOutOrg);
                feeOutOrg.next();
                String payOrg = feeOutOrg.getString("code");
                this.writeLog("费用支付公司---" + payOrg);


                //费用类型
                String fylx = rs.getString("fylxXY");//费用类型---expenseType

                detailRs.execute("select * from formtable_main_237_dt1  where mainid=" + id + "");//获取对应的明细表数据
                this.writeLog("明细表1信息" + "select * from formtable_main_237_dt1  where mainid=" + id + "");
                while (detailRs.next()) {

                    String accItem = detailRs.getString("flfsxm");//xinzeng
                    String expenseTypeId = fylx;//费用类型---expenseType
                    String purpose = detailRs.getString("fysm");//费用说明---purpose
                    String billDate = detailRs.getString("ywfsrq");//业务发生日期---billDate
                    Double exchangeRate = 1.0;

                    String localAmtNoRateValue = detailRs.getString("bhsje");//不含税金额---localAmtNoRate
                    this.writeLog("不含税金额55--->" + localAmtNoRateValue);
                    String rateValue = detailRs.getString("sl");//税率---rate
                    String rateAmtValue = detailRs.getString("se");//税额---rateAmt
                    String localAmtValue = detailRs.getString("hjje");//合计金额---localAmt
                    this.writeLog("合计金额55--->" + localAmtValue);
                    String amtApprovedNoRateLocalValue = detailRs.getString("hdbhsje");//核定不含税金额---amtApprovedNoRateLocal
                    this.writeLog("核定不含税金额55--->" + amtApprovedNoRateLocalValue);
                    String approvedTaxAmtValue = detailRs.getString("hdse");//核定税额---approvedTaxAmt
                    String amtApprovedLocalValue = detailRs.getString("hdje");//核定金额---amtApprovedLocal
                    this.writeLog("核定金额55--->" + amtApprovedLocalValue);

                    String payEntryDeptId = detailRs.getString("fycdbmXY");//费用承担部门---费用支付部门
                    String payEntryOrgId = detailRs.getString("fycdgsXY");//费用承担公司---费用支付公司
                    String pkOaBody = detailRs.getString("id");//(明细表2id)---pkOaBody
                    //新增
                    String dishuiId = detailRs.getString("sfdk");//是否抵扣N---sfdk---dishui
                    String shuifeiId = detailRs.getString("km");//科目档案N---km---shuifei
                    //2019.4.4新增产品
                    //cpda--产品档案
                    String productId = detailRs.getString("cpda");//产品档案---product
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

                    feeOutExpenseList feeOutExpenseList = new feeOutExpenseList();
                    // Double localAmtNoRate=0.0 ;
                   /* Double rate =0.0;
                    Double rateAmt=0.0 ;
                    Double localAmt=0.0 ;
                    Double amtApprovedNoRateLocal=0.0;
                    Double approvedTaxAmt=0.0;
                    Double amtApprovedLocal=0.0;*/

                    //解决金额千分位问题
                    double localAmtNoRateV = Double.valueOf(localAmtNoRateValue.replace(",", "")).doubleValue() < 0 ? 0 : Double.valueOf(localAmtNoRateValue.replace(",", "")).doubleValue();
                    NumberFormat numberFormat2 = NumberFormat.getNumberInstance();
                    numberFormat2.setGroupingUsed(false); //设置了以后不会有千分位，如果不设置，默认是有的
                    String localAmtNoRate = numberFormat2.format(localAmtNoRateV);
                    localAmtNoRate = setScale2(localAmtNoRate); // 保留两位小数

                    //解决金额千分位问题
                    double rateV = Double.valueOf(rateValue.replace(",", "")).doubleValue() < 0 ? 0 : Double.valueOf(rateValue.replace(",", "")).doubleValue();
                    NumberFormat numberFormat3 = NumberFormat.getNumberInstance();
                    numberFormat3.setGroupingUsed(false); //设置了以后不会有千分位，如果不设置，默认是有的
                    String rate = numberFormat3.format(rateV);

                    //解决金额千分位问题
                    double rateAmtV = Double.valueOf(rateAmtValue.replace(",", "")).doubleValue() < 0 ? 0 : Double.valueOf(rateAmtValue.replace(",", "")).doubleValue();
                    NumberFormat numberFormat4 = NumberFormat.getNumberInstance();
                    numberFormat4.setGroupingUsed(false); //设置了以后不会有千分位，如果不设置，默认是有的
                    String rateAmt = numberFormat4.format(rateAmtV);

                    //解决金额千分位问题
                    double localAmtV = Double.valueOf(localAmtValue.replace(",", "")).doubleValue() < 0 ? 0 : Double.valueOf(localAmtValue.replace(",", "")).doubleValue();
                    NumberFormat numberFormat5 = NumberFormat.getNumberInstance();
                    numberFormat5.setGroupingUsed(false); //设置了以后不会有千分位，如果不设置，默认是有的
                    String localAmt = numberFormat5.format(localAmtV);
                    localAmt = setScale2(localAmt); // 保留两位小数

                    //解决金额千分位问题
                    double amtApprovedNoRateLocalV = Double.valueOf(amtApprovedNoRateLocalValue.replace(",", "")).doubleValue() < 0 ? 0 : Double.valueOf(amtApprovedNoRateLocalValue.replace(",", "")).doubleValue();
                    NumberFormat numberFormat6 = NumberFormat.getNumberInstance();
                    numberFormat6.setGroupingUsed(false); //设置了以后不会有千分位，如果不设置，默认是有的
                    String amtApprovedNoRateLocal = numberFormat6.format(amtApprovedNoRateLocalV);
                    amtApprovedNoRateLocal = setScale2(amtApprovedNoRateLocal); // 保留两位小数

                    //解决金额千分位问题
                    double approvedTaxAmtV = Double.valueOf(approvedTaxAmtValue.replace(",", "")).doubleValue() < 0 ? 0 : Double.valueOf(approvedTaxAmtValue.replace(",", "")).doubleValue();
                    NumberFormat numberFormat7 = NumberFormat.getNumberInstance();
                    numberFormat7.setGroupingUsed(false); //设置了以后不会有千分位，如果不设置，默认是有的
                    String approvedTaxAmt = numberFormat7.format(approvedTaxAmtV);

                    //解决金额千分位问题
                    double amtApprovedLocalV = Double.valueOf(amtApprovedLocalValue.replace(",", "")).doubleValue() < 0 ? 0 : Double.valueOf(amtApprovedLocalValue.replace(",", "")).doubleValue();
                    NumberFormat numberFormat8 = NumberFormat.getNumberInstance();
                    numberFormat8.setGroupingUsed(false); //设置了以后不会有千分位，如果不设置，默认是有的
                    String amtApprovedLocal = numberFormat8.format(amtApprovedLocalV);
                    amtApprovedLocal = setScale2(amtApprovedLocal); // 保留两位小数

                    //是否抵税
                    boolean dishui;
                    if (dishuiId.equals("0")) {
                        dishui = true;//是
                    } else {
                        dishui = false;//否
                    }
                    //查询科目档案
                    String shuifei = "";
                    if ("null".equals(shuifeiId) || shuifeiId.trim().length() == 0) {
                        shuifei = "";//没填值则为""
                    } else {
                        RecordSet Km = new RecordSet();
                        String KmSql = "select code from uf_kmb where id=" + shuifeiId;
                        this.writeLog("科目code----->" + KmSql);
                        Km.execute(KmSql);
                        Km.next();
                        shuifei = Km.getString("code");
                        this.writeLog("科目code----->" + shuifei);
                    }

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

                    /*查询费用类型编码*/
                    RecordSet feeTypeCode = new RecordSet();
                    this.writeLog("查询费用类型" + "select code from uf_fylxb where id=" + expenseTypeId);
                    String queryFeeType = "select code from uf_fylxb where id=" + expenseTypeId;
                    feeTypeCode.execute(queryFeeType);
                    feeTypeCode.next();
                    String expenseTypeCode = feeTypeCode.getString("code");//费用类型code
                    this.writeLog("费用类型" + expenseTypeCode);


                    feeOutExpenseList.setRate(rate);
                    feeOutExpenseList.setPkOaBody(pkOaBody);
                    feeOutExpenseList.setExpenseType(expenseTypeCode);
                    feeOutExpenseList.setPurpose(purpose);
                    feeOutExpenseList.setBillDate(billDate);
                    feeOutExpenseList.setExchangeRate(exchangeRate);
                    feeOutExpenseList.setLocalAmtNoRate(localAmtNoRate);
                    feeOutExpenseList.setRateAmt(rateAmt);
                    feeOutExpenseList.setLocalAmt(localAmt);
                    feeOutExpenseList.setAmtApprovedNoRateLocal(amtApprovedNoRateLocal);
                    feeOutExpenseList.setApprovedTaxAmt(approvedTaxAmt);
                    feeOutExpenseList.setAmtApprovedLocal(amtApprovedLocal);
                    feeOutExpenseList.setPayEntryDept(payEntryDept);
                    feeOutExpenseList.setPayEntryOrg(payEntryOrg);

                    feeOutExpenseList.setAccItem(accItem);
                    //新增

                    //新增字段
                    feeOutExpenseList.setJiaona("");
                    feeOutExpenseList.setDishui(dishui);//选择框
                    feeOutExpenseList.setShuifei(shuifei);
                    feeOutExpenseList.setZaijian(zaijian);
                    feeOutExpenseList.setYajin("");
                    feeOutExpenseList.setGongcheng(gongchengValue);
                    feeOutExpenseList.setFeiyong(feiyong);
                    feeOutExpenseList.setZhifu(zhifu);
                    //2019.4.4
                    feeOutExpenseList.setProduct(longnumberCp);
                    arrayFeeOutExpenseList.add(feeOutExpenseList);

                }

                //主字段
                //基础信息
                Gson gson = new Gson();
                Map<String, Object> map = new TreeMap<String, Object>();
                map.put("number", number);
                map.put("pkOaHead", requestid);
                map.put("applier", applierCode);
                map.put("appDate", appDate);
                //map.put("payDept",  payDept);
                map.put("payOrg", payOrg);
                //map.put("payType",  tzffscode);
                map.put("describ", describ);
                map.put("recType", recType);
                map.put("recSuplier", recSuplier);
                map.put("recBank", recBank);
                map.put("recBankAccount", recBankAccount);
                map.put("retailCus", retailCus);
                map.put("retailBank", retailBank);
                map.put("retailBankAccount", retailBankAccount);
                map.put("auditor", auditorCode);
                map.put("auditTime", auditTime);
                map.put("creator", applierCode);
                map.put("originatTime", originatTime);
                map.put("contractNo", contractNo);
                map.put("contractName", contractName);
                map.put("contractType", contractType);
                map.put("contractAmt", contractAmt);
                map.put("paiedAmt", paiedAmt);
                map.put("unPayAmt", unPayAmt);
                map.put("attendCount", attendCount);
                map.put("oaBizType", oaBizType);
                map.put("kxjd", kDTextField);//新增字段
                map.put("zbj", kDNumberTextField);//新增字段
                //流程数据库表单名称
                map.put("oaTabBaseName", tableName);
                map.put("expenseList", arrayFeeOutExpenseList);
                String json = gson.toJson(map);
                this.writeLog("请求id：" + requestid + "，请求参数---->" + json);
                OaExpendService_ServiceLocator oaExpendService_serviceLocator = new OaExpendService_ServiceLocator();
                OaExpendService_PortType oaExpendServiceImplPort = oaExpendService_serviceLocator.getOaExpendServiceImplPort();
                s = oaExpendServiceImplPort.receiveOaExpend(json);
                this.writeLog("请求id：" + requestid + "，金蝶返回值---->" + s);
                JsonObject asJsonObject = new JsonParser().parse(s).getAsJsonObject();//获取jsonObject里的具体对象属性
                String c = asJsonObject.get("c").getAsString();//获取staff里的对象
                String msg = asJsonObject.get("m").getAsString();//获取staff里的对象
                if ("1020000".equals(c)) {
                    this.writeLog("请求id：" + requestid + "，调用成功");
                    result = Action.SUCCESS;
                    tsjg = "0";
                } else if ("1010001".equals(c) && "".equals(msg)) {
                    this.writeLog("请求id：" + requestid + "，金蝶数据保存出错,请联系相关人员处理：" + msg);
                    request.getRequestManager().setMessagecontent("金蝶数据保存出错,请联系相关人员处理，错误信息详情：" + msg);
                    request.getRequestManager().setMessageid("11111" + requestid + "55555");
                    result = Action.FAILURE_AND_CONTINUE;
                    tsjg = "1";
                } else {
                    this.writeLog("请求id：" + requestid + "，错误信息详情：" + msg);
                    //控制流程流转，增加以下两行，流程不会向下流转，表单上显示返回的自定义错误信息
                    request.getRequestManager().setMessagecontent("金蝶数据保存出错,请联系相关人员处理，错误信息详情：" + msg);
                    request.getRequestManager().setMessageid("11111" + requestid + "55555");
                    result = Action.FAILURE_AND_CONTINUE;
                    tsjg = "1";
                }
            } catch (Exception e) {
                this.writeLog("请求id：" + requestid + "，错误：" + e);
                //控制流程流转，增加以下两行，流程不会向下流转，表单上显示返回的自定义错误信息
                request.getRequestManager().setMessagecontent("代码异常。" + "错误信息详情：" + e);
                request.getRequestManager().setMessageid("11111" + requestid + "55555");
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
                this.writeLog("修改推送结果异常，请求id：" + requestid + "，错误：" + e);
            }
        }
        this.writeLog("请求id：" + requestid + "，推送结果：" + tsjg + ",流程执行结果：" + result);
        this.writeLog("feeOutForPurchasingCenterPaymentRequest 采购中心付款申请单--请求id：" + requestid + "--end");
        return result;
    }


    /**
     * 保留两位小数
     */
    private String setScale2(String before) {
        String returnSrr = "0";
        if (null == before || "".equals(before)) {
            return "0";
        }
        try {
            BigDecimal bigOne = new BigDecimal(before);
            returnSrr = bigOne.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        } catch (Exception e) {
            new BaseBean().writeLog("setScale2保留两位小数异常，接到参数： " + before);
        }

        return returnSrr;
    }

}
