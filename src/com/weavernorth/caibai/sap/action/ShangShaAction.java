package com.weavernorth.caibai.sap.action;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;
import com.weaver.general.TimeUtil;
import com.weavernorth.caibai.sap.CaiBaiPoolThree;
import weaver.conn.RecordSet;
import weaver.hrm.User;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 商厦结算流程 - 数据推送sap
 */
public class ShangShaAction extends BaseAction {

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

        recordSet.executeQuery("select workcode form hrmresource where id = " + user.getUID());
        recordSet.next();
        String workCode = recordSet.getString("workcode");

        this.writeLog("商厦结算流程 Start requestid --- " + requestId + "  user --- " + user.getUID() + "   fromTable --- " + tableName);
        try {
            this.writeLog("当前操作人工号： " + workCode);

            // 当前日期 yyyyMMdd
            String currentDate = TimeUtil.getCurrentDateString().replace("-", "");
            // 查询主表
            recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");
            recordSet.next();

            int mainId = recordSet.getInt("id");
            // 流程编号
            String lcbh = recordSet.getString("lcbh");

            JCoDestination jCoDestination = CaiBaiPoolThree.getJCoDestination();
            JCoFunction function = jCoDestination.getRepository().getFunction("ZOAIF0010_RFC");
            this.writeLog("获取函数完成===== " + function);

            JCoTable table = function.getTableParameterList().getTable("IT_TAB");

            recordSetDetail.executeQuery("select * from " + tableName + "_dt1 where mainid = " + mainId);
            // 明细行号
            int nums = recordSetDetail.getCounts();
            int i = 0;
            while (recordSetDetail.next()) {
                table.appendRow();
                table.setRow(i);

                // 费用科目
                String fykm = recordSetDetail.getString("fykm");
                // 付款金额
                String fkje = recordSetDetail.getString("fkje");
                // 付款时间
                String fksj = recordSetDetail.getString("fksj");
                // 付款方式
                String fkfs = recordSetDetail.getString("fkfs");

                // 集团
                table.setValue("MANDT", "");
                // 会计凭证编号
                table.setValue("ZPZH", lcbh);
                // 会计凭证中的行项目数
                table.setValue("ZHH", nums);
                // 公司代码
                table.setValue("ZGSDM", "");
                // 凭证中的凭证日期
                table.setValue("ZFKJDRQ", currentDate);

                // 凭证类型
                table.setValue("ZPZLX", "SA");
                // 货币码
                table.setValue("ZBZ", "CNY");
                // 发票的页数
                table.setValue("ZFPZS", recordSetDetail.getString("fpzs"));
                // 借方/贷方标识
                table.setValue("ZKMFX", recordSetDetail.getString("jd"));
                // 总账科目
                table.setValue("ZKMLB", recordSetDetail.getString("kmlb"));

                // 合作伙伴编码
                table.setValue("ZHZDWBM", recordSetDetail.getString("hzdwbm"));
                // 合作单位类别
                table.setValue("ZHZDWLB", recordSetDetail.getString("hzdwlb"));
                // 特殊总帐标识
                table.setValue("ZTSZZBS", recordSetDetail.getString("tszzbs"));
                // 原币金额
                table.setValue("ZYBJE", recordSetDetail.getString("ybje"));
                // 成本中心
                table.setValue("ZCBZX", recordSetDetail.getString("cbzx"));

                // 利润中心
                table.setValue("ZLRZX", recordSetDetail.getString("lrzx"));
                // 现金流量
                table.setValue("ZXJLL", recordSetDetail.getString("xjll"));
                // 用于到期日计算的基准日期
                table.setValue("ZFKJZRQ", replaceDate(recordSetDetail.getString("fkjzrq")));
                // 付款条件代码
                table.setValue("ZFKTJ", recordSetDetail.getString("fktj"));
                // 分配编号
                table.setValue("ZFP", recordSetDetail.getString("fp"));

                // 项目文本
                table.setValue("ZKXMC", recordSetDetail.getString("kxmc"));
                // 凭证抬头文本
                table.setValue("ZFKSXMS", recordSetDetail.getString("kxmc"));
                // 支付方式
                table.setValue("ZZFFS", recordSetDetail.getString("zffs"));
                // 往来单位
                table.setValue("ZSKGSMC", recordSetDetail.getString("skgsmc"));
                // OA审核发票节点操作人
                table.setValue("ZYZR", workCode);

                i++;
            }
            this.writeLog("推送表数据： " + table.toString());

            this.writeLog("调用sap接口 ===========");
            function.execute(jCoDestination);

            this.writeLog("商厦结算流程 End ===============");
        } catch (Exception e) {
            this.writeLog("商厦结算流程 Error： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("商厦结算流程 Error： " + e);
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

}
