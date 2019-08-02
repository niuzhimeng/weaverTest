package com.weavernorth.saiwen.myWeb;

public class WebUtil {

    private static final String USER = "weaverTest";
    private static final String PASSWORD = "123456";
    private static final String ORG = "101";

    /**
     * 获取所有科目信息
     */
    public static String getSubject(String myXml) throws Exception {
        SevenU9WeaverLocator locator = new SevenU9WeaverLocator();
        SevenU9WeaverSoap_PortType soap = locator.getSevenU9WeaverSoap();
        return soap.queryAccountFromXML(myXml, USER, PASSWORD, ORG);
    }

    /**
     * 获取所有客户信息
     */
    public static String getCustomer(String myXml) throws Exception {
        SevenU9WeaverLocator locator = new SevenU9WeaverLocator();
        SevenU9WeaverSoap_PortType soap = locator.getSevenU9WeaverSoap();
        return soap.querySupplierBankAccountFromXML(myXml, USER, PASSWORD, ORG);
    }

}
