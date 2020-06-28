package com.weavernorth.syncSupplier;

import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;
import com.weavernorth.util.LogUtil;
import com.weavernorth.util.SapConnect;
import weaver.conn.RecordSet;
import weaver.general.TimeUtil;
import weaver.general.Util;
import weaver.interfaces.schedule.BaseCronJob;

public class syncsupplier extends BaseCronJob {

    public void execute() {

        LogUtil.doWriteLog("---同步SAP供应商开始---" + TimeUtil.getOnlyCurrentTimeString());
        // 客户名称
        String strCustomer = "";
        // 简称
        String strAbbreviation = "";
        // 地址
        String strAddress = "";
        // 邮箱
        String strMailbox = "";
        // 姓名
        String strName = "";
        // 门户登录账号
        String strAccountNumber = "";
        // 密码
        String strPassword = "";
        String strCN = "";
        // 银行国家密码
        String strYhgjmm = "";
        // 银行代码
        String strYhdm = "";
        // 合作银行类型
        String strHzyhlx = "";
        // 银行账号的参考规定
        String strYhzhck = "";
        // 账户持有人姓名
        String strZhcyr = "";
        // 银行名称
        String strBank = "";
        // 银行账户号码
        String strBankNum = "";
        // 收款单位类型
        String strSkdw = "";
        // sap连接参数开始
        String strClient = "";
        String strUser = "";
        String strPasswd = "";
        String strLang = "";
        String strAshost = "";
        String strSysnr = "";
        // sap连接参数结束
        RecordSet rsupdate = new RecordSet();
        RecordSet rsoainsert = new RecordSet();
        RecordSet rsoaselect = new RecordSet();
        RecordSet rsoaselect2 = new RecordSet();
        // 连接sap
        SapConnect saptool = new SapConnect();
        JCO.Client sapconnection = saptool.getConnection();
        LogUtil.doWriteLog("---SAP连接成功---");
        try {
            JCO.Repository mRepository = new JCO.Repository("sap", sapconnection);
            IFunctionTemplate ft = mRepository.getFunctionTemplate("ZRFC_WBS_OA1");
            JCO.Function function = new JCO.Function(ft);
            LogUtil.doWriteLog("---SAP获得参数成功---");
            sapconnection.execute(function);
            JCO.Table table = function.getTableParameterList().getTable("IT_ITAB");
            boolean isSuccess = false;
            LogUtil.doWriteLog("---获得SAP输出表---");
            for (int i = 0; i < table.getNumRows(); i++) {
                LogUtil.doWriteLog("---进入循环---" + i);
                table.setRow(i);
                strCustomer = Util.null2String(table.getString("NAME1"));
                strAbbreviation = Util.null2String(table.getString("NAME2"));
                strAddress = Util.null2String(table.getString("ORT01"));
                strMailbox = Util.null2String(table.getString("PFACH"));
                strName = Util.null2String(table.getString("NAME"));
                // 登陆账号
                strAccountNumber = Util.null2String(table.getString("LIFNR"));
                strPassword = Util.null2String(table.getString("BANKL"));

                strYhgjmm = Util.null2String(table.getString("BANKS"));
                strYhdm = Util.null2String(table.getString("BANKL1"));
                strHzyhlx = Util.null2String(table.getString("BVTYP"));
                strYhzhck = Util.null2String(table.getString("BKREF"));
                strZhcyr = Util.null2String(table.getString("KOINH"));
                strBank = Util.null2String(table.getString("BANKA"));
                strBankNum = Util.null2String(table.getString("BANKN"));
                strSkdw = Util.null2String(table.getString("BRSCH"));
                rsoaselect.executeQuery("select count(1) CN from crm_customerinfo where crmcode='" + strAccountNumber + "'");
                if (rsoaselect.next()) {
                    strCN = Util.null2o(rsoaselect.getString("CN"));
                    LogUtil.doWriteLog(strCN + "---2同步SAP供应商密码---" + strPassword + "ABC--账号" + strAccountNumber);
                    if ("0".equals(strCN)) {
                        rsoainsert.executeUpdate("INSERT INTO crm_customerinfo  (name,engname,address1,type,description,size_n,source,sector,PortalLoginid,PortalPassword,PortalStatus,language,deleted,crmcode,frequently_suppliers,status,Bank_country_code,Bank_code,Cooperative_bank_type,Bank_Reference,Account_name,bankName,accounts,Receiving_unit_type) VALUES "
                                + "('"
                                + strCustomer
                                + "','"
                                + strAbbreviation
                                + "','"
                                + strAddress
                                + "'," +
                                "'2','8','7','9','93','"
                                + strAccountNumber
                                + "','"
                                + strPassword
                                + "','2','7','0','"
                                + strAccountNumber
                                + "','2','4','"
                                + strYhgjmm
                                + "','"
                                + strYhdm
                                + "','"
                                + strHzyhlx
                                + "','"
                                + strYhzhck
                                + "','"
                                + strZhcyr
                                + "','"
                                + strBank
                                + "','"
                                + strBankNum + "','" + strSkdw + "')");

                        rsoaselect2.executeSql("select id from crm_customerinfo where crmcode ='" + strAccountNumber + "'");
                        if (rsoaselect2.next()) {
                            String strid = Util.null2o(rsoaselect2.getString("id"));
                            LogUtil.doWriteLog("---获得新增客户的id---" + strid);

                            rsoainsert.executeSql("INSERT INTO CRM_ShareInfo  (deleted,relateditemid,sharetype,seclevel,sharelevel,contents,isdefault) VALUES "
                                    + "('0','"
                                    + strid
                                    + "','4','0','1','1','1')");
                            LogUtil.doWriteLog("INSERT INTO CRM_ShareInfo  (deleted,relateditemid,sharetype,seclevel,sharelevel,contents,isdefault) VALUES "
                                    + "('0','"
                                    + strid
                                    + "','4','0','1','1','1')");
                            rsoainsert.executeSql("INSERT INTO crm_customercontacter (customerid,title,fullname,firstname,jobtitle,language ) VALUES ("
                                    + strid
                                    + ",'1','"
                                    + strCustomer
                                    + "','"
                                    + strCustomer
                                    + "','Default','7')");
                        }
                    } else {
                        String strUpdate = "update crm_customerinfo set name = '"
                                + strCustomer
                                + "',engname='"
                                + strAbbreviation
                                + "',address1='"
                                + strAddress
                                + "',type='2',description='8',size_n='7',source='9',sector='93',PortalLoginid='"
                                + strAccountNumber
                                + "',PortalStatus='2',language='7',deleted='0',crmcode='"
                                + strAccountNumber
                                + "',status='4',Bank_country_code='"
                                + strYhgjmm
                                + "',Bank_code='"
                                + strYhdm
//                                + "',PortalPassword='"
//                                + strPassword
                                + "',Cooperative_bank_type='"
                                + strHzyhlx
                                + "',Bank_Reference='"
                                + strYhzhck
                                + "',Account_name='"
                                + strZhcyr
                                + "',bankName='"
                                + strBank
                                + "',accounts='"
                                + strBankNum
                                + "',Receiving_unit_type='"
                                + strSkdw
                                + "' where PortalLoginid='"
                                + strAccountNumber + "'";
                        rsupdate.executeSql(strUpdate);
                        LogUtil.doWriteLog("==2执行更新供应商sql==" + strUpdate);
                    }
                }
            }
        } catch (Exception e) {
            LogUtil.doWriteLog("---同步SAP供应商异常---" + e);
        }
        sapconnection.disconnect();
        saptool.disConnection();
        sapconnection = null;
        LogUtil.doWriteLog("---同步SAP供应商完成---" + TimeUtil.getOnlyCurrentTimeString());
    }

}
