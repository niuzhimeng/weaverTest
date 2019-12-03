package com.weavernorth.jiajiezb.hr;

import com.weavernorth.jiajiezb.hr.util.JiaJieConfigInfo;
import com.weavernorth.jiajiezb.hr.util.JiaJieConnUtil;
import weaver.conn.RecordSet;
import weaver.general.TimeUtil;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 经济补偿金流程
 */
public class JingJibcjWorkFlow extends BaseAction {

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
        this.writeLog("经济补偿金流程 Start requestid=" + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {
            // 查询主表
            recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");
            recordSet.next();
            // 离职人
            String xm = recordSet.getString("xm");
            // 部门
            String bm = recordSet.getString("bm");
            // 员工编号
            String ygbh = recordSet.getString("ygbh");
            // 补偿金
            String bcje = recordSet.getString("bcje");
            // 离职日期（工资结算日）
            String lzrq = recordSet.getString("lzrq");
            this.writeLog("补偿金: " + bcje + ", 离职日期（工资结算日）: " + lzrq);

            // 插入自定义表三条数据的基本字段
            JiaJieConnUtil.insertBaseCus(Integer.parseInt(xm));
            // 更新此流程中字段
            recordSet.executeUpdate("update CUS_FIELDDATA set " + JiaJieConfigInfo.lzrq.getValue() + " = ? where id = ?", lzrq, xm);

            // 更新建模表
            recordSet.executeQuery("select 1 from uf_jtxz where xm = '" + xm + "'");
            RecordSet updateSet = new RecordSet();
            if (recordSet.next()) {
                // 更新
                updateSet.executeUpdate("update uf_jtxz set jjbcjje = ? where xm = ?", bcje, xm);
            } else {
                // 新增
                updateSet.executeUpdate("insert into uf_jtxz(xm, ygbh, bm, jjbcjje," +
                                "formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime)" +
                                " values(?,?,?,?, ?,?,?,?,?)",
                        xm, ygbh, bm, bcje,
                        JiaJieConfigInfo.XZ_MODE_ID.getValue(), "1", "0", detailCurrentTimeString.substring(0, 10), detailCurrentTimeString.substring(11));
                JiaJieConnUtil.fuQuan(detailCurrentTimeString, "uf_jtxz", Integer.parseInt(JiaJieConfigInfo.XZ_MODE_ID.getValue()));
            }

            // 插入日志
            JiaJieConnUtil.insertPerCord(xm, requestId, "离职日期", "", lzrq);
            JiaJieConnUtil.insertPerCord(xm, requestId, "补偿金", "", bcje);

            this.writeLog("经济补偿金流程 End ===============");
        } catch (Exception e) {
            this.writeLog("经济补偿金流程 异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("经济补偿金流程 异常： " + e);
            return "0";
        }

        return "1";
    }
}
