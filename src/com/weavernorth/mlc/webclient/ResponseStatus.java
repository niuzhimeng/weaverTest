/**
 * ResponseStatus.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.mlc.webclient;

public class ResponseStatus  implements java.io.Serializable {
    private Integer errorCode;

    private String message;

    private Boolean success;

    public ResponseStatus() {
    }

    public ResponseStatus(
           Integer errorCode,
           String message,
           Boolean success) {
           this.errorCode = errorCode;
           this.message = message;
           this.success = success;
    }


    /**
     * Gets the errorCode value for this ResponseStatus.
     * 
     * @return errorCode
     */
    public Integer getErrorCode() {
        return errorCode;
    }


    /**
     * Sets the errorCode value for this ResponseStatus.
     * 
     * @param errorCode
     */
    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }


    /**
     * Gets the message value for this ResponseStatus.
     * 
     * @return message
     */
    public String getMessage() {
        return message;
    }


    /**
     * Sets the message value for this ResponseStatus.
     * 
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }


    /**
     * Gets the success value for this ResponseStatus.
     * 
     * @return success
     */
    public Boolean getSuccess() {
        return success;
    }


    /**
     * Sets the success value for this ResponseStatus.
     * 
     * @param success
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ResponseStatus)) return false;
        ResponseStatus other = (ResponseStatus) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.errorCode==null && other.getErrorCode()==null) || 
             (this.errorCode!=null &&
              this.errorCode.equals(other.getErrorCode()))) &&
            ((this.message==null && other.getMessage()==null) || 
             (this.message!=null &&
              this.message.equals(other.getMessage()))) &&
            ((this.success==null && other.getSuccess()==null) || 
             (this.success!=null &&
              this.success.equals(other.getSuccess())));
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
        if (getErrorCode() != null) {
            _hashCode += getErrorCode().hashCode();
        }
        if (getMessage() != null) {
            _hashCode += getMessage().hashCode();
        }
        if (getSuccess() != null) {
            _hashCode += getSuccess().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResponseStatus.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/", "ResponseStatus"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorCode");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/", "ErrorCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("message");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/", "Message"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("success");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/", "Success"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
