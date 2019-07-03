package com.weavernorth.OA2archives.util;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComFailException;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.weavernorth.util.LogUtil;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 将jacob.dll放入JDK的bin目录下 把jacob.jar放入项目的buildPath中（web项目放到WEB-INF\lib目录下）
 *
 * @author liulihua
 */
public class ConvertToPdf {

    /* 转PDF格式值 */
    private static final int wdFormatPDF = 17;
    private static final int xlFormatPDF = 0;
    private static final int ppFormatPDF = 32;
    private static final int msoTrue = -1;
    private static final int msofalse = 0;

    /* 转HTML格式值 */
    private static final int wdFormatHTML = 8;
    private static final int ppFormatHTML = 12;
    private static final int xlFormatHTML = 44;

    /* 转TXT格式值 */
    private static final int wdFormatTXT = 2;

    public boolean convert2PDF(String inputFile, String pdfFile) {
        LogUtil.debugLog("===转为pdf开始==================");
        LogUtil.debugLog("===pdf转换前文件=======" + inputFile);
        LogUtil.debugLog("===pdf转换pdf后文件========" + pdfFile);
        inputFile = inputFile.replaceAll("/", "\\\\");
        pdfFile = pdfFile.replaceAll("/", "\\\\");
        String suffix = getFileSufix(inputFile);
        File file = new File(inputFile);
        if (!file.exists()) {
            LogUtil.debugLog("文件不存在！");
            return false;
        }
        if (suffix.equals("pdf")) {
            LogUtil.releaseLog("PDF not need to convert!");
            return false;
        }
        if (suffix.equals("doc") || suffix.equals("docx")
                || suffix.equals("txt")) {
            return word2PDF(inputFile, pdfFile, true);
        } else if (suffix.equals("ppt") || suffix.equals("pptx")) {
            return ppt2PDF(inputFile, pdfFile);
        } else if (suffix.equals("xls") || suffix.equals("xlsx")) {
            return excel2PDF(inputFile, pdfFile);
        } else {
            LogUtil.debugLog("文件格式不支持转换!");
            return false;
        }

    }

    /**
     * 获取文件后缀
     *
     * @param fileName
     * @return
     * @author SHANHY
     */
    private String getFileSufix(String fileName) {
        int splitIndex = fileName.lastIndexOf(".");
        return fileName.substring(splitIndex + 1);
    }

    /**
     * @param d
     * @return
     */
    public static boolean isNull(Dispatch d) {
        try {
            Dispatch.call(d, "");
        } catch (IllegalStateException ise) {
            return true;
        } catch (ComFailException cfe) {
            // that's ok, we didn't expect this call to succeed
        }
        return false;
    }

