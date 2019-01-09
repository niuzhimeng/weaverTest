package com.weavernorth.taide.kqxg;

import com.weaver.general.TimeUtil;
import com.weavernorth.taide.util.ConnUtil;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 总部员工打卡补签05
 */
public class Headquarters05 extends BaseAction {

    private ModeRightInfo moderightinfo = new ModeRightInfo();

    @Override
    public String execute(RequestInfo requestInfo) {
        // 模块id
        int modeId = ConnUtil.getModeIdByType(7);
        // 公共选择框主键id
        int ggxzkId = 0;
        RecordSet dSet = new RecordSet();
        dSet.executeQuery("select loginid from uf_loginInfo where dataType = 7");
        if (dSet.next()) {
            ggxzkId = dSet.getInt("loginid");
        }

        String requestId = requestInfo.getRequestid();
        String operatetype = requestInfo.getRequestManager().getSrc();
        String tableName = requestInfo.getRequestManager().getBillTableName();
        this.writeLog("总部员工打卡补签05 start requestid --- " + requestId + "  operatetype --- " + operatetype + "   fromTable --- " + tableName);

        ConnStatement statement = new ConnStatement();
        try {
            if (operatetype.equals("submit")) {
                RecordSet recordSet = new RecordSet();
                String mainSql = "select * from " + tableName + " where requestid = '" + requestId + "'";
                recordSet.executeQuery(mainSql);
                int sqr = 0; // 申请人
                String workcode = ""; // 工号
                String sqrChina = ""; // 申请人中文名
                if (recordSet.next()) {
                    sqr = recordSet.getInt("sqr");
                }

                recordSet.executeQuery("select * from hrmresource where id = " + sqr);
                if (recordSet.next()) {
                    sqrChina = recordSet.getString("lastname");
                    workcode = recordSet.getString("workcode");
                }

                this.writeLog("申请人： " + sqr);
                this.writeLog("workcode： " + workcode);
                this.writeLog("sqrChina： " + sqrChina);

                recordSet.execute("SELECT d.* FROM " + tableName + " m LEFT JOIN " + tableName + "_DT1" + " d ON m.id = d.MAINID WHERE m.REQUESTID = " + requestId);

                String insertSql = "insert into uf_allkqsk(kq01, kq02, kq03, kq04, kq05," +
                        "kq06, kq07, kq08, kq09, kq10," +
                        "kq11, kq12, kq13, " +
                        "formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) values(?,?,?,?,?, ?,?,?,?,?, ?,?,?  ,?,?,?,?,?)";

                statement.setStatementSql(insertSql);
                while (recordSet.next()) {
                    String bqrq = recordSet.getString("bqrq"); //补签日期
                    String bqsj = recordSet.getString("bqsj"); //补签时间
                    String bqlx = recordSet.getString("bqlx"); //补签类型

                    RecordSet bqdmSet = new RecordSet();

                    //获取补签代码
                    String bqdm = "";
                    bqdmSet.executeQuery("SELECT NAME FROM  MODE_SELECTITEMPAGEDETAIL WHERE DISORDER = " + bqlx + " and MAINID = " + ggxzkId);
                    if (bqdmSet.next()) {
                        bqdm = bqdmSet.getString("NAME");
                    }
                    this.writeLog("补签代码分隔前： " + bqdm);
                    bqdm = bqdm.split("-")[0];
                    this.writeLog("补签代码分隔后： " + bqdm);

                    statement.setString(1, requestId);
                    statement.setString(2, workcode);
                    statement.setString(3, sqrChina);
                    statement.setString(4, "打卡补签");
                    statement.setString(5, bqrq);

                    statement.setString(6, bqsj);
                    statement.setString(7, bqrq + " " + bqsj);
                    statement.setString(8, "");
                    statement.setString(9, "");
                    statement.setString(10, bqlx);

                    statement.setInt(11, sqr);
                    statement.setString(12, "0");
                    statement.setString(13, bqdm); // 补签代码

                    statement.setInt(14, modeId);//模块id
                    statement.setString(15, "1");//创建人id
                    statement.setString(16, "0");//一个默认值0
                    statement.setString(17, TimeUtil.getCurrentTimeString().substring(0, 10));
                    statement.setString(18, TimeUtil.getCurrentTimeString().substring(11));
                    statement.executeUpdate();
                }
            }
            this.writeLog("总部员工打卡补签05========= END");
        } catch (Exception e) {
            this.writeLog("总部员工打卡补签05异常========= " + e);
            requestInfo.getRequestManager().setMessagecontent("总部员工打卡补签05异常： " + e);
            requestInfo.getRequestManager().setMessageid("11000");
        } finally {
            statement.close();
            //获取前3分钟的所有数据进行授权
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - 3);// 往前调一分钟
            String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
            String myDate = format.substring(0, 10);
            String myTime = format.substring(11, 19);
            //赋权
            moderightinfo.setNewRight(true);
            RecordSet maxSet = new RecordSet();
            maxSet.executeSql("select id from uf_allkqsk where MODEDATACREATEDATE = '" + myDate + "' and MODEDATACREATETIME > '" + myTime + "'");

            int maxId = 0;
            while (maxSet.next()) {
                maxId = maxSet.getInt("id");
                moderightinfo.editModeDataShare(1, modeId, maxId);//创建人id， 模块id， 该条数据id
            }
            this.writeLog("max(id): " + maxId);
        }

        return "1";
    }
}
