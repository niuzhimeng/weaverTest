package com.weavernorth.taide.kaoQin.jbxx.myWeb;

import weaver.conn.RecordSet;
import weaver.general.BaseBean;

/**
 * 人员基本信息调用工具类
 */
public class BaseInfoUtil {

    private static final String DATA_TYPE = "0"; // uf_loginInfo表中的密码类型
    private static BaseBean baseBean = new BaseBean();

    public static DT_HRI011_OUTRETURN[] execute(DT_HRI011_IN dtHri011In) {
        String userName = "";
        String passWord = "";
        RecordSet recordSet = new RecordSet();
        SI_YT_OUT_HRI011ServiceLocator locator = new SI_YT_OUT_HRI011ServiceLocator();
        DT_HRI011_OUTRETURN[] returns = new DT_HRI011_OUTRETURN[0];
        try {
            SI_YT_OUT_HRI011BindingStub stub = (SI_YT_OUT_HRI011BindingStub) locator.getHTTP_Port();
            recordSet.executeQuery("select * from uf_loginInfo where dataType = '" + DATA_TYPE + "'");
            if (recordSet.next()) {
                userName = recordSet.getString("loginid");
                passWord = recordSet.getString("password");
            }
            stub.setUsername(userName);
            stub.setPassword(passWord);

            //调用接口
            returns = stub.SI_YT_OUT_HRI011(dtHri011In);
        } catch (Exception e) {
            baseBean.writeLog("BaseInfoUtil 异常： " + e);
        }
        return returns;
    }
}
