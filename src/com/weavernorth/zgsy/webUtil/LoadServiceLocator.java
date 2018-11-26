/**
 * LoadServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.zgsy.webUtil;

public class LoadServiceLocator extends org.apache.axis.client.Service implements com.weavernorth.zgsy.webUtil.LoadService {

    public LoadServiceLocator() {
    }


    public LoadServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public LoadServiceLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for LoadServiceSoap12
    private String LoadServiceSoap12_address = "http://106.38.99.203:8888/ZGSYWebService_iis/LoadService.asmx";

    public String getLoadServiceSoap12Address() {
        return LoadServiceSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private String LoadServiceSoap12WSDDServiceName = "LoadServiceSoap12";

    public String getLoadServiceSoap12WSDDServiceName() {
        return LoadServiceSoap12WSDDServiceName;
    }

    public void setLoadServiceSoap12WSDDServiceName(String name) {
        LoadServiceSoap12WSDDServiceName = name;
    }

    public com.weavernorth.zgsy.webUtil.LoadServiceSoap_PortType getLoadServiceSoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(LoadServiceSoap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getLoadServiceSoap12(endpoint);
    }

    public com.weavernorth.zgsy.webUtil.LoadServiceSoap_PortType getLoadServiceSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.weavernorth.zgsy.webUtil.LoadServiceSoap12Stub _stub = new com.weavernorth.zgsy.webUtil.LoadServiceSoap12Stub(portAddress, this);
            _stub.setPortName(getLoadServiceSoap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setLoadServiceSoap12EndpointAddress(String address) {
        LoadServiceSoap12_address = address;
    }


    // Use to get a proxy class for LoadServiceSoap
    private String LoadServiceSoap_address = "http://106.38.99.203:8888/ZGSYWebService_iis/LoadService.asmx";

    public String getLoadServiceSoapAddress() {
        return LoadServiceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private String LoadServiceSoapWSDDServiceName = "LoadServiceSoap";

    public String getLoadServiceSoapWSDDServiceName() {
        return LoadServiceSoapWSDDServiceName;
    }

    public void setLoadServiceSoapWSDDServiceName(String name) {
        LoadServiceSoapWSDDServiceName = name;
    }

    public com.weavernorth.zgsy.webUtil.LoadServiceSoap_PortType getLoadServiceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(LoadServiceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getLoadServiceSoap(endpoint);
    }

    public com.weavernorth.zgsy.webUtil.LoadServiceSoap_PortType getLoadServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.weavernorth.zgsy.webUtil.LoadServiceSoap_BindingStub _stub = new com.weavernorth.zgsy.webUtil.LoadServiceSoap_BindingStub(portAddress, this);
            _stub.setPortName(getLoadServiceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setLoadServiceSoapEndpointAddress(String address) {
        LoadServiceSoap_address = address;
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
            if (com.weavernorth.zgsy.webUtil.LoadServiceSoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.weavernorth.zgsy.webUtil.LoadServiceSoap12Stub _stub = new com.weavernorth.zgsy.webUtil.LoadServiceSoap12Stub(new java.net.URL(LoadServiceSoap12_address), this);
                _stub.setPortName(getLoadServiceSoap12WSDDServiceName());
                return _stub;
            }
            if (com.weavernorth.zgsy.webUtil.LoadServiceSoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.weavernorth.zgsy.webUtil.LoadServiceSoap_BindingStub _stub = new com.weavernorth.zgsy.webUtil.LoadServiceSoap_BindingStub(new java.net.URL(LoadServiceSoap_address), this);
                _stub.setPortName(getLoadServiceSoapWSDDServiceName());
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
        if ("LoadServiceSoap12".equals(inputPortName)) {
            return getLoadServiceSoap12();
        }
        else if ("LoadServiceSoap".equals(inputPortName)) {
            return getLoadServiceSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "LoadService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "LoadServiceSoap12"));
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "LoadServiceSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {

if ("LoadServiceSoap12".equals(portName)) {
            setLoadServiceSoap12EndpointAddress(address);
        }
        else
if ("LoadServiceSoap".equals(portName)) {
            setLoadServiceSoapEndpointAddress(address);
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
