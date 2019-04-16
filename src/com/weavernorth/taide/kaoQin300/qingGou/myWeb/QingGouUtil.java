package com.weavernorth.taide.kaoQin300.qingGou.myWeb;

import weaver.conn.RecordSet;

/**
 * 人员增补流程util
 */
public class QingGouUtil {

    private static final String DATA_TYPE = "0"; // uf_loginInfo表中的密码类型

    public static DT_MMI001_OUTOUTPUT[] execute(DT_MMI001_IN dtHri008In) throws Exception {
        String userName = "";
        String passWord = "";
        RecordSet recordSet = new RecordSet();
        SI_OA_OUT_MMI001ServiceLocator locator = new SI_OA_OUT_MMI001ServiceLocator();

        SI_OA_OUT_MMI001BindingStub stub = (SI_OA_OUT_MMI001BindingStub) locator.getHTTP_Port();
        recordSet.executeQuery("select * from uf_loginInfo where dataType = '" + DATA_TYPE + "'");
        if (recordSet.next()) {
            userName = recordSet.getString("loginid");
            passWord = recordSet.getString("password");
        }
        stub.setUsername(userName);
        stub.setPassword(passWord);

        return stub.SI_OA_OUT_MMI001(dtHri008In);

    }
}
