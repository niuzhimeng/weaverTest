package com.weavernorth.caibai.util;

import com.weavernorth.caibai.orgsyn.vo.CbHrmResource;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;

import java.util.List;

public class CbConnUtil {

    private static ModeRightInfo moderightinfo = new ModeRightInfo();
    private static BaseBean baseBean = new BaseBean();

    /**
     * 定时任务模块id
     */
    private static final Integer LOG_MODE_ID = 162;
    /**
     * 错误信息模块id
     */
    private static final Integer ERR_LOG_MODE_ID = 163;

    /**
     * 插入定时任务日志
     *
     * @param tableName 表名
     * @param logStr    日志信息
     * @param nums      插入数量
     * @param tbzt      同步状态
     * @param bm        编码
     */
    public static void insertTimedLog(String tableName, String logStr, int nums, String tbzt, String bm) {
        String currentTimeString = TimeUtil.getCurrentTimeString();
        ConnStatement statement = new ConnStatement();
        String insertSql = "insert into uf_org_log(tableName, logTxt, logNum, tbzt, bm, logType, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime)" +
                "values (?,?,?,?,?,?,  ?,?,?,?,?)";
        try {
            statement.setStatementSql(insertSql);
            // 表名
            statement.setString(1, tableName);
            // 日志信息
            statement.setString(2, logStr);
            // 插入数量
            statement.setInt(3, nums);
            // 同步状态
            statement.setString(4, tbzt);
            // 编码
            statement.setString(5, bm);
            // 日志类型
            statement.setString(6, "人员同步");

            //模块id
            statement.setInt(7, LOG_MODE_ID);
            //创建人id
            statement.setString(8, "1");
            //一个默认值0
            statement.setString(9, "0");
            statement.setString(10, com.weaver.general.TimeUtil.getCurrentTimeString().substring(0, 10));
            statement.setString(11, com.weaver.general.TimeUtil.getCurrentTimeString().substring(11));
            statement.executeUpdate();
        } catch (Exception e) {
            baseBean.writeLog("插入定时任务日志, insertTimedLog 异常： " + e);
        } finally {
            statement.close();
            //赋权
            moderightinfo.setNewRight(true);
            RecordSet maxSet = new RecordSet();
            maxSet.executeSql("select id from uf_org_log where MODEDATACREATEDATE || MODEDATACREATETIME >= '" + TimeUtil.timeAdd(currentTimeString, -10) + "'");

            int maxId;
            while (maxSet.next()) {
                maxId = maxSet.getInt("id");
                // 创建人id， 模块id， 该条数据id
                moderightinfo.rebuildModeDataShareByEdit(1, LOG_MODE_ID, maxId);
            }
        }
    }

    /**
     * 插入定错误日志
     *
     * @param tableName 表名
     */
    public static void insertErrorLog(String tableName, List<CbHrmResource> errHrmResourceList, String sszj) {
        String currentTimeString = TimeUtil.getCurrentTimeString();
        ConnStatement statement = new ConnStatement();
        String insertSql = "insert into uf_org_errlog(tableName, logTxt, sszj, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime)" +
                "values (?,?,?,  ?,?,?,?,?)";
        try {
            String subDate = com.weaver.general.TimeUtil.getCurrentTimeString().substring(0, 10);
            String subTime = com.weaver.general.TimeUtil.getCurrentTimeString().substring(11);
            statement.setStatementSql(insertSql);
            for (CbHrmResource cbHrmResource : errHrmResourceList) {
                // 表名
                statement.setString(1, tableName);
                // 日志信息
                statement.setString(2, cbHrmResource.getErrMessage());
                // 所属主键
                statement.setString(3, sszj);

                //模块id
                statement.setInt(4, ERR_LOG_MODE_ID);
                //创建人id
                statement.setString(5, "1");
                //一个默认值0
                statement.setString(6, "0");
                statement.setString(7, subDate);
                statement.setString(8, subTime);
                statement.executeUpdate();
            }

        } catch (Exception e) {
            baseBean.writeLog("插入定时任务日志, insertTimedLog 异常： " + e);
        } finally {
            statement.close();
            //赋权
            moderightinfo.setNewRight(true);
            RecordSet maxSet = new RecordSet();
            maxSet.executeSql("select id from uf_org_errlog where MODEDATACREATEDATE || MODEDATACREATETIME >= '" + TimeUtil.timeAdd(currentTimeString, -3) + "'");

            int maxId;
            while (maxSet.next()) {
                maxId = maxSet.getInt("id");
                // 创建人id， 模块id， 该条数据id
                moderightinfo.rebuildModeDataShareByEdit(1, ERR_LOG_MODE_ID, maxId);
            }
        }
    }

