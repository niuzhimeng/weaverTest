package com.weavernorth.saiwen.action.gyslr;

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
                // 全称
                String kh = recordSet.getString("kh");
                // 统一社会信用代码
                String xydm = recordSet.getString("xydm");
                // 办公地址
                String bgdz = recordSet.getString("bgdz");
                // 注册地址
                String zcdz = recordSet.getString("zcdz");
                // 联系人
                String lxr = recordSet.getString("lxr");

                // 手机
                String sj = recordSet.getString("sj");
                // 邮箱
                String yx = recordSet.getString("yx");
                // 成立年份
                String clnf = recordSet.getString("clnf");
                // 员工人数
                String ygrs = recordSet.getString("ygrs");
                // 职务
                String zw = recordSet.getString("zw");

                // 办公电话
                String bgdh = recordSet.getString("bgdh");
                // 企业性质
                String qyxz = recordSet.getString("qyxz");
                // 建筑面积
                String jzmj = recordSet.getString("jzmj");
                // 发票税率
                String fpsl = recordSet.getString("fpsl");
                // 注册资本
                String zczb = recordSet.getString("zczb");

                // 上一财务年营业额
                String syye = recordSet.getString("syye");
                // 资本额币种
                String bz = recordSet.getString("bz");
                // 交易币种
                String jybz = recordSet.getString("jybz");
                // 产品类别
                String cplb = recordSet.getString("cplb");
                // 供应商等级
                String gysdj = recordSet.getString("gysdj");

                // 主要产品
                String zycp = recordSet.getString("zycp");
                // 收货原则
                String shyz = recordSet.getString("shyz");
                // 账期
                String zq = recordSet.getString("zq");
                // 必备文件
                String fj = recordSet.getString("fj");


            }

            this.writeLog("供应商录入 End ===============");
        } catch (Exception e) {
            this.writeLog("供应商录入 异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("供应商录入 异常： " + e);
            return "0";
        }

        return "1";
    }
}
