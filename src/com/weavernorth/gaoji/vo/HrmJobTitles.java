package com.weavernorth.gaoji.vo;

import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.BaseBean;

/**
 * 岗位
 */
public class HrmJobTitles extends BaseBean {

    private static final String modelId = "15";
    private ConnStatement statement;
    private ModeRightInfo modeRightInfo = new ModeRightInfo();

    private String id;
    private String jobtitlecode;
    private String jobtitlename;
    private String jobtitlemark;
    private String jobactivityid;
    private String jobactivitiecode;
    private String jobdepartmentid;

    private String czlx;//操作类型
    private String zt;//状态
    private String sbyy;//失败原因
    /**
     * HR唯一键
     */
    private String hrguid;

    public boolean insertJobTitle() {
        boolean is_success = false;
        statement = new ConnStatement();
        try {
            String sql = "insert into HrmJobTitles (jobtitlecode, jobtitlename, jobtitlemark, jobactivityid, hrguid) values (?,?,?,?,?)";
            statement.setStatementSql(sql);
            statement.setString(1, this.getJobtitlecode());
            statement.setString(2, this.getJobtitlename());
            statement.setString(3, this.getJobtitlename());
            statement.setString(4, this.getJobactivityid());
            statement.setString(5, this.getHrguid());
            statement.executeUpdate();
            is_success = true;
        } catch (Exception e) {
            this.writeLog("insert Jobtitle Exception :" + e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                this.writeLog("insertJobtitle Exception :" + e);
            }
        }
        return is_success;
    }

    public boolean updateJobTitle() {
        boolean is_success = false;
        statement = new ConnStatement();
        try {
            String sql = "update HrmJobTitles set jobtitlecode = ?, jobtitlename = ?, jobtitlemark = ?, jobactivityid = ? " +
                    " where id = ?";
            statement.setStatementSql(sql);
            statement.setString(1, this.getJobtitlecode());
            statement.setString(2, this.getJobtitlename());
            statement.setString(3, this.getJobtitlename());
            statement.setString(4, this.getJobactivityid());
            statement.setString(5, this.getId());
            statement.executeUpdate();
            is_success = true;
        } catch (Exception e) {
            this.writeLog("update Jobtitle Exception :" + e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                this.writeLog("updateJobtitle Exception :" + e);
            }
        }
        return is_success;
    }

    public boolean deleteJobtitle() {
        boolean is_success = false;
        RecordSet rs = null;
        try {
            rs = new RecordSet();
            if (rs.executeSql("delete from HrmJobTitles where id = " + this.getId())) {
                is_success = true;
            }
        } catch (Exception e) {
            this.writeLog("deleteJobtitle Exception :" + e);
        }
        return is_success;
    }

    /**
     * 插入日志
     */
    public void insertModel() {
        statement = new ConnStatement();
        try {
            String sql = "insert into uf_gw (jobtitlecode, jobtitlename, jobactivitiecode, czlx, zt, sbyy, " +
                    "formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) " +
                    "values(?,?,?,?,?,?,  ?,?,?,?,?)";
            statement.setStatementSql(sql);
            statement.setString(1, this.getJobtitlecode());
            statement.setString(2, this.getJobtitlename());
            statement.setString(3, this.getJobactivitiecode());

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
            maxSet.executeSql("select max(id) id from uf_gw");
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

    public String getJobtitlename() {
        return jobtitlename;
    }

    public void setJobtitlename(String jobtitlename) {
        this.jobtitlename = jobtitlename;
    }

    public String getJobtitlemark() {
        return jobtitlemark;
    }

    public void setJobtitlemark(String jobtitlemark) {
        this.jobtitlemark = jobtitlemark;
    }

    public String getJobactivityid() {
        return jobactivityid;
    }

    public void setJobactivityid(String jobactivityid) {
        this.jobactivityid = jobactivityid;
    }

    public String getJobdepartmentid() {
        return jobdepartmentid;
    }

    public void setJobdepartmentid(String jobdepartmentid) {
        this.jobdepartmentid = jobdepartmentid;
    }

    public String getJobtitlecode() {
        return jobtitlecode;
    }

    public void setJobtitlecode(String jobtitlecode) {
        this.jobtitlecode = jobtitlecode;
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

    public String getJobactivitiecode() {
        return jobactivitiecode;
    }

    public void setJobactivitiecode(String jobactivitiecode) {
        this.jobactivitiecode = jobactivitiecode;
    }

    public String getHrguid() {
        return hrguid;
    }

    public void setHrguid(String hrguid) {
        this.hrguid = hrguid;
    }
}
