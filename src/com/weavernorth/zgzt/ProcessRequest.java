/**
 * ProcessRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.zgzt;

public class ProcessRequest  implements java.io.Serializable {
    /* 流程ID（如果是String类型传此值） */
    private String processIdStr;

    /* 流程ID（如果是Int类型传此值） */
    private int processIdInt;

    /* 状态ID（如果是String类型传此值） */
    private String statusStr;

    /* 状态ID（如果是Int类型传此值） */
    private int statusInt;

    /* 审批人姓名 */
    private String name;

    public ProcessRequest() {
    }

    public ProcessRequest(
           String processIdStr,
           int processIdInt,
           String statusStr,
           int statusInt,
           String name) {
           this.processIdStr = processIdStr;
           this.processIdInt = processIdInt;
           this.statusStr = statusStr;
           this.statusInt = statusInt;
           this.name = name;
    }


    /**
     * Gets the processIdStr value for this ProcessRequest.
     *
     * @return processIdStr   * 流程ID（如果是String类型传此值）
     */
    public String getProcessIdStr() {
        return processIdStr;
    }


    /**
     * Sets the processIdStr value for this ProcessRequest.
     *
     * @param processIdStr   * 流程ID（如果是String类型传此值）
     */
    public void setProcessIdStr(String processIdStr) {
        this.processIdStr = processIdStr;
    }


    /**
     * Gets the processIdInt value for this ProcessRequest.
     *
     * @return processIdInt   * 流程ID（如果是Int类型传此值）
     */
    public int getProcessIdInt() {
        return processIdInt;
    }


    /**
     * Sets the processIdInt value for this ProcessRequest.
     *
     * @param processIdInt   * 流程ID（如果是Int类型传此值）
     */
    public void setProcessIdInt(int processIdInt) {
        this.processIdInt = processIdInt;
    }


    /**
     * Gets the statusStr value for this ProcessRequest.
     *
     * @return statusStr   * 状态ID（如果是String类型传此值）
     */
    public String getStatusStr() {
        return statusStr;
    }


    /**
     * Sets the statusStr value for this ProcessRequest.
     *
     * @param statusStr   * 状态ID（如果是String类型传此值）
     */
    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }


    /**
     * Gets the statusInt value for this ProcessRequest.
     *
     * @return statusInt   * 状态ID（如果是Int类型传此值）
     */
    public int getStatusInt() {
        return statusInt;
    }


    /**
     * Sets the statusInt value for this ProcessRequest.
     *
     * @param statusInt   * 状态ID（如果是Int类型传此值）
     */
    public void setStatusInt(int statusInt) {
        this.statusInt = statusInt;
    }


    /**
     * Gets the name value for this ProcessRequest.
     *
     * @return name   * 审批人姓名
     */
    public String getName() {
        return name;
    }


    /**
     * Sets the name value for this ProcessRequest.
     *
     * @param name   * 审批人姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ProcessRequest)) return false;
        ProcessRequest other = (ProcessRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.processIdStr==null && other.getProcessIdStr()==null) ||
             (this.processIdStr!=null &&
              this.processIdStr.equals(other.getProcessIdStr()))) &&
            this.processIdInt == other.getProcessIdInt() &&
            ((this.statusStr==null && other.getStatusStr()==null) ||
             (this.statusStr!=null &&
              this.statusStr.equals(other.getStatusStr()))) &&
            this.statusInt == other.getStatusInt() &&
            ((this.name==null && other.getName()==null) ||
             (this.name!=null &&
              this.name.equals(other.getName())));
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
        if (getProcessIdStr() != null) {
            _hashCode += getProcessIdStr().hashCode();
        }
        _hashCode += getProcessIdInt();
        if (getStatusStr() != null) {
            _hashCode += getStatusStr().hashCode();
        }
        _hashCode += getStatusInt();
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ProcessRequest.class, true);

    @Override
    public String toString() {
        return "ProcessRequest{" +
                "processIdStr='" + processIdStr + '\'' +
                ", statusStr='" + statusStr + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.zhengtongauto.com/ws/demo", ">ProcessRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("processIdStr");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.zhengtongauto.com/ws/demo", "processIdStr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("processIdInt");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.zhengtongauto.com/ws/demo", "processIdInt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusStr");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.zhengtongauto.com/ws/demo", "statusStr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusInt");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.zhengtongauto.com/ws/demo", "statusInt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.zhengtongauto.com/ws/demo", "name"));
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
