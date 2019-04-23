package com.weavernorth.huaLian.util;

import com.weaver.general.TimeUtil;
import com.weavernorth.huaLian.vo.ModelVo;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.BaseBean;

import java.util.List;

public class HlConnUtil {
    /**
     * 模块id
     */
    private static final Integer MODE_ID = 1;
    /**
     * 总部id
     */
    private static final int ZB_ID = 5;
    private static ModeRightInfo moderightinfo = new ModeRightInfo();
    private static BaseBean baseBean = new BaseBean();

    /**
     * 批量插入
     */
    public static void insertMode(List<ModelVo> insertVoList) {
        String currentTimeString = TimeUtil.getCurrentTimeString();
        ConnStatement statement = new ConnStatement();
        try {
            statement.setStatementSql("insert into uf_rsbzxx (fb, kbzrszs, zbrs, ksqrs, sqzrs, zpzrs, " +
                    " formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) values(?,?,?,?,?,?,  ?,?,?,?,?)");
            for (ModelVo vo : insertVoList) {
                statement.setInt(1, vo.getFb());
                statement.setInt(2, vo.getZbrs());
                statement.setInt(3, vo.getZbrs());
                statement.setInt(4, 0);
                statement.setInt(5, 0);
                statement.setInt(6, 0);

                statement.setInt(7, MODE_ID);
                statement.setString(8, "1");
                statement.setString(9, "0");
                statement.setString(10, TimeUtil.getCurrentTimeString().substring(0, 10));
                statement.setString(11, TimeUtil.getCurrentTimeString().substring(11));
                statement.executeUpdate();
            }
        } catch (Exception e) {
            baseBean.writeLog("insert into uf_rsbzxx异常： " + e);
        } finally {
            statement.close();
            //赋权
            moderightinfo.setNewRight(true);
            RecordSet maxSet = new RecordSet();
            maxSet.executeSql("select id from uf_rsbzxx where MODEDATACREATEDATE + MODEDATACREATETIME >= '" + weaver.general.TimeUtil.timeAdd(currentTimeString, -10) + "'");

            int maxId;
            while (maxSet.next()) {
                maxId = maxSet.getInt("id");
                //创建人id， 模块id， 该条数据id
                moderightinfo.editModeDataShare(1, MODE_ID, maxId);
            }
        }
    }

    /**
     * 批量更新
     */
    public static void updateMode(List<ModelVo> updateVoList) {
        ConnStatement statement = new ConnStatement();
        try {
            statement.setStatementSql("update uf_rsbzxx set zbrs = ?, ksqrs = ? where fb = ?");
            for (ModelVo vo : updateVoList) {
                statement.setInt(1, vo.getZbrs());
                statement.setInt(2, vo.getKsqrs());
                statement.setInt(3, vo.getFb());
                statement.executeUpdate();
            }
        } catch (Exception e) {
            baseBean.writeLog("update into uf_rsbzxx异常： " + e);
        } finally {
            statement.close();
        }
    }

    public static int getZbId() {
        return ZB_ID;
    }
}
