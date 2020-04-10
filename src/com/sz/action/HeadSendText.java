package com.sz.action;


import com.sz.util.ImageFile;
import com.sz.util.SftpAuthority;
import com.sz.util.SftpServiceImpl;
import com.sz.util.Unzip;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

import java.io.File;
import java.util.HashMap;

/**
 * @description:
 * @author: LZW
 * @create: 2020-03-31 14:50
 **/

public class HeadSendText extends BaseBean implements Action {
    @Override
    public String execute(RequestInfo requestInfo) {
        writeLog("---------------------------总部发文流程开始-----------------------------------------");
        String requestId = requestInfo.getRequestid();// 请求ID
        String workflowId = requestInfo.getWorkflowid();// 流程ID
        String tableName = requestInfo.getRequestManager().getBillTableName();// 表单名称
        String flag = SUCCESS;
        try {
            RecordSet rs0 = new RecordSet();
            //RecordSet rs01 = new RecordSet();
            String sqlSelect = "select zw,xgfj from "
                    + tableName
                    + "  where a.requestId = "
                    + requestId;
            rs0.execute(sqlSelect);
            String fj = "";
            if (rs0.next()) {
                String xgfj = Util.null2String(rs0.getString("xgfj"));
                String zwfj = Util.null2String(rs0.getString("xgfj"));
                if(!"".equals(xgfj)&&!"".equals(zwfj)){
                    fj = rs0.getString("xgfj") + "," + rs0.getString("zw");
                }else if(!"".equals(xgfj)&&"".equals(zwfj)){
                    fj = rs0.getString("xgfj");
                }else if("".equals(xgfj)&&!"".equals(zwfj)){
                    fj = rs0.getString("zw");
                }
            }
            String[] fjs = fj.split(",");
            HashMap<String, ImageFile> map = null;
            //获取相关附件名称和路径
            if (!"".equals(fj)) {
                for (int i = 0; i < fjs.length; i++) {
                    map = selectNamePath(fjs[i]);
                }
            }
            //解压并上传
            unzipAndSend(map, fjs);
        } catch (Exception e) {
            flag = "0";
            requestInfo.getRequestManager().setMessageid("10000");
            requestInfo.getRequestManager().setMessagecontent("系统内部异常" + e);
            return flag;
        }
        return SUCCESS;
    }
    //根据传入的路径对文件进行解压并返回解压后文件的路径
    private Boolean unZip(String filerealpath, String descDir, String name) {
        File zipFile = new File(filerealpath);
        Unzip uz = new Unzip();
        boolean flag = uz.unZip(zipFile, descDir, name);
        writeLog("解压是否成功" + flag);
        return  flag;

    }
    //查询附件和正文的名称以及真实路径
    public HashMap<String, ImageFile> selectNamePath(String docid) {
        String sql = "SELECT imagefilename,filerealpath  FROM imagefile WHERE IMAGEFILEID = ( SELECT IMAGEFILEID FROM docimagefile WHERE docid = '" + docid + "' )";
        RecordSet rs01 = new RecordSet();
        rs01.execute(sql);
        HashMap<String, ImageFile> map = new HashMap<String,ImageFile>();
        if (rs01.next()) {
            ImageFile imageFile = new ImageFile();
            imageFile.setImagefilename(Util.null2String(rs01.getString("imagefilename")));
            imageFile.setPath(Util.null2String(rs01.getString("filerealpath")));
            map.put(docid, imageFile);
        }
        return map;
    }

    //实现解压和上传功能
    public void unzipAndSend(HashMap<String, ImageFile> map, String[] fjs) {
        Boolean flag = true;
        Unzip uz = new Unzip();
        //解压文件
        String descDir = "/oa/weaver/ecology/filesystem/unzip/" + fjs[0] + "/";  //解压后文件存放地址
        File a = new File(descDir);
        for (int j = 0; j < fjs.length; j++) {
            // String[] nameAndPath = map.get(fjs[j]).split("&");
            flag = unZip(map.get(fjs[j]).getPath(), descDir, map.get(fjs[j]).getImagefilename());//解压
            if(!flag){
                uz.deleteFile(a);
                throw  new RuntimeException("文件解压失败");
            }
        }
//        //上传文件
//        FTPClient ftpClient = FTPUtil.connectFtpServer("47.93.241.112", 21, "root", "Weaver12345678sz!", "gbk");
//        File descDir1  = new File(descDir);
//        FTPUtil.uploadFiles(ftpClient,descDir1,descDir);
//        FTPUtil.closeFTPConnect(ftpClient);
        SftpServiceImpl sftpService = new SftpServiceImpl();

        SftpAuthority sftpAuthority = new SftpAuthority("root", "47.93.241.112", 22);

        sftpAuthority.setPassword("Weaver12345678sz!");

        sftpService.createChannel(sftpAuthority);
        String path = "/oa/file/";
        sftpService.uploadFile(sftpAuthority, descDir, path);

        //sftpService.removeFile(sftpAuthority, "/test/2.jpg");
        sftpService.closeChannel();
        //File a = new File(descDir);
        uz.deleteFile(a);
    }
}
