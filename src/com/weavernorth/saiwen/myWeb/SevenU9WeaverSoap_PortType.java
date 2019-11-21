/**
 * SevenU9WeaverSoap_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.saiwen.myWeb;

public interface SevenU9WeaverSoap_PortType extends java.rmi.Remote {

    /**
     * 创建客户
     */
    public java.lang.String createCustomerFromXML(java.lang.String customerXml, java.lang.String u9User, java.lang.String u9Pwd, java.lang.String u9Org) throws java.rmi.RemoteException;

    /**
     * 创建供应商
     */
    public java.lang.String createSupplierFromXML(java.lang.String supplierXml, java.lang.String u9User, java.lang.String u9Pwd, java.lang.String u9Org) throws java.rmi.RemoteException;

    /**
     * 查询科目
     */
    public java.lang.String queryAccountFromXML(java.lang.String xml, java.lang.String u9User, java.lang.String u9Pwd, java.lang.String u9Org) throws java.rmi.RemoteException;

    /**
     * 查询供应商银行账号
     */
    public java.lang.String querySupplierBankAccountFromXML(java.lang.String xml, java.lang.String u9User, java.lang.String u9Pwd, java.lang.String u9Org) throws java.rmi.RemoteException;

    /**
     * 销售价格调整单
     */
    public java.lang.String createSPAdjustFromXML(java.lang.String xml, java.lang.String u9User, java.lang.String u9Pwd, java.lang.String u9Org) throws java.rmi.RemoteException;

    /**
     * 凭证创建
     */
    public java.lang.String createVoucherFromXML(java.lang.String xml, java.lang.String u9User, java.lang.String u9Pwd, java.lang.String u9Org) throws java.rmi.RemoteException;

    /**
     * 创建文件 --分步1 返回文件id
     */
    public java.lang.String u9FileCreate(java.lang.String fileName) throws java.rmi.RemoteException;

    /**
     * 上传文件--分步2
     */
    public boolean u9FileAppend(java.lang.String fileName, byte[] buffer, java.lang.String fileDbID) throws java.rmi.RemoteException;

    /**
     * 上传验证--分步3
     */
    public boolean u9FileVerify(java.lang.String xml, java.lang.String u9User, java.lang.String u9Pwd, java.lang.String u9Org) throws java.rmi.RemoteException;

    /**
     * 创建客户银行账号
     */
    public java.lang.String createCustomerBankAccountFromXML(java.lang.String xml, java.lang.String u9User, java.lang.String u9Pwd, java.lang.String u9Org) throws java.rmi.RemoteException;

    /**
     * 创建地址
     */
    public java.lang.String createAddressFromXML(java.lang.String xml, java.lang.String u9User, java.lang.String u9Pwd, java.lang.String u9Org) throws java.rmi.RemoteException;

    /**
     * 创建联系对象
     */
    public java.lang.String createContanctFromXML(java.lang.String xml, java.lang.String u9User, java.lang.String u9Pwd, java.lang.String u9Org) throws java.rmi.RemoteException;
}
