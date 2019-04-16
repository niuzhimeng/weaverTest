/**
 * DT_HRI011_INDATA.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.taide.kaoQin500.jbxx.myWeb;

public class DT_HRI011_INDATA  implements java.io.Serializable {
    private DT_HRI011_INDATAITEMS ITEMS;

    public DT_HRI011_INDATA() {
    }

    public DT_HRI011_INDATA(
           DT_HRI011_INDATAITEMS ITEMS) {
           this.ITEMS = ITEMS;
    }


    /**
     * Gets the ITEMS value for this DT_HRI011_INDATA.
     * 
     * @return ITEMS
     */
    public DT_HRI011_INDATAITEMS getITEMS() {
        return ITEMS;
    }


    /**
     * Sets the ITEMS value for this DT_HRI011_INDATA.
     * 
     * @param ITEMS
     */
    public void setITEMS(DT_HRI011_INDATAITEMS ITEMS) {
        this.ITEMS = ITEMS;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof DT_HRI011_INDATA)) return false;
        DT_HRI011_INDATA other = (DT_HRI011_INDATA) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.ITEMS==null && other.getITEMS()==null) ||
             (this.ITEMS!=null &&
              this.ITEMS.equals(other.getITEMS())));
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
        if (getITEMS() != null) {
            _hashCode += getITEMS().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_HRI011_INDATA.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tidepharm.com/YT", ">DT_HRI011_IN>DATA"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ITEMS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ITEMS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tidepharm.com/YT", ">>DT_HRI011_IN>DATA>ITEMS"));
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
