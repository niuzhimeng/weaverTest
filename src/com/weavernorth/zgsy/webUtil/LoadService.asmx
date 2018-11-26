<?xml version="1.0" encoding="utf-8"?>
<wsdl:definitions xmlns:tm="http://microsoft.com/wsdl/mime/textMatching/" xmlns:soapenc="http://schemas.xmlsoap.org/soap/encoding/" xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/" xmlns:tns="http://tempuri.org/" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:s="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://schemas.xmlsoap.org/wsdl/soap12/" xmlns:http="http://schemas.xmlsoap.org/wsdl/http/" targetNamespace="http://tempuri.org/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">
  <wsdl:types>
    <s:schema elementFormDefault="qualified" targetNamespace="http://tempuri.org/">
      <s:element name="HelloWorld">
        <s:complexType />
      </s:element>
      <s:element name="HelloWorldResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="HelloWorldResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="MaterialData">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="Data" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="MaterialDataResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="MaterialDataResult" type="tns:ArrayOfMaterialInfo" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="ArrayOfMaterialInfo">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="MaterialInfo" nillable="true" type="tns:MaterialInfo" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="MaterialInfo">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="FNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FName" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FModel" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:element name="BaseData">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="Data" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="BaseDataResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="BaseDataResult" type="tns:ArrayOfBaseDataInfo" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="ArrayOfBaseDataInfo">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="BaseDataInfo" nillable="true" type="tns:BaseDataInfo" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="BaseDataInfo">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="FNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FName" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:element name="SavePO">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="Data" type="tns:POrder" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="POrder">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="FSupplyNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FHeadSelfP0224Number" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FExplanation" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="FDate" type="s:dateTime" />
          <s:element minOccurs="0" maxOccurs="1" name="FEmpNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FDeptNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="POrderEntries" type="tns:ArrayOfPOrderEntry" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfPOrderEntry">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="POrderEntry" nillable="true" type="tns:POrderEntry" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="POrderEntry">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="FItemNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FEntrySelfP0244" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="FQty" type="s:double" />
          <s:element minOccurs="1" maxOccurs="1" name="FTaxPrice" type="s:double" />
          <s:element minOccurs="0" maxOccurs="1" name="FEntrySelfP0224" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FNote" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="FEntrySelfP0278" type="s:int" />
        </s:sequence>
      </s:complexType>
      <s:element name="SavePOResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="SavePOResult" type="tns:WebResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="WebResult">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="BillNo" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="BillType" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="State" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="Message" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:element name="SaveSO">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="Data" type="tns:SOrder" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="SOrder">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="FlowNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FBillNo" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FCustNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FHeadSelfS0129" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FHeadSelfS0141Number" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FHeadSelfS0130Number" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FHeadSelfS0132" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FHeadSelfS0133" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FHeadSelfS0134" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="FDate" type="s:dateTime" />
          <s:element minOccurs="0" maxOccurs="1" name="FSaleStyleName" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FFetchStyleName" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FHeadSelfS0142Number" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FExplanation" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FHeadSelfS0135Number" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FDeptNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FEmpNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FMangerNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FBillerNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="SOrderEntries" type="tns:ArrayOfSOrderEntry" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfSOrderEntry">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="SOrderEntry" nillable="true" type="tns:SOrderEntry" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="SOrderEntry">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="FItemNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FEntrySelfS0175" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FEntrySelfS0133Name" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FEntrySelfS0132" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="FQty" type="s:double" />
          <s:element minOccurs="0" maxOccurs="1" name="FEntrySelfS0177Number" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="FEntrySelfS0131" type="s:double" />
          <s:element minOccurs="1" maxOccurs="1" name="FTaxPrice" type="s:double" />
          <s:element minOccurs="1" maxOccurs="1" name="FEntrySelfS0136" type="s:double" />
          <s:element minOccurs="1" maxOccurs="1" name="FDate" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="FEntrySelfS0174" type="s:double" />
          <s:element minOccurs="0" maxOccurs="1" name="FNote" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FEntrySelfS0138" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FEntrySelfS0140Number" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:element name="SaveSOResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="SaveSOResult" type="tns:WebResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="SaveVoucher">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="Data" type="tns:Voucher" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="Voucher">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="FlowNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FReference" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="FVoucherDate" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="FPreparerNumber" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="VoucherEntries" type="tns:ArrayOfVoucherEntry" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfVoucherEntry">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="VoucherEntry" nillable="true" type="tns:VoucherEntry" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="VoucherEntry">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="FExplanation" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FAccountNumber" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="FDC" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="FAmount" type="s:double" />
          <s:element minOccurs="0" maxOccurs="1" name="ItemDetailEntries" type="tns:ArrayOfItemDetailEntry" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfItemDetailEntry">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="ItemDetailEntry" nillable="true" type="tns:ItemDetailEntry" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ItemDetailEntry">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="FItemClassNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FItemNumber" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FItemName" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:element name="SaveVoucherResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="SaveVoucherResult" type="tns:WebResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
    </s:schema>
  </wsdl:types>
  <wsdl:message name="HelloWorldSoapIn">
    <wsdl:part name="parameters" element="tns:HelloWorld" />
  </wsdl:message>
  <wsdl:message name="HelloWorldSoapOut">
    <wsdl:part name="parameters" element="tns:HelloWorldResponse" />
  </wsdl:message>
  <wsdl:message name="MaterialDataSoapIn">
    <wsdl:part name="parameters" element="tns:MaterialData" />
  </wsdl:message>
  <wsdl:message name="MaterialDataSoapOut">
    <wsdl:part name="parameters" element="tns:MaterialDataResponse" />
  </wsdl:message>
  <wsdl:message name="BaseDataSoapIn">
    <wsdl:part name="parameters" element="tns:BaseData" />
  </wsdl:message>
  <wsdl:message name="BaseDataSoapOut">
    <wsdl:part name="parameters" element="tns:BaseDataResponse" />
  </wsdl:message>
  <wsdl:message name="SavePOSoapIn">
    <wsdl:part name="parameters" element="tns:SavePO" />
  </wsdl:message>
  <wsdl:message name="SavePOSoapOut">
    <wsdl:part name="parameters" element="tns:SavePOResponse" />
  </wsdl:message>
  <wsdl:message name="SaveSOSoapIn">
    <wsdl:part name="parameters" element="tns:SaveSO" />
  </wsdl:message>
  <wsdl:message name="SaveSOSoapOut">
    <wsdl:part name="parameters" element="tns:SaveSOResponse" />
  </wsdl:message>
  <wsdl:message name="SaveVoucherSoapIn">
    <wsdl:part name="parameters" element="tns:SaveVoucher" />
  </wsdl:message>
  <wsdl:message name="SaveVoucherSoapOut">
    <wsdl:part name="parameters" element="tns:SaveVoucherResponse" />
  </wsdl:message>
  <wsdl:portType name="LoadServiceSoap">
    <wsdl:operation name="HelloWorld">
      <wsdl:input message="tns:HelloWorldSoapIn" />
      <wsdl:output message="tns:HelloWorldSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="MaterialData">
      <wsdl:input message="tns:MaterialDataSoapIn" />
      <wsdl:output message="tns:MaterialDataSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="BaseData">
      <wsdl:input message="tns:BaseDataSoapIn" />
      <wsdl:output message="tns:BaseDataSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="SavePO">
      <wsdl:input message="tns:SavePOSoapIn" />
      <wsdl:output message="tns:SavePOSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="SaveSO">
      <wsdl:input message="tns:SaveSOSoapIn" />
      <wsdl:output message="tns:SaveSOSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="SaveVoucher">
      <wsdl:input message="tns:SaveVoucherSoapIn" />
      <wsdl:output message="tns:SaveVoucherSoapOut" />
    </wsdl:operation>
  </wsdl:portType>
  <wsdl:binding name="LoadServiceSoap" type="tns:LoadServiceSoap">
    <soap:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="HelloWorld">
      <soap:operation soapAction="http://tempuri.org/HelloWorld" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="MaterialData">
      <soap:operation soapAction="http://tempuri.org/MaterialData" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="BaseData">
      <soap:operation soapAction="http://tempuri.org/BaseData" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SavePO">
      <soap:operation soapAction="http://tempuri.org/SavePO" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SaveSO">
      <soap:operation soapAction="http://tempuri.org/SaveSO" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SaveVoucher">
      <soap:operation soapAction="http://tempuri.org/SaveVoucher" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:binding name="LoadServiceSoap12" type="tns:LoadServiceSoap">
    <soap12:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="HelloWorld">
      <soap12:operation soapAction="http://tempuri.org/HelloWorld" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="MaterialData">
      <soap12:operation soapAction="http://tempuri.org/MaterialData" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="BaseData">
      <soap12:operation soapAction="http://tempuri.org/BaseData" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SavePO">
      <soap12:operation soapAction="http://tempuri.org/SavePO" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SaveSO">
      <soap12:operation soapAction="http://tempuri.org/SaveSO" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SaveVoucher">
      <soap12:operation soapAction="http://tempuri.org/SaveVoucher" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:service name="LoadService">
    <wsdl:port name="LoadServiceSoap" binding="tns:LoadServiceSoap">
      <soap:address location="http://hangder-open.gnway.cc:6080/ZGSYWebService_IIS/LoadService.asmx" />
    </wsdl:port>
    <wsdl:port name="LoadServiceSoap12" binding="tns:LoadServiceSoap12">
      <soap12:address location="http://hangder-open.gnway.cc:6080/ZGSYWebService_IIS/LoadService.asmx" />
    </wsdl:port>
  </wsdl:service>
</wsdl:definitions>