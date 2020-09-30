package com.weavernorth.voucher.util;

import com.weaver.general.Util;

import _197._183._104._47.ormrpc.services.EASLogin.EASLoginProxy;
import _197._183._104._47.ormrpc.services.EASLogin.EASLoginProxyServiceLocator;
import client.WSContext;
import weaver.general.BaseBean;

/**
 * @description: EAS集成 登录工具类
 * @author: DJC
 * @date: 2019.11.18
 * @version: 1.0
 */
public class EasloginUtil extends BaseBean {

    /**
     * 判断是否登录EAS 成功
     * @param
     * @return true 登录成功 false 登录失败
     */
    public boolean IsLoginEas(){
        try {
            //配置文件 EAS 登录信息
            String userName = Util.null2String(getPropValue("Eas_Login","userName"));
            String password = Util.null2String(getPropValue("Eas_Login","password"));
            String slnName = Util.null2String(getPropValue("Eas_Login","slnName"));
            String dcName = Util.null2String(getPropValue("Eas_Login","dcName"));
            String language  = Util.null2String(getPropValue("Eas_Login","language"));
            int dbType = Util.getIntValue(getPropValue("Eas_Login","dbType"));
            writeLog("==userName=="+userName+"==password=="+password+"==slnName=="+slnName);
            writeLog("==dcName=="+dcName+"==language=="+language+"==dbType=="+dbType);

            //登录
            EASLoginProxy proxy = new EASLoginProxyServiceLocator().getEASLogin();
            WSContext context = proxy.login(userName,password,slnName,dcName,language,dbType);
            
            if(context.getSessionId() == null){
                writeLog("-----------EAS Login Fail 请检查参数配置!----------");
                return false;
            }else {
                writeLog("EAS Login Success");
                writeLog("-----------当前登录用户：" + context.getUserName());
                writeLog("-----------当前登录用户 SessionId: " + context.getSessionId());
                return true;
            }

        }catch (Exception e){
            e.printStackTrace();
            writeLog("EAS Login Fail: "+e);
            return false;
        }
    }
}
