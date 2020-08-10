package com.weavernorth.dadiyingyuan.travelExpenseReimBursement.chailvfeibaoxiaodan;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.weavernorth.dadiyingyuan.travelExpenseReimBursement.travelExpenseReimBean.travelExpenseListBean;
import com.weavernorth.dadiyingyuan.travelExpenseReimBursement.travelExpenseReimBean.travelRecListBean;
import com.weavernorth.dadiyingyuan.travelExpenseReimBursement.travelExpenseReimBean.travelRevLoanBean;
import com.weavernorth.dadiyingyuan.travelExpenseReimBursement.travelExpenseReimBursementClient.OaTravelService_PortType;
import com.weavernorth.dadiyingyuan.travelExpenseReimBursement.travelExpenseReimBursementClient.OaTravelService_ServiceLocator;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
/**
*@program: travelExpenseReimFormFilm
*@description: 差旅费报销单 (影院/汉堡王)
*@author: chchq
*@updateDate: 2019/6/20
*/
public class travelExpenseReimFormFilm extends BaseAction {

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
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String format = df.format(day);
        RecordSet rs = new RecordSet();//获取主表信息
        RecordSet detailRs = new RecordSet();//明细表1信息
        RecordSet detailRsTwo = new RecordSet();//明细表2信息

        //新建expenseList集合
        List arrayListexpenseList = new ArrayList();
        //新建recList集合
        List arrayListRecList = new ArrayList();
        //新建revLoan集合
        List arrayListRevLoan = new ArrayList();
        String s = "";

        //方法执行结果
        String result = "";
        //推送结果
        String tsjg = "";
        //修改推送结果sql
        String updateTsjgSql = "update "+tableName+" SET tsjg = ? where requestid = ?  ";
        ConnStatement con = new ConnStatement();

