package com.example.thinking.newsell.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * *****************************************
 * Created by thinking on 2017/8/14.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class Quest implements Serializable{


    /**
     * qid : 1
     * cid : 1
     * uid : 3
     * username : Lancer
     * title : 大家觉得怎么样
     * asktime : 2017-05-02 18:21:12
     * up : 0
     * replies : [{"rid":8,"qid":1,"uid":1,"username":"赵赵","buystatue":1,"up":0,"content":"虽然没买贵过这款但是这家店值得信赖","retime":"2017-07-31 17:02:01"},{"rid":7,"qid":1,"uid":3,"username":"王五","buystatue":0,"up":0,"content":"没问题的","retime":"2017-07-31 17:00:55"},{"rid":6,"qid":1,"uid":3,"username":null,"buystatue":0,"up":0,"content":"可以","retime":"2017-07-31 17:00:43"},{"rid":4,"qid":1,"uid":3,"username":"张三","buystatue":0,"up":0,"content":"挺好的","retime":"2017-07-31 16:51:23"},{"rid":3,"qid":1,"uid":3,"username":"李立","buystatue":0,"up":0,"content":"应该没什么问题","retime":"2017-07-31 16:47:00"}]
     */

    private int qid;
    private int cid;
    private int uid;
    private String username;
    private String title;
    private String asktime;
    private int up;
    private int attemtionSize;
    private List<RepliesBean> replies=new ArrayList<>();

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAsktime() {
        return asktime;
    }

    public void setAsktime(String asktime) {
        this.asktime = asktime;
    }

    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public List<RepliesBean> getReplies() {
        return replies;
    }

    public void setReplies(List<RepliesBean> replies) {
        this.replies = replies;
    }

    public int getAttemtionSize() {
        return attemtionSize;
    }

    public void setAttemtionSize(int attemtionSize) {
        this.attemtionSize = attemtionSize;
    }

    public class RepliesBean implements Serializable{
        /**
         * rid : 8
         * qid : 1
         * uid : 1
         * username : 赵赵
         * buystatue : 1
         * up : 0
         * content : 虽然没买贵过这款但是这家店值得信赖
         * retime : 2017-07-31 17:02:01
         */

        private int rid;
        private int qid;
        private int uid;
        private String username;
        private int buystatue;
        private int up;
        private String content;
        private String retime;

        public int getRid() {
            return rid;
        }

        public void setRid(int rid) {
            this.rid = rid;
        }

        public int getQid() {
            return qid;
        }

        public void setQid(int qid) {
            this.qid = qid;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getBuystatue() {
            return buystatue;
        }

        public void setBuystatue(int buystatue) {
            this.buystatue = buystatue;
        }

        public int getUp() {
            return up;
        }

        public void setUp(int up) {
            this.up = up;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getRetime() {
            return retime;
        }

        public void setRetime(String retime) {
            this.retime = retime;
        }
    }
}
