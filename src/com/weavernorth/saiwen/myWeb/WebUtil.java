package com.weavernorth.saiwen.myWeb;

import weaver.conn.RecordSet;

public class WebUtil {

    /**
     * 获取所有科目信息
     */
    public static String getSubject(String myXml) throws Exception {
        SevenU9WeaverLocator locator = new SevenU9WeaverLocator();
        SevenU9WeaverSoap_PortType soap = locator.getSevenU9WeaverSoap();
        return soap.queryAccountFromXML(myXml, "", "", "");
    }

    /**
     * 获取所有客户信息
     */
    public static String getCustomer(String myXml) throws Exception {
        SevenU9WeaverLocator locator = new SevenU9WeaverLocator();
        SevenU9WeaverSoap_PortType soap = locator.getSevenU9WeaverSoap();
        return soap.querySupplierBankAccountFromXML(myXml, "", "", "");
    }

    /**
     * 创建供应商
     *
     * @param myXml 供应商信息
     * @param orgId 组织编码
     */
    public static String createSupplier(String myXml, String orgId) throws Exception {
        String[] userInfo = getUserInfo();
        SevenU9WeaverLocator locator = new SevenU9WeaverLocator();
        SevenU9WeaverSoap_PortType soap = locator.getSevenU9WeaverSoap();
        return soap.createSupplierFromXML(myXml, userInfo[0], userInfo[1], orgId);
    }

    /**
     * 创建客户
     */
    public static String createClient(String myXml, String orgId) throws Exception {
        String[] userInfo = getUserInfo();
        SevenU9WeaverLocator locator = new SevenU9WeaverLocator();
        SevenU9WeaverSoap_PortType soap = locator.getSevenU9WeaverSoap();
        return soap.createCustomerFromXML(myXml, userInfo[0], userInfo[1], orgId);
    }

    /**
     * 获取登录信息
     */
    private static String[] getUserInfo() {
        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("select username, password from uf_u9login");
        recordSet.next();
        return new String[]{recordSet.getString("username"), recordSet.getString("password")};
    }

}
