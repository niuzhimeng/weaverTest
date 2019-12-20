package com.weavernorth.jiajiezb.hr;

import com.weavernorth.jiajiezb.hr.util.JiaJieConfigInfo;
import com.weavernorth.jiajiezb.hr.util.JiaJieConnUtil;
import com.weavernorth.jiajiezb.hr.vo.JiajieHrmResource;
import com.weavernorth.jiajiezb.hr.vo.JtDifferent;
import com.weavernorth.jiajiezb.hr.vo.ShiXiShengVo;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.hrm.resource.ResourceComInfo;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实习生转正流程
 */
public class ShiXiShengZziWorkFlow extends BaseAction {

    private RecordSet connSet = new RecordSet();
    private RecordSet updateSet = new RecordSet();
    private static Map<String, String> zdMap = new HashMap<String, String>();

    static {
        // 系统表字段
        zdMap.put("ygbh", "员工编号");
        zdMap.put("gw", "岗位");
        zdMap.put("zsld", "直属领导");
        zdMap.put("bm", "部门");
        zdMap.put("syqjsrq", "试用期结束日期");
        // 自定义表字段
        zdMap.put("zz", "职级");
        zdMap.put("gwlx", "岗位类型");
        zdMap.put("bps", "BPS审批人");
        zdMap.put("bgdd", "办公地点");
        zdMap.put("wxyj", "五险一金缴纳地");

        zdMap.put("cwou", "财务OU");
        zdMap.put("rzrq", "入职日期");
        zdMap.put("ldhtqs", "劳动合同签署主体");
        zdMap.put("ldhtksrq", "劳动合同签约开始日期");
        zdMap.put("ldhtjsrq", "劳动合同签约结束日期");

        zdMap.put("syqdz", "试用期是否打折");
        // 建模字段
        zdMap.put("jbgz", "基本工资");
        zdMap.put("jjbl", "奖金比例");
        zdMap.put("sbjs", "社保基数");
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
        String detailCurrentTimeString = TimeUtil.getCurrentTimeString();
        this.writeLog("实习生转正流程 Start requestid=" + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {
            // 查询主表
            recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");
            recordSet.next();

            // 系统表部分 ====================
            String xm = recordSet.getString("xm"); // 姓名
            String ygbh = recordSet.getString("ygbh"); // 员工编号
            String gw = recordSet.getString("gw"); // 岗位
            String zsld = recordSet.getString("zsld"); // 直接上级
            String bm = recordSet.getString("bm"); // 部门

            String fb = getSysByFiled("subcompanyid1", "hrmdepartment", bm); // 分部
            String syqjs = recordSet.getString("syqjs"); // 试用期结束日期

            JiajieHrmResource jiajieHrmResource = new JiajieHrmResource();
            jiajieHrmResource.setId(xm);
            jiajieHrmResource.setStatusOa("0"); // 试用
            jiajieHrmResource.setWorkcode(ygbh);
            jiajieHrmResource.setJobtitleId(gw);
            jiajieHrmResource.setManagerIdReal(zsld);

            jiajieHrmResource.setDepId(bm);
            jiajieHrmResource.setSubId(fb);
            jiajieHrmResource.setProbationenddate(syqjs);

            // 插入变更记录表
            ShiXiShengVo sysOldInfo = getSysOldInfo(xm); // 获取系统表旧字段
            ShiXiShengVo sysNewInfo = new ShiXiShengVo();
            sysNewInfo.setYgbh(ygbh); // 员工编号
            sysNewInfo.setGw(getSysByFiled("jobtitlename", "hrmjobtitles", gw)); // 岗位
            sysNewInfo.setZsld(getSysByFiled("lastname", "hrmresource", zsld)); // 直属领导
            sysNewInfo.setBm(getSysByFiled("departmentname", "hrmdepartment", bm)); // 部门
            sysNewInfo.setSyqjsrq(syqjs); // 试用期结束日期
            // 找出变动的对象
            List<JtDifferent> sysDifferentList = JiaJieConnUtil.compareObj(sysOldInfo, sysNewInfo);
            for (JtDifferent different : sysDifferentList) {
                insertPerCord(xm, requestId, zdMap.get(different.getFieldId()), different.getBeforeValue(), different.getAlterValue());
            }
            // 跟新系统表
            updateHrmResource(jiajieHrmResource);
            this.writeLog("操作hrmresource表结束===============" + jiajieHrmResource.toString());

            // 自定义表部分 ====================
            RecordSet zdySet = new RecordSet();
            String zj = recordSet.getString("zj"); // 职级
            String gwlx = recordSet.getString("gwlx"); // 岗位类型
            String bpsspr = recordSet.getString("bpsspr"); // BPS审批人
            String bgdd = recordSet.getString("bgdd"); // 办公地点
            String wxyj = recordSet.getString("wxyj"); // 五险一金缴纳地

            String cwou = recordSet.getString("cwou"); // 财务OU
            String rzrq = recordSet.getString("rzrq"); // 入职日期
            String htqszt = recordSet.getString("htqszt"); // 劳动合同签署主体
            String htksrq = recordSet.getString("htksrq"); // 劳动合同签约开始时间
            String htjsrq = recordSet.getString("htjsrq"); // 劳动合同签约结束时间

            String syqbdz = recordSet.getString("syqbdz"); // 特殊情况-试用期不打折

            this.writeLog("职级: " + zj + ", 岗位类型: " + gwlx + ", 办公地点: " + bgdd + ", 财务OU: " + cwou + ", 劳动合同签署主体: " + htqszt);
            this.writeLog("BPS审批人: " + bpsspr + ", 五险一金缴纳地: " + wxyj + ", 劳动合同签约开始时间: " + htksrq +
                    ", 劳动合同签约结束时间: " + htjsrq + ", 入职日期: " + rzrq + ", 试用期是否打折: " + syqbdz);

            // 找出变动的对象
            ShiXiShengVo zdyOldInfo = getZdyOldInfo(xm); // 获取自定义表旧字段
            ShiXiShengVo zdyNewInfo = new ShiXiShengVo(); // 自定义新对象
            zdyNewInfo.setZj(getGgxzk(JiaJieConfigInfo.ZHI_JI_SEL.getValue(), zj));
            zdyNewInfo.setGwlx(getGgxzk(JiaJieConfigInfo.GWLX_SEL.getValue(), gwlx));
            zdyNewInfo.setBps(getSysByFiled("lastname", "hrmresource", bpsspr));
            zdyNewInfo.setBgdd(getSysByFiled("bgdd", "UF_JTBGDD", bgdd));
            zdyNewInfo.setWxyj(getSysByFiled("bgdd", "UF_JTBGDD", wxyj));

            zdyNewInfo.setCwou(getGgxzk(JiaJieConfigInfo.CWOU_SEL.getValue(), cwou));
            zdyNewInfo.setRzrq(rzrq);
            zdyNewInfo.setLdhtqs(getGgxzk(JiaJieConfigInfo.LDHT_SEL.getValue(), htqszt));
            zdyNewInfo.setLdhtksrq(htksrq);
            zdyNewInfo.setLdhtjsrq(htjsrq);

            zdyNewInfo.setSyqdz(JiaJieConnUtil.yesOrNoChange(syqbdz));

            List<JtDifferent> zdyDifferentList = JiaJieConnUtil.compareObj(zdyOldInfo, zdyNewInfo);
            for (JtDifferent different : zdyDifferentList) {
                insertPerCord(xm, requestId, zdMap.get(different.getFieldId()), different.getBeforeValue(), different.getAlterValue());
            }

            // 插入自定义表三条数据的基本字段
            JiaJieConnUtil.insertBaseCus(Integer.parseInt(xm));
            // 更新
            String updateSql = "update CUS_FIELDDATA set " + JiaJieConfigInfo.ZHI_JI.getValue() + " = ?, " + JiaJieConfigInfo.GWLX.getValue() + " = ?, "
                    + JiaJieConfigInfo.BPS.getValue() + " = ?, " + JiaJieConfigInfo.BGDD.getValue() + " = ?," + JiaJieConfigInfo.WXYJ.getValue() + " = ?, "
                    + JiaJieConfigInfo.CWOU.getValue() + " = ?, " + JiaJieConfigInfo.RZRQ.getValue() + " = ?, " + JiaJieConfigInfo.LDHT.getValue() + " = ?, "
                    + JiaJieConfigInfo.QYKSRQ.getValue() + " = ?, " + JiaJieConfigInfo.QYJSRQ.getValue() + " = ?, " + JiaJieConfigInfo.SYQSFDZ.getValue() + " = ? where id = ?";
            this.writeLog("入职流程更新自定义表sql: " + updateSql);
            zdySet.executeUpdate(updateSql,
                    zj, gwlx,
                    bpsspr, bgdd, wxyj,
                    cwou, rzrq, htqszt,
                    htksrq, htjsrq, syqbdz, xm);
            this.writeLog("更新自定义表结束============");

            //清除缓存
            new ResourceComInfo().removeResourceCache();

            // 建模表部分 ====================
            String xzjbgz = recordSet.getString("xzjbgz"); // 基本工资
            String jjbl = recordSet.getString("jjbl"); // 奖金比例
            String sbjs = recordSet.getString("sbjs"); // 社保基数
            this.writeLog("基本工资: " + xzjbgz + ", 奖金比例: " + jjbl + ", 社保基数: " + sbjs);

            // 找出变动的对象
            ShiXiShengVo modeOldInfo = getModeOldInfo(xm); // 获取建模表旧字段
            ShiXiShengVo modeNewInfo = new ShiXiShengVo(); // 建模新对象
            modeNewInfo.setJbgz(xzjbgz);
            modeNewInfo.setJjbl(jjbl);
            modeNewInfo.setSbjs(sbjs);
            List<JtDifferent> differentList = JiaJieConnUtil.compareObj(modeOldInfo, modeNewInfo);
            for (JtDifferent different : differentList) {
                insertPerCord(xm, requestId, zdMap.get(different.getFieldId()), different.getBeforeValue(), different.getAlterValue());
            }

            recordSet.executeQuery("select * from uf_jtxz where xm = '" + xm + "'");
            RecordSet updateSet = new RecordSet();
            if (recordSet.next()) {
                this.writeLog("更新建模========");
                updateSet.executeUpdate("update uf_jtxz set jbgz = ?, jjbl = ?, sbjs = ? where xm = ?", xzjbgz, jjbl, sbjs, xm);
            } else {
                this.writeLog("新增建模========");
                updateSet.executeUpdate("insert into uf_jtxz(xm, ygbh, bm, jbgz, jjbl, sbjs, " +
                                "formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime)" +
                                " values(?,?,?,?,?, ?, ?,?,?,?,?)",
                        xm, ygbh, bm, xzjbgz, jjbl, sbjs,
                        JiaJieConfigInfo.XZ_MODE_ID.getValue(), "1", "0", detailCurrentTimeString.substring(0, 10), detailCurrentTimeString.substring(11));
                JiaJieConnUtil.fuQuan(detailCurrentTimeString, "uf_jtxz", Integer.parseInt(JiaJieConfigInfo.XZ_MODE_ID.getValue()));
            }

            this.writeLog("实习生转正流程 End ===============");
        } catch (Exception e) {
            this.writeLog("实习生转正流程 异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("实习生转正流程 异常： " + e);
            return "0";
        }

        return "1";
    }

    /**
     * 查询自定义表变更前数据
     */
    private ShiXiShengVo getZdyOldInfo(String userId) {
        String zz = ""; // 职级
        String gwlx = ""; // 岗位类型
        String bps = ""; // BPS审批人
        String bgdd = ""; // 办公地点
        String wxyj = ""; //  五险一金缴纳地

        String cwou = ""; // 财务OU
        String rzrq = ""; // 入职日期
        String ldhtqs = ""; // 劳动合同签署主体
        String ldhtksrq = ""; // 劳动合同签约开始日期
        String ldhtjsrq = ""; // 劳动合同签约结束日期

        String syqsfdz = ""; // 试用期是否打折
        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("select * from CUS_FIELDDATA where id = '" + userId + "'");
        while (recordSet.next()) {
            if (!"".equals(recordSet.getString(JiaJieConfigInfo.ZHI_JI.getValue()))) {
                zz = recordSet.getString(JiaJieConfigInfo.ZHI_JI.getValue());
            }
            if (!"".equals(recordSet.getString(JiaJieConfigInfo.GWLX.getValue()))) {
                gwlx = recordSet.getString(JiaJieConfigInfo.GWLX.getValue());
            }
            if (!"".equals(recordSet.getString(JiaJieConfigInfo.BPS.getValue()))) {
                bps = recordSet.getString(JiaJieConfigInfo.BPS.getValue());
            }
            if (!"".equals(recordSet.getString(JiaJieConfigInfo.BGDD.getValue()))) {
                bgdd = recordSet.getString(JiaJieConfigInfo.BGDD.getValue());
            }
            if (!"".equals(recordSet.getString(JiaJieConfigInfo.WXYJ.getValue()))) {
                wxyj = recordSet.getString(JiaJieConfigInfo.WXYJ.getValue());
            }

            if (!"".equals(recordSet.getString(JiaJieConfigInfo.CWOU.getValue()))) {
                cwou = recordSet.getString(JiaJieConfigInfo.CWOU.getValue());
            }
            if (!"".equals(recordSet.getString(JiaJieConfigInfo.RZRQ.getValue()))) {
                rzrq = recordSet.getString(JiaJieConfigInfo.RZRQ.getValue());
            }
            if (!"".equals(recordSet.getString(JiaJieConfigInfo.LDHT.getValue()))) {
                ldhtqs = recordSet.getString(JiaJieConfigInfo.LDHT.getValue());
            }
            if (!"".equals(recordSet.getString(JiaJieConfigInfo.QYKSRQ.getValue()))) {
                ldhtksrq = recordSet.getString(JiaJieConfigInfo.QYKSRQ.getValue());
            }
            if (!"".equals(recordSet.getString(JiaJieConfigInfo.QYJSRQ.getValue()))) {
                ldhtjsrq = recordSet.getString(JiaJieConfigInfo.QYJSRQ.getValue());
            }
            if (!"".equals(recordSet.getString(JiaJieConfigInfo.SYQSFDZ.getValue()))) {
                syqsfdz = recordSet.getString(JiaJieConfigInfo.SYQSFDZ.getValue());
            }
        }
        ShiXiShengVo shiXiShengVo = new ShiXiShengVo();
        shiXiShengVo.setZj(getGgxzk(JiaJieConfigInfo.ZHI_JI_SEL.getValue(), zz));
        shiXiShengVo.setGwlx(getGgxzk(JiaJieConfigInfo.GWLX_SEL.getValue(), gwlx));
        shiXiShengVo.setBps(getSysByFiled("lastname", "hrmresource", bps));
        shiXiShengVo.setBgdd(getSysByFiled("bgdd", "UF_JTBGDD", bgdd));
        shiXiShengVo.setWxyj(getSysByFiled("bgdd", "UF_JTBGDD", wxyj));

        shiXiShengVo.setCwou(getGgxzk(JiaJieConfigInfo.CWOU_SEL.getValue(), cwou));
        shiXiShengVo.setRzrq(rzrq);
        shiXiShengVo.setLdhtqs(getGgxzk(JiaJieConfigInfo.LDHT_SEL.getValue(), ldhtqs));
        shiXiShengVo.setLdhtksrq(ldhtksrq);
        shiXiShengVo.setLdhtjsrq(ldhtjsrq);

        shiXiShengVo.setSyqdz(JiaJieConnUtil.yesOrNoChange(syqsfdz));
        return shiXiShengVo;
    }

    /**
     * 查询建模表变更前数据
     */
    private ShiXiShengVo getModeOldInfo(String userId) {
        String jbgz = ""; // 基本工资
        String jjbl = ""; // 奖金比例
        String sbjs = ""; // 社保基数
        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("select * from uf_jtxz where xm = '" + userId + "'");
        if (recordSet.next()) {
            jbgz = recordSet.getString("jbgz");
            jjbl = recordSet.getString("jjbl");
            sbjs = recordSet.getString("sbjs");
        }

        ShiXiShengVo shiXiShengVo = new ShiXiShengVo();
        shiXiShengVo.setJbgz(jbgz);
        shiXiShengVo.setJjbl(jjbl);
        shiXiShengVo.setSbjs(sbjs);
        return shiXiShengVo;
    }

    /**
     * 查询系统表变更前数据
     */
    private ShiXiShengVo getSysOldInfo(String userId) {
        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("select * from hrmresource where id = '" + userId + "'");
        recordSet.next();

        ShiXiShengVo shiXiShengVo = new ShiXiShengVo();
        shiXiShengVo.setYgbh(recordSet.getString("workcode")); // 员工编号
        shiXiShengVo.setGw(getSysByFiled("jobtitlename", "hrmjobtitles", recordSet.getString("jobtitle"))); // 岗位
        shiXiShengVo.setZsld(getSysByFiled("lastname", "hrmresource", recordSet.getString("managerid"))); // 直属领导
        shiXiShengVo.setBm(getSysByFiled("departmentname", "hrmdepartment", recordSet.getString("departmentid"))); // 部门
        shiXiShengVo.setSyqjsrq(recordSet.getString("probationenddate")); // 试用期结束日期
        return shiXiShengVo;
    }

    private void updateHrmResource(JiajieHrmResource hrmResource) {
        ConnStatement statement = new ConnStatement();
        try {
            String sql = "update hrmresource set workcode = ?, status = ?, jobtitle = ?, managerid = ?, probationenddate = ?, " +
                    "departmentid = ?, subcompanyid1 = ? where id = ?";
            statement.setStatementSql(sql);
            statement.setString(1, hrmResource.getWorkcode());
            statement.setString(2, hrmResource.getStatusOa());
            statement.setString(3, hrmResource.getJobtitleId());
            statement.setString(4, hrmResource.getManagerIdReal());
            statement.setString(5, hrmResource.getProbationenddate());

            statement.setString(6, hrmResource.getDepId());
            statement.setString(7, hrmResource.getSubId());
            statement.setString(8, hrmResource.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            new BaseBean().writeLog("update HrmResource Exception :" + e);
        } finally {
            statement.close();
        }
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
}
