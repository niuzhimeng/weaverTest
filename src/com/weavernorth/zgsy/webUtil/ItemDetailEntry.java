/**
 * ItemDetailEntry.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.zgsy.webUtil;

public class ItemDetailEntry  implements java.io.Serializable {
    private String FItemClassNumber;

    private String FItemNumber;

    private String FItemName;

    public ItemDetailEntry() {
    }

    public ItemDetailEntry(
           String FItemClassNumber,
           String FItemNumber,
           String FItemName) {
           this.FItemClassNumber = FItemClassNumber;
           this.FItemNumber = FItemNumber;
           this.FItemName = FItemName;
    }


    /**
     * Gets the FItemClassNumber value for this ItemDetailEntry.
     *
     * @return FItemClassNumber
     */
    public String getFItemClassNumber() {
        return FItemClassNumber;
    }


    /**
     * Sets the FItemClassNumber value for this ItemDetailEntry.
     *
     * @param FItemClassNumber
     */
    public void setFItemClassNumber(String FItemClassNumber) {
        this.FItemClassNumber = FItemClassNumber;
    }


    /**
     * Gets the FItemNumber value for this ItemDetailEntry.
     *
     * @return FItemNumber
     */
    public String getFItemNumber() {
        return FItemNumber;
    }


    /**
     * Sets the FItemNumber value for this ItemDetailEntry.
     *
     * @param FItemNumber
     */
    public void setFItemNumber(String FItemNumber) {
        this.FItemNumber = FItemNumber;
    }


    /**
     * Gets the FItemName value for this ItemDetailEntry.
     *
     * @return FItemName
     */
    public String getFItemName() {
        return FItemName;
    }


    /**
     * Sets the FItemName value for this ItemDetailEntry.
     *
     * @param FItemName
     */
    public void setFItemName(String FItemName) {
        this.FItemName = FItemName;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ItemDetailEntry)) return false;
        ItemDetailEntry other = (ItemDetailEntry) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.FItemClassNumber==null && other.getFItemClassNumber()==null) ||
             (this.FItemClassNumber!=null &&
              this.FItemClassNumber.equals(other.getFItemClassNumber()))) &&
            ((this.FItemNumber==null && other.getFItemNumber()==null) ||
             (this.FItemNumber!=null &&
              this.FItemNumber.equals(other.getFItemNumber()))) &&
            ((this.FItemName==null && other.getFItemName()==null) ||
             (this.FItemName!=null &&
              this.FItemName.equals(other.getFItemName())));
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
        if (getFItemClassNumber() != null) {
            _hashCode += getFItemClassNumber().hashCode();
        }
        if (getFItemNumber() != null) {
            _hashCode += getFItemNumber().hashCode();
        }
        if (getFItemName() != null) {
            _hashCode += getFItemName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ItemDetailEntry.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "ItemDetailEntry"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FItemClassNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FItemClassNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FItemNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FItemNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FItemName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FItemName"));
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
