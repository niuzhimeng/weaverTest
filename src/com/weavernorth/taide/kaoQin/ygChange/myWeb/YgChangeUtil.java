package com.weavernorth.taide.kaoQin.ygChange.myWeb;

import weaver.conn.RecordSet;
import weaver.general.BaseBean;

/**
 * 转正、离职、转岗 OA -> SAP
 */
public class YgChangeUtil {

    private static final String DATA_TYPE = "0"; // uf_loginInfo表中的密码类型
    private static BaseBean baseBean = new BaseBean();

    public static DT_HRI001_OUTRETURN[] execute(DT_HRI001_IN dtMmi002In) {
        String userName = "";
        String passWord = "";
        RecordSet recordSet = new RecordSet();
        SI_HRI0001_OUTServiceLocator locator = new SI_HRI0001_OUTServiceLocator();
        DT_HRI001_OUTRETURN[] dtHri001Outreturns = new DT_HRI001_OUTRETURN[1];
        try {
            SI_HRI0001_OUTBindingStub stub = (SI_HRI0001_OUTBindingStub) locator.getHTTP_Port();
            recordSet.executeQuery("select * from uf_loginInfo where dataType = '" + DATA_TYPE + "'");
            if (recordSet.next()) {
                userName = recordSet.getString("loginid");
                passWord = recordSet.getString("password");
            }

            stub.setUsername(userName);
            stub.setPassword(passWord);

            dtHri001Outreturns = stub.SI_HRI0001_OUT(dtMmi002In);
        } catch (Exception e) {
            baseBean.writeLog("YgChangeUtil 异常： " + e);
        }
        return dtHri001Outreturns;
    }
}
