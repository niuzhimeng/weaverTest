package com.weavernorth.voucher.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.weaver.general.BaseBean;

import weaver.conn.RecordSet;
import weaver.general.Util;

/**
 * @description: EAS集成    凭证信息  编码映射 工具类
 * @author: DJC
 * @date: 2019.11.19
 * @version: 1.0
 */
public class CodeMappingUtil extends BaseBean {
	
	/**
	 * @description: 查询eas公司编码     费用报销流程
	 * @param: userid  人员id
	 */
	public String getCompanyNumber(String sqdw) {
		writeLog("getCompanyNumber: "+sqdw);
		try {
			RecordSet rs = new RecordSet();
			
			String sql = "SELECT eas_account_code FROM uf_account" + 
					" WHERE isseal = 1 AND oa_name = (select dwmc1 from uf_zhxxdj where id = ?)";
			
			rs.executeQuery(sql,sqdw);
			String account_code = "";
			if(rs.next()) {
				account_code = Util.null2String(rs.getString("eas_account_code"));
				 
			}
			writeLog("getCompanyNumber----------account_code: "+account_code);
			return account_code;
		}catch(Exception e){    
			e.printStackTrace();
			writeLog("获取公司编码	error: "+e);
			return "GetCompanyNumber Error";
		}
	}
	
	/**
	 * @description: 根据选择框
	 * @param: sqdw  下拉框id
	 */
	public String getXlkCompanyNumber(String sqdw) {
		
		String fieldid = getPropValue("Eas_Infos","fieldid");
		writeLog("----获取下拉框公司编码--fieldid-----------"+fieldid+"---sqdw---"+sqdw);
		try {
			RecordSet rs = new RecordSet();
			
			String sql = " SELECT eas_account_code FROM uf_account" + 
					"   WHERE isseal = 1 AND oa_name =(select selectname" + 
					"    from workflow_selectitem where fieldid = "+fieldid+" and selectvalue = "+sqdw+")";
			writeLog("----获取下拉框公司编码--sql-----------"+sql);
			rs.executeQuery(sql);
			String account_code = "";
			if(rs.next()) {
				account_code = Util.null2String(rs.getString("eas_account_code"));
				
			}
			writeLog("获取下拉框公司编码----------account_code: "+account_code);
			return account_code;
		}catch(Exception e){    
			e.printStackTrace();
			writeLog("获取下拉框公司编码	error: "+e);
			return "获取下拉框公司编码 Error";
		}
	}
	
	
	/**
	 * @description: 查询 eas部门编码	OA部门名称
	 * @param: requestID  流程id
	 * 		   tableName  流程数据库表名
	 */
	public Map<String,String> getDepartCode(String requestid,String tableName) {
		writeLog("获取部门编码----------:"+requestid+"---"+tableName);
		Map<String,String> map = new HashMap<String, String>();
		try {
			RecordSet rs = new RecordSet();
			String sqlo = "SELECT departmentname FROM hrmdepartment" + 
					" WHERE id = (SELECT departmentid FROM hrmresource" + 
					" WHERE id = (SELECT sqr FROM "+tableName+" WHERE requestId = "+requestid+"))";
			rs.executeQuery(sqlo);
			String oa_departname = null;
			if(rs.next()) {
				oa_departname = Util.null2String(rs.getString("departmentname"));
				map.put("departname", oa_departname);
			}
			
			String sql = "SELECT eas_departcode FROM uf_department" + 
					" WHERE oa_departname = '"+oa_departname+"'";
			rs.executeQuery(sql);
			if(rs.next()) {
				String eas_departcode = Util.null2String(rs.getString("eas_departcode"));
				map.put("departcode", eas_departcode);
			}
			writeLog("获取部门编码	--------------map: "+map);
			return map;
		}catch(Exception e){
			e.printStackTrace();
			writeLog("获取部门编码	error: "+e);
			return map;
		}
	}
	
