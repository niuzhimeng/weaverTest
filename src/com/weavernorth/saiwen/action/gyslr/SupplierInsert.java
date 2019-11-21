package com.weavernorth.saiwen.action.gyslr;

import com.weavernorth.saiwen.myWeb.WebUtil;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 供应商录入
 */
public class SupplierInsert extends BaseAction {

    @Override
    public String execute(RequestInfo requestInfo) {
        String requestId = requestInfo.getRequestid();
        String operateType = requestInfo.getRequestManager().getSrc();
        int formId = requestInfo.getRequestManager().getFormid();
        String tableName = "";
        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("SELECT tablename FROM workflow_bill WHERE id = '" + formId + "'");
        if (recordSet.next()) {
            tableName = recordSet.getString("tablename");
        }

        this.writeLog("供应商录入 Start requestid --- " + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {
            // 查询主表
            recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");
            if (recordSet.next()) {
                // 组织编码
                String zzbm = recordSet.getString("zzbm");

                Element root = DocumentHelper.createElement("RequestSupplierList");
                Element SupplierList = root.addElement("SupplierList");
                Element CreateSupplierModel = SupplierList.addElement("CreateSupplierModel");

                // 拼接子级
                Element m_descFlexField = CreateSupplierModel.addElement("M_descFlexField");
                m_descFlexField.addElement("M_privateDescSeg4").setText(recordSet.getString("gysdj")); // 供应商等级
                m_descFlexField.addElement("M_privateDescSeg3").setText(recordSet.getString("jzmj")); // 建筑面积
                m_descFlexField.addElement("M_privateDescSeg2").setText(recordSet.getString("qyxz")); // 企业性质
                m_descFlexField.addElement("M_pubDescSeg8").setText(recordSet.getString("gysjc")); // 主要产品
                CreateSupplierModel.addElement("M_Turnover").setText(recordSet.getString("syye")); // 营业额（文本）

                CreateSupplierModel.addElement("M_RegisterCapital").setText(recordSet.getString("zbebz")); // 资本额币种
                CreateSupplierModel.addElement("M_RegisterLocation").setText(recordSet.getString("zcdz")); // 注册地址
                CreateSupplierModel.addElement("M_EmployeeCount").setText(recordSet.getString("ygrs")); // 员工人数
                CreateSupplierModel.addElement("M_EstablishDate").setText(recordSet.getString("clnf")); // 成立年份
                CreateSupplierModel.addElement("M_effective").setText("true"); // 是否有效

                CreateSupplierModel.addElement("M_tradeCurrency").setText(recordSet.getString("jybz")); // 交易币种
                CreateSupplierModel.addElement("M_shortName").setText(recordSet.getString("gysjc")); // 供应商简称
                CreateSupplierModel.addElement("Name").setText(recordSet.getString("kh")); // 供应商全称
                CreateSupplierModel.addElement("M_RegisterCapitalCurrency").setText(recordSet.getString("jybz1")); // 交易币种
                CreateSupplierModel.addElement("M_Category").setText(recordSet.getString("cplb")); // 分类，编码

                CreateSupplierModel.addElement("M_receiptRule").setText(recordSet.getString("shyzll")); // 编码（目前三个选项）
                CreateSupplierModel.addElement("M_aPConfirmTerm").setText(recordSet.getString("lztj")); // 账期
                CreateSupplierModel.addElement("M_code").setText(recordSet.getString("gysbm")); // 供应商编码
                CreateSupplierModel.addElement("M_officialLocation").setText(recordSet.getString("bgdz")); // 办公地址
                CreateSupplierModel.addElement("M_corpUnifyCode").setText(recordSet.getString("xydm")); // 统一社会信用代码

                CreateSupplierModel.addElement("M_taxSchedule").setText(recordSet.getString("szh")); // 税组合编码
                CreateSupplierModel.addElement("M_isPriceListModify").setText("true");
                CreateSupplierModel.addElement("M_isAPConfirmTermEditable").setText("true");
                CreateSupplierModel.addElement("M_isPaymentTermModify").setText("true");
                CreateSupplierModel.addElement("M_isReceiptRuleEditable").setText("true");

                Document document = DocumentHelper.createDocument(root);
                String pushXml = document.asXML();
                this.writeLog("OA创建供应商推送xml：" + pushXml);

                // 调用创建接口
                String returnStr = WebUtil.createSupplier(pushXml, zzbm);
                this.writeLog("U9返回xml： " + returnStr);
                if (returnStr.contains("error")) {
                    this.writeLog("推送供应商错误： " + returnStr);
                    requestInfo.getRequestManager().setMessageid("1100000");
                    requestInfo.getRequestManager().setMessagecontent("供应商录入 异常： " + returnStr);
                    return "0";
                }
            }

            this.writeLog("供应商录入 End ===============");
        } catch (Exception e) {
            this.writeLog("供应商录入 异常： " + e);
            requestInfo.getRequestManager().setMessageid("1100000");
            requestInfo.getRequestManager().setMessagecontent("供应商录入 异常： " + e);
            return "0";
        }

        return "1";
    }
}
