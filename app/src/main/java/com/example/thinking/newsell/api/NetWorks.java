package com.example.thinking.newsell.api;

import android.content.Intent;

import com.example.thinking.newsell.bean.Assess;
import com.example.thinking.newsell.bean.BaseBean;
import com.example.thinking.newsell.bean.Buyer;
import com.example.thinking.newsell.bean.Category;
import com.example.thinking.newsell.bean.City;
import com.example.thinking.newsell.bean.Commodity;
import com.example.thinking.newsell.bean.GoodAttention;
import com.example.thinking.newsell.bean.Order;
import com.example.thinking.newsell.bean.OrderAddress;
import com.example.thinking.newsell.bean.OrderGood;
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
    protected static final OrderApi orderApi = getRetrofit().create(OrderApi.class);

    /**
     * 有关店铺的
     */
    /*查店铺信息*/
    public static void getShopInfo(Integer bid, BaseObserver<List<Shop>> shopBaseObserver) {
        setSubscribe(shopApi.getShopInfo(bid), shopBaseObserver);
    }

    //根据店铺id查询一个商店(id即为店铺sid)
    public static void getShopInfoById(Integer id, BaseObserver<Shop> shopbaseidObserver) {
        setSubscribe(shopApi.getShopInfoById(id), shopbaseidObserver);
    }

    //根据店铺id查看店铺访问量
    public static void getSidDateCount(Integer sid, String date, BaseObserver<Integer> shopVisitorsObserver) {
        setSubscribe(shopApi.getSidDateCount(sid, date), shopVisitorsObserver);
    }

    /* 根据店铺id日期查看总销售额*/
    public static void getSidDateSales(Integer sid, String date, BaseObserver<Double> shopSDSalesObserver) {
        setSubscribe(shopApi.getSidDateSales(sid, date), shopSDSalesObserver);
    }

    /*根据店铺id日期查看总销售量*/
    public static void getSidDateSalesCount(Integer sid, String date, BaseObserver<Integer> shopSDSCountObserver) {
        setSubscribe(shopApi.getSidDateSalesCount(sid, date), shopSDSCountObserver);
    }

    //根据店铺id查看所有销售量
    public static void getSidSalesVolume(Integer sid, BaseObserver<Integer> shopSalesVolumreObserver) {
        setSubscribe(shopApi.getSidSalesVolume(sid), shopSalesVolumreObserver);
    }

    //根据店铺id查看所有销售额
    public static void getSidSalesTotal(Integer sid, BaseObserver<Double> shopSalesTotalObserver) {
        setSubscribe(shopApi.getSidSalesTotal(sid), shopSalesTotalObserver);
    }

    //根据店铺id分页查看所有销售情况
    public static void getGoodSales(Integer sid, Integer page, BaseObserver<List<Commodity>> shopGoodSalesObserver) {
        setSubscribe(shopApi.getGoodSales(sid, page), shopGoodSalesObserver);
    }

    /**
     * 有关商品部分
     */
    //根据店铺id查找所有商品
    public static void getIDshopgoods(int sid, BaseObserver<List<Commodity>> goodsBaseObserver) {
        setSubscribe(commodityApi.getIDshopgoods(sid), goodsBaseObserver);
    }

    //根据商品id查找商品
    public static void getIDgood(int cid, BaseObserver<Commodity> goodBaseObserver) {
        setSubscribe(commodityApi.getIDgood(cid), goodBaseObserver);
    }

    //修改商品开放状态
    public static void putStatueGood(int cid, int statue, BaseObserver<Commodity> statusObserver) {
        setSubscribe(commodityApi.putStatueGood(cid, statue), statusObserver);
    }

    //根据店铺id 和商品状态 获取商品列表信息
    public static void getGoodStatus(int sid, int statue, BaseObserver<List<Commodity>> goodstatueObserver) {
        setSubscribe(commodityApi.getGoodStatus(sid, statue), goodstatueObserver);
    }

    /*根据店铺id和分类id查找商品*/
    public static void getSidCgidgoods(int sid, int cgid, BaseObserver<List<Commodity>> categorygoodsBaseObserver) {
        setSubscribe(commodityApi.getSidCgidgoods(sid, cgid), categorygoodsBaseObserver);
    }

    /*根据店铺查看店铺商品关注量总数*/
    public static void getGoodAttention(Integer sid, BaseObserver<Integer> GoodAttentionObserver) {
        setSubscribe(shopApi.getGoodAttention(sid), GoodAttentionObserver);
    }

    /**
     * 有关订单的
     */
    //根据订单状况日期查看订单数量
    public static void getBySSDOrderCount(Integer sid, Integer statue, String date, BaseObserver<Integer> orderBaseObserver) {
        setSubscribe(orderApi.getBySSDOrderCount(sid, statue, date), orderBaseObserver);
    }

    //根据订单状况日期查看订单
    public static void getBySSDOrderAll(Integer sid, Integer statue, String date, Integer page, BaseObserver<List<Order>> orderAllObserver) {
        setSubscribe(orderApi.getBySSDOrderAll(sid, statue, date, page), orderAllObserver);
    }

    //根据订单状况查看订单数量
    public static void getBySSOrderCount(Integer sid, Integer statue, BaseObserver<Integer> orderStatueObserver) {
        setSubscribe(orderApi.getBySSOrderCount(sid, statue), orderStatueObserver);
    }

    //根据订单状况查看订单
    public static void getBySSOrderAll(Integer sid, Integer statue, Integer page, BaseObserver<List<Order>> orderStatusAllObserver) {
        setSubscribe(orderApi.getBySSOrderAll(sid, statue, page), orderStatusAllObserver);
    }

    // 根据店铺id查看订单情况
    public static void getSidAllOrder(Integer sid, Integer page, BaseObserver<List<Order>> AllOrderObserver) {
        setSubscribe(orderApi.getSidAllOrder(sid, page), AllOrderObserver);
    }

    //根据订单id查询订单
    public static void getByOidOrder(Integer oid, BaseObserver<Order> orderBaseObserver) {
        setSubscribe(orderApi.getByOidOrder(oid), orderBaseObserver);
    }

    //根据订单id查找商品
    public static void getByOidGoods(Integer oid, BaseObserver<List<OrderGood>> orderGoodsObserver) {
        setSubscribe(orderApi.getByOidGoods(oid), orderGoodsObserver);
    }

    //根据id查找收货地址
    public static void getBySaidAddress(Integer said, BaseObserver<OrderAddress> orderAddressBaseObserver) {
        setSubscribe(orderApi.getBySaidAddress(said), orderAddressBaseObserver);
    }

    //根据店铺id日期查看订单商品内容
    public static void getBySDGoods(Integer sid, String date, Integer page, BaseObserver<List<Commodity>> orderSidGoodsObserver) {
        setSubscribe(orderApi.getBySDGoods(sid, date, page), orderSidGoodsObserver);
    }

    //根据店铺id日期查看订单商品内容
    public static void getSidDateOrder(Integer sid, String date, Integer page, BaseObserver<List<Order>> orderSDOrderObserver) {
        setSubscribe(orderApi.getSidDateOrder(sid, date, page), orderSDOrderObserver);
    }

    /**
     * 有关关注部分
     */

    /*有关关注商品*///根据商品id查看关注
    public static void getGoodAttention(Integer cid, Integer page, BaseObserver<List<GoodAttention>> goodAttentionObserver) {
        setSubscribe(attentionApi.getGoodAttention(cid, page), goodAttentionObserver);
    }

    /*有关关注店铺*///根据店铺id查询关注信息
    public static void getShopAttention(Integer sid, Integer page, BaseObserver<List<ShopAttention>> shopAttentionObserver) {
        setSubscribe(attentionApi.getShopAttention(sid, page), shopAttentionObserver);
    }

    /*有关关注店铺数量*///查看关注店铺总人数
    public static void getShopAttentionSize(Integer sid, BaseObserver<Integer> shopAttSizeObserver) {
        setSubscribe(attentionApi.getShopAttentionSize(sid), shopAttSizeObserver);
    }

    /**
     * 有关问题部分
     */
    //根据商品id查询疑问
    public static void getCidQuest(int cid, BaseObserver<List<Quest>> cidQuestObserver) {
        setSubscribe(questApi.getCidQuest(cid), cidQuestObserver);
    }

    //添加问题回复
    public static void commitQuestReply(Map<String, String> map, BaseObserver<Quest.RepliesBean> questObserver) {
        setSubscribe(questApi.commitQuestReply(map), questObserver);
    }


    /**
     * 有关分类部分
     */
    //根据类别id查找
    public static void getCategory(int id, BaseObserver<Category> categoryObserver) {
        setSubscribe(categoryApi.getCategory(id), categoryObserver);
    }

    //根据店铺id查找分类
    public static void getSidCategory(int sid, BaseObserver<List<Category>> categoriesObserver) {
        setSubscribe(categoryApi.getSidCategory(sid), categoriesObserver);
    }

    //查找所有
    public static void getAllCategory(BaseObserver<List<Category>> categoryObserver) {
        setSubscribe(categoryApi.getAllCategory(), categoryObserver);
    }

    //根据商品名称模糊查询分类
    public static void getNameCategory(String goodsname, BaseObserver<List<Category>> goodnameObserver) {
        setSubscribe(categoryApi.getNameCategory(goodsname), goodnameObserver);
    }

    //根据小一类类别查找类别信息
    public static void getsmallCategory(String small, BaseObserver<List<Category>> smallObserver) {
        setSubscribe(categoryApi.getsmallCategory(small), smallObserver);
    }

    /**
     * 有关用户部分
     */
    //商店老板登录
    public static void postLogin(String phone_name, String password, BaseObserver<User> userObservable) {//通过手机号获取用户信息
        setSubscribe(userApi.postLogin(phone_name, password), userObservable);
    }

    //修改密码
    public static void putNewPassword(int id, String password, BaseObserver<User> userpwObserver) {
        setSubscribe(userApi.putNewPassword(id, password), userpwObserver);
    }

    //修改昵称
    public static void putNewNicename(int id, String nicename, BaseObserver<User> usernicenmObserver) {
        setSubscribe(userApi.putNewNicename(id, nicename), usernicenmObserver);
    }

    //修改店主手机号
    public static void putNewPhone(int id, String phone, BaseObserver<User> userphoneObserver) {
        setSubscribe(userApi.putNewPhone(id, phone), userphoneObserver);
    }

    //根据店主id查找店主
    public static void getUserInfo(int id, BaseObserver<User> userinfoObserver) {
        setSubscribe(userApi.getUserInfo(id), userinfoObserver);
    }

    //根据店铺id查找店主
    public static void getUserInfoBySiD(int sid, BaseObserver<User> userinfoObserver) {
        setSubscribe(userApi.getUserInfoBySiD(sid), userinfoObserver);
    }

    /**
     * 买家用户
     */
    //buyer买家用户 根据用户数据库id查找用户
    public static void getIdInfo(Integer id, BaseObserver<Buyer> buyerBaseObserver) {
        setSubscribe(buyerApi.getIdInfo(id), buyerBaseObserver);
    }

    //根据手机号码区分是商家还是用户
    public static void knowByPhone(String phone, BaseObserver<UserBuyer> phoneObserver) {
        setSubscribe(userApi.knowByPhone(phone), phoneObserver);
    }

    /**
     * 有关评价部分
     */

    //根据商品id查找评价信息*
    public static void getAllAssess(Integer cid, BaseObserver<List<Assess>> assessObservable) {
        setSubscribe(assessApi.getAllAssess(cid), assessObservable);
    }

    //店家回复评论
    public static void putBackAssess(Integer aid, String back, BaseObserver<Assess> assessbackObserver) {
        setSubscribe(assessApi.putBackAssess(aid, back), assessbackObserver);
    }

    //根据商品id，获取评价总条数
    public static void getAssessCount(int cid, BaseObserver<Integer> assesscountObserver) {
        setSubscribe(assessApi.getAssessCount(cid), assesscountObserver);
    }

    //根据商品id获取最新评论信息
    public static void getNewAssess(int cid, BaseObserver<Assess> newassessObserver) {
        setSubscribe(assessApi.getNewAssess(cid), newassessObserver);
    }


    /**
     * 有关省
     */

    //获取所有的省份信息
    public static void getAllProvince(BaseObserver<List<Province>> provinceBaseObserver) {
        setSubscribe(provinceApi.getAllProvince(), provinceBaseObserver);
    }

    /**
     * 有关城市
     */

    //根据省id获取所有城市
    public static void getProvinceCitys(int pid, BaseObserver<List<City>> cityObserver) {
        setSubscribe(cityApi.getProvinceCitys(pid), cityObserver);
    }

    //根据城市id获取城市信息
    public static void getCity(Integer cid, BaseObserver<City> cityBaseObserver) {
        setSubscribe(cityApi.getCity(cid), cityBaseObserver);
    }

    /**
     * 有关合作商品的查询
     */

    //根据城市id查询合作商品
    public static void getCityParner(Integer ctid, Integer page, BaseObserver<List<Partner>> partnerObserver) {
        setSubscribe(partnerApi.getCityParner(ctid, page), partnerObserver);
    }

    //添加合作商品
    public static void postAddGood(Map<String, Object> addPartner, BaseObserver<Partner> addPartnerObserver) {
        setSubscribe(partnerApi.postAddGood(addPartner), addPartnerObserver);
    }

    //根据商品id查询商品合作信息
    public static void getPartnerGood(Integer cid, BaseObserver<Partner> partnergoodObserver) {
        setSubscribe(partnerApi.getPartnerGood(cid), partnergoodObserver);
    }

    //根据店铺id查找发布的商品
    public static void getShopGoods(Integer sid, BaseObserver<List<Partner>> partnerShopGoodObserver) {
        setSubscribe(partnerApi.getShopGoods(sid), partnerShopGoodObserver);
    }

    //查询所有合作商品（分页）
    public static void getAllPartners(Integer page, BaseObserver<List<Partner>> partnerAllObserver) {
        setSubscribe(partnerApi.getAllPartners(page), partnerAllObserver);
    }

    //根据商品名称模糊查询所有商品
    public static void getNamePartners(String goodsname, BaseObserver<List<Partner>> namePartnerObserver) {
        setSubscribe(partnerApi.getNamePartners(goodsname), namePartnerObserver);
    }

    //根据商品名称和城市id分类id查询
    public static void get3Partners(String goodsname, Integer ctid, Integer cgid, Integer page, BaseObserver<List<Partner>> threeObserver) {
        setSubscribe(partnerApi.get3Partners(goodsname, ctid, cgid, page), threeObserver);
    }

    //修改有合作意向的商品意向数量
    public static void getPartnerIntent(Integer psid, BaseObserver<Partner> partnerIntentObserver) {
        setSubscribe(partnerApi.getPartnerIntent(psid), partnerIntentObserver);
    }

    //根据店铺id和日期查看合作
    public static void getBySDPartenrs(Integer sid, String date, Integer page, BaseObserver<List<Partner>> BySDpartnersObserver) {
        setSubscribe(partnerApi.getBySDPartenrs(sid, date, page), BySDpartnersObserver);
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
