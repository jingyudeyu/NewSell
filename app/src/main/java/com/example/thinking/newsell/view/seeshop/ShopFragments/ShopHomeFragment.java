package com.example.thinking.newsell.view.seeshop.ShopFragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.RangeGridLayoutHelper;
import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.Commodity;
import com.example.thinking.newsell.bean.User;
import com.example.thinking.newsell.commen.Commen;
import com.example.thinking.newsell.utils.system.SpUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * *****************************************
 * Created by thinking on 2017/7/11.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class ShopHomeFragment extends Fragment {
    @BindView(R.id.hp_recycleview)
    RecyclerView hpRecycleview;
    @BindView(R.id.integrated)
    Button integrated;
    @BindView(R.id.sales)
    Button sales;
    @BindView(R.id.price)
    Button price;
    @BindView(R.id.v_and_h)
    Button v_and_h;

    Unbinder unbinder;
    private HomeAdapter homeAdapter;
    private GridLayoutManager gridLayoutManager;
     List<Commodity> commodityList=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View ShopHomeView = inflater.inflate(R.layout.shop_homepage, container, false);
        unbinder = ButterKnife.bind(this, ShopHomeView);

       final User user=(User) SpUtils.getObject(getContext(),Commen.USERINFO);


        NetWorks.getIDshopgoods(user.getSid(), new BaseObserver<List<Commodity>>() {
            @Override
            public void onHandleSuccess(List<Commodity> commodities) {
                commodityList=commodities;
                gridLayoutManager=new GridLayoutManager(getContext(),2);
                hpRecycleview.setLayoutManager(gridLayoutManager);
                homeAdapter=new HomeAdapter(getContext(),commodities);
                hpRecycleview.setAdapter(homeAdapter);
                integrated.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
            @Override
            public void onHandleError(int code, String message) {

            }
        });

        integrated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                price.setTextColor(getResources().getColor(R.color.colorBlack));
                sales.setTextColor(getResources().getColor(R.color.colorBlack));
                integrated.setTextColor(getResources().getColor(R.color.colorPrimary));

                NetWorks.getIDshopgoods(user.getSid(), new BaseObserver<List<Commodity>>() {
                    @Override
                    public void onHandleSuccess(List<Commodity> commodities) {

                        commodityList.clear();
                        commodityList=commodities;
                        homeAdapter=new HomeAdapter(getContext(),commodityList);
                        hpRecycleview.setAdapter(homeAdapter);

                    }
                    @Override
                    public void onHandleError(int code, String message) {

                    }
                });

            }
        });


        price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price.setTextColor(getResources().getColor(R.color.colorPrimary));
                integrated.setTextColor(getResources().getColor(R.color.colorBlack));
                sales.setTextColor(getResources().getColor(R.color.colorBlack));
                Collections.sort(commodityList, new Comparator<Commodity>() {
                    @Override
                    public int compare(Commodity o1, Commodity o2) {
                        if (o1.getPrice()>o2.getPrice()) {
                            return 1;
                        }
                        if (o1.getPrice()==o2.getPrice()){
                            return 0;
                        }
                        return -1;
                    }
                });
                homeAdapter=new HomeAdapter(getContext(),commodityList);
                hpRecycleview.setAdapter(homeAdapter);

            }
        });
        sales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sales.setTextColor(getResources().getColor(R.color.colorPrimary));
                integrated.setTextColor(getResources().getColor(R.color.colorBlack));
                price.setTextColor(getResources().getColor(R.color.colorBlack));

                Collections.sort(commodityList, new Comparator<Commodity>() {
                            @Override
                            public int compare(Commodity o1, Commodity o2) {
                                if (o1.getSalesvolu()>o2.getSalesvolu()){
                                    return -1;
                                }
                                if (o1.getSalesvolu()==o2.getSalesvolu()){
                                    return 0;
                                }
                                return 1;
                            }
                        });
                homeAdapter=new HomeAdapter(getContext(),commodityList);
                hpRecycleview.setAdapter(homeAdapter);
            }
        });


        return ShopHomeView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
