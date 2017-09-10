package com.example.thinking.newsell.view.seeshop.ShopFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thinking.newsell.R;

/**
 * *****************************************
 * Created by thinking on 2017/8/29.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class ShopHome2Fragment extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View Homeview=inflater.inflate(R.layout.shop_allpage,container,false);
        recyclerView=(RecyclerView)Homeview.findViewById(R.id.more_recycleview);




        return Homeview;
    }
}
