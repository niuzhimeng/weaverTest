package com.weavernorth.caibai.sap.action;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;
import com.weavernorth.caibai.sap.CaiBaiPoolThree;
import weaver.conn.RecordSet;
import weaver.hrm.User;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 付款申请流程 - 数据推送sap
 */
public class FuKuanAction extends BaseAction {

    private RecordSet selectSet = new RecordSet();
    /**
     * 下拉框字段id
     */
    private static final String FILE_ID = "7178";

    @Override
    public String execute(RequestInfo requestInfo) {
        User user = requestInfo.getRequestManager().getUser();
        String requestId = requestInfo.getRequestid();
        int formId = requestInfo.getRequestManager().getFormid();
        String tableName = "";

        RecordSet recordSet = new RecordSet();
        RecordSet recordSetDetail = new RecordSet();
        recordSet.executeQuery("SELECT tablename FROM workflow_bill WHERE id = '" + formId + "'");
        if (recordSet.next()) {
            tableName = recordSet.getString("tablename");
        }

        this.writeLog("付款申请流程 Start requestid --- " + requestId + "  user --- " + user.getUID() + "   fromTable --- " + tableName);
        try {

            // 查询主表
            recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");
            recordSet.next();

            int mainId = recordSet.getInt("id");
            // 流程编号
            String lcbh = recordSet.getString("lcbh");
            // 公司代码
            String gsdm = recordSet.getString("gsdm");
            // 发票张数
            String fpzs = recordSet.getString("fpzs");
            // 成本中心
            String cbzx = recordSet.getString("cbzx2");
            // 利润中心
            String lrzx = recordSet.getString("lrzx");
            // 财务节点，提交时间
            String tjsj = recordSet.getString("tjsj");
            // 审核发票信息，操作人工号
            String yzr = recordSet.getString("yzr");
            // 付款事项描述
            String fksxms = recordSet.getString("fksxms");

            JCoDestination jCoDestination = CaiBaiPoolThree.getJCoDestination();
            JCoFunction function = jCoDestination.getRepository().getFunction("ZOAIF0010_RFC");
            this.writeLog("获取函数完成===== " + function);

            JCoTable table = function.getTableParameterList().getTable("IT_TAB");

            recordSetDetail.executeQuery("select * from " + tableName + "_dt1 where mainid = " + mainId);

            int i = 0;
            while (recordSetDetail.next()) {
                table.appendRow();
                table.setRow(i);

                // 会计凭证编号
                table.setValue("ZPZH", lcbh);
                // 会计凭证中的行项目数
                table.setValue("ZHH", recordSetDetail.getString("id"));
                // 公司代码
                table.setValue("ZGSDM", gsdm);
                // 凭证中的凭证日期
                table.setValue("ZFKJDRQ", replaceDate(tjsj));

                // 凭证类型
                table.setValue("ZPZLX", recordSetDetail.getString("pzlx"));
                // 货币码
                table.setValue("ZBZ", "CNY");
                // 发票的页数
                table.setValue("ZFPZS", fpzs);
                // 借方/贷方标识
                table.setValue("ZDFKM", recordSetDetail.getString("dfkm"));
                // 总账科目
                table.setValue("ZKMLB", getKmCode(recordSetDetail.getString("kmlb")));

                // 合作伙伴编码
                table.setValue("ZHZDWBM", getJmName(recordSetDetail.getString("hzdwbm")));
                // 特殊总帐标识
                table.setValue("ZTSZZBS", recordSetDetail.getString("tszzbs"));
                // 原币金额
                String ybje;
                if (recordSetDetail.getInt("ybje") == 0) {
                    // 增值税专用发票”则使用“不含税金额”
                    ybje = recordSetDetail.getString("bhsje");
                } else {
                    ybje = recordSetDetail.getString("fpjshjje");
                }
                table.setValue("ZYBJE", ybje);
                // 成本中心
                table.setValue("ZCBZX", cbzx);

                // 利润中心
                table.setValue("ZLRZX", lrzx);
                // 现金流量
                table.setValue("ZXJLL", recordSetDetail.getString("xjll"));
                // 用于到期日计算的基准日期
                table.setValue("ZFKJZRQ", replaceDate(tjsj));
                // 付款条件代码
                table.setValue("ZFKTJ", recordSetDetail.getString("fktj"));
                // 分配编号
                table.setValue("ZFP", recordSetDetail.getString("fp"));

                // 商品名称
                table.setValue("ZSPMC", recordSetDetail.getString("spmc"));
                // 凭证抬头文本
                table.setValue("ZFKSXMS", fksxms);
                // 支付方式
                table.setValue("ZZFFS", recordSetDetail.getString("sjzffs"));
                // 往来单位
                table.setValue("ZSKGSMC", getKmName(recordSetDetail.getString("skgsmc")));
                // OA审核发票节点操作人
                table.setValue("ZYZR", yzr);
                // 发票类型
                table.setValue("ZFPLX", getSelectName(FILE_ID, recordSetDetail.getString("fplx")));

                i++;
            }
            this.writeLog("推送表数据： " + table.toString());

            this.writeLog("调用sap接口 ===========");
            function.execute(jCoDestination);

            JCoTable otReturn = function.getTableParameterList().getTable("IT_TAB");
            int numRows = otReturn.getNumRows();
            this.writeLog("返回表行数： " + numRows);
            for (int j = 0; j < numRows; j++) {
                otReturn.setRow(i);
                String type = otReturn.getString("TYPE");
                this.writeLog("TYPE: " + type);
                this.writeLog("MESSAGE: " + otReturn.getString("MESSAGE"));
                if ("E".equals(type) || "A".equals(type)) {
                    requestInfo.getRequestManager().setMessageid("110000");
                    requestInfo.getRequestManager().setMessagecontent("Sap 接口异常： " + otReturn.getString("MESSAGE"));
                    return "0";
                }
            }

            this.writeLog("付款申请流程 End ===============");
        } catch (Exception e) {
            this.writeLog("付款申请流程 Error： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("付款申请流程 Error： " + e);
            return "0";
        }

        return "1";
    }

    /**
     * 日期去掉 -
     */
    private String replaceDate(String dateStr) {
        String returnStr = "99999999";
        if (dateStr != null) {
            returnStr = dateStr.replace("-", "");
        }
        return returnStr;
    }

    /**
     * 获取下拉框的显示值
     */
    private String getSelectName(String fileId, String id) {
        String returnStr = "空";
        selectSet.executeQuery("SELECT selectname FROM workflow_SelectItem where fieldid = " + fileId + " and selectvalue = " + id);
        if (selectSet.next()) {
            returnStr = selectSet.getString("selectname");
        }
        return returnStr;
    }

    /**
     * 取系统表科目编码
     */
    private String getKmCode(String id) {
        String returnStr = "0000";
        selectSet.executeQuery("SELECT codeName from FnaBudgetfeeType where id = " + id);
        if (selectSet.next()) {
            returnStr = selectSet.getString("codeName");
        }
        return returnStr;
    }

    /**
     * 取科目名称中文名
     */
    private String getKmName(String code) {
        String returnStr = "0000";
        selectSet.executeQuery("SELECT zcmc from uf_hzdwjbxx where bh = " + code);
        if (selectSet.next()) {
            returnStr = selectSet.getString("zcmc");
        }
        return returnStr;
    }


    private String getJmName(String code) {
        String returnStr = "0000";
        selectSet.executeQuery("SELECT sapdybh from uf_hzdwjbxx where bh = " + code);
        if (selectSet.next()) {
            returnStr = selectSet.getString("sapdybh");
        }
        return returnStr;
    }

}
