package com.yyin.testfx.controllers.main;

import com.yyin.testfx.mediaplayer.Config;
import com.yyin.testfx.mediaplayer.IMediaPlayer;
import com.yyin.testfx.mediaplayer.PlayMode;
import com.yyin.testfx.mediaplayer.PlayerStatus;
import com.yyin.testfx.models.PlayListSong;
import com.yyin.testfx.utils.ImageUtils;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @Author: YinZhihao
 * @Description:
 * @Date: Created in 15:33 2021/12/16
 */
public class BottomController extends PlayerStatus implements IMediaPlayer {
    @FXML
    private BorderPane bottomPane;

    @FXML
    private HBox hBoxPlayListIcon;

    @FXML
    private Label labAlbum;

    @FXML
    private Label labMusicName;

    @FXML
    private Label labMusicSinger;

    @FXML
    private  Label labPlay;

    @FXML
    private Label labPlayLast;

    @FXML
    private Label labPlayListCount;


    @FXML
    private Label labPlayModeIcon;

    @FXML
    private Label labPlayNext;

    @FXML
    private Label labPlayedTime;

    @FXML
    private Label labSoundIcon;

    @FXML
    private Label labTotalTime;

    @FXML
    private ImageView playImg;

    @FXML
    private ProgressBar progressBarSong;

    @FXML
    private ProgressBar progressBarVolume;

    @FXML
    private Slider sliderSong;

    @FXML
    private Slider sliderVolume;

    public Label getLabPlay() {
        return labPlay;
    }

    public void setLabPlay(Label labPlay) {
        this.labPlay = labPlay;
    }

    public Slider getSliderSong() {
        return sliderSong;
    }

    public void setSliderSong(Slider sliderSong) {
        this.sliderSong = sliderSong;
    }

    public Slider getSliderVolume() {
        return sliderVolume;
    }

    public void setSliderVolume(Slider sliderVolume) {
        this.sliderVolume = sliderVolume;
    }

    public void initialize(){
        progressBarSong.prefWidthProperty().bind(((StackPane)progressBarSong.getParent()).widthProperty());  //宽度绑定
        sliderSong.prefWidthProperty().bind(((StackPane)sliderSong.getParent()).widthProperty());            //宽度绑定
        //设置播放进度滑动条的监听事件，使进度条始终跟随滚动条更新
        sliderSong.valueProperty().addListener((observable, oldValue, newValue) -> {
            Date date = new Date((int)newValue.doubleValue()*1000); //乘以一千变成秒数
            labPlayedTime.setText(new SimpleDateFormat("mm:ss").format(date));
            progressBarSong.setProgress(newValue.doubleValue()/sliderSong.getMax());
        });
        //设置音量滑动条的监听事件，使进度条始终跟随滚动条更新
        sliderVolume.valueProperty().addListener((observable, oldValue, newValue) -> {
            progressBarVolume.setProgress(newValue.doubleValue());
            if (observable.getValue().doubleValue() != 0){
               this.setVolume(observable.getValue().doubleValue());
            }
            if (observable.getValue().doubleValue() == 0 && !isMute()){
                setMute(true);
            }
            else if (observable.getValue().doubleValue() > 0 && isMute()){
                setMute(false);
            }
        });

    }
    @Override
    public PlayMode getPlayMode() {
        return super.getPlayMode();
    }

    @Override
    public void setPlayMode(PlayMode playMode) {
        super.setPlayMode(playMode);
    }


    @FXML
    void onClickedAlbum(MouseEvent event) {

    }


    @FXML
    void onClickedPlayListIcon(MouseEvent event) {

    }

