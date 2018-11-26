package weaver.hrm.attendance.action;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.weaver.general.TimeUtil;
import weaver.common.DateUtil;
import weaver.common.StringUtil;
import weaver.common.WeaverAction;
import weaver.conn.RecordSet;
import weaver.hrm.attendance.domain.HrmAttVacation;
import weaver.hrm.attendance.manager.HrmAttProcSetManager;
import weaver.hrm.attendance.manager.HrmAttVacationManager;
import weaver.hrm.attendance.manager.HrmLeaveTypeColorManager;
import weaver.hrm.attendance.manager.HrmPaidLeaveManager;
import weaver.hrm.schedule.HrmAnnualManagement;
import weaver.hrm.schedule.HrmPaidSickManagement;
import weaver.hrm.schedule.domain.HrmLeaveDay;
import weaver.hrm.schedule.manager.HrmScheduleManager;

/**
 * 年假扣减action
 */
public abstract class VacationAction extends WeaverAction {

    protected static final int DEDUCTION = 0;
    protected static final int FREEZE = 1;
    protected static final int RELEASE = 2;

    protected abstract int todo();

    protected HrmAttVacation bean = null;
    private HrmAttVacationManager manager = null;
    private HrmPaidLeaveManager leaveManager = null;
    private HrmLeaveTypeColorManager leaveTypeColor = null;
    protected long requestId = 0;
    protected long workflowId = 0;
    //请假天数
    private float leaveDays = 0;
    //上一年可用标准年假天数
    private float lastDays = 0;
    private String currentDate = "";
    private String thisYear = "";
    private String lastYear = "";
    private String strleaveTypes = "";
    private DecimalFormat df;

    public VacationAction() {
        this.df = new DecimalFormat("0.##");
        this.manager = new HrmAttVacationManager();
        this.leaveManager = new HrmPaidLeaveManager();
        this.leaveTypeColor = new HrmLeaveTypeColorManager();
    }

    private void init() {
        this.currentDate = DateUtil.getCurrentDate();
        this.thisYear = DateUtil.getYear();
        Calendar cal = DateUtil.addYear(Calendar.getInstance(), -1);
        this.lastYear = DateUtil.getYear(cal);

        this.requestId = StringUtil.parseToLong(request.getRequestid());
        this.workflowId = StringUtil.parseToLong(request.getWorkflowid());
        this.strleaveTypes = this.leaveTypeColor.getPaidleaveStr();
    }

    protected void handle() {
        RecordSet rs = new RecordSet();
        this.init();
        bean = manager.get(manager.getMapParam("field001:" + requestId + ";field002:" + workflowId));
        if (bean == null) bean = new HrmAttVacation();
        bean.setField001(requestId);
        bean.setField002(workflowId);
        Map<String, String> map = new HashMap<String, String>();
        map.put("requestId", "and t.requestId = " + bean.getField001());
        String param = "select id from hrm_att_proc_set where field001 = " + bean.getField002();
        rs.executeSql(new HrmAttProcSetManager().getSQLByField006(0, map, false, true, param));
        if (rs.next()) {
            bean.setField003((long) rs.getInt("resourceId"));
            bean.setField004(rs.getString("fromDate"));
            bean.setField005(rs.getString("fromTime"));
            bean.setField006(rs.getString("toDate"));
            bean.setField007(rs.getString("toTime"));
            bean.setField009(rs.getInt("newLeaveType"));
        }
        String[] result = getValue(bean).split("_");
        bean.setField008(result[0]);
        bean.setField011(result.length > 1 ? result[1] : "0.0");
        bean.setField010(todo());

        this.leaveDays = bean.getField008F();
        if (canSave()) manager.save(bean);
    }