	/**
	 * @description: 查询 明细表中eas部门编码	OA部门名称
	 * @param: departId  部门id
	 */
	public Map<String,String> getMxDpartInfo(String departId) {
		writeLog("获取明细表部门编码----------departId--------: "+departId);
		Map<String,String> map = new HashMap<String, String>();
		try {
			RecordSet rs = new RecordSet();
			String sqlo = "SELECT departmentname FROM hrmdepartment WHERE id = "+departId;
			rs.executeQuery(sqlo);
			String oa_departname = null;
			if(rs.next()) {
				oa_departname = Util.null2String(rs.getString("departmentname"));
				map.put("departname", oa_departname);
			}
			
			String sql = "SELECT eas_departcode FROM uf_department WHERE oa_departname ='"+oa_departname+"'";
			rs.executeQuery(sql);
			if(rs.next()) {
				String eas_departcode = Util.null2String(rs.getString("eas_departcode"));
				map.put("departcode", eas_departcode);
			}
			writeLog("获取明细表部门编码---------map: "+map);
			return map;
		}catch(Exception e){
			e.printStackTrace();
			writeLog("获取明细表部门编码	error: "+e);
			return map;
		}
	}
	
	

	/**
	 * @description: 查询EAS费用科目编码
	 * @param: fykm  费用科目名称
	 */
	public Map<String,String> getEasCode(String fykm) {
		writeLog("获取EAS费用科目编码: "+fykm);
		Map<String,String> map = new HashMap<String, String>();
		try {
			RecordSet rs = new RecordSet();
			String sql = "SELECT * FROM uf_fyxm WHERE isseal = 1 AND fykm_name = (select erjikemu from uf_kemubiao where id = ?)";
			rs.executeQuery(sql,fykm);
			if(rs.next()) {
				String eas_code = Util.null2String(rs.getString("eas_code"));
				String per = Util.null2String(rs.getString("isaccount_per"));
				String depart = Util.null2String(rs.getString("isaccount_depart"));
				String cus = Util.null2String(rs.getString("isaccount_cus"));
				String fykm_name = Util.null2String(rs.getString("fykm_name"));
				map.put("eas_code", eas_code);
				map.put("per", per);
				map.put("depart", depart);
				map.put("cus", cus);
				map.put("fykm_name", fykm_name);
			}
			writeLog("获取EAS费用科目编码--------------map: "+map);
			return map;
		}catch(Exception e){
			e.printStackTrace();
			writeLog("获取EAS费用科目编码  error: "+e);
			return map;
		}
	}
	
	/**
	 * @description: 查询人员姓名  人员编码
	 * @param: sqr   申请人员id
	 */
	public Map<String,String> getLastName(int sqr) {
		writeLog("获取申请人 姓名编码---: "+sqr);
		Map<String,String> map = new HashMap<String, String>();
		try {
			RecordSet rs = new RecordSet();
			String sql = "SELECT lastname,workcode FROM hrmresource WHERE id = ?";
			rs.executeQuery(sql,sqr);
			if(rs.next()) {
				String lastname = Util.null2String(rs.getString("lastname"));
				String workcode = Util.null2String(rs.getString("workcode"));
				
				map.put("lastname", lastname);
				map.put("workcode", workcode);
			}
			writeLog("获取申请人 姓名编码---------------map: "+map);
			return map;
		}catch(Exception e){
			e.printStackTrace();
			writeLog("获取申请人 姓名编码	error: "+e);
			return map;
		}
	}
	
	/**
	 * @description: 查询人员姓名  人员编码
	 * @param: userid   制单人员id
	 */
	public String getZdName(int userid) {
		writeLog("获取制单人 姓名编码-------------userid: "+userid);
		try {
			RecordSet rs = new RecordSet();
			String sql = "SELECT lastname FROM hrmresource WHERE id = ?";
			rs.executeQuery(sql,userid);
			String lastname = "";
			if(rs.next()) {
				lastname = Util.null2String(rs.getString("lastname"));
			}
			writeLog("获取制单人 姓名编码----------lastname: "+lastname);
			return lastname;
		}catch(Exception e){
			e.printStackTrace();
			writeLog("获取制单人 姓名编码	error: "+e);
			return "制单人 error";
		}
	}
	
