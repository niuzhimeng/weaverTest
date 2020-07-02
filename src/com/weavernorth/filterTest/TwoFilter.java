package com.weavernorth.filterTest;

import weaver.general.BaseBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TwoFilter extends BaseBean implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        this.writeLog("第2个过滤器执行了");

        response.sendRedirect("http://www.baidu.com");
        filterChain.doFilter(request, response);//交给下一个过滤器或servlet处理

    }

    @Override
    public void destroy() {

    }
}