    private String getValue(HrmAttVacation bean) {
        HrmLeaveDay lBean = new HrmLeaveDay();
        lBean.setNewLeaveType(String.valueOf(bean.getField009()));
        lBean.setFromDate(bean.getField004());
        lBean.setFromTime(bean.getField005());
        lBean.setToDate(bean.getField006());
        lBean.setToTime(bean.getField007());
        lBean.setResourceId(String.valueOf(bean.getField003()));
        lBean.setWorktime(true);
        lBean.setGettime(true);
        lBean.setScale(2);
        return new HrmScheduleManager().getLeaveDays(lBean);
    }

    private boolean canSave() {
        boolean result = true;
        switch (bean.getField009()) {
            case HrmAttVacation.L6:
                annual();
                break;
            case HrmAttVacation.L12:
                psl();
                break;
            case HrmAttVacation.L13:
                paidLeave();
                break;
            default:
                if (this.strleaveTypes.indexOf("," + bean.getField009() + ",") > -1) {
                    psl();
                    break;
                } else {
                    result = false;
                    break;
                }
        }
        return result;
    }

    private void annual() {
        try {
            String curDate = StringUtil.vString(bean.getField004());
            if (curDate.length() < 4) {
                curDate = currentDate;
            }
            String tempvalue = HrmAnnualManagement.getUserAannualInfo(String.valueOf(bean.getField003()), curDate);
            String[] values = StringUtil.split(tempvalue, "#");
            if (values.length >= 3) lastDays = StringUtil.parseToFloat(values[1], 0);
        } catch (Exception e) {
        }

        switch (bean.getField010()) {
            case DEDUCTION:
                annualDeduction();
                break;
            case FREEZE:
                break;
            case RELEASE:
                break;
        }
    }

    private void annualDeduction() {
        writeLog("扣减接口执行==========");
        //奖励 可用年假
        RecordSet jlSet = new RecordSet();
        jlSet.execute("SELECT * FROM uf_annualLeave WHERE unuseddate > (select CONVERT(varchar(30),getdate(),23)) AND ryid = '" + this.bean.getField003() + "'");
        Float currentTx = 0f;//今年奖励年假天数
        Float lastTx = 0f;//上一年奖励年假天数
        while (jlSet.next()) {
            if (TimeUtil.getCurrentDateString().substring(0, 4).equals(jlSet.getString("annualyear").trim())) {
                //今年的调休天数
                currentTx += jlSet.getFloat("annualdays");
            } else {
                //上一年的调休天数
                lastTx += jlSet.getFloat("annualdays");
            }
        }
        //上一年可用年假总和
        float allLastDays = this.lastDays + lastTx;
        RecordSet rs = new RecordSet();
        String curDate = StringUtil.vString(bean.getField004());
        if (curDate.length() < 4) {
            curDate = currentDate;
        }
        int realThisYear = StringUtil.parseToInt(curDate.substring(0, 4), StringUtil.parseToInt(currentDate.substring(0, 4), 0));
        int realLastYear = realThisYear - 1;
        if (leaveDays <= allLastDays) {
            //上一年剩余总年假够扣
            if (leaveDays <= this.lastDays) {
                //上一年标准年假够扣
                rs.executeSql("update hrmannualmanagement set annualdays = (annualdays - " + leaveDays + ") where annualyear = " + realLastYear + " and resourceid = " + bean.getField003() + " and status <> 0  ");
            } else {
                //上一年标准年假归0
                rs.executeSql("update hrmannualmanagement set annualdays = 0 where annualyear = " + realLastYear + " and resourceid = " + bean.getField003() + " and status <> 0  ");
                //上一年奖励年假中扣除
                rs.executeSql("update uf_annualLeave set annualdays = (annualdays - " + (leaveDays - this.lastDays) + ") where annualyear = " + realLastYear + " and ryid = " + bean.getField003());
            }
        } else {
            //上一年年假总和不够扣
            //上一年标准年假归0、奖励年假归0
            rs.executeSql("update hrmannualmanagement set annualdays = 0 where annualyear = " + realLastYear + " and resourceid = " + bean.getField003() + " and status <> 0  ");
            rs.executeSql("update uf_annualLeave set annualdays = 0 where annualyear = " + realLastYear + " and ryid = " + bean.getField003());
            //查询今年可用标准年假天数
            RecordSet thisDaysSet = new RecordSet();
            thisDaysSet.execute("select annualdays from HrmAnnualManagement where annualyear = '" + realThisYear + "' and resourceid = '" + bean.getField003() + "'");
            Float daysStandard = 0f;
            if (thisDaysSet.next()) {
                daysStandard = thisDaysSet.getFloat("annualdays");
            }
            if ((leaveDays - allLastDays) <= daysStandard) {
                //今年标准年假够扣
                rs.executeSql("update hrmannualmanagement set annualdays = (annualdays - " + (leaveDays - allLastDays) + ") where annualyear = " + realThisYear + " and resourceid = " + bean.getField003() + " and status <> 0  ");
            } else {
                //今年标准年假归0
                rs.executeSql("update hrmannualmanagement set annualdays = 0 where annualyear = " + realThisYear + " and resourceid = " + bean.getField003() + " and status <> 0  ");
                //今年奖励年假扣除
                rs.executeSql("update uf_annualLeave set annualdays = (annualdays - " + (leaveDays - allLastDays - daysStandard) + ") where annualyear = " + realThisYear + " and ryid = " + bean.getField003());
            }
        }
    }

