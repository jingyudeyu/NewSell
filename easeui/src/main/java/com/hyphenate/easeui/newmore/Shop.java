package com.hyphenate.easeui.newmore;

import java.io.Serializable;

/**
 * *****************************************
 * Created by thinking on 2017/7/12.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class Shop implements Serializable {

    /**
     * sid : 1
     * shopname : 三星电器
     * saddress : 郑州市龙湖镇中原工学院
     * stel : 5
     * slevel : 5
     * longgitude : 113.692776
     * latitude : 34.615568
     * type : 电器
     * salesvo : 5689
     * subscrib : 经营三星电子全系列
     * logo : https://img20.360buyimg.com/popshop/jfs/t2701/34/484677369/7439/ee13e8fa/5716e2c4Nc925baf3.jpg
     * showpic : http://img2.imgtn.bdimg.com/it/u=2894292235,80494207&fm=26&gp=0.jpg
     * headershow : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1498108768331&di=2aa51541ac5a14413b9ad92cf33d3a13&imgtype=0&src=http%3A%2F%2Fjiangxi.sinaimg.cn%2F2014%2F0918%2FU10818P1332DT20140918131449.jpg
     * permitpic : http://img3.imgtn.bdimg.com/it/u=2679936579,3877774682&fm=214&gp=0.jpg
     * scope : 家电
     * city : 郑州
     */


    private int sid;
    private String shopname;
    private String saddress;
    private String stel;
    private double slevel;
    private double longgitude;
    private double latitude;
    private String type;
    private int salesvo;
    private String subscrib;
    private String logo;
    private String showpic;
    private String headershow;
    private String permitpic;
    private String scope;
    private int city;//城市id
    private int bid;//店主id

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

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getSaddress() {
        return saddress;
    }

    public void setSaddress(String saddress) {
        this.saddress = saddress;
    }

    public String getStel() {
        return stel;
    }

    public void setStel(String stel) {
        this.stel = stel;
    }

    public double getSlevel() {
        return slevel;
    }

    public void setSlevel(double slevel) {
        this.slevel = slevel;
    }

    public double getLonggitude() {
        return longgitude;
    }

    public void setLonggitude(double longgitude) {
        this.longgitude = longgitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSalesvo() {
        return salesvo;
    }

    public void setSalesvo(int salesvo) {
        this.salesvo = salesvo;
    }

    public String getSubscrib() {
        return subscrib;
    }

    public void setSubscrib(String subscrib) {
        this.subscrib = subscrib;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getShowpic() {
        return showpic;
    }

    public void setShowpic(String showpic) {
        this.showpic = showpic;
    }

    public String getHeadershow() {
        return headershow;
    }

    public void setHeadershow(String headershow) {
        this.headershow = headershow;
    }

    public String getPermitpic() {
        return permitpic;
    }

    public void setPermitpic(String permitpic) {
        this.permitpic = permitpic;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }
}
