package com.domain.java.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 验证码识别（固定大小、固定位置、固定字体）
 * @author Mark Li
 * @version 1.0.0
 * @link http://blog.csdn.net/problc/article/details/5794460
 * @since 2016/11/3
 */
public class CaptchaUtil01 {

    private static int isWhite(int colorInt) {

        Color color = new Color(colorInt);
        if (color.getRed() + color.getGreen() + color.getBlue() > 400) {
            return 1;
        }
        return 0;
    }

    private static int isBlack(int colorInt) {

        Color color = new Color(colorInt);
        if (color.getRed() + color.getGreen() + color.getBlue() <= 400) {
            return 1;
        }
        return 0;
    }

    private static BufferedImage removeBackground(String picFile) throws Exception {

        BufferedImage img = ImageIO.read(new File(picFile));
        int width = img.getWidth();
        int height = img.getHeight();
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                if (isWhite(img.getRGB(x, y)) == 1) {
                    img.setRGB(x, y, Color.WHITE.getRGB());
                } else {
                    img.setRGB(x, y, Color.BLACK.getRGB());
                }
            }
        }
        ImageIO.write(img, "JPG", new File("./captcha/random.jpg"));
        return img;
    }

    private static List<BufferedImage> splitImage(BufferedImage img) throws Exception {

        List<BufferedImage> subImgs = new ArrayList<>();
        subImgs.add(img.getSubimage(6, 3, 9, 13));
        subImgs.add(img.getSubimage(19, 3, 9, 13));
        subImgs.add(img.getSubimage(32, 3, 9, 13));
        subImgs.add(img.getSubimage(45, 3, 9, 13));
        for (int i = 0; i < subImgs.size(); i++) {
            ImageIO.write(subImgs.get(i), "JPG", new File("./captcha/split01_" + i + ".jpg"));
        }

        return subImgs;
    }

    private static Map<BufferedImage, String> loadTrainData() throws Exception {

        Map<BufferedImage, String> map = new HashMap<>();
        String root = CaptchaUtil01.class.getResource("/").getPath();
        File dir = new File(root + "train/OCR01"); // 训练集有问题，只有4和7为9x13
        File[] files = dir.listFiles();
        if (files == null) {
            throw new Exception("训练集为空...");
        }
        for (File file : files) {
            map.put(ImageIO.read(file), file.getName().charAt(0) + "");
        }
        return map;
    }

    private static String getSingleCharOcr(BufferedImage img, Map<BufferedImage, String> map) {

        String result = "";
        int width = img.getWidth();
        int height = img.getHeight();
        int min = width * height;
        for (BufferedImage bi : map.keySet()) {
            int diff = 0; // 不同点统计，不同点越少，越相似
            Label1:
            for (int x = 0; x < width; ++x) {
                for (int y = 0; y < height; ++y) {
                    if (isWhite(img.getRGB(x, y)) != isWhite(bi.getRGB(x, y))) {
                        diff++;
                        // **这一段代码多余
                        if (diff >= min)
                            break Label1;
                        // **
                    }
                }
            }
            if (diff < min) {
                min = diff;
                result = map.get(bi);
            }
        }
        return result;
    }

    public static String getAllOcr(String file) throws Exception {

        BufferedImage img = removeBackground(file);
        List<BufferedImage> listImg = splitImage(img);
        Map<BufferedImage, String> map = loadTrainData();
        String result = "";
        for (BufferedImage bi : listImg) {
            result += getSingleCharOcr(bi, map);
        }
        ImageIO.write(img, "JPG", new File("./captcha/" + result + ".jpg"));
        return result;
    }

    public static void main(String[] args) throws Exception {

        String text = getAllOcr("D:\\Temp\\origin01.jpg");
        System.out.println("origin01.jpg = " + text);
    }
}
