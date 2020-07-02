package com.weavernorth.filterTest;

import weaver.general.BaseBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Proxy;

public class ThreeFilter extends BaseBean implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        String name = servletRequest.getParameter("name");


        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String name1 = request.getParameter("name");

        this.writeLog("name: " + name + " name1: " + name1);

        this.writeLog("第3个过滤器执行了: " + servletRequest);
        MyHandler myHandler = new MyHandler(servletRequest, servletResponse);
        Proxy.newProxyInstance(servletRequest.getClass().getClassLoader(), servletRequest.getClass().getInterfaces(), myHandler);

        //response.sendRedirect("http://www.iqiyi.com");
        filterChain.doFilter(servletRequest, servletResponse);//交给下一个过滤器或servlet处理

        //设置浏览器解析的格式，否则浏览器会出现乱码
//        response.setContentType("text/html;charset=utf-8");
//        PrintWriter writer = response.getWriter();
//
//        writer.println("修改了");
//        writer.flush();

    }

    @Override
    public void destroy() {

    }


}
