package com.example.thinking.newsell.view.seeshop.ShopFragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.thinking.newsell.view.seeStatistics.OrderAllActivity;

import java.util.List;

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

public class ShopFragmentAdapter extends FragmentStatePagerAdapter {
    private Context context;
    private List<Fragment> fragmentlist;
    private String[] titlelist;
    public ShopFragmentAdapter(FragmentManager fm,Context context,List<Fragment> fragmentlist,String[] titlelist) {
        super(fm);
        this.context=context;
        this.fragmentlist=fragmentlist;
        this.titlelist=titlelist;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return titlelist[position];
    }
    @Override
    public Fragment getItem(int position) {
        return fragmentlist.get(position);
    }

    @Override
    public int getCount() {
        return fragmentlist.size();
    }
}
