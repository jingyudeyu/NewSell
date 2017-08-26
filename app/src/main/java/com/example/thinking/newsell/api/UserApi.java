package com.example.thinking.newsell.api;

import com.bumptech.glide.load.model.stream.StreamResourceLoader;
import com.example.thinking.newsell.bean.BaseBean;
import com.example.thinking.newsell.bean.User;
import com.example.thinking.newsell.bean.UserBuyer;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * *****************************************
 * Created by thinking on 2017/7/10.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public interface UserApi {
    //设缓存有效期为1天
    long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
    //查询缓存的Cache-Control设置，使用缓存
    String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    //查询网络的Cache-Control设置。不使用缓存
    String CACHE_CONTROL_NETWORK = "max-age=0";


/*    @GET("user/{phone}")//通过手机号获取用户信息
    Observable<BaseBean<User>>getUserInfo(@Path("phone")String phone);

    @GET("user/username/{username}")//通过用户名获取用户信息
    Observable<BaseBean<User>>getUserInfoByname(@Path("username")String username);*/


    /*商店老板登录*/
    @POST("bosslogin")
    Observable<BaseBean<User>>postLogin(@Query("Login")String phone_name,@Query("password")String password);

    /*修改密码*/
    @FormUrlEncoded
    @PUT("boss/pass/{id}")
    Observable<BaseBean<User>>putNewPassword(@Path("id")int id, @Field("password")String password);

    /*修改昵称*/
    @FormUrlEncoded
    @PUT("boss/nick/{id}")
    Observable<BaseBean<User>>putNewNicename(@Path("id")int id,@Field("nick")String nicename);

    /*修改店主手机号*/
    @FormUrlEncoded
    @PUT("boss/phone/{id}")
    Observable<BaseBean<User>>putNewPhone(@Path("id")int id,@Field("phone")String phone);

    /*根据店主id查找店主*/
    @GET("boss/id/{id}")
    Observable<BaseBean<User>>getUserInfo(@Path("id")int id);

    /*根据店铺id查找店主*/
    @GET("boss/shop/{sid}")
    Observable<BaseBean<User>>getUserInfoBySiD(@Path("sid")int sid);

    /*根据手机号码区分是商家还是用户*/
    @GET("boss/user/phone/{phone}")
    Observable<BaseBean<UserBuyer>> knowByPhone(@Path("phone")String phone);
}
