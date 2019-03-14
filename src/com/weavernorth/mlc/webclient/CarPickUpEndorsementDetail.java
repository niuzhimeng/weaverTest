/**
 * CarPickUpEndorsementDetail.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.mlc.webclient;

public class CarPickUpEndorsementDetail  implements java.io.Serializable {
    private String cities;

    private CurrencyType currency;

    private String dropOffBeginDate;

    private String dropOffEndDate;

    private PassengerDetail[] passengerList;

    private Integer patternType;

    private String pickUpBeginDate;

    private String pickUpEndDate;

    private Long preVerifyFields;

    private Float price;

    private Integer productType;

    private Long skipFields;

    private String vehicleGroup;

    public CarPickUpEndorsementDetail() {
    }

    public CarPickUpEndorsementDetail(
           String cities,
           CurrencyType currency,
           String dropOffBeginDate,
           String dropOffEndDate,
           PassengerDetail[] passengerList,
           Integer patternType,
           String pickUpBeginDate,
           String pickUpEndDate,
           Long preVerifyFields,
           Float price,
           Integer productType,
           Long skipFields,
           String vehicleGroup) {
           this.cities = cities;
           this.currency = currency;
           this.dropOffBeginDate = dropOffBeginDate;
           this.dropOffEndDate = dropOffEndDate;
           this.passengerList = passengerList;
           this.patternType = patternType;
           this.pickUpBeginDate = pickUpBeginDate;
           this.pickUpEndDate = pickUpEndDate;
           this.preVerifyFields = preVerifyFields;
           this.price = price;
           this.productType = productType;
           this.skipFields = skipFields;
           this.vehicleGroup = vehicleGroup;
    }


    /**
     * Gets the cities value for this CarPickUpEndorsementDetail.
     * 
     * @return cities
     */
    public String getCities() {
        return cities;
    }


    /**
     * Sets the cities value for this CarPickUpEndorsementDetail.
     * 
     * @param cities
     */
    public void setCities(String cities) {
        this.cities = cities;
    }


    /**
     * Gets the currency value for this CarPickUpEndorsementDetail.
     * 
     * @return currency
     */
    public CurrencyType getCurrency() {
        return currency;
    }


    /**
     * Sets the currency value for this CarPickUpEndorsementDetail.
     * 
     * @param currency
     */
    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }


    /**
     * Gets the dropOffBeginDate value for this CarPickUpEndorsementDetail.
     * 
     * @return dropOffBeginDate
     */
    public String getDropOffBeginDate() {
        return dropOffBeginDate;
    }


    /**
     * Sets the dropOffBeginDate value for this CarPickUpEndorsementDetail.
     * 
     * @param dropOffBeginDate
     */
    public void setDropOffBeginDate(String dropOffBeginDate) {
        this.dropOffBeginDate = dropOffBeginDate;
    }


    /**
     * Gets the dropOffEndDate value for this CarPickUpEndorsementDetail.
     * 
     * @return dropOffEndDate
     */
    public String getDropOffEndDate() {
        return dropOffEndDate;
    }


    /**
     * Sets the dropOffEndDate value for this CarPickUpEndorsementDetail.
     * 
     * @param dropOffEndDate
     */
    public void setDropOffEndDate(String dropOffEndDate) {
        this.dropOffEndDate = dropOffEndDate;
    }


    /**
     * Gets the passengerList value for this CarPickUpEndorsementDetail.
     * 
     * @return passengerList
     */
    public PassengerDetail[] getPassengerList() {
        return passengerList;
    }


    /**
     * Sets the passengerList value for this CarPickUpEndorsementDetail.
     * 
     * @param passengerList
     */
    public void setPassengerList(PassengerDetail[] passengerList) {
        this.passengerList = passengerList;
    }


    /**
     * Gets the patternType value for this CarPickUpEndorsementDetail.
     * 
     * @return patternType
     */
    public Integer getPatternType() {
        return patternType;
    }


    /**
     * Sets the patternType value for this CarPickUpEndorsementDetail.
     * 
     * @param patternType
     */
    public void setPatternType(Integer patternType) {
        this.patternType = patternType;
    }


    /**
     * Gets the pickUpBeginDate value for this CarPickUpEndorsementDetail.
     * 
     * @return pickUpBeginDate
     */
    public String getPickUpBeginDate() {
        return pickUpBeginDate;
    }


    /**
     * Sets the pickUpBeginDate value for this CarPickUpEndorsementDetail.
     * 
     * @param pickUpBeginDate
     */
    public void setPickUpBeginDate(String pickUpBeginDate) {
        this.pickUpBeginDate = pickUpBeginDate;
    }


    /**
     * Gets the pickUpEndDate value for this CarPickUpEndorsementDetail.
     * 
     * @return pickUpEndDate
     */
    public String getPickUpEndDate() {
        return pickUpEndDate;
    }


    /**
     * Sets the pickUpEndDate value for this CarPickUpEndorsementDetail.
     * 
     * @param pickUpEndDate
     */
    public void setPickUpEndDate(String pickUpEndDate) {
        this.pickUpEndDate = pickUpEndDate;
    }


    /**
     * Gets the preVerifyFields value for this CarPickUpEndorsementDetail.
     * 
     * @return preVerifyFields
     */
    public Long getPreVerifyFields() {
        return preVerifyFields;
    }


    /**
     * Sets the preVerifyFields value for this CarPickUpEndorsementDetail.
     * 
     * @param preVerifyFields
     */
    public void setPreVerifyFields(Long preVerifyFields) {
        this.preVerifyFields = preVerifyFields;
    }


    /**
     * Gets the price value for this CarPickUpEndorsementDetail.
     * 
     * @return price
     */
    public Float getPrice() {
        return price;
    }


    /**
     * Sets the price value for this CarPickUpEndorsementDetail.
     * 
     * @param price
     */
    public void setPrice(Float price) {
        this.price = price;
    }


    /**
     * Gets the productType value for this CarPickUpEndorsementDetail.
     * 
     * @return productType
     */
    public Integer getProductType() {
        return productType;
    }


    /**
     * Sets the productType value for this CarPickUpEndorsementDetail.
     * 
     * @param productType
     */
    public void setProductType(Integer productType) {
        this.productType = productType;
    }


    /**
     * Gets the skipFields value for this CarPickUpEndorsementDetail.
     * 
     * @return skipFields
     */
    public Long getSkipFields() {
        return skipFields;
    }


    /**
     * Sets the skipFields value for this CarPickUpEndorsementDetail.
     * 
     * @param skipFields
     */
    public void setSkipFields(Long skipFields) {
        this.skipFields = skipFields;
    }


    /**
     * Gets the vehicleGroup value for this CarPickUpEndorsementDetail.
     * 
     * @return vehicleGroup
     */
    public String getVehicleGroup() {
        return vehicleGroup;
    }


    /**
     * Sets the vehicleGroup value for this CarPickUpEndorsementDetail.
     * 
     * @param vehicleGroup
     */
    public void setVehicleGroup(String vehicleGroup) {
        this.vehicleGroup = vehicleGroup;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof CarPickUpEndorsementDetail)) return false;
        CarPickUpEndorsementDetail other = (CarPickUpEndorsementDetail) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cities==null && other.getCities()==null) || 
             (this.cities!=null &&
              this.cities.equals(other.getCities()))) &&
            ((this.currency==null && other.getCurrency()==null) || 
             (this.currency!=null &&
              this.currency.equals(other.getCurrency()))) &&
            ((this.dropOffBeginDate==null && other.getDropOffBeginDate()==null) || 
             (this.dropOffBeginDate!=null &&
              this.dropOffBeginDate.equals(other.getDropOffBeginDate()))) &&
            ((this.dropOffEndDate==null && other.getDropOffEndDate()==null) || 
             (this.dropOffEndDate!=null &&
              this.dropOffEndDate.equals(other.getDropOffEndDate()))) &&
            ((this.passengerList==null && other.getPassengerList()==null) || 
             (this.passengerList!=null &&
              java.util.Arrays.equals(this.passengerList, other.getPassengerList()))) &&
            ((this.patternType==null && other.getPatternType()==null) || 
             (this.patternType!=null &&
              this.patternType.equals(other.getPatternType()))) &&
            ((this.pickUpBeginDate==null && other.getPickUpBeginDate()==null) || 
             (this.pickUpBeginDate!=null &&
              this.pickUpBeginDate.equals(other.getPickUpBeginDate()))) &&
            ((this.pickUpEndDate==null && other.getPickUpEndDate()==null) || 
             (this.pickUpEndDate!=null &&
              this.pickUpEndDate.equals(other.getPickUpEndDate()))) &&
            ((this.preVerifyFields==null && other.getPreVerifyFields()==null) || 
             (this.preVerifyFields!=null &&
              this.preVerifyFields.equals(other.getPreVerifyFields()))) &&
            ((this.price==null && other.getPrice()==null) || 
             (this.price!=null &&
              this.price.equals(other.getPrice()))) &&
            ((this.productType==null && other.getProductType()==null) || 
             (this.productType!=null &&
              this.productType.equals(other.getProductType()))) &&
            ((this.skipFields==null && other.getSkipFields()==null) || 
             (this.skipFields!=null &&
              this.skipFields.equals(other.getSkipFields()))) &&
            ((this.vehicleGroup==null && other.getVehicleGroup()==null) || 
             (this.vehicleGroup!=null &&
              this.vehicleGroup.equals(other.getVehicleGroup())));
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
        if (getCities() != null) {
            _hashCode += getCities().hashCode();
        }
        if (getCurrency() != null) {
            _hashCode += getCurrency().hashCode();
        }
        if (getDropOffBeginDate() != null) {
            _hashCode += getDropOffBeginDate().hashCode();
        }
        if (getDropOffEndDate() != null) {
            _hashCode += getDropOffEndDate().hashCode();
        }
        if (getPassengerList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPassengerList());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getPassengerList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPatternType() != null) {
            _hashCode += getPatternType().hashCode();
        }
        if (getPickUpBeginDate() != null) {
            _hashCode += getPickUpBeginDate().hashCode();
        }
        if (getPickUpEndDate() != null) {
            _hashCode += getPickUpEndDate().hashCode();
        }
        if (getPreVerifyFields() != null) {
            _hashCode += getPreVerifyFields().hashCode();
        }
        if (getPrice() != null) {
            _hashCode += getPrice().hashCode();
        }
        if (getProductType() != null) {
            _hashCode += getProductType().hashCode();
        }
        if (getSkipFields() != null) {
            _hashCode += getSkipFields().hashCode();
        }
        if (getVehicleGroup() != null) {
            _hashCode += getVehicleGroup().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CarPickUpEndorsementDetail.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "CarPickUpEndorsementDetail"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cities");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "Cities"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currency");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "Currency"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "CurrencyType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dropOffBeginDate");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "DropOffBeginDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dropOffEndDate");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "DropOffEndDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("passengerList");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "PassengerList"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "PassengerDetail"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "PassengerDetail"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("patternType");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "PatternType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pickUpBeginDate");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "PickUpBeginDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pickUpEndDate");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "PickUpEndDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("preVerifyFields");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "PreVerifyFields"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("price");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "Price"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productType");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "ProductType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("skipFields");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "SkipFields"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vehicleGroup");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "VehicleGroup"));
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
