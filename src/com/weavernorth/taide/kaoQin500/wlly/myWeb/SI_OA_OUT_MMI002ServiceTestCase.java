/**
 * SI_OA_OUT_MMI002ServiceTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.taide.kaoQin500.wlly.myWeb;

public class SI_OA_OUT_MMI002ServiceTestCase extends junit.framework.TestCase {
    public SI_OA_OUT_MMI002ServiceTestCase(String name) {
        super(name);
    }

    public void testHTTPS_PortWSDL() throws Exception {
        javax.xml.rpc.ServiceFactory serviceFactory = javax.xml.rpc.ServiceFactory.newInstance();
        java.net.URL url = new java.net.URL(new SI_OA_OUT_MMI002ServiceLocator().getHTTPS_PortAddress() + "?WSDL");
        javax.xml.rpc.Service service = serviceFactory.createService(url, new SI_OA_OUT_MMI002ServiceLocator().getServiceName());
        assertTrue(service != null);
    }

    public void test1HTTPS_PortSI_OA_OUT_MMI002() throws Exception {
        SI_OA_OUT_MMI002BindingStub binding;
        try {
            binding = (SI_OA_OUT_MMI002BindingStub)
                          new SI_OA_OUT_MMI002ServiceLocator().getHTTPS_Port();
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
        DT_MMI002_OUTOUTPUT[] value = null;
        value = binding.SI_OA_OUT_MMI002(new DT_MMI002_IN());
        // TBD - validate results
    }

    public void testHTTP_PortWSDL() throws Exception {
        javax.xml.rpc.ServiceFactory serviceFactory = javax.xml.rpc.ServiceFactory.newInstance();
        java.net.URL url = new java.net.URL(new SI_OA_OUT_MMI002ServiceLocator().getHTTP_PortAddress() + "?WSDL");
        javax.xml.rpc.Service service = serviceFactory.createService(url, new SI_OA_OUT_MMI002ServiceLocator().getServiceName());
        assertTrue(service != null);
    }

    public void test2HTTP_PortSI_OA_OUT_MMI002() throws Exception {
        SI_OA_OUT_MMI002BindingStub binding;
        try {
            binding = (SI_OA_OUT_MMI002BindingStub)
                          new SI_OA_OUT_MMI002ServiceLocator().getHTTP_Port();
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
        DT_MMI002_OUTOUTPUT[] value = null;
        value = binding.SI_OA_OUT_MMI002(new DT_MMI002_IN());
        // TBD - validate results
    }

}