    private void psl() {
        try {
            String curDate = StringUtil.vString(bean.getField004());
            if (curDate.length() < 4) {
                curDate = currentDate;
            }
            String tempvalue = HrmPaidSickManagement.getUserPaidSickInfo(String.valueOf(bean.getField003()), curDate, String.valueOf(bean.getField009()));
            String[] values = StringUtil.split(tempvalue, "#");
            if (values.length >= 3) lastDays = StringUtil.parseToFloat(values[1], 0);
        } catch (Exception e) {
        }

        switch (bean.getField010()) {
            case DEDUCTION:
                pslDeduction();
                break;
            case FREEZE:
                break;
            case RELEASE:
                break;
        }
    }

    private void pslDeduction() {
        RecordSet rs = new RecordSet();
        String curDate = StringUtil.vString(bean.getField004());
        if (curDate.length() < 4) {
            curDate = currentDate;
        }
        int realThisYear = StringUtil.parseToInt(curDate.substring(0, 4), StringUtil.parseToInt(currentDate.substring(0, 4), 0));
        int realLastYear = realThisYear - 1;
        if (leaveDays < 0) {
            rs.executeSql("update HrmPSLManagement set psldays = (psldays - " + leaveDays + ") where pslyear = " + realThisYear + " and resourceid = " + bean.getField003() + " and leavetype=" + bean.getField009());
        } else if (leaveDays < lastDays) {
            rs.executeSql("update HrmPSLManagement set psldays = (psldays - " + leaveDays + ") where pslyear = " + realLastYear + " and resourceid = " + bean.getField003() + " and leavetype=" + bean.getField009());
        } else {
            rs.executeSql("update HrmPSLManagement set psldays = 0 where pslyear = " + realLastYear + " and resourceid = " + bean.getField003() + " and leavetype=" + bean.getField009());
            rs.executeSql("update HrmPSLManagement set psldays = (psldays - " + StringUtil.parseToFloat(df.format(leaveDays - lastDays), 0) + ") where pslyear = " + realThisYear + " and resourceid = " + bean.getField003() + " and leavetype=" + bean.getField009());
        }
    }

    private void paidLeave() {
        switch (bean.getField010()) {
            case DEDUCTION:
                leaveManager.paidLeaveDeduction(String.valueOf(bean.getField003()), bean.getField004(), bean.getField005(), bean.getField006(), bean.getField007(), bean.getField011F());
                break;
            case FREEZE:
                break;
            case RELEASE:
                break;
        }
    }
}
