package com.yyin.testfx.controllers.main;

import com.yyin.testfx.mediaplayer.PlayMode;
import com.yyin.testfx.mediaplayer.PlayerStatus;
import com.yyin.testfx.models.PlayListSong;
import com.yyin.testfx.utils.ImageUtils;
import com.yyin.testfx.utils.MyMediaPlayer;
import com.yyin.testfx.utils.TimeUtils;
import com.yyin.testfx.utils.WindowUtils;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @Author: YinZhihao
 * @Description:
 * @Date: Created in 15:33 2021/12/16
 */
public class BottomController extends PlayerStatus {

    @FXML
    private Label labPlay;

    @FXML
    private Label labAlbum;

    @FXML
    private Label labMusicName;

    @FXML
    private Label labMusicSinger;

    @FXML
    private Label labPlayedTime;

    @FXML
    private Label labTotalTime;

    @FXML
    private ProgressBar progressBarSong;

    @FXML
    private Slider sliderSong;

    @FXML
    private Label labSoundIcon;

    @FXML
    private ProgressBar progressBarVolume;

    @FXML
    private Slider sliderVolume;

    private MainController mainController;

    @FXML
    private Label labPlayModeIcon;

    @FXML
    private Label labPlayListCount;

    @FXML
    private HBox hBoxPlayListIcon;

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


    private MyMediaPlayer myMediaPlayer = new MyMediaPlayer();


    private CenterController centerController;

    /**y隐藏专辑歌词面板的动画*/
    private Timeline timelineHide;

    public  Label getLabPlay() {
        return labPlay;
    }

    public Label getLabAlbum() {
        return labAlbum;
    }

    public Label getLabMusicName() {
        return labMusicName;
    }

    public Label getLabMusicSinger() {
        return labMusicSinger;
    }

    public Label getLabPlayTime() {
        return labPlayedTime;
    }

    public Label getLabTotalTime() {
        return labTotalTime;
    }

    public Slider getSliderVolume() {
        return sliderVolume;
    }

    public ProgressBar getProgressBarSong() {
        return progressBarSong;
    }

    public Slider getSliderSong() {
        return sliderSong;
    }

    public Label getLabPlayListCount() {
        return labPlayListCount;
    }

    public Label getLabPlayModeIcon() {
        return labPlayModeIcon;
    }

//    public VBox getVBoxAlbumLyricContainer() {
//        return vBoxLyricContentContainer;
//    }

    public Label getLabSoundIcon() {
        return labSoundIcon;
    }

    public void onClickedAlbum(MouseEvent mouseEvent) {
    }

    public void onClickedPlayLast(MouseEvent mouseEvent) throws TagException, CannotReadException, InvalidAudioFrameException, ReadOnlyFileException, IOException {
        if (mouseEvent.getButton() == MouseButton.PRIMARY){
            playLast();
        }
    }

