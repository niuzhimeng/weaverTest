package com.weavernorth.JTHW;

import weaver.general.BaseBean;
import weaver.interfaces.schedule.BaseCronJob;

/**
 * Created by dell on 2018/11/1.
 * 建投华文：系统时间大于**时间  修改**字段
 */
public class SysDateJob extends BaseCronJob {
    public void execute(){
        new BaseBean().writeLog("执行定时接口");
//        RecordSet rs_seldetail = new RecordSet();
//        RecordSet rs_editZT = new RecordSet();
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        String NowDate = df.format(new Date());
//        new BaseBean().writeLog("NowDate:" + NowDate);
//        String sql_seldetail = "select dbsxbt from uf_dbsx where  '" + NowDate +"' > wcsj";
//        new BaseBean().writeLog("///////////////////////sql_seldetail:" + sql_seldetail);
//        rs_seldetail.execute(sql_seldetail);
//        while (rs_seldetail.next()) {
//            String dbsxbt = Util.null2String(rs_seldetail.getString("dbsxbt"));
//            new BaseBean().writeLog("///////////////////////dbsxbt:" + dbsxbt);
//            String sql_editZT = "update uf_dbsx set zt = 2 where dbsxbt = '" + dbsxbt + "' and zt = 1";
//            new BaseBean().writeLog("///////////////////////sql_editZT:" + sql_editZT);
//            rs_editZT.execute(sql_editZT);
//        }
    }
}
