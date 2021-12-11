package com.yyin.testfx.service;

import com.yyin.testfx.dao.UserDaoImpl;
import com.yyin.testfx.dao.in.UserDao;
import com.yyin.testfx.models.User;

import java.util.regex.Pattern;

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
    public User loginByName(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public User loginByEmail(User user) {
        return userDao.queryUserByEmailAndPassword(user.getEmail(),user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {

        if (userDao.queryUserByUsername(username) == null) {
            // 等于null,说明没查到，没查到表示可用
            return false;
        }
        return true;
    }

    @Override
    public boolean existUserEmail(String email) {
        if(userDao.queryUserEmail(email)==null){
            // 等于null,说明没有查询到,没查到邮箱不可用,需要注册
            return false;
        }
        return true;
    }

    @Override
    public boolean checkPassword(String password) {
        String passwordPattern="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,16}$";
        return Pattern.matches(passwordPattern,password);
    }

   @Override
    public  boolean checkEmail(String email){
        String emailPattern ="^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        return Pattern.matches(emailPattern,email);
    }
}
