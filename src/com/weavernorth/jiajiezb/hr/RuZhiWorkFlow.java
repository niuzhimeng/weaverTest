package com.weavernorth.jiajiezb.hr;

import com.weavernorth.jiajiezb.hr.util.JiaJieConfigInfo;
import com.weavernorth.jiajiezb.hr.util.JiaJieConnUtil;
import com.weavernorth.jiajiezb.hr.vo.JiajieHrmResource;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.hrm.resource.ResourceComInfo;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 员工入职流程
 */
public class RuZhiWorkFlow extends BaseAction {

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
        this.writeLog("员工入职流程 Start requestid=" + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {
            // 查询主表
            recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");
            recordSet.next();
            String lb = recordSet.getString("lb"); // 类别
            // 系统表部分 ====================
            String xmStr = recordSet.getString("xm"); // 姓名
            String ygbh = recordSet.getString("ygbh"); // 员工编号
            String ygzt = statusChange(recordSet.getString("ygzt")); // 员工状态
            String gw = recordSet.getString("gw"); // 岗位
            String zsld = recordSet.getString("zsld"); // 直接上级

            String bm = recordSet.getString("bm"); // 部门
            String fb = getSysByFiled("subcompanyid1", "hrmdepartment", bm); // 分部
            String syjsrq = recordSet.getString("syjsrq"); // 试用期结束日期
            String xb = recordSet.getString("xb"); // 性别
            xb = "0".equals(xb) ? "1" : "0";

            JiajieHrmResource jiajieHrmResource = new JiajieHrmResource();
            jiajieHrmResource.setLoginid("");
            jiajieHrmResource.setDepId(bm);
            jiajieHrmResource.setSubId(fb);
            jiajieHrmResource.setStatusOa(ygzt);
            jiajieHrmResource.setSex(xb);

            jiajieHrmResource.setLoginid("");
            jiajieHrmResource.setEmail("");
            jiajieHrmResource.setMobile("");
            jiajieHrmResource.setManagerIdReal(zsld);
            jiajieHrmResource.setJobtitleId(gw);

            jiajieHrmResource.setProbationenddate(syjsrq);
            jiajieHrmResource.setWorkcode(ygbh);
            jiajieHrmResource.setLastname(xmStr);
            //账号类型
            jiajieHrmResource.setAccounttype("0");
            //语言类型
            jiajieHrmResource.setSystemlanguage("7");
            // 安全级别默认10
            jiajieHrmResource.setSeclevel("0");

            String xm = ""; // 人员id
            RecordSet sysSet = new RecordSet();
            sysSet.executeQuery("select id from hrmresource where workcode = '" + ygbh + "'");
            if (sysSet.next()) {
                this.writeLog("工号已存在===============" + ygbh);
                requestInfo.getRequestManager().setMessageid("110000");
                requestInfo.getRequestManager().setMessagecontent("工号已存在===============" + ygbh);
                return "0";
            } else {
                this.writeLog("插入hrmresource表===============");
                xm = String.valueOf(getHrmMaxId());
                jiajieHrmResource.setId(xm);
                insertHrmResource(jiajieHrmResource);
            }
            this.writeLog("操作hrmresource表结束===============" + jiajieHrmResource.toString());

            // 自定义表部分 ====================
            RecordSet zdySet = new RecordSet();
            String zj = recordSet.getString("zj"); // 职级
            String syqdz = recordSet.getString("syqdz"); // 试用期是否打折

            String gwlx = ""; // 岗位类型
            String bgdd = ""; // 办公地点
            String cwou = ""; // 财务OU
            String ldhtqszt = ""; // 劳动合同签署主体
            String htcfd = ""; // 合同存放地

            String bpsspr = recordSet.getString("bpsspr"); // BPS审批人
            String wxyj = recordSet.getString("wxyjjnd"); // 五险一金缴纳地
            String qykssj = recordSet.getString("qykssj"); // 劳动合同签约开始时间
            String qyjssj = recordSet.getString("qyjssj"); // 劳动合同签约结束时间
            String ypsqb = recordSet.getString("ypsqb"); // 应聘申请表

            String mspj = recordSet.getString("mspj"); // 面试评价表

            if ("0".equals(lb)) {
                // 经营类（业务）
                gwlx = recordSet.getString("gwlxyw");
                bgdd = recordSet.getString("bgddyw");
                cwou = recordSet.getString("cwouyw");
                ldhtqszt = recordSet.getString("ldhtqsztyw");
                htcfd = recordSet.getString("htcfdyw");
            } else {
                // 管理类（职能）
                gwlx = recordSet.getString("gwlxzn");
                bgdd = recordSet.getString("bgddzn");
                cwou = recordSet.getString("cwouzn");
                ldhtqszt = recordSet.getString("ldhtqsztzn");
                htcfd = recordSet.getString("htcfdzn");
            }
            this.writeLog("类别：" + lb + ", 职级: " + zj + ", 岗位类型: " + gwlx + ", 办公地点: " + bgdd + ", 财务OU: " + cwou + ", 劳动合同签署主体: " + ldhtqszt);
            this.writeLog("BPS审批人: " + bpsspr + ", 五险一金缴纳地: " + wxyj + ", 劳动合同签约开始时间: " + qykssj +
                    ", 劳动合同签约结束时间: " + qyjssj + ", 应聘申请表: " + ypsqb + ", 合同存放地: " + htcfd + ", 试用期是否打折: " + syqdz +
                    ", 面试评价表: " + mspj);

            // 插入自定义表三条数据的基本字段
            JiaJieConnUtil.insertBaseCus(Integer.parseInt(xm));
            // 更新
            String updateSql = "update CUS_FIELDDATA set " + JiaJieConfigInfo.ZHI_JI.getValue() + " = ?, " + JiaJieConfigInfo.GWLX.getValue() + " = ?, "
                    + JiaJieConfigInfo.BGDD.getValue() + " = ?, " + JiaJieConfigInfo.CWOU.getValue() + " = ?," + JiaJieConfigInfo.LDHT.getValue() + " = ?, "
                    + JiaJieConfigInfo.BPS.getValue() + " = ?, " + JiaJieConfigInfo.WXYJ.getValue() + " = ?, " + JiaJieConfigInfo.QYKSRQ.getValue() + " = ?, "
                    + JiaJieConfigInfo.QYJSRQ.getValue() + " = ?, " + JiaJieConfigInfo.YPSQB.getValue() + " = '" + ypsqb + "', " + JiaJieConfigInfo.RZRQ.getValue() + " = ?, "
                    + JiaJieConfigInfo.HTCFD.getValue() + " = ?, " + JiaJieConfigInfo.SYQSFDZ.getValue() + " =?, " + JiaJieConfigInfo.MSPJB.getValue() + " = '" + mspj + "' where id = ?";
            this.writeLog("入职流程更新自定义表sql: " + updateSql);
            zdySet.executeUpdate(updateSql,
                    zj, gwlx,
                    bgdd, cwou, ldhtqszt,
                    bpsspr, wxyj, qykssj,
                    qyjssj, qykssj,
                    htcfd, syqdz, xm);
            this.writeLog("更新自定义表结束============");

            //清除缓存
            new ResourceComInfo().removeResourceCache();

            // 建模表部分 ====================
            String jbgz1 = recordSet.getString("jbgz1"); // 基本工资
            String jjbl = recordSet.getString("jjbl"); // 奖金比例
            String sbjs = recordSet.getString("sbjs"); // 社保基数
            String nsr = recordSet.getString("nsr1"); // 年收入
            String jjbz = recordSet.getString("jjbz1"); // 奖金标准
            this.writeLog("基本工资: " + jbgz1 + ", 奖金比例: " + jjbl + ", 社保基数: " + sbjs +
                    ", 年收入: " + nsr + ", 奖金标准： " + jjbz);

            recordSet.executeQuery("select * from uf_jtxz where xm = '" + xm + "'");
            RecordSet updateSet = new RecordSet();
            if (recordSet.next()) {
                this.writeLog("更新建模========");
                updateSet.executeUpdate("update uf_jtxz set jbgz = ?, jjbl = ?, sbjs = ?, nsr = ?, jjbzyd = ? where xm = ?",
                        jbgz1, jjbl, sbjs, nsr, jjbz,
                        xm);
            } else {
                this.writeLog("新增建模========");
                updateSet.executeUpdate("insert into uf_jtxz(xm, ygbh, bm, jbgz, jjbl, sbjs, nsr, jjbzyd, " +
                                "formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime)" +
                                " values(?,?,?,?,?, ?,?,?, ?,?,?,?,?)",
                        xm, ygbh, bm, jbgz1, jjbl, sbjs, nsr, jjbz,
                        JiaJieConfigInfo.XZ_MODE_ID.getValue(), "1", "0", detailCurrentTimeString.substring(0, 10), detailCurrentTimeString.substring(11));

                JiaJieConnUtil.fuQuan(detailCurrentTimeString, "uf_jtxz", Integer.parseInt(JiaJieConfigInfo.XZ_MODE_ID.getValue()));
            }


            this.writeLog("员工入职流程 End ===============");
        } catch (Exception e) {
            this.writeLog("员工入职流程 异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("员工入职流程 异常： " + e);
            return "0";
        }

        return "1";
    }

    private void insertHrmResource(JiajieHrmResource hrmResource) {
        ConnStatement statement = new ConnStatement();
        try {
            String sql = "insert into hrmresource (workcode, lastname, loginid, status, sex," +
                    " locationid, email, mobile, managerid, seclevel, " +
                    "departmentid, subcompanyid1, jobtitle, dsporder, id," +
                    "password, accounttype, belongto, systemlanguage, telephone, " +
                    "probationenddate) " +
                    "values (?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?, ?)";
            statement.setStatementSql(sql);

            statement.setString(1, hrmResource.getWorkcode());
            statement.setString(2, hrmResource.getLastname());
            statement.setString(3, hrmResource.getLoginid());
            statement.setString(4, hrmResource.getStatusOa());
            statement.setString(5, hrmResource.getSex());

            statement.setString(6, hrmResource.getLocationId());
            statement.setString(7, hrmResource.getEmail());
            statement.setString(8, hrmResource.getMobile());
            statement.setString(9, hrmResource.getManagerIdReal());
            statement.setString(10, hrmResource.getSeclevel());

            statement.setString(11, hrmResource.getDepId());
            statement.setString(12, hrmResource.getSubId());
            statement.setString(13, hrmResource.getJobtitleId());
            statement.setString(14, hrmResource.getDsporder());
            statement.setString(15, hrmResource.getId());

            statement.setString(16, hrmResource.getPassWord());
            statement.setString(17, hrmResource.getAccounttype());
            statement.setString(18, hrmResource.getBelongto());
            statement.setString(19, hrmResource.getSystemlanguage());
            statement.setString(20, hrmResource.getTelephone());

            statement.setString(21, hrmResource.getProbationenddate());

            statement.executeUpdate();
            hrmResource.updaterights(hrmResource.getId());

        } catch (Exception e) {
            new BaseBean().writeLog("insert HrmResource Exception :" + e);
        } finally {
            statement.close();
        }
    }

    private int getHrmMaxId() {
        int maxID = 1;
        RecordSet rs = new RecordSet();
        try {
            rs.executeProc("HrmResourceMaxId_Get", "");
            if (rs.next()) {
                maxID = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maxID;
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

    private String statusChange(String status) {
        String returnStatus = "";
        if ("0".equals(status)) {
            // 试用期
            returnStatus = "0";
        } else if ("1".equals(status)) {
            // 正式员工
            returnStatus = "1";
        } else if ("2".equals(status)) {
            // 实习生
            returnStatus = "2";
        }

        return returnStatus;
    }
}