    /**
     * Word文档转换
     *
     * @param inputFile
     * @param pdfFile
     * @author SHANHY
     */
    private boolean word2PDF(String inputFile, String pdfFile, boolean isShowRevisions) {
        LogUtil.debugLog("===========进入word2PDF方法");
        ComThread.InitSTA(true);
        LogUtil.debugLog("===========看看是否卡在进程");
        long start = System.currentTimeMillis();
        ActiveXComponent app = null;
        Dispatch doc = null;
        Exception exception = null;

        if (app == null || app.m_pDispatch == 0) {
            try {
                app = new ActiveXComponent("Word.Application");// 创建一个word对象
                app.setProperty("Visible", new Variant(false)); // 不可见打开word
                app.setProperty("DisplayAlerts", new Variant(false));
                app.setProperty("AutomationSecurity", new Variant(3)); // 禁用宏
                if (doc == null || doc.m_pDispatch == 0) {
                    Dispatch docs = app.getProperty("Documents").toDispatch();// 获取word所有文档对象
                    LogUtil.debugLog("打开文档 =====>>> " + inputFile);
                    // Object[]第三个参数是表示“是否只读方式打开”
                    // 调用Documents对象中Open方法打开文档，并返回打开的文档对象Document
                    doc = Dispatch.call(docs, "Open", inputFile, false, true)
                            .toDispatch();
                    // 调用Document对象的SaveAs方法，将文档保存为pdf格式
                    LogUtil.debugLog("转换文档 [" + inputFile + "] 为 [" + pdfFile
                            + "]");
                    //lq 2018-9-2 清除批注和修订
                    if (isShowRevisions) {
                        Dispatch.put(doc, "ShowRevisions", false);
                    }
                    File file = new File(pdfFile);
                    if (file.exists()) {
                        file.delete();
                    }
                    Dispatch.call(doc, "SaveAs", pdfFile, wdFormatPDF);// word保存为pdf格式宏，值为17
                    // Dispatch.call(doc, "ExportAsFixedFormat", pdfFile,
                    // wdFormatPDF); // word保存为pdf格式宏，值为17
                    long end = System.currentTimeMillis();
                    LogUtil.debugLog("用时：" + (end - start) + "ms.");
                }
                return isNull(doc);
            } catch (Exception e) {
                e.printStackTrace();
                exception = e;
                LogUtil.debugLog("===========Error:文档转换失败：" + getExceptionMsg(e));
            } finally {
                LogUtil.debugLog("ConvertToPdf======finally");


                try {
                    Dispatch.call(doc, "Close", false);
                    LogUtil.debugLog("关闭文档");
                    if (app != null) {
                        app.invoke("Quit", new Variant[]{});
                    }
                    // 如果没有这句话,winword.exe进程将不会关闭
                    ComThread.Release();
                    ComThread.quitMainSTA();
                    LogUtil.debugLog("ConvertToPdf======释放完成");
                    //如果有异常 重新执行一次
                    if (exception != null && exception.getMessage().contains("方法或属性无效因为 该文档是已被保护的文档")) {
                         LogUtil.debugLog("文档转换异常，重新转换");
                         word2PDF(inputFile, pdfFile, false);
                     }
                     else if (exception != null && exception.getMessage().contains("Can't co-create object")) {
                         // 如果没有这句话,winword.exe进程将不会关闭
                         LogUtil.debugLog("Can't co-create object======异常重新开始");
                         word2PDF(inputFile, pdfFile, true);
                     }
                     else if (exception != null && exception.getMessage().contains("Invoke of: SaveAs")) {
                         // 如果没有这句话,winword.exe进程将不会关闭
                         LogUtil.debugLog("Invoke of: SaveAs======异常重新开始");
                         word2PDF(inputFile, pdfFile, true);
                     }
                } catch (Exception e) {
                    e.printStackTrace();
                    LogUtil.debugLog("关闭文档后的try"+getExceptionMsg(e));
                }
            }
        }
        return false;
    }

    /**
     * PPT文档转换
     *
     * @param inputFile
     * @param pdfFile
     * @author SHANHY
     */
    private boolean ppt2PDF(String inputFile, String pdfFile) {
        ComThread.InitSTA(true);

        long start = System.currentTimeMillis();
        ActiveXComponent app = null;
        Dispatch ppt = null;
        if (app == null || app.m_pDispatch == 0) {
            try {
                app = new ActiveXComponent("PowerPoint.Application");// 创建一个PPT对象
//				app.setProperty("Visible", new Variant(false));
//		        app.setProperty("DisplayAlerts", new Variant(false));
                // 不可见打开（PPT转换不运行隐藏，所以这里要注释掉）
//				app.setProperty("AutomationSecurity", new Variant(3)); // 禁用宏
                if (ppt == null || ppt.m_pDispatch == 0) {
                    Dispatch ppts = app.getProperty("Presentations").toDispatch();// 获取文挡属性
                    LogUtil.debugLog("打开文档 >>> " + inputFile);
                    // 调用Documents对象中Open方法打开文档，并返回打开的文档对象Document
                    ppt = Dispatch.call(ppts, "Open", inputFile, true,// ReadOnly
                            true,// Untitled指定文件是否有标题
                            false// WithWindow指定文件是否可见
                    ).toDispatch();
                    LogUtil.debugLog("转换文档 [" + inputFile + "] 为 [" + pdfFile + "]");
                    Dispatch.call(ppt, "SaveAs", pdfFile, ppFormatPDF);
//					Dispatch.call(ppt, "Close");
                    long end = System.currentTimeMillis();
                    LogUtil.debugLog("用时：" + (end - start) + "ms.");
                }
                return isNull(ppt);
            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.debugLog("========文档转换失败：" + getExceptionMsg(e));
            } finally {
                Dispatch.call(ppt, "Close");
                LogUtil.debugLog("关闭文档");
                if (app != null) {
                    app.invoke("Quit", new Variant[]{});
                }
                ComThread.Release();
                ComThread.quitMainSTA();
            }
        }
        return false;
    }

