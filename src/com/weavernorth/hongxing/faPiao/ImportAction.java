package com.weavernorth.hongxing.faPiao;

import com.weaver.general.BaseBean;
import com.weaver.general.TimeUtil;
import com.weavernorth.hongxing.faPiao.vo.FaPiaoVo;
import km.org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import weaver.conn.RecordSet;
import weaver.conn.RecordSetDataSource;
import weaver.file.ExcelParse;
import weaver.file.FileUploadToPath;
import weaver.formmode.interfaces.ImportPreInterfaceAction;
import weaver.formmode.setup.ModeRightInfo;
import weaver.hrm.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 批量导入发票
 */
public class ImportAction extends BaseBean implements ImportPreInterfaceAction {

    private ModeRightInfo modeRightInfo = new ModeRightInfo();

    @Override
    public String checkImportData(FileUploadToPath fileUploadToPath, User user, int ii, ExcelParse excelParse, int i1) {
        try {
            writeLog("批量导入发票接口执行==========================start---" + TimeUtil.getCurrentTimeString());
            HSSFWorkbook wb = excelParse.getWb();
            HSSFSheet sheet = wb.getSheet("主表");
            //总行数
            int numRow = sheet.getLastRowNum();
            HSSFRow row = sheet.getRow(0);
            //总列数
            short numCol = row.getLastCellNum();
            writeLog("总行数 and 总列数=：  " + numRow + ", " + numCol);
            String currentCell;//当前单元格内容
            //一行元素的集合
            List<String> rowList = new ArrayList<String>();
            List<FaPiaoVo> excelList = new ArrayList<FaPiaoVo>();
            for (short i = 1; i <= numRow; i++) {
                FaPiaoVo faPiaoVo = new FaPiaoVo();
                HSSFRow cells = sheet.getRow(i);
                for (short j = 1; j < numCol; j++) {
                    if (cells.getCell(j) == null) {
                        writeLog("当前单元格为null");
                        rowList.add("");
                        continue;
                    }
                    if (cells.getCell(j).getCellType() == HSSFCell.CELL_TYPE_BLANK || cells.getCell(j).getCellType() == HSSFCell.CELL_TYPE_ERROR) {
                        currentCell = "";
                    } else if (cells.getCell(j).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                        currentCell = cells.getCell(j).getStringCellValue();
                    } else {
                        currentCell = NumberToTextConverter.toText(cells.getCell(j).getNumericCellValue());
                    }
                    writeLog("当前单元格内容：" + currentCell);
                    if (currentCell != null) {
                        currentCell = currentCell.replaceAll("\\s*", "");
                    }
                    rowList.add(currentCell);
                }

                writeLog("list列数： " + rowList.size());

                faPiaoVo.setFyzfbm(rowList.get(0));//费用支付编码
                faPiaoVo.setGysbh(rowList.get(1));//供应商编号
                faPiaoVo.setGys(rowList.get(2));//供应商
                faPiaoVo.setHsje(rowList.get(3));//含税金额
                faPiaoVo.setFpbh(rowList.get(4));//发票编号

                faPiaoVo.setFlh(rowList.get(5));//分录号
                faPiaoVo.setYwrq(rowList.get(6));//业务日期
                faPiaoVo.setWlbh(rowList.get(7));//物料编号
                faPiaoVo.setWlmc(rowList.get(8));//物料名称
                faPiaoVo.setSln(rowList.get(9));//数量

                faPiaoVo.setHsdj(rowList.get(10));//含税单价
                faPiaoVo.setBhsje(rowList.get(11));//不含税金额
                faPiaoVo.setSl(rowList.get(12));//税率
                faPiaoVo.setSe(rowList.get(13));//税额

                excelList.add(faPiaoVo);
                rowList.clear();
            }
            //当前年份
            int currentYear = Integer.parseInt(weaver.general.TimeUtil.getCurrentDateString().substring(0, 4));
            RecordSetDataSource recordSet = new RecordSetDataSource("orcl");
            //判断
            for (FaPiaoVo vo : excelList) {
                writeLog(vo.toString() + "\r\n");
                if (check(vo)) {
                    vo.insertSuccess();
                    writeLog("插入成功信息");
                    //更新费用支付表 uf_fyzf
                    recordSet.executeSql("update uf_fyzf set kpje = '" + vo.getHsje() + "' where fyzfbm = '" + vo.getFyzfbm() + "'");
                    recordSet.executeSql("select * from uf_fyzf where fyzfbm = '" + vo.getFyzfbm() + "'");
                    int fylb = 0;//费用类别
                    //赋权
                    if (recordSet.next()) {
                        fylb = recordSet.getInt("fylb");
                        int id = recordSet.getInt("id");//数据id
                        int modeId = recordSet.getInt("formmodeid");//模块id
                        modeRightInfo.setNewRight(true);
                        modeRightInfo.editModeDataShare(1, modeId, id);//创建人id， 模块id， 该条数据id
                        writeLog("模块id： " + modeId);
                        writeLog("id： " + id);
                    }
                    //====================插入报表====================
                    writeLog("导入发票，插入报表开始=============");
                    recordSet.executeSql("select * from uf_scfyzzbb where nd = '" + currentYear + "' and fylb = '" + fylb + "'");
                    if (recordSet.next()) {
                        int id = recordSet.getInt("id");//数据id
                        int modeId = recordSet.getInt("formmodeid");//模块id

                        double ykp = recordSet.getDouble("ykp") < 0 ? 0 : recordSet.getDouble("ykp");//已开票金额
                        double ysq = recordSet.getDouble("ysq") < 0 ? 0 : recordSet.getDouble("ysq");//已申请金额
                        double ykpAll = ykp + Double.valueOf(vo.getHsje());//已开票总金额
                        double wkp = ysq - ykpAll;//未开票

                        String sql = "update uf_scfyzzbb set ykp = '" + ykpAll + "', wkp = '" + wkp + "' where nd = '" + currentYear + "' and fylb = '" + fylb + "'";
                        this.writeLog("更新报表sql： " + sql);
                        recordSet.executeSql(sql);
                        //赋权
                        modeRightInfo.setNewRight(true);
                        modeRightInfo.editModeDataShare(1, modeId, id);//创建人id， 模块id， 该条数据id
                    }
                } else {
                    vo.insertErr();
                    writeLog("插入失败信息");
                }
            }
            writeLog("批量导入接口结束==========================end");
            return "";
        } catch (Exception e) {
            writeLog("批量导入发票接口异常：" + e);
            return "数据有误";
        }
    }

