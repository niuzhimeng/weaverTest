/**
 * DT_HR0002_ININPUT.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.taide.kaoQin500.action.myWeb;

public class DT_HR0002_ININPUT  implements java.io.Serializable {
    private String TYPE;

    private DT_HR0002_ININPUTPT2002[] PT2002;

    public DT_HR0002_ININPUT() {
    }

    public DT_HR0002_ININPUT(
           String TYPE,
           DT_HR0002_ININPUTPT2002[] PT2002) {
           this.TYPE = TYPE;
           this.PT2002 = PT2002;
    }


    /**
     * Gets the TYPE value for this DT_HR0002_ININPUT.
     *
     * @return TYPE
     */
    public String getTYPE() {
        return TYPE;
    }


    /**
     * Sets the TYPE value for this DT_HR0002_ININPUT.
     *
     * @param TYPE
     */
    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }


    /**
     * Gets the PT2002 value for this DT_HR0002_ININPUT.
     *
     * @return PT2002
     */
    public DT_HR0002_ININPUTPT2002[] getPT2002() {
        return PT2002;
    }


    /**
     * Sets the PT2002 value for this DT_HR0002_ININPUT.
     *
     * @param PT2002
     */
    public void setPT2002(DT_HR0002_ININPUTPT2002[] PT2002) {
        this.PT2002 = PT2002;
    }

    public DT_HR0002_ININPUTPT2002 getPT2002(int i) {
        return this.PT2002[i];
    }

    public void setPT2002(int i, DT_HR0002_ININPUTPT2002 _value) {
        this.PT2002[i] = _value;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof DT_HR0002_ININPUT)) return false;
        DT_HR0002_ININPUT other = (DT_HR0002_ININPUT) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.TYPE==null && other.getTYPE()==null) ||
             (this.TYPE!=null &&
              this.TYPE.equals(other.getTYPE()))) &&
            ((this.PT2002==null && other.getPT2002()==null) ||
             (this.PT2002!=null &&
              java.util.Arrays.equals(this.PT2002, other.getPT2002())));
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
        if (getTYPE() != null) {
            _hashCode += getTYPE().hashCode();
        }
        if (getPT2002() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPT2002());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getPT2002(), i);
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
        new org.apache.axis.description.TypeDesc(DT_HR0002_ININPUT.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tidepharm.com/OA", ">DT_HR0002_IN>INPUT"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PT2002");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PT2002"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tidepharm.com/OA", ">>DT_HR0002_IN>INPUT>PT2002"));
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
