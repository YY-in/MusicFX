package com.yyin.testfx.mediaplayer;

import com.yyin.testfx.models.PlayListSong;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: YinZhihao
 * @Description:
 * @Date: Created in 20:19 2021/12/20
 */
public class MyMediaPlayer extends PlayerStatus implements IMediaPlayer {
    /**定义JavaFX媒体播放器对象**/
    private MediaPlayer mediaPlayer;
    /**记录上一首播放的索引记录**/
    private List<Integer> lastPlayIndexList = new LinkedList<>();
    /**记录下一首播放的索引记录**/
    private List<Integer> nextPlayIndexList = new LinkedList<>();

    private Config config;

    @Override
    public void play() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void prepareGUI() throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {

    }

    @Override
    public void prepareMediaPlayer() {

    }

    @Override
    public void playOrPause() throws IOException {

    }

    @Override
    public void playSong() throws ReadOnlyFileException, CannotReadException, TagException, InvalidAudioFrameException, IOException, ParseException {

    }

    @Override
    public void playAll(List tableItems) throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {

    }

    @Override
    public void playAll(List tableItems, int index) throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {

    }

    @Override
    public void playLast() throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {

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

    }

    @Override
    public void seek(Duration duration) {

    }

    @Override
    public void switchMute() {

    }

    @Override
    public void switchPlayMode() {

    }

    @Override
    public boolean isPlayingThis(PlayListSong playListSong) {
        return false;
    }
}
