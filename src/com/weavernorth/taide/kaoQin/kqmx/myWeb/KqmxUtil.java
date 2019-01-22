package com.weavernorth.taide.kaoQin.kqmx.myWeb;

import com.weavernorth.taide.kaoQin.pbsj05.myWeb.DT_HR0003_ININPUT;
import com.weavernorth.taide.kaoQin.pbsj05.myWeb.DT_HR0003_OUTOUTPUT;
import com.weavernorth.taide.kaoQin.pbsj05.myWeb.SI_OA_OUT_HR0003BindingStub;
import com.weavernorth.taide.kaoQin.pbsj05.myWeb.SI_OA_OUT_HR0003ServiceLocator;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;

/**
 * 考勤明细
 */
public class KqmxUtil {

    private static final String DATA_TYPE = "0"; // uf_loginInfo表中的密码类型
    private static BaseBean baseBean = new BaseBean();

    public static DT_HRI007_OUTRETURN[] execute(DT_HRI007_IN dt_hri007_in) {
        String userName = "";
        String passWord = "";
        RecordSet recordSet = new RecordSet();
        SI_OA_OUT_HR0007ServiceLocator locator = new SI_OA_OUT_HR0007ServiceLocator();
        DT_HRI007_OUTRETURN[] dt_hr0003_outoutputs = new DT_HRI007_OUTRETURN[0];
        try {
            SI_OA_OUT_HR0007BindingStub stub = (SI_OA_OUT_HR0007BindingStub) locator.getHTTP_Port();
            recordSet.executeQuery("select * from uf_loginInfo where dataType = '" + DATA_TYPE + "'");
            if (recordSet.next()) {
                userName = recordSet.getString("loginid");
                passWord = recordSet.getString("password");
            }

            stub.setUsername(userName);
            stub.setPassword(passWord);
            dt_hr0003_outoutputs = stub.SI_OA_OUT_HR0007(dt_hri007_in);
        } catch (Exception e) {
            baseBean.writeLog("KqmxUtil 异常： " + e);
        }
        return dt_hr0003_outoutputs;
    }
}
