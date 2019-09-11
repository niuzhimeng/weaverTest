package com.weavernorth.contract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import com.weavernorth.util.LogUtil;
import weaver.conn.RecordSet;
import weaver.general.Util;

/**
 * 付款流程、退款流程根据合同编号查询，该合同编号版本之前的所有编号
 * 
 * @author wgh
 * 
 */
public class WfContractNum {

	/**
	 * 变更流程查询  变更前合同号----(2)
	 * @param requestid
	 * @return
	 */
	public static JSONObject getContNum(String requestid) {
		JSONObject json = new JSONObject();
		RecordSet rs = new RecordSet();
		// 该合同号之前的版本号
		String strBeforeContractNum = "";
		// 该合同号之前的版本号合同编号
		String strBeforeContractName = "";
		// 该合同号之前的版本号
		String strBeforeContractAllNum = "";
		// 该合同号之前的版本号合同编号
		String strBeforeContractAllName = "";
		// 选中的合同编号
		String strContractNum = getContractNumByReqid(requestid);
		// 当合同号的最后一位为0位时，该合同号为签订合同流程合同号，没有之前的版本
		if ("0".equals(strContractNum.substring(strContractNum.length()-1))) {
			strBeforeContractNum = "0";
			strBeforeContractName = "0";
		} else {
			// 查询该合同号之前的版本号
			String BeforeContractNumSql = "select distinct ylc from uf_hbhtbd where (htbh=SUBSTRING('"
					+ strContractNum
					+ "',0,len('"
					+ strContractNum
					+ "')-1) or (htbh like '"
					+ strContractNum.substring(0, strContractNum.length() - 1)
					+ "%' and SUBSTRING(htbh, len(htbh) , len(htbh))<SUBSTRING('"
					+ strContractNum
					+ "',len('"
					+ strContractNum
					+ "'),len('"
					+ strContractNum + "')))) or htbh='"+strContractNum+"'";
			LogUtil.doWriteLog("======查询名称sql  1==="+BeforeContractNumSql);
			rs.execute(BeforeContractNumSql);
			while (rs.next()) {
				// 拼接requestid
				strBeforeContractNum += rs.getString("ylc") + ",";
				strBeforeContractName += getContractNumByReqid(rs.getString("ylc")) + ",";
				
				// 去掉最后一个逗号
				strBeforeContractAllNum = strBeforeContractNum.substring(0,
						strBeforeContractNum.length() - 1);
				strBeforeContractAllName = strBeforeContractName.substring(0,
						strBeforeContractName.length() - 1);
				LogUtil.doWriteLog(strBeforeContractAllNum + "==拼接合同id及名称==" + strBeforeContractAllName);
				try {
					json.put("strBeforeContractAllNum", strBeforeContractAllNum);
					json.put("strBeforeContractAllName", strBeforeContractAllName);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					LogUtil.debugLog("----查询合同号及名称时异常---"+e);
				}
			}
		}
		if("".equals(strBeforeContractAllNum)){
			try {
				json.put("strBeforeContractAllNum", "0");
				json.put("strBeforeContractAllName", "0");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				LogUtil.debugLog("----查询合同号及名称时异常---"+e);
			}
		}
		return json;
	}

