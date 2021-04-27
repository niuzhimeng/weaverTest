package com.weavernorth.workflow.payment;

import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;
import com.weaver.general.BaseBean;
import com.weaver.general.TimeUtil;
import com.weaver.general.Util;
import com.weaver.integration.datesource.SAPInterationOutUtil;
import com.weaver.integration.log.LogInfo;
import com.weavernorth.util.LogUtil;
import com.weavernorth.util.SapConnect;
import com.weavernorth.workflow.payment.SAPUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;

/**
 * 付款流程获取SAP采购订单信息
 *
 * @author
 * @create 2017-12-06 12:48
 **/
public class Purchase_order_SAP extends BaseBean{
    //获取配置的SAP数据源的id值
    private  String strPoolid = SAPUtil.getIdbyPoolName(new BaseBean().getPropValue("sapparam1","dataName"));
    private SapConnect saptool = new SapConnect();
    private  LogInfo li = new LogInfo();
    private  JCO.Function function = null;
    private  JCO.Client jcoClient = null;
    private  SAPInterationOutUtil sou = new SAPInterationOutUtil();

    /**
     *
     * @param purchaseNumber  采购订单号
     * @param WBSNum WBS号
     * @return
     */
    public JSONArray getPurchase_order(String purchaseNumber,String WBSNum,String isassets){
        LogUtil.debugLog("=====付款流程【获取采购订单号】执行开始==>>"+ TimeUtil.getCurrentTimeString());
        //采购凭证号
        String EBELN = "";
        //行号
        String EBELP = "";
        //物料号
        String MATNR = "";
        //物料/服务描述
        String MAKTX = "";
        //凭证中的过帐日期
        String BUDAT = "";
        //数量
        String MENGE = "";
        //单位
        String MEINS = "";
        //本币金额（不含增值税）
        String  DMBTR = "";
        //金额（不含税）
        String WRBTR = "";
        //货币码
        String WAERS = "";
        //成本中心
        String KOSTL = "";
        //总账科目
        String SAKTO = "";
        //WBS号
        String PS_PSP_PNR = "";
        //WBS描述
        String POST1 = "";
        //金额（含税）
        String BRTWR = "";
        // 物料编号
        String BELNR = "";
        //新增三个字段 add by lyl
        //参考凭证项目
        String LFPOS = "";
        //当前期间的会计年度
        String LFGJA = "";
        //参考凭证的凭证号
        String LFBNR = "";
        JSONArray array = new JSONArray();
        try {
            //连接sap
            jcoClient = (JCO.Client) sou.getConnection(strPoolid, li);
            LogUtil.debugLog("==连接sap的连接状态=="+jcoClient.getState());
            JCO.Repository mRepository = new JCO.Repository("Repository", jcoClient);
            //采购订单接口的函数名
            IFunctionTemplate ft = mRepository.getFunctionTemplate("ZRFC_CGDD_OA");
            JCO.Function function = new JCO.Function(ft);
            //  function = ft.getFunction();
            //传入采购订单号
            LogUtil.debugLog("==传入采购订单号==>"+purchaseNumber);
            function.getImportParameterList().setValue(purchaseNumber,"I_EBELN");
            //传入WBS号
            LogUtil.debugLog("==传入WBS号==>"+WBSNum);
            function.getImportParameterList().setValue(WBSNum,"I_PSPNR");
            LogUtil.debugLog("==传入业务类型==>"+isassets);
            function.getImportParameterList().setValue(isassets,"ISASSETS");
            jcoClient.execute(function);
            //输出的表名
            JCO.Table table = function.getTableParameterList().getTable("T_OUT");
            LogUtil.debugLog("==[采购订单信息]==函数ZRFC_CGDD_OA====>>>"+table);
            LogUtil.debugLog("==[采购订单信息]==table.getNumRows()===="+table.getNumRows());
            for (int i = 0; i <table.getNumRows(); i++) {
                table.setRow(i);
                JSONObject object = new JSONObject();
				
                EBELN = weaver.general.Util.null2String(table.getString("EBELN"));
                EBELP = weaver.general.Util.null2String(table.getString("EBELP"));
                MATNR = weaver.general.Util.null2String(table.getString("MATNR"));
                MAKTX = weaver.general.Util.null2String(table.getString("MAKTX"));
                BUDAT = weaver.general.Util.null2String(table.getString("BUDAT"));
                MENGE = weaver.general.Util.null2String(table.getString("MENGE"));
                MEINS = weaver.general.Util.null2String(table.getString("MEINS"));
                DMBTR = weaver.general.Util.null2String(table.getString("DMBTR"));
                WRBTR = weaver.general.Util.null2String(table.getString("WRBTR"));
                WAERS = weaver.general.Util.null2String(table.getString("WAERS"));
                KOSTL = weaver.general.Util.null2String(table.getString("KTEXT"));
                SAKTO = weaver.general.Util.null2String(table.getString("TXT20"));
//                PS_PSP_PNR = weaver.general.Util.null2String(table.getString("PS_PSP_PNR"));
                //update by lyl 20181113
                PS_PSP_PNR = weaver.general.Util.null2String(table.getString("POSID"));
                //新增三个字段 把三个字段更新到表单 begin
                LFPOS =  weaver.general.Util.null2String(table.getString("LFPOS"));
                LFGJA =  weaver.general.Util.null2String(table.getString("LFGJA"));
                LFBNR =  weaver.general.Util.null2String(table.getString("LFBNR"));
                //新增三个字段 end
                POST1 = weaver.general.Util.null2String(table.getString("POST1"));
                BRTWR = weaver.general.Util.null2String(table.getString("BRTWR"));
                /**
                 * 2018-03-19  weigh  增加物料编号字段  start
                 */
                BELNR = weaver.general.Util.null2String(table.getString("BELNR"));
                object.put("BELNR",BELNR);
                /**
                 * 2018-03-19  weigh  增加物料编号字段  end
                 */
                object.put("EBELN",EBELN);
                object.put("EBELP",EBELP);
                object.put("MATNR",MATNR);
                object.put("MAKTX",MAKTX);
                object.put("BUDAT",BUDAT);
                object.put("MENGE",MENGE);
                object.put("MEINS",MEINS);
                object.put("DMBTR",DMBTR);
                object.put("WRBTR",WRBTR);
                object.put("WAERS",WAERS);
                object.put("KOSTL",KOSTL);
                object.put("SAKTO",SAKTO);
                object.put("PS_PSP_PNR",PS_PSP_PNR);
                object.put("POST1",POST1);
                object.put("BRTWR",BRTWR);
                //新增三个字段
                object.put("LFPOS",LFPOS);
                object.put("LFGJA",LFGJA);
                object.put("LFBNR",LFBNR);
                array.add(object);
            }
        } catch (Exception e) {
            LogUtil.doWriteLog("==获取到的SAP的采购订单信息异常==>"+e);
        }
        LogUtil.debugLog("==获取到的SAP的采购订单信息==>"+array.toString());
        LogUtil.debugLog("=====付款流程【获取采购订单号】执行结束==>"+ TimeUtil.getCurrentTimeString());
        return array ;
    }
    /**
     * 获取供应商信息
     * @param provideAccount  供应商账号
     * @return
     */
    public JSONArray getProvideInfo(String provideAccount,String provideName){
        LogUtil.debugLog("=====付款流程【获取供应商信息】执行开始==>>"+ TimeUtil.getCurrentTimeString());
            JSONArray array  = new JSONArray();
          
            try {
                //连接sap
                jcoClient = (JCO.Client) sou.getConnection(strPoolid, li);
                JCO.Repository mRepository = new JCO.Repository("Repository", jcoClient);
                //传入函数名
                IFunctionTemplate ft = mRepository.getFunctionTemplate("ZRFC_GYS_OA");
                function = ft.getFunction();
                //传入参数----供应商账号
                LogUtil.debugLog("==供应商账号==>"+provideAccount);
                function.getImportParameterList().setValue(provideAccount,"I_LIFNR");
                function.getImportParameterList().setValue(provideName,"I_NAME");
                jcoClient.execute(function);
                //输出表名
                JCO.Table table = function.getTableParameterList().getTable("T_OUT");
                LogUtil.debugLog("==[获取供应商信息]==函数ZRFC_GYS_OA==>>>"+table);
                LogUtil.debugLog("==[获取供应商信息]==table.getNumRows()===="+table.getNumRows());
                //账号
                String crmcode = "";
                //名称
                String name = "";
                //银行国家代码
                String Bank_country_code = "";
                //银行代码
                String Bank_code = "";
                //银行名称
                String bankName = "";
                //合作银行类型
                String bank_type = "";
                //帐户持有人姓名
                String Account_name = "";
                //银行帐户号码
                String accounts = "";
                //银行帐户的参考规定
                String Bank_Reference = "";
                // 银行地址
                String BSTRAS = "";
                // 收款人地址
                String LSTRAS = "";
                // 供应商英文描述
                String ZNAME = "";
                for (int i = 0; i < table.getNumRows(); i++) {
                    table.setRow(i);
                    JSONObject obj = new JSONObject();
                    crmcode = weaver.general.Util.null2String(table.getString("LIFNR"));
                    name = weaver.general.Util.null2String(table.getString("NAME"));
                    Bank_country_code = weaver.general.Util.null2String(table.getString("BANKS"));
                    Bank_code = weaver.general.Util.null2String(table.getString("BANKL"));
                    bankName = weaver.general.Util.null2String(table.getString("BANKA"));
                    bank_type = weaver.general.Util.null2String(table.getString("BVTYP"));
                    Account_name = weaver.general.Util.null2String(table.getString("KOINH"));
                    accounts = weaver.general.Util.null2String(table.getString("BANKN"));
                    Bank_Reference = weaver.general.Util.null2String(table.getString("BKREF"));

                    BSTRAS = weaver.general.Util.null2String(table.getString("BSTRAS"));
                    LSTRAS = weaver.general.Util.null2String(table.getString("LSTRAS"));
                    ZNAME = weaver.general.Util.null2String(table.getString("ZNAME"));
                    obj.put("id",i+1);
                    obj.put("crmcode", crmcode);
                    obj.put("name", name);
                    
                    obj.put("Bank_country_code", Bank_country_code);
                    obj.put("Bank_code", Bank_code);
                    obj.put("bankName", bankName);
                    obj.put("bank_type", bank_type);
                    obj.put("Account_name", Account_name);
                    obj.put("accounts", accounts);
                    obj.put("Bank_Reference", Bank_Reference);

                    obj.put("BSTRAS", BSTRAS);
                    obj.put("LSTRAS", LSTRAS);
                    obj.put("ZNAME", ZNAME);
                    array.add(obj);
                    if(i>10){
                    	break;
                    }
                }
            } catch (Exception e) {
                LogUtil.doWriteLog("==获取供应商信息异常==>>>>"+e);
            }

        return  array;
    }
    
    
    /**
     * 获取客户信息
     * @param CustomerAccount  客户名称
     * @return
     */
    public JSONArray getCustomerInfo(String CustomerAccount,String CustomerName){
        LogUtil.debugLog("=====付款流程【获取客户信息】执行开始==>>"+ TimeUtil.getCurrentTimeString());
            JSONArray array  = new JSONArray();
            JSONObject obj1 = new JSONObject();
            JSONObject obj2 = new JSONObject();
            JSONObject obj3 = new JSONObject();
            JSONObject obj4 = new JSONObject();
            JSONObject obj5 = new JSONObject();
            JSONObject obj6 = new JSONObject();
            JSONObject obj7 = new JSONObject();
            JSONObject obj8 = new JSONObject();
            JSONObject obj9 = new JSONObject();
            //账号
            String crmcode2 = "";
            String crmcode1 = "";
            //名称
            String name2 = "";
            String name1 = "";
            //银行国家代码
            String Bank_country_code2 = "";
            String Bank_country_code1 = "";
            //银行代码
            String Bank_code2 = "";
            String Bank_code1 = "";
            //银行名称
            String bankName2 = "";
            String bankName1 = "";
            //合作银行类型
            String bank_type2 = "";
            String bank_type1 = "";
            //帐户持有人姓名
            String Account_name2 = "";
            String Account_name1 = "";
            //银行帐户号码
            String accounts2 = "";
            String accounts1 = "";
            //银行帐户的参考规定
            String Bank_Reference2 = "";
            String Bank_Reference1 = "";
            try {
                //连接sap
                jcoClient = (JCO.Client) sou.getConnection(strPoolid, li);
                JCO.Repository mRepository = new JCO.Repository("Repository", jcoClient);
                //传入函数名
                IFunctionTemplate ft = mRepository.getFunctionTemplate("ZRFC_GHDW_OA");
                function = ft.getFunction();
                //传入参数----客户账号
                LogUtil.debugLog("==客户账号==>"+CustomerAccount);
                function.getImportParameterList().setValue(CustomerAccount,"I_KUNNR");
                //传入参数----客户名称
                function.getImportParameterList().setValue(CustomerName,"I_NAME");
                jcoClient.execute(function);
                //输出表名
                JCO.Table table = function.getTableParameterList().getTable("T_OUT");
                LogUtil.debugLog("==[获取客户信息]==函数ZRFC_GYS_OA==>>>"+table);
                LogUtil.debugLog("==[获取客户信息]==table.getNumRows()===="+table.getNumRows());
                for (int i = 0; i < table.getNumRows(); i++) {
                    table.setRow(i);
                    crmcode1 = weaver.general.Util.null2String(table.getString("KUNNR"));
                    name1 = weaver.general.Util.null2String(table.getString("NAME2"));
                    Bank_country_code1 = weaver.general.Util.null2String(table.getString("BANKS"));
                    Bank_code1 = weaver.general.Util.null2String(table.getString("BANKL"));
                    bankName1 = weaver.general.Util.null2String(table.getString("BANKA"));
                    bank_type1 = weaver.general.Util.null2String(table.getString("BVTYP"));
                    Account_name1 = weaver.general.Util.null2String(table.getString("KOINH"));
                    accounts1 = weaver.general.Util.null2String(table.getString("BANKN"));
                    Bank_Reference1 = weaver.general.Util.null2String(table.getString("BKREF"));
                }
            } catch (Exception e) {
                LogUtil.doWriteLog("==获取客户信息异常==>>>>"+e);
            }
        if(!"".equals(CustomerAccount)){
            RecordSet rs = new RecordSet();
            String selSql = "select PortalLoginid,crmcode,name,Bank_country_code,Bank_code,bankName,Account_name,accounts,Bank_Reference from crm_customerinfo where PortalLoginid ='"+CustomerAccount+"'";
            rs.executeSql(selSql);
            if(rs.next()){
                //账号
                crmcode2 = Util.null2String(rs.getString("crmcode"));

                //名称
                name2 = Util.null2String(rs.getString("name"));

                //银行国家代码
                Bank_country_code2 =  Util.null2String(rs.getString("Bank_country_code"));

                //银行代码
                Bank_code2 =  Util.null2String(rs.getString("Bank_code"));

                //银行名称
                bankName2 = Util.null2String(rs.getString("bankName"));

                //账户持人姓名
                Account_name2 = Util.null2String(rs.getString("Account_name"));

                //银行账户号码
                accounts2 = Util.null2String(rs.getString("accounts"));

                //银行参考规定
                Bank_Reference2 = Util.null2String(rs.getString("Bank_Reference"));


            }
        }

        obj2.put("projName","供应商或客户名称");
        obj2.put("cell1",name1);
        obj2.put("cell2",name2);

        if(name1.equals(name2)){
            obj2.put("result","√");
        }else{
            obj2.put("result","×");
        }
        array.add(obj2);

        obj1.put("projName","供应商或客户编码");
        obj1.put("cell1",crmcode1);
        obj1.put("cell2",crmcode2);
        if(crmcode2.equals(crmcode1)){
            obj1.put("result","√");
        }else{
            obj1.put("result","×");
        }
        array.add(obj1);

        obj3.put("projName","银行国家代码");
        obj3.put("cell1",Bank_country_code1);
        obj3.put("cell2", Bank_country_code2);
        if(Bank_country_code1.equals(Bank_country_code2)){
            obj3.put("result","√");
        }else{
            obj3.put("result","×");
        }
        array.add(obj3);
        obj4.put("projName","银行代码");
        obj4.put("cell1",Bank_code1);
        obj4.put("cell2", Bank_code2);
        if(Bank_code1.equals(Bank_code2)){
            obj4.put("result","√");
        }else{
            obj4.put("result","×");
        }
        array.add(obj4);

        obj5.put("projName","银行名称");
        obj5.put("cell1",bankName1);
        obj5.put("cell2",bankName2);
        if(bankName1.equals(bankName2)){
            obj5.put("result","√");
        }else{
            obj5.put("result","×");
        }
        array.add(obj5);

        obj6.put("projName","账户持人姓名");
        obj6.put("cell1",Account_name1);
        obj6.put("cell2",Account_name2);
        if(bankName1.equals(bankName2)){
            obj6.put("result","√");
        }else{
            obj6.put("result","×");
        }
        array.add(obj6);
        obj7.put("projName", "银行账户号码");
        obj7.put("cell1", accounts1);
        obj7.put("cell2", accounts2);
        if(accounts1.equals(accounts2)){
            obj7.put("result","√");
        }else{
            obj7.put("result","×");
        }
        array.add(obj7);
        obj8.put("projName","银行参考规定");
        obj8.put("cell1",Bank_Reference1);
        obj8.put("cell2",Bank_Reference2);
        if(Bank_Reference1.equals(Bank_Reference2)){
            obj8.put("result","√");
        }else{
            obj8.put("result","×");
        }
        array.add(obj8);
        obj9.put("projName","");
        obj9.put("cell1","<input type='button' value='确认' onclick='check1()'>");
        obj9.put("cell2","<input type='button' value='确认' onclick='check2()'>");
        obj9.put("result","");
        array.add(obj9);

        LogUtil.debugLog("===供应商信息==>"+array.toString());
        LogUtil.debugLog("=====付款流程【获取供应商信息】执行结束==>>"+ TimeUtil.getCurrentTimeString());
        return  array;
    }

