package com.weavernorth.B1.zyml.po;

import com.weaver.general.BaseBean;
import com.weaver.general.TimeUtil;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;

/**
 * 获取所有资源目录
 */
public class CatalogAll {
    private static final String modelId = "2";

    private String id;//主键
    private String restitle;//信息资源名称
    private String create_user;//创建人
    private String resourceapply;//信息资源提供方
    private String resourceapplyinner;//提供方内部部门

    private String resourceapplycode;//资源提供方代码
    private String resourcetype;//信息资源格式分类
    private String resourcefiletype;//信息资源格式类型
    private String otherresourcedescribe;//其他类型资源格式描述
    private String net;//信息资源所在网络

    private String resourceposition;//信息资源所在位置
    private String resourcesummary;//信息资源摘要
    private String relationname;//关联名称
    private String catalog_code;//信息资源编码
    private String totaldatastore;//数据存储总量

    private String sharedatastore;//已共享的数据存储量
    private String opendatastore;//已开放的数据存储量
    private String totalstructrecords;//结构化信息记录总数
    private String sharestructrecords;//已共享结构化记录数
    private String openstructrecords;//已开放结构化记录数

    private BaseBean baseBean = new BaseBean();

    public void insertOrUpdate(RecordSet existSet) {
        existSet.execute("select id from uf_getAllCatalogLis where my_id = '" + this.id + "'");
        if (existSet.next()) {
            update();
        } else {
            insert();
        }
    }

    public void insert() {
        RecordSet maxSet = new RecordSet();
        //设置权限
        ModeRightInfo ModeRightInfo = new ModeRightInfo();
        ModeRightInfo.setNewRight(true);
        ConnStatement statement = new ConnStatement();
        String sql = "insert into uf_getAllCatalogLis (my_id, restitle, create_user, resourceapply, resourceapplyinner," +
                "resourceapplycode, resourcetype, resourcefiletype, otherresourcedescribe, net, " +
                "resourceposition, resourcesummary, relationname, catalog_code, totaldatastore," +
                "sharedatastore, opendatastore, totalstructrecords, sharestructrecords, openstructrecords," +
                "formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) " +
                "values(?,?,?,?,?,   ?,?,?,?,?,   ?,?,?,?,?,   ?,?,?,?,?,   ?,?,?,?,?)";
        try {
            statement.setStatementSql(sql);
            statement.setString(1, id);
            statement.setString(2, restitle);
            statement.setString(3, create_user);
            statement.setString(4, resourceapply);
            statement.setString(5, resourceapplyinner);

            statement.setString(6, resourceapplycode);
            statement.setString(7, resourcetype);
            statement.setString(8, resourcefiletype);
            statement.setString(9, otherresourcedescribe);
            statement.setString(10, net);

            statement.setString(11, resourceposition);
            statement.setString(12, resourcesummary);
            statement.setString(13, relationname);
            statement.setString(14, catalog_code);
            statement.setString(15, totaldatastore);

            statement.setString(16, sharedatastore);
            statement.setString(17, opendatastore);
            statement.setString(18, totalstructrecords);
            statement.setString(19, sharestructrecords);
            statement.setString(20, openstructrecords);

            statement.setString(21, modelId);//模块id
            statement.setString(22, "1");//创建人id
            statement.setString(23, "0");//一个默认值0
            statement.setString(24, TimeUtil.getCurrentTimeString().substring(0, 10));
            statement.setString(25, TimeUtil.getCurrentTimeString().substring(11));
            statement.executeUpdate();

            maxSet.executeSql("select max(id) id from uf_getAllCatalogLis");
            int maxId = 0;
            if (maxSet.next()) {
                maxId = maxSet.getInt("id");
            }
            ModeRightInfo.editModeDataShare(1, Integer.parseInt(modelId), maxId);//创建人id， 模块id， 该条数据id
        } catch (Exception e) {
            baseBean.writeLog("资源目录插入异常： " + e);
            baseBean.writeLog("插入异常数据： " + this.toString());
        } finally {
            statement.close();
        }
    }

    public void update() {
        ConnStatement statement = new ConnStatement();
        String sql = "update uf_getAllCatalogLis set restitle = ?, create_user = ?, resourceapply = ?, resourceapplyinner = ?," +
                "resourceapplycode = ?, resourcetype = ?, resourcefiletype = ?, otherresourcedescribe = ?, net = ?," +
                "resourceposition = ?, resourcesummary = ?, relationname = ?, catalog_code = ?, totaldatastore = ?," +
                "sharedatastore = ?, opendatastore = ?, totalstructrecords = ?, sharestructrecords = ?, openstructrecords = ? where my_id = ?";
        try {
            statement.setStatementSql(sql);
            statement.setString(1, restitle);
            statement.setString(2, create_user);
            statement.setString(3, resourceapply);
            statement.setString(4, resourceapplyinner);
            statement.setString(5, resourceapplycode);

            statement.setString(6, resourcetype);
            statement.setString(7, resourcefiletype);
            statement.setString(8, otherresourcedescribe);
            statement.setString(9, net);
            statement.setString(10, resourceposition);

            statement.setString(11, resourcesummary);
            statement.setString(12, relationname);
            statement.setString(13, catalog_code);
            statement.setString(14, totaldatastore);
            statement.setString(15, sharedatastore);

            statement.setString(16, opendatastore);
            statement.setString(17, totalstructrecords);
            statement.setString(18, sharestructrecords);
            statement.setString(19, openstructrecords);
            statement.setString(20, id);

            statement.executeUpdate();
        } catch (Exception e) {
            baseBean.writeLog("资源目录更新异常： " + e);
            baseBean.writeLog("更新异常数据： " + this.toString());
        } finally {
            statement.close();
        }
    }

