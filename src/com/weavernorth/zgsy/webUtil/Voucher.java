/**
 * Voucher.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.zgsy.webUtil;

public class Voucher  implements java.io.Serializable {
    private String flowNumber;

    private String FReference;

    private java.util.Calendar FVoucherDate;

    private int FPreparerNumber;

    private com.weavernorth.zgsy.webUtil.VoucherEntry[] voucherEntries;

    public Voucher() {
    }

    public Voucher(
           String flowNumber,
           String FReference,
           java.util.Calendar FVoucherDate,
           int FPreparerNumber,
           com.weavernorth.zgsy.webUtil.VoucherEntry[] voucherEntries) {
           this.flowNumber = flowNumber;
           this.FReference = FReference;
           this.FVoucherDate = FVoucherDate;
           this.FPreparerNumber = FPreparerNumber;
           this.voucherEntries = voucherEntries;
    }


    /**
     * Gets the flowNumber value for this Voucher.
     *
     * @return flowNumber
     */
    public String getFlowNumber() {
        return flowNumber;
    }


    /**
     * Sets the flowNumber value for this Voucher.
     *
     * @param flowNumber
     */
    public void setFlowNumber(String flowNumber) {
        this.flowNumber = flowNumber;
    }


    /**
     * Gets the FReference value for this Voucher.
     *
     * @return FReference
     */
    public String getFReference() {
        return FReference;
    }


    /**
     * Sets the FReference value for this Voucher.
     *
     * @param FReference
     */
    public void setFReference(String FReference) {
        this.FReference = FReference;
    }


    /**
     * Gets the FVoucherDate value for this Voucher.
     *
     * @return FVoucherDate
     */
    public java.util.Calendar getFVoucherDate() {
        return FVoucherDate;
    }


    /**
     * Sets the FVoucherDate value for this Voucher.
     *
     * @param FVoucherDate
     */
    public void setFVoucherDate(java.util.Calendar FVoucherDate) {
        this.FVoucherDate = FVoucherDate;
    }


    /**
     * Gets the FPreparerNumber value for this Voucher.
     *
     * @return FPreparerNumber
     */
    public int getFPreparerNumber() {
        return FPreparerNumber;
    }


    /**
     * Sets the FPreparerNumber value for this Voucher.
     *
     * @param FPreparerNumber
     */
    public void setFPreparerNumber(int FPreparerNumber) {
        this.FPreparerNumber = FPreparerNumber;
    }


    /**
     * Gets the voucherEntries value for this Voucher.
     *
     * @return voucherEntries
     */
    public com.weavernorth.zgsy.webUtil.VoucherEntry[] getVoucherEntries() {
        return voucherEntries;
    }


    /**
     * Sets the voucherEntries value for this Voucher.
     *
     * @param voucherEntries
     */
    public void setVoucherEntries(com.weavernorth.zgsy.webUtil.VoucherEntry[] voucherEntries) {
        this.voucherEntries = voucherEntries;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof Voucher)) return false;
        Voucher other = (Voucher) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.flowNumber==null && other.getFlowNumber()==null) ||
             (this.flowNumber!=null &&
              this.flowNumber.equals(other.getFlowNumber()))) &&
            ((this.FReference==null && other.getFReference()==null) ||
             (this.FReference!=null &&
              this.FReference.equals(other.getFReference()))) &&
            ((this.FVoucherDate==null && other.getFVoucherDate()==null) ||
             (this.FVoucherDate!=null &&
              this.FVoucherDate.equals(other.getFVoucherDate()))) &&
            this.FPreparerNumber == other.getFPreparerNumber() &&
            ((this.voucherEntries==null && other.getVoucherEntries()==null) ||
             (this.voucherEntries!=null &&
              java.util.Arrays.equals(this.voucherEntries, other.getVoucherEntries())));
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
        if (getFlowNumber() != null) {
            _hashCode += getFlowNumber().hashCode();
        }
        if (getFReference() != null) {
            _hashCode += getFReference().hashCode();
        }
        if (getFVoucherDate() != null) {
            _hashCode += getFVoucherDate().hashCode();
        }
        _hashCode += getFPreparerNumber();
        if (getVoucherEntries() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getVoucherEntries());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getVoucherEntries(), i);
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
        new org.apache.axis.description.TypeDesc(Voucher.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "Voucher"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flowNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FlowNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FVoucherDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FVoucherDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FPreparerNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FPreparerNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("voucherEntries");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "VoucherEntries"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "VoucherEntry"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://tempuri.org/", "VoucherEntry"));
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
