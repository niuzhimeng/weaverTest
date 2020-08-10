package com.weavernorth.filterTest.upload;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class UpLoadTest implements Servlet {
    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 缓存大小 10M  默认10k  临时文件存储位置
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory(1024 * 1024  * 10, new File("d:/temp"));
//        diskFileItemFactory.setSizeThreshold();
//        diskFileItemFactory.setRepository();

        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        servletFileUpload.setHeaderEncoding("utf-8");
        try {
            List<FileItem> fileItems = servletFileUpload.parseRequest(request);
            for (FileItem item : fileItems) {
                String fieldName = item.getFieldName();
                if (item.isFormField()) {
                    // 普通字段
                    String string = item.getString("utf-8"); // 字段值

                } else {
                    // 文件字段
                    String name = item.getName(); // 获取上传的文件名 不同浏览器不同的结果，有的是全路径名，有的只是文件名
                    name = name.substring(name.lastIndexOf("\\") + 1);

                    FileOutputStream fileOutputStream = new FileOutputStream("d:/");
                    IOUtils.copy(item.getInputStream(), fileOutputStream);


                    item.delete(); // 删除临时文件

                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void destroy() {

    }
}
