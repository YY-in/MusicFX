package com.yyin.testfx.dao.in;

import com.yyin.testfx.models.PlayListSong;

/**
 * @Author: YinZhihao
 * @Description:
 * @Date: Created in 20:53 2021/12/19
 */
public interface SongDao {
    /**
     * 根据歌曲名查询歌曲信息
     * @date: 15:51 2021/12/12
     * @param songName
     */
    public PlayListSong querySongByName(String songName);
    /**
     * 根据id查询歌曲信息
     * @param id 歌曲id
     * @return 如果返回null,说明没有这个用户。反之亦然
     */
    public PlayListSong querySongById(int id);

    /**
     * 根据 用户名和密码查询用户信息
     * @param username
     * @param password
     * @return 如果返回null,说明用户名或密码错误,反之亦然
     */
    public PlayListSong queryUserByUsernameAndPassword(String username, String password);


    /**
     * 保存歌曲信息
     * @param song
     * @return 返回-1表示操作失败，其他是sql语句影响的行数
     */
    public int saveSong(PlayListSong song);
}
