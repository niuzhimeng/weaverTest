package com.weavernorth.workflow;

import com.weavernorth.OA2archives.util.WorkflowUtil;
import com.weavernorth.util.LogUtil;
import com.weavernorth.util.ToolUtil;
import hpd_incidentinterface_create_ws.*;
import org.json.JSONException;
import org.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.formmode.interfaces.action.BaseAction;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.hrm.resource.ResourceComInfo;
import weaver.soa.workflow.request.RequestInfo;

import javax.xml.datatype.XMLGregorianCalendar;

public class ItToRemedy extends BaseAction {
	private static BaseBean bb = new BaseBean();
	// =====1.1.信息系统设备计划维护工单申请流程========================
	private static String strEquipmentMaintenanceWfid = bb.getPropValue(
			"wnrequestinfo", "strEquipmentMaintenanceWfid");
		// 维护——一层分类下拉框字段id
	private static String wh_FirstTye = bb.getPropValue(
			"wnrequestinfo", "wh_FirstTye");
		// 维护——二层分类下拉框字段id
	private static String wh_SecondType = bb.getPropValue(
			"wnrequestinfo", "wh_SecondType");
		// 维护——三层分类下拉框字段id--------无
	/*private static String wh_ThirdType = bb.getPropValue(
			"wnrequestinfo", "wh_ThirdType");*/
	// =====1.2.租赁设备转移申请表申请流程========================
	private static String strEquipmentTransferWfid = bb.getPropValue(
			"wnrequestinfo", "strEquipmentTransferWfid");
		// 租赁——一层分类下拉框字段id
	private static String zl_FirstTye = bb.getPropValue(
			"wnrequestinfo", "zl_FirstTye");
		// 租赁——二层分类下拉框字段id
	private static String zl_SecondType = bb.getPropValue(
			"wnrequestinfo", "zl_SecondType");
		// 租赁——三层分类下拉框字段id
	private static String zl_ThirdType = bb.getPropValue(
			"wnrequestinfo", "zl_ThirdType");
	// =====-1.3.设备借用申请单申请流程========================
	private static String strBorrowEquipmentWfid = bb.getPropValue(
			"wnrequestinfo", "strBorrowEquipmentWfid");
		// 借用——一层分类下拉框字段id
	private static String jy_FirstTye = bb.getPropValue(
			"wnrequestinfo", "jy_FirstTye");
		// 借用——二层分类下拉框字段id
	private static String jy_SecondType = bb.getPropValue(
			"wnrequestinfo", "jy_SecondType");
	// =====-1.4.应用系统用户权限申请流程========================
	private static String strUserRightsWfid = bb.getPropValue("wnrequestinfo",
			"strUserRightsWfid");
		// 权限——一层分类下拉框字段id
	private static String qx_FirstTye = bb.getPropValue(
			"wnrequestinfo", "qx_FirstTye");
		// 权限——三层分类下拉框字段id
	private static String qx_ThirdType = bb.getPropValue(
			"wnrequestinfo", "qx_ThirdType");
	// =====-1.5.应用系统配置变更申请流程========================
	private static String strConfigurationChangeWfid = bb.getPropValue(
			"wnrequestinfo", "strConfigurationChangeWfid");
		// 变更——一层分类下拉框字段id1
	private static String bg_FirstTye = bb.getPropValue(
			"wnrequestinfo", "bg_FirstTye");
		// 变更——二层分类下拉框字段id
	private static String bg_SecondType = bb.getPropValue(
			"wnrequestinfo", "bg_SecondType");
		// 变更——三层分类下拉框字段id
	private static String bg_ThirdType = bb.getPropValue(
			"wnrequestinfo", "bg_ThirdType");
	// 1.6.信息需求申请流程========================
	private static String strRequirementApplicationWfid = bb.getPropValue(
			"wnrequestinfo", "strRequirementApplicationWfid");
			// 需求-应用——一层分类下拉框字段id
		private static String xq_yy_FirstTye = bb.getPropValue(
				"wnrequestinfo", "xq_FirstTye");
			// 需求-应用——二层分类下拉框字段id
		private static String xq_yy_SecondType = bb.getPropValue(
				"wnrequestinfo", "xq_SecondType");
			// 需求-应用——三层分类下拉框字段id
		private static String xq_yy_ThirdType = bb.getPropValue(
				"wnrequestinfo", "xq_ThirdType");
			// 需求-设施——一层分类下拉框字段id
		private static String xq_ss_FirstTye = bb.getPropValue(
				"wnrequestinfo", "xq_FirstTye");
			// 需求-设施——二层分类下拉框字段id
		private static String xq_ss_SecondType = bb.getPropValue(
				"wnrequestinfo", "xq_SecondType");
			// 需求-设施——三层分类下拉框字段id
		private static String xq_ss_ThirdType = bb.getPropValue(
				"wnrequestinfo", "xq_ThirdType");
			// 需求-信息——一层分类下拉框字段id
		private static String xq_xx_FirstTye = bb.getPropValue(
				"wnrequestinfo", "xq_FirstTye");
			// 需求-信息——二层分类下拉框字段id
		private static String xq_xx_SecondType = bb.getPropValue(
				"wnrequestinfo", "xq_SecondType");
			// 需求-信息——三层分类下拉框字段id
		private static String xq_xx_ThirdType = bb.getPropValue(
				"wnrequestinfo", "xq_ThirdType");
	/**
	 * it流程节点后附加操作
	 */
	public String execute(RequestInfo request) {
		LogUtil.doWriteLog("====节点后附加操作，推送数据到remedy系统====");

		RecordSet RsSelectTabel = new RecordSet();
		RecordSet rs = new RecordSet();
		RecordSet rsdt = new RecordSet();
		// 简描述
		String strDescription = "";
		// 详细描述
		String strDetailed_Decription = "";
		// 申请人id
		String strHrmid = "";
		// 服务请求分层一
		String strCategorization_Tier_1 = "";
		// 服务请求分层二
		String strCategorization_Tier_2 = "";
		// 服务请求分层三
		String strCategorization_Tier_3 = "";
		// =====-remedy系统所需字段=====end
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
		// 1.1.信息系统设备计划维护工单申请流程
		if (strEquipmentMaintenanceWfid.contains(workflowid)) {
			// 查询表单信息
			String selectSql = "select * from " + strTablename
					+ " where requestid=" + requestid;
			rs.execute(selectSql);
			if (rs.first()) {
				// 简描述
				strDescription = "信息系统设备计划维护工单申请";
				// 详细描述
				strDetailed_Decription = "故障/需求原因:"+Util.null2String(rs.getString("gzxqyy"))+"#计划维护内容"+Util.null2String(rs.getString("jhwhnr"));
				// 人员id
				strHrmid = Util.null2String(rs.getString("lxr"));
				// 服务请求分层一
				strCategorization_Tier_1 = Util
						.null2String(ToolUtil.getselectvalue(wh_FirstTye, rs.getString("ycjg")));
				// 服务请求分层二
				strCategorization_Tier_2 = Util
						.null2String(ToolUtil.getselectvalue(wh_SecondType, rs.getString("ecfl1")));
				// 服务请求分层三
				strCategorization_Tier_3 = "";
			}
			String Result = OA2RemedyData(requestid,strCategorization_Tier_1, strCategorization_Tier_2, strCategorization_Tier_3, strHrmid, strDescription, strDetailed_Decription);
			LogUtil.doWriteLog("==调用推送方法返回值=="+Result);
		}
		// 1.2.租赁设备转移申请表申请流程
		else if (strEquipmentTransferWfid.contains(workflowid)) {
			// 查询表单信息
			String selectSql = "select * from " + strTablename
					+ " where requestid=" + requestid;
			LogUtil.doWriteLog("==租赁设备查询sql=="+selectSql);
			rs.execute(selectSql);
			if (rs.first()) {
				// 简描述
				strDescription = "租赁设备转移申请表申请";
				// 详细描述
				strDetailed_Decription = Util.null2String(rs.getString("xxms"));
				// 人员id
				strHrmid = Util.null2String(rs.getString("sqr"));
				// 服务请求分层一
				strCategorization_Tier_1 = Util
						.null2String(ToolUtil.getselectvalue(zl_FirstTye, rs.getString("yzfl")));
				// 服务请求分层二
				strCategorization_Tier_2 = Util
						.null2String(ToolUtil.getselectvalue(zl_SecondType, rs.getString("ecfl")));
				// 服务请求分层三
				strCategorization_Tier_3 = Util
						.null2String(ToolUtil.getselectvalue(zl_ThirdType, rs.getString("scfl")));
			}
			String Result = OA2RemedyData(requestid,strCategorization_Tier_1, strCategorization_Tier_2, strCategorization_Tier_3, strHrmid, strDescription, strDetailed_Decription);
			LogUtil.doWriteLog("==调用推送方法返回值=="+Result);
		}
		// 1.3.设备借用申请单申请流程
		else if (strBorrowEquipmentWfid.contains(workflowid)) {
			// 查询表单信息
			String selectSql = "select * from " + strTablename
					+ " where requestid=" + requestid;
			LogUtil.doWriteLog("==设备借用申请查询sql=="+selectSql);
			rs.execute(selectSql);
			if (rs.first()) {
				// 人员id
				strHrmid = Util.null2String(rs.getString("sqr"));
				// 简描述
				strDescription = "设备借用申请";
				// 详细描述
				strDetailed_Decription = Util.null2String(rs.getString("xqsm"))+"#名称:"+Util.null2String(rs.getString("mc"))+"#数量："+Util.null2String(rs.getString("sl"));
				// 服务请求分层一
				strCategorization_Tier_1 = Util
						.null2String(ToolUtil.getselectvalue(jy_FirstTye, rs.getString("yzfl")));
				// 服务请求分层二
				strCategorization_Tier_2 = Util
						.null2String(ToolUtil.getselectvalue(jy_SecondType, rs.getString("ecfl")));
				// 服务请求分层三---(借用不推送第三层分类)
				strCategorization_Tier_3 = "";
			}
			String Result = OA2RemedyData(requestid,strCategorization_Tier_1, strCategorization_Tier_2, strCategorization_Tier_3, strHrmid, strDescription, strDetailed_Decription);
			LogUtil.doWriteLog("==调用推送方法返回值=="+Result);
		}
		// 1.4.应用系统用户权限申请流程
		else if (strUserRightsWfid.contains(workflowid)) {
			// 查询表单信息
			String selectSql = "select * from " + strTablename
					+ " where requestid=" + requestid;
			LogUtil.doWriteLog("==用户权限申请sql=="+selectSql);
			rs.execute(selectSql);
			if (rs.first()) {
				// 简描述
				strDescription = "应用系统用户权限申请";
				// 人员id
				strHrmid = Util.null2String(rs.getString("sqr"));
				// 服务请求分层一
				strCategorization_Tier_1 = Util
						.null2String(ToolUtil.getselectvalue(qx_FirstTye, rs.getString("yzfl")));
				// 服务请求分层三
				strCategorization_Tier_3 = Util
						.null2String(ToolUtil.getselectvalue(qx_ThirdType, rs.getString("xt")));
				//当“新增删除用户”不为空时
				if(!"".equals(Util.null2String(rs.getString("xzsc")))){
					// 详细描述
					strDetailed_Decription = "姓名:"+Util.null2String(rs.getString("xm1"))+"#账号:"+Util.null2String(rs.getString("zh1"))+"#岗位:"+Util.null2String(rs.getString("gw1"))+"#是否培训:"+Util.null2String(rs.getString("sfpx1"))+"#相同岗位用户数:"+Util.null2String(rs.getString("xtgw1"))+"#使用频数:"+Util.null2String(rs.getString("stps1"));
					// 服务请求分层二
					strCategorization_Tier_2 = "新增删除用户 Add/Delete User";
					//新增删除用户
					String Result = OA2RemedyData(requestid,strCategorization_Tier_1, strCategorization_Tier_2, strCategorization_Tier_3, strHrmid, strDescription, strDetailed_Decription);
					LogUtil.doWriteLog("==新增删除用户 -调用推送方法返回值=="+Result);
				}
				//当“用户权限/岗位分配”不为空时
				if(!"".equals(Util.null2String(rs.getString("yhh")))){
					// 详细描述
					strDetailed_Decription = "姓名:"+Util.null2String(rs.getString("xm2"))+"#账号:"+Util.null2String(rs.getString("zh"))+"#角色:"+Util.null2String(rs.getString("js"))+"#备注:"+Util.null2String(rs.getString("bz"));
					// 服务请求分层二
					strCategorization_Tier_2 ="用户权限/岗位分配 User Authority Assignment";
					//用户权限/岗位分配
					String Result = OA2RemedyData(requestid,strCategorization_Tier_1, strCategorization_Tier_2, strCategorization_Tier_3, strHrmid, strDescription, strDetailed_Decription);
					LogUtil.doWriteLog("==用户权限/岗位分配 -调用推送方法返回值=="+Result);
				}
				//当“岗位权限修改”不为空时
				if(!"".equals(Util.null2String(rs.getString("gwq")))){
					// 详细描述
					strDetailed_Decription = "岗位名称:"+Util.null2String(rs.getString("gw11"))+"#岗位描述:"+Util.null2String(rs.getString("gw"))+"#新增、修改或删除角色:"+Util.null2String(rs.getString("xzxg"))+"#是否涉及操作类角色:"+ToolUtil.getselectvalue("9392",rs.getString("sf"));
					// 服务请求分层二
					strCategorization_Tier_2 = "岗位权限修改 Modify Role of Position";
					// 服务请求分层三
					strCategorization_Tier_3 = "ERP";
					//岗位权限修改
					String Result = OA2RemedyData(requestid,strCategorization_Tier_1, strCategorization_Tier_2, strCategorization_Tier_3, strHrmid, strDescription, strDetailed_Decription);
					LogUtil.doWriteLog("==岗位权限修改 -调用推送方法返回值=="+Result);				
				}
				//当“系统角色修改”不为空时
				if(!"".equals(Util.null2String(rs.getString("xtjs")))){
					// 详细描述
					strDetailed_Decription = "角色名称:"+Util.null2String(rs.getString("js1"))+"#角色描述:"+Util.null2String(rs.getString("jsms"))+"#新增、修改或删除角色:"+Util.null2String(rs.getString("xz"));
					// 服务请求分层二
					strCategorization_Tier_2 = "系统角色修改 Modify Role of System";
					//系统角色修改调用接口
					String Result = OA2RemedyData(requestid,strCategorization_Tier_1, strCategorization_Tier_2, strCategorization_Tier_3, strHrmid, strDescription, strDetailed_Decription);
					LogUtil.doWriteLog("==系统角色修改 -调用推送方法返回值=="+Result);
				}
			}
		}
		// 1.5.应用系统配置变更申请流程
		else if (strConfigurationChangeWfid.contains(workflowid)) {
			// 查询表单信息
			String selectSql = "select * from " + strTablename
					+ " where requestid=" + requestid;
			LogUtil.doWriteLog("==应用系统配置变更申请查询sql=="+selectSql);
			rs.execute(selectSql);
			if (rs.first()) {
				// 人员id
				strHrmid = Util.null2String(rs.getString("sqr"));
				// 简描述
				strDescription = "应用系统配置变更申请";
				// 详细描述
				strDetailed_Decription = Util.null2String(rs.getString("bg"));
				// 服务请求分层一
				strCategorization_Tier_1 = Util
						.null2String(ToolUtil.getselectvalue(bg_FirstTye, rs.getString("yzfl")));
				// 服务请求分层二
				strCategorization_Tier_2 = Util
						.null2String(ToolUtil.getselectvalue(bg_SecondType, rs.getString("sblb")));
				// 服务请求分层三
				strCategorization_Tier_3 = Util
						.null2String(ToolUtil.getselectvalue(bg_ThirdType, rs.getString("xt")));
			}
			String Result = OA2RemedyData(requestid,strCategorization_Tier_1, strCategorization_Tier_2, strCategorization_Tier_3, strHrmid, strDescription, strDetailed_Decription);
			LogUtil.doWriteLog("==调用推送方法返回值=="+Result);
		}
		// 1.6.信息需求申请流程
		else if ("152".contains(workflowid)) {
			// 查询表单信息
			String selectSql = "select * from " + strTablename
					+ " where requestid=" + requestid;
			LogUtil.doWriteLog("==信息需求申请流程查询sql=="+selectSql);
			rs.execute(selectSql);
			if (rs.first()) {
				// 人员id
				strHrmid = Util.null2String(rs.getString("sql"));
				// 简描述
				strDescription = "信息需求申请";
				// 详细描述
				strDetailed_Decription = Util.null2String(rs.getString("xxxq1"));
				//----选择了应用系统
				if("0".equals(Util.null2String(rs.getString("xqfl")))){
					// 服务请求分层一
					strCategorization_Tier_1 = Util
							.null2String(ToolUtil.getselectvalue(xq_yy_FirstTye, rs.getString("xqlx")));
					// 服务请求分层二
					strCategorization_Tier_2 = Util
							.null2String(ToolUtil.getselectvalue(xq_yy_SecondType, rs.getString("xtmc")));
					// 服务请求分层三
					strCategorization_Tier_3 = Util
							.null2String(ToolUtil.getselectvalue(xq_yy_ThirdType, rs.getString("mk")));
					
					String Result = OA2RemedyData(requestid,strCategorization_Tier_1, strCategorization_Tier_2, strCategorization_Tier_3, strHrmid, strDescription, strDetailed_Decription);
					LogUtil.doWriteLog("==调用推送方法返回值=="+Result);
				}
				//----选择了基础设施
				if("1".equals(Util.null2String(rs.getString("xqfl")))){
					// 服务请求分层一
					strCategorization_Tier_1 = Util
							.null2String(ToolUtil.getselectvalue(xq_ss_FirstTye, rs.getString("xqfl")));
					
					//邮箱
					if("1".equals(Util.null2String( rs.getString("yx1")))){
						// 服务请求分层二
						strCategorization_Tier_2 = Util.null2String( getLabelName("7417"));
						String Result = OA2RemedyData(requestid,strCategorization_Tier_1, strCategorization_Tier_2, strCategorization_Tier_3, strHrmid, strDescription, strDetailed_Decription);
						LogUtil.doWriteLog("==调用推送方法返回值=="+Result);
					}
					//互联网
					if("1".equals(Util.null2String( rs.getString("hlw")))){
						// 服务请求分层二
						strCategorization_Tier_2 = Util.null2String( getLabelName("7418"));
						String Result = OA2RemedyData(requestid,strCategorization_Tier_1, strCategorization_Tier_2, strCategorization_Tier_3, strHrmid, strDescription, strDetailed_Decription);
						LogUtil.doWriteLog("==调用推送方法返回值=="+Result);
					}
					//密码重置
					if("1".equals(Util.null2String( rs.getString("mmz")))){
						// 服务请求分层二
						strCategorization_Tier_2 = Util.null2String( getLabelName("7419"));
						String Result = OA2RemedyData(requestid,strCategorization_Tier_1, strCategorization_Tier_2, strCategorization_Tier_3, strHrmid, strDescription, strDetailed_Decription);
						LogUtil.doWriteLog("==调用推送方法返回值=="+Result);
					}
					//企业云盘
					if("1".equals(Util.null2String( rs.getString("qyyp")))){
						// 服务请求分层二
						strCategorization_Tier_2 = Util.null2String( getLabelName("7420"));
						String Result = OA2RemedyData(requestid,strCategorization_Tier_1, strCategorization_Tier_2, strCategorization_Tier_3, strHrmid, strDescription, strDetailed_Decription);
						LogUtil.doWriteLog("==调用推送方法返回值=="+Result);
					}
					//vpn
					if("1".equals(Util.null2String( rs.getString("vpn")))){
						// 服务请求分层二
						strCategorization_Tier_2 = Util.null2String( getLabelName("7421"));
						String Result = OA2RemedyData(requestid,strCategorization_Tier_1, strCategorization_Tier_2, strCategorization_Tier_3, strHrmid, strDescription, strDetailed_Decription);
						LogUtil.doWriteLog("==调用推送方法返回值=="+Result);
					}
					//移动存储
					if("1".equals(Util.null2String( rs.getString("ydcc")))){
						// 服务请求分层二
						strCategorization_Tier_2 = Util.null2String( getLabelName("7422"));
						String Result = OA2RemedyData(requestid,strCategorization_Tier_1, strCategorization_Tier_2, strCategorization_Tier_3, strHrmid, strDescription, strDetailed_Decription);
						LogUtil.doWriteLog("==调用推送方法返回值=="+Result);
					}
					//软件安装
					if("1".equals(Util.null2String( rs.getString("rjaz")))){
						// 服务请求分层二
						strCategorization_Tier_2 = Util.null2String( getLabelName("7784"));
						String Result = OA2RemedyData(requestid,strCategorization_Tier_1, strCategorization_Tier_2, strCategorization_Tier_3, strHrmid, strDescription, strDetailed_Decription);
						LogUtil.doWriteLog("==调用推送方法返回值=="+Result);
					}
					//局域网接入
					if("1".equals(Util.null2String( rs.getString("jywjs")))){
						// 服务请求分层二
						strCategorization_Tier_2 = Util.null2String( getLabelName("7423"));
						String Result = OA2RemedyData(requestid,strCategorization_Tier_1, strCategorization_Tier_2, strCategorization_Tier_3, strHrmid, strDescription, strDetailed_Decription);
						LogUtil.doWriteLog("==调用推送方法返回值=="+Result);
					}
					
					
				}
				//----选择了信息资产
				if("2".equals(Util.null2String(rs.getString("xqfl")))){
					// 服务请求分层一
					strCategorization_Tier_1 = Util
							.null2String(ToolUtil.getselectvalue(xq_xx_FirstTye, rs.getString("xqfl")));
					// 服务请求分层二
					strCategorization_Tier_2 = Util
							.null2String(ToolUtil.getselectvalue(xq_xx_SecondType, rs.getString("vpn")));
					// 服务请求分层三
					strCategorization_Tier_3 = Util
							.null2String(ToolUtil.getselectvalue(xq_xx_ThirdType, rs.getString("sbfl")));
					String Result = OA2RemedyData(requestid,strCategorization_Tier_1, strCategorization_Tier_2, strCategorization_Tier_3, strHrmid, strDescription, strDetailed_Decription);
					LogUtil.doWriteLog("==调用推送方法返回值=="+Result);
					
				}
				
			}
		} 
		/*it需求申请，1条流程拆分3条*/
		else if("156".equals(workflowid)){
			// 查询表单信息
			String selectSql = "select * from " + strTablename
					+ " where requestid=" + requestid;
			LogUtil.doWriteLog("==信息需求申请流程-基础设施查询sql=="+selectSql);
			rs.execute(selectSql);
			if (rs.first()) {
				// 人员id
				strHrmid = Util.null2String(rs.getString("sql"));
				// 简描述
				strDescription = "信息需求申请";
				// 详细描述
				strDetailed_Decription =  "用户账号:"+Util.null2String(rs.getString("zhyh"))+"#详细需求描述:"+Util.null2String(rs.getString("xxxq1"));
				// 服务请求分层一
				strCategorization_Tier_1 = "服务请求-基础设施 (Infrastructure Request)";
				//邮箱
				if("1".equals(Util.null2String( rs.getString("yx1")))){
					// 服务请求分层二
					//strCategorization_Tier_2 = Util.null2String( getLabelName("7417"));
					strCategorization_Tier_2 = "邮箱 Email";
					String Result = OA2RemedyData(requestid,strCategorization_Tier_1, strCategorization_Tier_2, strCategorization_Tier_3, strHrmid, strDescription, strDetailed_Decription);
					LogUtil.doWriteLog("==调用推送方法返回值=="+Result);
				}
				//互联网
				if("1".equals(Util.null2String( rs.getString("hlw")))){
					// 服务请求分层二
					//strCategorization_Tier_2 = Util.null2String( getLabelName("7418"));
					strCategorization_Tier_2 = "互联网 Internet";
					String Result = OA2RemedyData(requestid,strCategorization_Tier_1, strCategorization_Tier_2, strCategorization_Tier_3, strHrmid, strDescription, strDetailed_Decription);
					LogUtil.doWriteLog("==调用推送方法返回值=="+Result);
				}
				//密码重置
				if("1".equals(Util.null2String( rs.getString("mmz")))){
					// 服务请求分层二
					//strCategorization_Tier_2 = Util.null2String( getLabelName("7419"));
					strCategorization_Tier_2 = "密码重置 Password Reset";
					String Result = OA2RemedyData(requestid,strCategorization_Tier_1, strCategorization_Tier_2, strCategorization_Tier_3, strHrmid, strDescription, strDetailed_Decription);
					LogUtil.doWriteLog("==调用推送方法返回值=="+Result);
				}
				//企业云盘
				if("1".equals(Util.null2String( rs.getString("qyyp")))){
					// 服务请求分层二
					//strCategorization_Tier_2 = Util.null2String( getLabelName("7420"));
					strCategorization_Tier_2 = "企业云盘 Enterprise cloud disk";
					String Result = OA2RemedyData(requestid,strCategorization_Tier_1, strCategorization_Tier_2, strCategorization_Tier_3, strHrmid, strDescription, strDetailed_Decription);
					LogUtil.doWriteLog("==调用推送方法返回值=="+Result);
				}
				//vpn
				if("1".equals(Util.null2String( rs.getString("vpn")))){
					// 服务请求分层二
					//strCategorization_Tier_2 = Util.null2String( getLabelName("7421"));
					strCategorization_Tier_2 = "VPN";
					String Result = OA2RemedyData(requestid,strCategorization_Tier_1, strCategorization_Tier_2, strCategorization_Tier_3, strHrmid, strDescription, strDetailed_Decription);
					LogUtil.doWriteLog("==调用推送方法返回值=="+Result);
				}
				//移动存储
				if("1".equals(Util.null2String( rs.getString("ydcc")))){
					// 服务请求分层二
					//strCategorization_Tier_2 = Util.null2String( getLabelName("7422"));
					strCategorization_Tier_2 = "移动存储介质接入 Mobile Storage Media Access";
					String Result = OA2RemedyData(requestid,strCategorization_Tier_1, strCategorization_Tier_2, strCategorization_Tier_3, strHrmid, strDescription, strDetailed_Decription);
					LogUtil.doWriteLog("==调用推送方法返回值=="+Result);
				}
				//软件安装
				if("1".equals(Util.null2String( rs.getString("rjaz")))){
					// 服务请求分层二
					//strCategorization_Tier_2 = Util.null2String( getLabelName("7784"));
					strCategorization_Tier_2 = "软件安装 Software Installation";
					String Result = OA2RemedyData(requestid,strCategorization_Tier_1, strCategorization_Tier_2, strCategorization_Tier_3, strHrmid, strDescription, strDetailed_Decription);
					LogUtil.doWriteLog("==调用推送方法返回值=="+Result);
				}
				//局域网接入
				if("1".equals(Util.null2String( rs.getString("jywjs")))){
					// 服务请求分层二
					//strCategorization_Tier_2 = Util.null2String( getLabelName("7423"));
					strCategorization_Tier_2 = "局域网接入 LAN Access";
					String Result = OA2RemedyData(requestid,strCategorization_Tier_1, strCategorization_Tier_2, strCategorization_Tier_3, strHrmid, strDescription, strDetailed_Decription);
					LogUtil.doWriteLog("==调用推送方法返回值=="+Result);
				}
			}
		}
		else if("157".equals(workflowid)){
			// 查询表单信息-信息资产
			String selectSql = "select * from " + strTablename
					+ " where requestid=" + requestid;
			LogUtil.doWriteLog("==信息需求申请流程-信息资产查询sql=="+selectSql);
			rs.execute(selectSql);
			if (rs.first()) {
				// 人员id
				strHrmid = Util.null2String(rs.getString("sql"));
				// 简描述
				strDescription = "信息需求申请";
				// 详细描述
				strDetailed_Decription =  "需求描述:"+Util.null2String(rs.getString("xqms"));
				String SqlDt = "select * from "+strTablename+"_dt1 where mainid="+rs.getInt("id");
				rsdt.execute(SqlDt);
				while(rsdt.next()){
					strDetailed_Decription = strDetailed_Decription + "#使用人姓名:"+rsdt.getString("syrxm")+"#岗位:"+rsdt.getString("gw");
				}
				// 服务请求分层一
				strCategorization_Tier_1 = "服务请求-信息资产 (Asset Request)";
				// 服务请求分层二
				strCategorization_Tier_2 = Util
						.null2String(ToolUtil.getselectvalue("9745", rs.getString("sbfl")));
				// 服务请求分层三
				strCategorization_Tier_3 = Util
						.null2String(ToolUtil.getselectvalue("9746", rs.getString("sbfl1")));
				LogUtil.doWriteLog("===信息资产是否  0 是 1 否===="+rs.getString("sfw"));
				if((rs.getString("sfw")).equals("1")){
					String Result = OA2RemedyData(requestid,strCategorization_Tier_1, strCategorization_Tier_2, strCategorization_Tier_3, strHrmid, strDescription, strDetailed_Decription);
					LogUtil.doWriteLog("==调用推送方法返回值=="+Result);
				}else{
					LogUtil.doWriteLog("==零购或者收费的软件，这个事件单不传输运维平台==");
				}
				
			}
		}
		else if("136".equals(workflowid)){
			// 查询表单信息-应用系统
			String selectSql = "select * from " + strTablename
					+ " where requestid=" + requestid;
			LogUtil.doWriteLog("==信息需求申请流程-应用系统查询sql=="+selectSql);
			rs.execute(selectSql);
			if (rs.first()) {
				// 人员id
				strHrmid = Util.null2String(rs.getString("sql"));
				LogUtil.doWriteLog("=="+strHrmid);
				// 简描述
				strDescription = "信息需求申请";
				// 详细描述
				strDetailed_Decription = "姓名:"+rs.getString("xn")+"#账号:"+rs.getString("zh")+"#描述:"+rs.getString("yhsd");
				LogUtil.doWriteLog("=="+ToolUtil.getselectvalue("7915",rs.getString("xqlx")));
				//判断是用户信息修改，还是应用系统需求
				if("用户信息修改".equals(ToolUtil.getselectvalue("7915",rs.getString("xqlx")))){
					// 服务请求分层一
					strCategorization_Tier_1 = "服务请求-应用系统 (Application Request)";
					// 服务请求分层二
					strCategorization_Tier_2 = "用户信息修改 Modify User Information";
					// 服务请求分层三
					strCategorization_Tier_3 = Util
							.null2String(ToolUtil.getselectvalue("7916", rs.getString("xtmc")));
				}else{
					// 服务请求分层一
					strCategorization_Tier_1 = "服务请求-应用系统 (Application Request)";
					// 服务请求分层二
					strCategorization_Tier_2 = "需求/变更申请 Requirement/Change Request";
					// 服务请求分层三
					strCategorization_Tier_3 = Util
							.null2String(ToolUtil.getselectvalue("7916", rs.getString("xtmc")));
					strDetailed_Decription = "详细需求描述:"+rs.getString("xxxq1");
				}
				if(Util.null2String(rs.getString("sfsj")).equals("1")){
					String Result = OA2RemedyData(requestid,strCategorization_Tier_1, strCategorization_Tier_2, strCategorization_Tier_3, strHrmid, strDescription, strDetailed_Decription);
					LogUtil.doWriteLog("==调用推送方法返回值=="+Result);
				}else{
					LogUtil.doWriteLog("==涉及费用不传输运维平台==");
				}
			}
		}
		else {
			// 不在此中的流程，直接返回信息，禁止提交
			request.getRequestManager().setMessageid("12500");
			request.getRequestManager().setMessagecontent(
					"流程节点后附加操作配置错误，请联系系统管理员。");
		}
		
		return SUCCESS;
	}
	
