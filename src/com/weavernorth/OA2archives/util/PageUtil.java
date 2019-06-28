package com.weavernorth.OA2archives.util;

/**
 * 类名:PageUtil
 * 类描述: 分页信息Util类
 * Company:weavernorth
 *
 * @author:刘立华
 * @date: 2017-8-17 下午4:32:31
 */
public class PageUtil {


    /**
     * 文本匡多条件模糊查询
     * strparams:文本框输入的多个值
     * strparam:查询的字段名
     */

    public String getNewSQL(String strParams, String strParam) {
        String strSqlWhere = "";
        String[] strNewParams = strParams.split(",");
        if (strNewParams.length == 1) {

            strSqlWhere = " and (','||" + strParam + "||',' like '%," + strNewParams[0] + ",%'";

        } else {
            strSqlWhere = " and (','||" + strParam + "||',' like '%," + strNewParams[0] + ",%'";
            for (int i = 1; i < strNewParams.length; i++) {
                strSqlWhere += "or ','||" + strParam + "||',' like '%," + strNewParams[i] + ",%'";
            }

        }
        strSqlWhere += ")";
        return strSqlWhere;
    }

    /**
     * 是否成功的转换
     *
     * @return
     */
    public String getIsSuccess(String index) {
        String labelname = "";
        if ("".equals(index)) {

        } else if ("0".equals(index)) {
            labelname = "归档成功";
        } else if ("1".equals(index)) {
            labelname = "归档失败";
        } else if ("2".equals(index)) {
            labelname = "未归档";
        }
        return labelname;
    }

}
