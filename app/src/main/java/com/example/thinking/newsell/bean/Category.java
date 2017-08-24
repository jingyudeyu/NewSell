package com.example.thinking.newsell.bean;

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

public class Category implements Serializable {

    /**
     * cgid : 1
     * cid : 1
     * big : 家用电器
     * small : 电视
     * secend : 曲面电视
     * logo : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495710764607&di=f4e215e19ff99420160752d3f3cf8d29&imgtype=0&src=http%3A%2F%2Fy2.ifengimg.com%2Fe42a62d6e58775df%2F2015%2F0309%2Fre_54fd13ff6b3da.jpg
     */

    private int cgid;
    private int cid;
    private String big;
    private String small;
    private String secend;
    private String logo;



    private int ishead;
    public int getIshead() {
        return ishead;
    }

    public void setIshead(int ishead) {
        this.ishead = ishead;
    }

    public int getCgid() {
        return cgid;
    }

    public void setCgid(int cgid) {
        this.cgid = cgid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getBig() {
        return big;
    }

    public void setBig(String big) {
        this.big = big;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getSecend() {
        return secend;
    }

    public void setSecend(String secend) {
        this.secend = secend;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
