package com.weavernorth.ftp;

import com.weavernorth.OA2archives.util.DocUtil;
import com.weavernorth.util.LogUtil;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


/**
 * 封装FTP功能函数
 * @author Dylan
 *
 */
public class FtpUtil {
	
	
	
//    private static FTPClient ftpClient = new FTPClient();
	  private static String encoding = System.getProperty("file.encoding");
//    /**
//     * 向FTP服务器上传文件
//     * @param kcf	连接实体类
//     * @param filename	文件名
//     * @param input		读取文件流
//     * @return
//     */
//    public static boolean uploadFile(KmConfig kcf, String filename, InputStream input) {
//        boolean result = false;
//        
//        try {
//        	ftpClient.enterLocalPassiveMode();
//            int reply;
//            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
//            ftpClient.connect(kcf.getFtpHost(),kcf.getFtpPort());
//            //ftpClient.connect(url, port);// 连接FTP服务器
//            // 登录
//            ftpClient.login(kcf.getFtpUser(), kcf.getFtpPassword());
//            ftpClient.setControlEncoding(encoding);
//            // 检验是否连接成功
//            reply = ftpClient.getReplyCode();
//            LogUtil.doWriteLog(reply + "");
//            if (!FTPReply.isPositiveCompletion(reply)) {
//                LogUtil.doWriteLog("连接失败");
//                ftpClient.disconnect();
//                return result;
//            }
//
//            // 转移工作目录至指定目录下
//           // UUID uuid = UUID.randomUUID();
//   	     
//            boolean change = ftpClient.changeWorkingDirectory("/");
//            LogUtil.doWriteLog(change + "");
//            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//            if (change) {
//                result = ftpClient.storeFile(new String(filename.getBytes(encoding),"iso-8859-1"), input);
//                LogUtil.doWriteLog(result+"");
//                if (result) {
//                    LogUtil.doWriteLog("上传成功!");
//                }
//            }
//            input.close();
//            ftpClient.logout();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (ftpClient.isConnected()) {
//                try {
//                    ftpClient.disconnect();
//                } catch (IOException ioe) {
//                }
//            }
//        }
//        return result;
//    }
//    
    /**
     * 判断文件夹是否存在,不存在就创建     
     * @param file
     */
    public static void judeDirExists(File file) {
 
        if (file.exists()) {
           if (file.isDirectory()) {
                 LogUtil.doWriteLog("dir exists");
             } else {
                 LogUtil.doWriteLog("the same name file exists, can not create dir");
             }
         } else {
             LogUtil.doWriteLog("dir not exists, create it ...");
             file.mkdir();
         }
 
     }
	
    
	       
