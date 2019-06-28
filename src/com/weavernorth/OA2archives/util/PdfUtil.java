package com.weavernorth.OA2archives.util;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

public class PdfUtil {

    /**
     * 通过PDFbox获取文章总页数
     *
     * @param filePath:文件路径
     * @return
     * @throws IOException
     */
    public static int getNumberOfPages(String filePath) throws IOException, InterruptedException {
        File file = new File(filePath);
        PDDocument pdDocument = PDDocument.load(new File(filePath));
        int pages = pdDocument.getNumberOfPages();
        pdDocument.close();
        return pages;
    }

    public static void main(String[] args) throws Exception {
        String aa = "D://1128.pdf";
        System.out.println(getNumberOfPages(aa));
    }
}

