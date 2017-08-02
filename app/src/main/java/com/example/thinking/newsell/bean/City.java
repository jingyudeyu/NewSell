package com.example.thinking.newsell.bean;

import java.io.Serializable;

/**
 * *****************************************
 * Created by thinking on 2017/7/29.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class City implements Serializable {

    /**
     * cid : 1
     * pid : 1
     * citycode : 110100000000
     * cityname : 市辖区
     * url : http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2016/11/1101.html
     * mark : 100
     */

    private int cid;
    private int pid;
    private String citycode;
    private String cityname;
    private String url;
    private int mark;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
