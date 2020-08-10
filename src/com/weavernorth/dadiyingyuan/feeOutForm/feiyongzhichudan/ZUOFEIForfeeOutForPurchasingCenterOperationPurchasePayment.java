package com.weavernorth.dadiyingyuan.feeOutForm.feiyongzhichudan;

import com.weavernorth.dadiyingyuan.feeOutForm.feeOutClient.OaExpendService_PortType;
import com.weavernorth.dadiyingyuan.feeOutForm.feeOutClient.OaExpendService_ServiceLocator;
import org.json.JSONException;
import org.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/*******************************************
 * 采购中心付款申请单表单
 *******************************************/
public class ZUOFEIForfeeOutForPurchasingCenterOperationPurchasePayment extends BaseAction {
    @Override
    public String execute(RequestInfo request) {
        //1、获取流程表单数据
        String requestid = request.getRequestid();//获取流程请求id
        this.writeLog("流程请求id----->"+requestid);
        String tableName = request.getRequestManager().getBillTableName();//获取流程表单名称
        this.writeLog("流程表单名称------>"+tableName);
        String workflowid = request.getWorkflowid();//获取流程id
        this.writeLog("流程id----->"+workflowid);
        String operator = request.getRequestManager().getSrc();//获取流程状态
        this.writeLog("流程状态---->"+operator);
        String requestname = request.getRequestManager().getRequestname();//请求标题
        this.writeLog("请求标题----->"+requestname);
        String lastoperator = request.getLastoperator();//获取当前节点操作者
        this.writeLog("最后审核人"+lastoperator);
        //获取当前时间
        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String format = df.format(day);
        this.writeLog("最后审核时间"+format);
        RecordSet rs = new RecordSet();//主表信息
        RecordSet detailRs=new RecordSet();//明细表1信息

        if("submit".equals(operator)){
            //获取主表信息
            rs.execute("select * from " + tableName + " where requestId='" + requestid + "'");//获取流程主表数据
            this.writeLog("查询主表单获取数据-----" + "select * from " + tableName + " where requestId='" + requestid + "'");
            while(rs.next()){
                String number = rs.getString("dh");//单号---number
                String pkOaHead=requestid;//requestid---pkOaHead
                String applier = rs.getString("sqr");//申请人---applier
                String appDate = rs.getString("rq");//日期---appDate
                String payType = rs.getString("zffs");//支付方式---payType
                String recType = rs.getString("skrlx");//收款人类型---recType
                String recSuplier=rs.getString("skrlx");//收款人类型---recSuplier//供应商、个人客户是收款人类型中的选择字段，选定收款人类型即可
                String recBank = rs.getString("khyh");//开户银行---recBank
                String recBankAccount = rs.getString("yhzh");//银行账号---recBankAccount
                String retailCus=rs.getString("skrlx");//收款人类型---retailCus//供应商、个人客户是收款人类型中的选择字段，选定收款人类型即可
                String retailBank = rs.getString("khyh");//开户银行---retailBank
                String retailBankAccount = rs.getString("yhzh");//银行账号---retailBankAccount
                String auditor=lastoperator;//lastoperator---auditor
                String auditTime=format;//format---auditTime
                String creator = rs.getString("sqr");//申请人---creator
                String originatTime = rs.getString("rq");//日期---originatTime
                String contractNo = rs.getString("htbh");//合同编号---contractNo
                String contractName = rs.getString("htmc");//合同名称---contractName
                String contractType="";//(OA中无此字段)
                String contractAmt = rs.getString("htje");//合同金额---contractAmt
                String paiedAmt = rs.getString("ljyfje");//累积已付金额（不含本次）---paiedAmt
                String unPayAmt = rs.getString("bchwfk");//本次后尚未付款金额---unPayAmt
                String oaBizType=requestname;//requestname---oaBizType
                String payEntryDept = rs.getString("fyzfbm");//费用支付部门---payEntryDept
                String payEntryOrg = rs.getString("fyzfgs");//费用支付公司---payEntryOrg
                String accItem="";


                /*String cwsqspr = rs.getString("cwsqspr");//财务授权审批人
                String bdzt = rs.getString("bdzt");//表单状态
                String yymc = rs.getString("yymc");//影院名称
                String cgpm = rs.getString("cgpm");//采购品名
                String skdw = rs.getString("skdw");//收款单位
                String zh = rs.getString("zh");//账号
                String khhcs = rs.getString("khhcs");//开户行城市
                String ddmc = rs.getString("ddmc");//订单名称
                String ddbh = rs.getString("ddbh");//订单编号
                String ddje = rs.getString("ddje");//订单金额
                String ljyfbfb = rs.getString("ljyfbfb");//累积已付百分比
                String bcsqfkje = rs.getString("bcsqfkje");//本次申请付款金额
                String bcsqbfb = rs.getString("bcsqbfb");//本次申请百分比
                String ljzfje = rs.getString("ljzfje");//累计支付金额（含本次）
                String lzfbfb = rs.getString("lzfbfb");//累计支付百分比
                String swfkbfb = rs.getString("swfkbfb");//尚未付款百分比
                String fktj = rs.getString("fktj");//付款条件
                String zbj = rs.getString("zbj");//质保金
                String zbjbfb = rs.getString("zbjbfb");//质保金百分比
                String bzsm = rs.getString("bzsm");//备注说明
                String xgfj = rs.getString("xgfj");//相关附件（合同、订单、验收/单或确认/入库单）
                String gsmc2 = rs.getString("gsmc2");//公司名称
                String bhsjezh = rs.getString("bhsjezh");//不含税金额总和
                String hdbhsje = rs.getString("hdbhsje");//核定不含税金蝶总和
                String sezh = rs.getString("sezh");//税额总和
                String hdsezh = rs.getString("hdsezh");//核定税额总和
                String hjjezh = rs.getString("hjjezh");//合计金额总和
                String hdjezh = rs.getString("hdjezh");//核定金额总和
                String sfje = rs.getString("sfje");//实付金额
                String kpqk = rs.getString("kpqk");//开票情况
                String fjs = rs.getString("fjs");//附件数
                String skrmc = rs.getString("skrmc");//收款人名称
                String sy = rs.getString("sy");//事由
                String ywlb = rs.getString("ywlb");//业务类别*/

                detailRs.execute("select * from formtable_main_29_dt1 where mainid=(select id from " + tableName + " where requestId='" + requestid + "')");//获取对应的明细表数据
                this.writeLog("明细表1信息" + "select * from formtable_main_29_dt1 where mainid=(select id from " + tableName + " where requestId='" + requestid + "')");
                while(detailRs.next()){
                    //明细表1信息
                    String payDept = rs.getString("fycdbm");//费用承担部门---payDept
                    String payOrg = detailRs.getString("fycdgs");//费用承担公司---payOrg
                    String rate = detailRs.getString("sl");//税率---rate
                    String pkOaBody=detailRs.getString("id");//id---pkOaBody
                    String expenseType = detailRs.getString("fylx");//费用类型---expenseType
                    String purpose = detailRs.getString("fysm");//费用说明---purpose
                    String billDate = detailRs.getString("yefsrq");//业务发生日期---billDate
                    String exchangeRate="";
                    String localAmtNoRate = detailRs.getString("bhsje");//不含税金额---localAmtNoRate
                    String rateAmt = detailRs.getString("se");//税额---rateAmt
                    String localAmt = detailRs.getString("hjje");//合计金额---localAmt
                    String amtApprovedNoRateLocal = detailRs.getString("hdbhsje");//核定不含税金额---amtApprovedNoRateLocal
                    String approvedTaxAmt = detailRs.getString("hdse");//核定税额---approvedTaxAmt
                    String amtApprovedLocal = detailRs.getString("hdje");//核定金额---amtApprovedLocal

                    /*String ywlbOne = detailRs.getString("ywlb");//业务类别
                    String cs = detailRs.getString("cs");//测试*/

                    //1、添加expenseList信息数组
                    Map[] maps = new Map[1];
                    //添加expenseList信息详情
                    Map map = new TreeMap();
                    //2、添加recList信息数组
                    Map[] recList = new Map[1];
                    //添加Map集合recList信息详情
                    Map recListSet = new TreeMap();
                    //3、添加revLoan信息数组
                    Map[] revLoan = new Map[1];
                    //添加Map集合recList信息详情
                    Map revLoanSet = new TreeMap();

                    JSONObject jsonObject = new JSONObject();
                    //jsonObject.put("createTime",format);
                    try {
                        jsonObject.put("number","'"+number+"'");
                        jsonObject.put("pkOaHead",requestid);
                        jsonObject.put("applier","'"+applier+"'");
                        jsonObject.put("appDate","'"+appDate+"'");
                        jsonObject.put("payDept","'"+payDept+"'");
                        jsonObject.put("payOrg","'"+payOrg+"'");
                        jsonObject.put("payType", "'"+payType+"'");
                        jsonObject.put("recType", "'"+recType+"'");
                        jsonObject.put("recSuplier", "'"+recSuplier+"'");
                        jsonObject.put("recBank", "'"+recBank+"'");
                        jsonObject.put("recBankAccount", "'"+recBankAccount+"'");
                        jsonObject.put("retailCus", "'"+retailCus+"'");
                        jsonObject.put("retailBank", "'"+retailBank+"'");
                        jsonObject.put("retailBankAccount", "'"+retailBankAccount+"'");
                        jsonObject.put("auditor", "'"+auditor+"'");
                        jsonObject.put("auditTime", "'"+auditTime+"'");
                        jsonObject.put("creator", "'"+creator+"'");
                        jsonObject.put("originatTime", "'"+originatTime+"'");
                        jsonObject.put("contractNo", "'"+contractNo+"'");
                        jsonObject.put("contractName", "'"+contractName+"'");
                        jsonObject.put("contractType", "01");
                        jsonObject.put("contractAmt", "'"+contractAmt+"'");
                        jsonObject.put("paiedAmt", paiedAmt);
                        jsonObject.put("unPayAmt", unPayAmt);
                        jsonObject.put("oaBizType", "'"+oaBizType+"'");

                        //添加expenseList信息数组
                        jsonObject.put("expenseList",maps);

                        //将expenseList信息添加进集合中
                        map.put("rate",rate);
                        map.put("pkOaBody","'"+pkOaBody+"'");
                        map.put("expenseType", "'"+expenseType+"'");
                        map.put("purpose", "'"+purpose+"'");
                        map.put("billDate", "'"+billDate+"'");
                        map.put("exchangeRate", 1.0);
                        map.put("localAmtNoRate", localAmtNoRate);
                        map.put("rateAmt", rateAmt);
                        map.put("localAmt", localAmt);
                        map.put("amtApprovedNoRateLocal", amtApprovedNoRateLocal);
                        map.put("approvedTaxAmt", approvedTaxAmt);
                        map.put("amtApprovedLocal", amtApprovedLocal);
                        map.put("payEntryDept", "'"+payEntryDept+"'");
                        map.put("payEntryOrg", "'"+payEntryOrg+"'");
                        map.put("accItem", "0301");
                        for (int i = 0; i <maps.length; i++) {
                            maps[i]=map;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    OaExpendService_ServiceLocator oaExpendService_serviceLocator = new OaExpendService_ServiceLocator();
                    OaExpendService_PortType oaExpendServiceImplPort = null;
                    try {
                        oaExpendServiceImplPort = oaExpendService_serviceLocator.getOaExpendServiceImplPort();
                        String s = oaExpendServiceImplPort.receiveOaExpend(jsonObject.toString());
                        System.out.println("----->"+jsonObject);
                        System.out.println("----->"+s);
                    } catch (ServiceException e) {
                        e.printStackTrace();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return SUCCESS;
    }
}
