package com.weavernorth.saiwen.action.fybx;

import com.weaver.general.TimeUtil;
import com.weavernorth.saiwen.myWeb.WebUtil;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 费用报销
 */
public class Reimbursement extends BaseAction {

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

        this.writeLog("费用报销 Start requestid --- " + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {
            String currentTimeString = TimeUtil.getCurrentTimeString();
            // 查询主表
            recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");
            recordSet.next();

            String workCode = recordSet.getString("ygbm"); // 人员编码
            String lcbh = recordSet.getString("lcbh"); // 流程编号
            String jieksy = recordSet.getString("jieksy"); // 借款事由
            String fkfs = recordSet.getString("fkfs"); // 付款方式
            String zhangh = recordSet.getString("zhangh"); // 账户

            String fyssgs = recordSet.getString("fyssgs"); // 费用所属公司
            String jkje = recordSet.getString("jkje"); // 借款金额
            this.writeLog("流程编号: " + lcbh + ", 借款事由: " + jieksy);

            String jfCode = ""; // 借方-科目编码
            String dfCode = ""; // 贷方-科目编码
            if ("0".equals(fkfs)) {
                // 现金
                jfCode = "122101" + workCode;
                dfCode = "100101|0|0|0|0|0|0|0|0";
            } else if ("1".equals(fkfs)) {
                // 电汇 - 人民币
                jfCode = "122101" + workCode;
                dfCode = "100201" + zhangh;
            } else if ("2".equals(fkfs)) {
                // 电汇 - 外币
                jfCode = "122101" + workCode;
                dfCode = "100202" + zhangh;
            }

            Element root = DocumentHelper.createElement("GLVoucherList");
            Element SupplierList = root.addElement("VoucherList");
            Element glVoucherHead = SupplierList.addElement("GLVoucherHead");

            // 拼接明细
            Element m_entries_arrayLines = glVoucherHead.addElement("M_entries_ArrayLines");
            //================ 借方
            Element glVoucherLine = m_entries_arrayLines.addElement("GLVoucherLine");
            glVoucherLine.addElement("M_amountDr").setText("0"); // 固定值0
            glVoucherLine.addElement("M_amountCr").setText("0"); // 固定值0
            glVoucherLine.addElement("M_enteredDr").setText(jkje); // 借方金额(原币)
            glVoucherLine.addElement("M_enteredCr").setText(jkje); // 贷方金额(原币)
            glVoucherLine.addElement("M_accountedDr").setText(jkje); // 借方金额(本币)

            glVoucherLine.addElement("M_accountedCr").setText(jkje); // 贷方金额(本币)
            glVoucherLine.addElement("M_currency").setText("C001"); // 币种编码人民币（C001）
            glVoucherLine.addElement("M_account").setText(jfCode); // 科目编码
            glVoucherLine.addElement("M_abstracts").setText(lcbh + jieksy); // 摘要

            //================ 贷方
            Element glVoucherLinedf = m_entries_arrayLines.addElement("GLVoucherLine");
            glVoucherLinedf.addElement("M_amountDr").setText("0"); // 固定值0
            glVoucherLinedf.addElement("M_amountCr").setText("0"); // 固定值0
            glVoucherLinedf.addElement("M_enteredDr").setText(jkje); // 借方金额(原币)
            glVoucherLinedf.addElement("M_enteredCr").setText(jkje); // 贷方金额(原币)
            glVoucherLinedf.addElement("M_accountedDr").setText(jkje); // 借方金额(本币)

            glVoucherLinedf.addElement("M_accountedCr").setText(jkje); // 贷方金额(本币)
            glVoucherLinedf.addElement("M_currency").setText("C001"); // 币种编码人民币（C001）
            glVoucherLinedf.addElement("M_account").setText(dfCode); // 科目编码
            glVoucherLinedf.addElement("M_abstracts").setText(lcbh + jieksy); // 摘要

            //================ 单据头
            glVoucherLinedf.addElement("M_voucherCategory").setText("01"); // 单据类型 编码（01 记账凭证）
            glVoucherLinedf.addElement("M_sOB").setText(fyssgs); // 账簿 编码
            glVoucherLinedf.addElement("M_postedPeriod").setText(currentTimeString.substring(0, 7)); // 记账区间
            glVoucherLinedf.addElement("M_attachmentCount").setText("0"); // 固定值0
            glVoucherLinedf.addElement("M_createDate").setText(currentTimeString); // 凭证创日期时间

            Document document = DocumentHelper.createDocument(root);
            String pushXml = document.asXML();
            this.writeLog("个人借款凭证推送xml： " + pushXml);

            // 调用接口创建凭证
            String voucherReturn = WebUtil.createVoucher(pushXml, fyssgs);
            this.writeLog("创建凭证返回信息： " + voucherReturn);
            Document doc = DocumentHelper.parseText(voucherReturn);
            Element rootElt = doc.getRootElement();
            String state = rootElt.elementTextTrim("State");
            String msg = rootElt.elementTextTrim("Msg");
            if (!"SUCCESS".equalsIgnoreCase(state)) {
                this.writeLog("凭证创建失败： " + voucherReturn);
                requestInfo.getRequestManager().setMessageid("110000");
                requestInfo.getRequestManager().setMessagecontent("创建凭证返回信息： " + msg);
                return "0";
            }
            this.writeLog("费用报销 End ===============");
        } catch (Exception e) {
            this.writeLog("费用报销 异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("费用报销 异常： " + e);
            return "0";
        }

        return "1";
    }
}
