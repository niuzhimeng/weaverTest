package com.weavernorth.workflow.waterpdf;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.*;
import com.weavernorth.util.LogUtil;
import weaver.general.TimeUtil;

import java.awt.*;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class WaterPDF {
	public static void main(String[] args) throws Exception {
		 
		addPdfMark("F:/1.pdf", "F:/"+UUID.randomUUID()+".pdf", "F:/WaterMark.png", 0);
	}

	/**
	 * 给pdf文件添加水印
	 * 
	 * @param InPdfFile
	 *            要加水印的原pdf文件路径
	 * @param outPdfFile
	 *            加了水印后要输出的路径
	 * @param markImagePath
	 *            水印图片路径
	 * @param pageSize
	 *            原pdf文件的总页数（该方法是我当初将数据导入excel中然后再转换成pdf所以我这里的值是用excel的行数计算出来的，
	 *            如果不是我这种可以 直接用reader.getNumberOfPages()获取pdf的总页数）
	 * @throws Exception
	 */
	public static void addPdfMark(String InPdfFile, String outPdfFile,
			String markImagePath, int pageSize) throws Exception {
		LogUtil.debugLog("========进入pdf加水印开始==========>"+TimeUtil.getCurrentTimeString()+"=================================");
		LogUtil.debugLog("======pdf输入路径========>"+InPdfFile);
		LogUtil.debugLog("======pdf输出路径========>"+outPdfFile);
		PdfReader reader = new PdfReader(InPdfFile);
		LogUtil.debugLog("======PdfReader后========>");
		PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(outPdfFile));
		LogUtil.debugLog("======PdfStamper后========>"+outPdfFile);
		BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.EMBEDDED);
		///D:/WEAVER/ecology/weavernorth/workflow/WaterMark.png
		Image img = Image.getInstance("D:/WEAVER/ecology/weavernorth/workflow/WaterMark.png");// 插入水印
		//Image img = Image.getInstance("F:/WaterMark.png");// 插入水印
		LogUtil.debugLog("======开始添加水印========>"+outPdfFile);
		//图片插入位置
		img.setAbsolutePosition(90, 250);
		// img.setAbsolutePosition(150, 100);
		img.scaleToFit(430,400);
		PdfGState gs = new PdfGState();
		for (int i = 1; i <= reader.getNumberOfPages(); i++) {

			PdfContentByte under = stamp.getOverContent(i);
			//设置水印图片透明度
			gs.setFillOpacity(0.4f);
			under.setGState(gs);
			//under.restoreState();
			under.setColorFill(Color.BLACK);
			under.setFontAndSize(base, 16);
			under.addImage(img);

		}
		
		stamp.close();// 关闭
		reader.close();
		LogUtil.debugLog("========进入pdf加水印结束==========>"+TimeUtil.getCurrentTimeString()+"=================================");
		/*
		 * File tempfile = new File(InPdfFile);
		 * 
		 * if(tempfile.exists()) {
		 * 
		 * tempfile.delete(); }
		 */

	}

	public static void setWatermark(BufferedOutputStream bos, String input,
			String waterMarkName, int permission) throws DocumentException,
			IOException {

		PdfReader reader = new PdfReader(input);
		LogUtil.debugLog("=======要加水印的pdf文件路径==========>" + input);
		PdfStamper stamper = new PdfStamper(reader, bos);
		int total = reader.getNumberOfPages() + 1;
		PdfContentByte content;
		BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
				BaseFont.EMBEDDED);
		PdfGState gs = new PdfGState();
		for (int i = 1; i < total; i++) {
			// content = stamper.getOverContent(i);// 在内容上方加水印
			content = stamper.getUnderContent(i);// 在内容下方加水印
			gs.setFillOpacity(0.9f);
			content.setGState(gs);
			content.restoreState();
			content.beginText();
			content.setColorFill(Color.LIGHT_GRAY);
			content.setFontAndSize(base, 50);
			content.setTextMatrix(70, 200);
			// content.showTextAligned(Element.ALIGN_CENTER, "公司内部文件，请注意保密！",
			// 300,350, 55);
			Image image = Image
					.getInstance("D:/WEAVER/ecology/weavernorth/workflow/WaterMark.png");
			/*
			 * img.setAlignment(Image.LEFT | Image.TEXTWRAP);
			 * img.setBorder(Image.BOX); img.setBorderWidth(10);
			 * img.setBorderColor(BaseColor.WHITE); img.scaleToFit(1000,
			 * 72);//大小 img.setRotationDegrees(-30);//旋转
			 */
			// set the first background image of the absolute
			image.setAbsolutePosition(100, 306);
			image.scaleToFit(400, 400);
			content.addImage(image);
			content.setColorFill(Color.white);
			content.setFontAndSize(base, 16);
			// content.showTextAligned(Element.ALIGN_CENTER, "下载时间："
			// + waterMarkName + "", 300, 10, 0);
			content.endText();

		}
		stamper.close();
	}
}