package com.yyin.testfx.dao;

import com.yyin.testfx.dao.in.SongDao;
import com.yyin.testfx.models.PlayListSong;

/**
 * @Author: YinZhihao
 * @Description:
 * @Date: Created in 19:48 2021/12/19
 */
public class SongDaoImpl extends BaseDao implements SongDao {
    @Override
    public PlayListSong querySongByName(String songName) {
        return null;
    }

    @Override
    public PlayListSong querySongById(int id) {
        return null;
    }

    @Override
    public PlayListSong queryUserByUsernameAndPassword(String username, String password) {
        return null;
    }

    @Override
    public int saveSong(PlayListSong song) {
        String sql = "INSERT INTO t_song(`id`,`song_name`,`singer`,`album_name`,`song_time`,`resource`,`lyric`,`cover_url`)VALUES(?,?,?,?,?,?,?,?)";
        return update(sql, song.getId(),song.getName(),song.getSinger(),song.getAlbum(),song.getTotalTime(),song.getResource(),song.getLyric(),song.getImageURL());
    }
}
