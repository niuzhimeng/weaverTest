/**
 * SeatTypeEnum.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.weavernorth.mlc.webclient;

public class SeatTypeEnum implements java.io.Serializable {
    private String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected SeatTypeEnum(String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final String _YZ = "YZ";
    public static final String _RZ = "RZ";
    public static final String _TDZ = "TDZ";
    public static final String _YDZ = "YDZ";
    public static final String _EDZ = "EDZ";
    public static final String _GJRBS = "GJRBS";
    public static final String _GJRBX = "GJRBX";
    public static final String _SWZ = "SWZ";
    public static final String _YW = "YW";
    public static final String _RW = "RW";
    public static final String _DW = "DW";
    public static final String _GJRW = "GJRW";
    public static final String _YDRZ = "YDRZ";
    public static final String _EDRZ = "EDRZ";
    public static final String _YRRB = "YRRB";
    public static final String _GJDW = "GJDW";
    public static final SeatTypeEnum YZ = new SeatTypeEnum(_YZ);
    public static final SeatTypeEnum RZ = new SeatTypeEnum(_RZ);
    public static final SeatTypeEnum TDZ = new SeatTypeEnum(_TDZ);
    public static final SeatTypeEnum YDZ = new SeatTypeEnum(_YDZ);
    public static final SeatTypeEnum EDZ = new SeatTypeEnum(_EDZ);
    public static final SeatTypeEnum GJRBS = new SeatTypeEnum(_GJRBS);
    public static final SeatTypeEnum GJRBX = new SeatTypeEnum(_GJRBX);
    public static final SeatTypeEnum SWZ = new SeatTypeEnum(_SWZ);
    public static final SeatTypeEnum YW = new SeatTypeEnum(_YW);
    public static final SeatTypeEnum RW = new SeatTypeEnum(_RW);
    public static final SeatTypeEnum DW = new SeatTypeEnum(_DW);
    public static final SeatTypeEnum GJRW = new SeatTypeEnum(_GJRW);
    public static final SeatTypeEnum YDRZ = new SeatTypeEnum(_YDRZ);
    public static final SeatTypeEnum EDRZ = new SeatTypeEnum(_EDRZ);
    public static final SeatTypeEnum YRRB = new SeatTypeEnum(_YRRB);
    public static final SeatTypeEnum GJDW = new SeatTypeEnum(_GJDW);
    public String getValue() { return _value_;}
    public static SeatTypeEnum fromValue(String value)
          throws IllegalArgumentException {
        SeatTypeEnum enumeration = (SeatTypeEnum)
            _table_.get(value);
        if (enumeration==null) throw new IllegalArgumentException();
        return enumeration;
    }
    public static SeatTypeEnum fromString(String value)
          throws IllegalArgumentException {
        return fromValue(value);
    }
    public boolean equals(Object obj) {return (obj == this);}
    public int hashCode() { return toString().hashCode();}
    public String toString() { return _value_;}
    public Object readResolve() throws java.io.ObjectStreamException { return fromValue(_value_);}
    public static org.apache.axis.encoding.Serializer getSerializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SeatTypeEnum.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://www.corporatetravel.ctrip.com/SwtichAPI/Approval", "SeatTypeEnum"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
