/**
 * DT_HRI007_INDATAHEADER.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.taide.kaoQin.kqmx.myWeb;

public class DT_HRI007_INDATAHEADER  implements java.io.Serializable {
    /* 查询月份 */
    private java.lang.String ZYYYYMM;

    /* 查询日期 */
    private java.lang.String ZDATE;

    public DT_HRI007_INDATAHEADER() {
    }

    public DT_HRI007_INDATAHEADER(
           java.lang.String ZYYYYMM,
           java.lang.String ZDATE) {
           this.ZYYYYMM = ZYYYYMM;
           this.ZDATE = ZDATE;
    }


    /**
     * Gets the ZYYYYMM value for this DT_HRI007_INDATAHEADER.
     * 
     * @return ZYYYYMM   * 查询月份
     */
    public java.lang.String getZYYYYMM() {
        return ZYYYYMM;
    }


    /**
     * Sets the ZYYYYMM value for this DT_HRI007_INDATAHEADER.
     * 
     * @param ZYYYYMM   * 查询月份
     */
    public void setZYYYYMM(java.lang.String ZYYYYMM) {
        this.ZYYYYMM = ZYYYYMM;
    }


    /**
     * Gets the ZDATE value for this DT_HRI007_INDATAHEADER.
     * 
     * @return ZDATE   * 查询日期
     */
    public java.lang.String getZDATE() {
        return ZDATE;
    }


    /**
     * Sets the ZDATE value for this DT_HRI007_INDATAHEADER.
     * 
     * @param ZDATE   * 查询日期
     */
    public void setZDATE(java.lang.String ZDATE) {
        this.ZDATE = ZDATE;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_HRI007_INDATAHEADER)) return false;
        DT_HRI007_INDATAHEADER other = (DT_HRI007_INDATAHEADER) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ZYYYYMM==null && other.getZYYYYMM()==null) || 
             (this.ZYYYYMM!=null &&
              this.ZYYYYMM.equals(other.getZYYYYMM()))) &&
            ((this.ZDATE==null && other.getZDATE()==null) || 
             (this.ZDATE!=null &&
              this.ZDATE.equals(other.getZDATE())));
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
        if (getZYYYYMM() != null) {
            _hashCode += getZYYYYMM().hashCode();
        }
        if (getZDATE() != null) {
            _hashCode += getZDATE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_HRI007_INDATAHEADER.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tidepharm.com/OA", ">>DT_HRI007_IN>DATA>HEADER"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ZYYYYMM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ZYYYYMM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ZDATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ZDATE"));
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
