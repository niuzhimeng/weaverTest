package com.weavernorth.taide.kaoQin.sksj.timedTask;

import com.google.gson.Gson;
import com.weavernorth.taide.kaoQin.sksj.myWeb.DT_HR0005_IN;
import com.weavernorth.taide.kaoQin.sksj.myWeb.DT_HR0005_INDATAITEMS;
import com.weavernorth.taide.kaoQin.sksj.myWeb.DT_INTERFACE_COMMON;
import com.weavernorth.taide.kaoQin.sksj.myWeb.SksjUtil;
import com.weavernorth.taide.util.ConnUtil;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.interfaces.schedule.BaseCronJob;

/**
 * 14刷卡数据集成到SAP（包括刷卡补签数据）
 */
public class SendSksjToSapTimed14 extends BaseCronJob {

    private static BaseBean baseBean = new BaseBean();

    private String handExecuteDate = ""; //手动执行获取日期 yyyy-MM-dd
    private int stnCount; // 同步数据量

    public SendSksjToSapTimed14() {
    }

    public SendSksjToSapTimed14(String handExecuteDate) {
        this.handExecuteDate = handExecuteDate;
    }

    @Override
    public void execute() {
        baseBean.writeLog("14刷卡数据集成到SAP 接口执行==========");
        long startLogTime = System.currentTimeMillis();
        try {
            //获取前一天时间段
            String currentDate;
            if (!"".equals(handExecuteDate)) {
                currentDate = handExecuteDate;
            } else {
                currentDate = TimeUtil.getCurrentDateString();
            }

            // 往前调N天， 从数据库查询具体时间段
            int nDate = 0;
            RecordSet dateSet = new RecordSet();
            dateSet.executeQuery("select loginid from uf_loginInfo where dataType = 10");
            if (dateSet.next()) {
                nDate = dateSet.getInt("loginid");
            }
            nDate = 0 - nDate;

            String before1 = TimeUtil.dateAdd(currentDate, nDate);
            String start = before1 + " 00:00:00";
            String end = currentDate + " 00:00:00";

            RecordSet recordSet = new RecordSet();
            String sql = "select * from uf_allkqsk where MODEDATACREATEDATE || ' ' || MODEDATACREATEDATE >= '" + start + "' and MODEDATACREATEDATE || ' ' || MODEDATACREATEDATE < '" + end + "'";
            recordSet.executeQuery(sql);
            baseBean.writeLog("此次查询sql： " + sql);
            baseBean.writeLog("此次推送数据条数： " + recordSet.getCounts());
            stnCount = recordSet.getCounts();
            // 拼接对象
            DT_INTERFACE_COMMON data1 = new DT_INTERFACE_COMMON();
            data1.setINTF_ID("");
            data1.setSRC_SYSTEM("OA");
            data1.setDEST_SYSTEM("");
            data1.setCOMPANY_CODE("");
            data1.setSEND_TIME("");

            DT_HR0005_INDATAITEMS[] dt_hr0005_indataitems = new DT_HR0005_INDATAITEMS[recordSet.getCounts()];

            int i = 0;
            while (recordSet.next()) {
                DT_HR0005_INDATAITEMS indataitems = new DT_HR0005_INDATAITEMS();
                indataitems.setPERNR(recordSet.getString("kq02")); // 人员编号
                indataitems.setZENAME(recordSet.getString("kq11")); // 员工姓名
                indataitems.setBEGDA(dataChange(recordSet.getString("kq05"))); // 刷卡日期
                indataitems.setZP10P20(recordSet.getString("kq13")); // 上下班标识
                indataitems.setBEGUZ(timeChange(recordSet.getString("kq06"))); // 刷卡时间
                indataitems.setZ2011_TYPE(recordSet.getString("kq10")); // 数据来源
                indataitems.setZ2011_SITE(recordSet.getString("kq04")); // 打卡地方

                // sap要求字段不能null， 赋值为空串
                indataitems.setADDITIONAL1("");
                indataitems.setADDITIONAL2("");
                indataitems.setADDITIONAL3("");
                indataitems.setADDITIONAL4("");
                indataitems.setADDITIONAL5("");
                dt_hr0005_indataitems[i] = indataitems;
                i++;
            }

            //最外层对象
            DT_HR0005_IN dataAll = new DT_HR0005_IN();
            dataAll.setDATA(dt_hr0005_indataitems);
            dataAll.setSENDER(data1);

            String sendJson = new Gson().toJson(dataAll);
            baseBean.writeLog("发送json： " + sendJson);

            // 调用接口
            SksjUtil.execute(dataAll);
        } catch (Exception e) {
            baseBean.writeLog("14刷卡数据集成到SAP 接口异常： " + e);
        }
        long end = System.currentTimeMillis(); // 结束时间戳
        long cha = (end - startLogTime) / 1000;

        String logStr = "刷卡同步完成，此次推送数据： " + stnCount + " 条，耗时：" + cha + " 秒。";
        // 插入日志
        ConnUtil.insertTimedLog("uf_allkqsk", logStr, stnCount);
        baseBean.writeLog("14刷卡数据集成到SAP 执行结束==========");
    }

    /**
     * 日期去掉 -
     */
    private String dataChange(String myDate) {
        if (myDate != null) {
            myDate = myDate.replace("-", "");
        } else {
            myDate = "";
        }
        return myDate;
    }

    /**
     * 时间去掉 ： ，后面拼接 00
     */
    private String timeChange(String myTime) {
        if (myTime != null) {
            myTime = myTime.replace(":", "") + "00";
        } else {
            myTime = "000000";
        }
        return myTime;
    }
}
