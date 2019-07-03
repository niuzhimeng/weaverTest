package com.weavernorth.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class CommonUtil {
	
	public CommonUtil(){}
	
	/**
	 * 判断字符串是否是整数
	 * @param String strValue 需要判断的字符串
	 * @return boolean 
	 */
	 public static boolean isInteger(String value) {
		 try {
			 Integer.parseInt(value);
			 return true;
		 } catch (NumberFormatException e) {
			 return false;
		 }
	 }

	/**
	 * 判断字符串是否是浮点数
	 * @param String strValue 需要判断的字符串
	 * @return boolean 
	 */
	 public static boolean isDouble(String value) {
		 try {
			 Double.parseDouble(value);
			 if (value.contains("."))
				 return true;
			 return false;
		 } catch (NumberFormatException e) {
			 return false;
		 }
	 }
	 
	/**
	 * 判断字符串是否是数字
	 * @param String strValue 需要判断的字符串
	 * @return boolean 
	 */
	public static boolean isNumber(String strValue) {
		 return isInteger(strValue) || isDouble(strValue);
	}
	
	/**
	 * 处理异常信息，为SQL服务。可以设置输出的长度
	 * @param e 异常对象
	 * @param intLen 输出的长度。如果长度为0，则不截取
	 * @return 截取后的日志文字
	 */
	public static String getExceptionSting(Exception e, int intLen){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		e.printStackTrace(new PrintStream(baos));  
		String strErrMsg = baos.toString();  
		
		Pattern p = Pattern.compile("\n");
        Matcher m = p.matcher(strErrMsg);
        strErrMsg = m.replaceAll(";");
        strErrMsg=replaceSqlChar(strErrMsg);

		if (strErrMsg != null && !"".equals(strErrMsg)) {
			int intErrLen = strErrMsg.length();
			if(intLen!=0){
				if (intLen!=0&&intErrLen >= intLen) {
					intErrLen = intLen;
				}
				strErrMsg = strErrMsg.substring(0, intErrLen);
			}
		}
		
		return strErrMsg;
	}
	
	/**
	 * 更新SQL语句中的特殊字符
	 * @param strInput 输入字符串
	 * @return 替换为Oracle方式后的字符串
	 */
	public static String replaceSqlChar(String strInput) {
		String strOutput="";
		if (strInput.indexOf("&") > 0) {
			strOutput=strInput.replaceAll("&", "'||chr(38)||'");
		}else if (strInput.indexOf("'") > 0) {
			strOutput=strInput.replaceAll("\'", "'||chr(39)||'");
		}else{
			strOutput=strInput;
		}

		return strOutput;
	}
	
	/**
	 * 翻译流程接口返回异常信息
	 * @param intInput 输入字符串
	 * @return 如果输入为大于0的整数，则返回为Y，标识流程创建成功；如果输入为小于0的负数，则替换为对应的中文信息。
	 */
	public static String getWorkFlowReturnMsg(int intInput) {
		String strOutput="";
		
		if (intInput > 0) {
			strOutput="Y";
		} else if (intInput == -1) {
			strOutput = "创建流程失败";
		} else if (intInput == -2) {
			strOutput = "用户没有流程创建权限";
		} else if (intInput == -3) {
			strOutput = "创建流程基本信息失败";
		} else if (intInput == -4) {
			strOutput = "保存表单主表信息失败";
		} else if (intInput == -5) {
			strOutput = "更新紧急程度失败";
		} else if (intInput == -6) {
			strOutput = "流程操作失败";
		} else if (intInput == -7) {
			strOutput = "流转至下一节点失败";
		} else if (intInput == -8) {
			strOutput = "节点附加操作失败";
		} else if (intInput == -9) {
			strOutput = "保存明细表信息失败";
		}

		return strOutput;
	}

}
