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
 * @description: EAS集成   还款流程凭证信息拼接
 * @author: DJC
 * @date: 2019.11.19
 * @version: 1.0
 */
public class RepaymentVoucherBiz extends BaseBean {
	
	public WSWSVoucher[] repaymentInfo(String requestid,int userid) throws Exception{
		CodeMappingUtil codeUtil = new CodeMappingUtil();
		int entry = 0;//分录行
		String repayment_tablename = getPropValue("Eas_WorkflowId","repayment_tablename");
        String repayment_workflowid = getPropValue("Eas_WorkflowId","repayment_workflowid");
        String per_hs = getPropValue("Eas_Infos", "per"); 
		String bank_hs = getPropValue("Eas_Infos", "bank");
		String bank_account_hs = getPropValue("Eas_Infos", "bank_account");
		writeLog(" ----bank_hs---: "+bank_hs);
		
		RecordSet rs = new RecordSet();
		
		VoucherHeaderEntity entity = getRepaymentHeader(requestid,userid);
		WSWSVoucher[] vouchers = new WSWSVoucher[2];
		
		//主表信息
		String sql0 = "SELECT * FROM "+repayment_tablename+" where requestid = "+requestid;
		rs.executeQuery(sql0);
		rs.next();

		String hkje = Util.null2String(rs.getString("hkje"));//还款金额
		String hkdw = Util.null2String(rs.getString("hkdw1"));//还款单位
		//String sqbm = Util.null2String(rs.getString("sqbm"));//申请部门
		String sqr = Util.null2String(rs.getString("sqr"));//申请人
		String lcbh = Util.null2String(rs.getString("lcbh"));//流程编号
		 
		Map<String,String> bankMap = codeUtil.getXlkBankHsCode(hkdw);
		Map<String,String> perCodeMap = codeUtil.getLastName(Integer.valueOf(sqr));
		String currencyNumber = codeUtil.getXlkCurrency(hkdw);
		writeLog(" ----currencyNumber---: "+currencyNumber);
		//借方
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
		vouchers[entry].setVoucherAbstract(getPropValue("Eas_Infos", "hk_zy")+"-"+lcbh);
		vouchers[entry].setAccountNumber(bankMap.get("eas_code"));
		vouchers[entry].setCurrencyNumber(currencyNumber);
		vouchers[entry].setEntryDC(1);
		vouchers[entry].setOriginalAmount(Double.parseDouble(hkje));
		vouchers[entry].setDebitAmount(Double.parseDouble(hkje));
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
		
		//贷方
		Map<String,String> wlkmUtil = codeUtil.getWlkmMapping(repayment_workflowid, "2");//0-进账    1-出账
		
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
		vouchers[entry].setVoucherAbstract(getPropValue("Eas_Infos", "hk_zy")+"-"+lcbh);
		vouchers[entry].setAccountNumber(wlkmUtil.get("code"));//科目编码
		vouchers[entry].setCurrencyNumber(currencyNumber);
		vouchers[entry].setEntryDC(-1);
		vouchers[entry].setOriginalAmount(Double.parseDouble(hkje));
		vouchers[entry].setDebitAmount(0);
		vouchers[entry].setCreditAmount(Double.parseDouble(hkje));
		vouchers[entry].setAsstSeq(1);//辅助核算    部门、职员、客户
		if("0".equalsIgnoreCase(wlkmUtil.get("per"))) {
			vouchers[entry].setAsstActType1(per_hs);
			vouchers[entry].setAsstActName1(perCodeMap.get("lastname"));
			vouchers[entry].setAsstActNumber1(perCodeMap.get("workcode"));
		}
		vouchers[entry].setItemFlag(0);
		
		entry = entry+1;
		return vouchers;
	}
	
	//凭证 header 信息
	public VoucherHeaderEntity getRepaymentHeader(String requestid,int userid) {
		String repayment_tablename = getPropValue("Eas_WorkflowId","repayment_tablename");
		
		VoucherHeaderEntity entity = new VoucherHeaderEntity();
		CodeMappingUtil codeUtil = new CodeMappingUtil();
		RecordSet rs = new RecordSet();
		String sql = "SELECT * FROM "+repayment_tablename+" WHERE requestId = "+requestid;
		rs.executeQuery(sql);
		rs.next();
		
		entity.setCompanyNumber(codeUtil.getXlkCompanyNumber(Util.null2String(rs.getString("hkdw1"))));
		entity.setBookedDate(DateUtil.getCurrentDate());
		entity.setBizDate(Util.null2String(rs.getString("hksj")));
		entity.setPeriodYear(codeUtil.getCurrentYear());
		entity.setPeriodNumber(codeUtil.getCurrentMonth());
		entity.setVoucherType("总");
		entity.setVoucherNumber(requestid);
		
		return entity;
	}	
			
}
