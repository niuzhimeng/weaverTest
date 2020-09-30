package com.weavernorth.voucher.web;

import com.weaver.general.BaseBean;
import com.weavernorth.voucher.service.ImportVoucherService;
import com.weavernorth.voucher.service.impl.ImportVoucherServiceImpl;
import com.weavernorth.voucher.util.VouchLogUtil;

import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

/**
 * @description: EAS集成 费用报销推送凭证信息
 * @author: DJC
 * @date: 2019.11.18
 * @version: 1.0
 */
public class VoucherPushAction extends BaseBean implements Action {

	@Override
	public String execute(RequestInfo requestinfo) {
		writeLog("VoucherPushAction Start");
		String requestId = requestinfo.getRequestid();
		int userId = requestinfo.getRequestManager().getUserId();
		String workflowId = requestinfo.getWorkflowid();
		String tableName = requestinfo.getRequestManager().getBillTableName();
		
		writeLog("---------workflowId-------"+workflowId);
		writeLog("---------tableName-------"+tableName);
		
		VouchLogUtil util = new VouchLogUtil();
		boolean ispush = util.isPushVoucher(requestId, tableName);
		boolean isAccount = util.isAccountExist(requestId, tableName, workflowId);
		writeLog("---------是否推送-------"+ispush);
		writeLog("---------是否存在公司-------"+isAccount);
		if(ispush && isAccount) {
			ImportVoucherService service = new ImportVoucherServiceImpl();
			String flag = service.importVoucher(requestId, workflowId, userId ,tableName);
			
			if(flag == "0000" || "0000".equalsIgnoreCase(flag)) {
				writeLog("VoucherPushAction Start");
				return Action.SUCCESS;
			}else {
				writeLog("VoucherPushAction fail:"+flag);
				return Action.FAILURE_AND_CONTINUE;
			}
		}else {
			return Action.SUCCESS;
		}
	}
}
