package com.test;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.*;

import java.awt.*;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class HtmlToPdf {


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
            HtmlToPdfInterceptor error = new HtmlToPdfInterceptor(proc.getErrorStream());
            HtmlToPdfInterceptor output = new HtmlToPdfInterceptor(proc.getInputStream());
            error.start();
            output.start();
            proc.waitFor();
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }

        return result;
    }

    public static void main(String[] args) {

        HtmlToPdf.convert("C:\\Users\\29529\\Desktop\\123.html",
                "C:\\Users\\29529\\Desktop\\123.pdf");

        //setWatermark("C:\\Users\\29529\\Desktop\\123.pdf","C:\\Users\\29529\\Desktop\\123bak.pdf");

    }

    public static boolean setWatermark(String inputpath, String outputpath) {
        boolean result = false;
        try {
            PdfReader reader = new PdfReader(inputpath);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(outputpath)));
            PdfStamper stamper = new PdfStamper(reader, bos);
            int total = reader.getNumberOfPages() + 1;
            PdfContentByte content;
            BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
            PdfGState gs = new PdfGState();
            for (int i = 1; i < total; i++) {
                content = stamper.getOverContent(i);// 在内容上方加水印
                //content = stamper.getUnderContent(i);//在内容下方加水印
                gs.setFillOpacity(0.2f);
                content.beginText();
                content.setColorFill(Color.LIGHT_GRAY);
                content.setFontAndSize(base, 50);
                content.setTextMatrix(70, 200);
                content.showTextAligned(Element.ALIGN_CENTER, "123123", 300, 350, 55);
                content.setColorFill(Color.BLACK);
                content.setFontAndSize(base, 8);
                content.endText();

            }
            stamper.close();
            result = true;
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
