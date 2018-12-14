package com.weavernorth.huaLian.vo;

import com.weaver.general.TimeUtil;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.BaseBean;

import java.util.List;

/**
 * 人事编制vo
 */
public class ModelVo {

    private static final Integer modeId = 1;
    private BaseBean baseBean = new BaseBean();
    private ModeRightInfo moderightinfo = new ModeRightInfo();
    /**
     * 分部id
     */
    private int fb;
    /**
     * 部门id
     */
    private int bm;
    /**
     * 可编制人员总数
     */
    private int kbzrszs;
    /**
     * 在编人数
     */
    private int zbrs;
    /**
     * 可申请人数
     */
    private int ksqrs;
    /**
     * 申请中人数
     */
    private int sqzrs;
    /**
     * 下级部门id集合
     */
    private List<Integer> sonDepList;

    public void update() {
        ConnStatement statement = new ConnStatement();
        try {
            statement.setStatementSql("update uf_rsbzxx set fb = ?, zbrs = ?, ksqrs = ? where bm = ?");
            statement.setInt(1, this.fb);
            statement.setInt(2, this.zbrs);
            statement.setInt(3, this.ksqrs);
            statement.setInt(4, this.bm);

            statement.executeUpdate();
        } catch (Exception e) {
            baseBean.writeLog("update into uf_rsbzxx异常： " + e);
        } finally {
            statement.close();
        }


    }

    public void insert() {
        ConnStatement statement = new ConnStatement();
        try {
            statement.setStatementSql("insert into uf_rsbzxx (fb, bm, kbzrszs, zbrs, ksqrs, sqzrs," +
                    " formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) values(?,?,?,?,?,? ,?,?,?,?,?)");
            statement.setInt(1, this.fb);
            statement.setInt(2, this.bm);
            statement.setInt(3, zbrs);
            statement.setInt(4, zbrs);
            statement.setInt(5, 0);
            statement.setInt(6, 0);

            statement.setInt(7, modeId);//模块id
            statement.setString(8, "1");//创建人id
            statement.setString(9, "0");//一个默认值0
            statement.setString(10, TimeUtil.getCurrentTimeString().substring(0, 10));
            statement.setString(11, TimeUtil.getCurrentTimeString().substring(11));
            statement.executeUpdate();

            RecordSet maxSet = new RecordSet();
            maxSet.executeSql("select max(id) id from uf_rsbzxx");

            int maxId = 0;
            if (maxSet.next()) {
                maxId = maxSet.getInt("id");
            }
            //设置权限
            moderightinfo.setNewRight(true);
            moderightinfo.editModeDataShare(1, modeId, maxId);//创建人id， 模块id， 该条数据id
        } catch (Exception e) {
            baseBean.writeLog("insert into uf_rsbzxx异常： " + e);
        } finally {
            statement.close();
        }

    }

    public int getFb() {
        return fb;
    }

    public void setFb(int fb) {
        this.fb = fb;
    }

    public int getBm() {
        return bm;
    }

    public void setBm(int bm) {
        this.bm = bm;
    }

    public int getKbzrszs() {
        return kbzrszs;
    }

    public void setKbzrszs(int kbzrszs) {
        this.kbzrszs = kbzrszs;
    }

    public int getZbrs() {
        return zbrs;
    }

    public void setZbrs(int zbrs) {
        this.zbrs = zbrs;
    }

    public int getKsqrs() {
        return ksqrs;
    }

    public void setKsqrs(int ksqrs) {
        this.ksqrs = ksqrs;
    }

    public int getSqzrs() {
        return sqzrs;
    }

    public void setSqzrs(int sqzrs) {
        this.sqzrs = sqzrs;
    }

    public List<Integer> getSonDepList() {
        return sonDepList;
    }

    public void setSonDepList(List<Integer> sonDepList) {
        this.sonDepList = sonDepList;
    }
}
