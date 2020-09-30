package com.weavernorth.voucher.biz;

import java.util.Map;

import com.weaver.general.BaseBean;
import com.weavernorth.voucher.entity.VoucherHeaderEntity;
import com.weavernorth.voucher.util.CodeMappingUtil;

import weaver.common.DateUtil;
import weaver.conn.RecordSet;
import weaver.general.Util;
import wsvoucher.client.WSWSVoucher;

/**
 * @description: EAS集成    费用报销凭证信息拼接
 * @author: DJC
 * @date: 2019.11.19
 * @version: 1.2
 */
public class CostVocherBiz extends BaseBean {

	public WSWSVoucher[] costInfo(String requestid,int userid) throws Exception{
		CodeMappingUtil codeUtil = new CodeMappingUtil();
		int entry = 0;//分录行
		RecordSet rs = new RecordSet();
		String cost_tablename = getPropValue("Eas_WorkflowId","cost_tablename");
		String cost_workflowid = getPropValue("Eas_WorkflowId","cost_workflowid");
		String depart_hs = getPropValue("Eas_Infos", "depart");
		String per_hs = getPropValue("Eas_Infos", "per");
		String cus_hs = getPropValue("Eas_Infos", "cus");
		String bank_hs = getPropValue("Eas_Infos", "bank");
		String bank_account_hs = getPropValue("Eas_Infos", "bank_account");
		
		writeLog(" ----bank_hs---: "+bank_hs);
		
		VoucherHeaderEntity entity = getCostHeader(requestid,userid);
		WSWSVoucher[] vouchers = new WSWSVoucher[getEntryNum(requestid)];
		writeLog("CostVocherBiz ----分录行-------	num---: "+getEntryNum(requestid));
		
		//主表信息
		String sql0 = "SELECT * FROM "+cost_tablename+" where requestid = "+requestid;
		rs.executeQuery(sql0);
		rs.next();
		String id = Util.null2String(rs.getString("id"));
		String jtfzje = Util.null2String(rs.getString("jtfzje"));//交通费总金额
		String se = Util.null2String(rs.getString("se"));//税额    税金
		String thje = Util.null2String(rs.getString("thje"));//退还金额
		String blje = Util.null2String(rs.getString("blje"));//补领金额
		String sfdjk = Util.null2String(rs.getString("sfdjk"));//是否抵借款
		String jkje = Util.null2String(rs.getString("jkze"));//借款总额
		String bxje = Util.null2String(rs.getString("bxzjehs"));//报销金额 含税
		String sqr = Util.null2String(rs.getString("sqr"));//申请人
		String sklx = Util.null2String(rs.getString("fklx"));//收款类型
		String sqdw = Util.null2String(rs.getString("sqdw"));//申请单位
		String sqbm = Util.null2String(rs.getString("sqbm"));//申请部门
		String lcbh = Util.null2String(rs.getString("lcbh"));//流程编号
		
		Map<String,String> bankMap = codeUtil.getBankHsCode(sqdw);
		Map<String,String> departMap = codeUtil.getDepartCode(requestid, cost_tablename);
		Map<String,String> perCodeMap = codeUtil.getLastName(Integer.valueOf(sqr));//申请人
		String currencyNumber = codeUtil.getCurrency(sqdw);
		writeLog(" ----currencyNumber---: "+currencyNumber);
		
		/* 是否抵借款	   1否    				有现金流
		 * 			   0是    退还金额等于0		无现金流       
		 * 			                退还金额大于或小于0     有现金流
		 */
		String gys = "";//供应商  客户
		Map<String,String> cusCodeMap = null;
		if(sfdjk == "0" || "0".equalsIgnoreCase(sfdjk)) {
			writeLog("-----查询供应商--------");
			String sqlj = "SELECT * FROM "+cost_tablename+"_dt4 where mainid = "+id;
			rs.executeQuery(sqlj);
			rs.next();
			gys = Util.null2String(rs.getString("gys"));
			cusCodeMap = codeUtil.getCusInfo(gys);
		}
		
		/*收款类型		0--对公		1--对私	*/
		String gs = "";//公私
		if(sklx == "0" || "0".equalsIgnoreCase(sklx)) {
			gs = "0";
		}else {
			gs = "1";
		}
		writeLog("-------交通费分录行---------------"+entry);
		/*借方*/
		//交通费  大于0
		if(Double.parseDouble(jtfzje) > 0) {
			String sql1 = "SELECT * FROM "+cost_tablename+"_dt1 where mainid = "+id;
			rs.executeQuery(sql1);
			rs.next();
			String fymc = Util.null2String(rs.getString("kemu"));//科目名称
			//Map<String,String> easCodeMap = codeUtil.getEasCode(fymc);
			Map<String,String> jtCodeMap = codeUtil.getJtHsCode(sqbm,fymc);
			
			vouchers[entry] = new WSWSVoucher();
			vouchers[entry].setBookedDate(entity.getBookedDate());	  
			vouchers[entry].setBizDate(entity.getBizDate());
			vouchers[entry].setCompanyNumber(entity.getCompanyNumber());
			vouchers[entry].setPeriodYear(Integer.valueOf(entity.getPeriodYear()));
			vouchers[entry].setPeriodNumber(Integer.valueOf(entity.getPeriodNumber()));
			vouchers[entry].setCreator(codeUtil.getZdName(userid));//制单人
			vouchers[entry].setVoucherType(entity.getVoucherType());
			vouchers[entry].setVoucherNumber(entity.getVoucherNumber());
			
			vouchers[entry].setEntrySeq(entry+1);
			vouchers[entry].setVoucherAbstract(getPropValue("Eas_Infos", "fybx_jtzy")+"-"+lcbh);
			vouchers[entry].setAccountNumber(jtCodeMap.get("code"));
			vouchers[entry].setCurrencyNumber(currencyNumber);
			vouchers[entry].setEntryDC(1);
			vouchers[entry].setOriginalAmount(Double.parseDouble(jtfzje));
			vouchers[entry].setDebitAmount(Double.parseDouble(jtfzje));
			vouchers[entry].setCreditAmount(0);
			
			vouchers[entry].setAsstSeq(1);//辅助核算    部门、职员、客户
			if("0".equalsIgnoreCase(jtCodeMap.get("depart")) && "0".equalsIgnoreCase(jtCodeMap.get("per"))) {
				vouchers[entry].setAsstActType1(depart_hs);
				vouchers[entry].setAsstActName1(departMap.get("departname"));
				vouchers[entry].setAsstActNumber1(departMap.get("departcode"));
				vouchers[entry].setAsstActType2(per_hs);
				vouchers[entry].setAsstActName2(perCodeMap.get("lastname"));
				vouchers[entry].setAsstActNumber2(perCodeMap.get("workcode"));
			}else {
				if("0".equalsIgnoreCase(jtCodeMap.get("per"))) {
					vouchers[entry].setAsstActType1(per_hs);
					vouchers[entry].setAsstActName1(perCodeMap.get("lastname"));
					vouchers[entry].setAsstActNumber1(perCodeMap.get("workcode"));
				}
				if("0".equalsIgnoreCase(jtCodeMap.get("depart"))) {
					vouchers[entry].setAsstActType1(depart_hs);
					vouchers[entry].setAsstActName1(departMap.get("departname"));
					vouchers[entry].setAsstActNumber1(departMap.get("departcode"));
				}
			}
			vouchers[entry].setItemFlag(0);
			entry = entry+1;
		}
		writeLog("-------其它费用科目分录行---------------"+entry);
		//其它费用科目
		String sql2 = "SELECT * FROM "+cost_tablename+"_dt2 where mainid = "+id;
		rs.executeQuery(sql2);
		while(rs.next()) {
			String fymc = Util.null2String(rs.getString("ejkm"));//费用名称
			String bhsje = Util.null2String(rs.getString("bhsje"));//不含税金额
			String fycdbm = Util.null2String(rs.getString("fycdbm"));//费用承担部门
			
			Map<String,String> mxDepartCodeMap = codeUtil.getMxDpartInfo(fycdbm);
			//Map<String,String> easCodeMap = codeUtil.getEasCode(fymc);
			Map<String,String> jtCodeMap = codeUtil.getJtHsCode(fycdbm, fymc);
			
			vouchers[entry] = new WSWSVoucher();
			vouchers[entry].setBookedDate(entity.getBookedDate());			  
			vouchers[entry].setBizDate(entity.getBizDate());
			vouchers[entry].setCompanyNumber(entity.getCompanyNumber());
			vouchers[entry].setPeriodYear(Integer.valueOf(entity.getPeriodYear()));
			vouchers[entry].setPeriodNumber(Integer.valueOf(entity.getPeriodNumber()));
			vouchers[entry].setCreator(codeUtil.getZdName(userid));
			vouchers[entry].setVoucherType(entity.getVoucherType());
			vouchers[entry].setVoucherNumber(entity.getVoucherNumber());
			
			vouchers[entry].setEntrySeq(entry+1);
			vouchers[entry].setVoucherAbstract("报销"+jtCodeMap.get("fykm_name")+"-"+lcbh);
			vouchers[entry].setAccountNumber(jtCodeMap.get("code"));
			vouchers[entry].setCurrencyNumber(currencyNumber);
			vouchers[entry].setEntryDC(1);
			vouchers[entry].setOriginalAmount(Double.parseDouble(bhsje));
			vouchers[entry].setDebitAmount(Double.parseDouble(bhsje));
			vouchers[entry].setCreditAmount(0);
			
			vouchers[entry].setAsstSeq(1);//辅助核算    部门、职员、客户
			if("0".equalsIgnoreCase(jtCodeMap.get("depart")) && "0".equalsIgnoreCase(jtCodeMap.get("per"))) {
				vouchers[entry].setAsstActType1(depart_hs);
				vouchers[entry].setAsstActName1(mxDepartCodeMap.get("departname"));
				vouchers[entry].setAsstActNumber1(mxDepartCodeMap.get("departcode"));
				vouchers[entry].setAsstActType2(per_hs);
				vouchers[entry].setAsstActName2(perCodeMap.get("lastname"));
				vouchers[entry].setAsstActNumber2(perCodeMap.get("workcode"));
			}else {
				if("0".equalsIgnoreCase(jtCodeMap.get("per"))) {
					vouchers[entry].setAsstActType1(per_hs);
					vouchers[entry].setAsstActName1(perCodeMap.get("lastname"));
					vouchers[entry].setAsstActNumber1(perCodeMap.get("workcode"));
				}
				if("0".equalsIgnoreCase(jtCodeMap.get("depart"))) {
					vouchers[entry].setAsstActType1(depart_hs);
					vouchers[entry].setAsstActName1(mxDepartCodeMap.get("departname"));
					vouchers[entry].setAsstActNumber1(mxDepartCodeMap.get("departcode"));
				}
			}
			vouchers[entry].setItemFlag(0);
			
			entry = entry+1;
		}
		writeLog("-------税金  税额分录行数---------------"+entry);
		//税金  税额
		if(Double.parseDouble(se) > 0) {
			vouchers[entry] = new WSWSVoucher();
			vouchers[entry].setBookedDate(entity.getBookedDate());			  
			vouchers[entry].setBizDate(entity.getBizDate());
			vouchers[entry].setCompanyNumber(entity.getCompanyNumber());
			vouchers[entry].setPeriodYear(Integer.valueOf(entity.getPeriodYear()));
			vouchers[entry].setPeriodNumber(Integer.valueOf(entity.getPeriodNumber()));
			vouchers[entry].setCreator(codeUtil.getZdName(userid));
			vouchers[entry].setVoucherType(entity.getVoucherType());
			vouchers[entry].setVoucherNumber(entity.getVoucherNumber());
			
			vouchers[entry].setEntrySeq(entry+1);
			vouchers[entry].setVoucherAbstract(getPropValue("Eas_Infos", "tax_zy")+"-"+lcbh);
			vouchers[entry].setAccountNumber(getPropValue("Eas_Infos", "tax_code"));
			vouchers[entry].setCurrencyNumber(currencyNumber);
			vouchers[entry].setEntryDC(1);
			vouchers[entry].setOriginalAmount(Double.parseDouble(se));
			vouchers[entry].setDebitAmount(Double.parseDouble(se));
			vouchers[entry].setCreditAmount(0);
			
			vouchers[entry].setItemFlag(0);
			entry = entry+1;
		}
		
		//退还金额大于0
		if(Double.parseDouble(thje) > 0) {
			writeLog("-------退还金额大于0---------------"+entry);
			vouchers[entry] = new WSWSVoucher();
			vouchers[entry].setBookedDate(entity.getBookedDate());			  
			vouchers[entry].setBizDate(entity.getBizDate());
			vouchers[entry].setCompanyNumber(entity.getCompanyNumber());
			vouchers[entry].setPeriodYear(Integer.valueOf(entity.getPeriodYear()));
			vouchers[entry].setPeriodNumber(Integer.valueOf(entity.getPeriodNumber()));
			vouchers[entry].setCreator(codeUtil.getZdName(userid));
			vouchers[entry].setVoucherType(entity.getVoucherType());
			vouchers[entry].setVoucherNumber(entity.getVoucherNumber());
			//借方为银行的情况
			vouchers[entry].setEntrySeq(entry+1);
			vouchers[entry].setVoucherAbstract(getPropValue("Eas_Infos", "th_bank")+"-"+lcbh);
			vouchers[entry].setAccountNumber(bankMap.get("eas_code"));
			vouchers[entry].setCurrencyNumber(currencyNumber);
			vouchers[entry].setEntryDC(1);
			vouchers[entry].setOriginalAmount(Double.parseDouble(thje));
			vouchers[entry].setDebitAmount(Double.parseDouble(thje));
			vouchers[entry].setCreditAmount(0);
			vouchers[entry].setAsstSeq(1);
			vouchers[entry].setAsstActType1(bank_hs);
			vouchers[entry].setAsstActName1(bankMap.get("jrjgname"));
			vouchers[entry].setAsstActNumber1(bankMap.get("jrjgcode"));
			vouchers[entry].setAsstActType2(bank_account_hs);
			vouchers[entry].setAsstActName2(bankMap.get("name"));
			vouchers[entry].setAsstActNumber2(bankMap.get("code"));
			vouchers[entry].setItemFlag(0);
			entry = entry+1;
		}
		
		/*贷方*/
		
		Map<String,String> wlkmUtil = codeUtil.getWlkmMapping(cost_workflowid, gs);//0-对公    1-对私
		
		if((Double.parseDouble(jkje) < Double.parseDouble(bxje)) && (Double.parseDouble(jkje) != 0)) {
			writeLog(wlkmUtil.get("code")+"-------借款小于报销-----银行/现金----分录行-----"+entry);
			vouchers[entry] = new WSWSVoucher();
			vouchers[entry].setBookedDate(entity.getBookedDate());			  
			vouchers[entry].setBizDate(entity.getBizDate());
			vouchers[entry].setCompanyNumber(entity.getCompanyNumber());
			vouchers[entry].setPeriodYear(Integer.valueOf(entity.getPeriodYear()));
			vouchers[entry].setPeriodNumber(Integer.valueOf(entity.getPeriodNumber()));
			vouchers[entry].setCreator(codeUtil.getZdName(userid));
			vouchers[entry].setVoucherType(entity.getVoucherType());
			vouchers[entry].setVoucherNumber(entity.getVoucherNumber());
			
			vouchers[entry].setEntrySeq(entry+1);
			vouchers[entry].setVoucherAbstract(getPropValue("Eas_Infos", "wlkm_zy")+"-"+lcbh);
			vouchers[entry].setAccountNumber(bankMap.get("eas_code"));//现金银行科目编码
			vouchers[entry].setCurrencyNumber(currencyNumber);
			vouchers[entry].setEntryDC(-1);
			vouchers[entry].setOriginalAmount(Double.parseDouble(blje));
			vouchers[entry].setDebitAmount(0);
			vouchers[entry].setCreditAmount(Double.parseDouble(blje));
			vouchers[entry].setAsstSeq(1);//辅助核算 
			//银行类科目只涉及银行和银行账户的辅助项目（代码暂且写死）
			vouchers[entry].setAsstActType1(bank_hs);
			vouchers[entry].setAsstActName1(bankMap.get("jrjgname"));
			vouchers[entry].setAsstActNumber1(bankMap.get("jrjgcode"));
			vouchers[entry].setAsstActType2(bank_account_hs);
			vouchers[entry].setAsstActName2(bankMap.get("name"));
			vouchers[entry].setAsstActNumber2(bankMap.get("code"));
			
			vouchers[entry].setItemFlag(0);
			
			entry = entry+1;
			
			writeLog("------------------借款小于报销-----其他---------分录行-----------"+entry);
			
			vouchers[entry] = new WSWSVoucher();
			vouchers[entry].setBookedDate(entity.getBookedDate());			  
			vouchers[entry].setBizDate(entity.getBizDate());
			vouchers[entry].setCompanyNumber(entity.getCompanyNumber());
			vouchers[entry].setPeriodYear(Integer.valueOf(entity.getPeriodYear()));
			vouchers[entry].setPeriodNumber(Integer.valueOf(entity.getPeriodNumber()));
			vouchers[entry].setCreator(codeUtil.getZdName(userid));
			vouchers[entry].setVoucherType(entity.getVoucherType());
			vouchers[entry].setVoucherNumber(entity.getVoucherNumber());
			
			vouchers[entry].setEntrySeq(entry+1);
			vouchers[entry].setVoucherAbstract(getPropValue("Eas_Infos", "wlkm_zy")+"-"+lcbh);
			vouchers[entry].setAccountNumber(wlkmUtil.get("code"));//科目编码
			vouchers[entry].setCurrencyNumber(currencyNumber);
			vouchers[entry].setEntryDC(-1);
			vouchers[entry].setOriginalAmount(Double.parseDouble(jkje));
			vouchers[entry].setDebitAmount(0);
			vouchers[entry].setCreditAmount(Double.parseDouble(jkje));
			
			vouchers[entry].setAsstSeq(1);//辅助核算 
			//1221其他应收款   只挂客户或者职员（供应商、影视剧项目目前不涉及）
			if("0".equalsIgnoreCase(wlkmUtil.get("per"))) {
				vouchers[entry].setAsstActType1(per_hs);
				vouchers[entry].setAsstActName1(perCodeMap.get("lastname"));
				vouchers[entry].setAsstActNumber1(perCodeMap.get("workcode"));
			}
			if("0".equalsIgnoreCase(wlkmUtil.get("cus"))) {
				//vouchers[entry].setAsstActType1("客户");
				vouchers[entry].setAsstActType1(cus_hs);
				vouchers[entry].setAsstActName1(cusCodeMap.get("cusname"));
				vouchers[entry].setAsstActNumber1(cusCodeMap.get("cuscode"));
			}
			vouchers[entry].setItemFlag(0);
			entry = entry+1;
		
		}else if(Double.parseDouble(jkje) == 0){
			writeLog("------------------借款等于0--------分录行-----------"+entry);
			vouchers[entry] = new WSWSVoucher();
			vouchers[entry].setBookedDate(entity.getBookedDate());			  
			vouchers[entry].setBizDate(entity.getBizDate());
			vouchers[entry].setCompanyNumber(entity.getCompanyNumber());
			vouchers[entry].setPeriodYear(Integer.valueOf(entity.getPeriodYear()));
			vouchers[entry].setPeriodNumber(Integer.valueOf(entity.getPeriodNumber()));
			vouchers[entry].setCreator(codeUtil.getZdName(userid));
			vouchers[entry].setVoucherType(entity.getVoucherType());
			vouchers[entry].setVoucherNumber(entity.getVoucherNumber());
			
			vouchers[entry].setEntrySeq(entry+1);
			vouchers[entry].setVoucherAbstract(getPropValue("Eas_Infos", "wlkm_zy")+"-"+lcbh);
			vouchers[entry].setAccountNumber(bankMap.get("eas_code"));//现金银行科目编码
			vouchers[entry].setCurrencyNumber(currencyNumber);
			vouchers[entry].setEntryDC(-1);
			vouchers[entry].setOriginalAmount(Double.parseDouble(blje));
			vouchers[entry].setDebitAmount(0);
			vouchers[entry].setCreditAmount(Double.parseDouble(blje));
			vouchers[entry].setAsstSeq(1);//辅助核算 
			//银行类科目只涉及银行和银行账户的辅助项目（代码暂且写死）
			vouchers[entry].setAsstActType1(bank_hs);
			vouchers[entry].setAsstActName1(bankMap.get("jrjgname"));
			vouchers[entry].setAsstActNumber1(bankMap.get("jrjgcode"));
			vouchers[entry].setAsstActType2(bank_account_hs);
			vouchers[entry].setAsstActName2(bankMap.get("name"));
			vouchers[entry].setAsstActNumber2(bankMap.get("code"));
			
			vouchers[entry].setItemFlag(0);
			
			entry = entry+1;
		}else {
			writeLog(wlkmUtil.get("code")+"-------借款不小于报销分录行---------------"+entry);
			vouchers[entry] = new WSWSVoucher();
			vouchers[entry].setBookedDate(entity.getBookedDate());			  
			vouchers[entry].setBizDate(entity.getBizDate());
			vouchers[entry].setCompanyNumber(entity.getCompanyNumber());
			vouchers[entry].setPeriodYear(Integer.valueOf(entity.getPeriodYear()));
			vouchers[entry].setPeriodNumber(Integer.valueOf(entity.getPeriodNumber()));
			vouchers[entry].setCreator(codeUtil.getZdName(userid));
			vouchers[entry].setVoucherType(entity.getVoucherType());
			vouchers[entry].setVoucherNumber(entity.getVoucherNumber());
			//借方是其他应收款
			vouchers[entry].setEntrySeq(entry+1);
			vouchers[entry].setVoucherAbstract(getPropValue("Eas_Infos", "wlkm_zy")+"-"+lcbh);
			vouchers[entry].setAccountNumber(wlkmUtil.get("code"));//科目编码
			vouchers[entry].setCurrencyNumber(currencyNumber);
			vouchers[entry].setEntryDC(-1);
			vouchers[entry].setOriginalAmount(Double.parseDouble(jkje));
			vouchers[entry].setDebitAmount(0);
			vouchers[entry].setCreditAmount(Double.parseDouble(jkje));
			vouchers[entry].setAsstSeq(1);//辅助核算 
			//根据 workflowId 和 对公对私 进行查询
			if("0".equalsIgnoreCase(wlkmUtil.get("per"))) {
				vouchers[entry].setAsstActType1(per_hs);
				vouchers[entry].setAsstActName1(perCodeMap.get("lastname"));
				vouchers[entry].setAsstActNumber1(perCodeMap.get("workcode"));
			}
			if("0".equalsIgnoreCase(wlkmUtil.get("cus"))) {
				//vouchers[entry].setAsstActType1("客户");
				vouchers[entry].setAsstActType1(cus_hs);
				vouchers[entry].setAsstActName1(cusCodeMap.get("cusname"));
				vouchers[entry].setAsstActNumber1(cusCodeMap.get("cuscode"));
			}
			vouchers[entry].setItemFlag(0);
			entry = entry+1;
		}
		writeLog("-----vouchers------"+vouchers.toString());
		
		return vouchers;
	}
	
