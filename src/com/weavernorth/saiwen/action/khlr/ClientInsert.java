package com.weavernorth.saiwen.action.khlr;

import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 新客户录入
 */
public class ClientInsert extends BaseAction {

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

        this.writeLog("新客户录入 Start requestid --- " + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {
            // 查询主表
            recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");
            if (recordSet.next()) {
                // 行业
                String hy = recordSet.getString("hy");
                // 区域
                String qy = recordSet.getString("qy");
                // 公司名称
                String gsmc = recordSet.getString("gsmc");
                // 地址
                String dz = recordSet.getString("dz");
                // 开户行
                String khh = recordSet.getString("khh");

                // 客户简称
                String khjc = recordSet.getString("khjc");
                // 性质
                String xz = recordSet.getString("xz");
                // 税号
                String sh = recordSet.getString("sh");
                // 电话
                String dh = recordSet.getString("dh");
                // 开户行账号
                String khhzh = recordSet.getString("khhzh");

                // 姓名
                String xm = recordSet.getString("xm");
                // 办公电话
                String bgdh = recordSet.getString("bgdh");
                // 传真
                String cz = recordSet.getString("cz");
                // 交货币种 人民币/美元/欧元/英镑/丹麦克朗
                String jhbz = recordSet.getString("jhbz");
                // 税率 13%；9%；6%
                String sl = recordSet.getString("sl");

                // 办公/收货地址
                String bgshdz = recordSet.getString("bgshdz");
                // 性别
                String xb = recordSet.getString("xb");
                // 移动电话
                String yddh = recordSet.getString("yddh");
                // Email
                String email = recordSet.getString("email");
                // 出货方式
                String chfs = recordSet.getString("chfs");

                // 注册地址
                String zcdz = recordSet.getString("zcdz");
                // 销售员
                String xsy = recordSet.getString("xsy");
                // 对账日期
                String dzrq = recordSet.getString("dzrq");
                // 对账期间
                String dzqj = recordSet.getString("dzqj");
                // 开票日期
                String kprq = recordSet.getString("kprq");

                // 回款日
                String hkr = recordSet.getString("hkr");
                // 账期（天）
                String zqts = recordSet.getString("zqts");

            }

            this.writeLog("新客户录入 End ===============");
        } catch (Exception e) {
            this.writeLog("新客户录入 异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("新客户录入 异常： " + e);
            return "0";
        }

        return "1";
    }
}
