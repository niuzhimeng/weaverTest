package com.weavernorth.OA2archives.util;

import com.alibaba.fastjson.JSONArray;
import com.weavernorth.util.LogUtil;
import km.org.apache.poi.ss.usermodel.*;
import weaver.general.TimeUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;

public class TestFtp {
	public static void main(String[] args) throws Exception {

		String strArr1 = "[{\"0\":\"zhangsan\",\"1\":\"lisi\",\"2\":\"wangwu\",\"3\":\"maliu\"}," +
				"{\"00\":\"zhangsan\",\"11\":\"lisi\",\"22\":\"wangwu\",\"33\":\"maliu\"}]";

		String strArr = "[\n" +
				"    {\n" +
				"        \"c_compile_date\": \"2018-04-26\",\n" +
				"        \"c_archive_org\": \"生产部\",\n" +
				"        \"file_path\": \"D://testToExcel//生产部//生产部.xls\",\n" +
				"        \"c_issue_date\": \"2018-04-26\",\n" +
				"        \"c_target_dept\": \"生产部\",\n" +
				"        \"c_archive_owner\": \"蒋超-chao.jiang\",\n" +
				"        \"c_doc_type\": \"\",\n" +
				"        \"c_chinese_title\": \"4月份能耗，物耗和质量指标\",\n" +
				"        \"c_english_title\": \"\",\n" +
				"        \"c_compiler\": \"蒋超-chao.jiang\",\n" +
				"        \"c_page_counts\": \"1\",\n" +
				"        \"c_inner_sequence\": \"1\",\n" +
				"        \"c_issue_dept\": \"生产部\",\n" +
				"        \"object_name\": \"中沙津生产〔 2018〕 8 号\",\n" +
				"        \"c_archive_date\": \"2018-04-26\",\n" +
				"        \"c_compile_dept\": \"生产部\",\n" +
				"        \"file_format\": \"pdf\",\n" +
				"        \"c_issue_num\": \"\"\n" +
				"    },\n" +
				"    {\n" +
				"        \"c_compile_date \": \"2018-04-26\",\n" +
				"        \"c_archive_org\": \"生产部\",\n" +
				"        \"file_path\": \"D://testToExcel//生产部//生产部.xls\",\n" +
				"        \"c_issue_date\": \"2018 - 04 - 26\",\n" +
				"        \"c_target_dept\": \"生产部\",\n" +
				"        \"c_archive_owner\": \"马胜龙 - sl.ma\",\n" +
				"        \"c_doc_type\": \" \",\n" +
				"        \"c_chinese_title\": \"2018 年中沙（ 天津） 石化有限公司“ 劳动节” 期间工作安排SSTPC Work Arrangement during the“ Labor Day” of 2018\",\n" +
				"        \"c_english_title\": \" \",\n" +
				"        \"c_compiler\": \"马胜龙 - sl.ma\",\n" +
				"        \"c_page_counts\": \"1\",\n" +
				"        \"c_inner_sequence\": \"2\",\n" +
				"        \"c_issue_dept\": \"生产部\",\n" +
				"        \"object_name\": \"中沙津生产〔 2018〕 9 号\",\n" +
				"        \"c_archive_date\": \"2018-04-26\",\n" +
				"        \"c_compile_dept\": \"生产部\",\n" +
				"        \"file_format\": \"pdf\",\n" +
				"        \"c_issue_num\": \"\"\n" +
				"    },\n" +
				"    {\n" +
				"        \"c_compile_date\": \"2018-04-27\",\n" +
				"        \"c_archive_org\": \"安全环保部\",\n" +
				"        \"file_path\": \"D://testToExcel//安全环保部//安全环保部.xls\",\n" +
				"        \"c_issue_date\": \"2018-04-27\",\n" +
				"        \"c_target_dept\": \"安全环保部\",\n" +
				"        \"c_archive_owner\": \"刘津梅-jm.liu\",\n" +
				"        \"c_doc_type\": \"\",\n" +
				"        \"c_chinese_title\": \"关于下发2018年度职业健康检查的通知\",\n" +
				"        \"c_english_title\": \"\",\n" +
				"        \"c_compiler\": \"刘津梅-jm.liu\",\n" +
				"        \"c_page_counts\": \"1\",\n" +
				"        \"c_inner_sequence\": \"3\",\n" +
				"        \"c_issue_dept\": \"安全环保部\",\n" +
				"        \"object_name\": \"中沙津安保〔2018〕10号\",\n" +
				"        \"c_archive_date\": \"2018-04-27\",\n" +
				"        \"c_compile_dept\": \"安全环保部\",\n" +
				"        \"file_format\": \"pdf\",\n" +
				"        \"c_issue_num\": \"\"\n" +
				"    },\n" +
				"    {\n" +
				"        \"c_compile_date\": \"2018-04-27\",\n" +
				"        \"c_archive_org\": \"安全环保部\",\n" +
				"        \"file_path\": \"D://testToExcel//安全环保部//安全环保部.xls\",\n" +
				"        \"c_issue_date\": \"2018-04-27\",\n" +
				"        \"c_target_dept\": \"安全环保部\",\n" +
				"        \"c_archive_owner\": \"刘津梅-jm.liu\",\n" +
				"        \"c_doc_type\": \"\",\n" +
				"        \"c_chinese_title\": \"关于下发2018年度职业健康检查的通知\",\n" +
				"        \"c_english_title\": \"\",\n" +
				"        \"c_compiler\": \"刘津梅-jm.liu\",\n" +
				"        \"c_page_counts\": \"1\",\n" +
				"        \"c_inner_sequence\": \"3\",\n" +
				"        \"c_issue_dept\": \"井海洋\",\n" +
				"        \"object_name\": \"中沙津安保〔2018〕10号\",\n" +
				"        \"c_archive_date\": \"2018-04-27\",\n" +
				"        \"c_compile_dept\": \"安全环保部\",\n" +
				"        \"file_format\": \"pdf\",\n" +
				"        \"c_issue_num\": \"\"\n" +
				"    },\n" +
				"    {\n" +
				"        \"c_compile_date\": \"2018-04-27\",\n" +
				"        \"c_archive_org\": \"安全环保部\",\n" +
				"        \"file_path\": \"D://testToExcel//安全环保部//安全环保部.xls\",\n" +
				"        \"c_issue_date\": \"2018-04-27\",\n" +
				"        \"c_target_dept\": \"安全环保部\",\n" +
				"        \"c_archive_owner\": \"刘津梅-jm.liu\",\n" +
				"        \"c_doc_type\": \"\",\n" +
				"        \"c_chinese_title\": \"关于下发2018年度职业健康检查的通知\",\n" +
				"        \"c_english_title\": \"\",\n" +
				"        \"c_compiler\": \"刘津梅-jm.liu\",\n" +
				"        \"c_page_counts\": \"1\",\n" +
				"        \"c_inner_sequence\": \"3\",\n" +
				"        \"c_issue_dept\": \"刘君\",\n" +
				"        \"object_name\": \"中沙津安保〔2018〕10号\",\n" +
				"        \"c_archive_date\": \"2018-04-27\",\n" +
				"        \"c_compile_dept\": \"安全环保部\",\n" +
				"        \"file_format\": \"pdf\",\n" +
				"        \"c_issue_num\": \"\"\n" +
				"    },\n" +
				"    {\n" +
				"        \"c_compile_date\": \"2018-04-27\",\n" +
				"        \"c_archive_org\": \"安全井海洋部\",\n" +
				"        \"file_path\": \"D://testToExcel//安全环保部//安全环保部.xls\",\n" +
				"        \"c_issue_date\": \"2018-04-27\",\n" +
				"        \"c_target_dept\": \"安全环保部\",\n" +
				"        \"c_archive_owner\": \"刘津梅-jm.liu\",\n" +
				"        \"c_doc_type\": \"\",\n" +
				"        \"c_chinese_title\": \"关于下发2018年度职业健康检查的通知\",\n" +
				"        \"c_english_title\": \"\",\n" +
				"        \"c_compiler\": \"刘津梅-jm.liu\",\n" +
				"        \"c_page_counts\": \"1\",\n" +
				"        \"c_inner_sequence\": \"3\",\n" +
				"        \"c_issue_dept\": \"井海洋\",\n" +
				"        \"object_name\": \"中沙津安保〔2018〕10号\",\n" +
				"        \"c_archive_date\": \"2018-04-27\",\n" +
				"        \"c_compile_dept\": \"安全环保部\",\n" +
				"        \"file_format\": \"pdf\",\n" +
				"        \"c_issue_num\": \"\"\n" +
				"    },\n" +
				"    {\n" +
				"        \"c_compile_date\": \"2018-04-27\",\n" +
				"        \"c_archive_org\": \"安全环保部\",\n" +
				"        \"file_path\": \"D://testToExcel//井海洋//井海洋.xls\",\n" +
				"        \"c_issue_date\": \"2018-04-27\",\n" +
				"        \"c_target_dept\": \"安全环保部\",\n" +
				"        \"c_archive_owner\": \"刘津梅-jm.liu\",\n" +
				"        \"c_doc_type\": \"\",\n" +
				"        \"c_chinese_title\": \"关于下发2018年度职业健康检查的通知\",\n" +
				"        \"c_english_title\": \"\",\n" +
				"        \"c_compiler\": \"刘津梅-jm.liu\",\n" +
				"        \"c_page_counts\": \"1\",\n" +
				"        \"c_inner_sequence\": \"3\",\n" +
				"        \"c_issue_dept\": \"安全环保部\",\n" +
				"        \"object_name\": \"中沙津安保〔2018〕10号\",\n" +
				"        \"c_archive_date\": \"2018-04-27\",\n" +
				"        \"c_compile_dept\": \"井海洋\",\n" +
				"        \"file_format\": \"pdf\",\n" +
				"        \"c_issue_num\": \"\"\n" +
				"    },\n" +
				"    {\n" +
				"        \"c_compile_date\": \"2018-04-27\",\n" +
				"        \"c_archive_org\": \"安全环保部\",\n" +
				"        \"file_path\": \"D://testToExcel//井海洋//井海洋.xls\",\n" +
				"        \"c_issue_date\": \"2018-04-27\",\n" +
				"        \"c_target_dept\": \"安全环保部\",\n" +
				"        \"c_archive_owner\": \"刘津梅-jm.liu\",\n" +
				"        \"c_doc_type\": \"\",\n" +
				"        \"c_chinese_title\": \"关于下发2018年度职业健康检查的通知\",\n" +
				"        \"c_english_title\": \"\",\n" +
				"        \"c_compiler\": \"刘津梅-jm.liu\",\n" +
				"        \"c_page_counts\": \"1\",\n" +
				"        \"c_inner_sequence\": \"3\",\n" +
				"        \"c_issue_dept\": \"安全环保部\",\n" +
				"        \"object_name\": \"中沙津安保〔2018〕10号\",\n" +
				"        \"c_archive_date\": \"2018-04-27\",\n" +
				"        \"c_compile_dept\": \"井海洋\",\n" +
				"        \"file_format\": \"pdf\",\n" +
				"        \"c_issue_num\": \"\"\n" +
				"    },\n" +
				"    {\n" +
				"        \"c_compile_date\": \"2018-04-27\",\n" +
				"        \"c_archive_org\": \"安全环保部\",\n" +
				"        \"file_path\": \"D://testToExcel//井海洋//井海洋.xls\",\n" +
				"        \"c_issue_date\": \"2018-04-27\",\n" +
				"        \"c_target_dept\": \"安全环保部\",\n" +
				"        \"c_archive_owner\": \"刘津梅-jm.liu\",\n" +
				"        \"c_doc_type\": \"\",\n" +
				"        \"c_chinese_title\": \"关于下发2018年度职业健康检查的通知\",\n" +
				"        \"c_english_title\": \"\",\n" +
				"        \"c_compiler\": \"刘津梅-jm.liu\",\n" +
				"        \"c_page_counts\": \"1\",\n" +
				"        \"c_inner_sequence\": \"3\",\n" +
				"        \"c_issue_dept\": \"安全环保部\",\n" +
				"        \"object_name\": \"中沙津安保〔2018〕10号\",\n" +
				"        \"c_archive_date\": \"2018-04-27\",\n" +
				"        \"c_compile_dept\": \"井海洋\",\n" +
				"        \"file_format\": \"pdf\",\n" +
				"        \"c_issue_num\": \"\"\n" +
				"    },\n" +
				"    {\n" +
				"        \"c_compile_date\": \"2018-04-27\",\n" +
				"        \"c_archive_org\": \"安全环保部\",\n" +
				"        \"file_path\": \"D://testToExcel//刘君//刘君.xls\",\n" +
				"        \"c_issue_date\": \"2018-04-27\",\n" +
				"        \"c_target_dept\": \"安全环保部\",\n" +
				"        \"c_archive_owner\": \"刘津梅-jm.liu\",\n" +
				"        \"c_doc_type\": \"\",\n" +
				"        \"c_chinese_title\": \"关于下发2018年度职业健康检查的通知\",\n" +
				"        \"c_english_title\": \"\",\n" +
				"        \"c_compiler\": \"刘津梅-jm.liu\",\n" +
				"        \"c_page_counts\": \"1\",\n" +
				"        \"c_inner_sequence\": \"3\",\n" +
				"        \"c_issue_dept\": \"安全环保部\",\n" +
				"        \"object_name\": \"中沙津安保〔2018〕10号\",\n" +
				"        \"c_archive_date\": \"2018-04-27\",\n" +
				"        \"c_compile_dept\": \"刘君\",\n" +
				"        \"file_format\": \"pdf\",\n" +
				"        \"c_issue_num\": \"\"\n" +
				"    },\n" +
				"    {\n" +
				"        \"c_compile_date\": \"2018-04-27\",\n" +
				"        \"c_archive_org\": \"安全环保部\",\n" +
				"        \"file_path\": \"D://testToExcel//刘君//刘君.xls\",\n" +
				"        \"c_issue_date\": \"2018-04-27\",\n" +
				"        \"c_target_dept\": \"刘君\",\n" +
				"        \"c_archive_owner\": \"刘津梅-jm.liu\",\n" +
				"        \"c_doc_type\": \"\",\n" +
				"        \"c_chinese_title\": \"关于下发2018年度职业健康检查的通知\",\n" +
				"        \"c_english_title\": \"\",\n" +
				"        \"c_compiler\": \"刘津梅-jm.liu\",\n" +
				"        \"c_page_counts\": \"1\",\n" +
				"        \"c_inner_sequence\": \"3\",\n" +
				"        \"c_issue_dept\": \"安全环保部\",\n" +
				"        \"object_name\": \"中沙津安保〔2018〕10号\",\n" +
				"        \"c_archive_date\": \"2018-04-27\",\n" +
				"        \"c_compile_dept\": \"刘君\",\n" +
				"        \"file_format\": \"pdf\",\n" +
				"        \"c_issue_num\": \"\"\n" +
				"    }\n" +
				"]";	//第一种方式
		List<Map<String,String>> listObjectFir = (List<Map<String,String>>) JSONArray.parse(strArr);
		Map<String, List<Map<String, String>>> mapList = new HashMap<String, List<Map<String, String>>>();
		for (int i = 0; i < listObjectFir.size(); i++) {
			Map dataMap = listObjectFir.get(i);
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

		File finalXlsxFile = new File("D:\\archivesmode\\11.xls");
		Workbook workBook = WriteExcel.getWorkbok(finalXlsxFile);
		// sheet 对应第一个工作页
		Sheet sheet = workBook.getSheetAt(0);
		CellStyle cellStyle = workBook.createCellStyle();
		DataFormat format = workBook.createDataFormat();
		cellStyle.setDataFormat(format.getFormat("yyyy/M/d"));
		for (String key : mapList.keySet()) {
			List<Map<String, String>> list = mapList.get(key);
			String path = list.get(0).get("file_path");
			LogUtil.doWriteLog("-------------------------path-----------" + path);
			for (int o = 0; o < list.size(); o++) {
				// 创建一行：从第二行开始，跳过属性列
				Row row = sheet.createRow(o + 6);
				// 得到要插入的每一条记录
				Map dataMap = list.get(o);
				String object_name = dataMap.get("object_name").toString();
				String file_path = dataMap.get("file_path").toString();
				String file_format = dataMap.get("file_format").toString();
				//String c_english_title = dataMap.get("c_english_title").toString();
				String c_chinese_title = dataMap.get("c_chinese_title").toString();
				String c_doc_type = dataMap.get("c_doc_type").toString();
				String c_compile_dept = dataMap.get("c_compile_dept").toString();
				String c_compile_date = "2018-04-30";
				String c_compiler = dataMap.get("c_compiler").toString();
				String c_issue_date = "2018-04-30";
				String c_issue_num = dataMap.get("c_issue_num").toString();
				String c_issue_dept = dataMap.get("c_issue_dept").toString();
				String c_target_dept = dataMap.get("c_target_dept").toString();
				String c_page_counts = dataMap.get("c_page_counts").toString();
				String c_archive_date = dataMap.get("c_archive_date").toString();
				String c_archive_org = dataMap.get("c_archive_org").toString();
				String c_archive_owner = dataMap.get("c_archive_owner").toString();
				String c_inner_sequence = dataMap.get("c_inner_sequence").toString();
//                        LogUtil.debugLog("==c_inner_sequence==>" + c_inner_sequence);
				for (int p = 0; p <= list.size(); p++) {
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
					Calendar can13 = com.weaver.general.TimeUtil.getCalendar(c_compile_date, "yyyy-MM-dd");
					cel13.setCellValue(can13);
					cel13.setCellStyle(cellStyle);
					row.createCell(14).setCellValue(c_compiler);
					Cell cel20 = row.createCell(20);
					//转为日期格式
					Calendar can20 = com.weaver.general.TimeUtil.getCalendar(c_issue_date, "yyyy-MM-dd");
					cel20.setCellValue(can20);
					cel20.setCellStyle(cellStyle);
					row.createCell(21).setCellValue(c_issue_num);
					row.createCell(22).setCellValue(c_issue_dept);
					row.createCell(27).setCellValue(c_target_dept);
					row.createCell(34).setCellValue(c_page_counts);
					Cell cel40 = row.createCell(40);
					//转为日期格式
					Calendar can40 = com.weaver.general.TimeUtil.getCalendar(c_archive_date, "yyyy-MM-dd");
					cel40.setCellValue(can40);
					cel40.setCellStyle(cellStyle);
					row.createCell(41).setCellValue(c_archive_org);
					row.createCell(42).setCellValue(c_archive_owner);
					row.createCell(43).setCellValue(c_inner_sequence);
				}
			}


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
			OutputStream out = null;
			out = new FileOutputStream(path);
			workBook.write(out);
		}


		String strStartDate = TimeUtil.getMonthBeginDay();
		//TimeUtil.getLastMonthBeginDay();
		//TODO 上个月的最后一天 需要修改为上个月
		String strEndDate = TimeUtil.getMonthEndDay();
//		System.out.println(strStartDate+"-------------"+strEndDate);
//		System.out.println(mapList.toString());
	}
}
