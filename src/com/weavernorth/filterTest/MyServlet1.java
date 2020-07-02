package com.weavernorth.filterTest;

import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class MyServlet1 implements Servlet {


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

        String name = request.getParameter("name");

        BufferedImage bufferedImage = new BufferedImage(100, 50, BufferedImage.TYPE_INT_RGB);
        int width = 100;
        int height = 50;
        //response.setContentType("image/gif");
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        // 设置背景色 0,0 代表图片左上角
        graphics.setColor(Color.pink);
        graphics.fillRect(0, 0, width, height);

        // 画边框
        graphics.drawRect(0, 0, width - 1, height - 1);

        // 设置随机数
        String allStr = "1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
        int length = allStr.length();
        Random random = new Random();


        // 写入4位验证码
        graphics.setColor(Color.BLUE);
        Font font = new Font("微软雅黑",Font.BOLD ,25);
        graphics.setFont(font);
        for (int j = 1; j <= 4; j++) {
            int i = random.nextInt(length);
            graphics.drawString(String.valueOf(allStr.charAt(i)), width / 5 * j, height / 2);
        }

        // 画干扰线
        graphics.setColor(Color.green);
        for (int i = 0; i < 8; i++) {
            int x1 = random.nextInt(width);
            int x2 = random.nextInt(width);
            int y1 = random.nextInt(length);
            int y2 = random.nextInt(length);
            graphics.drawLine(x1, y1, x2, y2);
        }

        ImageIO.write(image, "jpg", response.getOutputStream());
        // 设置编码格式
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter writer = response.getWriter();
//        writer.println("收到参数： " + name);

    }

    @Override
    public void destroy() {

    }
}
