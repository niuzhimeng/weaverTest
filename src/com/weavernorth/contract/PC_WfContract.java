package com.weavernorth.contract;

import org.json.JSONException;
import org.json.JSONObject;

import com.weavernorth.util.LogUtil;
import weaver.conn.RecordSet;
import weaver.general.Util;

/**
 * 合同类别及对应审批权限
 * 根据“合同类别”，“审批级别”，“审批权限金额”查询“授权限额明细”
 * @author wgh
 *
 */
public class PC_WfContract {
	public static JSONObject getContract(String htlb_value,String gljy_value,String je_value,String requestid,String startdate,String enddate) {
		JSONObject obj = new JSONObject();
		RecordSet rs = new RecordSet();
		//返回信息--授权限额明细
		String strMonetDt = "";
		//返回信息--审批级别
		String strApprovalLevel = "";
		//查询授权限额sql
		String strSelectSql = "select sqxemx,spjb from uf_pchtspsqxemx where htlb='"+htlb_value+"' and gljypd='"+gljy_value+"' and isnull (zx,0)<'"+je_value+"' and '"+je_value+"'<=isnull(zd,999999999999)";
		LogUtil.doWriteLog("==查询授权限额sql=="+strSelectSql);
		rs.execute(strSelectSql);
		if(rs.first()){
			strMonetDt = Util.null2String(rs.getString("sqxemx"));
			strApprovalLevel = Util.null2String(rs.getString("spjb"));
			updateFile(strApprovalLevel, requestid);
			try {
				obj.put("strMonetDt", strMonetDt);
				obj.put("strApprovalLevel", strApprovalLevel);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				LogUtil.doWriteLog("==获取授权限额异常=="+e);
			}
			LogUtil.doWriteLog("==查询授权限额值=="+strMonetDt+"==审批级别=="+strApprovalLevel);
		}
		LogUtil.doWriteLog("==startdate=="+startdate+"==enddate=="+enddate);
		return obj;
	}
	/**
	 * 更新表单中的审批级别
	 * @param ApprovalLevel
	 * @param requestid
	 */
	public static void updateFile(String ApprovalLevel ,String requestid){
		RecordSet rs = new RecordSet();
		if("137".equals(getWorkflowid(requestid))){
			String updatesql = "update "+getTableName(requestid)+" set spjb='"+ApprovalLevel+"' where requestid="+requestid;
			rs.execute(updatesql);
		}else if("144".equals(getWorkflowid(requestid))){
			String updatesql = "update "+getTableName(requestid)+" set spjbbgh='"+ApprovalLevel+"' where requestid="+requestid;
			rs.execute(updatesql);
		}
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
	public static void main(String[] args) {
		String a = "2018-01-10";
		int b = Integer.parseInt(a.replaceAll("-","").substring(0,4));
		System.out.println(b);
		int c = Integer.parseInt(a.replaceAll("-","").substring(4,8));
		System.out.println(c);
	}
}
