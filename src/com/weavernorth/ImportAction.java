package com.weavernorth;

import com.weaver.general.BaseBean;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import weaver.conn.RecordSet;
import weaver.file.ExcelParse;
import weaver.file.FileUploadToPath;
import weaver.formmode.interfaces.ImportPreInterfaceAction;
import weaver.hrm.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 批量导入调休
 */
public class ImportAction extends BaseBean implements ImportPreInterfaceAction {
    @Override
    public String checkImportData(FileUploadToPath fileUploadToPath, User user, int i, ExcelParse excelParse, int i1) {
        try {
            writeLog("批量导入接口执行==========================start");

            HSSFWorkbook wb = excelParse.getWb();
            HSSFSheet sheet = wb.getSheet("主表");
            writeLog("sheet=================: " + sheet);
            //总行数
            int numRow = sheet.getLastRowNum();
            HSSFRow row = sheet.getRow(0);
            //总列数
            short numCol = row.getLastCellNum();
            writeLog("总行数 and 总列数=：  " + numRow + ", " + numCol);

            //获取map lastname - id
            Map<String, String> map = new HashMap<String, String>();

            //缺少数据的行
            StringBuilder errBuilder = new StringBuilder("ID为： ");

            RecordSet recordSet = new RecordSet();
            recordSet.execute("SELECT id, lastname FROM HrmResource");
            while (recordSet.next()) {
                map.put(recordSet.getString("lastname"), recordSet.getString("id"));
            }

            StringBuilder builder = new StringBuilder("INSERT INTO hrm_paid_leave ( delflag, field001, field002, field003," +
                    " field004, field005,field006,field007,field008,field009,field010,field011,field012 )VALUES");
            String currentCell;
            //一行元素的集合
            List<String> rowList = new ArrayList<String>();
            String id;
            for (int myRow = 1; myRow <= numRow; myRow++) {
                HSSFRow cells = sheet.getRow(myRow);
                for (int myCol = 0; myCol < numCol; myCol++) {
                    currentCell = getCellStringValue(cells.getCell((short) myCol));
                    rowList.add(currentCell.replaceAll("\\s*", ""));
                }
                writeLog("一行list==========================： " + rowList.toString());
                //判断该行是否有空数据
                if (rowList.contains("") || rowList.contains(null) || rowList.contains("null")) {
                    writeLog("拼接错误信息==============================");

                    errBuilder.append(rowList.get(0)).append(", ");
                }

                //拼接sql
                if (myRow != 1) {
                    builder.append(",");
                }
                //姓名转为id
                id = map.get(rowList.get(3));
                if (id == null) {
                    //该姓名不存在
                    return "员工： " + rowList.get(3) + " 不存在";
                }
                builder.append("('").append("0").append("','").append("0").append("','")
                        .append(id).append("','',").append("'','").append("','','").append("0").append("','")
                        .append("','','").append(rowList.get(1)).append("','").append(rowList.get(2)).append("','')");
                //清空list
                rowList.clear();
            }

            writeLog("错误信息errBuilder： " + errBuilder);
            if (errBuilder.length() > 5) {
                //说明某行数据不完整
                errBuilder.append("数据不完整");
                return errBuilder.toString();
            }
            recordSet.execute(builder.toString());

            writeLog("sql===========: " + builder.toString());
            writeLog("批量导入接口结束==========================end");

            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "数据有误";
        }
    }

    /**
     * 获取单元格数据内容为字符串类型的数据
     *
     * @param cell Excel单元格
     * @return String 单元格数据内容
     */
    private static String getCellStringValue(HSSFCell cell) {
        String strCell;
        switch (cell.getCellType()) {
//            case HSSFCell.CELL_TYPE_STRING:
//                strCell = cell.getStringCellValue();
//                break;
//            case HSSFCell.CELL_TYPE_NUMERIC:
//                Double value = cell.getNumericCellValue();
//                BigDecimal bd1 = new BigDecimal(Double.toString(value));
//                strCell = bd1.toPlainString();
//                break;
//            case HSSFCell.CELL_TYPE_BOOLEAN:
//                strCell = String.valueOf(cell.getBooleanCellValue());
//                break;
//            case HSSFCell.CELL_TYPE_BLANK:
//                strCell = "";
//                break;
//            default:
//                strCell = "";
//                break;
        }
      //  return strCell;

        return null;
    }
}
