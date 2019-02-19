/**
 * DT_HRI008_IN.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.taide.kaoQin.ryzb.myWeb;


/**
 * 岗位增补审批日期导入到SAP接口
 */
public class DT_HRI008_IN  implements java.io.Serializable {
    private com.weavernorth.taide.kaoQin.ryzb.myWeb.DT_HRI008_INSENDER SENDER;

    private com.weavernorth.taide.kaoQin.ryzb.myWeb.DT_HRI008_INDATAITEMS[] DATA;

    public DT_HRI008_IN() {
    }

    public DT_HRI008_IN(
           com.weavernorth.taide.kaoQin.ryzb.myWeb.DT_HRI008_INSENDER SENDER,
           com.weavernorth.taide.kaoQin.ryzb.myWeb.DT_HRI008_INDATAITEMS[] DATA) {
           this.SENDER = SENDER;
           this.DATA = DATA;
    }


    /**
     * Gets the SENDER value for this DT_HRI008_IN.
     * 
     * @return SENDER
     */
    public com.weavernorth.taide.kaoQin.ryzb.myWeb.DT_HRI008_INSENDER getSENDER() {
        return SENDER;
    }


    /**
     * Sets the SENDER value for this DT_HRI008_IN.
     * 
     * @param SENDER
     */
    public void setSENDER(com.weavernorth.taide.kaoQin.ryzb.myWeb.DT_HRI008_INSENDER SENDER) {
        this.SENDER = SENDER;
    }


    /**
     * Gets the DATA value for this DT_HRI008_IN.
     * 
     * @return DATA
     */
    public com.weavernorth.taide.kaoQin.ryzb.myWeb.DT_HRI008_INDATAITEMS[] getDATA() {
        return DATA;
    }


    /**
     * Sets the DATA value for this DT_HRI008_IN.
     * 
     * @param DATA
     */
    public void setDATA(com.weavernorth.taide.kaoQin.ryzb.myWeb.DT_HRI008_INDATAITEMS[] DATA) {
        this.DATA = DATA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_HRI008_IN)) return false;
        DT_HRI008_IN other = (DT_HRI008_IN) obj;
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
        new org.apache.axis.description.TypeDesc(DT_HRI008_IN.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tidepharm.com/YT", "DT_HRI008_IN"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SENDER");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SENDER"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tidepharm.com/YT", ">DT_HRI008_IN>SENDER"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DATA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DATA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tidepharm.com/YT", ">>DT_HRI008_IN>DATA>ITEMS"));
        elemField.setMinOccurs(0);
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
