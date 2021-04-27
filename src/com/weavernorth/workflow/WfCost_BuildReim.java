package com.weavernorth.workflow;

import weaver.conn.RecordSet;
import weaver.formmode.interfaces.action.BaseAction;
import weaver.general.TimeUtil;
import weaver.general.Util;
import weaver.soa.workflow.request.RequestInfo;
import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;
import com.weaver.general.BaseBean;
import com.weaver.integration.datesource.SAPInterationOutUtil;
import com.weaver.integration.log.LogInfo;
import com.weavernorth.util.LogUtil;
import com.weavernorth.workflow.payment.SAPUtil;

public class WfCost_BuildReim extends BaseAction{
	//获取配置的SAP数据源的id值
    private  String strPoolid = SAPUtil.getIdbyPoolName(new BaseBean().getPropValue("sapparam1","dataName"));
    private  LogInfo li = new LogInfo();
    private  JCO.Function function = null;
    private  JCO.Client jcoClient = null;
    private  SAPInterationOutUtil sou = new SAPInterationOutUtil();
    
    /**
	 * 流程节点后附加操作
	 */
	public String execute(RequestInfo request) {
		LogUtil.doWriteLog("======费用报销流程'匹配OA单号'节点后附加操作：付款申请查询======");
		String strMsg = BuildReim(request);
		
	    try {
			LogUtil.doWriteLog("--- 暂停时间开始 ---" + TimeUtil.getOnlyCurrentTimeString());
			Thread.sleep(3000L);
			LogUtil.doWriteLog("--- 暂停时间结束 ---" + TimeUtil.getOnlyCurrentTimeString());
		}catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    
		if(!"".equals(strMsg)){
			request.getRequestManager().setMessageid("12505");
			request.getRequestManager().setMessagecontent("SAP返回报错信息:"+strMsg);
		}
		return SUCCESS;
	}
    /**
     * 查询付款申请
     * @param request   流程信息
     * @return
     */

	public String BuildReim(RequestInfo request){
		RecordSet RsSelectTabel = new RecordSet();
		RecordSet RsTabel = new RecordSet();
		// 返回值
		String strMsg = "";
		// 会计凭证号码
		String strAccountingVoucherNum = "";
		// 财年
		String strFiscalYear = "";
		// 公司代码
		String strCompanyCoder = "";
		// 会计凭证的行项目
		String strLineProject = "";
		// OA单号
		String strOrderNum = "";
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
			strTablename = Util.null2String(RsSelectTabel.getString("tablename"));
		}
		
		//连接sap
        jcoClient = (JCO.Client) sou.getConnection(strPoolid, li);
        JCO.Repository mRepository = new JCO.Repository("Repository", jcoClient);
        // 匹配生成付款申请单的函数名
        IFunctionTemplate ft = mRepository.getFunctionTemplate("ZRFC_PZPP_OA");
        function = new JCO.Function(ft);
        //表 ZKS_PZPP_OA
        JCO.Table table_ZKS_PZPP_OA = function.getTableParameterList().getTable("T_INPUT");
         