	    private FTPClient ftp;
	    /**  
	     *   
	     * @param path 上传到ftp服务器哪个路径下     
	     * @param addr 地址  
	     * @param port 端口号  
	     * @param username 用户名  
	     * @param password 密码  
	     * @return  
	     * @throws Exception  
	     */    
	    @SuppressWarnings("unused")
		public  boolean connect(String path,String addr,int port,String username,String password) throws Exception {      
	        boolean result = false;      
	        ftp = new FTPClient();
	        int reply;      
	        ftp.connect(addr,port);      
	        ftp.login(username,password);      
	        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
	        reply = ftp.getReplyCode();      
	        if (!FTPReply.isPositiveCompletion(reply)) {
	            ftp.disconnect();      
	            return result;      
	        }      
	        ftp.changeWorkingDirectory(path);      
	        result = true;      
	        return result;      
	    }      
	    /**  
	     *   
	     * @param file 上传的文件或文件夹  
	     * @throws Exception  
	     */    
	    public void upload(File file) throws Exception{
	        if(file.isDirectory()){           
	            ftp.makeDirectory(new String(file.getName().getBytes(encoding),"iso-8859-1"));                
	            boolean flag = ftp.changeWorkingDirectory(new String(file.getName().getBytes(encoding),"iso-8859-1"));
	            //LogUtil.doWriteLog("  --file.getName()---" + file.getName());
	            //LogUtil.doWriteLog("  --flag---" + flag);
	            String[] files = file.list();             
	            for (int i = 0; i < files.length; i++) {      
	                File file1 = new File(file.getPath()+"\\"+files[i] );      
	                if(file1.isDirectory()){      
	                    upload(file1);      
	                    ftp.changeToParentDirectory();      
	                }else{
	                	//LogUtil.doWriteLog("文件名" + file.getPath()+"\\"+files[i]);
	                    File file2 = new File(file.getPath()+"\\"+files[i]);      
	                    FileInputStream input = new FileInputStream(file2);      
	                    //LogUtil.doWriteLog("文件名name" + file2.getName());
						boolean b = ftp.storeFile(new String(file2.getName().getBytes(encoding), "iso-8859-1"), input);



						input.close();
	                }                 
	            }      
	        }else{
	            File file2 = new File(file.getPath());
	            FileInputStream input = new FileInputStream(file2);      
	            ftp.storeFile(new String(file2.getName().getBytes(encoding),"iso-8859-1"), input);      
	            input.close();        
	        }      
	    }
	/**
	 *
	 *
	 * @param fo
	 * @param uploadpath
	 * @param file 上传excel的文件或文件夹
	 * @throws Exception
	 */
	public String uploadExcel(FtpObject fo, String uploadpath, File file) throws Exception{
		String returnStatus = "";
		FtpUtil_zz f = new FtpUtil_zz(true);
		if(file.isDirectory()){
			ftp.makeDirectory(new String(file.getName().getBytes(encoding),"iso-8859-1"));
			boolean flag = ftp.changeWorkingDirectory(new String(file.getName().getBytes(encoding),"iso-8859-1"));
			//LogUtil.doWriteLog("  --file.getName()---" + file.getName());
			//LogUtil.doWriteLog("  --flag---" + flag);
			String[] files = file.list();
			for (int i = 0; i < files.length; i++) {
				File file1 = new File(file.getPath()+"\\"+files[i] );
				if(file1.isDirectory()){
					uploadExcel(fo, uploadpath, file1);
					ftp.changeToParentDirectory();
				}else{
					//LogUtil.doWriteLog("文件名" + file.getPath()+"\\"+files[i]);
					String suffix = getFileSufix(files[i]);
					if("xls".equals(suffix) || "xlsx".equals(suffix)){
						File file2 = new File(file.getPath()+"\\"+files[i]);
						LogUtil.doWriteLog("上传excel文件名" + file.getPath()+"\\"+files[i]);
						//LogUtil.doWriteLog("文件名name" + file2.getName());
						returnStatus = f.uploadOneFile(fo, uploadpath, new File(file2.getPath()),null);

					}

				}
			}
		}else{
			returnStatus = f.uploadOneFile(fo, uploadpath, new File(file.getPath()),null);

		}
		return returnStatus;
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
	 *
	 * @param file 上传的文件或文件夹
	 * @throws Exception
	 */
	public void uploadForArchives(File file,String result) throws Exception{
		if(file.isDirectory()){
			ftp.makeDirectory(new String(file.getName().getBytes(encoding),"iso-8859-1"));
			boolean flag = ftp.changeWorkingDirectory(new String(file.getName().getBytes(encoding),"iso-8859-1"));
			//LogUtil.doWriteLog("  --file.getName()---" + file.getName());
			//LogUtil.doWriteLog("  --flag---" + flag);
			String[] files = file.list();
			for (int i = 0; i < files.length; i++) {
				File file1 = new File(file.getPath()+"\\"+files[i] );
				if(file1.isDirectory()){
					upload(file1);
					ftp.changeToParentDirectory();
					boolean delete = DocUtil.delete(file.getPath() + "\\" + files[i]);
					LogUtil.doWriteLog("=删除是否成功1==>"+delete);
				}else{
					//LogUtil.doWriteLog("文件名" + file.getPath()+"\\"+files[i]);
					File file2 = new File(file.getPath()+"\\"+files[i]);
					FileInputStream input = new FileInputStream(file2);
					//LogUtil.doWriteLog("文件名name" + file2.getName());
					boolean b = ftp.storeFile(new String(file2.getName().getBytes(encoding), "iso-8859-1"), input);
					LogUtil.doWriteLog("=删除上传前的pdf文件==>"+b);
					if(b){
						boolean delete = DocUtil.delete(file.getPath() + "\\" + files[i]);
						LogUtil.doWriteLog("=删除是否成功2==>"+delete);
					}
					LogUtil.doWriteLog("=删除上传后的pdf文件==>"+b);

					input.close();
				}
			}
		}else{
			File file2 = new File(file.getPath());
			FileInputStream input = new FileInputStream(file2);
			ftp.storeFile(new String(file2.getName().getBytes(encoding),"iso-8859-1"), input);
			boolean delete = DocUtil.delete(file.getPath());
			LogUtil.doWriteLog("==删除本地pdf是否成功3==>"+delete);
			input.close();
		}
	}

	    
		/**
		 * 关闭FTP连接
		 * @throws IOException
		 */
		public void disConnection() throws IOException{
			//检验ftp是否连接---如果连接关闭连接
			if(this.ftp.isConnected()){
				this.ftp.disconnect(); 
			} 
		}
   
	  


}
