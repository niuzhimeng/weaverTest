package com.weavernorth.workflow;

import com.weavernorth.mail.util.MailSender;
import com.weavernorth.mail.util.MailSenderInfo;
import com.weavernorth.util.LogUtil;
import weaver.conn.RecordSet;
import weaver.docs.docs.DocComInfo;
import weaver.formmode.interfaces.action.BaseAction;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.request.RequestComInfo;

import java.text.NumberFormat;
import java.util.Random;

/**
 * 1.公司发文、公司发函、公司会议纪要 2.部门发文、部门发函、部门会议纪要
 * 流程流转后，根据流程内“邮件提醒人员”字段，发送邮件给对应人员
 * 
 * @author wgh
 * 
 */
public class PC_MailRemind extends BaseAction {
	private static BaseBean bb = new BaseBean();
	// 公司发文workflowid
	private static String strCompDispatchWfid = bb.getPropValue("PC_wnrequestinfo",
			"PC_strCompDispatchWfid");
	// 公司发函workflowid
	private static String strCompAletterWfid = bb.getPropValue("PC_wnrequestinfo",
			"PC_strCompAletterWfid");
	// 公司会议纪要workflowid
	private static String strCompMeetingWfid = bb.getPropValue("PC_wnrequestinfo",
			"PC_strCompMeetingWfid");
	// 部门发文workflowid
	private static String strDeptDispatchWfid = bb.getPropValue("PC_wnrequestinfo",
			"PC_strDeptDispatchWfid");
	// 部门发函workflowid
	private static String strDeptAletterWfid = bb.getPropValue("PC_wnrequestinfo",
			"PC_strDeptAletterWfid");
	// 部门会议纪要workflowid
	private static String strDeptMeetingWfid = bb.getPropValue("PC_wnrequestinfo",
			"PC_strDeptMeetingWfid");
	// 授权流程workflowid
	private static String strAuthorWfid = bb.getPropValue("PC_wnrequestinfo",
			"PC_strAuthorWfid");
	// 规章制度workflowid 
	private static String strSystemWfid = bb.getPropValue("PC_wnrequestinfo",
			"PC_strSystemWfid");

