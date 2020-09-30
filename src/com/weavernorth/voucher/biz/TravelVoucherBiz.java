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
 * @description: EAS集成    出差流程凭证信息拼接
 * @author: DJC
 * @date: 2019.11.19
 * @version: 1.0
 */
public class TravelVoucherBiz extends BaseBean {
	
	public WSWSVoucher[] travelInfo(String requestid,int userid) throws Exception{
		writeLog("-----------travelInfo start----------");
		int entry = 0;//分录行
		RecordSet rs = new RecordSet();
        String travel_workflowid  = getPropValue("Eas_WorkflowId","travel_workflowid");
		String travel_tablename = getPropValue("Eas_WorkflowId","travel_tablename");
		//String depart_hs = getPropValue("Eas_Infos", "depart");
		String per_hs = getPropValue("Eas_Infos", "per");
		//String cus_hs = getPropValue("Eas_Infos", "cus");
		String bank_hs = getPropValue("Eas_Infos", "bank");
		String bank_account_hs = getPropValue("Eas_Infos", "bank_account");
		writeLog(" ----bank_hs---: "+bank_hs);
		
		CodeMappingUtil codeUtil = new CodeMappingUtil();
		VoucherHeaderEntity entity = getTravelHeader(requestid,userid);
		WSWSVoucher[] vouchers = new WSWSVoucher[2];
		 
		//主表信息
		String sql0 = "SELECT * FROM "+travel_tablename+" where requestid = "+requestid;
		rs.executeQuery(sql0);
		rs.next();
		
		String sfyjk = Util.null2String(rs.getString("sfyjk"));//是否借款
		String jkje = Util.null2String(rs.getString("jkje"));//借款金额
		//String bm = Util.null2String(rs.getString("bm"));//部门
		String sqr = Util.null2String(rs.getString("sqr"));//申请人
		String sqdw = Util.null2String(rs.getString("sqdw"));//申请单位
		String lcbh = Util.null2String(rs.getString("lcbh"));//流程编号
		
		Map<String,String> bankMap = codeUtil.getXlkBankHsCode(sqdw);
		Map<String,String> perCodeMap = codeUtil.getLastName(Integer.valueOf(sqr));
		Map<String,String> wlkmUtil = codeUtil.getWlkmMapping(travel_workflowid, "2");//0-对公    1-对私
		String currencyNumber = codeUtil.getXlkCurrency(sqdw);
		writeLog(" ----currencyNumber---: "+currencyNumber);
		
		if("0".equalsIgnoreCase(sfyjk)) {
			//借方
			writeLog("-----------借方----------"+entry);
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
			vouchers[entry].setVoucherAbstract(getPropValue("Eas_Infos", "cx_zy")+"-"+lcbh);
			vouchers[entry].setAccountNumber(wlkmUtil.get("code"));
			vouchers[entry].setCurrencyNumber(currencyNumber);
			vouchers[entry].setEntryDC(1);
			vouchers[entry].setOriginalAmount(Double.parseDouble(jkje));
			vouchers[entry].setDebitAmount(Double.parseDouble(jkje));
			vouchers[entry].setCreditAmount(0);
			
			vouchers[entry].setAsstSeq(1);//辅助核算    职员、客户
			
			if("0".equalsIgnoreCase(wlkmUtil.get("per"))) {
				vouchers[entry].setAsstActType1(per_hs);
				vouchers[entry].setAsstActName1(perCodeMap.get("lastname"));
				vouchers[entry].setAsstActNumber1(perCodeMap.get("workcode"));
			} 
			
			vouchers[entry].setItemFlag(0);
			
			entry = entry+1;
			writeLog("-----------贷方----------"+entry);
			//贷方
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
			vouchers[entry].setVoucherAbstract(getPropValue("Eas_Infos", "cx_zy")+"-"+lcbh);
			vouchers[entry].setAccountNumber(bankMap.get("eas_code"));//银行编码
			vouchers[entry].setCurrencyNumber(currencyNumber);
			vouchers[entry].setEntryDC(-1);
			vouchers[entry].setOriginalAmount(Double.parseDouble(jkje));
			vouchers[entry].setDebitAmount(0);
			vouchers[entry].setCreditAmount(Double.parseDouble(jkje));
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
		return vouchers;
	}
	
	//凭证 header 信息
	public VoucherHeaderEntity getTravelHeader(String requestid,int userid) {
		String travel_tablename = getPropValue("Eas_WorkflowId","travel_tablename");
		String fieldid = getPropValue("Eas_Infos","fieldid");
		writeLog("------fieldid-----------"+fieldid);
		VoucherHeaderEntity entity = new VoucherHeaderEntity();
		CodeMappingUtil codeUtil = new CodeMappingUtil();
		RecordSet rs = new RecordSet();
		String sql = "SELECT * FROM "+travel_tablename+" WHERE requestId = "+requestid;
		rs.executeQuery(sql);
		rs.next();
		
		entity.setCompanyNumber(codeUtil.getXlkCompanyNumber(Util.null2String(rs.getString("sqdw"))));
		entity.setBookedDate(DateUtil.getCurrentDate());
		entity.setBizDate(Util.null2String(rs.getString("sqrq")));
		entity.setPeriodYear(codeUtil.getCurrentYear());
		entity.setPeriodNumber(codeUtil.getCurrentMonth());
		entity.setVoucherType("总");
		entity.setVoucherNumber(requestid);
		
		return entity;
	}
}
