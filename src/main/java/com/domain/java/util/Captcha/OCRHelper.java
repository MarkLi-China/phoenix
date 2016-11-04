package com.domain.java.util.Captcha;

import org.jdesktop.swingx.util.OS;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OCRHelper {

    private final String LANG_OPTION = "-l"; // 英文字母小写l，并非数字1

    private final String EOL = System.getProperty("line.separator");

    private String tessPath = "D:\\Program\\Tesseract-OCR";
    // private String tessPath = new File("tesseract").getAbsolutePath();

    public String recognizeText(File imageFile, String imageFormat) throws Exception {

        File tempImage = ImageIOHelper.createImage(imageFile, imageFormat);
        File outputFile = new File(imageFile.getParentFile(), "output");
        StringBuilder strB = new StringBuilder();
        List<String> cmd = new ArrayList<String>();
        if (OS.isWindowsXP()) {
            cmd.add(tessPath + "\\tesseract");
        } else if (OS.isLinux()) {
            cmd.add("tesseract");
        } else {
            cmd.add(tessPath + "\\tesseract");
        }
        cmd.add("");
        cmd.add(outputFile.getName());
        cmd.add(LANG_OPTION);
        // cmd.add("chi_sim");
        cmd.add("eng");
        // TODO 自己做训练集

        ProcessBuilder pb = new ProcessBuilder();
        pb.directory(imageFile.getParentFile());

        cmd.set(1, tempImage.getName());
        pb.command(cmd);
        pb.redirectErrorStream(true);

        Process process = pb.start();
        // tesseract.exe 1.jpg 1 -l chi_sim
        int w = process.waitFor();

        // 删除临时正在工作文件
        tempImage.delete();

        if (w == 0) {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(outputFile.getAbsolutePath() + ".txt"), "UTF-8"));

            String str;
            while ((str = in.readLine()) != null) {
                strB.append(str).append(EOL);
            }
            in.close();
        } else {
            String msg;
            switch (w) {
                case 1:
                    msg = "Errors accessing files.There may be spaces in your image's filename.";
                    break;
                case 29:
                    msg = "Cannot recongnize the image or its selected region.";
                    break;
                case 31:
                    msg = "Unsupported image format.";
                    break;
                default:
                    msg = "Errors occurred.";
            }
            tempImage.delete();
            // throw new RuntimeException(msg);
        }
        new File(outputFile.getAbsolutePath() + ".txt").delete();
        return strB.toString();
    }

    private static boolean isWhite(int colorInt) {

        Color color = new Color(colorInt);
        return color.getRed() + color.getGreen() + color.getBlue() > 400;
    }

    private static BufferedImage removeBackground(File imageFile) throws Exception {

        BufferedImage img = ImageIO.read(imageFile);
        int width = img.getWidth();
        int height = img.getHeight();
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                if (isWhite(img.getRGB(x, y))) {
                    img.setRGB(x, y, Color.WHITE.getRGB());
                } else {
                    img.setRGB(x, y, Color.BLACK.getRGB());
                }
            }
        }
        ImageIO.write(img, "JPG", new File("./captcha/random.jpg"));
        return img;
    }

    public static void main(String[] args) {

        String path = "D:\\Temp\\origin03.jpg";
        try {
            String valCode = new OCRHelper().recognizeText(new File(path), "jpg");
            System.out.println(valCode);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
