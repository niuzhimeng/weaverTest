package com.weavernorth.taide.kaoQin.kqyc.timedTask;

import com.weaver.general.TimeUtil;
import com.weavernorth.taide.kaoQin.kqyc.myWeb.DT_HRI006_OUTRETURN;
import com.weavernorth.taide.kaoQin.kqyc.myWeb.KqycUtil;
import com.weavernorth.taide.util.ConnUtil;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.BaseBean;
import weaver.interfaces.schedule.BaseCronJob;

/**
 * 定时获取考勤异常，插入OA建模表
 */
public class TimedKqyc extends BaseCronJob {

    private ModeRightInfo moderightinfo = new ModeRightInfo();
    private static BaseBean baseBean = new BaseBean();
    private RecordSet recordSet = new RecordSet();
    private int stnCount; // 同步数据量

    @Override
    public void execute() {
        long start = System.currentTimeMillis(); // 开始时间戳
        // 模块id
        int modeId = ConnUtil.getModeIdByType(3);
        String currentTimeString = TimeUtil.getCurrentTimeString();
        baseBean.writeLog("定时获取考勤异常 Start ---------- " + currentTimeString);
        RecordSet deleteSet = new RecordSet();

        ConnStatement statement = new ConnStatement();
        String insertSql = "insert into uf_sap_kqyc(xm, gh, rq, wbms, kssj, jssj, sjkssj, sjjssj, bm, " +
                "formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) " +
                "values(?,?,?,?,?,?,?,?, ?,?,?,?,?,?)";
        try {
            statement.setStatementSql(insertSql);
            DT_HRI006_OUTRETURN[] execute = KqycUtil.execute();
            stnCount = execute.length;
            baseBean.writeLog("考勤异常返回条数： " + execute.length);
            if (execute.length > 0) {
                // 删除所有旧的数据
                deleteSet.execute("delete from uf_sap_kqyc");
                String id = "";
                String departmentid = "";
                for (DT_HRI006_OUTRETURN en : execute) {
                    // 根据工号查询id与部门id
                    recordSet.executeQuery("select id, departmentid from HRMRESOURCE where WORKCODE = '" + en.getPERNR() + "'");
                    if (recordSet.next()) {
                        id = recordSet.getString("id");
                        departmentid = recordSet.getString("departmentid");
                    }

                    statement.setString(1, id); // 人员id
                    statement.setString(2, en.getPERNR()); // 人员编号
                    statement.setString(3, pjDate(en.getDATUM())); // 日期
                    statement.setString(4, en.getZTEXT()); // 文本描述
                    statement.setString(5, en.getSOBEG()); // 班次开始时间

                    statement.setString(6, en.getSOEND()); // 班次结束时间
                    statement.setString(7, en.getFTKLA()); // 实际开始时间
                    statement.setString(8, en.getTPKLA()); // 实际结束时间
                    statement.setString(9, departmentid); // 部门

                    statement.setInt(10, modeId);//模块id
                    statement.setString(11, "1");//创建人id
                    statement.setString(12, "0");//一个默认值0
                    statement.setString(13, TimeUtil.getCurrentTimeString().substring(0, 10));
                    statement.setString(14, TimeUtil.getCurrentTimeString().substring(11));
                    statement.executeUpdate();
                }
            }
            baseBean.writeLog("TimedKqyc 定时获考勤异常数据 结束=========== ； ");
        } catch (Exception e) {
            baseBean.writeLog("TimedKqyc 定时获考勤异常数据异常 ； " + e);
        } finally {
            statement.close();
            //赋权
            moderightinfo.setNewRight(true);
            RecordSet maxSet = new RecordSet();
            maxSet.executeSql("select id from uf_sap_kqyc where MODEDATACREATEDATE || ' ' || MODEDATACREATETIME >= '" + currentTimeString + "'");

            int maxId;
            while (maxSet.next()) {
                maxId = maxSet.getInt("id");
                moderightinfo.editModeDataShare(1, modeId, maxId);//创建人id， 模块id， 该条数据id
            }
        }

        long end = System.currentTimeMillis(); // 结束时间戳
        long cha = (end - start) / 1000;

        String logStr = "考勤异常同步完成，此次同步数据： " + stnCount + " 条，耗时：" + cha + " 秒。";
        // 插入日志
        ConnUtil.insertTimedLog("uf_sap_kqyc", logStr, stnCount);
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
