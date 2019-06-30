package com.weavernorth.ftp;

import com.weavernorth.util.LogUtil;
import org.apache.axis.components.logger.LogFactory;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;


public class FtpUtil_zz {
    public FTPClient ftp;
    public ArrayList<String> arFiles;
    public static Log log = LogFactory.getLog(FtpUtil_zz.class.getName());


    /**
     * 重载构造函数
     *
     * @param isPrintCommmand 是否打印与FTPServer的交互命令
     */
    public FtpUtil_zz(boolean isPrintCommmand) {
        ftp = new FTPClient();
        arFiles = new ArrayList<String>();
        if (isPrintCommmand) {
            ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        }
    }

    /**
     * 查找Ftp服务器设置对象
     *
     * @param mapDs 服务器实际路径
     * @return FtpObject: Ftp设置对象
     */
    public FtpObject getFtpObject(Map<String, String> mapDs) {

        FtpObject fo = new FtpObject();

        // 获取FTP设置的URL，ftp://127.0.0.1:21/uploads
        String strDsURL = mapDs.get("url");
        strDsURL = strDsURL.substring(6, strDsURL.length());
        // 截取ip或域名
        String strHost = strDsURL.substring(0, strDsURL.indexOf(":"));
        // 截取端口
        String strPort = strDsURL.substring(strDsURL.indexOf(":") + 1, strDsURL.indexOf("/"));
        int intPort = Integer.parseInt(strPort);
        // 截取路径
        String strPath = strDsURL.substring(strDsURL.indexOf("/") + 1, strDsURL.length());

        fo.setHostName(strHost);
        fo.setPort(intPort);
        fo.setPath(strPath);

        fo.setUserName(mapDs.get("username"));
        fo.setPassWord(mapDs.get("password"));

        return fo;
    }

    /**
     * 登陆FTP服务器
     *
     * @param fo ftp对象，包含ftp的IP、用户名密码、端口号等
     * @return boolean 是否登录成功
     * @throws IOException
     */
    public boolean login(FtpObject fo) throws IOException {
        this.ftp.connect(fo.getHostName(), fo.getPort());
        if (FTPReply.isPositiveCompletion(this.ftp.getReplyCode())) {
            if (this.ftp.login(fo.getUserName(), fo.getPassWord())) {
                this.ftp.setControlEncoding("GBK");
                return true;
            }
        }
        if (this.ftp.isConnected()) {
            this.ftp.disconnect();
        }
        return false;
    }

    /**
     * 关闭数据链接
     *
     * @throws IOException
     */
    public void disConnection() throws IOException {
        if (this.ftp.isConnected()) {
            this.ftp.disconnect();
        }
    }

    /**
     * 递归遍历出目录下面所有文件
     *
     * @param pathName     需要遍历的目录，必须以"/"开始和结束
     * @param isSearchPath 是否需要遍历子目录
     * @throws IOException
     */
    public void listFiles(String pathName, boolean isSearchPath) throws IOException {
        if (pathName.startsWith("/") && pathName.endsWith("/")) {
            String directory = pathName;
            //更换目录到当前目录
            this.ftp.changeWorkingDirectory(directory);
            FTPFile[] files = this.ftp.listFiles();
            for (FTPFile file : files) {
                //解决中文乱码问题
                String strFileName = new String(file.getName().getBytes("GBK"), "ISO-8859-1");

                if (file.isFile()) {
                    arFiles.add(directory + strFileName);
                } else if (isSearchPath && file.isDirectory()) {
                    listFiles(directory + strFileName + "/", true);
                }
            }
        }
    }
    /**
     * 递归遍历出目录下面所有excel文件
     *
     * @param pathName     需要遍历的目录，必须以"/"开始和结束
     * @param s
     * @param isSearchPath 是否需要遍历子目录
     * @throws IOException
     */
    public void listFilesExcel(FtpObject fo, String pathName, String s, String oldPath , boolean isSearchPath) throws IOException {
        LogUtil.debugLog("----listFilesExcel进入" + pathName);
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(fo.getHostName(), fo.getPort());
        if (ftpClient.login(fo.getUserName(), fo.getPassWord())) {
            LogUtil.debugLog("----listFilesExcel--login" + pathName);
            //更换目录到当前目录
//            this.ftp.changeWorkingDirectory(pathName);
            File[] files = new File(pathName).listFiles();
            assert files != null;
            for (File file : files) {
                //解决中文乱码问题
                String strFileName = file.getName();
                LogUtil.debugLog("----listFilesExcel---strFileName" + strFileName);
                String suffix = getFileSufix(strFileName);
                if (file.isFile() && ("xls".equals(suffix) || "xlsx".equals(suffix))) {
                    LogUtil.debugLog("----listFilesExcel---excel---strFileName" + strFileName);
                    uploadOneExcelFile(fo, oldPath, file, strFileName);
                } else if (isSearchPath && file.isDirectory()) {
                    listFilesExcel(fo, pathName +"/"+ strFileName , s, pathName.substring(pathName.indexOf(s),pathName.length())+"/"+ strFileName,true);
                }
            }
        } else {
            LogUtil.debugLog("登录FTP失败!请检查用户名和密码!");
        }
    }
    /**
     * 获取文件后缀
     *
     * @param fileName
     * @return
     * @author SHANHY
     */
    private String getFileSufix(String fileName) {
        int splitIndex = fileName.lastIndexOf(".");
        return fileName.substring(splitIndex + 1);
    }


