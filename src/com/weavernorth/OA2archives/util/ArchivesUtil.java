package com.weavernorth.OA2archives.util;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.*;
import com.weavernorth.util.LogUtil;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.general.Util;
import weaver.hrm.company.DepartmentComInfo;
import weaver.hrm.resource.ResourceComInfo;
import weaver.soa.workflow.request.RequestInfo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 类名:WorkflowInfo
 * 类描述: 获取流程相关信息工具类
 * Company:weavernorth
 *
 * @author:刘立华
 * @date: 2017-11-1下午1:43:27
 */
public class ArchivesUtil extends BaseBean {
    private static BaseBean base = new BaseBean();

    /**
     * 获取pdf文件的页数
     *
     * @param filename 文件的路径
     * @return pdf文件的页数
     */
    public static Integer getPageYs(String filename) {
        PdfReader reader = null;
        int pagecount = 0;
        try {
            reader = new PdfReader(filename);
            pagecount = reader.getNumberOfPages();
        } catch (IOException e) {
            //LogUtil.releaseLog("获取pdf页数异常"+e);
            //e.printStackTrace();
        }

        return pagecount;
    }

    /**
     * @param files   需要合并的pdf路径数组
     * @param newfile 合并后的pdf的文件数据路径
     * @return
     */
    public static Integer mergePdfFiles(String[] files, String newfile) {
        LogUtil.debugLog("===合并pdf开始=========================");
        boolean retValue = false;
        Document document = null;
        try {

            document = new Document(new PdfReader(files[0]).getPageSize(1));
            LogUtil.doWriteLog("222222222" + files[0]);
//            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
//            TableHeader includeHeader = new TableHeader();
//            writer.setPageEvent(includeHeader);
            PdfCopy copy = new PdfCopy(document, new FileOutputStream(newfile));
            PdfPageEvent pdfPageEvent = new PdfPageEventHelper();
            copy.setPageEvent(pdfPageEvent);
            LogUtil.doWriteLog("3333333" + files[0]);
            document.open();
            for (int i = 0; i < files.length; i++) {
                LogUtil.doWriteLog("要处理的PDF文件的名：" + files[i]);
                PdfReader reader = new PdfReader(files[i]);
                int n = reader.getNumberOfPages();
                for (int j = 1; j <= n; j++) {
                    document.newPage();
                    PdfImportedPage page = copy.getImportedPage(reader, j);
                    copy.addPage(page);
                }
            }
            retValue = true;
        } catch (Exception e) {
            LogUtil.doWriteLog("===合并pdf文档时异常==>" + e);
            retValue = false;
        } finally {
            document.close();
            LogUtil.debugLog("===合并pdf结束=====================是否成功" + retValue);
            Integer pageNum = 0;
            if (retValue) {
                pageNum = getPageYs(newfile);
            }
            return pageNum;
        }

    }

    /**
     * @param files   需要合并的pdf路径数组
     * @param newfile 合并后的pdf的文件数据路径
     * @return
     */
    public static Integer mergePdfFiles(RequestInfo requestInfo, String[] files, String newfile) {
        LogUtil.debugLog("===合并pdf开始=========================");
        boolean retValue = false;
        Document document = null;
        try {

            document = new Document(new PdfReader(files[0]).getPageSize(1));
            LogUtil.doWriteLog("222222222" + files[0]);
//            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
//            TableHeader includeHeader = new TableHeader();
//            writer.setPageEvent(includeHeader);
            PdfCopy copy = new PdfCopy(document, new FileOutputStream(newfile));
            PdfPageEvent pdfPageEvent = new PdfPageEventHelper();
            copy.setPageEvent(pdfPageEvent);
            LogUtil.doWriteLog("3333333" + files[0]);
            document.open();
            for (int i = 0; i < files.length; i++) {
                LogUtil.doWriteLog("要处理的PDF文件的名：" + files[i]);
                PdfReader reader = new PdfReader(files[i]);
                int n = reader.getNumberOfPages();
                for (int j = 1; j <= n; j++) {
                    document.newPage();
                    PdfImportedPage page = copy.getImportedPage(reader, j);
                    copy.addPage(page);
                }
            }
            retValue = true;
        } catch (Exception e) {
            LogUtil.doWriteLog("===合并pdf文档时异常==>" + e);
            insertPdfLog(requestInfo.getRequestid(), requestInfo.getWorkflowid(), e.getMessage());
            retValue = false;
        } finally {
            document.close();
            LogUtil.debugLog("===合并pdf结束=====================是否成功" + retValue);
            Integer pageNum = 0;
            if (retValue) {
                pageNum = getPageYs(newfile);
            }
            return pageNum;
        }

    }

