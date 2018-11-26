package com.weavernorth.gaoji.vo;

import com.weaver.general.TimeUtil;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.BaseBean;

/**
 * 公司代码（费用承担主体）
 */
public class UfWfqybm extends BaseBean {

    private static final Integer formmodeid = 3242;
    private ConnStatement statement;
    private ModeRightInfo modeRightInfo = new ModeRightInfo();
    /**
     * 主键ID
     */
    private String id;
    /**
     * 父编码
     */
    private String companycode;
    /**
     * 编码
     */
    private String code;
    /**
     * 机构全称
     */
    private String fullname;
    /**
     * 可用状态 可用：1  非可用：0
     */
    private String status;
    /**
     * HR唯一键
     */
    private String hrguid;

    /**
     * 插入成本中心信息
     */
    public boolean insertUfWfqybm() {
        boolean is_success = false;
        statement = new ConnStatement();
        try {
            String sql = "insert into uf_wfqybm (hrguid, companycode, code, fullname, status, formmodeid, modedatacreater, modedatacreatertype, modedatacreatedate, modedatacreatetime) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            statement.setStatementSql(sql);
            statement.setString(1, hrguid);
            statement.setString(2, companycode);
            statement.setString(3, code);
            statement.setString(4, fullname);
            statement.setString(5, status);
            statement.setInt(6, formmodeid);
            statement.setInt(7, 1);
            statement.setInt(8, 0);
            statement.setString(9, TimeUtil.getCurrentTimeString().substring(0, 10));
            statement.setString(10, TimeUtil.getCurrentTimeString().substring(11));
            statement.executeUpdate();

            //设置权限
            RecordSet maxSet = new RecordSet();
            modeRightInfo.setNewRight(true);
            maxSet.executeSql("select max(id) id from uf_wfqybm where hrguid = '" + hrguid + "'");
            int maxId = 0;
            if (maxSet.next()) {
                maxId = maxSet.getInt("id");
            }
            modeRightInfo.editModeDataShare(1, formmodeid, maxId);//创建人id， 模块id， 该条数据id
            is_success = true;
        } catch (Exception e) {
            this.writeLog("insert uf_wfqybm Exception :" + e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                this.writeLog("insert uf_wfqybm close connect Exception :" + e);
            }
        }
        return is_success;
    }

    /**
     * 更新成本中心信息
     */
    public boolean updateUfWfqybm() {
        boolean is_success = false;
        statement = new ConnStatement();
        try {
            String sql = "update uf_wfqybm set companycode = ?, code = ?, fullname = ?, status = ?, modedatacreatedate = ?, modedatacreatetime = ? where id = ?";
            statement.setStatementSql(sql);
            statement.setString(1, companycode);
            statement.setString(2, code);
            statement.setString(3, fullname);
            statement.setString(4, status);
            statement.setString(5, TimeUtil.getCurrentTimeString().substring(0, 10));
            statement.setString(6, TimeUtil.getCurrentTimeString().substring(11));
            statement.setString(7, id);
            statement.executeUpdate();
            is_success = true;
        } catch (Exception e) {
            this.writeLog("update uf_wfqybm Exception :" + e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                this.writeLog("update uf_wfqybm close connect Exception :" + e);
            }
        }
        return is_success;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHrguid() {
        return hrguid;
    }

    public void setHrguid(String hrguid) {
        this.hrguid = hrguid;
    }
}
