/**
 * SevenU9WeaverTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.saiwen.myWeb;

public class SevenU9WeaverTestCase extends junit.framework.TestCase {
    public SevenU9WeaverTestCase(java.lang.String name) {
        super(name);
    }

    public void testSevenU9WeaverSoap12WSDL() throws Exception {
        javax.xml.rpc.ServiceFactory serviceFactory = javax.xml.rpc.ServiceFactory.newInstance();
        java.net.URL url = new java.net.URL(new com.weavernorth.saiwen.myWeb.SevenU9WeaverLocator().getSevenU9WeaverSoap12Address() + "?WSDL");
        javax.xml.rpc.Service service = serviceFactory.createService(url, new com.weavernorth.saiwen.myWeb.SevenU9WeaverLocator().getServiceName());
        assertTrue(service != null);
    }

    public void test1SevenU9WeaverSoap12CreateCustomerFromXML() throws Exception {
        com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap12Stub binding;
        try {
            binding = (com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap12Stub)
                          new com.weavernorth.saiwen.myWeb.SevenU9WeaverLocator().getSevenU9WeaverSoap12();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.createCustomerFromXML(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test2SevenU9WeaverSoap12CreateSupplierFromXML() throws Exception {
        com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap12Stub binding;
        try {
            binding = (com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap12Stub)
                          new com.weavernorth.saiwen.myWeb.SevenU9WeaverLocator().getSevenU9WeaverSoap12();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.createSupplierFromXML(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test3SevenU9WeaverSoap12QueryAccountFromXML() throws Exception {
        com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap12Stub binding;
        try {
            binding = (com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap12Stub)
                          new com.weavernorth.saiwen.myWeb.SevenU9WeaverLocator().getSevenU9WeaverSoap12();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.queryAccountFromXML(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test4SevenU9WeaverSoap12QuerySupplierBankAccountFromXML() throws Exception {
        com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap12Stub binding;
        try {
            binding = (com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap12Stub)
                          new com.weavernorth.saiwen.myWeb.SevenU9WeaverLocator().getSevenU9WeaverSoap12();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.querySupplierBankAccountFromXML(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test5SevenU9WeaverSoap12CreateSPAdjustFromXML() throws Exception {
        com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap12Stub binding;
        try {
            binding = (com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap12Stub)
                          new com.weavernorth.saiwen.myWeb.SevenU9WeaverLocator().getSevenU9WeaverSoap12();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.createSPAdjustFromXML(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test6SevenU9WeaverSoap12CreateVoucherFromXML() throws Exception {
        com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap12Stub binding;
        try {
            binding = (com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap12Stub)
                          new com.weavernorth.saiwen.myWeb.SevenU9WeaverLocator().getSevenU9WeaverSoap12();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.createVoucherFromXML(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test7SevenU9WeaverSoap12U9FileCreate() throws Exception {
        com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap12Stub binding;
        try {
            binding = (com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap12Stub)
                          new com.weavernorth.saiwen.myWeb.SevenU9WeaverLocator().getSevenU9WeaverSoap12();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.u9FileCreate(new java.lang.String());
        // TBD - validate results
    }

    public void test8SevenU9WeaverSoap12U9FileAppend() throws Exception {
        com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap12Stub binding;
        try {
            binding = (com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap12Stub)
                          new com.weavernorth.saiwen.myWeb.SevenU9WeaverLocator().getSevenU9WeaverSoap12();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        boolean value = false;
        value = binding.u9FileAppend(new java.lang.String(), new byte[0], new java.lang.String());
        // TBD - validate results
    }

    public void test9SevenU9WeaverSoap12U9FileVerify() throws Exception {
        com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap12Stub binding;
        try {
            binding = (com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap12Stub)
                          new com.weavernorth.saiwen.myWeb.SevenU9WeaverLocator().getSevenU9WeaverSoap12();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        boolean value = false;
        value = binding.u9FileVerify(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test10SevenU9WeaverSoap12CreateCustomerBankAccountFromXML() throws Exception {
        com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap12Stub binding;
        try {
            binding = (com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap12Stub)
                          new com.weavernorth.saiwen.myWeb.SevenU9WeaverLocator().getSevenU9WeaverSoap12();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.createCustomerBankAccountFromXML(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test11SevenU9WeaverSoap12CreateAddressFromXML() throws Exception {
        com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap12Stub binding;
        try {
            binding = (com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap12Stub)
                          new com.weavernorth.saiwen.myWeb.SevenU9WeaverLocator().getSevenU9WeaverSoap12();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.createAddressFromXML(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test12SevenU9WeaverSoap12CreateContanctFromXML() throws Exception {
        com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap12Stub binding;
        try {
            binding = (com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap12Stub)
                          new com.weavernorth.saiwen.myWeb.SevenU9WeaverLocator().getSevenU9WeaverSoap12();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.createContanctFromXML(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void testSevenU9WeaverSoapWSDL() throws Exception {
        javax.xml.rpc.ServiceFactory serviceFactory = javax.xml.rpc.ServiceFactory.newInstance();
        java.net.URL url = new java.net.URL(new com.weavernorth.saiwen.myWeb.SevenU9WeaverLocator().getSevenU9WeaverSoapAddress() + "?WSDL");
        javax.xml.rpc.Service service = serviceFactory.createService(url, new com.weavernorth.saiwen.myWeb.SevenU9WeaverLocator().getServiceName());
        assertTrue(service != null);
    }

    public void test13SevenU9WeaverSoapCreateCustomerFromXML() throws Exception {
        com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap_BindingStub binding;
        try {
            binding = (com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap_BindingStub)
                          new com.weavernorth.saiwen.myWeb.SevenU9WeaverLocator().getSevenU9WeaverSoap();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.createCustomerFromXML(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test14SevenU9WeaverSoapCreateSupplierFromXML() throws Exception {
        com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap_BindingStub binding;
        try {
            binding = (com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap_BindingStub)
                          new com.weavernorth.saiwen.myWeb.SevenU9WeaverLocator().getSevenU9WeaverSoap();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.createSupplierFromXML(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test15SevenU9WeaverSoapQueryAccountFromXML() throws Exception {
        com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap_BindingStub binding;
        try {
            binding = (com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap_BindingStub)
                          new com.weavernorth.saiwen.myWeb.SevenU9WeaverLocator().getSevenU9WeaverSoap();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.queryAccountFromXML(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test16SevenU9WeaverSoapQuerySupplierBankAccountFromXML() throws Exception {
        com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap_BindingStub binding;
        try {
            binding = (com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap_BindingStub)
                          new com.weavernorth.saiwen.myWeb.SevenU9WeaverLocator().getSevenU9WeaverSoap();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.querySupplierBankAccountFromXML(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test17SevenU9WeaverSoapCreateSPAdjustFromXML() throws Exception {
        com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap_BindingStub binding;
        try {
            binding = (com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap_BindingStub)
                          new com.weavernorth.saiwen.myWeb.SevenU9WeaverLocator().getSevenU9WeaverSoap();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.createSPAdjustFromXML(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test18SevenU9WeaverSoapCreateVoucherFromXML() throws Exception {
        com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap_BindingStub binding;
        try {
            binding = (com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap_BindingStub)
                          new com.weavernorth.saiwen.myWeb.SevenU9WeaverLocator().getSevenU9WeaverSoap();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.createVoucherFromXML(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test19SevenU9WeaverSoapU9FileCreate() throws Exception {
        com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap_BindingStub binding;
        try {
            binding = (com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap_BindingStub)
                          new com.weavernorth.saiwen.myWeb.SevenU9WeaverLocator().getSevenU9WeaverSoap();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.u9FileCreate(new java.lang.String());
        // TBD - validate results
    }

    public void test20SevenU9WeaverSoapU9FileAppend() throws Exception {
        com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap_BindingStub binding;
        try {
            binding = (com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap_BindingStub)
                          new com.weavernorth.saiwen.myWeb.SevenU9WeaverLocator().getSevenU9WeaverSoap();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        boolean value = false;
        value = binding.u9FileAppend(new java.lang.String(), new byte[0], new java.lang.String());
        // TBD - validate results
    }

    public void test21SevenU9WeaverSoapU9FileVerify() throws Exception {
        com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap_BindingStub binding;
        try {
            binding = (com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap_BindingStub)
                          new com.weavernorth.saiwen.myWeb.SevenU9WeaverLocator().getSevenU9WeaverSoap();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        boolean value = false;
        value = binding.u9FileVerify(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test22SevenU9WeaverSoapCreateCustomerBankAccountFromXML() throws Exception {
        com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap_BindingStub binding;
        try {
            binding = (com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap_BindingStub)
                          new com.weavernorth.saiwen.myWeb.SevenU9WeaverLocator().getSevenU9WeaverSoap();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.createCustomerBankAccountFromXML(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test23SevenU9WeaverSoapCreateAddressFromXML() throws Exception {
        com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap_BindingStub binding;
        try {
            binding = (com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap_BindingStub)
                          new com.weavernorth.saiwen.myWeb.SevenU9WeaverLocator().getSevenU9WeaverSoap();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.createAddressFromXML(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test24SevenU9WeaverSoapCreateContanctFromXML() throws Exception {
        com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap_BindingStub binding;
        try {
            binding = (com.weavernorth.saiwen.myWeb.SevenU9WeaverSoap_BindingStub)
                          new com.weavernorth.saiwen.myWeb.SevenU9WeaverLocator().getSevenU9WeaverSoap();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.createContanctFromXML(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

}
