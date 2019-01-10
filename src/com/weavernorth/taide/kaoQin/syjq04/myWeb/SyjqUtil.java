package com.weavernorth.taide.kaoQin.syjq04.myWeb;

import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;

/**
 * 获取送剩余假期-04
 */
public class SyjqUtil {

    private static final String DATA_TYPE = "0"; // uf_loginInfo表中的密码类型
    private static BaseBean baseBean = new BaseBean();

    public static DT_HR0004_OUTOUTPUT[] execute(DT_HR0004_IN dt_hr0004_in) {
        String userName = "";
        String passWord = "";
        RecordSet recordSet = new RecordSet();
        SI_OA_OUT_HR0004ServiceLocator locator = new SI_OA_OUT_HR0004ServiceLocator();
        DT_HR0004_OUTOUTPUT[] dt_hr0004_outoutputs = new DT_HR0004_OUTOUTPUT[0];
        try {
            SI_OA_OUT_HR0004BindingStub stub = (SI_OA_OUT_HR0004BindingStub) locator.getHTTP_Port();
            recordSet.executeQuery("select * from uf_loginInfo where dataType = '" + DATA_TYPE + "'");
            if (recordSet.next()) {
                userName = recordSet.getString("loginid");
                passWord = recordSet.getString("password");
            }

            stub.setUsername(userName);
            stub.setPassword(passWord);

            dt_hr0004_outoutputs = stub.SI_OA_OUT_HR0004(dt_hr0004_in);
        } catch (Exception e) {
            baseBean.writeLog("SyjqUtil 异常： " + e);
        }
        return dt_hr0004_outoutputs;
    }
}
