package com.weavernorth.gaoji.vo;

import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.BaseBean;
import weaver.general.Util;

/**
 * 职务
 */
public class SynHrmJobActivities extends BaseBean {

    private static final String modelId = "14";
    private ConnStatement statement;
    private ModeRightInfo modeRightInfo = new ModeRightInfo();

    private String id;
    private String jobactivityname;
    private String jobactivitymark;
    private String jobgroupid;
    private String jobcode;
    private String jobgroupcode;

    private String czlx;//操作类型
    private String zt;//状态
    private String sbyy;//失败原因

    public String insertActivities() {
        String returnValue = "";
        statement = new ConnStatement();
        try {
            String sql = "insert into HrmJobActivities (jobactivityname, jobactivitymark, jobgroupid, jobcode) values (?, ? , ?, ?)";
            statement.setStatementSql(sql);
            statement.setString(1, this.getJobactivityname());
            statement.setString(2, this.getJobactivitymark());
            statement.setString(3, this.getJobgroupid());
            statement.setString(4, this.getJobcode());
            statement.executeUpdate();

        } catch (Exception e) {
            this.writeLog("insert Activities Exception :" + e);
        } finally {
            try {
                statement.close();
                returnValue = this.getActivitiesMaxId();
            } catch (Exception e) {
                this.writeLog("insert Activities close connect Exception :" + e);
            }
        }
        return returnValue;
    }

    public void updateActivities() {
        statement = new ConnStatement();
        try {
            String sql = "update HrmJobActivities set jobactivityname = ?, jobactivitymark = ?, jobgroupid = ? where id = ?";
            statement.setStatementSql(sql);
            statement.setString(1, this.getJobactivityname());
            statement.setString(2, this.getJobactivitymark());
            statement.setString(3, this.getJobgroupid());
            statement.setString(4, this.getId());
            statement.executeUpdate();

        } catch (Exception e) {
            this.writeLog("updateActivities Exception :" + e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                this.writeLog("updateActivities close connect Exception :" + e);
            }
        }
    }

    public String getActivitiesIdByCode(String acticode) {
        String actid = "";
        if (null == acticode || "".equals(acticode)) {
            return actid;
        }
        try {
            RecordSet rs = new RecordSet();
            rs.executeSql("select id from HrmJobActivities where jobcode = '" + acticode + "'");
            if (rs.next()) {
                actid = Util.null2String(rs.getString("id"));
            }

        } catch (Exception e) {
            this.writeLog("getActivitiesIdByCode Exception :" + e);
        }
        return actid;
    }

    public String getActivitiesMaxId() {
        String returnValue = "";
        try {
            RecordSet rs = new RecordSet();
            rs.executeSql("select max(id) as maxid from HrmJobActivities");
            if (rs.next()) {
                returnValue = Util.null2String(rs.getString("maxid"));
            }
        } catch (Exception e) {
            this.writeLog("getActivitiesMaxId Exception :" + e);
        }
        return returnValue;
    }

    public void truncateActivities() {
        try {
            RecordSet rs = new RecordSet();
            rs.executeSql("truncate table hrmjobactivities");
        } catch (Exception e) {
            this.writeLog("truncateActivities Exception :" + e);
        }
    }

    public boolean deleteActivities() {
        boolean is_success = false;
        RecordSet rs = null;
        try {
            rs = new RecordSet();
            if (rs.executeSql("delete from HrmJobActivities where id = " + this.getId())) {
                is_success = true;
            }
        } catch (Exception e) {
            this.writeLog("deleteActivities Exception :" + e);
        }
        return is_success;
    }

    /**
     * 插入日志
     */
    public void insertModel() {
        statement = new ConnStatement();
        try {
            String sql = "insert into uf_zw (jobcode, jobactivitiename, jobgroupcode, czlx, zt, sbyy, " +
                    "formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) " +
                    "values(?,?,?,?,?,?, ?,?,?,?,?)";
            statement.setStatementSql(sql);
            statement.setString(1, this.getJobcode());
            statement.setString(2, this.getJobactivityname());
            statement.setString(3, this.getJobgroupcode());

            statement.setString(4, this.getCzlx());
            statement.setString(5, this.getZt());
            statement.setString(6, this.getSbyy());

            statement.setString(7, modelId);//模块id
            statement.setString(8, "1");//创建人id
            statement.setString(9, "0");//一个默认值0
            statement.setString(10, com.weaver.general.TimeUtil.getCurrentTimeString().substring(0, 10));
            statement.setString(11, com.weaver.general.TimeUtil.getCurrentTimeString().substring(11));
            statement.executeUpdate();

            //设置权限
            RecordSet maxSet = new RecordSet();
            modeRightInfo.setNewRight(true);
            maxSet.executeSql("select max(id) id from uf_zw");
            int maxId = 0;
            if (maxSet.next()) {
                maxId = maxSet.getInt("id");
            }
            modeRightInfo.editModeDataShare(1, Integer.parseInt(modelId), maxId);//创建人id， 模块id， 该条数据id
        } catch (Exception e) {
            this.writeLog("updateJobGroups Exception :" + e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                this.writeLog("updateJobGroups close connect Exception :" + e);
            }
        }
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobactivityname() {
        return jobactivityname;
    }

    public void setJobactivityname(String jobactivityname) {
        this.jobactivityname = jobactivityname;
    }

    public String getJobactivitymark() {
        return jobactivitymark;
    }

    public void setJobactivitymark(String jobactivitymark) {
        this.jobactivitymark = jobactivitymark;
    }

    public String getJobgroupid() {
        return jobgroupid;
    }

    public void setJobgroupid(String jobgroupid) {
        this.jobgroupid = jobgroupid;
    }

    public String getJobcode() {
        return jobcode;
    }

    public void setJobcode(String jobcode) {
        this.jobcode = jobcode;
    }

    public String getCzlx() {
        return czlx;
    }

    public void setCzlx(String czlx) {
        this.czlx = czlx;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getSbyy() {
        return sbyy;
    }

    public void setSbyy(String sbyy) {
        this.sbyy = sbyy;
    }

    public String getJobgroupcode() {
        return jobgroupcode;
    }

    public void setJobgroupcode(String jobgroupcode) {
        this.jobgroupcode = jobgroupcode;
    }
}
