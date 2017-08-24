package com.example.thinking.newsell.api;

import com.example.thinking.newsell.bean.BaseBean;
import com.example.thinking.newsell.bean.Commodity;
import com.example.thinking.newsell.bean.Shop;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

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

public interface CommodityApi {
    //设缓存有效期为1天
    long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
    //查询缓存的Cache-Control设置，使用缓存
    String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    //查询网络的Cache-Control设置。不使用缓存
    String CACHE_CONTROL_NETWORK = "max-age=0";

    @GET("goods/shop/{sid}")
    Observable<BaseBean<List<Commodity>>>getIDshopgoods(@Path("sid")Integer sid);

    @GET("goods/{id}")
    Observable<BaseBean<Commodity>>getIDgood(@Path("id")Integer cid);

    @GET("goods/sid/cgid/{sid}/{cgid}")
    Observable<BaseBean<List<Commodity>>>getSidCgidgoods(@Path("sid")Integer sid,@Path("cgid")Integer cgid);


    @PUT("goods/{cid}/{statue}")
    Observable<BaseBean<Commodity>>putStatueGood(@Path("cid")int cid, @Path("statue") int statue);


    @GET("goods/statue/{sid}/{statue}")
    Observable<BaseBean<List<Commodity>>> getGoodStatus(@Path("sid")int sid,@Path("statue")int statue);



/*    @GET("user/{phone}")//通过手机号获取用户信息
    Observable<BaseBean<User>>getUserInfo(@Path("phone")String phone);

    @GET("user/username/{username}")//通过用户名获取用户信息
    Observable<BaseBean<User>>getUserInfoByname(@Path("username")String username);*/
}
