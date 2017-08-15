package com.example.thinking.newsell.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * *****************************************
 * Created by thinking on 2017/8/14.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class Buyer implements Serializable {


    /**
     * uid : 1
     * username : lovegod
     * password : 86864c896090eb8e3e34b232cc418eeb
     * gender : 男
     * phone : 15249697121
     * headerpic : http://img.jsqq.net/uploads/allimg/150111/1_150111080328_19.jpg
     * id : 4127281999010112212
     * name : 周周
     * registtime : null
     */

    private int uid;
    private String username;
    private String password;
    private String gender;
    private String phone;
    private String headerpic;
    private long id;
    private String name;
    private String registtime;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHeaderpic() {
        return headerpic;
    }

    public void setHeaderpic(String headerpic) {
        this.headerpic = headerpic;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegisttime() {
        return registtime;
    }

    public void setRegisttime(String registtime) {
        this.registtime = registtime;
    }
}
