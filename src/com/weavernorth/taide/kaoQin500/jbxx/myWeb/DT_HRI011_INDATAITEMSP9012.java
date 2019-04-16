/**
 * DT_HRI011_INDATAITEMSP9012.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.taide.kaoQin500.jbxx.myWeb;

public class DT_HRI011_INDATAITEMSP9012  implements java.io.Serializable {
    /* 员工编号 */
    private String PERNR;

    /* 开始日期 */
    private String BEGDA;

    /* 结束日期 */
    private String ENDDA;

    /* 子信息类型 */
    private String SUBTY;

    /* 有相同代码的信息类型记录数 */
    private String SEQNR;

    /* INS/MOD */
    private String OPTION;

    /* 语种 */
    private String ZYUZH;

    /* 语种熟练程度 */
    private String ZSLCD;

    /* 证书名称 */
    private String ZZSMC;

    /* 证书编号 */
    private String ZZSBH;

    /* 获证日期 */
    private String ZHZRQ;

    /* 备用字段1 */
    private String additional1;

    /* 备用字段2 */
    private String additional2;

    /* 备用字段3 */
    private String additional3;

    /* 备用字段4 */
    private String additional4;

    public DT_HRI011_INDATAITEMSP9012() {
    }

    public DT_HRI011_INDATAITEMSP9012(
           String PERNR,
           String BEGDA,
           String ENDDA,
           String SUBTY,
           String SEQNR,
           String OPTION,
           String ZYUZH,
           String ZSLCD,
           String ZZSMC,
           String ZZSBH,
           String ZHZRQ,
           String additional1,
           String additional2,
           String additional3,
           String additional4) {
           this.PERNR = PERNR;
           this.BEGDA = BEGDA;
           this.ENDDA = ENDDA;
           this.SUBTY = SUBTY;
           this.SEQNR = SEQNR;
           this.OPTION = OPTION;
           this.ZYUZH = ZYUZH;
           this.ZSLCD = ZSLCD;
           this.ZZSMC = ZZSMC;
           this.ZZSBH = ZZSBH;
           this.ZHZRQ = ZHZRQ;
           this.additional1 = additional1;
           this.additional2 = additional2;
           this.additional3 = additional3;
           this.additional4 = additional4;
    }


    /**
     * Gets the PERNR value for this DT_HRI011_INDATAITEMSP9012.
     *
     * @return PERNR   * 员工编号
     */
    public String getPERNR() {
        return PERNR;
    }


    /**
     * Sets the PERNR value for this DT_HRI011_INDATAITEMSP9012.
     *
     * @param PERNR   * 员工编号
     */
    public void setPERNR(String PERNR) {
        this.PERNR = PERNR;
    }


    /**
     * Gets the BEGDA value for this DT_HRI011_INDATAITEMSP9012.
     *
     * @return BEGDA   * 开始日期
     */
    public String getBEGDA() {
        return BEGDA;
    }


    /**
     * Sets the BEGDA value for this DT_HRI011_INDATAITEMSP9012.
     *
     * @param BEGDA   * 开始日期
     */
    public void setBEGDA(String BEGDA) {
        this.BEGDA = BEGDA;
    }


    /**
     * Gets the ENDDA value for this DT_HRI011_INDATAITEMSP9012.
     *
     * @return ENDDA   * 结束日期
     */
    public String getENDDA() {
        return ENDDA;
    }


    /**
     * Sets the ENDDA value for this DT_HRI011_INDATAITEMSP9012.
     *
     * @param ENDDA   * 结束日期
     */
    public void setENDDA(String ENDDA) {
        this.ENDDA = ENDDA;
    }


    /**
     * Gets the SUBTY value for this DT_HRI011_INDATAITEMSP9012.
     *
     * @return SUBTY   * 子信息类型
     */
    public String getSUBTY() {
        return SUBTY;
    }


    /**
     * Sets the SUBTY value for this DT_HRI011_INDATAITEMSP9012.
     *
     * @param SUBTY   * 子信息类型
     */
    public void setSUBTY(String SUBTY) {
        this.SUBTY = SUBTY;
    }


    /**
     * Gets the SEQNR value for this DT_HRI011_INDATAITEMSP9012.
     *
     * @return SEQNR   * 有相同代码的信息类型记录数
     */
    public String getSEQNR() {
        return SEQNR;
    }


    /**
     * Sets the SEQNR value for this DT_HRI011_INDATAITEMSP9012.
     *
     * @param SEQNR   * 有相同代码的信息类型记录数
     */
    public void setSEQNR(String SEQNR) {
        this.SEQNR = SEQNR;
    }


    /**
     * Gets the OPTION value for this DT_HRI011_INDATAITEMSP9012.
     *
     * @return OPTION   * INS/MOD
     */
    public String getOPTION() {
        return OPTION;
    }


    /**
     * Sets the OPTION value for this DT_HRI011_INDATAITEMSP9012.
     *
     * @param OPTION   * INS/MOD
     */
    public void setOPTION(String OPTION) {
        this.OPTION = OPTION;
    }


    /**
     * Gets the ZYUZH value for this DT_HRI011_INDATAITEMSP9012.
     *
     * @return ZYUZH   * 语种
     */
    public String getZYUZH() {
        return ZYUZH;
    }


    /**
     * Sets the ZYUZH value for this DT_HRI011_INDATAITEMSP9012.
     *
     * @param ZYUZH   * 语种
     */
    public void setZYUZH(String ZYUZH) {
        this.ZYUZH = ZYUZH;
    }


    /**
     * Gets the ZSLCD value for this DT_HRI011_INDATAITEMSP9012.
     *
     * @return ZSLCD   * 语种熟练程度
     */
    public String getZSLCD() {
        return ZSLCD;
    }


    /**
     * Sets the ZSLCD value for this DT_HRI011_INDATAITEMSP9012.
     *
     * @param ZSLCD   * 语种熟练程度
     */
    public void setZSLCD(String ZSLCD) {
        this.ZSLCD = ZSLCD;
    }


    /**
     * Gets the ZZSMC value for this DT_HRI011_INDATAITEMSP9012.
     *
     * @return ZZSMC   * 证书名称
     */
    public String getZZSMC() {
        return ZZSMC;
    }


    /**
     * Sets the ZZSMC value for this DT_HRI011_INDATAITEMSP9012.
     *
     * @param ZZSMC   * 证书名称
     */
    public void setZZSMC(String ZZSMC) {
        this.ZZSMC = ZZSMC;
    }


    /**
     * Gets the ZZSBH value for this DT_HRI011_INDATAITEMSP9012.
     *
     * @return ZZSBH   * 证书编号
     */
    public String getZZSBH() {
        return ZZSBH;
    }


    /**
     * Sets the ZZSBH value for this DT_HRI011_INDATAITEMSP9012.
     *
     * @param ZZSBH   * 证书编号
     */
    public void setZZSBH(String ZZSBH) {
        this.ZZSBH = ZZSBH;
    }


    /**
     * Gets the ZHZRQ value for this DT_HRI011_INDATAITEMSP9012.
     *
     * @return ZHZRQ   * 获证日期
     */
    public String getZHZRQ() {
        return ZHZRQ;
    }


    /**
     * Sets the ZHZRQ value for this DT_HRI011_INDATAITEMSP9012.
     *
     * @param ZHZRQ   * 获证日期
     */
    public void setZHZRQ(String ZHZRQ) {
        this.ZHZRQ = ZHZRQ;
    }


    /**
     * Gets the additional1 value for this DT_HRI011_INDATAITEMSP9012.
     *
     * @return additional1   * 备用字段1
     */
    public String getAdditional1() {
        return additional1;
    }


    /**
     * Sets the additional1 value for this DT_HRI011_INDATAITEMSP9012.
     *
     * @param additional1   * 备用字段1
     */
    public void setAdditional1(String additional1) {
        this.additional1 = additional1;
    }


    /**
     * Gets the additional2 value for this DT_HRI011_INDATAITEMSP9012.
     *
     * @return additional2   * 备用字段2
     */
    public String getAdditional2() {
        return additional2;
    }


    /**
     * Sets the additional2 value for this DT_HRI011_INDATAITEMSP9012.
     *
     * @param additional2   * 备用字段2
     */
    public void setAdditional2(String additional2) {
        this.additional2 = additional2;
    }


    /**
     * Gets the additional3 value for this DT_HRI011_INDATAITEMSP9012.
     *
     * @return additional3   * 备用字段3
     */
    public String getAdditional3() {
        return additional3;
    }


    /**
     * Sets the additional3 value for this DT_HRI011_INDATAITEMSP9012.
     *
     * @param additional3   * 备用字段3
     */
    public void setAdditional3(String additional3) {
        this.additional3 = additional3;
    }


    /**
     * Gets the additional4 value for this DT_HRI011_INDATAITEMSP9012.
     *
     * @return additional4   * 备用字段4
     */
    public String getAdditional4() {
        return additional4;
    }


    /**
     * Sets the additional4 value for this DT_HRI011_INDATAITEMSP9012.
     *
     * @param additional4   * 备用字段4
     */
    public void setAdditional4(String additional4) {
        this.additional4 = additional4;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof DT_HRI011_INDATAITEMSP9012)) return false;
        DT_HRI011_INDATAITEMSP9012 other = (DT_HRI011_INDATAITEMSP9012) obj;
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
            ((this.BEGDA==null && other.getBEGDA()==null) ||
             (this.BEGDA!=null &&
              this.BEGDA.equals(other.getBEGDA()))) &&
            ((this.ENDDA==null && other.getENDDA()==null) ||
             (this.ENDDA!=null &&
              this.ENDDA.equals(other.getENDDA()))) &&
            ((this.SUBTY==null && other.getSUBTY()==null) ||
             (this.SUBTY!=null &&
              this.SUBTY.equals(other.getSUBTY()))) &&
            ((this.SEQNR==null && other.getSEQNR()==null) ||
             (this.SEQNR!=null &&
              this.SEQNR.equals(other.getSEQNR()))) &&
            ((this.OPTION==null && other.getOPTION()==null) ||
             (this.OPTION!=null &&
              this.OPTION.equals(other.getOPTION()))) &&
            ((this.ZYUZH==null && other.getZYUZH()==null) ||
             (this.ZYUZH!=null &&
              this.ZYUZH.equals(other.getZYUZH()))) &&
            ((this.ZSLCD==null && other.getZSLCD()==null) ||
             (this.ZSLCD!=null &&
              this.ZSLCD.equals(other.getZSLCD()))) &&
            ((this.ZZSMC==null && other.getZZSMC()==null) ||
             (this.ZZSMC!=null &&
              this.ZZSMC.equals(other.getZZSMC()))) &&
            ((this.ZZSBH==null && other.getZZSBH()==null) ||
             (this.ZZSBH!=null &&
              this.ZZSBH.equals(other.getZZSBH()))) &&
            ((this.ZHZRQ==null && other.getZHZRQ()==null) ||
             (this.ZHZRQ!=null &&
              this.ZHZRQ.equals(other.getZHZRQ()))) &&
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
              this.additional4.equals(other.getAdditional4())));
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
        if (getBEGDA() != null) {
            _hashCode += getBEGDA().hashCode();
        }
        if (getENDDA() != null) {
            _hashCode += getENDDA().hashCode();
        }
        if (getSUBTY() != null) {
            _hashCode += getSUBTY().hashCode();
        }
        if (getSEQNR() != null) {
            _hashCode += getSEQNR().hashCode();
        }
        if (getOPTION() != null) {
            _hashCode += getOPTION().hashCode();
        }
        if (getZYUZH() != null) {
            _hashCode += getZYUZH().hashCode();
        }
        if (getZSLCD() != null) {
            _hashCode += getZSLCD().hashCode();
        }
        if (getZZSMC() != null) {
            _hashCode += getZZSMC().hashCode();
        }
        if (getZZSBH() != null) {
            _hashCode += getZZSBH().hashCode();
        }
        if (getZHZRQ() != null) {
            _hashCode += getZHZRQ().hashCode();
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_HRI011_INDATAITEMSP9012.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tidepharm.com/YT", ">>>DT_HRI011_IN>DATA>ITEMS>P9012"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PERNR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PERNR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BEGDA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BEGDA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ENDDA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ENDDA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SUBTY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SUBTY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SEQNR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SEQNR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OPTION");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OPTION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ZYUZH");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ZYUZH"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ZSLCD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ZSLCD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ZZSMC");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ZZSMC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ZZSBH");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ZZSBH"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ZHZRQ");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ZHZRQ"));
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
