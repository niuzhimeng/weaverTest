package com.weavernorth.dadiyingyuan.brrowFeeForm.brrowFeeFormTest;/*
package com.weavernorth.dadiyingyuan.brrowFeeForm.brrowFeeFormTest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.Authenticator;
import com.weavernorth.dadiyingyuan.brrowFeeForm.brrowFeeFormBean.feeExpenseList;
import com.weavernorth.dadiyingyuan.brrowFeeForm.brrowFeeFormBean.feeRecList;
import com.weavernorth.dadiyingyuan.brrowFeeForm.brrowFeeFormClient.OaLoanService_PortType;
import com.weavernorth.dadiyingyuan.brrowFeeForm.brrowFeeFormClient.OaLoanService_ServiceLocator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class testDesign {
    @Test
    public void brrowFeeFormTest(){
        String oaLoan="";
        //新建expenseList集合
        List arrayListexpenseList= new ArrayList();
        //2、新建recList数组
        List arrayListeRecList= new ArrayList();
        for (int i = 0; i <1 ; i++) {
            feeExpenseList feeExpenseList = new feeExpenseList();
            feeExpenseList.setPkOaBody("52");
            feeExpenseList.setExpenseType("8");
            feeExpenseList.setPurpose("sv");
            //feeExpenseList.setAmt();
            feeExpenseList.setLocalAmt(66);
            feeExpenseList.setApprovedAmt(23);
            feeExpenseList.setPayEntryDept("hjkl");
            feeExpenseList.setPayEntryOrg("ijkml");
            arrayListexpenseList.add(feeExpenseList);
        }
        feeRecList feeRecList = new feeRecList();
        feeRecList.setPayEntryType("hujkl");
        feeRecList.setRecPerson("sdv");
        feeRecList.setRecCity("vsdc");
        feeRecList.setRecBankAccount("bsvxc");
        feeRecList.setBank("svdx");
        feeRecList.setRecAmt(5);
        arrayListeRecList.add(feeRecList);


        //基础信息
        Gson gson = new Gson();
        Map map = new TreeMap<String, Object>();
        try {
            map.put("number", "5165");//流程id
            map.put("payDept", "csds");
            map.put("payOrg", "cdscs");
            map.put("repaymentDate", "vsdxc");
            map.put("payType", "sbvxc");
            map.put("appDate", "sbfvxc");
            map.put("pkOaHead", "sbvxcc");//requestid"111111111111111"
            map.put("oaBizType", "sbfvcx");//标题
            map.put("applier", "bsfvxc");
            map.put("auditor", "bsfxd");
            map.put("auditTime", "bsvxc");
            map.put("originatTime", "bsvdcx");

            //添加expenseList至map
            map.put("expenseList", arrayListexpenseList);
            //添加recList至map
            map.put("recList", arrayListeRecList);
            String json = gson.toJson(map);
            //调用借款单接口
            OaLoanService_ServiceLocator oLSSL = new OaLoanService_ServiceLocator();
            OaLoanService_PortType oLSP = oLSSL.getOaLoanServiceImplPort();
            oaLoan = oLSP.receiveOaLoan(json);
            System.out.println("---->" + json);
            System.out.println("---->123456" + oaLoan);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("json数据错误" + e);
        }
        Object jsonObject = new Object();
        JsonObject asJsonObject = new JsonParser().parse(oaLoan).getAsJsonObject();//获取jsonObject里的具体对象属性
        String c = asJsonObject.get("c").getAsString();//获取staff里的对象
        System.out.println("-----2222>"+c);
    }

}
*/
