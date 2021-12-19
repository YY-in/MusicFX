package com.yyin.testfx.utils;

import com.yyin.testfx.controllers.main.BottomController;
import com.yyin.testfx.controllers.main.MainController;
import com.yyin.testfx.mediaplayer.PlayerStatus;
import com.yyin.testfx.models.PlayListSong;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: YinZhihao
 * @Description:
 * @Date: Created in 8:52 2021/12/17
 */
public class MyMediaPlayer extends PlayerStatus {

    /**
     * 定义JavaFX媒体播放器对象
     */
    private static MediaPlayer mediaPlayer;

    /**
     * 记录上一首播放的索引记录
     */
    private static List<Integer> lastPlayIndexList = new LinkedList<>();

    /**
     * 记录下一首播放的索引记录
     */
    private static List<Integer> nextPlayIndexList = new LinkedList<>();


    private static BottomController bottomController = new BottomController();

    
    public static void play() {
       mediaPlayer.play();

//TODO　lyricContentController
//        if (lyricContentController.isShow()
//                && lyricContentController.getRotateTransition().getStatus() != Animation.Status.RUNNING){
//            lyricContentController.getRotateTransition().play();
//        }
    }

    public static void pause() {
        if (mediaPlayer != null){
            mediaPlayer.pause();
        }

// TODO　lyricContentController
//        if (lyricContentController.isShow() && lyricContentController.getRotateTransition().getStatus() != Animation.Status.PAUSED){
//            lyricContentController.getRotateTransition().pause();
//        }
    }


    
    public static void prepareGUI() throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {

    }

     
    public static void prepareMediaPlayer() {
        /**如果当前的媒体播放器不为空,销毁它*/
        if (mediaPlayer != null) {
            mediaPlayer.dispose();
            mediaPlayer = null;
        }
        /**创建MediaPlayer播放*/
        String resource = "http://music.163.com/song/media/outer/url?id=1455101010.mp3";
//                playListSongs.get(currentPlayIndex).getResource();
        if (resource.contains("http:")){    //如果是在线资源，加载专辑图，并设置显示
            mediaPlayer = new MediaPlayer(new Media(resource)); //创建在线资源的媒体播放器对象
            play();
            mediaPlayer.setOnError(()->{
                WindowUtils.toastInfo(MainController.getStackPane(),new Label("资源获取失败"));
                pause();
            });
        }else { //本地文件资源
            File mediaFile = new File(resource);
            if (!mediaFile.exists()){       //文件不存在,播放失败
                WindowUtils.toastInfo(MainController.getStackPane(),new Label("播放失败"));
                pause();
            }else {
                mediaPlayer = new MediaPlayer(new Media(mediaFile.toURI().toString()));  //创建本地资源的媒体播放器对象
            }
        }
    }

     
    public static void playOrPause() throws IOException {
        if (mediaPlayer != null){
            if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING){
                pause();
            } else {
                play();
            }
        }else { //否则,需要创建媒体资源播放
            prepareMediaPlayer();
            play();   //播放
            //TODO
//            playAfter();
        }
    }

     
     public static void playSong() throws ReadOnlyFileException, CannotReadException, TagException, InvalidAudioFrameException, IOException, ParseException {

    }

     
     public static void playAll(List tableItems) throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {

    }

     
     public static void playAll(List tableItems, int index) throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {

    }

     
     public static void playLast() throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {

    }

     
     public static void playNext() throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {

    }

     
     public static void addToPlayList(PlayListSong playListSong) throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {

    }

     
     public static void removeFromPlayList(int index) throws ReadOnlyFileException, IOException, TagException, InvalidAudioFrameException, CannotReadException {

    }

     
     public static void addFavor() {

    }

     
     public static void destroy() {

    }

     
     public static void seek(Duration duration) {

    }

     
     public static void switchMute() {

    }

     
     public static void switchPlayMode() {

    }

     
    public boolean isPlayingThis(PlayListSong playListSong) {
        return false;
    }
}
