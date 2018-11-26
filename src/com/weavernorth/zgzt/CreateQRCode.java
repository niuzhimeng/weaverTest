package com.weavernorth.zgzt;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.util.HashMap;

//生成二维码
public class CreateQRCode {
    public static void main(String[] args) {
        final int width = 300;
        final int height = 300;
        final String format = "png";
        final String content = "我爱你，中国";

        //定义二维码的参数
        HashMap hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 2);

        //生成二维码
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            File file = new File("D:/img.png");
            MatrixToImageWriter.writeToFile(bitMatrix, format, file);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
