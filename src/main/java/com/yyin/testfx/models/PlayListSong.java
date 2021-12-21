package com.yyin.testfx.models;

import javafx.scene.control.Label;

/**
 * @author super lollipop
 * @date 20-1-24
 */


public class PlayListSong {
    private int id;     //歌曲id
    private String name;    //歌曲名
    private String singer;  //歌手
    private String album;   //专辑名
    private String totalTime;   //总时长
    private String resource;    //资源路径
    private String lyricURL;    //歌词
    private String imageURL;    //封面路径
    private Label labRemoveIcon;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public Label getLabRemoveIcon() {
        return labRemoveIcon;
    }

    public void setLabRemoveIcon(Label labRemoveIcon) {
        this.labRemoveIcon = labRemoveIcon;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getLyric() {
        return lyricURL;
    }

    public void setLyric(String lyricURL) {
        this.lyricURL = lyricURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public PlayListSong(int id,String name, String singer, String album, String totalTime, String resource, String lyricURL, String imageURL) {
        this.id = id;
        this.name = name;
        this.singer = singer;
        this.album = album;
        this.totalTime = totalTime;
        this.resource = resource;
        this.lyricURL = lyricURL;
        this.imageURL = imageURL;
    }

    public PlayListSong(int id,String name, String singer, String album, String totalTime, String resource) {
        this.id = id;
        this.name = name;
        this.singer = singer;
        this.album = album;
        this.totalTime = totalTime;
        this.resource = resource;
    }
    public PlayListSong(String resource) {
        this.resource = resource;
    }
}
