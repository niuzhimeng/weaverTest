package com.weavernorth.OA2archives;

import com.weavernorth.OA2archives.util.ArchivesUtil;
import com.weavernorth.OA2archives.util.WorkflowUtil;
import com.weavernorth.OA2archives.util.WriteExcel;
import com.weavernorth.ftp.FtpObject;
import com.weavernorth.ftp.FtpUtil;
import com.weavernorth.ftp.FtpUtil_zz;
import com.weavernorth.util.LogUtil;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.general.Util;
import weaver.hrm.company.DepartmentComInfo;
import weaver.hrm.resource.ResourceComInfo;
import weaver.interfaces.schedule.BaseCronJob;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.weavernorth.OA2archives.util.ArchivesUtil.getSettingColName;

/**
 * 类名:OA2ArchivesCron
 * 类描述:公文和合同流程数据存入excel并定时上传ftp
 * Company:weavernorth
 *
 * @author:刘立华
 * @date: 2017-11-6下午3:20:30
 */
public class OAExcel2ArchivesCron extends BaseCronJob {
    private static BaseBean base = new BaseBean();
    //TODO 上个月的第一天  需要修改为上个月
    public static String strStartDate = TimeUtil.getMonthBeginDay();
    //TimeUtil.getLastMonthBeginDay();
    //TODO 上个月的最后一天 需要修改为上个月
    public static String strEndDate = TimeUtil.getMonthEndDay();

    //TimeUtil.getLastMonthEndDay();
    public OAExcel2ArchivesCron() {
    	
    }


    public void execute() {
        LogUtil.doWriteLog("====生成excel开始===" + TimeUtil.getOnlyCurrentTimeString() + "===");
        ExcelInfo();
        LogUtil.doWriteLog("====生成excel结束===" + TimeUtil.getOnlyCurrentTimeString() + "===");
    }

