package com.weavernorth.ftp.entity;

import java.io.Serializable;
/**
 * FTP连接实体类
 * @author Dylan
 *
 */
public class KmConfig implements Serializable{
	
	/**
	 * 序列化化版本号
	 */
	private static final long serialVersionUID = 1L;
	//Ftp 地址
    private String FtpHost;
    //FTP 端口
    private int FtpPort;
    //FTP 用户
    private String FtpUser;
    //FTP 密码
    private String FtpPassword;
    //FTP 路径
    private String FtpPath;
    
	public KmConfig(String ftpHost, int ftpPort, String ftpUser,
			String ftpPassword, String ftpPath) {
		super();
		FtpHost = ftpHost;
		FtpPort = ftpPort;
		FtpUser = ftpUser;
		FtpPassword = ftpPassword;
		FtpPath = ftpPath;
	}
	
	public KmConfig() {
		super();
	}

	public String getFtpHost() {
		return FtpHost;
	}
	public void setFtpHost(String ftpHost) {
		FtpHost = ftpHost;
	}
	public int getFtpPort() {
		return FtpPort;
	}
	public void setFtpPort(int ftpPort) {
		FtpPort = ftpPort;
	}
	public String getFtpUser() {
		return FtpUser;
	}
	public void setFtpUser(String ftpUser) {
		FtpUser = ftpUser;
	}
	public String getFtpPassword() {
		return FtpPassword;
	}
	public void setFtpPassword(String ftpPassword) {
		FtpPassword = ftpPassword;
	}
	public String getFtpPath() {
		return FtpPath;
	}
	public void setFtpPath(String ftpPath) {
		FtpPath = ftpPath;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((FtpHost == null) ? 0 : FtpHost.hashCode());
		result = prime * result
				+ ((FtpPassword == null) ? 0 : FtpPassword.hashCode());
		result = prime * result + ((FtpPath == null) ? 0 : FtpPath.hashCode());
		result = prime * result + FtpPort;
		result = prime * result + ((FtpUser == null) ? 0 : FtpUser.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KmConfig other = (KmConfig) obj;
		if (FtpHost == null) {
			if (other.FtpHost != null)
				return false;
		} else if (!FtpHost.equals(other.FtpHost))
			return false;
		if (FtpPassword == null) {
			if (other.FtpPassword != null)
				return false;
		} else if (!FtpPassword.equals(other.FtpPassword))
			return false;
		if (FtpPath == null) {
			if (other.FtpPath != null)
				return false;
		} else if (!FtpPath.equals(other.FtpPath))
			return false;
		if (FtpPort != other.FtpPort)
			return false;
		if (FtpUser == null) {
			if (other.FtpUser != null)
				return false;
		} else if (!FtpUser.equals(other.FtpUser))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "KmConfig [FtpHost=" + FtpHost + ", FtpPort=" + FtpPort
				+ ", FtpUser=" + FtpUser + ", FtpPassword=" + FtpPassword
				+ ", FtpPath=" + FtpPath + "]";
	}
	
    
}
