package com.example.thinking.newsell.bean;

import java.io.Serializable;

/**
 * *****************************************
 * Created by thinking on 2017/8/16.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class ShopAttention  implements Serializable {
    /**
     * said : 20
     * sid : 1
     * uid : 3
     * looktime : 2017-08-11 16:02:47
     * username : Lancer
     * userlogo : http://i1.hdslb.com/bfs/face/5e19a275cd9e5b7b3828a13e27a7195b54eb5f1b.jpg
     * shopname : 三星电器
     * shoplogo : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502085406178&di=f1bce43c34f61df623f7603e3cacd07c&imgtype=0&src=http%3A%2F%2Fimg105.job1001.com%2Fupload%2Falbum%2F2015-05-28%2F1432781273-JKKIVRY_960_600.jpg
     */

    private int said;
    private int sid;
    private int uid;
    private String looktime;
    private String username;
    private String userlogo;
    private String shopname;
    private String shoplogo;

    public int getSaid() {
        return said;
    }

    public void setSaid(int said) {
        this.said = said;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
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

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getShoplogo() {
        return shoplogo;
    }

    public void setShoplogo(String shoplogo) {
        this.shoplogo = shoplogo;
    }


}