    /**
     * 导出excel  TODO  对应增加相应流程的逻辑
     */
    public void ExcelInfo() {

        ResourceComInfo re = null;
        try {
            re = new ResourceComInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String tableName = "";
        String param = "";
        int typeid = 0;
        //模板路径
        String oldXlsxPath = "";
        //新文件保存路径
        String newXlsPath = "";
        String sql = "";
        RecordSet rs = new RecordSet();
        for (int i = 1; i <= 41; i++) {
            LogUtil.debugLog("==进入循环开始生产excel=>"+i);
            boolean hasData = false;
            if (i == 1) {
                tableName = "wn_oa2emc_contract";
                param = "in (1,2)";
            } else if (i == 2) {
                tableName = "wn_oa2emc_document";
                param = " =3";
            } else if (i == 3) {
                tableName = "wn_oa2emc_document";
                param = " =4";
            } else if (i == 4) {
                tableName = "wn_oa2emc_document";
                param = " =5";
            } else if (i == 5) {
                tableName = "wn_oa2emc_document";
                param = " =6";
            } else if (i == 6) {
                tableName = "wn_oa2emc_document";
                param = " =7";
            } else if (i == 7) {
                tableName = "wn_oa2emc_document";
                param = " =8";
            } else if (i == 8) {
                tableName = "wn_oa2emc_document";
                param = " =9";
            } else if (i == 9) {
                tableName = "wn_oa2emc_document";
                param = " =10";
            } else if (i == 11) {
                tableName = "wn_oa2emc_document";
                param = " in (15,16,17)";
            } else if (i == 12) {
                tableName = "wn_oa2emc_document";
                param = " =18";
            } else if (i == 13) {
                tableName = "wn_oa2emc_document";
                param = " =19";
            } else if (i == 15) {
                tableName = "wn_oa2emc_document";
                param = " =22";
            } else if (i == 16) {
                tableName = "wn_oa2emc_document";
                param = " =23";
            } else if (i == 17) {
                tableName = "wn_oa2emc_document";
                param = " =24";
            } else if (i == 18) {
                tableName = "wn_oa2emc_document";
                param = " =25";
            } else if (i == 19) {
                tableName = "wn_oa2emc_document";
                param = " =26";
            } else if (i == 20) {
                tableName = "wn_oa2emc_document";
                param = " =27";
            } else if (i == 21) {
                tableName = "wn_oa2emc_contract";
                param = " in (29,30)";
            }else if (i == 22) {
                tableName = "wn_oa2emc_document";
                param = " =31"; //与19对应
            }else if (i == 23) {
                tableName = "wn_oa2emc_document";
                param = " =32"; //与4对应
            }else if (i == 24) {
                tableName = "wn_oa2emc_document";
                param = " =33"; //与5对应
            }else if (i == 25) {
                tableName = "wn_oa2emc_document";
                param = " =34"; //与22对应
            }else if (i == 26) {
                tableName = "wn_oa2emc_document";
                param = " =35"; //与23对应
            }else if (i == 27) {
                tableName = "wn_oa2emc_document";
                param = " =36"; //与24对应
            }else if (i == 28) {
                tableName = "wn_oa2emc_document";
                param = " =37"; //与25对应
            }else if (i == 29) {
                tableName = "wn_oa2emc_document";
                param = " =38"; //与26对应
            }else if (i == 30) {
                tableName = "wn_oa2emc_document";
                param = " =39"; //与27对应
            }else if (i == 31) {
                tableName = "wn_oa2emc_document";
                param = " =40"; //与9对应
            }else if (i == 32) {
                tableName = "wn_oa2emc_document";
                param = " =41"; //与10对应
            }else if (i == 33) {
                tableName = "wn_oa2emc_document";
                param = " =28";
            }
            // 	DBN合同签订流程
            else if (i == 34) {
                tableName = "wn_oa2emc_contract";
                param = " =42";
            }
            // DBN项目部发文申请
            else if (i == 35) {
                tableName = "wn_oa2emc_document";
                param = " =43";
            }
            // DBN项目部发函申请
            else if (i == 36) {
                tableName = "wn_oa2emc_document";
                param = " =44";
            }
            // DBN项目部会议纪要申请
            else if (i == 37) {
                tableName = "wn_oa2emc_document";
                param = " =45";
            }
            // DBN项目部部门会议纪要申请
            else if (i == 38) {
                tableName = "wn_oa2emc_document";
                param = " =46";
            }
            // DBN项目部签报单申请
            else if (i == 39) {
                tableName = "wn_oa2emc_document";
                param = " =47";
            }
            // DBN 合同变更审批流程
            else if (i == 40) {
                tableName = "wn_oa2emc_contract";
                param = " =49";
            }
            // DBN 授权委托申请
            else if (i == 41) {
                tableName = "wn_oa2emc_document";
                param = " =50";
            }
            sql = "select * from " + tableName + " where typeid " + param + "  and c_archive_date>='" + strStartDate + "' and c_archive_date<='" + strEndDate + "'";
            LogUtil.debugLog("==查询excel信息所需==>" + sql);
            rs.execute(sql);
            List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
            // 合同创建流程/合同变更流程  excel信息
            if (i == 1) {

                while (rs.next()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    oldXlsxPath = Util.null2String(rs.getString("excelmode_path")) + 1 + ".xls";
                    LogUtil.debugLog("==合同模板路径==>" + oldXlsxPath);
                    newXlsPath = Util.null2String(rs.getString("newexcel_path")) + "合同" + ".xls";
                    LogUtil.debugLog("==合同签订保存路径==>" + newXlsPath);
                    String requestid = Util.null2String(rs.getString("requestid"));
                    typeid = Util.getIntValue(rs.getString("typeid"));
                    dataMap.put("object_name", Util.null2String(rs.getString("object_name")));
                    dataMap.put("file_path", Util.null2String(rs.getString("file_path")).replace("/", "\\").replace("/", "\\"));
                    dataMap.put("file_format", Util.null2String(rs.getString("file_format")));
                    dataMap.put("c_chinese_title", Util.null2String(rs.getString("c_chinese_title")));
                    dataMap.put("c_doc_code", Util.null2String(rs.getString("c_doc_code")));
                    //流程中的c_contract_type的数值
                    String c_contract_type = Util.null2String(rs.getString("c_contract_type"));
                    LogUtil.debugLog("c_contract_type:" + c_contract_type);
                    dataMap.put("newexcel_path", newXlsPath);
                    //合同类型的数据库字段
                    String columName = getSettingColName(typeid + "", "gw_fwlx");
                    //合同类型字段的页面fieldid
                    String fieldId = WorkflowUtil.getFieldId(requestid, columName);
                    //合同类型的中文名称
                    String c_contract_type_CH = WorkflowUtil.getSelectChinesType(c_contract_type, fieldId);
                    dataMap.put("c_contract_type", c_contract_type_CH);
                    dataMap.put("c_year", Util.null2String(rs.getString("c_year")));
                    dataMap.put("c_party_a", Util.null2String(rs.getString("c_party_a")));
                    dataMap.put("c_party_b", Util.null2String(rs.getString("c_party_b")));
                    String c_resp_dept = Util.null2String(rs.getString("c_resp_dept"));
                    String c_resp_dept_ZH = WorkflowUtil.getDepartmentname(c_resp_dept);
                    dataMap.put("c_resp_dept", c_resp_dept_ZH);
                    dataMap.put("c_page_counts", Util.null2String(rs.getString("c_page_counts")));
                    dataMap.put("c_archive_date", Util.null2String(rs.getString("c_archive_date")));
                    String hrmid = Util.null2String(rs.getString("c_archive_owne"));
                    String hrmName = ArchivesUtil.getChineseHrmName(hrmid);
                    dataMap.put("c_archive_owne", hrmName);
                    dataMap.put("c_inner_sequence", Util.null2String(rs.getString("c_inner_sequence")));
                    dataList.add(dataMap);
                    hasData = true;
                }
            } else if (i == 2) {
                //公司收发文信息
                while (rs.next()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    oldXlsxPath = Util.null2String(rs.getString("excelmode_path")) + 3 + ".xls";
                    LogUtil.debugLog("==总裁信息模板路径==>" + oldXlsxPath);
                    String fileName = "总裁信息";
                    DepartmentComInfo deptinfo = new DepartmentComInfo();
                    String departmentName = "";
                    try {
                        departmentName = deptinfo.getDepartmentName(Util.null2String(rs.getString("c_compile_dept")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    newXlsPath = Util.null2String(rs.getString("newexcel_path")) + fileName + ".xls";
                    typeid = Util.getIntValue(rs.getString("typeid"));
                    LogUtil.debugLog("==公文保存路径==>" + newXlsPath);
                    dataMap.put("object_name", Util.null2String(rs.getString("object_name")));
                    dataMap.put("file_path", Util.null2String(rs.getString("file_path")).replace("/", "\\").replace("/", "\\"));
                    dataMap.put("file_format", Util.null2String(rs.getString("file_format")));
                    dataMap.put("c_chinese_title", Util.null2String(rs.getString("c_chinese_title")));
                    //发文类型数据库存的值
                    // String fwlx = Util.null2String(rs.getString("c_doc_type"));
                    String requestid = Util.null2String(rs.getString("requestid"));
                    //发文类型数据库的字段名
                    // String columName = getSettingColName(typeid + "", "gw_fwlx");
                    dataMap.put("newexcel_path", newXlsPath);
                    dataMap.put("c_doc_type", "");
                    String c_compile_deptName = WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept")));
                    dataMap.put("c_compile_dept", c_compile_deptName);
                    dataMap.put("c_compile_date", Util.null2String(rs.getString("c_compile_date")));
                    dataMap.put("c_compiler", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_compiler"))));
                    dataMap.put("c_issue_date", Util.null2String(rs.getString("c_issue_date")));
                    dataMap.put("c_issue_num", Util.null2String(rs.getString("c_issue_num")));
                    String c_issue_deptName = WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_issue_dept")));
                    dataMap.put("c_issue_dept", c_issue_deptName);
                    String c_target_deptName = WorkflowUtil.getDepartmentname(rs.getString("c_target_dept"));
                    dataMap.put("c_target_dept", c_target_deptName);
                    dataMap.put("c_page_counts", Util.null2String(rs.getString("c_page_counts")));
                    dataMap.put("c_archive_date", Util.null2String(rs.getString("c_archive_date")));
                    String c_archive_orgName = WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_archive_org")));
                    dataMap.put("c_archive_org", c_archive_orgName);
                    String c_archive_ownerName = ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_archive_owner")));
                    dataMap.put("c_archive_owner", c_archive_ownerName);
                    dataMap.put("c_inner_sequence", Util.null2String(rs.getString("c_inner_sequence")));
                    dataList.add(dataMap);
                    hasData = true;
                }
            } else if (i == 3) {
                while (rs.next()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    oldXlsxPath = Util.null2String(rs.getString("excelmode_path")) + 4 + ".xls";
                    LogUtil.debugLog("==公文模板路径==>" + oldXlsxPath);
                    String fileName = "用印申请";
                    typeid = Util.getIntValue(rs.getString("typeid"));
                    newXlsPath = Util.null2String(rs.getString("newexcel_path")) + fileName + ".xls";
                    LogUtil.debugLog("==公文保存路径==>" + newXlsPath);
                    dataMap.put("object_name", Util.null2String(rs.getString("object_name")));
                    dataMap.put("file_path", Util.null2String(rs.getString("file_path")).replace("/", "\\"));
                    dataMap.put("file_format", Util.null2String(rs.getString("file_format")));
                    dataMap.put("c_chinese_title", Util.null2String(rs.getString("c_chinese_title")));
                    //发文类型数据库存的值
                    // String fwlx = Util.null2String(rs.getString("c_doc_type"));
                    String requestid = Util.null2String(rs.getString("requestid"));
                    //发文类型数据库的字段名
                    // String columName = getSettingColName(typeid + "", "gw_fwlx");
                    dataMap.put("c_doc_type", "");
                    String c_compile_deptName = WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept")));
                    dataMap.put("c_compile_dept", c_compile_deptName);
                    dataMap.put("c_compile_date", Util.null2String(rs.getString("c_compile_date")));
                    String c_compilerName = ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_compiler")));
                    dataMap.put("c_compiler", c_compilerName);
                    dataMap.put("c_issue_date", Util.null2String(rs.getString("c_issue_date")));
                    dataMap.put("c_issue_num", Util.null2String(rs.getString("c_issue_num")));
                    String c_issue_deptName = WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_issue_dept")));
                    dataMap.put("newexcel_path", newXlsPath);
                    dataMap.put("c_issue_dept", c_issue_deptName);
                    String c_target_deptName = WorkflowUtil.getDepartmentname(Util.null2String((rs.getString("c_target_dept"))));
                    dataMap.put("c_target_dept", Util.null2String(c_target_deptName));
                    dataMap.put("c_page_counts", Util.null2String(rs.getString("c_page_counts")));
                    dataMap.put("c_archive_date", Util.null2String(rs.getString("c_archive_date")));
                    dataMap.put("c_archive_org", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_archive_org"))));
                    dataMap.put("c_archive_owner", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_archive_owner"))));
                    dataMap.put("c_inner_sequence", Util.null2String(rs.getString("c_inner_sequence")));
                    dataList.add(dataMap);
                    hasData = true;
                }
            } else if (i == 23) {
                while (rs.next()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    oldXlsxPath = Util.null2String(rs.getString("excelmode_path")) + 32 + ".xls";
                    LogUtil.debugLog("==公文模板路径==>" + oldXlsxPath);
                    String fileName = "PC用印申请";
                    typeid = Util.getIntValue(rs.getString("typeid"));
                    newXlsPath = Util.null2String(rs.getString("newexcel_path")) + fileName + ".xls";
                    LogUtil.debugLog("==公文保存路径==>" + newXlsPath);
                    dataMap.put("object_name", Util.null2String(rs.getString("object_name")));
                    dataMap.put("file_path", Util.null2String(rs.getString("file_path")).replace("/", "\\"));
                    dataMap.put("file_format", Util.null2String(rs.getString("file_format")));
                    dataMap.put("c_chinese_title", Util.null2String(rs.getString("c_chinese_title")));
                    //发文类型数据库存的值
                    // String fwlx = Util.null2String(rs.getString("c_doc_type"));
                    String requestid = Util.null2String(rs.getString("requestid"));
                    //发文类型数据库的字段名
                    // String columName = getSettingColName(typeid + "", "gw_fwlx");
                    dataMap.put("c_doc_type", "");
                    String c_compile_deptName = WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept")));
                    dataMap.put("c_compile_dept", c_compile_deptName);
                    dataMap.put("c_compile_date", Util.null2String(rs.getString("c_compile_date")));
                    String c_compilerName = ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_compiler")));
                    dataMap.put("c_compiler", c_compilerName);
                    dataMap.put("c_issue_date", Util.null2String(rs.getString("c_issue_date")));
                    dataMap.put("c_issue_num", Util.null2String(rs.getString("c_issue_num")));
                    String c_issue_deptName = WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_issue_dept")));
                    dataMap.put("newexcel_path", newXlsPath);
                    dataMap.put("c_issue_dept", c_issue_deptName);
                    String c_target_deptName = WorkflowUtil.getDepartmentname(Util.null2String((rs.getString("c_target_dept"))));
                    dataMap.put("c_target_dept", Util.null2String(c_target_deptName));
                    dataMap.put("c_page_counts", Util.null2String(rs.getString("c_page_counts")));
                    dataMap.put("c_archive_date", Util.null2String(rs.getString("c_archive_date")));
                    dataMap.put("c_archive_org", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_archive_org"))));
                    dataMap.put("c_archive_owner", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_archive_owner"))));
                    dataMap.put("c_inner_sequence", Util.null2String(rs.getString("c_inner_sequence")));
                    dataList.add(dataMap);
                    hasData = true;
                }
            } else if (i == 4) {
                while (rs.next()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    oldXlsxPath = Util.null2String(rs.getString("excelmode_path")) + 5 + ".xls";
                    LogUtil.debugLog("==公文模板路径==>" + oldXlsxPath);
                    String fileName = "印章";
                    DepartmentComInfo deptinfo = new DepartmentComInfo();
                    String departmentName = "";
                    try {
                        departmentName = deptinfo.getDepartmentName(Util.null2String(rs.getString("c_compile_dept")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    typeid = Util.getIntValue(rs.getString("typeid"));
                    newXlsPath = Util.null2String(rs.getString("newexcel_path")) + fileName + ".xls";
                    LogUtil.debugLog("==公文保存路径==>" + newXlsPath);
                    dataMap.put("object_name", Util.null2String(rs.getString("object_name")));
                    dataMap.put("file_path", Util.null2String(rs.getString("file_path")).replace("/", "\\"));
                    dataMap.put("file_format", Util.null2String(rs.getString("file_format")));
                    dataMap.put("c_chinese_title", Util.null2String(rs.getString("c_chinese_title")));
                    //发文类型数据库存的值
                    // String fwlx = Util.null2String(rs.getString("c_doc_type"));
                    String requestid = Util.null2String(rs.getString("requestid"));
                    //发文类型数据库的字段名
                    // String columName = getSettingColName(typeid + "", "gw_fwlx");
                    dataMap.put("c_doc_type", "");
                    dataMap.put("c_compile_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept"))));
                    dataMap.put("c_compile_date", Util.null2String(rs.getString("c_compile_date")));
                    dataMap.put("c_compiler", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_compiler"))));
                    dataMap.put("c_issue_date", Util.null2String(rs.getString("c_issue_date")));
                    dataMap.put("c_issue_num", Util.null2String(rs.getString("c_issue_num")));
                    dataMap.put("c_issue_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_issue_dept"))));
                    dataMap.put("newexcel_path", newXlsPath);
                    String c_target_deptName = WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_target_dept")));
                    dataMap.put("c_target_dept", Util.null2String(c_target_deptName));
                    dataMap.put("c_page_counts", Util.null2String(rs.getString("c_page_counts")));
                    dataMap.put("c_archive_date", Util.null2String(rs.getString("c_archive_date")));
                    dataMap.put("c_archive_org", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_archive_org"))));
                    dataMap.put("c_archive_owner", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_archive_owner"))));
                    dataMap.put("c_inner_sequence", Util.null2String(rs.getString("c_inner_sequence")));
                    dataList.add(dataMap);
                    hasData = true;
                }
            } else if (i == 24) {
                while (rs.next()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    oldXlsxPath = Util.null2String(rs.getString("excelmode_path")) + 33 + ".xls";
                    LogUtil.debugLog("==公文模板路径==>" + oldXlsxPath);
                    String fileName = "PC印章";
                    DepartmentComInfo deptinfo = new DepartmentComInfo();
                    String departmentName = "";
                    try {
                        departmentName = deptinfo.getDepartmentName(Util.null2String(rs.getString("c_compile_dept")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    typeid = Util.getIntValue(rs.getString("typeid"));
                    newXlsPath = Util.null2String(rs.getString("newexcel_path")) + fileName + ".xls";
                    LogUtil.debugLog("==公文保存路径==>" + newXlsPath);
                    dataMap.put("object_name", Util.null2String(rs.getString("object_name")));
                    dataMap.put("file_path", Util.null2String(rs.getString("file_path")).replace("/", "\\"));
                    dataMap.put("file_format", Util.null2String(rs.getString("file_format")));
                    dataMap.put("c_chinese_title", Util.null2String(rs.getString("c_chinese_title")));
                    //发文类型数据库存的值
                    // String fwlx = Util.null2String(rs.getString("c_doc_type"));
                    String requestid = Util.null2String(rs.getString("requestid"));
                    //发文类型数据库的字段名
                    // String columName = getSettingColName(typeid + "", "gw_fwlx");
                    dataMap.put("c_doc_type", "");
                    dataMap.put("c_compile_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept"))));
                    dataMap.put("c_compile_date", Util.null2String(rs.getString("c_compile_date")));
                    dataMap.put("c_compiler", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_compiler"))));
                    dataMap.put("c_issue_date", Util.null2String(rs.getString("c_issue_date")));
                    dataMap.put("c_issue_num", Util.null2String(rs.getString("c_issue_num")));
                    dataMap.put("c_issue_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_issue_dept"))));
                    dataMap.put("newexcel_path", newXlsPath);
                    String c_target_deptName = WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_target_dept")));
                    dataMap.put("c_target_dept", Util.null2String(c_target_deptName));
                    dataMap.put("c_page_counts", Util.null2String(rs.getString("c_page_counts")));
                    dataMap.put("c_archive_date", Util.null2String(rs.getString("c_archive_date")));
                    dataMap.put("c_archive_org", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_archive_org"))));
                    dataMap.put("c_archive_owner", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_archive_owner"))));
                    dataMap.put("c_inner_sequence", Util.null2String(rs.getString("c_inner_sequence")));
                    dataList.add(dataMap);
                    hasData = true;
                }
            }else if (i == 5) {
                while (rs.next()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    oldXlsxPath = Util.null2String(rs.getString("excelmode_path")) + 6 + ".xls";
                    LogUtil.debugLog("==办公会议模板路径==>" + oldXlsxPath);
                    String fileName = "议题提报";
                    DepartmentComInfo deptinfo = new DepartmentComInfo();
                    String departmentName = "";
                    try {
                        departmentName = deptinfo.getDepartmentName(Util.null2String(rs.getString("c_compile_dept")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    typeid = Util.getIntValue(rs.getString("typeid"));
                    newXlsPath = Util.null2String(rs.getString("newexcel_path")) + fileName + ".xls";
                    LogUtil.debugLog("==办公会议保存路径==>" + newXlsPath);
                    dataMap.put("object_name", Util.null2String(rs.getString("object_name")));
                    dataMap.put("file_path", Util.null2String(rs.getString("file_path")).replace("/", "\\"));
                    dataMap.put("file_format", Util.null2String(rs.getString("file_format")));
                    dataMap.put("c_chinese_title", Util.null2String(rs.getString("c_chinese_title")));
                    //发文类型数据库存的值
                    // String fwlx = Util.null2String(rs.getString("c_doc_type"));
                    String requestid = Util.null2String(rs.getString("requestid"));
                    //发文类型数据库的字段名
                    // String columName = getSettingColName(typeid + "", "gw_fwlx");
                    dataMap.put("newexcel_path", newXlsPath);
                    dataMap.put("c_doc_type", "");
                    dataMap.put("c_compile_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept"))));
                    dataMap.put("c_compile_date", Util.null2String(rs.getString("c_compile_date")));
                    dataMap.put("c_compiler", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_compiler"))));
                    dataMap.put("c_issue_date", Util.null2String(rs.getString("c_issue_date")));
                    dataMap.put("c_issue_num", Util.null2String(rs.getString("c_issue_num")));
                    dataMap.put("c_issue_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_issue_dept"))));
                    String c_target_deptName = WorkflowUtil.getDepartmentname(Util.null2String((rs.getString("c_target_dept"))));
                    dataMap.put("c_target_dept", Util.null2String(c_target_deptName));
                    dataMap.put("c_page_counts", Util.null2String(rs.getString("c_page_counts")));
                    dataMap.put("c_archive_date", Util.null2String(rs.getString("c_archive_date")));
                    dataMap.put("c_archive_org", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_archive_org"))));
                    dataMap.put("c_archive_owner", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_archive_owner"))));
                    dataMap.put("c_inner_sequence", Util.null2String(rs.getString("c_inner_sequence")));
                    dataList.add(dataMap);
                    hasData = true;
                }

            } else if (i == 6) {
                while (rs.next()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    oldXlsxPath = Util.null2String(rs.getString("excelmode_path")) + 7 + ".xls";
                    LogUtil.debugLog("==用印申请模板路径==>" + oldXlsxPath);
                    String fileName = "用印申请";
                    DepartmentComInfo deptinfo = new DepartmentComInfo();
                    String departmentName = "";
                    try {
                        departmentName = deptinfo.getDepartmentName(Util.null2String(rs.getString("c_compile_dept")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    typeid = Util.getIntValue(rs.getString("typeid"));
                    newXlsPath = Util.null2String(rs.getString("newexcel_path")) + fileName + ".xls";
                    LogUtil.debugLog("==用印申请保存路径==>" + newXlsPath);
                    dataMap.put("object_name", Util.null2String(rs.getString("object_name")));
                    dataMap.put("file_path", Util.null2String(rs.getString("file_path")).replace("/", "\\"));
                    dataMap.put("file_format", Util.null2String(rs.getString("file_format")));
                    dataMap.put("c_chinese_title", Util.null2String(rs.getString("c_chinese_title")));
                    //发文类型数据库存的值
                    // String fwlx = Util.null2String(rs.getString("c_doc_type"));
                    String requestid = Util.null2String(rs.getString("requestid"));
                    //发文类型数据库的字段名
                    // String columName = getSettingColName(typeid + "", "gw_fwlx");
                    dataMap.put("newexcel_path", newXlsPath);
                    dataMap.put("c_doc_type", "");
                    dataMap.put("c_compile_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept"))));
                    dataMap.put("c_compile_date", Util.null2String(rs.getString("c_compile_date")));
                    dataMap.put("c_compiler", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_compiler"))));
                    dataMap.put("c_issue_date", Util.null2String(rs.getString("c_issue_date")));
                    dataMap.put("c_issue_num", Util.null2String(rs.getString("c_issue_num")));
                    dataMap.put("c_issue_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_issue_dept"))));
                    String c_target_deptName = WorkflowUtil.getDepartmentname(Util.null2String((rs.getString("c_target_dept"))));
                    dataMap.put("c_target_dept", Util.null2String(c_target_deptName));
                    dataMap.put("c_page_counts", Util.null2String(rs.getString("c_page_counts")));
                    dataMap.put("c_archive_date", Util.null2String(rs.getString("c_archive_date")));
                    dataMap.put("c_archive_org", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_archive_org"))));
                    dataMap.put("c_archive_owner", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_archive_owner"))));
                    dataMap.put("c_inner_sequence", Util.null2String(rs.getString("c_inner_sequence")));
                    dataList.add(dataMap);
                    hasData = true;
                }
            } else if (i == 7) {
                while (rs.next()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    oldXlsxPath = Util.null2String(rs.getString("excelmode_path")) + 8 + ".xls";
                    LogUtil.debugLog("==用印申请模板路径==>" + oldXlsxPath);
                    String fileName = "用印申请";
                    DepartmentComInfo deptinfo = new DepartmentComInfo();
                    String departmentName = "";
                    try {
                        departmentName = deptinfo.getDepartmentName(Util.null2String(rs.getString("c_compile_dept")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    typeid = Util.getIntValue(rs.getString("typeid"));
                    newXlsPath = Util.null2String(rs.getString("newexcel_path")) + fileName + ".xls";
                    LogUtil.debugLog("==公文保存路径==>" + newXlsPath);
                    dataMap.put("object_name", Util.null2String(rs.getString("object_name")));
                    dataMap.put("file_path", Util.null2String(rs.getString("file_path")).replace("/", "\\"));
                    dataMap.put("file_format", Util.null2String(rs.getString("file_format")));
                    dataMap.put("c_chinese_title", Util.null2String(rs.getString("c_chinese_title")));
                    //发文类型数据库存的值
                    // String fwlx = Util.null2String(rs.getString("c_doc_type"));
                    String requestid = Util.null2String(rs.getString("requestid"));
                    //发文类型数据库的字段名
                    // String columName = getSettingColName(typeid + "", "gw_fwlx");
                    dataMap.put("newexcel_path", newXlsPath);
                    dataMap.put("c_doc_type", "");
                    dataMap.put("c_compile_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept"))));
                    dataMap.put("c_compile_date", Util.null2String(rs.getString("c_compile_date")));
                    dataMap.put("c_compiler", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_compiler"))));
                    dataMap.put("c_issue_date", Util.null2String(rs.getString("c_issue_date")));
                    dataMap.put("c_issue_num", Util.null2String(rs.getString("c_issue_num")));
                    dataMap.put("c_issue_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_issue_dept"))));
                    String c_target_deptName = WorkflowUtil.getDepartmentname(Util.null2String((rs.getString("c_target_dept"))));
                    dataMap.put("c_target_dept", Util.null2String(c_target_deptName));
                    dataMap.put("c_page_counts", Util.null2String(rs.getString("c_page_counts")));
                    dataMap.put("c_archive_date", Util.null2String(rs.getString("c_archive_date")));
                    dataMap.put("c_archive_org", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_archive_org"))));
                    dataMap.put("c_archive_owner", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_archive_owner"))));
                    dataMap.put("c_inner_sequence", Util.null2String(rs.getString("c_inner_sequence")));
                    dataList.add(dataMap);
                    hasData = true;
                }
            } else if (i == 8) {
                while (rs.next()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    oldXlsxPath = Util.null2String(rs.getString("excelmode_path")) + 9 + ".xls";
                    LogUtil.debugLog("==收文模板路径==>" + oldXlsxPath);
                    String fileName = "公司收文";
                    DepartmentComInfo deptinfo = new DepartmentComInfo();
                    String departmentName = "";
                    try {
                        departmentName = deptinfo.getDepartmentName(Util.null2String(rs.getString("c_compile_dept")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    typeid = Util.getIntValue(rs.getString("typeid"));
                    newXlsPath = Util.null2String(rs.getString("newexcel_path")) + fileName + ".xls";
                    LogUtil.debugLog("==收文保存路径==>" + newXlsPath);
                    dataMap.put("object_name", Util.null2String(rs.getString("object_name")));
                    dataMap.put("file_path", Util.null2String(rs.getString("file_path")).replace("/", "\\"));
                    dataMap.put("file_format", Util.null2String(rs.getString("file_format")));
                    dataMap.put("c_chinese_title", Util.null2String(rs.getString("c_chinese_title")));
                    //发文类型数据库存的值
                    // String fwlx = Util.null2String(rs.getString("c_doc_type"));
                    String requestid = Util.null2String(rs.getString("requestid"));
                    //发文类型数据库的字段名
                    // String columName = getSettingColName(typeid + "", "gw_fwlx");
                    dataMap.put("newexcel_path", newXlsPath);
                    dataMap.put("c_doc_type", "");
                    dataMap.put("c_compile_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept"))));
                    dataMap.put("c_compile_date", Util.null2String(rs.getString("c_compile_date")));
                    dataMap.put("c_compiler", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_compiler"))));
                    dataMap.put("c_issue_date", Util.null2String(rs.getString("c_issue_date")));
                    dataMap.put("c_issue_num", Util.null2String(rs.getString("c_issue_num")));
                    dataMap.put("c_issue_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_issue_dept"))));
                    String c_target_deptName = WorkflowUtil.getDepartmentname(Util.null2String((rs.getString("c_target_dept"))));
                    dataMap.put("c_target_dept", Util.null2String(c_target_deptName));
                    dataMap.put("c_page_counts", Util.null2String(rs.getString("c_page_counts")));
                    dataMap.put("c_archive_date", Util.null2String(rs.getString("c_archive_date")));
                    dataMap.put("c_archive_org", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_archive_org"))));
                    dataMap.put("c_archive_owner", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_archive_owner"))));
                    dataMap.put("c_inner_sequence", Util.null2String(rs.getString("c_inner_sequence")));
                    dataList.add(dataMap);
                    hasData = true;
                }
            }else if (i == 31) {
                while (rs.next()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    oldXlsxPath = Util.null2String(rs.getString("excelmode_path")) + 40 + ".xls";
                    LogUtil.debugLog("==收文模板路径==>" + oldXlsxPath);
                    String fileName = "项目部收文";
                    DepartmentComInfo deptinfo = new DepartmentComInfo();
                    String departmentName = "";
                    try {
                        departmentName = deptinfo.getDepartmentName(Util.null2String(rs.getString("c_compile_dept")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    typeid = Util.getIntValue(rs.getString("typeid"));
                    newXlsPath = Util.null2String(rs.getString("newexcel_path")) + fileName + ".xls";
                    LogUtil.debugLog("==收文保存路径==>" + newXlsPath);
                    dataMap.put("object_name", Util.null2String(rs.getString("object_name")));
                    dataMap.put("file_path", Util.null2String(rs.getString("file_path")).replace("/", "\\"));
                    dataMap.put("file_format", Util.null2String(rs.getString("file_format")));
                    dataMap.put("c_chinese_title", Util.null2String(rs.getString("c_chinese_title")));
                    //发文类型数据库存的值
                    // String fwlx = Util.null2String(rs.getString("c_doc_type"));
                    String requestid = Util.null2String(rs.getString("requestid"));
                    //发文类型数据库的字段名
                    // String columName = getSettingColName(typeid + "", "gw_fwlx");
                    dataMap.put("newexcel_path", newXlsPath);
                    dataMap.put("c_doc_type", "");
                    dataMap.put("c_compile_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept"))));
                    dataMap.put("c_compile_date", Util.null2String(rs.getString("c_compile_date")));
                    dataMap.put("c_compiler", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_compiler"))));
                    dataMap.put("c_issue_date", Util.null2String(rs.getString("c_issue_date")));
                    dataMap.put("c_issue_num", Util.null2String(rs.getString("c_issue_num")));
                    dataMap.put("c_issue_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_issue_dept"))));
                    String c_target_deptName = WorkflowUtil.getDepartmentname(Util.null2String((rs.getString("c_target_dept"))));
                    dataMap.put("c_target_dept", Util.null2String(c_target_deptName));
                    dataMap.put("c_page_counts", Util.null2String(rs.getString("c_page_counts")));
                    dataMap.put("c_archive_date", Util.null2String(rs.getString("c_archive_date")));
                    dataMap.put("c_archive_org", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_archive_org"))));
                    dataMap.put("c_archive_owner", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_archive_owner"))));
                    dataMap.put("c_inner_sequence", Util.null2String(rs.getString("c_inner_sequence")));
                    dataList.add(dataMap);
                    hasData = true;
                }
            } else if (i == 9) {
                while (rs.next()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    oldXlsxPath = Util.null2String(rs.getString("excelmode_path")) + 10 + ".xls";
                    LogUtil.debugLog("==签报单模板路径==>" + oldXlsxPath);
                    String fileName = "签报单";
                    DepartmentComInfo deptinfo = new DepartmentComInfo();
                    String departmentName = "";
                    try {
                        departmentName = deptinfo.getDepartmentName(Util.null2String(rs.getString("c_compile_dept")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    typeid = Util.getIntValue(rs.getString("typeid"));
                    newXlsPath = Util.null2String(rs.getString("newexcel_path")) + fileName + ".xls";
                    LogUtil.debugLog("==签报单保存路径==>" + newXlsPath);
                    dataMap.put("object_name", Util.null2String(rs.getString("object_name")));
                    dataMap.put("file_path", Util.null2String(rs.getString("file_path")).replace("/", "\\"));
                    dataMap.put("file_format", Util.null2String(rs.getString("file_format")));
                    dataMap.put("c_chinese_title", Util.null2String(rs.getString("c_chinese_title")));
                    //发文类型数据库存的值
                    // String fwlx = Util.null2String(rs.getString("c_doc_type"));
                    String requestid = Util.null2String(rs.getString("requestid"));
                    //发文类型数据库的字段名
                    // String columName = getSettingColName(typeid + "", "gw_fwlx");
                    dataMap.put("newexcel_path", newXlsPath);
                    dataMap.put("c_doc_type", "");
                    dataMap.put("c_compile_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept"))));
                    dataMap.put("c_compile_date", Util.null2String(rs.getString("c_compile_date")));
                    dataMap.put("c_compiler", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_compiler"))));
                    dataMap.put("c_issue_date", Util.null2String(rs.getString("c_issue_date")));
                    dataMap.put("c_issue_num", Util.null2String(rs.getString("c_issue_num")));
                    dataMap.put("c_issue_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_issue_dept"))));
                    String c_target_deptName = WorkflowUtil.getDepartmentname(Util.null2String((rs.getString("c_target_dept"))));
                    dataMap.put("c_target_dept", Util.null2String(c_target_deptName));
                    dataMap.put("c_page_counts", Util.null2String(rs.getString("c_page_counts")));
                    dataMap.put("c_archive_date", Util.null2String(rs.getString("c_archive_date")));
                    dataMap.put("c_archive_org", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_archive_org"))));
                    dataMap.put("c_archive_owner", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_archive_owner"))));
                    dataMap.put("c_inner_sequence", Util.null2String(rs.getString("c_inner_sequence")));
                    dataList.add(dataMap);
                    hasData = true;
                }
            }else if (i == 32) {
                while (rs.next()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    oldXlsxPath = Util.null2String(rs.getString("excelmode_path")) + 41 + ".xls";
                    LogUtil.debugLog("==签报单模板路径==>" + oldXlsxPath);
                    String fileName = "项目部签报单";
                    DepartmentComInfo deptinfo = new DepartmentComInfo();
                    String departmentName = "";
                    try {
                        departmentName = deptinfo.getDepartmentName(Util.null2String(rs.getString("c_compile_dept")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    typeid = Util.getIntValue(rs.getString("typeid"));
                    newXlsPath = Util.null2String(rs.getString("newexcel_path")) + fileName + ".xls";
                    LogUtil.debugLog("==签报单保存路径==>" + newXlsPath);
                    dataMap.put("object_name", Util.null2String(rs.getString("object_name")));
                    dataMap.put("file_path", Util.null2String(rs.getString("file_path")).replace("/", "\\"));
                    dataMap.put("file_format", Util.null2String(rs.getString("file_format")));
                    dataMap.put("c_chinese_title", Util.null2String(rs.getString("c_chinese_title")));
                    //发文类型数据库存的值
                    // String fwlx = Util.null2String(rs.getString("c_doc_type"));
                    String requestid = Util.null2String(rs.getString("requestid"));
                    //发文类型数据库的字段名
                    // String columName = getSettingColName(typeid + "", "gw_fwlx");
                    dataMap.put("newexcel_path", newXlsPath);
                    dataMap.put("c_doc_type", "");
                    dataMap.put("c_compile_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept"))));
                    dataMap.put("c_compile_date", Util.null2String(rs.getString("c_compile_date")));
                    dataMap.put("c_compiler", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_compiler"))));
                    dataMap.put("c_issue_date", Util.null2String(rs.getString("c_issue_date")));
                    dataMap.put("c_issue_num", Util.null2String(rs.getString("c_issue_num")));
                    dataMap.put("c_issue_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_issue_dept"))));
                    String c_target_deptName = WorkflowUtil.getDepartmentname(Util.null2String((rs.getString("c_target_dept"))));
                    dataMap.put("c_target_dept", Util.null2String(c_target_deptName));
                    dataMap.put("c_page_counts", Util.null2String(rs.getString("c_page_counts")));
                    dataMap.put("c_archive_date", Util.null2String(rs.getString("c_archive_date")));
                    dataMap.put("c_archive_org", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_archive_org"))));
                    dataMap.put("c_archive_owner", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_archive_owner"))));
                    dataMap.put("c_inner_sequence", Util.null2String(rs.getString("c_inner_sequence")));
                    dataList.add(dataMap);
                    hasData = true;
                }
            } else if (i == 11) {
                //服务采购四个
                LogUtil.debugLog("==查询服务采购excel信息所需==>" + sql);
                while (rs.next()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    oldXlsxPath = Util.null2String(rs.getString("excelmode_path")) + 11 + ".xls";
                    LogUtil.debugLog("==服务采购模板路径==>" + oldXlsxPath);
                    String fileName = "服务采购";
                    DepartmentComInfo deptinfo = new DepartmentComInfo();
                    String departmentName = "";
                    try {
                        departmentName = deptinfo.getDepartmentName(Util.null2String(rs.getString("c_compile_dept")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    typeid = Util.getIntValue(rs.getString("typeid"));
                    newXlsPath = Util.null2String(rs.getString("newexcel_path")) + fileName + ".xls";
                    dataMap.put("object_name", Util.null2String(rs.getString("object_name")));
                    dataMap.put("file_path", Util.null2String(rs.getString("file_path")).replace("/", "\\"));
                    dataMap.put("file_format", Util.null2String(rs.getString("file_format")));
                    dataMap.put("newexcel_path", newXlsPath);
                    dataMap.put("c_chinese_title", Util.null2String(rs.getString("c_chinese_title")));
                    //发文类型数据库存的值
                    //String fwlx = Util.null2String( rs.getString("c_doc_type"));
                    String requestid = Util.null2String(rs.getString("requestid"));
                    //发文类型数据库的字段名
                    //String columName = getSettingColName(typeid + "", "gw_fwlx");
                    //发文类型的fieldid
                    //String fieldId = WorkflowUtil.getFieldId(requestid, columName);
                    //发文类型中文名称
                    //String c_doc_type_CH = WorkflowUtil.getSelectChinesType(fwlx, fieldId);
                    dataMap.put("c_doc_type", "");
                    dataMap.put("c_compile_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept"))));
                    dataMap.put("c_compile_date", Util.null2String(rs.getString("c_compile_date")));
                    dataMap.put("c_compiler", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_compiler"))));
                    dataMap.put("c_issue_date", Util.null2String(rs.getString("c_issue_date")));
                    dataMap.put("c_issue_num", Util.null2String(rs.getString("c_issue_num")));
                    dataMap.put("c_issue_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_issue_dept"))));
                    String c_target_deptName = WorkflowUtil.getDepartmentname(Util.null2String((rs.getString("c_target_dept"))));
                    dataMap.put("c_target_dept", Util.null2String(c_target_deptName));
                    dataMap.put("c_page_counts", Util.null2String(rs.getString("c_page_counts")));
                    dataMap.put("c_archive_date", Util.null2String(rs.getString("c_archive_date")));
                    dataMap.put("c_archive_org", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_archive_org"))));
                    dataMap.put("c_archive_owner", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_archive_owner"))));
                    dataMap.put("c_inner_sequence", Util.null2String(rs.getString("c_inner_sequence")));
                    dataList.add(dataMap);
                    hasData = true;
                }
            } else if (i == 12) {
                //moc
                LogUtil.debugLog("==查询MOC==>" + sql);
                while (rs.next()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    oldXlsxPath = Util.null2String(rs.getString("excelmode_path")) + 18 + ".xls";
                    String fileName = "MOC";
                    DepartmentComInfo deptinfo = new DepartmentComInfo();
                    String departmentName = "";
                    try {
                        departmentName = deptinfo.getDepartmentName(Util.null2String(rs.getString("c_compile_dept")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    typeid = Util.getIntValue(rs.getString("typeid"));
                    newXlsPath = Util.null2String(rs.getString("newexcel_path")) + fileName + ".xls";
                    LogUtil.debugLog("==MOC管理保存路径==>" + newXlsPath);
                    dataMap.put("object_name", Util.null2String(rs.getString("object_name")));
                    dataMap.put("file_path", Util.null2String(rs.getString("file_path")).replace("/", "\\"));
                    dataMap.put("file_format", Util.null2String(rs.getString("file_format")));
                    dataMap.put("c_chinese_title", Util.null2String(rs.getString("c_chinese_title")));
                    //发文类型数据库存的值
                    //String fwlx = Util.null2String(rs.getString("c_doc_type"));
                    String requestid = Util.null2String(rs.getString("requestid"));
                    //发文类型数据库的字段名
                    //String columName = getSettingColName(typeid + "", "gw_fwlx");
                    //发文类型的fieldid
                    dataMap.put("newexcel_path", newXlsPath);
                    //String fieldId = WorkflowUtil.getFieldId(requestid, columName);
                    //发文类型中文名称
                    //String c_doc_type_CH = WorkflowUtil.getSelectChinesType(fwlx, fieldId);
                    dataMap.put("c_doc_type", "");
                    dataMap.put("c_compile_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept"))));
                    dataMap.put("c_compile_date", Util.null2String(rs.getString("c_compile_date")));
                    dataMap.put("c_compiler", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_compiler"))));
                    dataMap.put("c_issue_date", Util.null2String(rs.getString("c_issue_date")));
                    dataMap.put("c_issue_num", Util.null2String(rs.getString("c_issue_num")));
                    dataMap.put("c_issue_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_issue_dept"))));
                    String c_target_deptName = WorkflowUtil.getDepartmentname(Util.null2String((rs.getString("c_target_dept"))));
                    dataMap.put("c_target_dept", Util.null2String(c_target_deptName));
                    dataMap.put("c_page_counts", Util.null2String(rs.getString("c_page_counts")));
                    dataMap.put("c_archive_date", Util.null2String(rs.getString("c_archive_date")));
                    dataMap.put("c_archive_org", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_archive_org"))));
                    dataMap.put("c_archive_owner", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_archive_owner"))));
                    dataMap.put("c_inner_sequence", Util.null2String(rs.getString("c_inner_sequence")));
                    dataList.add(dataMap);
                    hasData = true;
                }
            } else if (i == 13) {
                LogUtil.debugLog("==查询授权信息所需==>" + sql);
                while (rs.next()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    oldXlsxPath = Util.null2String(rs.getString("excelmode_path")) + 19 + ".xls";
                    String fileName = "授权管理";
                    DepartmentComInfo deptinfo = new DepartmentComInfo();
                    String departmentName = "";
                    try {
                        departmentName = deptinfo.getDepartmentName(Util.null2String(rs.getString("c_compile_dept")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    typeid = Util.getIntValue(rs.getString("typeid"));
                    newXlsPath = Util.null2String(rs.getString("newexcel_path")) + fileName + ".xls";
                    LogUtil.debugLog("==PC授权管理保存路径==>" + newXlsPath);
                    dataMap.put("object_name", Util.null2String(rs.getString("object_name")));
                    dataMap.put("file_path", Util.null2String(rs.getString("file_path")).replace("/", "\\"));
                    dataMap.put("file_format", Util.null2String(rs.getString("file_format")));
                    dataMap.put("c_chinese_title", Util.null2String(rs.getString("c_chinese_title")));
                    //发文类型数据库存的值
                    //String fwlx = Util.null2String(rs.getString("c_doc_type"));
                    dataMap.put("newexcel_path", newXlsPath);
                    String requestid = Util.null2String(rs.getString("requestid"));
                    //发文类型数据库的字段名
                    //String columName = getSettingColName(typeid + "", "gw_fwlx");
                    //发文类型的fieldid
                    //String fieldId = WorkflowUtil.getFieldId(requestid, columName);
                    //发文类型中文名称
                    //String c_doc_type_CH = WorkflowUtil.getSelectChinesType(fwlx, fieldId);
                    dataMap.put("c_doc_type", "");
                    dataMap.put("c_compile_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept"))));
                    dataMap.put("c_compile_date", Util.null2String(rs.getString("c_compile_date")));
                    dataMap.put("c_compiler", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_compiler"))));
                    dataMap.put("c_issue_date", Util.null2String(rs.getString("c_issue_date")));
                    dataMap.put("c_issue_num", Util.null2String(rs.getString("c_issue_num")));
                    dataMap.put("c_issue_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_issue_dept"))));
                    String c_target_deptName = WorkflowUtil.getDepartmentname(Util.null2String((rs.getString("c_target_dept"))));
                    dataMap.put("c_target_dept", Util.null2String(c_target_deptName));
                    dataMap.put("c_page_counts", Util.null2String(rs.getString("c_page_counts")));
                    dataMap.put("c_archive_date", Util.null2String(rs.getString("c_archive_date")));
                    dataMap.put("c_archive_org", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_archive_org"))));
                    dataMap.put("c_archive_owner", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_archive_owner"))));
                    dataMap.put("c_inner_sequence", Util.null2String(rs.getString("c_inner_sequence")));
                    dataList.add(dataMap);
                    hasData = true;
                }
            }else if (i == 22) {
                LogUtil.debugLog("==查询授权信息所需==>" + sql);
                while (rs.next()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    oldXlsxPath = Util.null2String(rs.getString("excelmode_path")) + 31  + ".xls";
                    String fileName = "PC授权管理";
                    DepartmentComInfo deptinfo = new DepartmentComInfo();
                    String departmentName = "";
                    try {
                        departmentName = deptinfo.getDepartmentName(Util.null2String(rs.getString("c_compile_dept")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    typeid = Util.getIntValue(rs.getString("typeid"));
                    newXlsPath = Util.null2String(rs.getString("newexcel_path")) + fileName + ".xls";
                    dataMap.put("newexcel_path", newXlsPath);
                    LogUtil.debugLog("==授权管理保存路径==>" + newXlsPath);
                    dataMap.put("object_name", Util.null2String(rs.getString("object_name")));
                    dataMap.put("file_path", Util.null2String(rs.getString("file_path")).replace("/", "\\"));
                    dataMap.put("file_format", Util.null2String(rs.getString("file_format")));
                    dataMap.put("c_chinese_title", Util.null2String(rs.getString("c_chinese_title")));
                    //发文类型数据库存的值
                    //String fwlx = Util.null2String(rs.getString("c_doc_type"));
                    String requestid = Util.null2String(rs.getString("requestid"));
                    //发文类型数据库的字段名
                    //String columName = getSettingColName(typeid + "", "gw_fwlx");
                    //发文类型的fieldid
                    //String fieldId = WorkflowUtil.getFieldId(requestid, columName);
                    //发文类型中文名称
                    //String c_doc_type_CH = WorkflowUtil.getSelectChinesType(fwlx, fieldId);
                    dataMap.put("c_doc_type", "");
                    dataMap.put("c_compile_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept"))));
                    dataMap.put("c_compile_date", Util.null2String(rs.getString("c_compile_date")));
                    dataMap.put("c_compiler", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_compiler"))));
                    dataMap.put("c_issue_date", Util.null2String(rs.getString("c_issue_date")));
                    dataMap.put("c_issue_num", Util.null2String(rs.getString("c_issue_num")));
                    dataMap.put("c_issue_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_issue_dept"))));
                    String c_target_deptName = WorkflowUtil.getDepartmentname(Util.null2String((rs.getString("c_target_dept"))));
                    dataMap.put("c_target_dept", Util.null2String(c_target_deptName));
                    dataMap.put("c_page_counts", Util.null2String(rs.getString("c_page_counts")));
                    dataMap.put("c_archive_date", Util.null2String(rs.getString("c_archive_date")));
                    dataMap.put("c_archive_org", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_archive_org"))));
                    dataMap.put("c_archive_owner", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_archive_owner"))));
                    dataMap.put("c_inner_sequence", Util.null2String(rs.getString("c_inner_sequence")));
                    dataList.add(dataMap);
                    hasData = true;
                }
            } else if (i == 15) {
                //公司部门发文、会议纪要、发函
                LogUtil.debugLog("==查询公司部门发文、会议纪要、发函信息所需==>" + sql);
                while (rs.next()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    oldXlsxPath = Util.null2String(rs.getString("excelmode_path")) + 22 + ".xls";
                    String fileName = "公司发文";
                    String departmentName = "";
                    try {
                        departmentName = WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    typeid = Util.getIntValue(rs.getString("typeid"));
                    newXlsPath = Util.null2String(rs.getString("newexcel_path")) + fileName + ".xls";
                    LogUtil.debugLog("==" + fileName + "保存路径==>" + newXlsPath);
                    dataMap.put("newexcel_path", newXlsPath);
                    dataMap.put("object_name", Util.null2String(rs.getString("object_name")));
                    dataMap.put("file_path", Util.null2String(rs.getString("file_path")).replace("/", "\\"));
                    dataMap.put("file_format", Util.null2String(rs.getString("file_format")));
                    dataMap.put("c_chinese_title", Util.null2String(rs.getString("c_chinese_title")));
                    dataMap.put("c_english_title", Util.null2String(rs.getString("c_english_title")));
                    //发文类型数据库存的值
                    String fwlx = Util.null2String(rs.getString("c_doc_type"));
                    String requestid = Util.null2String(rs.getString("requestid"));
                    //发文类型数据库的字段名
                    String columName = getSettingColName(typeid + "", "gw_fwlx");
                    //发文类型的fieldid
                    String fieldId = WorkflowUtil.getFieldId(requestid, columName);
                    //发文类型中文名称
                    String c_doc_type_CH = WorkflowUtil.getSelectChinesType(fwlx, fieldId);
                    dataMap.put("c_doc_type", Util.null2String(c_doc_type_CH));
                    dataMap.put("c_compile_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept"))));
                    dataMap.put("c_compile_date", Util.null2String(rs.getString("c_compile_date")));
                    dataMap.put("c_compiler", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_compiler"))));
                    dataMap.put("c_issue_date", Util.null2String(rs.getString("c_issue_date")));
                    dataMap.put("c_issue_num", Util.null2String(rs.getString("c_issue_num")));
                    dataMap.put("c_issue_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_issue_dept"))));
                    String c_target_deptName = WorkflowUtil.getDepartmentname(Util.null2String((rs.getString("c_target_dept"))));
                    dataMap.put("c_target_dept", Util.null2String(c_target_deptName));
                    dataMap.put("c_page_counts", Util.null2String(rs.getString("c_page_counts")));
                    dataMap.put("c_archive_date", Util.null2String(rs.getString("c_archive_date")));
                    dataMap.put("c_archive_org", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_archive_org"))));
                    dataMap.put("c_archive_owner", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_archive_owner"))));
                    dataMap.put("c_inner_sequence", Util.null2String(rs.getString("c_inner_sequence")));
                    dataList.add(dataMap);
                    hasData = true;
                }
            }else if (i == 25) {
                //公司部门发文、会议纪要、发函
                LogUtil.debugLog("==查询公司部门发文、会议纪要、发函信息所需==>" + sql);
                while (rs.next()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    oldXlsxPath = Util.null2String(rs.getString("excelmode_path")) + 34 + ".xls";
                    String fileName = "项目部发文";
                    String departmentName = "";
                    try {
                        departmentName = WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    typeid = Util.getIntValue(rs.getString("typeid"));
                    newXlsPath = Util.null2String(rs.getString("newexcel_path")) + fileName + ".xls";
                    LogUtil.debugLog("==" + fileName + "保存路径==>" + newXlsPath);
                    dataMap.put("object_name", Util.null2String(rs.getString("object_name")));
                    dataMap.put("file_path", Util.null2String(rs.getString("file_path")).replace("/", "\\"));
                    dataMap.put("file_format", Util.null2String(rs.getString("file_format")));
                    dataMap.put("c_chinese_title", Util.null2String(rs.getString("c_chinese_title")));
                    dataMap.put("c_english_title", Util.null2String(rs.getString("c_english_title")));
                    //发文类型数据库存的值
                    String fwlx = Util.null2String(rs.getString("c_doc_type"));
                    String requestid = Util.null2String(rs.getString("requestid"));
                    //发文类型数据库的字段名
                    String columName = getSettingColName(typeid + "", "gw_fwlx");
                    //发文类型的fieldid
                    String fieldId = WorkflowUtil.getFieldId(requestid, columName);
                    dataMap.put("newexcel_path", newXlsPath);
                    //发文类型中文名称
                    String c_doc_type_CH = WorkflowUtil.getSelectChinesType(fwlx, fieldId);
                    dataMap.put("c_doc_type", Util.null2String(c_doc_type_CH));
                    dataMap.put("c_compile_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept"))));
                    dataMap.put("c_compile_date", Util.null2String(rs.getString("c_compile_date")));
                    dataMap.put("c_compiler", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_compiler"))));
                    dataMap.put("c_issue_date", Util.null2String(rs.getString("c_issue_date")));
                    dataMap.put("c_issue_num", Util.null2String(rs.getString("c_issue_num")));
                    dataMap.put("c_issue_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_issue_dept"))));
                    String c_target_deptName = WorkflowUtil.getDepartmentname(Util.null2String((rs.getString("c_target_dept"))));
                    dataMap.put("c_target_dept", Util.null2String(c_target_deptName));
                    dataMap.put("c_page_counts", Util.null2String(rs.getString("c_page_counts")));
                    dataMap.put("c_archive_date", Util.null2String(rs.getString("c_archive_date")));
                    dataMap.put("c_archive_org", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_archive_org"))));
                    dataMap.put("c_archive_owner", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_archive_owner"))));
                    dataMap.put("c_inner_sequence", Util.null2String(rs.getString("c_inner_sequence")));
                    dataList.add(dataMap);
                    hasData = true;
                }
            } else if (i == 16) {
                while (rs.next()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    oldXlsxPath = Util.null2String(rs.getString("excelmode_path")) + 23 + ".xls";
                    String fileName = "公司发函";
                    DepartmentComInfo deptinfo = new DepartmentComInfo();
                    String departmentName = "";
                    try {
                        departmentName = deptinfo.getDepartmentName(Util.null2String(rs.getString("c_compile_dept")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    typeid = Util.getIntValue(rs.getString("typeid"));
                    newXlsPath = Util.null2String(rs.getString("newexcel_path")) + fileName + ".xls";
                    LogUtil.debugLog("==" + fileName + "保存路径==>" + newXlsPath);
                    dataMap.put("newexcel_path", newXlsPath);
                    dataMap.put("object_name", Util.null2String(rs.getString("object_name")));
                    dataMap.put("file_path", Util.null2String(rs.getString("file_path")).replace("/", "\\"));
                    dataMap.put("file_format", Util.null2String(rs.getString("file_format")));
                    dataMap.put("c_chinese_title", Util.null2String(rs.getString("c_chinese_title")));
                    //发文类型数据库存的值
                    String fwlx = Util.null2String(rs.getString("c_doc_type"));
                    String requestid = Util.null2String(rs.getString("requestid"));
                    //发文类型数据库的字段名
                    String columName = getSettingColName(typeid + "", "gw_fwlx");
                    //发文类型的fieldid
                    String fieldId = WorkflowUtil.getFieldId(requestid, columName);
                    //发文类型中文名称
                    String c_doc_type_CH = WorkflowUtil.getSelectChinesType(fwlx, fieldId);
                    dataMap.put("c_doc_type", Util.null2String(c_doc_type_CH));
                    dataMap.put("c_compile_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept"))));
                    dataMap.put("c_compile_date", Util.null2String(rs.getString("c_compile_date")));
                    dataMap.put("c_compiler", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_compiler"))));
                    dataMap.put("c_issue_date", Util.null2String(rs.getString("c_issue_date")));
                    dataMap.put("c_issue_num", Util.null2String(rs.getString("c_issue_num")));
                    dataMap.put("c_issue_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_issue_dept"))));
                    String c_target_deptName = WorkflowUtil.getDepartmentname(Util.null2String((rs.getString("c_target_dept"))));
                    dataMap.put("c_target_dept", Util.null2String(c_target_deptName));
                    dataMap.put("c_page_counts", Util.null2String(rs.getString("c_page_counts")));
                    dataMap.put("c_archive_date", Util.null2String(rs.getString("c_archive_date")));
                    dataMap.put("c_archive_org", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_archive_org"))));
                    dataMap.put("c_archive_owner", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_archive_owner"))));
                    dataMap.put("c_inner_sequence", Util.null2String(rs.getString("c_inner_sequence")));
                    dataList.add(dataMap);
                    hasData = true;
                }
            }else if (i == 26) {
                while (rs.next()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    oldXlsxPath = Util.null2String(rs.getString("excelmode_path")) + 35 + ".xls";
                    String fileName = "项目部发函";
                    DepartmentComInfo deptinfo = new DepartmentComInfo();
                    String departmentName = "";
                    try {
                        departmentName = deptinfo.getDepartmentName(Util.null2String(rs.getString("c_compile_dept")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    typeid = Util.getIntValue(rs.getString("typeid"));
                    newXlsPath = Util.null2String(rs.getString("newexcel_path")) + fileName + ".xls";
                    LogUtil.debugLog("==" + fileName + "保存路径==>" + newXlsPath);
                    dataMap.put("object_name", Util.null2String(rs.getString("object_name")));
                    dataMap.put("file_path", Util.null2String(rs.getString("file_path")).replace("/", "\\"));
                    dataMap.put("file_format", Util.null2String(rs.getString("file_format")));
                    dataMap.put("newexcel_path", newXlsPath);
                    dataMap.put("c_chinese_title", Util.null2String(rs.getString("c_chinese_title")));
                    //发文类型数据库存的值
                    String fwlx = Util.null2String(rs.getString("c_doc_type"));
                    String requestid = Util.null2String(rs.getString("requestid"));
                    //发文类型数据库的字段名
                    String columName = getSettingColName(typeid + "", "gw_fwlx");
                    //发文类型的fieldid
                    String fieldId = WorkflowUtil.getFieldId(requestid, columName);
                    //发文类型中文名称
                    String c_doc_type_CH = WorkflowUtil.getSelectChinesType(fwlx, fieldId);
                    dataMap.put("c_doc_type", Util.null2String(c_doc_type_CH));
                    dataMap.put("c_compile_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept"))));
                    dataMap.put("c_compile_date", Util.null2String(rs.getString("c_compile_date")));
                    dataMap.put("c_compiler", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_compiler"))));
                    dataMap.put("c_issue_date", Util.null2String(rs.getString("c_issue_date")));
                    dataMap.put("c_issue_num", Util.null2String(rs.getString("c_issue_num")));
                    dataMap.put("c_issue_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_issue_dept"))));
                    String c_target_deptName = WorkflowUtil.getDepartmentname(Util.null2String((rs.getString("c_target_dept"))));
                    dataMap.put("c_target_dept", Util.null2String(c_target_deptName));
                    dataMap.put("c_page_counts", Util.null2String(rs.getString("c_page_counts")));
                    dataMap.put("c_archive_date", Util.null2String(rs.getString("c_archive_date")));
                    dataMap.put("c_archive_org", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_archive_org"))));
                    dataMap.put("c_archive_owner", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_archive_owner"))));
                    dataMap.put("c_inner_sequence", Util.null2String(rs.getString("c_inner_sequence")));
                    dataList.add(dataMap);
                    hasData = true;
                }
            } else if (i == 17) {
                while (rs.next()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    oldXlsxPath = Util.null2String(rs.getString("excelmode_path")) + 24 + ".xls";
                    String fileName = "公司会议纪要";
                    DepartmentComInfo deptinfo = new DepartmentComInfo();
                    String departmentName = "";
                    try {
                        departmentName = deptinfo.getDepartmentName(Util.null2String(rs.getString("c_compile_dept")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    typeid = Util.getIntValue(rs.getString("typeid"));
                    newXlsPath = Util.null2String(rs.getString("newexcel_path")) + fileName + ".xls";
                    LogUtil.debugLog("==" + fileName + "保存路径==>" + newXlsPath);
                    dataMap.put("newexcel_path", newXlsPath);
                    dataMap.put("object_name", Util.null2String(rs.getString("object_name")));
                    dataMap.put("file_path", Util.null2String(rs.getString("file_path")).replace("/", "\\"));
                    dataMap.put("file_format", Util.null2String(rs.getString("file_format")));
                    dataMap.put("c_chinese_title", Util.null2String(rs.getString("c_chinese_title")));
                    //发文类型数据库存的值
                    String fwlx = Util.null2String(rs.getString("c_doc_type"));
                    String requestid = Util.null2String(rs.getString("requestid"));
                    //发文类型数据库的字段名
                    String columName = getSettingColName(typeid + "", "gw_fwlx");
                    //发文类型的fieldid
                    String fieldId = WorkflowUtil.getFieldId(requestid, columName);
                    //发文类型中文名称
                    String c_doc_type_CH = WorkflowUtil.getSelectChinesType(fwlx, fieldId);
                    dataMap.put("c_doc_type", Util.null2String(c_doc_type_CH));
                    dataMap.put("c_compile_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept"))));
                    dataMap.put("c_compile_date", Util.null2String(rs.getString("c_compile_date")));
                    dataMap.put("c_compiler", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_compiler"))));
                    dataMap.put("c_issue_date", Util.null2String(rs.getString("c_issue_date")));
                    dataMap.put("c_issue_num", Util.null2String(rs.getString("c_issue_num")));
                    dataMap.put("c_issue_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_issue_dept"))));
                    String c_target_deptName = WorkflowUtil.getDepartmentname(Util.null2String((rs.getString("c_target_dept"))));
                    dataMap.put("c_target_dept", Util.null2String(c_target_deptName));
                    dataMap.put("c_page_counts", Util.null2String(rs.getString("c_page_counts")));
                    dataMap.put("c_archive_date", Util.null2String(rs.getString("c_archive_date")));
                    dataMap.put("c_archive_org", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_archive_org"))));
                    dataMap.put("c_archive_owner", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_archive_owner"))));
                    dataMap.put("c_inner_sequence", Util.null2String(rs.getString("c_inner_sequence")));
                    dataList.add(dataMap);
                    hasData = true;
                }
            }else if (i == 27) {
                while (rs.next()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    oldXlsxPath = Util.null2String(rs.getString("excelmode_path")) + 36 + ".xls";

                    DepartmentComInfo deptinfo = new DepartmentComInfo();
                    String departmentName = "";
                    try {
                        departmentName = deptinfo.getDepartmentName(Util.null2String(rs.getString("c_compile_dept")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String fileName = "项目部会议纪要";
                    typeid = Util.getIntValue(rs.getString("typeid"));
                    newXlsPath = Util.null2String(rs.getString("newexcel_path")) + fileName + ".xls";
                    LogUtil.debugLog("==" + fileName + "保存路径==>" + newXlsPath);
                    dataMap.put("object_name", Util.null2String(rs.getString("object_name")));
                    dataMap.put("file_path", Util.null2String(rs.getString("file_path")).replace("/", "\\"));
                    dataMap.put("file_format", Util.null2String(rs.getString("file_format")));
                    dataMap.put("c_chinese_title", Util.null2String(rs.getString("c_chinese_title")));
                    dataMap.put("newexcel_path", newXlsPath);
                    //发文类型数据库存的值
                    String fwlx = Util.null2String(rs.getString("c_doc_type"));
                    String requestid = Util.null2String(rs.getString("requestid"));
                    //发文类型数据库的字段名
                    String columName = getSettingColName(typeid + "", "gw_fwlx");
                    //发文类型的fieldid
                    String fieldId = WorkflowUtil.getFieldId(requestid, columName);
                    //发文类型中文名称
                    String c_doc_type_CH = WorkflowUtil.getSelectChinesType(fwlx, fieldId);
                    dataMap.put("c_doc_type", Util.null2String(c_doc_type_CH));
                    dataMap.put("c_compile_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept"))));
                    dataMap.put("c_compile_date", Util.null2String(rs.getString("c_compile_date")));
                    dataMap.put("c_compiler", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_compiler"))));
                    dataMap.put("c_issue_date", Util.null2String(rs.getString("c_issue_date")));
                    dataMap.put("c_issue_num", Util.null2String(rs.getString("c_issue_num")));
                    dataMap.put("c_issue_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_issue_dept"))));
                    String c_target_deptName = WorkflowUtil.getDepartmentname(Util.null2String((rs.getString("c_target_dept"))));
                    dataMap.put("c_target_dept", Util.null2String(c_target_deptName));
                    dataMap.put("c_page_counts", Util.null2String(rs.getString("c_page_counts")));
                    dataMap.put("c_archive_date", Util.null2String(rs.getString("c_archive_date")));
                    dataMap.put("c_archive_org", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_archive_org"))));
                    dataMap.put("c_archive_owner", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_archive_owner"))));
                    dataMap.put("c_inner_sequence", Util.null2String(rs.getString("c_inner_sequence")));
                    dataList.add(dataMap);
                    hasData = true;
                }
            } else if (i == 18) {
                while (rs.next()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    oldXlsxPath = Util.null2String(rs.getString("excelmode_path")) + 25 + ".xls";
                    String fileName = "";
                    DepartmentComInfo deptinfo = new DepartmentComInfo();
                    String departmentName = "";
                    try {
                        departmentName = WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    typeid = Util.getIntValue(rs.getString("typeid"));
                    fileName = "部门发文_" + departmentName;
                    newXlsPath = Util.null2String(rs.getString("newexcel_path")) + fileName + ".xls";
                    LogUtil.debugLog("==" + fileName + "保存路径==>" + newXlsPath);
                    dataMap.put("newexcel_path", newXlsPath);
                    dataMap.put("object_name", Util.null2String(rs.getString("object_name")));
                    dataMap.put("file_path", Util.null2String(rs.getString("file_path")).replace("/", "\\"));
                    dataMap.put("file_format", Util.null2String(rs.getString("file_format")));
                    dataMap.put("c_english_title", Util.null2String(rs.getString("c_english_title")));
                    dataMap.put("c_chinese_title", Util.null2String(rs.getString("c_chinese_title")));
                    //发文类型数据库存的值
                    String fwlx = Util.null2String(rs.getString("c_doc_type"));
                    String requestid = Util.null2String(rs.getString("requestid"));
                    //发文类型数据库的字段名
                    String columName = getSettingColName(typeid + "", "gw_fwlx");
                    //发文类型的fieldid
                    String fieldId = WorkflowUtil.getFieldId(requestid, columName);
                    //发文类型中文名称
                    String c_doc_type_CH = WorkflowUtil.getSelectChinesType(fwlx, fieldId);
                    dataMap.put("c_doc_type", Util.null2String(c_doc_type_CH));
                    dataMap.put("c_compile_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept"))));
                    dataMap.put("c_compile_date", Util.null2String(rs.getString("c_compile_date")));
                    dataMap.put("c_compiler", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_compiler"))));
                    dataMap.put("c_issue_date", Util.null2String(rs.getString("c_issue_date")));
                    dataMap.put("c_issue_num", Util.null2String(rs.getString("c_issue_num")));
                    dataMap.put("c_issue_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_issue_dept"))));
                    String c_target_deptName = WorkflowUtil.getDepartmentname(Util.null2String((rs.getString("c_target_dept"))));
                    dataMap.put("c_target_dept", Util.null2String(c_target_deptName));
                    dataMap.put("c_page_counts", Util.null2String(rs.getString("c_page_counts")));
                    dataMap.put("c_archive_date", Util.null2String(rs.getString("c_archive_date")));
                    dataMap.put("c_archive_org", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_archive_org"))));
                    dataMap.put("c_archive_owner", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_archive_owner"))));
                    dataMap.put("c_inner_sequence", Util.null2String(rs.getString("c_inner_sequence")));
                    dataList.add(dataMap);
                    hasData = true;
                }
            }else if (i == 28) {
                while (rs.next()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    oldXlsxPath = Util.null2String(rs.getString("excelmode_path")) + 37 + ".xls";
                    String fileName = "";
                    DepartmentComInfo deptinfo = new DepartmentComInfo();
                    String departmentName = "";
                    try {
                        departmentName = WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    typeid = Util.getIntValue(rs.getString("typeid"));
                    fileName = "项目部部门发文_" + departmentName;
                    newXlsPath = Util.null2String(rs.getString("newexcel_path")) + fileName + ".xls";
                    dataMap.put("newexcel_path", newXlsPath);
                    LogUtil.debugLog("==" + fileName + "保存路径==>" + newXlsPath);
                    dataMap.put("object_name", Util.null2String(rs.getString("object_name")));
                    dataMap.put("file_path", Util.null2String(rs.getString("file_path")).replace("/", "\\"));
                    dataMap.put("file_format", Util.null2String(rs.getString("file_format")));
                    dataMap.put("c_english_title", Util.null2String(rs.getString("c_english_title")));
                    dataMap.put("c_chinese_title", Util.null2String(rs.getString("c_chinese_title")));
                    //发文类型数据库存的值
                    String fwlx = Util.null2String(rs.getString("c_doc_type"));
                    String requestid = Util.null2String(rs.getString("requestid"));
                    //发文类型数据库的字段名
                    String columName = getSettingColName(typeid + "", "gw_fwlx");
                    //发文类型的fieldid
                    String fieldId = WorkflowUtil.getFieldId(requestid, columName);
                    //发文类型中文名称
                    String c_doc_type_CH = WorkflowUtil.getSelectChinesType(fwlx, fieldId);
                    dataMap.put("c_doc_type", Util.null2String(c_doc_type_CH));
                    dataMap.put("c_compile_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept"))));
                    dataMap.put("c_compile_date", Util.null2String(rs.getString("c_compile_date")));
                    dataMap.put("c_compiler", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_compiler"))));
                    dataMap.put("c_issue_date", Util.null2String(rs.getString("c_issue_date")));
                    dataMap.put("c_issue_num", Util.null2String(rs.getString("c_issue_num")));
                    dataMap.put("c_issue_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_issue_dept"))));
                    String c_target_deptName = WorkflowUtil.getDepartmentname(Util.null2String((rs.getString("c_target_dept"))));
                    dataMap.put("c_target_dept", Util.null2String(c_target_deptName));
                    dataMap.put("c_page_counts", Util.null2String(rs.getString("c_page_counts")));
                    dataMap.put("c_archive_date", Util.null2String(rs.getString("c_archive_date")));
                    dataMap.put("c_archive_org", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_archive_org"))));
                    dataMap.put("c_archive_owner", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_archive_owner"))));
                    dataMap.put("c_inner_sequence", Util.null2String(rs.getString("c_inner_sequence")));
                    dataList.add(dataMap);
                    hasData = true;
                }
            } else if (i == 19) {
                while (rs.next()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    oldXlsxPath = Util.null2String(rs.getString("excelmode_path")) + 26 + ".xls";
                    String fileName = "";
                    DepartmentComInfo deptinfo = new DepartmentComInfo();
                    String departmentName = "";
                    try {
                        departmentName = WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    typeid = Util.getIntValue(rs.getString("typeid"));
                    fileName = "部门发函_" + departmentName;
                    newXlsPath = Util.null2String(rs.getString("newexcel_path")) + fileName + ".xls";
                    LogUtil.debugLog("==" + fileName + "保存路径==>" + newXlsPath);
                    dataMap.put("newexcel_path", newXlsPath);
                    dataMap.put("object_name", Util.null2String(rs.getString("object_name")));
                    dataMap.put("file_path", Util.null2String(rs.getString("file_path")).replace("/", "\\"));
                    dataMap.put("file_format", Util.null2String(rs.getString("file_format")));
                    dataMap.put("c_chinese_title", Util.null2String(rs.getString("c_chinese_title")));
                    //发文类型数据库存的值
                    String fwlx = Util.null2String(rs.getString("c_doc_type"));
                    String requestid = Util.null2String(rs.getString("requestid"));
                    //发文类型数据库的字段名
                    String columName = getSettingColName(typeid + "", "gw_fwlx");
                    //发文类型的fieldid
                    String fieldId = WorkflowUtil.getFieldId(requestid, columName);
                    //发文类型中文名称
                    String c_doc_type_CH = WorkflowUtil.getSelectChinesType(fwlx, fieldId);
                    dataMap.put("c_doc_type", Util.null2String(c_doc_type_CH));
                    dataMap.put("c_compile_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept"))));
                    dataMap.put("c_compile_date", Util.null2String(rs.getString("c_compile_date")));
                    dataMap.put("c_compiler", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_compiler"))));
                    dataMap.put("c_issue_date", Util.null2String(rs.getString("c_issue_date")));
                    dataMap.put("c_issue_num", Util.null2String(rs.getString("c_issue_num")));
                    dataMap.put("c_issue_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_issue_dept"))));
                    String c_target_deptName = WorkflowUtil.getDepartmentname(Util.null2String((rs.getString("c_target_dept"))));
                    dataMap.put("c_target_dept", Util.null2String(c_target_deptName));
                    dataMap.put("c_page_counts", Util.null2String(rs.getString("c_page_counts")));
                    dataMap.put("c_archive_date", Util.null2String(rs.getString("c_archive_date")));
                    dataMap.put("c_archive_org", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_archive_org"))));
                    dataMap.put("c_archive_owner", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_archive_owner"))));
                    dataMap.put("c_inner_sequence", Util.null2String(rs.getString("c_inner_sequence")));
                    dataList.add(dataMap);
                    hasData = true;
                }
            }else if (i == 29) {
                while (rs.next()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    oldXlsxPath = Util.null2String(rs.getString("excelmode_path")) + 38 + ".xls";
                    String fileName = "";
                    DepartmentComInfo deptinfo = new DepartmentComInfo();
                    String departmentName = "";
                    try {
                        departmentName = WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    typeid = Util.getIntValue(rs.getString("typeid"));
                    fileName = "项目部部门发函_" + departmentName;
                    newXlsPath = Util.null2String(rs.getString("newexcel_path")) + fileName + ".xls";
                    LogUtil.debugLog("==" + fileName + "保存路径==>" + newXlsPath);
                    dataMap.put("object_name", Util.null2String(rs.getString("object_name")));
                    dataMap.put("newexcel_path", newXlsPath);
                    dataMap.put("file_path", Util.null2String(rs.getString("file_path")).replace("/", "\\"));
                    dataMap.put("file_format", Util.null2String(rs.getString("file_format")));
                    dataMap.put("c_chinese_title", Util.null2String(rs.getString("c_chinese_title")));
                    //发文类型数据库存的值
                    String fwlx = Util.null2String(rs.getString("c_doc_type"));
                    String requestid = Util.null2String(rs.getString("requestid"));
                    //发文类型数据库的字段名
                    String columName = getSettingColName(typeid + "", "gw_fwlx");
                    //发文类型的fieldid
                    String fieldId = WorkflowUtil.getFieldId(requestid, columName);
                    //发文类型中文名称
                    String c_doc_type_CH = WorkflowUtil.getSelectChinesType(fwlx, fieldId);
                    dataMap.put("c_doc_type", Util.null2String(c_doc_type_CH));
                    dataMap.put("c_compile_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept"))));
                    dataMap.put("c_compile_date", Util.null2String(rs.getString("c_compile_date")));
                    dataMap.put("c_compiler", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_compiler"))));
                    dataMap.put("c_issue_date", Util.null2String(rs.getString("c_issue_date")));
                    dataMap.put("c_issue_num", Util.null2String(rs.getString("c_issue_num")));
                    dataMap.put("c_issue_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_issue_dept"))));
                    String c_target_deptName = WorkflowUtil.getDepartmentname(Util.null2String((rs.getString("c_target_dept"))));
                    dataMap.put("c_target_dept", Util.null2String(c_target_deptName));
                    dataMap.put("c_page_counts", Util.null2String(rs.getString("c_page_counts")));
                    dataMap.put("c_archive_date", Util.null2String(rs.getString("c_archive_date")));
                    dataMap.put("c_archive_org", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_archive_org"))));
                    dataMap.put("c_archive_owner", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_archive_owner"))));
                    dataMap.put("c_inner_sequence", Util.null2String(rs.getString("c_inner_sequence")));
                    dataList.add(dataMap);
                    hasData = true;
                }
            } else if (i == 20) {
                while (rs.next()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    oldXlsxPath = Util.null2String(rs.getString("excelmode_path")) + 27 + ".xls";
                    String fileName = "";
                    DepartmentComInfo deptinfo = new DepartmentComInfo();
                    String departmentName = "";
                    try {
                        departmentName = WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    typeid = Util.getIntValue(rs.getString("typeid"));
                    fileName = "部门会议纪要_" + departmentName;
                    newXlsPath = Util.null2String(rs.getString("newexcel_path")) + fileName + ".xls";
                    LogUtil.debugLog("==" + fileName + "保存路径==>" + newXlsPath);
                    dataMap.put("newexcel_path", newXlsPath);
                    dataMap.put("object_name", Util.null2String(rs.getString("object_name")));
                    dataMap.put("file_path", Util.null2String(rs.getString("file_path")).replace("/", "\\"));
                    dataMap.put("file_format", Util.null2String(rs.getString("file_format")));
                    dataMap.put("c_chinese_title", Util.null2String(rs.getString("c_chinese_title")));
                    //发文类型数据库存的值
                    String fwlx = Util.null2String(rs.getString("c_doc_type"));
                    String requestid = Util.null2String(rs.getString("requestid"));
                    //发文类型数据库的字段名
                    String columName = getSettingColName(typeid + "", "gw_fwlx");
                    //发文类型的fieldid
                    String fieldId = WorkflowUtil.getFieldId(requestid, columName);
                    //发文类型中文名称
                    String c_doc_type_CH = WorkflowUtil.getSelectChinesType(fwlx, fieldId);
                    dataMap.put("c_doc_type", Util.null2String(c_doc_type_CH));
                    dataMap.put("c_compile_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept"))));
                    dataMap.put("c_compile_date", Util.null2String(rs.getString("c_compile_date")));
                    dataMap.put("c_compiler", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_compiler"))));
                    dataMap.put("c_issue_date", Util.null2String(rs.getString("c_issue_date")));
                    dataMap.put("c_issue_num", Util.null2String(rs.getString("c_issue_num")));
                    dataMap.put("c_issue_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_issue_dept"))));
                    String c_target_deptName = WorkflowUtil.getDepartmentname(Util.null2String((rs.getString("c_target_dept"))));
                    dataMap.put("c_target_dept", Util.null2String(c_target_deptName));
                    dataMap.put("c_page_counts", Util.null2String(rs.getString("c_page_counts")));
                    dataMap.put("c_archive_date", Util.null2String(rs.getString("c_archive_date")));
                    dataMap.put("c_archive_org", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_archive_org"))));
                    dataMap.put("c_archive_owner", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_archive_owner"))));
                    dataMap.put("c_inner_sequence", Util.null2String(rs.getString("c_inner_sequence")));
                    dataList.add(dataMap);
                    hasData = true;
                }
                //PC合同签订变更流程
            } else if (i == 30) {
                while (rs.next()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    oldXlsxPath = Util.null2String(rs.getString("excelmode_path")) + 39 + ".xls";
                    String fileName = "";
                    DepartmentComInfo deptinfo = new DepartmentComInfo();
                    String departmentName = "";
                    try {
                        departmentName = WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    typeid = Util.getIntValue(rs.getString("typeid"));
                    fileName = "项目部部门会议纪要_" + departmentName;
                    newXlsPath = Util.null2String(rs.getString("newexcel_path")) + fileName + ".xls";
                    LogUtil.debugLog("==" + fileName + "保存路径==>" + newXlsPath);
                    dataMap.put("object_name", Util.null2String(rs.getString("object_name")));
                    dataMap.put("file_path", Util.null2String(rs.getString("file_path")).replace("/", "\\"));
                    dataMap.put("file_format", Util.null2String(rs.getString("file_format")));
                    dataMap.put("c_chinese_title", Util.null2String(rs.getString("c_chinese_title")));
                    //发文类型数据库存的值
                    String fwlx = Util.null2String(rs.getString("c_doc_type"));
                    String requestid = Util.null2String(rs.getString("requestid"));
                    //发文类型数据库的字段名
                    String columName = getSettingColName(typeid + "", "gw_fwlx");
                    //发文类型的fieldid
                    String fieldId = WorkflowUtil.getFieldId(requestid, columName);
                    //发文类型中文名称
                    String c_doc_type_CH = WorkflowUtil.getSelectChinesType(fwlx, fieldId);
                    dataMap.put("c_doc_type", Util.null2String(c_doc_type_CH));
                    dataMap.put("c_compile_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept"))));
                    dataMap.put("c_compile_date", Util.null2String(rs.getString("c_compile_date")));
                    dataMap.put("c_compiler", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_compiler"))));
                    dataMap.put("c_issue_date", Util.null2String(rs.getString("c_issue_date")));
                    dataMap.put("c_issue_num", Util.null2String(rs.getString("c_issue_num")));
                    dataMap.put("c_issue_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_issue_dept"))));
                    String c_target_deptName = WorkflowUtil.getDepartmentname(Util.null2String((rs.getString("c_target_dept"))));
                    dataMap.put("c_target_dept", Util.null2String(c_target_deptName));
                    dataMap.put("newexcel_path", newXlsPath);
                    dataMap.put("c_page_counts", Util.null2String(rs.getString("c_page_counts")));
                    dataMap.put("c_archive_date", Util.null2String(rs.getString("c_archive_date")));
                    dataMap.put("c_archive_org", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_archive_org"))));
                    dataMap.put("c_archive_owner", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_archive_owner"))));
                    dataMap.put("c_inner_sequence", Util.null2String(rs.getString("c_inner_sequence")));
                    dataList.add(dataMap);
                    hasData = true;
                }
                //PC合同签订变更流程
            }else if (i == 21) {

                while (rs.next()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    oldXlsxPath = Util.null2String(rs.getString("excelmode_path")) + 29 + ".xls";
                    LogUtil.debugLog("==合同模板路径==>" + oldXlsxPath);
                    newXlsPath = Util.null2String(rs.getString("newexcel_path")) + "PC合同" + ".xls";
                    LogUtil.debugLog("==合同签订保存路径==>" + newXlsPath);
                    String requestid = Util.null2String(rs.getString("requestid"));
                    typeid = Util.getIntValue(rs.getString("typeid"));
                    dataMap.put("object_name", Util.null2String(rs.getString("object_name")));
                    dataMap.put("file_path", Util.null2String(rs.getString("file_path")).replace("/", "\\").replace("/", "\\"));
                    dataMap.put("file_format", Util.null2String(rs.getString("file_format")));
                    dataMap.put("c_chinese_title", Util.null2String(rs.getString("c_chinese_title")));
                    dataMap.put("c_doc_code", Util.null2String(rs.getString("c_doc_code")));
                    //流程中的c_contract_type的数值
                    String c_contract_type = Util.null2String(rs.getString("c_contract_type"));
                    LogUtil.debugLog("c_contract_type:" + c_contract_type);
                    //合同类型的数据库字段
                    String columName = getSettingColName(typeid + "", "gw_fwlx");
                    //合同类型字段的页面fieldid
                    String fieldId = WorkflowUtil.getFieldId(requestid, columName);
                    //合同类型的中文名称
                    String c_contract_type_CH = WorkflowUtil.getSelectChinesType(c_contract_type, fieldId);
                    dataMap.put("c_contract_type", c_contract_type_CH);
                    dataMap.put("c_year", Util.null2String(rs.getString("c_year")));
                    dataMap.put("c_party_a", Util.null2String(rs.getString("c_party_a")));
                    dataMap.put("newexcel_path", newXlsPath);
                    dataMap.put("c_party_b", Util.null2String(rs.getString("c_party_b")));
                    String c_resp_dept = Util.null2String(rs.getString("c_resp_dept"));
                    String c_resp_dept_ZH = WorkflowUtil.getDepartmentname(c_resp_dept);
                    dataMap.put("c_resp_dept", c_resp_dept_ZH);
                    dataMap.put("c_page_counts", Util.null2String(rs.getString("c_page_counts")));
                    dataMap.put("c_archive_date", Util.null2String(rs.getString("c_archive_date")));
                    String hrmid = Util.null2String(rs.getString("c_archive_owne"));
                    String hrmName = ArchivesUtil.getChineseHrmName(hrmid);
                    dataMap.put("c_archive_owne", hrmName);
                    dataMap.put("c_inner_sequence", Util.null2String(rs.getString("c_inner_sequence")));
                    dataList.add(dataMap);
                    hasData = true;
                }
            }else if (i == 33){
                //服务采购四个
                LogUtil.debugLog("==查询物资采购excel信息所需==>" + sql);
                while (rs.next()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    oldXlsxPath = Util.null2String(rs.getString("excelmode_path")) + 11 + ".xls";
                    LogUtil.debugLog("==物资采购模板路径==>" + oldXlsxPath);
                    String fileName = "物资采购";
                    DepartmentComInfo deptinfo = new DepartmentComInfo();
                    String departmentName = "";
                    try {
                        departmentName = deptinfo.getDepartmentName(Util.null2String(rs.getString("c_compile_dept")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    typeid = Util.getIntValue(rs.getString("typeid"));
                    newXlsPath = Util.null2String(rs.getString("newexcel_path")) + fileName + ".xls";
                    dataMap.put("newexcel_path", newXlsPath);
                    dataMap.put("object_name", Util.null2String(rs.getString("object_name")));
                    dataMap.put("file_path", Util.null2String(rs.getString("file_path")).replace("/", "\\"));
                    dataMap.put("file_format", Util.null2String(rs.getString("file_format")));
                    dataMap.put("c_chinese_title", Util.null2String(rs.getString("c_chinese_title")));
                    //发文类型数据库存的值
                    //String fwlx = Util.null2String( rs.getString("c_doc_type"));
                    String requestid = Util.null2String(rs.getString("requestid"));
                    //发文类型数据库的字段名
                    //String columName = getSettingColName(typeid + "", "gw_fwlx");
                    //发文类型的fieldid
                    //String fieldId = WorkflowUtil.getFieldId(requestid, columName);
                    //发文类型中文名称
                    //String c_doc_type_CH = WorkflowUtil.getSelectChinesType(fwlx, fieldId);
                    dataMap.put("c_doc_type", "");
                    dataMap.put("c_compile_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept"))));
                    dataMap.put("c_compile_date", Util.null2String(rs.getString("c_compile_date")));
                    dataMap.put("c_compiler", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_compiler"))));
                    dataMap.put("c_issue_date", Util.null2String(rs.getString("c_issue_date")));
                    dataMap.put("c_issue_num", Util.null2String(rs.getString("c_issue_num")));
                    dataMap.put("c_issue_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_issue_dept"))));
                    String c_target_deptName = WorkflowUtil.getDepartmentname(Util.null2String((rs.getString("c_target_dept"))));
                    dataMap.put("c_target_dept", Util.null2String(c_target_deptName));
                    dataMap.put("c_page_counts", Util.null2String(rs.getString("c_page_counts")));
                    dataMap.put("c_archive_date", Util.null2String(rs.getString("c_archive_date")));
                    dataMap.put("c_archive_org", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_archive_org"))));
                    dataMap.put("c_archive_owner", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_archive_owner"))));
                    dataMap.put("c_inner_sequence", Util.null2String(rs.getString("c_inner_sequence")));
                    dataList.add(dataMap);
                    hasData = true;
                }
            }
            // 	DBN合同签订流程
            else if (i == 34) {
                while (rs.next()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    oldXlsxPath = Util.null2String(rs.getString("excelmode_path")) + 42 + ".xls";
                    LogUtil.debugLog("==DBN合同签订模板路径==>" + oldXlsxPath);
                    newXlsPath = Util.null2String(rs.getString("newexcel_path")) + "DBN合同签订" + ".xls";
                    LogUtil.debugLog("==DBN合同签订保存路径==>" + newXlsPath);
                    String requestid = Util.null2String(rs.getString("requestid"));
                    typeid = Util.getIntValue(rs.getString("typeid"));
                    dataMap.put("object_name", Util.null2String(rs.getString("object_name")));
                    dataMap.put("file_path", Util.null2String(rs.getString("file_path")).replace("/", "\\").replace("/", "\\"));
                    dataMap.put("file_format", Util.null2String(rs.getString("file_format")));
                    dataMap.put("c_chinese_title", Util.null2String(rs.getString("c_chinese_title")));
                    dataMap.put("c_doc_code", Util.null2String(rs.getString("c_doc_code")));
                    //流程中的c_contract_type的数值
                    String c_contract_type = Util.null2String(rs.getString("c_contract_type"));
                    LogUtil.debugLog("c_contract_type:" + c_contract_type);
                    //合同类型的数据库字段
                    String columName = getSettingColName(typeid + "", "gw_fwlx");
                    //合同类型字段的页面fieldid
                    String fieldId = WorkflowUtil.getFieldId(requestid, columName);
                    //合同类型的中文名称
                    String c_contract_type_CH = WorkflowUtil.getSelectChinesType(c_contract_type, fieldId);
                    dataMap.put("c_contract_type", c_contract_type_CH);
                    dataMap.put("c_year", Util.null2String(rs.getString("c_year")));
                    dataMap.put("c_party_a", Util.null2String(rs.getString("c_party_a")));
                    dataMap.put("newexcel_path", newXlsPath);
                    dataMap.put("c_party_b", Util.null2String(rs.getString("c_party_b")));
                    String c_resp_dept = Util.null2String(rs.getString("c_resp_dept"));
                    String c_resp_dept_ZH = WorkflowUtil.getDepartmentname(c_resp_dept);
                    dataMap.put("c_resp_dept", c_resp_dept_ZH);
                    dataMap.put("c_page_counts", Util.null2String(rs.getString("c_page_counts")));
                    dataMap.put("c_archive_date", Util.null2String(rs.getString("c_archive_date")));
                    String hrmid = Util.null2String(rs.getString("c_archive_owne"));
                    String hrmName = ArchivesUtil.getChineseHrmName(hrmid);
                    dataMap.put("c_archive_owne", hrmName);
                    dataMap.put("c_inner_sequence", Util.null2String(rs.getString("c_inner_sequence")));
                    dataList.add(dataMap);
                    hasData = true;
                }
            }
            // 	DBN项目部发文申请
            else if (i == 35) {
                while (rs.next()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    oldXlsxPath = Util.null2String(rs.getString("excelmode_path")) + 43 + ".xls";
                    LogUtil.debugLog("==DBN项目部发文路径==>" + oldXlsxPath);
                    String fileName = "DBN项目部发文";
                    DepartmentComInfo deptinfo = new DepartmentComInfo();
                    String departmentName = "";
                    try {
                        departmentName = deptinfo.getDepartmentName(Util.null2String(rs.getString("c_compile_dept")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    typeid = Util.getIntValue(rs.getString("typeid"));
                    newXlsPath = Util.null2String(rs.getString("newexcel_path")) + fileName + ".xls";
                    LogUtil.debugLog("==DBN项目部发文申请保存路径==>" + newXlsPath);
                    dataMap.put("object_name", Util.null2String(rs.getString("object_name")));
                    dataMap.put("file_path", Util.null2String(rs.getString("file_path")).replace("/", "\\"));
                    dataMap.put("file_format", Util.null2String(rs.getString("file_format")));
                    dataMap.put("c_chinese_title", Util.null2String(rs.getString("c_chinese_title")));
                    //发文类型数据库存的值
                    // String fwlx = Util.null2String(rs.getString("c_doc_type"));
                    String requestid = Util.null2String(rs.getString("requestid"));
                    //发文类型数据库的字段名
                    // String columName = getSettingColName(typeid + "", "gw_fwlx");
                    dataMap.put("newexcel_path", newXlsPath);
                    dataMap.put("c_doc_type", "");
                    dataMap.put("c_compile_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept"))));
                    dataMap.put("c_compile_date", Util.null2String(rs.getString("c_compile_date")));
                    dataMap.put("c_compiler", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_compiler"))));
                    dataMap.put("c_issue_date", Util.null2String(rs.getString("c_issue_date")));
                    dataMap.put("c_issue_num", Util.null2String(rs.getString("c_issue_num")));
                    dataMap.put("c_issue_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_issue_dept"))));
                    String c_target_deptName = WorkflowUtil.getDepartmentname(Util.null2String((rs.getString("c_target_dept"))));
                    dataMap.put("c_target_dept", Util.null2String(c_target_deptName));
                    dataMap.put("c_page_counts", Util.null2String(rs.getString("c_page_counts")));
                    dataMap.put("c_archive_date", Util.null2String(rs.getString("c_archive_date")));
                    dataMap.put("c_archive_org", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_archive_org"))));
                    dataMap.put("c_archive_owner", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_archive_owner"))));
                    dataMap.put("c_inner_sequence", Util.null2String(rs.getString("c_inner_sequence")));
                    dataList.add(dataMap);
                    hasData = true;
                }
            }
            // 	DBN项目部发函申请
            else if (i == 36) {
                while (rs.next()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    oldXlsxPath = Util.null2String(rs.getString("excelmode_path")) + 44 + ".xls";
                    LogUtil.debugLog("==DBN项目部发函路径==>" + oldXlsxPath);
                    String fileName = "DBN项目部发函";
                    DepartmentComInfo deptinfo = new DepartmentComInfo();
                    String departmentName = "";
                    try {
                        departmentName = deptinfo.getDepartmentName(Util.null2String(rs.getString("c_compile_dept")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    typeid = Util.getIntValue(rs.getString("typeid"));
                    newXlsPath = Util.null2String(rs.getString("newexcel_path")) + fileName + ".xls";
                    LogUtil.debugLog("==DBN项目部发函申请保存路径==>" + newXlsPath);
                    dataMap.put("object_name", Util.null2String(rs.getString("object_name")));
                    dataMap.put("file_path", Util.null2String(rs.getString("file_path")).replace("/", "\\"));
                    dataMap.put("file_format", Util.null2String(rs.getString("file_format")));
                    dataMap.put("c_chinese_title", Util.null2String(rs.getString("c_chinese_title")));
                    //发文类型数据库存的值
                    // String fwlx = Util.null2String(rs.getString("c_doc_type"));
                    String requestid = Util.null2String(rs.getString("requestid"));
                    //发文类型数据库的字段名
                    // String columName = getSettingColName(typeid + "", "gw_fwlx");
                    dataMap.put("newexcel_path", newXlsPath);
                    dataMap.put("c_doc_type", "");
                    dataMap.put("c_compile_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept"))));
                    dataMap.put("c_compile_date", Util.null2String(rs.getString("c_compile_date")));
                    dataMap.put("c_compiler", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_compiler"))));
                    dataMap.put("c_issue_date", Util.null2String(rs.getString("c_issue_date")));
                    dataMap.put("c_issue_num", Util.null2String(rs.getString("c_issue_num")));
                    dataMap.put("c_issue_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_issue_dept"))));
                    String c_target_deptName = WorkflowUtil.getDepartmentname(Util.null2String((rs.getString("c_target_dept"))));
                    dataMap.put("c_target_dept", Util.null2String(c_target_deptName));
                    dataMap.put("c_page_counts", Util.null2String(rs.getString("c_page_counts")));
                    dataMap.put("c_archive_date", Util.null2String(rs.getString("c_archive_date")));
                    dataMap.put("c_archive_org", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_archive_org"))));
                    dataMap.put("c_archive_owner", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_archive_owner"))));
                    dataMap.put("c_inner_sequence", Util.null2String(rs.getString("c_inner_sequence")));
                    dataList.add(dataMap);
                    hasData = true;
                }
            }
            // 	DBN项目部会议纪要申请
            else if (i == 37) {
                while (rs.next()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    oldXlsxPath = Util.null2String(rs.getString("excelmode_path")) + 45 + ".xls";
                    LogUtil.debugLog("==DBN项目部会议纪要路径==>" + oldXlsxPath);
                    String fileName = "DBN项目部会议纪";
                    DepartmentComInfo deptinfo = new DepartmentComInfo();
                    String departmentName = "";
                    try {
                        departmentName = deptinfo.getDepartmentName(Util.null2String(rs.getString("c_compile_dept")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    typeid = Util.getIntValue(rs.getString("typeid"));
                    newXlsPath = Util.null2String(rs.getString("newexcel_path")) + fileName + ".xls";
                    LogUtil.debugLog("==DBN项目部会议纪要申请保存路径==>" + newXlsPath);
                    dataMap.put("object_name", Util.null2String(rs.getString("object_name")));
                    dataMap.put("file_path", Util.null2String(rs.getString("file_path")).replace("/", "\\"));
                    dataMap.put("file_format", Util.null2String(rs.getString("file_format")));
                    dataMap.put("c_chinese_title", Util.null2String(rs.getString("c_chinese_title")));
                    //发文类型数据库存的值
                    // String fwlx = Util.null2String(rs.getString("c_doc_type"));
                    String requestid = Util.null2String(rs.getString("requestid"));
                    //发文类型数据库的字段名
                    // String columName = getSettingColName(typeid + "", "gw_fwlx");
                    dataMap.put("newexcel_path", newXlsPath);
                    dataMap.put("c_doc_type", "");
                    dataMap.put("c_compile_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept"))));
                    dataMap.put("c_compile_date", Util.null2String(rs.getString("c_compile_date")));
                    dataMap.put("c_compiler", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_compiler"))));
                    dataMap.put("c_issue_date", Util.null2String(rs.getString("c_issue_date")));
                    dataMap.put("c_issue_num", Util.null2String(rs.getString("c_issue_num")));
                    dataMap.put("c_issue_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_issue_dept"))));
                    String c_target_deptName = WorkflowUtil.getDepartmentname(Util.null2String((rs.getString("c_target_dept"))));
                    dataMap.put("c_target_dept", Util.null2String(c_target_deptName));
                    dataMap.put("c_page_counts", Util.null2String(rs.getString("c_page_counts")));
                    dataMap.put("c_archive_date", Util.null2String(rs.getString("c_archive_date")));
                    dataMap.put("c_archive_org", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_archive_org"))));
                    dataMap.put("c_archive_owner", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_archive_owner"))));
                    dataMap.put("c_inner_sequence", Util.null2String(rs.getString("c_inner_sequence")));
                    dataList.add(dataMap);
                    hasData = true;
                }
            }
            // 	DBN项目部部门会议纪要申请
            else if (i == 38) {
                while (rs.next()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    oldXlsxPath = Util.null2String(rs.getString("excelmode_path")) + 46 + ".xls";
                    LogUtil.debugLog("==DBN项目部部门会议纪要模板路径==>" + oldXlsxPath);
                    String fileName = "DBN项目部部门会议纪要";
                    DepartmentComInfo deptinfo = new DepartmentComInfo();
                    String departmentName = "";
                    try {
                        departmentName = deptinfo.getDepartmentName(Util.null2String(rs.getString("c_compile_dept")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    typeid = Util.getIntValue(rs.getString("typeid"));
                    newXlsPath = Util.null2String(rs.getString("newexcel_path")) + fileName + ".xls";
                    LogUtil.debugLog("==DBN项目部部门会议纪要申请保存路径==>" + newXlsPath);
                    dataMap.put("object_name", Util.null2String(rs.getString("object_name")));
                    dataMap.put("file_path", Util.null2String(rs.getString("file_path")).replace("/", "\\"));
                    dataMap.put("file_format", Util.null2String(rs.getString("file_format")));
                    dataMap.put("c_chinese_title", Util.null2String(rs.getString("c_chinese_title")));
                    //发文类型数据库存的值
                    // String fwlx = Util.null2String(rs.getString("c_doc_type"));
                    String requestid = Util.null2String(rs.getString("requestid"));
                    //发文类型数据库的字段名
                    // String columName = getSettingColName(typeid + "", "gw_fwlx");
                    dataMap.put("newexcel_path", newXlsPath);
                    dataMap.put("c_doc_type", "");
                    dataMap.put("c_compile_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept"))));
                    dataMap.put("c_compile_date", Util.null2String(rs.getString("c_compile_date")));
                    dataMap.put("c_compiler", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_compiler"))));
                    dataMap.put("c_issue_date", Util.null2String(rs.getString("c_issue_date")));
                    dataMap.put("c_issue_num", Util.null2String(rs.getString("c_issue_num")));
                    dataMap.put("c_issue_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_issue_dept"))));
                    String c_target_deptName = WorkflowUtil.getDepartmentname(Util.null2String((rs.getString("c_target_dept"))));
                    dataMap.put("c_target_dept", Util.null2String(c_target_deptName));
                    dataMap.put("c_page_counts", Util.null2String(rs.getString("c_page_counts")));
                    dataMap.put("c_archive_date", Util.null2String(rs.getString("c_archive_date")));
                    dataMap.put("c_archive_org", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_archive_org"))));
                    dataMap.put("c_archive_owner", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_archive_owner"))));
                    dataMap.put("c_inner_sequence", Util.null2String(rs.getString("c_inner_sequence")));
                    dataList.add(dataMap);
                    hasData = true;
                }
            }
            // DBN项目部签报单申请
            else if (i == 39) {
                while (rs.next()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    oldXlsxPath = Util.null2String(rs.getString("excelmode_path")) + 47 + ".xls";
                    LogUtil.debugLog("==DBN项目部签报单路径==>" + oldXlsxPath);
                    String fileName = "DBN项目部签报单";
                    DepartmentComInfo deptinfo = new DepartmentComInfo();
                    String departmentName = "";
                    try {
                        departmentName = deptinfo.getDepartmentName(Util.null2String(rs.getString("c_compile_dept")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    typeid = Util.getIntValue(rs.getString("typeid"));
                    newXlsPath = Util.null2String(rs.getString("newexcel_path")) + fileName + ".xls";
                    LogUtil.debugLog("==DBN项目部签报单申请保存路径==>" + newXlsPath);
                    dataMap.put("object_name", Util.null2String(rs.getString("object_name")));
                    dataMap.put("file_path", Util.null2String(rs.getString("file_path")).replace("/", "\\"));
                    dataMap.put("file_format", Util.null2String(rs.getString("file_format")));
                    dataMap.put("c_chinese_title", Util.null2String(rs.getString("c_chinese_title")));
                    //发文类型数据库存的值
                    // String fwlx = Util.null2String(rs.getString("c_doc_type"));
                    String requestid = Util.null2String(rs.getString("requestid"));
                    //发文类型数据库的字段名
                    // String columName = getSettingColName(typeid + "", "gw_fwlx");
                    dataMap.put("newexcel_path", newXlsPath);
                    dataMap.put("c_doc_type", "");
                    dataMap.put("c_compile_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept"))));
                    dataMap.put("c_compile_date", Util.null2String(rs.getString("c_compile_date")));
                    dataMap.put("c_compiler", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_compiler"))));
                    dataMap.put("c_issue_date", Util.null2String(rs.getString("c_issue_date")));
                    dataMap.put("c_issue_num", Util.null2String(rs.getString("c_issue_num")));
                    dataMap.put("c_issue_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_issue_dept"))));
                    String c_target_deptName = WorkflowUtil.getDepartmentname(Util.null2String((rs.getString("c_target_dept"))));
                    dataMap.put("c_target_dept", Util.null2String(c_target_deptName));
                    dataMap.put("c_page_counts", Util.null2String(rs.getString("c_page_counts")));
                    dataMap.put("c_archive_date", Util.null2String(rs.getString("c_archive_date")));
                    dataMap.put("c_archive_org", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_archive_org"))));
                    dataMap.put("c_archive_owner", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_archive_owner"))));
                    dataMap.put("c_inner_sequence", Util.null2String(rs.getString("c_inner_sequence")));
                    dataList.add(dataMap);
                    hasData = true;
                }
            }
            // DBN合同变更审批流程
            else if (i == 40) {
                while (rs.next()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    oldXlsxPath = Util.null2String(rs.getString("excelmode_path")) + 49 + ".xls";
                    LogUtil.debugLog("==DBN合同变更审批模板路径==>" + oldXlsxPath);
                    newXlsPath = Util.null2String(rs.getString("newexcel_path")) + "DBN合同变更审批" + ".xls";
                    LogUtil.debugLog("==DBN合同变更审批保存路径==>" + newXlsPath);
                    String requestid = Util.null2String(rs.getString("requestid"));
                    typeid = Util.getIntValue(rs.getString("typeid"));
                    dataMap.put("object_name", Util.null2String(rs.getString("object_name")));
                    dataMap.put("file_path", Util.null2String(rs.getString("file_path")).replace("/", "\\").replace("/", "\\"));
                    dataMap.put("file_format", Util.null2String(rs.getString("file_format")));
                    dataMap.put("c_chinese_title", Util.null2String(rs.getString("c_chinese_title")));
                    dataMap.put("c_doc_code", Util.null2String(rs.getString("c_doc_code")));
                    //流程中的c_contract_type的数值
                    String c_contract_type = Util.null2String(rs.getString("c_contract_type"));
                    LogUtil.debugLog("c_contract_type:" + c_contract_type);
                    //合同类型的数据库字段
                    String columName = getSettingColName(typeid + "", "gw_fwlx");
                    //合同类型字段的页面fieldid
                    String fieldId = WorkflowUtil.getFieldId(requestid, columName);
                    //合同类型的中文名称
                    String c_contract_type_CH = WorkflowUtil.getSelectChinesType(c_contract_type, fieldId);
                    dataMap.put("c_contract_type", c_contract_type_CH);
                    dataMap.put("c_year", Util.null2String(rs.getString("c_year")));
                    dataMap.put("c_party_a", Util.null2String(rs.getString("c_party_a")));
                    dataMap.put("newexcel_path", newXlsPath);
                    dataMap.put("c_party_b", Util.null2String(rs.getString("c_party_b")));
                    String c_resp_dept = Util.null2String(rs.getString("c_resp_dept"));
                    String c_resp_dept_ZH = WorkflowUtil.getDepartmentname(c_resp_dept);
                    dataMap.put("c_resp_dept", c_resp_dept_ZH);
                    dataMap.put("c_page_counts", Util.null2String(rs.getString("c_page_counts")));
                    dataMap.put("c_archive_date", Util.null2String(rs.getString("c_archive_date")));
                    String hrmid = Util.null2String(rs.getString("c_archive_owne"));
                    String hrmName = ArchivesUtil.getChineseHrmName(hrmid);
                    dataMap.put("c_archive_owne", hrmName);
                    dataMap.put("c_inner_sequence", Util.null2String(rs.getString("c_inner_sequence")));
                    dataList.add(dataMap);
                    hasData = true;
                }
            }
            // DBN授权委托申请
            else if (i == 41) {
                while (rs.next()) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    oldXlsxPath = Util.null2String(rs.getString("excelmode_path")) + 50 + ".xls";
                    LogUtil.debugLog("==DBN授权委托模板路径==>" + oldXlsxPath);
                    String fileName = "DBN授权委托";
                    DepartmentComInfo deptinfo = new DepartmentComInfo();
                    String departmentName = "";
                    try {
                        departmentName = deptinfo.getDepartmentName(Util.null2String(rs.getString("c_compile_dept")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    typeid = Util.getIntValue(rs.getString("typeid"));
                    newXlsPath = Util.null2String(rs.getString("newexcel_path")) + fileName + ".xls";
                    LogUtil.debugLog("==DBN授权委托保存路径==>" + newXlsPath);
                    dataMap.put("object_name", Util.null2String(rs.getString("object_name")));
                    dataMap.put("file_path", Util.null2String(rs.getString("file_path")).replace("/", "\\"));
                    dataMap.put("file_format", Util.null2String(rs.getString("file_format")));
                    dataMap.put("c_chinese_title", Util.null2String(rs.getString("c_chinese_title")));
                    //发文类型数据库存的值
                    // String fwlx = Util.null2String(rs.getString("c_doc_type"));
                    String requestid = Util.null2String(rs.getString("requestid"));
                    //发文类型数据库的字段名
                    // String columName = getSettingColName(typeid + "", "gw_fwlx");
                    dataMap.put("newexcel_path", newXlsPath);
                    dataMap.put("c_doc_type", "");
                    dataMap.put("c_compile_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_compile_dept"))));
                    dataMap.put("c_compile_date", Util.null2String(rs.getString("c_compile_date")));
                    dataMap.put("c_compiler", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_compiler"))));
                    dataMap.put("c_issue_date", Util.null2String(rs.getString("c_issue_date")));
                    dataMap.put("c_issue_num", Util.null2String(rs.getString("c_issue_num")));
                    dataMap.put("c_issue_dept", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_issue_dept"))));
                    String c_target_deptName = WorkflowUtil.getDepartmentname(Util.null2String((rs.getString("c_target_dept"))));
                    dataMap.put("c_target_dept", Util.null2String(c_target_deptName));
                    dataMap.put("c_page_counts", Util.null2String(rs.getString("c_page_counts")));
                    dataMap.put("c_archive_date", Util.null2String(rs.getString("c_archive_date")));
                    dataMap.put("c_archive_org", WorkflowUtil.getDepartmentname(Util.null2String(rs.getString("c_archive_org"))));
                    dataMap.put("c_archive_owner", ArchivesUtil.getChineseHrmName(Util.null2String(rs.getString("c_archive_owner"))));
                    dataMap.put("c_inner_sequence", Util.null2String(rs.getString("c_inner_sequence")));
                    dataList.add(dataMap);
                    hasData = true;
                }
            }

            //生成excel到每月的目录下 例如：D:/WEAVER/ecology/archivesftpfile/2017/11/合同管理/
            if (hasData) {
                LogUtil.debugLog("==dataList==>" + dataList.toString());
                LogUtil.debugLog("==typeid==>" + typeid);
                WriteExcel.writeExcel(dataList, dataList.size(), oldXlsxPath, newXlsPath, typeid + "");

            }

        }
        //将每月的excel上传ftp
        FtpUtil ftpUtil = new FtpUtil();
        String addr = base.getPropValue("archivesftp", "addr");
        int port = Util.getIntValue(base.getPropValue("archivesftp", "port"));
        String username = base.getPropValue("archivesftp", "username");
        String password = base.getPropValue("archivesftp", "password");
        //服务器上的原始路径
        String strAllPdfPathbak = base.getPropValue("archives", "pdfpathbak");
        //excel的上传路径
        String uploadpath = strAllPdfPathbak + "/" + strStartDate.substring(0, 4)+"/"+strStartDate.substring(5, 7);
        //连接ftp
      /*  try {
            LogUtil.debugLog("==上传ftp，开始连接==");
            boolean ftpstatus = ftpUtil.connect("", addr, port, username, password);
            LogUtil.doWriteLog("==ftp连接状态==" + ftpstatus);
        } catch (Exception e) {
           LogUtil.doWriteLog("==上传excel时，连接ftp异常==" + e);
        } */
        //上传文件到ftp
        FtpUtil_zz f = new FtpUtil_zz(true);
        try {

            FtpObject fo = new FtpObject();
            fo.setHostName(addr);
            fo.setUserName(username);
            fo.setPassWord(password);
            fo.setPort(port);
            LogUtil.doWriteLog("==FTP==addr：" + addr+"--username:"+username+"--password:"+password+"==port:"+port);
            LogUtil.debugLog("==上传ftp，开始上传=="+uploadpath);
            f.listFilesExcel(fo,uploadpath,"/" + strStartDate.substring(0, 4)+"/"+strStartDate.substring(5, 7),"",true);
        } catch (Exception e) {
            LogUtil.doWriteLog("==上传excel时,上传ftp异常==" + e);
        }finally {
            try {
                f.disConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            LogUtil.debugLog("==上传excel结束，无论是否成功==");
        }
    }

    public static void main(String[] args) {
        String str = "D:/S/C/D";
        String str1 = str.replace("/", "\\");
        System.out.println(str1);
    }

}
