package com.weavernorth.taide.kaoQin500.action.myWeb;

import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;

/**
 * 推送考勤数据 - 四条流程公用
 */
public class PushKqWorkFlowUtil {

    private static final String DATA_TYPE = "0"; // uf_loginInfo表中的密码类型
    private static BaseBean baseBean = new BaseBean();

    public static DT_HR0002_OUTRet_Msg[] execute(DT_HR0002_IN dtHr0002In) throws Exception {
        baseBean.writeLog("PushKqWorkFlowUtil 执行开始=============== " + TimeUtil.getCurrentTimeString());
        String userName = "";
        String passWord = "";
        RecordSet recordSet = new RecordSet();

        SI_OA_OUT_HR0002ServiceLocator locator = new SI_OA_OUT_HR0002ServiceLocator();
        SI_OA_OUT_HR0002BindingStub stub = (SI_OA_OUT_HR0002BindingStub) locator.getHTTP_Port();

        recordSet.executeQuery("select * from uf_loginInfo where dataType = '" + DATA_TYPE + "'");
        if (recordSet.next()) {
            userName = recordSet.getString("loginid");
            passWord = recordSet.getString("password");
        }
        stub.setUsername(userName);
        stub.setPassword(passWord);

        DT_HR0002_OUTRet_Msg[] dt_hr0002_outRet_msgs = stub.SI_OA_OUT_HR0002(dtHr0002In);
        baseBean.writeLog("PushKqWorkFlowUtil 执行结束=============== " + TimeUtil.getCurrentTimeString() +
                ", 返回数组长度： " + dt_hr0002_outRet_msgs.length);

        return dt_hr0002_outRet_msgs;
    }

}
