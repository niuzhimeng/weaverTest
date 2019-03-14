/**
 * CarCharterEndorsementDetail.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.mlc.webclient;

public class CarCharterEndorsementDetail  implements java.io.Serializable {
    private String arriveCities;

    private String beginUseDate;

    private String beginUseTime;

    private CurrencyType currency;

    private String departCities;

    private String endUseDate;

    private String endUseTime;

    private PassengerDetail[] passengerList;

    private Long preVerifyFields;

    private Float price;

    private Integer productType;

    private Long skipFields;

    private Integer useDays;

    private String vehicleGroup;

    public CarCharterEndorsementDetail() {
    }

    public CarCharterEndorsementDetail(
           String arriveCities,
           String beginUseDate,
           String beginUseTime,
           CurrencyType currency,
           String departCities,
           String endUseDate,
           String endUseTime,
           PassengerDetail[] passengerList,
           Long preVerifyFields,
           Float price,
           Integer productType,
           Long skipFields,
           Integer useDays,
           String vehicleGroup) {
           this.arriveCities = arriveCities;
           this.beginUseDate = beginUseDate;
           this.beginUseTime = beginUseTime;
           this.currency = currency;
           this.departCities = departCities;
           this.endUseDate = endUseDate;
           this.endUseTime = endUseTime;
           this.passengerList = passengerList;
           this.preVerifyFields = preVerifyFields;
           this.price = price;
           this.productType = productType;
           this.skipFields = skipFields;
           this.useDays = useDays;
           this.vehicleGroup = vehicleGroup;
    }


    /**
     * Gets the arriveCities value for this CarCharterEndorsementDetail.
     * 
     * @return arriveCities
     */
    public String getArriveCities() {
        return arriveCities;
    }


    /**
     * Sets the arriveCities value for this CarCharterEndorsementDetail.
     * 
     * @param arriveCities
     */
    public void setArriveCities(String arriveCities) {
        this.arriveCities = arriveCities;
    }


    /**
     * Gets the beginUseDate value for this CarCharterEndorsementDetail.
     * 
     * @return beginUseDate
     */
    public String getBeginUseDate() {
        return beginUseDate;
    }


    /**
     * Sets the beginUseDate value for this CarCharterEndorsementDetail.
     * 
     * @param beginUseDate
     */
    public void setBeginUseDate(String beginUseDate) {
        this.beginUseDate = beginUseDate;
    }


    /**
     * Gets the beginUseTime value for this CarCharterEndorsementDetail.
     * 
     * @return beginUseTime
     */
    public String getBeginUseTime() {
        return beginUseTime;
    }


    /**
     * Sets the beginUseTime value for this CarCharterEndorsementDetail.
     * 
     * @param beginUseTime
     */
    public void setBeginUseTime(String beginUseTime) {
        this.beginUseTime = beginUseTime;
    }


    /**
     * Gets the currency value for this CarCharterEndorsementDetail.
     * 
     * @return currency
     */
    public CurrencyType getCurrency() {
        return currency;
    }


    /**
     * Sets the currency value for this CarCharterEndorsementDetail.
     * 
     * @param currency
     */
    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }


    /**
     * Gets the departCities value for this CarCharterEndorsementDetail.
     * 
     * @return departCities
     */
    public String getDepartCities() {
        return departCities;
    }


    /**
     * Sets the departCities value for this CarCharterEndorsementDetail.
     * 
     * @param departCities
     */
    public void setDepartCities(String departCities) {
        this.departCities = departCities;
    }


    /**
     * Gets the endUseDate value for this CarCharterEndorsementDetail.
     * 
     * @return endUseDate
     */
    public String getEndUseDate() {
        return endUseDate;
    }


    /**
     * Sets the endUseDate value for this CarCharterEndorsementDetail.
     * 
     * @param endUseDate
     */
    public void setEndUseDate(String endUseDate) {
        this.endUseDate = endUseDate;
    }


    /**
     * Gets the endUseTime value for this CarCharterEndorsementDetail.
     * 
     * @return endUseTime
     */
    public String getEndUseTime() {
        return endUseTime;
    }


    /**
     * Sets the endUseTime value for this CarCharterEndorsementDetail.
     * 
     * @param endUseTime
     */
    public void setEndUseTime(String endUseTime) {
        this.endUseTime = endUseTime;
    }


    /**
     * Gets the passengerList value for this CarCharterEndorsementDetail.
     * 
     * @return passengerList
     */
    public PassengerDetail[] getPassengerList() {
        return passengerList;
    }


    /**
     * Sets the passengerList value for this CarCharterEndorsementDetail.
     * 
     * @param passengerList
     */
    public void setPassengerList(PassengerDetail[] passengerList) {
        this.passengerList = passengerList;
    }


    /**
     * Gets the preVerifyFields value for this CarCharterEndorsementDetail.
     * 
     * @return preVerifyFields
     */
    public Long getPreVerifyFields() {
        return preVerifyFields;
    }


    /**
     * Sets the preVerifyFields value for this CarCharterEndorsementDetail.
     * 
     * @param preVerifyFields
     */
    public void setPreVerifyFields(Long preVerifyFields) {
        this.preVerifyFields = preVerifyFields;
    }


    /**
     * Gets the price value for this CarCharterEndorsementDetail.
     * 
     * @return price
     */
    public Float getPrice() {
        return price;
    }


    /**
     * Sets the price value for this CarCharterEndorsementDetail.
     * 
     * @param price
     */
    public void setPrice(Float price) {
        this.price = price;
    }


    /**
     * Gets the productType value for this CarCharterEndorsementDetail.
     * 
     * @return productType
     */
    public Integer getProductType() {
        return productType;
    }


    /**
     * Sets the productType value for this CarCharterEndorsementDetail.
     * 
     * @param productType
     */
    public void setProductType(Integer productType) {
        this.productType = productType;
    }


    /**
     * Gets the skipFields value for this CarCharterEndorsementDetail.
     * 
     * @return skipFields
     */
    public Long getSkipFields() {
        return skipFields;
    }


    /**
     * Sets the skipFields value for this CarCharterEndorsementDetail.
     * 
     * @param skipFields
     */
    public void setSkipFields(Long skipFields) {
        this.skipFields = skipFields;
    }


    /**
     * Gets the useDays value for this CarCharterEndorsementDetail.
     * 
     * @return useDays
     */
    public Integer getUseDays() {
        return useDays;
    }


    /**
     * Sets the useDays value for this CarCharterEndorsementDetail.
     * 
     * @param useDays
     */
    public void setUseDays(Integer useDays) {
        this.useDays = useDays;
    }


    /**
     * Gets the vehicleGroup value for this CarCharterEndorsementDetail.
     * 
     * @return vehicleGroup
     */
    public String getVehicleGroup() {
        return vehicleGroup;
    }


    /**
     * Sets the vehicleGroup value for this CarCharterEndorsementDetail.
     * 
     * @param vehicleGroup
     */
    public void setVehicleGroup(String vehicleGroup) {
        this.vehicleGroup = vehicleGroup;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof CarCharterEndorsementDetail)) return false;
        CarCharterEndorsementDetail other = (CarCharterEndorsementDetail) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.arriveCities==null && other.getArriveCities()==null) || 
             (this.arriveCities!=null &&
              this.arriveCities.equals(other.getArriveCities()))) &&
            ((this.beginUseDate==null && other.getBeginUseDate()==null) || 
             (this.beginUseDate!=null &&
              this.beginUseDate.equals(other.getBeginUseDate()))) &&
            ((this.beginUseTime==null && other.getBeginUseTime()==null) || 
             (this.beginUseTime!=null &&
              this.beginUseTime.equals(other.getBeginUseTime()))) &&
            ((this.currency==null && other.getCurrency()==null) || 
             (this.currency!=null &&
              this.currency.equals(other.getCurrency()))) &&
            ((this.departCities==null && other.getDepartCities()==null) || 
             (this.departCities!=null &&
              this.departCities.equals(other.getDepartCities()))) &&
            ((this.endUseDate==null && other.getEndUseDate()==null) || 
             (this.endUseDate!=null &&
              this.endUseDate.equals(other.getEndUseDate()))) &&
            ((this.endUseTime==null && other.getEndUseTime()==null) || 
             (this.endUseTime!=null &&
              this.endUseTime.equals(other.getEndUseTime()))) &&
            ((this.passengerList==null && other.getPassengerList()==null) || 
             (this.passengerList!=null &&
              java.util.Arrays.equals(this.passengerList, other.getPassengerList()))) &&
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
            ((this.useDays==null && other.getUseDays()==null) || 
             (this.useDays!=null &&
              this.useDays.equals(other.getUseDays()))) &&
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
        if (getArriveCities() != null) {
            _hashCode += getArriveCities().hashCode();
        }
        if (getBeginUseDate() != null) {
            _hashCode += getBeginUseDate().hashCode();
        }
        if (getBeginUseTime() != null) {
            _hashCode += getBeginUseTime().hashCode();
        }
        if (getCurrency() != null) {
            _hashCode += getCurrency().hashCode();
        }
        if (getDepartCities() != null) {
            _hashCode += getDepartCities().hashCode();
        }
        if (getEndUseDate() != null) {
            _hashCode += getEndUseDate().hashCode();
        }
        if (getEndUseTime() != null) {
            _hashCode += getEndUseTime().hashCode();
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
        if (getUseDays() != null) {
            _hashCode += getUseDays().hashCode();
        }
        if (getVehicleGroup() != null) {
            _hashCode += getVehicleGroup().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CarCharterEndorsementDetail.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "CarCharterEndorsementDetail"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arriveCities");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "ArriveCities"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beginUseDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "BeginUseDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beginUseTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "BeginUseTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currency");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "Currency"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "CurrencyType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("departCities");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "DepartCities"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endUseDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "EndUseDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endUseTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "EndUseTime"));
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
        elemField.setFieldName("useDays");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "UseDays"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
