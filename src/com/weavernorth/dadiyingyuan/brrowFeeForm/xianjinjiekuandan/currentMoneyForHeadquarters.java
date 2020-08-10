package com.weavernorth.dadiyingyuan.brrowFeeForm.xianjinjiekuandan;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.weavernorth.dadiyingyuan.brrowFeeForm.brrowFeeFormBean.feeExpenseList;
import com.weavernorth.dadiyingyuan.brrowFeeForm.brrowFeeFormBean.feeRecList;
import com.weavernorth.dadiyingyuan.brrowFeeForm.brrowFeeFormClient.OaLoanService_PortType;
import com.weavernorth.dadiyingyuan.brrowFeeForm.brrowFeeFormClient.OaLoanService_ServiceLocator;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
/**
*@program: currentMoneyForHeadquarters
*@description: 现金借款单（总部/媒体）
*@author: chchq
*@updateDate: 2019/6/20
*/
public class currentMoneyForHeadquarters extends BaseAction {

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
        //System.out.println(df.format(day));

        //新建expenseList集合
        List arrayListexpenseList= new ArrayList();
        //2、新建recList数组
        List arrayListeRecList= new ArrayList();

        RecordSet rs = new RecordSet();//主标信息
        RecordSet detailRs=new RecordSet();//明细表信息
        String oaLoan = "";

        //方法执行结果
        String result = "";
        //推送结果
        String tsjg = "";
        //修改推送结果sql
        String updateTsjgSql = "update "+tableName+" SET tsjg = ? where requestid = ?  ";
        ConnStatement con = new ConnStatement();
        this.writeLog("currentMoneyForHeadquarters 现金借款单（总部/媒体）--请求id："+requestid+"--start");

