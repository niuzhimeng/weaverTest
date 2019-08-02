package com.weavernorth.workflow;

import com.weavernorth.createDoc.webservice.DocCreateService;
import com.weavernorth.workflow.vo.ZsHtmlToPdf;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;

import java.io.*;

public class WorkflowToPdfThread implements Runnable {

    private String requestId;

    private String tableName;
    /**
     * 子目录id
     */
    private static final String MU_LU_ID = "122";
    /**
     * 附件字段名
     */
    private static final String FU_JIAN_ID = "fj";

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        BaseBean baseBean = new BaseBean();
        RecordSet recordSet = new RecordSet();
        RecordSet updateSet = new RecordSet();
        baseBean.writeLog("流程表单转pdf Start requestid --- " + requestId + "   fromTable --- " + tableName);

        recordSet.executeQuery("select filerealpath from DocImageFile a,ImageFile b,docdetail c where a.imagefileid=b.imagefileid and a.docid=c.id and c.docsubject='" + requestId + "'");
        if (recordSet.next()) {
            // html 存放位置(无后缀名)
            String noSuffix = recordSet.getString("filerealpath");
            baseBean.writeLog("html文件路径：" + noSuffix);
            String htmlPath = noSuffix + ".html";
            // 赋值一份带有后缀的文件
            fileCopy(noSuffix, htmlPath);

            baseBean.writeLog("srcPath: " + htmlPath);
            baseBean.writeLog("pushPath: " + noSuffix + ".pdf");
            boolean convert = ZsHtmlToPdf.convert(htmlPath, noSuffix + ".pdf");
            baseBean.writeLog("html to pdf : " + convert);

            RecordSet getNameSet = new RecordSet();
            getNameSet.executeQuery("select requestnamenew from workflow_requestbase where requestid = " + requestId);
            getNameSet.next();
            String requestName = getNameSet.getString("requestnamenew");

            // 上传附件=============
            DocCreateService service = new DocCreateService("sysadmin", "1");
            // 新生成的文档id
            String strDocId = service.init(requestName + ".pdf", noSuffix + ".pdf", MU_LU_ID, "");
            baseBean.writeLog("生成文档id： " + strDocId);

            // 更新到表单附件字段中
            String updateSql = "update " + tableName + " set " + FU_JIAN_ID + " = '" + strDocId + "' where requestid=" + requestId;
            updateSet.executeUpdate(updateSql);
            baseBean.writeLog("流程表单转pdf End ===============");
        } else {
            baseBean.writeLog("流程表单转pdf执行过早，请调整延迟时间=========");
        }

    }

    /**
     * 无后缀文件，转成有后缀文件
     */
    private void fileCopy(String srcPath, String outPath) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(srcPath);
            outputStream = new FileOutputStream(outPath);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = inputStream.read(bytes)) > -1) {
                outputStream.write(bytes, 0, length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
