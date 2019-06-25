package com.weavernorth.zhouji.util;

import com.weavernorth.zhouji.vo.ProjectVo;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;

import java.math.BigDecimal;

public class ZjCommUtil {

    /**
     * 模块id
     */
    private static final Integer ONE_MODE_ID = 8;
    private static final Integer TWO_MODE_ID = 9;
    private static final Integer THREE_MODE_ID = 10;

    private static BaseBean baseBean = new BaseBean();
    private static ModeRightInfo moderightinfo = new ModeRightInfo();

    /**
     * 操作一级项目
     */
    public static void oneProject(ProjectVo pro) {
        // 操作一级项目表
        insertOrUpdateOne(pro);
    }

    /**
     * 操作二级项目
     */
    public static void twoProject(ProjectVo pro) {
        // 查找一级项目编码
        String oneCode = getSupNoByCode(pro.getXmbm());

        ProjectVo infoByNo = getInfoByNo(oneCode);

        // 操作二级项目表
        pro.setSsyjxmbm(oneCode);
        pro.setSsyjxmmc(infoByNo.getXmmc());
        pro.setModeId(String.valueOf(TWO_MODE_ID));
        insertOrUpdateTwo(pro);

        // 操作一级项目表
        pro.setModeId(String.valueOf(ONE_MODE_ID));
        pro.setXmmc(infoByNo.getXmmc());
        pro.setHth(infoByNo.getHth());
        pro.setXmbm(oneCode);
        oneProject(pro);
    }

    /**
     * 操作三级项目
     */
    public static void threeProject(ProjectVo pro) {
        // 查找二级项目编码
        String twoCode = getSupNoByCode(pro.getXmbm());
        ProjectVo infoByNoTwo = getInfoByNo(twoCode);

        // 查找一级项目编码
        String oneCode = getSupNoByCode(pro.getXmbm());
        ProjectVo infoByNoOne = getInfoByNo(oneCode);


        // 操作三级项目表
        pro.setSsejxmbm(twoCode);
        pro.setSsejxmmc(infoByNoTwo.getXmmc());
        pro.setSsyjxmbm(oneCode);
        pro.setSsyjxmmc(infoByNoOne.getXmmc());
        pro.setModeId(String.valueOf(THREE_MODE_ID));
        insertOrUpdateThree(pro);

        // 操作二级
        infoByNoTwo.setModeId(String.valueOf(THREE_MODE_ID));
        infoByNoTwo.setKmbm(pro.getKmbm());
        infoByNoTwo.setKmmc(pro.getKmmc());
        infoByNoTwo.setJe(pro.getJe());
        twoProject(infoByNoTwo);
    }

    /**
     * 根据项目编码获取上级编码
     */
    private static String getSupNoByCode(String code) {
        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("select sjxmh from uf_project where xmbh = '" + code + "'");
        recordSet.next();

        return recordSet.getString("sjxmh");
    }

