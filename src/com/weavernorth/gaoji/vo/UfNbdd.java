package com.weavernorth.gaoji.vo;

import com.weaver.general.TimeUtil;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.BaseBean;

/**
 * 内部订单（费用承担项目）
 */
public class UfNbdd extends BaseBean {

    private static final Integer formmodeid = 1241;
    private ConnStatement statement;
    private ModeRightInfo modeRightInfo = new ModeRightInfo();
    /**
     * 主键ID
     */
    private String id;
    /**
     * 内部订单编码
     */
    private String code;
    /**
     * 内部订单名称
     */
    private String value;

    /**
     * 插入内部订单信息
     */
    public boolean insertUfNbdd() {
        boolean is_success = false;
        statement = new ConnStatement();
        try {
            String sql = "insert into uf_nbdd (code, value, formmodeid, modedatacreater, modedatacreatertype, modedatacreatedate, modedatacreatetime) " +
                    "values (?, ?, ?, ?, ?, ?, ?)";
            statement.setStatementSql(sql);
            statement.setString(1, code);
            statement.setString(2, value);
            statement.setInt(3, formmodeid);
            statement.setInt(4, 1);
            statement.setInt(5, 0);
            statement.setString(6, TimeUtil.getCurrentTimeString().substring(0, 10));
            statement.setString(7, TimeUtil.getCurrentTimeString().substring(11));
            statement.executeUpdate();

            //设置权限
            RecordSet maxSet = new RecordSet();
            modeRightInfo.setNewRight(true);
            maxSet.executeSql("select max(id) id from uf_nbdd where code ='" + code + "'");
            int maxId = 0;
            if (maxSet.next()) {
                maxId = maxSet.getInt("id");
            }
            modeRightInfo.editModeDataShare(1, formmodeid, maxId);//创建人id， 模块id， 该条数据id
            is_success = true;
        } catch (Exception e) {
            this.writeLog("insert uf_nbdd Exception :" + e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                this.writeLog("insert uf_nbdd close connect Exception :" + e);
            }
        }
        return is_success;
    }

    /**
     * 更新内部订单信息
     */
    public boolean updateUfNbdd() {
        boolean is_success = false;
        statement = new ConnStatement();
        try {
            String sql = "update uf_nbdd set code = ?, value = ?, modedatacreatedate = ?, modedatacreatetime = ? where id = ?";
            statement.setStatementSql(sql);
            statement.setString(1, code);
            statement.setString(2, value);
            statement.setString(3, TimeUtil.getCurrentTimeString().substring(0, 10));
            statement.setString(4, TimeUtil.getCurrentTimeString().substring(11));
            statement.setString(5, id);
            statement.executeUpdate();
            is_success = true;
        } catch (Exception e) {
            this.writeLog("update uf_nbdd Exception :" + e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                this.writeLog("update uf_nbdd close connect Exception :" + e);
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