	//凭证 header 信息
	public VoucherHeaderEntity getCostHeader(String requestid,int userid) {
		String cost_tablename = getPropValue("Eas_WorkflowId","cost_tablename");

		VoucherHeaderEntity entity = new VoucherHeaderEntity();
		CodeMappingUtil codeUtil = new CodeMappingUtil();
		RecordSet rs = new RecordSet();
		String sql = "SELECT * FROM "+cost_tablename+" WHERE requestId = "+requestid;
		rs.executeQuery(sql);
		rs.next();
		
		entity.setCompanyNumber(codeUtil.getCompanyNumber(Util.null2String(rs.getString("sqdw"))));
		entity.setBookedDate(DateUtil.getCurrentDate());
		entity.setBizDate(Util.null2String(rs.getString("sqrq")));
		entity.setPeriodYear(codeUtil.getCurrentYear());
		entity.setPeriodNumber(codeUtil.getCurrentMonth());
		entity.setVoucherType("总");
		entity.setVoucherNumber("1"+requestid);
		
		return entity;
	}
	
	//查询凭证  分录行数
	public int getEntryNum(String requestid) {
		int jf_jt = 0;//交通费明细1
		int jf_qt = 0;//其他明细2
		int tax_num = 0;//税额
		int jf_th = 0;//借方 银行/现金
		int df = 0;//贷方
		String cost_tablename = getPropValue("Eas_WorkflowId","cost_tablename");
		
		RecordSet rs = new RecordSet();
		String sql0 = "SELECT * FROM "+cost_tablename+" where requestid = "+requestid;
		rs.executeQuery(sql0);
		rs.next();
		
		String jkje = Util.null2String(rs.getString("jkze"));//借款总额
		String bxje = Util.null2String(rs.getString("bxzjehs"));//报销金额 含税
		String id = Util.null2String(rs.getString("id"));
		String thje = Util.null2String(rs.getString("thje"));//退还金额
		String se = Util.null2String(rs.getString("se"));//税额    税金
		
		if(Double.parseDouble(se) > 0) {
			tax_num = 1;
		}
		//writeLog("-----tax_num-------"+tax_num);
		String sql1 = "SELECT * FROM "+cost_tablename+"_dt1 where mainid = "+id;
		rs.executeQuery(sql1);
		if(rs.next()) {
			jf_jt = 1;//交通费用明细行数
		}
		//writeLog("-----jf_jt-------"+jf_jt);
		String sql2 = "SELECT count(id) FROM "+cost_tablename+"_dt2 where mainid = "+id;
		rs.executeQuery(sql2);
		if(rs.next()) {
			jf_qt =  Util.getIntValue(rs.getString(1));//其他费用明细行数
		}
		//writeLog("-----jf_qt-------"+jf_qt);
		if(Double.parseDouble(thje) > 0) {
			jf_th = 1;
		}
		//writeLog("-----jf_th-------"+jf_th);
		if((Double.parseDouble(jkje) < Double.parseDouble(bxje)) && (Double.parseDouble(jkje) != 0)) {
			df = 2;
		}else if(Double.parseDouble(jkje) == 0){
			df = 1;
		}else {
			df = 1;
		}
		//writeLog("-----df-------"+df);
		int z = jf_jt+jf_qt+tax_num+jf_th+df;
		writeLog("-----费用报销  分录行数-------"+z);
		return z;
	}
	
}
