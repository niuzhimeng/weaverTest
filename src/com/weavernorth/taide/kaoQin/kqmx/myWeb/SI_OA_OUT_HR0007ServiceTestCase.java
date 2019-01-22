/**
 * SI_OA_OUT_HR0007ServiceTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.taide.kaoQin.kqmx.myWeb;

public class SI_OA_OUT_HR0007ServiceTestCase extends junit.framework.TestCase {
    public SI_OA_OUT_HR0007ServiceTestCase(java.lang.String name) {
        super(name);
    }

    public void testHTTPS_PortWSDL() throws Exception {
        javax.xml.rpc.ServiceFactory serviceFactory = javax.xml.rpc.ServiceFactory.newInstance();
        java.net.URL url = new java.net.URL(new com.weavernorth.taide.kaoQin.kqmx.myWeb.SI_OA_OUT_HR0007ServiceLocator().getHTTPS_PortAddress() + "?WSDL");
        javax.xml.rpc.Service service = serviceFactory.createService(url, new com.weavernorth.taide.kaoQin.kqmx.myWeb.SI_OA_OUT_HR0007ServiceLocator().getServiceName());
        assertTrue(service != null);
    }

    public void test1HTTPS_PortSI_OA_OUT_HR0007() throws Exception {
        com.weavernorth.taide.kaoQin.kqmx.myWeb.SI_OA_OUT_HR0007BindingStub binding;
        try {
            binding = (com.weavernorth.taide.kaoQin.kqmx.myWeb.SI_OA_OUT_HR0007BindingStub)
                          new com.weavernorth.taide.kaoQin.kqmx.myWeb.SI_OA_OUT_HR0007ServiceLocator().getHTTPS_Port();
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
        com.weavernorth.taide.kaoQin.kqmx.myWeb.DT_HRI007_OUTRETURN[] value = null;
        value = binding.SI_OA_OUT_HR0007(new com.weavernorth.taide.kaoQin.kqmx.myWeb.DT_HRI007_IN());
        // TBD - validate results
    }

    public void testHTTP_PortWSDL() throws Exception {
        javax.xml.rpc.ServiceFactory serviceFactory = javax.xml.rpc.ServiceFactory.newInstance();
        java.net.URL url = new java.net.URL(new com.weavernorth.taide.kaoQin.kqmx.myWeb.SI_OA_OUT_HR0007ServiceLocator().getHTTP_PortAddress() + "?WSDL");
        javax.xml.rpc.Service service = serviceFactory.createService(url, new com.weavernorth.taide.kaoQin.kqmx.myWeb.SI_OA_OUT_HR0007ServiceLocator().getServiceName());
        assertTrue(service != null);
    }

    public void test2HTTP_PortSI_OA_OUT_HR0007() throws Exception {
        com.weavernorth.taide.kaoQin.kqmx.myWeb.SI_OA_OUT_HR0007BindingStub binding;
        try {
            binding = (com.weavernorth.taide.kaoQin.kqmx.myWeb.SI_OA_OUT_HR0007BindingStub)
                          new com.weavernorth.taide.kaoQin.kqmx.myWeb.SI_OA_OUT_HR0007ServiceLocator().getHTTP_Port();
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
        com.weavernorth.taide.kaoQin.kqmx.myWeb.DT_HRI007_OUTRETURN[] value = null;
        value = binding.SI_OA_OUT_HR0007(new com.weavernorth.taide.kaoQin.kqmx.myWeb.DT_HRI007_IN());
        // TBD - validate results
    }

}
