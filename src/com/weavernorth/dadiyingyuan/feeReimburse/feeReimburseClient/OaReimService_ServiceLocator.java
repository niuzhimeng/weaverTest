/**
 * OaReimService_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.dadiyingyuan.feeReimburse.feeReimburseClient;

public class OaReimService_ServiceLocator extends org.apache.axis.client.Service implements OaReimService_Service {

    public OaReimService_ServiceLocator() {
    }


    public OaReimService_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public OaReimService_ServiceLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for OaReimServiceImplPort
    private String OaReimServiceImplPort_address = "http://59.110.236.229:8080/oa/reim";

    public String getOaReimServiceImplPortAddress() {
        return OaReimServiceImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private String OaReimServiceImplPortWSDDServiceName = "OaReimServiceImplPort";

    public String getOaReimServiceImplPortWSDDServiceName() {
        return OaReimServiceImplPortWSDDServiceName;
    }

    public void setOaReimServiceImplPortWSDDServiceName(String name) {
        OaReimServiceImplPortWSDDServiceName = name;
    }

    public OaReimService_PortType getOaReimServiceImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(OaReimServiceImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getOaReimServiceImplPort(endpoint);
    }

    public OaReimService_PortType getOaReimServiceImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.weavernorth.dadiyingyuan.feeReimburse.feeReimburseClient.OaReimServiceSoapBindingStub _stub = new com.weavernorth.dadiyingyuan.feeReimburse.feeReimburseClient.OaReimServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getOaReimServiceImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setOaReimServiceImplPortEndpointAddress(String address) {
        OaReimServiceImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (OaReimService_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.weavernorth.dadiyingyuan.feeReimburse.feeReimburseClient.OaReimServiceSoapBindingStub _stub = new com.weavernorth.dadiyingyuan.feeReimburse.feeReimburseClient.OaReimServiceSoapBindingStub(new java.net.URL(OaReimServiceImplPort_address), this);
                _stub.setPortName(getOaReimServiceImplPortWSDDServiceName());
                return _stub;
            }
        }
        catch (Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("OaReimServiceImplPort".equals(inputPortName)) {
            return getOaReimServiceImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://vo.dadimedia.com/", "OaReimService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://vo.dadimedia.com/", "OaReimServiceImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {
        
if ("OaReimServiceImplPort".equals(portName)) {
            setOaReimServiceImplPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
