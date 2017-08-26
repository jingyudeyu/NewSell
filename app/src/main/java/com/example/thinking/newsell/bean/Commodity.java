package com.example.thinking.newsell.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Author: lovegod
 * Created by 123 on 2017/4/5.
 */
public class Commodity implements Serializable{
/* "priceTotal": 9998,
         "count": 2*/

    public double getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(double priceTotal) {
        this.priceTotal = priceTotal;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private double priceTotal;
    private int count;
    /**
     * cid : 1
     * sid : 1
     * cgid : 1
     * salesvolu : 6200
     * price : 3499.0
     * productname : 三星（SAMSUNG）UA49KUC30SJXXZ 49英寸 曲面 HDR 4K超高清智能电视 黑色
     * logo : https://img11.360buyimg.com/n7/jfs/t2701/117/4343853787/151157/a2a442e9/57b6cf89N535ddd1b.jpg
     * bluetoothmac : fdghjfghj
     * x : 235
     * y : 350
     * detailshow : https://img20.360buyimg.com/vc/jfs/t4684/4/4583595/648025/376dc7d8/58c7df8aN5517c294.jpg；https://img20.360buyimg.com/vc/jfs/t4498/253/8087555/130578/5398ac17/58c7e016Nb4896ed5.jpg;https://img20.360buyimg.com/vc/jfs/t4369/65/1858798668/181091/5babf5f7/58c7e021N0d7073e4.jpg;https://img20.360buyimg.com/vc/jfs/t4432/297/7923558/669455/79cfbf29/58c7e02cNbe2acd5c.jpg;
     * stock : 5600
     */

    private int cid;//商品id
    private int sid;//店铺id
    private int cgid;//类别id
    private int salesvolu;//销售量
    private double price;//价格
    private String productname;//名称
    private String logo;//Logo
    private String bluetoothmac;//绑定mac
    private int x;//X坐标
    private int y;//Y坐标
    private String detailshow;//详细展示，多个图片连接
    private int stock;//库存
    private int partstatue;//商品是否加入合作 0未加入 1已加入合作

    public int getPartstatue() {
        return partstatue;
    }

    public void setPartstatue(int partstatue) {
        this.partstatue = partstatue;
    }


    /**
     * price : 3499
     * bluetoothmac : null
     * headershow : https://img11.360buyimg.com/n1/jfs/t2755/238/4250736350/42552/5a33776/57b6ba97N708dd12d.jpg;https://img11.360buyimg.com/n1/jfs/t2902/80/4327395756/525123/1b3314a2/57b6ba9fN75832ea0.jpg;https://img11.360buyimg.com/n1/jfs/t2773/88/4301259396/528392/cae1ab6d/57b6ba83N8e058e6a.jpg;https://img11.360buyimg.com/n1/jfs/t6367/277/388721000/286463/92a66599/593e695bNc250caaf.jpg
     * param1 : 参数1
     * param2 : 参数2
     * param3 : 参数3
     * statue : 0
     * pgid : 2
     */

    private String headershow;
    private String param1;
    private String param2;
    private String param3;
    private int statue;
    private int pgid;

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

    public int getSalesvolu() {
        return salesvolu;
    }

    public void setSalesvolu(int salesvolu) {
        this.salesvolu = salesvolu;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBluetoothmac() {
        return bluetoothmac;
    }

    public void setBluetoothmac(String bluetoothmac) {
        this.bluetoothmac = bluetoothmac;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getDetailshow() {
        return detailshow;
    }

    public void setDetailshow(String detailshow) {
        this.detailshow = detailshow;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "CommodityApi{" +
                "cid=" + cid +
                ", sid=" + sid +
                ", cgid=" + cgid +
                ", salesvolu=" + salesvolu +
                ", price=" + price +
                ", productname='" + productname + '\'' +
                ", logo='" + logo + '\'' +
                ", bluetoothmac='" + bluetoothmac + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", detailshow='" + detailshow + '\'' +
                ", stock=" + stock +
                '}';
    }



    public String getHeadershow() {
        return headershow;
    }

    public void setHeadershow(String headershow) {
        this.headershow = headershow;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public String getParam3() {
        return param3;
    }

    public void setParam3(String param3) {
        this.param3 = param3;
    }

    public int getStatue() {
        return statue;
    }

    public void setStatue(int statue) {
        this.statue = statue;
    }

    public int getPgid() {
        return pgid;
    }

    public void setPgid(int pgid) {
        this.pgid = pgid;
    }
}
