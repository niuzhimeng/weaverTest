package com.weavernorth.util;

import java.math.BigDecimal;

import weaver.conn.RecordSet;

/**
 * 工具类
 * @author wgh
 *
 */
public class ToolUtil {
	
	
	public static void main(String[] args) {
         double money = 2020004.01;
         BigDecimal numberOfMoney = new BigDecimal(money);
         String s = number2CNMontrayUnit(numberOfMoney);
         System.out.println("你输入的金额为：【"+ money +"】   #--# [" +s.toString()+"]");
	}
	
	/**
	 * 流程查询显示是否作废
	 * weigh
	 * @param fieldid
	 * @param selectvalue
	 * @return
	 */
	public static String selectdelete(String requestdelete) {
		String strDeleteName = "";
		if(requestdelete.equals("1")){
			strDeleteName = "作废";
		}
		return strDeleteName;
	}
	
	/**
	 * 根据字段id和下拉框值查询下拉框显示值
	 * 
	 * @param fieldid
	 * @param selectvalue
	 * @return
	 */
	public static String getselectvalue(String fieldid, String selectvalue) {
		RecordSet rs = new RecordSet();
		String strSelectName = "";
		String Sql = "select dbo.convToCN(selectname) selectname from workflow_SelectItem where fieldid="
				+ fieldid + " and selectvalue=" + selectvalue;
		LogUtil.doWriteLog("==查询下拉框实际值==" + Sql);
		rs.execute(Sql);
		if (rs.first()) {
			strSelectName = rs.getString("selectname");
		}
		return strSelectName;

	}
	
	
	
	
	 /**
     * 汉语中数字大写
     */
    private static final String[] CN_UPPER_NUMBER = { "零", "壹", "贰", "叁", "肆",
            "伍", "陆", "柒", "捌", "玖" };
    /**
     * 汉语中货币单位大写，这样的设计类似于占位符
     */
    private static final String[] CN_UPPER_MONETRAY_UNIT = { "分", "角", "元",
            "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "兆", "拾",
            "佰", "仟" };
    /**
     * 特殊字符：整
     */
    private static final String CN_FULL = "整";
    /**
     * 特殊字符：负
     */
    private static final String CN_NEGATIVE = "负";
    /**
     * 金额的精度，默认值为2
     */
    private static final int MONEY_PRECISION = 2;
    /**
     * 特殊字符：零元整
     */
    private static final String CN_ZEOR_FULL = "零元" + CN_FULL;

    /**
     * 把输入的金额转换为汉语中人民币的大写
     * 
     * @param numberOfMoney
     *            输入的金额
     * @return 对应的汉语大写
     */
    public static String number2CNMontrayUnit(BigDecimal numberOfMoney) {
        StringBuffer sb = new StringBuffer();
        // -1, 0, or 1 as the value of this BigDecimal is negative, zero, or
        // positive.
        int signum = numberOfMoney.signum();
        // 零元整的情况
        if (signum == 0) {
            return CN_ZEOR_FULL;
        }
        //这里会进行金额的四舍五入
        long number = numberOfMoney.movePointRight(MONEY_PRECISION)
                .setScale(0, 4).abs().longValue();
        // 得到小数点后两位值
        long scale = number % 100;
        int numUnit = 0;
        int numIndex = 0;
        boolean getZero = false;
        // 判断最后两位数，一共有四中情况：00 = 0, 01 = 1, 10, 11
        if (!(scale > 0)) {
            numIndex = 2;
            number = number / 100;
            getZero = true;
        }
        if ((scale > 0) && (!(scale % 10 > 0))) {
            numIndex = 1;
            number = number / 10;
            getZero = true;
        }
        int zeroSize = 0;
        while (true) {
            if (number <= 0) {
                break;
            }
            // 每次获取到最后一个数
            numUnit = (int) (number % 10);
            if (numUnit > 0) {
                if ((numIndex == 9) && (zeroSize >= 3)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[6]);
                }
                if ((numIndex == 13) && (zeroSize >= 3)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[10]);
                }
                sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                getZero = false;
                zeroSize = 0;
            } else {
                ++zeroSize;
                if (!(getZero)) {
                    sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                }
                if (numIndex == 2) {
                    if (number > 0) {
                        sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                    }
                } else if (((numIndex - 2) % 4 == 0) && (number % 1000 > 0)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                }
                getZero = true;
            }
            // 让number每次都去掉最后一个数
            number = number / 10;
            ++numIndex;
        }
        // 如果signum == -1，则说明输入的数字为负数，就在最前面追加特殊字符：负
        if (signum == -1) {
            sb.insert(0, CN_NEGATIVE);
        }
        // 输入的数字小数点后两位为"00"的情况，则要在最后追加特殊字符：整
        if (!(scale > 0)) {
            sb.append(CN_FULL);
        }
        return sb.toString();
    }
    
  /**
   * 查询审批级别显示值
   * @param selectvalue
   * @return
   */
    public static String getSelectValue_SPJB(String selectvalue){
    	RecordSet rs = new RecordSet();
    	// 返回值  
    	String strMsg = "";
    	if(!"".equals(selectvalue)){
    		// 查询审批级别  显示值
        	String sql = "select dbo.convToCN(selectname) selectname from workflow_SelectItem where fieldid=11888 and selectvalue=" + selectvalue;
        	rs.execute(sql);
        	if(rs.first()){
        		strMsg = rs.getString("selectname");
        	}
    	}
		return strMsg;
    }
    
    
    /**
     * 查询付款情况显示值
     * @param selectvalue
     * @return
     */
      public static String getSelectValue_FKQK(String selectvalue){
      	RecordSet rs = new RecordSet();
      	// 返回值  
      	String strMsg = "";
      	if(!"".equals(selectvalue)){
	      	// 查询付款情况  显示值
	      	String sql = "select dbo.convToCN(selectname) selectname from workflow_SelectItem where fieldid=11945 and selectvalue=" + selectvalue;
	      	rs.execute(sql);
	      	if(rs.first()){
	      		strMsg = rs.getString("selectname");
	      	}
      	}
  		return strMsg;
      }
      
      /**
       * 查询履行情况情况显示值
       * @param selectvalue
       * @return
       */
        public static String getSelectValue_LXQK(String selectvalue){
        	RecordSet rs = new RecordSet();
        	// 返回值  
        	String strMsg = "";
        	if(!"".equals(selectvalue)){
  	      	// 查询付款情况  显示值
  	      	String sql = "select dbo.convToCN(selectname) selectname from workflow_SelectItem where fieldid=11928 and selectvalue=" + selectvalue;
  	      	rs.execute(sql);
  	      	if(rs.first()){
  	      		strMsg = rs.getString("selectname");
  	      	}
        	}
    		return strMsg;
        }
}
