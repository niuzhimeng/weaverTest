package com.weavernorth.workflow;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.weavernorth.util.LogUtil;
import weaver.conn.RecordSet;
import weaver.general.Util;
import weaver.hrm.resource.ResourceComInfo;

/**
 * 根据requestid查询一些相关信息
 * 
 * @author wgh
 */
public class RequestComInfo {
	
	
	/**
	 * 获取第一节点名称
	 * 
	 * @param workflowid

	 * @return
	 */
	public static String getFirstnode(String workflowid) {
		RecordSet rs = new RecordSet();
		// 流程类型id
		String strNodeName = "";
		//节点ID
		String strNodeid = "";
		String Sql = "select nodeid from workflow_flownode where nodetype = 0 and workflowid =  "+workflowid;
		rs.execute(Sql);
		if (rs.next()) {
			strNodeid = rs.getString("nodeid");
		}
		rs.execute("select nodename from workflow_nodebase where id = '"+strNodeid+"' and isstart = '1'");
		if (rs.next()) {
			strNodeName = rs.getString("nodename");
		}
		return strNodeName;
	}
	
	
	/**
	 * 获取数据库字段名
	 * 
	 * @param 表单名称
	 * @param 要获取的URL或者是创建人字段的数据库库字段名
	 * @return
	 */
	public static String getDatabaseField(String workflowid,String Field) {
		RecordSet rs = new RecordSet();
		// 流程类型id
		String strWorkflowid = "";
		String Sql = "select "+Field+" from wfdeploy where workflowid="
				+ workflowid;
		rs.execute(Sql);
		if (rs.first()) {
			strWorkflowid = rs.getString(Field);
		}
		return strWorkflowid;
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

	/**
	 * 根据workflowid获取流程表名
	 * 
	 * @param requestid
	 * author xly 20170621
	 * @return
	 */
	public static String getTableName2(String workflowid) {
		RecordSet rs = new RecordSet();
		// 表单表名
		String strTableName = "";
		// 获取表名
		String strSearchFormid = "select tablename from workflow_bill where id=(select formid from workflow_base where id= "+workflowid+")";
		rs.execute(strSearchFormid);
		if (rs.first()) {
			strTableName = Util.null2o(rs.getString("tablename"));
		} else {
			LogUtil.doWriteLog("--查询表单表名失败--" + strSearchFormid);
		}
		return strTableName;
	}
	/**
	 * 根据requestid获取表单主表id
	 * 
	 * @param requestid
	 * @return
	 */
	public static String getFormid(String requestid) {
		RecordSet rs = new RecordSet();
		// 流程表单中的主表id
		String strFormid = "";
		String Sql = "select id from " + getTableName(requestid)
				+ " where requestid=" + requestid;
		rs.execute(Sql);
		if (rs.first()) {
			strFormid = Util.null2o(rs.getString("id"));
		} else {
			LogUtil.doWriteLog("--查询表单id失败--" + Sql);
		}
		return strFormid;
	}

	/**
	 * 根据requestID查询流程上一节点名称
	 * 
	 * @param requestid
	 * @return
	 */
	public static String getLastNodeName(String requestid) {
		RecordSet rs = new RecordSet();
		// 返回数组形式
		JSONArray jsonArray = new JSONArray();
		// 当前节点名称
		String strCurrentNode = "";
		// 定义个数
		int i = 0;
		// sql语句
		String Sql = "select wfnb.nodename from workflow_flownode wffn,workflow_nodebase wfnb where wffn.nodeid=wfnb.id and wffn.workflowid="
				+ getWorkflowid(requestid)
				+ " order by CAST(LEFT(wfnb.nodename,2) AS FLOAT) asc";
		rs.execute(Sql);
		while (rs.next()) {
			strCurrentNode = rs.getString("nodename");
			try {
				jsonArray.put(i, strCurrentNode);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				LogUtil.doWriteLog("--json数组存值失败--" + e);
			}
			i++;
			LogUtil.doWriteLog("--查询流程上一节点节点--" + strCurrentNode);
		}

		return jsonArray.toString();
	}

	/**
	 * 根据requestID查询当前节点名称
	 * 
	 * @param requestid
	 * @return
	 */
	public static String getCurrentNodeName(String requestid) {
		JSONObject jsonObj = new JSONObject();
		RecordSet rs = new RecordSet();
		RecordSet rs1 = new RecordSet();
		// 返回数据
		String strReturn = "";
		// 当前节点名称
		String strLastnodeName = "";
		// 当前审批人
		String strCurrentOperator = "";
		// 查询当前节点操作人id sql语句
		String Sql = "select wfcp.userid from workflow_currentoperator wfcp,workflow_requestbase wfrb where wfcp.requestid=wfrb.requestid and wfrb.currentnodeid=wfcp.nodeid  and  wfcp.requestid="
				+ requestid;
		rs.execute(Sql);
		if (rs.first()) {
			strCurrentOperator = rs.getString("userid");
			LogUtil.doWriteLog("--查询流程当前节点操作人--" + strCurrentOperator);
		} else {
			LogUtil.doWriteLog("--查询流程当前节点操作人失败--" + Sql);
		}
		// 查询当前节点名称
		String Sql1 = "select wfrb.requestname,wfnb.nodename from workflow_requestbase wfrb,workflow_nodebase wfnb where wfrb.currentnodeid = wfnb.id and wfrb.requestid="
				+ requestid;
		rs1.execute(Sql1);
		if (rs1.first()) {
			strLastnodeName = rs1.getString("nodename");
			LogUtil.doWriteLog("--查询流程当前节点--" + strLastnodeName);
		} else {
			LogUtil.doWriteLog("--查询流程当前节点失败--" + Sql);
		}
		ResourceComInfo hrm = null;
		try {
			hrm = new ResourceComInfo();
			LogUtil.doWriteLog("--当前节点操作人--"
					+ hrm.getAccount(strCurrentOperator));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LogUtil.doWriteLog("--人员id转人员姓名失败---" + e);
		}
		strReturn = "{'" + strLastnodeName + "':'"
				+ hrm.getAccount(strCurrentOperator) + "'}";

		return strReturn;
	}

	/**
	 * 根据requestID查询流程上一节点操作人
	 * 
	 * @param requestid
	 * @return
	 */
	public static String getLastNodeUserid(String requestid) {
		RecordSet rs = new RecordSet();
		// 当前节点名称
		String strLastNodeUserid = "";
		// sql语句
		String Sql = "select wfrb.lastoperator from workflow_requestbase wfrb,workflow_nodebase wfnb where wfrb.lastnodeid = wfnb.id and wfrb.requestid="
				+ requestid;
		rs.execute(Sql);
		if (rs.first()) {
			strLastNodeUserid = rs.getString("lastoperator");
			LogUtil.doWriteLog("--查询流程上一节点操作人--" + strLastNodeUserid);
		} else {
			LogUtil.doWriteLog("--查询流程上一节点操作人失败--" + Sql);
		}
		return strLastNodeUserid;
	}

	/**
	 * 根据requestID查询流程上一节点名称
	 * 
	 * @param requestid
	 * @return
	 */
	public static String getLastNodeNamePush(String requestid) {
		RecordSet rs = new RecordSet();
		// 当前节点名称
		String strLastNodeName = "";
		// sql语句
		String Sql = "select wfnb.id,wfnb.nodename from workflow_requestbase wfrb,workflow_nodebase wfnb where wfrb.lastnodeid = wfnb.id and wfrb.requestid="
				+ requestid;
		rs.execute(Sql);
		if (rs.first()) {
			strLastNodeName = rs.getString("nodename");
			LogUtil.doWriteLog("--查询流程上一节点节点--" + strLastNodeName);
		} else {
			LogUtil.doWriteLog("--查询流程上一节点节点失败--" + Sql);
		}
		return strLastNodeName;
	}

	/**
	 * 根据requestID查询当前节点名称
	 * 
	 * @param requestid
	 * @return
	 */
	public static String getCurrentNodeNamePush(String requestid) {
		RecordSet rs = new RecordSet();
		// 当前节点名称
		String strCurrentNodeName = "";
		// 查询当前节点名称
		String Sql1 = "select wfrb.requestname,wfnb.nodename from workflow_requestbase wfrb,workflow_nodebase wfnb where wfrb.currentnodeid = wfnb.id and wfrb.requestid="
				+ requestid;
		rs.execute(Sql1);
		if (rs.first()) {
			strCurrentNodeName = rs.getString("nodename");
			LogUtil.doWriteLog("--查询流程当前节点--" + strCurrentNodeName);
		} else {
			LogUtil.doWriteLog("--查询流程当前节点失败--" + Sql1);
		}
		return strCurrentNodeName;
	}

	/**
	 * 根据requestID查询操作时间
	 * 
	 * @param requestid
	 * @return
	 */
	public static String getOperatonTime(String requestid) {
		RecordSet rs = new RecordSet();
		// 操作时间
		String strOperatonTime = "";
		// sql语句
		String Sql = "select lastoperatedate,lastoperatetime from workflow_requestbase where requestid="
				+ requestid;
		rs.execute(Sql);
		if (rs.first()) {
			strOperatonTime = rs.getString("lastoperatedate") + " "
					+ rs.getString("lastoperatetime");
			LogUtil.doWriteLog("--查询流程操作时间--" + strOperatonTime);
		} else {
			LogUtil.doWriteLog("--查询流程操作时间失败--" + Sql);
		}
		return strOperatonTime;
	}

	/**
	 * 根据requestID查询流程签字意见
	 * 
	 * @param requestid
	 * @return
	 */
	public static String getRemark(String requestid) {
		RecordSet rs = new RecordSet();
		// 签字意见
		String strRemark = "";
		// sql语句
		String Sql = "select remark from workflow_requestLog wfrl,workflow_requestbase wfrb where wfrl.nodeid=wfrb.lastnodeid and wfrl.requestid=wfrb.requestid and wfrl.requestid="
				+ requestid+ " and operatedate = convert(char(10),getdate(),120) order by operatetime desc";
		rs.execute(Sql);
		LogUtil.doWriteLog("--查询流程流程签字意见sql--" + Sql);
		if (rs.first()) {
			strRemark = rs.getString("remark");
			LogUtil.doWriteLog("--查询流程流程签字意见--" + strRemark);
		} else {
			LogUtil.doWriteLog("--查询流程流程签字意见失败--" + Sql);
		}
		return strRemark;
	}

	/**
	 * 流程流转是否完成
	 * 
	 * @param requestid
	 * @return 0 否 1 是
	 */
	public static String getIsComplete(String requestid) {
		RecordSet rs = new RecordSet();
		// 是否完成 0 否 1 是
		String strIsComplete = "";
		// sql语句
		String Sql = "select currentnodetype from workflow_requestbase where requestid="
				+ requestid;
		rs.execute(Sql);
		if (rs.first()) {
			if ((rs.getString("currentnodetype")).equals("3")) {
				strIsComplete = "1";
			} else {
				strIsComplete = "0";
			}
			LogUtil.doWriteLog("--查询流程是否归档--" + strIsComplete);
		} else {
			LogUtil.doWriteLog("--查询流程是否归档失败--" + Sql);
		}
		return strIsComplete;
	}
}
