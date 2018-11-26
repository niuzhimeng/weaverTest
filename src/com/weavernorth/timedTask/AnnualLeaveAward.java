package com.weavernorth.timedTask;

import com.weaver.general.BaseBean;
import com.weaver.general.TimeUtil;
import weaver.common.StringUtil;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.interfaces.schedule.BaseCronJob;

/**
 * 定时任务计算奖励年假
 */
public class AnnualLeaveAward extends BaseCronJob {
    //当前年份
    private Integer currentYear = Integer.parseInt(TimeUtil.getCurrentDateString().substring(0, 4));
    private BaseBean baseBean = new BaseBean();

    @Override
    public void execute() {
        baseBean.writeLog("===================奖励年假定时任务开始==================");
        RecordSet recordSet = new RecordSet();
        //奖励年假的开始计算日期
        recordSet.execute("SELECT startdate jlStartDate, id FROM HrmResource  where startdate is not null and loginid is not null");

        //获取奖励年假表中当前最大年份
        RecordSet getMaxYear = new RecordSet();
        getMaxYear.execute("select max(annualyear) annualyear from uf_annualLeave");
        if (getMaxYear.next() && StringUtil.isNotNull(getMaxYear.getString("annualyear"))) {
            baseBean.writeLog("库里有数据");
            baseBean.writeLog("当前库里最大年份-> " + getMaxYear.getString("annualyear"));
            if (currentYear.equals(Integer.valueOf(getMaxYear.getString("annualyear").trim()) + 1)) {
                baseBean.writeLog("年份增加，插入新一年的奖励年假");
                insertAnnualCount1Award(recordSet);
            }
        } else {
            baseBean.writeLog("库里无数据，插入奖励年假");
            insertAnnualCount1Award(recordSet);
        }
        baseBean.writeLog("===================奖励年假定时任务结束==================");
    }

    /**
     * 插入奖励年假信息
     */
    private void insertAnnualCount1Award(RecordSet recordSet) {
        //年份增加，插入新一年的奖励年假
        ConnStatement insertAward = new ConnStatement();
        String insertSql = "INSERT INTO uf_annualLeave(ryid,annualdays,unuseddate,annualyear) VALUES(?,?,?,?)";
        try {
            //奖励年假天数
            Integer annualCount1Award;
            insertAward.setStatementSql(insertSql);
            while (recordSet.next()) {
                annualCount1Award = annualCount1Award(recordSet.getString("jlStartDate"));
                baseBean.writeLog("入职日期： " + recordSet.getString("jlStartDate") + " " + recordSet.getString("id") + " 奖励假天数================= " + annualCount1Award);
                insertAward.setString(1, recordSet.getString("id"));
                insertAward.setString(2, String.valueOf(annualCount1Award));
                insertAward.setString(3, String.valueOf(currentYear + 1) + "-03-01");
                insertAward.setString(4, currentYear.toString());
                insertAward.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            insertAward.close();
        }
    }

    /**
     * 计算奖励年假天数
     *
     * @param jlStartDate 奖励年假开始日期
     * @return 奖励年假天数
     */
    private Integer annualCount1Award(String jlStartDate) {
        //奖励年假天数
        int annualCountAward = 0;
        //司龄
        Integer siLing = currentYear - Integer.parseInt(jlStartDate.substring(0, 4));
        int i = TimeUtil.dateInterval(jlStartDate, jlStartDate.substring(0, 5) + "06-30");
        if (i > 0) {
            //入职日期 < 6-30 - 当年享有奖励年假
            if (siLing >= 3 && siLing < 5) {
                annualCountAward = 1;
            } else if (siLing >= 5 && siLing < 8) {
                annualCountAward = 3;
            } else if (siLing >= 8) {
                annualCountAward = 5;
            }
        } else {
            //入职日期 >= 6月30日 次年享有奖励年假
            if (siLing >= 4 && siLing < 5) {
                annualCountAward = 1;
            } else if (siLing >= 5 && siLing < 8) {
                annualCountAward = 3;
            } else if (siLing >= 8) {
                annualCountAward = 5;
            }
        }
        return annualCountAward;
    }
}
