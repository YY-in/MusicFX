package com.yyin.testfx.utils;

import com.alibaba.fastjson.JSONObject;
import com.yyin.testfx.mediaplayer.Config;
import com.yyin.testfx.models.PlayListSong;
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
    private static  Config config = new Config();
    /**
     * @date: 11:12 2021/12/21
     * @param mid 歌曲的mid编号
     * @return 歌曲的播放时长 单位秒
     */
    //"http://music.163.com/song/media/outer/url?id=1455101010.mp3"
    public static int getSongPlayTimeByWeb(int mid) throws IOException, BitstreamException {
        URL url = new URL(config.getSongPlay(mid));
        URLConnection con = url.openConnection();
        // 得到音乐文件的总长度
        int b = con.getContentLength();

        BufferedInputStream bis = new BufferedInputStream(con.getInputStream());

        Bitstream bt = new Bitstream(bis);

        Header h = bt.readFrame();

        int time = (int) h.total_ms(b);

        return time / 1000;
    }

    public static JSONObject parseSongImfornation(int mid) throws IOException {
        return JDKSpider.getJson(config.getSongURL(mid));
    }

    public static PlayListSong createPlayListSong(int mid) throws IOException, BitstreamException {
        JSONObject object = JDKSpider.getJson(config.getSongURL(mid));
        System.out.println(JDKSpider.getPrettyJsonString(object));
        String title = object.get("title").toString();
        String singer = object.get("artist").toString();
        String album = object.get("album").toString();
        String totalTime = "" + getSongPlayTimeByWeb(mid);
        String resource = config.getSongPlay(mid);
        String lyricURL = config.getLyric(mid);
        String cover =object.get("cover").toString();
//        System.out.println(cover);
        //int id,String name, String singer, String album, String totalTime, String resource, String , String imageURL
        PlayListSong playListSong = new PlayListSong(mid,title,singer,album,totalTime,resource,lyricURL,cover);
        return playListSong;
    }


}
