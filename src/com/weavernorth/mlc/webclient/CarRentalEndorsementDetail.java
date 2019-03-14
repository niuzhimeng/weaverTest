/**
 * CarRentalEndorsementDetail.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.mlc.webclient;

public class CarRentalEndorsementDetail  implements java.io.Serializable {
    private CurrencyType currency;

    private String dropOffBeginDate;

    private String dropOffCities;

    private String dropOffEndDate;

    private PassengerDetail[] passengerList;

    private String pickUpBeginDate;

    private String pickUpCities;

    private String pickUpEndDate;

    private Long preVerifyFields;

    private Float price;

    private Integer productType;

    private Long skipFields;

    private String vehicleGroup;

    public CarRentalEndorsementDetail() {
    }

    public CarRentalEndorsementDetail(
           CurrencyType currency,
           String dropOffBeginDate,
           String dropOffCities,
           String dropOffEndDate,
           PassengerDetail[] passengerList,
           String pickUpBeginDate,
           String pickUpCities,
           String pickUpEndDate,
           Long preVerifyFields,
           Float price,
           Integer productType,
           Long skipFields,
           String vehicleGroup) {
           this.currency = currency;
           this.dropOffBeginDate = dropOffBeginDate;
           this.dropOffCities = dropOffCities;
           this.dropOffEndDate = dropOffEndDate;
           this.passengerList = passengerList;
           this.pickUpBeginDate = pickUpBeginDate;
           this.pickUpCities = pickUpCities;
           this.pickUpEndDate = pickUpEndDate;
           this.preVerifyFields = preVerifyFields;
           this.price = price;
           this.productType = productType;
           this.skipFields = skipFields;
           this.vehicleGroup = vehicleGroup;
    }


    /**
     * Gets the currency value for this CarRentalEndorsementDetail.
     * 
     * @return currency
     */
    public CurrencyType getCurrency() {
        return currency;
    }


    /**
     * Sets the currency value for this CarRentalEndorsementDetail.
     * 
     * @param currency
     */
    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }


    /**
     * Gets the dropOffBeginDate value for this CarRentalEndorsementDetail.
     * 
     * @return dropOffBeginDate
     */
    public String getDropOffBeginDate() {
        return dropOffBeginDate;
    }


    /**
     * Sets the dropOffBeginDate value for this CarRentalEndorsementDetail.
     * 
     * @param dropOffBeginDate
     */
    public void setDropOffBeginDate(String dropOffBeginDate) {
        this.dropOffBeginDate = dropOffBeginDate;
    }


    /**
     * Gets the dropOffCities value for this CarRentalEndorsementDetail.
     * 
     * @return dropOffCities
     */
    public String getDropOffCities() {
        return dropOffCities;
    }


    /**
     * Sets the dropOffCities value for this CarRentalEndorsementDetail.
     * 
     * @param dropOffCities
     */
    public void setDropOffCities(String dropOffCities) {
        this.dropOffCities = dropOffCities;
    }


    /**
     * Gets the dropOffEndDate value for this CarRentalEndorsementDetail.
     * 
     * @return dropOffEndDate
     */
    public String getDropOffEndDate() {
        return dropOffEndDate;
    }


    /**
     * Sets the dropOffEndDate value for this CarRentalEndorsementDetail.
     * 
     * @param dropOffEndDate
     */
    public void setDropOffEndDate(String dropOffEndDate) {
        this.dropOffEndDate = dropOffEndDate;
    }


    /**
     * Gets the passengerList value for this CarRentalEndorsementDetail.
     * 
     * @return passengerList
     */
    public PassengerDetail[] getPassengerList() {
        return passengerList;
    }


    /**
     * Sets the passengerList value for this CarRentalEndorsementDetail.
     * 
     * @param passengerList
     */
    public void setPassengerList(PassengerDetail[] passengerList) {
        this.passengerList = passengerList;
    }


    /**
     * Gets the pickUpBeginDate value for this CarRentalEndorsementDetail.
     * 
     * @return pickUpBeginDate
     */
    public String getPickUpBeginDate() {
        return pickUpBeginDate;
    }


    /**
     * Sets the pickUpBeginDate value for this CarRentalEndorsementDetail.
     * 
     * @param pickUpBeginDate
     */
    public void setPickUpBeginDate(String pickUpBeginDate) {
        this.pickUpBeginDate = pickUpBeginDate;
    }


    /**
     * Gets the pickUpCities value for this CarRentalEndorsementDetail.
     * 
     * @return pickUpCities
     */
    public String getPickUpCities() {
        return pickUpCities;
    }


    /**
     * Sets the pickUpCities value for this CarRentalEndorsementDetail.
     * 
     * @param pickUpCities
     */
    public void setPickUpCities(String pickUpCities) {
        this.pickUpCities = pickUpCities;
    }


    /**
     * Gets the pickUpEndDate value for this CarRentalEndorsementDetail.
     * 
     * @return pickUpEndDate
     */
    public String getPickUpEndDate() {
        return pickUpEndDate;
    }


    /**
     * Sets the pickUpEndDate value for this CarRentalEndorsementDetail.
     * 
     * @param pickUpEndDate
     */
    public void setPickUpEndDate(String pickUpEndDate) {
        this.pickUpEndDate = pickUpEndDate;
    }


    /**
     * Gets the preVerifyFields value for this CarRentalEndorsementDetail.
     * 
     * @return preVerifyFields
     */
    public Long getPreVerifyFields() {
        return preVerifyFields;
    }


    /**
     * Sets the preVerifyFields value for this CarRentalEndorsementDetail.
     * 
     * @param preVerifyFields
     */
    public void setPreVerifyFields(Long preVerifyFields) {
        this.preVerifyFields = preVerifyFields;
    }


    /**
     * Gets the price value for this CarRentalEndorsementDetail.
     * 
     * @return price
     */
    public Float getPrice() {
        return price;
    }


    /**
     * Sets the price value for this CarRentalEndorsementDetail.
     * 
     * @param price
     */
    public void setPrice(Float price) {
        this.price = price;
    }


    /**
     * Gets the productType value for this CarRentalEndorsementDetail.
     * 
     * @return productType
     */
    public Integer getProductType() {
        return productType;
    }


    /**
     * Sets the productType value for this CarRentalEndorsementDetail.
     * 
     * @param productType
     */
    public void setProductType(Integer productType) {
        this.productType = productType;
    }


    /**
     * Gets the skipFields value for this CarRentalEndorsementDetail.
     * 
     * @return skipFields
     */
    public Long getSkipFields() {
        return skipFields;
    }


    /**
     * Sets the skipFields value for this CarRentalEndorsementDetail.
     * 
     * @param skipFields
     */
    public void setSkipFields(Long skipFields) {
        this.skipFields = skipFields;
    }


    /**
     * Gets the vehicleGroup value for this CarRentalEndorsementDetail.
     * 
     * @return vehicleGroup
     */
    public String getVehicleGroup() {
        return vehicleGroup;
    }


    /**
     * Sets the vehicleGroup value for this CarRentalEndorsementDetail.
     * 
     * @param vehicleGroup
     */
    public void setVehicleGroup(String vehicleGroup) {
        this.vehicleGroup = vehicleGroup;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof CarRentalEndorsementDetail)) return false;
        CarRentalEndorsementDetail other = (CarRentalEndorsementDetail) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.currency==null && other.getCurrency()==null) || 
             (this.currency!=null &&
              this.currency.equals(other.getCurrency()))) &&
            ((this.dropOffBeginDate==null && other.getDropOffBeginDate()==null) || 
             (this.dropOffBeginDate!=null &&
              this.dropOffBeginDate.equals(other.getDropOffBeginDate()))) &&
            ((this.dropOffCities==null && other.getDropOffCities()==null) || 
             (this.dropOffCities!=null &&
              this.dropOffCities.equals(other.getDropOffCities()))) &&
            ((this.dropOffEndDate==null && other.getDropOffEndDate()==null) || 
             (this.dropOffEndDate!=null &&
              this.dropOffEndDate.equals(other.getDropOffEndDate()))) &&
            ((this.passengerList==null && other.getPassengerList()==null) || 
             (this.passengerList!=null &&
              java.util.Arrays.equals(this.passengerList, other.getPassengerList()))) &&
            ((this.pickUpBeginDate==null && other.getPickUpBeginDate()==null) || 
             (this.pickUpBeginDate!=null &&
              this.pickUpBeginDate.equals(other.getPickUpBeginDate()))) &&
            ((this.pickUpCities==null && other.getPickUpCities()==null) || 
             (this.pickUpCities!=null &&
              this.pickUpCities.equals(other.getPickUpCities()))) &&
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
        if (getCurrency() != null) {
            _hashCode += getCurrency().hashCode();
        }
        if (getDropOffBeginDate() != null) {
            _hashCode += getDropOffBeginDate().hashCode();
        }
        if (getDropOffCities() != null) {
            _hashCode += getDropOffCities().hashCode();
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
        if (getPickUpBeginDate() != null) {
            _hashCode += getPickUpBeginDate().hashCode();
        }
        if (getPickUpCities() != null) {
            _hashCode += getPickUpCities().hashCode();
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
        new org.apache.axis.description.TypeDesc(CarRentalEndorsementDetail.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "CarRentalEndorsementDetail"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currency");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "Currency"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "CurrencyType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dropOffBeginDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "DropOffBeginDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dropOffCities");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "DropOffCities"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dropOffEndDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "DropOffEndDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("passengerList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "PassengerList"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "PassengerDetail"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "PassengerDetail"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pickUpBeginDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "PickUpBeginDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pickUpCities");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "PickUpCities"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pickUpEndDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "PickUpEndDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("preVerifyFields");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "PreVerifyFields"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("price");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "Price"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "ProductType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("skipFields");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "SkipFields"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vehicleGroup");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "VehicleGroup"));
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
