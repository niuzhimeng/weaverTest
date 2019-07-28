package com.weavernorth.workflow;

import com.weavernorth.createDoc.webservice.DocCreateService;
import com.weavernorth.workflow.vo.ZsHtmlToPdf;
import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

import java.io.*;

/**
 * 流程表单转pdf
 */
public class WorkflowToPdf extends BaseAction {

    @Override
    public String execute(RequestInfo requestInfo) {
        String requestId = requestInfo.getRequestid();
        String operateType = requestInfo.getRequestManager().getSrc();
        int formId = requestInfo.getRequestManager().getFormid();
        String tableName = "";
        RecordSet updateSet = new RecordSet();
        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("SELECT tablename FROM workflow_bill WHERE id = '" + formId + "'");
        if (recordSet.next()) {
            tableName = recordSet.getString("tablename");
        }

        this.writeLog("流程表单转pdf Start requestid --- " + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {
            recordSet.executeQuery("select filerealpath from DocImageFile a,ImageFile b,docdetail c where a.imagefileid=b.imagefileid and a.docid=c.id and c.docsubject='" + requestId + "'");
            if (recordSet.next()) {
                // html 存放位置(无后缀名)
                String noSuffix = recordSet.getString("filerealpath");
                this.writeLog("html文件路径：" + noSuffix);
                String htmlPath = noSuffix + ".html";
                // 赋值一份带有后缀的文件
                fileCopy(noSuffix, htmlPath);

                this.writeLog("srcPath: " + htmlPath);
                this.writeLog("pushPath: " + noSuffix + ".pdf");
                boolean convert = ZsHtmlToPdf.convert(htmlPath, noSuffix + ".pdf");
                this.writeLog("html to pdf : " + convert);

                // 上传附件
                DocCreateService service = new DocCreateService("sysadmin", "1");
                // 新生成的文档id
                String strDocId = service.init(requestId + ".pdf", noSuffix + ".pdf", "122", "");
                this.writeLog("生成文档id： " + strDocId);

                // 更新到表单附件字段中
                String updateSql = "update " + tableName + " set fj = '" + strDocId + "' where requestid=" + requestId;
                updateSet.executeUpdate(updateSql);
            }


            this.writeLog("流程表单转pdf End ===============");
        } catch (Exception e) {
            this.writeLog("流程表单转pdf 异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("流程表单转pdf 异常： " + e);
            return "0";
        }
        return "1";
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

}
















