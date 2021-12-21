package com.yyin.testfx.service.impl;

import com.yyin.testfx.dao.SongDaoImpl;
import com.yyin.testfx.dao.in.SongDao;
import com.yyin.testfx.models.PlayListSong;
import com.yyin.testfx.service.SongService;

/**
 * @Author: YinZhihao
 * @Description:
 * @Date: Created in 14:28 2021/12/21
 */
public class SongServiceImpl implements SongService {
    private SongDao songDao= new SongDaoImpl();
    @Override
    public void saveSong(PlayListSong song) {
        songDao.saveSong(song);
    }
}
