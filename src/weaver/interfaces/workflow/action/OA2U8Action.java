package weaver.interfaces.workflow.action;

import org.apache.commons.lang.StringEscapeUtils;
import weaver.conn.RecordSet;
import weaver.conn.RecordSetDataSource;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.soa.workflow.request.*;

import java.text.DecimalFormat;
import java.util.*;

public class OA2U8Action extends BaseBean implements Action {

    @Override
    public String execute(RequestInfo requestInfo) {
        String requestid = requestInfo.getRequestid();
        try {
            if (insertU8(requestInfo)) {
                return SUCCESS;
            } else {
                return "0";
            }
        } catch (Exception e) {
            new BaseBean().writeLog(e);
            requestInfo.getRequestManager().setMessageid(requestid);//提醒信息id
            requestInfo.getRequestManager().setMessagecontent("action执行异常：" + e.getMessage());//提醒信息内容
            return Action.FAILURE_AND_CONTINUE;
        }
    }

    public boolean insertU8(RequestInfo requestInfo) throws Exception {

        String requestid = requestInfo.getRequestid();
        boolean flag = true;

        DecimalFormat df2 = new DecimalFormat("##########################################0.00");

        Map mainTableDataMap = new HashMap();
        List dtlTableDataList1 = new ArrayList();
        List dtlTableDataList2 = new ArrayList();

        Property[] props = requestInfo.getMainTableInfo().getProperty();
        for (int i = 0; i < props.length; i++) {
            String fieldname = props[i].getName().toLowerCase();
            String fieldval = Util.null2String(props[i].getValue());
            log("requestid=" + requestid + ";getMainTableInfo[" + i + "] fieldname=" + fieldname + ";fieldval=" + fieldval + ";");
            mainTableDataMap.put(fieldname, fieldval);
        }

        DetailTable dtltable1 = requestInfo.getDetailTableInfo().getDetailTable(0);
        Row[] rows1 = dtltable1.getRow();
        for (int i = 0; i < rows1.length; i++) {
            Row row = rows1[i];
            Map onerow = new HashMap();
            dtlTableDataList1.add(onerow);
            Cell[] cells = row.getCell();
            for (int j = 0; j < cells.length; j++) {
                Cell cell = cells[j];
                onerow.put(cell.getName().toLowerCase(), Util.null2String(cell.getValue()));
            }
        }

        DetailTable dtltable2 = requestInfo.getDetailTableInfo().getDetailTable(1);
        Row[] rows2 = dtltable2.getRow();
        for (int i = 0; i < rows2.length; i++) {
            Row row = rows2[i];
            Map onerow = new HashMap();
            dtlTableDataList2.add(onerow);
            Cell[] cells = row.getCell();
            for (int j = 0; j < cells.length; j++) {
                Cell cell = cells[j];
                onerow.put(cell.getName().toLowerCase(), Util.null2String(cell.getValue()));
            }
        }

        RecordSetDataSource rsds = new RecordSetDataSource("U8");//读起u8数据源
        RecordSet RecordSet = new RecordSet();

        Calendar today = Calendar.getInstance();
        String currentdate = Util.add0(today.get(Calendar.YEAR), 4) + "-" +
                Util.add0(today.get(Calendar.MONTH) + 1, 2) + "-" +
                Util.add0(today.get(Calendar.DAY_OF_MONTH), 2);
        String currenttime = Util.add0(today.get(Calendar.HOUR_OF_DAY), 2) + ":" +
                Util.add0(today.get(Calendar.MINUTE), 2) + ":" +
                Util.add0(today.get(Calendar.SECOND), 2);

        String[] currentdateArray = currentdate.split("-");
        String iyear = currentdateArray[0];
        String iYPeriod = currentdateArray[0] + currentdateArray[1];
        String tvouchtime = currentdate + " " + currenttime;

        String zph = Util.null2String((String) mainTableDataMap.get("zph"));//支票号

        String attachment_number = Util.null2String((String) mainTableDataMap.get("fdjs"));//附单据数

        String voucher_type = "记";
        String fiscal_year = Util.null2String((String) mainTableDataMap.get("bxsj")).substring(0, 4);//会计年度
        String accounting_period = currentdate.substring(5, 7);//会计期间

        String enter = requestInfo.getRequestManager().getUser().getLoginid();//制单人(用登陆名)


        String date = currentdate;//制单日期
        String isignseq = "";//"1";//凭证类别的id
        String coutno_id = "";//"GL0000000000130";//
        String ino_id = "";//"1016";//
        //--取得所有凭证流水号
        String u8sql = "Select 'GL'+RIGHT('10000000000000'+convert(varchar,MAX(SUBSTRING(coutno_id, 3, 20))+1),13) From GL_accvouch where coutno_id like '%GL%' ";
        rsds.executeSql(u8sql);
        if (rsds.next()) {
            coutno_id = Util.null2String(rsds.getString(1));
        }
        if ("".equals(coutno_id)) {
            coutno_id = "GL0000000000001";
        }
        //--取得对应凭证类型流水
        u8sql = "select isnull(max(ino_id),0) +1 from GL_accvouch where csign = '" + voucher_type + "' and iperiod = '" + (today.get(Calendar.MONTH) + 1) + "'";
        log("max(ino_id):" + u8sql);
        rsds.executeSql(u8sql);
        while (rsds.next()) {
            ino_id = Util.null2String(rsds.getString(1));
        }
        //--取得凭证类型的id
        u8sql = "Select isignseq From dsign where csign = '" + voucher_type + "'";
        rsds.executeSql(u8sql);
        while (rsds.next()) {
            isignseq = Util.null2String(rsds.getString(1));
        }

        log("attachment_number:" + attachment_number);
        log("voucher_type:" + voucher_type);
        log("fiscal_year:" + fiscal_year);
        log("accounting_period:" + accounting_period);
        log("enter:" + enter);
        log("date:" + date);
        log("isignseq:" + isignseq);
        log("coutno_id:" + coutno_id);
        log("ino_id:" + ino_id);
        log("**************************************************");

        if (isignseq.equals("") || coutno_id.equals("") || ino_id.equals("")) {
            log("凭证流水号为空、凭证类型流水为空、凭证类型的id为空");
            return false;
        }

        String bxr = Util.null2String((String) mainTableDataMap.get("bxr"));//OA系统报销人字段名称bxr；
        log("OA系统报销人字段bxr：" + bxr);
        String bxr_workcode = "";
        if (Util.getIntValue(bxr) > 0) {
            String itemcodesql = "select a.workcode from hrmresource a where a.id = " + Util.getIntValue(bxr);
            log("OA系统报销人字段工号sql：" + itemcodesql);
            RecordSet.execute(itemcodesql);
            if (RecordSet.next()) {
                bxr_workcode = RecordSet.getString("workcode");
            }
        }
        log("OA系统报销人字段工号bxr_workcode：" + bxr_workcode);

        String bxlx = Util.null2String((String) mainTableDataMap.get("bxje1"));//报销类型
        double clbxze = Util.getDoubleValue((String) mainTableDataMap.get("bxze"), 0);//差旅报销总额
        double qtbxze = Util.getDoubleValue((String) mainTableDataMap.get("qtfy"), 0);//其他报销总额
        double clfjxs = Util.getDoubleValue((String) mainTableDataMap.get("clfjxs"), 0);//差旅费进项税
        boolean isclbx = false;

        List tmplist = null;

        if (clbxze > 0) {
            tmplist = dtlTableDataList1;
            isclbx = true;
        }
        if (qtbxze > 0) {
            tmplist = dtlTableDataList2;
            isclbx = false;
        }

        int i_dept_id = Util.getIntValue((String) mainTableDataMap.get("bm"), 0);//辅助核算(部门)
        String deptcodesql = "select departmentcode from hrmdepartment where id=" + i_dept_id;
        RecordSet.execute(deptcodesql);
        String dept_id = "";
        while (RecordSet.next()) {
            dept_id = Util.null2String(RecordSet.getString("departmentcode")).trim();
        }
        if ("0201".equalsIgnoreCase(dept_id)) {
            throw new Exception("请更改部门！当前部门编码：" + dept_id);
        }
        if (dept_id.equals("")) {
            dept_id = "null";
        } else {
            dept_id = "'" + StringEscapeUtils.escapeSql(dept_id) + "'";
        }


        String document_date = "";
        document_date = currentdate;


        String allabstractstr = "付";
        String zsfjxsn;
        int rowsize = 0;
        if (tmplist != null) {
            for (int i = 0; i < tmplist.size(); i++) {
                Map onerow = (Map) tmplist.get(i);

                rowsize = (i + 1);
                String entry_id = rowsize + "";
                String account_code = "";
                String abstractstr = "";
                String natural_debit_currency = "";
                String natural_credit_currency = "";


                String personnel_id = "";
                String item_id = "";
                String item_class = "";

                if (isclbx) {
                    //差旅报销对应科目由660133改为660131
                    account_code = "660131";
                    //abstractstr = (String)onerow.get("ccsy1");
                    abstractstr = "差旅费";
                    natural_debit_currency = df2.format(clbxze);
                    natural_credit_currency = "0";
                } else {
                    account_code = (String) onerow.get("bxlb");
                    String itemcodesql = "select itemcode from workflow_SelectItem where fieldid=(select id from workflow_billfield where billid=(select formid from workflow_base where id=" + requestInfo.getWorkflowid() + ") and viewtype='1' and LOWER(fieldname)='bxlb') and selectvalue=" + account_code;
                    RecordSet.execute(itemcodesql);
                    while (RecordSet.next()) {
                        account_code = RecordSet.getString("itemcode");
                    }
                    abstractstr = (String) onerow.get("zy");
                    natural_debit_currency = (String) onerow.get("zje");
                    natural_credit_currency = "0";
                }
                allabstractstr += abstractstr + "、";

                personnel_id = "";
                item_id = "";
                item_class = "";


                log("entry_id:" + entry_id);
                log("account_code:" + account_code);
                log("abstractstr:" + abstractstr);
                log("natural_debit_currency:" + natural_debit_currency);
                log("natural_credit_currency:" + natural_credit_currency);
                log("document_date:" + document_date);
                log("personnel_id:" + personnel_id);
                log("dept_id:" + dept_id);
                log("item_id:" + item_id);
                log("item_class:" + item_class);
                log("isclbx:" + isclbx);
                log("clfjxs:" + df2.format(clfjxs));


                if (personnel_id.equals("")) {
                    personnel_id = "null";
                } else {
                    personnel_id = "'" + personnel_id + "'";
                }

                if (item_id.equals("")) {
                    item_id = "null";
                } else {
                    item_id = "'" + item_id + "'";
                }

                if (item_class.equals("")) {
                    item_class = "null";
                } else {
                    item_class = "'" + item_class + "'";
                }

                String sql = "Insert Into GL_accvouch(iperiod,csign,isignseq,coutno_id,ino_id,inid,dbill_date," +
                        "cbill,cdigest,ccode,md,mc,bdelete,doutbilldate,idoc,citem_id,citem_class,cdept_id,cperson_id" +
                        ", iyear, iYPeriod,tvouchtime, cn_id)" +
                        "values(" + accounting_period + ",'" + voucher_type + "'," + isignseq + ",'" + coutno_id + "','" + ino_id + "'," + entry_id + ",'" + date + " 00:00:00.000'," +
                        "'" + enter + "','" + abstractstr + "','" + account_code + "'," + natural_debit_currency + "," + natural_credit_currency + ",0,'" + date + " 00:00:00.000'," + attachment_number + "," + item_id + "," + item_class + "," + dept_id + "," + personnel_id + "" +
                        ",'" + StringEscapeUtils.escapeSql(iyear) + "','" + StringEscapeUtils.escapeSql(iYPeriod) + "','" + StringEscapeUtils.escapeSql(tvouchtime) + "', '" + StringEscapeUtils.escapeSql(zph) + "')";
                flag = rsds.executeSql(sql);
                log("flag=" + flag + ";sql--" + (i + 1) + "==" + sql);
                if (!flag) {
                    return false;
                }

                log("isclbx=" + isclbx + ";clfjxs=" + df2.format(clfjxs) + ";**************************************************");
                //差旅报销只有一行
                if (isclbx) {
                    if (clfjxs != 0.0) {
                        rowsize++;
                        entry_id = rowsize + "";

                        zsfjxsn = (String) onerow.get("zsfjxsn");
                        if (zsfjxsn.equals("0")) {
                            account_code = "222110010415";
                        }

                        if (zsfjxsn.equals("1")) {
                            account_code = "222110010502";
                        }

                        if (zsfjxsn.equals("2")) {
                            account_code = "222110010801";
                        }
                        abstractstr = "差旅费进项税";
                        natural_debit_currency = df2.format(clfjxs);
                        natural_credit_currency = "0";

                        String sql_clfjxs = "Insert Into GL_accvouch(iperiod,csign,isignseq,coutno_id,ino_id,inid,dbill_date," +
                                "cbill,cdigest,ccode,md,mc,bdelete,doutbilldate,idoc,citem_id,citem_class,cdept_id,cperson_id" +
                                ", iyear, iYPeriod,tvouchtime, cn_id)" +
                                "values(" + accounting_period + ",'" + voucher_type + "'," + isignseq + ",'" + coutno_id + "','" + ino_id + "'," + entry_id + ",'" + date + " 00:00:00.000'," +
                                "'" + enter + "','" + abstractstr + "','" + account_code + "'," + natural_debit_currency + "," + natural_credit_currency + ",0,'" + date + " 00:00:00.000'," + attachment_number + "," + item_id + "," + item_class + "," + dept_id + "," + personnel_id + "" +
                                ",'" + StringEscapeUtils.escapeSql(iyear) + "','" + StringEscapeUtils.escapeSql(iYPeriod) + "','" + StringEscapeUtils.escapeSql(tvouchtime) + "', '" + StringEscapeUtils.escapeSql(zph) + "')";
                        flag = rsds.executeSql(sql_clfjxs);
                        log("flag=" + flag + ";sql_clfjxs--" + (i + 1) + "==" + sql_clfjxs);
                        if (!flag) {
                            return false;
                        }
                    }

                    break;
                }
            }
            if (allabstractstr.endsWith("、")) {
                allabstractstr = allabstractstr.substring(0, allabstractstr.length() - 1);
            }
        }

        double blje = Util.getDoubleValue((String) mainTableDataMap.get("blje"), 0);//补领金额
        double thje = Util.getDoubleValue((String) mainTableDataMap.get("thje"), 0);//退还金额
        double yjje = Util.getDoubleValue((String) mainTableDataMap.get("yjlf"), 0);//预借金额
        String bxfs = (String) mainTableDataMap.get("bxfs");//报销方式 0现金；1银行存款

        double tmpzje = isclbx ? clbxze : qtbxze;

        String abstractstr = isclbx ? "付差旅费" : allabstractstr;
        String abstractstr2 = "冲职工借款";
//			String abstractstr3 = isclbx ? "付差旅费" : "退还职工借款";
        String abstractstr3 = "退还职工借款";
        String item_id = "null";
        String item_class = "null";

        String account_code = "";
        String natural_debit_currency = "";
        String natural_credit_currency = "";

        //当预借金额>0，且总金额>预借金额，需要进行补领，补领金额>0时
        if (yjje > 0 && tmpzje > yjje && blje > 0) {
            rowsize++;
            account_code = "12210201";
            natural_debit_currency = "0";
            natural_credit_currency = df2.format(yjje);

            String personnel_id = "null";
            if (!"".equals(bxr_workcode)) {
                personnel_id = "'" + StringEscapeUtils.escapeSql(bxr_workcode) + "'";
            } else {
                personnel_id = "null";
            }

            String sql = "Insert Into GL_accvouch(iperiod,csign,isignseq,coutno_id,ino_id,inid,dbill_date," +
                    "cbill,cdigest,ccode,md,mc,bdelete,doutbilldate,idoc,citem_id,citem_class,cdept_id,cperson_id" +
                    ", iyear, iYPeriod,tvouchtime, cn_id)" +
                    "values(" + accounting_period + ",'" + voucher_type + "'," + isignseq + ",'" + coutno_id + "','" + ino_id + "'," + rowsize + ",'" + date + " 00:00:00.000'," +
                    "'" + enter + "','" + abstractstr2 + "','" + account_code + "'," + natural_debit_currency + "," + natural_credit_currency + ",0,'" + date + " 00:00:00.000'," + attachment_number + "," + item_id + "," + item_class + "," + dept_id + "," + personnel_id + "" +
                    ",'" + StringEscapeUtils.escapeSql(iyear) + "','" + StringEscapeUtils.escapeSql(iYPeriod) + "','" + StringEscapeUtils.escapeSql(tvouchtime) + "', '" + StringEscapeUtils.escapeSql(zph) + "')";
            flag = rsds.executeSql(sql);
            log("(当预借金额>0，且总金额>预借金额，需要进行补领，补领金额>0时)--1==" + sql);
            if (!flag) {
                return false;
            }


            rowsize++;
            if (bxfs.equals("0")) {
                account_code = "1001";
            } else if (bxfs.equals("1")) {
                account_code = "100202";
            } else {
                account_code = "";
            }
            natural_debit_currency = "0";
            natural_credit_currency = df2.format(blje);
            sql = "Insert Into GL_accvouch(iperiod,csign,isignseq,coutno_id,ino_id,inid,dbill_date," +
                    "cbill,cdigest,ccode,md,mc,bdelete,doutbilldate,idoc,citem_id,citem_class,cdept_id,cperson_id" +
                    ", iyear, iYPeriod,tvouchtime, cn_id)" +
                    "values(" + accounting_period + ",'" + voucher_type + "'," + isignseq + ",'" + coutno_id + "','" + ino_id + "'," + rowsize + ",'" + date + " 00:00:00.000'," +
                    "'" + enter + "','" + abstractstr + "','" + account_code + "'," + natural_debit_currency + "," + natural_credit_currency + ",0,'" + date + " 00:00:00.000'," + attachment_number + "," + item_id + "," + item_class + "," + dept_id + "," + personnel_id + "" +
                    ",'" + StringEscapeUtils.escapeSql(iyear) + "','" + StringEscapeUtils.escapeSql(iYPeriod) + "','" + StringEscapeUtils.escapeSql(tvouchtime) + "', '" + StringEscapeUtils.escapeSql(zph) + "')";
            flag = rsds.executeSql(sql);
            log("(当预借金额>0，且总金额>预借金额，需要进行补领，补领金额>0时)--2==" + sql);
            if (!flag) {
                return false;
            }
        }

        //当预借金额>0，且总金额=预借金额，不补领，补领金额x1=0时
        if (yjje > 0 && tmpzje == yjje) {
            rowsize++;
            account_code = "12210201";
            natural_debit_currency = "0";
            natural_credit_currency = df2.format(yjje);

            String personnel_id = "null";
            if (!"".equals(bxr_workcode)) {
                personnel_id = "'" + StringEscapeUtils.escapeSql(bxr_workcode) + "'";
            } else {
                personnel_id = "null";
            }

            String sql = "Insert Into GL_accvouch(iperiod,csign,isignseq,coutno_id,ino_id,inid,dbill_date," +
                    "cbill,cdigest,ccode,md,mc,bdelete,doutbilldate,idoc,citem_id,citem_class,cdept_id,cperson_id, iyear, iYPeriod,tvouchtime, cn_id)" +
                    "values(" + accounting_period + ",'" + voucher_type + "'," + isignseq + ",'" + coutno_id + "','" + ino_id + "'," + rowsize + ",'" + date + " 00:00:00.000'," +
                    "'" + enter + "','" + abstractstr2 + "','" + account_code + "'," + natural_debit_currency + "," + natural_credit_currency + ",0,'" + date + " 00:00:00.000'," + attachment_number + "," + item_id + "," + item_class + "," + dept_id + "," + personnel_id + "" +
                    ",'" + StringEscapeUtils.escapeSql(iyear) + "','" + StringEscapeUtils.escapeSql(iYPeriod) + "','" + StringEscapeUtils.escapeSql(tvouchtime) + "', '" + StringEscapeUtils.escapeSql(zph) + "')";
            flag = rsds.executeSql(sql);
            log("(当预借金额>0，且总金额=预借金额，不补领，补领金额x1=0时)--1==" + sql);
            if (!flag) {
                return false;
            }
        }

        //当预借金额>0，且总金额<预借金额，需要退还，退还金额x2>0时
        if (yjje > 0 && tmpzje < yjje && thje > 0) {
            rowsize++;
            account_code = "12210201";
            natural_debit_currency = "0";
            natural_credit_currency = df2.format(yjje);

            String personnel_id = "null";
            if (!"".equals(bxr_workcode)) {
                personnel_id = "'" + StringEscapeUtils.escapeSql(bxr_workcode) + "'";
            } else {
                personnel_id = "null";
            }

            String sql = "Insert Into GL_accvouch(iperiod,csign,isignseq,coutno_id,ino_id,inid,dbill_date," +
                    "cbill,cdigest,ccode,md,mc,bdelete,doutbilldate,idoc,citem_id,citem_class,cdept_id,cperson_id, iyear, iYPeriod,tvouchtime, cn_id)" +
                    "values(" + accounting_period + ",'" + voucher_type + "'," + isignseq + ",'" + coutno_id + "','" + ino_id + "'," + rowsize + ",'" + date + " 00:00:00.000'," +
                    "'" + enter + "','" + abstractstr2 + "','" + account_code + "'," + natural_debit_currency + "," + natural_credit_currency + ",0,'" + date + " 00:00:00.000'," + attachment_number + "," + item_id + "," + item_class + "," + dept_id + "," + personnel_id + "" +
                    ",'" + StringEscapeUtils.escapeSql(iyear) + "','" + StringEscapeUtils.escapeSql(iYPeriod) + "','" + StringEscapeUtils.escapeSql(tvouchtime) + "', '" + StringEscapeUtils.escapeSql(zph) + "')";
            flag = rsds.executeSql(sql);
            log("(当预借金额>0，且总金额<预借金额，需要退还，退还金额x2>0时)--1==" + sql);
            if (!flag) {
                return false;
            }

            rowsize++;
            if (bxfs.equals("0")) {
                account_code = "1001";
            } else if (bxfs.equals("1")) {
                account_code = "100202";
            } else {
                account_code = "";
            }
            natural_debit_currency = df2.format(thje);
            natural_credit_currency = "0";

            sql = "Insert Into GL_accvouch(iperiod,csign,isignseq,coutno_id,ino_id,inid,dbill_date," +
                    "cbill,cdigest,ccode,md,mc,bdelete,doutbilldate,idoc,citem_id,citem_class,cdept_id,cperson_id, iyear, iYPeriod,tvouchtime, cn_id)" +
                    "values(" + accounting_period + ",'" + voucher_type + "'," + isignseq + ",'" + coutno_id + "','" + ino_id + "'," + rowsize + ",'" + date + " 00:00:00.000'," +
                    "'" + enter + "','" + abstractstr3 + "','" + account_code + "'," + natural_debit_currency + "," + natural_credit_currency + ",0,'" + date + " 00:00:00.000'," + attachment_number + "," + item_id + "," + item_class + "," + dept_id + "," + personnel_id + "" +
                    ",'" + StringEscapeUtils.escapeSql(iyear) + "','" + StringEscapeUtils.escapeSql(iYPeriod) + "','" + StringEscapeUtils.escapeSql(tvouchtime) + "', '" + StringEscapeUtils.escapeSql(zph) + "')";
            flag = rsds.executeSql(sql);
            log("(当预借金额>0，且总金额<预借金额，需要退还，退还金额x2>0时)--2==" + sql);
            if (!flag) {
                return false;
            }
        }

        //当预借金额=0，补领金额>0时，需要进行补领
        if (yjje == 0 && blje > 0) {
            rowsize++;
            if (bxfs.equals("0")) {
                account_code = "1001";
            } else if (bxfs.equals("1")) {
                account_code = "100202";
            } else {
                account_code = "";
            }
            natural_debit_currency = "0";
            natural_credit_currency = df2.format(blje);

            String personnel_id = "null";

            String sql = "Insert Into GL_accvouch(iperiod,csign,isignseq,coutno_id,ino_id,inid,dbill_date," +
                    "cbill,cdigest,ccode,md,mc,bdelete,doutbilldate,idoc,citem_id,citem_class,cdept_id,cperson_id, iyear, iYPeriod,tvouchtime, cn_id)" +
                    "values(" + accounting_period + ",'" + voucher_type + "'," + isignseq + ",'" + coutno_id + "','" + ino_id + "'," + rowsize + ",'" + date + " 00:00:00.000'," +
                    "'" + enter + "','" + abstractstr + "','" + account_code + "'," + natural_debit_currency + "," + natural_credit_currency + ",0,'" + date + " 00:00:00.000'," + attachment_number + "," + item_id + "," + item_class + "," + dept_id + "," + personnel_id + "" +
                    ",'" + StringEscapeUtils.escapeSql(iyear) + "','" + StringEscapeUtils.escapeSql(iYPeriod) + "','" + StringEscapeUtils.escapeSql(tvouchtime) + "', '" + StringEscapeUtils.escapeSql(zph) + "')";
            flag = rsds.executeSql(sql);
            log("(当预借金额=0，补领金额>0时，需要进行补领)--1==" + sql);
            if (!flag) {
                return false;
            }
        }


        return flag;
    }

    private void log(Object obj) {
        //System.out.println(obj);
        writeLog(obj);
    }
}