package com.weavernorth.caibai.util;

import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CbUtils {

    private static BaseBean baseBean = new BaseBean();

    /***
     * 通过编码转ID
     * @param tableName 表名
     * @param code    编码
     * @return id
     */
    public static String getIdByCode(String tableName, String code) {
        if (code == null || "".equals(code.trim())) {
            return "";
        }
        RecordSet rs = new RecordSet();
        String returnValue = "";

        String codeColumnName = "";
        if ("hrmsubcompany".equalsIgnoreCase(tableName)) {
            codeColumnName = "subcompanycode";
        } else if ("hrmdepartment".equalsIgnoreCase(tableName)) {
            codeColumnName = "departmentcode";
        } else if ("hrmjobtitles".equalsIgnoreCase(tableName)) {
            codeColumnName = "jobtitlecode";
        } else if ("hrmresource".equalsIgnoreCase(tableName)) {
            codeColumnName = "workcode";
        } else if ("uf_wfqyzt".equalsIgnoreCase(tableName)) {
            codeColumnName = "bm";
        } else if ("uf_wfqybm".equalsIgnoreCase(tableName)) {
            codeColumnName = "hrguid";
        } else if ("uf_nbdd".equalsIgnoreCase(tableName)) {
            codeColumnName = "code";
        }

        if ("".equals(codeColumnName)) {
            return returnValue;
        }
        rs.executeSql("select id from " + tableName + " where " + codeColumnName + " = '" + code + "'");
        if (rs.next()) {
            returnValue = Util.null2String(rs.getString("id"));
        }
        return returnValue;
    }

    public static String getSomeByIdDepartment(String column, String id) {
        RecordSet recordSet = new RecordSet();
        String sql = "select * from HrmDepartment where id = '" + id + "'";
        baseBean.writeLog("执行的sql： " + sql);
        recordSet.execute(sql);
        String returnStr = "";
        if (recordSet.next()) {
            returnStr = recordSet.getString(column);
        }
        return returnStr;
    }

    /***
     * 通过ID获取人员账号状态
     * @param hrmid    人员ID
     * @return
     */
    public static String getLoginidstatusById(String hrmid) {
        RecordSet rs = null;
        String returnValue = null;
        if (null == hrmid || "".equals(hrmid)) {
            return returnValue;
        }

        try {
            String tablename = " cus_fielddata ";
            String codeColumnName = "field6";
            rs = new RecordSet();
            rs.executeSql("select " + codeColumnName + " from " + tablename + " where id = '" + hrmid + "'");
            if (rs.next()) {
                returnValue = Util.null2String(rs.getString(codeColumnName));
            }

        } catch (Exception e) {

        }
        return returnValue;
    }

    /***
     * 得到人员最大ID
     */
    public static int getHrmMaxid() {
        int maxID = 1;
        RecordSet rs = new RecordSet();
        try {
            rs.executeProc("HrmResourceMaxId_Get", "");
            if (rs.next()) {
                maxID = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maxID;
    }

    public static String replaceStr(String str) {
        ArrayList list = Util.TokenizerString(str, ",");
        String temp = "";
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (temp.equals("")) {
                    temp = (String) list.get(i);
                } else {
                    temp += "," + (String) list.get(i);
                }
            }
        }
        return temp;
    }

    public static String getWorkFlowBillTable(String workflowid) {
        String returnValue = "";
        try {
            RecordSet rs = new RecordSet();
            if (null == workflowid || "".equals(workflowid)) {
                return returnValue;
            }
            rs.executeSql("select tablename from workflow_bill where id = (select formid from workflow_base where id= " + workflowid + " and isbill=1)");
            if (rs.next()) {
                returnValue = Util.null2String(rs.getString("tablename"));
            }
        } catch (Exception e) {
            returnValue = "";
        }
        return returnValue;
    }

    //第一个参数在第二个参数左边时间轴，true
    public static boolean compare_date(String DATE1, String DATE2) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return false;
            } else if (dt1.getTime() < dt2.getTime()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return true;
    }

    /**
     * 性别转换
     */
    public static String sexChange(String str) {
        if ("1".equals(str)) {//男
            return "0";
        } else {
            return "1";
        }
    }

    public static String insertLocation(String locationname) throws Exception {
        if (null == locationname || "".equals(locationname)) {
            return "";
        }
        String locationId = locationIsRepeat(locationname);

        if ("".equals(locationId)) {
            RecordSet rs = new RecordSet();
            rs.executeSql("insert into HrmLocations(locationname,locationdesc,countryid) values ('" + locationname + "','" + locationname + "','0')");
            rs.executeSql("select max(id) as id from HrmLocations");
            if (rs.next()) {
                locationId = Util.null2String(rs.getString("id"));
            }
        }
        return locationId;
    }

    private static String locationIsRepeat(String locationname) {
        String locationId = "";
        try {
            RecordSet rs = new RecordSet();
            rs.executeSql("select id from HrmLocations where locationname = '" + locationname + "'");
            if (rs.next()) {
                locationId = Util.null2String(rs.getString("id"));
            }
        } catch (Exception e) {
            baseBean.writeLog("check location is Repeat Exception" + e);
        }
        return locationId;
    }

}
