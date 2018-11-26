package com.weavernorth.hongxing.faPiao.vo;

import com.weaver.general.TimeUtil;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.conn.RecordSetDataSource;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.BaseBean;

/**
 * 发票信息实体
 */
public class FaPiaoVo {
    private ConnStatement statement;
    private static final String modelIdSuccess = "243";
    private static final String modelIdErr = "281";
    private BaseBean baseBean = new BaseBean();
    private ModeRightInfo modeRightInfo = new ModeRightInfo();
    /**
     * 费用支付编码
     */
    private String fyzfbm;
    /**
     * 供应商编号
     */
    private String gysbh;
    /**
     * 供应商
     */
    private String gys;
    /**
     * 含税金额
     */
    private String hsje;
    /**
     * 发票编号
     */
    private String fpbh;
    /**
     * 分录号
     */
    private String flh;
    /**
     * 业务日期
     */
    private String ywrq;
    /**
     * 物料编号
     */
    private String wlbh;
    /**
     * 物料名称
     */
    private String wlmc;
    /**
     * 数量
     */
    private String sln;
    /**
     * 含税单价
     */
    private String hsdj;
    /**
     * 不含税金额
     */
    private String bhsje;
    /**
     * 税率
     */
    private String sl;
    /**
     * 税额
     */
    private String se;
    /**
     * 失败原因
     */
    private String sbyy;

    public void insertSuccess() {
        statement = new ConnStatement();
        String successSql = "INSERT INTO UF_XSFP(FYZFBM, GYSBH, GYS, HSJE, FPBH," +
                "FLH, YWRQ, WLBH, WLMC, SLN," +
                "HSDJ, BHSJE, SL, SE, " +
                " formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) VALUES(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?, ?,?,?,?,?)";
        try {
            statement.setStatementSql(successSql);
            statement.setString(1, this.fyzfbm);
            statement.setString(2, this.gysbh);
            statement.setString(3, this.gys);
            statement.setString(4, this.hsje);
            statement.setString(5, this.fpbh);

            statement.setString(6, this.flh);
            statement.setString(7, this.ywrq);
            statement.setString(8, this.wlbh);
            statement.setString(9, this.wlmc);
            statement.setString(10, this.sln);

            statement.setString(11, this.hsdj);
            statement.setString(12, this.bhsje);
            statement.setString(13, this.sl);
            statement.setString(14, this.se);

            statement.setString(15, modelIdSuccess);//模块id
            statement.setString(16, "1");//创建人id
            statement.setString(17, "0");//一个默认值0
            statement.setString(18, TimeUtil.getCurrentTimeString().substring(0, 10));
            statement.setString(19, TimeUtil.getCurrentTimeString().substring(11));
            statement.executeUpdate();
        } catch (Exception e) {
            baseBean.writeLog("INSERT INTO UF_XSFP Exception: " + e);
        } finally {
            statement.close();
            RecordSetDataSource maxSet = new RecordSetDataSource("orcl");
            maxSet.executeSql("select max(id) id from UF_XSFP");
            int maxId = 0;
            String maxIdStr = "";
            if (maxSet.next()) {
                maxId = maxSet.getInt("id");
                maxIdStr = maxSet.getString("id");
            }
            baseBean.writeLog("select max(id) id from UF_XSFP: " + maxId);
            baseBean.writeLog("select max(id) id from UF_XSFP_str: " + maxIdStr);
            //设置权限
            modeRightInfo.setNewRight(true);
            modeRightInfo.editModeDataShare(1, Integer.parseInt(modelIdSuccess), maxId);//创建人id， 模块id， 该条数据id
        }
    }

