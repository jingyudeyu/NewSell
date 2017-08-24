package com.example.thinking.newsell.bean;

import java.io.Serializable;

/**
 * *****************************************
 * Created by thinking on 2017/8/23.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class Order implements Serializable{

    /**
     * oid : 112
     * said : 40
     * uid : 3
     * sid : 2
     * count : 1
     * totalprice : 9299
     * number : 20161230000000000001
     * statue : 3
     * createtime : 2017-07-27 09:41:04
     * freight : 75.1
     * paytime : 2017-07-27 09:41:08
     * paytype : 微信支付
     * shiptime : 2017-07-27 09:41:08
     * dealtime : 2017-7-28 14:49:12
     * openstatue : 1
     */

    private int oid;
    private int said;
    private int uid;
    private int sid;
    private int count;
    private int totalprice;
    private String number;
    private int statue;
    private String createtime;
    private double freight;
    private String paytime;
    private String paytype;
    private String shiptime;
    private String dealtime;
    private int openstatue;

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getStatue() {
        return statue;
    }

    public void setStatue(int statue) {
        this.statue = statue;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public double getFreight() {
        return freight;
    }

    public void setFreight(double freight) {
        this.freight = freight;
    }

    public String getPaytime() {
        return paytime;
    }

    public void setPaytime(String paytime) {
        this.paytime = paytime;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public String getShiptime() {
        return shiptime;
    }

    public void setShiptime(String shiptime) {
        this.shiptime = shiptime;
    }

    public String getDealtime() {
        return dealtime;
    }

    public void setDealtime(String dealtime) {
        this.dealtime = dealtime;
    }

    public int getOpenstatue() {
        return openstatue;
    }

    public void setOpenstatue(int openstatue) {
        this.openstatue = openstatue;
    }
}