    public static void insertHrmResource(List<CbHrmResource> insertHrmResourceList) {
        ConnStatement statement = new ConnStatement();
        try {
            String sql = "insert into hrmresource (workcode, lastname, loginid, status, sex," +
                    " locationid, email, mobile, managerid, joblevel," +
                    "seclevel, departmentid, subcompanyid1, jobtitle, birthday," +
                    "startdate, dsporder, telephone, folk, enddate," +
                    " id, password, accounttype, belongto, systemlanguage, " +
                    "certificatenum) " +
                    "values (?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?)";
            statement.setStatementSql(sql);
            int stnCount = 0;
            for (CbHrmResource hrmResource : insertHrmResourceList) {
                if (stnCount % 500 == 0) {
                    statement.close();
                    statement = new ConnStatement();
                    statement.setStatementSql(sql);
                }
                statement.setString(1, hrmResource.getWorkcode());
                statement.setString(2, hrmResource.getLastname());
                statement.setString(3, hrmResource.getLoginid());
                statement.setString(4, hrmResource.getStatus());
                statement.setString(5, hrmResource.getSex());

                statement.setString(6, hrmResource.getLocation());
                statement.setString(7, hrmResource.getEmail());
                statement.setString(8, hrmResource.getMobile());
                statement.setString(9, hrmResource.getManagerIdReal());
                statement.setString(10, hrmResource.getJoblevel());

                statement.setString(11, hrmResource.getSeclevel());
                statement.setString(12, hrmResource.getDepId());
                statement.setString(13, hrmResource.getSubId());
                statement.setString(14, hrmResource.getJobtitleId());
                statement.setString(15, hrmResource.getBirthday());

                statement.setString(16, hrmResource.getEntrydate());
                statement.setString(17, hrmResource.getDsporder());
                statement.setString(18, hrmResource.getMobile());
                statement.setString(19, hrmResource.getFolk());
                statement.setString(20, hrmResource.getEnddate());

                statement.setString(21, hrmResource.getId());
                statement.setString(22, hrmResource.getPassWord());
                statement.setString(23, hrmResource.getAccounttype());
                statement.setString(24, hrmResource.getBelongto());
                statement.setString(25, hrmResource.getSystemlanguage());

                statement.setString(26, hrmResource.getCertificatenum());
                statement.executeUpdate();
                hrmResource.updaterights(hrmResource.getId());
                stnCount++;
            }
        } catch (Exception e) {
            baseBean.writeLog("insert HrmResource Exception :" + e);
        } finally {
            statement.close();
        }
    }

    public static void updateHrmResource(List<CbHrmResource> updateHrmResourceList) {

        ConnStatement statement = new ConnStatement();
        try {
            String sql = "update hrmresource set lastname = ?, status = ?, sex = ?, locationid = ?, mobile = ?," +
                    " managerid = ?,  departmentid = ?, subcompanyid1 = ?, jobtitle = ?, birthday = ?, " +
                    " certificatenum = ?, email = ?, loginid = ?, seclevel = ? where workcode = ?";
            statement.setStatementSql(sql);
            int stnCount = 0;
            for (CbHrmResource hrmResource : updateHrmResourceList) {
                if (stnCount % 500 == 0) {
                    statement.close();
                    statement = new ConnStatement();
                    statement.setStatementSql(sql);
                }
                statement.setString(1, hrmResource.getLastname());
                statement.setString(2, hrmResource.getStatus());
                statement.setString(3, hrmResource.getSex());
                statement.setString(4, hrmResource.getLocation());
                statement.setString(5, hrmResource.getMobile());

                statement.setString(6, hrmResource.getManagerIdReal());
                statement.setString(7, hrmResource.getDepId());
                statement.setString(8, hrmResource.getSubId());
                statement.setString(9, hrmResource.getJobtitleId());
                statement.setString(10, hrmResource.getBirthday());

                statement.setString(11, hrmResource.getCertificatenum());
                statement.setString(12, hrmResource.getEmail());
                statement.setString(13, hrmResource.getLoginid());
                statement.setString(14, hrmResource.getSeclevel());
                statement.setString(15, hrmResource.getWorkcode());
                statement.executeUpdate();
                stnCount++;
            }
        } catch (Exception e) {
            baseBean.writeLog("update HrmResource Exception :" + e);
        } finally {
            statement.close();
        }
    }

}
