package com.weavernorth.gaoji.vo;

import com.weaver.general.TimeUtil;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.BaseBean;

/**
 * 公司代码（费用承担主体）
 */
public class UfWfqyzt extends BaseBean {

    private static final Integer formmodeid = 161;
    private ConnStatement statement;
    private ModeRightInfo modeRightInfo = new ModeRightInfo();
    /**
     * 主键ID
     */
    private String id;
    /**
     * 主体编码
     */
    private String bm;
    /**
     * 公司主体名称
     */
    private String wfqyzt;

    /**
     * 插入公司代码信息
     */
    public boolean insertUfWfqyzt() {
        boolean is_success = false;
        statement = new ConnStatement();
        try {
            String sql = "insert into uf_wfqyzt (bm, wfqyzt, formmodeid, modedatacreater, modedatacreatertype, modedatacreatedate, modedatacreatetime) " +
                    "values (?, ?, ?, ?, ?, ?, ?)";
            statement.setStatementSql(sql);
            statement.setString(1, bm);
            statement.setString(2, wfqyzt);
            statement.setInt(3, formmodeid);
            statement.setInt(4, 1);
            statement.setInt(5, 0);
            statement.setString(6, TimeUtil.getCurrentTimeString().substring(0, 10));
            statement.setString(7, TimeUtil.getCurrentTimeString().substring(11));
            statement.executeUpdate();

            //设置权限
            RecordSet maxSet = new RecordSet();
            modeRightInfo.setNewRight(true);
            maxSet.executeSql("select max(id) id from uf_wfqyzt where bm = '" + bm + "'");
            int maxId = 0;
            if (maxSet.next()) {
                maxId = maxSet.getInt("id");
            }
            modeRightInfo.editModeDataShare(1, formmodeid, maxId);//创建人id， 模块id， 该条数据id
            is_success = true;
        } catch (Exception e) {
            this.writeLog("insert uf_wfqyzt Exception :" + e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                this.writeLog("insert uf_wfqyzt close connect Exception :" + e);
            }
        }
        return is_success;
    }

    /**
     * 更新公司代码信息
     */
    public boolean updateUfWfqyzt() {
        boolean is_success = false;
        statement = new ConnStatement();
        try {
            String sql = "update uf_wfqyzt set bm = ?, wfqyzt = ?, modedatacreatedate = ?, modedatacreatetime = ? where id = ?";
            statement.setStatementSql(sql);
            statement.setString(1, bm);
            statement.setString(2, wfqyzt);
            statement.setString(3, TimeUtil.getCurrentTimeString().substring(0, 10));
            statement.setString(4, TimeUtil.getCurrentTimeString().substring(11));
            statement.setString(5, id);
            statement.executeUpdate();
            is_success = true;
        } catch (Exception e) {
            this.writeLog("update uf_wfqyzt Exception :" + e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                this.writeLog("update uf_wfqyzt close connect Exception :" + e);
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

    public String getBm() {
        return bm;
    }

    public void setBm(String bm) {
        this.bm = bm;
    }

    public String getWfqyzt() {
        return wfqyzt;
    }

    public void setWfqyzt(String wfqyzt) {
        this.wfqyzt = wfqyzt;
    }
}
