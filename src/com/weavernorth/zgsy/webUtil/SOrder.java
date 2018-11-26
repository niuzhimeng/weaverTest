/**
 * SOrder.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.zgsy.webUtil;

public class SOrder  implements java.io.Serializable {
    private String flowNumber;

    private String FBillNo;

    private String FCustNumber;

    private String FHeadSelfS0129;

    private String FHeadSelfS0141Number;

    private String FHeadSelfS0130Number;

    private String FHeadSelfS0132;

    private String FHeadSelfS0133;

    private String FHeadSelfS0134;

    private java.util.Calendar FDate;

    private String FSaleStyleName;

    private String FFetchStyleName;

    private String FHeadSelfS0142Number;

    private String FExplanation;

    private String FHeadSelfS0135Number;

    private String FDeptNumber;

    private String FEmpNumber;

    private String FMangerNumber;

    private String FBillerNumber;

    private com.weavernorth.zgsy.webUtil.SOrderEntry[] SOrderEntries;

    public SOrder() {
    }

    public SOrder(
           String flowNumber,
           String FBillNo,
           String FCustNumber,
           String FHeadSelfS0129,
           String FHeadSelfS0141Number,
           String FHeadSelfS0130Number,
           String FHeadSelfS0132,
           String FHeadSelfS0133,
           String FHeadSelfS0134,
           java.util.Calendar FDate,
           String FSaleStyleName,
           String FFetchStyleName,
           String FHeadSelfS0142Number,
           String FExplanation,
           String FHeadSelfS0135Number,
           String FDeptNumber,
           String FEmpNumber,
           String FMangerNumber,
           String FBillerNumber,
           com.weavernorth.zgsy.webUtil.SOrderEntry[] SOrderEntries) {
           this.flowNumber = flowNumber;
           this.FBillNo = FBillNo;
           this.FCustNumber = FCustNumber;
           this.FHeadSelfS0129 = FHeadSelfS0129;
           this.FHeadSelfS0141Number = FHeadSelfS0141Number;
           this.FHeadSelfS0130Number = FHeadSelfS0130Number;
           this.FHeadSelfS0132 = FHeadSelfS0132;
           this.FHeadSelfS0133 = FHeadSelfS0133;
           this.FHeadSelfS0134 = FHeadSelfS0134;
           this.FDate = FDate;
           this.FSaleStyleName = FSaleStyleName;
           this.FFetchStyleName = FFetchStyleName;
           this.FHeadSelfS0142Number = FHeadSelfS0142Number;
           this.FExplanation = FExplanation;
           this.FHeadSelfS0135Number = FHeadSelfS0135Number;
           this.FDeptNumber = FDeptNumber;
           this.FEmpNumber = FEmpNumber;
           this.FMangerNumber = FMangerNumber;
           this.FBillerNumber = FBillerNumber;
           this.SOrderEntries = SOrderEntries;
    }


    /**
     * Gets the flowNumber value for this SOrder.
     *
     * @return flowNumber
     */
    public String getFlowNumber() {
        return flowNumber;
    }


    /**
     * Sets the flowNumber value for this SOrder.
     *
     * @param flowNumber
     */
    public void setFlowNumber(String flowNumber) {
        this.flowNumber = flowNumber;
    }


    /**
     * Gets the FBillNo value for this SOrder.
     *
     * @return FBillNo
     */
    public String getFBillNo() {
        return FBillNo;
    }


    /**
     * Sets the FBillNo value for this SOrder.
     *
     * @param FBillNo
     */
    public void setFBillNo(String FBillNo) {
        this.FBillNo = FBillNo;
    }


    /**
     * Gets the FCustNumber value for this SOrder.
     *
     * @return FCustNumber
     */
    public String getFCustNumber() {
        return FCustNumber;
    }


    /**
     * Sets the FCustNumber value for this SOrder.
     *
     * @param FCustNumber
     */
    public void setFCustNumber(String FCustNumber) {
        this.FCustNumber = FCustNumber;
    }


    /**
     * Gets the FHeadSelfS0129 value for this SOrder.
     *
     * @return FHeadSelfS0129
     */
    public String getFHeadSelfS0129() {
        return FHeadSelfS0129;
    }


    /**
     * Sets the FHeadSelfS0129 value for this SOrder.
     *
     * @param FHeadSelfS0129
     */
    public void setFHeadSelfS0129(String FHeadSelfS0129) {
        this.FHeadSelfS0129 = FHeadSelfS0129;
    }


    /**
     * Gets the FHeadSelfS0141Number value for this SOrder.
     *
     * @return FHeadSelfS0141Number
     */
    public String getFHeadSelfS0141Number() {
        return FHeadSelfS0141Number;
    }


    /**
     * Sets the FHeadSelfS0141Number value for this SOrder.
     *
     * @param FHeadSelfS0141Number
     */
    public void setFHeadSelfS0141Number(String FHeadSelfS0141Number) {
        this.FHeadSelfS0141Number = FHeadSelfS0141Number;
    }


    /**
     * Gets the FHeadSelfS0130Number value for this SOrder.
     *
     * @return FHeadSelfS0130Number
     */
    public String getFHeadSelfS0130Number() {
        return FHeadSelfS0130Number;
    }


    /**
     * Sets the FHeadSelfS0130Number value for this SOrder.
     *
     * @param FHeadSelfS0130Number
     */
    public void setFHeadSelfS0130Number(String FHeadSelfS0130Number) {
        this.FHeadSelfS0130Number = FHeadSelfS0130Number;
    }


    /**
     * Gets the FHeadSelfS0132 value for this SOrder.
     *
     * @return FHeadSelfS0132
     */
    public String getFHeadSelfS0132() {
        return FHeadSelfS0132;
    }


    /**
     * Sets the FHeadSelfS0132 value for this SOrder.
     *
     * @param FHeadSelfS0132
     */
    public void setFHeadSelfS0132(String FHeadSelfS0132) {
        this.FHeadSelfS0132 = FHeadSelfS0132;
    }


    /**
     * Gets the FHeadSelfS0133 value for this SOrder.
     *
     * @return FHeadSelfS0133
     */
    public String getFHeadSelfS0133() {
        return FHeadSelfS0133;
    }


    /**
     * Sets the FHeadSelfS0133 value for this SOrder.
     *
     * @param FHeadSelfS0133
     */
    public void setFHeadSelfS0133(String FHeadSelfS0133) {
        this.FHeadSelfS0133 = FHeadSelfS0133;
    }


    /**
     * Gets the FHeadSelfS0134 value for this SOrder.
     *
     * @return FHeadSelfS0134
     */
    public String getFHeadSelfS0134() {
        return FHeadSelfS0134;
    }


    /**
     * Sets the FHeadSelfS0134 value for this SOrder.
     *
     * @param FHeadSelfS0134
     */
    public void setFHeadSelfS0134(String FHeadSelfS0134) {
        this.FHeadSelfS0134 = FHeadSelfS0134;
    }


    /**
     * Gets the FDate value for this SOrder.
     *
     * @return FDate
     */
    public java.util.Calendar getFDate() {
        return FDate;
    }


    /**
     * Sets the FDate value for this SOrder.
     *
     * @param FDate
     */
    public void setFDate(java.util.Calendar FDate) {
        this.FDate = FDate;
    }


    /**
     * Gets the FSaleStyleName value for this SOrder.
     *
     * @return FSaleStyleName
     */
    public String getFSaleStyleName() {
        return FSaleStyleName;
    }


    /**
     * Sets the FSaleStyleName value for this SOrder.
     *
     * @param FSaleStyleName
     */
    public void setFSaleStyleName(String FSaleStyleName) {
        this.FSaleStyleName = FSaleStyleName;
    }


    /**
     * Gets the FFetchStyleName value for this SOrder.
     *
     * @return FFetchStyleName
     */
    public String getFFetchStyleName() {
        return FFetchStyleName;
    }


    /**
     * Sets the FFetchStyleName value for this SOrder.
     *
     * @param FFetchStyleName
     */
    public void setFFetchStyleName(String FFetchStyleName) {
        this.FFetchStyleName = FFetchStyleName;
    }


    /**
     * Gets the FHeadSelfS0142Number value for this SOrder.
     *
     * @return FHeadSelfS0142Number
     */
    public String getFHeadSelfS0142Number() {
        return FHeadSelfS0142Number;
    }


    /**
     * Sets the FHeadSelfS0142Number value for this SOrder.
     *
     * @param FHeadSelfS0142Number
     */
    public void setFHeadSelfS0142Number(String FHeadSelfS0142Number) {
        this.FHeadSelfS0142Number = FHeadSelfS0142Number;
    }


    /**
     * Gets the FExplanation value for this SOrder.
     *
     * @return FExplanation
     */
    public String getFExplanation() {
        return FExplanation;
    }


    /**
     * Sets the FExplanation value for this SOrder.
     *
     * @param FExplanation
     */
    public void setFExplanation(String FExplanation) {
        this.FExplanation = FExplanation;
    }


    /**
     * Gets the FHeadSelfS0135Number value for this SOrder.
     *
     * @return FHeadSelfS0135Number
     */
    public String getFHeadSelfS0135Number() {
        return FHeadSelfS0135Number;
    }


    /**
     * Sets the FHeadSelfS0135Number value for this SOrder.
     *
     * @param FHeadSelfS0135Number
     */
    public void setFHeadSelfS0135Number(String FHeadSelfS0135Number) {
        this.FHeadSelfS0135Number = FHeadSelfS0135Number;
    }


    /**
     * Gets the FDeptNumber value for this SOrder.
     *
     * @return FDeptNumber
     */
    public String getFDeptNumber() {
        return FDeptNumber;
    }


    /**
     * Sets the FDeptNumber value for this SOrder.
     *
     * @param FDeptNumber
     */
    public void setFDeptNumber(String FDeptNumber) {
        this.FDeptNumber = FDeptNumber;
    }


    /**
     * Gets the FEmpNumber value for this SOrder.
     *
     * @return FEmpNumber
     */
    public String getFEmpNumber() {
        return FEmpNumber;
    }


    /**
     * Sets the FEmpNumber value for this SOrder.
     *
     * @param FEmpNumber
     */
    public void setFEmpNumber(String FEmpNumber) {
        this.FEmpNumber = FEmpNumber;
    }


    /**
     * Gets the FMangerNumber value for this SOrder.
     *
     * @return FMangerNumber
     */
    public String getFMangerNumber() {
        return FMangerNumber;
    }


    /**
     * Sets the FMangerNumber value for this SOrder.
     *
     * @param FMangerNumber
     */
    public void setFMangerNumber(String FMangerNumber) {
        this.FMangerNumber = FMangerNumber;
    }


    /**
     * Gets the FBillerNumber value for this SOrder.
     *
     * @return FBillerNumber
     */
    public String getFBillerNumber() {
        return FBillerNumber;
    }


    /**
     * Sets the FBillerNumber value for this SOrder.
     *
     * @param FBillerNumber
     */
    public void setFBillerNumber(String FBillerNumber) {
        this.FBillerNumber = FBillerNumber;
    }


    /**
     * Gets the SOrderEntries value for this SOrder.
     *
     * @return SOrderEntries
     */
    public com.weavernorth.zgsy.webUtil.SOrderEntry[] getSOrderEntries() {
        return SOrderEntries;
    }


    /**
     * Sets the SOrderEntries value for this SOrder.
     *
     * @param SOrderEntries
     */
    public void setSOrderEntries(com.weavernorth.zgsy.webUtil.SOrderEntry[] SOrderEntries) {
        this.SOrderEntries = SOrderEntries;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof SOrder)) return false;
        SOrder other = (SOrder) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.flowNumber==null && other.getFlowNumber()==null) ||
             (this.flowNumber!=null &&
              this.flowNumber.equals(other.getFlowNumber()))) &&
            ((this.FBillNo==null && other.getFBillNo()==null) ||
             (this.FBillNo!=null &&
              this.FBillNo.equals(other.getFBillNo()))) &&
            ((this.FCustNumber==null && other.getFCustNumber()==null) ||
             (this.FCustNumber!=null &&
              this.FCustNumber.equals(other.getFCustNumber()))) &&
            ((this.FHeadSelfS0129==null && other.getFHeadSelfS0129()==null) ||
             (this.FHeadSelfS0129!=null &&
              this.FHeadSelfS0129.equals(other.getFHeadSelfS0129()))) &&
            ((this.FHeadSelfS0141Number==null && other.getFHeadSelfS0141Number()==null) ||
             (this.FHeadSelfS0141Number!=null &&
              this.FHeadSelfS0141Number.equals(other.getFHeadSelfS0141Number()))) &&
            ((this.FHeadSelfS0130Number==null && other.getFHeadSelfS0130Number()==null) ||
             (this.FHeadSelfS0130Number!=null &&
              this.FHeadSelfS0130Number.equals(other.getFHeadSelfS0130Number()))) &&
            ((this.FHeadSelfS0132==null && other.getFHeadSelfS0132()==null) ||
             (this.FHeadSelfS0132!=null &&
              this.FHeadSelfS0132.equals(other.getFHeadSelfS0132()))) &&
            ((this.FHeadSelfS0133==null && other.getFHeadSelfS0133()==null) ||
             (this.FHeadSelfS0133!=null &&
              this.FHeadSelfS0133.equals(other.getFHeadSelfS0133()))) &&
            ((this.FHeadSelfS0134==null && other.getFHeadSelfS0134()==null) ||
             (this.FHeadSelfS0134!=null &&
              this.FHeadSelfS0134.equals(other.getFHeadSelfS0134()))) &&
            ((this.FDate==null && other.getFDate()==null) ||
             (this.FDate!=null &&
              this.FDate.equals(other.getFDate()))) &&
            ((this.FSaleStyleName==null && other.getFSaleStyleName()==null) ||
             (this.FSaleStyleName!=null &&
              this.FSaleStyleName.equals(other.getFSaleStyleName()))) &&
            ((this.FFetchStyleName==null && other.getFFetchStyleName()==null) ||
             (this.FFetchStyleName!=null &&
              this.FFetchStyleName.equals(other.getFFetchStyleName()))) &&
            ((this.FHeadSelfS0142Number==null && other.getFHeadSelfS0142Number()==null) ||
             (this.FHeadSelfS0142Number!=null &&
              this.FHeadSelfS0142Number.equals(other.getFHeadSelfS0142Number()))) &&
            ((this.FExplanation==null && other.getFExplanation()==null) ||
             (this.FExplanation!=null &&
              this.FExplanation.equals(other.getFExplanation()))) &&
            ((this.FHeadSelfS0135Number==null && other.getFHeadSelfS0135Number()==null) ||
             (this.FHeadSelfS0135Number!=null &&
              this.FHeadSelfS0135Number.equals(other.getFHeadSelfS0135Number()))) &&
            ((this.FDeptNumber==null && other.getFDeptNumber()==null) ||
             (this.FDeptNumber!=null &&
              this.FDeptNumber.equals(other.getFDeptNumber()))) &&
            ((this.FEmpNumber==null && other.getFEmpNumber()==null) ||
             (this.FEmpNumber!=null &&
              this.FEmpNumber.equals(other.getFEmpNumber()))) &&
            ((this.FMangerNumber==null && other.getFMangerNumber()==null) ||
             (this.FMangerNumber!=null &&
              this.FMangerNumber.equals(other.getFMangerNumber()))) &&
            ((this.FBillerNumber==null && other.getFBillerNumber()==null) ||
             (this.FBillerNumber!=null &&
              this.FBillerNumber.equals(other.getFBillerNumber()))) &&
            ((this.SOrderEntries==null && other.getSOrderEntries()==null) ||
             (this.SOrderEntries!=null &&
              java.util.Arrays.equals(this.SOrderEntries, other.getSOrderEntries())));
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
        if (getFlowNumber() != null) {
            _hashCode += getFlowNumber().hashCode();
        }
        if (getFBillNo() != null) {
            _hashCode += getFBillNo().hashCode();
        }
        if (getFCustNumber() != null) {
            _hashCode += getFCustNumber().hashCode();
        }
        if (getFHeadSelfS0129() != null) {
            _hashCode += getFHeadSelfS0129().hashCode();
        }
        if (getFHeadSelfS0141Number() != null) {
            _hashCode += getFHeadSelfS0141Number().hashCode();
        }
        if (getFHeadSelfS0130Number() != null) {
            _hashCode += getFHeadSelfS0130Number().hashCode();
        }
        if (getFHeadSelfS0132() != null) {
            _hashCode += getFHeadSelfS0132().hashCode();
        }
        if (getFHeadSelfS0133() != null) {
            _hashCode += getFHeadSelfS0133().hashCode();
        }
        if (getFHeadSelfS0134() != null) {
            _hashCode += getFHeadSelfS0134().hashCode();
        }
        if (getFDate() != null) {
            _hashCode += getFDate().hashCode();
        }
        if (getFSaleStyleName() != null) {
            _hashCode += getFSaleStyleName().hashCode();
        }
        if (getFFetchStyleName() != null) {
            _hashCode += getFFetchStyleName().hashCode();
        }
        if (getFHeadSelfS0142Number() != null) {
            _hashCode += getFHeadSelfS0142Number().hashCode();
        }
        if (getFExplanation() != null) {
            _hashCode += getFExplanation().hashCode();
        }
        if (getFHeadSelfS0135Number() != null) {
            _hashCode += getFHeadSelfS0135Number().hashCode();
        }
        if (getFDeptNumber() != null) {
            _hashCode += getFDeptNumber().hashCode();
        }
        if (getFEmpNumber() != null) {
            _hashCode += getFEmpNumber().hashCode();
        }
        if (getFMangerNumber() != null) {
            _hashCode += getFMangerNumber().hashCode();
        }
        if (getFBillerNumber() != null) {
            _hashCode += getFBillerNumber().hashCode();
        }
        if (getSOrderEntries() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSOrderEntries());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getSOrderEntries(), i);
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
        new org.apache.axis.description.TypeDesc(SOrder.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "SOrder"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flowNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FlowNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FBillNo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FBillNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FCustNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FCustNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FHeadSelfS0129");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FHeadSelfS0129"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FHeadSelfS0141Number");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FHeadSelfS0141Number"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FHeadSelfS0130Number");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FHeadSelfS0130Number"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FHeadSelfS0132");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FHeadSelfS0132"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FHeadSelfS0133");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FHeadSelfS0133"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FHeadSelfS0134");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FHeadSelfS0134"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FSaleStyleName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FSaleStyleName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FFetchStyleName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FFetchStyleName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FHeadSelfS0142Number");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FHeadSelfS0142Number"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FExplanation");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FExplanation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FHeadSelfS0135Number");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FHeadSelfS0135Number"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FDeptNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FDeptNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FEmpNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FEmpNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FMangerNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FMangerNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FBillerNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FBillerNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SOrderEntries");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "SOrderEntries"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "SOrderEntry"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://tempuri.org/", "SOrderEntry"));
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