    /**
     * 更新或插入主表 明细表
     *
     * @param pro 载体对象
     */
    private static void insertOrUpdateOne(ProjectVo pro) {
        try {
            String myDate = TimeUtil.getCurrentTimeString().substring(0, 10);
            String myTime = TimeUtil.getCurrentTimeString().substring(11);
            // 主表id
            int mainId;

            RecordSet recordSet = new RecordSet();
            RecordSet insertSet = new RecordSet();

            recordSet.executeQuery("select id, je from uf_xm_one where xmbm = '" + pro.getXmbm() + "'");
            if (recordSet.next()) {
                baseBean.writeLog("主表更新=========");
                mainId = recordSet.getInt("id");
                BigDecimal decimal = new BigDecimal(recordSet.getString("je"));
                BigDecimal newDecimal = new BigDecimal(pro.getJe());
                String allJe = decimal.add(newDecimal).toString();

                String mainUpdateSql = "update uf_xm_one set xmmc = ?, hth = ?, je = ? where xmbm = ?";
                Object[] args = {pro.getXmmc(), pro.getHth(), allJe, pro.getXmbm()};
                insertSet.executeUpdate(mainUpdateSql, args);
            } else {
                baseBean.writeLog("主表新增=========");
                String mainInsertSql = "insert into uf_xm_one (xmmc, xmbm, hth, je, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) "
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
            String mxSql = "select * from uf_xm_one_dt1 where kmbm = '" + pro.getKmbm() + "' and xmbm = '" + pro.getXmbm() + "'";
            baseBean.writeLog("mxSql: " + mxSql);
            mxSet.executeQuery(mxSql);

            RecordSet mxInsertSet = new RecordSet();
            if (mxSet.next()) {
                baseBean.writeLog("明细更新============= uf_xm_one_dt1");
                BigDecimal decimal = new BigDecimal(mxSet.getString("je"));
                BigDecimal newDecimal = new BigDecimal(pro.getJe());
                String allJe = decimal.add(newDecimal).toString();
                String updateSql = "update uf_xm_one_dt1 set kmmc = '" + pro.getKmmc() + "', je = '"
                        + allJe + "' where kmbm = '" + pro.getKmbm() + "' and xmbm = '" + pro.getXmbm() + "'";
                mxInsertSet.execute(updateSql);
            } else {
                baseBean.writeLog("明细新增============= uf_xm_one_dt1");
                String insertSql = "insert into uf_xm_one_dt1(mainid, kmmc, kmbm, je, xmbm) values('" + mainId + "','" + pro.getKmmc() + "','" + pro.getKmbm()
                        + "','" + pro.getJe() + "','" + pro.getXmbm() + "')";
                mxInsertSet.execute(insertSql);
            }
        } catch (Exception e) {
            baseBean.writeLog("ZjCommUtil-One 异常： " + e);
        }
    }

    /**
     * 更新或插入主表 明细表
     *
     * @param pro 载体对象
     */
    private static void insertOrUpdateTwo(ProjectVo pro) {
        try {
            String myDate = TimeUtil.getCurrentTimeString().substring(0, 10);
            String myTime = TimeUtil.getCurrentTimeString().substring(11);
            // 主表id
            int mainId;

            RecordSet recordSet = new RecordSet();
            RecordSet insertSet = new RecordSet();

            recordSet.executeQuery("select id, je from uf_xm_two where xmbm = '" + pro.getXmbm() + "'");
            if (recordSet.next()) {
                baseBean.writeLog("主表更新========= uf_xm_two");
                mainId = recordSet.getInt("id");
                BigDecimal decimal = new BigDecimal(recordSet.getString("je"));
                BigDecimal newDecimal = new BigDecimal(pro.getJe());
                String allJe = decimal.add(newDecimal).toString();

                String mainUpdateSql = "update uf_xm_two set xmmc = ?, hth = ?, je = ? ,ssyjxmmc = ?, ssyjxmbm = ? where xmbm = ?";
                Object[] args = {pro.getXmmc(), pro.getHth(), allJe, pro.getSsyjxmmc(), pro.getSsyjxmbm(), pro.getXmbm()};
                insertSet.executeUpdate(mainUpdateSql, args);
            } else {
                baseBean.writeLog("主表新增========= uf_xm_two");
                String mainInsertSql = "insert into uf_xm_two (xmmc, xmbm, hth, je, ssyjxmmc, ssyjxmbm, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) "
                        + "values(?,?,?,?,?,?, ?,?,?,?,?)";
                Object[] regs = {pro.getXmmc(), pro.getXmbm(), pro.getHth(), pro.getJe(), pro.getSsyjxmmc(), pro.getSsyjxmbm(),
                        pro.getModeId(), 1, 0, myDate, myTime};
                insertSet.executeUpdate(mainInsertSql, regs);

                insertSet.executeQuery("select id from uf_xm_two where xmbm = '" + pro.getXmbm() + "'");
                insertSet.next();
                mainId = insertSet.getInt("id");

            }

            // 明细表处理=====================
            RecordSet mxSet = new RecordSet();
            String mxSql = "select * from  uf_xm_two_dt1 where kmbm = '" + pro.getKmbm() + "' and xmbm = '" + pro.getXmbm() + "'";
            baseBean.writeLog("mxSql: " + mxSql);
            mxSet.executeQuery(mxSql);

            RecordSet mxInsertSet = new RecordSet();
            if (mxSet.next()) {
                baseBean.writeLog("明细更新============= uf_xm_two_dt1");
                BigDecimal decimal = new BigDecimal(mxSet.getString("je"));
                BigDecimal newDecimal = new BigDecimal(pro.getJe());
                String allJe = decimal.add(newDecimal).toString();
                String updateSql = "update uf_xm_two_dt1 set kmmc = '" + pro.getKmmc() + "', je = '"
                        + allJe + "' where kmbm = '" + pro.getKmbm() + "' and xmbm = '" + pro.getXmbm() + "'";
                mxInsertSet.execute(updateSql);
            } else {
                baseBean.writeLog("明细新增============= uf_xm_two_dt1");
                String insertSql = "insert into uf_xm_two_dt1(mainid, kmmc, kmbm, je, xmbm) values('" + mainId + "','" + pro.getKmmc() + "','" + pro.getKmbm()
                        + "','" + pro.getJe() + "','" + pro.getXmbm() + "')";
                mxInsertSet.execute(insertSql);
            }
        } catch (Exception e) {
            baseBean.writeLog("ZjCommUtil-Two 异常： " + e);
        }
    }

    /**
     * 更新或插入主表 明细表
     *
     * @param pro 载体对象
     */
    private static void insertOrUpdateThree(ProjectVo pro) {
        try {
            String myDate = TimeUtil.getCurrentTimeString().substring(0, 10);
            String myTime = TimeUtil.getCurrentTimeString().substring(11);
            // 主表id
            int mainId;

            RecordSet recordSet = new RecordSet();
            RecordSet insertSet = new RecordSet();

            recordSet.executeQuery("select id, je from uf_xm_three where xmbm = '" + pro.getXmbm() + "'");
            if (recordSet.next()) {
                baseBean.writeLog("主表更新========= uf_xm_three");
                mainId = recordSet.getInt("id");
                BigDecimal decimal = new BigDecimal(recordSet.getString("je"));
                BigDecimal newDecimal = new BigDecimal(pro.getJe());
                String allJe = decimal.add(newDecimal).toString();

                String mainUpdateSql = "update uf_xm_three set xmmc = ?, hth = ?, je = ? ,ssyjxmmc = ?, ssyjxmbm = ? , ssejxmmc = ?, ssejxmbm = ? where xmbm = ?";
                Object[] args = {pro.getXmmc(), pro.getHth(), allJe, pro.getSsyjxmmc(), pro.getSsyjxmbm(), pro.getSsejxmmc(), pro.getSsejxmbm(), pro.getXmbm()};
                insertSet.executeUpdate(mainUpdateSql, args);
            } else {
                baseBean.writeLog("主表新增========= uf_xm_three");
                String mainInsertSql = "insert into uf_xm_three (xmmc, xmbm, hth, je, ssyjxmmc, ssyjxmbm, ssejxmmc, ssejxmbm," +
                        " formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) "
                        + "values(?,?,?,?,?,?,?,?, ?,?,?,?,?)";
                Object[] regs = {pro.getXmmc(), pro.getXmbm(), pro.getHth(), pro.getJe(), pro.getSsyjxmmc(), pro.getSsyjxmbm(), pro.getSsejxmmc(), pro.getSsejxmbm(),
                        pro.getModeId(), 1, 0, myDate, myTime};
                insertSet.executeUpdate(mainInsertSql, regs);

                insertSet.executeQuery("select id from uf_xm_three where xmbm = '" + pro.getXmbm() + "'");
                insertSet.next();
                mainId = insertSet.getInt("id");

            }

            // 明细表处理=====================
            RecordSet mxSet = new RecordSet();
            String mxSql = "select * from  uf_xm_three_dt1 where kmbm = '" + pro.getKmbm() + "' and xmbm = '" + pro.getXmbm() + "'";
            baseBean.writeLog("mxSql: " + mxSql);
            mxSet.executeQuery(mxSql);

            RecordSet mxInsertSet = new RecordSet();
            if (mxSet.next()) {
                baseBean.writeLog("明细更新=============  uf_xm_three_dt1");
                BigDecimal decimal = new BigDecimal(mxSet.getString("je"));
                BigDecimal newDecimal = new BigDecimal(pro.getJe());
                String allJe = decimal.add(newDecimal).toString();
                String updateSql = "update uf_xm_three_dt1 set kmmc = '" + pro.getKmmc() + "', je = '"
                        + allJe + "' where kmbm = '" + pro.getKmbm() + "' and xmbm = '" + pro.getXmbm() + "'";
                mxInsertSet.execute(updateSql);
            } else {
                baseBean.writeLog("明细新增=============  uf_xm_three_dt1");
                String insertSql = "insert into uf_xm_three_dt1(mainid, kmmc, kmbm, je, xmbm) values('" + mainId + "','" + pro.getKmmc() + "','" + pro.getKmbm()
                        + "','" + pro.getJe() + "','" + pro.getXmbm() + "')";
                mxInsertSet.execute(insertSql);
            }
        } catch (Exception e) {
            baseBean.writeLog("ZjCommUtil-Three 异常： " + e);
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

    /**
     * 根据项目编码获取项目全部信息
     */
    public static ProjectVo getInfoByNo(String code) {
        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("select * from uf_project where xmbh = '" + code + "'");
        recordSet.next();

        ProjectVo projectVo = new ProjectVo();
        projectVo.setXmbm(code);
        projectVo.setXmmc(recordSet.getString("xmmc"));
        projectVo.setHth(recordSet.getString("cwhsdy"));
        projectVo.setXmcj(recordSet.getInt("xmcj"));
        return projectVo;
    }

}
