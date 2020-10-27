package com.example.demo.Entity;
//author:孙宝臻
//date:2020-10-26
//function:用户类
public class User {
//    用户id
    private String id;
//    用户名
    private String username;
//    密码
    private String password;
//    头像
    private String avatar;

    public User() {
    }

    public User(String id, String username, String password, String avatar) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
