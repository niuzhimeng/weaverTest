/**
 * UserLogList.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.zgzt;

public class UserLogList  implements java.io.Serializable {
    private int id;

    private String reason;

    private java.util.Date startTime;

    private java.util.Date endTime;

    public UserLogList() {
    }

    public UserLogList(
           int id,
           String reason,
           java.util.Date startTime,
           java.util.Date endTime) {
           this.id = id;
           this.reason = reason;
           this.startTime = startTime;
           this.endTime = endTime;
    }


    /**
     * Gets the id value for this UserLogList.
     *
     * @return id
     */
    public int getId() {
        return id;
    }


    /**
     * Sets the id value for this UserLogList.
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets the reason value for this UserLogList.
     *
     * @return reason
     */
    public String getReason() {
        return reason;
    }


    /**
     * Sets the reason value for this UserLogList.
     *
     * @param reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }


    /**
     * Gets the startTime value for this UserLogList.
     *
     * @return startTime
     */
    public java.util.Date getStartTime() {
        return startTime;
    }


    /**
     * Sets the startTime value for this UserLogList.
     *
     * @param startTime
     */
    public void setStartTime(java.util.Date startTime) {
        this.startTime = startTime;
    }


    /**
     * Gets the endTime value for this UserLogList.
     *
     * @return endTime
     */
    public java.util.Date getEndTime() {
        return endTime;
    }


    /**
     * Sets the endTime value for this UserLogList.
     *
     * @param endTime
     */
    public void setEndTime(java.util.Date endTime) {
        this.endTime = endTime;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof UserLogList)) return false;
        UserLogList other = (UserLogList) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            this.id == other.getId() &&
            ((this.reason==null && other.getReason()==null) ||
             (this.reason!=null &&
              this.reason.equals(other.getReason()))) &&
            ((this.startTime==null && other.getStartTime()==null) ||
             (this.startTime!=null &&
              this.startTime.equals(other.getStartTime()))) &&
            ((this.endTime==null && other.getEndTime()==null) ||
             (this.endTime!=null &&
              this.endTime.equals(other.getEndTime())));
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
        _hashCode += getId();
        if (getReason() != null) {
            _hashCode += getReason().hashCode();
        }
        if (getStartTime() != null) {
            _hashCode += getStartTime().hashCode();
        }
        if (getEndTime() != null) {
            _hashCode += getEndTime().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UserLogList.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.zhengtongauto.com/ws/demo", ">UserLogList"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.zhengtongauto.com/ws/demo", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reason");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.zhengtongauto.com/ws/demo", "reason"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.zhengtongauto.com/ws/demo", "startTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.zhengtongauto.com/ws/demo", "endTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
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
