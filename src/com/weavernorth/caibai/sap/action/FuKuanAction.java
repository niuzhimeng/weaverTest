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
 * 付款申请流程 - 数据推送sap
 */
public class FuKuanAction extends BaseAction {

    private RecordSet selectSet = new RecordSet();
    /**
     * 下拉框字段id
     */
    private static final String FILE_ID = "7178";

    private String lcbh;
    private String gsdm;
    private String fpzs;
    private String depName;
    private String requestId;

    private String skgsmc;
    private String tjsj;
    private String fksxms;
    private String yzr;

    @Override
    public String execute(RequestInfo requestInfo) {
        String operatetype = requestInfo.getRequestManager().getSrc();
        if (!"submit".equals(operatetype)) {
            return "1";
        }
        User user = requestInfo.getRequestManager().getUser();
        requestId = requestInfo.getRequestid();
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
            lcbh = recordSet.getString("lcbh");
            // 公司代码
            gsdm = recordSet.getString("gsdm");
            // 发票张数
            fpzs = recordSet.getString("fpzs");
            // 申请部门名称
            depName = getDepName(recordSet.getInt("sqbm"));
            // 财务节点，提交时间
            tjsj = TimeUtil.getCurrentDateString().replace("-", "");
            // 审核发票信息，操作人工号
            yzr = recordSet.getString("yzr");
            // 付款事项描述
            fksxms = recordSet.getString("fksxms");
            // 收款公司名称
            skgsmc = recordSet.getString("skgsmc");

            JCoDestination jCoDestination = CaiBaiPoolThree.getJCoDestination();
            JCoFunction function = jCoDestination.getRepository().getFunction("ZOAIF0010_RFC");
            this.writeLog("获取函数完成===== " + function);

            JCoTable table = function.getTableParameterList().getTable("IT_TAB");

            recordSetDetail.executeQuery("select * from " + tableName + "_dt1 where mainid = " + mainId + " order by id");

            int i = 0;
            while (recordSetDetail.next()) {
                table.appendRow();
                table.setRow(i);

                // 流程id
                table.setValue("ZID", requestId);
                // 会计凭证编号
                table.setValue("ZPZH", lcbh);
                // 会计凭证中的行项目数
                table.setValue("ZHH", i + 1);
                // 公司代码
                table.setValue("ZGSDM", gsdm);
                // 凭证中的凭证日期
                table.setValue("ZFKJDRQ", replaceDate(tjsj));

                // 凭证类型
                table.setValue("ZPZLX", getZdz(recordSetDetail.getString("pzlx")));
                // 货币码
                table.setValue("ZBZ", "CNY");
                // 发票的页数
                table.setValue("ZFPZS", fpzs);
                // 借/贷标识
                String jdbs;
                if ("0".equals(recordSetDetail.getString("jd"))) {
                    jdbs = "S";
                } else {
                    jdbs = "H";
                }
                table.setValue("ZKMFX", jdbs);
                // 科目类别
                table.setValue("ZKMLB", getKmCode(recordSetDetail.getString("kmlb")));
                // 合作伙伴编码
                table.setValue("ZHZDWBM", getJmName(skgsmc));

                // 特殊总帐标识
                table.setValue("ZTSZZBS", getZdz(recordSetDetail.getString("tszzbs")));
                // 原币金额
                String ybje;
                if (recordSetDetail.getInt("fplx") == 0) {
                    // 增值税专用发票”则使用“不含税金额”
                    ybje = recordSetDetail.getString("bhsje");
                } else {
                    // 取付款金额字段
                    ybje = recordSetDetail.getString("fkje");
                }
                table.setValue("ZYBJE", ybje);
                // 成本中心
                table.setValue("ZCBZX", recordSetDetail.getString("cbzx"));

                // 利润中心
                table.setValue("ZLRZX", recordSetDetail.getString("lrzx"));
                // 现金流量
                table.setValue("ZXJLL", getZdz(recordSetDetail.getString("xjll")));

                // 用于到期日计算的基准日期
                table.setValue("ZFKJZRQ", replaceDate(tjsj));
                // 付款条件代码
                table.setValue("ZFKTJ", getZdz(recordSetDetail.getString("fktj")));
                // 分配编号
                table.setValue("ZFP", recordSetDetail.getString("fp"));
                // 商品名称
                table.setValue("ZSPMC", depName + recordSetDetail.getString("spmc"));
                // 凭证抬头文本
                table.setValue("ZFKSXMS", fksxms);

                // 支付方式
                table.setValue("ZZFFS", getZdz(recordSetDetail.getString("sjzffs")));
                // 往来单位
                table.setValue("ZSKGSMC", getKmName(skgsmc));
                // OA审核发票节点操作人
                table.setValue("ZYZR", yzr);
                // 发票类型
                table.setValue("ZFPLX", getSelectName(FILE_ID, recordSetDetail.getString("fplx")));

                i++;
                this.writeLog("发票类型： " + recordSetDetail.getInt("fplx"));
                if (recordSetDetail.getInt("fplx") == 0) {
                    insertAgain(table, i, recordSetDetail);
                    i++;
                }
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

    private void insertAgain(JCoTable table, int i, RecordSet recordSetDetail) {
        this.writeLog("代码自增行==== " + i);
        table.appendRow();
        table.setRow(i);

        // 会计凭证编号
        table.setValue("ZID", requestId);
        // 会计凭证编号
        table.setValue("ZPZH", lcbh);
        // 会计凭证中的行项目数
        table.setValue("ZHH", i + 1);
        // 公司代码
        table.setValue("ZGSDM", gsdm);
        // 凭证中的凭证日期
        table.setValue("ZFKJDRQ", replaceDate(tjsj));

        // 凭证类型
        table.setValue("ZPZLX", getZdz(recordSetDetail.getString("pzlx")));
        // 货币码
        table.setValue("ZBZ", "CNY");
        // 发票的页数
        table.setValue("ZFPZS", fpzs);
        // 借/贷标识
        String jdbs;
        if ("0".equals(recordSetDetail.getString("jd"))) {
            jdbs = "S";
        } else {
            jdbs = "H";
        }
        table.setValue("ZKMFX", jdbs);
        // 总账科目
        table.setValue("ZKMLB", "2221001001");

        // 合作伙伴编码
        table.setValue("ZHZDWBM", getJmName(skgsmc));
        // 特殊总帐标识
        table.setValue("ZTSZZBS", getZdz(recordSetDetail.getString("tszzbs")));
        // 原币金额
        table.setValue("ZYBJE", recordSetDetail.getString("se"));
        // 成本中心
        table.setValue("ZCBZX", "");

        // 利润中心
        table.setValue("ZLRZX", "P8000001");
        // 现金流量
        table.setValue("ZXJLL", getZdz(recordSetDetail.getString("xjll")));
        // 用于到期日计算的基准日期
        table.setValue("ZFKJZRQ", replaceDate(tjsj));
        // 付款条件代码
        table.setValue("ZFKTJ", getZdz(recordSetDetail.getString("fktj")));
        // 分配编号
        table.setValue("ZFP", recordSetDetail.getString("fp"));

        // 商品名称
        table.setValue("ZSPMC", recordSetDetail.getString("spmc") + "可抵扣进项税");
        // 付款事项描述
        table.setValue("ZFKSXMS", fksxms + "可抵扣进项税");
        // 实际支付方式
        table.setValue("ZZFFS", getZdz(recordSetDetail.getString("sjzffs")));
        // 收款公司名称
        table.setValue("ZSKGSMC", getKmName(skgsmc));
        // OA审核发票节点操作人
        table.setValue("ZYZR", yzr);
        // 发票类型
        table.setValue("ZFPLX", getSelectName(FILE_ID, recordSetDetail.getString("fplx")));

    }

    /**
     * 获取申请部门中文名称
     */
    private String getDepName(int depId) {
        String returnStr = "空";
        selectSet.executeQuery("select departmentname from HrmDepartment where id = " + depId);
        if (selectSet.next()) {
            returnStr = selectSet.getString("departmentname");
        }
        return returnStr;
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
        if (id == null || "".equals(id)) {
            return returnStr;
        }
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
        if (id == null || "".equals(id)) {
            return returnStr;
        }
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
        if (code == null || "".equals(code)) {
            return returnStr;
        }
        selectSet.executeQuery("SELECT zcmc from uf_hzdwjbxx where id = " + code);
        if (selectSet.next()) {
            returnStr = selectSet.getString("zcmc");
        }
        return returnStr;
    }


    private String getJmName(String code) {
        String returnStr = "0000";
        if (code == null || "".equals(code)) {
            return returnStr;
        }
        selectSet.executeQuery("SELECT sapdybh from uf_hzdwjbxx where id = " + code);
        if (selectSet.next()) {
            returnStr = selectSet.getString("sapdybh");
        }
        return returnStr;
    }

    private String getZdz(String id) {
        String returnStr = "0000";
        if (id == null || "".equals(id)) {
            return returnStr;
        }
        selectSet.executeQuery("SELECT zdz from uf_pzb where id = " + id);
        if (selectSet.next()) {
            returnStr = selectSet.getString("zdz");
        }
        return returnStr;
    }

}
