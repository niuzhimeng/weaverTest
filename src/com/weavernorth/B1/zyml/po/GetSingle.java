package com.weavernorth.B1.zyml.po;

import com.weaver.general.BaseBean;
import com.weaver.general.TimeUtil;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;

/**
 * 获取单个资源目录字段信息
 */
public class GetSingle {

    private static final String modelId = "3";

    private String id;
    private String item_name;//信息项名称
    private String data_type;//数据类型
    private String table_name;//对应表名称
    private String share_type;//共享类型
    private String field_length;//长度
    private String share_condition;//共享条件
    private String share_mode;//共享方式
    private String isopen_society;//是否向社会开放
    private String updatecycle;//更新周期
    private String publishdate;//发布时间

    private BaseBean baseBean = new BaseBean();

    public String insert() {
        RecordSet maxSet = new RecordSet();
        //设置权限
        ModeRightInfo ModeRightInfo = new ModeRightInfo();
        ModeRightInfo.setNewRight(true);
        ConnStatement statement = new ConnStatement();
        String sql = "insert into uf_getFields(my_id, item_name, data_type, table_name, share_type," +
                "field_length, share_condition, share_mode, isopen_society, updatecycle, " +
                "publishdate," +
                "formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) values(?,?,?,?,?, ?,?,?,?,?, ?,  ?,?,?,?,?)";
        try {
            statement.setStatementSql(sql);
            statement.setString(1, id);
            statement.setString(2, item_name);
            statement.setString(3, data_type);
            statement.setString(4, table_name);
            statement.setString(5, share_type);

            statement.setString(6, field_length);
            statement.setString(7, share_condition);
            statement.setString(8, share_mode);
            statement.setString(9, isopen_society);
            statement.setString(10, updatecycle);

            statement.setString(11, publishdate);

            statement.setString(12, modelId);//模块id
            statement.setString(13, "1");//创建人id
            statement.setString(14, "0");//一个默认值0
            statement.setString(15, TimeUtil.getCurrentTimeString().substring(0, 10));
            statement.setString(16, TimeUtil.getCurrentTimeString().substring(11));
            statement.executeUpdate();

            maxSet.executeSql("select max(id) id from uf_getFields");
            int maxId = 0;
            if (maxSet.next()) {
                maxId = maxSet.getInt("id");
            }
            ModeRightInfo.editModeDataShare(1, Integer.parseInt(modelId), maxId);//创建人id， 模块id， 该条数据id
            return String.valueOf(maxId);
        } catch (Exception e) {
            baseBean.writeLog("获取单个资源目录字段信息插入异常： " + e);
            baseBean.writeLog("插入异常数据： " + this.toString());
        } finally {
            statement.close();
        }
        return "";
    }

    public String update() {
        BaseBean baseBean = new BaseBean();
        ConnStatement statement = new ConnStatement();
        String sql = "update uf_getFields set item_name = ?, data_type = ?, table_name = ?, share_type = ?," +
                "field_length = ?, share_condition = ?, share_mode = ?, isopen_society = ?, updatecycle = ?," +
                "publishdate = ?  where my_id = ?";
        try {
            statement.setStatementSql(sql);
            statement.setString(1, item_name);
            statement.setString(2, data_type);
            statement.setString(3, table_name);
            statement.setString(4, share_type);
            statement.setString(5, field_length);

            statement.setString(6, share_condition);
            statement.setString(7, share_mode);
            statement.setString(8, isopen_society);
            statement.setString(9, updatecycle);
            statement.setString(10, publishdate);

            statement.setString(11, id);

            return String.valueOf(id);
        } catch (Exception e) {
            baseBean.writeLog("获取单个资源目录字段信息更新异常： " + e);
            baseBean.writeLog("更新异常数据： " + this.toString());
        } finally {
            statement.close();
        }
        return "";
    }

    @Override
    public String toString() {
        return "GetSingle{" +
                "id='" + id + '\'' +
                ", item_name='" + item_name + '\'' +
                ", data_type='" + data_type + '\'' +
                ", table_name='" + table_name + '\'' +
                ", share_type='" + share_type + '\'' +
                ", field_length='" + field_length + '\'' +
                ", share_condition='" + share_condition + '\'' +
                ", share_mode='" + share_mode + '\'' +
                ", isopen_society='" + isopen_society + '\'' +
                ", updatecycle='" + updatecycle + '\'' +
                ", publishdate='" + publishdate + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getShare_type() {
        return share_type;
    }

    public void setShare_type(String share_type) {
        this.share_type = share_type;
    }

    public String getField_length() {
        return field_length;
    }

    public void setField_length(String field_length) {
        this.field_length = field_length;
    }

    public String getShare_condition() {
        return share_condition;
    }

    public void setShare_condition(String share_condition) {
        this.share_condition = share_condition;
    }

    public String getShare_mode() {
        return share_mode;
    }

    public void setShare_mode(String share_mode) {
        this.share_mode = share_mode;
    }

    public String getIsopen_society() {
        return isopen_society;
    }

    public void setIsopen_society(String isopen_society) {
        this.isopen_society = isopen_society;
    }

    public String getUpdatecycle() {
        return updatecycle;
    }

    public void setUpdatecycle(String updatecycle) {
        this.updatecycle = updatecycle;
    }

    public String getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(String publishdate) {
        this.publishdate = publishdate;
    }
}
