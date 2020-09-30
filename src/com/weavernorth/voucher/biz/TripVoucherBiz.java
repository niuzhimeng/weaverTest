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
 * @description: EAS集成   差旅费流程凭证信息拼接
 * @author: DJC
 * @date: 2019.11.19
 * @version: 1.0
 */
public class TripVoucherBiz extends BaseBean {
	
	public WSWSVoucher[] tripInfo(String requestid,int userid) throws Exception{
		int entry = 0;//分录行
		RecordSet rs = new RecordSet();
		String trip_tablename = getPropValue("Eas_WorkflowId","trip_tablename");
        String trip_workflowid = getPropValue("Eas_WorkflowId","trip_workflowid");
        String depart_hs = getPropValue("Eas_Infos", "depart");
		String per_hs = getPropValue("Eas_Infos", "per");
		String bank_hs = getPropValue("Eas_Infos", "bank");
		String bank_account_hs = getPropValue("Eas_Infos", "bank_account");
		writeLog(" ----bank_hs---: "+bank_hs);
		
		//主表信息
		String sql0 = "SELECT * FROM "+trip_tablename+" where requestid = "+requestid;
		rs.executeQuery(sql0);
		rs.next();
		
		String id = Util.null2String(rs.getString("id"));
		//String jtfzje = Util.null2String(rs.getString("jtfzje"));//交通费总金额
		//String qtfyzje = Util.null2String(rs.getString("qtfyzje"));//其他费用总金额
		String blje = Util.null2String(rs.getString("blje"));//补领金额
		String thje = Util.null2String(rs.getString("thje"));//退还金额
		String se = Util.null2String(rs.getString("se"));//税额
		String jkje = Util.null2String(rs.getString("jkze"));//借款总额
		String bxje = Util.null2String(rs.getString("bxzjehs"));//报销金额 含税
		//String bhsje = Util.null2String(rs.getString("bhsje"));//不含税金额
		String sqr = Util.null2String(rs.getString("sqr"));//申请人
		//String yhxx = Util.null2String(rs.getString("fkyhxx2"));//付款银行信息
		String sqdw = Util.null2String(rs.getString("sqdw2"));//申请单位
		String sqbm = Util.null2String(rs.getString("sqbm"));//申请部门
		String lcbh = Util.null2String(rs.getString("lcbh"));//流程编号
		 
		CodeMappingUtil codeUtil = new CodeMappingUtil();
		
		//根据申请单位     查询  银行编码
		Map<String,String> bankMap = codeUtil.getBankHsCode(sqdw);
		VoucherHeaderEntity entity = getTripHeader(requestid,userid);
		WSWSVoucher[] vouchers = new WSWSVoucher[getEntryNum(requestid)];
		String currencyNumber = codeUtil.getCurrency(sqdw);
		writeLog(" ----currencyNumber---: "+currencyNumber);
		Map<String,String> departMap = codeUtil.getDepartCode(requestid, trip_tablename);
		Map<String,String> perCodeMap = codeUtil.getLastName(Integer.valueOf(sqr));
		
		writeLog("------------交通费分录行--------"+entry);	
		String sql1 = "SELECT * FROM "+trip_tablename+"_dt1 where mainid = "+id;
		rs.executeQuery(sql1);
		
		while(rs.next()) {
			String ejkm1 = Util.null2String(rs.getString("ejkm"));//费用类别
			String jtfje1 = Util.null2String(rs.getString("je1"));//交通费金额
			writeLog("------------交通费金额-----不含税---"+jtfje1);
			/*tax1 = Util.null2String(rs.getString("tax"));//税金
			if(tax1 != null && !"".equalsIgnoreCase(tax1)) {
				writeLog("------1------交通费金额-----税金---"+tax1);
				bhsje1 = Double.parseDouble(jtfje1) - Double.parseDouble(tax1);
				writeLog("------------交通费金额-----不含税---"+bhsje1);
			}else {
				writeLog("------2------交通费金额-----税金---"+tax1);
				bhsje1 = Double.parseDouble(jtfje1);
				writeLog("------------交通费金额-----不含税---"+bhsje1);
			}*/
			Map<String,String> jtCodeMap = codeUtil.getJtHsCode(sqbm, ejkm1);
			
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
			
			vouchers[entry].setOriginalAmount(Double.parseDouble(jtfje1));
			vouchers[entry].setDebitAmount(Double.parseDouble(jtfje1));
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
		writeLog("-------其他费用明细--分录行------"+entry);	
		String sql2 = "SELECT * FROM "+trip_tablename+"_dt2 where mainid = "+id;
		rs.executeQuery(sql2);
		while(rs.next()) {
			String ejkm = Util.null2String(rs.getString("ejkm"));//费用名称
			//String hsje = Util.null2String(rs.getString("hsje1"));//含税金额
			String bhsje = Util.null2String(rs.getString("bhsje"));//不含税金额
			String fycdbm = Util.null2String(rs.getString("fycdbm1"));//费用承担部门
			
			Map<String,String> mxDepartCodeMap = codeUtil.getMxDpartInfo(fycdbm);
			Map<String,String> jtCodeMap = codeUtil.getJtHsCode(fycdbm,ejkm);
			
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
			writeLog("-------jtCodeMap其他费用明细--------"+jtCodeMap.get("code"));	
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
		
		writeLog("-------税金-----分录行----------"+entry);
		if(se == "" || se == null) {
			se = "0";
		}
		writeLog("----分录行数---税额----"+se);
		double tax = Double.parseDouble(se);
		if(tax > 0) {
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
			vouchers[entry].setVoucherAbstract(getPropValue("Eas_Infos", "tax_zy")+"-"+lcbh);//税金摘要
			vouchers[entry].setAccountNumber(getPropValue("Eas_Infos", "tax_code"));
			vouchers[entry].setCurrencyNumber(currencyNumber);
			vouchers[entry].setEntryDC(1);
			vouchers[entry].setOriginalAmount(tax);
			vouchers[entry].setDebitAmount(tax);
			vouchers[entry].setCreditAmount(0);
			
			vouchers[entry].setItemFlag(0);
			
			entry = entry+1;
		}
		
		if(Double.parseDouble(thje) > 0) {
			writeLog("-------退还金额大于0-------分录行--------"+entry);
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
			vouchers[entry].setAccountNumber(bankMap.get("eas_code"));//科目编码
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
		
		//贷方	
		//根据 workflowId 和 对公对私 进行查询
		Map<String,String> wlkmUtil = codeUtil.getWlkmMapping(trip_workflowid, "2");//0-对公    1-对私
		
		if(Double.parseDouble(jkje)>0 && Double.parseDouble(jkje) < Double.parseDouble(bxje) ) {// 2 个
			writeLog("----借款大于0------ 借款小于报销--------银行 现金----分录行--"+entry);
			//银行 现金
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
			vouchers[entry].setAccountNumber(bankMap.get("eas_code"));//科目编码
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
			
			writeLog("------借款大于0------ 借款小于报销----------其他 ----分录行---"+entry);
			
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
			 
			vouchers[entry].setItemFlag(0);

			entry = entry+1;
		}else if(Double.parseDouble(jkje)>0 && Double.parseDouble(jkje) >= Double.parseDouble(bxje)) {//1个
			writeLog("---借款大于0------ 借款大于等于报销------------分录行-------"+entry);
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
			//根据 workflowId 和 对公对私 进行查询
			if("0".equalsIgnoreCase(wlkmUtil.get("per"))) {
				vouchers[entry].setAsstActType1(per_hs);
				vouchers[entry].setAsstActName1(perCodeMap.get("lastname"));
				vouchers[entry].setAsstActNumber1(perCodeMap.get("workcode"));
			}
			 
			vouchers[entry].setItemFlag(0);
			
			entry = entry+1;
		}else {
			writeLog("------借款等于0--------银行 现金-------分录行------"+entry);
			//银行 现金
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
			vouchers[entry].setAccountNumber(bankMap.get("eas_code"));//科目编码
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
		}
		return vouchers;
	}
	
	//凭证 header 信息
	public VoucherHeaderEntity getTripHeader(String requestid,int userid) {
		writeLog("---进------凭证 header 信息----");
		String trip_tablename = getPropValue("Eas_WorkflowId","trip_tablename");

		VoucherHeaderEntity entity = new VoucherHeaderEntity();
		CodeMappingUtil codeUtil = new CodeMappingUtil();
		RecordSet rs = new RecordSet();
		String sql = "SELECT * FROM "+trip_tablename+" WHERE requestId = "+requestid;
		rs.executeQuery(sql);
		rs.next();
		
		entity.setCompanyNumber(codeUtil.getCompanyNumber(Util.null2String(rs.getString("sqdw2"))));
		entity.setBookedDate(DateUtil.getCurrentDate());
		entity.setBizDate(Util.null2String(rs.getString("sqrq")));
		entity.setPeriodYear(codeUtil.getCurrentYear());
		entity.setPeriodNumber(codeUtil.getCurrentMonth());
		entity.setVoucherType("总");
		entity.setVoucherNumber("1"+requestid);
		writeLog("-----出----凭证 header 信息----");
		return entity;
	}
	
	//计算税额
	public String getTaxes(String id) {
		writeLog("----------计算税额----");
		String trip_tablename = getPropValue("Eas_WorkflowId","trip_tablename");
		String t1 = "";//交通费税额
		String t2 = "";//其他费用税额
		RecordSet rs = new RecordSet();
		
		String sql0 = "SELECT sum(tax) FROM "+trip_tablename+"_dt1 where mainid = ?";
		rs.executeQuery(sql0,id);
		if(rs.next()) {
			t1 = Util.null2String(rs.getString("1"));
		}
		writeLog("----------交通费税额----"+t1);
		
		String sql1 = "SELECT sum(sj) FROM "+trip_tablename+"_dt2 where mainid = ?";
		rs.executeQuery(sql1,id);
		if(rs.next()) {
			t2 = Util.null2String(rs.getString("1"));
		}
		writeLog("----------其他费用税额----"+t2);
		
		String tax = String.valueOf(Double.parseDouble(t1)+Double.parseDouble(t2));
		writeLog("----------总税额-------"+tax);
		return tax;
	}
	
	//查询凭证  分录行数
	public int getEntryNum(String requestid) {
		writeLog("-----进-----分录行数-------");
		String trip_tablename = getPropValue("Eas_WorkflowId","trip_tablename");
		int jf_jt =0;//明细1计量
		int jf_qt = 0;//明细2计量
		int tax_num=0;//税费计量
		int other_num=0;//其他科目（其他应收、银行/现金）
		
		RecordSet rs = new RecordSet();
		String sql0 = "SELECT * FROM "+trip_tablename+" where requestid = "+requestid;
		rs.executeQuery(sql0);
		rs.next();
		
		String se = Util.null2String(rs.getString("se"));//税额
		if(se == "" || se == null) {
			se = "0";
		}
		writeLog("-----进-----分录行数---税额----"+se);
		String jkje = Util.null2String(rs.getString("jkze"));//借款总额
		String bxje = Util.null2String(rs.getString("bxzjehs"));//报销金额 含税
		String id = Util.null2String(rs.getString("id"));
		
		String sql1 = "SELECT count(id) FROM "+trip_tablename+"_dt1 where mainid = "+id;
		rs.executeQuery(sql1);
		if(rs.next()) {
			jf_jt = Util.getIntValue(rs.getString(1));
		};
		String sql2 = "SELECT count(id) FROM "+trip_tablename+"_dt2 where mainid = "+id;
		rs.executeQuery(sql2);
		if(rs.next()) {
			jf_qt = Util.getIntValue(rs.getString(1));
		}
		if(Double.parseDouble(se) > 0) {
			tax_num = 1;
		}
		
		if(Double.parseDouble(jkje)==0) {
			other_num = 1;//一个银行/现金
		}else if(Double.parseDouble(jkje) == Double.parseDouble(bxje)){
			other_num = 1;//一个其他应收款
		}else {
			other_num = 2;//一个银行、一个其他应收款
		}
		int z = jf_jt+jf_qt+tax_num+other_num;
		writeLog("-----出------分录行数------"+z);
		return z;
	}
	
}
