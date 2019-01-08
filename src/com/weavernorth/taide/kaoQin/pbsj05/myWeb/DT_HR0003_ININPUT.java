/**
 * DT_HR0003_ININPUT.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.taide.kaoQin.pbsj05.myWeb;

public class DT_HR0003_ININPUT  implements java.io.Serializable {
    /* 人员编号 */
    private java.lang.String PERNR;

    /* 开始日期 */
    private java.lang.String BEGDA;

    /* 结束日期 */
    private java.lang.String ENDDA;

    /* 备用字段1 */
    private java.lang.String additional1;

    /* 备用字段2 */
    private java.lang.String additional2;

    public DT_HR0003_ININPUT() {
    }

    public DT_HR0003_ININPUT(
           java.lang.String PERNR,
           java.lang.String BEGDA,
           java.lang.String ENDDA,
           java.lang.String additional1,
           java.lang.String additional2) {
           this.PERNR = PERNR;
           this.BEGDA = BEGDA;
           this.ENDDA = ENDDA;
           this.additional1 = additional1;
           this.additional2 = additional2;
    }


    /**
     * Gets the PERNR value for this DT_HR0003_ININPUT.
     * 
     * @return PERNR   * 人员编号
     */
    public java.lang.String getPERNR() {
        return PERNR;
    }


    /**
     * Sets the PERNR value for this DT_HR0003_ININPUT.
     * 
     * @param PERNR   * 人员编号
     */
    public void setPERNR(java.lang.String PERNR) {
        this.PERNR = PERNR;
    }


    /**
     * Gets the BEGDA value for this DT_HR0003_ININPUT.
     * 
     * @return BEGDA   * 开始日期
     */
    public java.lang.String getBEGDA() {
        return BEGDA;
    }


    /**
     * Sets the BEGDA value for this DT_HR0003_ININPUT.
     * 
     * @param BEGDA   * 开始日期
     */
    public void setBEGDA(java.lang.String BEGDA) {
        this.BEGDA = BEGDA;
    }


    /**
     * Gets the ENDDA value for this DT_HR0003_ININPUT.
     * 
     * @return ENDDA   * 结束日期
     */
    public java.lang.String getENDDA() {
        return ENDDA;
    }


    /**
     * Sets the ENDDA value for this DT_HR0003_ININPUT.
     * 
     * @param ENDDA   * 结束日期
     */
    public void setENDDA(java.lang.String ENDDA) {
        this.ENDDA = ENDDA;
    }


    /**
     * Gets the additional1 value for this DT_HR0003_ININPUT.
     * 
     * @return additional1   * 备用字段1
     */
    public java.lang.String getAdditional1() {
        return additional1;
    }


    /**
     * Sets the additional1 value for this DT_HR0003_ININPUT.
     * 
     * @param additional1   * 备用字段1
     */
    public void setAdditional1(java.lang.String additional1) {
        this.additional1 = additional1;
    }


    /**
     * Gets the additional2 value for this DT_HR0003_ININPUT.
     * 
     * @return additional2   * 备用字段2
     */
    public java.lang.String getAdditional2() {
        return additional2;
    }


    /**
     * Sets the additional2 value for this DT_HR0003_ININPUT.
     * 
     * @param additional2   * 备用字段2
     */
    public void setAdditional2(java.lang.String additional2) {
        this.additional2 = additional2;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_HR0003_ININPUT)) return false;
        DT_HR0003_ININPUT other = (DT_HR0003_ININPUT) obj;
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
        if (getBEGDA() != null) {
            _hashCode += getBEGDA().hashCode();
        }
        if (getENDDA() != null) {
            _hashCode += getENDDA().hashCode();
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
        new org.apache.axis.description.TypeDesc(DT_HR0003_ININPUT.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tidepharm.com/OA", ">DT_HR0003_IN>INPUT"));
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
