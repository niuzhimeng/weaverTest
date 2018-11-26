/**
 * LoadServiceSoap_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.zgsy.webUtil;

public interface LoadServiceSoap_PortType extends java.rmi.Remote {
    public String helloWorld() throws java.rmi.RemoteException;
    public com.weavernorth.zgsy.webUtil.MaterialInfo[] materialData(String data) throws java.rmi.RemoteException;
    public com.weavernorth.zgsy.webUtil.BaseDataInfo[] baseData(String data) throws java.rmi.RemoteException;
    public com.weavernorth.zgsy.webUtil.WebResult savePO(com.weavernorth.zgsy.webUtil.POrder data) throws java.rmi.RemoteException;
    public com.weavernorth.zgsy.webUtil.WebResult saveSO(com.weavernorth.zgsy.webUtil.SOrder data) throws java.rmi.RemoteException;
    public com.weavernorth.zgsy.webUtil.WebResult saveVoucher(com.weavernorth.zgsy.webUtil.Voucher data) throws java.rmi.RemoteException;
}
