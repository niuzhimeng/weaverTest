/**
 * SI_YT_OUT_HRI011ServiceTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.taide.kaoQin.jbxx.myWeb;

public class SI_YT_OUT_HRI011ServiceTestCase extends junit.framework.TestCase {
    public SI_YT_OUT_HRI011ServiceTestCase(java.lang.String name) {
        super(name);
    }

    public void testHTTPS_PortWSDL() throws Exception {
        javax.xml.rpc.ServiceFactory serviceFactory = javax.xml.rpc.ServiceFactory.newInstance();
        java.net.URL url = new java.net.URL(new com.weavernorth.taide.kaoQin.jbxx.myWeb.SI_YT_OUT_HRI011ServiceLocator().getHTTPS_PortAddress() + "?WSDL");
        javax.xml.rpc.Service service = serviceFactory.createService(url, new com.weavernorth.taide.kaoQin.jbxx.myWeb.SI_YT_OUT_HRI011ServiceLocator().getServiceName());
        assertTrue(service != null);
    }

    public void test1HTTPS_PortSI_YT_OUT_HRI011() throws Exception {
        com.weavernorth.taide.kaoQin.jbxx.myWeb.SI_YT_OUT_HRI011BindingStub binding;
        try {
            binding = (com.weavernorth.taide.kaoQin.jbxx.myWeb.SI_YT_OUT_HRI011BindingStub)
                          new com.weavernorth.taide.kaoQin.jbxx.myWeb.SI_YT_OUT_HRI011ServiceLocator().getHTTPS_Port();
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
        com.weavernorth.taide.kaoQin.jbxx.myWeb.DT_HRI011_OUTRETURN[] value = null;
        value = binding.SI_YT_OUT_HRI011(new com.weavernorth.taide.kaoQin.jbxx.myWeb.DT_HRI011_IN());
        // TBD - validate results
    }

    public void testHTTP_PortWSDL() throws Exception {
        javax.xml.rpc.ServiceFactory serviceFactory = javax.xml.rpc.ServiceFactory.newInstance();
        java.net.URL url = new java.net.URL(new com.weavernorth.taide.kaoQin.jbxx.myWeb.SI_YT_OUT_HRI011ServiceLocator().getHTTP_PortAddress() + "?WSDL");
        javax.xml.rpc.Service service = serviceFactory.createService(url, new com.weavernorth.taide.kaoQin.jbxx.myWeb.SI_YT_OUT_HRI011ServiceLocator().getServiceName());
        assertTrue(service != null);
    }

    public void test2HTTP_PortSI_YT_OUT_HRI011() throws Exception {
        com.weavernorth.taide.kaoQin.jbxx.myWeb.SI_YT_OUT_HRI011BindingStub binding;
        try {
            binding = (com.weavernorth.taide.kaoQin.jbxx.myWeb.SI_YT_OUT_HRI011BindingStub)
                          new com.weavernorth.taide.kaoQin.jbxx.myWeb.SI_YT_OUT_HRI011ServiceLocator().getHTTP_Port();
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
        com.weavernorth.taide.kaoQin.jbxx.myWeb.DT_HRI011_OUTRETURN[] value = null;
        value = binding.SI_YT_OUT_HRI011(new com.weavernorth.taide.kaoQin.jbxx.myWeb.DT_HRI011_IN());
        // TBD - validate results
    }

}
