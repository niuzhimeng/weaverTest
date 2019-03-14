/**
 * HotelEndorsementDetail.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.mlc.webclient;

public class HotelEndorsementDetail  implements java.io.Serializable {
    private String averagePrice;

    private java.util.Calendar checkInBeginDate;

    private String[] checkInCityCodes;

    private String checkInDateBegin;

    private String checkInDateEnd;

    private java.util.Calendar checkInEndDate;

    private java.util.Calendar checkOutBeginDate;

    private String checkOutDateBegin;

    private String checkOutDateEnd;

    private java.util.Calendar checkOutEndDate;

    private CurrencyType currency;

    private String dataChange_CreateTime;

    private String dataChange_LastTime;

    private String maxPrice;

    private String maxStarRating;

    private String minPrice;

    private String minStarRating;

    private PassengerDetail[] passengerList;

    private Long preVerifyFields;

    private HotelProductTypeEnum productType;

    private Integer roomCount;

    private Long skipFields;

    private String[] toCities;

    private String[] toCitiesEN;

    private Integer totalRoomNightCount;

    public HotelEndorsementDetail() {
    }

    public HotelEndorsementDetail(
           String averagePrice,
           java.util.Calendar checkInBeginDate,
           String[] checkInCityCodes,
           String checkInDateBegin,
           String checkInDateEnd,
           java.util.Calendar checkInEndDate,
           java.util.Calendar checkOutBeginDate,
           String checkOutDateBegin,
           String checkOutDateEnd,
           java.util.Calendar checkOutEndDate,
           CurrencyType currency,
           String dataChange_CreateTime,
           String dataChange_LastTime,
           String maxPrice,
           String maxStarRating,
           String minPrice,
           String minStarRating,
           PassengerDetail[] passengerList,
           Long preVerifyFields,
           HotelProductTypeEnum productType,
           Integer roomCount,
           Long skipFields,
           String[] toCities,
           String[] toCitiesEN,
           Integer totalRoomNightCount) {
           this.averagePrice = averagePrice;
           this.checkInBeginDate = checkInBeginDate;
           this.checkInCityCodes = checkInCityCodes;
           this.checkInDateBegin = checkInDateBegin;
           this.checkInDateEnd = checkInDateEnd;
           this.checkInEndDate = checkInEndDate;
           this.checkOutBeginDate = checkOutBeginDate;
           this.checkOutDateBegin = checkOutDateBegin;
           this.checkOutDateEnd = checkOutDateEnd;
           this.checkOutEndDate = checkOutEndDate;
           this.currency = currency;
           this.dataChange_CreateTime = dataChange_CreateTime;
           this.dataChange_LastTime = dataChange_LastTime;
           this.maxPrice = maxPrice;
           this.maxStarRating = maxStarRating;
           this.minPrice = minPrice;
           this.minStarRating = minStarRating;
           this.passengerList = passengerList;
           this.preVerifyFields = preVerifyFields;
           this.productType = productType;
           this.roomCount = roomCount;
           this.skipFields = skipFields;
           this.toCities = toCities;
           this.toCitiesEN = toCitiesEN;
           this.totalRoomNightCount = totalRoomNightCount;
    }


    /**
     * Gets the averagePrice value for this HotelEndorsementDetail.
     * 
     * @return averagePrice
     */
    public String getAveragePrice() {
        return averagePrice;
    }


    /**
     * Sets the averagePrice value for this HotelEndorsementDetail.
     * 
     * @param averagePrice
     */
    public void setAveragePrice(String averagePrice) {
        this.averagePrice = averagePrice;
    }


    /**
     * Gets the checkInBeginDate value for this HotelEndorsementDetail.
     * 
     * @return checkInBeginDate
     */
    public java.util.Calendar getCheckInBeginDate() {
        return checkInBeginDate;
    }


    /**
     * Sets the checkInBeginDate value for this HotelEndorsementDetail.
     * 
     * @param checkInBeginDate
     */
    public void setCheckInBeginDate(java.util.Calendar checkInBeginDate) {
        this.checkInBeginDate = checkInBeginDate;
    }


    /**
     * Gets the checkInCityCodes value for this HotelEndorsementDetail.
     * 
     * @return checkInCityCodes
     */
    public String[] getCheckInCityCodes() {
        return checkInCityCodes;
    }


    /**
     * Sets the checkInCityCodes value for this HotelEndorsementDetail.
     * 
     * @param checkInCityCodes
     */
    public void setCheckInCityCodes(String[] checkInCityCodes) {
        this.checkInCityCodes = checkInCityCodes;
    }


    /**
     * Gets the checkInDateBegin value for this HotelEndorsementDetail.
     * 
     * @return checkInDateBegin
     */
    public String getCheckInDateBegin() {
        return checkInDateBegin;
    }


    /**
     * Sets the checkInDateBegin value for this HotelEndorsementDetail.
     * 
     * @param checkInDateBegin
     */
    public void setCheckInDateBegin(String checkInDateBegin) {
        this.checkInDateBegin = checkInDateBegin;
    }


    /**
     * Gets the checkInDateEnd value for this HotelEndorsementDetail.
     * 
     * @return checkInDateEnd
     */
    public String getCheckInDateEnd() {
        return checkInDateEnd;
    }


    /**
     * Sets the checkInDateEnd value for this HotelEndorsementDetail.
     * 
     * @param checkInDateEnd
     */
    public void setCheckInDateEnd(String checkInDateEnd) {
        this.checkInDateEnd = checkInDateEnd;
    }


    /**
     * Gets the checkInEndDate value for this HotelEndorsementDetail.
     * 
     * @return checkInEndDate
     */
    public java.util.Calendar getCheckInEndDate() {
        return checkInEndDate;
    }


    /**
     * Sets the checkInEndDate value for this HotelEndorsementDetail.
     * 
     * @param checkInEndDate
     */
    public void setCheckInEndDate(java.util.Calendar checkInEndDate) {
        this.checkInEndDate = checkInEndDate;
    }


    /**
     * Gets the checkOutBeginDate value for this HotelEndorsementDetail.
     * 
     * @return checkOutBeginDate
     */
    public java.util.Calendar getCheckOutBeginDate() {
        return checkOutBeginDate;
    }


    /**
     * Sets the checkOutBeginDate value for this HotelEndorsementDetail.
     * 
     * @param checkOutBeginDate
     */
    public void setCheckOutBeginDate(java.util.Calendar checkOutBeginDate) {
        this.checkOutBeginDate = checkOutBeginDate;
    }


    /**
     * Gets the checkOutDateBegin value for this HotelEndorsementDetail.
     * 
     * @return checkOutDateBegin
     */
    public String getCheckOutDateBegin() {
        return checkOutDateBegin;
    }


    /**
     * Sets the checkOutDateBegin value for this HotelEndorsementDetail.
     * 
     * @param checkOutDateBegin
     */
    public void setCheckOutDateBegin(String checkOutDateBegin) {
        this.checkOutDateBegin = checkOutDateBegin;
    }


    /**
     * Gets the checkOutDateEnd value for this HotelEndorsementDetail.
     * 
     * @return checkOutDateEnd
     */
    public String getCheckOutDateEnd() {
        return checkOutDateEnd;
    }


    /**
     * Sets the checkOutDateEnd value for this HotelEndorsementDetail.
     * 
     * @param checkOutDateEnd
     */
    public void setCheckOutDateEnd(String checkOutDateEnd) {
        this.checkOutDateEnd = checkOutDateEnd;
    }


    /**
     * Gets the checkOutEndDate value for this HotelEndorsementDetail.
     * 
     * @return checkOutEndDate
     */
    public java.util.Calendar getCheckOutEndDate() {
        return checkOutEndDate;
    }


    /**
     * Sets the checkOutEndDate value for this HotelEndorsementDetail.
     * 
     * @param checkOutEndDate
     */
    public void setCheckOutEndDate(java.util.Calendar checkOutEndDate) {
        this.checkOutEndDate = checkOutEndDate;
    }


    /**
     * Gets the currency value for this HotelEndorsementDetail.
     * 
     * @return currency
     */
    public CurrencyType getCurrency() {
        return currency;
    }


    /**
     * Sets the currency value for this HotelEndorsementDetail.
     * 
     * @param currency
     */
    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }


    /**
     * Gets the dataChange_CreateTime value for this HotelEndorsementDetail.
     * 
     * @return dataChange_CreateTime
     */
    public String getDataChange_CreateTime() {
        return dataChange_CreateTime;
    }


    /**
     * Sets the dataChange_CreateTime value for this HotelEndorsementDetail.
     * 
     * @param dataChange_CreateTime
     */
    public void setDataChange_CreateTime(String dataChange_CreateTime) {
        this.dataChange_CreateTime = dataChange_CreateTime;
    }


    /**
     * Gets the dataChange_LastTime value for this HotelEndorsementDetail.
     * 
     * @return dataChange_LastTime
     */
    public String getDataChange_LastTime() {
        return dataChange_LastTime;
    }


    /**
     * Sets the dataChange_LastTime value for this HotelEndorsementDetail.
     * 
     * @param dataChange_LastTime
     */
    public void setDataChange_LastTime(String dataChange_LastTime) {
        this.dataChange_LastTime = dataChange_LastTime;
    }


    /**
     * Gets the maxPrice value for this HotelEndorsementDetail.
     * 
     * @return maxPrice
     */
    public String getMaxPrice() {
        return maxPrice;
    }


    /**
     * Sets the maxPrice value for this HotelEndorsementDetail.
     * 
     * @param maxPrice
     */
    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }


    /**
     * Gets the maxStarRating value for this HotelEndorsementDetail.
     * 
     * @return maxStarRating
     */
    public String getMaxStarRating() {
        return maxStarRating;
    }


    /**
     * Sets the maxStarRating value for this HotelEndorsementDetail.
     * 
     * @param maxStarRating
     */
    public void setMaxStarRating(String maxStarRating) {
        this.maxStarRating = maxStarRating;
    }


    /**
     * Gets the minPrice value for this HotelEndorsementDetail.
     * 
     * @return minPrice
     */
    public String getMinPrice() {
        return minPrice;
    }


    /**
     * Sets the minPrice value for this HotelEndorsementDetail.
     * 
     * @param minPrice
     */
    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }


    /**
     * Gets the minStarRating value for this HotelEndorsementDetail.
     * 
     * @return minStarRating
     */
    public String getMinStarRating() {
        return minStarRating;
    }


    /**
     * Sets the minStarRating value for this HotelEndorsementDetail.
     * 
     * @param minStarRating
     */
    public void setMinStarRating(String minStarRating) {
        this.minStarRating = minStarRating;
    }


    /**
     * Gets the passengerList value for this HotelEndorsementDetail.
     * 
     * @return passengerList
     */
    public PassengerDetail[] getPassengerList() {
        return passengerList;
    }


    /**
     * Sets the passengerList value for this HotelEndorsementDetail.
     * 
     * @param passengerList
     */
    public void setPassengerList(PassengerDetail[] passengerList) {
        this.passengerList = passengerList;
    }


    /**
     * Gets the preVerifyFields value for this HotelEndorsementDetail.
     * 
     * @return preVerifyFields
     */
    public Long getPreVerifyFields() {
        return preVerifyFields;
    }


    /**
     * Sets the preVerifyFields value for this HotelEndorsementDetail.
     * 
     * @param preVerifyFields
     */
    public void setPreVerifyFields(Long preVerifyFields) {
        this.preVerifyFields = preVerifyFields;
    }


    /**
     * Gets the productType value for this HotelEndorsementDetail.
     * 
     * @return productType
     */
    public HotelProductTypeEnum getProductType() {
        return productType;
    }


    /**
     * Sets the productType value for this HotelEndorsementDetail.
     * 
     * @param productType
     */
    public void setProductType(HotelProductTypeEnum productType) {
        this.productType = productType;
    }


    /**
     * Gets the roomCount value for this HotelEndorsementDetail.
     * 
     * @return roomCount
     */
    public Integer getRoomCount() {
        return roomCount;
    }


    /**
     * Sets the roomCount value for this HotelEndorsementDetail.
     * 
     * @param roomCount
     */
    public void setRoomCount(Integer roomCount) {
        this.roomCount = roomCount;
    }


    /**
     * Gets the skipFields value for this HotelEndorsementDetail.
     * 
     * @return skipFields
     */
    public Long getSkipFields() {
        return skipFields;
    }


    /**
     * Sets the skipFields value for this HotelEndorsementDetail.
     * 
     * @param skipFields
     */
    public void setSkipFields(Long skipFields) {
        this.skipFields = skipFields;
    }


    /**
     * Gets the toCities value for this HotelEndorsementDetail.
     * 
     * @return toCities
     */
    public String[] getToCities() {
        return toCities;
    }


    /**
     * Sets the toCities value for this HotelEndorsementDetail.
     * 
     * @param toCities
     */
    public void setToCities(String[] toCities) {
        this.toCities = toCities;
    }


    /**
     * Gets the toCitiesEN value for this HotelEndorsementDetail.
     * 
     * @return toCitiesEN
     */
    public String[] getToCitiesEN() {
        return toCitiesEN;
    }


    /**
     * Sets the toCitiesEN value for this HotelEndorsementDetail.
     * 
     * @param toCitiesEN
     */
    public void setToCitiesEN(String[] toCitiesEN) {
        this.toCitiesEN = toCitiesEN;
    }


    /**
     * Gets the totalRoomNightCount value for this HotelEndorsementDetail.
     * 
     * @return totalRoomNightCount
     */
    public Integer getTotalRoomNightCount() {
        return totalRoomNightCount;
    }


    /**
     * Sets the totalRoomNightCount value for this HotelEndorsementDetail.
     * 
     * @param totalRoomNightCount
     */
    public void setTotalRoomNightCount(Integer totalRoomNightCount) {
        this.totalRoomNightCount = totalRoomNightCount;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof HotelEndorsementDetail)) return false;
        HotelEndorsementDetail other = (HotelEndorsementDetail) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.averagePrice==null && other.getAveragePrice()==null) || 
             (this.averagePrice!=null &&
              this.averagePrice.equals(other.getAveragePrice()))) &&
            ((this.checkInBeginDate==null && other.getCheckInBeginDate()==null) || 
             (this.checkInBeginDate!=null &&
              this.checkInBeginDate.equals(other.getCheckInBeginDate()))) &&
            ((this.checkInCityCodes==null && other.getCheckInCityCodes()==null) || 
             (this.checkInCityCodes!=null &&
              java.util.Arrays.equals(this.checkInCityCodes, other.getCheckInCityCodes()))) &&
            ((this.checkInDateBegin==null && other.getCheckInDateBegin()==null) || 
             (this.checkInDateBegin!=null &&
              this.checkInDateBegin.equals(other.getCheckInDateBegin()))) &&
            ((this.checkInDateEnd==null && other.getCheckInDateEnd()==null) || 
             (this.checkInDateEnd!=null &&
              this.checkInDateEnd.equals(other.getCheckInDateEnd()))) &&
            ((this.checkInEndDate==null && other.getCheckInEndDate()==null) || 
             (this.checkInEndDate!=null &&
              this.checkInEndDate.equals(other.getCheckInEndDate()))) &&
            ((this.checkOutBeginDate==null && other.getCheckOutBeginDate()==null) || 
             (this.checkOutBeginDate!=null &&
              this.checkOutBeginDate.equals(other.getCheckOutBeginDate()))) &&
            ((this.checkOutDateBegin==null && other.getCheckOutDateBegin()==null) || 
             (this.checkOutDateBegin!=null &&
              this.checkOutDateBegin.equals(other.getCheckOutDateBegin()))) &&
            ((this.checkOutDateEnd==null && other.getCheckOutDateEnd()==null) || 
             (this.checkOutDateEnd!=null &&
              this.checkOutDateEnd.equals(other.getCheckOutDateEnd()))) &&
            ((this.checkOutEndDate==null && other.getCheckOutEndDate()==null) || 
             (this.checkOutEndDate!=null &&
              this.checkOutEndDate.equals(other.getCheckOutEndDate()))) &&
            ((this.currency==null && other.getCurrency()==null) || 
             (this.currency!=null &&
              this.currency.equals(other.getCurrency()))) &&
            ((this.dataChange_CreateTime==null && other.getDataChange_CreateTime()==null) || 
             (this.dataChange_CreateTime!=null &&
              this.dataChange_CreateTime.equals(other.getDataChange_CreateTime()))) &&
            ((this.dataChange_LastTime==null && other.getDataChange_LastTime()==null) || 
             (this.dataChange_LastTime!=null &&
              this.dataChange_LastTime.equals(other.getDataChange_LastTime()))) &&
            ((this.maxPrice==null && other.getMaxPrice()==null) || 
             (this.maxPrice!=null &&
              this.maxPrice.equals(other.getMaxPrice()))) &&
            ((this.maxStarRating==null && other.getMaxStarRating()==null) || 
             (this.maxStarRating!=null &&
              this.maxStarRating.equals(other.getMaxStarRating()))) &&
            ((this.minPrice==null && other.getMinPrice()==null) || 
             (this.minPrice!=null &&
              this.minPrice.equals(other.getMinPrice()))) &&
            ((this.minStarRating==null && other.getMinStarRating()==null) || 
             (this.minStarRating!=null &&
              this.minStarRating.equals(other.getMinStarRating()))) &&
            ((this.passengerList==null && other.getPassengerList()==null) || 
             (this.passengerList!=null &&
              java.util.Arrays.equals(this.passengerList, other.getPassengerList()))) &&
            ((this.preVerifyFields==null && other.getPreVerifyFields()==null) || 
             (this.preVerifyFields!=null &&
              this.preVerifyFields.equals(other.getPreVerifyFields()))) &&
            ((this.productType==null && other.getProductType()==null) || 
             (this.productType!=null &&
              this.productType.equals(other.getProductType()))) &&
            ((this.roomCount==null && other.getRoomCount()==null) || 
             (this.roomCount!=null &&
              this.roomCount.equals(other.getRoomCount()))) &&
            ((this.skipFields==null && other.getSkipFields()==null) || 
             (this.skipFields!=null &&
              this.skipFields.equals(other.getSkipFields()))) &&
            ((this.toCities==null && other.getToCities()==null) || 
             (this.toCities!=null &&
              java.util.Arrays.equals(this.toCities, other.getToCities()))) &&
            ((this.toCitiesEN==null && other.getToCitiesEN()==null) || 
             (this.toCitiesEN!=null &&
              java.util.Arrays.equals(this.toCitiesEN, other.getToCitiesEN()))) &&
            ((this.totalRoomNightCount==null && other.getTotalRoomNightCount()==null) || 
             (this.totalRoomNightCount!=null &&
              this.totalRoomNightCount.equals(other.getTotalRoomNightCount())));
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
        if (getAveragePrice() != null) {
            _hashCode += getAveragePrice().hashCode();
        }
        if (getCheckInBeginDate() != null) {
            _hashCode += getCheckInBeginDate().hashCode();
        }
        if (getCheckInCityCodes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCheckInCityCodes());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getCheckInCityCodes(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCheckInDateBegin() != null) {
            _hashCode += getCheckInDateBegin().hashCode();
        }
        if (getCheckInDateEnd() != null) {
            _hashCode += getCheckInDateEnd().hashCode();
        }
        if (getCheckInEndDate() != null) {
            _hashCode += getCheckInEndDate().hashCode();
        }
        if (getCheckOutBeginDate() != null) {
            _hashCode += getCheckOutBeginDate().hashCode();
        }
        if (getCheckOutDateBegin() != null) {
            _hashCode += getCheckOutDateBegin().hashCode();
        }
        if (getCheckOutDateEnd() != null) {
            _hashCode += getCheckOutDateEnd().hashCode();
        }
        if (getCheckOutEndDate() != null) {
            _hashCode += getCheckOutEndDate().hashCode();
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
        if (getMaxPrice() != null) {
            _hashCode += getMaxPrice().hashCode();
        }
        if (getMaxStarRating() != null) {
            _hashCode += getMaxStarRating().hashCode();
        }
        if (getMinPrice() != null) {
            _hashCode += getMinPrice().hashCode();
        }
        if (getMinStarRating() != null) {
            _hashCode += getMinStarRating().hashCode();
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
        if (getProductType() != null) {
            _hashCode += getProductType().hashCode();
        }
        if (getRoomCount() != null) {
            _hashCode += getRoomCount().hashCode();
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
        if (getToCitiesEN() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getToCitiesEN());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getToCitiesEN(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTotalRoomNightCount() != null) {
            _hashCode += getTotalRoomNightCount().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(HotelEndorsementDetail.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "HotelEndorsementDetail"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("averagePrice");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "AveragePrice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checkInBeginDate");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "CheckInBeginDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checkInCityCodes");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "CheckInCityCodes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checkInDateBegin");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "CheckInDateBegin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checkInDateEnd");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "CheckInDateEnd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checkInEndDate");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "CheckInEndDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checkOutBeginDate");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "CheckOutBeginDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checkOutDateBegin");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "CheckOutDateBegin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checkOutDateEnd");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "CheckOutDateEnd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checkOutEndDate");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "CheckOutEndDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
        elemField.setFieldName("maxPrice");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "MaxPrice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maxStarRating");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "MaxStarRating"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("minPrice");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "MinPrice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("minStarRating");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "MinStarRating"));
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
        elemField.setFieldName("preVerifyFields");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "PreVerifyFields"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productType");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "ProductType"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "HotelProductTypeEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("roomCount");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "RoomCount"));
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
        elemField.setFieldName("toCities");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "ToCities"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("toCitiesEN");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "ToCitiesEN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalRoomNightCount");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "TotalRoomNightCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
