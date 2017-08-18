package com.hyphenate.easeui.newmore;


import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.android.schedulers.*;
/**
 * *****************************************
 * Created by thinking on 2017/7/10.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class NetWorks extends RetrofitUtils {


    protected static final UserApi userApi = getRetrofit().create(UserApi.class);


    //根据手机号码区分是商家还是用户
    public static void knowByPhone(String phone, BaseObserver<UserBuyer> phoneObserver){
        setSubscribe(userApi.knowByPhone(phone),phoneObserver);
    }
    //根据店主bid 获取店铺信息
    public static void getShopInfo(Integer bid, BaseObserver<Shop> shopBaseObserver) {
        setSubscribe(userApi.getShopInfo(bid), shopBaseObserver);
    }

    /**
     * 插入观察者
     *
     * @param observable
     * @param observer
     * @param <T>
     */
    public static <T> void setSubscribe(Observable<BaseBean<T>> observable, BaseObserver<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())//子线程访问网络
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(observer);
    }

}
