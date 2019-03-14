/**
 * FlightEndorsementDetail.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.mlc.webclient;

public class FlightEndorsementDetail  implements java.io.Serializable {
    private String airline;

    private String arrivalBeginTime;

    private String[] arrivalCityCodes;

    private String arrivalEndTime;

    private CurrencyType currency;

    private java.util.Calendar departBeginDate;

    private String[] departCityCodes;

    private String departDateBegin;

    private String departDateEnd;

    private java.util.Calendar departEndDate;

    private Float discount;

    private FlightWayType flightWay;

    private String[] fromCities;

    private String[] fromCitiesEN;

    private PassengerDetail[] passengerList;

    private Long preVerifyFields;

    private Float price;

    private ProductTypeEnum productType;

    private java.util.Calendar returnBeginDate;

    private String returnDateBegin;

    private String returnDateEnd;

    private java.util.Calendar returnEndDate;

    private SeatClassType seatClass;

    private Long skipFields;

    private String takeOffBeginTime;

    private String takeOffEndTime;

    private String[] toCities;

    private String[] toCitiesEN;

    private Integer totalTravelerCount;

    private Integer travelerCount;

    public FlightEndorsementDetail() {
    }

    public FlightEndorsementDetail(
           String airline,
           String arrivalBeginTime,
           String[] arrivalCityCodes,
           String arrivalEndTime,
           CurrencyType currency,
           java.util.Calendar departBeginDate,
           String[] departCityCodes,
           String departDateBegin,
           String departDateEnd,
           java.util.Calendar departEndDate,
           Float discount,
           FlightWayType flightWay,
           String[] fromCities,
           String[] fromCitiesEN,
           PassengerDetail[] passengerList,
           Long preVerifyFields,
           Float price,
           ProductTypeEnum productType,
           java.util.Calendar returnBeginDate,
           String returnDateBegin,
           String returnDateEnd,
           java.util.Calendar returnEndDate,
           SeatClassType seatClass,
           Long skipFields,
           String takeOffBeginTime,
           String takeOffEndTime,
           String[] toCities,
           String[] toCitiesEN,
           Integer totalTravelerCount,
           Integer travelerCount) {
           this.airline = airline;
           this.arrivalBeginTime = arrivalBeginTime;
           this.arrivalCityCodes = arrivalCityCodes;
           this.arrivalEndTime = arrivalEndTime;
           this.currency = currency;
           this.departBeginDate = departBeginDate;
           this.departCityCodes = departCityCodes;
           this.departDateBegin = departDateBegin;
           this.departDateEnd = departDateEnd;
           this.departEndDate = departEndDate;
           this.discount = discount;
           this.flightWay = flightWay;
           this.fromCities = fromCities;
           this.fromCitiesEN = fromCitiesEN;
           this.passengerList = passengerList;
           this.preVerifyFields = preVerifyFields;
           this.price = price;
           this.productType = productType;
           this.returnBeginDate = returnBeginDate;
           this.returnDateBegin = returnDateBegin;
           this.returnDateEnd = returnDateEnd;
           this.returnEndDate = returnEndDate;
           this.seatClass = seatClass;
           this.skipFields = skipFields;
           this.takeOffBeginTime = takeOffBeginTime;
           this.takeOffEndTime = takeOffEndTime;
           this.toCities = toCities;
           this.toCitiesEN = toCitiesEN;
           this.totalTravelerCount = totalTravelerCount;
           this.travelerCount = travelerCount;
    }


    /**
     * Gets the airline value for this FlightEndorsementDetail.
     * 
     * @return airline
     */
    public String getAirline() {
        return airline;
    }


    /**
     * Sets the airline value for this FlightEndorsementDetail.
     * 
     * @param airline
     */
    public void setAirline(String airline) {
        this.airline = airline;
    }


    /**
     * Gets the arrivalBeginTime value for this FlightEndorsementDetail.
     * 
     * @return arrivalBeginTime
     */
    public String getArrivalBeginTime() {
        return arrivalBeginTime;
    }


    /**
     * Sets the arrivalBeginTime value for this FlightEndorsementDetail.
     * 
     * @param arrivalBeginTime
     */
    public void setArrivalBeginTime(String arrivalBeginTime) {
        this.arrivalBeginTime = arrivalBeginTime;
    }


    /**
     * Gets the arrivalCityCodes value for this FlightEndorsementDetail.
     * 
     * @return arrivalCityCodes
     */
    public String[] getArrivalCityCodes() {
        return arrivalCityCodes;
    }


    /**
     * Sets the arrivalCityCodes value for this FlightEndorsementDetail.
     * 
     * @param arrivalCityCodes
     */
    public void setArrivalCityCodes(String[] arrivalCityCodes) {
        this.arrivalCityCodes = arrivalCityCodes;
    }


    /**
     * Gets the arrivalEndTime value for this FlightEndorsementDetail.
     * 
     * @return arrivalEndTime
     */
    public String getArrivalEndTime() {
        return arrivalEndTime;
    }


    /**
     * Sets the arrivalEndTime value for this FlightEndorsementDetail.
     * 
     * @param arrivalEndTime
     */
    public void setArrivalEndTime(String arrivalEndTime) {
        this.arrivalEndTime = arrivalEndTime;
    }


    /**
     * Gets the currency value for this FlightEndorsementDetail.
     * 
     * @return currency
     */
    public CurrencyType getCurrency() {
        return currency;
    }


    /**
     * Sets the currency value for this FlightEndorsementDetail.
     * 
     * @param currency
     */
    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }


    /**
     * Gets the departBeginDate value for this FlightEndorsementDetail.
     * 
     * @return departBeginDate
     */
    public java.util.Calendar getDepartBeginDate() {
        return departBeginDate;
    }


    /**
     * Sets the departBeginDate value for this FlightEndorsementDetail.
     * 
     * @param departBeginDate
     */
    public void setDepartBeginDate(java.util.Calendar departBeginDate) {
        this.departBeginDate = departBeginDate;
    }


    /**
     * Gets the departCityCodes value for this FlightEndorsementDetail.
     * 
     * @return departCityCodes
     */
    public String[] getDepartCityCodes() {
        return departCityCodes;
    }


    /**
     * Sets the departCityCodes value for this FlightEndorsementDetail.
     * 
     * @param departCityCodes
     */
    public void setDepartCityCodes(String[] departCityCodes) {
        this.departCityCodes = departCityCodes;
    }


    /**
     * Gets the departDateBegin value for this FlightEndorsementDetail.
     * 
     * @return departDateBegin
     */
    public String getDepartDateBegin() {
        return departDateBegin;
    }


    /**
     * Sets the departDateBegin value for this FlightEndorsementDetail.
     * 
     * @param departDateBegin
     */
    public void setDepartDateBegin(String departDateBegin) {
        this.departDateBegin = departDateBegin;
    }


    /**
     * Gets the departDateEnd value for this FlightEndorsementDetail.
     * 
     * @return departDateEnd
     */
    public String getDepartDateEnd() {
        return departDateEnd;
    }


    /**
     * Sets the departDateEnd value for this FlightEndorsementDetail.
     * 
     * @param departDateEnd
     */
    public void setDepartDateEnd(String departDateEnd) {
        this.departDateEnd = departDateEnd;
    }


    /**
     * Gets the departEndDate value for this FlightEndorsementDetail.
     * 
     * @return departEndDate
     */
    public java.util.Calendar getDepartEndDate() {
        return departEndDate;
    }


    /**
     * Sets the departEndDate value for this FlightEndorsementDetail.
     * 
     * @param departEndDate
     */
    public void setDepartEndDate(java.util.Calendar departEndDate) {
        this.departEndDate = departEndDate;
    }


    /**
     * Gets the discount value for this FlightEndorsementDetail.
     * 
     * @return discount
     */
    public Float getDiscount() {
        return discount;
    }


    /**
     * Sets the discount value for this FlightEndorsementDetail.
     * 
     * @param discount
     */
    public void setDiscount(Float discount) {
        this.discount = discount;
    }


    /**
     * Gets the flightWay value for this FlightEndorsementDetail.
     * 
     * @return flightWay
     */
    public FlightWayType getFlightWay() {
        return flightWay;
    }


    /**
     * Sets the flightWay value for this FlightEndorsementDetail.
     * 
     * @param flightWay
     */
    public void setFlightWay(FlightWayType flightWay) {
        this.flightWay = flightWay;
    }


    /**
     * Gets the fromCities value for this FlightEndorsementDetail.
     * 
     * @return fromCities
     */
    public String[] getFromCities() {
        return fromCities;
    }


    /**
     * Sets the fromCities value for this FlightEndorsementDetail.
     * 
     * @param fromCities
     */
    public void setFromCities(String[] fromCities) {
        this.fromCities = fromCities;
    }


    /**
     * Gets the fromCitiesEN value for this FlightEndorsementDetail.
     * 
     * @return fromCitiesEN
     */
    public String[] getFromCitiesEN() {
        return fromCitiesEN;
    }


    /**
     * Sets the fromCitiesEN value for this FlightEndorsementDetail.
     * 
     * @param fromCitiesEN
     */
    public void setFromCitiesEN(String[] fromCitiesEN) {
        this.fromCitiesEN = fromCitiesEN;
    }


    /**
     * Gets the passengerList value for this FlightEndorsementDetail.
     * 
     * @return passengerList
     */
    public PassengerDetail[] getPassengerList() {
        return passengerList;
    }


    /**
     * Sets the passengerList value for this FlightEndorsementDetail.
     * 
     * @param passengerList
     */
    public void setPassengerList(PassengerDetail[] passengerList) {
        this.passengerList = passengerList;
    }


    /**
     * Gets the preVerifyFields value for this FlightEndorsementDetail.
     * 
     * @return preVerifyFields
     */
    public Long getPreVerifyFields() {
        return preVerifyFields;
    }


    /**
     * Sets the preVerifyFields value for this FlightEndorsementDetail.
     * 
     * @param preVerifyFields
     */
    public void setPreVerifyFields(Long preVerifyFields) {
        this.preVerifyFields = preVerifyFields;
    }


    /**
     * Gets the price value for this FlightEndorsementDetail.
     * 
     * @return price
     */
    public Float getPrice() {
        return price;
    }


    /**
     * Sets the price value for this FlightEndorsementDetail.
     * 
     * @param price
     */
    public void setPrice(Float price) {
        this.price = price;
    }


    /**
     * Gets the productType value for this FlightEndorsementDetail.
     * 
     * @return productType
     */
    public ProductTypeEnum getProductType() {
        return productType;
    }


    /**
     * Sets the productType value for this FlightEndorsementDetail.
     * 
     * @param productType
     */
    public void setProductType(ProductTypeEnum productType) {
        this.productType = productType;
    }


    /**
     * Gets the returnBeginDate value for this FlightEndorsementDetail.
     * 
     * @return returnBeginDate
     */
    public java.util.Calendar getReturnBeginDate() {
        return returnBeginDate;
    }


    /**
     * Sets the returnBeginDate value for this FlightEndorsementDetail.
     * 
     * @param returnBeginDate
     */
    public void setReturnBeginDate(java.util.Calendar returnBeginDate) {
        this.returnBeginDate = returnBeginDate;
    }


    /**
     * Gets the returnDateBegin value for this FlightEndorsementDetail.
     * 
     * @return returnDateBegin
     */
    public String getReturnDateBegin() {
        return returnDateBegin;
    }


    /**
     * Sets the returnDateBegin value for this FlightEndorsementDetail.
     * 
     * @param returnDateBegin
     */
    public void setReturnDateBegin(String returnDateBegin) {
        this.returnDateBegin = returnDateBegin;
    }


    /**
     * Gets the returnDateEnd value for this FlightEndorsementDetail.
     * 
     * @return returnDateEnd
     */
    public String getReturnDateEnd() {
        return returnDateEnd;
    }


    /**
     * Sets the returnDateEnd value for this FlightEndorsementDetail.
     * 
     * @param returnDateEnd
     */
    public void setReturnDateEnd(String returnDateEnd) {
        this.returnDateEnd = returnDateEnd;
    }


    /**
     * Gets the returnEndDate value for this FlightEndorsementDetail.
     * 
     * @return returnEndDate
     */
    public java.util.Calendar getReturnEndDate() {
        return returnEndDate;
    }


    /**
     * Sets the returnEndDate value for this FlightEndorsementDetail.
     * 
     * @param returnEndDate
     */
    public void setReturnEndDate(java.util.Calendar returnEndDate) {
        this.returnEndDate = returnEndDate;
    }


    /**
     * Gets the seatClass value for this FlightEndorsementDetail.
     * 
     * @return seatClass
     */
    public SeatClassType getSeatClass() {
        return seatClass;
    }


    /**
     * Sets the seatClass value for this FlightEndorsementDetail.
     * 
     * @param seatClass
     */
    public void setSeatClass(SeatClassType seatClass) {
        this.seatClass = seatClass;
    }


    /**
     * Gets the skipFields value for this FlightEndorsementDetail.
     * 
     * @return skipFields
     */
    public Long getSkipFields() {
        return skipFields;
    }


    /**
     * Sets the skipFields value for this FlightEndorsementDetail.
     * 
     * @param skipFields
     */
    public void setSkipFields(Long skipFields) {
        this.skipFields = skipFields;
    }


    /**
     * Gets the takeOffBeginTime value for this FlightEndorsementDetail.
     * 
     * @return takeOffBeginTime
     */
    public String getTakeOffBeginTime() {
        return takeOffBeginTime;
    }


    /**
     * Sets the takeOffBeginTime value for this FlightEndorsementDetail.
     * 
     * @param takeOffBeginTime
     */
    public void setTakeOffBeginTime(String takeOffBeginTime) {
        this.takeOffBeginTime = takeOffBeginTime;
    }


    /**
     * Gets the takeOffEndTime value for this FlightEndorsementDetail.
     * 
     * @return takeOffEndTime
     */
    public String getTakeOffEndTime() {
        return takeOffEndTime;
    }


    /**
     * Sets the takeOffEndTime value for this FlightEndorsementDetail.
     * 
     * @param takeOffEndTime
     */
    public void setTakeOffEndTime(String takeOffEndTime) {
        this.takeOffEndTime = takeOffEndTime;
    }


    /**
     * Gets the toCities value for this FlightEndorsementDetail.
     * 
     * @return toCities
     */
    public String[] getToCities() {
        return toCities;
    }


    /**
     * Sets the toCities value for this FlightEndorsementDetail.
     * 
     * @param toCities
     */
    public void setToCities(String[] toCities) {
        this.toCities = toCities;
    }


    /**
     * Gets the toCitiesEN value for this FlightEndorsementDetail.
     * 
     * @return toCitiesEN
     */
    public String[] getToCitiesEN() {
        return toCitiesEN;
    }


    /**
     * Sets the toCitiesEN value for this FlightEndorsementDetail.
     * 
     * @param toCitiesEN
     */
    public void setToCitiesEN(String[] toCitiesEN) {
        this.toCitiesEN = toCitiesEN;
    }


    /**
     * Gets the totalTravelerCount value for this FlightEndorsementDetail.
     * 
     * @return totalTravelerCount
     */
    public Integer getTotalTravelerCount() {
        return totalTravelerCount;
    }


    /**
     * Sets the totalTravelerCount value for this FlightEndorsementDetail.
     * 
     * @param totalTravelerCount
     */
    public void setTotalTravelerCount(Integer totalTravelerCount) {
        this.totalTravelerCount = totalTravelerCount;
    }


    /**
     * Gets the travelerCount value for this FlightEndorsementDetail.
     * 
     * @return travelerCount
     */
    public Integer getTravelerCount() {
        return travelerCount;
    }


    /**
     * Sets the travelerCount value for this FlightEndorsementDetail.
     * 
     * @param travelerCount
     */
    public void setTravelerCount(Integer travelerCount) {
        this.travelerCount = travelerCount;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof FlightEndorsementDetail)) return false;
        FlightEndorsementDetail other = (FlightEndorsementDetail) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.airline==null && other.getAirline()==null) || 
             (this.airline!=null &&
              this.airline.equals(other.getAirline()))) &&
            ((this.arrivalBeginTime==null && other.getArrivalBeginTime()==null) || 
             (this.arrivalBeginTime!=null &&
              this.arrivalBeginTime.equals(other.getArrivalBeginTime()))) &&
            ((this.arrivalCityCodes==null && other.getArrivalCityCodes()==null) || 
             (this.arrivalCityCodes!=null &&
              java.util.Arrays.equals(this.arrivalCityCodes, other.getArrivalCityCodes()))) &&
            ((this.arrivalEndTime==null && other.getArrivalEndTime()==null) || 
             (this.arrivalEndTime!=null &&
              this.arrivalEndTime.equals(other.getArrivalEndTime()))) &&
            ((this.currency==null && other.getCurrency()==null) || 
             (this.currency!=null &&
              this.currency.equals(other.getCurrency()))) &&
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
            ((this.discount==null && other.getDiscount()==null) || 
             (this.discount!=null &&
              this.discount.equals(other.getDiscount()))) &&
            ((this.flightWay==null && other.getFlightWay()==null) || 
             (this.flightWay!=null &&
              this.flightWay.equals(other.getFlightWay()))) &&
            ((this.fromCities==null && other.getFromCities()==null) || 
             (this.fromCities!=null &&
              java.util.Arrays.equals(this.fromCities, other.getFromCities()))) &&
            ((this.fromCitiesEN==null && other.getFromCitiesEN()==null) || 
             (this.fromCitiesEN!=null &&
              java.util.Arrays.equals(this.fromCitiesEN, other.getFromCitiesEN()))) &&
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
            ((this.seatClass==null && other.getSeatClass()==null) || 
             (this.seatClass!=null &&
              this.seatClass.equals(other.getSeatClass()))) &&
            ((this.skipFields==null && other.getSkipFields()==null) || 
             (this.skipFields!=null &&
              this.skipFields.equals(other.getSkipFields()))) &&
            ((this.takeOffBeginTime==null && other.getTakeOffBeginTime()==null) || 
             (this.takeOffBeginTime!=null &&
              this.takeOffBeginTime.equals(other.getTakeOffBeginTime()))) &&
            ((this.takeOffEndTime==null && other.getTakeOffEndTime()==null) || 
             (this.takeOffEndTime!=null &&
              this.takeOffEndTime.equals(other.getTakeOffEndTime()))) &&
            ((this.toCities==null && other.getToCities()==null) || 
             (this.toCities!=null &&
              java.util.Arrays.equals(this.toCities, other.getToCities()))) &&
            ((this.toCitiesEN==null && other.getToCitiesEN()==null) || 
             (this.toCitiesEN!=null &&
              java.util.Arrays.equals(this.toCitiesEN, other.getToCitiesEN()))) &&
            ((this.totalTravelerCount==null && other.getTotalTravelerCount()==null) || 
             (this.totalTravelerCount!=null &&
              this.totalTravelerCount.equals(other.getTotalTravelerCount()))) &&
            ((this.travelerCount==null && other.getTravelerCount()==null) || 
             (this.travelerCount!=null &&
              this.travelerCount.equals(other.getTravelerCount())));
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
        if (getAirline() != null) {
            _hashCode += getAirline().hashCode();
        }
        if (getArrivalBeginTime() != null) {
            _hashCode += getArrivalBeginTime().hashCode();
        }
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
        if (getArrivalEndTime() != null) {
            _hashCode += getArrivalEndTime().hashCode();
        }
        if (getCurrency() != null) {
            _hashCode += getCurrency().hashCode();
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
        if (getDiscount() != null) {
            _hashCode += getDiscount().hashCode();
        }
        if (getFlightWay() != null) {
            _hashCode += getFlightWay().hashCode();
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
        if (getFromCitiesEN() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFromCitiesEN());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getFromCitiesEN(), i);
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
        if (getSeatClass() != null) {
            _hashCode += getSeatClass().hashCode();
        }
        if (getSkipFields() != null) {
            _hashCode += getSkipFields().hashCode();
        }
        if (getTakeOffBeginTime() != null) {
            _hashCode += getTakeOffBeginTime().hashCode();
        }
        if (getTakeOffEndTime() != null) {
            _hashCode += getTakeOffEndTime().hashCode();
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
        if (getTotalTravelerCount() != null) {
            _hashCode += getTotalTravelerCount().hashCode();
        }
        if (getTravelerCount() != null) {
            _hashCode += getTravelerCount().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FlightEndorsementDetail.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "FlightEndorsementDetail"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("airline");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "Airline"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arrivalBeginTime");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "ArrivalBeginTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arrivalCityCodes");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "ArrivalCityCodes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arrivalEndTime");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "ArrivalEndTime"));
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
        elemField.setFieldName("discount");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "Discount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flightWay");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "FlightWay"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "FlightWayType"));
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
        elemField.setFieldName("fromCitiesEN");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "FromCitiesEN"));
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
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productType");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "ProductType"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "ProductTypeEnum"));
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
        elemField.setFieldName("seatClass");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "SeatClass"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "SeatClassType"));
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
        elemField.setFieldName("takeOffBeginTime");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "TakeOffBeginTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("takeOffEndTime");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "TakeOffEndTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
        elemField.setFieldName("totalTravelerCount");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "TotalTravelerCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("travelerCount");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "TravelerCount"));
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
