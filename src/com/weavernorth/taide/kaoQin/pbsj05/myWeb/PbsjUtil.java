package com.weavernorth.taide.kaoQin.pbsj05.myWeb;

import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;

/**
 * 获取送剩余假期-04
 */
public class PbsjUtil {

    private static final String DATA_TYPE = "0"; // uf_loginInfo表中的密码类型
    private static BaseBean baseBean = new BaseBean();

    public static DT_HR0003_OUTOUTPUT[] execute(DT_HR0003_ININPUT[] MT_OA_OUT_HR0003) {
        String userName = "";
        String passWord = "";
        RecordSet recordSet = new RecordSet();
        SI_OA_OUT_HR0003ServiceLocator locator = new SI_OA_OUT_HR0003ServiceLocator();
        DT_HR0003_OUTOUTPUT[] dt_hr0003_outoutputs = new DT_HR0003_OUTOUTPUT[0];
        try {
            SI_OA_OUT_HR0003BindingStub stub = (SI_OA_OUT_HR0003BindingStub) locator.getHTTP_Port();
            recordSet.executeQuery("select * from uf_loginInfo where dataType = '" + DATA_TYPE + "'");
            if (recordSet.next()) {
                userName = recordSet.getString("loginid");
                passWord = recordSet.getString("password");
            }

            stub.setUsername(userName);
            stub.setPassword(passWord);
            dt_hr0003_outoutputs = stub.SI_OA_OUT_HR0003(MT_OA_OUT_HR0003);
        } catch (Exception e) {
            baseBean.writeLog("PbsjUtil 异常： " + e);
        }
        return dt_hr0003_outoutputs;
    }
}
