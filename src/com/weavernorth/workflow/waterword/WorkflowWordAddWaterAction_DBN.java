package com.weavernorth.workflow.waterword;

import com.weavernorth.OA2archives.util.ConvertToPdf;
import com.weavernorth.OA2archives.util.DocUtil;
import com.weavernorth.createDoc.webservice.DocCreateService;
import com.weavernorth.util.LogUtil;
import com.weavernorth.workflow.waterpdf.WaterPDF;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.workflow.action.BaseAction;
import weaver.soa.workflow.request.RequestInfo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * DBN合同签订流程 加水印
 *
 * @create 2019-06-29
 **/
public class WorkflowWordAddWaterAction_DBN extends BaseAction {

    @Override
    public String execute(RequestInfo request) {
        String requestId = request.getRequestid();
        String workFlowId = request.getWorkflowid();
        int formId = request.getRequestManager().getFormid();
        String tableName = "";
        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("SELECT tablename FROM workflow_bill WHERE id = '" + formId + "'");
        if (recordSet.next()) {
            tableName = recordSet.getString("tablename");
        }

        RecordSet rs = new RecordSet();
        JSONArray array = new JSONArray();
        // 水影图片保存路径
        String imgPath = "D:/WEAVER/ecology/weavernorth/workflow/WaterMark.png";

        this.writeLog("==DBN合同签订流程Action Start==> requestid: " + requestId + " tableName: " + tableName + " formId: " + formId);

        // DBN合同签订流程
        if ("359".equals(workFlowId)) {
            String selSql = "select * from " + tableName + " where requestid=" + requestId;
            rs.executeSql(selSql);
            this.writeLog("==DBN合同签订流程Action查询文档附件==>" + selSql);
            if (rs.next()) {
                // 合同正文
                String htzwfj = Util.null2String(rs.getString("htzwfj"));
                if (!"".equals(htzwfj)) {
                    String[] htzwfjs = htzwfj.split(",");
                    for (String bdsczw1 : htzwfjs) {
                        String docids1 = DocUtil.getImageFileId(Integer.parseInt(bdsczw1)) + "";
                        JSONObject object = new JSONObject();
                        object.put("fieldName", "htzwfj");
                        object.put("fieldValue", docids1);
                        object.put("creater", DocUtil.getDocCreaterLoginId(bdsczw1));
                        object.put("fileName", DocUtil.getFileName(bdsczw1));
                        object.put("directory", DocUtil.getDocDirectory(bdsczw1));
                        array.add(object);
                    }
                }

                // 合同附件
                String htfj = Util.null2String(rs.getString("htfj"));
                if (!"".equals(htfj)) {
                    String[] htzwwds = htfj.split(",");
                    for (String htzwwd1 : htzwwds) {
                        String docids1 = DocUtil.getImageFileId(Integer.parseInt(htzwwd1)) + "";
                        JSONObject object = new JSONObject();
                        object.put("fieldName", "htfj");
                        object.put("fieldValue", docids1);
                        object.put("creater", DocUtil.getDocCreaterLoginId(htzwwd1));
                        object.put("fileName", DocUtil.getFileName(htzwwd1));
                        object.put("directory", DocUtil.getDocDirectory(htzwwd1));
                        array.add(object);
                    }
                }

                // 相关附件
                String xgfj = Util.null2String(rs.getString("xgfj"));
                if (!"".equals(xgfj)) {
                    String[] htzwwds = xgfj.split(",");
                    for (String htzwwd1 : htzwwds) {
                        String docids1 = DocUtil.getImageFileId(Integer.parseInt(htzwwd1)) + "";
                        JSONObject object = new JSONObject();
                        object.put("fieldName", "xgfj");
                        object.put("fieldValue", docids1);
                        object.put("creater", DocUtil.getDocCreaterLoginId(htzwwd1));
                        object.put("fileName", DocUtil.getFileName(htzwwd1));
                        object.put("directory", DocUtil.getDocDirectory(htzwwd1));
                        array.add(object);
                    }
                }

            }
            this.writeLog("==DBN合同签订流程array=>" + array.toString());

        }

        // 循环解压图片
        if (array.size() > 0) {
            //用来记录附件字段是否加过水印
            String fieldContent = "";
            //是否加过水印
            boolean hasChange;
            RecordSet rsUp = new RecordSet();

            for (int i = 0; i < array.size(); i++) {
                JSONObject object = array.getJSONObject(i);
                // 流程字段名称
                String fieldName = object.getString("fieldName");
                this.writeLog("fieldName==" + fieldName);
                //如果改字符串中包含该字段名称，那么就执行带有逗号的更新
                hasChange = fieldContent.contains(fieldName);
                // 文档名称
                String fileName = object.getString("fileName");
                // 文档创建人登陆账号
                String creater = object.getString("creater");
                this.writeLog("creater==" + creater);
                // 文档子目录
                String directoryid = object.getString("directory");
                this.writeLog("directoryid==" + directoryid);
                // 得到文档imagefileid
                int imagefileid = Integer.parseInt(object.getString("fieldValue"));
                // 文件真实路径
                String fileRealPath = DocUtil.getRealDoc(imagefileid, 0);
                // 加水印是否成功
                boolean result = false;
                this.writeLog("fileRealPath==" + fileRealPath);
                // 只有是word格式的才加水印
                if (fileRealPath.contains("doc") || fileRealPath.contains("docx") || fileRealPath.contains("pdf")) {
                    // word加水印
                    if (fileRealPath.contains("doc") || fileRealPath.contains("docx")) {
                        //将文档转为pdf的操作
                        ConvertToPdf conpdf = new ConvertToPdf();
                        String pdfName = fileRealPath.substring(fileRealPath.lastIndexOf("/") + 1, fileRealPath.lastIndexOf("."));
                        this.writeLog("====pdfName====>" + pdfName);

                        //转为pdf
                        boolean isOk = conpdf.convert2PDF(fileRealPath, fileRealPath.substring(0, fileRealPath.lastIndexOf(".")) + ".pdf");
                        this.writeLog("======转为pdf的输出路径=======>" + fileRealPath.substring(0, fileRealPath.lastIndexOf(".")) + ".pdf");
                        this.writeLog("======word在加水印前转pdf是否成功=======>" + isOk);
                        String newFilePath = new BaseBean().getPropValue("contactpdf", "pdfpath") + pdfName + ".pdf";
                        this.writeLog("=======pdf加水印后的输出路径=========" + newFilePath);
                        // 加水印后的pdf文件保存的位置
                        try {
                            // 加水印后的pdf的生成路径
                            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(newFilePath)));
                            // 调用
                            try {
                                WaterPDF.addPdfMark(fileRealPath.substring(0, fileRealPath.lastIndexOf(".")) + ".pdf", newFilePath, "", 0);
                            } catch (Exception e) {
                                this.writeLog("-=====pdf加水印异常=======>" + e);
                                e.printStackTrace();
                            }
                        } catch (FileNotFoundException e) {
                            LogUtil.doWriteLog("=流程附件源文件未找到==>" + e);
                        }
                        result = true;
                        fileName = pdfName + ".pdf";
                        fileRealPath = newFilePath;
                        this.writeLog("====上传前fileRealPath=======" + fileRealPath);
                        // pdf加水印
                    } else if (fileRealPath.contains("pdf")) {
                        // 输出新的加水印的pdf文件路径
                        String pdfName = fileRealPath.substring(fileRealPath.lastIndexOf("/") + 1, fileRealPath.lastIndexOf("."));
                        // 加水印后的pdf文件保存的位置
                        String newFilePath = new BaseBean().getPropValue("contactpdf", "pdfpath") + imagefileid + pdfName + ".pdf";
                        try {
                            // 加水印后的pdf的生成路径
                            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(newFilePath)));
                            // 调用
                            try {
                                WaterPDF.addPdfMark(fileRealPath, newFilePath, "", 0);
                            } catch (Exception e) {
                                this.writeLog("-=====pdf加水印异常=======>" + e);
                                e.printStackTrace();
                            }
                        } catch (FileNotFoundException e) {
                            LogUtil.doWriteLog("=流程附件源文件未找到==>" + e);
                        }
                        result = true;
                        fileRealPath = newFilePath;
                    }
                    // 生成文件到到系统中并更新到流程表单上
                    if (result) {
                        DocCreateService service = new DocCreateService(
                                creater, "1");
                        // 新生成的文档id
                        // 考虑合同附件是多个的情况
                        String strDocId = service.init(fileName, fileRealPath,
                                directoryid, "");
                        String updateSql0 = "";

                        //已经更新过该字段
                        if (hasChange) {
                            String bakDocid = "";
                            String selSql1 = "select " + fieldName + " from "
                                    + tableName + " where requestid="
                                    + requestId;
                            RecordSet rssql = new RecordSet();
                            rssql.executeSql(selSql1);
                            if (rssql.next()) {
                                bakDocid = Util.null2String(rssql
                                        .getString(fieldName));
                            }
                            updateSql0 = "update " + tableName + " set "
                                    + fieldName + "='" + bakDocid + ","
                                    + strDocId + "' where requestid="
                                    + requestId;

                        } else {
                            updateSql0 = "update " + tableName + " set "
                                    + fieldName + "='" + strDocId
                                    + "' where requestid=" + requestId;
                            fieldContent += fieldName + ",";
                        }
                        rsUp.executeSql(updateSql0);
                        this.writeLog("==更新表单字段sql===>" + updateSql0);
                    }
                }
            }
        }
        return SUCCESS;
    }
}
