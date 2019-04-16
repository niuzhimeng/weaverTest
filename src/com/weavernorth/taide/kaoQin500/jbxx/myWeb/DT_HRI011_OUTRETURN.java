/**
 * DT_HRI011_OUTRETURN.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.taide.kaoQin500.jbxx.myWeb;

public class DT_HRI011_OUTRETURN  implements java.io.Serializable {
    /* 人员号 */
    private String PERNR;

    /* 信息类型 */
    private String ZINFOTYPE;

    /* 导入日期 */
    private String inputDate;

    /* 导入结果 */
    private String MSG_TYPE;

    /* 异常原因 */
    private String MESSAGE;

    /* 备用字段1 */
    private String additional1;

    /* 备用字段2 */
    private String additional2;

    public DT_HRI011_OUTRETURN() {
    }

    public DT_HRI011_OUTRETURN(
           String PERNR,
           String ZINFOTYPE,
           String inputDate,
           String MSG_TYPE,
           String MESSAGE,
           String additional1,
           String additional2) {
           this.PERNR = PERNR;
           this.ZINFOTYPE = ZINFOTYPE;
           this.inputDate = inputDate;
           this.MSG_TYPE = MSG_TYPE;
           this.MESSAGE = MESSAGE;
           this.additional1 = additional1;
           this.additional2 = additional2;
    }


    /**
     * Gets the PERNR value for this DT_HRI011_OUTRETURN.
     *
     * @return PERNR   * 人员号
     */
    public String getPERNR() {
        return PERNR;
    }


    /**
     * Sets the PERNR value for this DT_HRI011_OUTRETURN.
     *
     * @param PERNR   * 人员号
     */
    public void setPERNR(String PERNR) {
        this.PERNR = PERNR;
    }


    /**
     * Gets the ZINFOTYPE value for this DT_HRI011_OUTRETURN.
     *
     * @return ZINFOTYPE   * 信息类型
     */
    public String getZINFOTYPE() {
        return ZINFOTYPE;
    }


    /**
     * Sets the ZINFOTYPE value for this DT_HRI011_OUTRETURN.
     *
     * @param ZINFOTYPE   * 信息类型
     */
    public void setZINFOTYPE(String ZINFOTYPE) {
        this.ZINFOTYPE = ZINFOTYPE;
    }


    /**
     * Gets the inputDate value for this DT_HRI011_OUTRETURN.
     *
     * @return inputDate   * 导入日期
     */
    public String getInputDate() {
        return inputDate;
    }


    /**
     * Sets the inputDate value for this DT_HRI011_OUTRETURN.
     *
     * @param inputDate   * 导入日期
     */
    public void setInputDate(String inputDate) {
        this.inputDate = inputDate;
    }


    /**
     * Gets the MSG_TYPE value for this DT_HRI011_OUTRETURN.
     *
     * @return MSG_TYPE   * 导入结果
     */
    public String getMSG_TYPE() {
        return MSG_TYPE;
    }


    /**
     * Sets the MSG_TYPE value for this DT_HRI011_OUTRETURN.
     *
     * @param MSG_TYPE   * 导入结果
     */
    public void setMSG_TYPE(String MSG_TYPE) {
        this.MSG_TYPE = MSG_TYPE;
    }


    /**
     * Gets the MESSAGE value for this DT_HRI011_OUTRETURN.
     *
     * @return MESSAGE   * 异常原因
     */
    public String getMESSAGE() {
        return MESSAGE;
    }


    /**
     * Sets the MESSAGE value for this DT_HRI011_OUTRETURN.
     *
     * @param MESSAGE   * 异常原因
     */
    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }


    /**
     * Gets the additional1 value for this DT_HRI011_OUTRETURN.
     *
     * @return additional1   * 备用字段1
     */
    public String getAdditional1() {
        return additional1;
    }


    /**
     * Sets the additional1 value for this DT_HRI011_OUTRETURN.
     *
     * @param additional1   * 备用字段1
     */
    public void setAdditional1(String additional1) {
        this.additional1 = additional1;
    }


    /**
     * Gets the additional2 value for this DT_HRI011_OUTRETURN.
     *
     * @return additional2   * 备用字段2
     */
    public String getAdditional2() {
        return additional2;
    }


    /**
     * Sets the additional2 value for this DT_HRI011_OUTRETURN.
     *
     * @param additional2   * 备用字段2
     */
    public void setAdditional2(String additional2) {
        this.additional2 = additional2;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof DT_HRI011_OUTRETURN)) return false;
        DT_HRI011_OUTRETURN other = (DT_HRI011_OUTRETURN) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.PERNR==null && other.getPERNR()==null) ||
             (this.PERNR!=null &&
              this.PERNR.equals(other.getPERNR()))) &&
            ((this.ZINFOTYPE==null && other.getZINFOTYPE()==null) ||
             (this.ZINFOTYPE!=null &&
              this.ZINFOTYPE.equals(other.getZINFOTYPE()))) &&
            ((this.inputDate==null && other.getInputDate()==null) ||
             (this.inputDate!=null &&
              this.inputDate.equals(other.getInputDate()))) &&
            ((this.MSG_TYPE==null && other.getMSG_TYPE()==null) ||
             (this.MSG_TYPE!=null &&
              this.MSG_TYPE.equals(other.getMSG_TYPE()))) &&
            ((this.MESSAGE==null && other.getMESSAGE()==null) ||
             (this.MESSAGE!=null &&
              this.MESSAGE.equals(other.getMESSAGE()))) &&
            ((this.additional1==null && other.getAdditional1()==null) ||
             (this.additional1!=null &&
              this.additional1.equals(other.getAdditional1()))) &&
            ((this.additional2==null && other.getAdditional2()==null) ||
             (this.additional2!=null &&
              this.additional2.equals(other.getAdditional2())));
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
        if (getPERNR() != null) {
            _hashCode += getPERNR().hashCode();
        }
        if (getZINFOTYPE() != null) {
            _hashCode += getZINFOTYPE().hashCode();
        }
        if (getInputDate() != null) {
            _hashCode += getInputDate().hashCode();
        }
        if (getMSG_TYPE() != null) {
            _hashCode += getMSG_TYPE().hashCode();
        }
        if (getMESSAGE() != null) {
            _hashCode += getMESSAGE().hashCode();
        }
        if (getAdditional1() != null) {
            _hashCode += getAdditional1().hashCode();
        }
        if (getAdditional2() != null) {
            _hashCode += getAdditional2().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_HRI011_OUTRETURN.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tidepharm.com/YT", ">DT_HRI011_OUT>RETURN"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PERNR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PERNR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ZINFOTYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ZINFOTYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inputDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "InputDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MSG_TYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MSG_TYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MESSAGE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MESSAGE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("additional1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Additional1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("additional2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Additional2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
