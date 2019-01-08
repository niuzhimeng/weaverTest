package com.weavernorth.taide.kaoQin.syjq04.timedTask;

import com.weaver.general.TimeUtil;
import com.weavernorth.taide.kaoQin.syjq04.myWeb.DT_HR0004_IN;
import com.weavernorth.taide.kaoQin.syjq04.myWeb.DT_HR0004_ININPUT;
import com.weavernorth.taide.kaoQin.syjq04.myWeb.DT_HR0004_OUTOUTPUT;
import com.weavernorth.taide.kaoQin.syjq04.myWeb.SyjqUtil;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.BaseBean;
import weaver.interfaces.schedule.BaseCronJob;

/**
 * 定时获取剩余假期，插入OA建模表
 */
public class TimedSyjq extends BaseCronJob {

    private static final Integer modeId = 704; //模块id
    private ModeRightInfo moderightinfo = new ModeRightInfo();
    private static BaseBean baseBean = new BaseBean();

    @Override
    public void execute() {
        String currentTimeString = TimeUtil.getCurrentTimeString();
        baseBean.writeLog("定时获取剩余假期接口执行==========： " + currentTimeString);

        String currentDate = TimeUtil.getCurrentDateString().replace("-", "");
        RecordSet recordSet = new RecordSet();
        RecordSet deleteSet = new RecordSet();
        ConnStatement statement = new ConnStatement();
        String insertSql = "insert into uf_sap_syjq(xm, ygbh, delx, cxrq, sydes, jldw, jqlx, " +
                "formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) " +
                "values(?,?,?,?,?,?,?, ?,?,?,?,?)";
        try {
            statement.setStatementSql(insertSql);
            recordSet.executeQuery("select id, workcode from hrmresource where status < 4");

            String workCode; // 工号
            while (recordSet.next()) {
                workCode = recordSet.getString("workcode");
                if (workCode.length() < 8) {
                    continue;
                }
                //拼接参数
                DT_HR0004_IN dt_hr0004_in = new DT_HR0004_IN();
                dt_hr0004_in.setKTART("");
                dt_hr0004_in.setZBEGDA(currentDate);

                //拼接数组
                DT_HR0004_ININPUT[] dt_hr0004_ininput = new DT_HR0004_ININPUT[1];
                dt_hr0004_ininput[0] = new DT_HR0004_ININPUT(workCode, "", "");
                dt_hr0004_in.setINPUT(dt_hr0004_ininput);

                // 调用接口
                DT_HR0004_OUTOUTPUT[] execute = SyjqUtil.execute(dt_hr0004_in);
                if (execute != null && execute.length > 0) {
                    // 删除当前人员旧数据
                    deleteSet.execute("delete from uf_sap_syjq where xm = '" + recordSet.getString("id") + "'");
                    for (DT_HR0004_OUTOUTPUT en : execute) {
                        statement.setString(1, recordSet.getString("id"));
                        statement.setString(2, workCode);
                        statement.setString(3, en.getKTART()); // 类型转为汉字
                        statement.setString(4, en.getZBEGDA());
                        statement.setString(5, en.getZANZHL());
                        statement.setString(6, en.getEITXT());
                        statement.setString(7, changeType(en.getKTART()));

                        statement.setInt(8, modeId);//模块id
                        statement.setString(9, "1");//创建人id
                        statement.setString(10, "0");//一个默认值0
                        statement.setString(11, TimeUtil.getCurrentTimeString().substring(0, 10));
                        statement.setString(12, TimeUtil.getCurrentTimeString().substring(11));
                        statement.executeUpdate();
                    }
                }
            }
        } catch (Exception e) {
            baseBean.writeLog("TimedSyjq 定时获取剩余假期异常 ； " + e);
        } finally {
            statement.close();
            //赋权
            moderightinfo.setNewRight(true);
            RecordSet maxSet = new RecordSet();
            maxSet.executeSql("select id from uf_sap_syjq where MODEDATACREATEDATE || ' ' || MODEDATACREATEDATE >= '" + currentTimeString + "'");

            int maxId;
            while (maxSet.next()) {
                maxId = maxSet.getInt("id");
                moderightinfo.editModeDataShare(1, modeId, maxId);//创建人id， 模块id， 该条数据id
            }
        }
    }

    private String changeType(String myType) {
        if ("10".equals(myType)) {
            myType = "年假";
        } else if ("20".equals(myType)) {
            myType = "调休";
        } else if ("30".equals(myType)) {
            myType = "家长会假";
        }
        return myType;
    }

}