	/**
	 * OA流程信息推送remedy系统数据
	 * @param categorizationTier1	一层分类
	 * @param categorizationTier2	二层分类
	 * @param categorizationTier3	三层分类
	 * @param resourceid			人员id
	 * @param summary				简描述
	 * @param notes					详细描述
	 * @return	strReturnMsg		返回信息
	 */
	public static String OA2RemedyData(String requestid,String categorizationTier1,
			String categorizationTier2, String categorizationTier3,
			String resourceid, String summary, String notes) {
		ResourceComInfo hrm = null;
		// 给详细描述增加 OA单号
		notes = "OA单号:"+getWFNumByReqid(requestid)+"#"+notes;
		//返回信息
		String strReturnMsg = "";
		try {
			hrm = new ResourceComInfo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LogUtil.debugLog("ResourceComInfo对象初始化异常" + e);
		}
		try {
			LogUtil.doWriteLog("==开始初始化HPDIncidentInterfaceCreateWSService==");
			HPDIncidentInterfaceCreateWSService srvc = new HPDIncidentInterfaceCreateWSService();
			LogUtil.doWriteLog("==开始初始化HPDIncidentInterfaceCreateWSPortTypePortType==");
			HPDIncidentInterfaceCreateWSPortTypePortType delegate = srvc
					.getHPDIncidentInterfaceCreateWSPortTypeSoap();
			// OA中的数据
			// String assignee = "qi.zhou 周琦";
			// String categorizationTier1 = "应用系统(Application System)";
			// String categorizationTier2 = "ERP";
			// String categorizationTier3 = null;
			// String firstName = "xy.yue";
			// String lastName = "岳兴禹";
			// String summary = "test request";
			// String notes = "test test";
			// String loginID = "xy.yue";
			String assignee = "qi.zhou 周琦";
			String firstName = hrm.getLoginID(resourceid);
			String lastName = getChineseHrmName(resourceid);
			String loginID = firstName;
			LogUtil.doWriteLog("==assignee=="+assignee);
			LogUtil.doWriteLog("==firstName=="+firstName);
			LogUtil.doWriteLog("==lastName=="+lastName);
			LogUtil.doWriteLog("==loginID=="+loginID);
			LogUtil.doWriteLog("==categorizationTier1=="+categorizationTier1);
			LogUtil.doWriteLog("==categorizationTier2=="+categorizationTier2);
			LogUtil.doWriteLog("==categorizationTier3=="+categorizationTier3);
			LogUtil.doWriteLog("==summary=="+summary);
			LogUtil.doWriteLog("==notes=="+notes);
			// 默认数据
			String assignedGroup = "服务台";
			String assignedSupportCompany = "中沙（天津）石化有限公司（SSTPC）";
			String assignedSupportOrganization = "外部运维";
			String department = null;
			String impact = "4000";
			String urgency = "4000";
			String action = "CREATE";
			ReportedSourceType reportedSource = ReportedSourceType.WALK_IN;
			String resolution = null;
			ServiceTypeType serviceType = ServiceTypeType.USER_SERVICE_REQUEST;
			StatusType status = StatusType.NEW;
			CreateRequestType createRequest = null;
			String customerCompany = null;

			String manufacturer = null, assignedGroupShiftName = null, productCategorizationTier1 = null, productCategorizationTier2 = null, productCategorizationTier3 = null, productModelVersion = null, productName = null, ciName = null, closureManufacturer = null, closureProductCategoryTier1 = null, closureProductCategoryTier2 = null, closureProductCategoryTier3 = null, closureProductModelVersion = null, closureProductName = null, lookupKeyword = null, resolutionCategoryTier1 = null, resolutionCategoryTier2 = null, resolutionCategoryTier3 = null, workInfoSummary = null, workInfoNotes = null, middleInitial = null, directContactFirstName = null, directContactLastName = null, templateID = null, serviceCI = null, serviceCIReconID = null, hpdCI = null, directContactMiddleInitial = null, hpdCIReconID = null, hpdCIFormName = null, workInfoAttachment1Name = null, corporateID = null;

			WorkInfoTypeType workInfoType = null;
			XMLGregorianCalendar workInfoDate = null;
			WorkInfoSourceType workInfoSource = null;
			CreateRequestType workInfoLocked = null;
			WorkInfoViewAccessType workInfoViewAccess = null;
			StatusReasonType statusReason = null;
			byte[] workInfoAttachment1Data = null;
			Integer workInfoAttachment1OrigSize = null;
			String response = "";
					response = delegate.helpDeskSubmitService(assignedGroup,
					assignedGroupShiftName, assignedSupportCompany,
					assignedSupportOrganization, assignee, categorizationTier1,
					categorizationTier2, categorizationTier3, ciName,
					closureManufacturer, closureProductCategoryTier1,
					closureProductCategoryTier2, closureProductCategoryTier3,
					closureProductModelVersion, closureProductName, department,
					firstName, impact, lastName, lookupKeyword, manufacturer,
					productCategorizationTier1, productCategorizationTier2,
					productCategorizationTier3, productModelVersion,
					productName, reportedSource, resolution,
					resolutionCategoryTier1, resolutionCategoryTier2,
					resolutionCategoryTier3, serviceType, status, action,
					createRequest, summary, notes, urgency, workInfoSummary,
					workInfoNotes, workInfoType, workInfoDate, workInfoSource,
					workInfoLocked, workInfoViewAccess, middleInitial,
					statusReason, directContactFirstName,
					directContactMiddleInitial, directContactLastName,
					templateID, serviceCI, serviceCIReconID, hpdCI,
					hpdCIReconID, hpdCIFormName, workInfoAttachment1Name,
					workInfoAttachment1Data, workInfoAttachment1OrigSize,
					loginID, customerCompany, corporateID);
			strReturnMsg = response;
			LogUtil.doWriteLog("helpDesk_Submit_Service方法返回==" + strReturnMsg);
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.debugLog("helpDesk_Submit_Service方法调用异常" + e);
		}

		return strReturnMsg;
	}
	
