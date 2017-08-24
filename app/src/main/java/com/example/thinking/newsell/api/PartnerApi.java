package com.example.thinking.newsell.api;

import com.example.thinking.newsell.bean.BaseBean;
import com.example.thinking.newsell.bean.Partner;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * *****************************************
 * Created by thinking on 2017/7/31.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public interface PartnerApi {
    //设缓存有效期为1天
    long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
    //查询缓存的Cache-Control设置，使用缓存
    String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    //查询网络的Cache-Control设置。不使用缓存
    String CACHE_CONTROL_NETWORK = "max-age=0";

    @GET("partner/city/{ctid}/{page}")
    Observable<BaseBean<List<Partner>>>getCityParner(@Path("ctid")Integer ctid, @Path("page")Integer page);

    @FormUrlEncoded
    @POST("addPubGood")
    Observable<BaseBean<Partner>>postAddGood(@FieldMap Map<String,Object> addPartner);

    @GET("partner/goods/{cid}")
    Observable<BaseBean<Partner>>getPartnerGood(@Path("cid")Integer cid);

    @GET("partner/shop/goods/{sid}")
    Observable<BaseBean<List<Partner>>>getShopGoods(@Path("sid")Integer sid);

    @GET("partner/all/{page}")
    Observable<BaseBean<List<Partner>>>getAllPartners(@Path("page")Integer page);

    @GET("partner/goods/name")
    Observable<BaseBean<List<Partner>>>getNamePartners(@Query("goodsname") String goodsname);


    @GET("partner/goods/name/city/cate")
    Observable<BaseBean<List<Partner>>> get3Partners(@Query("goodsname")String goodsname,@Query("ctid")Integer ctid,@Query("cgid")Integer cgid,@Query("page")int page);
    //页数（从1开始）
    @GET("partner/goods/name/city/cate")
    Observable<BaseBean<List<Partner>>> get3PartnersBynamecity(@QueryMap Map<String,Object> map);
    //页

    @PUT("partner/intent/{psid}")
    Observable<BaseBean<Partner>> getPartnerIntent(@Path("psid")Integer psid);
}
