package com.weavernorth.OA2archives.util;

import com.weaver.general.BaseBean;
import com.weaver.general.TimeUtil;
import com.weavernorth.util.LogUtil;
import km.org.apache.poi.hssf.usermodel.HSSFWorkbook;
import km.org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.*;

/**
 * 类名:WriteExcel
 * 类描述:excel模板数据写入并生成新的文档
 * Company:weavernorth
 *
 * @author:刘立华
 * @date: 2017-11-6下午3:57:26
 */
public class WriteExcel {
    private static final String EXCEL_XLS = "xls";

    /**
     * TODO 增加对应流程的逻辑
     *
     * @param dataList
     * @param cloumnCount 总共多少列
     * @param oldXlsxPath 原始的excel模板
     */
    public static void writeExcel(List<Map<String, String>> dataList, int cloumnCount, String oldXlsxPath, String newXlsPath, String strType) {
        OutputStream out = null;
        LogUtil.debugLog("==dataList.size()===>" + dataList.size());
        try {
            // 获取总列数
            int columnNumCount = cloumnCount;
            // 读取Excel文档
            File finalXlsxFile = new File(oldXlsxPath);
            Workbook workBook = getWorkbok(finalXlsxFile);
            // sheet 对应第一个工作页
            Sheet sheet = workBook.getSheetAt(0);
            CellStyle cellStyle = workBook.createCellStyle();
            DataFormat format = workBook.createDataFormat();
            cellStyle.setDataFormat(format.getFormat("yyyy/M/d"));
            //生成excel时把按照部门分类的 通过配置文件获取
            String typeids = new BaseBean().getPropValue("dept_assortment", "typeids");
            /**
             * 往Excel中写新数据   wn_workflowexceltype
             */
            //合同模板中插入数据
            if ("1".equals(strType) || "2".equals(strType) || "29".equals(strType) || "30".equals(strType)) {
                for (int j = 0; j < dataList.size(); j++) {
                    // 创建一行：从第二行开始，跳过属性列
                    Row row = sheet.createRow(j + 6);
                    // 得到要插入的每一条记录
                    Map dataMap = dataList.get(j);
                    String object_name = dataMap.get("object_name").toString();
                    String file_path = dataMap.get("file_path").toString();
                    String file_format = dataMap.get("file_format").toString();
                    String c_chinese_title = dataMap.get("c_chinese_title").toString();
                    String c_doc_code = dataMap.get("c_doc_code").toString();
                    String c_contract_type = dataMap.get("c_contract_type").toString();
                    String c_year = dataMap.get("c_year").toString();
                    String c_party_a = dataMap.get("c_party_a").toString();
                    String c_party_b = dataMap.get("c_party_b").toString();
                    String c_resp_dept = dataMap.get("c_resp_dept").toString();
                    String c_page_counts = dataMap.get("c_page_counts").toString();
                    String c_archive_date = dataMap.get("c_archive_date").toString();
                    String c_archive_owne = dataMap.get("c_archive_owne").toString();
                    String c_inner_sequence = dataMap.get("c_inner_sequence").toString();
                    for (int k = 0; k <= columnNumCount; k++) {
                        // 在一行内循环
                        row.createCell(2).setCellValue(object_name);
                        row.createCell(3).setCellValue(file_path + object_name + ".pdf");
                        row.createCell(4).setCellValue(file_format);
                        row.createCell(8).setCellValue(c_chinese_title);
                        row.createCell(9).setCellValue(c_doc_code);
                        row.createCell(14).setCellValue(c_contract_type);
                        row.createCell(15).setCellValue(c_year);
                        row.createCell(16).setCellValue(c_party_a);
                        row.createCell(17).setCellValue(c_party_b);
                        row.createCell(25).setCellValue(c_resp_dept);
                        row.createCell(29).setCellValue(c_page_counts);
                        Cell cel33 = row.createCell(33);
                        Calendar can33 = TimeUtil.getCalendar(c_archive_date, "yyyy-MM-dd");
                        cel33.setCellValue(can33);
                        //设置excel样式
                        cel33.setCellStyle(cellStyle);
                        row.createCell(34).setCellValue(c_archive_owne);
                        row.createCell(35).setCellValue(j + 1);
                    }
                }
                LogUtil.debugLog("===合同模板中==数据写入成功====");
                //公文模板数据
            } else if ("3".equals(strType) || "4".equals(strType) || "5".equals(strType) || "6".equals(strType)
                    || "7".equals(strType) /*|| "8".equals(strType)*/ || "9".equals(strType) || "10".equals(strType)
                    || "15".equals(strType) || "16".equals(strType)
                    || "17".equals(strType) || "18".equals(strType) || "19".equals(strType)
                    || "22".equals(strType) || "23".equals(strType) || "24".equals(strType)
                    /* || "25".equals(strType) || "26".equals(strType) || "27".equals(strType)*/
                    || "28".equals(strType) || "31".equals(strType) || "32".equals(strType)
                    || "33".equals(strType) || "34".equals(strType) || "35".equals(strType)
                    || "36".equals(strType) || /*"37".equals(strType)|| "38".equals(strType)
                    || "39".equals(strType)|| "40".equals(strType)||*/ "41".equals(strType)) {
                LogUtil.debugLog("=开始写入==>" + strType);
                for (int j = 0; j < dataList.size(); j++) {
                    // 创建一行：从第二行开始，跳过属性列
                    Row row = sheet.createRow(j + 6);
                    // 得到要插入的每一条记录
                    Map dataMap = dataList.get(j);
                    String object_name = dataMap.get("object_name").toString();
                    String file_path = dataMap.get("file_path").toString();
                    String file_format = dataMap.get("file_format").toString();
                    //String c_english_title = dataMap.get("c_english_title").toString();
                    String c_chinese_title = dataMap.get("c_chinese_title").toString();
                    String c_doc_type = dataMap.get("c_doc_type").toString();
                    String c_compile_dept = dataMap.get("c_compile_dept").toString();
                    String c_compile_date = dataMap.get("c_compile_date").toString();
                    String c_compiler = dataMap.get("c_compiler").toString();
                    String c_issue_date = dataMap.get("c_issue_date").toString();
                    String c_issue_num = dataMap.get("c_issue_num").toString();
                    String c_issue_dept = dataMap.get("c_issue_dept").toString();
                    String c_target_dept = dataMap.get("c_target_dept").toString();
                    String c_page_counts = dataMap.get("c_page_counts").toString();
                    String c_archive_date = dataMap.get("c_archive_date").toString();
                    String c_archive_org = dataMap.get("c_archive_org").toString();
                    String c_archive_owner = dataMap.get("c_archive_owner").toString();
                    String c_inner_sequence = dataMap.get("c_inner_sequence").toString();
                    LogUtil.debugLog("==c_page_counts==>" + c_page_counts);
                    for (int k = 0; k <= columnNumCount; k++) {
                        // 在一行内循环
                        row.createCell(2).setCellValue(object_name);
                        row.createCell(3).setCellValue(file_path + object_name + ".pdf");
                        row.createCell(4).setCellValue(file_format);
                        //row.createCell(7).setCellValue(c_english_title);
                        row.createCell(8).setCellValue(c_chinese_title);
                        row.createCell(10).setCellValue(c_doc_type);
                        row.createCell(12).setCellValue(c_compile_dept);
                        Cell cel13 = row.createCell(13);
                        //转为日期格式
                        Calendar can13 = TimeUtil.getCalendar(c_compile_date, "yyyy-MM-dd");
                        cel13.setCellValue(can13);
                        cel13.setCellStyle(cellStyle);
                        row.createCell(14).setCellValue(c_compiler);
                        Cell cel20 = row.createCell(20);
                        //转为日期格式
                        Calendar can20 = TimeUtil.getCalendar(c_issue_date, "yyyy-MM-dd");
                        cel20.setCellValue(can20);
                        cel20.setCellStyle(cellStyle);
                        row.createCell(21).setCellValue(c_issue_num);
                        row.createCell(22).setCellValue(c_issue_dept);
                        row.createCell(27).setCellValue(c_target_dept);
                        row.createCell(34).setCellValue(c_page_counts);
                        Cell cel40 = row.createCell(40);
                        //转为日期格式
                        Calendar can40 = TimeUtil.getCalendar(c_archive_date, "yyyy-MM-dd");
                        cel40.setCellValue(can40);
                        cel40.setCellStyle(cellStyle);
                        row.createCell(41).setCellValue(c_archive_org);
                        row.createCell(42).setCellValue(c_archive_owner);
                        row.createCell(43).setCellValue(j + 1);
                    }
                }
                LogUtil.debugLog("===公文模板中==数据写入成功====");
            }
            //公文模板中写入数据信息

            LogUtil.debugLog("==数据导出开始====");
            // 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效

            if (!("37".equals(strType) || "38".equals(strType) || "39".equals(strType) || "40".equals(strType)
                    || "25".equals(strType) || "26".equals(strType) || "27".equals(strType) || "8".equals(strType))) {
                //期望的目录路径
                File ca = new File(newXlsPath);
                File mulu = new File(ca.getParent());
                if (!mulu.exists()) {
                    //不存在路径则创建
                    mulu.mkdirs();
                }
                out = new FileOutputStream(newXlsPath);
                workBook.write(out);
            }

            //这些单独处理生成目录
            if ("37".equals(strType) || "38".equals(strType) || "39".equals(strType) || "40".equals(strType)
                    || "25".equals(strType) || "26".equals(strType) || "27".equals(strType) || "8".equals(strType)) {

//                List<Map<String,List<Map<String, String>>>> newListDataList = new ArrayList<Map<String, List<Map<String, String>>>>();
                Map<String, List<Map<String, String>>> mapList = new HashMap<String, List<Map<String, String>>>();
                for (int i = 0; i < dataList.size(); i++) {
                    Map dataMap = dataList.get(i);
                    String c_compile_dept = dataMap.get("c_compile_dept").toString();
                    if (mapList.containsKey(c_compile_dept)) {
                        List<Map<String, String>> list = mapList.get(c_compile_dept);
                        list.add(dataMap);
                        mapList.put(c_compile_dept, list);
                    } else {
                        List<Map<String, String>> newListData2 = new ArrayList<Map<String, String>>();
                        newListData2.add(dataMap);
                        mapList.put(c_compile_dept, newListData2);
                    }
                }

                LogUtil.doWriteLog("-------------------------newListDataList------------" + mapList.toString());
                LogUtil.doWriteLog("-------------------------newListDataList.size()------------" + mapList.size());


                for (String key : mapList.keySet()) {
                    OutputStream out1 = null;

                    File finalXlsxFile1 = new File(oldXlsxPath);
                    Workbook workBook1 = getWorkbok(finalXlsxFile1);
                    // sheet 对应第一个工作页
                    Sheet sheet1 = workBook1.getSheetAt(0);
                    CellStyle cellStyle1 = workBook1.createCellStyle();
                    DataFormat format1 = workBook1.createDataFormat();
                    cellStyle1.setDataFormat(format1.getFormat("yyyy/M/d"));
                    try {
                        List<Map<String, String>> list = mapList.get(key);
                        String path = list.get(0).get("newexcel_path");
                        LogUtil.doWriteLog("-------------------------path-----------" + path);
                        LogUtil.doWriteLog("-------------------------path-------list----" + list.toString());
                        for (int o = 0; o < list.size(); o++) {
                            // 创建一行：从第二行开始，跳过属性列
                            Row row = sheet1.createRow(o + 6);
                            // 得到要插入的每一条记录
                            Map dataMap = list.get(o);
                            String object_name = dataMap.get("object_name").toString();
                            String file_path = dataMap.get("file_path").toString();
                            String file_format = dataMap.get("file_format").toString();
                            //String c_english_title = dataMap.get("c_english_title").toString();
                            String c_chinese_title = dataMap.get("c_chinese_title").toString();
                            String c_doc_type = dataMap.get("c_doc_type").toString();
                            String c_compile_dept = dataMap.get("c_compile_dept").toString();
                            String c_compile_date = dataMap.get("c_compile_date").toString();
                            String c_compiler = dataMap.get("c_compiler").toString();
                            String c_issue_date = dataMap.get("c_issue_date").toString();
                            String c_issue_num = dataMap.get("c_issue_num").toString();
                            String c_issue_dept = dataMap.get("c_issue_dept").toString();
                            String c_target_dept = dataMap.get("c_target_dept").toString();
                            String c_page_counts = dataMap.get("c_page_counts").toString();
                            String c_archive_date = dataMap.get("c_archive_date").toString();
                            String c_archive_org = dataMap.get("c_archive_org").toString();
                            String c_archive_owner = dataMap.get("c_archive_owner").toString();
                            String c_inner_sequence = dataMap.get("c_inner_sequence").toString();
                            //                        LogUtil.debugLog("==c_inner_sequence==>" + c_inner_sequence);
                            for (int p = 0; p <= columnNumCount; p++) {
                                // 在一行内循环
                                row.createCell(2).setCellValue(object_name);
                                row.createCell(3).setCellValue(file_path + object_name + ".pdf");
                                row.createCell(4).setCellValue(file_format);
                                //row.createCell(7).setCellValue(c_english_title);
                                row.createCell(8).setCellValue(c_chinese_title);
                                row.createCell(10).setCellValue(c_doc_type);
                                row.createCell(12).setCellValue(c_compile_dept);
                                Cell cel13 = row.createCell(13);
                                //转为日期格式
                                Calendar can13 = TimeUtil.getCalendar(c_compile_date, "yyyy-MM-dd");
                                cel13.setCellValue(can13);
                                cel13.setCellStyle(cellStyle1);
                                row.createCell(14).setCellValue(c_compiler);
                                Cell cel20 = row.createCell(20);
                                //转为日期格式
                                Calendar can20 = TimeUtil.getCalendar(c_issue_date, "yyyy-MM-dd");
                                cel20.setCellValue(can20);
                                cel20.setCellStyle(cellStyle1);
                                row.createCell(21).setCellValue(c_issue_num);
                                row.createCell(22).setCellValue(c_issue_dept);
                                row.createCell(27).setCellValue(c_target_dept);
                                row.createCell(34).setCellValue(c_page_counts);
                                Cell cel40 = row.createCell(40);
                                //转为日期格式
                                Calendar can40 = TimeUtil.getCalendar(c_archive_date, "yyyy-MM-dd");
                                cel40.setCellValue(can40);
                                cel40.setCellStyle(cellStyle1);
                                row.createCell(41).setCellValue(c_archive_org);
                                row.createCell(42).setCellValue(c_archive_owner);
                                row.createCell(43).setCellValue(o + 1);
                                LogUtil.doWriteLog("-------------------------o+1-----------" + o + 1);
                            }
                        }

                        LogUtil.doWriteLog("------------------------key-----------" + key);
                        //期望的目录路径,这个地方要注意，调用FileOutputStream方法前要创建的文件目录是否存在，如果文件目录不存在回抛了文件找不到异常：FileNotFoundException
                        //我们知道， 使用new Fileoutputstream(filepath)时， 如果filepath指定的文件不存在， 文件输出流会帮我们自动创建一个文件。
                        // 仅仅是文件而已。 如果filepath中包含尚未创建的目录， 就会抛出文件找不到异常。
                        // 所以在使用输出流之前， 最好使用File的mkdirs方法， 先创建文件的目录信息
                        File ca1 = new File(path);
                        File mulu1 = new File(ca1.getParent());
                        if (!mulu1.exists()) {
                            //不存在路径则创建
                            mulu1.mkdirs();
                        }
                        out1 = new FileOutputStream(path);
                        workBook1.write(out1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (out1 != null) {
                                out1.flush();
                                out1.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            LogUtil.debugLog("==数据导出异常3====" + e.getMessage());
                        }
                    }
                }

            }
            LogUtil.debugLog("==数据导出成功====");
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.debugLog("==数据导出异常1====" + e.getMessage());
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                LogUtil.debugLog("==数据导出异常2====" + e.getMessage());
            }
        }

    }

    /**
     * 判断Excel的版本,获取Workbook
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbok(File file) throws IOException {
        Workbook wb = null;
        FileInputStream in = new FileInputStream(file);
        if (file.getName().endsWith(EXCEL_XLS)) {  //Excel 2003
            wb = (Workbook) new HSSFWorkbook(in);
        }
        return wb;
    }

    public static void main(String[] args) {
        Calendar calendar = TimeUtil.getCalendar("", "yyyy-MM-dd");
        System.out.println(calendar);
    }
}