	/**
	 * @description: 查询客户编码信息
	 * @param: gys   供应商id
	 */
	public Map<String,String> getCusInfo(String gys) {
		writeLog("获取客户编码信息----------: "+gys);
		Map<String,String> map = new HashMap<String, String>();
		try {
			RecordSet rs = new RecordSet();
			String sql = "SELECT eas_cus_name,eas_cus_code FROM uf_customer WHERE isseal = 1 and id = ?";
			rs.executeQuery(sql,gys);
			if(rs.next()) {
				String cusname = Util.null2String(rs.getString("eas_cus_name"));
				String cuscode = Util.null2String(rs.getString("eas_cus_code"));
				
				map.put("cusname", cusname);
				map.put("cuscode", cuscode);
			}
			writeLog("获取客户编码信息----------------map: "+map);
			return map;
		}catch(Exception e){
			e.printStackTrace();
			writeLog("获取客户编码信息	error: "+e);
			return map;
		}
	}
	
	/**
	 * @description: 查询现金流量编码
	 * @param: fx  0-进账    1-出账
	 */
	public Map<String,String> getCashitem(String fx) {
		writeLog("getCashitem: "+fx);
		Map<String,String> map = new HashMap<String, String>();
		try {
			RecordSet rs = new RecordSet();
			String sql = "SELECT eas_llxm_name,eas_llxm_code FROM uf_cashitem WHERE isseal = 1 AND account_fx = ?";
			rs.executeQuery(sql,fx);
			if(rs.next()) {
				String cashname = Util.null2String(rs.getString("eas_llxm_name"));
				String cashcode = Util.null2String(rs.getString("eas_llxm_code"));
				
				map.put("cashname", cashname);
				map.put("cashcode", cashcode);
			}
			writeLog("getCashitem----------------map: "+map);
			return map;
		}catch(Exception e){
			e.printStackTrace();
			writeLog("获取现金流量编码	error: "+e);
			return map;
		}
	}
	
	/**
	 * @description: 查询银行科目编码
	 * @param: yhxx	银行信息
	 */
	public String getBankCode(String yhxx) {
		writeLog("获取银行编码----------: "+yhxx);
		try {
			RecordSet rs = new RecordSet();
			
			String sql = "SELECT eas_code FROM uf_yhkm WHERE fkyh_name = ?";
			
			rs.executeQuery(sql,yhxx);
			String eas_code = "";
			if(rs.next()) {
				eas_code = Util.null2String(rs.getString("eas_code"));
			}
			writeLog("获取银行编码-----------------eas_code: "+eas_code);
			return eas_code;
		}catch(Exception e){
			e.printStackTrace();
			writeLog("获取银行编码	error: "+e);
			return "getBankCode Error";
		}	
	}
	
	/**
	 * @description: 查询往来科目编码映射
	 * @param: workflowid	流程workflowId
	 * 		   gs			对公对私
	 */
	public Map<String,String> getWlkmMapping(String workflowid, String gs){
		
		writeLog("获取往来科目映射编码-------:"+workflowid+"获取往来科目映射编码---:"+gs);
		Map<String,String> map = new HashMap<String, String>();
		try {
			RecordSet rs = new RecordSet();
			String sql = "SELECT * from uf_wlSubMapping WHERE isseal = 1 AND workflowID = ? AND gslx = ?";
			writeLog("获取往来科目映射编码-----------:"+sql);
			rs.executeQuery(sql,workflowid,gs);
			if(rs.next()) {
				String name = Util.null2String(rs.getString("name"));
				String code = Util.null2String(rs.getString("code"));
				String isper = Util.null2String(rs.getString("isper"));
				String isdepart = Util.null2String(rs.getString("isdepart"));
				String iscus = Util.null2String(rs.getString("iscus"));
				
				map.put("name",name);
				map.put("code",code);
				map.put("per", isper);
				map.put("depart",isdepart);
				map.put("cus",iscus);
			}
			writeLog("获取往来科目映射编码------------map---:"+map);
			return map;
		}catch(Exception e){
			e.printStackTrace();
			writeLog("获取往来科目映射编码	error: "+e);
			return map;
		}
	}
	
