package com.weavernorth.OA2archives.util;

import com.weaver.general.Util;
import com.weavernorth.OA2archives.bean.ArchivesBean;
import com.weavernorth.util.LogUtil;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.hrm.company.DepartmentComInfo;
import weaver.hrm.resource.ResourceComInfo;
import weaver.soa.workflow.request.RequestInfo;

/**
 * 类名:WorkflowInfoSave
 * 类描述:根据不同流程保存流程数据 Company:weavernorth
 *
 * @author:刘立华
 * @date: 2017-11-8上午9:55:23
 */
public class TestWorkflowInfoSave {

    public boolean SaveWorkflowInfo(RequestInfo request, String strftppath, String saveStatus, String uploadStatus, Integer pageNum, boolean isUpdateNmu) {
        BaseBean base = new BaseBean();

        String createridReal = WorkflowUtil.getCreaterId(request.getRequestid());
        LogUtil.debugLog("===createridReal===" + createridReal);
        // 请求id
        String requestid = request.getRequestid();
        // 流程id
        String workflowid = request.getWorkflowid();
        // 文件名（不带后缀）
        String object_name = "";
        // ftp路径
        String file_path = strftppath;
        // 文件类型
        String file_format = "pdf";
        // 标题
        String c_chinese_title = changChinese(request.getRequestManager().getRequestname());
        // 流程--合同号
        String c_doc_code = "";
        // 流程--合同类别
        String c_contract_type = "";
        // 合同年度
        String c_year = "";
        // SSTPC
        String c_party_a = "sstpc";
        // 流程--合同乙方
        String c_party_b = "";
        ResourceComInfo re = null;
        try {
            re = new ResourceComInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DepartmentComInfo de = new DepartmentComInfo();
        String deptid = re.getDepartmentID(createridReal);
        // 发起人部门
        // 页数
        String c_page_counts = pageNum + "";
        // 流程--归档日期
        String c_archive_date = getWorkflowEndDate(requestid);

        //typeid
        String typeid = ArchivesUtil.getExcelFileType(workflowid);
        //当前操作人
        String Lastoperator = "";

        if(typeid.equals("9")||typeid.equals("18")||typeid.equals("22")||typeid.equals("23")||typeid.equals("24")){
            Lastoperator = getTypeOperator("type_"+typeid);
        }else {
            Lastoperator = getCurrentOperator(request);
        }
        LogUtil.debugLog("===Lastoperator===" + Lastoperator);
        //创建人
        // 归档人
        String c_archive_owne = Lastoperator;
        //当前操作人的部门
        String c_archive_org = re.getDepartmentID(Lastoperator);
        // 流水号
        String c_inner_sequence = getMaxc_inner_sequence(typeid) + "";
        //模板路径
        String excelmode_path = base.getPropValue("archives", "excelmodepath");
        //excel保存路径
        String newexcel_path = base.getPropValue("archives", "pdfpathbak") + file_path;
        // 流程表名
        String tableName = request.getRequestManager().getBillTableName();
        //对应的exceltype
        String strSql = "select * from " + tableName + " where requestid='" + requestid + "'";
        LogUtil.debugLog("=========流程内信息查询===>" + strSql);
        ArchivesBean bean = new ArchivesBean();
        RecordSet rs = new RecordSet();
        rs.execute(strSql);
        if (rs.next()) {
            // 合同创建流程
            if (typeid.equals("1")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("htbh"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title);
                bean.setC_doc_code(rs.getString("htbh"));
                bean.setC_contract_type(rs.getString("htlb"));
                if (rs.getString("rq") != null && !"".equals(rs.getString("rq"))) {
                    bean.setC_year(rs.getString("rq").substring(0, 4));
                } else {
                    bean.setC_year("");
                }
                bean.setC_party_a(c_party_a);
                bean.setC_party_b(rs.getString("httf"));
                bean.setC_resp_dept(deptid);
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_owne(createridReal);
                bean.setC_inner_sequence(c_inner_sequence);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);
            } else if (typeid.equals("2")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("xhtbh"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title);
                bean.setC_doc_code(rs.getString("xhtbh"));
                bean.setC_contract_type(rs.getString("htlb"));
                if (rs.getString("rq") != null && !"".equals(rs.getString("rq"))) {
                    bean.setC_year(rs.getString("rq").substring(0, 4));
                } else {
                    bean.setC_year("");
                }
                bean.setC_party_a(c_party_a);
                bean.setC_party_b(rs.getString("httf"));
                bean.setC_resp_dept(deptid);
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_owne(createridReal);
                bean.setC_inner_sequence(c_inner_sequence);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);
            }
            //PC合同签订流程
            else if (typeid.equals("29")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("htbh"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title);
                bean.setC_doc_code(rs.getString("htbh"));
                bean.setC_contract_type("");
                bean.setC_year("");
                bean.setC_party_a(c_party_a);
                bean.setC_party_b(rs.getString("httf"));
                bean.setC_resp_dept(deptid);
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_owne(createridReal);
                bean.setC_inner_sequence(c_inner_sequence);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);
            }
            //PC合同变更流程
            else if (typeid.equals("30")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("xhtbh"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title);
                bean.setC_doc_code(rs.getString("xhtbh"));
                bean.setC_contract_type("");
                bean.setC_year("");
                bean.setC_party_a(c_party_a);
                bean.setC_party_b(rs.getString("bgh9"));
                bean.setC_resp_dept(deptid);
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_owne(createridReal);
                bean.setC_inner_sequence(c_inner_sequence);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);
            }

            //1.1总裁身份证/人名使用章申请流程
            else if (typeid.equals("3")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("bh"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title);
                bean.setC_doc_type("");
                bean.setC_compile_dept(deptid);
                bean.setC_compile_date(WorkflowUtil.getCreateDate(request.getRequestid()));
                bean.setC_compiler(createridReal);
                bean.setC_issue_date(c_archive_date);
                bean.setC_issue_num(rs.getString("bh"));
                bean.setC_issue_dept(deptid);
                String hqbm = Util.null2String(rs.getString("sqbm"));
                bean.setC_target_dept(rs.getString("sqbm"));
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_org(c_archive_org);

                bean.setC_inner_sequence(c_inner_sequence);
                bean.setC_archive_owne(Lastoperator);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);

                // 1.2用印审批单申请流程
            } else if (typeid.equals("4")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("sqdbh"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title);
                bean.setC_doc_type("");
                bean.setC_compile_dept(deptid);
                bean.setC_compile_date(WorkflowUtil.getCreateDate(request.getRequestid()));

                bean.setC_compiler(createridReal);
                bean.setC_issue_date(c_archive_date);
                bean.setC_issue_num(rs.getString("sqdbh"));
                bean.setC_issue_dept(deptid);
                String hqbm = Util.null2String(rs.getString("sqbm"));
                bean.setC_target_dept(rs.getString("sqbm"));
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_org(c_archive_org);
                bean.setC_inner_sequence(c_inner_sequence);
                bean.setC_archive_owne(Lastoperator);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);

               
            } 
           //PC用印审批单申请流程
            else if (typeid.equals("32")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("sqdbh"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title);
                bean.setC_doc_type("");
                bean.setC_compile_dept(deptid);
                bean.setC_compile_date(WorkflowUtil.getCreateDate(request.getRequestid()));

                bean.setC_compiler(createridReal);
                bean.setC_issue_date(c_archive_date);
                bean.setC_issue_num(rs.getString("sqdbh"));
                bean.setC_issue_dept(deptid);
                String hqbm = Util.null2String(rs.getString("sqbm"));
                bean.setC_target_dept(rs.getString("sqbm"));
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_org(c_archive_org);
                bean.setC_inner_sequence(c_inner_sequence);
                bean.setC_archive_owne(Lastoperator);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);
               
            } 
            // 1.3印章刻制（作废）申请流程
            else if (typeid.equals("5")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("sqdbh"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title);
                bean.setC_doc_type(rs.getString("yzzf"));
                bean.setC_compile_dept(deptid);
                bean.setC_compile_date(WorkflowUtil.getCreateDate(request.getRequestid()));
                bean.setC_compiler(createridReal);
                bean.setC_issue_date(c_archive_date);
                bean.setC_issue_num(rs.getString("sqdbh"));
                bean.setC_issue_dept(deptid);
                String hqbm = Util.null2String(rs.getString("sqbm"));
                bean.setC_target_dept(rs.getString("sqbm"));
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_org(c_archive_org);
                bean.setC_inner_sequence(c_inner_sequence);
                bean.setC_archive_owne(Lastoperator);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);
                
            }
            // PC印章刻制（作废）申请流程
            else if (typeid.equals("33")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("sqdbh"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title);
                bean.setC_doc_type(rs.getString("yzzf"));
                bean.setC_compile_dept(deptid);
                bean.setC_compile_date(WorkflowUtil.getCreateDate(request.getRequestid()));
                bean.setC_compiler(createridReal);
                bean.setC_issue_date(c_archive_date);
                bean.setC_issue_num(rs.getString("sqdbh"));
                bean.setC_issue_dept(deptid);
                String hqbm = Util.null2String(rs.getString("sqbm"));
                bean.setC_target_dept(rs.getString("sqbm"));
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_org(c_archive_org);
                bean.setC_inner_sequence(c_inner_sequence);
                bean.setC_archive_owne(Lastoperator);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);
            }
            
          //1.4.总裁办公室议题审批申请流程
            else if (typeid.equals("6")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("bh"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title);
                bean.setC_doc_type(rs.getString("sfnk"));
                bean.setC_compile_dept(deptid);
                bean.setC_compile_date(WorkflowUtil.getCreateDate(request.getRequestid()));
                bean.setC_compiler(createridReal);
                bean.setC_issue_date(c_archive_date);
                bean.setC_issue_num(rs.getString("bh"));
                bean.setC_issue_dept(deptid);
                String hqbm = Util.null2String(deptid);
                bean.setC_target_dept(deptid);
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_org(c_archive_org);
                bean.setC_inner_sequence(c_inner_sequence);
                bean.setC_archive_owne(Lastoperator);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);
                // 1.5.公司发文、发函、会议纪要申请流程
            } else if (typeid.equals("7")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("wh"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title);
                bean.setC_doc_type(rs.getString("fwlx1"));
                bean.setC_compile_dept(deptid);
                bean.setC_compile_date(WorkflowUtil.getCreateDate(request.getRequestid()));
                bean.setC_compiler(createridReal);
                bean.setC_issue_date(c_archive_date);
                bean.setC_issue_num(rs.getString("wh"));
                bean.setC_issue_dept(deptid);
                String hqbm = Util.null2String(rs.getString("hqbm"));
                bean.setC_target_dept(deptid);
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_org(c_archive_org);
                bean.setC_inner_sequence(c_inner_sequence);
                bean.setC_archive_owne(Lastoperator);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);
                // 1.6.部门发文、发函、会议纪要申请流程
            } else if (typeid.equals("8")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("wh"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title);
                bean.setC_doc_type(rs.getString("fwlx1"));
                bean.setC_compile_dept(deptid);
                bean.setC_compile_date(WorkflowUtil.getCreateDate(request.getRequestid()));
                bean.setC_compiler(createridReal);
                bean.setC_issue_date(c_archive_date);
                bean.setC_issue_num(rs.getString("wh"));
                bean.setC_issue_dept(deptid);
                String hqbm = Util.null2String(rs.getString("hqbm"));
                bean.setC_target_dept(deptid);
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_org(c_archive_org);
                bean.setC_inner_sequence(c_inner_sequence);
                bean.setC_archive_owne(Lastoperator);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);

                // 公司收文承办申请
            } else if (typeid.equals("9")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("zsshswh"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title);
                bean.setC_doc_type("");
                bean.setC_compile_dept(deptid);
                bean.setC_compile_date(WorkflowUtil.getCreateDate(request.getRequestid()));
                bean.setC_compiler(createridReal);
                bean.setC_issue_date(c_archive_date);
                bean.setC_issue_num(rs.getString("zsshswh"));
                bean.setC_issue_dept(deptid);
                String hqbm = Util.null2String(deptid);
                bean.setC_target_dept(deptid);
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_org(c_archive_org);
                bean.setC_inner_sequence(c_inner_sequence);
                String typeOperator = getTypeOperator("type_9");
                bean.setC_archive_owne(Lastoperator);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);
            }
            // PC公司收文承办申请
            else if (typeid.equals("40")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("zsshswh"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title);
                bean.setC_doc_type("");
                bean.setC_compile_dept(deptid);
                bean.setC_compile_date(WorkflowUtil.getCreateDate(request.getRequestid()));
                bean.setC_compiler(createridReal);
                bean.setC_issue_date(c_archive_date);
                bean.setC_issue_num(rs.getString("zsshswh"));
                bean.setC_issue_dept(deptid);
                String hqbm = Util.null2String(deptid);
                bean.setC_target_dept(deptid);
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_org(c_archive_org);
                bean.setC_inner_sequence(c_inner_sequence);
                bean.setC_archive_owne(Lastoperator);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);
            }
            // 1.8.签报单申请流程
            else if (typeid.equals("10")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("wh"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title);
                bean.setC_doc_type("");
                bean.setC_compile_dept(deptid);
                bean.setC_compile_date(WorkflowUtil.getCreateDate(request.getRequestid()));
                bean.setC_compiler(createridReal);
                bean.setC_issue_date(c_archive_date);
                bean.setC_issue_num(rs.getString("wh"));
                bean.setC_issue_dept(deptid);
                String hqbm = Util.null2String(deptid);
                bean.setC_target_dept(deptid);
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_org(c_archive_org);
                bean.setC_inner_sequence(c_inner_sequence);
                bean.setC_archive_owne(Lastoperator);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);
            } 
            // PC签报单申请流程
            else if (typeid.equals("41")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("wh"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title);
                bean.setC_doc_type("");
                bean.setC_compile_dept(deptid);
                bean.setC_compile_date(WorkflowUtil.getCreateDate(request.getRequestid()));
                bean.setC_compiler(createridReal);
                bean.setC_issue_date(c_archive_date);
                bean.setC_issue_num(rs.getString("wh"));
                bean.setC_issue_dept(deptid);
                String hqbm = Util.null2String(deptid);
                bean.setC_target_dept(deptid);
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_org(c_archive_org);
                bean.setC_inner_sequence(c_inner_sequence);
                bean.setC_archive_owne(Lastoperator);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);
            }
            // 工程预算确认单
            else if (typeid.equals("11")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("DocumentNumber"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                String ProjectDescription = Util.null2String(rs.getString("ProjectDescription"));
                if ("".equals(ProjectDescription)) {
                    ProjectDescription = "无";
                }
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title + "-" + ProjectDescription);
                bean.setC_doc_type("");
                bean.setC_compile_dept(deptid);
                bean.setC_compile_date(WorkflowUtil.getCreateDate(request.getRequestid()));
                bean.setC_compiler(createridReal);
                bean.setC_issue_date(c_archive_date);
                bean.setC_issue_num(rs.getString("DocumentNumber"));
                bean.setC_issue_dept(deptid);
                String hqbm = Util.null2String(deptid);
                bean.setC_target_dept(deptid);
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_org(c_archive_org);
                bean.setC_inner_sequence(c_inner_sequence);
                bean.setC_archive_owne(Lastoperator);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);
            }
            //工程结算确认单
            else if (typeid.equals("12")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("Document_Code"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                String Project_Description = Util.null2String(rs.getString("Project_Description"));
                if ("".equals(Project_Description)) {
                    Project_Description = "无";
                }
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title + "-" + Project_Description);
                bean.setC_doc_type("");
                bean.setC_compile_dept(deptid);
                bean.setC_compile_date(WorkflowUtil.getCreateDate(request.getRequestid()));
                bean.setC_compiler(createridReal);
                bean.setC_issue_date(c_archive_date);
                bean.setC_issue_num(rs.getString("Document_Code"));
                bean.setC_issue_dept(deptid);
                String hqbm = Util.null2String(deptid);
                bean.setC_target_dept(deptid);
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_org(c_archive_org);
                bean.setC_inner_sequence(c_inner_sequence);
                bean.setC_archive_owne(Lastoperator);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);
            }
            // 检维修预算确认单
            else if (typeid.equals("13")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("Document_Number"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                String projectName = Util.null2String(rs.getString("Project_name"));
                if ("".equals(projectName)) {
                    projectName = "无";
                }
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title + "-" + projectName);
                bean.setC_doc_type("");
                bean.setC_compile_dept(deptid);
                bean.setC_compile_date(WorkflowUtil.getCreateDate(request.getRequestid()));
                bean.setC_compiler(createridReal);
                bean.setC_issue_date(c_archive_date);
                bean.setC_issue_num(rs.getString("Document_Number"));
                bean.setC_issue_dept(deptid);
                String hqbm = Util.null2String(deptid);
                bean.setC_target_dept(deptid);
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_org(c_archive_org);
                bean.setC_inner_sequence(c_inner_sequence);
                bean.setC_archive_owne(Lastoperator);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);
            }
            // 检维修结算确认单
            else if (typeid.equals("14")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("Document_Code"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                String projectName = Util.null2String(rs.getString("Project_name"));
                if ("".equals(projectName)) {
                    projectName = "无";
                }
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title + "-" + projectName);
                bean.setC_doc_type("");
                bean.setC_compile_dept(deptid);
                bean.setC_compile_date(WorkflowUtil.getCreateDate(request.getRequestid()));
                bean.setC_compiler(createridReal);
                bean.setC_issue_date(c_archive_date);
                bean.setC_issue_num(rs.getString("Document_Code"));
                bean.setC_issue_dept(deptid);
                String hqbm = Util.null2String(deptid);
                bean.setC_target_dept(deptid);
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_org(c_archive_org);
                bean.setC_inner_sequence(c_inner_sequence);
                bean.setC_archive_owne(Lastoperator);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);
            }
            //服务采购需求提报表
            else if (typeid.equals("15")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("Application_Number"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title);
                bean.setC_doc_type("");
                bean.setC_compile_dept(deptid);
                bean.setC_compile_date(WorkflowUtil.getCreateDate(request.getRequestid()));
                bean.setC_compiler(createridReal);
                bean.setC_issue_date(c_archive_date);
                bean.setC_issue_num(rs.getString("Application_Number"));
                bean.setC_issue_dept(deptid);
                String hqbm = Util.null2String(deptid);
                bean.setC_target_dept(deptid);
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_org(c_archive_org);
                bean.setC_inner_sequence(c_inner_sequence);
                bean.setC_archive_owne(Lastoperator);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);
            }
            // 服务采购预选供应商审批表
            else if (typeid.equals("16")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("Application_Number"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title);
                bean.setC_doc_type("");
                bean.setC_compile_dept(deptid);
                bean.setC_compile_date(WorkflowUtil.getCreateDate(request.getRequestid()));
                bean.setC_compiler(createridReal);
                bean.setC_issue_date(c_archive_date);
                bean.setC_issue_num(rs.getString("Application_Number"));
                bean.setC_issue_dept(deptid);
                String hqbm = Util.null2String(deptid);
                bean.setC_target_dept(deptid);
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_org(c_archive_org);
                bean.setC_inner_sequence(c_inner_sequence);
                bean.setC_archive_owne(Lastoperator);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);
            }
            // 服务采购择定供应商审批表
            else if (typeid.equals("17")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("Application_Number"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title);
                bean.setC_doc_type("");
                bean.setC_compile_dept(deptid);
                bean.setC_compile_date(WorkflowUtil.getCreateDate(request.getRequestid()));
                bean.setC_compiler(createridReal);
                bean.setC_issue_date(c_archive_date);
                bean.setC_issue_num(rs.getString("Application_Number"));
                bean.setC_issue_dept(deptid);
                String hqbm = Util.null2String(deptid);
                bean.setC_target_dept(deptid);
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_org(c_archive_org);
                bean.setC_inner_sequence(c_inner_sequence);
                bean.setC_archive_owne(Lastoperator);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);
            }
            //物资采购预算供应商
            else if (typeid.equals("28")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("sqbh"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title);
                bean.setC_doc_type("");
                bean.setC_compile_dept(deptid);
                bean.setC_compile_date(WorkflowUtil.getCreateDate(request.getRequestid()));
                bean.setC_compiler(createridReal);
                bean.setC_issue_date(c_archive_date);
                bean.setC_issue_num(rs.getString("sqbh"));
                bean.setC_issue_dept(deptid);
                String hqbm = Util.null2String(deptid);
                bean.setC_target_dept(deptid);
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_org(c_archive_org);
                bean.setC_inner_sequence(c_inner_sequence);
                bean.setC_archive_owne(Lastoperator);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);
            }
            // MOC变更申请流程
            else if (typeid.equals("18")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("bgsqbh"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title);
                bean.setC_doc_type("");
                bean.setC_compile_dept(deptid);
                bean.setC_compile_date(WorkflowUtil.getCreateDate(request.getRequestid()));
                bean.setC_compiler(createridReal);
                bean.setC_issue_date(c_archive_date);
                bean.setC_issue_num(rs.getString("bgsqbh"));
                bean.setC_issue_dept(deptid);
                String hqbm = Util.null2String(deptid);
                bean.setC_target_dept(deptid);
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_org(c_archive_org);
                bean.setC_inner_sequence(c_inner_sequence);
                String typeOperator = getTypeOperator("type_18");
                bean.setC_archive_owne(Lastoperator);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);
            }
            //授权委托申请流程
            else if (typeid.equals("19")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("bh"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                //授权人id
                String giveRightHrmId = Util.null2String(rs.getString("sqr"));
                String hrmName = "";
                if ("".equals(giveRightHrmId)) {
                    hrmName = "无";
                } else {
                    hrmName = ArchivesUtil.getChineseHrmName(giveRightHrmId);
                }
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title);
                bean.setC_doc_type("");
                bean.setC_compile_dept(deptid);
                bean.setC_compile_date(WorkflowUtil.getCreateDate(request.getRequestid()));
                bean.setC_compiler(createridReal);
                bean.setC_issue_date(c_archive_date);
                bean.setC_issue_num(rs.getString("bh"));
                bean.setC_issue_dept(deptid);
                String hqbm = Util.null2String(deptid);
                bean.setC_target_dept(deptid);
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_org(c_archive_org);
                bean.setC_inner_sequence(c_inner_sequence);
                bean.setC_archive_owne(Lastoperator);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);
            }
          //PC授权委托申请流程
            else if (typeid.equals("31")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("bh"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                //授权人id
                String giveRightHrmId = Util.null2String(rs.getString("sqr"));
                String hrmName = "";
                if ("".equals(giveRightHrmId)) {
                    hrmName = "无";
                } else {
                    hrmName = ArchivesUtil.getChineseHrmName(giveRightHrmId);
                }
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title);
                bean.setC_doc_type("");
                bean.setC_compile_dept(deptid);
                bean.setC_compile_date(WorkflowUtil.getCreateDate(request.getRequestid()));
                bean.setC_compiler(createridReal);
                bean.setC_issue_date(c_archive_date);
                bean.setC_issue_num(rs.getString("bh"));
                bean.setC_issue_dept(deptid);
                String hqbm = Util.null2String(deptid);
                bean.setC_target_dept(deptid);
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_org(c_archive_org);
                bean.setC_inner_sequence(c_inner_sequence);
                bean.setC_archive_owne(Lastoperator);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);
            }
            //付款流程保存信息的数据对应
            else if (typeid.equals("20")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("spdbh"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title);
                bean.setC_doc_type("");
                bean.setC_compile_dept(deptid);
                bean.setC_compile_date(WorkflowUtil.getCreateDate(request.getRequestid()));
                bean.setC_compiler(createridReal);
                bean.setC_issue_date(c_archive_date);
                bean.setC_issue_num(rs.getString("spdbh"));
                bean.setC_issue_dept(deptid);
                String hqbm = Util.null2String(deptid);
                bean.setC_target_dept(deptid);
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_org(c_archive_org);
                bean.setC_inner_sequence(c_inner_sequence);
                bean.setC_archive_owne(Lastoperator);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);
            }
            //退款流程
            else if (typeid.equals("21")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("spdbh"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title);
                bean.setC_doc_type("");
                bean.setC_compile_dept(deptid);
                bean.setC_compile_date(WorkflowUtil.getCreateDate(request.getRequestid()));
                bean.setC_compiler(createridReal);
                bean.setC_issue_date(c_archive_date);
                bean.setC_issue_num(rs.getString("spdbh"));
                bean.setC_issue_dept(deptid);
                String hqbm = Util.null2String(deptid);
                bean.setC_target_dept(deptid);
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_org(c_archive_org);
                bean.setC_inner_sequence(c_inner_sequence);
                bean.setC_archive_owne(Lastoperator);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);
                //公司发文申请
            } else if (typeid.equals("22")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("wh"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                //TODO 公司发文加英文标题
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title);
                bean.setC_doc_type(rs.getString("fwlx"));
                bean.setC_compile_dept(deptid);
                bean.setC_compile_date(WorkflowUtil.getCreateDate(request.getRequestid()));

                bean.setC_compiler(createridReal);
                bean.setC_issue_date(c_archive_date);
                bean.setC_issue_num(rs.getString("wh"));
                bean.setC_issue_dept(deptid);
                String hqbm = Util.null2String(rs.getString("hqbm"));
                bean.setC_target_dept(deptid);
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_org(c_archive_org);
                bean.setC_inner_sequence(c_inner_sequence);
                String typeOperator = getTypeOperator("type_22");
                bean.setC_archive_owne(Lastoperator);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);
            }
            //PC公司发文申请
            else if (typeid.equals("34")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("wh"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                //TODO 公司发文加英文标题
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title);
                bean.setC_doc_type(rs.getString("fwlx"));
                bean.setC_compile_dept(deptid);
                bean.setC_compile_date(WorkflowUtil.getCreateDate(request.getRequestid()));
                bean.setC_compiler(createridReal);
                bean.setC_issue_date(c_archive_date);
                bean.setC_issue_num(rs.getString("wh"));
                bean.setC_issue_dept(deptid);
                String hqbm = Util.null2String(rs.getString("hqbm"));
                bean.setC_target_dept(deptid);
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_org(c_archive_org);
                bean.setC_inner_sequence(c_inner_sequence);
                bean.setC_archive_owne(Lastoperator);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);
            }
            
            //公司发函
            else if (typeid.equals("23")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("wh"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title);
                bean.setC_doc_type(rs.getString("fwlx"));
                bean.setC_compile_dept(deptid);
                bean.setC_compile_date(WorkflowUtil.getCreateDate(request.getRequestid()));

                bean.setC_compiler(createridReal);
                bean.setC_issue_date(c_archive_date);
                bean.setC_issue_num(rs.getString("wh"));
                bean.setC_issue_dept(deptid);
                String hqbm = Util.null2String(rs.getString("hqbm"));
                bean.setC_target_dept(deptid);
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_org(c_archive_org);
                bean.setC_inner_sequence(c_inner_sequence);
                String typeOperator = getTypeOperator("type_23");
                bean.setC_archive_owne(Lastoperator);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);
                
            }
            //PC公司发函
            else if (typeid.equals("35")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("wh"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title);
                bean.setC_doc_type(rs.getString("fwlx"));
                bean.setC_compile_dept(deptid);
                bean.setC_compile_date(WorkflowUtil.getCreateDate(request.getRequestid()));

                bean.setC_compiler(createridReal);
                bean.setC_issue_date(c_archive_date);
                bean.setC_issue_num(rs.getString("wh"));
                bean.setC_issue_dept(deptid);
                String hqbm = Util.null2String(rs.getString("hqbm"));
                bean.setC_target_dept(deptid);
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_org(c_archive_org);
                bean.setC_inner_sequence(c_inner_sequence);
                bean.setC_archive_owne(Lastoperator);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);
            }
            
            // 公司会议纪要
            else if (typeid.equals("24")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("wh"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title);
                bean.setC_doc_type(rs.getString("fwlx"));
                bean.setC_compile_dept(deptid);
                bean.setC_compile_date(WorkflowUtil.getCreateDate(request.getRequestid()));

                bean.setC_compiler(createridReal);
                bean.setC_issue_date(c_archive_date);
                bean.setC_issue_num(rs.getString("wh"));
                bean.setC_issue_dept(deptid);
                String hqbm = Util.null2String(rs.getString("hqbm"));
                bean.setC_target_dept(deptid);
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_org(c_archive_org);
                bean.setC_inner_sequence(c_inner_sequence);
                String typeOperator = getTypeOperator("type_24");
                bean.setC_archive_owne(Lastoperator);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);
            }
            
            else if (typeid.equals("36")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("wh"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title);
                bean.setC_doc_type(rs.getString("fwlx"));
                bean.setC_compile_dept(deptid);
                bean.setC_compile_date(WorkflowUtil.getCreateDate(request.getRequestid()));

                bean.setC_compiler(createridReal);
                bean.setC_issue_date(c_archive_date);
                bean.setC_issue_num(rs.getString("wh"));
                bean.setC_issue_dept(deptid);
                String hqbm = Util.null2String(rs.getString("hqbm"));
                bean.setC_target_dept(deptid);
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_org(c_archive_org);
                bean.setC_inner_sequence(c_inner_sequence);
                bean.setC_archive_owne(Lastoperator);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);
            }
            // 部门发文
            else if (typeid.equals("25")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("wh"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                //TODO 部门发文加英文标题
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title);
                bean.setC_doc_type(rs.getString("fwlx1"));
                bean.setC_compile_dept(deptid);
                bean.setC_compile_date(WorkflowUtil.getCreateDate(request.getRequestid()));

                bean.setC_compiler(createridReal);
                bean.setC_issue_date(c_archive_date);
                bean.setC_issue_num(rs.getString("wh"));
                bean.setC_issue_dept(deptid);
                String hqbm = Util.null2String(rs.getString("hqbm"));
                bean.setC_target_dept(deptid);
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_org(c_archive_org);
                bean.setC_inner_sequence(c_inner_sequence);
                bean.setC_archive_owne(Lastoperator);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);
                
            } 
            //PC部门发文
            else if (typeid.equals("37")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("wh"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                //TODO 部门发文加英文标题
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title);
                bean.setC_doc_type(rs.getString("fwlx1"));
                bean.setC_compile_dept(deptid);
                bean.setC_compile_date(WorkflowUtil.getCreateDate(request.getRequestid()));

                bean.setC_compiler(createridReal);
                bean.setC_issue_date(c_archive_date);
                bean.setC_issue_num(rs.getString("wh"));
                bean.setC_issue_dept(deptid);
                String hqbm = Util.null2String(rs.getString("hqbm"));
                bean.setC_target_dept(deptid);
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_org(c_archive_org);
                bean.setC_inner_sequence(c_inner_sequence);
                bean.setC_archive_owne(Lastoperator);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);
                
            } 
            // 部门发函
            else if (typeid.equals("26")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("wh"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title);
                bean.setC_doc_type(rs.getString("fwlx"));
                bean.setC_compile_dept(deptid);
                bean.setC_compile_date(WorkflowUtil.getCreateDate(request.getRequestid()));

                bean.setC_compiler(createridReal);
                bean.setC_issue_date(c_archive_date);
                bean.setC_issue_num(rs.getString("wh"));
                bean.setC_issue_dept(deptid);
                String hqbm = Util.null2String(rs.getString("hqbm"));
                bean.setC_target_dept(deptid);
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_org(c_archive_org);
                bean.setC_inner_sequence(c_inner_sequence);
                bean.setC_archive_owne(Lastoperator);
                bean.setTypeid(typeid);

                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);
                
            }
            //PC部门发函
            else if (typeid.equals("38")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("wh"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title);
                bean.setC_doc_type(rs.getString("fwlx"));
                bean.setC_compile_dept(deptid);
                bean.setC_compile_date(WorkflowUtil.getCreateDate(request.getRequestid()));
                bean.setC_compiler(createridReal);
                bean.setC_issue_date(c_archive_date);
                bean.setC_issue_num(rs.getString("wh"));
                bean.setC_issue_dept(deptid);
                String hqbm = Util.null2String(rs.getString("hqbm"));
                bean.setC_target_dept(deptid);
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_org(c_archive_org);
                bean.setC_inner_sequence(c_inner_sequence);
                bean.setC_archive_owne(Lastoperator);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);
            }
            
            //部门会议纪要申请流程
            else if (typeid.equals("27")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("wh"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title);
                bean.setC_doc_type(rs.getString("fwlx"));
                bean.setC_compile_dept(deptid);
                bean.setC_compile_date(WorkflowUtil.getCreateDate(request.getRequestid()));

                bean.setC_compiler(createridReal);
                bean.setC_issue_date(c_archive_date);
                bean.setC_issue_num(rs.getString("wh"));
                bean.setC_issue_dept(deptid);
                String hqbm = Util.null2String(rs.getString("hqbm"));
                bean.setC_target_dept(deptid);
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_org(c_archive_org);
                bean.setC_inner_sequence(c_inner_sequence);
                bean.setC_archive_owne(Lastoperator);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);
            }
            
          //PC部门会议纪要申请流程
            else if (typeid.equals("39")) {
                bean.setRequestid(requestid);
                //合并后pdf的名称
                bean.setObject_name(rs.getString("wh"));
                bean.setFile_path(file_path);
                bean.setFile_format(file_format);
                bean.setC_english_title("");
                bean.setC_chinese_title(c_chinese_title);
                bean.setC_doc_type(rs.getString("fwlx"));
                bean.setC_compile_dept(deptid);
                bean.setC_compile_date(WorkflowUtil.getCreateDate(request.getRequestid()));

                bean.setC_compiler(createridReal);
                bean.setC_issue_date(c_archive_date);
                bean.setC_issue_num(rs.getString("wh"));
                bean.setC_issue_dept(deptid);
                String hqbm = Util.null2String(rs.getString("hqbm"));
                bean.setC_target_dept(deptid);
                bean.setC_page_counts(c_page_counts);
                bean.setC_archive_date(c_archive_date);
                bean.setC_archive_org(c_archive_org);
                bean.setC_inner_sequence(c_inner_sequence);
                bean.setC_archive_owne(Lastoperator);
                bean.setTypeid(typeid);
                bean.setSaveStatus(saveStatus);
                bean.setUploadStatus(uploadStatus);
                bean.setExcelmode_path(excelmode_path);
                bean.setNewexcel_path(newexcel_path);
            }
            //TODO 增加获取流程的相关信息


        }
        if (checkRecord(requestid, typeid)) {
            UpdateContractInfo(bean,isUpdateNmu);
        } else {
            InsertContractInfo(bean);
        }
        return true;
    }

    /**
     * 查询excel模板数据信息是否存在
     *
     * @param requestid
     * @param exceltype
     * @return
     */
    public boolean checkRecord(String requestid, String exceltype) {
        RecordSet rs = new RecordSet();
        boolean result = false;
        if ("1".equals(exceltype) || "2".equals(exceltype) || "29".equals(exceltype) || "30".equals(exceltype)) {
            String sql = "select id from wn_oa2emc_contract where requestid=" + requestid + "";
            rs.execute(sql);
            if (rs.next()) {
                result = true;
            }
        } else if (
                "3".equals(exceltype) || "4".equals(exceltype) || "5".equals(exceltype) || "6".equals(exceltype)
                        || "7".equals(exceltype) || "8".equals(exceltype) || "9".equals(exceltype) || "10".equals(exceltype)
                        || "11".equals(exceltype) || "12".equals(exceltype) || "13".equals(exceltype) || "14".equals(exceltype)
                        || "15".equals(exceltype) || "16".equals(exceltype) || "17".equals(exceltype) || "18".equals(exceltype)
                        || "19".equals(exceltype) || "22".equals(exceltype) || "23".equals(exceltype) || "24".equals(exceltype)
                        || "25".equals(exceltype) || "26".equals(exceltype) || "27".equals(exceltype) || "28".equals(exceltype)
                        || "31".equals(exceltype) || "32".equals(exceltype) || "33".equals(exceltype) || "34".equals(exceltype)
                        || "35".equals(exceltype) || "36".equals(exceltype) || "37".equals(exceltype) || "38".equals(exceltype)
                        || "39".equals(exceltype) || "40".equals(exceltype) || "41".equals(exceltype) 
                ) {
            String sql = "select id from wn_oa2emc_document where requestid=" + requestid + "";
            rs.execute(sql);
            if (rs.next()) {
                result = true;
            }

        } else if ("20".equals(exceltype) || "21".equals(exceltype)) {
            String sql = "select id from wn_oa2emc_payment where requestid=" + requestid + "";
            rs.execute(sql);
            if (rs.next()) {
                result = true;
            }
        }


        return result;
    }

    /**
     * 插入合同信息到数据库表中
     *
     * @param bean
     * @return
     */
    //TODO 增加获取对应的流程typeid的逻辑
    private boolean InsertContractInfo(ArchivesBean bean) {
        LogUtil.debugLog("===插入excel导出信息==>");

        StringBuffer subinsert = new StringBuffer();
        String typeid = bean.getTypeid();
        if ("1".equals(typeid) || "2".equals(typeid) || "29".equals(typeid) || "30".equals(typeid)) {
            subinsert.append("insert into wn_oa2emc_contract ");
            subinsert.append("(requestid,");
            subinsert.append("object_name,");
            subinsert.append("file_path,");
            subinsert.append("file_format,");
            subinsert.append("c_chinese_title,");
            subinsert.append("c_doc_code,");
            subinsert.append("c_contract_type,");
            subinsert.append("c_year,");
            subinsert.append("c_party_a,");
            subinsert.append("c_party_b,");
            subinsert.append("c_resp_dept,");
            subinsert.append("c_page_counts,");
            subinsert.append("c_archive_date,");
            subinsert.append("c_archive_owne,");
            subinsert.append("c_inner_sequence,");
            subinsert.append("typeid,");
            subinsert.append("save_status,");
            subinsert.append("upload_status,");
            subinsert.append("excelmode_path,");
            subinsert.append("newexcel_path)");
            subinsert.append(" values(");
            subinsert.append("'" + bean.getRequestid() + "',");
            subinsert.append("'" + bean.getObject_name() + "',");
            subinsert.append("'" + bean.getFile_path() + "',");
            subinsert.append("'" + bean.getFile_format() + "',");
            subinsert.append("'" + bean.getC_chinese_title() + "',");
            subinsert.append("'" + bean.getC_doc_code() + "',");
            subinsert.append("'" + bean.getC_contract_type() + "',");
            subinsert.append("'" + bean.getC_year() + "',");
            subinsert.append("'" + bean.getC_party_a() + "',");
            subinsert.append("'" + bean.getC_party_b() + "',");
            subinsert.append("'" + bean.getC_resp_dept() + "',");
            subinsert.append("'" + bean.getC_page_counts() + "',");
            subinsert.append("'" + bean.getC_archive_date() + "',");
            subinsert.append("'" + bean.getC_archive_owne() + "',");
            subinsert.append("'" + bean.getC_inner_sequence() + "',");
            subinsert.append("'" + bean.getTypeid() + "',");
            subinsert.append("'" + bean.getSaveStatus() + "',");
            subinsert.append("'" + bean.getUploadStatus() + "',");
            subinsert.append("'" + bean.getExcelmode_path() + "',");
            subinsert.append("'" + bean.getNewexcel_path() + "'");
            subinsert.append(")");
            //公文类、预结算类流程的excel信息插入
        } else if ("3".equals(typeid) || "4".equals(typeid) || "5".equals(typeid) || "6".equals(typeid)
                || "7".equals(typeid) || "8".equals(typeid) || "9".equals(typeid) || "10".equals(typeid)
                || "11".equals(typeid) || "12".equals(typeid) || "13".equals(typeid) || "14".equals(typeid)
                || "15".equals(typeid) || "16".equals(typeid) || "17".equals(typeid) || "18".equals(typeid)
                || "19".equals(typeid) || "22".equals(typeid) || "23".equals(typeid) || "24".equals(typeid)
                || "25".equals(typeid) || "26".equals(typeid) || "27".equals(typeid) || "28".equals(typeid)
                || "31".equals(typeid) || "32".equals(typeid) || "33".equals(typeid) || "34".equals(typeid)
                || "35".equals(typeid) || "36".equals(typeid) || "37".equals(typeid) || "38".equals(typeid)
                || "39".equals(typeid) || "40".equals(typeid) || "41".equals(typeid) 
                ) {
            subinsert.append("insert into wn_oa2emc_document ");
            subinsert.append("(requestid,");
            subinsert.append("object_name,");
            subinsert.append("file_path,");
            subinsert.append("file_format,");
            subinsert.append("c_english_title,");
            subinsert.append("c_chinese_title,");
            subinsert.append("c_doc_type,");
            subinsert.append("c_compile_dept,");
            subinsert.append("c_compile_date,");
            subinsert.append("c_compiler,");
            subinsert.append("c_issue_date,");
            subinsert.append("c_issue_num,");
            subinsert.append("c_issue_dept,");
            subinsert.append("c_target_dept,");
            subinsert.append("c_page_counts,");
            subinsert.append("c_archive_date,");
            subinsert.append("c_archive_org,");
            subinsert.append("c_archive_owner,");
            subinsert.append("c_inner_sequence,");
            subinsert.append("typeid,");
            subinsert.append("save_status,");
            subinsert.append("upload_status,");
            subinsert.append("excelmode_path,");
            subinsert.append("newexcel_path)");
            subinsert.append(" values(");
            subinsert.append("'" + bean.getRequestid() + "',");
            subinsert.append("'" + bean.getObject_name() + "',");
            subinsert.append("'" + bean.getFile_path() + "',");
            subinsert.append("'" + bean.getFile_format() + "',");
            subinsert.append("'" + bean.getC_english_title() + "',");
            subinsert.append("'" + bean.getC_chinese_title() + "',");
            subinsert.append("'" + bean.getC_doc_type() + "',");
            subinsert.append("'" + bean.getC_compile_dept() + "',");
            subinsert.append("'" + bean.getC_compile_date() + "',");
            subinsert.append("'" + bean.getC_compiler() + "',");
            subinsert.append("'" + bean.getC_issue_date() + "',");
            subinsert.append("'" + bean.getC_issue_num() + "',");
            subinsert.append("'" + bean.getC_issue_dept() + "',");
            subinsert.append("'" + bean.getC_target_dept() + "',");
            subinsert.append("'" + bean.getC_page_counts() + "',");
            subinsert.append("'" + bean.getC_archive_date() + "',");
            subinsert.append("'" + bean.getC_archive_org() + "',");
            subinsert.append("'" + bean.getC_archive_owne() + "',");
            subinsert.append("'" + bean.getC_inner_sequence() + "',");
            subinsert.append("'" + bean.getTypeid() + "',");
            subinsert.append("'" + bean.getSaveStatus() + "',");
            subinsert.append("'" + bean.getUploadStatus() + "',");
            subinsert.append("'" + bean.getExcelmode_path() + "',");
            subinsert.append("'" + bean.getNewexcel_path() + "'");
            subinsert.append(")");
        }
        //付款流程信息
        else if ("20".equals(typeid) || "21".equals(typeid)) {
            subinsert.append("insert into wn_oa2emc_payment ");
            subinsert.append("(requestid,");
            subinsert.append("object_name,");
            subinsert.append("file_path,");
            subinsert.append("file_format,");
            subinsert.append("c_chinese_title,");
            subinsert.append("c_archive_no,");
            subinsert.append("c_owner_dept,");
            subinsert.append("c_receive_date,");
            subinsert.append("c_org_certify_num,");
            subinsert.append("c_page_counts,");
            subinsert.append("c_archive_date,");
            subinsert.append("c_archive_owner,");
            subinsert.append("c_revision,");
            subinsert.append("c_inner_sequence,");
            subinsert.append("typeid,");
            subinsert.append("save_status,");
            subinsert.append("upload_status,");
            subinsert.append("excelmode_path,");
            subinsert.append("newexcel_path)");
            subinsert.append(" values(");
            subinsert.append("'" + bean.getRequestid() + "',");
            subinsert.append("'" + bean.getObject_name() + "',");
            subinsert.append("'" + bean.getFile_path() + "',");
            subinsert.append("'" + bean.getFile_format() + "',");
            subinsert.append("'" + bean.getC_chinese_title() + "',");
            subinsert.append("'" + bean.getC_issue_num() + "',");
            subinsert.append("'" + bean.getC_compile_dept() + "',");
            subinsert.append("'" + bean.getC_compile_date() + "',");
            subinsert.append("'" + bean.getC_issue_num() + "',");
            subinsert.append("'" + bean.getC_page_counts() + "',");
            subinsert.append("'" + bean.getC_archive_date() + "',");
            subinsert.append("'" + bean.getC_archive_owne() + "',");
            subinsert.append("'',");
            subinsert.append("'" + bean.getC_inner_sequence() + "',");
            subinsert.append("'" + bean.getTypeid() + "',");
            subinsert.append("'" + bean.getSaveStatus() + "',");
            subinsert.append("'" + bean.getUploadStatus() + "',");
            subinsert.append("'" + bean.getExcelmode_path() + "',");
            subinsert.append("'" + bean.getNewexcel_path() + "'");
            subinsert.append(")");
        }
        RecordSet rs = new RecordSet();

        LogUtil.debugLog("==信息插入sql===>" + subinsert.toString());
        boolean result = rs.execute(subinsert.toString());
        return result;
    }

    /**
     * 更新合同信息
     *
     * @param bean
     * @return
     */
    //TODO 增加获取对应的流程typeid的逻辑
    private boolean UpdateContractInfo(ArchivesBean bean, boolean isUpdateNmu) {
        LogUtil.debugLog("===更新excel导出信息==>");
        StringBuffer subUpdate = new StringBuffer();
        RecordSet rs = new RecordSet();
        String typeid = bean.getTypeid();
        if ("1".equals(typeid) || "2".equals(typeid) || "29".equals(typeid) || "30".equals(typeid)) {
            subUpdate.append("update wn_oa2emc_contract set ");
            subUpdate.append(" c_archive_owne='" + bean.getC_archive_owne() + "',");
            subUpdate.append(" c_resp_dept='" + bean.getC_resp_dept() + "',");
        } else if ("3".equals(typeid) || "4".equals(typeid) || "5".equals(typeid) || "6".equals(typeid)
                || "7".equals(typeid) || "8".equals(typeid) || "9".equals(typeid) || "10".equals(typeid)
                || "11".equals(typeid) || "12".equals(typeid) || "13".equals(typeid) || "14".equals(typeid)
                || "15".equals(typeid) || "16".equals(typeid) || "17".equals(typeid) || "18".equals(typeid)
                || "19".equals(typeid) || "22".equals(typeid) || "23".equals(typeid) || "24".equals(typeid)
                || "25".equals(typeid) || "26".equals(typeid) || "27".equals(typeid) || "28".equals(typeid)
                || "31".equals(typeid) || "32".equals(typeid) || "33".equals(typeid) || "34".equals(typeid)
                || "35".equals(typeid) || "36".equals(typeid) || "37".equals(typeid) || "38".equals(typeid)
                || "39".equals(typeid) || "40".equals(typeid) || "41".equals(typeid) 
                ) {
            subUpdate.append("update wn_oa2emc_document set ");
            subUpdate.append(" c_archive_owner='" + bean.getC_archive_owne() + "',");
            subUpdate.append(" c_archive_org='" + bean.getC_archive_org() + "',");
        }
        subUpdate.append(" object_name='" + bean.getObject_name() + "',");
        subUpdate.append(" save_status='" + bean.getSaveStatus() + "',");
        subUpdate.append(" c_archive_date='" + bean.getC_archive_date() + "',");
        subUpdate.append(" c_chinese_title='" + bean.getC_chinese_title() + "',");
        if(isUpdateNmu)
            subUpdate.append(" c_page_counts='" + bean.getC_page_counts() + "',");
        subUpdate.append(" file_path='" + bean.getFile_path() + "',");
        subUpdate.append(" excelmode_path='" + bean.getExcelmode_path() + "',");
        subUpdate.append(" newexcel_path='" + bean.getNewexcel_path() + "',");
        subUpdate.append(" upload_status='" + bean.getUploadStatus() + "'");
        subUpdate.append(" where requestid = '" + bean.getRequestid() + "'");
        LogUtil.debugLog("==更新sql===>" + subUpdate.toString());
        boolean result = rs.execute(subUpdate.toString());
        return result;
    }

    /**
     * 获取不同类型中的最大序列号
     *
     * @param type
     * @return合并后的pdf文件共
     */
    //TODO 增加获取对应的流程typeid的逻辑
    public Integer getMaxc_inner_sequence(String type) {
        int maxid = 0;
        RecordSet rs = new RecordSet();
        String sql = "";
        //合同序号获取
        if ("1".equals(type) || "2".equals(type)) {
            sql = "select max(c_inner_sequence) maxid from wn_oa2emc_contract where typeid in (1,2)";
        //公文序号获取
        } else if ("3".equals(type) || "4".equals(type) || "5".equals(type) || "6".equals(type)
                || "7".equals(type) || "8".equals(type) || "9".equals(type) || "10".equals(type)
                || "18".equals(type) || "19".equals(type) || "22".equals(type) || "23".equals(type)
                || "24".equals(type) || "25".equals(type) || "26".equals(type) || "27".equals(type)
                || "31".equals(type)|| "32".equals(type)|| "33".equals(type)|| "34".equals(type)
                || "35".equals(type)|| "36".equals(type)|| "37".equals(type)|| "38".equals(type)
                || "39".equals(type)|| "40".equals(type)|| "41".equals(type)) {
            sql = "select max(c_inner_sequence) maxid from wn_oa2emc_document where typeid ='" + type + "'";

        } else if ("11".equals(type) || "12".equals(type) || "13".equals(type) || "14".equals(type)) {
            sql = "select max(c_inner_sequence) maxid from wn_oa2emc_document where typeid in (11,12,13,14)";
        } else if ("15".equals(type) || "16".equals(type) || "17".equals(type) || "28".equals(type)) {
            sql = "select max(c_inner_sequence) maxid from wn_oa2emc_document where typeid in (15,16,17,28)";
        }
        //付款的序号获取
        else if ("20".equals(type) || "21".equals(type)) {
            sql = "select max(c_inner_sequence) maxid from wn_oa2emc_payment where typeid in (20,21)";
        //PC合同序号获取
        } else if ("29".equals(type) || "30".equals(type)) {
            sql = "select max(c_inner_sequence) maxid from wn_oa2emc_contract where typeid in (29,30)";

        }
        rs.execute(sql);
        if (rs.next()) {
            maxid = Util.getIntValue(rs.getString("maxid"), 0);
        }
        return maxid + 1;
    }

    private String getWorkflowEndDate(String requestid){

        String receivedate="";
        RecordSet rs = new RecordSet();
        rs.executeQuery("select receivedate from workflow_currentoperator where isremark = '4' and requestid = "+requestid);
        if(rs.next()){
            receivedate = rs.getString("receivedate");
        }
        LogUtil.debugLog("=======TestWorkflowInfoSave=====>receivedate:" + receivedate+"---requestid--:"+requestid);
        return receivedate;
    }

    private String changChinese(String title){
        String chineseTitle = "";
        String nextTitle = "";
        if(weaver.common.StringUtil.isNull(title))
            return title;
        if(title.indexOf("`~`~")!=-1 &&title.indexOf("`~`~")!=-1) {
            int twoIndex = title.indexOf("`~`~");
            nextTitle = title.substring(twoIndex + "`~`~".length(), title.length());
            RecordSet rs = new RecordSet();
            rs.executeQuery("select dbo.convToCN('~`~`7 服务采购择定供应商审批表单`~`8 Approval Form For Supplier Selection For Service Procurement`~`~') chineseTitle");
            rs.next();
            chineseTitle = rs.getString("chineseTitle");
            LogUtil.doWriteLog("chineseTitle" + chineseTitle + nextTitle);
            return chineseTitle+nextTitle;
        }else {
            return title;
        }

    }


    /**
     *获取角色人员
     * @param typeid
     * @return
     */
    public static String  getTypeOperator(String typeid){
        String lastoperator = "";
        if(!"".equals(typeid)){
            RecordSet rs  = new RecordSet();
            String roleid = new BaseBean().getPropValue("TypeOperator",typeid);
            String sql  = "select resourceid from HrmRoleMembers where roleid="+roleid;
            rs.executeSql(sql);
            if(rs.next()){
                lastoperator = weaver.general.Util.null2String(rs.getString("resourceid"));
            }
        }
        return lastoperator;
    }

    private static String getCurrentOperator(RequestInfo request){
        String currentoperator = "";
        String nodeid = "";
        if(request!=null){
            RecordSet rs  = new RecordSet();
            String sql  = "select nodeid from uf_lcjdgxb where wfid = '"+request.getWorkflowid()+"'";
            rs.executeSql(sql);
            if(rs.next()){
                nodeid = weaver.general.Util.null2String(rs.getString("nodeid"));
            }
            String sql1  = "select userid from workflow_currentoperator where requestid = "+request.getRequestid()+" and nodeid = "+nodeid+" and isremark ='2' and preisremark = '0' and handleforwardid is null";
            rs.executeSql(sql1);
            if(rs.next()){
                currentoperator = weaver.general.Util.null2String(rs.getString("userid"));
            }
        }
        return currentoperator;
    }
}