	/**
	 * 
	 * @param OANameOne		oa中it需求申请单中的一层结构名称
	 * @param OANameTwo		oa中it需求申请单中的二层结构名称
	 * @param OANameThree	oa中it需求申请单中的三层结构名称
	 * @return  obj
	 */
	public static JSONObject GetOANameByRemedyName(String OANameOne,String OANameTwo,String OANameThree){
		JSONObject obj = new JSONObject();
		RecordSet rs = new RecordSet();
		// remedy一层结构名称
		String strRemedyNameOne = "";
		// remedy二层结构名称
		String strRemedyNameTwo = "";
		// remedy三层结构名称
		String strRemedyNameThree = "";
		// 查询remedy名称根据OA名称
		String SelectRemedyNameSql = "select ycjg,ecjg,scjg from uf_xxxq where yzfl='"+OANameOne+"' and sblb='"+OANameTwo+"' and sbxq='"+OANameThree+"'";
		rs.execute(SelectRemedyNameSql);
		if(rs.first()){
			strRemedyNameOne = rs.getString("ycjg");
			strRemedyNameTwo = rs.getString("ecjg");
			strRemedyNameThree = rs.getString("scjg");
			
			try {
				obj.put("strRemedyNameOne", strRemedyNameOne);
				obj.put("strRemedyNameTwo", strRemedyNameTwo);
				obj.put("strRemedyNameThree", strRemedyNameThree);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				LogUtil.doWriteLog("==根据OA中的it需求单中的结构，查询remedy名称异常=="+e);
			}
		}
		
		
		return obj;
		
		
	}

