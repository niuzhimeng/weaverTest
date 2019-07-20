package com.weavernorth.workflow.payment;

import com.sap.mw.jco.JCO;
import com.weavernorth.util.LogUtil;
import weaver.conn.RecordSet;

/**
 * SAP开发过程中用的工具类
 *
 */
public class SAPUtil{
    private static JCO.Client mConnection = null;
    /**
     * 根据sap 数据源名称查询到数据库中的ID
     * @param poolName
     * @return
     */
    public  static String getIdbyPoolName(String poolName){
        String strId = "";
        RecordSet rs = new RecordSet();
        String strSql = "select id from sap_datasource where poolname='"+ poolName +"'";
        LogUtil.debugLog("strSql======="+strSql);
        rs.execute(strSql);
        if(rs.first()){
            strId = rs.getString("id");
        }
        return strId;
    }

}
