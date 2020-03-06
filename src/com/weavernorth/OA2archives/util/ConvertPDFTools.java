package com.weavernorth.OA2archives.util;

import DBstep.iMsgServer2000;
import com.lowagie.text.pdf.PdfReader;
import com.weavernorth.util.LogUtil;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import weaver.common.StringUtil;
import weaver.file.ImageFileManager;
import weaver.general.BaseBean;
import weaver.general.GCONST;
import weaver.general.Util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 永中dcs服务pdf转换工具类
 *
 * @author jinghy
 */
public class ConvertPDFTools extends BaseBean {


    /**
     * 根据附件id生成pdf
     *
     * @param imagefileid
     * @param pdffile
     * @return
     * @throws IOException
     * @throws JSONException
     */

    public int conertToPdf(String imagefileid, String pdffile) throws IOException, JSONException {
        String filename = this.getImageFile(imagefileid);
        int pdfimagefileid = -1;
        if (filename.isEmpty()) {
            return -1;
        }
        String suffix = getFileSufix(filename);
        if (suffix.equals("pdf")) {
            ImageFileManager imageFileManager = new ImageFileManager();
            imageFileManager.getImageFileInfoById(Util.getIntValue(imagefileid));
            InputStream inputStream = imageFileManager.getInputStream();
            getInputStreamToFile(inputStream, pdffile);
            LogUtil.doWriteLog("PDF not need to convert!");
            return -1;
        }
        String sourceFilePath = GCONST.getRootPath() + "filesystem" + File.separatorChar + "sourceFilePath";
        String targetFilePath = GCONST.getRootPath() + "filesystem" + File.separatorChar + "targetFilePath";
        File file = new File(sourceFilePath);
        if (!file.exists()) {
            file.mkdir();
        }

        file = new File(targetFilePath);
        if (!file.exists()) {
            file.mkdir();
        }

        String targetFileName = filename.substring(0, filename.lastIndexOf(".")) + ".pdf";
        BaseBean bs = new BaseBean();
        String dcsresult = "";
        String dcsUrl = "";
        JSONObject json = null;

        String docdcsurl = Util.null2String(bs.getPropValue("docpreview", "docdcsurl"));
        dcsUrl = submitToDCS(docdcsurl, sourceFilePath + "/" + filename, "3");
        bs.writeLog("ConvertPDFTools--imagefileid=" + imagefileid);
        bs.writeLog("ConvertPDFTools--docdcsurl=" + docdcsurl);
        bs.writeLog("ConvertPDFTools--dcsUrl=" + dcsUrl);
        json = new JSONObject(dcsUrl);
        JSONArray data = json.getJSONArray("data");
        dcsresult = json.getString("result");
        dcsUrl = data.get(0).toString();
        InputStream inputStream = getInputStreamFromDcs(dcsUrl);
        getInputStreamToFile(inputStream, pdffile);
        int result = getPageYs(pdffile);
        if(result <=0 ){
            bs.writeLog("ConvertPDFTools--如果页数小于0 在转一次"+dcsUrl);
            InputStream inputStream1 = getInputStreamFromDcs(dcsUrl);
            getInputStreamToFile(inputStream1, pdffile);
        }

        File targetFile = new File(targetFilePath + File.separatorChar + targetFileName);
        if (targetFile.exists()) {
            targetFile.delete();
        }
        File sourceFile = new File(sourceFilePath + File.separatorChar + filename);
        if (sourceFile.exists()) {
            sourceFile.delete();
        }
        return pdfimagefileid;
    }

