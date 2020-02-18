<?xml version="1.0" encoding="utf-8"?>
<wsdl:definitions xmlns:s="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://schemas.xmlsoap.org/wsdl/soap12/" xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/" xmlns:tns="http://tempuri.org/" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tm="http://microsoft.com/wsdl/mime/textMatching/" xmlns:http="http://schemas.xmlsoap.org/wsdl/http/" xmlns:soapenc="http://schemas.xmlsoap.org/soap/encoding/" targetNamespace="http://tempuri.org/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">
  <wsdl:types>
    <s:schema elementFormDefault="qualified" targetNamespace="http://tempuri.org/">
      <s:element name="CreateCustomerFromXML">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="customerXml" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="u9User" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="u9Pwd" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="u9Org" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="CreateCustomerFromXMLResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CreateCustomerFromXMLResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="CreateSupplierFromXML">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="supplierXml" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="u9User" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="u9Pwd" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="u9Org" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="CreateSupplierFromXMLResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CreateSupplierFromXMLResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="QueryAccountFromXML">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="xml" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="u9User" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="u9Pwd" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="u9Org" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="QueryAccountFromXMLResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="QueryAccountFromXMLResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="QuerySupplierBankAccountFromXML">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="xml" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="u9User" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="u9Pwd" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="u9Org" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="QuerySupplierBankAccountFromXMLResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="QuerySupplierBankAccountFromXMLResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="CreateSPAdjustFromXML">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="xml" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="u9User" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="u9Pwd" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="u9Org" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="CreateSPAdjustFromXMLResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CreateSPAdjustFromXMLResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="CreateVoucherFromXML">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="xml" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="u9User" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="u9Pwd" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="u9Org" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="CreateVoucherFromXMLResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CreateVoucherFromXMLResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="U9FileCreate">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="fileName" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="U9FileCreateResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="U9FileCreateResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="U9FileAppend">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="fileName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="buffer" type="s:base64Binary" />
            <s:element minOccurs="0" maxOccurs="1" name="fileDbID" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="U9FileAppendResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="U9FileAppendResult" type="s:boolean" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="U9FileVerify">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="xml" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="u9User" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="u9Pwd" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="u9Org" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="U9FileVerifyResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="U9FileVerifyResult" type="s:boolean" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="CreateCustomerBankAccountFromXML">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="xml" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="u9User" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="u9Pwd" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="u9Org" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="CreateCustomerBankAccountFromXMLResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CreateCustomerBankAccountFromXMLResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="CreateAddressFromXML">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="xml" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="u9User" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="u9Pwd" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="u9Org" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="CreateAddressFromXMLResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CreateAddressFromXMLResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="CreateContanctFromXML">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="xml" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="u9User" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="u9Pwd" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="u9Org" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="CreateContanctFromXMLResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CreateContanctFromXMLResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
    </s:schema>
  </wsdl:types>
  <wsdl:message name="CreateCustomerFromXMLSoapIn">
    <wsdl:part name="parameters" element="tns:CreateCustomerFromXML" />
  </wsdl:message>
  <wsdl:message name="CreateCustomerFromXMLSoapOut">
    <wsdl:part name="parameters" element="tns:CreateCustomerFromXMLResponse" />
  </wsdl:message>
  <wsdl:message name="CreateSupplierFromXMLSoapIn">
    <wsdl:part name="parameters" element="tns:CreateSupplierFromXML" />
  </wsdl:message>
  <wsdl:message name="CreateSupplierFromXMLSoapOut">
    <wsdl:part name="parameters" element="tns:CreateSupplierFromXMLResponse" />
  </wsdl:message>
  <wsdl:message name="QueryAccountFromXMLSoapIn">
    <wsdl:part name="parameters" element="tns:QueryAccountFromXML" />
  </wsdl:message>
  <wsdl:message name="QueryAccountFromXMLSoapOut">
    <wsdl:part name="parameters" element="tns:QueryAccountFromXMLResponse" />
  </wsdl:message>
  <wsdl:message name="QuerySupplierBankAccountFromXMLSoapIn">
    <wsdl:part name="parameters" element="tns:QuerySupplierBankAccountFromXML" />
  </wsdl:message>
  <wsdl:message name="QuerySupplierBankAccountFromXMLSoapOut">
    <wsdl:part name="parameters" element="tns:QuerySupplierBankAccountFromXMLResponse" />
  </wsdl:message>
  <wsdl:message name="CreateSPAdjustFromXMLSoapIn">
    <wsdl:part name="parameters" element="tns:CreateSPAdjustFromXML" />
  </wsdl:message>
  <wsdl:message name="CreateSPAdjustFromXMLSoapOut">
    <wsdl:part name="parameters" element="tns:CreateSPAdjustFromXMLResponse" />
  </wsdl:message>
  <wsdl:message name="CreateVoucherFromXMLSoapIn">
    <wsdl:part name="parameters" element="tns:CreateVoucherFromXML" />
  </wsdl:message>
  <wsdl:message name="CreateVoucherFromXMLSoapOut">
    <wsdl:part name="parameters" element="tns:CreateVoucherFromXMLResponse" />
  </wsdl:message>
  <wsdl:message name="U9FileCreateSoapIn">
    <wsdl:part name="parameters" element="tns:U9FileCreate" />
  </wsdl:message>
  <wsdl:message name="U9FileCreateSoapOut">
    <wsdl:part name="parameters" element="tns:U9FileCreateResponse" />
  </wsdl:message>
  <wsdl:message name="U9FileAppendSoapIn">
    <wsdl:part name="parameters" element="tns:U9FileAppend" />
  </wsdl:message>
  <wsdl:message name="U9FileAppendSoapOut">
    <wsdl:part name="parameters" element="tns:U9FileAppendResponse" />
  </wsdl:message>
  <wsdl:message name="U9FileVerifySoapIn">
    <wsdl:part name="parameters" element="tns:U9FileVerify" />
  </wsdl:message>
  <wsdl:message name="U9FileVerifySoapOut">
    <wsdl:part name="parameters" element="tns:U9FileVerifyResponse" />
  </wsdl:message>
  <wsdl:message name="CreateCustomerBankAccountFromXMLSoapIn">
    <wsdl:part name="parameters" element="tns:CreateCustomerBankAccountFromXML" />
  </wsdl:message>
  <wsdl:message name="CreateCustomerBankAccountFromXMLSoapOut">
    <wsdl:part name="parameters" element="tns:CreateCustomerBankAccountFromXMLResponse" />
  </wsdl:message>
  <wsdl:message name="CreateAddressFromXMLSoapIn">
    <wsdl:part name="parameters" element="tns:CreateAddressFromXML" />
  </wsdl:message>
  <wsdl:message name="CreateAddressFromXMLSoapOut">
    <wsdl:part name="parameters" element="tns:CreateAddressFromXMLResponse" />
  </wsdl:message>
  <wsdl:message name="CreateContanctFromXMLSoapIn">
    <wsdl:part name="parameters" element="tns:CreateContanctFromXML" />
  </wsdl:message>
  <wsdl:message name="CreateContanctFromXMLSoapOut">
    <wsdl:part name="parameters" element="tns:CreateContanctFromXMLResponse" />
  </wsdl:message>
  <wsdl:portType name="SevenU9WeaverSoap">
    <wsdl:operation name="CreateCustomerFromXML">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">创建客户</wsdl:documentation>
      <wsdl:input message="tns:CreateCustomerFromXMLSoapIn" />
      <wsdl:output message="tns:CreateCustomerFromXMLSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="CreateSupplierFromXML">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">创建供应商</wsdl:documentation>
      <wsdl:input message="tns:CreateSupplierFromXMLSoapIn" />
      <wsdl:output message="tns:CreateSupplierFromXMLSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="QueryAccountFromXML">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">查询科目</wsdl:documentation>
      <wsdl:input message="tns:QueryAccountFromXMLSoapIn" />
      <wsdl:output message="tns:QueryAccountFromXMLSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="QuerySupplierBankAccountFromXML">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">查询供应商银行账号</wsdl:documentation>
      <wsdl:input message="tns:QuerySupplierBankAccountFromXMLSoapIn" />
      <wsdl:output message="tns:QuerySupplierBankAccountFromXMLSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="CreateSPAdjustFromXML">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">销售价格调整单</wsdl:documentation>
      <wsdl:input message="tns:CreateSPAdjustFromXMLSoapIn" />
      <wsdl:output message="tns:CreateSPAdjustFromXMLSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="CreateVoucherFromXML">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">凭证创建</wsdl:documentation>
      <wsdl:input message="tns:CreateVoucherFromXMLSoapIn" />
      <wsdl:output message="tns:CreateVoucherFromXMLSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="U9FileCreate">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">创建文件 --分步1 返回文件id </wsdl:documentation>
      <wsdl:input message="tns:U9FileCreateSoapIn" />
      <wsdl:output message="tns:U9FileCreateSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="U9FileAppend">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">上传文件--分步2</wsdl:documentation>
      <wsdl:input message="tns:U9FileAppendSoapIn" />
      <wsdl:output message="tns:U9FileAppendSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="U9FileVerify">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">上传验证--分步3</wsdl:documentation>
      <wsdl:input message="tns:U9FileVerifySoapIn" />
      <wsdl:output message="tns:U9FileVerifySoapOut" />
    </wsdl:operation>
    <wsdl:operation name="CreateCustomerBankAccountFromXML">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">创建客户银行账号</wsdl:documentation>
      <wsdl:input message="tns:CreateCustomerBankAccountFromXMLSoapIn" />
      <wsdl:output message="tns:CreateCustomerBankAccountFromXMLSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="CreateAddressFromXML">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">创建地址</wsdl:documentation>
      <wsdl:input message="tns:CreateAddressFromXMLSoapIn" />
      <wsdl:output message="tns:CreateAddressFromXMLSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="CreateContanctFromXML">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">创建联系对象</wsdl:documentation>
      <wsdl:input message="tns:CreateContanctFromXMLSoapIn" />
      <wsdl:output message="tns:CreateContanctFromXMLSoapOut" />
    </wsdl:operation>
  </wsdl:portType>
  <wsdl:binding name="SevenU9WeaverSoap" type="tns:SevenU9WeaverSoap">
    <soap:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="CreateCustomerFromXML">
      <soap:operation soapAction="http://tempuri.org/CreateCustomerFromXML" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CreateSupplierFromXML">
      <soap:operation soapAction="http://tempuri.org/CreateSupplierFromXML" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="QueryAccountFromXML">
      <soap:operation soapAction="http://tempuri.org/QueryAccountFromXML" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="QuerySupplierBankAccountFromXML">
      <soap:operation soapAction="http://tempuri.org/QuerySupplierBankAccountFromXML" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CreateSPAdjustFromXML">
      <soap:operation soapAction="http://tempuri.org/CreateSPAdjustFromXML" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CreateVoucherFromXML">
      <soap:operation soapAction="http://tempuri.org/CreateVoucherFromXML" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="U9FileCreate">
      <soap:operation soapAction="http://tempuri.org/U9FileCreate" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="U9FileAppend">
      <soap:operation soapAction="http://tempuri.org/U9FileAppend" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="U9FileVerify">
      <soap:operation soapAction="http://tempuri.org/U9FileVerify" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CreateCustomerBankAccountFromXML">
      <soap:operation soapAction="http://tempuri.org/CreateCustomerBankAccountFromXML" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CreateAddressFromXML">
      <soap:operation soapAction="http://tempuri.org/CreateAddressFromXML" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CreateContanctFromXML">
      <soap:operation soapAction="http://tempuri.org/CreateContanctFromXML" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:binding name="SevenU9WeaverSoap12" type="tns:SevenU9WeaverSoap">
    <soap12:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="CreateCustomerFromXML">
      <soap12:operation soapAction="http://tempuri.org/CreateCustomerFromXML" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CreateSupplierFromXML">
      <soap12:operation soapAction="http://tempuri.org/CreateSupplierFromXML" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="QueryAccountFromXML">
      <soap12:operation soapAction="http://tempuri.org/QueryAccountFromXML" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="QuerySupplierBankAccountFromXML">
      <soap12:operation soapAction="http://tempuri.org/QuerySupplierBankAccountFromXML" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CreateSPAdjustFromXML">
      <soap12:operation soapAction="http://tempuri.org/CreateSPAdjustFromXML" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CreateVoucherFromXML">
      <soap12:operation soapAction="http://tempuri.org/CreateVoucherFromXML" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="U9FileCreate">
      <soap12:operation soapAction="http://tempuri.org/U9FileCreate" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="U9FileAppend">
      <soap12:operation soapAction="http://tempuri.org/U9FileAppend" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="U9FileVerify">
      <soap12:operation soapAction="http://tempuri.org/U9FileVerify" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CreateCustomerBankAccountFromXML">
      <soap12:operation soapAction="http://tempuri.org/CreateCustomerBankAccountFromXML" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CreateAddressFromXML">
      <soap12:operation soapAction="http://tempuri.org/CreateAddressFromXML" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CreateContanctFromXML">
      <soap12:operation soapAction="http://tempuri.org/CreateContanctFromXML" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:service name="SevenU9Weaver">
    <wsdl:port name="SevenU9WeaverSoap" binding="tns:SevenU9WeaverSoap">
      <soap:address location="http://60.28.102.130/SVUW/SevenU9Weaver.asmx" />
    </wsdl:port>
    <wsdl:port name="SevenU9WeaverSoap12" binding="tns:SevenU9WeaverSoap12">
      <soap12:address location="http://60.28.102.130/SVUW/SevenU9Weaver.asmx" />
    </wsdl:port>
  </wsdl:service>
</wsdl:definitions>