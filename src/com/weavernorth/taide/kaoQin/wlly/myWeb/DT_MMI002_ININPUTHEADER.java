/**
 * DT_MMI002_ININPUTHEADER.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.taide.kaoQin.wlly.myWeb;

public class DT_MMI002_ININPUTHEADER  implements java.io.Serializable {
    /* 工厂 */
    private java.lang.String WERKS;

    /* 移动类型 */
    private java.lang.String BWART;

    /* 成本中心 */
    private java.lang.String KOSTL;

    /* 内部订单 */
    private java.lang.String AUFNR;

    /* WBS元素 */
    private java.lang.String PS_PSP_PNR;

    /* OA领料单号 */
    private java.lang.String WEMPF;

    /* 领料申请抬头标识 */
    private java.lang.String REMARK1;

    private com.weavernorth.taide.kaoQin.wlly.myWeb.DT_MMI002_ININPUTHEADERITEM[] ITEM;

    public DT_MMI002_ININPUTHEADER() {
    }

    public DT_MMI002_ININPUTHEADER(
           java.lang.String WERKS,
           java.lang.String BWART,
           java.lang.String KOSTL,
           java.lang.String AUFNR,
           java.lang.String PS_PSP_PNR,
           java.lang.String WEMPF,
           java.lang.String REMARK1,
           com.weavernorth.taide.kaoQin.wlly.myWeb.DT_MMI002_ININPUTHEADERITEM[] ITEM) {
           this.WERKS = WERKS;
           this.BWART = BWART;
           this.KOSTL = KOSTL;
           this.AUFNR = AUFNR;
           this.PS_PSP_PNR = PS_PSP_PNR;
           this.WEMPF = WEMPF;
           this.REMARK1 = REMARK1;
           this.ITEM = ITEM;
    }


    /**
     * Gets the WERKS value for this DT_MMI002_ININPUTHEADER.
     * 
     * @return WERKS   * 工厂
     */
    public java.lang.String getWERKS() {
        return WERKS;
    }


    /**
     * Sets the WERKS value for this DT_MMI002_ININPUTHEADER.
     * 
     * @param WERKS   * 工厂
     */
    public void setWERKS(java.lang.String WERKS) {
        this.WERKS = WERKS;
    }


    /**
     * Gets the BWART value for this DT_MMI002_ININPUTHEADER.
     * 
     * @return BWART   * 移动类型
     */
    public java.lang.String getBWART() {
        return BWART;
    }


    /**
     * Sets the BWART value for this DT_MMI002_ININPUTHEADER.
     * 
     * @param BWART   * 移动类型
     */
    public void setBWART(java.lang.String BWART) {
        this.BWART = BWART;
    }


    /**
     * Gets the KOSTL value for this DT_MMI002_ININPUTHEADER.
     * 
     * @return KOSTL   * 成本中心
     */
    public java.lang.String getKOSTL() {
        return KOSTL;
    }


    /**
     * Sets the KOSTL value for this DT_MMI002_ININPUTHEADER.
     * 
     * @param KOSTL   * 成本中心
     */
    public void setKOSTL(java.lang.String KOSTL) {
        this.KOSTL = KOSTL;
    }


    /**
     * Gets the AUFNR value for this DT_MMI002_ININPUTHEADER.
     * 
     * @return AUFNR   * 内部订单
     */
    public java.lang.String getAUFNR() {
        return AUFNR;
    }


    /**
     * Sets the AUFNR value for this DT_MMI002_ININPUTHEADER.
     * 
     * @param AUFNR   * 内部订单
     */
    public void setAUFNR(java.lang.String AUFNR) {
        this.AUFNR = AUFNR;
    }


    /**
     * Gets the PS_PSP_PNR value for this DT_MMI002_ININPUTHEADER.
     * 
     * @return PS_PSP_PNR   * WBS元素
     */
    public java.lang.String getPS_PSP_PNR() {
        return PS_PSP_PNR;
    }


    /**
     * Sets the PS_PSP_PNR value for this DT_MMI002_ININPUTHEADER.
     * 
     * @param PS_PSP_PNR   * WBS元素
     */
    public void setPS_PSP_PNR(java.lang.String PS_PSP_PNR) {
        this.PS_PSP_PNR = PS_PSP_PNR;
    }


    /**
     * Gets the WEMPF value for this DT_MMI002_ININPUTHEADER.
     * 
     * @return WEMPF   * OA领料单号
     */
    public java.lang.String getWEMPF() {
        return WEMPF;
    }


    /**
     * Sets the WEMPF value for this DT_MMI002_ININPUTHEADER.
     * 
     * @param WEMPF   * OA领料单号
     */
    public void setWEMPF(java.lang.String WEMPF) {
        this.WEMPF = WEMPF;
    }


    /**
     * Gets the REMARK1 value for this DT_MMI002_ININPUTHEADER.
     * 
     * @return REMARK1   * 领料申请抬头标识
     */
    public java.lang.String getREMARK1() {
        return REMARK1;
    }


    /**
     * Sets the REMARK1 value for this DT_MMI002_ININPUTHEADER.
     * 
     * @param REMARK1   * 领料申请抬头标识
     */
    public void setREMARK1(java.lang.String REMARK1) {
        this.REMARK1 = REMARK1;
    }


    /**
     * Gets the ITEM value for this DT_MMI002_ININPUTHEADER.
     * 
     * @return ITEM
     */
    public com.weavernorth.taide.kaoQin.wlly.myWeb.DT_MMI002_ININPUTHEADERITEM[] getITEM() {
        return ITEM;
    }


    /**
     * Sets the ITEM value for this DT_MMI002_ININPUTHEADER.
     * 
     * @param ITEM
     */
    public void setITEM(com.weavernorth.taide.kaoQin.wlly.myWeb.DT_MMI002_ININPUTHEADERITEM[] ITEM) {
        this.ITEM = ITEM;
    }

    public com.weavernorth.taide.kaoQin.wlly.myWeb.DT_MMI002_ININPUTHEADERITEM getITEM(int i) {
        return this.ITEM[i];
    }

    public void setITEM(int i, com.weavernorth.taide.kaoQin.wlly.myWeb.DT_MMI002_ININPUTHEADERITEM _value) {
        this.ITEM[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_MMI002_ININPUTHEADER)) return false;
        DT_MMI002_ININPUTHEADER other = (DT_MMI002_ININPUTHEADER) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.WERKS==null && other.getWERKS()==null) || 
             (this.WERKS!=null &&
              this.WERKS.equals(other.getWERKS()))) &&
            ((this.BWART==null && other.getBWART()==null) || 
             (this.BWART!=null &&
              this.BWART.equals(other.getBWART()))) &&
            ((this.KOSTL==null && other.getKOSTL()==null) || 
             (this.KOSTL!=null &&
              this.KOSTL.equals(other.getKOSTL()))) &&
            ((this.AUFNR==null && other.getAUFNR()==null) || 
             (this.AUFNR!=null &&
              this.AUFNR.equals(other.getAUFNR()))) &&
            ((this.PS_PSP_PNR==null && other.getPS_PSP_PNR()==null) || 
             (this.PS_PSP_PNR!=null &&
              this.PS_PSP_PNR.equals(other.getPS_PSP_PNR()))) &&
            ((this.WEMPF==null && other.getWEMPF()==null) || 
             (this.WEMPF!=null &&
              this.WEMPF.equals(other.getWEMPF()))) &&
            ((this.REMARK1==null && other.getREMARK1()==null) || 
             (this.REMARK1!=null &&
              this.REMARK1.equals(other.getREMARK1()))) &&
            ((this.ITEM==null && other.getITEM()==null) || 
             (this.ITEM!=null &&
              java.util.Arrays.equals(this.ITEM, other.getITEM())));
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
        if (getWERKS() != null) {
            _hashCode += getWERKS().hashCode();
        }
        if (getBWART() != null) {
            _hashCode += getBWART().hashCode();
        }
        if (getKOSTL() != null) {
            _hashCode += getKOSTL().hashCode();
        }
        if (getAUFNR() != null) {
            _hashCode += getAUFNR().hashCode();
        }
        if (getPS_PSP_PNR() != null) {
            _hashCode += getPS_PSP_PNR().hashCode();
        }
        if (getWEMPF() != null) {
            _hashCode += getWEMPF().hashCode();
        }
        if (getREMARK1() != null) {
            _hashCode += getREMARK1().hashCode();
        }
        if (getITEM() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getITEM());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getITEM(), i);
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
        new org.apache.axis.description.TypeDesc(DT_MMI002_ININPUTHEADER.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tidepharm.com/OA", ">>DT_MMI002_IN>INPUT>HEADER"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("WERKS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "WERKS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BWART");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BWART"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("KOSTL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "KOSTL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AUFNR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AUFNR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PS_PSP_PNR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PS_PSP_PNR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("WEMPF");
        elemField.setXmlName(new javax.xml.namespace.QName("", "WEMPF"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("REMARK1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "REMARK1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ITEM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ITEM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tidepharm.com/OA", ">>>DT_MMI002_IN>INPUT>HEADER>ITEM"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