		// 查询表单数据
		String SelectSql = "select * from "+strTablename+" where requestid="+requestid;
		LogUtil.doWriteLog("==查询表单数据=="+SelectSql);
		RsTabel.execute(SelectSql);
		if(RsTabel.first()){
			// 会计凭证号码
			strAccountingVoucherNum = RsTabel.getString("kjpzh");
			// 财年
			strFiscalYear = (RsTabel.getString("pzrq")).substring(0, 4);
			// 公司代码
			strCompanyCoder = "9009";
			// 会计凭证的行项目
			//strLineProject = ReimHrmNum(requestid);
			// OA单号
			strOrderNum = RsTabel.getString("djbh");
			LogUtil.doWriteLog("==会计凭证号码==" + strAccountingVoucherNum);
			LogUtil.doWriteLog("==财年==" + strFiscalYear);
			LogUtil.doWriteLog("==公司代码==" + strCompanyCoder);
			LogUtil.doWriteLog("==会计凭证的行项目==" + strLineProject);
			LogUtil.doWriteLog("==OA单号==" + strOrderNum);
			
			// 是否为单人报销
			String strReiSingleSql = RsTabel.getString("sqrsfwbxr");
			LogUtil.doWriteLog("==是否为本人报销（"+strReiSingleSql+"）==");
			// 申请人本人
			if("0".equals(strReiSingleSql)){
				//单人报销 
				table_ZKS_PZPP_OA.appendRow();
				table_ZKS_PZPP_OA.setValue(strAccountingVoucherNum, "BELNR");
				table_ZKS_PZPP_OA.setValue(strFiscalYear, "GJAHR");
				table_ZKS_PZPP_OA.setValue(strCompanyCoder, "BUKRS");
				table_ZKS_PZPP_OA.setValue("1", "BUZEI");
				table_ZKS_PZPP_OA.setValue(strOrderNum, "ZOAID");
			}else{
				//多人报销
				RecordSet rsdt = new RecordSet();
				int dtIndex = 1;
				String dtsql = "select fmdt.* from formtable_main_85 fm,formtable_main_85_dt4 fmdt where fm.requestid='"+requestid+"' and fm.id= fmdt.mainid and fmdt.jzm=31 ";
				rsdt.execute(dtsql);
				while(rsdt.next()){
					String strXH = rsdt.getString("xh");
					String dtId = rsdt.getString("id");
					if(!strXH.equals("")){
						
						table_ZKS_PZPP_OA.appendRow();
						table_ZKS_PZPP_OA.setValue(strAccountingVoucherNum, "BELNR");
						table_ZKS_PZPP_OA.setValue(strFiscalYear, "GJAHR");
						table_ZKS_PZPP_OA.setValue(strCompanyCoder, "BUKRS");
						table_ZKS_PZPP_OA.setValue(strXH, "BUZEI");
						table_ZKS_PZPP_OA.setValue(strOrderNum+"_"+dtIndex, "ZOAID");
						
						dtIndex++;
					}else{
						LogUtil.doWriteLog("==序号为空（"+dtId+"）==");
					}
					
				}
				
			}
			
		}//查询流程信息结束
				
        LogUtil.doWriteLog("==执行输入=前=");
        // 执行输入
        jcoClient.execute(function);
        LogUtil.doWriteLog("==执行输入=后=");
        //输出的表名
        JCO.Table table_T_RETURN = function.getTableParameterList().getTable("T_RETURN");
        for (int i = 0; i <table_T_RETURN.getNumRows(); i++) {
        	table_T_RETURN.setRow(i);
        	LogUtil.doWriteLog("==查询付款申请=="+table_T_RETURN.getString("TYPE")+"=="+table_T_RETURN.getString("ID")+"=="+table_T_RETURN.getString("NUMBER")+"=="+table_T_RETURN.getString("MESSAGE")+"==");
            if(Util.null2String(table_T_RETURN.getString("TYPE")).equals("S")){
            	UpdateMainSql("1",strTablename,requestid);
        	}else{
        		if((table_T_RETURN.getString("MESSAGE")).contains("已匹配")){
        			UpdateMainSql("1",strTablename,requestid);
        			LogUtil.doWriteLog("==已匹配信息=="+(table_T_RETURN.getString("MESSAGE")));
        		}else{
        			strMsg = table_T_RETURN.getString("MESSAGE");
        		}
        		
        	}
        }
		return strMsg;
	}
	
	/**
	 * 插入主表数据
	 */
	public static void UpdateMainSql(String value,String tablename,String requestid){
		RecordSet rsupdate = new RecordSet();
		// 更新 项目状态  （主表）SQL
		String UpdateMainSql = "update "+tablename+" set xmzt = '"+value+"' where requestid="+requestid;
		LogUtil.doWriteLog("==更新 项目状态  （主表）SQL=="+UpdateMainSql);
		rsupdate.execute(UpdateMainSql);
	}
	
	/**
	 * 查询单人报销/多人报销
	 */
	public static String ReimHrmNum(String requestid){
		RecordSet rsmain = new RecordSet();
		RecordSet rsdt = new RecordSet();
		// 报销人数
		String strHrmNums = "1";
		// 查询是否为单人报销
		String strMainSql = "select * from formtable_main_85 where requestid="+requestid;
		rsmain.execute(strMainSql);
		if(rsmain.first()){
			// 是否为单人报销
			String strReiSingleSql = rsmain.getString("sqrsfwbxr");
			// 申请人本人
			if("0".equals(strReiSingleSql)){
				strHrmNums = "1";
			}else{
				String dtsql = "select fmdt.* from formtable_main_85 fm,formtable_main_85_dt2 fmdt where fm.requestid='"+requestid+"' and fm.id= fmdt.mainid";
				rsdt.execute(dtsql);
				if(rsdt.first()){
					strHrmNums = String.valueOf(rsdt.getCounts());
				}
			}
			LogUtil.doWriteLog(strReiSingleSql+"==报销明细=="+strHrmNums);
		}
		return strHrmNums;
	}
}
