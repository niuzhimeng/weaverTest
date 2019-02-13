package com.weavernorth.taide.kaoQin.kqmx.timedTask;

import com.weaver.general.TimeUtil;
import com.weavernorth.taide.kaoQin.kqmx.myWeb.*;
import com.weavernorth.taide.util.ConnUtil;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.BaseBean;
import weaver.interfaces.schedule.BaseCronJob;

/**
 * 定时获取考勤明细数据，插入OA建模表
 */
public class TimedKqmx extends BaseCronJob {

    private ModeRightInfo moderightinfo = new ModeRightInfo();
    private static BaseBean baseBean = new BaseBean();
    private String dateStr = "";
    private int stnCount; // 同步数据量

    public TimedKqmx() {
    }

    public TimedKqmx(String dateStr) {
        this.dateStr = dateStr;
    }

    @Override
    public void execute() {
        long start = System.currentTimeMillis(); // 开始时间戳

        int modeId = ConnUtil.getModeIdByType(2);
        String currentTimeString = TimeUtil.getCurrentTimeString();
        baseBean.writeLog("定时获取考勤明细数据 Start ---------- " + currentTimeString);

        RecordSet recordSet = new RecordSet();
        RecordSet deleteSet = new RecordSet();

        ConnStatement statement = new ConnStatement();
        String insertSql = "insert into uf_workdetails(OAID, PERNR, ENAME, ZVDSK1, ZPLANS, " +
                "ZPLAN_H, ZTRUE_H, Z9065, Z9064, Z9063, " +
                "Z9015, Z9016, Z9013, Z5002_H,Z5001, Z0010, " + //此行6个字段
                "Z0020, ZY0020, Z0030, Z0040, Z0060, " +
                "Z0070, Z0080, Z0090, Z0100, Z0120, " +
                "Z0050, Z0130, Z5101, Z5102, Z5103, " +
                "Z200620, Z5107, Z5114, Z5115, Z5104, " +
                "Z5105, Z5111, Z5112, Z5113, Z5108, " +
                "Z5109, Z01, Z02, Z03, Z04, " +
                "Z05, Z06, Z07, Z08, Z09, " +
                "Z10, Z11, Z12, Z13, Z14, " +
                "Z15, Z16, Z17, Z18, Z19, " +
                "Z20, Z21, Z22, Z23, Z24, " +
                "Z25, Z26, Z27, Z28, Z29, " +
                "Z30, Z31, month, " +
                "formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) " +
                "values(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?," +
                "?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?," +
                "?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?)";
        String workCode = ""; // 工号
        try {
            recordSet.executeQuery("select id, workcode from HRMRESOURCE where status < 4");
            int id; // 人员id
            while (recordSet.next()) {
                workCode = recordSet.getString("workcode");
                id = recordSet.getInt("id");
                if (workCode.length() < 8) {
                    continue;
                }

                // 日期格式 201901，小于17号传本月, >=17号传下一个月
                String dateString = TimeUtil.getCurrentDateString();
                int day = Integer.parseInt(dateString.substring(8, 10));
                String myMonth;
                if (day < 17) {
                    myMonth = dateString.substring(0, 7).replace("-", "");
                } else {
                    myMonth = weaver.general.TimeUtil.dateAdd(dateString, 20).substring(0, 7).replace("-", "");
                }

                // 手动传入时间
                if (dateStr.length() > 0) {
                    myMonth = dateStr;
                }

                statement.setStatementSql(insertSql);
                // 拼接对象
                DT_HRI007_INSENDER dt_hri007_insender = new DT_HRI007_INSENDER();
                dt_hri007_insender.setINTF_ID("");
                dt_hri007_insender.setSrc_System("OA");
                dt_hri007_insender.setDest_System("");
                dt_hri007_insender.setCompany_Code("");
                dt_hri007_insender.setSend_Time("");

                DT_HRI007_INDATAHEADER dt_hri007_indataheader = new DT_HRI007_INDATAHEADER();
                dt_hri007_indataheader.setZYYYYMM(myMonth); // 查询月份
                dt_hri007_indataheader.setZDATE("");

                DT_HRI007_INDATAITEMS[] dt_hri007_indataitems = new DT_HRI007_INDATAITEMS[1];
                DT_HRI007_INDATAITEMS dt_hri007_indataitems1 = new DT_HRI007_INDATAITEMS();
                dt_hri007_indataitems1.setPERNR(workCode); // 人员编号
                dt_hri007_indataitems1.setAdditional1("");
                dt_hri007_indataitems1.setAdditional2("");
                dt_hri007_indataitems[0] = dt_hri007_indataitems1;

                DT_HRI007_INDATA dt_hri007_indata = new DT_HRI007_INDATA();
                dt_hri007_indata.setITEMS(dt_hri007_indataitems);
                dt_hri007_indata.setHEADER(dt_hri007_indataheader);

                // 拼接最外层对象
                DT_HRI007_IN dt_hri007_in = new DT_HRI007_IN();
                dt_hri007_in.setSENDER(dt_hri007_insender);
                dt_hri007_in.setDATA(dt_hri007_indata);

                DT_HRI007_OUTRETURN[] execute = KqmxUtil.execute(dt_hri007_in);
                stnCount = execute.length;
                if (execute != null && execute.length > 0) {
                    // 删除当前人员旧的考勤明细数据
                    String deleteSql = "delete from uf_workdetails where OAID = '" + id + "' and month = '" + myMonth + "'";
                    deleteSet.execute(deleteSql);

                    for (DT_HRI007_OUTRETURN en : execute) {
                        statement.setInt(1, id);
                        statement.setString(2, workCode);
                        statement.setString(3, en.getENAME());
                        statement.setString(4, en.getZVDSK1());
                        statement.setString(5, en.getZPLANS()); // 应出勤（小时）

                        statement.setString(6, en.getZPLAN_H());
                        statement.setString(7, en.getZTRUE_H());
                        statement.setString(8, en.getZ9065());
                        statement.setString(9, en.getZ9064());
                        statement.setString(10, en.getZ9063()); // 旷工（小时）

                        statement.setString(11, en.getZ9015());
                        statement.setString(12, en.getZ9016());
                        statement.setString(13, en.getZ9013());
                        statement.setString(14, en.getZ5002_H());
                        statement.setString(15, en.getZ5001()); // 出差（天）

                        statement.setString(16, en.getZ0010());
                        statement.setString(17, en.getZ0020());
                        statement.setString(18, en.getZY0020());
                        statement.setString(19, en.getZ0030());
                        statement.setString(20, en.getZ0040()); // 年假（天）

                        statement.setString(21, en.getZ0060());
                        statement.setString(22, en.getZ0070());
                        statement.setString(23, en.getZ0080());
                        statement.setString(24, en.getZ0090());
                        statement.setString(25, en.getZ0100()); // 哺乳假（天）

                        statement.setString(26, en.getZ0120());
                        statement.setString(27, en.getZ0050());
                        statement.setString(28, en.getZ0130());
                        statement.setString(29, en.getZ5101());
                        statement.setString(30, en.getZ5102()); // 公休日加班折现（小时）

                        statement.setString(31, en.getZ5103());
                        statement.setString(32, en.getZ200620());
                        statement.setString(33, en.getZ5107());
                        statement.setString(34, en.getZ5114());
                        statement.setString(35, en.getZ5115()); // 晚班值班（次）

                        statement.setString(36, en.getZ5104());
                        statement.setString(37, en.getZ5105());
                        statement.setString(38, en.getZ5111());
                        statement.setString(39, en.getZ5112());
                        statement.setString(40, en.getZ5113()); // 夜班（次）

                        statement.setString(41, en.getZ5108());
                        statement.setString(42, en.getZ5109());
                        statement.setString(43, en.getZ01());
                        statement.setString(44, en.getZ02());
                        statement.setString(45, en.getZ03()); // 03号

                        statement.setString(46, en.getZ04());
                        statement.setString(47, en.getZ05());
                        statement.setString(48, en.getZ06());
                        statement.setString(49, en.getZ07());
                        statement.setString(50, en.getZ08()); // 08号

                        statement.setString(51, en.getZ09());
                        statement.setString(52, en.getZ10());
                        statement.setString(53, en.getZ11());
                        statement.setString(54, en.getZ12());
                        statement.setString(55, en.getZ13()); // 13号

                        statement.setString(56, en.getZ14());
                        statement.setString(57, en.getZ15());
                        statement.setString(58, en.getZ16());
                        statement.setString(59, en.getZ17());
                        statement.setString(60, en.getZ18()); // 18号

                        statement.setString(61, en.getZ19());
                        statement.setString(62, en.getZ20());
                        statement.setString(63, en.getZ21());
                        statement.setString(64, en.getZ22());
                        statement.setString(65, en.getZ23()); // 23号

                        statement.setString(66, en.getZ24());
                        statement.setString(67, en.getZ25());
                        statement.setString(68, en.getZ26());
                        statement.setString(69, en.getZ27());
                        statement.setString(70, en.getZ28()); // 28号

                        statement.setString(71, en.getZ29());
                        statement.setString(72, en.getZ30());
                        statement.setString(73, en.getZ31());
                        statement.setString(74, myMonth);

                        statement.setInt(75, modeId);//模块id
                        statement.setString(76, "1");//创建人id
                        statement.setString(77, "0");//一个默认值0
                        statement.setString(78, TimeUtil.getCurrentTimeString().substring(0, 10));
                        statement.setString(79, TimeUtil.getCurrentTimeString().substring(11));
                        statement.executeUpdate();
                    }
                }
            }
        } catch (Exception e) {
            baseBean.writeLog("TimedKqmx 定时获取考勤明细数据异常,人员工号 ； " + workCode + ": " + e);
        } finally {
            statement.close();
            //赋权
            moderightinfo.setNewRight(true);
            RecordSet maxSet = new RecordSet();
            maxSet.executeSql("select id from uf_workdetails where MODEDATACREATEDATE || ' ' || MODEDATACREATEDATE >= '" + currentTimeString + "'");

            int maxId;
            while (maxSet.next()) {
                maxId = maxSet.getInt("id");
                moderightinfo.editModeDataShare(1, modeId, maxId);//创建人id， 模块id， 该条数据id
            }
            baseBean.writeLog("定时获取考勤明细数据 End ---------- " + TimeUtil.getCurrentTimeString());
        }

        long end = System.currentTimeMillis(); // 结束时间戳
        long cha = (end - start) / 1000;

        String logStr = "考勤明细同步完成，此次同步数据： " + stnCount + " 条，耗时：" + cha + " 秒。";
        // 插入日志
        ConnUtil.insertTimedLog("uf_workdetails", logStr, stnCount);


    }


}
