package com.example.thinking.newsell.view.HomeFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.Partner;
import com.example.thinking.newsell.bean.User;
import com.example.thinking.newsell.commen.Commen;
import com.example.thinking.newsell.utils.system.SpUtils;
import com.example.thinking.newsell.view.seekpartners.SearchPartner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * *****************************************
 * Created by thinking on 2017/8/2.
 * 创建时间：
 * <p>
 * 描述：店铺管理的首页（包括店铺更换、查看店铺、店铺的上架、下架、合作商品、以及一些合作商品）
 * <p/>
 * <p/>
 * *******************************************
 */

public class ShopManageFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.recycler_shopmanage)
    RecyclerView recyclerShopmanage;
    @BindView(R.id.view_partnergoods)
    TextView viewPartnergoods;//标题栏搜索textview
    ShopAdapter shopAdapter;
    LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View ShopManageView = inflater.inflate(R.layout.fragment_shopmanage, container, false);
        unbinder = ButterKnife.bind(this, ShopManageView);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerShopmanage.setLayoutManager(linearLayoutManager);//设置布局样式

        //搜索合作
        viewPartnergoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchPartner.class);//跳转至搜索页
                startActivity(intent);
            }
        });


        //首页下方的合作商品的列表展示，只查了第一页
        NetWorks.getAllPartners(0, new BaseObserver<List<Partner>>() {
            @Override
            public void onHandleSuccess(List<Partner> partnerList) {
                shopAdapter = new ShopAdapter(getActivity(), partnerList);
                recyclerShopmanage.setAdapter(shopAdapter);

            }

            @Override
            public void onHandleError(int code, String message) {
                Toast.makeText(getActivity(),code+message,Toast.LENGTH_SHORT).show();
            }
        });

        return ShopManageView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
