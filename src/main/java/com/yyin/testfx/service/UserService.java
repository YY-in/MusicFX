package com.yyin.testfx.service;

import com.yyin.testfx.models.User;

/**
 * @Author: YinZhihao
 * @Description: most implement of user service
 * @Date: Created in 22:31 2021/12/9
 */
public interface UserService {
    /**
     * 注册用户
     * @param user
     */
    public void registUser(User user);

    /**
     * @description 通过用户名验证登录
     * @param user
     * @return 如果返回null，说明登录失败，返回有值，是登录成功
     */
    public User loginByName(User user);

    /**
     * @description: 通过邮箱验证登录
     * @param user
     * @return 如果返回null，说明登录失败，返回有值，是登录成功
     */
    public User loginByEmail(User user);
    /**
     * 检查 用户名是否可用
     * @param username
     * @return 返回true表示用户名已存在，返回false表示用户名可用
     */
    public boolean existsUsername(String username);

    /**
     * @Description: 检查数据库中是否存在拥有这样邮箱的用户
     * @Date: 22:21 2021/12/10
     * @param email
     * @return 存在 true ,不存在 false
      */
    public boolean existUserEmail(String email);

}
