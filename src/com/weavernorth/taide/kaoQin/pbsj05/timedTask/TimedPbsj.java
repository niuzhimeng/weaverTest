package com.weavernorth.taide.kaoQin.pbsj05.timedTask;

import com.weaver.general.TimeUtil;
import com.weavernorth.taide.kaoQin.pbsj05.myWeb.DT_HR0003_ININPUT;
import com.weavernorth.taide.kaoQin.pbsj05.myWeb.DT_HR0003_OUTOUTPUT;
import com.weavernorth.taide.kaoQin.pbsj05.myWeb.PbsjUtil;
import com.weavernorth.taide.util.ConnUtil;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.BaseBean;
import weaver.interfaces.schedule.BaseCronJob;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 定时获取排班数据，插入OA建模表
 */
public class TimedPbsj extends BaseCronJob {

    private static final String DATA_TYPE = "1"; // uf_loginInfo表中的类型，1代表排班数据
    private ModeRightInfo moderightinfo = new ModeRightInfo();
    private static BaseBean baseBean = new BaseBean();
    private String dateStr = "";

    public TimedPbsj() {
    }

    public TimedPbsj(String dateStr) {
        this.dateStr = dateStr;
    }

    @Override
    public void execute() {
        int modeId = ConnUtil.getModeIdByType(1);
        String currentTimeString = TimeUtil.getCurrentTimeString();
        baseBean.writeLog("定时获取排班数据 Start ---------- " + currentTimeString);
        long start = System.currentTimeMillis();
        baseBean.writeLog("开始执行时间 " + start);
        RecordSet recordSet = new RecordSet();
        RecordSet deleteSet = new RecordSet();
        RecordSet daysSet = new RecordSet();

        ConnStatement statement = new ConnStatement();
        String insertSql = "insert into uf_sap_pbb(pb01, pb02, pb03, pb04, pb05, " +
                "pb06, pb07, pb08, pb09, pb10, pb00, " +
                "formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) " +
                "values(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,? ,?)";
        try {
            int before = 5; // 当前日期 往前天数
            int after = 10; // 当前日期 往后天数
            daysSet.executeQuery("select * from uf_loginInfo where dataType = '" + DATA_TYPE + "'");
            if (daysSet.next()) {
                before = daysSet.getInt("loginid"); // 往前n天
                after = daysSet.getInt("password"); // 往后n天
            }

            Date date = new Date();
            // 手动传入时间
            if (dateStr.length() > 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                date = sdf.parse(dateStr);
            }

            //获取时间
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - before); // 往前调n天
            String deleteDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()); //删除日期节点
            String before5 = new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + before + after); // 往后调n天
            String after10 = new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());

            recordSet.executeQuery("select id, workcode from HRMRESOURCE_SAP");

            String workCode; // 工号
            while (recordSet.next()) {
                workCode = recordSet.getString("workcode");
                if (workCode.length() < 8) {
                    continue;
                }
                statement.setStatementSql(insertSql);
                //拼接参数对象
                DT_HR0003_ININPUT dt_hr0003_ininput = new DT_HR0003_ININPUT();
                dt_hr0003_ininput.setPERNR(recordSet.getString("workcode")); // 工号
                dt_hr0003_ininput.setBEGDA(before5);
                dt_hr0003_ininput.setENDDA(after10);
                dt_hr0003_ininput.setAdditional1("");
                dt_hr0003_ininput.setAdditional2("");

                DT_HR0003_ININPUT[] dt_hr0003_ininputs = new DT_HR0003_ININPUT[1];
                dt_hr0003_ininputs[0] = dt_hr0003_ininput;
                DT_HR0003_OUTOUTPUT[] execute = PbsjUtil.execute(dt_hr0003_ininputs);
                if (execute != null && execute.length > 0) {
                    // 删除当前人员旧的排班数据
                    String deleteSql = "delete from uf_sap_pbb where pb01 = '" + recordSet.getString("workcode") + "' and pb02 >= '" + deleteDate + "'";
                    baseBean.writeLog("删除sql： " + deleteSql);
                    deleteSet.execute(deleteSql);
                    for (DT_HR0003_OUTOUTPUT en : execute) {
                        statement.setString(1, en.getPERNR()); // 人员编号
                        statement.setString(2, pjDate(en.getDATUM())); // 日期
                        statement.setString(3, en.getMOTPR()); // 日工作日程表的人事子范围分组
                        statement.setString(4, en.getTPROG()); // 日工作计划
                        statement.setString(5, en.getFTKLA()); // 公共假日类

                        statement.setString(6, en.getTPKLA()); // 日工作计划类型
                        statement.setString(7, en.getTTEXT()); // 班次描述
                        statement.setString(8, en.getSOLLZ()); // 班次时长
                        statement.setString(9, pjTime(en.getSOBEG())); // 班次开始时间
                        statement.setString(10, pjTime(en.getSOEND())); // 班次结束时间

                        statement.setString(11, recordSet.getString("id")); // 人员主键

                        statement.setInt(12, modeId);//模块id
                        statement.setString(13, "1");//创建人id
                        statement.setString(14, "0");//一个默认值0
                        statement.setString(15, TimeUtil.getCurrentTimeString().substring(0, 10));
                        statement.setString(16, TimeUtil.getCurrentTimeString().substring(11));
                        statement.executeUpdate();
                    }
                }
            }
        } catch (Exception e) {
            baseBean.writeLog("TimedPbsj 定时获取排班数据异常 ； " + e);
        } finally {
            statement.close();
            long end = System.currentTimeMillis();
            baseBean.writeLog("数据插入完毕耗时: " + (end - start));
            //赋权
            moderightinfo.setNewRight(true);
            RecordSet maxSet = new RecordSet();
            maxSet.executeSql("select id from uf_sap_pbb where MODEDATACREATEDATE || ' ' || MODEDATACREATEDATE >= '" + currentTimeString + "'");

            int maxId;
            while (maxSet.next()) {
                maxId = maxSet.getInt("id");
                moderightinfo.editModeDataShare(1, modeId, maxId);//创建人id， 模块id， 该条数据id
            }
            long end1 = System.currentTimeMillis();
            baseBean.writeLog("授权结束耗时: " + (end1 - end));
        }
    }

    /**
     * 时间保留四位，加入 ：
     */
    private String pjTime(String timeStart) {
        if (timeStart != null && timeStart.length() > 3) {
            timeStart = timeStart.substring(0, 2) + ":" + timeStart.substring(2, 4);
        } else {
            timeStart = "00:00";
        }
        return timeStart;
    }

    private String pjDate(String date) {
        if (date != null && date.length() > 7) {
            date = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
        } else {
            date = "0000-00-00";
        }
        return date;
    }
}
