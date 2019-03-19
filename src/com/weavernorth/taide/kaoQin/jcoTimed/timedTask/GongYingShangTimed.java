package com.weavernorth.taide.kaoQin.jcoTimed.timedTask;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoTable;
import com.weavernorth.taide.kaoQin.jcoTimed.ConnPoolThree;
import com.weavernorth.taide.util.ConnUtil;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.interfaces.schedule.BaseCronJob;

public class GongYingShangTimed extends BaseCronJob {

    private ModeRightInfo moderightinfo = new ModeRightInfo();
    private BaseBean baseBean = new BaseBean();

    @Override
    public void execute() {
        int numRows = 0; // 此次同步数据量
        long start = System.currentTimeMillis(); // 开始时间戳
        int modeId = ConnUtil.getModeIdByType(13);
        String currentTimeString = TimeUtil.getCurrentTimeString();

        RecordSet deleteSet = new RecordSet();
        ConnStatement statement = new ConnStatement();
        String insertSql = "insert into uf_sap_supply(LIFNR, NAME, BU_SORT1, BU_SORT2, STRAS, " +
                "SMTP_ADDR, SPRAS, TAXTYPE, TAXNUM, VBUND, " +
                "ADDITIONAL1, ADDITIONAL2, " +
                "formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) values " +
                "(?,?,?,?,?, ?,?,?,?,?, ?,?, ?,?,?,?,?)";

        try {
            baseBean.writeLog("获取供应商数据定时任务Start =============： " + currentTimeString);
            JCoDestination destination = ConnPoolThree.getJCoDestination();
            JCoFunction function = destination.getRepository().getFunctionTemplate("ZMMI0005").getFunction();

            // 输入参数
            JCoParameterList importParameterList = function.getImportParameterList();
            importParameterList.setValue("LIFNR_HIGH", "9999999999");
            importParameterList.setValue("LIFNR_LOW", "1");
            importParameterList.setValue("REMARK", "1");

            // 调用sap接口
            function.execute(destination);

            // 返回列表
            JCoTable output_item = function.getTableParameterList().getTable("OUTPUT");
            numRows = output_item.getNumRows();

            statement.setStatementSql(insertSql);
            for (int i = 0; i < numRows; i++) {
                output_item.setRow(i);
                deleteSet.execute("delete from uf_sap_supply where LIFNR = '" + output_item.getString("LIFNR") + "'");

                statement.setString(1, output_item.getString("LIFNR")); // 供应商编号
                statement.setString(2, output_item.getString("NAME")); // 供应商描述
                statement.setString(3, output_item.getString("BU_SORT1")); // 业务伙伴的搜索词 1
                statement.setString(4, output_item.getString("BU_SORT2")); // 业务伙伴的搜索词 2
                statement.setString(5, output_item.getString("STRAS")); // 详细地址

                statement.setString(6, output_item.getString("SMTP_ADDR")); // 供应商邮箱
                statement.setString(7, output_item.getString("SPRAS")); // 语言代码
                statement.setString(8, output_item.getString("TAXTYPE")); // 税号类别
                statement.setString(9, output_item.getString("TAXNUM")); // 业务伙伴税号
                statement.setString(10, output_item.getString("VBUND")); // 公司标识

                statement.setString(11, output_item.getString("ADDITIONAL1")); // 备用字段1
                statement.setString(12, output_item.getString("ADDITIONAL2")); // 备用字段2

                statement.setInt(13, modeId);//模块id
                statement.setString(14, "1");//创建人id
                statement.setString(15, "0");//一个默认值0
                statement.setString(16, com.weaver.general.TimeUtil.getCurrentTimeString().substring(0, 10));
                statement.setString(17, com.weaver.general.TimeUtil.getCurrentTimeString().substring(11));
                statement.executeUpdate();
            }

            baseBean.writeLog("获取供应商数据定时任务END============" + TimeUtil.getCurrentTimeString());
        } catch (Exception e) {
            baseBean.writeLog("定时任务GongYingShangTimed 获取供应商异常： " + e);

        } finally {
            statement.close();
            //赋权
            moderightinfo.setNewRight(true);
            RecordSet maxSet = new RecordSet();
            maxSet.executeSql("select id from uf_sap_supply where MODEDATACREATEDATE || ' ' || MODEDATACREATETIME >= '" + currentTimeString + "'");

            int maxId;
            while (maxSet.next()) {
                maxId = maxSet.getInt("id");
                moderightinfo.editModeDataShare(1, modeId, maxId);//创建人id， 模块id， 该条数据id
            }
        }

        long end = System.currentTimeMillis(); // 结束时间戳
        long cha = (end - start) / 1000;

        String logStr = "定时获取供应商数据完成，此次获取数据： " + numRows + " 条，耗时：" + cha + " 秒。";
        // 插入日志
        ConnUtil.insertTimedLog("uf_sap_supply", logStr, numRows);

    }
}
