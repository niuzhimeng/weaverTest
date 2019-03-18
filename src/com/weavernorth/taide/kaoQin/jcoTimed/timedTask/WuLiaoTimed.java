package com.weavernorth.taide.kaoQin.jcoTimed.timedTask;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;

import com.weavernorth.taide.kaoQin.jcoTimed.ConnPoolThree;
import com.weavernorth.taide.util.ConnUtil;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.interfaces.schedule.BaseCronJob;

public class WuLiaoTimed extends BaseCronJob {

    private ModeRightInfo moderightinfo = new ModeRightInfo();
    private BaseBean baseBean = new BaseBean();

    @Override
    public void execute() {
        int numRows = 0; // 此次同步数据量
        long start = System.currentTimeMillis(); // 开始时间戳
        int modeId = ConnUtil.getModeIdByType(14);
        String currentTimeString = TimeUtil.getCurrentTimeString();

        RecordSet deleteSet = new RecordSet();
        ConnStatement statement = new ConnStatement();
        String insertSql = "insert into uf_materiel_info(MATNR, MAKTX, MTBEZ, BISMT, MEINS, " +
                "MSEH6, MATKL, WGBEZ, BRGEW, GEWEI, " +
                "NORMT, GROES, MEINH, RESPTI, UMREZ, " +
                "HERKL, TEMPB, TBTXT, RAUBE, RBTXT, " +
                "formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) values " +
                "(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?)";

        try {
            baseBean.writeLog("获取物料数据定时任务Start =============： " + currentTimeString);
            JCoDestination destination = ConnPoolThree.getJCoDestination();
            JCoFunction function = destination.getRepository().getFunctionTemplate("ZMMI0006").getFunction();

            // 输入参数
            function.getImportParameterList().setValue("MATKL_HIGH", "9999");
            function.getImportParameterList().setValue("MATKL_LOW", "1");
            function.getImportParameterList().setValue("MATNR_HIGH", "999999999");
            function.getImportParameterList().setValue("MATNR_LOW", "1");
            function.getImportParameterList().setValue("REMARK", "1");
            function.getImportParameterList().setValue("WERKS_HIGH", "");
            function.getImportParameterList().setValue("WERKS_LOW", "");

            // 调用sap接口
            function.execute(destination);

            // 返回列表
            JCoTable output_item = function.getTableParameterList().getTable("OUTPUT_HEAD");
            numRows = output_item.getNumRows();

            statement.setStatementSql(insertSql);
            for (int i = 0; i < numRows; i++) {
                output_item.setRow(i);
                deleteSet.execute("delete from uf_materiel_info where MATNR = '" + output_item.getString("MATNR") + "'");

                statement.setString(1, output_item.getString("MATNR")); // 物料编号
                statement.setString(2, output_item.getString("MAKTX")); // 物料描述
                statement.setString(3, output_item.getString("MTBEZ")); // 物料类型描述
                statement.setString(4, output_item.getString("BISMT")); // 旧物料号
                statement.setString(5, output_item.getString("MEINS")); // 基本计量单位

                statement.setString(6, output_item.getString("MSEH6")); // 技术格式中的外部计量单位(6-字符)
                statement.setString(7, output_item.getString("MATKL")); // 物料组
                statement.setString(8, output_item.getString("WGBEZ")); // 物料组描述
                statement.setString(9, output_item.getString("BRGEW")); // 毛重
                statement.setString(10, output_item.getString("GEWEI")); // 重量单位

                statement.setString(11, output_item.getString("NORMT")); // 工业标准描述 (例如 ANSI 或 ISO)
                statement.setString(12, output_item.getString("GROES")); // 大小/量纲
                statement.setString(13, output_item.getString("MEINH")); // 用于显示的计量单位
                statement.setString(14, output_item.getString("RESPTI")); // 技术格式中的外部计量单位(6-字符)
                statement.setString(15, output_item.getString("UMREZ")); // 基本计量单位转换分子

                statement.setString(16, output_item.getString("HERKL")); // 物料原产国（非优惠原产地）
                statement.setString(17, output_item.getString("TEMPB")); // 温度条件标识
                statement.setString(18, output_item.getString("TBTXT")); // 温度条件的描述
                statement.setString(19, output_item.getString("RAUBE")); // 存储条件
                statement.setString(20, output_item.getString("RBTXT")); // 存储条件的描述

                statement.setInt(21, modeId);//模块id
                statement.setString(22, "1");//创建人id
                statement.setString(23, "0");//一个默认值0
                statement.setString(24, com.weaver.general.TimeUtil.getCurrentTimeString().substring(0, 10));
                statement.setString(25, com.weaver.general.TimeUtil.getCurrentTimeString().substring(11));
                statement.executeUpdate();
            }

            baseBean.writeLog("获取物料数据定时任务END============" + TimeUtil.getCurrentTimeString());
        } catch (Exception e) {
            baseBean.writeLog("定时任务WuLiaoTimed 获取物料异常： " + e);

        } finally {
            statement.close();
            //赋权
            moderightinfo.setNewRight(true);
            RecordSet maxSet = new RecordSet();
            maxSet.executeSql("select id from uf_materiel_info where MODEDATACREATEDATE || ' ' || MODEDATACREATETIME >= '" + currentTimeString + "'");

            int maxId;
            while (maxSet.next()) {
                maxId = maxSet.getInt("id");
                moderightinfo.editModeDataShare(1, modeId, maxId);//创建人id， 模块id， 该条数据id
            }
        }

        long end = System.currentTimeMillis(); // 结束时间戳
        long cha = (end - start) / 1000;

        String logStr = "定时获取物料数据完成，此次获取数据： " + numRows + " 条，耗时：" + cha + " 秒。";
        // 插入日志
        ConnUtil.insertTimedLog("uf_materiel_info", logStr, numRows);

    }
}
