package com.example.thinking.newsell.view.seeshop.ShopFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.Commodity;
import com.example.thinking.newsell.commen.Commen;
import com.example.thinking.newsell.utils.system.SpUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * *****************************************
 * Created by thinking on 2017/7/11.
 * 创建时间：
 * <p>
 * 描述：店铺的全部商品页面布局
 * <p/>
 * <p/>
 * *******************************************
 */

public class ShopAllGoodFragment extends Fragment {
    @BindView(R.id.hp_recycleview)
    RecyclerView hpRecycleview;
    @BindView(R.id.integrated)
    Button integrated;//综合
    @BindView(R.id.sales)
    Button sales;//销售量
    @BindView(R.id.price)
    Button price;//价格
    @BindView(R.id.v_and_h)
    Button v_and_h;//grid变linear

    Unbinder unbinder;
    private AllGoodAdapter allGoodAdapter;
    private GridLayoutManager gridLayoutManager;
     List<Commodity> commodityList=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View ShopHomeView = inflater.inflate(R.layout.shop_homepage, container, false);
        unbinder = ButterKnife.bind(this, ShopHomeView);

        /*获取该商店的所有商品*/
        NetWorks.getIDshopgoods(SpUtils.getInt(getActivity(),Commen.SHOPSIDdefault), new BaseObserver<List<Commodity>>() {
            @Override
            public void onHandleSuccess(List<Commodity> commodities) {
                commodityList=commodities;
                gridLayoutManager=new GridLayoutManager(getContext(),2);
                hpRecycleview.setLayoutManager(gridLayoutManager);
                allGoodAdapter =new AllGoodAdapter(getContext(),commodities);
                hpRecycleview.setAdapter(allGoodAdapter);
                integrated.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
            @Override
            public void onHandleError(int code, String message) {

            }
        });
        /*获取该商店的所有商品*/
        integrated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                price.setTextColor(getResources().getColor(R.color.colorBlack));
                sales.setTextColor(getResources().getColor(R.color.colorBlack));
                integrated.setTextColor(getResources().getColor(R.color.colorPrimary));

                NetWorks.getIDshopgoods(SpUtils.getInt(getActivity(),Commen.SHOPSIDdefault), new BaseObserver<List<Commodity>>() {
                    @Override
                    public void onHandleSuccess(List<Commodity> commodities) {

                        commodityList.clear();
                        commodityList=commodities;
                        allGoodAdapter =new AllGoodAdapter(getContext(),commodityList);
                        hpRecycleview.setAdapter(allGoodAdapter);

                    }
                    @Override
                    public void onHandleError(int code, String message) {

                    }
                });

            }
        });
        /*所有商品按价格排序*/
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
                allGoodAdapter =new AllGoodAdapter(getContext(),commodityList);
                hpRecycleview.setAdapter(allGoodAdapter);

            }
        });
        /*所有商品按销售量排序*/
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
                allGoodAdapter =new AllGoodAdapter(getContext(),commodityList);
                hpRecycleview.setAdapter(allGoodAdapter);
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
