package com.sz.util;

import com.jcraft.jsch.*;
import weaver.general.BaseBean;

import java.io.File;
import java.util.Properties;

/**
 * @description:
 * @author: LZW
 * @create: 2020-03-28 12:35
 **/

public class SftpServiceImpl extends BaseBean implements SftpService {
    private Session session;
    private Channel channel;
    private ChannelSftp channelSftp;

    @Override
    public void createChannel(SftpAuthority sftpAuthority) {
        try {
            JSch jSch = new JSch();
            session = jSch.getSession(sftpAuthority.getUser(), sftpAuthority.getHost(), sftpAuthority.getPort());

            if (sftpAuthority.getPassword() != null) {
                // 使用用户名密码创建SSH
                writeLog("使用用户名密码创建SSH");
                session.setPassword(sftpAuthority.getPassword());

            }

            Properties properties = new Properties();
            properties.put("StrictHostKeyChecking", "no");  // 主动接收ECDSA key fingerprint，不进行HostKeyChecking
            session.setConfig(properties);
            //session.setTimeout(0);  // 设置超时时间为无穷大
            writeLog("通过session建立连接");
            session.connect(); // 通过session建立连接
            writeLog("打开SFTP通道");
            channel = session.openChannel("sftp"); // 打开SFTP通道
            channel.connect();
            channelSftp = (ChannelSftp) channel;

        } catch (JSchException e) {

            writeLog("create sftp channel failed!"+e);


        }
    }

    @Override
    public void closeChannel() {
        if (channel != null) {
            channel.disconnect();

        }

        if (session != null) {
            session.disconnect();

        }
    }

    @Override
    public boolean uploadFile(SftpAuthority sftpAuthority, String src, String dst) {
        File file = new File(src);
        if (!file.exists()) {
            writeLog(">>>>>待上传文件为空或者文件不存在*****放弃文件上传****");
            return false;
        }
        //BaseBean bs = new BaseBean();
        if (channelSftp == null) {

            writeLog("need create channelSftp before upload file");
            return false;

        }

        if (channelSftp.isClosed()) {
            createChannel(sftpAuthority); // 如果被关闭则应重新创建

        }

        try {
            try {
                channelSftp.cd(dst);
            } catch (Exception e) {
                channelSftp.mkdir(dst);
            }
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                for(File f : listFiles){
                    String fp =f.getAbsolutePath();
                   if(f.isDirectory()){
                       String mkdir = dst + "/" + f.getName();
                       try {
                           channelSftp.cd(mkdir);
                       } catch (Exception e) {
                           channelSftp.mkdir(mkdir);
                       }
                       uploadFile(sftpAuthority,fp,dst);
                   }else{
                       channelSftp.put(fp, dst, ChannelSftp.OVERWRITE);
                   }
                }
            }else{
                channelSftp.put(src, dst, ChannelSftp.OVERWRITE);
                writeLog("sftp upload file success! src: {" + src + "}, dst: {" + dst + "}");
            }

            return true;

        } catch (SftpException e) {
            writeLog("sftp upload file failed! src: {" + src + "}, dst: {" + dst + "}" + e);
            return false;

        }


    }

    @Override
    public boolean removeFile(SftpAuthority sftpAuthority, String dst) {

       // BaseBean bs = new BaseBean();
        if (channelSftp == null) {
            writeLog("need create channelSftp before remove file");
            return false;
        }

        if (channelSftp.isClosed()) {
            createChannel(sftpAuthority); // 如果被关闭则应重新创建
        }

        try {
            channelSftp.rm(dst);
            writeLog("sftp remove file success! dst: {" + dst + "}");
            return true;
        } catch (SftpException e) {
            writeLog("sftp remove file failed! dst: {" + dst + "}" + e);
            return false;
        }
    }


}
