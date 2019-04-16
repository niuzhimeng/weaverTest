/**
 * DT_HRI011_INDATAITEMSP9002.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.taide.kaoQin500.jbxx.myWeb;

public class DT_HRI011_INDATAITEMSP9002  implements java.io.Serializable {
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

    /* 资格名称 */
    private String ZZGMC;

    /* 获得时间 */
    private String ZHDSJ;

    /* 证书编号 */
    private String ZZSBH;

    /* 资格等级 */
    private String ZZGDJ;

    /* 授予/评定单位 */
    private String ZPDDW;

    /* 备用字段1 */
    private String additional1;

    /* 备用字段2 */
    private String additional2;

    /* 备用字段3 */
    private String additional3;

    /* 备用字段4 */
    private String additional4;

    public DT_HRI011_INDATAITEMSP9002() {
    }

    public DT_HRI011_INDATAITEMSP9002(
           String PERNR,
           String BEGDA,
           String ENDDA,
           String SUBTY,
           String SEQNR,
           String OPTION,
           String ZZGMC,
           String ZHDSJ,
           String ZZSBH,
           String ZZGDJ,
           String ZPDDW,
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
           this.ZZGMC = ZZGMC;
           this.ZHDSJ = ZHDSJ;
           this.ZZSBH = ZZSBH;
           this.ZZGDJ = ZZGDJ;
           this.ZPDDW = ZPDDW;
           this.additional1 = additional1;
           this.additional2 = additional2;
           this.additional3 = additional3;
           this.additional4 = additional4;
    }


    /**
     * Gets the PERNR value for this DT_HRI011_INDATAITEMSP9002.
     *
     * @return PERNR   * 员工编号
     */
    public String getPERNR() {
        return PERNR;
    }


    /**
     * Sets the PERNR value for this DT_HRI011_INDATAITEMSP9002.
     *
     * @param PERNR   * 员工编号
     */
    public void setPERNR(String PERNR) {
        this.PERNR = PERNR;
    }


    /**
     * Gets the BEGDA value for this DT_HRI011_INDATAITEMSP9002.
     *
     * @return BEGDA   * 开始日期
     */
    public String getBEGDA() {
        return BEGDA;
    }


    /**
     * Sets the BEGDA value for this DT_HRI011_INDATAITEMSP9002.
     *
     * @param BEGDA   * 开始日期
     */
    public void setBEGDA(String BEGDA) {
        this.BEGDA = BEGDA;
    }


    /**
     * Gets the ENDDA value for this DT_HRI011_INDATAITEMSP9002.
     *
     * @return ENDDA   * 结束日期
     */
    public String getENDDA() {
        return ENDDA;
    }


    /**
     * Sets the ENDDA value for this DT_HRI011_INDATAITEMSP9002.
     *
     * @param ENDDA   * 结束日期
     */
    public void setENDDA(String ENDDA) {
        this.ENDDA = ENDDA;
    }


    /**
     * Gets the SUBTY value for this DT_HRI011_INDATAITEMSP9002.
     *
     * @return SUBTY   * 子信息类型
     */
    public String getSUBTY() {
        return SUBTY;
    }


    /**
     * Sets the SUBTY value for this DT_HRI011_INDATAITEMSP9002.
     *
     * @param SUBTY   * 子信息类型
     */
    public void setSUBTY(String SUBTY) {
        this.SUBTY = SUBTY;
    }


    /**
     * Gets the SEQNR value for this DT_HRI011_INDATAITEMSP9002.
     *
     * @return SEQNR   * 有相同代码的信息类型记录数
     */
    public String getSEQNR() {
        return SEQNR;
    }


    /**
     * Sets the SEQNR value for this DT_HRI011_INDATAITEMSP9002.
     *
     * @param SEQNR   * 有相同代码的信息类型记录数
     */
    public void setSEQNR(String SEQNR) {
        this.SEQNR = SEQNR;
    }


    /**
     * Gets the OPTION value for this DT_HRI011_INDATAITEMSP9002.
     *
     * @return OPTION   * INS/MOD
     */
    public String getOPTION() {
        return OPTION;
    }


    /**
     * Sets the OPTION value for this DT_HRI011_INDATAITEMSP9002.
     *
     * @param OPTION   * INS/MOD
     */
    public void setOPTION(String OPTION) {
        this.OPTION = OPTION;
    }


    /**
     * Gets the ZZGMC value for this DT_HRI011_INDATAITEMSP9002.
     *
     * @return ZZGMC   * 资格名称
     */
    public String getZZGMC() {
        return ZZGMC;
    }


    /**
     * Sets the ZZGMC value for this DT_HRI011_INDATAITEMSP9002.
     *
     * @param ZZGMC   * 资格名称
     */
    public void setZZGMC(String ZZGMC) {
        this.ZZGMC = ZZGMC;
    }


    /**
     * Gets the ZHDSJ value for this DT_HRI011_INDATAITEMSP9002.
     *
     * @return ZHDSJ   * 获得时间
     */
    public String getZHDSJ() {
        return ZHDSJ;
    }


    /**
     * Sets the ZHDSJ value for this DT_HRI011_INDATAITEMSP9002.
     *
     * @param ZHDSJ   * 获得时间
     */
    public void setZHDSJ(String ZHDSJ) {
        this.ZHDSJ = ZHDSJ;
    }


    /**
     * Gets the ZZSBH value for this DT_HRI011_INDATAITEMSP9002.
     *
     * @return ZZSBH   * 证书编号
     */
    public String getZZSBH() {
        return ZZSBH;
    }


    /**
     * Sets the ZZSBH value for this DT_HRI011_INDATAITEMSP9002.
     *
     * @param ZZSBH   * 证书编号
     */
    public void setZZSBH(String ZZSBH) {
        this.ZZSBH = ZZSBH;
    }


    /**
     * Gets the ZZGDJ value for this DT_HRI011_INDATAITEMSP9002.
     *
     * @return ZZGDJ   * 资格等级
     */
    public String getZZGDJ() {
        return ZZGDJ;
    }


    /**
     * Sets the ZZGDJ value for this DT_HRI011_INDATAITEMSP9002.
     *
     * @param ZZGDJ   * 资格等级
     */
    public void setZZGDJ(String ZZGDJ) {
        this.ZZGDJ = ZZGDJ;
    }


    /**
     * Gets the ZPDDW value for this DT_HRI011_INDATAITEMSP9002.
     *
     * @return ZPDDW   * 授予/评定单位
     */
    public String getZPDDW() {
        return ZPDDW;
    }


    /**
     * Sets the ZPDDW value for this DT_HRI011_INDATAITEMSP9002.
     *
     * @param ZPDDW   * 授予/评定单位
     */
    public void setZPDDW(String ZPDDW) {
        this.ZPDDW = ZPDDW;
    }


    /**
     * Gets the additional1 value for this DT_HRI011_INDATAITEMSP9002.
     *
     * @return additional1   * 备用字段1
     */
    public String getAdditional1() {
        return additional1;
    }


    /**
     * Sets the additional1 value for this DT_HRI011_INDATAITEMSP9002.
     *
     * @param additional1   * 备用字段1
     */
    public void setAdditional1(String additional1) {
        this.additional1 = additional1;
    }


    /**
     * Gets the additional2 value for this DT_HRI011_INDATAITEMSP9002.
     *
     * @return additional2   * 备用字段2
     */
    public String getAdditional2() {
        return additional2;
    }


    /**
     * Sets the additional2 value for this DT_HRI011_INDATAITEMSP9002.
     *
     * @param additional2   * 备用字段2
     */
    public void setAdditional2(String additional2) {
        this.additional2 = additional2;
    }


    /**
     * Gets the additional3 value for this DT_HRI011_INDATAITEMSP9002.
     *
     * @return additional3   * 备用字段3
     */
    public String getAdditional3() {
        return additional3;
    }


    /**
     * Sets the additional3 value for this DT_HRI011_INDATAITEMSP9002.
     *
     * @param additional3   * 备用字段3
     */
    public void setAdditional3(String additional3) {
        this.additional3 = additional3;
    }


    /**
     * Gets the additional4 value for this DT_HRI011_INDATAITEMSP9002.
     *
     * @return additional4   * 备用字段4
     */
    public String getAdditional4() {
        return additional4;
    }


    /**
     * Sets the additional4 value for this DT_HRI011_INDATAITEMSP9002.
     *
     * @param additional4   * 备用字段4
     */
    public void setAdditional4(String additional4) {
        this.additional4 = additional4;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof DT_HRI011_INDATAITEMSP9002)) return false;
        DT_HRI011_INDATAITEMSP9002 other = (DT_HRI011_INDATAITEMSP9002) obj;
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
            ((this.ZZGMC==null && other.getZZGMC()==null) ||
             (this.ZZGMC!=null &&
              this.ZZGMC.equals(other.getZZGMC()))) &&
            ((this.ZHDSJ==null && other.getZHDSJ()==null) ||
             (this.ZHDSJ!=null &&
              this.ZHDSJ.equals(other.getZHDSJ()))) &&
            ((this.ZZSBH==null && other.getZZSBH()==null) ||
             (this.ZZSBH!=null &&
              this.ZZSBH.equals(other.getZZSBH()))) &&
            ((this.ZZGDJ==null && other.getZZGDJ()==null) ||
             (this.ZZGDJ!=null &&
              this.ZZGDJ.equals(other.getZZGDJ()))) &&
            ((this.ZPDDW==null && other.getZPDDW()==null) ||
             (this.ZPDDW!=null &&
              this.ZPDDW.equals(other.getZPDDW()))) &&
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
        if (getZZGMC() != null) {
            _hashCode += getZZGMC().hashCode();
        }
        if (getZHDSJ() != null) {
            _hashCode += getZHDSJ().hashCode();
        }
        if (getZZSBH() != null) {
            _hashCode += getZZSBH().hashCode();
        }
        if (getZZGDJ() != null) {
            _hashCode += getZZGDJ().hashCode();
        }
        if (getZPDDW() != null) {
            _hashCode += getZPDDW().hashCode();
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
        new org.apache.axis.description.TypeDesc(DT_HRI011_INDATAITEMSP9002.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tidepharm.com/YT", ">>>DT_HRI011_IN>DATA>ITEMS>P9002"));
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
        elemField.setFieldName("ZZGMC");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ZZGMC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ZHDSJ");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ZHDSJ"));
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
        elemField.setFieldName("ZZGDJ");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ZZGDJ"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ZPDDW");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ZPDDW"));
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
