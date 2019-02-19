package com.weavernorth.taide.kaoQin.htxq.myWeb;

import weaver.conn.RecordSet;

/**
 * 合同续签流程util
 */
public class HtxqUtil {

    private static final String DATA_TYPE = "0"; // uf_loginInfo表中的密码类型

    public static DT_HRI010_OUTRETURN[] execute(DT_HRI010_IN dtHri010In) throws Exception {
        String userName = "";
        String passWord = "";
        RecordSet recordSet = new RecordSet();
        SI_OA_OUT_HRI010ServiceLocator locator = new SI_OA_OUT_HRI010ServiceLocator();

        SI_OA_OUT_HRI010BindingStub stub = (SI_OA_OUT_HRI010BindingStub) locator.getHTTP_Port();
        recordSet.executeQuery("select * from uf_loginInfo where dataType = '" + DATA_TYPE + "'");
        if (recordSet.next()) {
            userName = recordSet.getString("loginid");
            passWord = recordSet.getString("password");
        }
        stub.setUsername(userName);
        stub.setPassword(passWord);

        return stub.SI_OA_OUT_HRI010(dtHri010In);

    }
}
