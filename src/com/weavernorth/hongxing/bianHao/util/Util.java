package com.weavernorth.hongxing.bianHao.util;

import weaver.conn.RecordSetDataSource;
import weaver.general.BaseBean;

public class Util {
    private static BaseBean baseBean = new BaseBean();

    public String getCurrentNo(String wybs) {
        RecordSetDataSource recordSet = new RecordSetDataSource("orcl");
        int currentNo;
        String currentNoStr;
        recordSet.execute("select * from uf_lcbh where wybs = '" + wybs + "'");

        if (recordSet.next()) {
            baseBean.writeLog("查到了");
            currentNo = recordSet.getInt("currentNo") < 0 ? 0 : recordSet.getInt("currentNo");
            int newNo = currentNo + 1;
            recordSet.execute("update uf_lcbh set currentNo = '" + newNo + "' where wybs = '" + wybs + "'");
            currentNoStr = String.valueOf(currentNo);
            if (currentNoStr.length() == 1) {
                currentNoStr = "000" + currentNoStr;
            } else if (currentNoStr.length() == 2) {
                currentNoStr = "00" + currentNoStr;
            } else if (currentNoStr.length() == 3) {
                currentNoStr = "0" + currentNoStr;
            }
        } else {
            baseBean.writeLog("没查到");
            recordSet.executeSql("insert into uf_lcbh(wybs, currentNo) values ('" + wybs + "',2)");
            currentNoStr = "001";
        }
        baseBean.writeLog("当前唯一标识： " + wybs + " 对应的流程编号为： " + currentNoStr);
        return currentNoStr;
    }
}
