package com.example.thinking.newsell.bean;

import java.io.Serializable;

/**
 * *****************************************
 * Created by thinking on 2017/6/13.
 * 创建时间：
 * <p>
 * 描述：评价的数据
 * <p/>
 * <p/>
 * *******************************************
 */

public class Assess implements Serializable {
    /**
     * aid : 4
     * ogid : 1
     * cid : 1
     * uid : 1
     * hollrall : 凑合
     * detail : 凑合吧凑合吧凑合吧凑合吧凑合吧凑合吧凑合吧凑合吧凑合吧凑合吧凑合吧凑合吧凑合吧凑合吧凑合吧凑合吧凑合吧凑合吧凑合吧凑合吧凑合吧凑合吧
     * grade : 2.1
     * param : 中评
     * date : 2017-06-22 14:06:29.0
     * pics : https://img30.360buyimg.com/shaidan/s616x405_jfs/t3256/159/5305586814/134917/5b4a9fc9/58835c58N215de23e.jpg;https://img30.360buyimg.com/shaidan/s616x405_jfs/t3214/350/5772563677/151285/ef91a445/58835c79Nddd09266.jpg;https://img30.360buyimg.com/shaidan/s616x405_jfs/t4084/166/1695027883/91217/6bd95ac2/58835cacN32dc6318.jpg
     * username : liu
     * count : 1
     * bossback : 吧凑合吧凑合吧凑合吧凑合吧凑合吧凑合吧凑合吧凑合吧凑合吧凑合吧凑合吧凑合吧凑合吧凑合吧凑合吧凑合吧凑合
     */

    private int aid;//自增id
    private int ogid;//订单商品id
    private int cid;//商品id
    private int uid;//用户id
    private String hollrall;//总评
    private String detail;//细评
    private double grade;//星级
    private String param;//购买的商品参数
    private String date;//时间
    private String pics;//买家秀图
    private String username;//用户名
    private int count;//商品数量
    private String bossback;//店主回复

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getOgid() {
        return ogid;
    }

    public void setOgid(int ogid) {
        this.ogid = ogid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getHollrall() {
        return hollrall;
    }

    public void setHollrall(String hollrall) {
        this.hollrall = hollrall;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getBossback() {
        return bossback;
    }

    public void setBossback(String bossback) {
        this.bossback = bossback;
    }

}
