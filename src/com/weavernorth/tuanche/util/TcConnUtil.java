package com.weavernorth.tuanche.util;

import com.weavernorth.tuanche.orgsyn.vo.TcHrmDepartment;
import com.weavernorth.tuanche.orgsyn.vo.TcHrmJobTitles;
import com.weavernorth.tuanche.orgsyn.vo.TcHrmResource;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;

import java.util.List;

public class TcConnUtil {

    private static ModeRightInfo moderightinfo = new ModeRightInfo();
    private static BaseBean baseBean = new BaseBean();
    /**
     * 日志模块id
     */
    private static final Integer LOG_MODE_ID = 1;

    /**
     * 插入定时任务日志
     *
     * @param tableName 表名
     * @param logStr    日志信息
     * @param nums      插入数量
     */
    public static void insertTimedLog(String tableName, String logStr, int nums) {
        String currentTimeString = TimeUtil.getCurrentTimeString();
        ConnStatement statement = new ConnStatement();
        String insertSql = "insert into uf_org_log(tableName, logTxt, logNum, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime)" +
                "values (?,?,?,  ?,?,?,?,?)";
        try {
            statement.setStatementSql(insertSql);

            // 表名
            statement.setString(1, tableName);
            // 日志信息
            statement.setString(2, logStr);
            // 插入数量
            statement.setInt(3, nums);

            //模块id
            statement.setInt(4, LOG_MODE_ID);
            //创建人id
            statement.setString(5, "1");
            //一个默认值0
            statement.setString(6, "0");
            statement.setString(7, com.weaver.general.TimeUtil.getCurrentTimeString().substring(0, 10));
            statement.setString(8, com.weaver.general.TimeUtil.getCurrentTimeString().substring(11));
            statement.executeUpdate();
        } catch (Exception e) {
            baseBean.writeLog("插入定时任务日志, insertTimedLog 异常： " + e);
        } finally {
            statement.close();
            //赋权
            moderightinfo.setNewRight(true);
            RecordSet maxSet = new RecordSet();
            maxSet.executeSql("select id from uf_org_log where MODEDATACREATEDATE + MODEDATACREATETIME >= '" + TimeUtil.timeAdd(currentTimeString, -10) + "'");

            int maxId;
            while (maxSet.next()) {
                maxId = maxSet.getInt("id");
                // 创建人id， 模块id， 该条数据id
                moderightinfo.editModeDataShare(1, LOG_MODE_ID, maxId);
            }
        }
        statement.close();
    }

    /**
     * 插入部门信息
     */
    public static void insertHrmDepartment(List<TcHrmDepartment> insertHrmDepartments) {
        ConnStatement statement = new ConnStatement();
        try {
            String sql = "insert into HrmDepartment (departmentcode, departmentname, departmentmark," +
                    " subcompanyid1, supdepid, canceled, showorder) " +
                    "values (?,?,?,?,?,  ?,?)";
            statement.setStatementSql(sql);
            for (TcHrmDepartment department : insertHrmDepartments) {
                statement.setString(1, department.getDepcode());
                statement.setString(2, department.getDepname());
                statement.setString(3, department.getDepname());
                statement.setString(4, department.getSupsubid());
                statement.setString(5, department.getSupdepid());
                statement.setString(6, department.getStatus());
                statement.setString(7, department.getShoworder());
                statement.executeUpdate();
                baseBean.writeLog("部门创建成功 - 部门编码 - " + department.getDepcode() + " - 部门名称 - " + department.getDepname());
            }
        } catch (Exception e) {
            baseBean.writeLog("insert department Exception :" + e);
        } finally {
            statement.close();
        }

    }

    /**
     * 更新部门
     */
    public static void updateHrmDepartment(List<TcHrmDepartment> insertHrmDepartments) {
        ConnStatement statement = new ConnStatement();
        try {
            String sql = "update HrmDepartment set  departmentname = ?, departmentmark = ?, subcompanyid1 = ?, supdepid = ?," +
                    " canceled = ? where departmentcode = ?";
            statement.setStatementSql(sql);
            for (TcHrmDepartment hrmDepartment : insertHrmDepartments) {
                statement.setString(1, hrmDepartment.getDepname());
                statement.setString(2, hrmDepartment.getDepname());
                statement.setString(3, hrmDepartment.getSupsubid());
                statement.setString(4, hrmDepartment.getSupdepid());
                statement.setString(5, hrmDepartment.getStatus());

                statement.setString(6, hrmDepartment.getDepcode());
                statement.executeUpdate();
                baseBean.writeLog("部门更新成功 - 部门编码 - " + hrmDepartment.getDepcode() + " - 部门名称 - " + hrmDepartment.getDepname());
            }
        } catch (Exception e) {
            baseBean.writeLog("update department Exception :" + e);
        } finally {
            statement.close();
        }

    }

