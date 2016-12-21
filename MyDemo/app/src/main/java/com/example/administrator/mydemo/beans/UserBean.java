package com.example.administrator.mydemo.beans;

/**
 * Created by Administrator on 2016/12/20.
 */
public class UserBean {
    private Integer userId;
    private String userName;
    private String userPwd;
    private Integer age;

    public UserBean(Integer userId, String userName, String userPwd, Integer age) {
        this.userId = userId;
        this.userName = userName;
        this.userPwd = userPwd;
        this.age = age;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
