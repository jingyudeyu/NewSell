package com.example.thinking.newsell.view.seeshop.ShopFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

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
    ImageView v_and_h;//grid变linear

    Unbinder unbinder;
    private AllGoodAdapter allGoodAdapter;
    private GridLayoutManager mLayoutManager;
    List<Commodity> commodityList = new ArrayList<>();
    private int VH = 0;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View ShopHomeView = inflater.inflate(R.layout.shop_homepage, container, false);
        unbinder = ButterKnife.bind(this, ShopHomeView);

        //纵横转换的imageview
        v_and_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (VH == 0) {//默认是grid
                    getVorH(VH);
                    v_and_h.setImageResource(R.mipmap.change_ver);
                } else if (VH == 1) {
                    getVorH(VH);
                    v_and_h.setImageResource(R.mipmap.change_hor);
                }
            }
        });

        //初始化
        VH = 0;
        getVorH(VH);
        integrated.setTextColor(getResources().getColor(R.color.colorPrimary));
        /*获取该商店的所有商品*/
        NetWorks.getIDshopgoods(getArguments().getInt(Commen.SHOPSID), new BaseObserver<List<Commodity>>() {
            @Override
            public void onHandleSuccess(List<Commodity> commodities) {
                commodityList.clear();
                commodityList = commodities;
                allGoodAdapter = new AllGoodAdapter(getContext(), commodityList, mLayoutManager);
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

                NetWorks.getIDshopgoods(getArguments().getInt(Commen.SHOPSID), new BaseObserver<List<Commodity>>() {
                    @Override
                    public void onHandleSuccess(List<Commodity> commodities) {

                        commodityList.clear();
                        commodityList = commodities;
                        allGoodAdapter = new AllGoodAdapter(getContext(), commodityList, mLayoutManager);
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
                        if (o1.getPrice() > o2.getPrice()) {
                            return 1;
                        }
                        if (o1.getPrice() == o2.getPrice()) {
                            return 0;
                        }
                        return -1;
                    }
                });
                allGoodAdapter = new AllGoodAdapter(getContext(), commodityList, mLayoutManager);
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
                        if (o1.getSalesvolu() > o2.getSalesvolu()) {
                            return -1;
                        }
                        if (o1.getSalesvolu() == o2.getSalesvolu()) {
                            return 0;
                        }
                        return 1;
                    }
                });
                allGoodAdapter = new AllGoodAdapter(getContext(), commodityList, mLayoutManager);
                hpRecycleview.setAdapter(allGoodAdapter);
            }
        });

        return ShopHomeView;
    }

    private void getVorH(int vh) {
        int scrollPosition = 0;
          if (hpRecycleview.getLayoutManager()!=null){
        scrollPosition = ((LinearLayoutManager) hpRecycleview.getLayoutManager()).findFirstVisibleItemPosition();
          }
        switch (vh) {
            case 0:
                mLayoutManager = new GridLayoutManager(getActivity(), 2);
                VH = 1;
                break;
            case 1:
                mLayoutManager = new GridLayoutManager(getActivity(), 1);
                VH = 0;
                break;
            default:
                mLayoutManager = new GridLayoutManager(getActivity(), 2);
                VH = 1;
        }
        hpRecycleview.setLayoutManager(mLayoutManager);
        allGoodAdapter = new AllGoodAdapter(getContext(), commodityList, mLayoutManager);
        hpRecycleview.scrollToPosition(scrollPosition);
        hpRecycleview.setAdapter(allGoodAdapter);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    //初始化数据
    private void initData() {
        NetWorks.getIDshopgoods(getArguments().getInt(Commen.SHOPSID), new BaseObserver<List<Commodity>>() {
            @Override
            public void onHandleSuccess(List<Commodity> commodities) {
                commodityList = commodities;
            }

            @Override
            public void onHandleError(int code, String message) {
                Toast.makeText(getActivity(), code + message, Toast.LENGTH_SHORT).show();
            }
        });

    }

}
