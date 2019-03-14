/**
 * PassengerDetail.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.mlc.webclient;

public class PassengerDetail  implements java.io.Serializable {
    private String credentialsNumber;

    private String credentialsType;

    private String EID;

    private String name;

    private String nameEn;

    private String UID;

    public PassengerDetail() {
    }

    public PassengerDetail(
           String credentialsNumber,
           String credentialsType,
           String EID,
           String name,
           String nameEn,
           String UID) {
           this.credentialsNumber = credentialsNumber;
           this.credentialsType = credentialsType;
           this.EID = EID;
           this.name = name;
           this.nameEn = nameEn;
           this.UID = UID;
    }


    /**
     * Gets the credentialsNumber value for this PassengerDetail.
     * 
     * @return credentialsNumber
     */
    public String getCredentialsNumber() {
        return credentialsNumber;
    }


    /**
     * Sets the credentialsNumber value for this PassengerDetail.
     * 
     * @param credentialsNumber
     */
    public void setCredentialsNumber(String credentialsNumber) {
        this.credentialsNumber = credentialsNumber;
    }


    /**
     * Gets the credentialsType value for this PassengerDetail.
     * 
     * @return credentialsType
     */
    public String getCredentialsType() {
        return credentialsType;
    }


    /**
     * Sets the credentialsType value for this PassengerDetail.
     * 
     * @param credentialsType
     */
    public void setCredentialsType(String credentialsType) {
        this.credentialsType = credentialsType;
    }


    /**
     * Gets the EID value for this PassengerDetail.
     * 
     * @return EID
     */
    public String getEID() {
        return EID;
    }


    /**
     * Sets the EID value for this PassengerDetail.
     * 
     * @param EID
     */
    public void setEID(String EID) {
        this.EID = EID;
    }


    /**
     * Gets the name value for this PassengerDetail.
     * 
     * @return name
     */
    public String getName() {
        return name;
    }


    /**
     * Sets the name value for this PassengerDetail.
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Gets the nameEn value for this PassengerDetail.
     * 
     * @return nameEn
     */
    public String getNameEn() {
        return nameEn;
    }


    /**
     * Sets the nameEn value for this PassengerDetail.
     * 
     * @param nameEn
     */
    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }


    /**
     * Gets the UID value for this PassengerDetail.
     * 
     * @return UID
     */
    public String getUID() {
        return UID;
    }


    /**
     * Sets the UID value for this PassengerDetail.
     * 
     * @param UID
     */
    public void setUID(String UID) {
        this.UID = UID;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof PassengerDetail)) return false;
        PassengerDetail other = (PassengerDetail) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.credentialsNumber==null && other.getCredentialsNumber()==null) || 
             (this.credentialsNumber!=null &&
              this.credentialsNumber.equals(other.getCredentialsNumber()))) &&
            ((this.credentialsType==null && other.getCredentialsType()==null) || 
             (this.credentialsType!=null &&
              this.credentialsType.equals(other.getCredentialsType()))) &&
            ((this.EID==null && other.getEID()==null) || 
             (this.EID!=null &&
              this.EID.equals(other.getEID()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.nameEn==null && other.getNameEn()==null) || 
             (this.nameEn!=null &&
              this.nameEn.equals(other.getNameEn()))) &&
            ((this.UID==null && other.getUID()==null) || 
             (this.UID!=null &&
              this.UID.equals(other.getUID())));
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
        if (getCredentialsNumber() != null) {
            _hashCode += getCredentialsNumber().hashCode();
        }
        if (getCredentialsType() != null) {
            _hashCode += getCredentialsType().hashCode();
        }
        if (getEID() != null) {
            _hashCode += getEID().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getNameEn() != null) {
            _hashCode += getNameEn().hashCode();
        }
        if (getUID() != null) {
            _hashCode += getUID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PassengerDetail.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "PassengerDetail"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("credentialsNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "CredentialsNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("credentialsType");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "CredentialsType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EID");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "EID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nameEn");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "NameEn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UID");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "UID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
