package com.yyin.testfx.mediaplayer;

import com.yyin.testfx.models.PlayListSong;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

/**
 * @author super lollipop
 * @date 20-2-17
 */
@Data
@NoArgsConstructor
public class PlayerStatus {

    /**
     * 播放器音量
     * */
    protected double volume = 0.25;

    /**
     * 是否静音
     * */
    protected boolean mute = false;

    /**
     * 播放列表歌曲集合
     * */
    protected List<PlayListSong> playListSongs = testList();

    /**
     * 当前播放的歌曲在播放列表中位置索引
     * */
    protected int currentPlayIndex = 1;

    /**
     * 定义播放模式枚举类型,默认为顺序播放
     */
    protected PlayMode playMode = PlayMode.SEQUENCE;

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public boolean isMute() {
        return mute;
    }

    public void setMute(boolean mute) {
        this.mute = mute;
    }

    public List<PlayListSong> getPlayListSongs() {
        return playListSongs;
    }

    public void setPlayListSongs(List<PlayListSong> playListSongs) {
        this.playListSongs = playListSongs;
    }

    public int getCurrentPlayIndex() {
        return currentPlayIndex;
    }

    public void setCurrentPlayIndex(int currentPlayIndex) {
        this.currentPlayIndex = currentPlayIndex;
    }

    public PlayMode getPlayMode() {
        return playMode;
    }

    public void setPlayMode(PlayMode playMode) {
        this.playMode = playMode;
    }


    public PlayerStatus(double volume, boolean mute, List<PlayListSong> playListSongs, int currentPlayIndex, PlayMode playMode) {
        this.volume = volume;
        this.mute = mute;
        for (int i = 0; i < playListSongs.size(); i++) {
            if (playListSongs.get(i).getLabRemoveIcon() != null){
                playListSongs.get(i).setLabRemoveIcon(null);
            }
        }
        this.playListSongs = playListSongs;
        this.currentPlayIndex = currentPlayIndex;
        this.playMode = playMode;
    }

    public List<PlayListSong> testList(){
        Config config = new Config();
        List<PlayListSong> playListSongs = new LinkedList<>();
        playListSongs.add(new PlayListSong(config.getSongPlay(1373172794)));
        playListSongs.add(new PlayListSong(config.getSongPlay(400876320)));
        playListSongs.add(new PlayListSong(config.getSongPlay(3597282)));
        playListSongs.add(new PlayListSong(config.getSongPlay(636176)));
        playListSongs.add(new PlayListSong(config.getSongPlay(1466691753)));
        playListSongs.add(new PlayListSong(config.getSongPlay(1861126812)));
        playListSongs.add(new PlayListSong(config.getSongPlay(26289183)));
        playListSongs.add(new PlayListSong(config.getSongPlay(1844006141)));
        playListSongs.add(new PlayListSong(config.getSongPlay(1370359829)));
        return  playListSongs;
    }
}