	/**
	 * @description:   查询费用科目编码
	 * @param: sqbm		申请部门id
	 * 		   kmmcid	科目名称id
	 */
	public Map<String,String> getJtHsCode(String sqbm,String kmmcid) {
		writeLog("费用科目编码----------sqbm---: "+sqbm+"--kmmcid--"+kmmcid);
		Map<String,String> map = new HashMap<String, String>();
		try {
			RecordSet rs = new RecordSet();
			String sql = "select * from uf_fyxm where isseal = 1 AND fykm_id = "+kmmcid;
			writeLog("费用科目编码----------:"+sql);
			rs.executeQuery(sql);
			while(rs.next()) {
				String bms = Util.null2String(rs.getString("cs_depart"));
				
				if(!"".equalsIgnoreCase(bms) && bms != null) {
					String[] bmArr = bms.split(",");
					 
					
					for(String bm:bmArr) {
						 
						if(sqbm == bm || sqbm.equalsIgnoreCase(bm)) {
							String code = Util.null2String(rs.getString("eas_code"));
							String per = Util.null2String(rs.getString("isaccount_per"));
							String depart = Util.null2String(rs.getString("isaccount_depart"));
							String cus = Util.null2String(rs.getString("isaccount_cus"));
							String fykm_name = Util.null2String(rs.getString("fykm_name"));
							map.put("code", code);
							map.put("per", per);
							map.put("depart", depart);
							map.put("cus", cus);
							map.put("fykm_name", fykm_name);
						}
					}
				}else {
					String code = Util.null2String(rs.getString("eas_code"));
					String per = Util.null2String(rs.getString("isaccount_per"));
					String depart = Util.null2String(rs.getString("isaccount_depart"));
					String cus = Util.null2String(rs.getString("isaccount_cus"));
					String fykm_name = Util.null2String(rs.getString("fykm_name"));
					
					map.put("code", code);
					map.put("per", per);
					map.put("depart", depart);
					map.put("cus", cus);
					map.put("fykm_name", fykm_name);
				}
				
			}
			writeLog("根据职能部门获取费用科目编码--------map---: "+map);
			return map;
		}catch(Exception e){
			e.printStackTrace();
			writeLog("根据职能部门获取费用科目编码	error: "+e);
			return map;
		}
	}
	
	/**
	 * @description: 根据申请单位 id  查询银行核算项目编码
	 * @param: sqdw		申请单位 id
	 */
	public Map<String,String> getBankHsCode(String sqdw) {
		writeLog("--------------银行核算项目编码----------："+sqdw);
		Map<String,String> map = new HashMap<String, String>();
		
		try {
			RecordSet rs = new RecordSet();
			String sql = "select * from uf_yhkm where fkdwmc = (select dwmc1 from uf_zhxxdj where id = "+sqdw+")";
			writeLog("getBankHsCode: "+sql);
			rs.executeQuery(sql);
			rs.next();
			String code = Util.null2String(rs.getString("fkyh_code"));
			String name = Util.null2String(rs.getString("fkyh_name"));
			String jrjgname = Util.null2String(rs.getString("jrjgname"));
			String jrjgcode = Util.null2String(rs.getString("jrjgcode"));
			String eas_code = Util.null2String(rs.getString("eas_code"));
			map.put("name", name);
			map.put("code", code);
			map.put("jrjgname", jrjgname);
			map.put("jrjgcode", jrjgcode);
			map.put("eas_code", eas_code);
			writeLog("---银行核算项目编码---map:  "+map);
			return map;
		}catch(Exception e){
			e.printStackTrace();
			writeLog("获取银行核算项目编码------error: "+e);
			return map;
		}
		
	}
	
