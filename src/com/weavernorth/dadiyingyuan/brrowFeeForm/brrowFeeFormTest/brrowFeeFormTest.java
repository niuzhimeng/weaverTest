package com.weavernorth.dadiyingyuan.brrowFeeForm.brrowFeeFormTest;/*
package com.weavernorth.dadiyingyuan.brrowFeeForm.brrowFeeFormTest;

import com.google.gson.Gson;
import com.weavernorth.dadiyingyuan.brrowFeeForm.brrowFeeFormBean.feeExpenseList;
import com.weavernorth.dadiyingyuan.brrowFeeForm.brrowFeeFormBean.feeRecList;
import com.weavernorth.dadiyingyuan.brrowFeeForm.brrowFeeFormClient.OaLoanService_PortType;
import com.weavernorth.dadiyingyuan.brrowFeeForm.brrowFeeFormClient.OaLoanService_ServiceLocator;
import org.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class brrowFeeFormTest {
    @Test
    public void brrowFeeFormTest()throws Exception{
        //1、新建expenseList数组
        //Map[] expenseList = new Map[1];
        //新建expenseList集合
        List arrayListexpenseList= new ArrayList();
        //Map expenseListSet = new TreeMap();

        //2、新建recList数组
        List arrayListeRecList= new ArrayList();

        //添加expenseList的信息
        for (int j = 0; j <2 ; j++) {
            feeExpenseList feeExpenseList = new feeExpenseList();
            feeExpenseList.setPkOaBody("111111111111111");
            feeExpenseList.setExpenseType("DD-001.003");
            feeExpenseList.setPurpose( "租房子");
            //feeExpenseList.setAmt(15000.0);
            feeExpenseList.setLocalAmt(15000.0);
            feeExpenseList.setApprovedAmt(10000.0);
            feeExpenseList.setPayEntryDept("DD-F7");
            feeExpenseList.setPayEntryOrg("D004.000.02");
            arrayListexpenseList.add(feeExpenseList);
        }

        //expenseListSet.put("pkOaBody", "111111111111111");//明细表id
        //expenseListSet.put("expenseType", "DD-001.003");
        //expenseListSet.put("purpose", "租房子");
//        expenseListSet.put("Amt", 15000.0);
//        expenseListSet.put("localAmt", 15000.0);
//        expenseListSet.put("approvedAmt", 10000.0);
//        expenseListSet.put("payEntryDept", "DD-F7");
//        expenseListSet.put("payEntryOrg", "D004.000.02");
//        //将expenseList集合添加到expenseList数组
//        for (int i = 0; i <expenseList.length ; i++) {
//            expenseList[i]=expenseListSet;
//        }

        //添加recList的信息
        for (int i = 0; i <3 ; i++) {
            feeRecList feeRecList = new feeRecList();
            feeRecList.setPayEntryType("01");
            feeRecList.setRecPerson("龚保玉");
            feeRecList.setRecCity("北京");
            feeRecList.setRecBankAccount("6217850100006482635");
            feeRecList.setBank("中国银行北京新源里支行");
            feeRecList.setRecAmt(23000.00);
            arrayListeRecList.add(feeRecList);
        }

        //recListSet.put("payEntryType", "01");
        //recListSet.put("recPerson", "龚保玉");
        //recListSet.put("recCity", "北京");
        //recListSet.put("recBankAccount", "6217850100006482635");
        //recListSet.put("bank", "中国银行北京新源里支行");
        //recListSet.put("recAmt", 23000.0);
        //将recList集合添加到recList数组
        */
/*for (int i = 0; i <recList.length ; i++) {
            recList[i]=recListSet;
        }*//*

        //调用借款单接口
//        OaLoanService_ServiceLocator oLSSL = new OaLoanService_ServiceLocator();
//        OaLoanService_PortType oLSP = oLSSL.getOaLoanServiceImplPort();
//        String oaLoan = oLSP.receiveOaLoan(jsonObject.toString());
        //Map[] recList = new Map[1];
        //新建recList集合
        //Map recListSet = new TreeMap();
        //基础信息
        Gson gson = new Gson();
        //String s = gson.toJson(list);

        //JSONObject jsonObject = new JSONObject();
        //List list = new ArrayList();
        Map map = new TreeMap<String, Object>();
        map.put("pkOaHead", "111111111111111");//requestid
        map.put("number", "E199001");//流程id
        map.put("applier", "44444444444");
        map.put("appDate", "2018-07-09");
        map.put("repaymentDate", "2018-09-09");
        map.put("appDept", "A3-B5-C2");
        map.put("appOrg", "D004.000.02");
        map.put("payDept", "DD-F7");
        map.put("payOrg", "D004.000.02");
        map.put("payType", "01");
        map.put("oaBizType", "XXX借款单");//标题
        map.put("auditor", "02");
        map.put("auditTime", "2018-07-11");
        map.put("creator", "测试用户");
        map.put("originatTime", "2018-07-11");
        map.put("attendCount", "2");

        //添加expenseList至map
        map.put("expenseList",arrayListexpenseList);
        //添加recList至map
        map.put("recList",arrayListeRecList);
        String json = gson.toJson(map);

        System.out.println("---->"+json);

        //调用借款单接口
        OaLoanService_ServiceLocator oLSSL = new OaLoanService_ServiceLocator();
        OaLoanService_PortType oLSP = oLSSL.getOaLoanServiceImplPort();
        String oaLoan = oLSP.receiveOaLoan(json);
        System.out.println("---->" + json);
        System.out.println("---->" + oaLoan);
        //System.out.println("---->"+oaLoan);
    }
}*/
