package com.weavernorth.jiajiezb.hr.batch;

import com.weavernorth.jiajiezb.hr.util.JiaJieConfigInfo;
import com.weavernorth.jiajiezb.hr.util.JiaJieConnUtil;
import com.weavernorth.jiajiezb.hr.vo.JtChangeVo;
import com.weavernorth.jiajiezb.hr.vo.JtDifferent;
import weaver.conn.RecordSet;
import weaver.general.TimeUtil;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 群体任免流程
 */
public class BatchRenMianWorkflow extends BaseAction {

    private RecordSet connSet = new RecordSet();
    private RecordSet updateSet = new RecordSet();
    private static Map<String, String> zdMap = new HashMap<String, String>();

    static {
        // 系统表字段
        zdMap.put("gw", "岗位");
        zdMap.put("bm", "部门");
        // 自定义表字段
        zdMap.put("zz", "职级");
        zdMap.put("gwlx", "岗位类型");
        zdMap.put("bps", "BPS审批人");
        zdMap.put("bgdd", "办公地点");
        zdMap.put("wxyj", "五险一金缴纳地");

        zdMap.put("cwou", "财务OU");
        zdMap.put("ldhtqs", "劳动合同签署主体");
        zdMap.put("ddrq", "生效日期");
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

        this.writeLog("群体任免流程 Start requestid=" + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
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
                String xm = recordSet.getString("xm"); // 姓名
                String bm = recordSet.getString("bm");  // 部门
                String fb = getSysByFiled("subcompanyid1", "hrmdepartment", bm); // 分部
                String gw = recordSet.getString("gw");  // 岗位

                // 插入变更记录表
                JtChangeVo sysOldInfo = getSysOldInfo(xm); // 获取系统表旧字段
                JtChangeVo sysNewInfo = new JtChangeVo(); // 表单新字段
                sysNewInfo.setBm(getSysByFiled("departmentname", "hrmdepartment", bm)); // 部门
                sysNewInfo.setGw(getSysByFiled("jobtitlename", "hrmjobtitles", gw)); // 岗位
                // 找出变动的对象
                List<JtDifferent> sysDifferentList = JiaJieConnUtil.compareObj(sysOldInfo, sysNewInfo);
                for (JtDifferent different : sysDifferentList) {
                    insertPerCord(xm, requestId, zdMap.get(different.getFieldId()), different.getBeforeValue(), different.getAlterValue());
                }
                // 更新系统表
                sysSet.executeUpdate("update hrmresource set departmentid = ?, subcompanyid1 = ?, jobtitle = ? where id = ?",
                        bm, fb, gw, xm);
                this.writeLog("操作hrmresource表结束===============");

                // 自定义表部分 ====================
                RecordSet zdySet = new RecordSet();
                String gwlx = recordSet.getString("gwlx"); // 岗位类型
                String zj = recordSet.getString("zj"); // 职级
                String bgdd = recordSet.getString("bgdd"); // 办公地点
                String wxyj = recordSet.getString("wxyjjnd"); // 五险一金缴纳地
                String htqszt = recordSet.getString("htqszt"); // 劳动合同签署主体

                String bpsspr = recordSet.getString("bpsspr"); // BPS审批人
                String cwou = recordSet.getString("cwou"); // 财务OU

                this.writeLog("职级: " + zj + ", 岗位类型: " + gwlx + ", 办公地点: " + bgdd + ", 财务OU: " + cwou + ", 劳动合同签署主体: " + htqszt);
                this.writeLog("BPS审批人: " + bpsspr + ", 五险一金缴纳地: " + wxyj);

                JtChangeVo oldChangeVo = getZdyOldInfo(xm);
                JtChangeVo newChangeVo = new JtChangeVo();
                newChangeVo.setGwlx(getGgxzk(JiaJieConfigInfo.GWLX_SEL.getValue(), gwlx));
                newChangeVo.setZz(getGgxzk(JiaJieConfigInfo.ZHI_JI_SEL.getValue(), zj));
                newChangeVo.setBgdd(getSysByFiled("bgdd", "UF_JTBGDD", bgdd));
                newChangeVo.setWxyj(getSysByFiled("bgdd", "UF_JTBGDD", wxyj));
                newChangeVo.setLdhtqs(getGgxzk(JiaJieConfigInfo.LDHT_SEL.getValue(), htqszt));

                newChangeVo.setBps(getSysByFiled("lastname", "hrmresource", bpsspr));
                newChangeVo.setCwou(getGgxzk(JiaJieConfigInfo.CWOU_SEL.getValue(), cwou));
                newChangeVo.setDdrq(sxrq);

                List<JtDifferent> zdyDifferentList = JiaJieConnUtil.compareObj(oldChangeVo, newChangeVo);
                for (JtDifferent different : zdyDifferentList) {
                    insertPerCord(xm, requestId, zdMap.get(different.getFieldId()), different.getBeforeValue(), different.getAlterValue());
                }

                // 插入自定义表三条数据的基本字段
                JiaJieConnUtil.insertBaseCus(Integer.parseInt(xm));
                // 更新
                String updateSql = "update CUS_FIELDDATA set " + JiaJieConfigInfo.GWLX.getValue() + " = ?, " + JiaJieConfigInfo.ZHI_JI.getValue() + " = ?, "
                        + JiaJieConfigInfo.BGDD.getValue() + " = ?, " + JiaJieConfigInfo.WXYJ.getValue() + " = ?," + JiaJieConfigInfo.LDHT.getValue() + " = ?, "
                        + JiaJieConfigInfo.BPS.getValue() + " = ?, " + JiaJieConfigInfo.CWOU.getValue() + " = ?, " + JiaJieConfigInfo.DIAO_DONG_RI_QI.getValue() + " = ? "
                        + " where id = ?";
                zdySet.executeUpdate(updateSql,
                        gwlx, zj,
                        bgdd, wxyj, htqszt,
                        bpsspr, cwou, sxrq,
                        xm);
                this.writeLog("更新自定义表结束============");
            }

            this.writeLog("群体任免流程 End ===============");
        } catch (Exception e) {
            this.writeLog("群体任免流程 异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("群体任免流程 异常： " + e);
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
        jtChangeVo.setBm(getSysByFiled("departmentname", "hrmdepartment", recordSet.getString("departmentid"))); // 部门
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
        String gwlx = ""; // 岗位类型
        String zz = ""; // 职级
        String bgdd = ""; // 办公地点
        String wxyj = ""; //  五险一金缴纳地
        String ldhtqs = ""; // 劳动合同签署主体

        String bps = ""; // BPS审批人
        String cwou = ""; // 财务OU
        String ddrq = ""; // 调动日期
        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("select * from CUS_FIELDDATA where id = '" + userId + "'");
        while (recordSet.next()) {
            if (!"".equals(recordSet.getString(JiaJieConfigInfo.GWLX.getValue()))) {
                gwlx = recordSet.getString(JiaJieConfigInfo.GWLX.getValue());
            }
            if (!"".equals(recordSet.getString(JiaJieConfigInfo.ZHI_JI.getValue()))) {
                zz = recordSet.getString(JiaJieConfigInfo.ZHI_JI.getValue());
            }
            if (!"".equals(recordSet.getString(JiaJieConfigInfo.BGDD.getValue()))) {
                bgdd = recordSet.getString(JiaJieConfigInfo.BGDD.getValue());
            }
            if (!"".equals(recordSet.getString(JiaJieConfigInfo.WXYJ.getValue()))) {
                wxyj = recordSet.getString(JiaJieConfigInfo.WXYJ.getValue());
            }
            if (!"".equals(recordSet.getString(JiaJieConfigInfo.LDHT.getValue()))) {
                ldhtqs = recordSet.getString(JiaJieConfigInfo.LDHT.getValue());
            }
            if (!"".equals(recordSet.getString(JiaJieConfigInfo.BPS.getValue()))) {
                bps = recordSet.getString(JiaJieConfigInfo.BPS.getValue());
            }
            if (!"".equals(recordSet.getString(JiaJieConfigInfo.CWOU.getValue()))) {
                cwou = recordSet.getString(JiaJieConfigInfo.CWOU.getValue());
            }
            if (!"".equals(recordSet.getString(JiaJieConfigInfo.DIAO_DONG_RI_QI.getValue()))) {
                ddrq = recordSet.getString(JiaJieConfigInfo.DIAO_DONG_RI_QI.getValue());
            }
        }
        JtChangeVo jtChangeVo = new JtChangeVo();

        jtChangeVo.setGwlx(getGgxzk(JiaJieConfigInfo.GWLX_SEL.getValue(), gwlx));
        jtChangeVo.setZz(getGgxzk(JiaJieConfigInfo.ZHI_JI_SEL.getValue(), zz));
        jtChangeVo.setBgdd(getSysByFiled("bgdd", "UF_JTBGDD", bgdd));
        jtChangeVo.setWxyj(getSysByFiled("bgdd", "UF_JTBGDD", wxyj));
        jtChangeVo.setLdhtqs(getGgxzk(JiaJieConfigInfo.LDHT_SEL.getValue(), ldhtqs));

        jtChangeVo.setBps(getSysByFiled("lastname", "hrmresource", bps));
        jtChangeVo.setCwou(getGgxzk(JiaJieConfigInfo.CWOU_SEL.getValue(), cwou));
        jtChangeVo.setDdrq(ddrq);
        return jtChangeVo;
    }
}
