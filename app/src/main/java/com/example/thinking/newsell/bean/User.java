package com.example.thinking.newsell.bean;

import java.io.Serializable;

/**
 * *****************************************
 * Created by thinking on 2017/7/10.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class User implements Serializable {


    /**
     * bid : 2
     * sid : 2
     * name : 刘**
     * password : 86864c896090eb8e3e34b232cc418eeb
     * pic : https://img20.360buyimg.com/popshop/jfs/t2365/311/2902780631/4872/72b8e3d8/56fe1ec7N0c3302e2.jpg
     * nickname : lll
     * phone : 18838150655
     * id : 412322332112
     */

    private int bid;
    private int sid;
    private String name;
    private String password;
    private String pic;
    private String nickname;
    private String phone;
    private String id;

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
