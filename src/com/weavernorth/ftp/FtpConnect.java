package com.weavernorth.ftp;

import com.weavernorth.ftp.entity.KmConfig;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import weaver.general.BaseBean;

import java.io.IOException;

/**
 * Ftp连接器
 * @author Dylan
 *
 */
public class FtpConnect {

	private FTPClient ftp;

	private BaseBean bb = new BaseBean();
	
	public FtpConnect(){
		ftp = new FTPClient();

	}
    
	/**
	 * FTP 连接登录
	 * @param host	地址
	 * @param port	端口
	 * @param username	用户名
	 * @param password	密码
	 * @return
	 * @throws IOException
	 */
	public boolean login(KmConfig kcf) throws IOException{
		this.ftp.connect(kcf.getFtpHost(),kcf.getFtpPort());
		if(FTPReply.isPositiveCompletion(this.ftp.getReplyCode())&&this.ftp.login(kcf.getFtpUser(),kcf.getFtpPassword())){

				//设置编码格式
				this.ftp.setControlEncoding(bb.getPropValue("ftpreport", "ftpEncoding"));
				return true; 

		}else {
			this.ftp.isConnected();
			return false;
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
