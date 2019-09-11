package com.weavernorth.contract;

import com.weavernorth.util.LogUtil;
import org.json.JSONException;
import org.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 2018-03-23
 *
 * @author wgh
 *
 */
public class WfReimContractNum {

	public static void main(String[] args) throws JSONException {
		JSONObject json = new JSONObject();
		json.put("a","1");
		json.put("b","2");

		json.getString("a");
		System.out.println(json.getString("a"));
	}
	/**
	 * 付款流程、退款流程根据合同编号查询，该合同编号版本之前的所有编号
	 *
	 * @param requestid
	 * @return
	 */
	public static JSONObject getContNum(String requestid) {
		JSONObject json = new JSONObject();
		RecordSet rs = new RecordSet();
		// 该合同号之前的版本号
		String strBeforeContractAllNum = "";
		// 该合同号之前的版本号合同编号
		String strBeforeContractAllName = "";
		// 选中的合同编号
		String strContractNum = getContractNumByReqid(requestid);
		// 当合同号的最后一位为0位时，该合同号为签订合同流程合同号，没有之前的版本
		if ("0".equals(strContractNum.substring(strContractNum.length() - 1))) {
			strBeforeContractAllNum = "0";
			strBeforeContractAllName = "0";
			try {
				json.put("strBeforeContractAllNum", requestid);
				json.put("strBeforeContractAllName", strContractNum);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				LogUtil.debugLog("----查询合同号及名称时异常1---" + e);
			}

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
			LogUtil.doWriteLog("======查询名称sql  1===" + BeforeContractNumSql);
			rs.execute(BeforeContractNumSql);
			while (rs.next()) {
				// 拼接合同号
				strBeforeContractAllNum += rs.getString("ylc") + ",";
				// 拼接合同名称
				strBeforeContractAllName += getContractNumByReqid(rs
						.getString("ylc")) + ",";
				LogUtil.doWriteLog(strBeforeContractAllNum + "==拼接合同id及名称=="
						+ strBeforeContractAllName);
			}
			// 去掉最后一个逗号
			strBeforeContractAllNum = strBeforeContractAllNum.substring(0,
					strBeforeContractAllNum.length() - 1);
			strBeforeContractAllName = strBeforeContractAllName.substring(0,
					strBeforeContractAllName.length() - 1);
			try {
				json.put("strBeforeContractAllNum", strBeforeContractAllNum);
				json.put("strBeforeContractAllName", strBeforeContractAllName);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				LogUtil.debugLog("----查询合同号及名称时异常2---" + e);
			}
		}
		return json;
	}

	/**
	 * 根据requestid，合同号查询OA号、发票金额
	 * @param requestid
	 * @return
	 */
	public static List<Map<String, String>> getContractNum(String requestid) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		RecordSet rs = new RecordSet();
		// 选中的合同编号
		String strContractNum = getContractNumByReqid(requestid);

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
				+ strContractNum + "')))) or htbh='" + strContractNum + "'";
		LogUtil.doWriteLog("==查询总体该合同号之前的版本==" + BeforeContractNumSql);
		rs.execute(BeforeContractNumSql);
		while (rs.next()) {
			RecordSet rsmap = new RecordSet();
			//张振过滤开始
            RecordSet rs_filter = new RecordSet();
            boolean flag = false;
			//张振过滤结束
			// 查询OA号，发票金额
			String strSql = "select requestid,spdbh,fkje,fkrqq from formtable_main_17 where htbh="
					+ rs.getString("ylc");
			LogUtil.doWriteLog("==查询付款表单==" + strSql);
			rsmap.execute(strSql);
			while (rsmap.next()) {
                //张振过滤开始
//                String filter = "WITH CTE AS (SELECT nodeid,destnodeid FROM workflow_nodelink WHERE destnodeid = (select currentnodeid from workflow_requestbase where requestid = '"+rsmap.getString("requestid")+"') UNION ALL SELECT w.nodeid,w.destnodeid FROM CTE INNER JOIN workflow_nodelink w ON w.destnodeid=CTE.nodeid) SELECT distinct nodeid FROM CTE where nodeid in (1151,1152,1226)";
//                LogUtil.doWriteLog("==过滤sql==" + filter);
//                rs_filter.execute(filter);
//                if(!rs_filter.next()){
//                    LogUtil.doWriteLog("==过滤掉==");
//                    continue;
//                }
//                LogUtil.doWriteLog("==没过滤掉==");
                flag = false;
                String filter = "select nodeid,logtype,destnodeid from workflow_requestlog where requestid = '"+rsmap.getString("requestid")+"' order by LOGID";
                LogUtil.doWriteLog("==过滤sql==" + filter);
                rs_filter.execute(filter);
                while(rs_filter.next()){
                    //如果退回到三个会计节点之前就置为false
                    if("887".equals(rs_filter.getString("nodeid")) || "1147".equals(rs_filter.getString("nodeid")) || "1148".equals(rs_filter.getString("nodeid")) || "1149".equals(rs_filter.getString("nodeid")) || "1150".equals(rs_filter.getString("nodeid"))){
                        flag = false;
                    }
                    //如果到了三个节点
                    if("1151".equals(rs_filter.getString("nodeid")) || "1152".equals(rs_filter.getString("nodeid")) || "1226".equals(rs_filter.getString("nodeid"))){
                        //批准置为true
                        if("0".equals(rs_filter.getString("logtype"))){
                            flag = true;
                        }
                    }
                    //考虑之后又退回情况
                    if("3".equals(rs_filter.getString("logtype"))){
                        if("887".equals(rs_filter.getString("destnodeid")) || "1147".equals(rs_filter.getString("destnodeid")) || "1148".equals(rs_filter.getString("destnodeid")) || "1149".equals(rs_filter.getString("destnodeid")) || "1150".equals(rs_filter.getString("destnodeid"))){
                            flag = false;
                        }
                    }
                }
                if(!flag){
                    LogUtil.doWriteLog("==过滤掉==");
                    continue;
                }
                LogUtil.doWriteLog("==没过滤掉==");
                //张振过滤结束
				Map<String, String> map = new HashMap<String, String>();
				String strOANum = rsmap.getString("spdbh");
				String strInvoiceMoney = Util.null2String(rsmap.getString("fkje"));
				if("".equals(strInvoiceMoney)){
					continue;
				}
				String strPayDate = rsmap.getString("fkrqq");
				LogUtil.doWriteLog("==class--strOANum==" + strOANum);
				LogUtil.doWriteLog("==class--strInvoiceMoney=="
						+ strInvoiceMoney);
				LogUtil.doWriteLog("==class--strPayDate==" + strPayDate);

				map.put("strOANum", strOANum);
				map.put("strInvoiceMoney", strInvoiceMoney);
				map.put("strPayDate", strPayDate);
				list.add(map);
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
}
