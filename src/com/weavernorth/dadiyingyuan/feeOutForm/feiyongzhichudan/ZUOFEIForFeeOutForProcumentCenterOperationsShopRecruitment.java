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

public class ZUOFEIForFeeOutForProcumentCenterOperationsShopRecruitment extends BaseAction {
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
                String payDept="";
                String payOrg="";
                String payType="";
                String recType="";
                String recSuplier="";
                String recBank = rs.getString("khyh");//开户银行---recBank
                String recBankAccount = rs.getString("zh");//账号---recBankAccount
                String retailCus="";

                String cwsqspr = rs.getString("cwsqspr");//财务授权审核人
                String xzzfshbry = rs.getString("xzzfshbry");//选择支付审核部人员
                String bdzt = rs.getString("bdzt");//表单状态




                String yymc = rs.getString("yymc");// 影院名称
                String cgpm = rs.getString("cgpm");//采购品名
                String skdw = rs.getString("skdw");//收款单位


                String khhcs = rs.getString("khhcs");//开户行城市
                String htmc = rs.getString("htmc");//合同名称
                String ddmc = rs.getString("ddmc");//订单名称
                String htbh = rs.getString("htbh");//合同编号
                String ddbh = rs.getString("ddbh");//订单编号
                String htje = rs.getString("htje");//合同金额
                String ddje = rs.getString("ddje");//订单金额
                String ljyfje = rs.getString("ljyfje");//累积已付金额（不含本次）
                String ljyfbfb = rs.getString("ljyfbfb");//累积已付百分比
                String bcsqfkje = rs.getString("bcsqfkje");//本次申请付款金额
                String bcsqbfb = rs.getString("bcsqbfb");//本次申请百分比
                String ljzfje = rs.getString("ljzfje");//累计支付金额（含本次）
                String lzfbfb = rs.getString("lzfbfb");//累计支付百分比
                String swfkje = rs.getString("swfkje");//本次后尚未付款金额
                String swfkbfb = rs.getString("swfkbfb");//尚未付款百分比
                String fktj = rs.getString("fktj");//付款条件
                String zbj = rs.getString("zbj");//质保金
                String zbjbfb = rs.getString("zbjbfb");//质保金百分比
                String bzsm = rs.getString("bzsm");//备注说明
                String xgfj = rs.getString("xgfj");//相关附件（合同、订单、验收/单或确认/入库单）
                String gsmc1 = rs.getString("gsmc1");//公司名称



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

        return SUCCESS;
    }
}
