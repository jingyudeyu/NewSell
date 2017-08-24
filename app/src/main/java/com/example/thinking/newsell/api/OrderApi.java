package com.example.thinking.newsell.api;

import com.example.thinking.newsell.bean.BaseBean;
import com.example.thinking.newsell.bean.Order;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * *****************************************
 * Created by thinking on 2017/8/23.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public interface OrderApi {
    //设缓存有效期为1天
    long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
    //查询缓存的Cache-Control设置，使用缓存
    String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    //查询网络的Cache-Control设置。不使用缓存
    String CACHE_CONTROL_NETWORK = "max-age=0";

    /*根据订单状况日期查看订单数量*/      //日期 年（2017）月（2017-06）日（2017-06-09）//statue：0未付款、1代发货、2待收货、3已收货
    @GET("orders/count/{sid}/{statue}/{date}")
    Observable<BaseBean<Integer>> getBySSDOrderCount(@Path("sid")Integer sid, @Path("statue")Integer statue, @Path("date") String date);

    /*根据订单状况日期查看订单*/
    @GET("orders/content/{sid}/{statue}/{date}/{page}")
    Observable<BaseBean<List<Order>>> getBySSDOrderAll(@Path("sid") Integer sid, @Path("statue") Integer statue, @Path("date") String date, @Path("page") Integer page);

    /*根据订单状况查看订单数量*/  //日期 年（2017）月（2017-06）日（2017-06-09）
    @GET("orders/count/all/{sid}/{statue}")
    Observable<BaseBean<Integer>> getBySSOrderCount(@Path("sid")Integer sid,@Path("statue")Integer statue);

    /*根据订单状况查看订单*/
    @GET("orders/content/all/{sid}/{statue}/{page}")
    Observable<BaseBean<List<Order>>> getBySSOrderAll(@Path("sid")Integer sid,@Path("statue")Integer statue,@Path("page")Integer page);

}
