package com.example.thinking.newsell.api;

import com.example.thinking.newsell.bean.BaseBean;
import com.example.thinking.newsell.bean.Category;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Part;
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

public interface CategoryApi {
    //设缓存有效期为1天
    long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
    //查询缓存的Cache-Control设置，使用缓存
    String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    //查询网络的Cache-Control设置。不使用缓存
    String CACHE_CONTROL_NETWORK = "max-age=0";

    @GET("categorys/id/{id}")
    Observable<BaseBean<Category>> getCategory(@Path("id") int id);

    @GET("categorys/sid/{sid}")
    Observable<BaseBean<List<Category>>> getSidCategory(@Path("sid")int sid);

    @GET("categorys")
    Observable<BaseBean<List<Category>>>getAllCategory();

    @GET("categorys/goodsname")
    Observable<BaseBean<List<Category>>> getNameCategory(@Query("goodsname")String goodsname);

    @GET("categorys/small/{small}")
    Observable<BaseBean<List<Category>>> getsmallCategory(@Path("small")String small);
}
