package com.weavernorth.workflow.vo;

import java.io.*;

public class ZsHtmlToPdf {


    /**
     * html转pdf
     *
     * @param srcPath  html路径，可以是硬盘上的路径，也可以是网络路径
     * @param destPath pdf保存路径
     * @return 转换成功返回true
     */
    public static boolean convert(String srcPath, String destPath) {
        File file = new File(destPath);
        File parent = file.getParentFile();
        // 如果pdf保存路径不存在，则创建路径
        if (!parent.exists()) {
            parent.mkdirs();
        }
        StringBuilder cmd = new StringBuilder();
        cmd.append("D:\\wkhtmltopdf\\bin\\wkhtmltopdf.exe");
        cmd.append(" ");
//        cmd.append("  --header-line");//页眉下面的线
//        cmd.append("  --header-center 这里是页眉这里是页眉这里是页眉这里是页眉 ");//页眉中间内容
//        //cmd.append("  --margin-top 30mm ");//设置页面上边距 (default 10mm)
//        cmd.append(" --header-spacing 10 ");//    (设置页眉和内容的距离,默认0)
        cmd.append(srcPath);
        cmd.append(" ");
        cmd.append(destPath);

        System.out.println(cmd.toString());
        boolean result = true;
        try {
            Process proc = Runtime.getRuntime().exec(cmd.toString());
            ZsHtmlToPdfInterceptor error = new ZsHtmlToPdfInterceptor(proc.getErrorStream());
            ZsHtmlToPdfInterceptor output = new ZsHtmlToPdfInterceptor(proc.getInputStream());
            error.start();
            output.start();
            proc.waitFor();
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }

        return result;
    }



}
