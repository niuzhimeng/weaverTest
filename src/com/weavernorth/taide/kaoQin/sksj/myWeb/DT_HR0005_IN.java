/**
 * DT_HR0005_IN.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.taide.kaoQin.sksj.myWeb;


/**
 * SAP接收OA刷卡数据的数据类型
 */
public class DT_HR0005_IN  implements java.io.Serializable {
    /* 接口的抬头信息 */
    private com.weavernorth.taide.kaoQin.sksj.myWeb.DT_INTERFACE_COMMON SENDER;

    /* 接口字段 */
    private com.weavernorth.taide.kaoQin.sksj.myWeb.DT_HR0005_INDATAITEMS[] DATA;

    public DT_HR0005_IN() {
    }

    public DT_HR0005_IN(
           com.weavernorth.taide.kaoQin.sksj.myWeb.DT_INTERFACE_COMMON SENDER,
           com.weavernorth.taide.kaoQin.sksj.myWeb.DT_HR0005_INDATAITEMS[] DATA) {
           this.SENDER = SENDER;
           this.DATA = DATA;
    }


    /**
     * Gets the SENDER value for this DT_HR0005_IN.
     * 
     * @return SENDER   * 接口的抬头信息
     */
    public com.weavernorth.taide.kaoQin.sksj.myWeb.DT_INTERFACE_COMMON getSENDER() {
        return SENDER;
    }


    /**
     * Sets the SENDER value for this DT_HR0005_IN.
     * 
     * @param SENDER   * 接口的抬头信息
     */
    public void setSENDER(com.weavernorth.taide.kaoQin.sksj.myWeb.DT_INTERFACE_COMMON SENDER) {
        this.SENDER = SENDER;
    }


    /**
     * Gets the DATA value for this DT_HR0005_IN.
     * 
     * @return DATA   * 接口字段
     */
    public com.weavernorth.taide.kaoQin.sksj.myWeb.DT_HR0005_INDATAITEMS[] getDATA() {
        return DATA;
    }


    /**
     * Sets the DATA value for this DT_HR0005_IN.
     * 
     * @param DATA   * 接口字段
     */
    public void setDATA(com.weavernorth.taide.kaoQin.sksj.myWeb.DT_HR0005_INDATAITEMS[] DATA) {
        this.DATA = DATA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_HR0005_IN)) return false;
        DT_HR0005_IN other = (DT_HR0005_IN) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.SENDER==null && other.getSENDER()==null) || 
             (this.SENDER!=null &&
              this.SENDER.equals(other.getSENDER()))) &&
            ((this.DATA==null && other.getDATA()==null) || 
             (this.DATA!=null &&
              java.util.Arrays.equals(this.DATA, other.getDATA())));
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
        if (getSENDER() != null) {
            _hashCode += getSENDER().hashCode();
        }
        if (getDATA() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDATA());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDATA(), i);
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
        new org.apache.axis.description.TypeDesc(DT_HR0005_IN.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tidepharm.com/OA", "DT_HR0005_IN"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SENDER");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SENDER"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tidepharm.com/WMS", "DT_INTERFACE_COMMON"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DATA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DATA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tidepharm.com/OA", ">>DT_HR0005_IN>DATA>ITEMS"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "ITEMS"));
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
