package com.yyin.testfx.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * @Author: YinZhihao
 * @Description:
 * @Date: Created in 14:55 2021/12/12
 */
public class ImageUtils {
    /**
     * 图片转二进制字符串
     * @param path
     * @date: 15:00 2021/12/12
     */
    public static byte[] pathToBinaryStream(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        try {
            //读取对应路径的图片
            BufferedImage bi = ImageIO.read(file);
            //二进制输出流
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //获得文件格式名
            String sufffix = getSuffix(path);
            //将图片按格式写入二进制输出流中
            ImageIO.write(bi, sufffix, baos);
            byte[] img_bytes = baos.toByteArray();

            return img_bytes;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 字符串转图片文件
     *  @param path      图片路径
     * @param imgBinary 图片字符串*/
    public static void binaryToImg(String path, byte[] imgBinary) {
        try {
            File file = new File(path);

            ByteArrayInputStream bais = new ByteArrayInputStream(imgBinary);
            BufferedImage bi1 = ImageIO.read(bais);
            String suffix = getSuffix(path);
            ImageIO.write(bi1, suffix, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取图片后缀名
     *
     * @param path
     * @return
     */
    private static String getSuffix(String path) {
        int index = path.contains(".") ? path.lastIndexOf(".") : -1;
        if (index > -1) {
            return path.substring(index + 1);
        }
        return null;
    }
}
