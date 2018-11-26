package com.weavernorth.guoJiCaiWu;

import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class GetImage extends BaseAction {

    @Override
    public String execute(RequestInfo requestInfo) {
        this.writeLog("解压文件action执行开始=====================");
        String tableName = requestInfo.getRequestManager().getBillTableName();
        String requestId = requestInfo.getRequestid();

        RecordSet recordSet = new RecordSet();
        recordSet.execute("select * from " + tableName + " where requestid = '" + requestId + "'");
        String imageIdStr = "";
        if (recordSet.next()) {
            imageIdStr = recordSet.getString("fj");
        }

        this.writeLog("附件字段： " + imageIdStr);

        if (imageIdStr.length() <= 0) {
            return "1";
        }
        String[] images = imageIdStr.split(",");
        RecordSet imageSet = new RecordSet();
        for (String id : images) {
            imageSet.executeQuery("select * from docimagefile where docid = '" + id + "'");
            if (imageSet.next()) {
                String imagefileid = imageSet.getString("imagefileid");//路径表关联字段
                imageSet.executeQuery("select * from imagefile where imagefileid = '" + imagefileid + "'");
                imageSet.next();
                String filerealpath = imageSet.getString("filerealpath");//存放路径
                String imagefilename = imageSet.getString("imagefilename");//文件名

                this.writeLog("存放路径： " + filerealpath);
                this.writeLog("文件名： " + imagefilename);

                int i = imagefilename.lastIndexOf(".");
                String hzName = imagefilename.substring(i);//后缀名

                int j = filerealpath.lastIndexOf("\\");
                String path = filerealpath.substring(0, j + 1);//解压后路径
                unZip(new File(filerealpath), path, imagefilename);

                String afterPath = path + imagefilename;
                this.writeLog("解压后文件路径： " + afterPath);
            }
        }
        this.writeLog("解压文件action执行完毕=====================");

        return "1";
    }

    /**
     * zip解压
     *
     * @param srcFile     zip源文件
     * @param destDirPath 解压后的目标文件夹
     * @param hzm         后缀名
     * @throws RuntimeException 解压失败会抛出运行时异常
     */

    private void unZip(File srcFile, String destDirPath, String hzm) throws RuntimeException {
        // 判断源文件是否存在
        if (!srcFile.exists()) {
            throw new RuntimeException(srcFile.getPath() + "所指文件不存在");
        }
        // 开始解压
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(srcFile);
            Enumeration<?> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                // 如果是文件夹，就创建个文件夹
                if (entry.isDirectory()) {
                    String dirPath = destDirPath + "/" + hzm;
                    File dir = new File(dirPath);
                    dir.mkdirs();
                } else {
                    // 如果是文件，就先创建一个文件，然后用io流把内容copy过去
                    File targetFile = new File(destDirPath + "/" + hzm);
                    // 保证这个文件的父文件夹必须要存在
                    if (!targetFile.getParentFile().exists()) {
                        targetFile.getParentFile().mkdirs();
                    }
                    targetFile.createNewFile();
                    // 将压缩文件内容写入到这个文件中
                    InputStream is = zipFile.getInputStream(entry);
                    FileOutputStream fos = new FileOutputStream(targetFile);
                    int len;
                    byte[] buf = new byte[4096];
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    // 关流顺序，先打开的后关闭
                    fos.close();
                    is.close();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("unzip error from ZipUtils", e);
        } finally {
            if (zipFile != null) {
                try {
                    zipFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
