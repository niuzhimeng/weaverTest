package com.weavernorth.zgsy.baseInfo;

import com.weavernorth.zgsy.webUtil.*;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by zhaohr on 2018/9/16.
 */
public class CW02TestAction extends BaseBean implements Action {
    public Calendar dataToCalendar(String date) {
        Calendar calendar = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        try {
            date1 = format.parse(date);
            calendar = Calendar.getInstance();
            calendar.setTime(date1);
        } catch (ParseException e) {
            this.writeLog("转换问题：  " + e);
        }

        return calendar;

    }

    @Override
    public String execute(RequestInfo requestInfo) {
        String requestid = requestInfo.getRequestid();
        RecordSet rs = new RecordSet();
        RecordSet rs1 = new RecordSet();

        RecordSet rs2 = new RecordSet();//查库
        RecordSet rs3 = new RecordSet();//查库
        RecordSet rs4 = new RecordSet();//查库
        rs.execute("select * from formtable_main_40 where requestid= '" + requestid + "'");
        if (rs.next()) {
            String mid = rs.getString("id");
            writeLog("主表ID-------------->" + mid);
            String sqrq = rs.getString("sqrq");
            writeLog("sqrq-------------->" + sqrq);
            String lcbh = rs.getString("lcbh");
            writeLog("流程编号--------->" + lcbh);
            String fycdbm = rs.getString("fycdbm");
            writeLog("费用承担部门--------->" + fycdbm);//两个部门？？？
            int resourceId = rs.getInt("resourceId");
            writeLog("申请人-------------->" + resourceId);
            String fygsr = rs.getString("fygsr");
            writeLog("费用归属人--------->" + fygsr);
            double clfzje = rs.getDouble("clfzje");
            writeLog("差旅费总金额------->" + clfzje);
            String cwshrq = rs.getString("cwshrq");
            writeLog("财务审核日期--------->" + cwshrq);
            String xm = rs.getString("xm");
            writeLog("项目--------->" + xm);
            String yfxm = rs.getString("yfxm");
            writeLog("研发项目--------->" + yfxm);
            String bz = rs.getString("bz");
            writeLog("备注--------->" + bz);
            double zshj = rs.getDouble("zshj");
            writeLog("住宿合计------->" + zshj);
            rs2.execute("select workcode,departmentid from hrmresource where id = '" + fygsr + "'");
            rs2.next();
            String workcode = rs2.getString("workcode");
            this.writeLog("人员编码---------->" + workcode);
            rs3.execute("select departmentcode from hrmdepartment where id = '" + fycdbm + "'");
            rs3.next();
            String departmentcode = rs3.getString("departmentcode");
            this.writeLog("销售部门2------->" + departmentcode);

            try {
                LoadServiceLocator loadServiceLocator = new LoadServiceLocator();
                LoadServiceSoap_PortType soap = loadServiceLocator.getLoadServiceSoap();
                Voucher voucher = new Voucher();
                voucher.setFlowNumber(lcbh);
                voucher.setFReference(bz);
                voucher.setFVoucherDate(dataToCalendar(sqrq));
                voucher.setFPreparerNumber(100209);

                VoucherEntry[] voucherEntries = new VoucherEntry[5];


                rs4.execute("select * from formtable_main_40_dt2 where mainid = '" + mid + " '");
                int i = 0;
                while (rs4.next()) {
                    String cpx = rs4.getString("cpx");
                    this.writeLog("cpx----------" + cpx);
                    int zb = rs4.getInt("zb");
                    this.writeLog("zb----------" + zb);
                    double other = clfzje - zshj;
                    VoucherEntry voucherEntry2 = new VoucherEntry();
                    voucherEntry2.setFExplanation("其他费用报销");
                    if (yfxm.equals("00") && (departmentcode.contains("1023") || departmentcode.contains("1007"))) {
                        voucherEntry2.setFAccountNumber("5508.11.03");
                    } else if (yfxm.equals("00") && !departmentcode.contains("1023") && !departmentcode.contains("1007")) {
                        voucherEntry2.setFAccountNumber("5507.11.03");
                    } else if (!yfxm.equals("00")) {
                        voucherEntry2.setFAccountNumber("4107.01.21.03");
                    }

                    voucherEntry2.setFDC(1);//FDC？？？？
                    voucherEntry2.setFAmount(other * zb * 0.01);
                    this.writeLog("other * zb * 0.01" + "------------" + other * zb * 0.01);
                    voucherEntries[i] = voucherEntry2;
                    voucher.setVoucherEntries(voucherEntries);
                    ItemDetailEntry[] itemDetailEntries1 = new ItemDetailEntry[8];
                    ItemDetailEntry itemDetailEntry11 = new ItemDetailEntry();
                    ItemDetailEntry itemDetailEntry111 = new ItemDetailEntry();
                    ItemDetailEntry itemDetailEntry21 = new ItemDetailEntry();
                    ItemDetailEntry itemDetailEntry31 = new ItemDetailEntry();
                    ItemDetailEntry itemDetailEntry41 = new ItemDetailEntry();
                    ItemDetailEntry itemDetailEntry51 = new ItemDetailEntry();
                    ItemDetailEntry itemDetailEntry61 = new ItemDetailEntry();
                    ItemDetailEntry itemDetailEntry71 = new ItemDetailEntry();

                    itemDetailEntry11.setFItemClassNumber("029");
                    itemDetailEntry11.setFItemNumber(cpx);
                    itemDetailEntry11.setFItemName("");
                    itemDetailEntries1[0] = itemDetailEntry11;
                    itemDetailEntry111.setFItemClassNumber("002");
                    itemDetailEntry111.setFItemNumber(departmentcode);
                    itemDetailEntry111.setFItemName("");
                    itemDetailEntries1[1] = itemDetailEntry111;
                    itemDetailEntry21.setFItemClassNumber("003");
                    itemDetailEntry21.setFItemName("");
                    itemDetailEntry21.setFItemNumber(workcode);
                    itemDetailEntries1[2] = itemDetailEntry21;
                    itemDetailEntry31.setFItemClassNumber("009");
                    itemDetailEntry31.setFItemName("");
                    itemDetailEntry31.setFItemNumber(xm);
                    itemDetailEntries1[3] = itemDetailEntry31;
                    itemDetailEntry41.setFItemClassNumber("018");
                    itemDetailEntry41.setFItemNumber("00");
                    itemDetailEntry41.setFItemName("");
                    itemDetailEntries1[4] = itemDetailEntry41;
                    itemDetailEntry51.setFItemClassNumber("019");
                    itemDetailEntry51.setFItemNumber("00");
                    itemDetailEntry51.setFItemName("");
                    itemDetailEntries1[5] = itemDetailEntry51;
                    itemDetailEntry61.setFItemClassNumber("004");
                    itemDetailEntry61.setFItemNumber("00");
                    itemDetailEntry61.setFItemName("");
                    itemDetailEntries1[6] = itemDetailEntry61;
                    if (!yfxm.equals("00") && (!departmentcode.contains("1023") || !departmentcode.contains("1007"))) {
                        itemDetailEntry71.setFItemClassNumber("028");
                        itemDetailEntry71.setFItemNumber(yfxm);
                        itemDetailEntry71.setFItemName("");
                        itemDetailEntries1[7] = itemDetailEntry71;
                    }
                    voucherEntry2.setItemDetailEntries(itemDetailEntries1);


                    //住宿费
                    VoucherEntry voucherEntry = new VoucherEntry();
                    voucherEntry.setFExplanation("申请人差旅报销");
                    if (yfxm.equals("00") && (departmentcode.contains("1023") || departmentcode.contains("1007"))) {
                        voucherEntry.setFAccountNumber("5508.11.01");
                    } else if (yfxm.equals("00") && !departmentcode.contains("1023") && !departmentcode.contains("1007")) {
                        voucherEntry.setFAccountNumber("5507.11.01");
                    } else if (!yfxm.equals("00")) {
                        voucherEntry.setFAccountNumber("4107.01.21.01");
                    }
                    voucherEntry.setFDC(1);//FDC？？？？
                    voucherEntry.setFAmount(zshj * zb * 0.01);
                    this.writeLog("zshj * zb * 0.01" + "-------------" + zshj * zb * 0.01 + i);
                    i++;
                    voucherEntries[i] = voucherEntry;
                    voucher.setVoucherEntries(voucherEntries);
                    ItemDetailEntry[] itemDetailEntries = new ItemDetailEntry[8];
                    ItemDetailEntry itemDetailEntry = new ItemDetailEntry();
                    ItemDetailEntry itemDetailEntry1 = new ItemDetailEntry();
                    ItemDetailEntry itemDetailEntry2 = new ItemDetailEntry();
                    ItemDetailEntry itemDetailEntry3 = new ItemDetailEntry();
                    ItemDetailEntry itemDetailEntry4 = new ItemDetailEntry();
                    ItemDetailEntry itemDetailEntry5 = new ItemDetailEntry();
                    ItemDetailEntry itemDetailEntry6 = new ItemDetailEntry();
                    ItemDetailEntry itemDetailEntry7 = new ItemDetailEntry();
                    itemDetailEntry.setFItemClassNumber("029");
                    itemDetailEntry.setFItemNumber(cpx);
                    itemDetailEntry.setFItemName("");
                    itemDetailEntries[0] = itemDetailEntry;
                    itemDetailEntry1.setFItemClassNumber("002");
                    itemDetailEntry1.setFItemNumber(departmentcode);
                    itemDetailEntry1.setFItemName("");
                    itemDetailEntries[1] = itemDetailEntry1;
                    itemDetailEntry2.setFItemClassNumber("003");
                    itemDetailEntry2.setFItemName("");
                    itemDetailEntry2.setFItemNumber(workcode);
                    itemDetailEntries[2] = itemDetailEntry2;
                    itemDetailEntry3.setFItemClassNumber("009");
                    itemDetailEntry3.setFItemName("");
                    itemDetailEntry3.setFItemNumber(xm);
                    itemDetailEntries[3] = itemDetailEntry3;
                    itemDetailEntry4.setFItemClassNumber("018");
                    itemDetailEntry4.setFItemNumber("00");
                    itemDetailEntry4.setFItemName("");
                    itemDetailEntries[4] = itemDetailEntry4;
                    itemDetailEntry5.setFItemClassNumber("019");
                    itemDetailEntry5.setFItemNumber("00");
                    itemDetailEntry5.setFItemName("");
                    itemDetailEntries[5] = itemDetailEntry5;
                    itemDetailEntry6.setFItemClassNumber("004");
                    itemDetailEntry6.setFItemNumber("00");
                    itemDetailEntry6.setFItemName("");
                    itemDetailEntries[6] = itemDetailEntry6;
                    if (!yfxm.equals("00") && (!departmentcode.contains("1023") || !departmentcode.contains("1007"))) {
                        itemDetailEntry7.setFItemClassNumber("028");
                        itemDetailEntry7.setFItemNumber(yfxm);
                        itemDetailEntry7.setFItemName("");
                        itemDetailEntries[7] = itemDetailEntry7;
                    }

                    voucherEntry.setItemDetailEntries(itemDetailEntries);

                }
                i++;
                this.writeLog("i---------" + i);


                VoucherEntry voucherEntry1 = new VoucherEntry();
                voucherEntry1.setFExplanation("申请人差旅报销");
                voucherEntry1.setFAccountNumber("1002.01");
                voucherEntry1.setFDC(0);//FDC？？？？
                voucherEntry1.setFAmount(clfzje);
                voucherEntries[4] = voucherEntry1;
                voucher.setVoucherEntries(voucherEntries);
                WebResult webResult = soap.saveVoucher(voucher);
                this.writeLog("---------------" + webResult.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        return SUCCESS;
    }
}
