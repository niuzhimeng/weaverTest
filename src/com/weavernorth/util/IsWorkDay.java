package com.weavernorth.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import weaver.conn.RecordSet;
public class IsWorkDay {  
  
	
	public static boolean getIsWorkday(String strDate){
		boolean ret=true;
		//如果为公众假日，则不为工作日
		String sql="select 1 from HrmPubHoliday where holidayDate = '"+strDate+"' and changeType = 1 and countryid = 1 ";
		RecordSet rs=new RecordSet();
		rs.executeSql(sql);
		if(rs.getCounts()>0){  
			ret=false;
			return ret;
		}  
		//如果为周末,默认为非工作日且被调配为工作日，则返回false
		int a=getWeekByDate(strDate);  
		if(a==1 || a==7){
			sql="select 1 from HrmPubHoliday where holidayDate = '"+strDate+"' and changeType = 2 and countryid = 1 ";
			rs.executeSql(sql);
			if(rs.getCounts()==0){
				ret=false;
				return ret;	
			}
		}
		return ret;
	}
	public static int getWeekByDate(String strDate){
		int returnStr=0;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar=Calendar.getInstance();
		try {
			Date date = sdf.parse(strDate);
			calendar.setFirstDayOfWeek(Calendar.MONDAY);
			calendar.setTime(date);
			returnStr=calendar.get(Calendar.DAY_OF_WEEK);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return returnStr;
	}
}
