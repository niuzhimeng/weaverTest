/**
 * DT_HR0004_IN.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.taide.kaoQin.syjq04.myWeb;

public class DT_HR0004_IN  implements java.io.Serializable {
    /* 定额类型 */
    private java.lang.String KTART;

    /* 查询日期 */
    private java.lang.String ZBEGDA;

    private com.weavernorth.taide.kaoQin.syjq04.myWeb.DT_HR0004_ININPUT[] INPUT;

    public DT_HR0004_IN() {
    }

    public DT_HR0004_IN(
           java.lang.String KTART,
           java.lang.String ZBEGDA,
           com.weavernorth.taide.kaoQin.syjq04.myWeb.DT_HR0004_ININPUT[] INPUT) {
           this.KTART = KTART;
           this.ZBEGDA = ZBEGDA;
           this.INPUT = INPUT;
    }


    /**
     * Gets the KTART value for this DT_HR0004_IN.
     * 
     * @return KTART   * 定额类型
     */
    public java.lang.String getKTART() {
        return KTART;
    }


    /**
     * Sets the KTART value for this DT_HR0004_IN.
     * 
     * @param KTART   * 定额类型
     */
    public void setKTART(java.lang.String KTART) {
        this.KTART = KTART;
    }


    /**
     * Gets the ZBEGDA value for this DT_HR0004_IN.
     * 
     * @return ZBEGDA   * 查询日期
     */
    public java.lang.String getZBEGDA() {
        return ZBEGDA;
    }


    /**
     * Sets the ZBEGDA value for this DT_HR0004_IN.
     * 
     * @param ZBEGDA   * 查询日期
     */
    public void setZBEGDA(java.lang.String ZBEGDA) {
        this.ZBEGDA = ZBEGDA;
    }


    /**
     * Gets the INPUT value for this DT_HR0004_IN.
     * 
     * @return INPUT
     */
    public com.weavernorth.taide.kaoQin.syjq04.myWeb.DT_HR0004_ININPUT[] getINPUT() {
        return INPUT;
    }


    /**
     * Sets the INPUT value for this DT_HR0004_IN.
     * 
     * @param INPUT
     */
    public void setINPUT(com.weavernorth.taide.kaoQin.syjq04.myWeb.DT_HR0004_ININPUT[] INPUT) {
        this.INPUT = INPUT;
    }

    public com.weavernorth.taide.kaoQin.syjq04.myWeb.DT_HR0004_ININPUT getINPUT(int i) {
        return this.INPUT[i];
    }

    public void setINPUT(int i, com.weavernorth.taide.kaoQin.syjq04.myWeb.DT_HR0004_ININPUT _value) {
        this.INPUT[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_HR0004_IN)) return false;
        DT_HR0004_IN other = (DT_HR0004_IN) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.KTART==null && other.getKTART()==null) || 
             (this.KTART!=null &&
              this.KTART.equals(other.getKTART()))) &&
            ((this.ZBEGDA==null && other.getZBEGDA()==null) || 
             (this.ZBEGDA!=null &&
              this.ZBEGDA.equals(other.getZBEGDA()))) &&
            ((this.INPUT==null && other.getINPUT()==null) || 
             (this.INPUT!=null &&
              java.util.Arrays.equals(this.INPUT, other.getINPUT())));
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
        if (getKTART() != null) {
            _hashCode += getKTART().hashCode();
        }
        if (getZBEGDA() != null) {
            _hashCode += getZBEGDA().hashCode();
        }
        if (getINPUT() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getINPUT());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getINPUT(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_HR0004_IN.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tidepharm.com/OA", "DT_HR0004_IN"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("KTART");
        elemField.setXmlName(new javax.xml.namespace.QName("", "KTART"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ZBEGDA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ZBEGDA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INPUT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "INPUT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tidepharm.com/OA", ">DT_HR0004_IN>INPUT"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
