/**
 * OaExpendService_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.dadiyingyuan.feeOutForm.feeOutClient;

public class OaExpendService_ServiceLocator extends org.apache.axis.client.Service implements OaExpendService_Service {

    public OaExpendService_ServiceLocator() {
    }


    public OaExpendService_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public OaExpendService_ServiceLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for OaExpendServiceImplPort
    private String OaExpendServiceImplPort_address = "http://59.110.236.229:8080/oa/expend";

    public String getOaExpendServiceImplPortAddress() {
        return OaExpendServiceImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private String OaExpendServiceImplPortWSDDServiceName = "OaExpendServiceImplPort";

    public String getOaExpendServiceImplPortWSDDServiceName() {
        return OaExpendServiceImplPortWSDDServiceName;
    }

    public void setOaExpendServiceImplPortWSDDServiceName(String name) {
        OaExpendServiceImplPortWSDDServiceName = name;
    }

    public OaExpendService_PortType getOaExpendServiceImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(OaExpendServiceImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getOaExpendServiceImplPort(endpoint);
    }

    public OaExpendService_PortType getOaExpendServiceImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.weavernorth.dadiyingyuan.feeOutForm.feeOutClient.OaExpendServiceSoapBindingStub _stub = new com.weavernorth.dadiyingyuan.feeOutForm.feeOutClient.OaExpendServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getOaExpendServiceImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setOaExpendServiceImplPortEndpointAddress(String address) {
        OaExpendServiceImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (OaExpendService_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.weavernorth.dadiyingyuan.feeOutForm.feeOutClient.OaExpendServiceSoapBindingStub _stub = new com.weavernorth.dadiyingyuan.feeOutForm.feeOutClient.OaExpendServiceSoapBindingStub(new java.net.URL(OaExpendServiceImplPort_address), this);
                _stub.setPortName(getOaExpendServiceImplPortWSDDServiceName());
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
        if ("OaExpendServiceImplPort".equals(inputPortName)) {
            return getOaExpendServiceImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://vo.dadimedia.com/", "OaExpendService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://vo.dadimedia.com/", "OaExpendServiceImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {
        
if ("OaExpendServiceImplPort".equals(portName)) {
            setOaExpendServiceImplPortEndpointAddress(address);
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
