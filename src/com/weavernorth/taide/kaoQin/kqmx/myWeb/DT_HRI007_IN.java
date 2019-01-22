/**
 * DT_HRI007_IN.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.taide.kaoQin.kqmx.myWeb;

public class DT_HRI007_IN  implements java.io.Serializable {
    private com.weavernorth.taide.kaoQin.kqmx.myWeb.DT_HRI007_INSENDER SENDER;

    private com.weavernorth.taide.kaoQin.kqmx.myWeb.DT_HRI007_INDATA DATA;

    public DT_HRI007_IN() {
    }

    public DT_HRI007_IN(
           com.weavernorth.taide.kaoQin.kqmx.myWeb.DT_HRI007_INSENDER SENDER,
           com.weavernorth.taide.kaoQin.kqmx.myWeb.DT_HRI007_INDATA DATA) {
           this.SENDER = SENDER;
           this.DATA = DATA;
    }


    /**
     * Gets the SENDER value for this DT_HRI007_IN.
     * 
     * @return SENDER
     */
    public com.weavernorth.taide.kaoQin.kqmx.myWeb.DT_HRI007_INSENDER getSENDER() {
        return SENDER;
    }


    /**
     * Sets the SENDER value for this DT_HRI007_IN.
     * 
     * @param SENDER
     */
    public void setSENDER(com.weavernorth.taide.kaoQin.kqmx.myWeb.DT_HRI007_INSENDER SENDER) {
        this.SENDER = SENDER;
    }


    /**
     * Gets the DATA value for this DT_HRI007_IN.
     * 
     * @return DATA
     */
    public com.weavernorth.taide.kaoQin.kqmx.myWeb.DT_HRI007_INDATA getDATA() {
        return DATA;
    }


    /**
     * Sets the DATA value for this DT_HRI007_IN.
     * 
     * @param DATA
     */
    public void setDATA(com.weavernorth.taide.kaoQin.kqmx.myWeb.DT_HRI007_INDATA DATA) {
        this.DATA = DATA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_HRI007_IN)) return false;
        DT_HRI007_IN other = (DT_HRI007_IN) obj;
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
              this.DATA.equals(other.getDATA())));
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
            _hashCode += getDATA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_HRI007_IN.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tidepharm.com/OA", "DT_HRI007_IN"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SENDER");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SENDER"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tidepharm.com/OA", ">DT_HRI007_IN>SENDER"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DATA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DATA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tidepharm.com/OA", ">DT_HRI007_IN>DATA"));
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
