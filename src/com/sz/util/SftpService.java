package com.sz.util;

/**
 * @description:
 * @author: LZW
 * @create: 2020-03-28 12:32
 **/

public interface SftpService {
    void createChannel(SftpAuthority sftpAuthority);

    void closeChannel();

    boolean uploadFile(SftpAuthority sftpAuthority, String src, String dst);

    boolean removeFile(SftpAuthority sftpAuthority, String dst);
}
