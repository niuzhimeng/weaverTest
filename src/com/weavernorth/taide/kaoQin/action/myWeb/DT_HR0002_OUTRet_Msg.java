/**
 * DT_HR0002_OUTRet_Msg.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.taide.kaoQin.action.myWeb;

public class DT_HR0002_OUTRet_Msg  implements java.io.Serializable {
    /* 单据号 */
    private java.lang.String zattend_ID;

    private java.lang.String PERNR;

    /* S-成功,E-错误,W-警告 */
    private java.lang.String MSG_TYPE;

    /* 消息文本 */
    private java.lang.String MESSAGE;

    public DT_HR0002_OUTRet_Msg() {
    }

    public DT_HR0002_OUTRet_Msg(
           java.lang.String zattend_ID,
           java.lang.String PERNR,
           java.lang.String MSG_TYPE,
           java.lang.String MESSAGE) {
           this.zattend_ID = zattend_ID;
           this.PERNR = PERNR;
           this.MSG_TYPE = MSG_TYPE;
           this.MESSAGE = MESSAGE;
    }


    /**
     * Gets the zattend_ID value for this DT_HR0002_OUTRet_Msg.
     * 
     * @return zattend_ID   * 单据号
     */
    public java.lang.String getZattend_ID() {
        return zattend_ID;
    }


    /**
     * Sets the zattend_ID value for this DT_HR0002_OUTRet_Msg.
     * 
     * @param zattend_ID   * 单据号
     */
    public void setZattend_ID(java.lang.String zattend_ID) {
        this.zattend_ID = zattend_ID;
    }


    /**
     * Gets the PERNR value for this DT_HR0002_OUTRet_Msg.
     * 
     * @return PERNR
     */
    public java.lang.String getPERNR() {
        return PERNR;
    }


    /**
     * Sets the PERNR value for this DT_HR0002_OUTRet_Msg.
     * 
     * @param PERNR
     */
    public void setPERNR(java.lang.String PERNR) {
        this.PERNR = PERNR;
    }


    /**
     * Gets the MSG_TYPE value for this DT_HR0002_OUTRet_Msg.
     * 
     * @return MSG_TYPE   * S-成功,E-错误,W-警告
     */
    public java.lang.String getMSG_TYPE() {
        return MSG_TYPE;
    }


    /**
     * Sets the MSG_TYPE value for this DT_HR0002_OUTRet_Msg.
     * 
     * @param MSG_TYPE   * S-成功,E-错误,W-警告
     */
    public void setMSG_TYPE(java.lang.String MSG_TYPE) {
        this.MSG_TYPE = MSG_TYPE;
    }


    /**
     * Gets the MESSAGE value for this DT_HR0002_OUTRet_Msg.
     * 
     * @return MESSAGE   * 消息文本
     */
    public java.lang.String getMESSAGE() {
        return MESSAGE;
    }


    /**
     * Sets the MESSAGE value for this DT_HR0002_OUTRet_Msg.
     * 
     * @param MESSAGE   * 消息文本
     */
    public void setMESSAGE(java.lang.String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_HR0002_OUTRet_Msg)) return false;
        DT_HR0002_OUTRet_Msg other = (DT_HR0002_OUTRet_Msg) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.zattend_ID==null && other.getZattend_ID()==null) || 
             (this.zattend_ID!=null &&
              this.zattend_ID.equals(other.getZattend_ID()))) &&
            ((this.PERNR==null && other.getPERNR()==null) || 
             (this.PERNR!=null &&
              this.PERNR.equals(other.getPERNR()))) &&
            ((this.MSG_TYPE==null && other.getMSG_TYPE()==null) || 
             (this.MSG_TYPE!=null &&
              this.MSG_TYPE.equals(other.getMSG_TYPE()))) &&
            ((this.MESSAGE==null && other.getMESSAGE()==null) || 
             (this.MESSAGE!=null &&
              this.MESSAGE.equals(other.getMESSAGE())));
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
        if (getZattend_ID() != null) {
            _hashCode += getZattend_ID().hashCode();
        }
        if (getPERNR() != null) {
            _hashCode += getPERNR().hashCode();
        }
        if (getMSG_TYPE() != null) {
            _hashCode += getMSG_TYPE().hashCode();
        }
        if (getMESSAGE() != null) {
            _hashCode += getMESSAGE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_HR0002_OUTRet_Msg.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tidepharm.com/OA", ">DT_HR0002_OUT>Ret_Msg"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("zattend_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Zattend_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PERNR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PERNR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MSG_TYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MSG_TYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MESSAGE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MESSAGE"));
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
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
