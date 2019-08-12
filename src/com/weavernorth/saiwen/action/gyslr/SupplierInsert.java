package com.weavernorth.saiwen.action.gyslr;

import com.weavernorth.saiwen.myWeb.WebUtil;
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
                // 交易币种
                String jybz = recordSet.getString("jybzn");
                // 全称
                String kh = recordSet.getString("kh");
                // 简称
                String gysjc = recordSet.getString("gysjc");
                // 供应商编码
                String gysbm = recordSet.getString("gysbm");
                // 组织编码
                String zzbm = recordSet.getString("zzbm");

                String pushXml = "<?xml version=\"1.0\" encoding=\"utf‐16\"?>\n" +
                        "<RequestSupplierList>\n" +
                        "<SupplierList>\n" +
                        "<CreateSupplierModel>\n" +
                        "<M_effective>true</M_effective>\n" +
                        "<M_tradeCurrency>" + jybz + "</M_tradeCurrency>\n" + // 交易币种 code 字符串
                        "<M_shortName>" + gysjc + "</M_shortName>\n" + // 供应商简称 字符串
                        "<Name>" + kh + "</Name>\n" + // 供应商全称 字符串
                        "<M_code>" + gysbm + "</M_code>\n" + // 供应商编码 字符串
                        "<M_officialLocation></M_officialLocation>\n" + // 办公地址 code    可选
                        "<M_org>" + zzbm + "</M_org>\n" + // 组织编码
                        "<M_isPriceListModify>true</M_isPriceListModify>\n" +
                        "<M_isAPConfirmTermEditable>true</M_isAPConfirmTermEditable>\n" +
                        "<M_isPaymentTermModify>true</M_isPaymentTermModify>\n" +
                        "<M_isReceiptRuleEditable>true</M_isReceiptRuleEditable>\n" +
                        "</CreateSupplierModel>\n" +
                        "</SupplierList>\n" +
                        "</RequestSupplierList>";
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
