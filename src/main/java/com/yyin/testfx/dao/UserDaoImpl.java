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
    public User queryUserByUsername(String username) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where username = ?";
        return queryForOne(User.class, sql, username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where username = ? and password = ?";
        return queryForOne(User.class, sql, username,password);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(`username`,`password`,`email`) values(?,?,?)";
        return update(sql, user.getUsername(),user.getPassword(),user.getEmail());
    }

    @Override
    public User queryUserByEmailAndPassword(String email, String password) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where email = ? and password = ?";
        return queryForOne(User.class, sql,email,password);
    }
}
