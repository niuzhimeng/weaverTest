package com.weavernorth.util;

import com.weaver.general.BaseBean;

import weaver.conn.RecordSet;
import weaver.general.Util;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;
/**
 * 校验附件个数
 * @author Administrator
 *
 */
public class ComCheckMainDocCountAction extends BaseBean implements Action {

	@Override
	public String execute(RequestInfo request) {
		String requestid = request.getRequestid();
		String workflowid = request.getWorkflowid();
		String tableName = request.getRequestManager().getBillTableName();
		String sqlSel = "";
		RecordSet rs = new RecordSet();
		String propWorkflowid = getPropValue("wn_fjcheck", "workflowids");
		String propfjfieldName = getPropValue("wn_fjcheck", "fjfieldNames");
		String[] workflowids = propWorkflowid.split(",");
		String[] fjfieldNames = propfjfieldName.split(",");
		for (int i = 0; i < workflowids.length; i++) {
			String pwfid = workflowids[i];
			String fjname = fjfieldNames[i];
			if(workflowid.equals(pwfid)){
				sqlSel = "select "+fjname+" from "+tableName+ " where requestid='"+requestid+"'";
				rs.execute(sqlSel);
				if(rs.next()){
					String strfj = Util.null2String(rs.getString(fjname));
					if (strfj.contains(",")) {
						request.getRequestManager().setMessageid(
								System.currentTimeMillis() + "");
						request.getRequestManager().setMessagecontent("只能上传一个附件");
						return "0";
					}
				}
			}
		}
		
		return SUCCESS;
	}

}