    @Override
    public String toString() {
        return "CatalogAll{" +
                "id='" + id + '\'' +
                ", restitle='" + restitle + '\'' +
                ", create_user='" + create_user + '\'' +
                ", resourceapply='" + resourceapply + '\'' +
                ", resourceapplyinner='" + resourceapplyinner + '\'' +
                ", resourceapplycode='" + resourceapplycode + '\'' +
                ", resourcetype='" + resourcetype + '\'' +
                ", resourcefiletype='" + resourcefiletype + '\'' +
                ", otherresourcedescribe='" + otherresourcedescribe + '\'' +
                ", net='" + net + '\'' +
                ", resourceposition='" + resourceposition + '\'' +
                ", resourcesummary='" + resourcesummary + '\'' +
                ", relationname='" + relationname + '\'' +
                ", catalog_code='" + catalog_code + '\'' +
                ", totaldatastore='" + totaldatastore + '\'' +
                ", sharedatastore='" + sharedatastore + '\'' +
                ", opendatastore='" + opendatastore + '\'' +
                ", totalstructrecords='" + totalstructrecords + '\'' +
                ", sharestructrecords='" + sharestructrecords + '\'' +
                ", openstructrecords='" + openstructrecords + '\'' +
                ", baseBean=" + baseBean +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRestitle() {
        return restitle;
    }

    public void setRestitle(String restitle) {
        this.restitle = restitle;
    }

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    public String getResourceapply() {
        return resourceapply;
    }

    public void setResourceapply(String resourceapply) {
        this.resourceapply = resourceapply;
    }

    public String getResourceapplyinner() {
        return resourceapplyinner;
    }

    public void setResourceapplyinner(String resourceapplyinner) {
        this.resourceapplyinner = resourceapplyinner;
    }

    public String getResourceapplycode() {
        return resourceapplycode;
    }

    public void setResourceapplycode(String resourceapplycode) {
        this.resourceapplycode = resourceapplycode;
    }

    public String getResourcetype() {
        return resourcetype;
    }

    public void setResourcetype(String resourcetype) {
        this.resourcetype = resourcetype;
    }

    public String getResourcefiletype() {
        return resourcefiletype;
    }

    public void setResourcefiletype(String resourcefiletype) {
        this.resourcefiletype = resourcefiletype;
    }

    public String getOtherresourcedescribe() {
        return otherresourcedescribe;
    }

    public void setOtherresourcedescribe(String otherresourcedescribe) {
        this.otherresourcedescribe = otherresourcedescribe;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public String getResourceposition() {
        return resourceposition;
    }

    public void setResourceposition(String resourceposition) {
        this.resourceposition = resourceposition;
    }

    public String getResourcesummary() {
        return resourcesummary;
    }

    public void setResourcesummary(String resourcesummary) {
        this.resourcesummary = resourcesummary;
    }

    public String getRelationname() {
        return relationname;
    }

    public void setRelationname(String relationname) {
        this.relationname = relationname;
    }

    public String getCatalog_code() {
        return catalog_code;
    }

    public void setCatalog_code(String catalog_code) {
        this.catalog_code = catalog_code;
    }

    public String getTotaldatastore() {
        return totaldatastore;
    }

    public void setTotaldatastore(String totaldatastore) {
        this.totaldatastore = totaldatastore;
    }

    public String getSharedatastore() {
        return sharedatastore;
    }

    public void setSharedatastore(String sharedatastore) {
        this.sharedatastore = sharedatastore;
    }

    public String getOpendatastore() {
        return opendatastore;
    }

    public void setOpendatastore(String opendatastore) {
        this.opendatastore = opendatastore;
    }

    public String getTotalstructrecords() {
        return totalstructrecords;
    }

    public void setTotalstructrecords(String totalstructrecords) {
        this.totalstructrecords = totalstructrecords;
    }

    public String getSharestructrecords() {
        return sharestructrecords;
    }

    public void setSharestructrecords(String sharestructrecords) {
        this.sharestructrecords = sharestructrecords;
    }

    public String getOpenstructrecords() {
        return openstructrecords;
    }

    public void setOpenstructrecords(String openstructrecords) {
        this.openstructrecords = openstructrecords;
    }
}