    @FXML
    void onClickedPlayMode(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY){     //鼠标左击才执行
            switchPlayMode();
        }
    }

    @FXML
    void onClickedPlayLast(MouseEvent event) throws TagException, CannotReadException, InvalidAudioFrameException, ReadOnlyFileException, IOException, ParseException {
        if (event.getButton() == MouseButton.PRIMARY){
           playLast();
        }
    }

    @FXML
    void onClickedPlayNext(MouseEvent event) {

    }

    @FXML
    void onClickedPlayOrPause(MouseEvent event) throws IOException {
        if (event.getButton() == MouseButton.PRIMARY){
            playOrPause();
        }
    }
    /**歌曲进度滑动条的鼠标单击事件*/
    @FXML
    void onClickedSliderSong(MouseEvent event) {
        seek(new Duration(1000 * sliderSong.getValue()));
    }
    /**音量图标的单击事件*/
    @FXML
    void onClickedSoundIcon(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY){  //鼠标左击
            switchMute();
        }
    }
    /**歌曲进度滑动条的鼠标释放事件*/
    @FXML
    void onReleasedSliderSong(MouseEvent event) {
        seek(new Duration(1000 * sliderSong.getValue()));
    }


    /**定义JavaFX媒体播放器对象**/
    private MediaPlayer mediaPlayer;
    /**记录上一首播放的索引记录**/
    private List<Integer> lastPlayIndexList = new LinkedList<>();
    /**记录下一首播放的索引记录**/
    private List<Integer> nextPlayIndexList = new LinkedList<>();

    private Config config = new Config();

    public MediaPlayer getPlayer() {
        return mediaPlayer;
    }

    public List<PlayListSong> getPlayListSongs() {
        if (playListSongs == null){
            playListSongs = new LinkedList<>();
        }
        return playListSongs;
    }

    public int getCurrentPlayIndex() {
        return currentPlayIndex;
    }

    public PlayListSong getCurrentPlaySong(){
        return playListSongs.get(currentPlayIndex);
    }

    public List<Integer> getNextPlayIndexList() {
        if (nextPlayIndexList == null) {
            nextPlayIndexList = new LinkedList<>();
        }
        return nextPlayIndexList;
    }

    public void setPlayListSongs(ObservableList<PlayListSong> playListSongs) {
        this.playListSongs = playListSongs;
    }

    public void setCurrentPlayIndex(int currentPlayIndex) {
        this.currentPlayIndex = currentPlayIndex;
    }

