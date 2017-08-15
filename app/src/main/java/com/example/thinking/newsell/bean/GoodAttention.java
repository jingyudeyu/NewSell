package com.example.thinking.newsell.bean;

import java.io.Serializable;

/**
 * *****************************************
 * Created by thinking on 2017/8/15.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class GoodAttention implements Serializable{
    /**
     * gaid : 60
     * sid : 2
     * cid : 5
     * uid : 3
     * looktime : 2017-08-13 20:20:00
     * goodsName : Xiaomi/小米 小米电视4A 43英寸49/55寸智能平板电视3S48寸现货4K
     * goodslogo : http://images.ofweek.com/Upload/News/2017-04/10/Gary/1491796023183019144.jpg
     * username : Lancer
     * userlogo : http://i1.hdslb.com/bfs/face/5e19a275cd9e5b7b3828a13e27a7195b54eb5f1b.jpg
     */

    private int gaid;
    private int sid;
    private int cid;
    private int uid;
    private String looktime;
    private String goodsName;
    private String goodslogo;
    private String username;
    private String userlogo;

    public int getGaid() {
        return gaid;
    }

    public void setGaid(int gaid) {
        this.gaid = gaid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
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

    public String getLooktime() {
        return looktime;
    }

    public void setLooktime(String looktime) {
        this.looktime = looktime;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodslogo() {
        return goodslogo;
    }

    public void setGoodslogo(String goodslogo) {
        this.goodslogo = goodslogo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserlogo() {
        return userlogo;
    }

    public void setUserlogo(String userlogo) {
        this.userlogo = userlogo;
    }
}
