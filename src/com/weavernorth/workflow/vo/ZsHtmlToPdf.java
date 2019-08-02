package com.weavernorth.workflow.vo;

import java.io.File;

public class ZsHtmlToPdf {

    private static final String PDF_TOOL_PATH = "D:\\wkhtmltopdf\\bin\\wkhtmltopdf.exe";

    //private static final String PDF_TOOL_PATH = "D:\\WEAVER\\wk2pdf\\wkhtmltopdf.exe";

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
        cmd.append(PDF_TOOL_PATH);
        cmd.append(" ");
        cmd.append(" --margin-left 21mm ");
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
