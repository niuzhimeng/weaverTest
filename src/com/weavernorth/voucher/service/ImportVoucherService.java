package com.weavernorth.voucher.service;
/**
 * @description: EAS集成    推送凭证信息
 * @author: DJC
 * @date: 2019.11.19
 * @version: 1.0
 */
public interface ImportVoucherService {
	
	/**
	 * @description: 推送凭证信息
	 * @param: userID		当前操作人员id
	 * 		   requestId	流程id
	 *         workflowId	流程workflowId
	 *         tableName	流程表单数据库表名
	 */
	public String importVoucher(String requestid, String workflowid, int userid, String tableName);

}
