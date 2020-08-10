package com.weavernorth.dadiyingyuan.travelExpenseReimBursement.travelExpenseReimBursementTest;/*
package com.weavernorth.dadiyingyuan.travelExpenseReimBursement.travelExpenseReimBursementTest;

import com.google.gson.Gson;
import com.weavernorth.dadiyingyuan.travelExpenseReimBursement.travelExpenseReimBean.travelBaseExpenseReimBean;
import com.weavernorth.dadiyingyuan.travelExpenseReimBursement.travelExpenseReimBean.travelExpenseListBean;
import com.weavernorth.dadiyingyuan.travelExpenseReimBursement.travelExpenseReimBean.travelRecListBean;
import com.weavernorth.dadiyingyuan.travelExpenseReimBursement.travelExpenseReimBean.travelRevLoanBean;
import com.weavernorth.dadiyingyuan.travelExpenseReimBursement.travelExpenseReimBursementClient.OaTravelService_PortType;
import com.weavernorth.dadiyingyuan.travelExpenseReimBursement.travelExpenseReimBursementClient.OaTravelService_ServiceLocator;

import org.json.JSONObject;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class travelExpenseReimBursement {
    @Test
    public void travelExpenseReimBursement() throws Exception {
        //新建expenseList集合
        List arrayListexpenseList= new ArrayList();
        //新建recList集合
        List arrayListRecList= new ArrayList();
        //新建revLoan集合
        List arrayListRevLoan= new ArrayList();
        //第一步:查找流程表单获取对应字段
        for (int i = 0; i < 3; i++) {
            //明细表1字段齐全
            travelExpenseListBean travelExpenseListBean = new travelExpenseListBean();
            travelExpenseListBean.setAmtApprovedLocal(65165.0);
            travelExpenseListBean.setAmtApprovedNoRateLocal(6651.0);
            travelExpenseListBean.setApprovedTaxAmt(0.0);
            travelExpenseListBean.setCurDate("ghjkl");
            travelExpenseListBean.setEndDate("653.");
            travelExpenseListBean.setExchangeRate(1.0);
            travelExpenseListBean.setExpenseType("78632");
            travelExpenseListBean.setFrom("fgbn");
            travelExpenseListBean.setToArea("hjio");
            travelExpenseListBean.setLocalAmt(0.0);
            travelExpenseListBean.setLocalAmtNoRate(0.0);
            travelExpenseListBean.setOtherAmt(5645.0);
            travelExpenseListBean.setPayEntryDept("ujm");
            travelExpenseListBean.setPayEntryOrg("8523");
            travelExpenseListBean.setPkOaBody("sx");
            travelExpenseListBean.setPurpose("");
            travelExpenseListBean.setStayAmt(65456.0);
            travelExpenseListBean.setTaxAmt(0.0);
            travelExpenseListBean.setTransfee(6565.0);
            travelExpenseListBean.setTransfer(6654.0);
            travelExpenseListBean.setVehicle("yjk");
            arrayListexpenseList.add(travelExpenseListBean);
        }

        for (int j = 0; j <4 ; j++) {
            //明细表2字段齐全

            travelRevLoanBean travelRevLoanBean = new travelRevLoanBean();
            travelRevLoanBean.setAmount(654165.0);
            travelRevLoanBean.setLoanNumber("rfgbn");
            travelRevLoanBean.setPkOaBody("896523");
            travelRevLoanBean.setPkOaHead("5");
            arrayListRevLoan.add(travelRevLoanBean);
        }
        //recList信息
        travelRecListBean travelRecListBean = new travelRecListBean();
        travelRecListBean.setBank("962");
        travelRecListBean.setPayEntryType("vhjk");
        travelRecListBean.setRecAmt(652.0);
        travelRecListBean.setRecBankAccount("4653");
        travelRecListBean.setRecPerson("96521");
        travelRecListBean.setRecCity("cghjkl");
        arrayListRecList.add(travelRecListBean);
        //拼接json
        //基础信息
        Gson gson = new Gson();
        Map map = new TreeMap<String, Object>();
        try {
            map.put("number", "852");//"1001"---->流程id--->唯一的字段与金蝶做回传
            map.put("pkOaHead", "99");//requestid
            map.put("appDate", 62);
            map.put("payDept", "fghjm");
            map.put("payOrg", "8652");
            map.put("payType", "cgh");
            map.put("auditor", "yhbn");
            map.put("auditTime", "xcghjkl");
            map.put("creator", "852");
            map.put("originatTime", "9632");
            map.put("applier", "9+63");
            map.put("oaBizType", "8542320");
            //map.put("bearDept", "1111111111111111111");
            //map.put("bearOrg", "3333333333333333333");
            //添加expenseList信息
            map.put("expenseList", arrayListexpenseList);
            //添加expenseList信息
            map.put("recList", arrayListRecList);
            //添加revLoan信息
            map.put("revLoan", arrayListRevLoan);
            String json = gson.toJson(map);
            //调用差旅费用报销接口
            OaTravelService_ServiceLocator oTSSL = new OaTravelService_ServiceLocator();
            OaTravelService_PortType oTSP = oTSSL.getOaTravelServiceImplPort();
            String receiveOaTravel = oTSP.receiveOaTravel(json);
            System.out.println("---->" + json);
            System.out.println("---->" + receiveOaTravel);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
*/