    /**
     * 新增岗位
     */
    public static void insertJobTitle(List<TcHrmJobTitles> insertHrmJobTitlesList) {
        ConnStatement statement = new ConnStatement();
        try {
            String sql = "insert into HrmJobTitles (jobtitlecode, jobtitlename, jobtitlemark, jobactivityid) values (?,?,?,?)";
            statement.setStatementSql(sql);
            for (TcHrmJobTitles hrmJobTitles : insertHrmJobTitlesList) {
                statement.setString(1, hrmJobTitles.getJobtitlecode());
                statement.setString(2, hrmJobTitles.getJobtitlename());
                statement.setString(3, hrmJobTitles.getJobtitlename());
                statement.setString(4, hrmJobTitles.getJobactivityid());
                statement.executeUpdate();
                baseBean.writeLog("岗位创建成功，岗位编码为-----" + hrmJobTitles.getJobtitlecode()
                        + " --  岗位名称为--" + hrmJobTitles.getJobtitlename());
            }
        } catch (Exception e) {
            baseBean.writeLog("insert Jobtitle Exception :" + e);
        } finally {
            statement.close();
        }

    }

    /**
     * 更新岗位
     */
    public static void updateJobTitle(List<TcHrmJobTitles> updateHrmJobTitlesList) {
        ConnStatement statement = new ConnStatement();
        try {
            String sql = "update HrmJobTitles set jobtitlename = ?, jobtitlemark = ?, jobactivityid = ?  where id = ?";
            statement.setStatementSql(sql);
            for (TcHrmJobTitles jobTitles : updateHrmJobTitlesList) {
                statement.setString(1, jobTitles.getJobtitlename());
                statement.setString(2, jobTitles.getJobtitlename());
                statement.setString(3, jobTitles.getJobactivityid());
                statement.setString(4, jobTitles.getId());
                statement.executeUpdate();
                baseBean.writeLog("岗位更新成功，岗位编码为-----" + jobTitles.getJobtitlecode() + " --  岗位名称为--"
                        + jobTitles.getJobtitlename());
            }
        } catch (Exception e) {
            baseBean.writeLog("update 岗位 Exception :" + e);
        } finally {
            statement.close();
        }

    }

    public static void insertHrmResource(List<TcHrmResource> insertHrmResourceList) {
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
            for (TcHrmResource hrmResource : insertHrmResourceList) {
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
                baseBean.writeLog("---------------------------");
                baseBean.writeLog("人员code: " + hrmResource.getWorkcode());
                baseBean.writeLog("人员名称: " + hrmResource.getLastname());
                baseBean.writeLog("人员上级code: " + hrmResource.getManagerCode());
                baseBean.writeLog("所属部门code: " + hrmResource.getDepcode());
                baseBean.writeLog("所属岗位code: " + hrmResource.getJobtitlecode());
                baseBean.writeLog("工作地点: " + hrmResource.getLocation());
                baseBean.writeLog("提示消息：人员创建成功------ id = " + hrmResource.getId());
                stnCount++;
            }
        } catch (Exception e) {
            baseBean.writeLog("insert HrmResource Exception :" + e);
        } finally {
            statement.close();
        }
    }

    public static void updateHrmResource(List<TcHrmResource> updateHrmResourceList) {

        ConnStatement statement = new ConnStatement();
        try {
            String sql = "update hrmresource set lastname = ?, status = ?, sex = ?, locationid = ?, mobile = ?,"
                    + " managerid = ?, joblevel = ?, departmentid = ?, subcompanyid1 = ?, jobtitle = ?,"
                    + " birthday = ?, startdate = ?, dsporder = ?, folk = ?, enddate = ?, "
                    + "certificatenum = ? where workcode = ?";
            statement.setStatementSql(sql);
            int stnCount = 0;
            for (TcHrmResource hrmResource : updateHrmResourceList) {
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
                statement.setString(7, hrmResource.getJoblevel());
                statement.setString(8, hrmResource.getDepId());
                statement.setString(9, hrmResource.getSubId());
                statement.setString(10, hrmResource.getJobtitleId());

                statement.setString(11, hrmResource.getBirthday());
                statement.setString(12, hrmResource.getStartdate());
                statement.setString(13, hrmResource.getDsporder());
                statement.setString(14, hrmResource.getFolk());

                statement.setString(15, hrmResource.getEnddate());
                statement.setString(16, hrmResource.getCertificatenum());
                statement.setString(17, hrmResource.getWorkcode());
                statement.executeUpdate();

                baseBean.writeLog("---------------------------");
                baseBean.writeLog("人员code: " + hrmResource.getWorkcode());
                baseBean.writeLog("人员名称: " + hrmResource.getLastname());
                baseBean.writeLog("人员上级code: " + hrmResource.getManagerCode());
                baseBean.writeLog("所属部门code: " + hrmResource.getDepcode());
                baseBean.writeLog("所属岗位code: " + hrmResource.getJobtitlecode());
                baseBean.writeLog("工作地点: " + hrmResource.getLocation());
                baseBean.writeLog("提示消息：人员更新成功------ id = " + hrmResource.getId());
                stnCount++;
            }
        } catch (Exception e) {
            baseBean.writeLog("update HrmResource Exception :" + e);
        } finally {
            statement.close();
        }
    }
}
