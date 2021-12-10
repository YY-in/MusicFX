package com.yyin.testfx.service;

import com.yyin.testfx.dao.in.UserDao;
import com.yyin.testfx.dao.UserDaoImpl;
import com.yyin.testfx.models.User;

/**
 * @Author: YinZhihao
 * @Description:
 * @Date: Created in 22:24 2021/12/9
 */
public class UserServiceImpl implements UserService{
    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {

        if (userDao.queryUserByUsername(username) == null) {
            // 等于null,说明没查到，没查到表示可用
            return false;
        }
        return true;
    }
}
