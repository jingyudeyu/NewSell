package com.example.thinking.newsell.view.seeshop;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.thinking.newsell.MainActivity;
import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.Shop;
import com.example.thinking.newsell.bean.User;
import com.example.thinking.newsell.commen.Commen;
import com.example.thinking.newsell.utils.system.SpUtils;
import com.example.thinking.newsell.view.seeshop.ShopFragments.ShopAllFragment;
import com.example.thinking.newsell.view.seeshop.ShopFragments.ShopFragmentAdapter;
import com.example.thinking.newsell.view.seeshop.ShopFragments.ShopHomeFragment;
import com.example.thinking.newsell.view.seeshop.ShopFragments.ShopInfoFragment;
import com.example.thinking.newsell.view.seeshop.ShopFragments.ShopNewFragment;
import com.example.thinking.newsell.view.seeshop.ShopInfo.IntroductionShop;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    RatingBar shopValuation;
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
    ShopHomeFragment shopHomeFragment = new ShopHomeFragment();
    ShopAllFragment shopAllFragment = new ShopAllFragment();
    ShopNewFragment shopNewFragment = new ShopNewFragment();
    ShopInfoFragment shopInfoFragment = new ShopInfoFragment();
    ShopFragmentAdapter shopFragmentAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        ButterKnife.bind(this);

        final Shop shop = (Shop) getIntent().getSerializableExtra(Commen.SHOPINFO);
        Glide.with(ShopActivity.this).load(shop.getHeadershow()).into(shopImage);
        Glide.with(ShopActivity.this).load(shop.getLogo()).into(shopLogo);
        shopName.setText(shop.getShopname());
        shopLocation.setText(shop.getSaddress());
        shopValuation.setRating((float) shop.getSlevel());
        toolbarShopName.setText(shop.getShopname());

      /*  RoundedBitmapDrawable drawableB = RoundedBitmapDrawableFactory.create(getResources(), id2Bitmap(this, R.drawable.icon_avatar));
        drawableB.setCornerRadius(30L);
        rbB.setImageDrawable(drawableB);*/

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
                Toast.makeText(ShopActivity.this, "商店地址", Toast.LENGTH_SHORT).show();
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

        //设置状态栏
        //沉浸式状态栏
  /*      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        //通过CollapsingToolbarLayout修改字体颜色
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.BLACK);//设置还没收缩时状态下字体颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后Toolbar上字体的颜色
        setSupportActionBar(toolbar);*/
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //给页面设置工具栏
        if (mCollapsingToolbarLayout != null) {
            //设置隐藏图片时候ToolBar的颜色
            mCollapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorWhite));
            //  mCollapsingToolbarLayout.setContentScrimColor(Color.parseColor(R.color.colorPrimary));
            //设置工具栏标题
            mCollapsingToolbarLayout.setTitle("newsell");
        }
        appbarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                Log.d("STATE", state.name());
                if (state == State.EXPANDED) {
                    //展开状态
                    /*shopBack.setImageResource(R.drawable.ic_navigate_before_white_24dp);
                    shopMore.setImageResource(R.drawable.ic_more_vert_white_24dp);
                    toolbarShopName.setTextColor(getResources().getColor(R.color.colorWhite));*/
                    toolbar.setBackgroundColor(Color.TRANSPARENT);
                } else if (state == State.COLLAPSED) {
                    //折叠状态
                    //shopBack.setImageResource(R.drawable.ic_navigate_before_black_24dp);
                    // shopMore.setImageResource(R.drawable.ic_more_vert_black_24dp);
                    //  toolbarShopName.setTextColor(getResources().getColor(R.color.colorBlack));
                    toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                }/* else {
                    //中间状态
                    Toast.makeText(ShopActivity.this, "中间状态", Toast.LENGTH_SHORT).show();
                }*/
            }
        });
        shopMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putSerializable(Commen.SHOPINFO,shop);
                Intent intent = new Intent(ShopActivity.this, IntroductionShop.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


        Bundle bundle = new Bundle();
        bundle.putString(Commen.SHOWPICS, shop.getShowpic());
        shopInfoFragment.setArguments(bundle);

        fragmentlist.add(shopAllFragment);
        fragmentlist.add(shopHomeFragment);
        fragmentlist.add(shopNewFragment);
        fragmentlist.add(shopInfoFragment);
        mTitles = getResources().getStringArray(R.array.tab_titles);
        // String titles[] = {"首页", "所有商品", "新品"};
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
