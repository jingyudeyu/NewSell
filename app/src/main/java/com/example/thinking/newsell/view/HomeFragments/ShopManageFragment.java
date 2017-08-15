package com.example.thinking.newsell.view.HomeFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.Commodity;
import com.example.thinking.newsell.bean.Partner;
import com.example.thinking.newsell.bean.Shop;
import com.example.thinking.newsell.bean.User;
import com.example.thinking.newsell.commen.Commen;
import com.example.thinking.newsell.utils.system.SpUtils;
import com.example.thinking.newsell.view.seekpartners.SearchPartner;
import com.example.thinking.newsell.view.seekpartners.SeekPartner;
import com.example.thinking.newsell.view.seeshop.ShopActivity;
import com.example.thinking.newsell.view.seeshop.ShopFragments.InfoFragments.InfoShelfGood;
import com.example.thinking.newsell.view.seeshop.ShopFragments.InfoFragments.InfoShopGoods;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * *****************************************
 * Created by thinking on 2017/8/2.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class ShopManageFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.view_shop)
    Button viewShop;
    @BindView(R.id.view_partnergoods)
    TextView viewPartnergoods;
    @BindView(R.id.view_shelves_above)
    Button viewShelvesAbove;
    @BindView(R.id.view_shelves_below)
    Button viewShelvesBelow;
    @BindView(R.id.view_partner_goods)
    Button viewPartnerGoods;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View ShopManageView = inflater.inflate(R.layout.fragment_shopmanage, container, false);
        unbinder = ButterKnife.bind(this, ShopManageView);
        final User user = (User) SpUtils.getObject(getContext(), Commen.USERINFO);

        initListener(user);




        return ShopManageView;

    }

    private void initListener(User user0) {
        //获取用户信息
        //  final User user = (User) SpUtils.getObject(getContext(), Commen.USERINFO);
        final User user = user0;
        //查看店铺
        viewPartnergoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchPartner.class);
                startActivity(intent);
            }
        });

        //查看合作商品
        viewShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  User user = (User) SpUtils.getObject(getContext(), Commen.USERINFO);
                NetWorks.getShopInfo(user.getBid(), new BaseObserver<Shop>() {
                    @Override
                    public void onHandleSuccess(Shop shop) {
                        //把店铺所在城市放入sp中保存
                        //跳转至商店页面
                        SpUtils.putInt(getContext(), Commen.SHOPCITYID, shop.getCity());
                        Intent intent = new Intent(getContext(), ShopActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(Commen.SHOPINFO, shop);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }

                    @Override
                    public void onHandleError(int code, String message) {

                    }
                });
            }
        });

        //查看上架商品
        viewShelvesAbove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NetWorks.getGoodStatus(user.getSid(), 0, new BaseObserver<List<Commodity>>() {
                    @Override
                    public void onHandleSuccess(List<Commodity> commodities) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(Commen.ABOVEGOODS, (Serializable) commodities);
                        Intent intent = new Intent(getContext(), InfoShelfGood.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }

                    @Override
                    public void onHandleError(int code, String message) {

                    }
                });
            }
        });

        //查看下架商品
        viewShelvesBelow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetWorks.getGoodStatus(user.getSid(), 1, new BaseObserver<List<Commodity>>() {
                    @Override
                    public void onHandleSuccess(List<Commodity> commodities) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(Commen.ABOVEGOODS, (Serializable) commodities);
                        Intent intent = new Intent(getContext(), InfoShelfGood.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }

                    @Override
                    public void onHandleError(int code, String message) {

                    }
                });
            }
        });

        //查看自己店铺的合作商品
        viewPartnerGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetWorks.getShopGoods(user.getSid(), new BaseObserver<List<Partner>>() {
                    @Override
                    public void onHandleSuccess(List<Partner> partnerList) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(Commen.SHOPGOODS, (Serializable) partnerList);
                        Intent intent = new Intent(getContext(), InfoShopGoods.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }

                    @Override
                    public void onHandleError(int code, String message) {

                    }
                });
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
