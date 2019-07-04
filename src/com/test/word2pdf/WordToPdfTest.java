package com.test.word2pdf;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.*;
import com.weavernorth.util.LogUtil;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;

public class WordToPdfTest {

    /**
     * word 转 pdf
     */
    @Test
    public void test() {
        ActiveXComponent app = null;
        Dispatch doc = null;
        try {
            app = new ActiveXComponent("Word.Application");
            app.setProperty("Visible", new Variant(false));
            Dispatch docs = app.getProperty("Documents").toDispatch();

            //转换前的文件路径
            String startFile = "C:\\Users\\29529\\Desktop\\123.docx";
            //转换后的文件路劲
            String overFile = "C:\\Users\\29529\\Desktop\\123.pdf";

            doc = Dispatch.call(docs, "Open", startFile).toDispatch();
            File tofile = new File(overFile);
            if (tofile.exists()) {
                tofile.delete();
            }
            Dispatch.call(doc, "SaveAs", overFile, 17);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            Dispatch.call(doc, "Close", false);
            if (app != null) {
                app.invoke("Quit", new Variant[]{});
            }
        }
        //结束后关闭进程
        ComThread.Release();

    }

    @Test
    public void test2() throws Exception {
        addPdfMark("C:\\Users\\29529\\Desktop\\123.pdf","C:\\Users\\29529\\Desktop\\123After.pdf");
    }

    public static void addPdfMark(String inpdffile, String outPdfFile) throws Exception {

        PdfReader reader = new PdfReader(inpdffile);

        PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(outPdfFile));

        BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);

        Image img = Image.getInstance("D:/WEAVER/ecology/weavernorth/workflow/WaterMark.png");

        LogUtil.debugLog("======开始添加水印========>" + outPdfFile);
        //图片插入位置
        img.setAbsolutePosition(90, 250);

        img.scaleToFit(430, 400);
        PdfGState gs = new PdfGState();
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            PdfContentByte under = stamp.getOverContent(i);
            //设置水印图片透明度
            gs.setFillOpacity(0.4f);

            under.setGState(gs);
            under.setColorFill(Color.BLACK);
            under.setFontAndSize(base, 16);
            under.addImage(img);

        }

        stamp.close();// 关闭
        reader.close();


    }


}
