package com.weavernorth.OA2archives;


import com.weaver.general.Util;
import com.weavernorth.OA2archives.util.*;
import com.weavernorth.ftp.FtpObject;
import com.weavernorth.ftp.FtpUtil_zz;
import com.weavernorth.util.LogUtil;
import org.apache.commons.lang.StringUtils;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.hrm.company.DepartmentComInfo;
import weaver.hrm.resource.ResourceComInfo;
import weaver.soa.workflow.request.RequestInfo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类名:OAPDF2archivesActionbak
 * 类描述:流程归档后将表单和附件正文合并为一个pdf文件推送至ftp
 * Company:weavernorth
 *
 * @author:刘立华
 * @date: 2017-11-1下午1:36:42
 */
public class OAPDF2archivesAction extends BaseBean {
    private BaseBean base = new BaseBean();


    public String execute(RequestInfo request) {
        LogUtil.doWriteLog("===============文档归档接口Action开始===" + TimeUtil.getCurrentTimeString() + "=========================");
        int sta = start(request);
        //拿到相关附件的docid
        if (sta == 1) {
            LogUtil.doWriteLog("===============文档归档接口Action结束return0===" + TimeUtil.getCurrentTimeString() + "==========================");
            return "0";
        }
        LogUtil.doWriteLog("===============文档归档接口Action结束===" + TimeUtil.getCurrentTimeString() + "==========================");
        return "1";
    }


