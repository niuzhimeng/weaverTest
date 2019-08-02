/**
 * SevenU9WeaverLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.saiwen.myWeb;

public class SevenU9WeaverLocator extends org.apache.axis.client.Service implements com.weavernorth.saiwen.myWeb.SevenU9Weaver {

    public SevenU9WeaverLocator() {
    }


    public SevenU9WeaverLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SevenU9WeaverLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SevenU9WeaverSoap12
    private java.lang.String SevenU9WeaverSoap12_address = "http://60.28.102.130/SVUW/SevenU9Weaver.asmx";

    public java.lang.String getSevenU9WeaverSoap12Address() {
        return SevenU9WeaverSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SevenU9WeaverSoap12WSDDServiceName = "SevenU9WeaverSoap12";

    public java.lang.String getSevenU9WeaverSoap12WSDDServiceName() {
        return SevenU9WeaverSoap12WSDDServiceName;
    }

    public void setSevenU9WeaverSoap12WSDDServiceName(java.lang.String name) {
        SevenU9WeaverSoap12WSDDServiceName = name;
    }

    public com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap_PortType getSevenU9WeaverSoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SevenU9WeaverSoap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSevenU9WeaverSoap12(endpoint);
    }

    public com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap_PortType getSevenU9WeaverSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap12Stub _stub = new com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap12Stub(portAddress, this);
            _stub.setPortName(getSevenU9WeaverSoap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSevenU9WeaverSoap12EndpointAddress(java.lang.String address) {
        SevenU9WeaverSoap12_address = address;
    }


    // Use to get a proxy class for SevenU9WeaverSoap
    private java.lang.String SevenU9WeaverSoap_address = "http://60.28.102.130/SVUW/SevenU9Weaver.asmx";

    public java.lang.String getSevenU9WeaverSoapAddress() {
        return SevenU9WeaverSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SevenU9WeaverSoapWSDDServiceName = "SevenU9WeaverSoap";

    public java.lang.String getSevenU9WeaverSoapWSDDServiceName() {
        return SevenU9WeaverSoapWSDDServiceName;
    }

    public void setSevenU9WeaverSoapWSDDServiceName(java.lang.String name) {
        SevenU9WeaverSoapWSDDServiceName = name;
    }

    public com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap_PortType getSevenU9WeaverSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SevenU9WeaverSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSevenU9WeaverSoap(endpoint);
    }

    public com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap_PortType getSevenU9WeaverSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap_BindingStub _stub = new com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap_BindingStub(portAddress, this);
            _stub.setPortName(getSevenU9WeaverSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSevenU9WeaverSoapEndpointAddress(java.lang.String address) {
        SevenU9WeaverSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     * This service has multiple ports for a given interface;
     * the proxy implementation returned may be indeterminate.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap12Stub _stub = new com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap12Stub(new java.net.URL(SevenU9WeaverSoap12_address), this);
                _stub.setPortName(getSevenU9WeaverSoap12WSDDServiceName());
                return _stub;
            }
            if (com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap_BindingStub _stub = new com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap_BindingStub(new java.net.URL(SevenU9WeaverSoap_address), this);
                _stub.setPortName(getSevenU9WeaverSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
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
        java.lang.String inputPortName = portName.getLocalPart();
        if ("SevenU9WeaverSoap12".equals(inputPortName)) {
            return getSevenU9WeaverSoap12();
        }
        else if ("SevenU9WeaverSoap".equals(inputPortName)) {
            return getSevenU9WeaverSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "SevenU9Weaver");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "SevenU9WeaverSoap12"));
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "SevenU9WeaverSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SevenU9WeaverSoap12".equals(portName)) {
            setSevenU9WeaverSoap12EndpointAddress(address);
        }
        else 
if ("SevenU9WeaverSoap".equals(portName)) {
            setSevenU9WeaverSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
