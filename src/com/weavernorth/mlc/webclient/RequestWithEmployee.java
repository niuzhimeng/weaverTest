/**
 * RequestWithEmployee.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.mlc.webclient;

public class RequestWithEmployee  extends RequestBase  implements java.io.Serializable {
    private String ctripCardNO;

    private String employeeID;

    public RequestWithEmployee() {
    }

    public RequestWithEmployee(
           Authentification auth,
           String ctripCardNO,
           String employeeID) {
        super(
            auth);
        this.ctripCardNO = ctripCardNO;
        this.employeeID = employeeID;
    }


    /**
     * Gets the ctripCardNO value for this RequestWithEmployee.
     * 
     * @return ctripCardNO
     */
    public String getCtripCardNO() {
        return ctripCardNO;
    }


    /**
     * Sets the ctripCardNO value for this RequestWithEmployee.
     * 
     * @param ctripCardNO
     */
    public void setCtripCardNO(String ctripCardNO) {
        this.ctripCardNO = ctripCardNO;
    }


    /**
     * Gets the employeeID value for this RequestWithEmployee.
     * 
     * @return employeeID
     */
    public String getEmployeeID() {
        return employeeID;
    }


    /**
     * Sets the employeeID value for this RequestWithEmployee.
     * 
     * @param employeeID
     */
    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof RequestWithEmployee)) return false;
        RequestWithEmployee other = (RequestWithEmployee) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.ctripCardNO==null && other.getCtripCardNO()==null) || 
             (this.ctripCardNO!=null &&
              this.ctripCardNO.equals(other.getCtripCardNO()))) &&
            ((this.employeeID==null && other.getEmployeeID()==null) || 
             (this.employeeID!=null &&
              this.employeeID.equals(other.getEmployeeID())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getCtripCardNO() != null) {
            _hashCode += getCtripCardNO().hashCode();
        }
        if (getEmployeeID() != null) {
            _hashCode += getEmployeeID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RequestWithEmployee.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/", "RequestWithEmployee"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ctripCardNO");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/", "CtripCardNO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("employeeID");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/", "EmployeeID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