    public void onClickedPlayOrPause(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getButton() == MouseButton.PRIMARY){
            playOrPause();
        }
    }

    public void onClickedPlayNext(MouseEvent mouseEvent)  {

    }

    public void onClickedSliderSong(MouseEvent mouseEvent) {
    }

    public void onReleasedSliderSong(MouseEvent mouseEvent) {
    }

    public void onClickedSoundIcon(MouseEvent mouseEvent) {
    }

    public void onClickedPlayMode(MouseEvent mouseEvent) {
    }

    public void onClickedPlayListIcon(MouseEvent mouseEvent) {
    }


    public PlayListSong getCurrentPlaySong(){
        return playListSongs.get(currentPlayIndex);
    }
    /**
     * 自定义媒体播放器播放新歌曲行为
     */
    public void playSong() throws ReadOnlyFileException, CannotReadException, TagException, InvalidAudioFrameException, IOException {
        prepareGUI();
        prepareMediaPlayer();
        play();   //播放
//        playAfter();
    }
    public void prepareGUI() throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {
        //.播放、暂停"按钮图片
        labPlay.setGraphic(ImageUtils.createImageView("NeteasePause.png", 32, 32));
        //歌曲名称、歌手、歌曲总时间
        labMusicName.setText(getCurrentPlaySong().getName());
        labMusicSinger.setText(getCurrentPlaySong().getSinger());
        labTotalTime.setText(getCurrentPlaySong().getTotalTime());
        //播放进度条设置
        sliderSong.setValue(0);
        sliderSong.setMax(TimeUtils.toSeconds(getCurrentPlaySong().getTotalTime()));  //设置歌曲滑动条的最大值为歌曲的秒数
        //专辑图片
        if (getCurrentPlaySong().getImageURL() != null){    //如果URL不为空,就是在线资源了.
            Image image = new Image(getCurrentPlaySong().getImageURL(),58,58,true,true);
            if (!image.isError()){
                labAlbum.setGraphic(ImageUtils.createImageView(image,58,58));
            }else { //无网络时

            }
        }else { //本地资源加载专辑图
            labAlbum.setGraphic(ImageUtils.getAlbumImageView(getCurrentPlaySong(),58,58));
        }
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
    public static void play() {
        mediaPlayer.play();

//TODO　lyricContentController
//        if (lyricContentController.isShow()
//                && lyricContentController.getRotateTransition().getStatus() != Animation.Status.RUNNING){
//            lyricContentController.getRotateTransition().play();
//        }
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
    public  void playOrPause() throws IOException {
        if (mediaPlayer != null){
            if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING){
                labPlay.setGraphic(ImageUtils.createImageView("NeteasePause.png",32,32));
                pause();
            } else {
                labPlay.setGraphic(ImageUtils.createImageView("NeteasePlaying.png",32,32));
                play();
            }
        }else { //否则,需要创建媒体资源播放
            prepareMediaPlayer();
            labPlay.setGraphic(ImageUtils.createImageView("NeteasePlaying.png",32,32));
            play();   //播放
            //TODO
//            playAfter();
        }
    }
    public void playLast() throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {
        if (playListSongs.size() == 1) {     //播放列表的歌曲只有一首歌时执行的处理
            mediaPlayer.seek(new Duration(0));
        } else {      //否则播放列表的歌曲大于1，播放下一首歌曲
            if (nextPlayIndexList.contains(currentPlayIndex)) {
                nextPlayIndexList.remove((Object) currentPlayIndex);
            }
            nextPlayIndexList.add(currentPlayIndex);  //播放上一首歌曲之前，把当前的索引添加到下一次播放的索引列表
            if (lastPlayIndexList.size() == 0) {    //如果记录上一首播放的歌曲的列表等于0，证明当前没有上一首歌播放
                if (playMode == PlayMode.SHUFFLE) {   //“随机播放”模式
                    while (true) {  //直到生成的随机数不是当前播放的索引值，执行播放
                        int randomIndex = new Random().nextInt(playListSongs.size());
                        if (randomIndex != currentPlayIndex) {  //如果随机索引值不是当前播放的索引值
                            currentPlayIndex = randomIndex;       //替换当前的播放索引值,退出循环
                            break;
                        }
                    }
                } else { //否则，则为“顺序播放”或“单曲循环”或“顺序循环”模式，且在播放列表歌曲大于1的情况下
                    if (currentPlayIndex == 0) { //如果当前播放歌曲索引为第0位置，设置为播放列表最后的歌曲索引
                        currentPlayIndex = playListSongs.size() - 1;
                    } else { //否则，都是当前播放索引+1
                        currentPlayIndex = currentPlayIndex - 1;
                    }
                }
            } else {       //否则,则lastPlayIndexList的大小大于零,存储有索引,取出记录上一首歌列表里的最后一次添加的那一个歌曲播放
                int index = lastPlayIndexList.size() - 1;
                currentPlayIndex = lastPlayIndexList.get(index);
                lastPlayIndexList.remove(index);
            }
            playSong();  //执行播放索引值对应的歌曲
        }
//        if (lyricContentController.isShow()){
//            lyricContentController.loadAlbumLyric();
//        }
    }
}
