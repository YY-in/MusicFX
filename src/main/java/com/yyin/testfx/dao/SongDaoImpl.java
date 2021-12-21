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
        String sql = "INSERT INTO t_music(`mid`,`title`,`singer_name`,`album_name`,`song_time`,`cover`,`lyric`,`link`)VALUES(?,?,?,?,?,?,?,?)";
        return update(sql, song.getId(),song.getName(),song.getSinger(),song.getAlbum(),song.getTotalTime(),song.getImageURL(),song.getLyric(),song.getResource());
    }
}
