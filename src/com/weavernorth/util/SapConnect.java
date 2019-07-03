package com.weavernorth.util;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import com.sap.mw.jco.JCO;
import weaver.general.GCONST;






public class SapConnect
{
	  private static String client = "800";
	  private static String user = "RFCOA02";
	  private static String passwd = "sstpc.1234";
	  private static String lang = "ZH";
	  private static String ashost = "10.102.176.171";
	  private static String sysnr = "01";

  private static JCO.Client mConnection = null;
  Properties prop = new Properties();

  private void getProp()
  {
    FileInputStream fis = null;
    try {
      fis = new FileInputStream(GCONST.getPropertyPath() + 
        "e-cologySAP.properties");
      this.prop.load(fis);
    } catch (Exception e) {
      e.printStackTrace();

      if (fis != null)
        try {
          fis.close();
        } catch (IOException ex) {
          ex.printStackTrace();
        }
    }
    finally
    {
      if (fis != null) {
        try {
          fis.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    user = this.prop.getProperty("jco.client.user");
    client = this.prop.getProperty("jco.client.client");
    passwd = this.prop.getProperty("jco.client.passwd");
    lang = this.prop.getProperty("jco.client.lang");
    ashost = this.prop.getProperty("jco.client.ashost");
    sysnr = this.prop.getProperty("jco.client.sysnr");
  }

  private void init()
  {
    try
    {
      mConnection = JCO.createClient(client, user, passwd, lang, ashost, 
        sysnr);

      mConnection.connect();
    }
    catch (Exception localException)
    {
    }
  }

  public static JCO.Client Conn()
  {
    try
    {
      mConnection = JCO.createClient(client, user, passwd, lang, ashost, 
        sysnr);
      mConnection.connect();
      return mConnection;
    }
    catch (Exception localException) {
    }
    return null;
  }

  public JCO.Client getConnection()
  {
    if (mConnection == null) {
      init();
    }
    return mConnection;
  }

  public void setConnection(JCO.Client conn) {
    mConnection = conn;
  }
  public void disConnection() {
    mConnection.disconnect();
    mConnection = null;
  }

	private JCO.Function function;
	public JCO.Table getTable(String tableName){
		return this.function.getTableParameterList().getTable(tableName);
	}
}
