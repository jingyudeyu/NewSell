package com.example.thinking.newsell.api;

import com.example.thinking.newsell.bean.Assess;
import com.example.thinking.newsell.bean.BaseBean;
import com.example.thinking.newsell.bean.Shop;
import com.example.thinking.newsell.bean.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * *****************************************
 * Created by thinking on 2017/7/21.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public interface AssessApi {
    //设缓存有效期为1天
    long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
    //查询缓存的Cache-Control设置，使用缓存
    String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    //查询网络的Cache-Control设置。不使用缓存
    String CACHE_CONTROL_NETWORK = "max-age=0";


    /*根据商品id查找评价信息*/
    @GET("assess/{cid}")
    Observable<BaseBean<List<Assess>>> getAllAssess(@Path("cid")Integer cid);


    /*店家回复评论*/
    @FormUrlEncoded
    @PUT("backAssess/{aid}")
    Observable<BaseBean<Assess>>putBackAssess(@Path("aid")Integer aid, @Field("back")String back);

    /*根据商品id，获取评价总条数*/
    @GET("assess/count/{cid}")
    Observable<BaseBean<Integer>>getAssessCount(@Path("cid")int cid);

    /*根据商品id获取最新评论信息*/
    @GET("assess/new/{cid}")
    Observable<BaseBean<Assess>>getNewAssess(@Path("cid")int cid);
}
