package com.example.thinking.newsell.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.example.thinking.newsell.view.seekpartners.SeekPartner;
import com.example.thinking.newsell.view.seemy.MyselfInfo;
import com.example.thinking.newsell.view.seeshop.ShopActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.id.list;
import static com.example.thinking.newsell.MyApplication.getContext;
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

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.sp_clear)
    Button spClear;

    @BindView(R.id.sp_clear2)
    Button spClear2;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.home_tablayout)
    TabLayout homeTablayout;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private List<Fragment> listFragment;

    private String[] titles = {"店铺管理", "合作商家", "消息", "我的店铺"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        toolbar.setTitleTextColor(this.getResources().getColor(R.color.colorWhite));
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        if (SpUtils.getBoolean(HomeActivity.this, Commen.USERLOGIN)) {
            final User user_old = (User) SpUtils.getObject(HomeActivity.this, Commen.USERINFO);
            NetWorks.getUserInfo(user_old.getBid(), new BaseObserver<User>() {
                @Override
                public void onHandleSuccess(User user) {
                    if (!user_old.getPassword().equals(user.getPassword()) || !user_old.getNickname().equals(user.getNickname()) || !user_old.getPhone().equals(user.getPhone())) {

                        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {

                    }
                }
                @Override
                public void onHandleError(int code, String message) {

                }
            });
        }

        spClear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this, SeekPartner.class);
                startActivity(intent);
            }
        });




        spClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent1 = new Intent(HomeActivity.this, MyselfInfo.class);
               // startActivity(intent1);
                User user= (User) SpUtils.getObject(HomeActivity.this, Commen.USERINFO);
                Log.v("用户名字：",user.getName());
                Log.v("用户id：",String.valueOf(user.getBid()));
                NetWorks.getShopInfo(user.getBid(), new BaseObserver<Shop>() {
                    @Override
                    public void onHandleSuccess(Shop shop) {
                        //把店铺所在城市放入sp中保存
                        //跳转至商店页面
                        Log.v("店铺名字：",shop.getShopname());
                        SpUtils.putInt(HomeActivity.this,Commen.SHOPCITYID,shop.getCity());
                        Intent intent=new Intent(HomeActivity.this,ShopActivity.class);
                        Bundle bundle=new Bundle();
                        bundle.putSerializable(Commen.SHOPINFO,shop);
                        intent.putExtras(bundle);
                        startActivity(intent);

                    }

                    @Override
                    public void onHandleError(int code, String message) {

                    }
                });
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        LayoutInflater.from(getContext()).inflate(R.layout.nav_header_main, navigationView);
        navigationView.setItemIconTintList(null);
        ImageView head = (ImageView) findViewById(R.id.imageView);
       TextView Name = (TextView) findViewById(R.id.text_user_name);
       TextView Name2 = (TextView) findViewById(R.id.text_user_name2);

        User user=(User) SpUtils.getObject(HomeActivity.this,Commen.USERINFO);
        Glide.with(getContext()).load(user.getPic()).into(head);
        Name.setText(user.getNickname());
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),MyselfInfo.class);
                startActivity(intent);
            }
        });



        //页面，数据源
     /*   listFragment = new ArrayList<>();
        listFragment.add(new Tab1Fragment());
        listFragment.add(new Tab2Fragment());
        listFragment.add(new Tab3Fragment());
        listFragment.add(new Tab4Fragment());
        //ViewPager的适配器
        //adapter = new MyAdapter(getSupportFragmentManager(), this);
       // viewpager.setAdapter(adapter);
        //绑定
        homeTablayout.setupWithViewPager(viewpager);*/

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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
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

}
