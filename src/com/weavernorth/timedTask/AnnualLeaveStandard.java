package com.weavernorth.timedTask;

import com.weaver.general.BaseBean;
import com.weaver.general.TimeUtil;
import weaver.common.StringUtil;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.interfaces.schedule.BaseCronJob;

/**
 * 定时任务计算标准年假
 */
public class AnnualLeaveStandard extends BaseCronJob {
    //当前年份
    private Integer currentYear = Integer.parseInt(TimeUtil.getCurrentDateString().substring(0, 4));
    private BaseBean baseBean = new BaseBean();

    @Override
    public void execute() {
        baseBean.writeLog("===================标准年假定时任务开始==================");
        RecordSet recordSet = new RecordSet();
        //查询标准年假的开始计算日期
        recordSet.execute("SELECT field0 bzStartDate,id FROM cus_fielddata c where field0 is not null and field0 !=''");
        //获取标准年假表中当前最大年份
        RecordSet getMaxYear = new RecordSet();
        getMaxYear.execute("SELECT max(annualyear) annualyear FROM HrmAnnualManagement");
        if (getMaxYear.next() && StringUtil.isNotNull(getMaxYear.getString("annualyear"))) {
            baseBean.writeLog("库里有数据");
            baseBean.writeLog("当前库里最大年份-> " + getMaxYear.getString("annualyear"));
            if (currentYear.equals(Integer.valueOf(getMaxYear.getString("annualyear").trim()) + 1)) {
                baseBean.writeLog("年份增加，插入新一年的标准年假");
                insertAnnualCount1Standard(recordSet);
            }
        }
        baseBean.writeLog("===================标准年假定时任务结束==================");

    }

    /**
     * 插入奖励年假信息
     */
    private void insertAnnualCount1Standard(RecordSet recordSet) {
        //年份增加，插入新一年的标准年假
        ConnStatement insertAward = new ConnStatement();
        String insertSql = "insert into HrmAnnualManagement(resourceid,annualyear, annualdays, status) VALUES(?,?,?,1)";
        try {
            //奖励年假天数
            Integer annualCount1Standard;
            insertAward.setStatementSql(insertSql);
            while (recordSet.next()) {
                annualCount1Standard = annualCountStandard(recordSet.getString("bzStartDate"));
                baseBean.writeLog("标准年假开始日期： " + recordSet.getString("bzStartDate") + " " + recordSet.getString("id") + " 标准年假天数================= " + annualCount1Standard);
                insertAward.setString(1, recordSet.getString("id"));
                insertAward.setString(2, String.valueOf(currentYear));
                insertAward.setString(3, String.valueOf(annualCount1Standard));
                insertAward.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            insertAward.close();
        }
    }

    /**
     * 计算标准年假天数
     *
     * @param bzStartDate 标准年假开始日期
     * @return 标准年假天数
     */
    private Integer annualCountStandard(String bzStartDate) {
        Integer gongLing = currentYear - Integer.parseInt(bzStartDate.substring(0, 4));
        //标准年假天数
        int annualCountStandard = 0;
        if (gongLing >= 1 && gongLing < 10) {
            annualCountStandard = 5;
        } else if (gongLing >= 10 && gongLing < 20) {
            annualCountStandard = 10;
        } else if (gongLing >= 20) {
            annualCountStandard = 15;
        }
        return annualCountStandard;
    }
}
