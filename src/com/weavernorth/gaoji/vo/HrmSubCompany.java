package com.weavernorth.gaoji.vo;

import com.weaver.general.TimeUtil;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.BaseBean;

/**
 * 分部
 */
public class HrmSubCompany extends BaseBean {

    private static final String modelId = "16";
    private ConnStatement statement;
    /**
     * 分部id
     */
    private String id;
    /**
     * 分部编码
     */
    private String subcode;
    /**
     * 分部名称
     */
    private String subname;
    /**
     * 上级分部编码
     */
    private String supsubcode;
    /**
     * 上级分部id
     */
    private String supsubcomid;

    /**
     * 封存标识 1：封存
     */
    private String status;
    /**
     * 显示顺序
     */
    private String showorder;
    /**
     * 生效日期
     */
    private String effectivedate;
    /**
     * 是否门店
     */
    private String sfmd;
    /**
     * 是否行政机构
     */
    private String sfxzjg;
    /**
     * 层级
     */
    private String grade;

    private String czlx;//操作类型
    private String zt;//状态
    private String sbyy;//失败原因
    /**
     * HR唯一键
     */
    private String hrguid;

    /**
     * 插入分部信息
     */
    public void insertHrmSubCompany() {
        statement = new ConnStatement();
        try {
            String sql = "insert into HrmSubCompany (hrguid, subcompanycode, subcompanyname, subcompanydesc, supsubcomid, canceled, showorder, companyid)" +
                    " values (?, ?, ?, ?, ?, ?, ?, ?)";
            statement.setStatementSql(sql);
            statement.setString(1, this.hrguid);
            statement.setString(2, this.subcode);
            statement.setString(3, this.subname);
            statement.setString(4, this.subname);
            statement.setString(5, this.supsubcomid);
            statement.setString(6, this.status);
            statement.setString(7, this.showorder);
            statement.setString(8, "1");
            statement.executeUpdate();
            //插入或更新自定义字段
            insertOrUpdateHrmSubcompanyDefined();
        } catch (Exception e) {
            this.writeLog("insert SubCompany Exception :" + e);
        } finally {
            statement.close();
        }
    }

    /**
     * 更新分部信息
     */
    public void updateHrmSubCompany() {

        statement = new ConnStatement();
        try {
            String sql = "update HrmSubCompany set subcompanyname = ?, subcompanydesc = ?, supsubcomid = ?, canceled = ?, " +
                    "showorder = ?, subcompanycode = ? where id = ?";
            statement.setStatementSql(sql);
            statement.setString(1, this.subname);
            statement.setString(2, this.subname);
            statement.setString(3, this.supsubcomid);
            statement.setString(4, this.status);
            statement.setString(5, this.showorder);
            statement.setString(6, this.subcode);
            statement.setString(7, this.id);
            statement.executeUpdate();
            //插入或更新自定义字段
            insertOrUpdateHrmSubcompanyDefined();
        } catch (Exception e) {
            this.writeLog("update SubCompany Exception :" + e);
        } finally {
            statement.close();
        }
    }

    private void insertOrUpdateHrmSubcompanyDefined() {
        RecordSet recordSet = new RecordSet();
        recordSet.execute("select * from hrmsubcompanydefined where subcomid = '" + this.id + "'");
        if (recordSet.next()) {
            updateHrmSubcompanyDefined();
        } else {
            insertHrmSubcompanyDefined();
        }
    }


    /**
     * 插入分部自定义表
     */
    private void insertHrmSubcompanyDefined() {
        ConnStatement statement = new ConnStatement();
        try {
            String sql = "insert into hrmsubcompanydefined (subcomid, sfmd, sfxzjg) values (?, ?, ?)";
            statement.setStatementSql(sql);
            statement.setString(1, this.id);
            statement.setString(2, this.sfmd);
            statement.setString(3, this.sfxzjg);
            statement.executeUpdate();
        } catch (Exception e) {
            this.writeLog("insert hrmsubcompanydefined Exception :" + e);
        } finally {
            statement.close();
        }
    }

