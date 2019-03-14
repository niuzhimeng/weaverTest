/**
 * TrainEndorsementDetail.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.mlc.webclient;

public class TrainEndorsementDetail  implements java.io.Serializable {
    private String[] arrivalCityCodes;

    private CurrencyType currency;

    private String dataChange_CreateTime;

    private String dataChange_LastTime;

    private java.util.Calendar departBeginDate;

    private String[] departCityCodes;

    private String departDateBegin;

    private String departDateEnd;

    private java.util.Calendar departEndDate;

    private String[] fromCities;

    private PassengerDetail[] passengerList;

    private Long preVerifyFields;

    private String price;

    private TrainProductTypeEnum productType;

    private java.util.Calendar returnBeginDate;

    private String returnDateBegin;

    private String returnDateEnd;

    private java.util.Calendar returnEndDate;

    private SeatTypeEnum[] seatType;

    private Long skipFields;

    private String[] toCities;

    private Integer travelerCount;

    private TripTypeEnum tripType;

    public TrainEndorsementDetail() {
    }

    public TrainEndorsementDetail(
           String[] arrivalCityCodes,
           CurrencyType currency,
           String dataChange_CreateTime,
           String dataChange_LastTime,
           java.util.Calendar departBeginDate,
           String[] departCityCodes,
           String departDateBegin,
           String departDateEnd,
           java.util.Calendar departEndDate,
           String[] fromCities,
           PassengerDetail[] passengerList,
           Long preVerifyFields,
           String price,
           TrainProductTypeEnum productType,
           java.util.Calendar returnBeginDate,
           String returnDateBegin,
           String returnDateEnd,
           java.util.Calendar returnEndDate,
           SeatTypeEnum[] seatType,
           Long skipFields,
           String[] toCities,
           Integer travelerCount,
           TripTypeEnum tripType) {
           this.arrivalCityCodes = arrivalCityCodes;
           this.currency = currency;
           this.dataChange_CreateTime = dataChange_CreateTime;
           this.dataChange_LastTime = dataChange_LastTime;
           this.departBeginDate = departBeginDate;
           this.departCityCodes = departCityCodes;
           this.departDateBegin = departDateBegin;
           this.departDateEnd = departDateEnd;
           this.departEndDate = departEndDate;
           this.fromCities = fromCities;
           this.passengerList = passengerList;
           this.preVerifyFields = preVerifyFields;
           this.price = price;
           this.productType = productType;
           this.returnBeginDate = returnBeginDate;
           this.returnDateBegin = returnDateBegin;
           this.returnDateEnd = returnDateEnd;
           this.returnEndDate = returnEndDate;
           this.seatType = seatType;
           this.skipFields = skipFields;
           this.toCities = toCities;
           this.travelerCount = travelerCount;
           this.tripType = tripType;
    }


    /**
     * Gets the arrivalCityCodes value for this TrainEndorsementDetail.
     * 
     * @return arrivalCityCodes
     */
    public String[] getArrivalCityCodes() {
        return arrivalCityCodes;
    }


    /**
     * Sets the arrivalCityCodes value for this TrainEndorsementDetail.
     * 
     * @param arrivalCityCodes
     */
    public void setArrivalCityCodes(String[] arrivalCityCodes) {
        this.arrivalCityCodes = arrivalCityCodes;
    }


    /**
     * Gets the currency value for this TrainEndorsementDetail.
     * 
     * @return currency
     */
    public CurrencyType getCurrency() {
        return currency;
    }


    /**
     * Sets the currency value for this TrainEndorsementDetail.
     * 
     * @param currency
     */
    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }


    /**
     * Gets the dataChange_CreateTime value for this TrainEndorsementDetail.
     * 
     * @return dataChange_CreateTime
     */
    public String getDataChange_CreateTime() {
        return dataChange_CreateTime;
    }


    /**
     * Sets the dataChange_CreateTime value for this TrainEndorsementDetail.
     * 
     * @param dataChange_CreateTime
     */
    public void setDataChange_CreateTime(String dataChange_CreateTime) {
        this.dataChange_CreateTime = dataChange_CreateTime;
    }


    /**
     * Gets the dataChange_LastTime value for this TrainEndorsementDetail.
     * 
     * @return dataChange_LastTime
     */
    public String getDataChange_LastTime() {
        return dataChange_LastTime;
    }


    /**
     * Sets the dataChange_LastTime value for this TrainEndorsementDetail.
     * 
     * @param dataChange_LastTime
     */
    public void setDataChange_LastTime(String dataChange_LastTime) {
        this.dataChange_LastTime = dataChange_LastTime;
    }


    /**
     * Gets the departBeginDate value for this TrainEndorsementDetail.
     * 
     * @return departBeginDate
     */
    public java.util.Calendar getDepartBeginDate() {
        return departBeginDate;
    }


    /**
     * Sets the departBeginDate value for this TrainEndorsementDetail.
     * 
     * @param departBeginDate
     */
    public void setDepartBeginDate(java.util.Calendar departBeginDate) {
        this.departBeginDate = departBeginDate;
    }


    /**
     * Gets the departCityCodes value for this TrainEndorsementDetail.
     * 
     * @return departCityCodes
     */
    public String[] getDepartCityCodes() {
        return departCityCodes;
    }


    /**
     * Sets the departCityCodes value for this TrainEndorsementDetail.
     * 
     * @param departCityCodes
     */
    public void setDepartCityCodes(String[] departCityCodes) {
        this.departCityCodes = departCityCodes;
    }


    /**
     * Gets the departDateBegin value for this TrainEndorsementDetail.
     * 
     * @return departDateBegin
     */
    public String getDepartDateBegin() {
        return departDateBegin;
    }


    /**
     * Sets the departDateBegin value for this TrainEndorsementDetail.
     * 
     * @param departDateBegin
     */
    public void setDepartDateBegin(String departDateBegin) {
        this.departDateBegin = departDateBegin;
    }


    /**
     * Gets the departDateEnd value for this TrainEndorsementDetail.
     * 
     * @return departDateEnd
     */
    public String getDepartDateEnd() {
        return departDateEnd;
    }


    /**
     * Sets the departDateEnd value for this TrainEndorsementDetail.
     * 
     * @param departDateEnd
     */
    public void setDepartDateEnd(String departDateEnd) {
        this.departDateEnd = departDateEnd;
    }


    /**
     * Gets the departEndDate value for this TrainEndorsementDetail.
     * 
     * @return departEndDate
     */
    public java.util.Calendar getDepartEndDate() {
        return departEndDate;
    }


    /**
     * Sets the departEndDate value for this TrainEndorsementDetail.
     * 
     * @param departEndDate
     */
    public void setDepartEndDate(java.util.Calendar departEndDate) {
        this.departEndDate = departEndDate;
    }


    /**
     * Gets the fromCities value for this TrainEndorsementDetail.
     * 
     * @return fromCities
     */
    public String[] getFromCities() {
        return fromCities;
    }


    /**
     * Sets the fromCities value for this TrainEndorsementDetail.
     * 
     * @param fromCities
     */
    public void setFromCities(String[] fromCities) {
        this.fromCities = fromCities;
    }


    /**
     * Gets the passengerList value for this TrainEndorsementDetail.
     * 
     * @return passengerList
     */
    public PassengerDetail[] getPassengerList() {
        return passengerList;
    }


    /**
     * Sets the passengerList value for this TrainEndorsementDetail.
     * 
     * @param passengerList
     */
    public void setPassengerList(PassengerDetail[] passengerList) {
        this.passengerList = passengerList;
    }


    /**
     * Gets the preVerifyFields value for this TrainEndorsementDetail.
     * 
     * @return preVerifyFields
     */
    public Long getPreVerifyFields() {
        return preVerifyFields;
    }


    /**
     * Sets the preVerifyFields value for this TrainEndorsementDetail.
     * 
     * @param preVerifyFields
     */
    public void setPreVerifyFields(Long preVerifyFields) {
        this.preVerifyFields = preVerifyFields;
    }


    /**
     * Gets the price value for this TrainEndorsementDetail.
     * 
     * @return price
     */
    public String getPrice() {
        return price;
    }


    /**
     * Sets the price value for this TrainEndorsementDetail.
     * 
     * @param price
     */
    public void setPrice(String price) {
        this.price = price;
    }


    /**
     * Gets the productType value for this TrainEndorsementDetail.
     * 
     * @return productType
     */
    public TrainProductTypeEnum getProductType() {
        return productType;
    }


    /**
     * Sets the productType value for this TrainEndorsementDetail.
     * 
     * @param productType
     */
    public void setProductType(TrainProductTypeEnum productType) {
        this.productType = productType;
    }


    /**
     * Gets the returnBeginDate value for this TrainEndorsementDetail.
     * 
     * @return returnBeginDate
     */
    public java.util.Calendar getReturnBeginDate() {
        return returnBeginDate;
    }


    /**
     * Sets the returnBeginDate value for this TrainEndorsementDetail.
     * 
     * @param returnBeginDate
     */
    public void setReturnBeginDate(java.util.Calendar returnBeginDate) {
        this.returnBeginDate = returnBeginDate;
    }


    /**
     * Gets the returnDateBegin value for this TrainEndorsementDetail.
     * 
     * @return returnDateBegin
     */
    public String getReturnDateBegin() {
        return returnDateBegin;
    }


    /**
     * Sets the returnDateBegin value for this TrainEndorsementDetail.
     * 
     * @param returnDateBegin
     */
    public void setReturnDateBegin(String returnDateBegin) {
        this.returnDateBegin = returnDateBegin;
    }


    /**
     * Gets the returnDateEnd value for this TrainEndorsementDetail.
     * 
     * @return returnDateEnd
     */
    public String getReturnDateEnd() {
        return returnDateEnd;
    }


    /**
     * Sets the returnDateEnd value for this TrainEndorsementDetail.
     * 
     * @param returnDateEnd
     */
    public void setReturnDateEnd(String returnDateEnd) {
        this.returnDateEnd = returnDateEnd;
    }


    /**
     * Gets the returnEndDate value for this TrainEndorsementDetail.
     * 
     * @return returnEndDate
     */
    public java.util.Calendar getReturnEndDate() {
        return returnEndDate;
    }


    /**
     * Sets the returnEndDate value for this TrainEndorsementDetail.
     * 
     * @param returnEndDate
     */
    public void setReturnEndDate(java.util.Calendar returnEndDate) {
        this.returnEndDate = returnEndDate;
    }


    /**
     * Gets the seatType value for this TrainEndorsementDetail.
     * 
     * @return seatType
     */
    public SeatTypeEnum[] getSeatType() {
        return seatType;
    }


    /**
     * Sets the seatType value for this TrainEndorsementDetail.
     * 
     * @param seatType
     */
    public void setSeatType(SeatTypeEnum[] seatType) {
        this.seatType = seatType;
    }


    /**
     * Gets the skipFields value for this TrainEndorsementDetail.
     * 
     * @return skipFields
     */
    public Long getSkipFields() {
        return skipFields;
    }


    /**
     * Sets the skipFields value for this TrainEndorsementDetail.
     * 
     * @param skipFields
     */
    public void setSkipFields(Long skipFields) {
        this.skipFields = skipFields;
    }


    /**
     * Gets the toCities value for this TrainEndorsementDetail.
     * 
     * @return toCities
     */
    public String[] getToCities() {
        return toCities;
    }


    /**
     * Sets the toCities value for this TrainEndorsementDetail.
     * 
     * @param toCities
     */
    public void setToCities(String[] toCities) {
        this.toCities = toCities;
    }


    /**
     * Gets the travelerCount value for this TrainEndorsementDetail.
     * 
     * @return travelerCount
     */
    public Integer getTravelerCount() {
        return travelerCount;
    }


    /**
     * Sets the travelerCount value for this TrainEndorsementDetail.
     * 
     * @param travelerCount
     */
    public void setTravelerCount(Integer travelerCount) {
        this.travelerCount = travelerCount;
    }


    /**
     * Gets the tripType value for this TrainEndorsementDetail.
     * 
     * @return tripType
     */
    public TripTypeEnum getTripType() {
        return tripType;
    }


    /**
     * Sets the tripType value for this TrainEndorsementDetail.
     * 
     * @param tripType
     */
    public void setTripType(TripTypeEnum tripType) {
        this.tripType = tripType;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof TrainEndorsementDetail)) return false;
        TrainEndorsementDetail other = (TrainEndorsementDetail) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.arrivalCityCodes==null && other.getArrivalCityCodes()==null) || 
             (this.arrivalCityCodes!=null &&
              java.util.Arrays.equals(this.arrivalCityCodes, other.getArrivalCityCodes()))) &&
            ((this.currency==null && other.getCurrency()==null) || 
             (this.currency!=null &&
              this.currency.equals(other.getCurrency()))) &&
            ((this.dataChange_CreateTime==null && other.getDataChange_CreateTime()==null) || 
             (this.dataChange_CreateTime!=null &&
              this.dataChange_CreateTime.equals(other.getDataChange_CreateTime()))) &&
            ((this.dataChange_LastTime==null && other.getDataChange_LastTime()==null) || 
             (this.dataChange_LastTime!=null &&
              this.dataChange_LastTime.equals(other.getDataChange_LastTime()))) &&
            ((this.departBeginDate==null && other.getDepartBeginDate()==null) || 
             (this.departBeginDate!=null &&
              this.departBeginDate.equals(other.getDepartBeginDate()))) &&
            ((this.departCityCodes==null && other.getDepartCityCodes()==null) || 
             (this.departCityCodes!=null &&
              java.util.Arrays.equals(this.departCityCodes, other.getDepartCityCodes()))) &&
            ((this.departDateBegin==null && other.getDepartDateBegin()==null) || 
             (this.departDateBegin!=null &&
              this.departDateBegin.equals(other.getDepartDateBegin()))) &&
            ((this.departDateEnd==null && other.getDepartDateEnd()==null) || 
             (this.departDateEnd!=null &&
              this.departDateEnd.equals(other.getDepartDateEnd()))) &&
            ((this.departEndDate==null && other.getDepartEndDate()==null) || 
             (this.departEndDate!=null &&
              this.departEndDate.equals(other.getDepartEndDate()))) &&
            ((this.fromCities==null && other.getFromCities()==null) || 
             (this.fromCities!=null &&
              java.util.Arrays.equals(this.fromCities, other.getFromCities()))) &&
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
            ((this.returnBeginDate==null && other.getReturnBeginDate()==null) || 
             (this.returnBeginDate!=null &&
              this.returnBeginDate.equals(other.getReturnBeginDate()))) &&
            ((this.returnDateBegin==null && other.getReturnDateBegin()==null) || 
             (this.returnDateBegin!=null &&
              this.returnDateBegin.equals(other.getReturnDateBegin()))) &&
            ((this.returnDateEnd==null && other.getReturnDateEnd()==null) || 
             (this.returnDateEnd!=null &&
              this.returnDateEnd.equals(other.getReturnDateEnd()))) &&
            ((this.returnEndDate==null && other.getReturnEndDate()==null) || 
             (this.returnEndDate!=null &&
              this.returnEndDate.equals(other.getReturnEndDate()))) &&
            ((this.seatType==null && other.getSeatType()==null) || 
             (this.seatType!=null &&
              java.util.Arrays.equals(this.seatType, other.getSeatType()))) &&
            ((this.skipFields==null && other.getSkipFields()==null) || 
             (this.skipFields!=null &&
              this.skipFields.equals(other.getSkipFields()))) &&
            ((this.toCities==null && other.getToCities()==null) || 
             (this.toCities!=null &&
              java.util.Arrays.equals(this.toCities, other.getToCities()))) &&
            ((this.travelerCount==null && other.getTravelerCount()==null) || 
             (this.travelerCount!=null &&
              this.travelerCount.equals(other.getTravelerCount()))) &&
            ((this.tripType==null && other.getTripType()==null) || 
             (this.tripType!=null &&
              this.tripType.equals(other.getTripType())));
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
        if (getArrivalCityCodes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getArrivalCityCodes());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getArrivalCityCodes(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCurrency() != null) {
            _hashCode += getCurrency().hashCode();
        }
        if (getDataChange_CreateTime() != null) {
            _hashCode += getDataChange_CreateTime().hashCode();
        }
        if (getDataChange_LastTime() != null) {
            _hashCode += getDataChange_LastTime().hashCode();
        }
        if (getDepartBeginDate() != null) {
            _hashCode += getDepartBeginDate().hashCode();
        }
        if (getDepartCityCodes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDepartCityCodes());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getDepartCityCodes(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDepartDateBegin() != null) {
            _hashCode += getDepartDateBegin().hashCode();
        }
        if (getDepartDateEnd() != null) {
            _hashCode += getDepartDateEnd().hashCode();
        }
        if (getDepartEndDate() != null) {
            _hashCode += getDepartEndDate().hashCode();
        }
        if (getFromCities() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFromCities());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getFromCities(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
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
        if (getReturnBeginDate() != null) {
            _hashCode += getReturnBeginDate().hashCode();
        }
        if (getReturnDateBegin() != null) {
            _hashCode += getReturnDateBegin().hashCode();
        }
        if (getReturnDateEnd() != null) {
            _hashCode += getReturnDateEnd().hashCode();
        }
        if (getReturnEndDate() != null) {
            _hashCode += getReturnEndDate().hashCode();
        }
        if (getSeatType() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSeatType());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getSeatType(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSkipFields() != null) {
            _hashCode += getSkipFields().hashCode();
        }
        if (getToCities() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getToCities());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getToCities(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTravelerCount() != null) {
            _hashCode += getTravelerCount().hashCode();
        }
        if (getTripType() != null) {
            _hashCode += getTripType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TrainEndorsementDetail.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "TrainEndorsementDetail"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arrivalCityCodes");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "ArrivalCityCodes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currency");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "Currency"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "CurrencyType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataChange_CreateTime");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "DataChange_CreateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataChange_LastTime");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "DataChange_LastTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("departBeginDate");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "DepartBeginDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("departCityCodes");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "DepartCityCodes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("departDateBegin");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "DepartDateBegin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("departDateEnd");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "DepartDateEnd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("departEndDate");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "DepartEndDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fromCities");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "FromCities"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "string"));
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
        elemField.setFieldName("preVerifyFields");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "PreVerifyFields"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("price");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "Price"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productType");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "ProductType"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "TrainProductTypeEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnBeginDate");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "ReturnBeginDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnDateBegin");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "ReturnDateBegin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnDateEnd");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "ReturnDateEnd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnEndDate");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "ReturnEndDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("seatType");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "SeatType"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "SeatTypeEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "SeatTypeEnum"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("skipFields");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "SkipFields"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("toCities");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "ToCities"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("travelerCount");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "TravelerCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tripType");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "TripType"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "TripTypeEnum"));
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
