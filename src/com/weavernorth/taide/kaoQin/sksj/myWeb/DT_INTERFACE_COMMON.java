/**
 * DT_INTERFACE_COMMON.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.taide.kaoQin.sksj.myWeb;


/**
 * 接口的通用字段定义
 */
public class DT_INTERFACE_COMMON  implements java.io.Serializable {
    /* 接口名称 */
    private java.lang.String INTF_ID;

    /* 源系统 */
    private java.lang.String SRC_SYSTEM;

    /* 目标系统 */
    private java.lang.String DEST_SYSTEM;

    /* 公司代码 */
    private java.lang.String COMPANY_CODE;

    /* 发送时间戳 */
    private java.lang.String SEND_TIME;

    public DT_INTERFACE_COMMON() {
    }

    public DT_INTERFACE_COMMON(
           java.lang.String INTF_ID,
           java.lang.String SRC_SYSTEM,
           java.lang.String DEST_SYSTEM,
           java.lang.String COMPANY_CODE,
           java.lang.String SEND_TIME) {
           this.INTF_ID = INTF_ID;
           this.SRC_SYSTEM = SRC_SYSTEM;
           this.DEST_SYSTEM = DEST_SYSTEM;
           this.COMPANY_CODE = COMPANY_CODE;
           this.SEND_TIME = SEND_TIME;
    }


    /**
     * Gets the INTF_ID value for this DT_INTERFACE_COMMON.
     * 
     * @return INTF_ID   * 接口名称
     */
    public java.lang.String getINTF_ID() {
        return INTF_ID;
    }


    /**
     * Sets the INTF_ID value for this DT_INTERFACE_COMMON.
     * 
     * @param INTF_ID   * 接口名称
     */
    public void setINTF_ID(java.lang.String INTF_ID) {
        this.INTF_ID = INTF_ID;
    }


    /**
     * Gets the SRC_SYSTEM value for this DT_INTERFACE_COMMON.
     * 
     * @return SRC_SYSTEM   * 源系统
     */
    public java.lang.String getSRC_SYSTEM() {
        return SRC_SYSTEM;
    }


    /**
     * Sets the SRC_SYSTEM value for this DT_INTERFACE_COMMON.
     * 
     * @param SRC_SYSTEM   * 源系统
     */
    public void setSRC_SYSTEM(java.lang.String SRC_SYSTEM) {
        this.SRC_SYSTEM = SRC_SYSTEM;
    }


    /**
     * Gets the DEST_SYSTEM value for this DT_INTERFACE_COMMON.
     * 
     * @return DEST_SYSTEM   * 目标系统
     */
    public java.lang.String getDEST_SYSTEM() {
        return DEST_SYSTEM;
    }


    /**
     * Sets the DEST_SYSTEM value for this DT_INTERFACE_COMMON.
     * 
     * @param DEST_SYSTEM   * 目标系统
     */
    public void setDEST_SYSTEM(java.lang.String DEST_SYSTEM) {
        this.DEST_SYSTEM = DEST_SYSTEM;
    }


    /**
     * Gets the COMPANY_CODE value for this DT_INTERFACE_COMMON.
     * 
     * @return COMPANY_CODE   * 公司代码
     */
    public java.lang.String getCOMPANY_CODE() {
        return COMPANY_CODE;
    }


    /**
     * Sets the COMPANY_CODE value for this DT_INTERFACE_COMMON.
     * 
     * @param COMPANY_CODE   * 公司代码
     */
    public void setCOMPANY_CODE(java.lang.String COMPANY_CODE) {
        this.COMPANY_CODE = COMPANY_CODE;
    }


    /**
     * Gets the SEND_TIME value for this DT_INTERFACE_COMMON.
     * 
     * @return SEND_TIME   * 发送时间戳
     */
    public java.lang.String getSEND_TIME() {
        return SEND_TIME;
    }


    /**
     * Sets the SEND_TIME value for this DT_INTERFACE_COMMON.
     * 
     * @param SEND_TIME   * 发送时间戳
     */
    public void setSEND_TIME(java.lang.String SEND_TIME) {
        this.SEND_TIME = SEND_TIME;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_INTERFACE_COMMON)) return false;
        DT_INTERFACE_COMMON other = (DT_INTERFACE_COMMON) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.INTF_ID==null && other.getINTF_ID()==null) || 
             (this.INTF_ID!=null &&
              this.INTF_ID.equals(other.getINTF_ID()))) &&
            ((this.SRC_SYSTEM==null && other.getSRC_SYSTEM()==null) || 
             (this.SRC_SYSTEM!=null &&
              this.SRC_SYSTEM.equals(other.getSRC_SYSTEM()))) &&
            ((this.DEST_SYSTEM==null && other.getDEST_SYSTEM()==null) || 
             (this.DEST_SYSTEM!=null &&
              this.DEST_SYSTEM.equals(other.getDEST_SYSTEM()))) &&
            ((this.COMPANY_CODE==null && other.getCOMPANY_CODE()==null) || 
             (this.COMPANY_CODE!=null &&
              this.COMPANY_CODE.equals(other.getCOMPANY_CODE()))) &&
            ((this.SEND_TIME==null && other.getSEND_TIME()==null) || 
             (this.SEND_TIME!=null &&
              this.SEND_TIME.equals(other.getSEND_TIME())));
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
        if (getINTF_ID() != null) {
            _hashCode += getINTF_ID().hashCode();
        }
        if (getSRC_SYSTEM() != null) {
            _hashCode += getSRC_SYSTEM().hashCode();
        }
        if (getDEST_SYSTEM() != null) {
            _hashCode += getDEST_SYSTEM().hashCode();
        }
        if (getCOMPANY_CODE() != null) {
            _hashCode += getCOMPANY_CODE().hashCode();
        }
        if (getSEND_TIME() != null) {
            _hashCode += getSEND_TIME().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_INTERFACE_COMMON.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tidepharm.com/WMS", "DT_INTERFACE_COMMON"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INTF_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "INTF_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SRC_SYSTEM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SRC_SYSTEM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DEST_SYSTEM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DEST_SYSTEM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("COMPANY_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "COMPANY_CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SEND_TIME");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SEND_TIME"));
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
