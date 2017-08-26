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

    /*根据类别id查找*/
    @GET("categorys/id/{id}")
    Observable<BaseBean<Category>> getCategory(@Path("id") int id);

    /*根据店铺id查找分类*/
    @GET("categorys/sid/{sid}")
    Observable<BaseBean<List<Category>>> getSidCategory(@Path("sid")int sid);

    /*查找所有*/
    @GET("categorys")
    Observable<BaseBean<List<Category>>>getAllCategory();

    /*根据商品名称模糊查询分类*/
    @GET("categorys/goodsname")
    Observable<BaseBean<List<Category>>> getNameCategory(@Query("goodsname")String goodsname);

    /*根据小一类类别查找类别信息*/
    @GET("categorys/small/{small}")
    Observable<BaseBean<List<Category>>> getsmallCategory(@Path("small")String small);
}
