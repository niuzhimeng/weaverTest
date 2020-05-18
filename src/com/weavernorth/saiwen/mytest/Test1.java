package com.weavernorth.saiwen.mytest;

import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;

public class Test1 {

    @Test
    public void test1() {
        Element root = DocumentHelper.createElement("RequestCustomerList");
        Element SupplierList = root.addElement("CustomerList");
        Element CreateSupplierModel = SupplierList.addElement("CreateCustomerModel");
        // 拼接子级
        Element m_descFlexField = CreateSupplierModel.addElement("M_descFlexField");
        m_descFlexField.addElement("M_privateDescSeg4").setText(""); // 供应商等级
        m_descFlexField.addElement("M_privateDescSeg2").setText(""); // 建筑面积

        CreateSupplierModel.addElement("M_department").setText("");
        CreateSupplierModel.addElement("M_department").setText("");
        CreateSupplierModel.addElement("M_department").setText("");
        CreateSupplierModel.addElement("M_department").setText("");
        CreateSupplierModel.addElement("M_department").setText("");

        CreateSupplierModel.addElement("M_department").setText("");
        CreateSupplierModel.addElement("M_department").setText("");
        CreateSupplierModel.addElement("M_department").setText("");
        CreateSupplierModel.addElement("M_department").setText("");
        CreateSupplierModel.addElement("M_department").setText("");

        CreateSupplierModel.addElement("M_department").setText("");
        CreateSupplierModel.addElement("M_department").setText("");
        CreateSupplierModel.addElement("M_department").setText("");
        CreateSupplierModel.addElement("M_department").setText("");
        CreateSupplierModel.addElement("M_department").setText("");

        CreateSupplierModel.addElement("M_department").setText("");
        CreateSupplierModel.addElement("M_department").setText("");
        CreateSupplierModel.addElement("M_department").setText("");
        CreateSupplierModel.addElement("M_department").setText("");
        CreateSupplierModel.addElement("M_department").setText("");

        CreateSupplierModel.addElement("M_department").setText("");
        CreateSupplierModel.addElement("M_department").setText("");
        CreateSupplierModel.addElement("M_department").setText("");
        CreateSupplierModel.addElement("M_department").setText("");
        CreateSupplierModel.addElement("M_department").setText("");

        CreateSupplierModel.addElement("M_department").setText("");
        CreateSupplierModel.addElement("M_department").setText("");
        CreateSupplierModel.addElement("M_department").setText("");

        Document document = DocumentHelper.createDocument(root);
        String pushXml = document.asXML();
        System.out.println(pushXml);
    }

    @Test
    public void test2() {
        Element root = DocumentHelper.createElement("GLVoucherList");
        Element SupplierList = root.addElement("VoucherList");
        Element glVoucherHead = SupplierList.addElement("GLVoucherHead");

        // 拼接明细
        Element m_entries_arrayLines = glVoucherHead.addElement("M_entries_ArrayLines");
        for (int i = 0; i < 2; i++) {
            Element glVoucherLine = m_entries_arrayLines.addElement("GLVoucherLine");
            glVoucherLine.addElement("M_amountDr").setText("1");
            glVoucherLine.addElement("M_amountCr").setText("1");
            glVoucherLine.addElement("M_enteredDr").setText("1");
            glVoucherLine.addElement("M_enteredCr").setText("1");
            glVoucherLine.addElement("M_accountedDr").setText("1");

            glVoucherLine.addElement("M_accountedCr").setText("1");
            glVoucherLine.addElement("M_currency").setText("1");
            glVoucherLine.addElement("M_account").setText("1");
            glVoucherLine.addElement("M_abstracts").setText("nzm&lt;br&gt");
        }

        glVoucherHead.addElement("M_voucherCategory").setText("1");
        glVoucherHead.addElement("M_sOB").setText("1");
        glVoucherHead.addElement("M_postedPeriod").setText("1");
        glVoucherHead.addElement("M_attachmentCount").setText("1");
        glVoucherHead.addElement("M_createDate").setText("1");

        Document document = DocumentHelper.createDocument(root);
        String pushXml = document.asXML();
        System.out.println(pushXml.replaceAll("\\s*",""));
    }

    @Test
    public void test3(){
        Element root = DocumentHelper.createElement("PayBillList");
        Element payBillDoc = root.addElement("PayBillDoc");
        Element payBillHead = payBillDoc.addElement("PayBillHead");

        Element m_payBillLines_arrayLines = payBillHead.addElement("M_payBillLines_ArrayLines");
        Element payBillLine = m_payBillLines_arrayLines.addElement("PayBillLine");

        Element m_payBillUseLines = payBillLine.addElement("M_payBillUseLines");
        Element payBillUseLine = m_payBillUseLines.addElement("PayBillUseLine");
        payBillUseLine.addElement("M_money").setText("");
        payBillUseLine.addElement("M_payProperty").setText("");

        payBillLine.addElement("M_payBkAcc").setText("");
        payBillLine.addElement("M_recBkAccount").setText("");
        payBillLine.addElement("M_recBkAccName").setText("");
        payBillLine.addElement("M_settlementMethod").setText("");

        payBillHead.addElement("M_dept").setText("2");
        payBillHead.addElement("M_transactor").setText("");
        payBillHead.addElement("M_payObjType").setText("");
        payBillHead.addElement("M_payDate").setText("");
        payBillHead.addElement("M_supp").setText("");

        payBillHead.addElement("M_documentType").setText("");
        payBillHead.addElement("M_suppSite").setText("");
        payBillHead.addElement("M_pC").setText("");
        payBillHead.addElement("M_bizOrg").setText("");
        Document document = DocumentHelper.createDocument(root);
        String pushXml = document.asXML();
        System.out.println(pushXml);
    }

    @Test
    public void test4() throws DocumentException {
        String returnXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "\n" +
                "<AccountFullResult> \n" +
                "  <State>SUCCESS</State>  \n" +
                "  <Msg>成功获取指定科目余额</Msg>  \n" +
                "  <AccountResult> \n" +
                "    <SOB_Code>SWTJSOB</SOB_Code>  \n" +
                "    <EndBalanceTotal>-889.000000000</EndBalanceTotal>  \n" +
                "    <EndBalanceTotalBEQ>-889.000000000</EndBalanceTotalBEQ>  \n" +
                "    <Account_Code>122101|0|0|103|T3704|0|0|0|0|0|0</Account_Code>  \n" +
                "    <Account_Name>内部个人往来|0|0|人力资源部|呂昰錡|0|0|0|0|0|0</Account_Name>  \n" +
                "    <Account_Segment1>122101</Account_Segment1>  \n" +
                "    <CurrencyCode>C001</CurrencyCode>  \n" +
                "    <CurrencyName>人民币元</CurrencyName>  \n" +
                "    <PeriodNetDr>0.000000000</PeriodNetDr>  \n" +
                "    <PeriodNetDrBEQ>0.000000000</PeriodNetDrBEQ>  \n" +
                "    <PeriodNetCr>889.000000000</PeriodNetCr>  \n" +
                "    <PeriodNetCrBEQ>889.000000000</PeriodNetCrBEQ>  \n" +
                "    <Account_CombineName>其他应收款\uE76C内部个人往来#@##@##@#人力资源部#@#呂昰錡#@##@##@##@##@#</Account_CombineName> \n" +
                "  </AccountResult> \n" +
                "</AccountFullResult>\n";

        Document doc = DocumentHelper.parseText(returnXml);
        Element rootElt = doc.getRootElement();
        String state = rootElt.elementTextTrim("State");
        System.out.println(state);



    }

}