    public void insertErr() {
        statement = new ConnStatement();
        String errSql = "INSERT INTO UF_XSFP_ERR(FYZFBM, GYSBH, GYS, HSJE, FPBH," +
                "FLH, YWRQ, WLBH, WLMC, SLN," +
                "HSDJ, BHSJE, SL, SE, SBYY," +
                "formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) VALUES(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?)";
        try {
            statement.setStatementSql(errSql);
            statement.setString(1, this.fyzfbm);
            statement.setString(2, this.gysbh);
            statement.setString(3, this.gys);
            statement.setString(4, this.hsje);
            statement.setString(5, this.fpbh);

            statement.setString(6, this.flh);
            statement.setString(7, this.ywrq);
            statement.setString(8, this.wlbh);
            statement.setString(9, this.wlmc);
            statement.setString(10, this.sln);

            statement.setString(11, this.hsdj);
            statement.setString(12, this.bhsje);
            statement.setString(13, this.sl);
            statement.setString(14, this.se);
            statement.setString(15, this.sbyy);

            statement.setString(16, modelIdErr);//模块id
            statement.setString(17, "1");//创建人id
            statement.setString(18, "0");//一个默认值0
            statement.setString(19, TimeUtil.getCurrentTimeString().substring(0, 10));
            statement.setString(20, TimeUtil.getCurrentTimeString().substring(11));
            statement.executeUpdate();

        } catch (Exception e) {
            baseBean.writeLog("INSERT INTO UF_XSFP_ERR Exception: " + e);
        } finally {
            statement.close();
            RecordSetDataSource maxSet = new RecordSetDataSource("orcl");
            maxSet.executeSql("select max(id) id from UF_XSFP_ERR");
            int maxId = 0;
            String maxIdStr = "";
            if (maxSet.next()) {
                maxIdStr = maxSet.getString("id");
                maxId = maxSet.getInt("id");
            }
            baseBean.writeLog("select max(id) id from UF_XSFP_ERR: " + maxId);
            baseBean.writeLog("select max(id) id from UF_XSFP_ERR str: " + maxIdStr);
            //设置权限
            modeRightInfo.setNewRight(true);
            modeRightInfo.editModeDataShare(1, Integer.parseInt(modelIdErr), maxId);//创建人id， 模块id， 该条数据id
        }
    }

    @Override
    public String toString() {
        return "FaPiaoVo{" +
                "fyzfbm='" + fyzfbm + '\'' +
                ", gysbh='" + gysbh + '\'' +
                ", gys='" + gys + '\'' +
                ", hsje='" + hsje + '\'' +
                ", fpbh='" + fpbh + '\'' +
                ", flh='" + flh + '\'' +
                ", ywrq='" + ywrq + '\'' +
                ", wlbh='" + wlbh + '\'' +
                ", wlmc='" + wlmc + '\'' +
                ", sln='" + sln + '\'' +
                ", hsdj='" + hsdj + '\'' +
                ", bhsje='" + bhsje + '\'' +
                ", sl='" + sl + '\'' +
                ", se='" + se + '\'' +
                ", sbyy='" + sbyy + '\'' +
                '}';
    }

    public String getSbyy() {
        return sbyy;
    }

    public void setSbyy(String sbyy) {
        this.sbyy = sbyy;
    }

    public String getGysbh() {
        return gysbh;
    }

    public void setGysbh(String gysbh) {
        this.gysbh = gysbh;
    }

    public String getGys() {
        return gys;
    }

    public void setGys(String gys) {
        this.gys = gys;
    }

    public String getHsje() {
        return hsje;
    }

    public void setHsje(String hsje) {
        this.hsje = hsje;
    }

    public String getFyzfbm() {
        return fyzfbm;
    }

    public void setFyzfbm(String fyzfbm) {
        this.fyzfbm = fyzfbm;
    }

    public String getFpbh() {
        return fpbh;
    }

    public void setFpbh(String fpbh) {
        this.fpbh = fpbh;
    }

    public String getFlh() {
        return flh;
    }

    public void setFlh(String flh) {
        this.flh = flh;
    }

    public String getYwrq() {
        return ywrq;
    }

    public void setYwrq(String ywrq) {
        this.ywrq = ywrq;
    }

    public String getWlbh() {
        return wlbh;
    }

    public void setWlbh(String wlbh) {
        this.wlbh = wlbh;
    }

    public String getWlmc() {
        return wlmc;
    }

    public void setWlmc(String wlmc) {
        this.wlmc = wlmc;
    }

    public String getSln() {
        return sln;
    }

    public void setSln(String sln) {
        this.sln = sln;
    }

    public String getHsdj() {
        return hsdj;
    }

    public void setHsdj(String hsdj) {
        this.hsdj = hsdj;
    }

    public String getBhsje() {
        return bhsje;
    }

    public void setBhsje(String bhsje) {
        this.bhsje = bhsje;
    }

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public String getSe() {
        return se;
    }

    public void setSe(String se) {
        this.se = se;
    }
}
