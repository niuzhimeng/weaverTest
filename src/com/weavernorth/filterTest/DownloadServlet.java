package com.weavernorth.filterTest;

import sun.misc.BASE64Encoder;
import weaver.general.BaseBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

public class DownloadServlet extends BaseBean implements Servlet {
    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.writeLog(this.getClass().getName() + " DownloadServlet初始化了==========");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        response.setDateHeader("Expires", -1);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");

        String fileName = request.getParameter("name");
        // 设置响应头类型
        ServletContext servletContext = request.getSession().getServletContext();
        String mimeType = servletContext.getMimeType(fileName);
        this.writeLog("mimeType: " + mimeType);
        response.setHeader("content-type", mimeType);

        // 中文名设置
        //获取客户端信息
        String agent = request.getHeader("User-Agent");
        this.writeLog("agent: " + agent);
        //定义一个变量记录编码之后的名字
        String filenameEncoder = "";
        if (agent.contains("MSIE")) {
            //IE编码
            filenameEncoder = URLEncoder.encode(fileName, "utf-8");
            filenameEncoder = filenameEncoder.replace("+", "");
        } else if (agent.contains("Firefox")) {
            //火狐编码
            BASE64Encoder base64Encoder = new BASE64Encoder();
            filenameEncoder = "=?utf-8?B?" + base64Encoder.encode(fileName.getBytes("utf-8")) + "?=";
        } else {
            //浏览器编码
            filenameEncoder = URLEncoder.encode(fileName, "utf-8");
        }

        this.writeLog("调整编码后文件名： " + filenameEncoder);

        // 设置响应头打开方式
        response.setHeader("content-disposition", "attachment;filename=" + filenameEncoder);

        ServletOutputStream outputStream = response.getOutputStream();

        // 读取服务器文件
        FileInputStream fileInputStream = new FileInputStream("D:\\WEAVER\\ecology\\" + fileName);
        byte[] bytes = new byte[1024 * 2];
        int len;
        while ((len = fileInputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, len);
        }

        // 从response获取的流程不需要手动关闭
        fileInputStream.close();

    }

    @Override
    public void destroy() {

    }


}
