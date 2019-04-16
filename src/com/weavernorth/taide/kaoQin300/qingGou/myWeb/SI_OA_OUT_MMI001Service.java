/**
 * SI_OA_OUT_MMI001Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.taide.kaoQin300.qingGou.myWeb;

public interface SI_OA_OUT_MMI001Service extends javax.xml.rpc.Service {
    public String getHTTPS_PortAddress();

    public SI_OA_OUT_MMI001 getHTTPS_Port() throws javax.xml.rpc.ServiceException;

    public SI_OA_OUT_MMI001 getHTTPS_Port(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public String getHTTP_PortAddress();

    public SI_OA_OUT_MMI001 getHTTP_Port() throws javax.xml.rpc.ServiceException;

    public SI_OA_OUT_MMI001 getHTTP_Port(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
