package com.weavernorth.workflow.waterword;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComFailException;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.weavernorth.util.LogUtil;
import ln.TimeUtil;

import java.io.File;

public class WaterWord {
	private static WaterWord instance;
	private Dispatch aDoc = null;// 用于存储一个文档：比如新增一个文档时返回，新增的文档
	private Dispatch activeWindow = null;// 当前活动窗口
	private Dispatch docSelection = null;// 存储当前被选中文档
	private Dispatch wordDocs = null;// 存储所有的文档
	private String fileName;
	private ActiveXComponent wordApp = null;// Word对象
	public WaterWord() {
		
	}
	public final static synchronized WaterWord getInstance() {
		if (instance == null) {
			instance = new WaterWord();
		}
		return instance;
	}
	/*** 初始化Word对象 */
	public boolean initWordApp() {
		boolean retFlag = false;
		// 初始化com线程【相当于打开冰箱门，准备放大箱】
		// 使用结束后要调用 realease方法关闭线程【相当于关上冰箱门】
		ComThread.InitSTA();
		if (wordApp == null || wordApp.m_pDispatch == 0) {
			try {
				// 初始化word应用程序，初始化表格是：Excel.Application
				wordApp = new ActiveXComponent("Word.Application");
				// 配置启动word时是显示执行还是隐式执行
				wordApp.setProperty("Visible", new Variant(false));
				wordApp.setProperty("DisplayAlerts", new Variant(false));
				wordApp.setProperty("AutomationSecurity", new Variant(3)); // 禁用宏    
				if (wordDocs == null || wordDocs.m_pDispatch == 0) {
					wordDocs = wordApp.getProperty("Documents").toDispatch();// 获取word所有文档对象
					if(!isNull(wordDocs)){
						retFlag = true;
					}else{
						retFlag = false;
					}
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				// 释放com线程。根据jacob的帮助文档，com的线程回收不由java的垃圾回收器处理
				ComThread.Release();
				ComThread.quitMainSTA(); 
			}
				LogUtil.debugLog("===初始化Word对象==="+retFlag);
		}
		return retFlag;
	}

	/** 打开一个已存在的文档 */
	public void openDocument(String docPath) {
		LogUtil.debugLog("===打开一个已存在的文档==="+docPath);
		if (this.aDoc != null) {
			this.closeDocument();
		}
		// docPath要打开的文档的详细地址
		aDoc = Dispatch.call(wordDocs, "Open", new Variant(docPath)).toDispatch();
		docSelection = Dispatch.get(wordApp, "Selection").toDispatch();// 获得该文档对象，并返回
	}

	/** 取得活动窗体对象 */
	public void getActiveWindow() {
		LogUtil.debugLog("===取得活动窗体对象===");
		activeWindow = wordApp.getProperty("ActiveWindow").toDispatch();// 取得活动窗体对象
	}

	/*** 创建一个新的word文档 */
	public void createNewDocument() {
		LogUtil.debugLog("===创建一个新的word文档===");
		aDoc = Dispatch.call(wordDocs, "Add").toDispatch();// 创建一个新的word文档,并返回
		docSelection = Dispatch.get(wordApp, "Selection").toDispatch();// 获得该文档对象，并返回
	}

	/*** 保存并关闭当前word文档 */
	public void closeDocument() {
		LogUtil.debugLog("===保存并关闭当前word文档===");
		if (aDoc != null) {
			Dispatch.call(aDoc, "Save");// 保存
			Dispatch.call(aDoc, "Close", new Variant(true));//关闭
			aDoc = null;
		}
	}

	/*** 关闭Word资源 */
	public void closeWordObj() {
		LogUtil.debugLog("===关闭Word资源===");
		wordApp.invoke("Quit", new Variant[] {});// 关闭word文件
		wordApp.safeRelease();
		ComThread.Release();// 释放com线程。根据jacob的帮助文档，com的线程回收不由java的垃圾回收器处理
		ComThread.quitMainSTA(); 
	}

	

	/** 文档设置图片水印,waterPic水印图片路径 
	 * @throws Exception */
	public void setWaterPic(String waterPic) throws Exception {
			// 活动窗格
			Dispatch activePane = Dispatch.get(activeWindow, "ActivePane").toDispatch();
			// 视窗对象
			Dispatch view = Dispatch.get(activePane, "View").toDispatch();
			// 打开页眉，值为9，页脚值为10
			Dispatch.put(view, "SeekView", new Variant(9));
			// 获取页眉和页脚
			Dispatch headerfooter = Dispatch.get(docSelection, "HeaderFooter").toDispatch();
			// 获取水印图形对象
			Dispatch shapes = Dispatch.get(headerfooter, "Shapes").toDispatch();
			// 调用shapes对象的AddPicture方法将图片插入当前文档
			Dispatch picture = Dispatch.call(shapes, "AddPicture", waterPic).toDispatch();
			// 选择当前word文档的图片水印
			Dispatch.call(picture, "Select");
			// 设置图片水印参数
			Dispatch.put(picture, "Left", new Variant(20));
			Dispatch.put(picture, "Top", new Variant(240));
			// 调整大小时保持其长宽比例不变
			Dispatch.put(picture, "LockAspectRatio", new Boolean(true));
			Dispatch.put(picture, "Width", new Variant(360));
			Dispatch.put(picture, "Height", new Variant(270));
			// 关闭页眉，0表示恢复视图;
			Dispatch.put(view, "SeekView", new Variant(0));
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 给文档加上保护
	 * 
	 * 说明：
	 * 
	 * @param pswd
	 * @throws Exception
	 *             创建时间：2011-6-4 下午07:33:44
	 */
	public void setProtected(String pswd) throws Exception {
		String protectionType = Dispatch.get(aDoc, "ProtectionType").toString();
		if (protectionType.equals("-1")) {
			Dispatch.call(aDoc, "Protect", new Variant(3), new Variant(true),
					pswd);
		}
	}

	public boolean wordAddWater(String filePath, String imgPath) throws Exception {
		LogUtil.debugLog("==进入加水印==="+TimeUtil.getCurrentTimeString());
		WaterWord d = WaterWord.getInstance();
		Boolean result = false;
		try {
			if (d.initWordApp()) {
				d.openDocument(filePath);
				d.getActiveWindow();
				d.setWaterPic(imgPath);// 页中图片
				d.setProtected("sstpc2018");
				d.closeDocument();
				result = true;
			} else {
				LogUtil.debugLog("初始化Word读写对象失败！");
				result = false;
			}
		} catch (Exception e) {
			LogUtil.doWriteLog("==word加水印异常===>" + e); 
			e.printStackTrace();
			result = true;
		} finally {
			d.closeWordObj();
		}
		LogUtil.debugLog("==加水印结束===" + result+"   "+TimeUtil.getCurrentTimeString());
		return result;
	}
	public static boolean isNull(Dispatch d)
	{
	  try {
	    Dispatch.call(d, "");
	  }
	  catch (IllegalStateException ise) {
	    return true;
	  }
	  catch (ComFailException cfe) {
	    // that's ok, we didn't expect this call to succeed
	  }
	  return false;
	}
	// 测试功能
	public static void main(String[] argv) {
		WaterWord d = WaterWord.getInstance();
		try {
			if (d.initWordApp()) {
				d.openDocument("F:" + File.separator + "2.docx");
				d.getActiveWindow();
				String imgPath = "F:" + File.separator + "WaterMark.png";
				d.setWaterPic(imgPath);// 页中图片
				d.setProtected("cms123");
				d.closeDocument();
			} else {
				LogUtil.debugLog("初始化Word读写对象失败！");
			}
		} catch (Exception e) {
			LogUtil.debugLog("==word加水印异常===>" + e);
			e.printStackTrace();
		} finally {
			d.closeWordObj();
		}
	}
}