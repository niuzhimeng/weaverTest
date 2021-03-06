/**
 * UseTimeDetail.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.mlc.webclient;

public class UseTimeDetail  implements java.io.Serializable {
    private String beginUseTime;

    private String endUseTime;

    public UseTimeDetail() {
    }

    public UseTimeDetail(
           String beginUseTime,
           String endUseTime) {
           this.beginUseTime = beginUseTime;
           this.endUseTime = endUseTime;
    }


    /**
     * Gets the beginUseTime value for this UseTimeDetail.
     * 
     * @return beginUseTime
     */
    public String getBeginUseTime() {
        return beginUseTime;
    }


    /**
     * Sets the beginUseTime value for this UseTimeDetail.
     * 
     * @param beginUseTime
     */
    public void setBeginUseTime(String beginUseTime) {
        this.beginUseTime = beginUseTime;
    }


    /**
     * Gets the endUseTime value for this UseTimeDetail.
     * 
     * @return endUseTime
     */
    public String getEndUseTime() {
        return endUseTime;
    }


    /**
     * Sets the endUseTime value for this UseTimeDetail.
     * 
     * @param endUseTime
     */
    public void setEndUseTime(String endUseTime) {
        this.endUseTime = endUseTime;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof UseTimeDetail)) return false;
        UseTimeDetail other = (UseTimeDetail) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.beginUseTime==null && other.getBeginUseTime()==null) || 
             (this.beginUseTime!=null &&
              this.beginUseTime.equals(other.getBeginUseTime()))) &&
            ((this.endUseTime==null && other.getEndUseTime()==null) || 
             (this.endUseTime!=null &&
              this.endUseTime.equals(other.getEndUseTime())));
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
        if (getBeginUseTime() != null) {
            _hashCode += getBeginUseTime().hashCode();
        }
        if (getEndUseTime() != null) {
            _hashCode += getEndUseTime().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UseTimeDetail.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "UseTimeDetail"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beginUseTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "BeginUseTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endUseTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "EndUseTime"));
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