    /**
     * Excel文档转换
     *
     * @param inputFile
     * @param pdfFile
     * @author SHANHY
     */
    private boolean excel2PDF(String inputFile, String pdfFile) {
        ComThread.InitSTA(true);
        long start = System.currentTimeMillis();
        ActiveXComponent app = null;
        Dispatch excel = null;
        if (app == null || app.m_pDispatch == 0) {

            try {
                app = new ActiveXComponent("Excel.Application");// 创建一个PPT对象
                app.setProperty("Visible", new Variant(false)); // 不可见打开
                app.setProperty("AutomationSecurity", new Variant(3)); // 禁用宏
                if (excel == null || excel.m_pDispatch == 0) {
                    Dispatch excels = app.getProperty("Workbooks").toDispatch();// 获取文挡属性
                    LogUtil.debugLog("打开文档 >>> " + inputFile);
                    // 调用Documents对象中Open方法打开文档，并返回打开的文档对象Document
                    excel = Dispatch.call(excels, "Open", inputFile, false, true)
                            .toDispatch();
                    // 调用Document对象方法，将文档保存为pdf格式
                    LogUtil.debugLog("转换文档 [" + inputFile + "] >>> [" + pdfFile + "]");
                    // Excel 不能调用SaveAs方法
                    Dispatch.call(excel, "ExportAsFixedFormat", xlFormatPDF, pdfFile);
                    long end = System.currentTimeMillis();
                    LogUtil.debugLog("用时：" + (end - start) + "ms.");
                }
                return isNull(excel);
            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.debugLog("========Error:文档转换失败：" + getExceptionMsg(e));
            } finally {
                Dispatch.call(excel, "Close", false);
                LogUtil.debugLog("关闭文档");
                if (app != null) {
                    app.invoke("Quit", new Variant[]{});
                }
                ComThread.Release();
                ComThread.quitMainSTA();
            }
        }
        return false;
    }

    /**
     * 测试
     *
     * @param args
     * @author liulihua
     */
    public static void main(String[] args) {
//		ConvertToPdf d = new ConvertToPdf();
////		d.convert2PDF("d:\\test1.pdf", "d:\\test1.pdf");
//		 d.convert2PDF("d:\\2.doc", "d:\\test2.pdf");
//		 d.convert2PDF("d:\\testexcel.xlsx", "d:\\testexcel.pdf");


        try {
            if("22".equals("")){

            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        } finally {
            aaa();
            System.out.println("333333333");
        }
    }
    private static  void aaa(){
        System.out.println("111111111");
    }


    /**
     * 从异常对象中获取信息，转为String输出
     * @param e
     * @return
     */
    private static String getExceptionMsg(Exception e) {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String strMsg=sw.toString();
            sw.close();
            pw.close();
            return "\r\n" + strMsg + "\r\n";
        } catch (Exception e2) {
            return "ErrorInfoFromException";
        }
    }


}