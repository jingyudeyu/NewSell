package com.example.thinking.newsell.view.seeshop.ShopFragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.Category;

import java.util.List;

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

public class ShopNewFragment extends Fragment {
    @BindView(R.id.new_recyclerview)
    RecyclerView newRecyclerview;
    Unbinder unbinder;

    GridLayoutManager gridLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View ShopNewView=inflater.inflate(R.layout.shop_newpage,container,false);
        unbinder = ButterKnife.bind(this, ShopNewView);

        NetWorks.getAllCategory(new BaseObserver<List<Category>>() {
            @Override
            public void onHandleSuccess(List<Category> categories) {
                gridLayoutManager=new GridLayoutManager(getContext(),1);

            }

            @Override
            public void onHandleError(int code, String message) {

            }
        });


        return ShopNewView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
