/**
 * OaLoanService_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.dadiyingyuan.brrowFeeForm.brrowFeeFormClient;

public class OaLoanService_ServiceLocator extends org.apache.axis.client.Service implements OaLoanService_Service {

    public OaLoanService_ServiceLocator() {
    }


    public OaLoanService_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public OaLoanService_ServiceLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for OaLoanServiceImplPort
    private String OaLoanServiceImplPort_address = "http://59.110.236.229:8080/oa/loan";

    public String getOaLoanServiceImplPortAddress() {
        return OaLoanServiceImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private String OaLoanServiceImplPortWSDDServiceName = "OaLoanServiceImplPort";

    public String getOaLoanServiceImplPortWSDDServiceName() {
        return OaLoanServiceImplPortWSDDServiceName;
    }

    public void setOaLoanServiceImplPortWSDDServiceName(String name) {
        OaLoanServiceImplPortWSDDServiceName = name;
    }

    public OaLoanService_PortType getOaLoanServiceImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(OaLoanServiceImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getOaLoanServiceImplPort(endpoint);
    }

    public OaLoanService_PortType getOaLoanServiceImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.weavernorth.dadiyingyuan.brrowFeeForm.brrowFeeFormClient.OaLoanServiceSoapBindingStub _stub = new com.weavernorth.dadiyingyuan.brrowFeeForm.brrowFeeFormClient.OaLoanServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getOaLoanServiceImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setOaLoanServiceImplPortEndpointAddress(String address) {
        OaLoanServiceImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (OaLoanService_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.weavernorth.dadiyingyuan.brrowFeeForm.brrowFeeFormClient.OaLoanServiceSoapBindingStub _stub = new com.weavernorth.dadiyingyuan.brrowFeeForm.brrowFeeFormClient.OaLoanServiceSoapBindingStub(new java.net.URL(OaLoanServiceImplPort_address), this);
                _stub.setPortName(getOaLoanServiceImplPortWSDDServiceName());
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
        if ("OaLoanServiceImplPort".equals(inputPortName)) {
            return getOaLoanServiceImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://vo.dadimedia.com/", "OaLoanService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://vo.dadimedia.com/", "OaLoanServiceImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {
        
if ("OaLoanServiceImplPort".equals(portName)) {
            setOaLoanServiceImplPortEndpointAddress(address);
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
