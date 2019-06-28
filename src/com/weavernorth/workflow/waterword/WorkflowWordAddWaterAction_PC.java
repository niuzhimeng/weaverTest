package com.weavernorth.workflow.waterword;

import com.lowagie.text.DocumentException;
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
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

import java.io.*;

/**
 * PC合同签订流程
 * 
 * @author
 * @create 2018-04-03 2:55
 **/
public class WorkflowWordAddWaterAction_PC implements Action {
	@Override
	public String execute(RequestInfo request) {
		String tableName = request.getRequestManager().getBillTableName();
		String requestId = request.getRequestid();
		String workFlowId = request.getWorkflowid();
		String docids1 = "";
		String docids2 = "";
		String docids3 = "";
		RecordSet rs = new RecordSet();
		JSONArray array = new JSONArray();
		// 水影图片保存路径
		String imgPath = "D:/WEAVER/ecology/weavernorth/workflow/WaterMark.png";
		// .replaceAll("//",File.separator);
		// 合同签订流程_PC V1:154 V2:255 
		if (workFlowId.equals("255")) {
			String selSql = "select bdsczw,htzwwd,htfj from " + tableName
					+ " where requestid=" + requestId;
			rs.executeSql(selSql);
			LogUtil.debugLog("==合同签订流程水印Action查询文档附件==>" + selSql);
			if (rs.next()) {
				// 本地上传正文
				String htzwfj = Util.null2String(rs.getString("bdsczw"));
				if (!"".equals(htzwfj)) {
					String[] htzwfjs = htzwfj.split(",");
					for (int i = 0; i < htzwfjs.length; i++) {
						String htzwfj1 = htzwfjs[i];
						docids1 = DocUtil.getImageFileId(Integer
								.parseInt(htzwfj1)) + "";
						JSONObject object = new JSONObject();
						object.put("fieldName", "bdsczw");
						object.put("fieldValue", docids1);
						object.put("creater",
								DocUtil.getDocCreaterLoginId(htzwfj1));
						object.put("fileName", DocUtil.getFileName(htzwfj1));
						object.put("directory",
								DocUtil.getDocDirectory(htzwfj1));
						array.add(object);
					}
				}
				// 相关附件
				String htzwwd = Util.null2String(rs.getString("htzwwd"));
				if (!"".equals(htzwwd)) {
					String[] htzwwds = htzwwd.split(",");
					for (int i = 0; i < htzwwds.length; i++) {
						String htzwwd1 = htzwwds[i];
						docids2 = DocUtil.getImageFileId(Integer
								.parseInt(htzwwd1)) + "";
						JSONObject object = new JSONObject();
						object.put("fieldName", "htzwwd");
						object.put("fieldValue", docids2);
						object.put("creater",
								DocUtil.getDocCreaterLoginId(htzwwd1));
						object.put("fileName", DocUtil.getFileName(htzwwd1));
						object.put("directory",
								DocUtil.getDocDirectory(htzwwd1));
						array.add(object);
					}
				}
				// 合同附件
				String htfj = Util.null2String(rs.getString("htfj"));
				if (!"".equals(htfj)) {
					String[] htfjs = htfj.split(",");
					for (int i = 0; i < htfjs.length; i++) {
						String htfj1 = htfjs[i];
						docids3 = DocUtil.getImageFileId(Integer
								.parseInt(htfj1)) + "";
						JSONObject object = new JSONObject();
						object.put("fieldName", "htfj");
						object.put("fieldValue", docids3);
						object.put("creater",
								DocUtil.getDocCreaterLoginId(htfj1));
						object.put("fileName", DocUtil.getFileName(htfj1));
						object.put("directory", DocUtil.getDocDirectory(htfj1));
						array.add(object);
					}
				}
			}

		}
		LogUtil.debugLog("==array=>" + array.toString());
		// 合同变更流程
		if (workFlowId.equals("158")) {
			String selSql = "select htzwwd,htzwfj,htfj from " + tableName
					+ " where requestid=" + requestId;
			rs.executeSql(selSql);
			LogUtil.debugLog("==合同变更流程水印Action查询文档附件==>" + selSql);
			if (rs.next()) {
				// 相关附件
				String htzwwd = Util.null2String(rs.getString("htzwwd"));
				if (!"".equals(htzwwd)) {
					String[] htzwwds = htzwwd.split(",");
					for (int i = 0; i < htzwwds.length; i++) {
						String htzwwd1 = htzwwds[i];
						docids1 = DocUtil.getImageFileId(Integer
								.parseInt(htzwwd1)) + "";
						JSONObject object = new JSONObject();
						object.put("fieldName", "htzwwd");
						object.put("fieldValue", docids1);
						object.put("creater",
								DocUtil.getDocCreaterLoginId(htzwwd1));
						object.put("fileName", DocUtil.getFileName(htzwwd1));
						object.put("directory",
								DocUtil.getDocDirectory(htzwwd1));
						array.add(object);
					}
				}
				// 合同正文附件
				String htzwfj = Util.null2String(rs.getString("htzwfj"));
				if (!"".equals(htzwfj)) {
					String[] htzwfjs = htzwfj.split(",");
					for (int i = 0; i < htzwfjs.length; i++) {
						String htzwfj1 = htzwfjs[i];
						docids2 = DocUtil.getImageFileId(Integer
								.parseInt(htzwfj1)) + "";
						JSONObject object = new JSONObject();
						object.put("fieldName", "htzwfj");
						object.put("fieldValue", docids2);
						object.put("creater",
								DocUtil.getDocCreaterLoginId(htzwfj1));
						object.put("fileName", DocUtil.getFileName(htzwfj1));
						object.put("directory",
								DocUtil.getDocDirectory(htzwfj1));
						array.add(object);
					}
				}
				// 合同附件
				String htfj = Util.null2String(rs.getString("htfj"));
				if (!"".equals(htfj)) {
					String[] htfjs = htfj.split(",");
					for (int i = 0; i < htfjs.length; i++) {
						String htfj1 = htfjs[i];
						docids3 = DocUtil.getImageFileId(Integer
								.parseInt(htfj1)) + "";
						JSONObject object = new JSONObject();
						object.put("fieldName", "htfj");
						object.put("fieldValue", docids3);
						object.put("creater",
								DocUtil.getDocCreaterLoginId(htfj1));
						object.put("fileName", DocUtil.getFileName(htfj1));
						object.put("directory", DocUtil.getDocDirectory(htfj1));
						array.add(object);
					}
				}
			}
		}
		// 循环解压图片
		if (array.size() > 0) {
			/*int index1 = 0;
			int index2 = 0;
			int index3 = 0;*/
			//用来记录附件字段是否加过水印
			String fieldContent = "";
			//是否加过水印
			boolean  hasChange = false;
			RecordSet rsUp = new RecordSet();
			for (int i = 0; i < array.size(); i++) {
				JSONObject object = array.getJSONObject(i);
				// 流程字段名称
				String fieldName = object.getString("fieldName");
				LogUtil.debugLog("fieldName==" + fieldName);
				//如果改字符串中包含该字段名称，那么就执行带有逗号的更新
				if(fieldContent.contains(fieldName)){
					hasChange = true;
				}
				// 文档名称
				String fileName = object.getString("fileName");
				// 文档创建人登陆账号
				String creater = object.getString("creater");
				LogUtil.debugLog("creater==" + creater);
				// 文档子目录
				String directoryid = object.getString("directory");
				LogUtil.debugLog("directoryid==" + directoryid);
				// 得到文档imagefileid
				int imagefileid = Integer.parseInt(object
						.getString("fieldValue"));
				// 文件真实路径
				String fileRealPath = DocUtil.getRealDoc(imagefileid, 0);
				// 加水印是否成功
				boolean result = false;
				LogUtil.debugLog("fileRealPath==" + fileRealPath);
				// 只有是word格式的才加水印
				if (fileRealPath.contains("doc")
						|| fileRealPath.contains("docx")
						|| fileRealPath.contains("pdf")) {
					// word加水印
					if (fileRealPath.contains("doc")
							|| fileRealPath.contains("docx")) {
						//将文档转为pdf的操作
						ConvertToPdf conpdf =  new ConvertToPdf();
						String pdfName = fileRealPath.substring(fileRealPath.lastIndexOf("/") + 1,fileRealPath.lastIndexOf("."));
						LogUtil.debugLog("====pdfName====>"+pdfName);
						//转为pdf
						boolean  isOk = conpdf.convert2PDF(fileRealPath, fileRealPath.substring(0, fileRealPath.lastIndexOf(".")) + ".pdf");
						LogUtil.debugLog("======转为pdf的输出路径=======>"+fileRealPath.substring(0, fileRealPath.lastIndexOf(".")) + ".pdf");
						LogUtil.debugLog("======word在加水印前转pdf是否成功=======>"+isOk);
						String newFilePath = new BaseBean().getPropValue("contactpdf", "pdfpath") +pdfName+ ".pdf";
						LogUtil.debugLog("=======pdf加水印后的输出路径========="+newFilePath);
						// 加水印后的pdf文件保存的位置
						try {
							// 加水印后的pdf的生成路径
							BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(newFilePath)));
							// 调用
							try {
								WaterPDF.addPdfMark(fileRealPath.substring(0, fileRealPath.lastIndexOf(".")) + ".pdf",newFilePath,"",0);
							} catch (Exception e) {
								LogUtil.debugLog("-=====pdf加水印异常=======>"+e);
								e.printStackTrace();
							}
						} catch (FileNotFoundException e) {
							LogUtil.doWriteLog("=流程附件源文件未找到==>" + e);
						}
						result = true;
						fileName = pdfName+".pdf";
						fileRealPath = newFilePath;
						LogUtil.debugLog("====上传前fileRealPath======="+fileRealPath);
					// pdf加水印
					} else if (fileRealPath.contains("pdf")) {
						// 输出新的加水印的pdf文件路径
						String pdfName = fileRealPath.substring(
								fileRealPath.lastIndexOf("/") + 1,
								fileRealPath.lastIndexOf("."));
						// 加水印后的pdf文件保存的位置
						String newFilePath = new BaseBean().getPropValue(
								"contactpdf", "pdfpath") + imagefileid+pdfName + ".pdf";
						try {
							// 加水印后的pdf的生成路径
							BufferedOutputStream bos = new BufferedOutputStream(
									new FileOutputStream(new File(newFilePath)));
							// 调用
							try {
								WaterPDF.addPdfMark(fileRealPath,newFilePath,"",0);
							} catch (Exception e) {
								LogUtil.debugLog("-=====pdf加水印异常=======>"+e);
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
						LogUtil.debugLog("====进入生成系统文档===>");
						DocCreateService service = new DocCreateService(
								creater, "1");
						// 新生成的文档id
						// 考虑合同附件是多个的情况
						String strDocId = service.init(fileName, fileRealPath,
								directoryid, "");
						String updateSql0 = "";
						String updateSql1 = "";
						/*if (fieldName.equals("htfj")) {
							index1++;
						}
						if (fieldName.equals("htzwfj")) {
							index2++;
						}
						if (fieldName.equals("htzwwd")) {
							index3++;
						}*/
						/*LogUtil.debugLog("==index1===>" + index1);
						LogUtil.debugLog("==index2===>" + index2);
						LogUtil.debugLog("==index3===>" + index3);*/
						//已经更新过该字段
						if (hasChange) {
							String bakDocid = "";
							String selSql = "select " + fieldName + " from "
									+ tableName + " where requestid="
									+ requestId;
							RecordSet rssql = new RecordSet();
							rssql.executeSql(selSql);
							if (rssql.next()) {
								bakDocid = Util.null2String(rssql
										.getString(fieldName));
							}
							updateSql0 = "update " + tableName + " set "
									+ fieldName + "='" + bakDocid + ","
									+ strDocId + "' where requestid="
									+ requestId;
							LogUtil.debugLog("==更新表单字段sql===>" + updateSql0);
						}  
						else{
							/*updateSql1 = "update " + tableName + " set "
									+ fieldName + "='' where requestid=" + requestId;*/
							updateSql0 = "update " + tableName + " set "
									+ fieldName + "='"+strDocId+"' where requestid=" + requestId;
							fieldContent += fieldName+",";
						}
						rsUp.executeSql(updateSql0); 
						LogUtil.debugLog("==更新表单字段sql===>" + updateSql0);
					}
				}
			}
		}

		return SUCCESS;
	}
}
