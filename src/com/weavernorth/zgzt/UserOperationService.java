/**
 * UserOperationService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.zgzt;

public interface UserOperationService extends javax.xml.rpc.Service {
    public String getUserOperationSoap11Address();

    public com.weavernorth.zgzt.UserOperation getUserOperationSoap11() throws javax.xml.rpc.ServiceException;

    public com.weavernorth.zgzt.UserOperation getUserOperationSoap11(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
