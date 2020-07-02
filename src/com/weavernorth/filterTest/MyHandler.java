package com.weavernorth.filterTest;

import com.alibaba.fastjson.JSONObject;
import weaver.general.BaseBean;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyHandler extends BaseBean implements InvocationHandler {

    private ServletRequest servletRequest;

    private ServletResponse servletResponse;

    public MyHandler(ServletRequest servletRequest, ServletResponse servletResponse) {
        this.servletRequest = servletRequest;
        this.servletResponse = servletResponse;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        this.writeLog("当前方法名： " + method.getName());
        this.writeLog("当前args： " + JSONObject.toJSONString(args));
        if ("getParameter".equals(method.getName())) {
            return method.invoke(servletRequest, args)+ "哈哈哈";
        }
        return method.invoke(servletRequest, args);
    }
}
