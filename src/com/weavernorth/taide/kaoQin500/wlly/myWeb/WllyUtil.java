package com.weavernorth.taide.kaoQin500.wlly.myWeb;

import weaver.conn.RecordSet;
import weaver.general.BaseBean;

/**
 * 物料领用 OA -> SAP
 */
public class WllyUtil {

    private static final String DATA_TYPE = "0"; // uf_loginInfo表中的密码类型
    private static BaseBean baseBean = new BaseBean();

    public static DT_MMI002_OUTOUTPUT[] execute(DT_MMI002_IN dtMmi002In) {
        String userName = "";
        String passWord = "";
        RecordSet recordSet = new RecordSet();
        SI_OA_OUT_MMI002ServiceLocator locator = new SI_OA_OUT_MMI002ServiceLocator();
        DT_MMI002_OUTOUTPUT[] dt_hr0004_outoutputs = new DT_MMI002_OUTOUTPUT[0];
        try {
            SI_OA_OUT_MMI002BindingStub stub = (SI_OA_OUT_MMI002BindingStub) locator.getHTTP_Port();
            recordSet.executeQuery("select * from uf_loginInfo where dataType = '" + DATA_TYPE + "'");
            if (recordSet.next()) {
                userName = recordSet.getString("loginid");
                passWord = recordSet.getString("password");
            }

            stub.setUsername(userName);
            stub.setPassword(passWord);

            dt_hr0004_outoutputs = stub.SI_OA_OUT_MMI002(dtMmi002In);
        } catch (Exception e) {
            baseBean.writeLog("WllyUtil 异常： " + e);
        }
        return dt_hr0004_outoutputs;
    }
}
