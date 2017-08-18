package com.example.thinking.newsell.bean;

import java.io.Serializable;

/**
 * *****************************************
 * Created by thinking on 2017/8/17.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class UserBuyer implements Serializable{

    /**
     * type : 0
     * user : {"uid":1,"username":"lovegod","password":null,"gender":"男","phone":"15249697121","headerpic":"http://img.jsqq.net/uploads/allimg/150111/1_150111080328_19.jpg","id":"412","name":"李立","registtime":"2017-07-11 18:44:07"}
     * boss : {"bid":1,"sid":1,"name":"gxc","password":null,"pic":"https://img20./jfs/t2365/311/2902780631/4872/72b8e3d8/56fe1ec7N0c3302e2.jpg","nickname":"guo","phone":"18838150659","id":"41272811111111"}
     */

    private int type;
    private UserBean user;
    private BossBean boss;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public BossBean getBoss() {
        return boss;
    }

    public void setBoss(BossBean boss) {
        this.boss = boss;
    }

    public static class UserBean {
        /**
         * uid : 1
         * username : lovegod
         * password : null
         * gender : 男
         * phone : 15249697121
         * headerpic : http://img.jsqq.net/uploads/allimg/150111/1_150111080328_19.jpg
         * id : 412
         * name : 李立
         * registtime : 2017-07-11 18:44:07
         */

        private int uid;
        private String username;
        private Object password;
        private String gender;
        private String phone;
        private String headerpic;
        private String id;
        private String name;
        private String registtime;

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

        public Object getPassword() {
            return password;
        }

        public void setPassword(Object password) {
            this.password = password;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getHeaderpic() {
            return headerpic;
        }

        public void setHeaderpic(String headerpic) {
            this.headerpic = headerpic;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRegisttime() {
            return registtime;
        }

        public void setRegisttime(String registtime) {
            this.registtime = registtime;
        }
    }

    public static class BossBean {
        /**
         * bid : 1
         * sid : 1
         * name : gxc
         * password : null
         * pic : https://img20./jfs/t2365/311/2902780631/4872/72b8e3d8/56fe1ec7N0c3302e2.jpg
         * nickname : guo
         * phone : 18838150659
         * id : 41272811111111
         */

        private int bid;
        private int sid;
        private String name;
        private Object password;
        private String pic;
        private String nickname;
        private String phone;
        private String id;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getPassword() {
            return password;
        }

        public void setPassword(Object password) {
            this.password = password;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
