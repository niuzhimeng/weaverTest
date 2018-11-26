/**
 * WebResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.zgsy.webUtil;

public class WebResult  implements java.io.Serializable {
    private String billNo;

    private int billType;

    private int state;

    private String message;

    public WebResult() {
    }

    public WebResult(
           String billNo,
           int billType,
           int state,
           String message) {
           this.billNo = billNo;
           this.billType = billType;
           this.state = state;
           this.message = message;
    }


    /**
     * Gets the billNo value for this WebResult.
     *
     * @return billNo
     */
    public String getBillNo() {
        return billNo;
    }


    /**
     * Sets the billNo value for this WebResult.
     *
     * @param billNo
     */
    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }


    /**
     * Gets the billType value for this WebResult.
     *
     * @return billType
     */
    public int getBillType() {
        return billType;
    }


    /**
     * Sets the billType value for this WebResult.
     *
     * @param billType
     */
    public void setBillType(int billType) {
        this.billType = billType;
    }


    /**
     * Gets the state value for this WebResult.
     *
     * @return state
     */
    public int getState() {
        return state;
    }


    /**
     * Sets the state value for this WebResult.
     *
     * @param state
     */
    public void setState(int state) {
        this.state = state;
    }


    /**
     * Gets the message value for this WebResult.
     *
     * @return message
     */
    public String getMessage() {
        return message;
    }


    /**
     * Sets the message value for this WebResult.
     *
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof WebResult)) return false;
        WebResult other = (WebResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.billNo==null && other.getBillNo()==null) ||
             (this.billNo!=null &&
              this.billNo.equals(other.getBillNo()))) &&
            this.billType == other.getBillType() &&
            this.state == other.getState() &&
            ((this.message==null && other.getMessage()==null) ||
             (this.message!=null &&
              this.message.equals(other.getMessage())));
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
        if (getBillNo() != null) {
            _hashCode += getBillNo().hashCode();
        }
        _hashCode += getBillType();
        _hashCode += getState();
        if (getMessage() != null) {
            _hashCode += getMessage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(WebResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "WebResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("billNo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "BillNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("billType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "BillType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("state");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "State"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("message");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Message"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
