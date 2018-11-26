/**
 * BaseDataInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.zgsy.webUtil;

public class BaseDataInfo  implements java.io.Serializable {
    private String FNumber;

    private String FName;

    public BaseDataInfo() {
    }

    public BaseDataInfo(
           String FNumber,
           String FName) {
           this.FNumber = FNumber;
           this.FName = FName;
    }


    /**
     * Gets the FNumber value for this BaseDataInfo.
     *
     * @return FNumber
     */
    public String getFNumber() {
        return FNumber;
    }


    /**
     * Sets the FNumber value for this BaseDataInfo.
     *
     * @param FNumber
     */
    public void setFNumber(String FNumber) {
        this.FNumber = FNumber;
    }


    /**
     * Gets the FName value for this BaseDataInfo.
     *
     * @return FName
     */
    public String getFName() {
        return FName;
    }


    /**
     * Sets the FName value for this BaseDataInfo.
     *
     * @param FName
     */
    public void setFName(String FName) {
        this.FName = FName;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof BaseDataInfo)) return false;
        BaseDataInfo other = (BaseDataInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.FNumber==null && other.getFNumber()==null) ||
             (this.FNumber!=null &&
              this.FNumber.equals(other.getFNumber()))) &&
            ((this.FName==null && other.getFName()==null) ||
             (this.FName!=null &&
              this.FName.equals(other.getFName())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getFNumber() != null) {
            _hashCode += getFNumber().hashCode();
        }
        if (getFName() != null) {
            _hashCode += getFName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BaseDataInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "BaseDataInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
