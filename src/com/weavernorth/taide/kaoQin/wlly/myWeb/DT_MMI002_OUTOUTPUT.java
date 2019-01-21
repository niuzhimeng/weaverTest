/**
 * DT_MMI002_OUTOUTPUT.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.taide.kaoQin.wlly.myWeb;

public class DT_MMI002_OUTOUTPUT  implements java.io.Serializable {
    /* OA领料申请号 */
    private java.lang.String REMARK1;

    /* 预留 */
    private java.lang.String RSNUM;

    /* 消息类型 */
    private java.lang.String MSG_TYPE;

    /* 消息文本 */
    private java.lang.String MESSAGE;

    public DT_MMI002_OUTOUTPUT() {
    }

    public DT_MMI002_OUTOUTPUT(
           java.lang.String REMARK1,
           java.lang.String RSNUM,
           java.lang.String MSG_TYPE,
           java.lang.String MESSAGE) {
           this.REMARK1 = REMARK1;
           this.RSNUM = RSNUM;
           this.MSG_TYPE = MSG_TYPE;
           this.MESSAGE = MESSAGE;
    }


    /**
     * Gets the REMARK1 value for this DT_MMI002_OUTOUTPUT.
     * 
     * @return REMARK1   * OA领料申请号
     */
    public java.lang.String getREMARK1() {
        return REMARK1;
    }


    /**
     * Sets the REMARK1 value for this DT_MMI002_OUTOUTPUT.
     * 
     * @param REMARK1   * OA领料申请号
     */
    public void setREMARK1(java.lang.String REMARK1) {
        this.REMARK1 = REMARK1;
    }


    /**
     * Gets the RSNUM value for this DT_MMI002_OUTOUTPUT.
     * 
     * @return RSNUM   * 预留
     */
    public java.lang.String getRSNUM() {
        return RSNUM;
    }


    /**
     * Sets the RSNUM value for this DT_MMI002_OUTOUTPUT.
     * 
     * @param RSNUM   * 预留
     */
    public void setRSNUM(java.lang.String RSNUM) {
        this.RSNUM = RSNUM;
    }


    /**
     * Gets the MSG_TYPE value for this DT_MMI002_OUTOUTPUT.
     * 
     * @return MSG_TYPE   * 消息类型
     */
    public java.lang.String getMSG_TYPE() {
        return MSG_TYPE;
    }


    /**
     * Sets the MSG_TYPE value for this DT_MMI002_OUTOUTPUT.
     * 
     * @param MSG_TYPE   * 消息类型
     */
    public void setMSG_TYPE(java.lang.String MSG_TYPE) {
        this.MSG_TYPE = MSG_TYPE;
    }


    /**
     * Gets the MESSAGE value for this DT_MMI002_OUTOUTPUT.
     * 
     * @return MESSAGE   * 消息文本
     */
    public java.lang.String getMESSAGE() {
        return MESSAGE;
    }


    /**
     * Sets the MESSAGE value for this DT_MMI002_OUTOUTPUT.
     * 
     * @param MESSAGE   * 消息文本
     */
    public void setMESSAGE(java.lang.String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_MMI002_OUTOUTPUT)) return false;
        DT_MMI002_OUTOUTPUT other = (DT_MMI002_OUTOUTPUT) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.REMARK1==null && other.getREMARK1()==null) || 
             (this.REMARK1!=null &&
              this.REMARK1.equals(other.getREMARK1()))) &&
            ((this.RSNUM==null && other.getRSNUM()==null) || 
             (this.RSNUM!=null &&
              this.RSNUM.equals(other.getRSNUM()))) &&
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
        if (getREMARK1() != null) {
            _hashCode += getREMARK1().hashCode();
        }
        if (getRSNUM() != null) {
            _hashCode += getRSNUM().hashCode();
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
        new org.apache.axis.description.TypeDesc(DT_MMI002_OUTOUTPUT.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tidepharm.com/OA", ">DT_MMI002_OUT>OUTPUT"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("REMARK1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "REMARK1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RSNUM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RSNUM"));
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