    /**
     * 寄售业务获取SAP数据
     * @param materials 物料凭证编号
     * @param startdate 开始日期
     * @param enddate 结束日期
     * @param materialsNO 物料编码
     * @param provideSCS 供应商描述
     * @return 寄售业务信息
     */
    public JSONArray getCommission(String materials,String startdate,
                                   String enddate,String materialsNO,
                                   String provideSCS){
            LogUtil.debugLog("=====付款流程【寄售业务】执行开始==>>"+ TimeUtil.getCurrentTimeString());
            //物料凭证编号
            String MBLNR = "";
            //物料凭证年度
            String MJAHR = "";
            //物料描述
            String MAKTX = "";
            //凭证中的过账日期
            String BUDAT = "";
            //数量
            String BSTMG = "";
            //单位
            String BSTME = "";
            //单价
            String DMBTR_DJ = "";
            //金额（含税）
            String DMBTR_HS = "";
            //金额（不含税）
            String WRBTR = "";
            //工厂
            String WERKS = "";
            //寄售单号
            String EBELN = "";
            // 物料号
            String MATNR = "";

            JSONArray array = new JSONArray();
            try {
                //连接sap
                jcoClient = (JCO.Client) sou.getConnection(strPoolid, li);
                LogUtil.debugLog("==连接sap的连接状态=="+jcoClient.getState());
                JCO.Repository mRepository = new JCO.Repository("Repository", jcoClient);
                //采购订单接口的函数名
                IFunctionTemplate ft = mRepository.getFunctionTemplate("ZRFC_JSYW_OA");
                JCO.Function function = new JCO.Function(ft);
                //  function = ft.getFunction();
                //传入采购订单号
                LogUtil.debugLog("==传入物料凭证编号==>"+materials);
                function.getImportParameterList().setValue(materials,"I_MBLNR");
                //传入WBS号
                LogUtil.debugLog("==传入过账日期（开始）==>"+startdate);
                function.getImportParameterList().setValue(startdate.replaceAll("-",""),"I_BUDAT1");
                LogUtil.debugLog("==传入过账日期（结束）==>"+enddate);
                function.getImportParameterList().setValue(enddate.replaceAll("-",""),"I_BUDAT2");
                LogUtil.debugLog("==传入物料编码（模糊）==>"+materialsNO);
                function.getImportParameterList().setValue(materialsNO,"I_MATNR");
                LogUtil.debugLog("==传入供应商描述（模糊）==>"+provideSCS);
                function.getImportParameterList().setValue(provideSCS,"I_NAME");
                jcoClient.execute(function);
                //输出的表名
                JCO.Table table = function.getTableParameterList().getTable("IT_OUT");
                LogUtil.debugLog("==[寄售业务信息]==ZRFC_JSYW_OA====>>>"+table);
                LogUtil.debugLog("==[寄售业务信息]==table.getNumRows()===="+table.getNumRows());
                for (int i = 0; i <table.getNumRows(); i++) {
                    table.setRow(i);
                    JSONObject object = new JSONObject();
                    MBLNR = weaver.general.Util.null2String(table.getString("MBLNR"));
                    MJAHR = weaver.general.Util.null2String(table.getString("MJAHR"));
                    MAKTX = weaver.general.Util.null2String(table.getString("MAKTX"));
                    BUDAT = weaver.general.Util.null2String(table.getString("BUDAT"));
                    BSTMG = weaver.general.Util.null2String(table.getString("MENGE"));
                    BSTME = weaver.general.Util.null2String(table.getString("MEINS"));
                    DMBTR_DJ = weaver.general.Util.null2String(table.getString("DMBTR_DJ"));
                    DMBTR_HS = weaver.general.Util.null2String(table.getString("DMBTR_HS"));
                    WRBTR = weaver.general.Util.null2String(table.getString("WRBTR"));
                    WERKS = weaver.general.Util.null2String(table.getString("WERKS"));
                    EBELN = weaver.general.Util.null2String(table.getString("EBELN"));
                    /**
                     * 2018-03-19  weigh 增加物料号  start
                     */
                    MATNR = weaver.general.Util.null2String(table.getString("MATNR"));
                    object.put("MATNR",MATNR);
                    /**
                     * 2018-03-19  weigh 增加物料号  end
                     */
                    object.put("MBLNR",MBLNR);
                    object.put("MJAHR",MJAHR);
                    object.put("MAKTX",MAKTX);
                    object.put("BUDAT",BUDAT);
                    object.put("BSTMG",BSTMG);
                    object.put("BSTME",BSTME);
                    object.put("DMBTR_DJ",DMBTR_DJ);
                    object.put("DMBTR_HS",DMBTR_HS);
                    object.put("WRBTR",WRBTR);
                    object.put("WERKS",WERKS);
                    object.put("EBELN",EBELN);
                    array.add(object);
                }
            } catch (Exception e) {
                LogUtil.doWriteLog("==获取到的SAP的寄售业务信息信息异常==>"+e);
            }
            LogUtil.debugLog("==获取到的寄售业务信息==>"+array.toString());
            LogUtil.debugLog("=====付款流程【寄售业务信息】执行结束==>"+ TimeUtil.getCurrentTimeString());
            return array ;
    }
}
