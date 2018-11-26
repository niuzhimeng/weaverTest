/**
 * UserLogPageRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.zgzt;

public class UserLogPageRequest  implements java.io.Serializable {
    private int id;

    /* 当前页 */
    private int page;

    /* 每页行 */
    private int rows;

    public UserLogPageRequest() {
    }

    public UserLogPageRequest(
           int id,
           int page,
           int rows) {
           this.id = id;
           this.page = page;
           this.rows = rows;
    }


    /**
     * Gets the id value for this UserLogPageRequest.
     * 
     * @return id
     */
    public int getId() {
        return id;
    }


    /**
     * Sets the id value for this UserLogPageRequest.
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets the page value for this UserLogPageRequest.
     * 
     * @return page   * 当前页
     */
    public int getPage() {
        return page;
    }


    /**
     * Sets the page value for this UserLogPageRequest.
     * 
     * @param page   * 当前页
     */
    public void setPage(int page) {
        this.page = page;
    }


    /**
     * Gets the rows value for this UserLogPageRequest.
     * 
     * @return rows   * 每页行
     */
    public int getRows() {
        return rows;
    }


    /**
     * Sets the rows value for this UserLogPageRequest.
     * 
     * @param rows   * 每页行
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof UserLogPageRequest)) return false;
        UserLogPageRequest other = (UserLogPageRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            this.id == other.getId() &&
            this.page == other.getPage() &&
            this.rows == other.getRows();
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
        _hashCode += getId();
        _hashCode += getPage();
        _hashCode += getRows();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UserLogPageRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.zhengtongauto.com/ws/demo", ">UserLogPageRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.zhengtongauto.com/ws/demo", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("page");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.zhengtongauto.com/ws/demo", "page"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rows");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.zhengtongauto.com/ws/demo", "rows"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
