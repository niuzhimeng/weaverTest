package com.weavernorth.workflow;

import com.weavernorth.util.LogUtil;
import weaver.conn.RecordSet;
import weaver.formmode.interfaces.action.BaseAction;
import weaver.general.Util;
import weaver.soa.workflow.request.RequestInfo;

public class WfCheckBudget extends BaseAction {
	/**
	 * 流程节点后附加操作
	 */
	public String execute(RequestInfo request) {
		LogUtil.doWriteLog("==预结算流程节点后验证==");
		RecordSet RsSelectTabel = new RecordSet();
		// 重复数据
		String strDate = "";
		// 重复单号
		String strOrderCode = "";
		// 重复序号
		String strNum = "";
		// workflowid
		String workflowid = request.getWorkflowid();
		// requestid
		String requestid = request.getRequestid();
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
		strDate = CheckNowWf(strTablename, requestid);
		// 查询本流程中的 工单号是否重复
		if (!"S".equals(strDate)) {
			String[] strs = strDate.split("#");
			strOrderCode = strs[0].toString();
			strNum = strs[1].toString();
			request.getRequestManager().setMessageid("12508");
			request.getRequestManager().setMessagecontent(
					"本流程中存在重复 工单号:" + strOrderCode + ",序号:" + strNum
							+ ",请检查本流程填写数据。");
		} else {
			strDate = CheckHistoryWf(strTablename, requestid);
			// 查询历史流程中的 工单号是否重复
			if (!"S".equals(strDate)) {
				String[] strs = strDate.split("#");
				strOrderCode = strs[0].toString();
				strNum = strs[1].toString();
				request.getRequestManager().setMessageid("12509");
				request.getRequestManager().setMessagecontent(
						"历史流程中存在重复 工单号:" + strOrderCode + ",序号:" + strNum
								+ ",请检查本流程填写数据。");
			}
		}

		return SUCCESS;
	}

	/**
	 * 
	 * @param tablename
	 *            表名
	 * @param requestid
	 * @return 是否有重复数据
	 */
	public static String CheckNowWf(String tablename, String requestid) {
		RecordSet rsnow = new RecordSet();
		RecordSet rs = new RecordSet();
		// 返回信息
		String strMsg = "S";
		// 遍历当前流程的明细数据
		String NowSql = "select (fmdt.Work_number+'#'+ fmdt.Process) as value from "
				+ tablename
				+ " fm,"
				+ tablename
				+ "_dt1 fmdt where fm.id=fmdt.mainid and requestid="
				+ requestid;
		LogUtil.doWriteLog("==预结算流程  遍历当前流程的明细数据==" + NowSql);
		rsnow.execute(NowSql);
		while (rsnow.next()) {
			String strValue = rsnow.getString("value");
			// 查询当前流程的明细数据是否存在超过2条的数据
			String Sql = "select 1 from " + tablename + " fm," + tablename
					+ "_dt1 fmdt where fm.id=fmdt.mainid and requestid="
					+ requestid + " and (fmdt.Work_number+'#'+ fmdt.Process)='"
					+ strValue + "'";
			LogUtil.doWriteLog("==预结算流程  查询当前流程的明细数据是否存在超过2条的数据==" + Sql);
			rs.execute(Sql);
			if (rs.getCounts() > 1) {
				strMsg = strValue;
			}
		}

		return strMsg;
	}

	/**
	 * 
	 * @param tablename
	 *            表名
	 * @param requestid
	 * @return 是否有重复数据
	 */
	public static String CheckHistoryWf(String tablename, String requestid) {
		RecordSet rsnow = new RecordSet();
		RecordSet rs = new RecordSet();
		// 返回信息
		String strMsg = "S";
		// 遍历当前流程的明细数据
		String NowSql = "select (fmdt.Work_number+'#'+ fmdt.Process) as value from "
				+ tablename
				+ " fm,"
				+ tablename
				+ "_dt1 fmdt where fm.id=fmdt.mainid and requestid="
				+ requestid;
		LogUtil.doWriteLog("==预结算流程  遍历当前流程的明细数据==" + NowSql);
		rsnow.execute(NowSql);
		while (rsnow.next()) {
			String strValue = rsnow.getString("value");
			// 查询当前流程的明细数据是否存在超过2条的数据
			String Sql = "select 1 from "
					+ tablename
					+ " fm,"
					+ tablename
					+ "_dt1 fmdt where fm.id=fmdt.mainid and (fmdt.Work_number+'#'+ fmdt.Process)='"
					+ strValue + "'";
			LogUtil.doWriteLog("==预结算流程  查询历史流程的明细数据是否存在超过2条的数据==" + Sql);
			rs.execute(Sql);
			if (rs.getCounts() > 1) {
				strMsg = strValue;
			}
		}

		return strMsg;
	}

	public static void main(String[] args) {
		String str = "000600079203#0020";
		String[] strs = str.split("#");
		System.out.println(strs[0].toString());
		System.out.println(strs[1].toString());

	}

}
