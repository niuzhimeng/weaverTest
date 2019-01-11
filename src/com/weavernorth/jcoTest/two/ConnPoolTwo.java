package com.weavernorth.jcoTest.two;

import com.sap.mw.jco.JCO;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;

import java.util.Properties;

public class ConnPoolTwo extends BaseBean {

	private JCO.Client connection = null;
	private Properties prop = new Properties();

	private final static int MAX_CONNECTION = 5;
	private final static String POOL_NAME = "qjThePool";

	/**
	 * 创建连接池
	 */
	private void init() {
		try {
			JCO.Pool pool = JCO.getClientPoolManager().getPool(POOL_NAME);
			if (pool == null) {
				// 服务器的主机名
				String ashost = "10.1.199.10";
				prop.put("jco.client.ashost", ashost);
				// 客户端 测试300 正式800
				String client = "300";
				prop.put("jco.client.client", client);
				// 系统
				String sysnr = "00";
				prop.put("jco.client.sysnr", sysnr);
				// 用户名
				String user = "OARFC";
				prop.put("jco.client.user", user);
				// 密码19920706
				String passwd = "Aa123456";
				prop.put("jco.client.passwd", passwd);
				// SapRouter /H/saproute.shunxinholdings.com/H/
				String sapRouter = "";
				prop.put("jco.client.sapRouter", sapRouter);
				// 语言
				String lang = "ZH";
				prop.put("jco.client.lang", lang);
				JCO.addClientPool(POOL_NAME, MAX_CONNECTION, prop);
			}
		} catch (Exception e) {
			this.writeLog("建立sap连接池异常：" + e);
		}
	}

	public JCO.Client getConnection() {
		JCO.Pool pool = JCO.getClientPoolManager().getPool(POOL_NAME);
		if (pool == null) {
			init();
		}
		connection = JCO.getClient(POOL_NAME);
		return connection;
	}

	public void disConnection() {
		JCO.releaseClient(connection);
		connection = null;
	}

	public static void removeConnectionPool() {
		JCO.removeClientPool(POOL_NAME);
	}

}