    /**
     * 插入校验
     */
    private boolean check(FaPiaoVo vo) {
        writeLog("check 执行----start");
        try {
            RecordSet recordSet = new RecordSet();
            recordSet.executeSql("select * from uf_xsfp where fpbh = '" + StringUtils.trimToEmpty(vo.getFpbh()) + "'");
            if (recordSet.next()) {
                vo.setSbyy("发票编号重复");
                return false;
            }
            recordSet.executeSql("select * from uf_fyzf where fyzfbm = '" + vo.getFyzfbm() + "'");
            if (recordSet.next()) {
                double zfje = recordSet.getDouble("zfje") < 0 ? 0 : recordSet.getDouble("zfje");//支付金额
                writeLog("zfje===========： " + zfje);
                String gys = recordSet.getString("khmc1");//供应商
                writeLog("gys===========： " + gys);
                if (zfje != Double.valueOf(vo.getHsje())) {
                    vo.setSbyy("金额与支付编号对应的支付金额不同");
                    return false;
                } else if (!StringUtils.trimToEmpty(gys).equals(StringUtils.trimToEmpty(vo.getGys()))) {
                    writeLog("供应商===========： " + StringUtils.trimToEmpty(gys));
                    vo.setSbyy("供应商不相同");
                    return false;
                }
            } else {
                vo.setSbyy("没有对应的支付流程");
                return false;
            }
        } catch (Exception e) {
            writeLog("校验异常： " + e);
            return false;
        }
        writeLog("check 执行----end");
        return true;
    }
}
