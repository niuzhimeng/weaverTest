/**
 * MaterialInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.zgsy.webUtil;

public class MaterialInfo  implements java.io.Serializable {
    private String FNumber;

    private String FName;

    private String FModel;

    public MaterialInfo() {
    }

    public MaterialInfo(
           String FNumber,
           String FName,
           String FModel) {
           this.FNumber = FNumber;
           this.FName = FName;
           this.FModel = FModel;
    }


    /**
     * Gets the FNumber value for this MaterialInfo.
     *
     * @return FNumber
     */
    public String getFNumber() {
        return FNumber;
    }


    /**
     * Sets the FNumber value for this MaterialInfo.
     *
     * @param FNumber
     */
    public void setFNumber(String FNumber) {
        this.FNumber = FNumber;
    }


    /**
     * Gets the FName value for this MaterialInfo.
     *
     * @return FName
     */
    public String getFName() {
        return FName;
    }


    /**
     * Sets the FName value for this MaterialInfo.
     *
     * @param FName
     */
    public void setFName(String FName) {
        this.FName = FName;
    }


    /**
     * Gets the FModel value for this MaterialInfo.
     *
     * @return FModel
     */
    public String getFModel() {
        return FModel;
    }


    /**
     * Sets the FModel value for this MaterialInfo.
     *
     * @param FModel
     */
    public void setFModel(String FModel) {
        this.FModel = FModel;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof MaterialInfo)) return false;
        MaterialInfo other = (MaterialInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.FNumber==null && other.getFNumber()==null) ||
             (this.FNumber!=null &&
              this.FNumber.equals(other.getFNumber()))) &&
            ((this.FName==null && other.getFName()==null) ||
             (this.FName!=null &&
              this.FName.equals(other.getFName()))) &&
            ((this.FModel==null && other.getFModel()==null) ||
             (this.FModel!=null &&
              this.FModel.equals(other.getFModel())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
//    public synchronized int hashCode() {
//        if (__hashCodeCalc) {
//            return 0;
//        }
//        __hashCodeCalc = true;
//        int _hashCode = 1;
//        if (getFNumber() != null) {
//            _hashCode += getFNumber().hashCode();
//        }
//        if (getFName() != null) {
//            _hashCode += getFName().hashCode();
//        }
//        if (getFModel() != null) {
//            _hashCode += getFModel().hashCode();
//        }
//        __hashCodeCalc = false;
//        return _hashCode;
//    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MaterialInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "MaterialInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FModel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FModel"));
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
