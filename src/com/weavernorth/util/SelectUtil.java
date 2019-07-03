package com.weavernorth.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import weaver.conn.RecordSet;
import weaver.general.Util;
/**
 * 查询关于流程相关信息
 * @author Dylan
 *
 */
public class SelectUtil {

    /**
     * 获取流程字段值
     * @param requestid
     * @param field
     * @return
     */
    public static String getFileVal(String requestid,String field){
        String strTableName = getTableName(requestid);
        RecordSet rs = new RecordSet();
        String strReturn = "";
        rs.executeSql("select * from " + strTableName + " where requestid = " + requestid);
        LogUtil.doWriteLog("select * from " + strTableName + " where requestid = " + requestid);
        if(rs.next()){
            strReturn = Util.null2String(rs.getString(field));
        }

        return strReturn;
    }

    /**
     * 根据requestid更新字段值
     * @param requestid
     * @param field
     * @param val
     * @return
     */
    public static boolean update(String requestid,String field,String val){
        String strTableName = getTableName(requestid);
        RecordSet rs = new RecordSet();
        String sql = "update " + strTableName + " set " + field + " = '" + val + "' where requestid = " + requestid;
        boolean flag = rs.executeSql(sql);
        LogUtil.doWriteLog(sql);
        return flag;
    }

    /**
     * 获取workflowid
     *
     * @param requestid
     * @return
     */
    public static String getWorkflowid(String requestid) {
        RecordSet rs = new RecordSet();
        // 流程类型id
        String strWorkflowid = "";
        String Sql = "select workflowid from workflow_requestbase where requestid="
                + requestid;
        rs.execute(Sql);
        if (rs.first()) {
            strWorkflowid = rs.getString("workflowid");
        }
        return strWorkflowid;
    }

    /**
     * 根据requestid获取流程表名
     *
     * @param requestid
     * @return
     */
    public static String getTableName(String requestid) {
        RecordSet rs = new RecordSet();
        // 表单表名
        String strTableName = "";
        // 获取表名
        String strSearchFormid = "select tablename from workflow_bill where id=(select formid from workflow_base where id="
                + getWorkflowid(requestid) + ")";
        rs.execute(strSearchFormid);
        if (rs.first()) {
            strTableName = Util.null2o(rs.getString("tablename"));
        } else {
            LogUtil.doWriteLog("--查询表单表名失败--" + strSearchFormid);
        }
        return strTableName;
    }

    /**
     * 根据requestid获取表单主表id
     *
     * @param requestid
     * @return
     */
    public static String getFormid(String requestid) {
        RecordSet rs = new RecordSet();
        // 流程表单中的主表id
        String strFormid = "";
        String Sql = "select id from " + getTableName(requestid)
                + " where requestid=" + requestid;
        rs.execute(Sql);
        if (rs.first()) {
            strFormid = Util.null2o(rs.getString("id"));
        } else {
            LogUtil.doWriteLog("--查询表单id失败--" + Sql);
        }
        return strFormid;
    }
    /**
     * 根据requestid获取流程附件存储目录
     * @param requestid
     * @return
     */
    public static String getDocId(String requestid){
        //获取该流程附件存放目录
        String strFileId = "";
        RecordSet rs = new RecordSet();
        rs.executeSql("select docCategory from workflow_base where id ="+SelectUtil.getWorkflowid(requestid));
        if(rs.next()){
            strFileId = Util.null2String(rs.getString("docCategory"));
        }
        int intone = strFileId.lastIndexOf(",");
        strFileId  = strFileId.substring((intone+1),strFileId.length());
        return strFileId;
    }

    /**
     * 得到文档名称
     * @param strDocId
     * @return
     */
    public  String getDocName(String strDocId){
        String strDocName = "";
        if(!"".equals(strDocId)){
            RecordSet rs = new RecordSet();
            String strSelSQL = "select imagefilename from imagefile where imagefileid="+strDocId;
            rs.execute(strSelSQL);
            if(rs.next()){
                strDocName = Util.null2String(rs.getString("imagefilename"));
                LogUtil.debugLog("--DocName---"+strDocName);
            }
        }
        return strDocName;
    }


    /**
     * 得到文档的实际目录
     * @param strDocId 文档id
     * @return 文档服务器的真实路径
     */
    public static String getDocRealPath(String strDocId){
        String strPath = "";
        if(!"".equals(strDocId)){
            RecordSet rs = new RecordSet();
            String strSelSQL = "select filerealpath from imagefile where imagefileid="+strDocId;
            rs.execute(strSelSQL);
            if(rs.next()){
                strPath = Util.null2String(rs.getString("filerealpath"));
                LogUtil.debugLog("--strPath--"+strPath);
            }
        }
        return strPath;
    }

    /**文件重命名
     * @param path 文件目录
     * @param oldname  原来的文件名
     * @param newname 新文件名
     */
    public  void renameFile(String path,String oldname,String newname){
        if(!oldname.equals(newname)){//新的文件名和以前文件名不同时,才有必要进行重命名
            File oldfile=new File(path+"/"+oldname);
            File newfile=new File(path+"/"+newname);
            if(!oldfile.exists()){
                return;//重命名文件不存在
            }
            if(newfile.exists())//若在该目录下已经有一个文件和新文件名相同，则不允许重命名
                System.out.println(newname+"已经存在！");
            else{
                oldfile.renameTo(newfile);
            }
        }else{
            System.out.println("新文件名和旧文件名相同...");
        }
    }

    /**
     * 复制文件
     * @param f1
     * @param f2
     * @return
     * @throws Exception
     */
    public static long forFile(File f1,File f2) throws Exception{
        long time=new Date().getTime();
        int length=2097152;
        FileInputStream in=new FileInputStream(f1);
        FileOutputStream out=new FileOutputStream(f2);
        byte[] buffer=new byte[length];
        while(true){
            int ins=in.read(buffer);
            if(ins==-1){
                in.close();
                out.flush();
                out.close();
                return new Date().getTime()-time;
            }else
                out.write(buffer,0,ins);
        }
    }
    /**
     * 复制文件
     * @param fromFile
     * @param toFile
     * <br/>
     * 2016年12月19日  下午3:31:50
     * @throws IOException
     */
    public void copyFile(File fromFile,File toFile) throws IOException{
        FileInputStream ins = new FileInputStream(fromFile);
        FileOutputStream out = new FileOutputStream(toFile);
        byte[] b = new byte[1024];
        int n=0;
        while((n=ins.read(b))!=-1){
            out.write(b, 0, b.length);
        }

        ins.close();
        out.close();
    }
    /**
     * 获取供应商信息
     * @param id
     * @return
     */
    public static String getCRM_CustomerInfo(String id,String data){
        String strPath = "";
        if(!"".equals(id)){
            RecordSet rs = new RecordSet();
            String strSelSQL = "select * from CRM_CustomerInfo where id="+id;
            rs.execute(strSelSQL);
            if(rs.next()){
                strPath = Util.null2String(rs.getString(data));
                //LogUtil.debugLog("--strPath--"+strPath);
            }
        }
        return strPath;
    }

}
