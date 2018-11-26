/**
 * POrder.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.zgsy.webUtil;

public class POrder  implements java.io.Serializable {
    private String FSupplyNumber;

    private String FHeadSelfP0224Number;

    private String FExplanation;

    private java.util.Calendar FDate;

    private String FEmpNumber;

    private String FDeptNumber;

    private com.weavernorth.zgsy.webUtil.POrderEntry[] POrderEntries;

    public POrder() {
    }

    public POrder(
           String FSupplyNumber,
           String FHeadSelfP0224Number,
           String FExplanation,
           java.util.Calendar FDate,
           String FEmpNumber,
           String FDeptNumber,
           com.weavernorth.zgsy.webUtil.POrderEntry[] POrderEntries) {
           this.FSupplyNumber = FSupplyNumber;
           this.FHeadSelfP0224Number = FHeadSelfP0224Number;
           this.FExplanation = FExplanation;
           this.FDate = FDate;
           this.FEmpNumber = FEmpNumber;
           this.FDeptNumber = FDeptNumber;
           this.POrderEntries = POrderEntries;
    }


    /**
     * Gets the FSupplyNumber value for this POrder.
     *
     * @return FSupplyNumber
     */
    public String getFSupplyNumber() {
        return FSupplyNumber;
    }


    /**
     * Sets the FSupplyNumber value for this POrder.
     *
     * @param FSupplyNumber
     */
    public void setFSupplyNumber(String FSupplyNumber) {
        this.FSupplyNumber = FSupplyNumber;
    }


    /**
     * Gets the FHeadSelfP0224Number value for this POrder.
     *
     * @return FHeadSelfP0224Number
     */
    public String getFHeadSelfP0224Number() {
        return FHeadSelfP0224Number;
    }


    /**
     * Sets the FHeadSelfP0224Number value for this POrder.
     *
     * @param FHeadSelfP0224Number
     */
    public void setFHeadSelfP0224Number(String FHeadSelfP0224Number) {
        this.FHeadSelfP0224Number = FHeadSelfP0224Number;
    }


    /**
     * Gets the FExplanation value for this POrder.
     *
     * @return FExplanation
     */
    public String getFExplanation() {
        return FExplanation;
    }


    /**
     * Sets the FExplanation value for this POrder.
     *
     * @param FExplanation
     */
    public void setFExplanation(String FExplanation) {
        this.FExplanation = FExplanation;
    }


    /**
     * Gets the FDate value for this POrder.
     *
     * @return FDate
     */
    public java.util.Calendar getFDate() {
        return FDate;
    }


    /**
     * Sets the FDate value for this POrder.
     *
     * @param FDate
     */
    public void setFDate(java.util.Calendar FDate) {
        this.FDate = FDate;
    }


    /**
     * Gets the FEmpNumber value for this POrder.
     *
     * @return FEmpNumber
     */
    public String getFEmpNumber() {
        return FEmpNumber;
    }


    /**
     * Sets the FEmpNumber value for this POrder.
     *
     * @param FEmpNumber
     */
    public void setFEmpNumber(String FEmpNumber) {
        this.FEmpNumber = FEmpNumber;
    }


    /**
     * Gets the FDeptNumber value for this POrder.
     *
     * @return FDeptNumber
     */
    public String getFDeptNumber() {
        return FDeptNumber;
    }


    /**
     * Sets the FDeptNumber value for this POrder.
     *
     * @param FDeptNumber
     */
    public void setFDeptNumber(String FDeptNumber) {
        this.FDeptNumber = FDeptNumber;
    }


    /**
     * Gets the POrderEntries value for this POrder.
     *
     * @return POrderEntries
     */
    public com.weavernorth.zgsy.webUtil.POrderEntry[] getPOrderEntries() {
        return POrderEntries;
    }


    /**
     * Sets the POrderEntries value for this POrder.
     *
     * @param POrderEntries
     */
    public void setPOrderEntries(com.weavernorth.zgsy.webUtil.POrderEntry[] POrderEntries) {
        this.POrderEntries = POrderEntries;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof POrder)) return false;
        POrder other = (POrder) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.FSupplyNumber==null && other.getFSupplyNumber()==null) ||
             (this.FSupplyNumber!=null &&
              this.FSupplyNumber.equals(other.getFSupplyNumber()))) &&
            ((this.FHeadSelfP0224Number==null && other.getFHeadSelfP0224Number()==null) ||
             (this.FHeadSelfP0224Number!=null &&
              this.FHeadSelfP0224Number.equals(other.getFHeadSelfP0224Number()))) &&
            ((this.FExplanation==null && other.getFExplanation()==null) ||
             (this.FExplanation!=null &&
              this.FExplanation.equals(other.getFExplanation()))) &&
            ((this.FDate==null && other.getFDate()==null) ||
             (this.FDate!=null &&
              this.FDate.equals(other.getFDate()))) &&
            ((this.FEmpNumber==null && other.getFEmpNumber()==null) ||
             (this.FEmpNumber!=null &&
              this.FEmpNumber.equals(other.getFEmpNumber()))) &&
            ((this.FDeptNumber==null && other.getFDeptNumber()==null) ||
             (this.FDeptNumber!=null &&
              this.FDeptNumber.equals(other.getFDeptNumber()))) &&
            ((this.POrderEntries==null && other.getPOrderEntries()==null) ||
             (this.POrderEntries!=null &&
              java.util.Arrays.equals(this.POrderEntries, other.getPOrderEntries())));
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
        if (getFSupplyNumber() != null) {
            _hashCode += getFSupplyNumber().hashCode();
        }
        if (getFHeadSelfP0224Number() != null) {
            _hashCode += getFHeadSelfP0224Number().hashCode();
        }
        if (getFExplanation() != null) {
            _hashCode += getFExplanation().hashCode();
        }
        if (getFDate() != null) {
            _hashCode += getFDate().hashCode();
        }
        if (getFEmpNumber() != null) {
            _hashCode += getFEmpNumber().hashCode();
        }
        if (getFDeptNumber() != null) {
            _hashCode += getFDeptNumber().hashCode();
        }
        if (getPOrderEntries() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPOrderEntries());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getPOrderEntries(), i);
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
        new org.apache.axis.description.TypeDesc(POrder.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "POrder"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FSupplyNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FSupplyNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FHeadSelfP0224Number");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FHeadSelfP0224Number"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FExplanation");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FExplanation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FEmpNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FEmpNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FDeptNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FDeptNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("POrderEntries");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "POrderEntries"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "POrderEntry"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://tempuri.org/", "POrderEntry"));
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
