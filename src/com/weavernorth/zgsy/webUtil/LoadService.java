/**
 * LoadService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.zgsy.webUtil;

public interface LoadService extends javax.xml.rpc.Service {
    public String getLoadServiceSoap12Address();

    public com.weavernorth.zgsy.webUtil.LoadServiceSoap_PortType getLoadServiceSoap12() throws javax.xml.rpc.ServiceException;

    public com.weavernorth.zgsy.webUtil.LoadServiceSoap_PortType getLoadServiceSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public String getLoadServiceSoapAddress();

    public com.weavernorth.zgsy.webUtil.LoadServiceSoap_PortType getLoadServiceSoap() throws javax.xml.rpc.ServiceException;

    public com.weavernorth.zgsy.webUtil.LoadServiceSoap_PortType getLoadServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
