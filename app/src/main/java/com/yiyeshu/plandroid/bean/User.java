package com.yiyeshu.plandroid.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by lhw on 2017/4/17.
 */
public class User extends BmobObject {
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    private String password;
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
