package com.example.thinking.newsell.view.seeshop;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.Shop;
import com.example.thinking.newsell.commen.Commen;
import com.example.thinking.newsell.utils.system.SpUtils;
import com.example.thinking.newsell.view.seeshop.GoodInfo.Attention.GoodAttentionActivity;
import com.example.thinking.newsell.view.seeshop.ShopFragments.ShopHomeFragment;
import com.example.thinking.newsell.view.seeshop.ShopFragments.ShopAllGoodFragment;
import com.example.thinking.newsell.view.seeshop.ShopFragments.ShopCategoryFragment;
import com.example.thinking.newsell.view.seeshop.ShopFragments.ShopFragmentAdapter;
import com.example.thinking.newsell.view.seeshop.ShopFragments.ShopInfoFragment;
import com.example.thinking.newsell.view.seeshop.ShopInfo.IntroductionShop;
import com.example.thinking.newsell.view.views.StarRating;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static android.support.design.widget.TabLayout.MODE_FIXED;

/**
 * *****************************************
 * Created by thinking on 2017/7/11.
 * 创建时间：
 * <p>
 * 描述：店铺页面（首页、全部商品页和**页）
 * <p/>
 * <p/>
 * *******************************************
 */

public class ShopActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    @BindView(R.id.shop_image)
    ImageView shopImage;
    @BindView(R.id.shop_phone)
    ImageView shopPhone;
    @BindView(R.id.imageview_tu)
    ImageView imageviewTu;
    @BindView(R.id.shop_location)
    TextView shopLocation;
    @BindView(R.id.linearlayout_location)
    LinearLayout linearlayoutLocation;
    @BindView(R.id.re_black)
    RelativeLayout reBlack;
    @BindView(R.id.shop_logo)
    ImageView shopLogo;
    @BindView(R.id.shop_name)
    TextView shopName;
    @BindView(R.id.shop_valuation)
    StarRating shopValuation;
    private TextView attShopMany;

    @BindView(R.id.shop_back)
    ImageView shopBack;
    @BindView(R.id.shop_more)
    ImageView shopMore;
    @BindView(R.id.toolbar_shop_name)
    TextView toolbarShopName;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.shop_tablayout)
    TabLayout shopTablayout;
    @BindView(R.id.appbar_layout)
    AppBarLayout appbarLayout;
    @BindView(R.id.shop_viewpage)
    ViewPager shopViewpage;

    private String[] mTitles;
    // 填充到ViewPager中的Fragment
    private List<Fragment> fragmentlist = new ArrayList<Fragment>();
    ShopAllGoodFragment shopAllGoodFragment = new ShopAllGoodFragment();
    ShopHomeFragment shopHomeFragment = new ShopHomeFragment();
    ShopCategoryFragment shopCategoryFragment = new ShopCategoryFragment();
    ShopInfoFragment shopInfoFragment = new ShopInfoFragment();
    ShopFragmentAdapter shopFragmentAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        ButterKnife.bind(this);

        attShopMany = (TextView) findViewById(R.id.att_shop_many);
        final Shop shop = (Shop) getIntent().getSerializableExtra(Commen.SHOPINFO);
        Glide.with(ShopActivity.this).load(shop.getHeadershow()).into(shopImage);
        Glide.with(ShopActivity.this).load(shop.getLogo())
                .bitmapTransform(new RoundedCornersTransformation(this, 5, 0, RoundedCornersTransformation.CornerType.ALL)).into(shopLogo);
        shopName.setText(shop.getShopname());
        shopLocation.setText(shop.getSaddress());
        shopValuation.setCurrentStarCount((int) shop.getSlevel());
        toolbarShopName.setText(shop.getShopname());

        /*商店back*/
        shopBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //back
                finish();
            }
        });
        /*商店更多*/
        shopMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //more
            }
        });
        /* 商店地址*/
        linearlayoutLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(ShopActivity.this, "商店地址", Toast.LENGTH_SHORT).show();
            }
        });
        /*商店电话*/
        shopPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.DIAL");
                intent.setData(Uri.parse("tel:" + shop.getStel()));
                startActivity(intent);
            }
        });

        //店铺关注人数
        NetWorks.getShopAttentionSize(shop.getSid(), new BaseObserver<Integer>() {
            @Override
            public void onHandleSuccess(Integer integer) {
                if (integer != 0) {
                    attShopMany.setText("已有" + integer + "人关注");
                }
            }

            @Override
            public void onHandleError(int code, String message) {
                Toast.makeText(ShopActivity.this, code + message, Toast.LENGTH_SHORT).show();
            }
        });

        //查看店铺关注人数详情
        attShopMany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shop.getSid()== SpUtils.getInt(ShopActivity.this,Commen.SHOPSIDdefault)){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Commen.SHOPINFO, shop);
                    bundle.putInt(Commen.ATTENTIONTYPE, 1);
                    Intent intent = new Intent(ShopActivity.this, GoodAttentionActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });


        //给页面设置工具栏
        if (mCollapsingToolbarLayout != null) {
            //设置隐藏图片时候ToolBar的颜色
            mCollapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorWhite));
            //设置工具栏标题
            mCollapsingToolbarLayout.setTitle("newsell");
        }
        appbarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                Log.d("STATE", state.name());
                if (state == State.EXPANDED) {
                    //展开状态
                    toolbar.setBackgroundColor(Color.TRANSPARENT);
                } else if (state == State.COLLAPSED) {
                    //折叠状态
                    toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                } else {
                    //中间状态
                    toolbar.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });

        //商店介绍页
        shopMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(Commen.SHOPINFO, shop);
                Intent intent = new Intent(ShopActivity.this, IntroductionShop.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        //关于店铺的图片介绍
        Bundle bundle = new Bundle();
        bundle.putString(Commen.SHOWPICS, shop.getShowpic());
        bundle.putInt(Commen.SHOPSID, shop.getSid());
        shopInfoFragment.setArguments(bundle);

        Bundle bundle1 = new Bundle();
        bundle1.putInt(Commen.SHOPSID, shop.getSid());
        shopHomeFragment.setArguments(bundle1);

        shopAllGoodFragment.setArguments(bundle1);
        shopCategoryFragment.setArguments(bundle1);

        fragmentlist.add(shopHomeFragment);
        fragmentlist.add(shopAllGoodFragment);
        fragmentlist.add(shopCategoryFragment);
        fragmentlist.add(shopInfoFragment);
        mTitles = getResources().getStringArray(R.array.tab_titles);
        shopFragmentAdapter = new ShopFragmentAdapter(getSupportFragmentManager(), this, fragmentlist, mTitles);
        shopViewpage.setAdapter(shopFragmentAdapter);
        shopTablayout.addTab(shopTablayout.newTab().setText(mTitles[0]));
        shopTablayout.addTab(shopTablayout.newTab().setText(mTitles[1]));
        shopTablayout.addTab(shopTablayout.newTab().setText(mTitles[2]));
        shopTablayout.addTab(shopTablayout.newTab().setText(mTitles[3]));
        shopViewpage.setOffscreenPageLimit(1);
        // 给ViewPager添加页面动态监听器（为了让Toolbar中的Title可以变化相应的Tab的标题）
        shopViewpage.addOnPageChangeListener(this);
        shopTablayout.setupWithViewPager(shopViewpage);
        shopTablayout.setTabMode(MODE_FIXED);
        shopTablayout.setTabsFromPagerAdapter(shopFragmentAdapter);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {

    }
}
