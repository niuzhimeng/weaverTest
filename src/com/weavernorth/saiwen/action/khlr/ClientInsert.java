package com.weavernorth.saiwen.action.khlr;

import com.weavernorth.saiwen.myWeb.WebUtil;
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
                String pushXml = "<?xml version=\"1.0\" encoding=\"utf‐16\"?>\n" +
                        "<RequestCustomerList>\n" +
                        "<CustomerList>\n" +
                        "<CreateCustomerModel>\n" +
                        "<M_effective>true</M_effective>\n" +
                        "<M_tradeCurrency>C001</M_tradeCurrency>\n" +
                        "<M_shortName>tsup0607‐1006</M_shortName>\n" +
                        "<Name>test customer 0607‐1006</Name>\n" +
                        "<M_code>customerts06071006</M_code>\n" +
                        "<M_org>101</M_org>\n" +
                        "<M_commission>0</M_commission>\n" +
                        "<M_commissionType>‐1</M_commissionType>\n" +
                        "<M_isCreditCheck>false</M_isCreditCheck>\n" +
                        "<M_isARCfmModify>true</M_isARCfmModify>\n" +
                        "<M_isRecTermModify>true</M_isRecTermModify>\n" +
                        "<M_isShipmentModify>true</M_isShipmentModify>\n" +
                        "<M_isPriceListModify>true</M_isPriceListModify>\n" +
                        "</CreateCustomerModel\n" +
                        "</CustomerList>\n" +
                        "</RequestCustomerList>";
                this.writeLog("OA创建客户推送xml：" + pushXml);

                // 调用创建接口
                String returnStr = WebUtil.createClient(pushXml,"");
                this.writeLog("U9返回xml： " + returnStr);
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
