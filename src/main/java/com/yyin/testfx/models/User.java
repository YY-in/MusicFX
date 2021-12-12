package com.yyin.testfx.models;

import com.yyin.testfx.utils.ImageUtils;

/**
 * @Author: YinZhihao
 * @Description: User model for service
 * @Date: Created in 21:33 2021/12/9
 */
public class User {

    private Integer id;
    private String username;
    private String password;
    private String email;
    private byte[] user_img;

    public byte[] getUser_img() {
        return user_img;
    }

    public void setUser_img(byte[] user_img) {
        this.user_img = user_img;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public User() {
    }

    public User(Integer id, String username, String password, String email,String path) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.user_img = ImageUtils.pathToBinaryStream(path);
    }
}