    /**
     * 插入归档异常的数据到日志表中
     *
     * @param requestid
     * @param workflowid
     * @param exception  uf_gdcwrz
     *                   lcid   流程id
     *                   wfid  流程类别
     *                   bcrq  报错日期
     *                   bcsj  报错时间
     *                   bcnr  报错内容
     */
    public static void insertPdfLog(String requestid, String workflowid, String exception) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(date);
        SimpleDateFormat sdftime = new SimpleDateFormat("HH:mm:ss");
        String dateNowStrtime = sdftime.format(date);

        RecordSet rs = new RecordSet();
        String sql = "insert into uf_gdcwrz (lcid,wfid,bcrq,bcsj,bcnr) values('" + requestid + "','" + workflowid + "','" + dateNowStr + "','" + dateNowStrtime + "','" + exception + "')";
        rs.execute(sql);


    }

    /**
     * 从异常对象中获取信息，转为String输出
     *
     * @param e
     * @return
     */
    public static String getExceptionMsg(Exception e) {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String strMsg = sw.toString();
            sw.close();
            pw.close();
            return "\r\n" + strMsg + "\r\n";
        } catch (Exception e2) {
            return "ErrorInfoFromException";
        }
    }

    /**
     * TODO 根据流程id生成不同的ftp上传路径
     *
     * @param workflowid
     * @return String ftp上传路径
     */
    public static String getftppath(String workflowid, String requestid, String creatertid) {
        //ftp上传路径
        String ftppath = "";
        String[] strs = TimeUtil.getCurrentDateString().split("-");
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
        String year = strs[0];
        String month = strs[1];
        if (!"".equals(workflowid)) {
            String type = getExcelFileType(workflowid);
            //合同类  1 合同创建  2 合同变更
            if ("1".equals(type) || "2".equals(type)) {
                ftppath = "/" + year + "/" + month + "/" + "合同管理/";
            } else if ("3".equals(type)) {
                ftppath = "/" + year + "/" + month + "/" + "公文管理类/总裁信息/";
            } else if ("4".equals(type)) {
                ftppath = "/" + year + "/" + month + "/" + "公文管理类/用印申请/";
            } else if ("32".equals(type)) {
                ftppath = "/" + year + "/" + month + "/" + "PC/公文管理类/用印申请/";
            } else if ("5".equals(type)) {
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
                String fwlx_ZH = getTypeNameByRequest(requestid, fwlxColumName);
                if ("部门发文".equals(fwlx_ZH) || "部门发函".equals(fwlx_ZH) || "部门会议纪要".equals(fwlx_ZH)) {
                    ftppath = "/" + year + "/" + month + "/" + "公文管理类/" + fwlx_ZH + "/" + deptname + "/";
                } else {
                    ftppath = "/" + year + "/" + month + "/" + "公文管理类/" + fwlx_ZH + "/";
                }
            } else if ("9".equals(type)) {
                ftppath = "/" + year + "/" + month + "/" + "公文管理类/收文/";
            } else if ("40".equals(type)) {
                ftppath = "/" + year + "/" + month + "/" + "PC/公文管理类/收文/";
            } else if ("10".equals(type)) {
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
            } else if ("31".equals(type)) {
                ftppath = "/" + year + "/" + month + "/" + "PC/授权管理/";
            } else if ("20".equals(type)) {
                ftppath = "/" + year + "/" + month + "/" + "付款/";
            } else if ("21".equals(type)) {
                ftppath = "/" + year + "/" + month + "/" + "退款/";
            } else if ("22".equals(type) || "23".equals(type) || "24".equals(type)
                    || "25".equals(type) || "26".equals(type) || "27".equals(type)) {
                //只有公司发文流程和部门发文两个流程有该字段
                String fwlxColumName = getSettingColName(type, "gw_fwlx");
                //base.getPropValue("archives","gw_fwlx");
                String fwlx_ZH = getTypeNameByRequest(requestid, fwlxColumName);
                if ("部门发文".equals(fwlx_ZH) || "部门发函".equals(fwlx_ZH) || "部门会议纪要".equals(fwlx_ZH)) {
                    ftppath = "/" + year + "/" + month + "/" + "公文管理类/" + fwlx_ZH + "/" + deptname + "/";
                } else {
                    ftppath = "/" + year + "/" + month + "/" + "公文管理类/" + fwlx_ZH + "/";
                }

            } else if ("34".equals(type) || "35".equals(type) || "36".equals(type)
                    || "37".equals(type) || "38".equals(type) || "39".equals(type)) {
                //只有公司发文流程和部门发文两个流程有该字段
                String fwlxColumName = getSettingColName(type, "gw_fwlx");
                //base.getPropValue("archives","gw_fwlx");
                String fwlx_ZH = getTypeNameByRequest(requestid, fwlxColumName);
                if ("部门发文".equals(fwlx_ZH) || "部门发函".equals(fwlx_ZH) || "部门会议纪要".equals(fwlx_ZH)) {
                    ftppath = "/" + year + "/" + month + "/" + "PC/公文管理类/" + fwlx_ZH + "/" + deptname + "/";
                } else {
                    ftppath = "/" + year + "/" + month + "/" + "PC/公文管理类/" + fwlx_ZH + "/";
                }

            }
            //pc合同签订/变更流程
            else if ("29".equals(type) || "30".equals(type)) {
                ftppath = "/" + year + "/" + month + "/PC/合同管理/";
            }

        }
        return ftppath;
    }


    /**
     * 获取公文流程发文类型字段的值对应的中文名称
     *
     * @param requestid
     * @return
     */
    public static String getTypeNameByRequest(String requestid, String strFWTypeColumName) {
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
    public static String getExcelFileType(String workflowId) {
        RecordSet rs = new RecordSet();
        String type = "";
        rs.execute("select type from wn_workflowexceltype where workflowid='" + workflowId + "'");
        if (rs.next()) {
            type = Util.null2String(rs.getString("type"));
        }
        return type;
    }

    /**
     * 获取合并后pdf需要的名称
     *
     * @param requestid
     * @return
     */
    //TODO 对应取得pdf文档名称
    public static String getFileNameByWorkflow(String requestid) {
        String colValue = "";
        String workflowId = WorkflowUtil.getWorkflowId(requestid);
        //流程表单名称
        String tableName = WorkflowUtil.getMainTableNameByReqId(requestid);
        String typeid = getExcelFileType(workflowId);
        //合同流程中的生成pdf的文件的名称为【合同编号.pdf】
        String strGetString = "";
        RecordSet rsCol = new RecordSet();
        String selSql = "";
        String ColumName = "";

        if ("1".equals(typeid)) {
            ColumName = getSettingColName(typeid, "ht_hth");
        } else if ("2".equals(typeid)) {
            ColumName = getSettingColName(typeid, "ht_hth");
        } else if ("3".equals(typeid) || "4".equals(typeid) || "5".equals(typeid) || "6".equals(typeid)
                || "7".equals(typeid) || "8".equals(typeid) || "9".equals(typeid) || "10".equals(typeid)) {
            ColumName = getSettingColName(typeid, "gw_wh");

        } else if ("11".equals(typeid)) {
            ColumName = getSettingColName(typeid, "gw_wh");
        } else if ("12".equals(typeid)) {
            ColumName = getSettingColName(typeid, "gw_wh");
        } else if ("13".equals(typeid)) {
            ColumName = getSettingColName(typeid, "gw_wh");
        } else if ("14".equals(typeid)) {
            ColumName = getSettingColName(typeid, "gw_wh");
        } else if ("15".equals(typeid)) {
            ColumName = getSettingColName(typeid, "gw_wh");
        } else if ("16".equals(typeid)) {
            ColumName = getSettingColName(typeid, "gw_wh");
        } else if ("17".equals(typeid)) {
            ColumName = getSettingColName(typeid, "gw_wh");
        } else if ("18".equals(typeid)) {
            ColumName = getSettingColName(typeid, "gw_wh");
        } else if ("19".equals(typeid)) {
            ColumName = getSettingColName(typeid, "gw_wh");
        } else if ("20".equals(typeid)) {
            ColumName = getSettingColName(typeid, "gw_wh");
        } else if ("21".equals(typeid)) {
            ColumName = getSettingColName(typeid, "gw_wh");
        } else if ("22".equals(typeid)) {
            ColumName = getSettingColName(typeid, "gw_wh");
        } else if ("23".equals(typeid)) {
            ColumName = getSettingColName(typeid, "gw_wh");
        } else if ("24".equals(typeid)) {
            ColumName = getSettingColName(typeid, "gw_wh");
        } else if ("25".equals(typeid)) {
            ColumName = getSettingColName(typeid, "gw_wh");
        } else if ("26".equals(typeid)) {
            ColumName = getSettingColName(typeid, "gw_wh");
        } else if ("27".equals(typeid)) {
            ColumName = getSettingColName(typeid, "gw_wh");
        } else if ("28".equals(typeid)) {
            ColumName = getSettingColName(typeid, "gw_wh");
        } else if ("29".equals(typeid)) {
            ColumName = getSettingColName(typeid, "ht_hth");
        } else if ("30".equals(typeid)) {
            ColumName = getSettingColName(typeid, "ht_hth");
        } else if ("31".equals(typeid)) {
            ColumName = getSettingColName(typeid, "gw_wh");
        } else if ("32".equals(typeid)) {
            ColumName = getSettingColName(typeid, "gw_wh");
        } else if ("33".equals(typeid)) {
            ColumName = getSettingColName(typeid, "gw_wh");
        } else if ("34".equals(typeid)) {
            ColumName = getSettingColName(typeid, "gw_wh");
        } else if ("35".equals(typeid)) {
            ColumName = getSettingColName(typeid, "gw_wh");
        } else if ("36".equals(typeid)) {
            ColumName = getSettingColName(typeid, "gw_wh");
        } else if ("37".equals(typeid)) {
            ColumName = getSettingColName(typeid, "gw_wh");
        } else if ("38".equals(typeid)) {
            ColumName = getSettingColName(typeid, "gw_wh");
        } else if ("39".equals(typeid)) {
            ColumName = getSettingColName(typeid, "gw_wh");
        } else if ("40".equals(typeid)) {
            ColumName = getSettingColName(typeid, "gw_wh");
        } else if ("41".equals(typeid)) {
            ColumName = getSettingColName(typeid, "gw_wh");
        } else if ("42".equals(typeid)) {
            ColumName = getSettingColName(typeid, "ht_hth");
        } else if ("43".equals(typeid)) {
            ColumName = getSettingColName(typeid, "gw_wh");
        } else if ("44".equals(typeid)) {
            ColumName = getSettingColName(typeid, "gw_wh");
        } else if ("45".equals(typeid)) {
            ColumName = getSettingColName(typeid, "gw_wh");
        } else if ("46".equals(typeid)) {
            ColumName = getSettingColName(typeid, "gw_wh");
        } else if ("47".equals(typeid)) {
            ColumName = getSettingColName(typeid, "gw_wh");
        } else if ("48".equals(typeid)) {
            ColumName = getSettingColName(typeid, "gw_wh");
        } else if ("49".equals(typeid)) {
            ColumName = getSettingColName(typeid, "ht_hth");
        } else if ("50".equals(typeid)) {
            ColumName = getSettingColName(typeid, "gw_wh");
        }


        if (!"".equals(ColumName)) {
            String selFileName = "select " + ColumName + " from " + tableName + " where requestid=" + requestid;
            rsCol.executeSql(selFileName);
            if (rsCol.next()) {
                colValue = Util.null2String(rsCol.getString(ColumName));
            }
        }
        return colValue;
    }

    /**
     *获取归档的流程中需要用到的配置的字段名称
     public static  String  getFWLXColName(String typeid){
     String colName= "";
     RecordSet rs = new RecordSet();
     rs.executeSql("select gw_fwlx from wn_workflowexceltype where type="+typeid);
     if(rs.next()){
     colName = Util.null2String(rs.getString("gw_fwlx"));
     }
     return colName;
     }*/
    /**
     * 获取自定义表中的字段名称
     *
     * @param typeid    类型id
     * @param columName 列名
     * @return 设置的列名
     */
    public static String getSettingColName(String typeid, String columName) {
        String colName = "";
        RecordSet rs = new RecordSet();
        rs.executeSql("select " + columName + " from wn_workflowexceltype where type=" + typeid);
        if (rs.next()) {
            colName = Util.null2String(rs.getString(columName));
        }
        return colName;

    }

    /**
     * 获取人员中文名称
     *
     * @param hrmid
     * @return 人员中文名称
     */
    public static String getChineseHrmName(String hrmid) {
        String name = "";
        if (!"".equals(hrmid)) {
            RecordSet rs = new RecordSet();
            String sql = "select dbo.convToCN(lastname) lastname from hrmresource where id=" + hrmid;
            rs.executeSql(sql);
            if (rs.next()) {
                name = Util.null2String(rs.getString("lastname"));
            }
        }
        return name;
    }

    /**
     * 获取人员中文名称
     *
     * @param loginid
     * @return 人员中文名称
     */
    public static String getChineseHrmNameByLoginId(String loginid) {
        String name = "";
        if (!"".equals(loginid)) {
            RecordSet rs = new RecordSet();
            String sql = "select dbo.convToCN(lastname) lastname from hrmresource where loginid='" + loginid + "'";
            rs.executeSql(sql);
            if (rs.next()) {
                name = Util.null2String(rs.getString("lastname"));
            }
        }
        return name;
    }

    /**
     * 获取人员中文名称
     *
     * @param loginid
     * @return 人员中文名称
     */
    public static String getIdByLoginId(String loginid) {
        String id = "";
        if (!"".equals(loginid)) {
            RecordSet rs = new RecordSet();
            String sql = "select id from hrmresource where loginid='" + loginid + "'";
            rs.executeSql(sql);
            if (rs.next()) {
                id = Util.null2String(rs.getString("id"));
            }
        }
        return id;
    }


    public static String getLinkByGensuiteId(String gensuiteId) {
        String content = "";
        String URL = "";
        RecordSet rs = new RecordSet();
        String selSql = "select content,pcurl from  wn_gensuiteInfo where id ='" + gensuiteId + "'";
        rs.executeSql(selSql);
        if (rs.next()) {
            URL = Util.null2String(rs.getString("pcurl"));
            content = Util.null2String(rs.getString("content"));
        }
        String returnString = "<a href='" + URL + "' target='_blank'>" + content + "</a>";
        return returnString;
    }

    public static void main(String[] args) {

    }
}