    /**
     * 递归遍历目录下面指定的文件名
     *
     * @param pathName 需要遍历的目录，必须以"/"开始和结束
     * @param ext      文件的扩展名
     * @throws IOException
     */
    public void listFiles(String pathName, String ext) throws IOException {
        if (pathName.startsWith("/") && pathName.endsWith("/")) {
            String directory = pathName;
            //更换目录到当前目录
            this.ftp.changeWorkingDirectory(directory);
            FTPFile[] files = this.ftp.listFiles();
            for (FTPFile file : files) {
                //解决中文乱码问题
                String strFileName = new String(file.getName().getBytes("GBK"), "ISO-8859-1");

                if (file.isFile()) {
                    if (ext == null || "".equals(ext)) {
                        arFiles.add(directory + strFileName);
                    } else {
                        if (file.getName().endsWith(ext)) {
                            arFiles.add(directory + strFileName);
                        }
                    }
                } else if (file.isDirectory()) {
                    listFiles(directory + strFileName + "/", ext);
                }
            }
        }
    }

    /**
     * FTP上传单个文件
     *
     * @param fo            ftp对象，包含ftp的IP、用户名密码、端口号等
     * @param strUpoladPath 服务器端文件夹，类似“/admin/pic”
     * @param fileSource    需要上传的文件对象
     * @param strTargetName 服务器端文件名。如果留空，则使用原名
     * @return String 返回消息。如果是success，则上传成功，否则均为错误信息。
     */
    public String uploadOneFile(FtpObject fo, String strUpoladPath, File fileSource, String strTargetName) throws IOException {
        log.info("uploadOneFile..............begin------");
        String Message = "success";
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(fo.getHostName(), fo.getPort());
        FileInputStream fis = null;
        try {
            if (strTargetName == null || "".equals(strTargetName)) {
                strTargetName = fileSource.getName();
            }
            if (ftpClient.login(fo.getUserName(), fo.getPassWord())) {
                fis = new FileInputStream(fileSource);
                //设置上传目录
                ftpClient.enterLocalPassiveMode();
                ftpClient.setBufferSize(1024);
                ftpClient.setControlEncoding("UTF-8");
                //设置文件类型（二进制）
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                log.info("strUpoladPath:" + strUpoladPath);

                if(strUpoladPath != null && !"".equals(strUpoladPath.trim())){
                    String[] pathes = strUpoladPath.split("/");
                    for(String onePath :pathes){
                        if(onePath == null || "".equals(onePath.trim())){
                            continue;
                        }
                        onePath = new String(onePath.getBytes("UTF-8"),"iso-8859-1");
                        if(!ftpClient.changeWorkingDirectory(onePath)){
                            ftpClient.makeDirectory(onePath);
                            ftpClient.changeWorkingDirectory(onePath);
                        }
                    }
                }
                ftpClient.storeFile(new String(strTargetName.getBytes("UTF-8"), "iso-8859-1"), fis);
//                if (ftpClient.changeWorkingDirectory(strUpoladPath)) {
//                    ftpClient.storeFile(new String(strTargetName.getBytes("UTF-8"), "iso-8859-1"), fis);
//                } else {
//                    if (ftpClient.makeDirectory(strUpoladPath)) {
//                        ftpClient.changeWorkingDirectory(strUpoladPath);
//                        ftpClient.storeFile(new String(strTargetName.getBytes("UTF-8"), "iso-8859-1"), fis);
//                    } else {
//                        Message = "登录FTP成功!但是创建文件加失败,请确认是否有文件创建权限!";
//                    }
//                }
            } else {
                Message = "登录FTP失败!请检查用户名和密码!";
            }
        } catch (IOException e) {
            e.printStackTrace();
            Message = e.getMessage();
            log.error(e.getMessage());
        } finally {
            IOUtils.closeQuietly(fis);
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
                Message = e.getMessage();
                log.error(e.getMessage());
            }
        }
        return Message;
    }


    /**
     * FTP上传单个文件
     *
     * @param fo            ftp对象，包含ftp的IP、用户名密码、端口号等
     * @param strUpoladPath 服务器端文件夹，类似“/admin/pic”
     * @param fileSource    需要上传的文件对象
     * @param strTargetName 服务器端文件名。如果留空，则使用原名
     * @return String 返回消息。如果是success，则上传成功，否则均为错误信息。
     */
    private void uploadOneExcelFile(FtpObject fo, String strUpoladPath, File fileSource, String strTargetName) throws IOException {
        log.info("uploadOneExcelFile..............begin------");
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(fo.getHostName(), fo.getPort());
        FileInputStream fis = null;
        try {
            if (strTargetName == null || "".equals(strTargetName)) {
                strTargetName = fileSource.getName();
            }
            if (ftpClient.login(fo.getUserName(), fo.getPassWord())) {
                fis = new FileInputStream(fileSource);
                //设置上传目录
                ftpClient.enterLocalPassiveMode();
                ftpClient.setBufferSize(1024);
                ftpClient.setControlEncoding("UTF-8");
                //设置文件类型（二进制）
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                log.info("strUpoladPath:" + strUpoladPath);

                if (strUpoladPath != null && !"".equals(strUpoladPath.trim())) {
                    String[] pathes = strUpoladPath.split("/");
                    for (String onePath : pathes) {
                        if (onePath == null || "".equals(onePath.trim())) {
                            continue;
                        }
                        onePath = new String(onePath.getBytes("UTF-8"), "iso-8859-1");
                        if (!ftpClient.changeWorkingDirectory(onePath)) {
                            ftpClient.makeDirectory(onePath);
                            ftpClient.changeWorkingDirectory(onePath);
                        }
                    }
                }
                ftpClient.storeFile(new String(strTargetName.getBytes("UTF-8"), "iso-8859-1"), fis);
            }else {
                LogUtil.debugLog("uploadOneExcelFile登录FTP失败!请检查用户名和密码!");
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        } finally {
            IOUtils.closeQuietly(fis);
        }
    }

    /**
     * FTP下载单个文件
     *
     * @param fo                ftp对象，包含ftp的IP、用户名密码、端口号等
     * @param strRemoteFileName 服务器端文件夹及文件名，类似“/admin/pic/test.gif”
     * @return String 返回消息。如果是success，则上传成功，否则均为错误信息。
     */
    public String downloadOneFile(FtpObject fo, String strRemoteFileName, String strTargetName) {
        FTPClient ftpClient = new FTPClient();
        FileOutputStream fos = null;

        try {
            ftpClient.connect(fo.getHostName(), fo.getPort());
            ftpClient.login(fo.getUserName(), fo.getPassWord());

            if (strTargetName == null || "".equals(strTargetName)) {
                strTargetName = strRemoteFileName;
            }
            fos = new FileOutputStream(strTargetName);

            ftpClient.setBufferSize(1024);
            //设置文件类型（二进制）
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

            strRemoteFileName = new String(strRemoteFileName.getBytes("GBK"), "iso-8859-1");
            ftpClient.retrieveFile(strRemoteFileName, fos);

            return "success";
        } catch (IOException e) {
            e.printStackTrace();
            return "FTP客户端出错！";
        } finally {
            IOUtils.closeQuietly(fos);
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
                return "关闭FTP连接发生异常！";
            }
        }

    }

    /**
     * FTP获取单个文件的InputStream
     *
     * @param fo                ftp对象，包含ftp的IP、用户名密码、端口号等
     * @param strRemoteFileName 服务器端文件夹及文件名，类似“/admin/pic/test.gif”
     * @return InputStream 返回文件流对象。
     */
    public InputStream getOneFileStream(FtpObject fo, String strRemoteFileName) {
        FTPClient ftpClient = new FTPClient();
        InputStream is = null;

        try {
            ftpClient.connect(fo.getHostName(), fo.getPort());
            ftpClient.login(fo.getUserName(), fo.getPassWord());


            ftpClient.setBufferSize(1024);
            //设置文件类型（二进制）
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

            strRemoteFileName = new String(strRemoteFileName.getBytes("GBK"), "iso-8859-1");
            is = ftpClient.retrieveFileStream(strRemoteFileName);

            return is;
        } catch (IOException e) {
            LogUtil.doWriteLog("FTP客户端出错！");
        } finally {
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                LogUtil.doWriteLog("关闭FTP连接发生异常！");
            }
        }

        return is;
    }

    /**
     * 删除ftp上的文件
     *
     * @param strFilePath 需要删除文件，包含服务器端的相对路径、文件名，如“/admin/test.jpg”
     * @return boolean 是否成功删除
     */
    public boolean removeOneFile(String strFilePath) {
        boolean flag = false;
        if (ftp != null) {
            try {
                flag = ftp.deleteFile(strFilePath);
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    this.disConnection();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return false;
                }
            }
        }
        return flag;
    }

    /**
     * FTP指定目录下的所有文件
     *
     * @param fo            ftp对象，包含ftp的IP、用户名密码、端口号等
     * @param isSearchPath  是否需要遍历子目录
     * @param strTargetPath 客户端（本地）文件夹。
     * @return String 返回消息。如果是success，则上传成功，否则均为错误信息。
     */
    public String downloadAllFile(FtpObject fo, boolean isSearchPath, String strTargetPath) {
        FtpUtil_zz f = new FtpUtil_zz(true);
        FileOutputStream fos = null;
        try {
            if (f.login(fo)) {
                String strRemotePath = fo.getPath();
                if (strRemotePath == null || "".equals(strRemotePath)) {
                    strRemotePath = "/";
                }
                if (!strRemotePath.endsWith("/")) {
                    strRemotePath = strRemotePath + "/";
                }

                //需要遍历的文件的扩展名，如果留空，则遍历所有文件。
                String strExt = fo.getExt();
                if (strExt == null || "".equals(strExt)) {
                    f.listFiles(strRemotePath, isSearchPath);
                } else {
                    f.listFiles(strRemotePath, strExt);
                }

                String strTargetName = "";
                String strSourcePath = "";
                Iterator<String> it = f.arFiles.iterator();
                while (it.hasNext()) {
                    strTargetName = it.next();
                    strSourcePath = strTargetName.substring(0, strTargetName.lastIndexOf("/"));
                    strTargetName = strTargetName.substring(strTargetName.lastIndexOf("/") + 1, strTargetName.length());

                    strTargetPath = strTargetPath + strSourcePath;
                    File fileTargetPath = new File(strTargetPath);

                    strTargetName = strTargetPath + strTargetName;

                    fos = new FileOutputStream(strTargetName);

                    ftp.setBufferSize(1024);
                    //设置文件类型（二进制）
                    ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
                    ftp.retrieveFile(it.next(), fos);
                    fos.close();
                }

                IOUtils.closeQuietly(fos);
                try {
                    f.disConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                    return "关闭FTP连接发生异常！";
                }
                return "success";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "FTP客户端出错！";
        }

        return "error";
    }

    public static void main(String[] args) throws IOException {

        FtpUtil_zz f = new FtpUtil_zz(true);

        FtpObject fo = new FtpObject();
        fo.setHostName("10.2.100.95");
        fo.setUserName("oa");
        fo.setPassWord("ziguangoa");
        fo.setPort(21);

        File file = new File("E:\\imageTest\\unzipFile\\2019-03-14\\54986\\test.doc");

        LogUtil.doWriteLog(f.uploadOneFile(fo, "/doc", file, "test.doc"));

        //
        //    	if(f.login(fo)){
        //            f.listFiles("/",null);
        //        }
        //
        ////    	f.downloadOneFile(fo, "/test/2016/吴昊2015年第三季度KPI.xlsx", "d:/吴昊2015年第三季度KPI.xlsx");
        ////    	f.downloadAllFile(fo, false, "d:/");
        //    	InputStream is=f.getOneFileStream(fo, "/test/2016/吴昊2015年第三季度KPI.xlsx");
        //    	FileOutputStream  fos = new FileOutputStream("d:/吴昊2015年第三季度KPI.xlsx");
        //        //控制一次性输出到客户端的数据的大小
        //        byte[] bytBuf = new byte[1024 * 5];
        //        int intSize = -1;
        //        while ((intSize = is.read(bytBuf)) > 0) {
        //            fos.write(bytBuf, 0, intSize);
        //        }
        //        if(is!=null){
        //            is.close();
        //        }
        //
        //
        //        f.disConnection();
        //        Iterator<String> it = f.arFiles.iterator();
        //        while(it.hasNext()){
        //        	LogUtil.doWriteLog(it.next());
        //        }

    }
}
