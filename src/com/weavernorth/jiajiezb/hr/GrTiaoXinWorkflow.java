package com.weavernorth.jiajiezb.hr;

import com.weavernorth.jiajiezb.hr.util.JiaJieConfigInfo;
import com.weavernorth.jiajiezb.hr.util.JiaJieConnUtil;
import weaver.conn.RecordSet;
import weaver.general.TimeUtil;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 个人调薪流程
 */
public class GrTiaoXinWorkflow extends BaseAction {

    private RecordSet connSet = new RecordSet();

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
        String detailCurrentTimeString = TimeUtil.getCurrentTimeString();
        this.writeLog("个人调薪流程 Start requestid=" + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {

            // 查询主表
            recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");
            recordSet.next();
            // 姓名
            String xm = recordSet.getString("xm");
            // 部门
            String bm = recordSet.getString("bm");
            // 员工编号
            String ygbh = recordSet.getString("ygbh");
            // 调整后职级
            String hzj = recordSet.getString("hzj");
            // 本次生效日期
            String sxrq = recordSet.getString("sxrq");
            // 调整后基本工资
            String hjbgz = recordSet.getString("hjbgz");

            String nsr2 = recordSet.getString("nsr2"); // 调整后年收入
            String jjbz2 = recordSet.getString("jjbz2"); // 调整后奖金标准

            // 查询原值
            String oldZhiJi = JiaJieConnUtil.getCusById(xm, JiaJieConfigInfo.ZHI_JI.getValue()); // 职级
            String oldTiaoZRQ = JiaJieConnUtil.getCusById(xm, JiaJieConfigInfo.TIAO_ZHENG_RI_QI.getValue()); // 调整日期

            // 插入自定义表三条数据的基本字段
            JiaJieConnUtil.insertBaseCus(Integer.parseInt(xm));
            // 更新此流程中字段
            recordSet.executeUpdate("update CUS_FIELDDATA set " + JiaJieConfigInfo.ZHI_JI.getValue() + " = ? ," +
                            JiaJieConfigInfo.TIAO_ZHENG_RI_QI.getValue() + " = ? where id = ?",
                    hzj, sxrq, xm);

            this.writeLog("职级原值: " + oldZhiJi + ", 现值： " + hzj);
            this.writeLog("生效日期原值: " + oldTiaoZRQ + ", 现值：" + sxrq);

            // 更新建模表
            String jbgz = ""; // 基本工资
            String nsr = ""; // 年收入
            String jjbz = ""; // 奖金标准
            recordSet.executeQuery("select * from uf_jtxz where xm = '" + xm + "'");
            RecordSet updateSet = new RecordSet();
            if (recordSet.next()) {
                this.writeLog("更新建模========");
                jbgz = recordSet.getString("jbgz");
                nsr = recordSet.getString("nsr");
                jjbz = recordSet.getString("jjbz");
                updateSet.executeUpdate("update uf_jtxz set jbgz = ?, nsr = ?, jjbz = ? where xm = ?", hjbgz, nsr2, jjbz2,
                        xm);
            } else {
                this.writeLog("新增建模========");
                updateSet.executeUpdate("insert into uf_jtxz(xm, ygbh, bm, jbgz, nsr, jjbz, " +
                                "formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime)" +
                                " values(?,?,?,?,?,?,  ?,?,?,?,?)",
                        xm, ygbh, bm, hjbgz, nsr2, jjbz2,
                        JiaJieConfigInfo.XZ_MODE_ID.getValue(), "1", "0", detailCurrentTimeString.substring(0, 10), detailCurrentTimeString.substring(11));

                JiaJieConnUtil.fuQuan(detailCurrentTimeString, "uf_jtxz", Integer.parseInt(JiaJieConfigInfo.XZ_MODE_ID.getValue()));
            }
            this.writeLog("【基本工资】原值: " + jbgz + ", 现值： " + hjbgz +
                    ", 【年收入】原值：" + nsr + ", 现值： " + nsr2 +
                    ", 【奖金标准】原值：" + jjbz + ", 现值： " + jjbz2);

            // 插入日志
            JiaJieConnUtil.insertPerCord(xm, requestId, "职级", getGgxzk(JiaJieConfigInfo.ZHI_JI_SEL.getValue(), oldZhiJi),
                    getGgxzk(JiaJieConfigInfo.ZHI_JI_SEL.getValue(), hzj));
            JiaJieConnUtil.insertPerCord(xm, requestId, "调整日期", oldTiaoZRQ, sxrq);
            JiaJieConnUtil.insertPerCord(xm, requestId, "基本工资", jbgz, hjbgz);
            this.writeLog("个人调薪流程 End ===============");
        } catch (Exception e) {
            this.writeLog("个人调薪流程 异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("个人调薪流程 异常： " + e);
            return "0";
        }

        return "1";
    }

    /**
     * 查询公共选择框的汉字显示
     *
     * @param mainId   公共选择框id
     * @param disorder 选项id
     */
    private String getGgxzk(String mainId, String disorder) {
        String returnStr = "";
        connSet.executeQuery(" SELECT NAME FROM MODE_SELECTITEMPAGEDETAIL WHERE MAINID = '" + mainId + "' and DISORDER = '" + disorder + "'");
        if (connSet.next()) {
            returnStr = connSet.getString("NAME");
        }
        return returnStr;
    }
}
