package com.sz.util;


import weaver.general.BaseBean;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Unzip extends BaseBean{
    static int fla = 1;//用来判断文件是否删除成功
//    public static void main(String[] args) {
//        // 文件存放位置
//        String zipPath = "C:\\work\\1\\4.zip";
//        File zipFile = new File(zipPath);
//        // 文件解压存放位置
//        String descDir = "C:\\work\\2\\";
//        boolean flag = unZip(zipFile, descDir);
//        //调用文件上传方法
//        //FTPUtil ftpUtil = new FTPUtil();
//        System.out.println("-----------------------应用启动------------------------");
//        FTPClient ftpClient = FTPUtil.connectFtpServer("192.168.3.45", 21, "599", "li", "utf-8");
//        File fileDir = new File("C:\\work\\2\\");
//        FTPUtil.uploadFiles(ftpClient,fileDir);
//
//        //deleteServerFiles(ftpClient,"/datas");
//        FTPUtil.closeFTPConnect(ftpClient);
//        System.out.println("-----------------------应用关闭------------------------");
//
//
//        File a = new File("C:\\work\\2\\");
//        deleteFile(a);
//        if (fla == 1){
//            System.out.println("文件删除成功！");
//        }
////        System.out.println("解压成功还是失败=" + flag);
//    }
   /**
     * 解压zip文件
     *

     * @return true/false
     */
    public  boolean unZip(File zipFile, String descDir,String name) {
        boolean flag = false;
        File pathFile = new File(descDir);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        ZipFile zip = null;
        try {
            // 指定编码，否则压缩包里面不能有中文目录
            zip = new ZipFile(zipFile);
            for (Enumeration entries = zip.entries(); entries.hasMoreElements();) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                //String zipEntryName = name;
                InputStream in = zip.getInputStream(entry);
                String outPath = (descDir + name).replace("/",
                        File.separator);
                // 判断路径是否存在,不存在则创建文件路径
                writeLog("解压后路径" +outPath );
                File file = new File(outPath.substring(0,
                        outPath.lastIndexOf(File.separator)));
                if (!file.exists()) {
                    file.mkdirs();
                }
                // 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
                if (new File(outPath).isDirectory()) {
                    continue;
                }

                OutputStream out = new FileOutputStream(outPath);
                byte[] buf1 = new byte[2048];
                int len;
                while ((len = in.read(buf1)) > 0) {
                    out.write(buf1, 0, len);
                }
                in.close();
                out.close();
            }
            flag = true;
            // 必须关闭，否则无法删除该zip文件
            zip.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }


    public  void deleteFile(File file){
        try {
            //判断文件不为null或文件目录存在
            if (file == null || !file.exists()){
                fla = 0;
                writeLog("文件删除失败,请检查文件路径是否正确");
                return;
            }
            //取得这个目录下的所有子文件对象
            File[] files = file.listFiles();
            //遍历该目录下的文件对象
            for (File f: files){
                //打印文件名
                String name = f.getName();
                writeLog(name);
                //判断子目录是否存在子目录,如果是文件则删除
                if (f.isDirectory()){
                    deleteFile(f);
                }else {
                    f.delete();
                }
            }
            //删除空文件夹  for循环已经把上一层节点的目录清空。
            file.delete();
        }catch(RuntimeException e){
            writeLog(e);
        }

    }





}

