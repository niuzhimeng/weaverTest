package com.weavernorth.taide.kaoQin.ryzb.myWeb;

import weaver.conn.RecordSet;
import weaver.general.BaseBean;

/**
 * 人员增补流程util
 */
public class RyzbUtil {

    private static final String DATA_TYPE = "0"; // uf_loginInfo表中的密码类型

    public static DT_HRI008_OUTRETURN[] execute(DT_HRI008_IN dtHri008In) throws Exception {
        String userName = "";
        String passWord = "";
        RecordSet recordSet = new RecordSet();
        SI_OA_OUT_HRI008ServiceLocator locator = new SI_OA_OUT_HRI008ServiceLocator();

        SI_OA_OUT_HRI008BindingStub stub = (SI_OA_OUT_HRI008BindingStub) locator.getHTTP_Port();
        recordSet.executeQuery("select * from uf_loginInfo where dataType = '" + DATA_TYPE + "'");
        if (recordSet.next()) {
            userName = recordSet.getString("loginid");
            passWord = recordSet.getString("password");
        }
        stub.setUsername(userName);
        stub.setPassword(passWord);

        return stub.SI_OA_OUT_HRI008(dtHri008In);

    }
}
