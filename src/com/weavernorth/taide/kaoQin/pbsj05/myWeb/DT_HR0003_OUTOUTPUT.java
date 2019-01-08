/**
 * DT_HR0003_OUTOUTPUT.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.taide.kaoQin.pbsj05.myWeb;

public class DT_HR0003_OUTOUTPUT  implements java.io.Serializable {
    /* 人员编号 */
    private java.lang.String PERNR;

    /* 日期 */
    private java.lang.String DATUM;

    /* 日工作日程表的人事子范围分组 */
    private java.lang.String MOTPR;

    /* 日工作计划 */
    private java.lang.String TPROG;

    /* 公共假日类 */
    private java.lang.String FTKLA;

    /* 日工作计划类型 */
    private java.lang.String TPKLA;

    /* 班次描述 */
    private java.lang.String TTEXT;

    /* 班次时长 */
    private java.lang.String SOLLZ;

    /* 班次开始时间 */
    private java.lang.String SOBEG;

    /* 班次结束时间 */
    private java.lang.String SOEND;

    /* 消息类型 */
    private java.lang.String MSG_TYPE;

    /* 消息文本 */
    private java.lang.String MESSAGE;

    /* 备用字段1 */
    private java.lang.String additional1;

    /* 备用字段2 */
    private java.lang.String additional2;

    /* 备用字段3 */
    private java.lang.String additional3;

    /* 备用字段4 */
    private java.lang.String additional4;

    /* 备用字段5 */
    private java.lang.String additional5;

    public DT_HR0003_OUTOUTPUT() {
    }

    public DT_HR0003_OUTOUTPUT(
           java.lang.String PERNR,
           java.lang.String DATUM,
           java.lang.String MOTPR,
           java.lang.String TPROG,
           java.lang.String FTKLA,
           java.lang.String TPKLA,
           java.lang.String TTEXT,
           java.lang.String SOLLZ,
           java.lang.String SOBEG,
           java.lang.String SOEND,
           java.lang.String MSG_TYPE,
           java.lang.String MESSAGE,
           java.lang.String additional1,
           java.lang.String additional2,
           java.lang.String additional3,
           java.lang.String additional4,
           java.lang.String additional5) {
           this.PERNR = PERNR;
           this.DATUM = DATUM;
           this.MOTPR = MOTPR;
           this.TPROG = TPROG;
           this.FTKLA = FTKLA;
           this.TPKLA = TPKLA;
           this.TTEXT = TTEXT;
           this.SOLLZ = SOLLZ;
           this.SOBEG = SOBEG;
           this.SOEND = SOEND;
           this.MSG_TYPE = MSG_TYPE;
           this.MESSAGE = MESSAGE;
           this.additional1 = additional1;
           this.additional2 = additional2;
           this.additional3 = additional3;
           this.additional4 = additional4;
           this.additional5 = additional5;
    }


    /**
     * Gets the PERNR value for this DT_HR0003_OUTOUTPUT.
     * 
     * @return PERNR   * 人员编号
     */
    public java.lang.String getPERNR() {
        return PERNR;
    }


    /**
     * Sets the PERNR value for this DT_HR0003_OUTOUTPUT.
     * 
     * @param PERNR   * 人员编号
     */
    public void setPERNR(java.lang.String PERNR) {
        this.PERNR = PERNR;
    }


    /**
     * Gets the DATUM value for this DT_HR0003_OUTOUTPUT.
     * 
     * @return DATUM   * 日期
     */
    public java.lang.String getDATUM() {
        return DATUM;
    }


    /**
     * Sets the DATUM value for this DT_HR0003_OUTOUTPUT.
     * 
     * @param DATUM   * 日期
     */
    public void setDATUM(java.lang.String DATUM) {
        this.DATUM = DATUM;
    }


    /**
     * Gets the MOTPR value for this DT_HR0003_OUTOUTPUT.
     * 
     * @return MOTPR   * 日工作日程表的人事子范围分组
     */
    public java.lang.String getMOTPR() {
        return MOTPR;
    }


    /**
     * Sets the MOTPR value for this DT_HR0003_OUTOUTPUT.
     * 
     * @param MOTPR   * 日工作日程表的人事子范围分组
     */
    public void setMOTPR(java.lang.String MOTPR) {
        this.MOTPR = MOTPR;
    }


    /**
     * Gets the TPROG value for this DT_HR0003_OUTOUTPUT.
     * 
     * @return TPROG   * 日工作计划
     */
    public java.lang.String getTPROG() {
        return TPROG;
    }


    /**
     * Sets the TPROG value for this DT_HR0003_OUTOUTPUT.
     * 
     * @param TPROG   * 日工作计划
     */
    public void setTPROG(java.lang.String TPROG) {
        this.TPROG = TPROG;
    }


    /**
     * Gets the FTKLA value for this DT_HR0003_OUTOUTPUT.
     * 
     * @return FTKLA   * 公共假日类
     */
    public java.lang.String getFTKLA() {
        return FTKLA;
    }


    /**
     * Sets the FTKLA value for this DT_HR0003_OUTOUTPUT.
     * 
     * @param FTKLA   * 公共假日类
     */
    public void setFTKLA(java.lang.String FTKLA) {
        this.FTKLA = FTKLA;
    }


    /**
     * Gets the TPKLA value for this DT_HR0003_OUTOUTPUT.
     * 
     * @return TPKLA   * 日工作计划类型
     */
    public java.lang.String getTPKLA() {
        return TPKLA;
    }


    /**
     * Sets the TPKLA value for this DT_HR0003_OUTOUTPUT.
     * 
     * @param TPKLA   * 日工作计划类型
     */
    public void setTPKLA(java.lang.String TPKLA) {
        this.TPKLA = TPKLA;
    }


    /**
     * Gets the TTEXT value for this DT_HR0003_OUTOUTPUT.
     * 
     * @return TTEXT   * 班次描述
     */
    public java.lang.String getTTEXT() {
        return TTEXT;
    }


    /**
     * Sets the TTEXT value for this DT_HR0003_OUTOUTPUT.
     * 
     * @param TTEXT   * 班次描述
     */
    public void setTTEXT(java.lang.String TTEXT) {
        this.TTEXT = TTEXT;
    }


    /**
     * Gets the SOLLZ value for this DT_HR0003_OUTOUTPUT.
     * 
     * @return SOLLZ   * 班次时长
     */
    public java.lang.String getSOLLZ() {
        return SOLLZ;
    }


    /**
     * Sets the SOLLZ value for this DT_HR0003_OUTOUTPUT.
     * 
     * @param SOLLZ   * 班次时长
     */
    public void setSOLLZ(java.lang.String SOLLZ) {
        this.SOLLZ = SOLLZ;
    }


    /**
     * Gets the SOBEG value for this DT_HR0003_OUTOUTPUT.
     * 
     * @return SOBEG   * 班次开始时间
     */
    public java.lang.String getSOBEG() {
        return SOBEG;
    }


    /**
     * Sets the SOBEG value for this DT_HR0003_OUTOUTPUT.
     * 
     * @param SOBEG   * 班次开始时间
     */
    public void setSOBEG(java.lang.String SOBEG) {
        this.SOBEG = SOBEG;
    }


    /**
     * Gets the SOEND value for this DT_HR0003_OUTOUTPUT.
     * 
     * @return SOEND   * 班次结束时间
     */
    public java.lang.String getSOEND() {
        return SOEND;
    }


    /**
     * Sets the SOEND value for this DT_HR0003_OUTOUTPUT.
     * 
     * @param SOEND   * 班次结束时间
     */
    public void setSOEND(java.lang.String SOEND) {
        this.SOEND = SOEND;
    }


    /**
     * Gets the MSG_TYPE value for this DT_HR0003_OUTOUTPUT.
     * 
     * @return MSG_TYPE   * 消息类型
     */
    public java.lang.String getMSG_TYPE() {
        return MSG_TYPE;
    }


    /**
     * Sets the MSG_TYPE value for this DT_HR0003_OUTOUTPUT.
     * 
     * @param MSG_TYPE   * 消息类型
     */
    public void setMSG_TYPE(java.lang.String MSG_TYPE) {
        this.MSG_TYPE = MSG_TYPE;
    }


    /**
     * Gets the MESSAGE value for this DT_HR0003_OUTOUTPUT.
     * 
     * @return MESSAGE   * 消息文本
     */
    public java.lang.String getMESSAGE() {
        return MESSAGE;
    }


    /**
     * Sets the MESSAGE value for this DT_HR0003_OUTOUTPUT.
     * 
     * @param MESSAGE   * 消息文本
     */
    public void setMESSAGE(java.lang.String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }


    /**
     * Gets the additional1 value for this DT_HR0003_OUTOUTPUT.
     * 
     * @return additional1   * 备用字段1
     */
    public java.lang.String getAdditional1() {
        return additional1;
    }


    /**
     * Sets the additional1 value for this DT_HR0003_OUTOUTPUT.
     * 
     * @param additional1   * 备用字段1
     */
    public void setAdditional1(java.lang.String additional1) {
        this.additional1 = additional1;
    }


    /**
     * Gets the additional2 value for this DT_HR0003_OUTOUTPUT.
     * 
     * @return additional2   * 备用字段2
     */
    public java.lang.String getAdditional2() {
        return additional2;
    }


    /**
     * Sets the additional2 value for this DT_HR0003_OUTOUTPUT.
     * 
     * @param additional2   * 备用字段2
     */
    public void setAdditional2(java.lang.String additional2) {
        this.additional2 = additional2;
    }


    /**
     * Gets the additional3 value for this DT_HR0003_OUTOUTPUT.
     * 
     * @return additional3   * 备用字段3
     */
    public java.lang.String getAdditional3() {
        return additional3;
    }


    /**
     * Sets the additional3 value for this DT_HR0003_OUTOUTPUT.
     * 
     * @param additional3   * 备用字段3
     */
    public void setAdditional3(java.lang.String additional3) {
        this.additional3 = additional3;
    }


    /**
     * Gets the additional4 value for this DT_HR0003_OUTOUTPUT.
     * 
     * @return additional4   * 备用字段4
     */
    public java.lang.String getAdditional4() {
        return additional4;
    }


    /**
     * Sets the additional4 value for this DT_HR0003_OUTOUTPUT.
     * 
     * @param additional4   * 备用字段4
     */
    public void setAdditional4(java.lang.String additional4) {
        this.additional4 = additional4;
    }


    /**
     * Gets the additional5 value for this DT_HR0003_OUTOUTPUT.
     * 
     * @return additional5   * 备用字段5
     */
    public java.lang.String getAdditional5() {
        return additional5;
    }


    /**
     * Sets the additional5 value for this DT_HR0003_OUTOUTPUT.
     * 
     * @param additional5   * 备用字段5
     */
    public void setAdditional5(java.lang.String additional5) {
        this.additional5 = additional5;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_HR0003_OUTOUTPUT)) return false;
        DT_HR0003_OUTOUTPUT other = (DT_HR0003_OUTOUTPUT) obj;
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
            ((this.DATUM==null && other.getDATUM()==null) || 
             (this.DATUM!=null &&
              this.DATUM.equals(other.getDATUM()))) &&
            ((this.MOTPR==null && other.getMOTPR()==null) || 
             (this.MOTPR!=null &&
              this.MOTPR.equals(other.getMOTPR()))) &&
            ((this.TPROG==null && other.getTPROG()==null) || 
             (this.TPROG!=null &&
              this.TPROG.equals(other.getTPROG()))) &&
            ((this.FTKLA==null && other.getFTKLA()==null) || 
             (this.FTKLA!=null &&
              this.FTKLA.equals(other.getFTKLA()))) &&
            ((this.TPKLA==null && other.getTPKLA()==null) || 
             (this.TPKLA!=null &&
              this.TPKLA.equals(other.getTPKLA()))) &&
            ((this.TTEXT==null && other.getTTEXT()==null) || 
             (this.TTEXT!=null &&
              this.TTEXT.equals(other.getTTEXT()))) &&
            ((this.SOLLZ==null && other.getSOLLZ()==null) || 
             (this.SOLLZ!=null &&
              this.SOLLZ.equals(other.getSOLLZ()))) &&
            ((this.SOBEG==null && other.getSOBEG()==null) || 
             (this.SOBEG!=null &&
              this.SOBEG.equals(other.getSOBEG()))) &&
            ((this.SOEND==null && other.getSOEND()==null) || 
             (this.SOEND!=null &&
              this.SOEND.equals(other.getSOEND()))) &&
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
              this.additional2.equals(other.getAdditional2()))) &&
            ((this.additional3==null && other.getAdditional3()==null) || 
             (this.additional3!=null &&
              this.additional3.equals(other.getAdditional3()))) &&
            ((this.additional4==null && other.getAdditional4()==null) || 
             (this.additional4!=null &&
              this.additional4.equals(other.getAdditional4()))) &&
            ((this.additional5==null && other.getAdditional5()==null) || 
             (this.additional5!=null &&
              this.additional5.equals(other.getAdditional5())));
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
        if (getDATUM() != null) {
            _hashCode += getDATUM().hashCode();
        }
        if (getMOTPR() != null) {
            _hashCode += getMOTPR().hashCode();
        }
        if (getTPROG() != null) {
            _hashCode += getTPROG().hashCode();
        }
        if (getFTKLA() != null) {
            _hashCode += getFTKLA().hashCode();
        }
        if (getTPKLA() != null) {
            _hashCode += getTPKLA().hashCode();
        }
        if (getTTEXT() != null) {
            _hashCode += getTTEXT().hashCode();
        }
        if (getSOLLZ() != null) {
            _hashCode += getSOLLZ().hashCode();
        }
        if (getSOBEG() != null) {
            _hashCode += getSOBEG().hashCode();
        }
        if (getSOEND() != null) {
            _hashCode += getSOEND().hashCode();
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
        if (getAdditional3() != null) {
            _hashCode += getAdditional3().hashCode();
        }
        if (getAdditional4() != null) {
            _hashCode += getAdditional4().hashCode();
        }
        if (getAdditional5() != null) {
            _hashCode += getAdditional5().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_HR0003_OUTOUTPUT.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tidepharm.com/OA", ">DT_HR0003_OUT>OUTPUT"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PERNR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PERNR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DATUM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DATUM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MOTPR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MOTPR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TPROG");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TPROG"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FTKLA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FTKLA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TPKLA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TPKLA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TTEXT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TTEXT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SOLLZ");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SOLLZ"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SOBEG");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SOBEG"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SOEND");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SOEND"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("additional1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Additional1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("additional2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Additional2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("additional3");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Additional3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("additional4");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Additional4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("additional5");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Additional5"));
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
