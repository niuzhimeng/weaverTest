package com.weavernorth.dadiyingyuan.feeReimburse.feeReimburseTest;//package com.weavernorth.dadiyingyuan.feeReimburse.feeReimburseTest;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import com.weavernorth.dadiyingyuan.feeReimburse.feeBaseReimburseBean.feeReimburseExpenseListBean;
//import com.weavernorth.dadiyingyuan.feeReimburse.feeBaseReimburseBean.feeReimburseRecListBean;
//import com.weavernorth.dadiyingyuan.feeReimburse.feeBaseReimburseBean.feeReimburseRevLoanBean;
//import com.weavernorth.dadiyingyuan.feeReimburse.feeReimburseClient.OaReimService_PortType;
//import com.weavernorth.dadiyingyuan.feeReimburse.feeReimburseClient.OaReimService_ServiceLocator;
//import org.json.JSONObject;
//import org.junit.Test;
//
//import javax.xml.rpc.ServiceException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.TreeMap;
//
//public class feeReimburse {
//    @Test
//    public void feeReimburse() throws Exception {
//        //新建expenseList集合
//        List arrayListexpenseList= new ArrayList();
//        //新建recList集合
//        List arrayListRecList= new ArrayList();
//        //新建revLoan集合
//        List arrayListRevLoan= new ArrayList();
//        String s="";
//        for (int i = 0; i <2 ; i++) {
//            feeReimburseExpenseListBean feeReimburseExpenseListBean = new feeReimburseExpenseListBean();
//            feeReimburseExpenseListBean.setPkOaBody("1");
//            feeReimburseExpenseListBean.setExpenseType("12");
//            feeReimburseExpenseListBean.setPurpose("85");
//            feeReimburseExpenseListBean.setCurDate("789");
//            feeReimburseExpenseListBean.setExchangeRate(89456.0);
//            feeReimburseExpenseListBean.setLocalAmtNoRate(465.0);
//            feeReimburseExpenseListBean.setRate(1.0);
//            feeReimburseExpenseListBean.setTaxAmt(78965.0);
//            feeReimburseExpenseListBean.setLocalAmt(86525.0);
//            feeReimburseExpenseListBean.setAmtApprovedNoRateLocal(0.0);
//            feeReimburseExpenseListBean.setApprovedTaxAmt(0.0);
//            feeReimburseExpenseListBean.setAmtApprovedLocal(0.0);
//            feeReimburseExpenseListBean.setPayEntryDept("243rrfds");
//            feeReimburseExpenseListBean.setPayEntryOrg("gfdvfvc");
//            arrayListexpenseList.add(feeReimburseExpenseListBean);
//        }
//
//        for (int j = 0; j < 3; j++) {
//            feeReimburseRevLoanBean feeReimburseRevLoanBean = new feeReimburseRevLoanBean();
//            feeReimburseRevLoanBean.setLoanNumber("753");
//            feeReimburseRevLoanBean.setPkOaHead("852");
//            feeReimburseRevLoanBean.setPkOaBody("77");
//            feeReimburseRevLoanBean.setAmount(222.0);
//            arrayListRevLoan.add(feeReimburseRevLoanBean);
//        }
//
//        feeReimburseRecListBean feeReimburseRecListBean = new feeReimburseRecListBean();
//        feeReimburseRecListBean.setPayEntryType("vhjkl");
//        feeReimburseRecListBean.setRecPerson("985623");
//        feeReimburseRecListBean.setRecCity("jhbjbh");
//        feeReimburseRecListBean.setRecBankAccount(742.0);
//        feeReimburseRecListBean.setBank("92656");
//        feeReimburseRecListBean.setRecAmt(2222.0);
//        arrayListRecList.add(feeReimburseRecListBean);
//        //拼接json
//        Gson gson = new Gson();
//        Map map = new TreeMap<String, Object>();
//        map.put("number", "hjk");
//        map.put("pkOaHead", "78523");
//        map.put("applier", "xuebaocen");
//        map.put("appDate", "trfv");
//        map.put("payDept", "brgdvg");
//        map.put("payOrg", "gfdvdv");
//        map.put("payType", "tbevdfc");
//        map.put("describ", "bvdfvc");
//        map.put("auditor", "xuebaocen");
//        map.put("auditTime", "2018-12-2");
//        map.put("creator", "xuebaocen");
//        map.put("originatTime", "gvedfcv");
//        map.put("oaBizType", "gvefdc");
//
//        //添加expenseList信息
//        map.put("expenseList", arrayListexpenseList);
//        //添加recList信息
//        map.put("recList", arrayListRecList);
//        //添加revLoan信息
//        map.put("revLoan", arrayListRevLoan);
//        String json = gson.toJson(map);
//        //调用费用报销接口
//        OaReimService_ServiceLocator oRSL = new OaReimService_ServiceLocator();
//        OaReimService_PortType oRSIP = null;
//        try {
//            oRSIP = oRSL.getOaReimServiceImplPort();
//            s = oRSIP.receiveOaReim(json);
//            System.out.println("------>" + json);
//            //System.out.println("fanhuizhi???------>" + s);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Object jsonObject = new Object();
//        JsonObject asJsonObject = new JsonParser().parse(s).getAsJsonObject();//获取jsonObject里的具体对象属性
//        String c = asJsonObject.get("c").getAsString();//获取staff里的对象
//        System.out.println("-----2222>"+c);
//    }
//}
