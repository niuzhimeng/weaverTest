package com.weavernorth.caibai.sap;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.ext.DestinationDataProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * 菜百 sap连接池
 */
public class CaiBaiPoolThree {

    private static final String CONN_NAME = "CAI_BAI_POOL";

    public static JCoDestination getJCoDestination() throws JCoException {

        Properties properties = new Properties();
        // IP
        properties.setProperty(DestinationDataProvider.JCO_ASHOST, "192.168.30.20");
        // 系统编号
        properties.setProperty(DestinationDataProvider.JCO_SYSNR, "00");
        // 客户端编号
        properties.setProperty(DestinationDataProvider.JCO_CLIENT, "103");
        // 用户名
        properties.setProperty(DestinationDataProvider.JCO_USER, "OA_RFC");
        // 密码
        properties.setProperty(DestinationDataProvider.JCO_PASSWD, "QOARFC1020");
        // 语言
        properties.setProperty(DestinationDataProvider.JCO_LANG, "zh");

        // JCO_PEAK_LIMIT - 同时可创建的最大活动连接数，0表示无限制，默认为JCO_POOL_CAPACITY的值
        // 如果小于JCO_POOL_CAPACITY的值，则自动设置为该值，在没有设置JCO_POOL_CAPACITY的情况下为0
        // 最大活动连接数
        properties.setProperty(DestinationDataProvider.JCO_PEAK_LIMIT, "10");
        // 最大空闲连接数
        properties.setProperty(DestinationDataProvider.JCO_POOL_CAPACITY, "5");
        createDestinationDataFile(properties);

        return JCoDestinationManager.getDestination(CONN_NAME);
    }

    /**
     * 创建连信息接配置文件
     */
    private static void createDestinationDataFile(Properties properties) {
        File destCfg = new File(CaiBaiPoolThree.CONN_NAME + ".jcoDestination");
        try {
            if (!destCfg.exists()) {
                FileOutputStream fos = new FileOutputStream(destCfg, false);
                properties.store(fos, "MY test jco3");
                fos.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("Unable to create the destination files", e);
        }
    }
}