	/**
	 * 付款流程---查询合同号----（1）
	 * @param requestid
	 * @return
	 */
	public static List<Map<String, String>> getContractNum(String requestid) {
		List<Map<String ,String>> list=new ArrayList<Map<String ,String>>();
		RecordSet rs = new RecordSet();
		// 该合同号之前的版本号
		String strBeforeContractNum = "";
		// 该合同号之前的版本号
		String strBeforeContractAllNum = "";
		// 改合同号之前的合同名称
		String strBeforeContractAllName = "";
		// 选中的合同编号
		String strContractNum = getContractNumByReqid(requestid);
		// 当合同号的长度为6位时，该合同号为签订合同流程合同号，没有之前的版本
		if (strContractNum.length() == 0) {
			strBeforeContractNum = "0";
		} else {
			// 查询该合同号之前的版本号
			String BeforeContractNumSql = "select distinct ylc from uf_hbhtbd where (htbh=SUBSTRING('"
					+ strContractNum
					+ "',0,len('"
					+ strContractNum
					+ "')-1) or (htbh like '"
					+ strContractNum.substring(0, strContractNum.length() - 1)
					+ "%' and SUBSTRING(htbh, len(htbh) , len(htbh))<SUBSTRING('"
					+ strContractNum
					+ "',len('"
					+ strContractNum
					+ "'),len('"
					+ strContractNum + "')))) or htbh='"+strContractNum+"'";
			LogUtil.doWriteLog("==查询总体该合同号之前的版本==" + BeforeContractNumSql);
			rs.execute(BeforeContractNumSql);
			while (rs.next()) {
				// 当合同号为最后一位为0时，无需带出之前版本的合同号
				if ("0".equals(strContractNum.substring(strContractNum.length()-1))) {
					// 拼接requestid
					strBeforeContractNum += "0,";
					// 去掉最后一个逗号
					strBeforeContractAllNum = strBeforeContractNum.substring(0,
							strBeforeContractNum.length() - 1);
					strBeforeContractAllName  += "0,";
				}else{
					// 拼接requestid
					strBeforeContractNum += rs.getString("ylc") + ",";
					// 去掉最后一个逗号
					strBeforeContractAllNum = strBeforeContractNum.substring(0,
							strBeforeContractNum.length() - 1);
				}
				
				
				RecordSet rsmap = new RecordSet();
			    // 查询OA号，发票金额
			    String strSql = "select spdbh,fkje,fkrqq from formtable_main_17 where htbh="+rs.getString("ylc");
			    LogUtil.doWriteLog("==查询付款表单=="+strSql);
			    rsmap.execute(strSql);
			    if(rsmap.getCounts()>0 ){
				    while(rsmap.next()){
				    	Map<String ,String> map = new HashMap<String ,String>();
				    	String strOANum = rsmap.getString("spdbh");
						String strInvoiceMoney = rsmap.getString("fkje");
						String strPayDate = rsmap.getString("fkrqq");
						LogUtil.doWriteLog("==class--strOANum=="+strOANum);
						LogUtil.doWriteLog("==class--strInvoiceMoney=="+strInvoiceMoney);
						LogUtil.doWriteLog("==class--strPayDate=="+strPayDate);
						LogUtil.doWriteLog("==class--strBeforeContractAllNum=="+strBeforeContractAllNum);
						LogUtil.doWriteLog("==class--strBeforeContractAllName=="+strBeforeContractAllName);

						map.put("strOANum", strOANum) ;
						map.put("strInvoiceMoney", strInvoiceMoney) ;
						map.put("strPayDate", strPayDate) ;
						
						map.put("strBeforeContractAllNum",strBeforeContractAllNum) ;
						map.put("strBeforeContractAllName", strBeforeContractAllName) ;
						
						list.add(map);
				    }
			    }else{
			    	Map<String ,String> map = new HashMap<String ,String>();
			    	LogUtil.doWriteLog("==class--strBeforeContractAllNum=="+strBeforeContractAllNum);
					LogUtil.doWriteLog("==class--strBeforeContractAllName=="+strBeforeContractAllName);
			    	map.put("strBeforeContractAllNum",strBeforeContractAllNum) ;
					map.put("strBeforeContractAllName", strBeforeContractAllName) ;
					list.add(map);
			    }
			}
		}
		return list;
	}

	/**
	 * 根据requestid查询建模表中的合同号
	 * 
	 * @param requestid
	 * @return
	 */
	public static String getContractNumByReqid(String requestid) {
		RecordSet rs = new RecordSet();
		// 合同号
		String strContractNum = "";
		// 查询sql
		String strSelectSql = "select distinct htbh from uf_hbhtbd where ylc='"
				+ requestid + "'";
		LogUtil.doWriteLog("==根据requestid查询建模表中的合同号==" + strSelectSql);
		rs.execute(strSelectSql);
		if (rs.first()) {
			strContractNum = rs.getString("htbh");
		}
		return strContractNum;
	}
	
	/**
	 * 根据requestid，合同号查询OA号、发票金额
	 * @param requestid
	 * @return
	 */
	public static JSONObject getContractInfo(String requestid){
		JSONObject json = new JSONObject();
	    RecordSet rs = new RecordSet();
	    // 查询OA号，发票金额
	    String strSql = "select * from formtable_main_17 where htbh="+requestid;
	    LogUtil.doWriteLog("==查询付款表单=="+strSql);
	    rs.execute(strSql);
	    if(rs.first()){
	    	try {
    			json.put("strOANum", Util.null2String(rs.getString("spdbh")));
				json.put("strInvoiceMoney", Util.null2String(rs.getString("fkje")));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				LogUtil.debugLog("==查询合同变更流程OA号，发票金额异常==");
			}
	    }
	    
		return json;
	}
	
	/**
	 * 获取workflowid
	 * 
	 * @param requestid
	 * @return
	 */
	public static String getWorkflowid(String requestid) {
		RecordSet rs = new RecordSet();
		// 流程类型id
		String strWorkflowid = "";
		String Sql = "select workflowid from workflow_requestbase where requestid="
				+ requestid;
		rs.execute(Sql);
		if (rs.first()) {
			strWorkflowid = rs.getString("workflowid");
		}
		return strWorkflowid;
	}

	/**
	 * 根据requestid获取流程表名
	 * 
	 * @param requestid
	 * @return
	 */
	public static String getTableName(String requestid) {
		RecordSet rs = new RecordSet();
		// 表单表名
		String strTableName = "";
		// 获取表名
		String strSearchFormid = "select tablename from workflow_bill where id=(select formid from workflow_base where id="
				+ getWorkflowid(requestid) + ")";
		rs.execute(strSearchFormid);
		if (rs.first()) {
			strTableName = Util.null2o(rs.getString("tablename"));
		} else {
			LogUtil.doWriteLog("--查询表单表名失败--" + strSearchFormid);
		}
		return strTableName;
	}
}
