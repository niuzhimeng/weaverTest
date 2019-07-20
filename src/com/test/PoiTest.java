package com.test;

import km.org.apache.poi.hssf.usermodel.HSSFCellStyle;
import km.org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import java.io.FileOutputStream;

public class PoiTest {

    @Test
    public void test() {
        try {

            //创建一个工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            //创建一个电子表格
            HSSFSheet sheet = workbook.createSheet("Sheet1");

            //内容字体
            org.apache.poi.hssf.usermodel.HSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short) 9); //字体高度
            font.setFontName("微软雅黑"); //字体
            font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL); //宽度

            //标题字体
            org.apache.poi.hssf.usermodel.HSSFFont biaoTifont = workbook.createFont();
            biaoTifont.setFontHeightInPoints((short) 10); //字体高度
            biaoTifont.setFontName("微软雅黑"); //字体
            biaoTifont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); //宽度

            //内容格式
            org.apache.poi.hssf.usermodel.HSSFCellStyle neiRongStyle = workbook.createCellStyle();
            neiRongStyle.setFont(font);
            neiRongStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT); //布局，偏左
            neiRongStyle.setWrapText(true);
            neiRongStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            neiRongStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            neiRongStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            neiRongStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            //标题格式
            org.apache.poi.hssf.usermodel.HSSFCellStyle biaoTiStyle = workbook.createCellStyle();
            biaoTiStyle.setFont(biaoTifont);
            biaoTiStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); //布局，居中
            biaoTiStyle.setWrapText(true);
            biaoTiStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            biaoTiStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            biaoTiStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            biaoTiStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);

            // 标题行
            HSSFRow titleRow = sheet.createRow(0);

            for (int i = 0; i < 5; i++) {
                HSSFCell cell = titleRow.createCell((short) i);
                cell.setCellValue("牛智萌" + i);
//                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
//                cell.setEncoding(HSSFCell.ENCODING_UTF_16);
//                cell.setCellStyle(biaoTiStyle);
            }

            for (int i = 1; i < 5; i++) {
                HSSFRow row1 = sheet.createRow(i);
                for (int j = 0; j < 5; j++) {
                    HSSFCell cell1 = row1.createCell((short) j);
                    cell1.setCellValue(j + "");
//                    cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
//                    cell1.setEncoding(HSSFCell.ENCODING_UTF_16);
//                    cell1.setCellStyle(neiRongStyle);
                }

            }

//        response.setContentType("application/octet-stream");
//        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("test.xls", "UTF-8"));  //返回头 文件名
//        //获取返回体输出权
//        ServletOutputStream output = response.getOutputStream();
//        out.clearBuffer();
//        OutputStream os = new BufferedOutputStream(output);
//        workbook.write(os);
//        os.flush();
//        os.close();
            FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\29529\\Desktop\\student.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
