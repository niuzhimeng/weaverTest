package com.weavernorth.jiajiezb.hr.batch;

import com.weavernorth.jiajiezb.hr.util.JiaJieConfigInfo;
import com.weavernorth.jiajiezb.hr.util.JiaJieConnUtil;
import com.weavernorth.jiajiezb.hr.vo.JtChangeVo;
import com.weavernorth.jiajiezb.hr.vo.JtDifferent;
import weaver.conn.RecordSet;
import weaver.general.TimeUtil;
import weaver.general.Util;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 群体劳动合同续签流程
 */
public class BatchXuQianWorkflow extends BaseAction {

    private RecordSet connSet = new RecordSet();
    private RecordSet updateSet = new RecordSet();
    private static Map<String, String> zdMap = new HashMap<String, String>();

    static {
        // 系统表字段
        zdMap.put("gw", "岗位");
        // 自定义表字段
        zdMap.put("zz", "职级");
        zdMap.put("ldhtqs", "劳动合同签署主体");
        zdMap.put("cwou", "财务OU");
        zdMap.put("ldhtksrq", "劳动合同签约开始日期");
        zdMap.put("ldhtjsrq", "劳动合同签约结束日期");

        zdMap.put("qscs", "劳动合同签署次数");
    }

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

        this.writeLog("群体劳动合同续签流程 Start requestid=" + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {
            // 查询主表
            recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");
            recordSet.next();
            String mainId = recordSet.getString("id");
            // 生效日期
            String sxrq = recordSet.getString("sxrq");
            this.writeLog("生效日期=====" + sxrq);

            recordSet.executeQuery("select * from " + tableName + "_dt1 where mainid = '" + mainId + "'");
            RecordSet sysSet = new RecordSet();
            while (recordSet.next()) {
                String xm = recordSet.getString("ygxm"); // 姓名
                String gw = recordSet.getString("gw");  // 岗位

                // 插入变更记录表
                JtChangeVo sysOldInfo = getSysOldInfo(xm); // 获取系统表旧字段
                JtChangeVo sysNewInfo = new JtChangeVo(); // 表单新字段
                sysNewInfo.setGw(getSysByFiled("jobtitlename", "hrmjobtitles", gw)); // 岗位
                // 找出变动的对象
                List<JtDifferent> sysDifferentList = JiaJieConnUtil.compareObj(sysOldInfo, sysNewInfo);
                for (JtDifferent different : sysDifferentList) {
                    insertPerCord(xm, requestId, zdMap.get(different.getFieldId()), different.getBeforeValue(), different.getAlterValue());
                }
                // 更新系统表
                sysSet.executeUpdate("update hrmresource set jobtitle = ? where id = ?", gw, xm);
                this.writeLog("操作hrmresource表结束===============");

                // 自定义表部分 ====================
                RecordSet zdySet = new RecordSet();

                String zj = recordSet.getString("zj"); // 职级
                String cwou = recordSet.getString("cwou"); // 财务OU
                String htqszt = recordSet.getString("htzt"); // 劳动合同签署主体
                String htksrq = recordSet.getString("htksrq"); // 劳动合同签约开始时间
                String htjsrq = recordSet.getString("htjsrq"); // 劳动合同签约结束时间

                String htyqdcs = recordSet.getString("htyqdcs"); // 劳动合同签署次数
                String newHtyqdcs = String.valueOf(Util.getIntValue(htyqdcs, 1) + 1); // 劳动合同签署次数 + 1

                this.writeLog("职级: " + zj + ", 财务OU: " + cwou + ", 劳动合同签约开始时间: " + htksrq);
                this.writeLog("劳动合同签署主体: " + htqszt + ", 劳动合同签约结束时间: " + htjsrq + ", 劳动合同签署次数: " + htyqdcs +
                        "劳动合同签署次数+1: " + newHtyqdcs);

                JtChangeVo oldChangeVo = getZdyOldInfo(xm);
                JtChangeVo newChangeVo = new JtChangeVo();
                newChangeVo.setZz(getGgxzk(JiaJieConfigInfo.ZHI_JI_SEL.getValue(), zj));
                newChangeVo.setCwou(getGgxzk(JiaJieConfigInfo.CWOU_SEL.getValue(), cwou));
                newChangeVo.setLdhtqs(getGgxzk(JiaJieConfigInfo.LDHT_SEL.getValue(), htqszt));
                newChangeVo.setLdhtksrq(htksrq);
                newChangeVo.setLdhtjsrq(htjsrq);

                newChangeVo.setQscs(newHtyqdcs);

                List<JtDifferent> zdyDifferentList = JiaJieConnUtil.compareObj(oldChangeVo, newChangeVo);
                for (JtDifferent different : zdyDifferentList) {
                    insertPerCord(xm, requestId, zdMap.get(different.getFieldId()), different.getBeforeValue(), different.getAlterValue());
                }

                // 插入自定义表三条数据的基本字段
                JiaJieConnUtil.insertBaseCus(Integer.parseInt(xm));
                // 更新
                String updateSql = "update CUS_FIELDDATA set " + JiaJieConfigInfo.ZHI_JI.getValue() + " = ?, " + JiaJieConfigInfo.CWOU.getValue() + " = ?, "
                        + JiaJieConfigInfo.LDHT.getValue() + " = ?, " + JiaJieConfigInfo.QYKSRQ.getValue() + " = ?," + JiaJieConfigInfo.QYJSRQ.getValue() + " = ?, "
                        + JiaJieConfigInfo.QYCS.getValue() + " = ?, " + " where id = ?";
                zdySet.executeUpdate(updateSql,
                        zj, cwou,
                        htqszt, htksrq, htjsrq,
                        htyqdcs, xm);
                this.writeLog("更新自定义表结束============");
            }

            this.writeLog("群体劳动合同续签流程 End ===============");
        } catch (Exception e) {
            this.writeLog("群体劳动合同续签流程 异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("群体劳动合同续签流程 异常： " + e);
            return "0";
        }

        return "1";
    }

    /**
     * 查询系统表变更前数据
     */
    private JtChangeVo getSysOldInfo(String userId) {
        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("select * from hrmresource where id = '" + userId + "'");
        recordSet.next();
        JtChangeVo jtChangeVo = new JtChangeVo();
        jtChangeVo.setGw(getSysByFiled("jobtitlename", "hrmjobtitles", recordSet.getString("jobtitle"))); // 岗位
        return jtChangeVo;
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

    /**
     * 查询自定义表变更前数据
     */
    private JtChangeVo getZdyOldInfo(String userId) {
        String zz = ""; // 职级
        String cwou = ""; // 财务OU
        String ldhtqs = ""; // 劳动合同签署主体
        String ldhtksrq = ""; // 劳动合同签约开始日期
        String ldhtjsrq = ""; // 劳动合同签约结束日期

        String qscs = ""; // 合同已签订次数

        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("select * from CUS_FIELDDATA where id = '" + userId + "'");
        while (recordSet.next()) {
            if (!"".equals(recordSet.getString(JiaJieConfigInfo.ZHI_JI.getValue()))) {
                zz = recordSet.getString(JiaJieConfigInfo.ZHI_JI.getValue());
            }
            if (!"".equals(recordSet.getString(JiaJieConfigInfo.LDHT.getValue()))) {
                ldhtqs = recordSet.getString(JiaJieConfigInfo.LDHT.getValue());
            }
            if (!"".equals(recordSet.getString(JiaJieConfigInfo.CWOU.getValue()))) {
                cwou = recordSet.getString(JiaJieConfigInfo.CWOU.getValue());
            }
            if (!"".equals(recordSet.getString(JiaJieConfigInfo.QYKSRQ.getValue()))) {
                ldhtksrq = recordSet.getString(JiaJieConfigInfo.QYKSRQ.getValue());
            }
            if (!"".equals(recordSet.getString(JiaJieConfigInfo.QYJSRQ.getValue()))) {
                ldhtjsrq = recordSet.getString(JiaJieConfigInfo.QYJSRQ.getValue());
            }
            if (!"".equals(recordSet.getString(JiaJieConfigInfo.QYCS.getValue()))) {
                qscs = recordSet.getString(JiaJieConfigInfo.QYCS.getValue());
            }

        }
        JtChangeVo jtChangeVo = new JtChangeVo();

        jtChangeVo.setZz(getGgxzk(JiaJieConfigInfo.ZHI_JI_SEL.getValue(), zz));
        jtChangeVo.setLdhtqs(getGgxzk(JiaJieConfigInfo.LDHT_SEL.getValue(), ldhtqs));
        jtChangeVo.setCwou(getGgxzk(JiaJieConfigInfo.CWOU_SEL.getValue(), cwou));
        jtChangeVo.setLdhtksrq(ldhtksrq);
        jtChangeVo.setLdhtjsrq(ldhtjsrq);

        jtChangeVo.setQscs(qscs);
        return jtChangeVo;
    }
}