	/**
	 * 根据字段id查询字段显示名
	 * @param Filedid
	 * @return
	 */
	public static String getLabelName(String Filedid){
		RecordSet rs = new RecordSet();
		// 字段显示名
		String strLabelName = "";
		// 字段查询sql
		String SelectLabelNameSql = "select html.labelname from HtmlLabelInfo html , workflow_billfield wfbill where wfbill.fieldlabel=html.indexid and wfbill.id="+Filedid;
		rs.execute(SelectLabelNameSql);
		if(rs.first()){
			strLabelName = rs.getString("labelname");
		}
		return strLabelName;
	}
	
	/**
	 *获取人员中文名称
	 * @param hrmid
	 * @return 人员中文名称
	 */
	public static String  getChineseHrmName(String  hrmid){
		String name ="";
		if(!"".equals(hrmid)){
			RecordSet rs = new RecordSet();
			String sql = "select dbo.convToCN(lastname) lastname from hrmresource where id="+hrmid;
			rs.executeSql(sql);
			if(rs.next()){
				name = Util.null2String(rs.getString("lastname"));
			}
		}
		return name;
	}
	/**
	 * 根据requestid查询流程编号
	 * @param requestid
	 * @return
	 */
	public static String getWFNumByReqid(String requestid){
		RecordSet rs = new RecordSet();
		// 流程编号
		String strWFNum = "";
		// sql
		String Sql = "select bianh from "+WorkflowUtil.getMainTableNameByReqId(requestid)+" where requestid='"+requestid+"'";
		rs.execute(Sql);
		if(rs.first()){
			strWFNum = Util.null2String(rs.getString("bianh"));
		}
		return strWFNum;
	}
	
	public static void main(String[] args) {
		String a = "";
		if (!a.equals("3")) {
			System.out.println("1111");
		}else{
			System.out.println("22222");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
