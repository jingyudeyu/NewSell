package com.example.thinking.newsell.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.Resource;
import com.example.thinking.newsell.MainActivity;
import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.Shop;
import com.example.thinking.newsell.bean.User;
import com.example.thinking.newsell.commen.Commen;
import com.example.thinking.newsell.utils.system.SpUtils;
import com.example.thinking.newsell.view.HomeFragments.MyFragment;
import com.example.thinking.newsell.view.HomeFragments.NewsFragment;
import com.example.thinking.newsell.view.HomeFragments.ShopManageFragment;
import com.example.thinking.newsell.view.HomeFragments.StatisticsFragment;
import com.example.thinking.newsell.view.HomeFragments.ViewPagerAdapter;
import com.example.thinking.newsell.view.seekpartners.SeekPartner;
import com.example.thinking.newsell.view.seemy.MyselfInfo;
import com.example.thinking.newsell.view.seeshop.ShopActivity;
import com.example.thinking.newsell.view.seeshop.ShopFragments.ShopFragmentAdapter;
import com.example.thinking.newsell.view.views.BottomNavigationViewHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.id.list;
import static com.example.thinking.newsell.MyApplication.getContext;
import static com.example.thinking.newsell.R.id.viewPager;
import static com.example.thinking.newsell.utils.system.SpUtils.removeSP;

/**
 * *****************************************
 * Created by thinking on 2017/7/10.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    ShopManageFragment shopManageFragment;
    NewsFragment newsFragment;
    StatisticsFragment statisticsFragment;
    MyFragment myFragment;

    BottomNavigationView bottomNavigationView;
    private MenuItem menuItem;
    private String[] titles = {"店铺管理", "合作商家", "消息", "我的店铺"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        toolbar.setTitleTextColor(this.getResources().getColor(R.color.colorWhite));
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);



        //判断用户信息是否发生变化，有变化则重新登录
        if (SpUtils.getBoolean(HomeActivity.this, Commen.USERLOGIN)) {
            final User user_old = (User) SpUtils.getObject(HomeActivity.this, Commen.USERINFO);
            NetWorks.getUserInfo(user_old.getBid(), new BaseObserver<User>() {
                @Override
                public void onHandleSuccess(User user) {
                    if (!user_old.getPassword().equals(user.getPassword()) || !user_old.getNickname().equals(user.getNickname()) || !user_old.getPhone().equals(user.getPhone())) {

                        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }

                @Override
                public void onHandleError(int code, String message) {

                }
            });
        }

        //有关于侧滑菜单的内容
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        //侧滑菜单中的列表菜单
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_camera) {
                    Intent intent1 = new Intent(HomeActivity.this, MyselfInfo.class);
                    startActivity(intent1);
                } else if (id == R.id.nav_gallery) {

                    SpUtils.removeSP(HomeActivity.this);
                    Intent intentout = new Intent(HomeActivity.this, MainActivity.class);
                    startActivity(intentout);
                } else if (id == R.id.nav_slideshow) {
                    Intent intentout = new Intent(HomeActivity.this, ShopActivity.class);
                    startActivity(intentout);

                } else if (id == R.id.nav_manage) {

                } else if (id == R.id.nav_share) {

                } else if (id == R.id.nav_send) {

                }

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        LayoutInflater.from(getContext()).inflate(R.layout.nav_header_main, navigationView);
        navigationView.setItemIconTintList(null);
        ImageView head = (ImageView) findViewById(R.id.imageView);
        TextView Name = (TextView) findViewById(R.id.text_user_name);
        TextView Name2 = (TextView) findViewById(R.id.text_user_name2);

        User user = (User) SpUtils.getObject(HomeActivity.this, Commen.USERINFO);
        Glide.with(getContext()).load(user.getPic()).into(head);
        Name.setText(user.getNickname());
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MyselfInfo.class);
                startActivity(intent);
            }
        });

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bootom_navigation);
        //底部导航栏等于多于4时使用BottomNavigationViewHelper禁止选中放大的效果
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        //底部导航栏的监听
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.shop_manage:
                        viewpager.setCurrentItem(0);
                        break;
                    case R.id.my:
                        viewpager.setCurrentItem(1);
                        break;
                    case R.id.news:
                        viewpager.setCurrentItem(2);
                        break;
                    case R.id.statistics:
                        viewpager.setCurrentItem(3);
                        break;
                }
                return false;
            }
        });

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //禁止ViewPager滑动
        viewpager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        setupViewPager(viewpager);

    }

    //viewpager中加入4个fragment
    private void setupViewPager(ViewPager viewpager) {

        shopManageFragment=new ShopManageFragment();
        myFragment=new MyFragment();
        newsFragment=new NewsFragment();
        statisticsFragment=new StatisticsFragment();
        //ViewPager的适配器
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(shopManageFragment);
        viewPagerAdapter.addFragment(myFragment);
        viewPagerAdapter.addFragment(newsFragment);
        viewPagerAdapter.addFragment(statisticsFragment);
        viewpager.setAdapter(viewPagerAdapter);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