    /**
     * 更新分部自定义表
     */
    private void updateHrmSubcompanyDefined() {
        ConnStatement statement = new ConnStatement();
        try {
            String sql = "update hrmsubcompanydefined set sfmd = ?, sfxzjg = ? where subcomid = ?";
            statement.setStatementSql(sql);
            statement.setString(1, this.sfmd);
            statement.setString(2, this.sfxzjg);
            statement.setString(3, this.id);
            statement.executeUpdate();
        } catch (Exception e) {
            this.writeLog("update hrmsubcompanydefined Exception :" + e);
        } finally {
            statement.close();
        }
    }


    /**
     * 插入日志
     */
    public void insertModel() {
        statement = new ConnStatement();
        try {
            String sql = "insert into uf_fb (subcode, subname, supsubcode, status, showorder, effectivedate, czlx, zt, sbyy," +
                    "formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime)" +
                    " values (?, ?, ?, ?, ?,  ?, ?, ?, ?,  ?,?,?,?,?)";
            statement.setStatementSql(sql);
            statement.setString(1, this.subcode);
            statement.setString(2, this.subname);
            statement.setString(3, this.supsubcode);
            statement.setString(4, this.status);
            statement.setString(5, this.showorder);
            statement.setString(6, this.effectivedate);
            statement.setString(7, this.czlx);
            statement.setString(8, this.zt);
            statement.setString(9, this.sbyy);

            statement.setString(10, modelId);//模块id
            statement.setString(11, "1");//创建人id
            statement.setString(12, "0");//一个默认值0
            statement.setString(13, TimeUtil.getCurrentTimeString().substring(0, 10));
            statement.setString(14, TimeUtil.getCurrentTimeString().substring(11));
            statement.executeUpdate();
            //设置主表权限
            RecordSet recordSet = new RecordSet();
            recordSet.executeSql("select max(id) id from uf_fb");
            int maxId = 0;
            if (recordSet.next()) {
                maxId = recordSet.getInt("id");
            }
            ModeRightInfo modeRightInfo = new ModeRightInfo();
            modeRightInfo.setNewRight(true);
            modeRightInfo.editModeDataShare(1, Integer.parseInt(modelId), maxId);//创建人id， 模块id， 该条数据id
        } catch (Exception e) {
            this.writeLog("insert SubCompany Exception :" + e);
        } finally {
            statement.close();
        }
    }

    public void insertMenuConfig(String id) {
        try {
            RecordSet rs = new RecordSet();
            rs.executeSql("insert into leftmenuconfig (userid,infoid,visible,viewindex,resourceid,resourcetype,locked,lockedbyid,usecustomname,customname,customname_e)  select  distinct  userid,infoid,visible,viewindex," + id + ",2,locked,lockedbyid,usecustomname,customname,customname_e from leftmenuconfig  where resourcetype=1  and resourceid=1");
            rs.executeSql("insert into mainmenuconfig (userid,infoid,visible,viewindex,resourceid,resourcetype,locked,lockedbyid,usecustomname,customname,customname_e)  select  distinct  userid,infoid,visible,viewindex," + id + ",2,locked,lockedbyid,usecustomname,customname,customname_e from mainmenuconfig where resourcetype=1  and resourceid=1");
        } catch (Exception e) {
            this.writeLog("insert Menuconfig Exception :" + e);
        }
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSfxzjg() {
        return sfxzjg;
    }

    public void setSfxzjg(String sfxzjg) {
        this.sfxzjg = sfxzjg;
    }

    public String getSfmd() {
        return sfmd;
    }

    public void setSfmd(String sfmd) {
        this.sfmd = sfmd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubcode() {
        return subcode;
    }

    public void setSubcode(String subcode) {
        this.subcode = subcode;
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    public String getSupsubcode() {
        return supsubcode;
    }

    public void setSupsubcode(String supsubcode) {
        this.supsubcode = supsubcode;
    }

    public String getSupsubcomid() {
        return supsubcomid;
    }

    public void setSupsubcomid(String supsubcomid) {
        this.supsubcomid = supsubcomid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShoworder() {
        return showorder;
    }

    public void setShoworder(String showorder) {
        this.showorder = showorder;
    }

    public String getEffectivedate() {
        return effectivedate;
    }

    public void setEffectivedate(String effectivedate) {
        this.effectivedate = effectivedate;
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

    public String getHrguid() {
        return hrguid;
    }

    public void setHrguid(String hrguid) {
        this.hrguid = hrguid;
    }
}
