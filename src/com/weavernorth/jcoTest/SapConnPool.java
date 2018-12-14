package com.weavernorth.jcoTest;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.ext.DestinationDataProvider;
import com.sap.conn.jco.ext.Environment;

import java.util.Properties;

public class SapConnPool {

    private static DestinationDataProviderImpl destinationDataProvider = DestinationDataProviderImpl.getInstance();

    static {
        Properties properties = new Properties();
        properties.setProperty(DestinationDataProvider.JCO_ASHOST, "192.168.111.123");//服务器的主机地址
        properties.setProperty(DestinationDataProvider.JCO_SYSNR, "00");//系统
        properties.setProperty(DestinationDataProvider.JCO_CLIENT, "800");//客户端
        properties.setProperty(DestinationDataProvider.JCO_USER, "SAPECC");//用户名
        properties.setProperty(DestinationDataProvider.JCO_PASSWD, "sapecc60");//密码
        properties.setProperty(DestinationDataProvider.JCO_LANG, "EN");//语言
        // *********连接池方式与直接不同的是设置了下面两个连接属性
        // JCO_PEAK_LIMIT - 同时可创建的最大活动连接数，0表示无限制，默认为JCO_POOL_CAPACITY的值
        // 如果小于JCO_POOL_CAPACITY的值，则自动设置为该值，在没有设置JCO_POOL_CAPACITY的情况下为0
        properties.setProperty(DestinationDataProvider.JCO_PEAK_LIMIT, "10");
        // JCO_POOL_CAPACITY - 空闲连接数，如果为0，则没有连接池效果，默认为1
        properties.setProperty(DestinationDataProvider.JCO_POOL_CAPACITY, "5");
        // Add a destination
        destinationDataProvider.addDestination("pool", properties);
        Environment.registerDestinationDataProvider(destinationDataProvider);
    }

    public static JCoDestination getJCoDestination(String poolName) throws JCoException {
        return JCoDestinationManager.getDestination(poolName);
    }
}
