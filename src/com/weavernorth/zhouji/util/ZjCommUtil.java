package com.weavernorth.zhouji.util;

import com.weavernorth.zhouji.vo.ProjectVo;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;

import java.math.BigDecimal;

public class ZjCommUtil {
    private static BaseBean baseBean = new BaseBean();
    private static ModeRightInfo moderightinfo = new ModeRightInfo();

    /**
     * 操作一级项目
     */
    public static void oneProject(ProjectVo pro) {
        insertOrUpdate(pro);
    }

    public static void twoProject() {

    }

    public static void threeProject() {

    }

    /**
     * 更新或插入主表 明细表
     *
     * @param pro 载体对象
     */
    private static void insertOrUpdate(ProjectVo pro) {
        try {
            String myDate = TimeUtil.getCurrentTimeString().substring(0, 10);
            String myTime = TimeUtil.getCurrentTimeString().substring(11);
            // 主表id
            int mainId;

            RecordSet recordSet = new RecordSet();
            RecordSet insertSet = new RecordSet();

            recordSet.executeQuery("select id, je from uf_xm_one where xmbm = '" + pro.getXmbm() + "'");
            if (recordSet.next()) {
                baseBean.writeLog("主表更新=========" + pro.getTableName());
                mainId = recordSet.getInt("id");
                BigDecimal decimal = new BigDecimal(recordSet.getString("je"));
                BigDecimal newDecimal = new BigDecimal(pro.getJe());
                String allJe = decimal.add(newDecimal).toString();

                String mainUpdateSql = "update " + pro.getTableName() + " set xmmc = ?, hth = ?, je = ? where xmbm = ?";
                Object[] args = {pro.getXmmc(), pro.getHth(), allJe, pro.getXmbm()};
                insertSet.executeUpdate(mainUpdateSql, args);
            } else {
                baseBean.writeLog("主表新增=========" + pro.getTableName());
                String mainInsertSql = "insert into " + pro.getTableName() + " (xmmc, xmbm, hth, je, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) "
                        + "values(?,?,?,?, ?,?,?,?,?)";
                Object[] regs = {pro.getXmmc(), pro.getXmbm(), pro.getHth(), pro.getJe(),
                        pro.getModeId(), 1, 0, myDate, myTime};
                insertSet.executeUpdate(mainInsertSql, regs);

                insertSet.executeQuery("select id from uf_xm_one where xmbm = '" + pro.getXmbm() + "'");
                insertSet.next();
                mainId = insertSet.getInt("id");

            }

            // 明细表处理=====================
            RecordSet mxSet = new RecordSet();
            String mxSql = "select * from " + pro.getTableName() + "_dt1 where kmbm = '" + pro.getKmbm() + "' and xmbm = '" + pro.getXmbm() + "'";
            baseBean.writeLog("mxSql: " + mxSql);
            mxSet.executeQuery(mxSql);

            RecordSet mxInsertSet = new RecordSet();
            if (mxSet.next()) {
                baseBean.writeLog("明细更新=============" + pro.getTableName() + "_dt1");
                BigDecimal decimal = new BigDecimal(mxSet.getString("je"));
                BigDecimal newDecimal = new BigDecimal(pro.getJe());
                String allJe = decimal.add(newDecimal).toString();
                String updateSql = "update " + pro.getTableName() + "_dt1 set kmmc = '" + pro.getKmmc() + "', je = '"
                        + allJe + "' where kmbm = '" + pro.getKmbm() + "' and xmbm = '" + pro.getXmbm() + "'";
                mxInsertSet.execute(updateSql);
            } else {
                baseBean.writeLog("明细新增=============" + pro.getTableName() + "_dt1");
                String insertSql = "insert into " + pro.getTableName() + "_dt1(mainid, kmmc, kmbm, je, xmbm) values('" + mainId + "','" + pro.getKmmc() + "','" + pro.getKmbm()
                        + "','" + pro.getJe() + "','" + pro.getXmbm() + "')";
                mxInsertSet.execute(insertSql);
            }
        } catch (Exception e) {
            baseBean.writeLog("ZjCommUtil 异常： " + e);
        }
    }

    /**
     * 建模表授权
     *
     * @param tableName 表名
     * @param myTime    操作开始时间
     * @param modeId    模块id
     */
    public static void rebuildModeDataShare(String tableName, String myTime, int modeId) {
        //赋权
        moderightinfo.setNewRight(true);
        RecordSet maxSet = new RecordSet();
        maxSet.executeSql("select id from " + tableName + " where MODEDATACREATEDATE + MODEDATACREATETIME >= '" + myTime + "'");

        int maxId;
        while (maxSet.next()) {
            maxId = maxSet.getInt("id");
            // 创建人id， 模块id， 该条数据id
            moderightinfo.rebuildModeDataShareByEdit(1, modeId, maxId);
        }
    }


}