	/**
	 * @description: 根据下拉框申请单位 id  查询银行核算项目编码
	 * @param: sqdw		下拉框 id
	 */
	public Map<String,String> getXlkBankHsCode(String sqdw) {
		
		Map<String,String> map = new HashMap<String, String>();
		String fieldid = getPropValue("Eas_Infos","fieldid");
		writeLog("----下拉框---银行核算项目编码--fieldid-----------"+fieldid+"--------sqdw----"+sqdw);
		try {
			RecordSet rs = new RecordSet();
			String sql = "select * from uf_yhkm where fkdwmc = (" + 
					"    select selectname from workflow_selectitem" + 
					"     where fieldid = "+fieldid+" and selectvalue = "+sqdw+")";
			writeLog("下拉框---银行核算项目编码------------:"+sql);
			rs.executeQuery(sql);
			rs.next();
			String code = Util.null2String(rs.getString("fkyh_code"));
			String name = Util.null2String(rs.getString("fkyh_name"));
			String jrjgname = Util.null2String(rs.getString("jrjgname"));
			String jrjgcode = Util.null2String(rs.getString("jrjgcode"));
			String eas_code = Util.null2String(rs.getString("eas_code"));
			map.put("name", name);
			map.put("code", code);
			map.put("jrjgname", jrjgname);
			map.put("jrjgcode", jrjgcode);
			map.put("eas_code", eas_code);
			writeLog("---下拉框---银行核算项目编码---map:  "+map);
			return map;
		}catch(Exception e){
			e.printStackTrace();
			writeLog("下拉框---银行核算项目编码----error: "+e);
			return map;
		}
		
	}
	
	/**
	 * @description: 查询公司账套的币别           费用报销流程
	 * @param: sqdw	 浏览按钮集成（公司） id 
	 */
	public String getCurrency(String sqdw) {
		String currencyNumber = "";
		try {
			RecordSet rs = new RecordSet();
			String sql = "select currency from uf_account where oa_name" + 
					" = (select dwmc1 from uf_zhxxdj where id = "+sqdw+")";
			writeLog("公司账套的币别---SQL-:"+sql);
			rs.executeQuery(sql);
			if(rs.next()) {
				currencyNumber = Util.null2String(rs.getString("currency"));
			}
			writeLog("公司账套的币别----:"+currencyNumber);
			return currencyNumber;
		}catch(Exception e){
			e.printStackTrace();
			writeLog("公司账套的币别----:"+e);
			return currencyNumber;
		}
	}
	
	/**
	 * @description: 查询公司账套的币别
	 * @param: sqdw	   公司下拉框 id        
	 */
	public String getXlkCurrency(String sqdw) {
		String currencyNumber = "";
		String fieldid = getPropValue("Eas_Infos","fieldid");
		try {
			RecordSet rs = new RecordSet();
			String sql = "select currency from uf_account where oa_name = (" + 
					"    select selectname from workflow_selectitem" + 
					"    where fieldid = "+fieldid+" and selectvalue = "+sqdw+")";
			writeLog("下拉框---公司账套的币别---SQL-:"+sql);
			rs.executeQuery(sql);
			if(rs.next()) {
				currencyNumber = Util.null2String(rs.getString("currency"));
			}
			writeLog("下拉框---公司账套的币别----:"+currencyNumber);
			return currencyNumber;
		}catch(Exception e){
			e.printStackTrace();
			writeLog("下拉框---公司账套的币别----:"+e);
			return currencyNumber;
		}
	}
	
	/**
	 * @description: 获取当前年份
	 */
	public String getCurrentYear(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();String d = sdf.format(date);
        writeLog("获取当前年份----------:"+d);
        return sdf.format(date);
	}
	
	/**
	 * @description: 获取当前月份
	 */
	public String getCurrentMonth(){
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
        Date date = new Date();
        String d = sdf.format(date);
        writeLog("获取当前月份----------:"+d);
        return d;
	}
	
	

}