	// 节点后执行操作
	public String execute(RequestInfo request) {
		LogUtil.doWriteLog("====节点后附加操作，发送邮件====");
		RequestComInfo ReqInfo = null;
		try {
			ReqInfo = new RequestComInfo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RecordSet RsSelectTabel = new RecordSet();
		RecordSet rs = new RecordSet();
		// 邮件提醒人
		String strReceiveMail = "";
		// 邮件主题
		String strMailTheme = "";
		// 邮件内容
		String strMailContent = "";
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
		// ----------公司发文
		if (workflowid.equals(strCompDispatchWfid)) {
			// 发文类型
			String strTextType = "";
			// 查询sql
			String SelectSql = "select * from " + strTablename
					+ " where requestid=" + requestid;
			rs.execute(SelectSql);
			if (rs.first()) {
				strReceiveMail = getEmails(Util
						.null2String(rs.getString("sjr")));
				strTextType = Util.null2String(rs.getString("gzzdlx"));
				strMailTheme = ReqInfo.getRequestname(requestid);
				int num = getNumDocIds(Util.null2String(rs.getString("fj")));
				LogUtil.doWriteLog("==num"+num);
				if(strTextType.equals("1")){
					strMailContent = getHrefDocHtmlNames(Util.null2String(rs.getString("zw")),"套红版正文",-1) + getHrefDocHtmlNames(Util.null2String(rs.getString("gzzdzw")),"规章制度正文",-1) + getHrefDocHtmlNames(Util.null2String(rs.getString("gzzdspfj")),"附件",0);
				}else{
					strMailContent = getHrefDocHtmlNames(Util.null2String(rs.getString("zw")),"套红版正文",-1) + getHrefDocHtmlNames(Util.null2String(rs.getString("fj")),"附件",0);
				}
				
			}
		}
		// ----------公司发函
		else if (workflowid.equals(strCompAletterWfid)) {
			// 查询sql
			String SelectSql = "select * from " + strTablename
					+ " where requestid=" + requestid;
			rs.execute(SelectSql);
			if (rs.first()) {
				strReceiveMail = getEmails(Util
						.null2String(rs.getString("sjr")));
				strMailTheme = ReqInfo.getRequestname(requestid);
				strMailContent = getHrefDocHtmlNames(Util.null2String(rs.getString("zw")),"套红版正文",-1) + getHrefDocHtmlNames(Util.null2String(rs.getString("fj")),"附件",0);
			}
		}
		// ----------公司会议纪要
		else if (workflowid.equals(strCompMeetingWfid)) {
			// 查询sql
			String SelectSql = "select * from " + strTablename
					+ " where requestid=" + requestid;
			rs.execute(SelectSql);
			if (rs.first()) {
				strReceiveMail = getEmails(Util
						.null2String(rs.getString("sjr")));
				strMailTheme = ReqInfo.getRequestname(requestid);
				strMailContent = getHrefDocHtmlNames(Util.null2String(rs.getString("zw")),"套红版正文",-1) + getHrefDocHtmlNames(Util.null2String(rs.getString("fj")),"附件",0);
			}
		}
		// ----------部门发文
		else if (workflowid.equals(strDeptDispatchWfid)) {
			// 查询sql
			String SelectSql = "select * from " + strTablename
					+ " where requestid=" + requestid;
			rs.execute(SelectSql);
			if (rs.first()) {
				strReceiveMail = getEmails(Util
						.null2String(rs.getString("sjr")));
				strMailTheme = ReqInfo.getRequestname(requestid);
				strMailContent = getHrefDocHtmlNames(Util.null2String(rs.getString("zw")),"套红版正文",-1) + getHrefDocHtmlNames(Util.null2String(rs.getString("fj")),"附件",0);
			}
		} 
		// ----------部门发函
		else if (workflowid.equals(strDeptAletterWfid)) {
			// 查询sql
			String SelectSql = "select * from " + strTablename
					+ " where requestid=" + requestid;
			rs.execute(SelectSql);
			if (rs.first()) {
				strReceiveMail = getEmails(Util
						.null2String(rs.getString("sjr")));
				strMailTheme = ReqInfo.getRequestname(requestid);
				strMailContent = getHrefDocHtmlNames(Util.null2String(rs.getString("zw")),"套红版正文",-1) + getHrefDocHtmlNames(Util.null2String(rs.getString("fj")),"附件",0);
			}
		} 
		// ----------部门会议纪要
		else if (workflowid.equals(strDeptMeetingWfid)) {
			// 查询sql
			String SelectSql = "select * from " + strTablename
					+ " where requestid=" + requestid;
			rs.execute(SelectSql);
			if (rs.first()) {
				strReceiveMail = getEmails(Util
						.null2String(rs.getString("sjr")));
				strMailTheme = ReqInfo.getRequestname(requestid);
				strMailContent = getHrefDocHtmlNames(Util.null2String(rs.getString("zw")),"套红版正文",-1) + getHrefDocHtmlNames(Util.null2String(rs.getString("fj")),"附件",0);
			}
		} 
		// ----------授权流程
		else if (workflowid.equals(strAuthorWfid)) {
			// 查询sql
			String SelectSql = "select * from " + strTablename
					+ " where requestid=" + requestid;
			rs.execute(SelectSql);
			if (rs.first()) {
				strReceiveMail = getEmails(Util
						.null2String(rs.getString("sqffr")));
				strMailTheme = ReqInfo.getRequestname(requestid);
				int num = getNumDocIds(Util.null2String(rs.getString("fj")));
				strMailContent = getHrefDocHtmlNames(Util.null2String(rs.getString("scsqs")),"授权委托书",-1) + getHrefDocHtmlNames(Util.null2String(rs.getString("fj")),"附件",0) + getHrefDocHtmlNames(Util.null2String(rs.getString("xgfj")),"附件",num);
			}
		}
		// ----------规章制度流程
		else if (workflowid.equals(strSystemWfid)) {
			// 查询sql
			String SelectSql = "select * from " + strTablename
					+ " where requestid=" + requestid;
			rs.execute(SelectSql);
			if (rs.first()) {
				strReceiveMail = getEmails(Util
						.null2String(rs.getString("sjr")));
				strMailTheme = ReqInfo.getRequestname(requestid);
				strMailContent = getHrefDocHtmlNames(Util.null2String(rs.getString("zdzw")),"规章制度正文",-1) + getHrefDocHtmlNames(Util.null2String(rs.getString("scfj")),"附件",0);
			}
		}
		LogUtil.doWriteLog("==邮件提醒人==strReceiveMail（默认）==" + strReceiveMail);
		LogUtil.doWriteLog("==邮件主题==strMailTheme==" + strMailTheme);
		LogUtil.doWriteLog("==邮件内容==strMailContent==" + strMailContent);

		// 调用发送邮件方法
		//sendMail("953352698@qq.com,1337721867@qq.com", strMailTheme, strMailContent);
		sendMail(strReceiveMail, strMailTheme, strMailContent);
		// 不在此中的流程，直接返回信息，禁止提交
		return SUCCESS;

	}

	/**
	 * 发送邮件
	 * @param receiveAddress
	 *            接收者邮箱
	 * @param mailTheme
	 *            邮件主题
	 * @param mailContent
	 *            邮件内容
	 */
	public static void sendMail(String receiveAddress,
			String mailTheme, String mailContent) {
		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		// 发送邮件的服务器的IP
		mailInfo.setMailServerHost("10.102.176.215");
		// 发送邮件的服务器的端口
		mailInfo.setMailServerPort("587");
		// 是否需要身份验证
		mailInfo.setValidate(true);
		// 登陆邮件发送服务器的用户名和密码
		mailInfo.setUserName("OAadmin@ss-tpc.com");
		mailInfo.setPassword("W247811p");
		// 邮件发送者的地址
		mailInfo.setFromAddress("OAadmin@ss-tpc.com");
		// 邮件接收者的地址
		mailInfo.setToAddress(receiveAddress);
		// 抄送的地址。
		// mailInfo.setCcAddress(strCopy2);
		// 密送的地址。
		// mailInfo.setBccAddress(strBCopy2);
		// 邮件主题
		mailInfo.setSubject(mailTheme);
		// 邮件的文本内容
		mailInfo.setContent(mailContent);
		// 所有附件（绝对路径），发送html格式时有效
		// Map<String,String> map=new HashMap<String,String>();
		// map.put("filepathname", "F:/上单0杠5.txt");
		// map.put("filerealname", "0杠5.txt");
		// List<Map<String,String>> listFiles=new ArrayList<Map<String,
		// String>>();
		// listFiles.add(map);
		// mailInfo.setListFiles(listFiles);
		// 发送邮件
		// 发送文本格式
		//MailSender sms = new MailSender();
		//sms.sendTextMail(mailInfo);
		//发送html格式
		
		try{
			LogUtil.doWriteLog("====邮件发送是否成功===="+MailSender.sendHtmlMail(mailInfo));
		}catch (Exception e) {
			// TODO: handle exception
			LogUtil.doWriteLog("====邮件发送异常===="+e);
		}
	}

	/**
	 * 根据人员id，查询邮箱
	 * @param userids	人员id，多个则用英文逗号分开
	 * @return
	 */
	public static String getEmails(String userids) {
		RecordSet rs = new RecordSet();
		// 邮件地址
		String strEmails = "";
		// 用英文版逗号分隔人员id
		String[] strarray = userids.split(",");
		for (int i = 0; i < strarray.length; i++) {
			String sql = "select email from hrmresource where id="
					+ strarray[i];
			rs.execute(sql);
			if (rs.first()) {
				strEmails = strEmails + Util.null2String(rs.getString("email")) + ",";
			}
		}
		return strEmails;
	}
	/**
	 * 根据docid查询出附件名称，并添加链接     如果是多个，则用英文逗号分开，附件名称则换行
	 * @param docids   附件id
	 * @param label	   附件名称前显示字段
	 * @param num	   起始数字， num 为-1时，不写数字
	 * @return
	 */
	public static String getHrefDocHtmlNames(String docids, String label ,int num) {
		DocComInfo docinfo = null;
		try {
			docinfo = new DocComInfo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//附件名称，并添加链接
		String HrefDocHtmlNames = "";
		// 用英文版逗号分隔docid
		String[] strarray = docids.split(",");
		if(num < 0){
			for (int i = 0; i < strarray.length; i++) {
				/*HrefDocHtmlNames = HrefDocHtmlNames + "<br>"+label+": <a href='http://10.102.180.178:88/login/loginSSO.jsp?gopage=/docs/docs/DocDsp.jsp?id="
						+ strarray[i] + "'>"+docinfo.getDocname(strarray[i]) + "</a></br>";*/
				if(!"".equals(Util.null2String(strarray[i]))){
					HrefDocHtmlNames = HrefDocHtmlNames + "<br>"+label+": <a href='https://oa.ss-tpc.com/login/loginSSO.jsp?gopage=/docs/docs/DocDsp.jsp?id="
							+ strarray[i] + "'>"+docinfo.getDocname(strarray[i]) + "</a></br>";
				}
			}
		}else{
			for (int i = 0; i < strarray.length; i++) {
				LogUtil.doWriteLog("==for num===="+num);
				/*HrefDocHtmlNames = HrefDocHtmlNames + "<br>"+label+""+(num+1)+": <a href='http://10.102.180.178:88/login/loginSSO.jsp?gopage=/docs/docs/DocDsp.jsp?id="
						+ strarray[i] + "'>"+docinfo.getDocname(strarray[i]) + "</a></br>";*/
				if(!"".equals(Util.null2String(strarray[i]))){
					HrefDocHtmlNames = HrefDocHtmlNames + "<br>"+label+""+(num+1)+": <a href='https://oa.ss-tpc.com/login/loginSSO.jsp?gopage=/docs/docs/DocDsp.jsp?id="
							+ strarray[i] + "'>"+docinfo.getDocname(strarray[i]) + "</a></br>";
				}
				num += 1;
			}
		}
		
		return HrefDocHtmlNames;
	}
	/**
	 * 
	 * @param docids
	 * @return
	 */
	public static int getNumDocIds(String docids){
		int num = 0;
		// 用英文版逗号分隔docid
		String[] strarray = docids.split(",");
		for (int i = 0; i < strarray.length; i++) {
			num += 1;
		}
		return num;
	}
	private static String big(double d) {
        NumberFormat nf = NumberFormat.getInstance();
        // 是否以逗号隔开, 默认true以逗号隔开,如[123,456,789.128]
        nf.setGroupingUsed(false);
        // 结果未做任何处理
        return nf.format(d);
    }
	/**
	 * 双色球随机号码
	 * @param args
	 */
	public static void main(String[] args) {
		String Type = "大乐透";
		if(Type.equals("双色球")){
			//定义一个数组
			int[] RedBallArray =new int [33];
			for(int i=0;i<RedBallArray.length;i++){
				RedBallArray[i]=i+1;
			}
			Random RedBallRandom = new Random();
			int[]NewRedBallArray = new int[6];
			int RedBallTemp = 0;
			int RedBallIndex = 0;
			for(int i=0;i<6;i++){
				//取a数组的下标数 0---33
				RedBallIndex = RedBallRandom.nextInt(RedBallArray.length-i);  
				//把取到的数存于新建的数组b中
				NewRedBallArray[i] = RedBallArray[RedBallIndex];                          
				RedBallTemp = RedBallArray[RedBallIndex];
				//6次循环取到的随机数按序交换到数组最后面
				RedBallArray[RedBallIndex] = RedBallArray[RedBallArray.length-1-i];
				RedBallArray[RedBallArray.length-1-i] = RedBallTemp;
			}
			System.out.println("##########双色球#######");
			System.out.print("红球号码：");
			for(int i=0;i<6;i++){
				if(i<5){
					System.out.print(NewRedBallArray[i]+", ");
				}else{
					System.out.println(NewRedBallArray[i]);
				}
			}
			//定义一个数组
			int BlueBallArr[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
			//获取数组长度给变量len
	        int len = BlueBallArr.length;
	        //创建随机对象
	        Random random = new Random();
	        //随机数组索引，nextInt(len-1)表示随机整数[0,(len-1)]之间的值
	        int BlueBallArrIdx = random.nextInt(len-1);
	        int BlueBallNum = BlueBallArr[BlueBallArrIdx];//获取数组值
	        System.out.println("篮球号码："+BlueBallNum);
		}else if(Type.equals("大乐透")){
			//定义一个数组
			int[] RedBallArray =new int [35];
			for(int i=0;i<RedBallArray.length;i++){
				RedBallArray[i]=i+1;
			}
			Random RedBallRandom = new Random();
			int[]NewRedBallArray = new int[5];
			int RedBallTemp = 0;
			int RedBallIndex = 0;
			for(int i=0;i<5;i++){
				//取a数组的下标数 0---34
				RedBallIndex = RedBallRandom.nextInt(RedBallArray.length-i);  
				//把取到的数存于新建的数组b中
				NewRedBallArray[i] = RedBallArray[RedBallIndex];                          
				RedBallTemp = RedBallArray[RedBallIndex];
				//6次循环取到的随机数按序交换到数组最后面
				RedBallArray[RedBallIndex] = RedBallArray[RedBallArray.length-1-i];
				RedBallArray[RedBallArray.length-1-i] = RedBallTemp;
			}
			System.out.println("##########大乐透#######");
			System.out.print("红球号码：");
			for(int i=0;i<5;i++){
				if(i<4){
					System.out.print(NewRedBallArray[i]+", ");
				}else{
					System.out.println(NewRedBallArray[i]);
				}
			}
			
			//定义一个数组
			int[] BlueBallArray =new int [12];
			for(int i=0;i<BlueBallArray.length;i++){
				BlueBallArray[i]=i+1;
			}
			Random BlueBallRandom = new Random();
			int[]NewBlueBallArray = new int[2];
			int BlueBallTemp = 0;
			int BlueBallIndex = 0;
			for(int i=0;i<2;i++){
				//取a数组的下标数 0---11
				BlueBallIndex = BlueBallRandom.nextInt(BlueBallArray.length-i);  
				//把取到的数存于新建的数组b中
				NewBlueBallArray[i] = BlueBallArray[BlueBallIndex];                          
				BlueBallTemp = BlueBallArray[BlueBallIndex];
				//6次循环取到的随机数按序交换到数组最后面
				BlueBallArray[BlueBallIndex] = BlueBallArray[BlueBallArray.length-1-i];
				BlueBallArray[BlueBallArray.length-1-i] = BlueBallTemp;
			}
			System.out.print("蓝球号码：");
			for(int i=0;i<2;i++){
				if(i<1){
					System.out.print(NewBlueBallArray[i]+", ");
				}else{
					System.out.println(NewBlueBallArray[i]);
				}
			}
		}
	}
	
	
}
