package com.example.thinking.newsell.view.seeStatistics;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.thinking.newsell.R;
import com.example.thinking.newsell.commen.Commen;
import com.example.thinking.newsell.view.seeshop.ShopFragments.ShopFragmentAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.thinking.newsell.R.id.viewPager;

/**
 * *****************************************
 * Created by thinking on 2017/8/24.
 * 创建时间：
 * <p>
 * 描述：查看订单页(包括全部、未付款、未发货、待收货、已完成)
 * <p/>
 * <p/>
 * *******************************************
 */

public class OrderAllActivity extends AppCompatActivity{
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_title_date)
    TextView tvTitleDate;
    @BindView(R.id.tabl_order)
    TabLayout tablOrder;
    @BindView(R.id.viewpager_order)
    ViewPager viewpagerOrder;
    private int TYPEDATE = 0;//0代表今天、1代表本月、2代表本年、7代表所有
    private int TYPESTATUS = 0;//0未付款、 1待发货、 2待收货、3已收货、  7代表所有

    private List<Fragment> fragments = new ArrayList<>();
    private String[] titlelist;
    private ShopFragmentAdapter viewpagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_all_look);
        ButterKnife.bind(this);
        //  getDateType();
        tvTitleName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
       TYPEDATE = getIntent().getIntExtra(Commen.DATEMARK, 0);


        if (TYPEDATE==0){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd");
            String date = simpleDateFormat.format(new java.util.Date());
            tvTitleDate.setText(date);
        }else if (TYPEDATE==7){
            tvTitleDate.setText("所有订单");
        }
        init();//初始化一些参数


    }

    private void init() {
        //tablayout标签
        titlelist=getResources().getStringArray(R.array.tab_title_order);
        fragments.add(OrderFragment.newInstance(TYPEDATE,7));
        fragments.add(OrderFragment.newInstance(TYPEDATE,0));
        fragments.add(OrderFragment.newInstance(TYPEDATE,1));
        fragments.add(OrderFragment.newInstance(TYPEDATE,2));
        fragments.add(OrderFragment.newInstance(TYPEDATE,3));
        viewpagerAdapter=new ShopFragmentAdapter(getSupportFragmentManager(),this,fragments,titlelist);
        viewpagerOrder.setAdapter(viewpagerAdapter);
        tablOrder.setupWithViewPager(viewpagerOrder);


        switch (getIntent().getIntExtra(Commen.STATUSMARK,0)){
            case 7:
                viewpagerOrder.setCurrentItem(0);
                break;
            case 0:
                viewpagerOrder.setCurrentItem(1);
                break;
            case 1:
                viewpagerOrder.setCurrentItem(2);
                break;
            case 2:
                viewpagerOrder.setCurrentItem(3);
                break;
            case 3:
                viewpagerOrder.setCurrentItem(4);
                break;
        }
    }

}
