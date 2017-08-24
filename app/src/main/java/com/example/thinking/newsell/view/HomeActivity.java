package com.example.thinking.newsell.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

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
import com.example.thinking.newsell.view.views.BottomNavigationViewHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


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

    /*   @BindView(R.id.viewpager)
       ViewPager viewpager;*/
    FrameLayout frameLayout;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;


    private ShopManageFragment shopManageFragment;
    private NewsFragment newsFragment;
    private StatisticsFragment statisticsFragment;
    private MyFragment myFragment;
    private Fragment[] fragments;
    private int lastfragment = 0;

    BottomNavigationView bottomNavigationView;
    private MenuItem menuItem;
    private String[] titles = {"店铺管理", "合作商家", "消息", "我的店铺"};

    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_main);
        ButterKnife.bind(this);
        frameLayout = (FrameLayout) findViewById(R.id.frame);
        //底部导航栏
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bootom_navigation);


        //判断用户信息是否发生变化，有变化则重新登录
        if (SpUtils.getBoolean(HomeActivity.this, Commen.USERLOGIN)) {
            final User user_old = (User) SpUtils.getObject(HomeActivity.this, Commen.USERINFO);
            NetWorks.getUserInfo(user_old.getBid(), new BaseObserver<User>() {
                @Override
                public void onHandleSuccess(User user) {
                    if (!user_old.getPassword().equals(user.getPassword()) || !user_old.getNickname().equals(user.getNickname()) || !user_old.getPhone().equals(user.getPhone())) {
                        SpUtils.removeKey(HomeActivity.this, Commen.USERLOGIN);//删除登录boolean
                        SpUtils.putBoolean(HomeActivity.this, Commen.USERLOGIN, false);//登录boolean设为false
                        Intent intent = new Intent(HomeActivity.this, MainActivity.class);//回到登录页
                        startActivity(intent);
                        finish();
                    } else {
                        //根据店主id获取该店主所有店铺的店铺 sid,sid组成的list放入sharepreference中
                        NetWorks.getShopInfo(user.getBid(), new BaseObserver<List<Shop>>() {
                            @Override
                            public void onHandleSuccess(List<Shop> shops) {
                                SpUtils.removeIntArray(HomeActivity.this, Commen.SHOPSIDLIST);//删除店铺sidlist
                                SpUtils.removeKey(HomeActivity.this, Commen.SHOPSIDdefault);
                                List<Integer> integerList = new ArrayList<Integer>();
                                for (Shop shop : shops) {
                                    integerList.add(shop.getSid());
                                }
                                SpUtils.putIntArray(HomeActivity.this, Commen.SHOPSIDLIST, integerList);
                                SpUtils.putInt(HomeActivity.this, Commen.SHOPSIDdefault, integerList.get(0));
                            }

                            @Override
                            public void onHandleError(int code, String message) {
                                Toast.makeText(HomeActivity.this, code + message, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }

                @Override
                public void onHandleError(int code, String message) {
                    Toast.makeText(HomeActivity.this, code + message, Toast.LENGTH_SHORT).show();
                }
            });
        }

        initFragments();

        //底部导航栏等于多于4时使用BottomNavigationViewHelper禁止选中放大的效果
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        //底部导航栏的监听
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.shop_manage:
                        if (lastfragment!=0){
                            switchfragment(lastfragment,0);
                            lastfragment=0;
                        }
                        return true;
                    case R.id.my:
                        if (lastfragment!=1){
                            switchfragment(lastfragment,1);
                            lastfragment=1;
                        }
                        return true;
                    case R.id.news:
                        if (lastfragment!=2){
                            switchfragment(lastfragment,2);
                            lastfragment=2;
                        }
                        return true;
                    case R.id.statistics:
                        if (lastfragment!=3){
                            switchfragment(lastfragment,3);
                            lastfragment=3;
                        }
                        return true;
                }
                return false;
            }
        });

    }


    private void switchfragment(int last, int index) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.hide(fragments[last]);
        if (fragments[lastfragment].isAdded()) {
            fragmentTransaction.add(R.id.frame, fragments[index]);
        }
        fragmentTransaction.show(fragments[index]).commitAllowingStateLoss();

    }

    private void initFragments() {
        shopManageFragment = new ShopManageFragment();
        myFragment = new MyFragment();
        newsFragment = new NewsFragment();
        statisticsFragment = new StatisticsFragment();
        fragments = new Fragment[]{shopManageFragment, myFragment, newsFragment, statisticsFragment};
        lastfragment = 0;
        getSupportFragmentManager().beginTransaction().add(R.id.frame, shopManageFragment).show(shopManageFragment).commit();

    }

    private void initListenr() {
        //底部导航栏的监听
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.shop_manage:
                        //  viewpager.setCurrentItem(0);

                        fragmentTransaction.replace(R.id.frame, shopManageFragment);
                        break;
                    case R.id.my:
                        //   viewpager.setCurrentItem(1);
                        fragmentTransaction.replace(R.id.frame, myFragment);

                        break;
                    case R.id.news:
                        //  viewpager.setCurrentItem(2);
                        fragmentTransaction.replace(R.id.frame, newsFragment);

                        break;
                    case R.id.statistics:
                        //   viewpager.setCurrentItem(3);
                        fragmentTransaction.replace(R.id.frame, statisticsFragment);

                        break;
                }
                fragmentTransaction.commit();
                return false;
            }
        });

    }

  /*  private void addFragment(Fragment fragment, String tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frame, fragment, tag);
        transaction.commit();
    }*/


    /*退出应用*/
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(HomeActivity.this, "再按一次退出双线购", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
