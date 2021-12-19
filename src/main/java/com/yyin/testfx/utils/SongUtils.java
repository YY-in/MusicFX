package com.yyin.testfx.utils;

import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.decoder.Header;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author: YinZhihao
 * @Description:
 * @Date: Created in 22:39 2021/12/19
 */
public class SongUtils {
    //"http://music.163.com/song/media/outer/url?id=1455101010.mp3"
    public static int getSongPlayTimeByWeb(String urlString) throws IOException, BitstreamException {
        URL url = new URL(urlString);
        URLConnection con = url.openConnection();
        // 得到音乐文件的总长度
        int b = con.getContentLength();

        BufferedInputStream bis = new BufferedInputStream(con.getInputStream());

        Bitstream bt = new Bitstream(bis);

        Header h = bt.readFrame();

        int time = (int) h.total_ms(b);

        return time / 1000;
    }
}
