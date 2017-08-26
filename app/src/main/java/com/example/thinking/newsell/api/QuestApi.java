package com.example.thinking.newsell.api;

import com.example.thinking.newsell.bean.BaseBean;
import com.example.thinking.newsell.bean.Quest;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * *****************************************
 * Created by thinking on 2017/8/14.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public interface QuestApi {
    //设缓存有效期为1天
    long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
    //查询缓存的Cache-Control设置，使用缓存
    String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    //查询网络的Cache-Control设置。不使用缓存
    String CACHE_CONTROL_NETWORK = "max-age=0";

    /*根据商品id查询疑问*/
    @GET("quests/{cid}")
    Observable<BaseBean<List<Quest>>> getCidQuest(@Path("cid") int cid);

    /*添加问题回复*/
    @FormUrlEncoded
    @POST("addReply")
    Observable<BaseBean<Quest.RepliesBean>> commitQuestReply(@FieldMap Map<String, String> map);
}