    private String getImageFile(String imagefileid) {
        InputStream imagefile = null;
        FileOutputStream fos = null;
        try {
            ImageFileManager imageFileManager = new ImageFileManager();
            imageFileManager.getImageFileInfoById(Util.getIntValue(imagefileid));
            imagefile = imageFileManager.getInputStream();
            String filename = imageFileManager.getImageFileName();
            String extName = "";
            if (filename.indexOf(".") > -1) {
                int bx = filename.lastIndexOf(".");
                if (bx >= 0) {
                    extName = filename.substring(bx + 1, filename.length());
                }
            }
            if (isOfficeToDocument(extName) && isMsgObjToDocument()) {
                int byteread;
                byte data[] = new byte[1024];
                //正文的处理
                ByteArrayOutputStream bout = null;
                try {
                    bout = new ByteArrayOutputStream();
                    while ((byteread = imagefile.read(data)) != -1) {
                        bout.write(data, 0, byteread);
                        bout.flush();
                    }
                    byte[] fileBody = bout.toByteArray();
                    iMsgServer2000 MsgObj = new iMsgServer2000();
                    MsgObj.MsgFileBody(fileBody);            //将文件信息打包
                    fileBody = MsgObj.ToDocument(MsgObj.MsgFileBody());    //通过iMsgServer200 将pgf文件流转化为普通Office文件流
                    imagefile = new ByteArrayInputStream(fileBody);
                    bout.close();
                } catch (Exception e) {
                    if (bout != null) bout.close();
                }
            }
            filename = UUID.randomUUID() + filename.substring(filename.lastIndexOf("."));
            String sourceFilePath = GCONST.getRootPath() + "filesystem" + File.separatorChar + "sourceFilePath";
            File file = new File(sourceFilePath);
            if (!file.exists()) {
                file.mkdir();
            }
            fos = new FileOutputStream(sourceFilePath + File.separatorChar + filename);
            int n = 0;
            byte[] b = new byte[1024];
            while ((n = imagefile.read(b)) > 0) {
                fos.write(b, 0, n);
            }
            return filename;

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (imagefile != null) {
                try {
                    imagefile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
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


    private int savePdfImageFile(String sourceFilePath, String fileName) {
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;
        try {
            File file = new File(sourceFilePath);
            fis = new FileInputStream(file);
            bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            ImageFileManager imageFileManager = new ImageFileManager();
            imageFileManager.resetParameter();
            imageFileManager.setImagFileName(fileName);
            imageFileManager.setComefrom("pdfconvert");
            imageFileManager.setData(bos.toByteArray());
            return imageFileManager.saveImageFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return -1;
    }

    private boolean isMsgObjToDocument() {
        boolean isMsgObjToDocument = true;
        BaseBean basebean = new BaseBean();
        String mClientName = Util.null2String(basebean.getPropValue("weaver_obj", "iWebOfficeClientName"));
        boolean isIWebOffice2003 = (mClientName.indexOf("iWebOffice2003") > -1) ? true : false;
        String isHandWriteForIWebOffice2009 = Util.null2String(basebean.getPropValue("weaver_obj", "isHandWriteForIWebOffice2009"));
        if (isIWebOffice2003 || isHandWriteForIWebOffice2009.equals("0")) {
            isMsgObjToDocument = false;
        }

        return isMsgObjToDocument;
    }

    private boolean isOfficeToDocument(String extName) {
        boolean isOfficeForToDocument = false;
        if ("xls".equalsIgnoreCase(extName) || "doc".equalsIgnoreCase(extName) || "wps".equalsIgnoreCase(extName) || "ppt".equalsIgnoreCase(extName) || "docx".equalsIgnoreCase(extName) || "xlsx".equalsIgnoreCase(extName) || "pptx".equalsIgnoreCase(extName)) {
            isOfficeForToDocument = true;
        }
        return isOfficeForToDocument;
    }


    /**
     * 永中的转pdf方法
     * @param url
     * @param filepath
     * @param type 3 word转pdf
     * @return
     */
    public static String submitToDCS(String url, String filepath, String type) {
        String requestJson = "";
        HttpClient httpclient = new DefaultHttpClient();
        try {
            HttpPost httppost = new HttpPost(url);
            FileBody file = new FileBody(new File(filepath));
            MultipartEntity reqEntity = new MultipartEntity(
                    HttpMultipartMode.BROWSER_COMPATIBLE, null,
                    Charset.forName("UTF-8"));
            reqEntity.addPart("file", file);
            reqEntity.addPart("targetUrl",
                    new StringBody("1", Charset.forName("UTF-8")));
            reqEntity.addPart("isDelSrc",
                    new StringBody("1", Charset.forName("UTF-8")));
            reqEntity.addPart("acceptTracks",
                    new StringBody("0", Charset.forName("UTF-8")));
            reqEntity.addPart("convertType",
                    new StringBody(type, Charset.forName("UTF-8")));

            httppost.setEntity(reqEntity);
            HttpResponse response = httpclient.execute(httppost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity resEntity = response.getEntity();
                //requestJson = EntityUtils.toString(resEntity);
                byte[] json = EntityUtils.toByteArray(resEntity);
                requestJson = new String(json, "UTF-8");
                LogUtil.doWriteLog("------------转pdf返回值：" + requestJson);
                EntityUtils.consume(resEntity);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.getConnectionManager().shutdown();
            } catch (Exception ignore) {
                ignore.printStackTrace();
            }

        }
        return requestJson;
    }

    /**
     * 从永中dcs服务生产pdf后返回的文件路径拿到文件流，此处如果拿不到需要看看永中的tomcat服务的config/server.xml里面有没有配置<Context docBase="D:\\dcs\\sampledoc\\output" path=""/>
     * @param urlStr
     * @return
     * @throws IOException
     */
    private static InputStream getInputStreamFromDcs(String urlStr) throws IOException {
        InputStream inputStream = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(3 * 1000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            //得到输入流
            inputStream = conn.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    /**
     * 保存pdf文件到系统imagefile表
     * @param fis 源文件流
     * @param fileName 文件名
     * @return 返回pdf文件的imagefileid
     */
    private int savePdfImageFile(InputStream fis, String fileName) {
        ByteArrayOutputStream bos = null;
        try {
            bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            ImageFileManager imageFileManager = new ImageFileManager();
            imageFileManager.resetParameter();
            imageFileManager.setImagFileName(fileName);
            imageFileManager.setComefrom("pdfconvert");
            imageFileManager.setData(bos.toByteArray());
            return imageFileManager.saveImageFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return -1;
    }


    /**
     * 永中合并pdf方法
     *
     * @param serverurl   永中的地址ip+/dcs.web/convert
     * @param param       永中参数 Map中需传递三个必要参数
     *                    {inputDir:"第一个合并的文件路径（放在永中config.xml中input参数的相对路径，只能放一个文件）",
     *                    mergeInput:"其余需要合并的pdf文件，以英文逗号分隔（此参数必须有值，如无值代表只有一个pdf需要合并，那么直接保存文件即可，无需调用永中的合并方法）",
     *                    convertType:31 （合并pdf的type类型）}
     * @param savePath 业务上需要保存的合并后pdf路径
     * @param savePathBak 业务上需要保存的合并后pdf备份路径
     * @return 返回合并后的pdf的页数
     */
    public static int sendPost(String serverurl, Map<String, String> param, String savePath, String savePathBak) {
        int result = 0;
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        String fullurl = "";
        String mergeInput = "";
        //请求参数
        for (String key : param.keySet()) {
            list.add(new BasicNameValuePair(key, param.get(key)));
            mergeInput = param.get("mergeInput");
        }
        try {
            //如果就一个附件需要合并的话直接调用复制文件的方法，return出去
            if (StringUtil.isNull(mergeInput)) {
                String inputDir = param.get("inputDir");
                copyFileUsingFileChannels(new File("E:/filesystem/" + inputDir), new File(savePath));
                copyFileUsingFileChannels(new File(savePath), new File(savePathBak));
                LogUtil.doWriteLog("就一个附件，无需使用永中插件合并");
                return getPageYs(savePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.doWriteLog("就一个附件，无需使用永中插件合并合并pdf异常" + e);
        }

        try {
            fullurl = EntityUtils.toString(new UrlEncodedFormEntity((Iterable<? extends NameValuePair>) list, Consts.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpPost httpget = new HttpPost(serverurl + "?" + fullurl);
        LogUtil.doWriteLog("永中合并附件url：" + serverurl + "?" + fullurl);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            CloseableHttpResponse response = httpclient.execute(httpget);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity resEntity = response.getEntity();
                byte[] josn = EntityUtils.toByteArray(resEntity);
                String requestJson = new String(josn, "UTF-8");
                LogUtil.doWriteLog("------------合并pdf返回result" + requestJson);
                JSONArray data = new JSONObject(requestJson).getJSONArray("data");
                String dcsresult = new JSONObject(requestJson).getString("result");

                if ("0".equals(dcsresult)) {
                    String dcsUrl = data.get(0).toString();
                    InputStream inputStream = getInputStreamFromDcs(dcsUrl);
                    getInputStreamToFile(inputStream, savePath);
                    copyFileUsingFileChannels(new File(savePath), new File(savePathBak));
                    result = getPageYs(savePath);
                }

            } else {
                LogUtil.doWriteLog("永中合并pdf异常" + response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.doWriteLog("永中合并pdf异常" + e);
        } finally {
            if (httpget != null)
                httpget.releaseConnection();
        }
        return result;
    }


    /**
     * 复制文件的方法
     * @param source 源文件
     * @param dest 目标文件
     * @throws IOException
     */
    private static void copyFileUsingFileChannels(File source, File dest) throws IOException {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } catch (Exception e) {
            LogUtil.doWriteLog("====系统文件读取异常===>" + e);
        } finally {
            assert inputChannel != null;
            inputChannel.close();
            assert outputChannel != null;
            outputChannel.close();
        }
    }

    /**
     * 获取pdf文件的页数
     *
     * @param filename 文件的路径
     * @return pdf文件的页数
     */
    public static Integer getPageYs(String filename) {
        PdfReader reader = null;
        int pagecount = 0;
        try {
            reader = new PdfReader(filename);
            pagecount = reader.getNumberOfPages();
        } catch (IOException e) {
            LogUtil.releaseLog("获取pdf页数异常"+e);
            e.printStackTrace();
        }

        return pagecount;
    }

    /**
     * 实际处理文档获取文档方法,将文档解压到压缩的原始目录下
     *
     * @param inputStream 源文件的inputstream流
     * @param file 目标文件
     * @throws Exception
     */
    private static void getInputStreamToFile(InputStream inputStream, String file) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(new File(file));
            //文件拷贝
            byte flush[] = new byte[1024];
            int len = 0;
            while (0 <= (len = inputStream.read(flush))) {
                os.write(flush, 0, len);
            }
            os.flush();
        } catch (Exception e) {
            LogUtil.doWriteLog("====系统文件读取异常===>" + e);
        } finally {
            try {
                //关闭流的注意 先打开的后关
                assert os != null;
                os.close();
                inputStream.close();
                inputStream = null;
            } catch (IOException e) {
                e.printStackTrace();
                LogUtil.doWriteLog("====系统文件读取异常===>" + e);
            }
        }
    }
}