    /**
     *TODO 根据流程id生成不同的ftp上传路径
     *
     * @param workflowid
     * @return String ftp上传路径
     */
    private String getftppath(String workflowid, String requestid, String creatertid) {
        //ftp上传路径
        String ftppath = "";
        String[] strs = getWorkflowEndDate(requestid).split("-");
        ResourceComInfo re = null;
        try {
            re = new ResourceComInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DepartmentComInfo de = new DepartmentComInfo();
        String deptid = re.getDepartmentID(creatertid);
        String deptname = de.getDepartmentname(deptid);
        deptname = WorkflowUtil.getChineseName("departmentname", "HrmDepartment", deptname);
        LogUtil.doWriteLog("---getftppath中获取发文类型部门---id="+creatertid+"----deptname="+deptname);
        String year = strs[0];
        String month = strs[1];
        if (!"".equals(workflowid)) {
            String type = getExcelFileType(workflowid);
            Map<String,String> fwlx = getFwlxWithTypeid(type);
            String fwlx_ZH = fwlx.containsKey("fwlx")?fwlx.get("fwlx"):"";
            String isAddDept = fwlx.containsKey("bm")?fwlx.get("bm"):"";
            LogUtil.doWriteLog("============getftppath查看是否有=fwlx_ZH="+fwlx_ZH+"----是否添加部门isAddDept:"+isAddDept);
            //合同类  1 合同创建  2 合同变更
            if ("1".equals(type) || "2".equals(type)) {
                ftppath = "/" + year + "/" + month + "/" + "合同管理/";
            } else if ("3".equals(type)) {
                ftppath = "/" + year + "/" + month + "/" + "公文管理类/总裁信息/";
            } else if ("4".equals(type)) {
                ftppath = "/" + year + "/" + month + "/" + "公文管理类/用印申请/";
            } else if ("32".equals(type)) {
                ftppath = "/" + year + "/" + month + "/" + "PC/公文管理类/用印申请/";
            }else if ("5".equals(type)) {
                //String fwlxColumName =getSettingColName(type,"gw_fwlx");
                //String fwlx_ZH = getTypeNameByRequest(requestid,fwlxColumName);
                //if("刻制".equals(fwlx_ZH)){
                //ftppath = "/"+year+"/"+month+"/"+"公文管理类/印章/";
                //}else if("作废".equals(fwlx_ZH)){
                ftppath = "/" + year + "/" + month + "/" + "公文管理类/印章/";
                //}

            } else if ("33".equals(type)) {
                ftppath = "/" + year + "/" + month + "/" + "PC/公文管理类/印章/";
            } else if ("6".equals(type)) {
                ftppath = "/" + year + "/" + month + "/" + "公文管理类/议题提报/";
            } else if ("7".equals(type) || "8".equals(type)) {
                //只有公司发文流程和部门发文两个流程有该字段
                String fwlxColumName = getSettingColName(type, "gw_fwlx");
                //base.getPropValue("archives","gw_fwlx");
                if ("0".equals(isAddDept)) {
                    ftppath = "/" + year + "/" + month + "/" + "公文管理类/" + fwlx_ZH + "/" + deptname + "/";
                } else {
                    ftppath = "/" + year + "/" + month + "/" + "公文管理类/" + fwlx_ZH + "/";
                }
            } else if ("9".equals(type)) {
                ftppath = "/" + year + "/" + month + "/" + "公文管理类/收文/";
            }else if ("40".equals(type)) {
                ftppath = "/" + year + "/" + month + "/" + "PC/公文管理类/收文/";
            }else if ("10".equals(type)) {
                ftppath = "/" + year + "/" + month + "/" + "公文管理类/签报单/";
            } else if ("41".equals(type)) {
                ftppath = "/" + year + "/" + month + "/" + "PC/公文管理类/签报单/";
            } else if ("11".equals(type)) {
                ftppath = "/" + year + "/" + month + "/" + "预结算/";
            } else if ("12".equals(type)) {
                ftppath = "/" + year + "/" + month + "/" + "预结算/";
            } else if ("13".equals(type)) {
                ftppath = "/" + year + "/" + month + "/" + "预结算/";
            } else if ("14".equals(type)) {
                ftppath = "/" + year + "/" + month + "/" + "预结算/";
            } else if ("15".equals(type)) {
                ftppath = "/" + year + "/" + month + "/" + "服务采购/";
            } else if ("16".equals(type)) {
                ftppath = "/" + year + "/" + month + "/" + "服务采购/";
            } else if ("17".equals(type)) {
                ftppath = "/" + year + "/" + month + "/" + "服务采购/";
            } else if ("28".equals(type)) {
                ftppath = "/" + year + "/" + month + "/物资采购/";
            } else if ("18".equals(type)) {
                ftppath = "/" + year + "/" + month + "/" + "MOC/";
            } else if ("19".equals(type)) {
                ftppath = "/" + year + "/" + month + "/" + "授权管理/";
            }else if ("31".equals(type)) {
                ftppath = "/" + year + "/" + month + "/" + "PC/授权管理/";
            }else if ("20".equals(type)) {
                ftppath = "/" + year + "/" + month + "/" + "付款/";
            } else if ("21".equals(type)) {
                ftppath = "/" + year + "/" + month + "/" + "退款/";
            } else if ("22".equals(type) || "23".equals(type) || "24".equals(type)
                    || "25".equals(type) || "26".equals(type) || "27".equals(type)) {
                //只有公司发文流程和部门发文两个流程有该字段
                //base.getPropValue("archives","gw_fwlx");
                if ("0".equals(isAddDept)) {
                    ftppath = "/" + year + "/" + month + "/" + "公文管理类/" + fwlx_ZH + "/" + deptname + "/";
                } else {
                    ftppath = "/" + year + "/" + month + "/" + "公文管理类/" + fwlx_ZH + "/";
                }

            }else if ("34".equals(type) || "35".equals(type) || "36".equals(type)
                    || "37".equals(type) || "38".equals(type) || "39".equals(type)) {
                //只有公司发文流程和部门发文两个流程有该字段
                //base.getPropValue("archives","gw_fwlx");
                if ("0".equals(isAddDept)) {
                    ftppath = "/" + year + "/" + month + "/" + "PC/公文管理类/" + fwlx_ZH + "/" + deptname + "/";
                } else {
                    ftppath = "/" + year + "/" + month + "/" + "PC/公文管理类/" + fwlx_ZH + "/";
                }

            }
            //pc合同签订/变更流程
            else if ("29".equals(type) || "30".equals(type)) {
                ftppath = "/" + year + "/" + month  + "/PC/合同管理/";
            }

        }
        return ftppath;
    }


    /**
     * 通过typeid获取发文类型
     * @param typeid
     * @return
     */
    private Map<String,String> getFwlxWithTypeid(String typeid){
        Map<String,String>  fwlx = new HashMap<String,String>();
        RecordSet recordSet = new RecordSet();
        recordSet.execute("select fwlx,bm from uf_fwlx where typeid = "+typeid);
        while (recordSet.next()){
            fwlx.put("fwlx",recordSet.getString("fwlx"));
            fwlx.put("bm",recordSet.getString("bm"));
        }
        return fwlx;
    }


    /**
     * 获取自定义表中的字段名称
     *workflow_currentoperator
     * @param typeid    类型id
     * @param columName 列名
     * @return 设置的列名
     */
    private static String getSettingColName(String typeid, String columName) {
        String colName = "";
        RecordSet rs = new RecordSet();
        rs.executeSql("select " + columName + " from wn_workflowexceltype where type=" + typeid);
        if (rs.next()) {
            colName = Util.null2String(rs.getString(columName));
        }
        return colName;

    }

    /**
     * 获取公文流程发文类型字段的值对应的中文名称
     *
     * @param requestid
     * @return
     */
    private static String getTypeNameByRequest(String requestid, String strFWTypeColumName) {
        String fwlxValue = "";
        RecordSet rs = new RecordSet();
        //公文表单名称
        String tableName = WorkflowUtil.getMainTableNameByReqId(requestid);
        rs.execute("select " + strFWTypeColumName + " from " + tableName + " where requestid='" + requestid + "'");
        if (rs.next()) {
            fwlxValue = Util.null2String(rs.getString(strFWTypeColumName));
        }
        String fieldId = WorkflowUtil.getFieldId(requestid, strFWTypeColumName);
        String fwlxValueName = WorkflowUtil.getSelectChinesType(fwlxValue, fieldId);
        LogUtil.debugLog("==requestid为[" + requestid + "]的字段名为[" + strFWTypeColumName + "]下拉框数值[" + fwlxValue + "]中文值==>[" + fwlxValueName + "]");
        return fwlxValueName;
    }


    /**
     * 获取流程的模板类型
     *
     * @param workflowId
     * @return String typeid
     */
    private static String getExcelFileType(String workflowId) {
        RecordSet rs = new RecordSet();
        String type = "";
        rs.execute("select type from wn_workflowexceltype where workflowid='" + workflowId + "'");
        if (rs.next()) {
            type = Util.null2String(rs.getString("type"));
        }
        return type;
    }

    /**
     * 获取归档时间
     *
     * @param requestid
     * @return
     */
    private String getWorkflowEndDate(String requestid) {

        String receivedate = "";
        RecordSet rs = new RecordSet();
        rs.executeQuery("select receivedate from workflow_currentoperator where isremark = '4' and requestid = " + requestid);
        if (rs.next()) {
            receivedate = rs.getString("receivedate");
        }
        LogUtil.debugLog("=======OAPDF2archivesAction=====>receivedate:" + receivedate + "---requestid--:" + requestid);
        return receivedate;
    }

    /**
     * 归档整体逻辑的处理
     *
     * @param request
     * @return
     */
    private Integer start(RequestInfo request) {
        int result = 0;
        List list = DocUtil.getDocFieldIds(request);
        //1.解压文档到压缩的目录下
        //2.将解压后的文件转换为pdf文件
        List<Map> dataList = getDoc(request, list);
        this.writeLog("需要合并的list长度:" + dataList.size());
        //如果没有附件就报错
        if (dataList.size() == 0) {
            result = 1;
        } else {

            try {
                // 服务器上的原始路径
                String strAllPdfPath = base.getPropValue("archives", "pdfpath");
                //服务器上的备份路径
                String strAllPdfPathBak = base.getPropValue("archives", "pdfpathbak");
                //本地文件上传ftp的路径
                String uploadpath = strAllPdfPath + "/" + TimeUtil.getCurrentDateString().substring(0, 4);
                LogUtil.debugLog("==上传ftp的路径==>" + uploadpath);
                //生成的需要上传ftp文件的相对路径，仅仅用于数据库的记录
                //TODO 文件路径需要修改
                String createPath = getftppath(request.getWorkflowid(), request.getRequestid(), WorkflowUtil.getCreaterId(request.getRequestid()));
                //服务器上保存合并后文件的绝对路径
                String savePath = strAllPdfPath + createPath + ArchivesUtil.getFileNameByWorkflow(request.getRequestid()) + ".pdf";
                //服务器上保存合并后文件的绝对路径备份
                String savePathBak = strAllPdfPathBak + createPath + ArchivesUtil.getFileNameByWorkflow(request.getRequestid()) + ".pdf";
                LogUtil.debugLog("==OA服务器上保存文件的绝对路径==>" + savePath);
                LogUtil.debugLog("==OA服务器上保存文件的绝对路径备份路径==>" + savePathBak);
                File file = new File(strAllPdfPath + createPath);
                File fileBak = new File(strAllPdfPathBak + createPath);
                //创建路径
                DocUtil.judeDirExistsAll(file);
                DocUtil.judeDirExistsAll(fileBak);

                //合并并输出文件到上一步创建的文件路劲下是否成功
                Integer pagenum = 0;
                //合并pdf到指定目录
                //每个pdf文件的路径
                String inputDir = "";
                List<String> list1 = new ArrayList<>();
                for (int i = 0; i < dataList.size(); i++) {
                    Map dataMap = dataList.get(i);
                    this.writeLog("dataMap:" + dataMap.toString());
                    //得到pdf文件的路径
                    String newpath = (String) dataMap.get("newpath");
                    if (i == 0) {
                        inputDir = newpath.substring("//10.102.180.254/filesystem/".length(), newpath.length());
                    } else {
                        list1.add(newpath.substring("//10.102.180.254/filesystem/".length(), newpath.length()));
                    }
                    LogUtil.debugLog("===需要合并的pdf文件路径==>" + inputDir + list1);
                }
                Map<String, String> map = new HashMap<String, String>();
                map.put("inputDir", inputDir);
                map.put("mergeInput", StringUtils.join(list1, ","));
                map.put("convertType", "31");

                //使用永中进行转pdf
                String docdcsconverturl = weaver.general.Util.null2String(new BaseBean().getPropValue("docpreview", "docdcsconverturl"));
                pagenum = ConvertPDFTools.sendPost(docdcsconverturl, map, savePath, savePathBak);
                //保存到备份目录下
                LogUtil.debugLog("======合并后的pdf文件共【" + pagenum + "】页");
                WorkflowInfoSave save = new WorkflowInfoSave();
                //如果合并pdf成功
                if (pagenum > 0) {
                    LogUtil.doWriteLog("==上传ftp保存excel信息开始" + TimeUtil.getCurrentTimeString() + "====");
                    //此处做bean数据的插入或更新，注意此处的是保存状态是成功   uploadStatus 0 成功  1失败 2未上传
                    //TODO 文件信息的保存需要修改
                    save.SaveWorkflowInfo(request, createPath, "0", "2", pagenum);
                    //上传ftp
                    String addr = base.getPropValue("archivesftp", "addr");
                    int port = Util.getIntValue(base.getPropValue("archivesftp", "port"));
                    String username = base.getPropValue("archivesftp", "username");
                    String password = base.getPropValue("archivesftp", "password");
                    FtpUtil_zz f = new FtpUtil_zz(true);
                    //上传文件到ftp
                    try {
                        FtpObject fo = new FtpObject();
                        fo.setHostName(addr);
                        fo.setUserName(username);
                        fo.setPassWord(password);
                        fo.setPort(port);
                        LogUtil.doWriteLog("==FTP==addr：" + addr+"--username:"+username+"--password:"+password+"==port:"+port);
//                        File file = new File("E:\\imageTest\\unzipFile\\2019-03-14\\54986\\test.doc");

                        String fptstatus = f.uploadOneFile(fo, createPath, new File(savePath), ArchivesUtil.getFileNameByWorkflow(request.getRequestid()) + ".pdf");
                        LogUtil.doWriteLog("==FTP==" + fptstatus);
                        //更新上传成功状态
                        save.SaveWorkflowInfo(request, createPath, "0", "0", pagenum);
                    } catch (Exception e) {
                        LogUtil.doWriteLog("==上传ftp异常==" + e);
                        //保存成功  上传失败状态
                        save.SaveWorkflowInfo(request, createPath, "0", "1", pagenum);
                    }finally {
                        try {
                            f.disConnection();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    LogUtil.doWriteLog("==上传ftp保存excel信息结束" + TimeUtil.getCurrentTimeString() + "====");
                } else {
                    LogUtil.debugLog("===合并pdf失败==>");
                    //此处做bean数据的插入或更新，注意此处的是保存状态是失败
                    //保存失败 ，上传失败
                    save.SaveWorkflowInfo(request, createPath, "1", "1", pagenum);
                }
            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.doWriteLog("生成异常" + e);
            }
        }
        return result;
    }

    /**
     * 将zip文档解压出来，并将其转pdf存在原压缩目录下
     *
     * @return pdf文件的路径
     */
    private static List<Map> getDoc(RequestInfo request, List listDocids) {
        int docid = 0;
        int result = -1;
        String strPath = "";
        List<Map> listpath = new ArrayList<Map>();
        try {
            //1.需要在此添加表单pdf的路径信息，
            for (Object listDocid : listDocids) {
                Map mapoldAndNew = new HashMap();
                docid = weaver.general.Util.getIntValue((String) listDocid, 0);
                //将文档解压到原始目录下,得到解压的真实路径
                strPath = DocUtil.getRealDoc(DocUtil.getImageFileId(docid));
                if (!"".equals(strPath)) {
                    //过滤到文件名中的..
                    LogUtil.debugLog("===文件保存的原始目录==" + strPath);
                    strPath = strPath.replace("._.", ".");
                    strPath = strPath.replace("....", ".");
                    strPath = strPath.replace("..", ".");
                    strPath = strPath.replace(",.", ".");
                    strPath = strPath.replace("_", "");
                    strPath = strPath.replace("-", "");
                    strPath = strPath.replace("._.", "");
                    LogUtil.debugLog("===文件保存的过滤后的原始目录==" + strPath);
                    //将文档转为pdf，依然存在该目录下
                    ConvertPDFTools conpdf = new ConvertPDFTools();
                    result = conpdf.conertToPdf(DocUtil.getImageFileId(docid) + "", strPath.substring(0, strPath.lastIndexOf(".")) + ".pdf");
                    mapoldAndNew.put("oldpath", strPath);
                    LogUtil.debugLog("===转为pdf的文件保存目录==" + strPath.substring(0, strPath.lastIndexOf(".")) + ".pdf");
                    mapoldAndNew.put("newpath", strPath.substring(0, strPath.lastIndexOf(".")) + ".pdf");
                    mapoldAndNew.put("result", result);
                    listpath.add(mapoldAndNew);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.debugLog("OAPDF2archivesAction.java转异常:" + e);
            ArchivesUtil.insertPdfLog(request.getRequestid(), request.getWorkflowid(), ArchivesUtil.getExceptionMsg(e));
        }


        return listpath;
    }


}
