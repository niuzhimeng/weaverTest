package com.weavernorth.daTang.vo;

import weaver.conn.ConnStatement;
import weaver.general.BaseBean;

/**
 * 预算明细表VO
 */
public class BudGetDetailVo {

    private BaseBean baseBean = new BaseBean();

    private String budgetinfoid;//部门预算信息id
    private String budgetperiods;//年度期间ID
    private String budgettypeid;//科目id
    private double budgetaccount;//金额
    private String budgetperiodslist;//期间id

    public void insert() {
        ConnStatement statement = new ConnStatement();
        try {
            String sql = "insert into fnabudgetinfodetail (budgetinfoid, budgetperiods, budgettypeid," +
                    " budgetaccount, budgetperiodslist) values (?, ?, ?, ?, ?)";
            statement.setStatementSql(sql);
            statement.setString(1, budgetinfoid);
            statement.setString(2, budgetperiods);
            statement.setString(3, budgettypeid);
            statement.setString(4, String.valueOf(budgetaccount));
            statement.setString(5, budgetperiodslist);

            statement.executeUpdate();
        } catch (Exception e) {
            baseBean.writeLog("insert fnabudgetinfodetail 异常： " + e);
        } finally {
            statement.close();
        }
    }

    public double getBudgetaccount() {
        return budgetaccount;
    }

    public void setBudgetaccount(double budgetaccount) {
        this.budgetaccount = budgetaccount;
    }

    public BaseBean getBaseBean() {
        return baseBean;
    }

    public void setBaseBean(BaseBean baseBean) {
        this.baseBean = baseBean;
    }

    public String getBudgetinfoid() {
        return budgetinfoid;
    }

    public void setBudgetinfoid(String budgetinfoid) {
        this.budgetinfoid = budgetinfoid;
    }

    public String getBudgetperiods() {
        return budgetperiods;
    }

    public void setBudgetperiods(String budgetperiods) {
        this.budgetperiods = budgetperiods;
    }

    public String getBudgettypeid() {
        return budgettypeid;
    }

    public void setBudgettypeid(String budgettypeid) {
        this.budgettypeid = budgettypeid;
    }


    public String getBudgetperiodslist() {
        return budgetperiodslist;
    }

    public void setBudgetperiodslist(String budgetperiodslist) {
        this.budgetperiodslist = budgetperiodslist;
    }
}
