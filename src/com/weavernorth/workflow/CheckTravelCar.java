package com.weavernorth.workflow;

import com.weavernorth.util.LogUtil;
import weaver.conn.RecordSet;
import weaver.formmode.interfaces.action.BaseAction;
import weaver.general.Util;
import weaver.soa.workflow.request.RequestInfo;

public class CheckTravelCar extends BaseAction{
	/**
	 * 流程节点后附加操作
	 */
	@Override
	public String execute(RequestInfo request) {
		LogUtil.doWriteLog("======差旅及用车流程节点后附加操作======");
		RecordSet RsSelectTabel = new RecordSet();
		RecordSet RsSelect = new RecordSet();
		RecordSet RsCheck = new RecordSet();
		// 是否允许提交     yes:允许提交    no:不允许提交
		String strFlag = "yes";
		//差旅人
		String strHrmId = "";
		//差旅开始日期时间
		String strStartTime = "";
		//差旅结束日期时间
		String strEndTime = "";
		// 流程请求id
		String requestid = request.getRequestid();
		// 流程id
		String workflowid = request.getWorkflowid();
		// 流程表名
		String strTablename = "";
		// 获取表名
		String strSearchFormid = "select tablename from workflow_bill where id=(select formid from workflow_base where id="
				+ workflowid + ")";
		RsSelectTabel.executeSql(strSearchFormid);
		if (RsSelectTabel.next()) {
			strTablename = Util.null2String(RsSelectTabel
					.getString("tablename"));
		}
		
		//查询本条流程信息
		String SelectSql = "select * from "+strTablename+"  where requestid="+requestid;
		RsSelect.execute(SelectSql);
		if(RsSelect.first()){
			strHrmId = RsSelect.getString("Name_Number");
			strStartTime = RsSelect.getString("Travel_Date")+" "+RsSelect.getString("cnvb");
			strEndTime = RsSelect.getString("Travel_End_Date")+" "+RsSelect.getString("stop_time");
			//查询开始日期时间是否重复(已有时间是否包含本条流程时间)			
			String strCheckStartSql = "select * from "+strTablename+" where requestid<>"+requestid+" and Name_Number="+strHrmId+" and '"+strStartTime+"' between  Travel_Date+' '+cnvb and Travel_End_Date+' '+stop_time";
			LogUtil.doWriteLog("======查询开始日期时间是否重复(已有时间是否包含本条流程时间)======"+strCheckStartSql);
			RsCheck.execute(strCheckStartSql);
			if(RsCheck.first()){
				strFlag = "no";
			}
			//查询结束日期时间是否重复(已有时间是否包含本条流程时间)	
			String strCheckEndSql = "select * from "+strTablename+" where requestid<>"+requestid+" and Name_Number="+strHrmId+" and '"+strEndTime+"' between  Travel_Date+' '+cnvb and Travel_End_Date+' '+stop_time";
			LogUtil.doWriteLog("======查询结束日期时间是否重复(已有时间是否包含本条流程时间)======"+strCheckEndSql);
			RsCheck.execute(strCheckEndSql);
			if(RsCheck.first()){
				strFlag = "no";
			}
		}
		
		//查询开始日期时间是否重复(本条流程时间是否包含已有时间)		
		String strCheckStartBackSql = "select * from "+strTablename+" where requestid<>"+requestid+" and Name_Number="+strHrmId+" and Travel_Date+' '+cnvb between '"+strStartTime+"'  and '"+strEndTime+"'";
		LogUtil.doWriteLog("======查询开始日期时间是否重复(本条流程时间是否包含已有时间)======"+strCheckStartBackSql);
		RsCheck.execute(strCheckStartBackSql);
		if(RsCheck.first()){
			strFlag = "no";
		}
		//查询结束日期时间是否重复(本条流程时间是否包含已有时间)	
		String strCheckEndBackSql = "select * from "+strTablename+" where requestid<>"+requestid+" and Name_Number="+strHrmId+" and Travel_End_Date+' '+stop_time between '"+strStartTime+"'  and '"+strEndTime+"'";
		LogUtil.doWriteLog("======查询结束日期时间是否重复(本条流程时间是否包含已有时间)======"+strCheckEndBackSql);
		RsCheck.execute(strCheckEndBackSql);
		if(RsCheck.first()){
			strFlag = "no";
		}
		LogUtil.doWriteLog("====是否允许提交======"+strFlag);
		//禁止提交流程
		if(strFlag.equals("no")){
			request.getRequestManager().setMessageid("12500");
			request.getRequestManager().setMessagecontent("差旅时间已经重复，请核对。");
			//return "0";
		}
		
		return SUCCESS;
	}
}
