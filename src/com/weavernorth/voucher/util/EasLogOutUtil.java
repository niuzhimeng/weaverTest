package com.weavernorth.voucher.util;

import com.weaver.general.BaseBean;
import com.weaver.general.Util;

import _197._183._104._47.ormrpc.services.EASLogin.EASLoginProxy;
import _197._183._104._47.ormrpc.services.EASLogin.EASLoginProxyServiceLocator;

/**
 * @description: EAS集成 登出工具类
 * @author: DJC
 * @date: 2019.12.04
 * @version: 1.0
 */
public class EasLogOutUtil extends BaseBean {

	/**
     * 判断是否登出EAS 成功
     * @param
     * @return true 登出成功 false 登出失败
     */
    public boolean IsLogOutEas(){
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

             //登出
             EASLoginProxy proxy = new EASLoginProxyServiceLocator().getEASLogin();
             boolean context = proxy.logout(userName, slnName, dcName, language);
             
             if(!context){
                 writeLog("-----------EAS LogOut Fail 请检查参数配置!----------");
                 return false;
             }else {
                 writeLog("EAS LogOut Success");
                 return true;
             }

         }catch (Exception e){
             e.printStackTrace();
             writeLog("EAS LogOut Fail: "+e);
             return false;
         }
     }
    	
}
