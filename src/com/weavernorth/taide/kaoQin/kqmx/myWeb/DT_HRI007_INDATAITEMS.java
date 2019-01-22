/**
 * DT_HRI007_INDATAITEMS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.taide.kaoQin.kqmx.myWeb;

public class DT_HRI007_INDATAITEMS  implements java.io.Serializable {
    /* 人员编号 */
    private java.lang.String PERNR;

    /* 备用字段1 */
    private java.lang.String additional1;

    /* 备用字段2 */
    private java.lang.String additional2;

    public DT_HRI007_INDATAITEMS() {
    }

    public DT_HRI007_INDATAITEMS(
           java.lang.String PERNR,
           java.lang.String additional1,
           java.lang.String additional2) {
           this.PERNR = PERNR;
           this.additional1 = additional1;
           this.additional2 = additional2;
    }


    /**
     * Gets the PERNR value for this DT_HRI007_INDATAITEMS.
     * 
     * @return PERNR   * 人员编号
     */
    public java.lang.String getPERNR() {
        return PERNR;
    }


    /**
     * Sets the PERNR value for this DT_HRI007_INDATAITEMS.
     * 
     * @param PERNR   * 人员编号
     */
    public void setPERNR(java.lang.String PERNR) {
        this.PERNR = PERNR;
    }


    /**
     * Gets the additional1 value for this DT_HRI007_INDATAITEMS.
     * 
     * @return additional1   * 备用字段1
     */
    public java.lang.String getAdditional1() {
        return additional1;
    }


    /**
     * Sets the additional1 value for this DT_HRI007_INDATAITEMS.
     * 
     * @param additional1   * 备用字段1
     */
    public void setAdditional1(java.lang.String additional1) {
        this.additional1 = additional1;
    }


    /**
     * Gets the additional2 value for this DT_HRI007_INDATAITEMS.
     * 
     * @return additional2   * 备用字段2
     */
    public java.lang.String getAdditional2() {
        return additional2;
    }


    /**
     * Sets the additional2 value for this DT_HRI007_INDATAITEMS.
     * 
     * @param additional2   * 备用字段2
     */
    public void setAdditional2(java.lang.String additional2) {
        this.additional2 = additional2;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_HRI007_INDATAITEMS)) return false;
        DT_HRI007_INDATAITEMS other = (DT_HRI007_INDATAITEMS) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.PERNR==null && other.getPERNR()==null) || 
             (this.PERNR!=null &&
              this.PERNR.equals(other.getPERNR()))) &&
            ((this.additional1==null && other.getAdditional1()==null) || 
             (this.additional1!=null &&
              this.additional1.equals(other.getAdditional1()))) &&
            ((this.additional2==null && other.getAdditional2()==null) || 
             (this.additional2!=null &&
              this.additional2.equals(other.getAdditional2())));
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
        if (getPERNR() != null) {
            _hashCode += getPERNR().hashCode();
        }
        if (getAdditional1() != null) {
            _hashCode += getAdditional1().hashCode();
        }
        if (getAdditional2() != null) {
            _hashCode += getAdditional2().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_HRI007_INDATAITEMS.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tidepharm.com/OA", ">>DT_HRI007_IN>DATA>ITEMS"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PERNR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PERNR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("additional1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Additional1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("additional2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Additional2"));
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
