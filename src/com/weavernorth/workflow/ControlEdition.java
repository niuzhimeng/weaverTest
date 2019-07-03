package com.weavernorth.workflow;

import weaver.soa.workflow.request.RequestInfo;
import com.weavernorth.util.LogUtil;
import weaver.conn.RecordSet;
import weaver.formmode.interfaces.action.BaseAction;
import weaver.general.BaseBean;
import weaver.general.Util;

public class ControlEdition extends BaseAction {
	private static BaseBean bb = new BaseBean();
	// 合同签订流程
	private static String strContractOrderWfid = bb.getPropValue(
			"wnrequestinfo", "strContractOrderWfid");
	// 合同变更流程
	private static String strContractChangeWfid = bb.getPropValue(
			"wnrequestinfo", "strContractChangeWfid");
	private static String strkuangjiaWfid = bb.getPropValue(
			"wnrequestinfo", "strkuangjiaWfid");
	/**
	 * 流程节点后附加操作
	 */
	public String execute(RequestInfo request) {
		LogUtil.doWriteLog("======合同签订、变更流程节点后附加操作======");
		if ("submit".equals(request.getRequestManager().getSrc())) {
			RecordSet RsSelectTabel = new RecordSet();
			RecordSet RsSelect = new RecordSet();
			// 是否允许提交 yes:允许提交 no:不允许提交
			String strFlag = "yes";
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
			// 合同签订流程
			if (strContractOrderWfid.equals(workflowid)||strkuangjiaWfid.equals(workflowid)) {
				String SelectEditionNum = "select zwbbh,fjbbh,htfjbbh,htzwwd,bdsczw ,htfj from "
						+ strTablename + " where requestid=" + requestid;
				LogUtil.doWriteLog("===合同签订流程查询版本号==="+SelectEditionNum);
				RsSelect.execute(SelectEditionNum);
				if (RsSelect.first()) {
					// 第一节点 新建正文 版本号
					String OneNodeXJZWnum = Util.null2o(RsSelect
							.getString("zwbbh"));
					// 第一节点 上传正文 版本号
					String OneNodeSCZWWnum = Util.null2o(RsSelect
							.getString("htfjbbh"));
					// 第一节点 合同附件 版本号
					String OneNodeHTFJnum = Util.null2o(RsSelect
							.getString("fjbbh"));
					// 当前 新建正文 版本号
					String NowNodeXJZWnum = Util
							.null2o(getMaxNumByDocids(RsSelect
									.getString("htzwwd")));
					// 当前 上传正文 版本号
					String NowNodeSCZWWnum = Util
							.null2o(getMaxNumByDocids(RsSelect
									.getString("htfj")));
					// 当前 合同附件 版本号
					String NowNodeHTFJWnum = Util
							.null2o(getMaxNumByDocids(RsSelect
									.getString("bdsczw")));
					// 验证版本号
					if (OneNodeXJZWnum.equals(NowNodeXJZWnum)
							&& OneNodeSCZWWnum.equals(NowNodeSCZWWnum)
							&& OneNodeHTFJnum.equals(NowNodeHTFJWnum)) {
						strFlag = "yes";
					} else {
						LogUtil.doWriteLog("==签订第一节点版本号==" + OneNodeXJZWnum
								+ "--" + OneNodeSCZWWnum + "--" + OneNodeHTFJnum);
						LogUtil.doWriteLog("==签订当前节点版本号==" + NowNodeXJZWnum
								+ "--" + NowNodeSCZWWnum + "--"
								+ NowNodeHTFJWnum);
						strFlag = "no";
					}

				}
			}
			// 合同变更流程
			else if (strContractChangeWfid.equals(workflowid)) {
				String SelectEditionNum = "select * from " + strTablename
						+ " where requestid=" + requestid;
				RsSelect.execute(SelectEditionNum);
				if (RsSelect.first()) {
					// 第一节点 新建正文 版本号
					String OneNodeXJZWnum = Util.null2o(RsSelect
							.getString("zwbb"));
					// 第一节点 上传正文 版本号
					String OneNodeSCZWWnum = Util.null2o(RsSelect
							.getString("fjbb"));
					// 第一节点 合同附件 版本号
					String OneNodeHTFJnum = Util.null2o(RsSelect
							.getString("htfjbb"));
					// 当前 新建正文 版本号
					String NowNodeXJZWnum = Util
							.null2o(getMaxNumByDocids(RsSelect
									.getString("htzwfj")));
					// 当前 上传正文 版本号
					String NowNodeSCZWWnum = Util
							.null2o(getMaxNumByDocids(RsSelect
									.getString("htzwwd")));
					// 当前 合同附件 版本号
					String NowNodeHTFJWnum = Util
							.null2o(getMaxNumByDocids(RsSelect
									.getString("htfj")));
					// 验证版本号
					if (OneNodeXJZWnum.equals(NowNodeXJZWnum)
							&& OneNodeSCZWWnum.equals(NowNodeSCZWWnum)
							&& OneNodeHTFJnum.equals(NowNodeHTFJWnum)) {
						strFlag = "yes";
					} else {
						LogUtil.doWriteLog("==变更第一节点版本号==" + OneNodeXJZWnum
								+ "--" + OneNodeXJZWnum + "--" + OneNodeHTFJnum);
						LogUtil.doWriteLog("==变更当前节点版本号==" + NowNodeXJZWnum
								+ "--" + NowNodeSCZWWnum + "--"
								+ NowNodeHTFJWnum);
						strFlag = "no";
					}
				}
			}
			if ("no".equals(strFlag)) {
				request.getRequestManager().setMessageid("12500");
				request.getRequestManager().setMessagecontent("文档内容已经修改，请退回。");
			}

		}
		return SUCCESS;

	}

	/**
	 * 根据docids获取多个附件的版本号
	 * @param docids
	 * @return
	 */
	public static String getMaxNumByDocids(String docids) {
		RecordSet rs = new RecordSet();
		// 该文档的最大版本号
		String strMaxNum = "";
		String[] strarray=docids.split(","); 
		for (int i = 0; i < strarray.length; i++){
			// 查询该文档最大版本号SQL
			String MaxNumSql = "select max(versionid) DocMaxEditionNum from DocImageFile where docid = '"
					+ strarray[i] + "'";
			LogUtil.doWriteLog("===查询该文档的最大版本号==="+MaxNumSql);
			rs.execute(MaxNumSql);
			if (rs.first()) {
				strMaxNum += Util.null2String(rs.getString("DocMaxEditionNum"))+",";
			}
		}
		return strMaxNum;
	}
}
