package com.example.thinking.newsell.api;

import com.example.thinking.newsell.bean.BaseBean;
import com.example.thinking.newsell.bean.User;
import com.example.thinking.newsell.utils.retrofitRxjava.RetrofitUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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

    public static void getUserInfo(String phone, BaseObserver<User> userObservable) {//通过手机号获取用户信息
        setSubscribe(userApi.getUserInfo(phone), userObservable);
    }

    public static void getUserInfoByname(String username, BaseObserver<User> userinfoBaseObserver) {
        setSubscribe(userApi.getUserInfoByname(username), userinfoBaseObserver);

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
