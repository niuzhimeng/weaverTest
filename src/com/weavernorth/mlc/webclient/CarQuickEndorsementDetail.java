/**
 * CarQuickEndorsementDetail.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.mlc.webclient;

public class CarQuickEndorsementDetail  implements java.io.Serializable {
    private String beginUseDate;

    private String cities;

    private CompanyAddressDetail[] companyAddressList;

    private CurrencyType currency;

    private String endUseDate;

    private PassengerDetail[] passengerList;

    private Long preVerifyFields;

    private Float price;

    private Integer productType;

    private Long skipFields;

    private UseTimeDetail[] useTimeList;

    private String vehicleGroup;

    public CarQuickEndorsementDetail() {
    }

    public CarQuickEndorsementDetail(
           String beginUseDate,
           String cities,
           CompanyAddressDetail[] companyAddressList,
           CurrencyType currency,
           String endUseDate,
           PassengerDetail[] passengerList,
           Long preVerifyFields,
           Float price,
           Integer productType,
           Long skipFields,
           UseTimeDetail[] useTimeList,
           String vehicleGroup) {
           this.beginUseDate = beginUseDate;
           this.cities = cities;
           this.companyAddressList = companyAddressList;
           this.currency = currency;
           this.endUseDate = endUseDate;
           this.passengerList = passengerList;
           this.preVerifyFields = preVerifyFields;
           this.price = price;
           this.productType = productType;
           this.skipFields = skipFields;
           this.useTimeList = useTimeList;
           this.vehicleGroup = vehicleGroup;
    }


    /**
     * Gets the beginUseDate value for this CarQuickEndorsementDetail.
     * 
     * @return beginUseDate
     */
    public String getBeginUseDate() {
        return beginUseDate;
    }


    /**
     * Sets the beginUseDate value for this CarQuickEndorsementDetail.
     * 
     * @param beginUseDate
     */
    public void setBeginUseDate(String beginUseDate) {
        this.beginUseDate = beginUseDate;
    }


    /**
     * Gets the cities value for this CarQuickEndorsementDetail.
     * 
     * @return cities
     */
    public String getCities() {
        return cities;
    }


    /**
     * Sets the cities value for this CarQuickEndorsementDetail.
     * 
     * @param cities
     */
    public void setCities(String cities) {
        this.cities = cities;
    }


    /**
     * Gets the companyAddressList value for this CarQuickEndorsementDetail.
     * 
     * @return companyAddressList
     */
    public CompanyAddressDetail[] getCompanyAddressList() {
        return companyAddressList;
    }


    /**
     * Sets the companyAddressList value for this CarQuickEndorsementDetail.
     * 
     * @param companyAddressList
     */
    public void setCompanyAddressList(CompanyAddressDetail[] companyAddressList) {
        this.companyAddressList = companyAddressList;
    }


    /**
     * Gets the currency value for this CarQuickEndorsementDetail.
     * 
     * @return currency
     */
    public CurrencyType getCurrency() {
        return currency;
    }


    /**
     * Sets the currency value for this CarQuickEndorsementDetail.
     * 
     * @param currency
     */
    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }


    /**
     * Gets the endUseDate value for this CarQuickEndorsementDetail.
     * 
     * @return endUseDate
     */
    public String getEndUseDate() {
        return endUseDate;
    }


    /**
     * Sets the endUseDate value for this CarQuickEndorsementDetail.
     * 
     * @param endUseDate
     */
    public void setEndUseDate(String endUseDate) {
        this.endUseDate = endUseDate;
    }


    /**
     * Gets the passengerList value for this CarQuickEndorsementDetail.
     * 
     * @return passengerList
     */
    public PassengerDetail[] getPassengerList() {
        return passengerList;
    }


    /**
     * Sets the passengerList value for this CarQuickEndorsementDetail.
     * 
     * @param passengerList
     */
    public void setPassengerList(PassengerDetail[] passengerList) {
        this.passengerList = passengerList;
    }


    /**
     * Gets the preVerifyFields value for this CarQuickEndorsementDetail.
     * 
     * @return preVerifyFields
     */
    public Long getPreVerifyFields() {
        return preVerifyFields;
    }


    /**
     * Sets the preVerifyFields value for this CarQuickEndorsementDetail.
     * 
     * @param preVerifyFields
     */
    public void setPreVerifyFields(Long preVerifyFields) {
        this.preVerifyFields = preVerifyFields;
    }


    /**
     * Gets the price value for this CarQuickEndorsementDetail.
     * 
     * @return price
     */
    public Float getPrice() {
        return price;
    }


    /**
     * Sets the price value for this CarQuickEndorsementDetail.
     * 
     * @param price
     */
    public void setPrice(Float price) {
        this.price = price;
    }


    /**
     * Gets the productType value for this CarQuickEndorsementDetail.
     * 
     * @return productType
     */
    public Integer getProductType() {
        return productType;
    }


    /**
     * Sets the productType value for this CarQuickEndorsementDetail.
     * 
     * @param productType
     */
    public void setProductType(Integer productType) {
        this.productType = productType;
    }


    /**
     * Gets the skipFields value for this CarQuickEndorsementDetail.
     * 
     * @return skipFields
     */
    public Long getSkipFields() {
        return skipFields;
    }


    /**
     * Sets the skipFields value for this CarQuickEndorsementDetail.
     * 
     * @param skipFields
     */
    public void setSkipFields(Long skipFields) {
        this.skipFields = skipFields;
    }


    /**
     * Gets the useTimeList value for this CarQuickEndorsementDetail.
     * 
     * @return useTimeList
     */
    public UseTimeDetail[] getUseTimeList() {
        return useTimeList;
    }


    /**
     * Sets the useTimeList value for this CarQuickEndorsementDetail.
     * 
     * @param useTimeList
     */
    public void setUseTimeList(UseTimeDetail[] useTimeList) {
        this.useTimeList = useTimeList;
    }


    /**
     * Gets the vehicleGroup value for this CarQuickEndorsementDetail.
     * 
     * @return vehicleGroup
     */
    public String getVehicleGroup() {
        return vehicleGroup;
    }


    /**
     * Sets the vehicleGroup value for this CarQuickEndorsementDetail.
     * 
     * @param vehicleGroup
     */
    public void setVehicleGroup(String vehicleGroup) {
        this.vehicleGroup = vehicleGroup;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof CarQuickEndorsementDetail)) return false;
        CarQuickEndorsementDetail other = (CarQuickEndorsementDetail) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.beginUseDate==null && other.getBeginUseDate()==null) || 
             (this.beginUseDate!=null &&
              this.beginUseDate.equals(other.getBeginUseDate()))) &&
            ((this.cities==null && other.getCities()==null) || 
             (this.cities!=null &&
              this.cities.equals(other.getCities()))) &&
            ((this.companyAddressList==null && other.getCompanyAddressList()==null) || 
             (this.companyAddressList!=null &&
              java.util.Arrays.equals(this.companyAddressList, other.getCompanyAddressList()))) &&
            ((this.currency==null && other.getCurrency()==null) || 
             (this.currency!=null &&
              this.currency.equals(other.getCurrency()))) &&
            ((this.endUseDate==null && other.getEndUseDate()==null) || 
             (this.endUseDate!=null &&
              this.endUseDate.equals(other.getEndUseDate()))) &&
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
            ((this.useTimeList==null && other.getUseTimeList()==null) || 
             (this.useTimeList!=null &&
              java.util.Arrays.equals(this.useTimeList, other.getUseTimeList()))) &&
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
        if (getBeginUseDate() != null) {
            _hashCode += getBeginUseDate().hashCode();
        }
        if (getCities() != null) {
            _hashCode += getCities().hashCode();
        }
        if (getCompanyAddressList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCompanyAddressList());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getCompanyAddressList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCurrency() != null) {
            _hashCode += getCurrency().hashCode();
        }
        if (getEndUseDate() != null) {
            _hashCode += getEndUseDate().hashCode();
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
        if (getUseTimeList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUseTimeList());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getUseTimeList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getVehicleGroup() != null) {
            _hashCode += getVehicleGroup().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CarQuickEndorsementDetail.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "CarQuickEndorsementDetail"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beginUseDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "BeginUseDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cities");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "Cities"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("companyAddressList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "CompanyAddressList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "CompanyAddressDetail"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "CompanyAddressDetail"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currency");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "Currency"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "CurrencyType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endUseDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "EndUseDate"));
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
        elemField.setFieldName("useTimeList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "UseTimeList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "UseTimeDetail"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "UseTimeDetail"));
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
