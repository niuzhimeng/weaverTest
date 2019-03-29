/**
 * DT_MMI002_IN.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.taide.kaoQin500.wlly.myWeb;


/**
 * OA领料申请传至SAP创建预留
 */
public class DT_MMI002_IN  implements java.io.Serializable {
    /* 接口名称 */
    private String INTF_ID;

    /* 源系统 */
    private String src_System;

    /* 目标系统 */
    private String dest_System;

    /* 公司代码 */
    private String company_Code;

    /* 发送时间戳 */
    private String send_Time;

    private DT_MMI002_ININPUTHEADER[] INPUT;

    public DT_MMI002_IN() {
    }

    public DT_MMI002_IN(
           String INTF_ID,
           String src_System,
           String dest_System,
           String company_Code,
           String send_Time,
           DT_MMI002_ININPUTHEADER[] INPUT) {
           this.INTF_ID = INTF_ID;
           this.src_System = src_System;
           this.dest_System = dest_System;
           this.company_Code = company_Code;
           this.send_Time = send_Time;
           this.INPUT = INPUT;
    }


    /**
     * Gets the INTF_ID value for this DT_MMI002_IN.
     *
     * @return INTF_ID   * 接口名称
     */
    public String getINTF_ID() {
        return INTF_ID;
    }


    /**
     * Sets the INTF_ID value for this DT_MMI002_IN.
     *
     * @param INTF_ID   * 接口名称
     */
    public void setINTF_ID(String INTF_ID) {
        this.INTF_ID = INTF_ID;
    }


    /**
     * Gets the src_System value for this DT_MMI002_IN.
     *
     * @return src_System   * 源系统
     */
    public String getSrc_System() {
        return src_System;
    }


    /**
     * Sets the src_System value for this DT_MMI002_IN.
     *
     * @param src_System   * 源系统
     */
    public void setSrc_System(String src_System) {
        this.src_System = src_System;
    }


    /**
     * Gets the dest_System value for this DT_MMI002_IN.
     *
     * @return dest_System   * 目标系统
     */
    public String getDest_System() {
        return dest_System;
    }


    /**
     * Sets the dest_System value for this DT_MMI002_IN.
     *
     * @param dest_System   * 目标系统
     */
    public void setDest_System(String dest_System) {
        this.dest_System = dest_System;
    }


    /**
     * Gets the company_Code value for this DT_MMI002_IN.
     *
     * @return company_Code   * 公司代码
     */
    public String getCompany_Code() {
        return company_Code;
    }


    /**
     * Sets the company_Code value for this DT_MMI002_IN.
     *
     * @param company_Code   * 公司代码
     */
    public void setCompany_Code(String company_Code) {
        this.company_Code = company_Code;
    }


    /**
     * Gets the send_Time value for this DT_MMI002_IN.
     *
     * @return send_Time   * 发送时间戳
     */
    public String getSend_Time() {
        return send_Time;
    }


    /**
     * Sets the send_Time value for this DT_MMI002_IN.
     *
     * @param send_Time   * 发送时间戳
     */
    public void setSend_Time(String send_Time) {
        this.send_Time = send_Time;
    }


    /**
     * Gets the INPUT value for this DT_MMI002_IN.
     *
     * @return INPUT
     */
    public DT_MMI002_ININPUTHEADER[] getINPUT() {
        return INPUT;
    }


    /**
     * Sets the INPUT value for this DT_MMI002_IN.
     *
     * @param INPUT
     */
    public void setINPUT(DT_MMI002_ININPUTHEADER[] INPUT) {
        this.INPUT = INPUT;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof DT_MMI002_IN)) return false;
        DT_MMI002_IN other = (DT_MMI002_IN) obj;
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
            ((this.src_System==null && other.getSrc_System()==null) ||
             (this.src_System!=null &&
              this.src_System.equals(other.getSrc_System()))) &&
            ((this.dest_System==null && other.getDest_System()==null) ||
             (this.dest_System!=null &&
              this.dest_System.equals(other.getDest_System()))) &&
            ((this.company_Code==null && other.getCompany_Code()==null) ||
             (this.company_Code!=null &&
              this.company_Code.equals(other.getCompany_Code()))) &&
            ((this.send_Time==null && other.getSend_Time()==null) ||
             (this.send_Time!=null &&
              this.send_Time.equals(other.getSend_Time()))) &&
            ((this.INPUT==null && other.getINPUT()==null) ||
             (this.INPUT!=null &&
              java.util.Arrays.equals(this.INPUT, other.getINPUT())));
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
        if (getSrc_System() != null) {
            _hashCode += getSrc_System().hashCode();
        }
        if (getDest_System() != null) {
            _hashCode += getDest_System().hashCode();
        }
        if (getCompany_Code() != null) {
            _hashCode += getCompany_Code().hashCode();
        }
        if (getSend_Time() != null) {
            _hashCode += getSend_Time().hashCode();
        }
        if (getINPUT() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getINPUT());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getINPUT(), i);
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
        new org.apache.axis.description.TypeDesc(DT_MMI002_IN.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tidepharm.com/OA", "DT_MMI002_IN"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INTF_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "INTF_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("src_System");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Src_System"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dest_System");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Dest_System"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("company_Code");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Company_Code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("send_Time");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Send_Time"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INPUT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "INPUT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tidepharm.com/OA", ">>DT_MMI002_IN>INPUT>HEADER"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "HEADER"));
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
