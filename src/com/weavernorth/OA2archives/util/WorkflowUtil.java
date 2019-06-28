package com.weavernorth.OA2archives.util;

import com.weavernorth.util.LogUtil;
import weaver.conn.RecordSet;
import weaver.general.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkflowUtil {

    /**
     *
     * @param selectValue   下拉框的值
     * @param fieldid   下拉框的fieldid
     * @return   下拉框选择的中文名称
     */
    public static String getSelectChinesType(String selectValue,String fieldid ){
        String selecname = "";
        if(!"".equals(selectValue)&&!"".equals(fieldid)){
            RecordSet rs=new RecordSet();
            String sql=" select dbo.convToCN(selectname) selectname  from workflow_selectitem d where fieldid='"+fieldid+"' and selectvalue="+selectValue;
            LogUtil.debugLog("====查询下拉框中文名==>"+sql);
            rs.execute(sql);
            if(rs.next()){
                selecname= Util.null2String(rs.getString("selectname"));
            }
        }

        return selecname;
    }

    /**
     *获取表单字段的页面fieldid值
     * @param strRequestId
     * @param columName  数据库字段名
     * @return
     */
    public static String  getFieldId(String strRequestId,String columName){
        String fieldId = "";
        String workflowId = getWorkflowId(strRequestId);
        if(!"".equals(workflowId)&&!"".equals(columName)){
                RecordSet rs =   new RecordSet();
                String sqlSel = "select d.id from workflow_billfield d ,workflow_base e where d.billid =e.formid and e.id ='"+workflowId+"'  and d.fieldname ='"+columName+"'";
                rs.executeSql(sqlSel);
                if(rs.next()){
                    fieldId = Util.null2String(rs.getString("id"));
                }
        }
        return fieldId;
    }

    /**
     *
     * @param strWorkflowId
     * @param strRequestId
     * @return 流程明细表中有序附件字段的id值
     */
    public static List getDetailtableDocFieldIds(String strWorkflowId,String strRequestId){
        List<Map> dataList =  getWorkflowDetailTableDocFieldName(strWorkflowId);
        String strMainTableName = getMainTableNameByWFId(strWorkflowId);
        String strSelSql ="";
        //文档id值
        List listDocids = new ArrayList();
        for (int i = 0; i < dataList.size(); i++) {
            Map dataMap = dataList.get(i);
            String strFieldName = (String) dataMap.get("fieldname");
            RecordSet rs = new RecordSet();
            String strDetailtable = (String) dataMap.get("strDetailtable");
            strSelSql = "select d."+strFieldName+" from "+strDetailtable+" d,"+strMainTableName+" m where  m.id= d.mainid and m.requestid='"+strRequestId+"' order by d.id asc";
            LogUtil.debugLog("===获取明细文档id=sql=>"+strSelSql);
            rs.execute(strSelSql);
            while(rs.next()){
                String strDocids1 = Util.null2String(rs.getString(strFieldName));
                String[] strDocids = strDocids1.split(",");
                for(int j=0;j<strDocids.length;j++){
                    String strDocid = strDocids[j];
                    LogUtil.debugLog("==strDocid==" + strDocid);
                    if (!"".equals(strDocid)) {
                        LogUtil.debugLog("==文件类型判断执行前==");
                        if (getDocType(strDocid)) {
                            LogUtil.debugLog("==文件类型判断执行==");
                            listDocids.add(strDocid);
                            LogUtil.debugLog("==放入list后==");
                        } else {
                            LogUtil.debugLog("===执行判断文件类型为false==");
                        }
                    }

                    LogUtil.debugLog("==内层for循环执行==");
                }

                LogUtil.debugLog("==while内循环执行==");
            }

            LogUtil.debugLog("==外层for循环执行==");
        }

        LogUtil.debugLog("===拿到的明细数据信息===>" + listDocids.toString());
        return listDocids;
    }
    /**
     *
     * @param strWorkflowId 流程id
     * @return 流程中的附件上传型主表字段数据库名称
     */
    public static List<Map> getWorkflowMainTableDocFieldName(String strWorkflowId){
        //附件上传字段数据库名称
        List<Map> listField = new ArrayList();
        if(!"".equals(strWorkflowId)){
            //流程主表名称
            String strMainTableName = getMainTableNameByWFId(strWorkflowId);
            String strSelSQL = "select d.fieldname from workflow_billfield d ,workflow_base e where d.billid =e.formid  and (d.type=9 or d.type=37 or fieldhtmltype=6)  and d.viewtype=0 and e.id = '"+strWorkflowId+"' order by d.dsporder";
            RecordSet rs = new RecordSet();
            rs.execute(strSelSQL);
            while(rs.next()){
                String strFieldName = Util.null2String(rs.getString("fieldname"));
                Map dataMap = new HashMap();
                dataMap.put("fieldname",strFieldName);
                dataMap.put("strMaintable",strMainTableName);
                listField.add(dataMap);
            }
        }
        return listField;
    }
    /**
     *
     * @param strWorkflowId
     * @return 流程中的附件上传型明细表字段数据库名称
     */
    public static  List<Map> getWorkflowDetailTableDocFieldName(String strWorkflowId){
        //附件上传型字段数据库名称
        List<Map> listField = new ArrayList();
        if(!"".equals(strWorkflowId)){
            String strSelSQL = "select d.fieldname,d.detailtable from workflow_billfield d ,workflow_base e where d.billid =e.formid  and (d.type=9 or d.type=37 or fieldhtmltype=6)  and d.viewtype=1 and e.id = '"+strWorkflowId+"'  order by d.dsporder";
            RecordSet rs = new RecordSet();
            rs.execute(strSelSQL);
            while(rs.next()){
                //明细附件上传字段
                String strFieldName = Util.null2String(rs.getString("fieldname"));
                //明细表表名
                String strDetailtable = Util.null2String(rs.getString("detailtable"));
                Map<String, String> dataMap = new HashMap<String, String>();
                dataMap.put("fieldname",strFieldName);
                dataMap.put("strDetailtable",strDetailtable);
                listField.add(dataMap);
            }
        }
        return listField;
    }

    /**
     * 判断文档类型是否满足转pdf的格式要求
     * @param strDocId
     * @return
     */
    public static boolean getDocType(String strDocId){
        boolean result = false;
        if(!"".equals(strDocId)){
            RecordSet rs = new RecordSet();
            rs.execute("select imagefilename from docimagefile where docid='"+strDocId+"'");
            if(rs.next()){
                String strImageFilename = Util.null2String(rs.getString("imagefilename"));
                strImageFilename = strImageFilename.substring(strImageFilename.lastIndexOf(".")+1,strImageFilename.length());
                //满足如下格式，才保存docid
                if(strImageFilename.equalsIgnoreCase("doc")||strImageFilename.equalsIgnoreCase("docx")||strImageFilename.equalsIgnoreCase("ppt")||strImageFilename.equalsIgnoreCase("pptx")
                        ||strImageFilename.equalsIgnoreCase("xls")||strImageFilename.equalsIgnoreCase("xlsx")||strImageFilename.equalsIgnoreCase("txt")||strImageFilename.equalsIgnoreCase("pdf")){
                    result = true;
                }
            }
        }
        return result;
    }
    /**
     * 获取自定义流程主表名称
     * @param strWorkflowId 流程id
     * @return 主表名称
     */
    public static String getMainTableNameByWFId(String strWorkflowId){
        String strTableName = "";
        int intFormid = 0;
        if(!"".equals(strWorkflowId)){
            String strSQL = "select formid from workflow_base where id='"+strWorkflowId+"'";
            RecordSet rs = new RecordSet();
            rs.execute(strSQL);
            if(rs.next()){
                intFormid = -rs.getInt("formid");
            }
        }
        if(intFormid!=0){
            strTableName = "formtable_main_"+intFormid;
        }
        return strTableName;
    }
    public static String getMainTableNameByReqId(String strRequestId){
        String strWorkflowId = getWorkflowId(strRequestId);
        String strTableName = "";
        int intFormid = 0;
        if(!"".equals(strWorkflowId)){
            String strSQL = "select formid from workflow_base where id='"+strWorkflowId+"'";
            RecordSet rs = new RecordSet();
            rs.execute(strSQL);
            if(rs.next()){
                intFormid = -rs.getInt("formid");
            }
        }
        if(intFormid!=0){
            strTableName = "formtable_main_"+intFormid;
        }
        return strTableName;
    }
    /**
     * 获取流程主表中含有文档字段的文档id值
     * @param strWorkflowId
     * @param strRequestId
     * @return List 主表中有序文档id的值
     */
    public static List getMaintableDocFieldIds(String strWorkflowId,String strRequestId) {
        List<Map> dataList =  getWorkflowMainTableDocFieldName(strWorkflowId);
        String strSelSql ="";
        //文档id值
        List<String>  listDocids = new ArrayList();
        for (int i = 0; i < dataList.size(); i++) {
            Map dataMap = dataList.get(i);
            String strFieldName = (String) dataMap.get("fieldname");
            String strMaintable = (String) dataMap.get("strMaintable");
            strSelSql = "select "+strFieldName+" from "+strMaintable+" where requestid='"+strRequestId+"'";
            LogUtil.debugLog("===获取主表文档id=sql=>"+strSelSql);
            RecordSet rs = new RecordSet();
            rs.execute(strSelSql);
            if(rs.next()){
                String strDocids1 = Util.null2String(rs.getString(strFieldName));
                String[] strDocids = strDocids1.split(",");

                for(int j=0;j<strDocids.length;j++){
                    String strdocid = strDocids[j];
                    if(!"".equals(strdocid)){
                        //只保存满足转为pdf类型的文档id
                        if(getDocType(strdocid)){
                            listDocids.add(strdocid);
                        }
                    }
                }

            }
        }
        return listDocids;
    }

    /**
     * 根据requestid 获取 workflowid
     * @param strRequestId
     * @return
     */
    public static String getWorkflowId(String strRequestId){
        String workflowId = "";
        if(!"".equals(strRequestId)){
            RecordSet rs = new RecordSet();
            String sqlSel = "select workflowid from workflow_requestbase where requestid = '"+strRequestId+"'";
            rs.executeSql(sqlSel);
            if(rs.next()){
                workflowId = Util.null2String(rs.getString("workflowid"));
            }
        }
        return workflowId;
    }

    /**
     * 获取多个部门的名称,以逗号分隔字符串连接
     */
    public  static String getMultipleDepartmentName(String departmentIds){
        String departmentNames = "";
        if(!"".equals(departmentIds)){
            RecordSet rs = new RecordSet();
            String sql = "select departmentname from HrmDepartment  where id in ("+departmentIds+")";
            rs.executeSql(sql);
            while(rs.next()){
                departmentNames += Util.null2String(rs.getString("departmentname"))+",";

            }
        }
        if(departmentNames.contains(",")){
            departmentNames.substring(0,departmentNames.lastIndexOf(","));
        }
        //LogUtil.debugLog("====departmentNames==>"+departmentNames);
        return departmentNames ;
    }

    /**
     *
     * @param requestid
     * @return
     */
    public static String  getCreaterId(String requestid){
        String createrId = "";
        if(!"".equals(requestid)){
            RecordSet rs  = new RecordSet();
            String sql  = "select creater from workflow_requestbase where requestid="+requestid;
            rs.executeSql(sql);
            if(rs.next()){
                createrId = Util.null2String(rs.getString("creater"));
            }
        }
        return createrId;
    }

    /**
     *获取最后节点操作者
     * @param requestid
     * @return
     */
    public static String  getLastOperator(String requestid){
        String lastoperator = "";
        if(!"".equals(requestid)){
            RecordSet rs  = new RecordSet();
            String sql  = "select lastoperator from workflow_requestbase where requestid ="+requestid;
            rs.executeSql(sql);
            if(rs.next()){
                lastoperator = Util.null2String(rs.getString("lastoperator"));
            }
        }
        return lastoperator;
    }

    public static String getCreateDate(String requestid){
        String createdate = "";
        if(!"".equals(requestid)){
            RecordSet rs  = new RecordSet();
            String sql  = "select createdate from workflow_requestbase where requestid ="+requestid;
            rs.executeSql(sql);
            if(rs.next()){
                createdate = Util.null2String(rs.getString("createdate"));
            }
        }
        return createdate;

}
    /**
     * 部门中英文转换
     */
    public static String getDepartmentname(String id){

        String name ="";
        if(!"".equals(id)){
            RecordSet rs = new RecordSet();
            String sql = "select dbo.convToCN(departmentname) departmentname from hrmdepartment where id="+id;
            rs.executeSql(sql);
            if(rs.next()){
                name = Util.null2String(rs.getString("departmentname"));
            }
        }
        return name;
    }
    /**
     * 中英文转换方法
     * @param fieldName
     * @param tableName
     * @param FieldValue
     * @return
     */
    public static String  getChineseName(String fieldName,String tableName,String FieldValue){
        RecordSet rs = new RecordSet();
        String returnName = "";
        String  sql  = "select dbo.convToCN("+fieldName+") "+fieldName+" from "+tableName+" where "+fieldName+"='"+FieldValue+"'";
        LogUtil.debugLog("==中英文转换sql==>"+sql);
        rs.executeSql(sql);
        if(rs.next()){
            returnName = Util.null2String(rs.getString(fieldName));
        }
        return  returnName;
    }
}
