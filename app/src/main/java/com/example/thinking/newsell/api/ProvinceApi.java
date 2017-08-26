package com.example.thinking.newsell.api;

import com.example.thinking.newsell.bean.BaseBean;
import com.example.thinking.newsell.bean.Province;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * *****************************************
 * Created by thinking on 2017/7/29.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public interface ProvinceApi {
    //设缓存有效期为1天
    long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
    //查询缓存的Cache-Control设置，使用缓存
    String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    //查询网络的Cache-Control设置。不使用缓存
    String CACHE_CONTROL_NETWORK = "max-age=0";

    /*获取所有的省份信息*/
    @GET("allprovince")
    Observable<BaseBean<List<Province>>> getAllProvince();
}
