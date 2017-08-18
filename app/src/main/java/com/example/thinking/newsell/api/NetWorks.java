package com.example.thinking.newsell.api;

import com.example.thinking.newsell.bean.Assess;
import com.example.thinking.newsell.bean.BaseBean;
import com.example.thinking.newsell.bean.Buyer;
import com.example.thinking.newsell.bean.Category;
import com.example.thinking.newsell.bean.City;
import com.example.thinking.newsell.bean.Commodity;
import com.example.thinking.newsell.bean.GoodAttention;
import com.example.thinking.newsell.bean.Partner;
import com.example.thinking.newsell.bean.Province;
import com.example.thinking.newsell.bean.Quest;
import com.example.thinking.newsell.bean.Shop;
import com.example.thinking.newsell.bean.ShopAttention;
import com.example.thinking.newsell.bean.User;
import com.example.thinking.newsell.bean.UserBuyer;
import com.example.thinking.newsell.utils.retrofitRxjava.RetrofitUtils;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.R.attr.id;

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
    protected static final ShopApi shopApi = getRetrofit().create(ShopApi.class);
    protected static final CommodityApi commodityApi = getRetrofit().create(CommodityApi.class);
    protected static final CategoryApi categoryApi = getRetrofit().create(CategoryApi.class);
    protected static final AssessApi assessApi = getRetrofit().create(AssessApi.class);
    protected static final ProvinceApi provinceApi = getRetrofit().create(ProvinceApi.class);
    protected static final CityApi cityApi = getRetrofit().create(CityApi.class);
    protected static final PartnerApi partnerApi = getRetrofit().create(PartnerApi.class);
    protected static final QuestApi questApi = getRetrofit().create(QuestApi.class);
    protected static final BuyerApi buyerApi = getRetrofit().create(BuyerApi.class);
    protected static final AttentionsApi attentionApi = getRetrofit().create(AttentionsApi.class);

    public static void getShopInfo(Integer bid, BaseObserver<Shop> shopBaseObserver) {
        setSubscribe(shopApi.getShopInfo(bid), shopBaseObserver);
    }

    /**
     * 有关商品部分
     */
    public static void getIDshopgoods(int sid, BaseObserver<List<Commodity>> goodsBaseObserver) {
        setSubscribe(commodityApi.getIDshopgoods(sid), goodsBaseObserver);
    }

    public static void getIDgood(int cid, BaseObserver<Commodity> goodBaseObserver) {
        setSubscribe(commodityApi.getIDgood(cid), goodBaseObserver);
    }

    public static void getSidCgidgoods(int sid, int cgid, BaseObserver<List<Commodity>> categorygoodsBaseObserver) {
        setSubscribe(commodityApi.getSidCgidgoods(sid, cgid), categorygoodsBaseObserver);
    }

    /*有关关注商品*/
    public static void getGoodAttention(Integer cid, Integer page, BaseObserver<List<GoodAttention>> goodAttentionObserver) {
        setSubscribe(attentionApi.getGoodAttention(cid, page), goodAttentionObserver);
    }

    /*有关关注店铺*/
    public static void getShopAttention(Integer sid, Integer page, BaseObserver<List<ShopAttention>> shopAttentionObserver) {
        setSubscribe(attentionApi.getShopAttention(sid, page), shopAttentionObserver);
    }

    /*有关关注店铺数量*/
    public static void getShopAttentionSize(Integer sid, BaseObserver<Integer> shopAttSizeObserver) {
        setSubscribe(attentionApi.getShopAttentionSize(sid), shopAttSizeObserver);
    }

    /**
     * 有关问题部分
     */
    public static void getCidQuest(int cid, BaseObserver<List<Quest>> cidQuestObserver) {
        setSubscribe(questApi.getCidQuest(cid), cidQuestObserver);
    }

    public static void commitQuestReply(Map<String, String> map, BaseObserver<Quest.RepliesBean> questObserver) {
        setSubscribe(questApi.commitQuestReply(map), questObserver);
    }

    //buyer买家用户
    public static void getIdInfo(Integer id, BaseObserver<Buyer> buyerBaseObserver) {
        setSubscribe(buyerApi.getIdInfo(id), buyerBaseObserver);
    }

    //根据手机号码区分是商家还是用户
    public static void knowByPhone(String phone, BaseObserver<UserBuyer> phoneObserver){
        setSubscribe(userApi.knowByPhone(phone),phoneObserver);
    }


    /**
     * 有关分类部分
     */
    public static void getCategory(int id, BaseObserver<Category> categoryObserver) {
        setSubscribe(categoryApi.getCategory(id), categoryObserver);
    }

    public static void getSidCategory(int sid, BaseObserver<List<Category>> categoriesObserver) {
        setSubscribe(categoryApi.getSidCategory(sid), categoriesObserver);
    }

    public static void getAllCategory(BaseObserver<List<Category>> categoryObserver) {
        setSubscribe(categoryApi.getAllCategory(), categoryObserver);
    }

    public static void getNameCategory(String goodsname, BaseObserver<List<Category>> goodnameObserver) {
        setSubscribe(categoryApi.getNameCategory(goodsname), goodnameObserver);
    }

    public static void getsmallCategory(String small, BaseObserver<List<Category>> smallObserver) {
        setSubscribe(categoryApi.getsmallCategory(small), smallObserver);
    }

    /**
     * 有关用户部分
     */
    public static void postLogin(String phone_name, String password, BaseObserver<User> userObservable) {//通过手机号获取用户信息
        setSubscribe(userApi.postLogin(phone_name, password), userObservable);
    }

    public static void putNewPassword(int id, String password, BaseObserver<User> userpwObserver) {
        setSubscribe(userApi.putNewPassword(id, password), userpwObserver);
    }

    public static void putNewNicename(int id, String nicename, BaseObserver<User> usernicenmObserver) {
        setSubscribe(userApi.putNewNicename(id, nicename), usernicenmObserver);
    }

    public static void putNewPhone(int id, String phone, BaseObserver<User> userphoneObserver) {
        setSubscribe(userApi.putNewPhone(id, phone), userphoneObserver);
    }

    public static void getUserInfo(int id, BaseObserver<User> userinfoObserver) {
        setSubscribe(userApi.getUserInfo(id), userinfoObserver);
    }

    public static void getUserInfoBySiD(int sid, BaseObserver<User> userinfoObserver) {
        setSubscribe(userApi.getUserInfoBySiD(sid), userinfoObserver);
    }

    /**
     * 有关评价部分
     */
    public static void getAllAssess(Integer cid, BaseObserver<List<Assess>> assessObservable) {
        setSubscribe(assessApi.getAllAssess(cid), assessObservable);
    }

    public static void putBackAssess(Integer aid, String back, BaseObserver<Assess> assessbackObserver) {
        setSubscribe(assessApi.putBackAssess(aid, back), assessbackObserver);
    }

    public static void getAssessCount(int cid, BaseObserver<Integer> assesscountObserver) {
        setSubscribe(assessApi.getAssessCount(cid), assesscountObserver);
    }

    public static void getNewAssess(int cid, BaseObserver<Assess> newassessObserver) {
        setSubscribe(assessApi.getNewAssess(cid), newassessObserver);
    }

    public static void putStatueGood(int cid, int statue, BaseObserver<Commodity> statusObserver) {
        setSubscribe(commodityApi.putStatueGood(cid, statue), statusObserver);
    }

    public static void getGoodStatus(int sid, int statue, BaseObserver<List<Commodity>> goodstatueObserver) {
        setSubscribe(commodityApi.getGoodStatus(sid, statue), goodstatueObserver);
    }

    /**
     * 有关省
     */
    public static void getAllProvince(BaseObserver<List<Province>> provinceBaseObserver) {
        setSubscribe(provinceApi.getAllProvince(), provinceBaseObserver);
    }

    /*
    *有关城市
    */
    public static void getProvinceCitys(int pid, BaseObserver<List<City>> cityObserver) {
        setSubscribe(cityApi.getProvinceCitys(pid), cityObserver);
    }

    public static void getCity(Integer cid, BaseObserver<City> cityBaseObserver) {
        setSubscribe(cityApi.getCity(cid), cityBaseObserver);
    }

    /**
     * 有关合作商品的查询
     */
    public static void getCityParner(Integer ctid, Integer page, BaseObserver<List<Partner>> partnerObserver) {
        setSubscribe(partnerApi.getCityParner(ctid, page), partnerObserver);
    }

    public static void postAddGood(Map<String, Object> addPartner, BaseObserver<Partner> addPartnerObserver) {
        setSubscribe(partnerApi.postAddGood(addPartner), addPartnerObserver);
    }

    public static void getPartnerGood(Integer cid, BaseObserver<Partner> partnergoodObserver) {
        setSubscribe(partnerApi.getPartnerGood(cid), partnergoodObserver);
    }

    public static void getShopGoods(Integer sid, BaseObserver<List<Partner>> partnerShopGoodObserver) {
        setSubscribe(partnerApi.getShopGoods(sid), partnerShopGoodObserver);
    }

    public static void getAllPartners(Integer page, BaseObserver<List<Partner>> partnerAllObserver) {
        setSubscribe(partnerApi.getAllPartners(page), partnerAllObserver);
    }

    public static void getNamePartners(String goodsname, BaseObserver<List<Partner>> namePartnerObserver) {
        setSubscribe(partnerApi.getNamePartners(goodsname), namePartnerObserver);
    }

    public static void get3Partners(String goodsname, Integer ctid, Integer cgid, Integer page, BaseObserver<List<Partner>> threeObserver) {
        setSubscribe(partnerApi.get3Partners(goodsname, ctid, cgid, page), threeObserver);
    /*    Map<String,Object> map=new HashMap<String,Object>();
        map.put("goodsname",goodsname);
        map.put("ctid",ctid);
        map.put("cgid",cgid);
        map.put("page",page);
        setSubscribe(partnerApi.get3PartnersBynamecity(map),threeObserver);
        System.out.println();*/
    }
   /* public static void getUserInfoByname(String username, BaseObserver<User> userinfoBaseObserver) {
        setSubscribe(userApi.getUserInfoByname(username), userinfoBaseObserver);

    }*/

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
