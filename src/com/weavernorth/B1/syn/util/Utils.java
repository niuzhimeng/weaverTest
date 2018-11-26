package com.weavernorth.B1.syn.util;

import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Utils {


    /***
     * 通过编码转ID
     * @param tableName 表名
     * @param code    编码
     * @return id
     */
    public static String getIdByCode(String tableName, String code) {
        RecordSet rs = new RecordSet();
        String returnValue = "";

        String codeColumnName = "";
        if (tableName.equalsIgnoreCase("hrmsubcompany")) {
            codeColumnName = "subcompanycode";
        } else if (tableName.equalsIgnoreCase("hrmdepartment")) {
            codeColumnName = "departmentcode";
        } else if (tableName.equalsIgnoreCase("hrmjobtitles")) {
            codeColumnName = "ehrjobid";
        } else if (tableName.equalsIgnoreCase("hrmresource")) {
            codeColumnName = "workcode";
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

    /***
     * 通过ID得到SAP编码
     * @param tablename 表名
     * @param code    编码
     * @return
     */
    public static String getCodeById(String tablename, String id) {
        RecordSet rs = null;
        String returnValue = "";
        if (null == tablename || "".equals(tablename)) {
            return returnValue;
        }
        if (null == id || "".equals(id)) {
            return returnValue;
        }
        try {

            String codeColumnName = "";

            if (tablename.equalsIgnoreCase("hrmsubcompany")) {
                codeColumnName = "subcompanycode";
            } else if (tablename.equalsIgnoreCase("hrmdepartment")) {
                codeColumnName = "departmentcode";
            } else if (tablename.equalsIgnoreCase("hrmjobtitles")) {
                codeColumnName = "ehrjobid";
            } else if (tablename.equalsIgnoreCase("hrmresource")) {
                codeColumnName = "ehrid";
            }

            if ("".equals(codeColumnName)) {
                return returnValue;
            }
            rs = new RecordSet();
            rs.executeSql("select " + codeColumnName + " from " + tablename + " where id = " + id);
            if (rs.next()) {
                returnValue = Util.null2String(rs.getString(codeColumnName));
            }
        } catch (Exception e) {

        }
        return returnValue;
    }


    /***
     * 通过ID获取人员账号状态
     * @param ID    人员ID
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
            new BaseBean().writeLog(e);
        }
        return maxID;
    }

    public static String replaceStr(String str) {
        ArrayList list = Util.TokenizerString(str, ",");
        String temp = "";
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (temp.equals(""))
                    temp = (String) list.get(i);
                else
                    temp += "," + (String) list.get(i);
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

}
