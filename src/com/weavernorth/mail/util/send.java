package com.weavernorth.mail.util;

public class send {

	public static void main(String[] args){  
		// 这个类主要是设置邮件
				MailSenderInfo mailInfo = new MailSenderInfo();
				// 发送邮件的服务器的IP
				mailInfo.setMailServerHost("10.102.176.215");
				// 发送邮件的服务器的端口
				mailInfo.setMailServerPort("587");
				// 是否需要身份验证
				mailInfo.setValidate(true);
				// 登陆邮件发送服务器的用户名和密码
				mailInfo.setUserName("OAadmin");
				mailInfo.setPassword("W247811p");
				// 邮件发送者的地址
				mailInfo.setFromAddress("xq.sun@ss-tpc.com");
				// 邮件接收者的地址
				mailInfo.setToAddress("1337721867@qq.com");
				// 抄送的地址。
				// mailInfo.setCcAddress(strCopy2);
				// 密送的地址。
				// mailInfo.setBccAddress(strBCopy2);
				// 邮件主题
				mailInfo.setSubject("主题：测试发布");
				// 邮件的文本内容
				mailInfo.setContent("内容：测试内容");
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
				if(MailSender.sendHtmlMail(mailInfo)){
					System.out.println("====邮件发送成功====");
				}
	     	
	     	//发送邮件  
	     	//MailSender sms = new MailSender();  
	     	//System.out.println(sms.sendTextMail(mailInfo));
	     	//sms.sendTextMail(mailInfo);//发送文本格式
	     
	     //	MailSender.sendHtmlMail(mailInfo);//发送html格式  
			
		//}
	}  
}
