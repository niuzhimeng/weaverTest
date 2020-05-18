package com.weavernorth.test0518;

import com.weaver.general.TimeUtil;
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

/**
 * 附件获取测试
 */
public class GetFuJianAction extends BaseAction {

    @Override
    public String execute(RequestInfo requestInfo) {
        String requestId = requestInfo.getRequestid();
        String operateType = requestInfo.getRequestManager().getSrc();
        int formId = requestInfo.getRequestManager().getFormid();
        String tableName = "";
        RecordSet recordSet = new RecordSet();

        recordSet.executeQuery("SELECT tablename FROM workflow_bill WHERE id = '" + formId + "'");
        if (recordSet.next()) {
            tableName = recordSet.getString("tablename");
        }

        this.writeLog("附件获取测试 Start requestid=" + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {
            // 查询主表
            recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");
            recordSet.next();
            String fj = recordSet.getString("htzwfj");

            String pathSql = "SELECT im.imagefileid,im.imagefilename,im.filerealpath FROM ImageFile im LEFT JOIN " +
                    "DocImageFile df ON df.imagefileid = im.imagefileid WHERE df.docid IN ( " + fj + " )";
            this.writeLog("拼接后sql： " + pathSql);
            recordSet.executeQuery(pathSql);
            String dataPath = TimeUtil.getCurrentDateString().substring(0, 7);
            while (recordSet.next()) {
                String imagefilename = recordSet.getString("imagefilename");
                String filerealpath = recordSet.getString("filerealpath");
                String savePath = "D:\\WEAVER\\TmpU9File\\" + dataPath + "\\" + imagefilename;
                unZip(filerealpath, savePath);
            }

            this.writeLog("附件获取测试 End ===============");
        } catch (Exception e) {
            this.writeLog("附件获取测试 异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("附件获取测试 异常： " + e);
            return "0";
        }

        return "1";
    }

    private void unZip(String srcFilePath, String destDirPath) throws RuntimeException, IOException {
        System.setProperty("sun.jnu.encoding", "UTF-8");
        File srcFile = new File(srcFilePath);
        // 判断源文件是否存在
        if (!srcFile.exists()) {
            throw new RuntimeException(srcFile.getPath() + "所指文件不存在");
        }
        // 开始解压
        ZipFile zipFile = new ZipFile(srcFile);

        Enumeration<?> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            // 如果是文件，就先创建一个文件，然后用io流把内容copy过去
            File targetFile = new File(destDirPath);
            // 保证这个文件的父文件夹必须要存在
            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdirs();
            }
            // 将压缩文件内容写入到这个文件中
            InputStream is = zipFile.getInputStream(entry);
            FileOutputStream fos = new FileOutputStream(targetFile);
            int len;
            byte[] buf = new byte[2048];
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            // 关流顺序，先打开的后关闭
            fos.close();
            is.close();
        }

        if (zipFile != null) {
            try {
                zipFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
