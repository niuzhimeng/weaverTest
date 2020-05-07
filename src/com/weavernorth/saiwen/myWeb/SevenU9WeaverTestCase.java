/**
 * SevenU9WeaverTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.saiwen.myWeb;

public class SevenU9WeaverTestCase extends junit.framework.TestCase {
    public SevenU9WeaverTestCase(String name) {
        super(name);
    }

    public void testSevenU9WeaverSoap12WSDL() throws Exception {
        javax.xml.rpc.ServiceFactory serviceFactory = javax.xml.rpc.ServiceFactory.newInstance();
        java.net.URL url = new java.net.URL(new SevenU9WeaverLocator().getSevenU9WeaverSoap12Address() + "?WSDL");
        javax.xml.rpc.Service service = serviceFactory.createService(url, new SevenU9WeaverLocator().getServiceName());
        assertTrue(service != null);
    }

    public void test1SevenU9WeaverSoap12CreateCustomerFromXML() throws Exception {
        SevenU9WeaverSoap12Stub binding;
        try {
            binding = (SevenU9WeaverSoap12Stub)
                          new SevenU9WeaverLocator().getSevenU9WeaverSoap12();
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
        String value = null;
        value = binding.createCustomerFromXML(new String(), new String(), new String(), new String());
        // TBD - validate results
    }

    public void test2SevenU9WeaverSoap12CreateSupplierFromXML() throws Exception {
        SevenU9WeaverSoap12Stub binding;
        try {
            binding = (SevenU9WeaverSoap12Stub)
                          new SevenU9WeaverLocator().getSevenU9WeaverSoap12();
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
        String value = null;
        value = binding.createSupplierFromXML(new String(), new String(), new String(), new String());
        // TBD - validate results
    }

    public void test3SevenU9WeaverSoap12QueryAccountFromXML() throws Exception {
        SevenU9WeaverSoap12Stub binding;
        try {
            binding = (SevenU9WeaverSoap12Stub)
                          new SevenU9WeaverLocator().getSevenU9WeaverSoap12();
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
        String value = null;
        value = binding.queryAccountFromXML(new String(), new String(), new String(), new String());
        // TBD - validate results
    }

    public void test4SevenU9WeaverSoap12QuerySupplierBankAccountFromXML() throws Exception {
        SevenU9WeaverSoap12Stub binding;
        try {
            binding = (SevenU9WeaverSoap12Stub)
                          new SevenU9WeaverLocator().getSevenU9WeaverSoap12();
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
        String value = null;
        value = binding.querySupplierBankAccountFromXML(new String(), new String(), new String(), new String());
        // TBD - validate results
    }

    public void test5SevenU9WeaverSoap12CreateSPAdjustFromXML() throws Exception {
        SevenU9WeaverSoap12Stub binding;
        try {
            binding = (SevenU9WeaverSoap12Stub)
                          new SevenU9WeaverLocator().getSevenU9WeaverSoap12();
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
        String value = null;
        value = binding.createSPAdjustFromXML(new String(), new String(), new String(), new String());
        // TBD - validate results
    }

    public void test6SevenU9WeaverSoap12CreateVoucherFromXML() throws Exception {
        SevenU9WeaverSoap12Stub binding;
        try {
            binding = (SevenU9WeaverSoap12Stub)
                          new SevenU9WeaverLocator().getSevenU9WeaverSoap12();
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
        String value = null;
        value = binding.createVoucherFromXML(new String(), new String(), new String(), new String());
        // TBD - validate results
    }

    public void test7SevenU9WeaverSoap12CreateCustomerBankAccountFromXML() throws Exception {
        SevenU9WeaverSoap12Stub binding;
        try {
            binding = (SevenU9WeaverSoap12Stub)
                          new SevenU9WeaverLocator().getSevenU9WeaverSoap12();
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
        String value = null;
        value = binding.createCustomerBankAccountFromXML(new String(), new String(), new String(), new String());
        // TBD - validate results
    }

    public void test8SevenU9WeaverSoap12CreateAddressFromXML() throws Exception {
        SevenU9WeaverSoap12Stub binding;
        try {
            binding = (SevenU9WeaverSoap12Stub)
                          new SevenU9WeaverLocator().getSevenU9WeaverSoap12();
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
        String value = null;
        value = binding.createAddressFromXML(new String(), new String(), new String(), new String());
        // TBD - validate results
    }

    public void test9SevenU9WeaverSoap12CreateContanctFromXML() throws Exception {
        SevenU9WeaverSoap12Stub binding;
        try {
            binding = (SevenU9WeaverSoap12Stub)
                          new SevenU9WeaverLocator().getSevenU9WeaverSoap12();
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
        String value = null;
        value = binding.createContanctFromXML(new String(), new String(), new String(), new String());
        // TBD - validate results
    }

    public void test10SevenU9WeaverSoap12CreatepayBillFromXML() throws Exception {
        SevenU9WeaverSoap12Stub binding;
        try {
            binding = (SevenU9WeaverSoap12Stub)
                          new SevenU9WeaverLocator().getSevenU9WeaverSoap12();
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
        String value = null;
        value = binding.createpayBillFromXML(new String(), new String(), new String(), new String());
        // TBD - validate results
    }

    public void test11SevenU9WeaverSoap12CreateSupplierBankAccountFromXML() throws Exception {
        SevenU9WeaverSoap12Stub binding;
        try {
            binding = (SevenU9WeaverSoap12Stub)
                          new SevenU9WeaverLocator().getSevenU9WeaverSoap12();
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
        String value = null;
        value = binding.createSupplierBankAccountFromXML(new String(), new String(), new String(), new String());
        // TBD - validate results
    }

    public void test12SevenU9WeaverSoap12GetAccountRemainFromXML() throws Exception {
        SevenU9WeaverSoap12Stub binding;
        try {
            binding = (SevenU9WeaverSoap12Stub)
                          new SevenU9WeaverLocator().getSevenU9WeaverSoap12();
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
        String value = null;
        value = binding.getAccountRemainFromXML(new String(), new String(), new String(), new String());
        // TBD - validate results
    }

    public void testSevenU9WeaverSoapWSDL() throws Exception {
        javax.xml.rpc.ServiceFactory serviceFactory = javax.xml.rpc.ServiceFactory.newInstance();
        java.net.URL url = new java.net.URL(new SevenU9WeaverLocator().getSevenU9WeaverSoapAddress() + "?WSDL");
        javax.xml.rpc.Service service = serviceFactory.createService(url, new SevenU9WeaverLocator().getServiceName());
        assertTrue(service != null);
    }

    public void test13SevenU9WeaverSoapCreateCustomerFromXML() throws Exception {
        SevenU9WeaverSoap_BindingStub binding;
        try {
            binding = (SevenU9WeaverSoap_BindingStub)
                          new SevenU9WeaverLocator().getSevenU9WeaverSoap();
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
        String value = null;
        value = binding.createCustomerFromXML(new String(), new String(), new String(), new String());
        // TBD - validate results
    }

    public void test14SevenU9WeaverSoapCreateSupplierFromXML() throws Exception {
        SevenU9WeaverSoap_BindingStub binding;
        try {
            binding = (SevenU9WeaverSoap_BindingStub)
                          new SevenU9WeaverLocator().getSevenU9WeaverSoap();
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
        String value = null;
        value = binding.createSupplierFromXML(new String(), new String(), new String(), new String());
        // TBD - validate results
    }

    public void test15SevenU9WeaverSoapQueryAccountFromXML() throws Exception {
        SevenU9WeaverSoap_BindingStub binding;
        try {
            binding = (SevenU9WeaverSoap_BindingStub)
                          new SevenU9WeaverLocator().getSevenU9WeaverSoap();
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
        String value = null;
        value = binding.queryAccountFromXML(new String(), new String(), new String(), new String());
        // TBD - validate results
    }

    public void test16SevenU9WeaverSoapQuerySupplierBankAccountFromXML() throws Exception {
        SevenU9WeaverSoap_BindingStub binding;
        try {
            binding = (SevenU9WeaverSoap_BindingStub)
                          new SevenU9WeaverLocator().getSevenU9WeaverSoap();
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
        String value = null;
        value = binding.querySupplierBankAccountFromXML(new String(), new String(), new String(), new String());
        // TBD - validate results
    }

    public void test17SevenU9WeaverSoapCreateSPAdjustFromXML() throws Exception {
        SevenU9WeaverSoap_BindingStub binding;
        try {
            binding = (SevenU9WeaverSoap_BindingStub)
                          new SevenU9WeaverLocator().getSevenU9WeaverSoap();
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
        String value = null;
        value = binding.createSPAdjustFromXML(new String(), new String(), new String(), new String());
        // TBD - validate results
    }

    public void test18SevenU9WeaverSoapCreateVoucherFromXML() throws Exception {
        SevenU9WeaverSoap_BindingStub binding;
        try {
            binding = (SevenU9WeaverSoap_BindingStub)
                          new SevenU9WeaverLocator().getSevenU9WeaverSoap();
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
        String value = null;
        value = binding.createVoucherFromXML(new String(), new String(), new String(), new String());
        // TBD - validate results
    }

    public void test19SevenU9WeaverSoapCreateCustomerBankAccountFromXML() throws Exception {
        SevenU9WeaverSoap_BindingStub binding;
        try {
            binding = (SevenU9WeaverSoap_BindingStub)
                          new SevenU9WeaverLocator().getSevenU9WeaverSoap();
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
        String value = null;
        value = binding.createCustomerBankAccountFromXML(new String(), new String(), new String(), new String());
        // TBD - validate results
    }

    public void test20SevenU9WeaverSoapCreateAddressFromXML() throws Exception {
        SevenU9WeaverSoap_BindingStub binding;
        try {
            binding = (SevenU9WeaverSoap_BindingStub)
                          new SevenU9WeaverLocator().getSevenU9WeaverSoap();
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
        String value = null;
        value = binding.createAddressFromXML(new String(), new String(), new String(), new String());
        // TBD - validate results
    }

    public void test21SevenU9WeaverSoapCreateContanctFromXML() throws Exception {
        SevenU9WeaverSoap_BindingStub binding;
        try {
            binding = (SevenU9WeaverSoap_BindingStub)
                          new SevenU9WeaverLocator().getSevenU9WeaverSoap();
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
        String value = null;
        value = binding.createContanctFromXML(new String(), new String(), new String(), new String());
        // TBD - validate results
    }

    public void test22SevenU9WeaverSoapCreatepayBillFromXML() throws Exception {
        SevenU9WeaverSoap_BindingStub binding;
        try {
            binding = (SevenU9WeaverSoap_BindingStub)
                          new SevenU9WeaverLocator().getSevenU9WeaverSoap();
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
        String value = null;
        value = binding.createpayBillFromXML(new String(), new String(), new String(), new String());
        // TBD - validate results
    }

    public void test23SevenU9WeaverSoapCreateSupplierBankAccountFromXML() throws Exception {
        SevenU9WeaverSoap_BindingStub binding;
        try {
            binding = (SevenU9WeaverSoap_BindingStub)
                          new SevenU9WeaverLocator().getSevenU9WeaverSoap();
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
        String value = null;
        value = binding.createSupplierBankAccountFromXML(new String(), new String(), new String(), new String());
        // TBD - validate results
    }

    public void test24SevenU9WeaverSoapGetAccountRemainFromXML() throws Exception {
        SevenU9WeaverSoap_BindingStub binding;
        try {
            binding = (SevenU9WeaverSoap_BindingStub)
                          new SevenU9WeaverLocator().getSevenU9WeaverSoap();
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
        String value = null;
        value = binding.getAccountRemainFromXML(new String(), new String(), new String(), new String());
        // TBD - validate results
    }

}
