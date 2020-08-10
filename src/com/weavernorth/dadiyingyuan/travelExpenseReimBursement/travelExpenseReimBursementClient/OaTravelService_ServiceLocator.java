/**
 * OaTravelService_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.dadiyingyuan.travelExpenseReimBursement.travelExpenseReimBursementClient;

public class OaTravelService_ServiceLocator extends org.apache.axis.client.Service implements OaTravelService_Service {

    public OaTravelService_ServiceLocator() {
    }


    public OaTravelService_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public OaTravelService_ServiceLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for OaTravelServiceImplPort
    //测试地址   59.110.236.229
    //正式地址   39.105.96.67
    private String OaTravelServiceImplPort_address = "http://59.110.236.229:8080/oa/travel";

    public String getOaTravelServiceImplPortAddress() {
        return OaTravelServiceImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private String OaTravelServiceImplPortWSDDServiceName = "OaTravelServiceImplPort";

    public String getOaTravelServiceImplPortWSDDServiceName() {
        return OaTravelServiceImplPortWSDDServiceName;
    }

    public void setOaTravelServiceImplPortWSDDServiceName(String name) {
        OaTravelServiceImplPortWSDDServiceName = name;
    }

    public OaTravelService_PortType getOaTravelServiceImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(OaTravelServiceImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getOaTravelServiceImplPort(endpoint);
    }

    public OaTravelService_PortType getOaTravelServiceImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.weavernorth.dadiyingyuan.travelExpenseReimBursement.travelExpenseReimBursementClient.OaTravelServiceSoapBindingStub _stub = new com.weavernorth.dadiyingyuan.travelExpenseReimBursement.travelExpenseReimBursementClient.OaTravelServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getOaTravelServiceImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setOaTravelServiceImplPortEndpointAddress(String address) {
        OaTravelServiceImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (OaTravelService_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.weavernorth.dadiyingyuan.travelExpenseReimBursement.travelExpenseReimBursementClient.OaTravelServiceSoapBindingStub _stub = new com.weavernorth.dadiyingyuan.travelExpenseReimBursement.travelExpenseReimBursementClient.OaTravelServiceSoapBindingStub(new java.net.URL(OaTravelServiceImplPort_address), this);
                _stub.setPortName(getOaTravelServiceImplPortWSDDServiceName());
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
        if ("OaTravelServiceImplPort".equals(inputPortName)) {
            return getOaTravelServiceImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://vo.dadimedia.com/", "OaTravelService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://vo.dadimedia.com/", "OaTravelServiceImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {
        
if ("OaTravelServiceImplPort".equals(portName)) {
            setOaTravelServiceImplPortEndpointAddress(address);
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
