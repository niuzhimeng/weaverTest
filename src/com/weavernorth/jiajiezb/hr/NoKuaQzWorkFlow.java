package com.weavernorth.jiajiezb.hr;

import com.weavernorth.jiajiezb.hr.util.JiaJieConfigInfo;
import com.weavernorth.jiajiezb.hr.util.JiaJieConnUtil;
import com.weavernorth.jiajiezb.hr.vo.JtChangeVo;
import com.weavernorth.jiajiezb.hr.vo.JtDifferent;
import weaver.conn.RecordSet;
import weaver.general.Util;
import weaver.hrm.resource.ResourceComInfo;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

import java.util.List;
import java.util.Map;

/**
 * 不跨群组流程
 */
public class NoKuaQzWorkFlow extends BaseAction {

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

        this.writeLog("不跨群组流程 Start requestid=" + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {
            // 字段中文名map
            Map<String, String> zdMap = JiaJieConnUtil.zdMap;
            String ddrqFiled = JiaJieConfigInfo.DIAO_DONG_RI_QI.getValue();
            String gwlxFiled = JiaJieConfigInfo.GWLX.getValue();
            String zjFiled = JiaJieConfigInfo.ZHI_JI.getValue();

            String bgddFiled = JiaJieConfigInfo.BGDD.getValue();
            String wxyjFiled = JiaJieConfigInfo.WXYJ.getValue();
            String ldhtFiled = JiaJieConfigInfo.LDHT.getValue();
            String bpsFiled = JiaJieConfigInfo.BPS.getValue();
            String cwouFiled = JiaJieConfigInfo.CWOU.getValue();

            String htcfdFiled = JiaJieConfigInfo.HTCFD.getValue();
            String mspjbFiled = JiaJieConfigInfo.MSPJB.getValue();

            // 查询主表
            recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");
            recordSet.next();
            String xm = recordSet.getString("xm");
            String drbm = Util.null2String(recordSet.getString("drbm")); // 调入部门
            String drgw = Util.null2String(recordSet.getString("drgw")); // 调入岗位
            String ddrq = Util.null2String(recordSet.getString("ddrq")); // 调动日期
            String drgwlx = Util.null2String(recordSet.getString("drgwlx")); // 调入-岗位类型
            String drzj = Util.null2String(recordSet.getString("drzj")); // 调入-职级

            String drdd = Util.null2String(recordSet.getString("drdd")); // 调入-办公地点
            String drwxyj = Util.null2String(recordSet.getString("drwxyj")); // 调入-五险一金缴纳地
            String drzt = Util.null2String(recordSet.getString("drzt")); // 调入-劳动合同签署主体
            String drbpsspr = Util.null2String(recordSet.getString("drbpsspr")); // 调入-BPS审批人
            String drcwou = Util.null2String(recordSet.getString("drcwou")); // 调入-财务OU

            String drhtcfd = Util.null2String(recordSet.getString("drhtcfd")); // 调入-合同存放地
            String mspjOld = Util.null2String(recordSet.getString("dcmspjb")).trim(); // 面试评价表-调出
            String mspjNew = Util.null2String(recordSet.getString("mspj")).trim(); // 面试评价表-调入
            if (!"".equals(mspjOld)) {
                mspjNew = mspjOld + "," + mspjNew;
            }

            JtChangeVo oldChangeVo = new JtChangeVo();
            oldChangeVo.setBm(getSysByFiled("departmentname", "hrmdepartment", recordSet.getString("dcbm"))); // 调出部门
            oldChangeVo.setGw(getSysByFiled("jobtitlename", "hrmjobtitles", recordSet.getString("dcgw"))); // 调出岗位 dcgw
            oldChangeVo.setDdrq(Util.null2String(JiaJieConnUtil.getCusById(xm, ddrqFiled))); // 调动日期
            oldChangeVo.setGwlx(getGgxzk(JiaJieConfigInfo.GWLX_SEL.getValue(), Util.null2String(recordSet.getString("dcgwlx")))); // 调出-岗位类型
            oldChangeVo.setZz(getGgxzk(JiaJieConfigInfo.ZHI_JI_SEL.getValue(), Util.null2String(recordSet.getString("dczj")))); // 调出-职级

            oldChangeVo.setBgdd(getSysByFiled("bgdd", "UF_JTBGDD", recordSet.getString("dcdd"))); // 调出-办公地点
            oldChangeVo.setWxyj(getSysByFiled("bgdd", "UF_JTBGDD", recordSet.getString("dcwxyj"))); // 调出-五险一金缴纳地
            oldChangeVo.setLdhtqs(getGgxzk(JiaJieConfigInfo.LDHT_SEL.getValue(), Util.null2String(recordSet.getString("dchtzt")))); // 调出-劳动合同签署主体
            oldChangeVo.setBps(getSysByFiled("lastname", "hrmresource", recordSet.getString("dcbpsspr"))); // 调出-BPS审批人
            oldChangeVo.setCwou(getGgxzk(JiaJieConfigInfo.CWOU_SEL.getValue(), Util.null2String(recordSet.getString("dccwou")))); // 调出-财务OU

            oldChangeVo.setHtcfd(getGgxzk(JiaJieConfigInfo.HTCFD_SEL.getValue(), Util.null2String(recordSet.getString("dchtcfd")))); // 调出-合同存放地
            this.writeLog("调出对象信息： " + oldChangeVo.toString());

            JtChangeVo newChangeVo = new JtChangeVo();
            newChangeVo.setBm(getSysByFiled("departmentname", "hrmdepartment", drbm)); // 调入部门
            newChangeVo.setGw(getSysByFiled("jobtitlename", "hrmjobtitles", drgw)); // 调入岗位
            newChangeVo.setDdrq(ddrq); // 调动日期
            newChangeVo.setGwlx(getGgxzk(JiaJieConfigInfo.GWLX_SEL.getValue(), drgwlx)); // 调入-岗位类型
            newChangeVo.setZz(getGgxzk(JiaJieConfigInfo.ZHI_JI_SEL.getValue(), drzj)); // 调入-职级

            newChangeVo.setBgdd(getSysByFiled("bgdd", "UF_JTBGDD", drdd)); // 调入-办公地点
            newChangeVo.setWxyj(getSysByFiled("bgdd", "UF_JTBGDD", drwxyj)); // 调入-五险一金缴纳地
            newChangeVo.setLdhtqs(getGgxzk(JiaJieConfigInfo.LDHT_SEL.getValue(), drzt)); // 调入-劳动合同签署主体
            newChangeVo.setBps(getSysByFiled("lastname", "hrmresource", drbpsspr)); // 调入-BPS审批人
            newChangeVo.setCwou(getGgxzk(JiaJieConfigInfo.CWOU_SEL.getValue(), drcwou)); // 调入-财务OU

            newChangeVo.setHtcfd(getGgxzk(JiaJieConfigInfo.HTCFD_SEL.getValue(), drhtcfd)); // 调入-合同存放地
            this.writeLog("调入对象信息： " + newChangeVo.toString());

            // 更新系统表
            RecordSet updateSet = new RecordSet();
            updateSet.executeUpdate("update hrmresource set departmentid = ?, jobtitle = ? where id = ?",
                    drbm, drgw, xm);
            this.writeLog("更新系统表结束============");

            // 插入自定义表三条数据的基本字段
            JiaJieConnUtil.insertBaseCus(Integer.parseInt(xm));
            // 更新
            String updateSql = "update CUS_FIELDDATA set " + ddrqFiled + " = ?, " + gwlxFiled + " = ?, "
                    + zjFiled + " = ?, " + bgddFiled + " = ?," + wxyjFiled + " = ?, " + ldhtFiled + " = ?, " +
                    bpsFiled + " = ?, " + cwouFiled + " = ?, " + htcfdFiled + " = ?, " + mspjbFiled + " = ? " +
                    " where id = ?";
            this.writeLog("调动申请updateSql: " + updateSql);
            updateSet.executeUpdate(updateSql,
                    ddrq, drgwlx,
                    drzj, drdd, drwxyj, drzt,
                    drbpsspr, drcwou, drhtcfd, mspjNew,
                    xm);
            this.writeLog("更新自定义表结束============");

            //清除缓存
            new ResourceComInfo().removeResourceCache();

            // 找出变动的对象
            List<JtDifferent> differentList = JiaJieConnUtil.compareObj(oldChangeVo, newChangeVo);
            for (JtDifferent different : differentList) {
                JiaJieConnUtil.insertPerCord(xm, requestId, zdMap.get(different.getFieldId()), different.getBeforeValue(), different.getAlterValue());
            }

            this.writeLog("不跨群组流程 End ===============");
        } catch (Exception e) {
            this.writeLog("不跨群组流程 异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("不跨群组流程 异常： " + e);
            return "0";
        }

        return "1";
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
