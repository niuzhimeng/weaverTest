package com.weavernorth.taide.kaoQin.kqyc.myWeb;

import weaver.conn.RecordSet;
import weaver.general.BaseBean;

/**
 * 定时获取考勤异常数据
 */
public class KqycUtil {

    private static final String DATA_TYPE = "0"; // uf_loginInfo表中的密码类型
    private static BaseBean baseBean = new BaseBean();

    public static DT_HRI006_OUTRETURN[] execute() {
        String userName = "";
        String passWord = "";
        RecordSet recordSet = new RecordSet();
        SI_OA_OUT_HR0006ServiceLocator locator = new SI_OA_OUT_HR0006ServiceLocator();
        DT_HRI006_OUTRETURN[] returns = new DT_HRI006_OUTRETURN[0];
        try {
            SI_OA_OUT_HR0006BindingStub stub = (SI_OA_OUT_HR0006BindingStub) locator.getHTTP_Port();
            recordSet.executeQuery("select * from uf_loginInfo where dataType = '" + DATA_TYPE + "'");
            if (recordSet.next()) {
                userName = recordSet.getString("loginid");
                passWord = recordSet.getString("password");
            }
            stub.setUsername(userName);
            stub.setPassword(passWord);

            DT_HRI006_INSENDER dt_hri006_insender = new DT_HRI006_INSENDER();
            dt_hri006_insender.setCompany_Code("");
            dt_hri006_insender.setDest_System("");
            dt_hri006_insender.setINTF_ID("");
            dt_hri006_insender.setSend_Time("");
            dt_hri006_insender.setSrc_System("OA");

            DT_HRI006_INDATA dt_hri006_indata = new DT_HRI006_INDATA();
            dt_hri006_indata.setBEGDA("");
            dt_hri006_indata.setENDDA("");

            // 拼接最外层对象
            DT_HRI006_IN dt_hri006_in = new DT_HRI006_IN();
            dt_hri006_in.setDATA(dt_hri006_indata);
            dt_hri006_in.setSENDER(dt_hri006_insender);

            //调用接口
            returns = stub.SI_OA_OUT_HR0006(dt_hri006_in);
        } catch (Exception e) {
            baseBean.writeLog("KqycUtil 异常： " + e);
        }
        return returns;
    }
}
