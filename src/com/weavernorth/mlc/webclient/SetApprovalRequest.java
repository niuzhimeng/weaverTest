/**
 * SetApprovalRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.mlc.webclient;

public class SetApprovalRequest  extends com.weavernorth.mlc.webclient.RequestWithEmployee  implements java.io.Serializable {
    private String approvalNumber;

    private com.weavernorth.mlc.webclient.CarCharterEndorsementDetail[] carCharterEndorsementDetails;

    private com.weavernorth.mlc.webclient.CarPickUpEndorsementDetail[] carPickUpEndorsementDetails;

    private com.weavernorth.mlc.webclient.CarQuickEndorsementDetail[] carQuickEndorsementDetails;

    private com.weavernorth.mlc.webclient.CarRentalEndorsementDetail[] carRentalEndorsementDetails;

    private String expiredTime;

    private ExtendField[] extendFieldList;

    private com.weavernorth.mlc.webclient.FlightEndorsementDetail[] flightEndorsementDetails;

    private com.weavernorth.mlc.webclient.HotelEndorsementDetail[] hotelEndorsementDetails;

    private RankInfo rankInfo;

    private String remark;

    private String signature;

    private Integer status;

    private com.weavernorth.mlc.webclient.TrainEndorsementDetail[] trainEndorsementDetails;

    public SetApprovalRequest() {
    }

    public SetApprovalRequest(
           Authentification auth,
           String ctripCardNO,
           String employeeID,
           String approvalNumber,
           com.weavernorth.mlc.webclient.CarCharterEndorsementDetail[] carCharterEndorsementDetails,
           com.weavernorth.mlc.webclient.CarPickUpEndorsementDetail[] carPickUpEndorsementDetails,
           com.weavernorth.mlc.webclient.CarQuickEndorsementDetail[] carQuickEndorsementDetails,
           com.weavernorth.mlc.webclient.CarRentalEndorsementDetail[] carRentalEndorsementDetails,
           String expiredTime,
           ExtendField[] extendFieldList,
           com.weavernorth.mlc.webclient.FlightEndorsementDetail[] flightEndorsementDetails,
           com.weavernorth.mlc.webclient.HotelEndorsementDetail[] hotelEndorsementDetails,
           RankInfo rankInfo,
           String remark,
           String signature,
           Integer status,
           com.weavernorth.mlc.webclient.TrainEndorsementDetail[] trainEndorsementDetails) {
        super(
            auth,
            ctripCardNO,
            employeeID);
        this.approvalNumber = approvalNumber;
        this.carCharterEndorsementDetails = carCharterEndorsementDetails;
        this.carPickUpEndorsementDetails = carPickUpEndorsementDetails;
        this.carQuickEndorsementDetails = carQuickEndorsementDetails;
        this.carRentalEndorsementDetails = carRentalEndorsementDetails;
        this.expiredTime = expiredTime;
        this.extendFieldList = extendFieldList;
        this.flightEndorsementDetails = flightEndorsementDetails;
        this.hotelEndorsementDetails = hotelEndorsementDetails;
        this.rankInfo = rankInfo;
        this.remark = remark;
        this.signature = signature;
        this.status = status;
        this.trainEndorsementDetails = trainEndorsementDetails;
    }


    /**
     * Gets the approvalNumber value for this SetApprovalRequest.
     * 
     * @return approvalNumber
     */
    public String getApprovalNumber() {
        return approvalNumber;
    }


    /**
     * Sets the approvalNumber value for this SetApprovalRequest.
     * 
     * @param approvalNumber
     */
    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }


    /**
     * Gets the carCharterEndorsementDetails value for this SetApprovalRequest.
     * 
     * @return carCharterEndorsementDetails
     */
    public com.weavernorth.mlc.webclient.CarCharterEndorsementDetail[] getCarCharterEndorsementDetails() {
        return carCharterEndorsementDetails;
    }


    /**
     * Sets the carCharterEndorsementDetails value for this SetApprovalRequest.
     * 
     * @param carCharterEndorsementDetails
     */
    public void setCarCharterEndorsementDetails(com.weavernorth.mlc.webclient.CarCharterEndorsementDetail[] carCharterEndorsementDetails) {
        this.carCharterEndorsementDetails = carCharterEndorsementDetails;
    }


    /**
     * Gets the carPickUpEndorsementDetails value for this SetApprovalRequest.
     * 
     * @return carPickUpEndorsementDetails
     */
    public com.weavernorth.mlc.webclient.CarPickUpEndorsementDetail[] getCarPickUpEndorsementDetails() {
        return carPickUpEndorsementDetails;
    }


    /**
     * Sets the carPickUpEndorsementDetails value for this SetApprovalRequest.
     * 
     * @param carPickUpEndorsementDetails
     */
    public void setCarPickUpEndorsementDetails(com.weavernorth.mlc.webclient.CarPickUpEndorsementDetail[] carPickUpEndorsementDetails) {
        this.carPickUpEndorsementDetails = carPickUpEndorsementDetails;
    }


    /**
     * Gets the carQuickEndorsementDetails value for this SetApprovalRequest.
     * 
     * @return carQuickEndorsementDetails
     */
    public com.weavernorth.mlc.webclient.CarQuickEndorsementDetail[] getCarQuickEndorsementDetails() {
        return carQuickEndorsementDetails;
    }


    /**
     * Sets the carQuickEndorsementDetails value for this SetApprovalRequest.
     * 
     * @param carQuickEndorsementDetails
     */
    public void setCarQuickEndorsementDetails(com.weavernorth.mlc.webclient.CarQuickEndorsementDetail[] carQuickEndorsementDetails) {
        this.carQuickEndorsementDetails = carQuickEndorsementDetails;
    }


    /**
     * Gets the carRentalEndorsementDetails value for this SetApprovalRequest.
     * 
     * @return carRentalEndorsementDetails
     */
    public com.weavernorth.mlc.webclient.CarRentalEndorsementDetail[] getCarRentalEndorsementDetails() {
        return carRentalEndorsementDetails;
    }


    /**
     * Sets the carRentalEndorsementDetails value for this SetApprovalRequest.
     * 
     * @param carRentalEndorsementDetails
     */
    public void setCarRentalEndorsementDetails(com.weavernorth.mlc.webclient.CarRentalEndorsementDetail[] carRentalEndorsementDetails) {
        this.carRentalEndorsementDetails = carRentalEndorsementDetails;
    }


    /**
     * Gets the expiredTime value for this SetApprovalRequest.
     * 
     * @return expiredTime
     */
    public String getExpiredTime() {
        return expiredTime;
    }


    /**
     * Sets the expiredTime value for this SetApprovalRequest.
     * 
     * @param expiredTime
     */
    public void setExpiredTime(String expiredTime) {
        this.expiredTime = expiredTime;
    }


    /**
     * Gets the extendFieldList value for this SetApprovalRequest.
     * 
     * @return extendFieldList
     */
    public ExtendField[] getExtendFieldList() {
        return extendFieldList;
    }


    /**
     * Sets the extendFieldList value for this SetApprovalRequest.
     * 
     * @param extendFieldList
     */
    public void setExtendFieldList(ExtendField[] extendFieldList) {
        this.extendFieldList = extendFieldList;
    }


    /**
     * Gets the flightEndorsementDetails value for this SetApprovalRequest.
     * 
     * @return flightEndorsementDetails
     */
    public com.weavernorth.mlc.webclient.FlightEndorsementDetail[] getFlightEndorsementDetails() {
        return flightEndorsementDetails;
    }


    /**
     * Sets the flightEndorsementDetails value for this SetApprovalRequest.
     * 
     * @param flightEndorsementDetails
     */
    public void setFlightEndorsementDetails(com.weavernorth.mlc.webclient.FlightEndorsementDetail[] flightEndorsementDetails) {
        this.flightEndorsementDetails = flightEndorsementDetails;
    }


    /**
     * Gets the hotelEndorsementDetails value for this SetApprovalRequest.
     * 
     * @return hotelEndorsementDetails
     */
    public com.weavernorth.mlc.webclient.HotelEndorsementDetail[] getHotelEndorsementDetails() {
        return hotelEndorsementDetails;
    }


    /**
     * Sets the hotelEndorsementDetails value for this SetApprovalRequest.
     * 
     * @param hotelEndorsementDetails
     */
    public void setHotelEndorsementDetails(com.weavernorth.mlc.webclient.HotelEndorsementDetail[] hotelEndorsementDetails) {
        this.hotelEndorsementDetails = hotelEndorsementDetails;
    }


    /**
     * Gets the rankInfo value for this SetApprovalRequest.
     * 
     * @return rankInfo
     */
    public RankInfo getRankInfo() {
        return rankInfo;
    }


    /**
     * Sets the rankInfo value for this SetApprovalRequest.
     * 
     * @param rankInfo
     */
    public void setRankInfo(RankInfo rankInfo) {
        this.rankInfo = rankInfo;
    }


    /**
     * Gets the remark value for this SetApprovalRequest.
     * 
     * @return remark
     */
    public String getRemark() {
        return remark;
    }


    /**
     * Sets the remark value for this SetApprovalRequest.
     * 
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }


    /**
     * Gets the signature value for this SetApprovalRequest.
     * 
     * @return signature
     */
    public String getSignature() {
        return signature;
    }


    /**
     * Sets the signature value for this SetApprovalRequest.
     * 
     * @param signature
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }


    /**
     * Gets the status value for this SetApprovalRequest.
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }


    /**
     * Sets the status value for this SetApprovalRequest.
     * 
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }


    /**
     * Gets the trainEndorsementDetails value for this SetApprovalRequest.
     * 
     * @return trainEndorsementDetails
     */
    public com.weavernorth.mlc.webclient.TrainEndorsementDetail[] getTrainEndorsementDetails() {
        return trainEndorsementDetails;
    }


    /**
     * Sets the trainEndorsementDetails value for this SetApprovalRequest.
     * 
     * @param trainEndorsementDetails
     */
    public void setTrainEndorsementDetails(com.weavernorth.mlc.webclient.TrainEndorsementDetail[] trainEndorsementDetails) {
        this.trainEndorsementDetails = trainEndorsementDetails;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof SetApprovalRequest)) return false;
        SetApprovalRequest other = (SetApprovalRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.approvalNumber==null && other.getApprovalNumber()==null) || 
             (this.approvalNumber!=null &&
              this.approvalNumber.equals(other.getApprovalNumber()))) &&
            ((this.carCharterEndorsementDetails==null && other.getCarCharterEndorsementDetails()==null) || 
             (this.carCharterEndorsementDetails!=null &&
              java.util.Arrays.equals(this.carCharterEndorsementDetails, other.getCarCharterEndorsementDetails()))) &&
            ((this.carPickUpEndorsementDetails==null && other.getCarPickUpEndorsementDetails()==null) || 
             (this.carPickUpEndorsementDetails!=null &&
              java.util.Arrays.equals(this.carPickUpEndorsementDetails, other.getCarPickUpEndorsementDetails()))) &&
            ((this.carQuickEndorsementDetails==null && other.getCarQuickEndorsementDetails()==null) || 
             (this.carQuickEndorsementDetails!=null &&
              java.util.Arrays.equals(this.carQuickEndorsementDetails, other.getCarQuickEndorsementDetails()))) &&
            ((this.carRentalEndorsementDetails==null && other.getCarRentalEndorsementDetails()==null) || 
             (this.carRentalEndorsementDetails!=null &&
              java.util.Arrays.equals(this.carRentalEndorsementDetails, other.getCarRentalEndorsementDetails()))) &&
            ((this.expiredTime==null && other.getExpiredTime()==null) || 
             (this.expiredTime!=null &&
              this.expiredTime.equals(other.getExpiredTime()))) &&
            ((this.extendFieldList==null && other.getExtendFieldList()==null) || 
             (this.extendFieldList!=null &&
              java.util.Arrays.equals(this.extendFieldList, other.getExtendFieldList()))) &&
            ((this.flightEndorsementDetails==null && other.getFlightEndorsementDetails()==null) || 
             (this.flightEndorsementDetails!=null &&
              java.util.Arrays.equals(this.flightEndorsementDetails, other.getFlightEndorsementDetails()))) &&
            ((this.hotelEndorsementDetails==null && other.getHotelEndorsementDetails()==null) || 
             (this.hotelEndorsementDetails!=null &&
              java.util.Arrays.equals(this.hotelEndorsementDetails, other.getHotelEndorsementDetails()))) &&
            ((this.rankInfo==null && other.getRankInfo()==null) || 
             (this.rankInfo!=null &&
              this.rankInfo.equals(other.getRankInfo()))) &&
            ((this.remark==null && other.getRemark()==null) || 
             (this.remark!=null &&
              this.remark.equals(other.getRemark()))) &&
            ((this.signature==null && other.getSignature()==null) || 
             (this.signature!=null &&
              this.signature.equals(other.getSignature()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.trainEndorsementDetails==null && other.getTrainEndorsementDetails()==null) || 
             (this.trainEndorsementDetails!=null &&
              java.util.Arrays.equals(this.trainEndorsementDetails, other.getTrainEndorsementDetails())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getApprovalNumber() != null) {
            _hashCode += getApprovalNumber().hashCode();
        }
        if (getCarCharterEndorsementDetails() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCarCharterEndorsementDetails());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getCarCharterEndorsementDetails(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCarPickUpEndorsementDetails() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCarPickUpEndorsementDetails());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getCarPickUpEndorsementDetails(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCarQuickEndorsementDetails() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCarQuickEndorsementDetails());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getCarQuickEndorsementDetails(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCarRentalEndorsementDetails() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCarRentalEndorsementDetails());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getCarRentalEndorsementDetails(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getExpiredTime() != null) {
            _hashCode += getExpiredTime().hashCode();
        }
        if (getExtendFieldList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getExtendFieldList());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getExtendFieldList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getFlightEndorsementDetails() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFlightEndorsementDetails());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getFlightEndorsementDetails(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getHotelEndorsementDetails() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getHotelEndorsementDetails());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getHotelEndorsementDetails(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getRankInfo() != null) {
            _hashCode += getRankInfo().hashCode();
        }
        if (getRemark() != null) {
            _hashCode += getRemark().hashCode();
        }
        if (getSignature() != null) {
            _hashCode += getSignature().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getTrainEndorsementDetails() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTrainEndorsementDetails());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getTrainEndorsementDetails(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SetApprovalRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "SetApprovalRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("approvalNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "ApprovalNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("carCharterEndorsementDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "CarCharterEndorsementDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "CarCharterEndorsementDetail"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "CarCharterEndorsementDetail"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("carPickUpEndorsementDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "CarPickUpEndorsementDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "CarPickUpEndorsementDetail"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "CarPickUpEndorsementDetail"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("carQuickEndorsementDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "CarQuickEndorsementDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "CarQuickEndorsementDetail"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "CarQuickEndorsementDetail"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("carRentalEndorsementDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "CarRentalEndorsementDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "CarRentalEndorsementDetail"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "CarRentalEndorsementDetail"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expiredTime");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "ExpiredTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extendFieldList");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "ExtendFieldList"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "ExtendField"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "ExtendField"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flightEndorsementDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "FlightEndorsementDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "FlightEndorsementDetail"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "FlightEndorsementDetail"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hotelEndorsementDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "HotelEndorsementDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "HotelEndorsementDetail"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "HotelEndorsementDetail"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rankInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "RankInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SwitchAPI.Entity.ApprovalEntities", "RankInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("remark");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "Remark"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signature");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "Signature"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "Status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trainEndorsementDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "TrainEndorsementDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "TrainEndorsementDetail"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "TrainEndorsementDetail"));
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