//    private MainController mainController = getMainController();
//
//    private MainController getMainController(){
//        try {
//            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("fxml/main/main-pane.fxml"));
//            Parent root = loader.load();
//            MainController c = loader.getController();
//            return c;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    private CenterController centerController = getCenterController();
//
//    private CenterController getCenterController(){
//        try {
//            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("fxml/main/main-center.fxml"));
//            Parent root = loader.load();
//            CenterController c = loader.getController();
//            return c;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    private BottomController bottomController=getBottomController();
//
//    private BottomController getBottomController(){
//        try {
//            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("fxml/main/main-bottom.fxml"));
//            Parent root = loader.load();
//            BottomController c = loader.getController();
//            return c;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }



    @Override
    public void play() {
        mediaPlayer.play();
        getLabPlay().setGraphic(ImageUtils.createImageView("NeteasePlaying.png", 32, 32));  //"播放、暂停"按钮图片
//        if (lyricContentController.isShow()
//                && lyricContentController.getRotateTransition().getStatus() != Animation.Status.RUNNING){
//            lyricContentController.getRotateTransition().play();
//        }
    }

    @Override
    public void pause() {
        if (mediaPlayer != null){
            mediaPlayer.pause();
        }
        labPlay.setGraphic(ImageUtils.createImageView("NeteasePause.png", 32, 32));  //"播放、暂停"按钮图片
//        if (lyricContentController.isShow() && lyricContentController.getRotateTransition().getStatus() != Animation.Status.PAUSED){
//            lyricContentController.getRotateTransition().pause();
//        }
    }

    @Override
    public void prepareGUI() throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {
        //.播放、暂停"按钮图片
        labPlay.setGraphic(ImageUtils.createImageView("NeteasePause.png", 32, 32));
        //歌曲名称、歌手、歌曲总时间
        labMusicName.setText(getCurrentPlaySong().getName());
        labMusicSinger.setText(getCurrentPlaySong().getSinger());
        labTotalTime.setText(getCurrentPlaySong().getTotalTime());
        //播放进度条设置
        sliderSong.setValue(0);
        sliderSong.setMax(Double.parseDouble(getCurrentPlaySong().getTotalTime()));  //设置歌曲滑动条的最大值为歌曲的秒数
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

    @Override
    public void prepareMediaPlayer() {
        if (mediaPlayer != null) {  //如果当前的媒体播放器不为空,销毁它
            this.mediaPlayer.dispose();
            this.mediaPlayer = null;
        }
        /**创建MediaPlayer播放*/
        String resource = playListSongs.get(currentPlayIndex).getResource();
                config.getSongPlay(1893335808);
//        System.out.println(config.getSongPlay(1893335808));
        System.out.println(resource);
        if (resource.contains("http")){    //如果是在线资源，加载专辑图，并设置显示
            mediaPlayer = new MediaPlayer(new Media(resource)); //创建在线资源的媒体播放器对象
            mediaPlayer.setOnError(()->{
                System.out.println("error");
//                WindowUtils.toastInfo(mainController.getStackPane(),new Label("资源获取失败"));
                pause();
            });
        }else { //本地文件资源
            File mediaFile = new File(resource);
            if (!mediaFile.exists()){       //文件不存在,播放失败
//                WindowUtils.toastInfo(mainController.getStackPane(),new Label("播放失败"));
                pause();
            }else {
                mediaPlayer = new MediaPlayer(new Media(mediaFile.toURI().toString()));  //创建本地资源的媒体播放器对象
            }
        }

        mediaPlayer.volumeProperty().bind(sliderVolume.valueProperty());  //设置媒体播放器的音量绑定音量条组件的音量
        mediaPlayer.setMute(isMute());
        mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            //底部进度条
            if (!sliderSong.isPressed()) {  //没有被鼠标按下时
                sliderSong.setValue(observable.getValue().toSeconds());
            }
//            if (lyricContentController.isShow()){
//                //歌词滚动
//                float second = (float) (Math.round(observable.getValue().toSeconds() * 10 ) / 10.0);    //保留一位小数
//                lyricContentController.scrollLyric(second);
//            }
        });
        /**媒体播放器结束后触发的事件
         * start*/
        mediaPlayer.setOnEndOfMedia(() -> {   //媒体播放器结束后触发的事件
            switch (playMode) {
                case SINGLE_LOOP: {                        //单曲循环模式
                    mediaPlayer.seek(new Duration(0));  //定位到0毫秒(0秒)的时间，重新开始播放
                    break;
                }
                case SEQUENCE: {                             //顺序播放模式
                    if (lastPlayIndexList == null){ //如果记录下一首播放的索引记录等于null，则初始化
                        lastPlayIndexList = new LinkedList<>();
                    }else if (lastPlayIndexList.contains(currentPlayIndex)){    //否则，判断是否包含了当前的播放索引
                        lastPlayIndexList.remove((Object)currentPlayIndex); //包含就移除
                    }
                    lastPlayIndexList.add(currentPlayIndex);    //添加到下一首播放的索引记录

                    if (nextPlayIndexList != null && nextPlayIndexList.size() > 0) {              //顺序播放模式下，如果记录下一首播放的索引记录大于0
                        currentPlayIndex = nextPlayIndexList.get(nextPlayIndexList.size()-1);   //取出最后一次添加的“下一首”记录
                        nextPlayIndexList.remove(nextPlayIndexList.size()-1);   //移除
                        if (nextPlayIndexList.size() == 0){ //如果大小为0，赋值为null
                            nextPlayIndexList = null;
                        }
                    } else {            //否则，顺序播放，当前播放索引+1，如果是最后一首歌播放结束后，释放媒体播放器资源
                        currentPlayIndex = currentPlayIndex + 1;   //当前的播放索引加1
                        if (currentPlayIndex > playListSongs.size() - 1) {   //如果索引不在播放列表中，播放第一首歌，然后暂停
                            currentPlayIndex = 0;   //当前播放索引设置为0
                            lastPlayIndexList = null;   //清空记录上一首播放的索引
                            nextPlayIndexList = null;
                            try {
                                this.playSong();
                                this.pause();
                                return;
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                    try {
                        this.playSong(); //播放当前索引
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case SEQUENCE_LOOP: {       //列表循环模式
                    if (playListSongs.size() == 1) {    //列表循环模式下，如果歌曲表格只有一首歌，只要把mediaPlayer的当前播放时间重新设置为0秒就可以了
                        mediaPlayer.seek(new Duration(0));  //定位到0毫秒(0秒)的时间，重新开始播放
                        return;
                    }
                    if (lastPlayIndexList == null) { //如果记录下一首播放的索引记录等于null，则初始化它
                        lastPlayIndexList = new LinkedList<Integer>();
                    } else if (lastPlayIndexList.contains(currentPlayIndex)) {    //否则，lastPlayIndexList不等于null,需判断是否包含了当前的播放索引
                        lastPlayIndexList.remove((Object) currentPlayIndex); //包含就移除
                    }
                    lastPlayIndexList.add(currentPlayIndex);    //添加到下一首播放的索引记录

                    if (nextPlayIndexList != null && nextPlayIndexList.size() > 0) { //列表循环模式下，如果记录下一首播放不等于null且大于0，执行索引下一首播放
                        currentPlayIndex = nextPlayIndexList.get(nextPlayIndexList.size() - 1);   //取出最后一次添加的“下一首”记录
                        nextPlayIndexList.remove(nextPlayIndexList.size() - 1);   //移除
                        if (nextPlayIndexList.size() == 0) { //如果大小为0，赋值为null
                            nextPlayIndexList = null;
                        }
                    } else { //否则，执行当前播放索引+1播放
                        currentPlayIndex = currentPlayIndex + 1;
                        if (currentPlayIndex > playListSongs.size() - 1) {  //如果当前索引越界，值为0，形成一个循环
                            currentPlayIndex = 0;
                        }
                    }
                    try {
                        playSong();  //播放歌曲
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case SHUFFLE: {              //"随机播放"
                    //随机播放模式下，如果播放列表只有一首歌，只要把mediaPlayer的当前播放时间重新设置为0秒就可以了
                    if (playListSongs.size() == 1) {
                        mediaPlayer.seek(new Duration(0));  //定位到0毫秒(0秒)的时间，重新开始播放
                    }
                    //否则，播放列表大于1，生成一个非当前播放的索引值来播放
                    else {
                        if (lastPlayIndexList == null){
                            lastPlayIndexList = new LinkedList<>();
                        }else if (lastPlayIndexList.contains(currentPlayIndex)) {    //否则，lastPlayIndexList不等于null,需判断是否包含了当前的播放索引
                            lastPlayIndexList.remove((Object) currentPlayIndex); //包含就移除
                        }
                        lastPlayIndexList.add(currentPlayIndex);   //先记录当前的索引是上一首需要的索引

                        if (nextPlayIndexList == null || nextPlayIndexList.size() ==0) {  //nextPlayIndexList的大小等0或为空，证明当前没有需要播放下一首歌曲的索引，直接生成随机索引数播放
                            //然后生成一个随机数不是当前播放的索引值，执行播放
                            while (true) {
                                int randomIndex = new Random().nextInt(playListSongs.size());
                                if (randomIndex != currentPlayIndex) {  //如果随机数不是当前播放的索引值
                                    currentPlayIndex = randomIndex;    //当前的播放索引替换成生成的随机索引
                                    break;                           //跳出循环
                                }
                            }
                            try {
                                this.playSong();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {   //否则,则nextPlayIndexList的大小大于零,存储有索引,取出记录上一首歌列表里的最后一次添加的那一个歌曲播放
                            int index = nextPlayIndexList.size() - 1;
                            currentPlayIndex = nextPlayIndexList.get(index);  //设置当前的播放索引值
                            nextPlayIndexList.remove(index);  //移除本条索引值,因为已经播放了.
                            if (nextPlayIndexList.size() ==0){
                                nextPlayIndexList = null;
                            }
                            try {
                                this.playSong();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    break;
                }
                default:
            }
        });
        /**end*/
    }

    @Override
    public boolean playOrPause() throws IOException {
        if (mediaPlayer != null){
            if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING){
                pause();
                return false;
            } else {
                play();
                return true;
            }
        }else { //否则,需要创建媒体资源播放
            prepareMediaPlayer();
            play();   //播放
            playAfter();
            //TODO
        }
        return false;
    }

    public void playAfter() {
        System.out.println("播放结束后处理操作");
    }

    @Override
    public void playSong() throws ReadOnlyFileException, CannotReadException, TagException, InvalidAudioFrameException, IOException, ParseException {
        prepareGUI();
        prepareMediaPlayer();
        play();   //播放
        playAfter();
    }

    @Override
    public void playAll(List tableItems) throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {

    }

    @Override
    public void playAll(List tableItems, int index) throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {

    }

    @Override
    public void playLast() throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException, ParseException {
        if (playListSongs.size() == 1) {     //播放列表的歌曲只有一首歌时执行的处理,歌曲回滚处理
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

    @Override
    public void playNext() throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {

    }

    @Override
    public void addToPlayList(PlayListSong playListSong) throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {

    }

    @Override
    public void removeFromPlayList(int index) throws ReadOnlyFileException, IOException, TagException, InvalidAudioFrameException, CannotReadException {

    }

    @Override
    public void addFavor() {

    }

    @Override
    public void destroy() {
        if (mediaPlayer != null){
            mediaPlayer.dispose();
            mediaPlayer = null;
        }
        currentPlayIndex = -1;
        if (lastPlayIndexList != null && lastPlayIndexList.size() > 0){
            lastPlayIndexList.clear();
        }
        if (nextPlayIndexList != null && nextPlayIndexList.size() > 0){
            nextPlayIndexList.clear();
        }
        if (playListSongs != null && playListSongs.size() > 0){
            playListSongs.clear(); //清空播放列表
        }

        //还需要更新底部显示音乐进度的GUI显示
        labAlbum.setGraphic(ImageUtils.createImageView("DefaultAlbumImage_200.png", 58, 58));    //设置默认的图片专辑图
        labPlay.setGraphic(ImageUtils.createImageView("NeteasePause.png", 32, 32));           //设置为暂停的图片
        labMusicName.setText("无");    //设置播放歌曲信息"无"
        labMusicSinger.setText("无");  //设置播放歌手信息"无"
        labPlayedTime.setText("00:00"); //设置播放时间为"00:00"
        labTotalTime.setText("00:00");
        sliderSong.setValue(0);
        labPlayListCount.setText("0");
        labPlayListCount.setText("0");    //并且更新显示播放列表数量的组件
        System.gc();
    }

    @Override
    public void seek(Duration duration) {
        if (mediaPlayer != null){
            mediaPlayer.seek(duration);
        }
    }

    @Override
    public void switchMute() {
        if (isMute()){
            setMute(false);
            getSliderVolume().setValue(volume);
            labSoundIcon.setGraphic(ImageUtils.createImageView("NeteaseVolumeIcon.png",20,20));

        }else {
            setMute(true);
            volume = getSliderVolume().getValue(); //存储当前的值
            getSliderVolume().setValue(0);
            labSoundIcon.setGraphic(ImageUtils.createImageView("volumeCross.png",20,20));
        }
    }

    @Override
    public void setMute(boolean mute) {
        super.setMute(mute);
    }

    @Override
    public void switchPlayMode() {
        if (playMode == PlayMode.SEQUENCE){
            playMode = PlayMode.SEQUENCE_LOOP;
           labPlayModeIcon.setGraphic(ImageUtils.createImageView("NeteaseSequenceLoopMode.png",24,24));
//            WindowUtils.toastInfo(centerController.getStackPane(),new Label("顺序循环"));
        }
        else if (playMode == PlayMode.SEQUENCE_LOOP){
            playMode = PlayMode.SINGLE_LOOP;
            labPlayModeIcon.setGraphic(ImageUtils.createImageView("NeteaseSingleRoopIcon.png",24,24));
//            WindowUtils.toastInfo(centerController.getStackPane(),new Label("单曲循环"));
        }
        else if (playMode == PlayMode.SINGLE_LOOP){
            playMode = PlayMode.SHUFFLE;
            nextPlayIndexList.clear();
            lastPlayIndexList.clear();
            labPlayModeIcon.setGraphic(ImageUtils.createImageView("NeteaseShufflePlayMode.png",24,24));
//            WindowUtils.toastInfo(centerController.getStackPane(),new Label("随机播放"));
        }
        else if (playMode == PlayMode.SHUFFLE){
            playMode = PlayMode.SEQUENCE;
            labPlayModeIcon.setGraphic(ImageUtils.createImageView("NeteaseSequencePlayMode.png",24,24));
//            WindowUtils.toastInfo(centerController.getStackPane(),new Label("顺序播放"));
        }
    }

    @Override
    public boolean isPlayingThis(PlayListSong playListSong) {
        return false;
    }
}
