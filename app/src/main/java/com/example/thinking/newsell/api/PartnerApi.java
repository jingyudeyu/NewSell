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

    /*根据城市id查询合作商品*/
    @GET("partner/city/{ctid}/{page}")
    Observable<BaseBean<List<Partner>>>getCityParner(@Path("ctid")Integer ctid, @Path("page")Integer page);

    /* 添加合作商品*/
    @FormUrlEncoded
    @POST("addPubGood")
    Observable<BaseBean<Partner>>postAddGood(@FieldMap Map<String,Object> addPartner);

    /*根据商品id查询商品合作信息*/
    @GET("partner/goods/{cid}")
    Observable<BaseBean<Partner>>getPartnerGood(@Path("cid")Integer cid);

    /*根据店铺id查找发布的商品*/
    @GET("partner/shop/goods/{sid}")
    Observable<BaseBean<List<Partner>>>getShopGoods(@Path("sid")Integer sid);

    /*查询所有合作商品（分页）*/
    @GET("partner/all/{page}")
    Observable<BaseBean<List<Partner>>>getAllPartners(@Path("page")Integer page);

    /*根据商品名称模糊查询所有商品*/
    @GET("partner/goods/name")
    Observable<BaseBean<List<Partner>>>getNamePartners(@Query("goodsname") String goodsname);


    /*根据商品名称和城市id分类id查询*/
    @GET("partner/goods/name/city/cate")
    Observable<BaseBean<List<Partner>>> get3Partners(@Query("goodsname")String goodsname,@Query("ctid")Integer ctid,@Query("cgid")Integer cgid,@Query("page")int page);
    //页数（从1开始）


    /*修改有合作意向的商品意向数量*/
    @PUT("partner/intent/{psid}")
    Observable<BaseBean<Partner>> getPartnerIntent(@Path("psid")Integer psid);

    /*根据店铺id和日期查看合作*/
    @GET("partner/goods/sid/{sid}/{date}/{page}")
    Observable<BaseBean<List<Partner>>> getBySDPartenrs(@Path("sid")Integer sid,@Path("date")String date,@Path("page")Integer page);
}
