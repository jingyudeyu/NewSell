package com.example.thinking.newsell.view.seeshop.ShopFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.Category;
import com.example.thinking.newsell.commen.Commen;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * *****************************************
 * Created by thinking on 2017/7/11.
 * 创建时间：
 * <p>
 * 描述：店铺页面里的分类页面
 * <p/>
 * <p/>
 * *******************************************
 */

public class ShopCategoryFragment extends Fragment {
    @BindView(R.id.new_recyclerview)
    RecyclerView newRecyclerview;
    Unbinder unbinder;

    private GridLayoutManager gridLayoutManager;
    private List<Category> categoryListnew = new ArrayList<>();
    private CategoryAdapter categoryAdapter;
    private int sid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View ShopNewView = inflater.inflate(R.layout.shop_newpage, container, false);
        unbinder = ButterKnife.bind(this, ShopNewView);
        sid = getArguments().getInt(Commen.SHOPSID);
        /*获取根据店铺sid,去查这个店铺分类*/
        NetWorks.getSidCategory(sid, new BaseObserver<List<Category>>() {
            @Override
            public void onHandleSuccess(List<Category> categories) {
                if (categories.size() != 0) {
                    categoryListnew = SortBySmall(categories);
                    gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
                    newRecyclerview.setLayoutManager(gridLayoutManager);
                    categoryAdapter = new CategoryAdapter(getActivity(), categoryListnew);
                    newRecyclerview.setAdapter(categoryAdapter);
                }
            }

            @Override
            public void onHandleError(int code, String message) {
                Toast.makeText(getActivity(), code + message, Toast.LENGTH_SHORT).show();
            }
        });


        return ShopNewView;
    }

    /*列表的排序*/
    private List<Category> SortBySmall(List<Category> categoryList) {
        List<Category> categorys = new ArrayList<>();
        categoryListnew.clear();
        List<String> cate = new ArrayList<>();
        for (int i = 0; i < categoryList.size(); i++) {
            cate.add(categoryList.get(i).getSmall());
        }
        HashSet set = new HashSet(cate);
        cate.clear();
        cate.addAll(set);
        for (int a = 0; a < cate.size(); a++) {
            Category category = new Category();
            category.setSmall(cate.get(a));
            category.setIshead(1);
            categorys.add(category);
        }
        for (int n = 0; n < categorys.size(); n++) {
            categoryListnew.add(categorys.get(n));
            String small = categorys.get(n).getSmall();
            for (int m = 0; m < categoryList.size(); m++) {
                Category category = new Category();
                category = categoryList.get(m);
                if (category.getSmall().equals(small)) {
                    category.setIshead(0);
                    categoryListnew.add(category);
                }
            }
        }
        return categoryListnew;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
