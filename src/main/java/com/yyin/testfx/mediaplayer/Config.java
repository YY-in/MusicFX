package com.yyin.testfx.mediaplayer;


import java.nio.file.Path;

/*
 * @author super lollipop
 * @date 20-2-11
 */

public class Config {

    /**播放器配置文件的存放路径*/
    private Path configPath;

    private String server= "http://api.yyin.top:3000";

    public String getSongPlay(int mid){
        return  "http://music.163.com/song/media/outer/url?id="+mid;
    }

    public String getSongURL(int mid) {
        return "https://api.paugram.com/netease/?id="+mid;
    }

    public String getSingerURL(int sid){
        return server + "/artist/detail?id="+ sid;
    }

    public String getAlbumURL(int aid){
        return server + "/album?id="+aid;
    }

    public String getUserURL(int uid){
        return server + "/user/detail?uid=" + uid;
    }

    public String getGroupURL(int pid){
        return server + "/playlist/detail?id=" + pid;
    }

    public String getSongDetailURL(int mid){
        return server + "/song/detail?id="+mid;
    }

    public String getSongDetailURL(int ... args){
        StringBuffer song = new StringBuffer();
        song.append(args[0]);
        for (int i = 1; i < args.length; i++) {
            song.append(","+args[i]);
        }
        return server + "/song/detail?ids="+song;
    }

    public String getLyric(int mid){
        return server + "/lyric?id=" + mid;
    }



}
