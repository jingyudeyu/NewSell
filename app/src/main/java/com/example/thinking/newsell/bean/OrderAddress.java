package com.example.thinking.newsell.bean;

import java.io.Serializable;

/**
 * *****************************************
 * Created by thinking on 2017/8/25.
 * 创建时间：
 * <p>
 * 描述：订单地址
 * <p/>
 * <p/>
 * *******************************************
 */

public class OrderAddress implements Serializable {

    /**
     * said : 40
     * uid : 3
     * name : 岳伟翔
     * sex : 0
     * phone : 15105183867
     * address : 江苏省 南京市 鼓楼区;江东街道 腾飞园社区居委会;龙江小区月光1号
     * statue : 0
     * zip : 210036
     */

    private int said;
    private int uid;
    private String name;
    private int sex;
    private String phone;
    private String address;
    private int statue;
    private String zip;

    public int getSaid() {
        return said;
    }

    public void setSaid(int said) {
        this.said = said;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatue() {
        return statue;
    }

    public void setStatue(int statue) {
        this.statue = statue;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
