package com.weavernorth.taide.invoice;

public class ConnUtil {

    /**
     * 是否抵扣状态转换
     */
    public static String sfdkChange(String before) {
        String returnCode = "N";
        if ("0".equals(before)) {
            returnCode = "Y";
        }
        return returnCode;
    }
}
