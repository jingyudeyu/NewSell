package com.example.thinking.newsell.api;

import com.example.thinking.newsell.bean.BaseBean;
import com.example.thinking.newsell.bean.Commodity;
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


    /*查店铺信息*/
    @GET("shop/bid/{bid}")
    Observable<BaseBean<List<Shop>>> getShopInfo(@Path("bid") Integer bid);

    /* 根据店铺id查询一个商店(id即为店铺sid)*/
    @GET("shops/{id}")
    Observable<BaseBean<Shop>> getShopInfoById(@Path("id") Integer id);

    /*根据店铺id查看店铺访问量*/  //时间 1998-02-05
    @GET("track/see/sid/{sid}/{date}")
    Observable<BaseBean<Integer>> getSidDateCount(@Path("sid") Integer sid, @Path("date") String date);

    /* 根据店铺id日期查看总销售额*///日期 年（2017）月（2017-06）日（2017-06-09）
    @GET("orgood/shop/sale/total/{sid}/{date}")
    Observable<BaseBean<Double>> getSidDateSales(@Path("sid") Integer sid, @Path("date") String date);

    /*根据店铺id日期查看总销售量*/
    @GET("orgood/shop/sale/count/{sid}/{date}")
    Observable<BaseBean<Integer>> getSidDateSalesCount(@Path("sid") Integer sid, @Path("date") String date);

    /*根据店铺查看店铺商品关注量总数*/
    @GET("goodslook/sid/1")
    Observable<BaseBean<Integer>> getGoodAttention(@Path("sid") Integer sid);

    /*根据店铺id查看所有销售量*/
    @GET("sales/count/sid/{sid}")
    Observable<BaseBean<Integer>> getSidSalesVolume(@Path("sid") Integer sid);

    /*根据店铺id查看所有销售额*/
    @GET("sales/total/sid/{sid}")
    Observable<BaseBean<Double>> getSidSalesTotal(@Path("sid") Integer sid);

    /*根据店铺id分页查看所有销售情况*/
    @GET("orgood/goods/total/sid/{sid}/{page}")
    Observable<BaseBean<List<Commodity>>> getGoodSales(@Path("sid") Integer sid, @Path("page") Integer page);
}
