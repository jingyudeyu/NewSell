package com.example.thinking.newsell.bean;

import java.io.Serializable;

/**
 * *****************************************
 * Created by thinking on 2017/8/24.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class OrderGood implements Serializable {

    /**
     * ogid : 141
     * oid : 112
     * cid : 3
     * sid : 2
     * count : 1
     * totalprice : 9299
     * param : 55寸
     * evaluatestatue : 0
     * goodsname : 小米（MI）小米电视3S 65英寸 分体曲面 4K超高清超薄金属机身64位智能网络液晶平板电视（含小米电视主机） L65M4-AQ
     * logo : https://img14.360buyimg.com/n7/jfs/t4252/328/1463643177/249762/380ffbf7/58c26130N3cd0120f.jpg
     */

    private int ogid;
    private int oid;
    private int cid;
    private int sid;
    private int count;
    private int totalprice;
    private String param;
    private int evaluatestatue;
    private String goodsname;
    private String logo;

    public int getOgid() {
        return ogid;
    }

    public void setOgid(int ogid) {
        this.ogid = ogid;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public int getEvaluatestatue() {
        return evaluatestatue;
    }

    public void setEvaluatestatue(int evaluatestatue) {
        this.evaluatestatue = evaluatestatue;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
