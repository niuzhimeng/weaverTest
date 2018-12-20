package com.weavernorth.daTang.updateYs;

import com.weavernorth.daTang.vo.BudGetDetailVo;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.general.Util;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 销售费用插入系统预算
 */
public class UpdateBudgetXs extends BaseAction {

    private BaseBean baseBean = new BaseBean();

    private String tableName;

    private String requestId;

    private String[] zds = {"q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "a", "s"};

    @Override
    public String execute(RequestInfo requestInfo) {
        baseBean.writeLog("更新预算表 - 销售费用 Start --------- " + TimeUtil.getCurrentTimeString());
        tableName = requestInfo.getRequestManager().getBillTableName();
        requestId = requestInfo.getRequestid();
        try {
            RecordSet mainSet = new RecordSet();
            mainSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");

            int bm = 0; //部门id
            int cbzx = 0;//成本中心
            int orgId = 2;//最终插入的id
            String nf = TimeUtil.getCurrentDateString().substring(0, 4);//年份
            String organizationtype = "";//组织ID类型
            if (mainSet.next()) {
                bm = Util.getIntValue(mainSet.getString("bm"), 0);
                cbzx = Util.getIntValue(mainSet.getString("cbzx"), 0);
                nf = mainSet.getString("nf");
                organizationtype = mainSet.getString("gslb");
            }

            if ("2".equals(organizationtype)) {//0：总部； 1：分部； 2：部门； 18004：成本中心；
                orgId = bm;
            } else if ("18004".equals(organizationtype)) {
                orgId = cbzx;
            }

            baseBean.writeLog("部门=================== " + bm);
            baseBean.writeLog("成本中心=================== " + cbzx);
            baseBean.writeLog("组织ID类型=================== " + organizationtype);
            baseBean.writeLog("最终插入的id=================== " + orgId);
            baseBean.writeLog("年份=================== " + nf);

            //主表处理
            mainSet.executeQuery("select m.* from fnabudgetinfo m left join fnayearsperiods d on " +
                    "m.budgetperiods = d.id where m.budgetorganizationid = '" + bm + "' and d.fnayear = '" + nf + "'");

            //查找年分表id
            RecordSet maxSet = new RecordSet();
            String nfSql = "select id from fnayearsperiods where fnayear = '" + nf + "'";
            baseBean.writeLog("查询年份sql: " + nfSql);
            maxSet.executeQuery(nfSql);
            maxSet.next();
            String nfId = maxSet.getString("id");
            baseBean.writeLog("年份id： " + nfId);
            if ("".equals(nfId)) {
                requestInfo.getRequestManager().setMessageid("10000");
                requestInfo.getRequestManager().setMessagecontent("错误提示：请维护预算年份!（数据库表：fnayearsperiods）");
                return "1";
            }

            RecordSet updateSet = new RecordSet();
            if (mainSet.next()) {
                //删除明细表已有数据，重新插入
                String mainId = mainSet.getString("id");
                delete(mainId, nfId);
                insert(mainId, nfId);
            } else {
                //插入主表fnabudgetinfo
                String insertSql = "insert into fnabudgetinfo (budgetstatus, createrid, budgetorganizationid, organizationtype, budgetperiods, revision, status, createdate)" +
                        " values(1, 1," + orgId + ", " + organizationtype + ", " + nfId + ", 0, 1, '" + TimeUtil.getCurrentDateString() + "')";
                baseBean.writeLog("insert fnabudgetinfo : " + insertSql);
                updateSet.execute(insertSql);
                //查询主表该条数据id
                maxSet.executeSql("select max(id) id from fnabudgetinfo");
                String maxId = "";
                if (maxSet.next()) {
                    maxId = maxSet.getString("id");
                }
                //插入明细表
                insert(maxId, nfId);
            }
        } catch (Exception e) {
            baseBean.writeLog("更新销售费用明细表 异常： " + e);
        }
        baseBean.writeLog("更新销售费用明细 End --------- " + TimeUtil.getCurrentTimeString());
        return "1";
    }

    /**
     * @param mainId 主表id
     * @param nfId   年份id
     */
    private void insert(String mainId, String nfId) {
        RecordSet detailSet = new RecordSet();
        String sql = "select d.* from " + tableName + "_dt1 d left join " + tableName + " m on m.id = d.mainid where m.requestId = '" + requestId + "'";
        this.writeLog("insert查询sql： " + sql);
        detailSet.executeQuery(sql);
        BudGetDetailVo vo = new BudGetDetailVo();
        while (detailSet.next()) {
            String km = detailSet.getString("km");//科目
            for (int i = 0; i < 12; i++) {
                vo.setBudgetinfoid(mainId);
                vo.setBudgetperiods(nfId);
                vo.setBudgettypeid(km);
                vo.setBudgetaccount(Util.getDoubleValue(detailSet.getString(zds[i]), 0));
                vo.setBudgetperiodslist(String.valueOf(i + 1));
                vo.insert();
            }
        }

    }

    /**
     * 删除对应明细表数据
     *
     * @param mainId 主表id
     * @param nfId   年份id
     */
    private void delete(String mainId, String nfId) {
        RecordSet detailSet = new RecordSet();
        detailSet.execute("delete from fnabudgetinfodetail where budgetinfoid = '" + mainId + "' and budgetperiods = '" + nfId + "'");

    }
}
