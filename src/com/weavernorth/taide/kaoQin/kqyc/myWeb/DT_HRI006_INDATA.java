/**
 * DT_HRI006_INDATA.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.taide.kaoQin.kqyc.myWeb;

public class DT_HRI006_INDATA  implements java.io.Serializable {
    /* 结束日期 */
    private java.lang.String BEGDA;

    /* 开始日期 */
    private java.lang.String ENDDA;

    public DT_HRI006_INDATA() {
    }

    public DT_HRI006_INDATA(
           java.lang.String BEGDA,
           java.lang.String ENDDA) {
           this.BEGDA = BEGDA;
           this.ENDDA = ENDDA;
    }


    /**
     * Gets the BEGDA value for this DT_HRI006_INDATA.
     * 
     * @return BEGDA   * 结束日期
     */
    public java.lang.String getBEGDA() {
        return BEGDA;
    }


    /**
     * Sets the BEGDA value for this DT_HRI006_INDATA.
     * 
     * @param BEGDA   * 结束日期
     */
    public void setBEGDA(java.lang.String BEGDA) {
        this.BEGDA = BEGDA;
    }


    /**
     * Gets the ENDDA value for this DT_HRI006_INDATA.
     * 
     * @return ENDDA   * 开始日期
     */
    public java.lang.String getENDDA() {
        return ENDDA;
    }


    /**
     * Sets the ENDDA value for this DT_HRI006_INDATA.
     * 
     * @param ENDDA   * 开始日期
     */
    public void setENDDA(java.lang.String ENDDA) {
        this.ENDDA = ENDDA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_HRI006_INDATA)) return false;
        DT_HRI006_INDATA other = (DT_HRI006_INDATA) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.BEGDA==null && other.getBEGDA()==null) || 
             (this.BEGDA!=null &&
              this.BEGDA.equals(other.getBEGDA()))) &&
            ((this.ENDDA==null && other.getENDDA()==null) || 
             (this.ENDDA!=null &&
              this.ENDDA.equals(other.getENDDA())));
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
        if (getBEGDA() != null) {
            _hashCode += getBEGDA().hashCode();
        }
        if (getENDDA() != null) {
            _hashCode += getENDDA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_HRI006_INDATA.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tidepharm.com/OA", ">DT_HRI006_IN>DATA"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BEGDA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BEGDA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ENDDA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ENDDA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
