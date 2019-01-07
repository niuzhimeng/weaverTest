package com.weavernorth.taide.kaoQin.sksj.myWeb;

import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;

/**
 * 刷卡数据util
 */
public class SksjUtil {

    private static final String DATA_TYPE = "0"; // uf_loginInfo表中的密码类型
    private static BaseBean baseBean = new BaseBean();

    public static void execute(DT_HR0005_IN dtHr0005In) {
        baseBean.writeLog("SksjUtil刷卡数据 执行=============== " + TimeUtil.getCurrentTimeString());
        String userName = "";
        String passWord = "";
        RecordSet recordSet = new RecordSet();
        SI_OA_OUT_HR0005ServiceLocator locator = new SI_OA_OUT_HR0005ServiceLocator();
        try {
            SI_OA_OUT_HR0005BindingStub stub = (SI_OA_OUT_HR0005BindingStub) locator.getHTTP_Port();
            recordSet.executeQuery("select * from uf_loginInfo where dataType = '" + DATA_TYPE + "'");
            if (recordSet.next()) {
                userName = recordSet.getString("loginid");
                passWord = recordSet.getString("password");
            }
            baseBean.writeLog("用户名： " + userName);
            stub.setUsername(userName);
            stub.setPassword(passWord);

            stub.SI_OA_OUT_HR0005(dtHr0005In);
            baseBean.writeLog("SksjUtil刷卡数据 结束=============== " + TimeUtil.getCurrentTimeString());
        } catch (Exception e) {
            baseBean.writeLog("刷卡数据util SksjUtil异常： " + e);
        }


    }
}