        this.writeLog("travelExpenseReimFormFilm 差旅费报销单 (影院/汉堡王)--请求id："+requestid+"--start");
        if ("submit".equals(operator)) {
            try {
                rs.execute("select * from " + tableName + " where requestId='" + requestid + "'");//获取流程主表数据
                this.writeLog("查询主表单获取数据-----" + "select * from " + tableName + " where requestId='" + requestid + "'");
                rs.next();
                //获取主表数据
                String pkOaHead = requestid;//requestid---pkOaHead(差旅报销单requestid)
                String number = rs.getString("dh");//单号dh---number
                this.writeLog("单号："+number);
                String applier = rs.getString("bxr");//申请人sqr---applier
                String appDate = rs.getString("sqrq");//申请日期sqrq---appDate
                //String payDept = rs.getString("fycdbm");//费用承担部门fycdbm---payDept
                String payOrgId = rs.getString("fyzfgs");//费用支付公司fycdgs---payOrg
                //String payType = rs.getString("zffs");//支付方式zffs---payType
                String describ = rs.getString("sy");//	事由---describ
                int attendCount = rs.getInt("fjs");//附件数---attendCount
                String auditor = lastoperator;
                String auditTime = format;
                String creator = rs.getString("sqr");//申请人sqr---creator
                String originatTime = rs.getString("sqrq");//申请日期sqrq---originatTime
                String oaBizType = "差旅费报销单(影院/汉堡王)";

                //明细表字段--expense
                String amtApprovedLocalValue = rs.getString("hdjezh");//核定金额总和hdjezh---amtApprovedLocal
                String amtApprovedNoRateLocalValue = rs.getString("sqjrzh");//申请金额总和sqjrzh---amtApprovedNoRateLocal
                //String approvedTaxAmt="";(不传)
                //String exchangeRate="1";(固定值1)
                String expenseTypeId = rs.getString("fylx");//费用类型fylx---expenseType
                //String  localAmt="";(不传)
                //String localAmtNoRate="";(不传)
                //Double otherAmt = rs.getDouble("sqjeqt");//申请金额(其他)sqjeqt---otherAmt(联调使用字段)
                String otherAmtValue = rs.getString("hdjeqt");//hdjeqt 核定金额(其他)
                String payEntryDeptId = rs.getString("fycdbm");//费用承担部门---费用支付部门
                String payEntryOrgId = rs.getString("fycdgs");//费用承担公司---费用支付公司
                //String purpose="";(不传)
                String stayAmtValue = rs.getString("hdjezsf");//核定金额(住宿费)hdjezsf---stayAmt
                //String taxAmt="0";
                String transfeeValue = rs.getString("hdjectjtf");//核定金额(长途交通费)hdjectjtf---transfee
                String transferValue = rs.getString("hdjesnjtf");//核定金额(市内交通费)hdjesnjtf---transfer
                //新增字段
                String purpose = rs.getString("yt");//purpose--用途
                String zhifu = rs.getString("zfklb");//支付款类别N---zhifu
                //明细表--rec
                String bank = rs.getString("khyh");//开户银行khyh---bank
                //String payEntryType = rs.getString("zffs");//支付方式zffs---payEntryType
                String recAmtValue = rs.getString("sfje");//核定金额总和sfje---recAmt
                String recBankAccount = rs.getString("yhzh");//银行账号yeah---recBankAccount
                String recPerson = rs.getString("skrmc");//收款人名称skrmc---recPerson
                String recCity = rs.getString("khhdq");//	开户行地区khhdq---recCity
                //2019.4.4原借款金额
                String brrowAmtValue = rs.getString("yjkje");//	原借款金额---brrowAmt
                //处理千分位问题
                double brrowAmtV = Double.valueOf(brrowAmtValue.replace(",", "")).doubleValue() < 0 ? 0 : Double.valueOf(brrowAmtValue.replace(",", "")).doubleValue();
                NumberFormat numberFormat20 = NumberFormat.getNumberInstance();
                numberFormat20.setGroupingUsed(false); //设置了以后不会有千分位，如果不设置，默认是有的
                String brrowAmt = numberFormat20.format(brrowAmtV);
                String id = rs.getString("id");//id

                //解决金额千分位问题
                double amtApprovedLocalV = Double.valueOf(amtApprovedLocalValue.replace(",", "")).doubleValue() < 0 ? 0 : Double.valueOf(amtApprovedLocalValue.replace(",", "")).doubleValue();
                NumberFormat numberFormat2 = NumberFormat.getNumberInstance();
                numberFormat2.setGroupingUsed(false); //设置了以后不会有千分位，如果不设置，默认是有的
                String amtApprovedLocal = numberFormat2.format(amtApprovedLocalV);

                double amtApprovedNoRateLocalV = Double.valueOf(amtApprovedNoRateLocalValue.replace(",", "")).doubleValue() < 0 ? 0 : Double.valueOf(amtApprovedNoRateLocalValue.replace(",", "")).doubleValue();
                NumberFormat numberFormat3 = NumberFormat.getNumberInstance();
                numberFormat3.setGroupingUsed(false); //设置了以后不会有千分位，如果不设置，默认是有的
                String amtApprovedNoRateLocal = numberFormat3.format(amtApprovedNoRateLocalV);

                double otherAmtV = Double.valueOf(otherAmtValue.replace(",", "")).doubleValue() < 0 ? 0 : Double.valueOf(otherAmtValue.replace(",", "")).doubleValue();
                NumberFormat numberFormat4 = NumberFormat.getNumberInstance();
                numberFormat4.setGroupingUsed(false); //设置了以后不会有千分位，如果不设置，默认是有的
                String otherAmt = numberFormat4.format(otherAmtV);

                double stayAmtV = Double.valueOf(stayAmtValue.replace(",", "")).doubleValue() < 0 ? 0 : Double.valueOf(stayAmtValue.replace(",", "")).doubleValue();
                NumberFormat numberFormat5 = NumberFormat.getNumberInstance();
                numberFormat5.setGroupingUsed(false); //设置了以后不会有千分位，如果不设置，默认是有的
                String stayAmt = numberFormat5.format(stayAmtV);

                double transfeeV = Double.valueOf(transfeeValue.replace(",", "")).doubleValue() < 0 ? 0 : Double.valueOf(transfeeValue.replace(",", "")).doubleValue();
                NumberFormat numberFormat6 = NumberFormat.getNumberInstance();
                numberFormat6.setGroupingUsed(false); //设置了以后不会有千分位，如果不设置，默认是有的
                String transfee = numberFormat6.format(transfeeV);

                double transferV = Double.valueOf(transferValue.replace(",", "")).doubleValue() < 0 ? 0 : Double.valueOf(transferValue.replace(",", "")).doubleValue();
                NumberFormat numberFormat7 = NumberFormat.getNumberInstance();
                numberFormat7.setGroupingUsed(false); //设置了以后不会有千分位，如果不设置，默认是有的
                String transfer = numberFormat7.format(transferV);

                double recAmtV = Double.valueOf(recAmtValue.replace(",", "")).doubleValue() < 0 ? 0 : Double.valueOf(recAmtValue.replace(",", "")).doubleValue();
                NumberFormat numberFormat8 = NumberFormat.getNumberInstance();
                numberFormat8.setGroupingUsed(false); //设置了以后不会有千分位，如果不设置，默认是有的
                String recAmt = numberFormat8.format(recAmtV);
                //获取申请人邮箱
                RecordSet rsEmail = new RecordSet();//主标信息
                String emailSql = "select email from HrmResource where id= " + applier + "";
                rsEmail.execute(emailSql);
                rsEmail.next();
                String email = rsEmail.getString("email");
                String emailBefore = email.substring(0, email.indexOf("@"));//截取@之前的字符串
                int length = emailBefore.length();
                String emailAfter = email.substring(length);
                this.writeLog("---------------llllll");
                String applierCode = "";
                if (emailAfter.equals("@dadimedia")) {
                    applierCode = emailBefore;
                } else {
                    applierCode = email;
                }
                this.writeLog("------->申请人" + applierCode);
                //获取当前操作人邮箱+
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
                this.writeLog("------->审核人" + auditorCode);

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

                /*查询费用类型编码*/
                RecordSet feeTypeCode = new RecordSet();
                this.writeLog("查询费用类型" + "select code from uf_fylxb where id=" + expenseTypeId);
                String queryFeeType = "select code from uf_fylxb where id=" + expenseTypeId;
                feeTypeCode.execute(queryFeeType);
                feeTypeCode.next();
                String expenseTypeCode = feeTypeCode.getString("code");//费用类型code

                Double amount = 0.0;
                detailRsTwo.execute("select * from formtable_main_240_dt2   where mainid=" + id + "");//获取对应的明细表数据
                this.writeLog("明细表2信息" + "select * from formtable_main_240_dt2   where mainid=" + id + "");
                //明细表2信息---RevLoan信息
                while (detailRsTwo.next()) {
                    //明细表2字段齐全
                    String loanNumberId = detailRsTwo.getString("lcdh");//流程单号lcdh---loanNumber
                    String loanDate = detailRsTwo.getString("jkrq");//借款日期---loanDate
                    String loanReason = detailRsTwo.getString("jksy");//借款事由---loanReason
                    String expenseTypeTwo = expenseTypeCode;//费用类型code
                    String balance = detailRsTwo.getString("jkye");//借款余额---balance
                    amount = detailRsTwo.getDouble("cjkje");//冲借款金额cjkje---amount
                    //String pkOaBodyTwo = detailRsTwo.getString("id");//名细表2id---pkOaBody
                    //String pkOaHeadBrrow = detailRsTwo.getString("qqid");//明细表2---qqid(借款单requestid)(新增字段)
                    //查询报销单
                    String loanNumber = "";
                    String fhid = "";
                    String mxid = "";

                    //暂时没有冲借款业务需求
                    /*if (loanNumberId != null || !"".equals(loanNumberId.trim())) {
                        RecordSet recordSetRiemForm = new RecordSet();
                        String sqlRiem = "select jkdh,fhid,mxid from uf_bxkjjkg where id=" + loanNumberId;
                        recordSetRiemForm.execute(sqlRiem);
                        recordSetRiemForm.next();
                        loanNumber = recordSetRiemForm.getString("jkdh");
                        fhid = recordSetRiemForm.getString("fhid");//借款单requestid
                        mxid = recordSetRiemForm.getString("mxid");//借款单明细id
                        this.writeLog("流程单号jkdh---->" + loanNumber);
                        this.writeLog("借款单requestid---->" + fhid);
                        this.writeLog("借款单明细id---->" + mxid);
                    }*/

                    travelRevLoanBean travelRevLoanBean = new travelRevLoanBean();

                    //暂时没有冲借款业务需求
                    //travelRevLoanBean.setAmount(amount);
                    //travelRevLoanBean.setLoanNumber(loanNumber);
                    //travelRevLoanBean.setPkOaBody(mxid);
                    //travelRevLoanBean.setPkOaHead(fhid);
                    //arrayListRevLoan.add(travelRevLoanBean);
                }

                //明细表1信息---ExpenseList信息
                detailRs.execute("select * from formtable_main_240_dt1   where mainid=" + id + "");//获取对应的明细表数据
                this.writeLog("明细表1信息" + "select * from formtable_main_240_dt1   where mainid=" + id + "");
                while (detailRs.next()) {
                    //明细表1字段齐全
                    String curDate = detailRs.getString("ccksrq");//出差开始日期ccksrq---curDate
                    String endDate = detailRs.getString("ccjsrq");//出差结束日期ccjsrq---endDate
                    String from = detailRs.getString("cfd");//出发地cfd---from
                    String pkOaBody = detailRs.getString("id");//pkOaBody---明细表1的id
                    String toArea = detailRs.getString("mdd");//目的地mdd---toArea
                    String vehicleValue = detailRs.getString("jtgj");//交通工具jtgj---vehicle
                    //新增
                    //String purpose=detailRs.getString("yt");
                    this.writeLog("交通工具--->" + vehicleValue);
                    String vehicle = "";
                    if (vehicleValue.equals("0")) {
                        vehicle = "飞机";
                    } else if (vehicleValue.equals("1")) {
                        vehicle = "火车";
                    } else if (vehicleValue.equals("2")) {
                        vehicle = "高铁";
                    } else if (vehicleValue.equals("3")) {
                        vehicle = "火车";
                    } else if (vehicleValue.equals("4")) {
                        vehicle = "轮船";
                    } else if (vehicleValue.equals("5")) {
                        vehicle = "汽车";
                    } else if (vehicleValue.equals("6")) {
                        vehicle = "其他";
                    }
                    travelExpenseListBean travelExpenseListBean = new travelExpenseListBean();
                    travelExpenseListBean.setExpenseType(expenseTypeCode);
                    travelExpenseListBean.setCurDate(curDate);
                    travelExpenseListBean.setEndDate(endDate);
                    travelExpenseListBean.setFrom(from);
                    travelExpenseListBean.setToArea(toArea);
                    travelExpenseListBean.setVehicle(vehicle);
                    travelExpenseListBean.setExchangeRate(1.0);
                    travelExpenseListBean.setTransfee(transfee);
                    travelExpenseListBean.setTransfer(transfer);
                    travelExpenseListBean.setTaxAmt("0.0");
                    travelExpenseListBean.setStayAmt(stayAmt);
                    travelExpenseListBean.setOtherAmt(otherAmt);
                    travelExpenseListBean.setLocalAmtNoRate("0.0");
                    travelExpenseListBean.setLocalAmt(recAmt);//recAmt--amtApprovedNoRateLocal
                    travelExpenseListBean.setApprovedTaxAmt("0.0");
                    travelExpenseListBean.setAmtApprovedNoRateLocal(amtApprovedNoRateLocal);
                    travelExpenseListBean.setAmtApprovedLocal(amtApprovedLocal);
                    travelExpenseListBean.setPayEntryDept(payEntryDept);
                    travelExpenseListBean.setPayEntryOrg(payEntryOrg);
                    travelExpenseListBean.setPkOaBody(pkOaBody);
                    travelExpenseListBean.setBudgetAmountOri(0.0);
                    travelExpenseListBean.setPurpose(purpose);
                    travelExpenseListBean.setZhifu(zhifu);
                    arrayListexpenseList.add(travelExpenseListBean);
                }
                //recList信息
                travelRecListBean travelRecListBean = new travelRecListBean();
                travelRecListBean.setBank(bank);
                //travelRecListBean.setPayEntryType(payEntryType);
                travelRecListBean.setRecAmt(recAmt);
                travelRecListBean.setRecBankAccount(recBankAccount);
                travelRecListBean.setRecPerson(recPerson);
                travelRecListBean.setRecCity(recCity);
                arrayListRecList.add(travelRecListBean);
                //拼接json
                //基础信息
                Gson gson = new Gson();
                Map map = new TreeMap<String, Object>();
                map.put("pkOaHead", requestid);//requestid
                map.put("number", number);//"1001"---->流程id--->唯一的字段与金蝶做回传
                map.put("applier", applierCode);
                map.put("appDate", appDate);
                //map.put("payDept",  payDept);
                map.put("payOrg", payOrg);
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
                //map.put("bearDept", "1111111111111111111");
                //map.put("bearOrg", "3333333333333333333");
                //添加expenseList信息
                map.put("expenseList", arrayListexpenseList);
                //添加expenseList信息
                map.put("recList", arrayListRecList);
                //添加revLoan信息
                map.put("revLoan", arrayListRevLoan);
                String json = gson.toJson(map);
                this.writeLog("请求id："+requestid+"，打印参数---->" + json);

                //调用差旅费用报销接口
                OaTravelService_ServiceLocator oTSSL = new OaTravelService_ServiceLocator();
                OaTravelService_PortType oTSP = null;
                oTSP = oTSSL.getOaTravelServiceImplPort();
                s = oTSP.receiveOaTravel(json);
                this.writeLog("请求id："+requestid+"，打印返回结果---->" + s);
                JsonObject asJsonObject = new JsonParser().parse(s).getAsJsonObject();//获取jsonObject里的具体对象属性
                String c = asJsonObject.get("c").getAsString();//获取staff里的对象
                String msg = asJsonObject.get("m").getAsString();//获取staff里的对象

                if ("1020000".equals(c)) {
                    this.writeLog("请求id："+requestid+"，调用成功");
                    result = SUCCESS;
                    tsjg = "0";
                } else if ("1010001".equals(c) && !"".equals(msg)) {
                    this.writeLog("请求id："+requestid+"，金蝶数据保存出错,请联系相关人员处理："+msg);
                    request.getRequestManager().setMessagecontent("金蝶数据保存出错,请联系相关人员处理，错误信息详情："+msg);
                    request.getRequestManager().setMessageid("11111"+requestid+"55555");
                    result = Action.FAILURE_AND_CONTINUE;
                    tsjg = "1";
                } else {
                    this.writeLog("请求id："+requestid+"，错误信息详情："+msg);
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
        this.writeLog("travelExpenseReimFormFilm 差旅费报销单 (影院/汉堡王)--请求id："+requestid+"--end");
        return result;
    }
}
