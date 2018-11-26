/**
 * POrderEntry.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.zgsy.webUtil;

public class POrderEntry  implements java.io.Serializable {
    private String FItemNumber;

    private String FEntrySelfP0244;

    private double FQty;

    private double FTaxPrice;

    private String FEntrySelfP0224;

    private String FNote;

    private int FEntrySelfP0278;

    public POrderEntry() {
    }

    public POrderEntry(
           String FItemNumber,
           String FEntrySelfP0244,
           double FQty,
           double FTaxPrice,
           String FEntrySelfP0224,
           String FNote,
           int FEntrySelfP0278) {
           this.FItemNumber = FItemNumber;
           this.FEntrySelfP0244 = FEntrySelfP0244;
           this.FQty = FQty;
           this.FTaxPrice = FTaxPrice;
           this.FEntrySelfP0224 = FEntrySelfP0224;
           this.FNote = FNote;
           this.FEntrySelfP0278 = FEntrySelfP0278;
    }


    /**
     * Gets the FItemNumber value for this POrderEntry.
     *
     * @return FItemNumber
     */
    public String getFItemNumber() {
        return FItemNumber;
    }


    /**
     * Sets the FItemNumber value for this POrderEntry.
     *
     * @param FItemNumber
     */
    public void setFItemNumber(String FItemNumber) {
        this.FItemNumber = FItemNumber;
    }


    /**
     * Gets the FEntrySelfP0244 value for this POrderEntry.
     *
     * @return FEntrySelfP0244
     */
    public String getFEntrySelfP0244() {
        return FEntrySelfP0244;
    }


    /**
     * Sets the FEntrySelfP0244 value for this POrderEntry.
     *
     * @param FEntrySelfP0244
     */
    public void setFEntrySelfP0244(String FEntrySelfP0244) {
        this.FEntrySelfP0244 = FEntrySelfP0244;
    }


    /**
     * Gets the FQty value for this POrderEntry.
     *
     * @return FQty
     */
    public double getFQty() {
        return FQty;
    }


    /**
     * Sets the FQty value for this POrderEntry.
     *
     * @param FQty
     */
    public void setFQty(double FQty) {
        this.FQty = FQty;
    }


    /**
     * Gets the FTaxPrice value for this POrderEntry.
     *
     * @return FTaxPrice
     */
    public double getFTaxPrice() {
        return FTaxPrice;
    }


    /**
     * Sets the FTaxPrice value for this POrderEntry.
     *
     * @param FTaxPrice
     */
    public void setFTaxPrice(double FTaxPrice) {
        this.FTaxPrice = FTaxPrice;
    }


    /**
     * Gets the FEntrySelfP0224 value for this POrderEntry.
     *
     * @return FEntrySelfP0224
     */
    public String getFEntrySelfP0224() {
        return FEntrySelfP0224;
    }


    /**
     * Sets the FEntrySelfP0224 value for this POrderEntry.
     *
     * @param FEntrySelfP0224
     */
    public void setFEntrySelfP0224(String FEntrySelfP0224) {
        this.FEntrySelfP0224 = FEntrySelfP0224;
    }


    /**
     * Gets the FNote value for this POrderEntry.
     *
     * @return FNote
     */
    public String getFNote() {
        return FNote;
    }


    /**
     * Sets the FNote value for this POrderEntry.
     *
     * @param FNote
     */
    public void setFNote(String FNote) {
        this.FNote = FNote;
    }


    /**
     * Gets the FEntrySelfP0278 value for this POrderEntry.
     *
     * @return FEntrySelfP0278
     */
    public int getFEntrySelfP0278() {
        return FEntrySelfP0278;
    }


    /**
     * Sets the FEntrySelfP0278 value for this POrderEntry.
     *
     * @param FEntrySelfP0278
     */
    public void setFEntrySelfP0278(int FEntrySelfP0278) {
        this.FEntrySelfP0278 = FEntrySelfP0278;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof POrderEntry)) return false;
        POrderEntry other = (POrderEntry) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.FItemNumber==null && other.getFItemNumber()==null) ||
             (this.FItemNumber!=null &&
              this.FItemNumber.equals(other.getFItemNumber()))) &&
            ((this.FEntrySelfP0244==null && other.getFEntrySelfP0244()==null) ||
             (this.FEntrySelfP0244!=null &&
              this.FEntrySelfP0244.equals(other.getFEntrySelfP0244()))) &&
            this.FQty == other.getFQty() &&
            this.FTaxPrice == other.getFTaxPrice() &&
            ((this.FEntrySelfP0224==null && other.getFEntrySelfP0224()==null) ||
             (this.FEntrySelfP0224!=null &&
              this.FEntrySelfP0224.equals(other.getFEntrySelfP0224()))) &&
            ((this.FNote==null && other.getFNote()==null) ||
             (this.FNote!=null &&
              this.FNote.equals(other.getFNote()))) &&
            this.FEntrySelfP0278 == other.getFEntrySelfP0278();
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
        if (getFItemNumber() != null) {
            _hashCode += getFItemNumber().hashCode();
        }
        if (getFEntrySelfP0244() != null) {
            _hashCode += getFEntrySelfP0244().hashCode();
        }
        _hashCode += new Double(getFQty()).hashCode();
        _hashCode += new Double(getFTaxPrice()).hashCode();
        if (getFEntrySelfP0224() != null) {
            _hashCode += getFEntrySelfP0224().hashCode();
        }
        if (getFNote() != null) {
            _hashCode += getFNote().hashCode();
        }
        _hashCode += getFEntrySelfP0278();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(POrderEntry.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "POrderEntry"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FItemNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FItemNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FEntrySelfP0244");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FEntrySelfP0244"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FQty");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FQty"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FTaxPrice");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FTaxPrice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FEntrySelfP0224");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FEntrySelfP0224"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FNote");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FNote"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FEntrySelfP0278");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FEntrySelfP0278"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
