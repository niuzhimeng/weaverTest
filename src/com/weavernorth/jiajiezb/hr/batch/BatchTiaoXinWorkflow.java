package com.weavernorth.jiajiezb.hr.batch;

import com.weavernorth.jiajiezb.hr.batch.vo.BatchTxVo;
import com.weavernorth.jiajiezb.hr.util.JiaJieConfigInfo;
import com.weavernorth.jiajiezb.hr.util.JiaJieConnUtil;
import com.weavernorth.jiajiezb.hr.vo.JtDifferent;
import weaver.conn.RecordSet;
import weaver.general.TimeUtil;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 群体调薪流程
 */
public class BatchTiaoXinWorkflow extends BaseAction {

    private RecordSet connSet = new RecordSet();
    private RecordSet updateSet = new RecordSet();
    private static Map<String, String> zdMap = new HashMap<String, String>();

    static {
        zdMap.put("zj", "职级");
        zdMap.put("tzrq", "调整日期");
        zdMap.put("jbgz", "基本工资");
    }

    @Override
    public String execute(RequestInfo requestInfo) {
        String requestId = requestInfo.getRequestid();
        String operateType = requestInfo.getRequestManager().getSrc();
        int formId = requestInfo.getRequestManager().getFormid();
        String tableName = "";
        RecordSet recordSet = new RecordSet();
        RecordSet updateSet = new RecordSet();
        recordSet.executeQuery("SELECT tablename FROM workflow_bill WHERE id = '" + formId + "'");
        if (recordSet.next()) {
            tableName = recordSet.getString("tablename");
        }
        String detailCurrentTimeString = TimeUtil.getCurrentTimeString();
        this.writeLog("群体调薪流程 Start requestid=" + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {
            // 建模表已有员工id集合
            List<String> idList = new ArrayList<String>();
            recordSet.executeQuery("select xm from uf_jtxz");
            while (recordSet.next()) {
                idList.add(recordSet.getString("xm"));
            }

            // 查询主表
            recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");
            recordSet.next();
            String mainId = recordSet.getString("id");
            // 生效日期
            String sxrq = recordSet.getString("sxrq");
            this.writeLog("生效日期=====" + sxrq);

            recordSet.executeQuery("select * from " + tableName + "_dt1 where mainid = '" + mainId + "'");
            while (recordSet.next()) {
                String xm = recordSet.getString("xm"); // 姓名
                String bm = getSysByFiled("departmentid", "hrmresource", xm);  // 部门
                String ygbh = recordSet.getString("ygbh");  // 员工编号

                String zj = recordSet.getString("zj"); // 调整前职级
                String sctxrq = recordSet.getString("sctxrq"); // 上次调薪日期
                String tzq = recordSet.getString("tzq"); // 调整前基本工资
                BatchTxVo oldChangeVo = new BatchTxVo();
                oldChangeVo.setZj(getGgxzk(JiaJieConfigInfo.ZHI_JI_SEL.getValue(), zj));
                oldChangeVo.setTzrq(sctxrq);
                oldChangeVo.setJbgz(tzq);

                String zj1 = recordSet.getString("zj1"); // 调整后职级
                String tzh = recordSet.getString("tzh"); // 调整后基本工资
                String nsr2 = recordSet.getString("nsr2"); // 调整后年收入
                String jjbz2 = recordSet.getString("jjbz2"); // 调整后奖金标准
                BatchTxVo newChangeVo = new BatchTxVo();
                newChangeVo.setZj(getGgxzk(JiaJieConfigInfo.ZHI_JI_SEL.getValue(), zj1));
                newChangeVo.setTzrq(sxrq);
                newChangeVo.setJbgz(tzh);

                // 插入自定义表三条数据的基本字段
                JiaJieConnUtil.insertBaseCus(Integer.parseInt(xm));
                // 更新此流程中字段
                recordSet.executeUpdate("update CUS_FIELDDATA set " + JiaJieConfigInfo.ZHI_JI.getValue() + " = ? ," +
                                JiaJieConfigInfo.TIAO_ZHENG_RI_QI.getValue() + " = ? where id = ?",
                        zj1, sxrq, xm);

                this.writeLog("职级原值: " + zj + ", 现值： " + zj1);
                this.writeLog("生效日期原值: " + sctxrq + ", 现值：" + sxrq);
                this.writeLog("调整前基本工资: " + tzq + ", 调整后基本工资：" + tzh);

                // 更新建模表
                if (idList.contains(xm)) {
                    updateSet.executeUpdate("update uf_jtxz set jbgz = ?, nsr = ?, jjbz = ? where xm = ?", tzh, nsr2, jjbz2, xm);
                } else {
                    updateSet.executeUpdate("insert into uf_jtxz(xm, ygbh, bm, jbgz, nsr, jjbz, " +
                                    "formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime)" +
                                    " values(?,?,?,?,?,?, ?,?,?,?,?)",
                            xm, ygbh, bm, tzh, nsr2, jjbz2,
                            JiaJieConfigInfo.XZ_MODE_ID.getValue(), "1", "0", detailCurrentTimeString.substring(0, 10), detailCurrentTimeString.substring(11));
                    JiaJieConnUtil.fuQuan(detailCurrentTimeString, "uf_jtxz", Integer.parseInt(JiaJieConfigInfo.XZ_MODE_ID.getValue()));
                    idList.add(xm);
                }

                // 找出变动的对象
                List<JtDifferent> differentList = JiaJieConnUtil.compareObj(oldChangeVo, newChangeVo);
                for (JtDifferent different : differentList) {
                    insertPerCord(xm, requestId, zdMap.get(different.getFieldId()), different.getBeforeValue(), different.getAlterValue());
                }
            }

            this.writeLog("群体调薪流程 End ===============");
        } catch (Exception e) {
            this.writeLog("群体调薪流程 异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("群体调薪流程 异常： " + e);
            return "0";
        }

        return "1";
    }

    /**
     * 插入人员信息变更表
     *
     * @param bgr  变更人
     * @param bglc 变更流程
     * @param zdmc 字段名称
     * @param yz   原值
     * @param xz   现值
     */
    public void insertPerCord(String bgr, String bglc, String zdmc, String yz, String xz) {
        String currentDateString = TimeUtil.getCurrentDateString();
        updateSet.executeUpdate("insert into UF_RYKPBG(bgr, bglc, sxrq, zdmc, yz, xz) values(?,?,?,?,?, ?)",
                bgr, bglc, currentDateString, zdmc, yz, xz);
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

    /**
     * 根据某一字段查另一个字段
     *
     * @param resultField 查询的字段名
     * @param tableName   查询表名
     * @param selField    条件字段名
     */
    private String getSysByFiled(String resultField, String tableName, String selField) {
        String returnStr = "";
        connSet.executeQuery("select " + resultField + " from " + tableName + " where id = '" + selField + "'");
        if (connSet.next()) {
            returnStr = connSet.getString(resultField);
        }
        return returnStr;
    }

}
