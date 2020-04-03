package com.weavernorth.saiwen.mytest;

import org.dom4j.Document;
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
}
