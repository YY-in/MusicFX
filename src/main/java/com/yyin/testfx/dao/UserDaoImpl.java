package com.yyin.testfx.dao;

import com.yyin.testfx.dao.in.UserDao;
import com.yyin.testfx.models.User;

/**
 * @Author: YinZhihao
 * @Description:
 * @Date: Created in 22:13 2021/12/9
 */
public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserImgByUserName(String userName) {
        String sql = "select `user_img` from t_user where username = ?";
        return queryForOne(User.class,sql,userName);
    }

    @Override
    public User queryUserByUserName(String userName) {
        String sql = "select `uid`,`username`,`password`,`email`,`user_img` from t_user where username = ?";
        return queryForOne(User.class, sql, userName);
    }

    @Override
    public User queryUserByUsernameAndPassword(String userName, String password) {
        String sql = "select `uid`,`username`,`password`,`email` from t_user where username = ? and password = ?";
        return queryForOne(User.class, sql, userName,password);
    }

    @Override
    public User queryUserEmail(String email) {
        String sql = "select `uid`,`username`,`password`,`email` from t_user where email = ?";
        return queryForOne(User.class,sql,email);
    }

    @Override
    public User queryUserByEmailAndPassword(String email, String password) {
        String sql = "select `uid`,`username`,`password`,`email` from t_user where email = ? and password = ?";
        return queryForOne(User.class, sql,email,password);
    }

    @Override
    public int updateUserPasswordByEmail(String email, String newPassword) {
        String sql ="update t_user set `password` = ? where email= ?";
        return update(sql,newPassword,email);
    }
    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(`username`,`password`,`email`,`user_img`) values(?,?,?,?)";
        return update(sql, user.getUsername(),user.getPassword(),user.getEmail(),user.getUser_img());
    }
}
