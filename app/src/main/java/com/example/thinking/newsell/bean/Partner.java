package com.example.thinking.newsell.bean;

import java.io.Serializable;

/**
 * *****************************************
 * Created by thinking on 2017/7/31.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class Partner implements Serializable {


    /**
     * psid : 3
     * cid : 3
     * sid : 1
     * cgid : 1
     * pubtime : 2017-07-27 14:46:14
     * reason : 挺好的
     * count : 0
     * star : 4
     * ctid : 152
     * intentcount : 0
     * goodsname : 小米（MI）小米电视3S 65英寸 分体曲面 4K超高清超薄金属机身64位智能网络液晶平板电视（含小米电视主机） L65M4-AQ
     * goodslogo : https://img14.360buyimg.com/n7/jfs/t4252/328/1463643177/249762/380ffbf7/58c26130N3cd0120f.jpg
     * city : 郑州市
     * price : 9299
     * saleCount : 13000
     * shopname : 三星电器
     * shoplogo : https://img20.360buyimg.com/popshop/jfs/t2701/34/484677369/7439/ee13e8fa/5716e2c4Nc925baf3.jpg
     */

    private int psid;//自增id
    private int cid;//商品id
    private int sid;//商店id
    private int cgid;//类别id
    private String pubtime;//提交时间
    private String reason;//合作理由
    private int count;//合作数量
    private int star;//推荐星级
    private int ctid;//城市id
    private int intentcount;//意向人数


    private String goodsname;//商品名称
    private String goodslogo;//商品logo
    private String city;//城市
    private String price;//价格
    private int saleCount;//销售量
    private String shopname;//商店名称
    private String shoplogo;//商店logo

    public int getPsid() {
        return psid;
    }

    public void setPsid(int psid) {
        this.psid = psid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getCgid() {
        return cgid;
    }

    public void setCgid(int cgid) {
        this.cgid = cgid;
    }

    public String getPubtime() {
        return pubtime;
    }

    public void setPubtime(String pubtime) {
        this.pubtime = pubtime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getCtid() {
        return ctid;
    }

    public void setCtid(int ctid) {
        this.ctid = ctid;
    }

    public int getIntentcount() {
        return intentcount;
    }

    public void setIntentcount(int intentcount) {
        this.intentcount = intentcount;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public String getGoodslogo() {
        return goodslogo;
    }

    public void setGoodslogo(String goodslogo) {
        this.goodslogo = goodslogo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
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
