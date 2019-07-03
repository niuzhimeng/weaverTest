package com.weavernorth.util;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.json.JSONException;
import weaver.conn.RecordSet;

/**
 * 类名:PrivateUtil 类描述:列表工具类 Company:weavernorth
 * 
 * @author:weigh
 * @date: 2017-05-04
 */
public class PrivateUtil {

	/**
	 * 获取系统当前日期时间 
	 * 
	 * @return 系统当前日期时间
	 */
	public static String getNowDate() {
		long longNowDate = System.currentTimeMillis();
		return String.valueOf(longNowDate);
	}
	
	public static String getNowTime(){
		Date day=new Date();    

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 

		return df.format(day);
	}
	
	public static String NowDate(){
		Date day=new Date();    

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 

		return df.format(day);
	}

	/**
	 * 比较一个string类型的时间 和 系统当前时间作对比，相差多少秒
	 * 
	 * @param StartDate
	 * @return
	 */
	public static int CompareTime(String StartDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 参数中的时间
		Date DateTime_send = null;
		// 系统当前时间
		Date DateTime_sys = null;
		try {
			DateTime_send = sdf.parse(StartDate);
			DateTime_sys = sdf.parse(getNowDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long long_DateTime_send = DateTime_send.getTime();
		long long_DateTime_sys = DateTime_sys.getTime();
		int c = (int) ((long_DateTime_sys - long_DateTime_send) / 1000);

		return c;
	}

	/**
	 * 根据登录账号 获取该人员的相应信息
	 * 
	 * @param loginid
	 *            登录账号
	 * @return
	 * @throws JSONException
	 */
	public static JSONObject getHrmResource(String loginid)
			throws JSONException {
		RecordSet rs = new RecordSet();
		JSONObject jsonObj = new JSONObject();
		// 判断该账号是否存在oa系统中
		String strIsExist = "no";
		// 用户id
		String strUserid = "";
		// 用户密码
		String strUserPassword = "";
		// 查询人员信息
		String strHrmSql = "select id,password from hrmresource where loginid='"
				+ loginid + "'";
		System.out.println("--查询该账号是否存在oa系统中Sql:---" + strHrmSql);
		rs.execute(strHrmSql);
		if (rs.first()) {
			strIsExist = "yes";
			strUserid = rs.getString("id");
			strUserPassword = rs.getString("password");
		}
		jsonObj.put("isexist", strIsExist);
		jsonObj.put("userid", strUserid);
		jsonObj.put("userpassword", strUserPassword);

		return jsonObj;
	}
	
	
	/**
	 * 根据人员id查询登录名
	 * @param userid
	 * @return
	 */
	public static String getAccountbyid(String userid){
		RecordSet rs = new RecordSet();
		String strAccount = "";
		String SelectSql = "select loginid from hrmresource where id='"
				+ userid + "'";
		rs.execute(SelectSql);
		if(rs.first()){
			strAccount = rs.getString("loginid");
		}
		return strAccount;
		
	}

	public static String big(double d) {
        NumberFormat nf = NumberFormat.getInstance();
        // 是否以逗号隔开, 默认true以逗号隔开,如[123,456,789.128]
        nf.setGroupingUsed(false);
        // 结果未做任何处理
        return nf.format(d);
    }

	
	/**
	 * 测试方法
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "sunny");
		map.put("value", "1");
		map.put("value", "2");
		map.put("value", "3");
		map.put("value", "4");

		map.get("we");
		
		System.out.println("====="+getNowTime());
		

	}
}