        if("submit".equals(operator)) {
            try {
                rs.execute("select * from " + tableName + " where requestId='" + requestid + "'");//获取流程主表数据
                this.writeLog("查询主表单获取数据-----" + "select * from " + tableName + " where requestId='" + requestid + "'");
                rs.next();
                //获取主表数据
                String number = rs.getString("dh");//单号---number
                this.writeLog("单号："+number);
                String applier = rs.getString("sqr");//申请人--applier
                String appDate = rs.getString("sqrq");//申请日期---appDate
                //String payDept = rs.getString("fycdcode");//费用承担部门----payDept
                String payOrgId = rs.getString("fyzfgs");//	费用承担公司----payOrg
                String describ = rs.getString("sy");//事由
                String repaymentDate = rs.getString("yjhkr");//预计还款日---repaymentDate
                //String payType = rs.getString("zffs");//支付方式----payType
                String oaBizType = "现金借款单(总部/媒体)";//requestname---oaBizType
                String auditor =lastoperator;//lastoperator---auditor
                String auditTime=format;//format---auditTime
                String creator=applier;//制单人
                String originatTime = rs.getString("sqrq");//制单日期---originatTime
                int attendCount=rs.getInt("fjs");//附件数---attendCount
                String pkOaHead = requestid;//requestid---pkOaHead


                //添加至明细表信息---expenseList
                String payEntryDeptId = rs.getString("fycdbm");//费用承担部门---费用支付部门
                String payEntryOrgId = rs.getString("fycdgs");//费用承担公司---费用支付公司
                String bank = rs.getString("khyh");//开户银行
                //String payEntryType = rs.getString("zffs");//支付方式----payEntryType
                String recAmtValue = rs.getString("sqhjje");//核定合计金额hdhjje改为--申请合计金额sqhjje
                String recBankAccount = rs.getString("yxzh");//银行账号
                String recCity = rs.getString("khdq");//开户地区
                String recPerson = rs.getString("skrmc");//收款人名称
                //新增支付款类别
                String zhiFu = rs.getString("zfklb");//
                String id = rs.getString("id");

                //处理千分位问题
                double recAmtV = Double.valueOf(recAmtValue.replace(",", "")).doubleValue()<0?0:Double.valueOf(recAmtValue.replace(",", "")).doubleValue();
                NumberFormat numberFormat1 = NumberFormat.getNumberInstance();
                numberFormat1.setGroupingUsed(false); //设置了以后不会有千分位，如果不设置，默认是有的
                String recAmt = numberFormat1.format(recAmtV);

                //获取申请人邮箱
                RecordSet rsEmail = new RecordSet();//主标信息
                String emailSql="select email from HrmResource where id= "+applier+"";
                rsEmail.execute(emailSql);
                rsEmail.next();
                String email = rsEmail.getString("email");
                String emailBefore=email.substring(0, email.indexOf("@"));//截取@之前的字符串
                int length = emailBefore.length();
                String emailAfter=email.substring(length);
                String applierCode="";
                if(emailAfter.equals("@dadimedia")){
                    applierCode=emailBefore;
                }else{
                    applierCode=email;
                }

                //获取当前操作人邮箱
                RecordSet rsAuditorEmail = new RecordSet();//主标信息
                String emailAuditorSql="select email from HrmResource where id= "+lastoperator+"";
                rsAuditorEmail.execute(emailAuditorSql);
                rsAuditorEmail.next();
                String emailAuditor = rsAuditorEmail.getString("email");
                String emailAuditorBefore=emailAuditor.substring(0, emailAuditor.indexOf("@"));//截取@之前的字符串
                int lengthAuditor = emailAuditorBefore.length();
                String emailAuditorAfter=email.substring(lengthAuditor);
                String auditorCode="";
                if(emailAuditorAfter.equals("@dadimedia")){
                    auditorCode=emailAuditorBefore;
                }else{
                    auditorCode=emailAuditor;
                }

                //查询费用支付公司
                RecordSet feeOutOrg = new RecordSet();
                this.writeLog("查询费用支付公司--->"+"select code from uf_zzb where id="+payOrgId);
                String queryFeeOutOrg="select code from uf_zzb where id="+payOrgId;
                feeOutOrg.execute(queryFeeOutOrg);
                feeOutOrg.next();
                String payOrg = feeOutOrg.getString("code");
                this.writeLog("费用支付公司---"+payOrg);


                //查询费用承担部门和承担公司code
                RecordSet feeAcceptDept = new RecordSet();
                this.writeLog("查询承担部门--->"+"select code from uf_zzb where id="+payEntryDeptId);
                String queryDept="select code from uf_zzb where id="+payEntryDeptId;
                feeAcceptDept.execute(queryDept);
                feeAcceptDept.next();
                String payEntryDept = feeAcceptDept.getString("code");
                this.writeLog("承担部门---"+payEntryDept);

                //查询承担公司
                RecordSet feeAcceptOrg = new RecordSet();
                this.writeLog("查询承担公司--->"+"select code from uf_zzb where id="+payEntryOrgId);
                String queryOrg="select code from uf_zzb where id="+payEntryOrgId;
                feeAcceptOrg.execute(queryOrg);
                feeAcceptOrg.next();
                String payEntryOrg = feeAcceptOrg.getString("code");
                this.writeLog("承担公司---"+payEntryOrg);

                detailRs.execute("select * from formtable_main_247_dt1  where mainid="+id+"");//获取对应的明细表数据
                this.writeLog("明细表信息" + "select * from formtable_main_247_dt1  where mainid="+id+"");
                while (detailRs.next()) {
                    String approvedAmtValue = detailRs.getString("hdje");//核定金额
                    String expenseTypeId = detailRs.getString("fylx");//fylxbm---expenseType(新增字段)
                    String localAmtValue = detailRs.getString("sqje");//申请金额
                    String pkOaBody = detailRs.getString("id");
                    String purpose = detailRs.getString("jkyt");//借款用途

                    //处理千分位问题
                    double localAmtV = Double.valueOf(localAmtValue.replace(",", "")).doubleValue()<0?0:Double.valueOf(localAmtValue.replace(",", "")).doubleValue();
                    NumberFormat numberFormat2 = NumberFormat.getNumberInstance();
                    numberFormat2.setGroupingUsed(false); //设置了以后不会有千分位，如果不设置，默认是有的
                    String localAmt = numberFormat2.format(localAmtV);

                    //处理千分位问题
                    double approvedAmtV = Double.valueOf(approvedAmtValue.replace(",", "")).doubleValue()<0?0:Double.valueOf(approvedAmtValue.replace(",", "")).doubleValue();
                    NumberFormat numberFormat3 = NumberFormat.getNumberInstance();
                    numberFormat3.setGroupingUsed(false); //设置了以后不会有千分位，如果不设置，默认是有的
                    String approvedAmt = numberFormat3.format(approvedAmtV);

                    /*查询费用类型编码*/
                    RecordSet feeTypeCode = new RecordSet();
                    this.writeLog("查询费用类型--"+"select code from uf_fylxb where id="+expenseTypeId);
                    String queryFeeType="select code from uf_fylxb where id="+expenseTypeId;
                    feeTypeCode.execute(queryFeeType);
                    feeTypeCode.next();
                    String expenseTypeCode = feeTypeCode.getString("code");//费用类型code

                    feeExpenseList feeExpenseList = new feeExpenseList();
                    feeExpenseList.setPkOaBody(pkOaBody);
                    feeExpenseList.setExpenseType(expenseTypeCode);
                    feeExpenseList.setPurpose(purpose);
                    //feeExpenseList.setAmt();
                    feeExpenseList.setLocalAmt(localAmt);
                    feeExpenseList.setApprovedAmt(approvedAmt);
                    feeExpenseList.setPayEntryDept(payEntryDept);
                    feeExpenseList.setPayEntryOrg(payEntryOrg);
                    feeExpenseList.setZhifu(zhiFu);
                    //feeExpenseList.setXianjin("1.7.8.1");
                    arrayListexpenseList.add(feeExpenseList);
                }

                feeRecList feeRecList = new feeRecList();
                //feeRecList.setPayEntryType("01");
                feeRecList.setRecPerson(recPerson);
                feeRecList.setRecCity(recCity);
                feeRecList.setRecBankAccount(recBankAccount);
                feeRecList.setBank(bank);
                feeRecList.setRecAmt(recAmt);
                arrayListeRecList.add(feeRecList);
                //拼接json串
                //基础信息
                //基础信息
                Gson gson = new Gson();
                Map map = new TreeMap<String, Object>();
                map.put("number", number);//流程id
                map.put("applier", applierCode);
                map.put("appDate", appDate);
                //写死值payDept
                //map.put("payDept", "D005.034-03");
                map.put("payOrg", payOrg);
                map.put("describ",describ);
                map.put("repaymentDate", repaymentDate);
                //写死值payType
                //map.put("payType", "01");
                map.put("oaBizType", oaBizType);//标题
                map.put("auditor", auditorCode);
                map.put("auditTime", auditTime);
                map.put("creator", applierCode);
                map.put("originatTime", originatTime);
                map.put("attendCount", attendCount);
                //流程数据库表单名称
                map.put("oaTabBaseName",tableName);
                map.put("pkOaHead", requestid);//requestid"111111111111111"

                //添加expenseList至map
                map.put("expenseList", arrayListexpenseList);
                //添加recList至map
                map.put("recList", arrayListeRecList);
                String json = gson.toJson(map);
                this.writeLog("请求id："+requestid+",请求参数---->"+json);
                //调用借款单接口
                OaLoanService_ServiceLocator oLSSL = new OaLoanService_ServiceLocator();
                OaLoanService_PortType oLSP = oLSSL.getOaLoanServiceImplPort();
                oaLoan = oLSP.receiveOaLoan(json);
                this.writeLog("请求id："+requestid+",金蝶返回值---->"+oaLoan);
                JsonObject asJsonObject = new JsonParser().parse(oaLoan).getAsJsonObject();//获取jsonObject里的具体对象属性
                String c = asJsonObject.get("c").getAsString();//获取staff里的对象
                String msg = asJsonObject.get("m").getAsString();//获取staff里的对象

                if ("1020000".equals(c)) {
                    this.writeLog("请求id："+requestid+",调用成功");
                    result = SUCCESS;
                    tsjg = "0";
                } else if("1010001".equals(c) && !"".equals(msg)) {
                    this.writeLog("请求id："+requestid+",金蝶数据保存出错,请联系相关人员处理。处理错误信息详情：" + msg);
                    request.getRequestManager().setMessagecontent("金蝶数据保存出错,请联系相关人员。处理错误信息详情：" + msg);
                    request.getRequestManager().setMessageid("11111"+requestid+"55555");
                    result = Action.FAILURE_AND_CONTINUE;
                    tsjg = "1";
                }else{
                    this.writeLog("请求id："+requestid+",金蝶数据保存出错,请联系相关人员处理。处理错误信息详情：" + msg);
                    //控制流程流转，增加以下两行，流程不会向下流转，表单上显示返回的自定义错误信息
                    request.getRequestManager().setMessagecontent("金蝶数据保存出错,请联系相关人员。处理错误信息详情：" + msg);
                    request.getRequestManager().setMessageid("11111"+requestid+"55555");
                    result = Action.FAILURE_AND_CONTINUE;
                    tsjg = "1";
                }
            }catch(Exception e){
                this.writeLog("请求id："+requestid+"，错误："+e);
                //控制流程流转，增加以下两行，流程不会向下流转，表单上显示返回的自定义错误信息
                request.getRequestManager().setMessagecontent("代码异常。"+"错误信息详情：" + e);
                request.getRequestManager().setMessageid("11111"+requestid+"55555");
                result = Action.FAILURE_AND_CONTINUE;
                tsjg = "2";
            }
            try {
                con.setStatementSql(updateTsjgSql);
                con.setString(1,tsjg);
                con.setString(2,requestid);
                con.executeUpdate();
                con.close();
            } catch (Exception e) {
                this.writeLog("修改推送结果异常，请求id："+requestid+"，错误："+e);
            }
        }
        this.writeLog("请求id："+requestid+"，推送结果："+tsjg+",流程执行结果："+result);
        this.writeLog("currentMoneyForHeadquarters 现金借款单（总部/媒体）--请求id："+requestid+"--end");
        return result;
    }
}
