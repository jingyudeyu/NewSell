package com.example.thinking.newsell.api;

import com.example.thinking.newsell.bean.BaseBean;
import com.example.thinking.newsell.bean.Shop;
import com.example.thinking.newsell.bean.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

public interface ShopApi {
    //设缓存有效期为1天
    long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
    //查询缓存的Cache-Control设置，使用缓存
    String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    //查询网络的Cache-Control设置。不使用缓存
    String CACHE_CONTROL_NETWORK = "max-age=0";



    @GET("shop/bid/{bid}")
    Observable<BaseBean<List<Shop>>>getShopInfo(@Path("bid")Integer bid);

    /* 根据店铺id查询一个商店(id即为店铺sid)*/
    @GET("shops/{id}")
    Observable<BaseBean<Shop>> getShopInfoById(@Path("id")Integer id);

    /*根据店铺id查看店铺访问量*/  //时间 1998-02-05
    @GET("track/see/sid/{sid}/{date}")
    Observable<BaseBean<Integer>> getSidDateCount(@Path("sid")Integer sid,@Path("date")String date);

/*    @GET("user/{phone}")//通过手机号获取用户信息
    Observable<BaseBean<User>>getUserInfo(@Path("phone")String phone);

    @GET("user/username/{username}")//通过用户名获取用户信息
    Observable<BaseBean<User>>getUserInfoByname(@Path("username")String username);*/
}
