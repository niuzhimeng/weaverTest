/**
 * VoucherEntry.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.zgsy.webUtil;

public class VoucherEntry  implements java.io.Serializable {
    private String FExplanation;

    private String FAccountNumber;

    private int FDC;

    private double FAmount;

    private com.weavernorth.zgsy.webUtil.ItemDetailEntry[] itemDetailEntries;

    public VoucherEntry() {
    }

    public VoucherEntry(
           String FExplanation,
           String FAccountNumber,
           int FDC,
           double FAmount,
           com.weavernorth.zgsy.webUtil.ItemDetailEntry[] itemDetailEntries) {
           this.FExplanation = FExplanation;
           this.FAccountNumber = FAccountNumber;
           this.FDC = FDC;
           this.FAmount = FAmount;
           this.itemDetailEntries = itemDetailEntries;
    }


    /**
     * Gets the FExplanation value for this VoucherEntry.
     *
     * @return FExplanation
     */
    public String getFExplanation() {
        return FExplanation;
    }


    /**
     * Sets the FExplanation value for this VoucherEntry.
     *
     * @param FExplanation
     */
    public void setFExplanation(String FExplanation) {
        this.FExplanation = FExplanation;
    }


    /**
     * Gets the FAccountNumber value for this VoucherEntry.
     *
     * @return FAccountNumber
     */
    public String getFAccountNumber() {
        return FAccountNumber;
    }


    /**
     * Sets the FAccountNumber value for this VoucherEntry.
     *
     * @param FAccountNumber
     */
    public void setFAccountNumber(String FAccountNumber) {
        this.FAccountNumber = FAccountNumber;
    }


    /**
     * Gets the FDC value for this VoucherEntry.
     *
     * @return FDC
     */
    public int getFDC() {
        return FDC;
    }


    /**
     * Sets the FDC value for this VoucherEntry.
     *
     * @param FDC
     */
    public void setFDC(int FDC) {
        this.FDC = FDC;
    }


    /**
     * Gets the FAmount value for this VoucherEntry.
     *
     * @return FAmount
     */
    public double getFAmount() {
        return FAmount;
    }


    /**
     * Sets the FAmount value for this VoucherEntry.
     *
     * @param FAmount
     */
    public void setFAmount(double FAmount) {
        this.FAmount = FAmount;
    }


    /**
     * Gets the itemDetailEntries value for this VoucherEntry.
     *
     * @return itemDetailEntries
     */
    public com.weavernorth.zgsy.webUtil.ItemDetailEntry[] getItemDetailEntries() {
        return itemDetailEntries;
    }


    /**
     * Sets the itemDetailEntries value for this VoucherEntry.
     *
     * @param itemDetailEntries
     */
    public void setItemDetailEntries(com.weavernorth.zgsy.webUtil.ItemDetailEntry[] itemDetailEntries) {
        this.itemDetailEntries = itemDetailEntries;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof VoucherEntry)) return false;
        VoucherEntry other = (VoucherEntry) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.FExplanation==null && other.getFExplanation()==null) ||
             (this.FExplanation!=null &&
              this.FExplanation.equals(other.getFExplanation()))) &&
            ((this.FAccountNumber==null && other.getFAccountNumber()==null) ||
             (this.FAccountNumber!=null &&
              this.FAccountNumber.equals(other.getFAccountNumber()))) &&
            this.FDC == other.getFDC() &&
            this.FAmount == other.getFAmount() &&
            ((this.itemDetailEntries==null && other.getItemDetailEntries()==null) ||
             (this.itemDetailEntries!=null &&
              java.util.Arrays.equals(this.itemDetailEntries, other.getItemDetailEntries())));
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
        if (getFExplanation() != null) {
            _hashCode += getFExplanation().hashCode();
        }
        if (getFAccountNumber() != null) {
            _hashCode += getFAccountNumber().hashCode();
        }
        _hashCode += getFDC();
        _hashCode += new Double(getFAmount()).hashCode();
        if (getItemDetailEntries() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getItemDetailEntries());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getItemDetailEntries(), i);
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
        new org.apache.axis.description.TypeDesc(VoucherEntry.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "VoucherEntry"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FExplanation");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FExplanation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FAccountNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FAccountNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FDC");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FDC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("itemDetailEntries");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ItemDetailEntries"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "ItemDetailEntry"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://tempuri.org/", "ItemDetailEntry"));
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
