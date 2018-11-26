package com.weavernorth.gaoji.vo;

import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.BaseBean;
import weaver.general.Util;

/**
 * 职务类别
 */
public class HrmJobGroups extends BaseBean {

    private static final String modelId = "13";
    private ConnStatement statement;
    private ModeRightInfo modeRightInfo = new ModeRightInfo();

    private String id;
    private String jobgroupname;
    private String jobgroupremark;
    private String jobgrouprcode;

    private String czlx;//操作类型
    private String zt;//状态
    private String sbyy;//失败原因


    public String insertJobGroups() {
        String returnValue = "";
        statement = new ConnStatement();
        try {
            String sql = "insert into HrmJobGroups (jobgroupname, jobgroupremark, jobgroupcode) values (?, ?, ?)";
            statement.setStatementSql(sql);
            statement.setString(1, this.getJobgroupname());
            statement.setString(2, this.getJobgroupremark());
            statement.setString(3, this.getJobgrouprcode());
            statement.executeUpdate();

        } catch (Exception e) {
            this.writeLog("insert JobGroups Exception :" + e);
        } finally {
            try {
                statement.close();
                returnValue = this.getJobGroupsMaxId();
            } catch (Exception e) {
                this.writeLog("insert JobGroups close connect Exception :" + e);
            }
        }
        return returnValue;
    }

    public void updateJobGroups() {
        statement = new ConnStatement();
        try {
            String sql = "update HrmJobGroups set JOBGROUPNAME = ?, JOBGROUPREMARK = ? where id = ?";
            statement.setStatementSql(sql);
            statement.setString(1, this.getJobgroupname());
            statement.setString(2, this.getJobgroupname());
            statement.setString(3, this.getId());
            statement.executeUpdate();
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

    public String getJobGroupsIdByCode(String jobgroupscode) {
        String actid = "";
        if (null == jobgroupscode || "".equals(jobgroupscode)) {
            return actid;
        }
        try {
            RecordSet rs = new RecordSet();
            rs.executeSql("select id from HrmJobGroups where jobgroupcode = '" + jobgroupscode + "'");
            if (rs.next()) {
                actid = Util.null2String(rs.getString("id"));
            }
        } catch (Exception e) {
            this.writeLog("getJobGroupsIdByCode Exception :" + e);
        }
        return actid;
    }

    private String getJobGroupsMaxId() {
        String returnValue = "";
        try {
            RecordSet rs = new RecordSet();
            rs.executeSql("select max(id) as maxid from HrmJobGroups");
            if (rs.next()) {
                returnValue = Util.null2String(rs.getString("maxid"));
            }
        } catch (Exception e) {
            this.writeLog("getJobGroupsMaxId Exception :" + e);
        }
        return returnValue;
    }

    public boolean deleteJobGroups() {

        boolean is_success = false;
        RecordSet rs = null;
        try {
            rs = new RecordSet();
            if (rs.executeSql("delete from HrmJobGroups where id = " + this.getId())) {
                is_success = true;
            }
        } catch (Exception e) {
            this.writeLog("deleteJobGroups Exception :" + e);
        }
        return is_success;

    }

    /**
     * 插入日志
     */
    public void insertModel() {
        statement = new ConnStatement();
        try {
            String sql = "insert into uf_zwlb(jobgroupcode, jobgroupname, czlx, zt, sbyy, " +
                    "formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) " +
                    "values(?,?,?,?,?, ?,?,?,?,?)";
            statement.setStatementSql(sql);
            statement.setString(1, this.getJobgrouprcode());
            statement.setString(2, this.getJobgroupname());
            statement.setString(3, this.getCzlx());
            statement.setString(4, this.getZt());
            statement.setString(5, this.getSbyy());

            statement.setString(6, modelId);//模块id
            statement.setString(7, "1");//创建人id
            statement.setString(8, "0");//一个默认值0
            statement.setString(9, com.weaver.general.TimeUtil.getCurrentTimeString().substring(0, 10));
            statement.setString(10, com.weaver.general.TimeUtil.getCurrentTimeString().substring(11));
            statement.executeUpdate();

            //设置权限
            RecordSet maxSet = new RecordSet();
            modeRightInfo.setNewRight(true);
            maxSet.executeSql("select max(id) id from uf_zwlb");
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

    public String getJobgroupname() {
        return jobgroupname;
    }

    public void setJobgroupname(String jobgroupname) {
        this.jobgroupname = jobgroupname;
    }

    public String getJobgroupremark() {
        return jobgroupremark;
    }

    public void setJobgroupremark(String jobgroupremark) {
        this.jobgroupremark = jobgroupremark;
    }

    public String getJobgrouprcode() {
        return jobgrouprcode;
    }

    public void setJobgrouprcode(String jobgrouprcode) {
        this.jobgrouprcode = jobgrouprcode;
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
}
