/**
 * DT_MMI001_OUTOUTPUT.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.taide.kaoQin300.qingGou.myWeb;

public class DT_MMI001_OUTOUTPUT  implements java.io.Serializable {
    /* OA请购单号 */
    private String REMARK1;

    /* SAP采购申请号 */
    private String BANFN;

    /* 消息类型 */
    private String TYPE;

    /* 消息文本 */
    private String MESSAGE;

    public DT_MMI001_OUTOUTPUT() {
    }

    public DT_MMI001_OUTOUTPUT(
           String REMARK1,
           String BANFN,
           String TYPE,
           String MESSAGE) {
           this.REMARK1 = REMARK1;
           this.BANFN = BANFN;
           this.TYPE = TYPE;
           this.MESSAGE = MESSAGE;
    }


    /**
     * Gets the REMARK1 value for this DT_MMI001_OUTOUTPUT.
     *
     * @return REMARK1   * OA请购单号
     */
    public String getREMARK1() {
        return REMARK1;
    }


    /**
     * Sets the REMARK1 value for this DT_MMI001_OUTOUTPUT.
     *
     * @param REMARK1   * OA请购单号
     */
    public void setREMARK1(String REMARK1) {
        this.REMARK1 = REMARK1;
    }


    /**
     * Gets the BANFN value for this DT_MMI001_OUTOUTPUT.
     *
     * @return BANFN   * SAP采购申请号
     */
    public String getBANFN() {
        return BANFN;
    }


    /**
     * Sets the BANFN value for this DT_MMI001_OUTOUTPUT.
     *
     * @param BANFN   * SAP采购申请号
     */
    public void setBANFN(String BANFN) {
        this.BANFN = BANFN;
    }


    /**
     * Gets the TYPE value for this DT_MMI001_OUTOUTPUT.
     *
     * @return TYPE   * 消息类型
     */
    public String getTYPE() {
        return TYPE;
    }


    /**
     * Sets the TYPE value for this DT_MMI001_OUTOUTPUT.
     *
     * @param TYPE   * 消息类型
     */
    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }


    /**
     * Gets the MESSAGE value for this DT_MMI001_OUTOUTPUT.
     *
     * @return MESSAGE   * 消息文本
     */
    public String getMESSAGE() {
        return MESSAGE;
    }


    /**
     * Sets the MESSAGE value for this DT_MMI001_OUTOUTPUT.
     *
     * @param MESSAGE   * 消息文本
     */
    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof DT_MMI001_OUTOUTPUT)) return false;
        DT_MMI001_OUTOUTPUT other = (DT_MMI001_OUTOUTPUT) obj;
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
            ((this.BANFN==null && other.getBANFN()==null) ||
             (this.BANFN!=null &&
              this.BANFN.equals(other.getBANFN()))) &&
            ((this.TYPE==null && other.getTYPE()==null) ||
             (this.TYPE!=null &&
              this.TYPE.equals(other.getTYPE()))) &&
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
        if (getBANFN() != null) {
            _hashCode += getBANFN().hashCode();
        }
        if (getTYPE() != null) {
            _hashCode += getTYPE().hashCode();
        }
        if (getMESSAGE() != null) {
            _hashCode += getMESSAGE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_MMI001_OUTOUTPUT.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tidepharm.com/OA", ">DT_MMI001_OUT>OUTPUT"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("REMARK1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "REMARK1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BANFN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BANFN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TYPE"));
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
