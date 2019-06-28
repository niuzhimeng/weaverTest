package com.weavernorth.OA2archives.util;

import com.weavernorth.util.LogUtil;
import weaver.conn.RecordSet;
import weaver.docs.docs.DocComInfo;
import weaver.file.ImageFileManager;
import weaver.general.TimeUtil;
import weaver.general.Util;
import weaver.hrm.resource.ResourceComInfo;
import weaver.soa.workflow.request.RequestInfo;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocUtil {
    /**
     * 判断文档类型是否满足转pdf的格式要求
     *
     * @param strDocId
     * @return
     */
    public static boolean getDocType(String strDocId) {
        boolean result = false;
        if (!"".equals(strDocId)) {
            RecordSet rs = new RecordSet();
            rs.execute("select top 1  imagefilename from docimagefile  where docid='" + strDocId + "' order by versionId desc");
            if (rs.next()) {
                String strImageFilename = Util.null2String(rs.getString("imagefilename"));
                strImageFilename = strImageFilename.substring(strImageFilename.lastIndexOf(".") + 1, strImageFilename.length());
                //满足如下格式，才保存docid
                if (strImageFilename.equals("doc") || strImageFilename.equals("docx") || strImageFilename.equals("ppt") || strImageFilename.equals("pptx")
                        || strImageFilename.equals("xls") || strImageFilename.equals("xlsx") || strImageFilename.equals("txt") || strImageFilename.equals("pdf")) {
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     * 根据docid 获取最高版本 imageFileId
     *
     * @param Docid
     * @return
     */
    public static Integer getImageFileId(int Docid) {
        int ImageFileId = -1;
        if (!"".equals(Docid + "")) {
            RecordSet rs = new RecordSet();
            rs.execute("select max(imageFileId) imageFileId from DocImageFile where docid = '" + Docid + "'");
            if (rs.next()) {
                ImageFileId = rs.getInt("imageFileId");
            }
        }
        return ImageFileId;
    }

    /**
     * 获取表单转成的pdf文件的docid
     *
     * @param strRerquestId
     * @return
     */
    public static String getTablePdfDocid(String strRerquestId) {
        String strDocId = "";
        if (!"".equals(strRerquestId)) {
            RecordSet rs = new RecordSet();
            String sql = "select docid from wn_tabllepdfofrequest where requestid='" + strRerquestId + "' order by docid desc";
            rs.execute(sql);
            if (rs.next()) {
                strDocId = Util.null2String(rs.getString("docid"));

            }
        }
        return strDocId;
    }

    /**
     * 获取所有文档id
     *
     * @param request
     * @return 所有文档id
     */
    @SuppressWarnings({"unchecked", "null"})
    public static List getDocFieldIds(RequestInfo request) {
        LogUtil.debugLog("====获取归档文档的docid开始" + TimeUtil.getCurrentTimeString() + "====");
        String strWorkflowId = request.getWorkflowid();
        String strRequestId = request.getRequestid();
        List<String> listDocidsAll = new ArrayList<String>();
        //将表单转的pdf文件加到list中去
        String strDocId = getTablePdfDocid(strRequestId);
        listDocidsAll.add(strDocId);
        LogUtil.debugLog("===获取流程表单pdfid===>>>" + strDocId);
        //主表字段所有正文转为pdf后的正文docid、附件docid,
        List<String> listDocidsMain = WorkflowUtil.getMaintableDocFieldIds(strWorkflowId, strRequestId);
        listDocidsAll.addAll(listDocidsMain);
        LogUtil.debugLog("===流程主表中的附件id===>>>" + listDocidsMain.toString());
        //明细字段所有附件docid
        List<String> listDocidsDetail = WorkflowUtil.getDetailtableDocFieldIds(strWorkflowId, strRequestId);
        LogUtil.debugLog("===流程明细表中的附件id===>>>" + listDocidsDetail.toString());
        listDocidsAll.addAll(listDocidsDetail);
        LogUtil.debugLog("====获取归档文档的docid结束" + TimeUtil.getCurrentTimeString() + "====");
        return listDocidsAll;
    }

    /**
     * 实际处理文档获取文档方法,将文档解压到压缩的原始目录下
     *
     * @param imageFileId
     * @throws Exception
     */
    public static String getRealDoc(int imageFileId) {
        String decompressPath = "";
        ImageFileManager im = new ImageFileManager();
        im.getImageFileInfoById(imageFileId);
        InputStream inputStream = im.getInputStream();
        String strFilerealpath = im.getFileRealPath();
        strFilerealpath = strFilerealpath.replaceAll("\\\\", "/");
        //解压路径
        int intlastIndex = strFilerealpath.lastIndexOf("/");
        String strZipOldPath = strFilerealpath.substring(0, intlastIndex + 1);
        String ImageFileName = im.getImageFileName().replaceAll("[\\s\\\\/:\\*\\?\\\"<>\\|]", "&");
        OutputStream os = null;
        try {
            os = new FileOutputStream(new File(strZipOldPath + ImageFileName));
            //文件拷贝
            byte flush[] = new byte[1024];
            int len = 0;
            while (0 <= (len = inputStream.read(flush))) {
                os.write(flush, 0, len);
            }
            os.flush();


            decompressPath = strZipOldPath + ImageFileName;
        } catch (Exception e) {
            LogUtil.doWriteLog("====系统文件读取异常===>" + e.getMessage());
        }
        finally
        {
            try
            {
                //关闭流的注意 先打开的后关
                os.close();
                inputStream.close();
            } catch (IOException e)
            {
                e.printStackTrace();
                LogUtil.doWriteLog("====系统文件读取异常===>" + e.getMessage());
            }
        }
        return decompressPath;
    }
    /**
     * 实际处理文档获取文档方法，将文档解压到压缩的原始目录下
     *
     * @param imageFileId
     * @throws Exception
     */
    public static String getRealDoc(int imageFileId,int i) {
        String decompressPath = "";
        ImageFileManager im = new ImageFileManager();
        im.getImageFileInfoById(imageFileId);
        InputStream inputStream = im.getInputStream();
        String strFilerealpath = im.getFileRealPath();
        strFilerealpath = strFilerealpath.replaceAll("\\\\", "/");
        //解压路径
        int intlastIndex = strFilerealpath.lastIndexOf("/");
        String strZipOldPath = "D:/WEAVER/ecology/contactReal/";
        String ImageFileName = im.getImageFileName().replaceAll("[\\s\\\\/:\\*\\?\\\"<>\\|]", "&");
        try {
            OutputStream os = new FileOutputStream(new File(strZipOldPath + imageFileId+ImageFileName));
            //文件拷贝
            byte flush[] = new byte[1024];
            int len = 0;
            while (0 <= (len = inputStream.read(flush))) {
                os.write(flush, 0, len);
            }
            os.flush();
            //关闭流的注意 先打开的后关
            os.close();
            inputStream.close();
            decompressPath = strZipOldPath + imageFileId+ImageFileName;
        } catch (Exception e) {
            LogUtil.doWriteLog("====系统文件读取异常===>" + e);
        }
        return decompressPath;
    }
    
    /**
     * 实际处理文档获取文档方法，将文档解压到压缩的原始目录下
     *
     * @param imageFileId
     * @throws Exception
     */
    public static String getRealDoc(int imageFileId,int i,int j) {
        String decompressPath = "";
        ImageFileManager im = new ImageFileManager();
        im.getImageFileInfoById(imageFileId);
        InputStream inputStream = im.getInputStream();
        String strFilerealpath = im.getFileRealPath();
        strFilerealpath = strFilerealpath.replaceAll("\\\\", "/");
        //解压路径
        int intlastIndex = strFilerealpath.lastIndexOf("/");
        String strZipOldPath = "D:/WEAVER/ecology/contactReal/";
        String ImageFileName = im.getImageFileName().replaceAll("[\\s\\\\/:\\*\\?\\\"<>\\|]", "&");
        try {
            OutputStream os = new FileOutputStream(new File(strZipOldPath + imageFileId+ImageFileName));
            //文件拷贝
            byte flush[] = new byte[1024];
            int len = 0;
            while (0 <= (len = inputStream.read(flush))) {
                os.write(flush, 0, len);
            }
            os.flush();
            //关闭流的注意 先打开的后关
            os.close();
            inputStream.close();
            decompressPath = strZipOldPath +ImageFileName;
        } catch (Exception e) {
            LogUtil.doWriteLog("====系统文件读取异常===>" + e);
        }
        return decompressPath;
    }


    /**
     * 将zip文档解压出来，并将其转pdf存在原压缩目录下
     *
     * @return pdf文件的路径
     */
    public static List<Map> getDoc(List listDocids) {
        int docid = 0;
        boolean result = false;
        String strPath = "";
        List listpath = new ArrayList();
        try
        {
            //1.需要在此添加表单pdf的路径信息，
            for (int i = 0; i < listDocids.size(); i++) {
                Map mapoldAndNew = new HashMap();
                docid = Util.getIntValue((String) listDocids.get(i), 0);
                //将文档解压到原始目录下,得到解压的真实路径
                strPath = getRealDoc(getImageFileId(docid));
                if (!"".equals(strPath)) {
                    //将文档转为pdf，依然存在该目录下
                    ConvertToPdf conpdf = new ConvertToPdf();
                    result = conpdf.convert2PDF(strPath, strPath.substring(0, strPath.lastIndexOf(".")) + ".pdf");
                    result = false;
                    LogUtil.debugLog("===文件保存的原始目录==" + strPath);
                    mapoldAndNew.put("oldpath", strPath);
                    LogUtil.debugLog("===转为pdf的文件保存目录==" + strPath.substring(0, strPath.lastIndexOf(".")) + ".pdf");
                    mapoldAndNew.put("newpath", strPath.substring(0, strPath.lastIndexOf(".")) + ".pdf");
                    mapoldAndNew.put("result", result);
                    listpath.add(mapoldAndNew);
                }

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            LogUtil.debugLog("异常:" + e.getMessage());
        }


        return listpath;
    }

    /**
     * 判断多级文件夹是否存在，不存在就创建
     */
    public static void judeDirExistsAll(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                LogUtil.doWriteLog("dirs exists");
            } else {
                LogUtil.doWriteLog("the same name file exists, can not create dirs");
            }
        } else {
            LogUtil.doWriteLog("dirs not exists, create it ...");
            file.mkdirs();
        }
    }

    public static void setTableOfPDFInfo(String docid, String requestid) {
        //增加流程和docid的对应
        RecordSet rssel = new RecordSet();
        rssel.executeSql("select id from wn_tabllepdfofrequest where requestid='" + requestid + "'");
        if (rssel.next()) {
            RecordSet rsInsert = new RecordSet();
            rsInsert.execute("update  wn_tabllepdfofrequest set docid='" + docid + "' where requestid='" + requestid + "'");
        } else {
            RecordSet rsInsert = new RecordSet();
            rsInsert.execute("insert into wn_tabllepdfofrequest (docid,requestid) values(" + docid + "," + requestid + ")");
        }

    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir 要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            System.out.println("删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = DocUtil.deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = DocUtil.deleteDirectory(files[i]
                        .getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            System.out.println("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            System.out.println("删除目录" + dir + "成功！");
            return true;
        } else {
            return false;
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }


    /**
     * 删除文件，可以是文件或文件夹
     *
     * @param fileName 要删除的文件名
     * @return 删除成功返回true，否则返回false
     */
    public static boolean delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("删除文件失败:" + fileName + "不存在！");
            return false;
        } else {
            if (file.isFile())
                return deleteFile(fileName);
            else
                return deleteDirectory(fileName);
        }
    }

    /**
     * 复制文件的方法
     */
    public static void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
                fs.close();
            }
        } catch (Exception e) {
            LogUtil.debugLog("复制单个文件操作出错");
            e.printStackTrace();
        }
    }

    /**
     * @param docId
     * @return 获取文档名称
     */
    public static String getFileName(String docId) {
        String fileName = "";
        if (!"".equals(docId)) {
            RecordSet recordSet = new RecordSet();
            String sql = "select imagefilename from DocImageFile where docid=" + docId;
            recordSet.executeSql(sql);
            if (recordSet.next()) {
                fileName = Util.null2String(recordSet.getString("imagefilename"));
            }
        }
        return fileName;
    }

    /**
     * @param docid
     * @return 获取文档创建人的登陆账号
     */
    public static String getDocCreaterLoginId(String docid) {
        DocComInfo info = new DocComInfo();
        String createid = info.getDocCreaterid(docid);

        String LoginId = "";
        try {
            ResourceComInfo resinfo = new ResourceComInfo();
            LoginId = resinfo.getLoginID(createid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return LoginId;
    }

    /**
     * @param docid
     * @return 文档子目录
     */
    public static String getDocDirectory(String docid) {
        DocComInfo info = new DocComInfo();
        String Directory = info.getDocSecCategory(docid) + "";
        return Directory;
    }


}
