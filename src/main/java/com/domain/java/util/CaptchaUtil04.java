package com.domain.java.util;

import cn.easyproject.easyocr.EasyOCR;
import cn.easyproject.easyocr.ImageType;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.TIFFEncodeParam;

import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * com.domain.java.util
 * @author Mark Li
 * @version 1.0.0
 * @since 2016/11/7
 */
public class CaptchaUtil04 {

    public static void main(String[] args) {

        // testEasyOCR();
        // jpg2tif();
        // renameFile();
        // boxTrain();
        printCmd();
    }

    private static void testEasyOCR() {

        EasyOCR OCR = new EasyOCR("D:\\Program\\Tesseract-OCR\\tesseract");
        // EasyOCR OCR = new EasyOCR();
        // 直接识别图片内容
        System.out.println(OCR.discern("D:/Temp/images/demo_eurotext.png"));
        // 直接识别验证码图片内容
        System.out.println(OCR.discernAndAutoCleanImage("D:/Temp/images/img_INTERFERENCE_LINE.png", ImageType.CAPTCHA_INTERFERENCE_LINE));
        // 验证码图片，经过：普通清理、形变场景自动一体化处理后，识别内容
        System.out.println(OCR.discernAndAutoCleanImage("D:/Temp/images/img_NORMAL.jpg", ImageType.CAPTCHA_NORMAL, 1.6, 0.7));
        System.out.println(OCR.discernAndAutoCleanImage("D:/Temp/origin03.jpg", ImageType.CAPTCHA_INTERFERENCE_LINE, 1.6, 2.2));
        System.out.println(OCR.discernAndAutoCleanImage("D:/Temp/origin04.jpg", ImageType.CAPTCHA_INTERFERENCE_LINE, 1.6, 2.2));
        for (double imageWidthRatio = 0.8; imageWidthRatio <= 2; imageWidthRatio += 0.1) {
            for (double imageHeightRatio = 0.8; imageHeightRatio <= 2.8; imageHeightRatio += 0.1) {
                String random = OCR.discernAndAutoCleanImage("D:/Temp/pingan/1.jpg", ImageType.CAPTCHA_INTERFERENCE_LINE, imageWidthRatio, imageHeightRatio);
                System.out.println("imageWidthRatio" + imageWidthRatio);
                System.out.println("imageHeightRatio" + imageHeightRatio);
                System.out.println(random);
            }
        }
    }

    private static void jpg2tif() {

        try {
            File dir = new File("D:\\Temp\\pingan\\jpg");
            File[] files = dir.listFiles();
            for (int i = 0; i < files.length; i++) {
                String srcFile = "D:\\Temp\\pingan\\jpg\\" + i + ".jpg";
                String descFile = "D:\\Temp\\pingan\\tif\\" + i + ".tif";
                RenderedOp src = JAI.create("fileload", srcFile);
                OutputStream os = new FileOutputStream(descFile);
                TIFFEncodeParam param = new TIFFEncodeParam();
                param.setCompression(TIFFEncodeParam.COMPRESSION_JPEG_TTN2);
                ImageEncoder encoder = ImageCodec.createImageEncoder("TIFF", os, param);
                encoder.encode(src);
                os.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printCmd() {

        File dir = new File("D:\\Temp\\pingan\\train");
        File[] files = dir.listFiles();
        StringBuffer sb = new StringBuffer();
        // sb.append("unicharset_extractor");
        // sb.append("mftraining.exe -F font_properties -U unicharset");
        // sb.append("cntraining");
        for (int i = 0; i < files.length; i++) {
            File oldFile = files[i];
            String fileName = oldFile.getName();
            if (fileName.endsWith(".tr")) {
                sb.append(" ").append(fileName);
            }
        }
        // System.out.println("unicharset_extractor" + sb.toString());
        System.out.println("shapeclustering -F font_properties -U unicharset" + sb.toString());
        System.out.println("mftraining -F font_properties -U unicharset -O pingan.unicharset" + sb.toString());
        System.out.println("cntraining" + sb.toString());
    }

    private static void boxTrain() {

        try {
            File dir = new File("D:\\Temp\\pingan\\train");
            File[] files = dir.listFiles();
            for (int i = 0; i < files.length; i++) {
                File oldFile = files[i];
                String fileName = oldFile.getName();
                if (fileName.endsWith(".tif")) {
                    List<String> cmd = new ArrayList<>();
                    cmd.add("D:\\Program\\Tesseract-OCR\\tesseract");
                    cmd.add("");
                    cmd.add(fileName);
                    cmd.add("");
                    cmd.add(fileName.substring(0, fileName.length() - 4));
                    cmd.add("");
                    cmd.add("nobatch");
                    cmd.add("");
                    cmd.add("box.train");

                    String command = "tesseract " + fileName + " " + fileName.substring(0, fileName.length() - 4) + " nobatch box.train";
                    System.out.println(command);
                    ProcessBuilder pb = new ProcessBuilder();
                    pb.directory(oldFile.getParentFile());

                    // cmd.set(1, oldFile.getName());
                    pb.command(cmd);
                    pb.redirectErrorStream(true);

                    Process process = pb.start();
                    int w = process.waitFor();
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void renameFile() {

        File dir = new File("D:\\Temp\\pingan\\train");
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            File oldFile = files[i];
            System.out.println(oldFile.getName());
            File newFile = new File("D:\\Temp\\pingan\\train\\pingan.none.exp" + oldFile.getName());
            oldFile.renameTo(newFile);
        }
    }
}
