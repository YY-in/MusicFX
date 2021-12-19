package com.yyin.testfx.utils;

import com.yyin.testfx.MainApplication;
import com.yyin.testfx.models.PlayListSong;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.id3.AbstractID3v2Frame;
import org.jaudiotagger.tag.id3.framebody.FrameBodyAPIC;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

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
        if(path==null){
            return null;
        }
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
    /**获取一个ImageView的对象
     * @param resource 资源的路径
     * @param  fitWidth 图片的宽度
     * @param  fitHeight 图片的高度
     * @return ImageView*/
    public static ImageView createImageView(String resource, double fitWidth, double fitHeight){
        Image image =new Image(MainApplication.class.getResource("img/"+resource).toString(),fitWidth,fitHeight,false,true);
        return createImageView(image,fitWidth,fitHeight);
    }

    /**获取一个ImageView的对象
     * @param image 图片对象
     * @param  fitWidth 图片的宽度
     * @param  fitHeight 图片的高度
     * @return ImageView*/
    public static ImageView createImageView(Image image,double fitWidth,double fitHeight){
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(fitHeight);
        imageView.setFitWidth(fitWidth);
        return imageView;
    }
    public static Image getImage(String imageName){
        return new Image(MainApplication.class.getResource("img/"+imageName).toString());
    }

    /**根据歌曲资源的路径获取资源的专辑图片
     * @param playListSong 播放歌曲
     * @return imgAlbum */
    public static ImageView getAlbumImageView(PlayListSong playListSong, double width, double height) throws ReadOnlyFileException, CannotReadException, TagException, InvalidAudioFrameException, IOException {
        ImageView imgAlbum;
        if (!playListSong.getResource().contains("http:")){
            try {
                MP3File mp3File = new MP3File(playListSong.getResource());
                if (mp3File.hasID3v2Tag()){
                    imgAlbum = createImageView(getAlbumImage(playListSong,width,height),width,height);
                }
                else {
                    imgAlbum = createImageView("image/DefaultAlbumImage_200.png",width,height);
                }
            }catch (FileNotFoundException e){
                return createImageView("image/DefaultAlbumImage_200.png",width,height);
            }
        }else { //在线歌曲资源的专辑图，先设置为默认的黑白专辑图，后面再添加。。。
            imgAlbum = createImageView("image/DefaultAlbumImage_200.png",width,height);
        }
        return imgAlbum;
    }

    /**获取资源路径的音乐文件的专辑图片*/
    public static Image getAlbumImage(PlayListSong playListSong,double width,double height) throws ReadOnlyFileException, CannotReadException, TagException, InvalidAudioFrameException, IOException {
        if (!playListSong.getResource().contains("http://")){   //没有包含"http字样",那就是本地歌曲了.
            Image imageData = getAlbumImage(playListSong.getResource(),width, height);
            if (imageData != null) return imageData;
        }else if (playListSong.getImageURL() != null){
            Image image = new Image(playListSong.getImageURL(),width,height,true,true);
            if (!image.isError()){
                return image;
            }
        }
        return new Image("image/DefaultAlbumImage_200.png",width,height,true,true);
    }

    /**根据mp3文件资源路径获取专辑mp3文件的专辑图
     * @param resource 资源路径
     * @param width 宽度
     * @param height 高度*/
    public static Image getAlbumImage(String resource,double width, double height) throws IOException, TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException {
        MP3File mp3File = new MP3File(resource);
        if (mp3File.hasID3v2Tag()){
            try {
                AbstractID3v2Frame abstractID3v2Frame = (AbstractID3v2Frame) mp3File.getID3v2Tag().getFrame("APIC");    //APIC：Attached picture
                FrameBodyAPIC frameBodyAPIC = (FrameBodyAPIC) abstractID3v2Frame.getBody();
                byte[] imageData = frameBodyAPIC.getImageData();
                return new Image(new ByteArrayInputStream(imageData),width,height,true,true);
            }catch (NullPointerException e){
                return new Image("image/DefaultAlbumImage_200.png",width,height,true,true);
            }
        }
        return null;
    }
}

