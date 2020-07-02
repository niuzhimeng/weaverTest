package com.test.proxytest.proxy;

import com.test.proxytest.Computer;
import com.test.proxytest.impl.Lenovo;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyProxy {

    @Test
    public void test1() {
        final Lenovo lenovo = new Lenovo();
        //lenovo.product("联系");

        Computer computer = (Computer) Proxy.newProxyInstance(lenovo.getClass().getClassLoader(), lenovo.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // System.out.println("代理方法直接");
                if (method.getName().equals("product")) {

                }
                Object obj = method.invoke(lenovo, args);
                return obj;
            }
        });

        String str = computer.product("联系");
        System.out.println("代理方法执行结果： " + str);
    }
}
