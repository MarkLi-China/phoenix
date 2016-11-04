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
 * 验证码识别（位置不固定，每个字符颜色统一）
 * @author Mark Li
 * @version 1.0.0
 * @link http://blog.csdn.net/problc/article/details/5800093
 * @since 2016/11/3
 */
public class CaptchaUtil02 {

    private static int isWhite(int colorInt) {

        Color color = new Color(colorInt);
        if (color.getRed() + color.getGreen() + color.getBlue() > 400) {
            return 1;
        }
        return 0;
    }

    private static int isBlack(int colorInt) {

        Color color = new Color(colorInt);
        if (color.getRed() + color.getGreen() + color.getBlue() <= 100) {
            return 1;
        }
        return 0;
    }

    private static BufferedImage removeBackground02(String picFile, int charNum) throws Exception {

        BufferedImage img = ImageIO.read(new File(picFile));
        img = img.getSubimage(1, 1, img.getWidth() - 2, img.getHeight() - 2); // 切除边界
        int width = img.getWidth();
        int height = img.getHeight();
        double subWidth = (double) width / charNum; // 按个数分成相应份数
        for (int i = 0; i < charNum; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int x = (int) (1 + i * subWidth); x < (i + 1) * subWidth && x < width - 1; ++x) {
                for (int y = 0; y < height; ++y) {
                    if (img.getRGB(x, y) == Color.WHITE.getRGB())
                        continue;
                    if (map.containsKey(img.getRGB(x, y))) {
                        map.put(img.getRGB(x, y), map.get(img.getRGB(x, y)) + 1);
                    } else {
                        map.put(img.getRGB(x, y), 1);
                    }
                }
            }
            int max = 0;
            int colorMax = 0;
            // 颜色值最多的就是验证码的颜色
            for (Integer color : map.keySet()) {
                if (max < map.get(color)) {
                    max = map.get(color);
                    colorMax = color;
                }
            }
            // 去除背景色
            for (int x = (int) (1 + i * subWidth); x < (i + 1) * subWidth && x < width - 1; ++x) {
                for (int y = 0; y < height; ++y) {
                    if (img.getRGB(x, y) != colorMax) {
                        img.setRGB(x, y, Color.WHITE.getRGB());
                    } else {
                        img.setRGB(x, y, Color.BLACK.getRGB());
                    }
                }
            }
        }
        ImageIO.write(img, "JPG", new File("./captcha/random.jpg"));
        return img;
    }

    // 横向切
    private static BufferedImage removeBlank(BufferedImage img) throws Exception {

        int width = img.getWidth();
        int height = img.getHeight();
        int start = 0; // 横向切起点
        int end = 0; // 横向切终点
        Label1:
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                if (isBlack(img.getRGB(x, y)) == 1) {
                    start = y;
                    break Label1;
                }
            }
        }
        Label2:
        for (int y = height - 1; y >= 0; --y) {
            for (int x = 0; x < width; ++x) {
                if (isBlack(img.getRGB(x, y)) == 1) {
                    end = y;
                    break Label2;
                }
            }
        }
        return img.getSubimage(0, start, width, end - start + 1);
    }

    private static List<BufferedImage> splitImage02(BufferedImage img) throws Exception {

        List<BufferedImage> subImgs = new ArrayList<>();
        int width = img.getWidth();
        int height = img.getHeight();
        List<Integer> weightList = new ArrayList<>();
        for (int x = 0; x < width; ++x) {
            int count = 0;
            for (int y = 0; y < height; ++y) {
                if (isBlack(img.getRGB(x, y)) == 1) {
                    count++;
                }
            }
            weightList.add(count);
        }
        for (int i = 0; i < weightList.size(); i++) {
            int length = 0; // 每个字符的宽度，用来纵向切
            while (i < weightList.size() && weightList.get(i) > 0) {
                i++;
                length++;
            }
            if (length > 2) {
                subImgs.add(removeBlank(img.getSubimage(i - length, 0, length, height)));
            }
        }
        for (int i = 0; i < subImgs.size(); i++) {
            ImageIO.write(subImgs.get(i), "JPG", new File("./captcha/split02_" + i + ".jpg"));
        }

        return subImgs;
    }

    private static Map<BufferedImage, String> loadTrainData02() throws Exception {

        Map<BufferedImage, String> map = new HashMap<>();
        String root = CaptchaUtil02.class.getResource("/").getPath();
        File dir = new File(root + "train/OCR02");
        File[] files = dir.listFiles();
        if (files == null) {
            throw new Exception("训练集为空...");
        }
        for (File file : files) {
            map.put(ImageIO.read(file), file.getName().charAt(0) + "");
        }
        return map;
    }

    private static String getSingleCharOcr02(BufferedImage img, Map<BufferedImage, String> map) {

        String result = "";
        int width = img.getWidth();
        int height = img.getHeight();
        int min = width * height;
        for (BufferedImage bi : map.keySet()) {
            int diff = 0; // 不同点统计，不同点越少，越相似
            if (Math.abs(bi.getWidth() - width) > 2)
                continue;
            int widthMin = width < bi.getWidth() ? width : bi.getWidth();
            int heightMin = height < bi.getHeight() ? height : bi.getHeight();
            Label1:
            for (int x = 0; x < widthMin; ++x) {
                for (int y = 0; y < heightMin; ++y) {
                    if (isWhite(img.getRGB(x, y)) != isWhite(bi.getRGB(x, y))) {
                        diff++;
                        if (diff >= min)
                            break Label1;
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

    public static String getAllOcr02(String file) throws Exception {

        BufferedImage img = removeBackground02(file, 5);
        List<BufferedImage> listImg = splitImage02(img);
        Map<BufferedImage, String> map = loadTrainData02();
        String result = "";
        for (BufferedImage bi : listImg) {
            result += getSingleCharOcr02(bi, map);
        }
        ImageIO.write(img, "JPG", new File("./captcha/" + result + ".jpg"));
        return result;
    }

    private static void trainData(int charNum) throws Exception {

        int index = 0;
        String path = "D:\\Temp\\captcha02\\temp";
        File dir = new File(path);
        File[] files = dir.listFiles();
        if (files == null) {
            throw new Exception("原始数据集为空，无法进行训练...");
        }
        for (File file : files) {
            BufferedImage img = removeBackground02(path + "\\" + file.getName(), 5);
            List<BufferedImage> listImg = splitImage02(img);
            if (listImg.size() == charNum) {
                for (int j = 0; j < listImg.size(); ++j) {
                    ImageIO.write(listImg.get(j), "JPG", new File("D:\\Temp\\captcha02\\train\\" + file.getName().charAt(j) + "-" + (index++) + ".jpg"));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {

        // 缺少训练集
        String text = getAllOcr02("D:\\Temp\\origin02.jpg");
        System.out.println("origin02.jpg = " + text);
    }
}
