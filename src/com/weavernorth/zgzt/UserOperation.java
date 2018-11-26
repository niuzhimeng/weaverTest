/**
 * UserOperation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.zgzt;

public interface UserOperation extends java.rmi.Remote {
    public com.weavernorth.zgzt.UserLogList[] userLog(com.weavernorth.zgzt.UserLogRequest userLogRequest) throws java.rmi.RemoteException;
    public com.weavernorth.zgzt.UserLogPageResponse userLogPage(com.weavernorth.zgzt.UserLogPageRequest userLogPageRequest) throws java.rmi.RemoteException;
    public void process(com.weavernorth.zgzt.ProcessRequest processRequest) throws java.rmi.RemoteException;
}
