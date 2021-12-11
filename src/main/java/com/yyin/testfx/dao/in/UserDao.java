package com.yyin.testfx.dao.in;

import com.yyin.testfx.models.User;

/**
 * @Author: YinZhihao
 * @Description:
 * @Date: Created in 22:12 2021/12/9
 */
public interface UserDao {
    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 如果返回null,说明没有这个用户。反之亦然
     */
    public User queryUserByUsername(String username);

    /**
     * 根据 用户名和密码查询用户信息
     * @param username
     * @param password
     * @return 如果返回null,说明用户名或密码错误,反之亦然
     */
    public User queryUserByUsernameAndPassword(String username, String password);

    /**
     * @Description: 检查email是否注册
     * @Date: 22:16 2021/12/10
     * @param email：
     */
    public User queryUserEmail(String email);
    /**
     * 保存用户信息
     * @param user
     * @return 返回-1表示操作失败，其他是sql语句影响的行数
     */
    public int saveUser(User user);
    /**
     * @Author: InfinityPenguin
     * @Description: 根据用户名和密码查询用户
     * @Date: 15:33 2021/12/11
     * @param email：
     * @param password：
     */
    public User queryUserByEmailAndPassword(String email, String password);

    /**
     * @param email
     * @param newPassword
     * @return 如果返回-1,说明执行失败返回其他表示影响的行数
     */
    public int updateUserPasswordByEmail(String email, String newPassword);
}
