/**
 * DT_HRI007_INDATA.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.taide.kaoQin.kqmx.myWeb;

public class DT_HRI007_INDATA  implements java.io.Serializable {
    private com.weavernorth.taide.kaoQin.kqmx.myWeb.DT_HRI007_INDATAHEADER HEADER;

    private com.weavernorth.taide.kaoQin.kqmx.myWeb.DT_HRI007_INDATAITEMS[] ITEMS;

    public DT_HRI007_INDATA() {
    }

    public DT_HRI007_INDATA(
           com.weavernorth.taide.kaoQin.kqmx.myWeb.DT_HRI007_INDATAHEADER HEADER,
           com.weavernorth.taide.kaoQin.kqmx.myWeb.DT_HRI007_INDATAITEMS[] ITEMS) {
           this.HEADER = HEADER;
           this.ITEMS = ITEMS;
    }


    /**
     * Gets the HEADER value for this DT_HRI007_INDATA.
     * 
     * @return HEADER
     */
    public com.weavernorth.taide.kaoQin.kqmx.myWeb.DT_HRI007_INDATAHEADER getHEADER() {
        return HEADER;
    }


    /**
     * Sets the HEADER value for this DT_HRI007_INDATA.
     * 
     * @param HEADER
     */
    public void setHEADER(com.weavernorth.taide.kaoQin.kqmx.myWeb.DT_HRI007_INDATAHEADER HEADER) {
        this.HEADER = HEADER;
    }


    /**
     * Gets the ITEMS value for this DT_HRI007_INDATA.
     * 
     * @return ITEMS
     */
    public com.weavernorth.taide.kaoQin.kqmx.myWeb.DT_HRI007_INDATAITEMS[] getITEMS() {
        return ITEMS;
    }


    /**
     * Sets the ITEMS value for this DT_HRI007_INDATA.
     * 
     * @param ITEMS
     */
    public void setITEMS(com.weavernorth.taide.kaoQin.kqmx.myWeb.DT_HRI007_INDATAITEMS[] ITEMS) {
        this.ITEMS = ITEMS;
    }

    public com.weavernorth.taide.kaoQin.kqmx.myWeb.DT_HRI007_INDATAITEMS getITEMS(int i) {
        return this.ITEMS[i];
    }

    public void setITEMS(int i, com.weavernorth.taide.kaoQin.kqmx.myWeb.DT_HRI007_INDATAITEMS _value) {
        this.ITEMS[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_HRI007_INDATA)) return false;
        DT_HRI007_INDATA other = (DT_HRI007_INDATA) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.HEADER==null && other.getHEADER()==null) || 
             (this.HEADER!=null &&
              this.HEADER.equals(other.getHEADER()))) &&
            ((this.ITEMS==null && other.getITEMS()==null) || 
             (this.ITEMS!=null &&
              java.util.Arrays.equals(this.ITEMS, other.getITEMS())));
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
        if (getHEADER() != null) {
            _hashCode += getHEADER().hashCode();
        }
        if (getITEMS() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getITEMS());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getITEMS(), i);
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
        new org.apache.axis.description.TypeDesc(DT_HRI007_INDATA.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tidepharm.com/OA", ">DT_HRI007_IN>DATA"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("HEADER");
        elemField.setXmlName(new javax.xml.namespace.QName("", "HEADER"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tidepharm.com/OA", ">>DT_HRI007_IN>DATA>HEADER"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ITEMS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ITEMS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tidepharm.com/OA", ">>DT_HRI007_IN>DATA>ITEMS"));
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
