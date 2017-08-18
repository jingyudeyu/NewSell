package com.example.thinking.newsell.view.seeshop.GoodInfo.Attention;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.Commodity;
import com.example.thinking.newsell.bean.GoodAttention;
import com.example.thinking.newsell.bean.Shop;
import com.example.thinking.newsell.bean.ShopAttention;
import com.example.thinking.newsell.commen.Commen;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * *****************************************
 * Created by thinking on 2017/8/15.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class GoodAttentionActivity extends AppCompatActivity {
    @BindView(R.id.att_toolbar)
    Toolbar attToolbar;
    @BindView(R.id.att_good_image)
    ImageView attGoodImage;
    @BindView(R.id.att_good_name)
    TextView attGoodName;
    @BindView(R.id.att_rl_goodinfo)
    RelativeLayout attRlGoodinfo;
    @BindView(R.id.att_recycler)
    RecyclerView attRecycler;
    @BindView(R.id.toolbar_name)
    TextView toolbarName;
    private Commodity commodity;
    private AttGoodAdapter attGoodAdapter;
    private AttShopAdapter attShopAdapter;
    private List<GoodAttention> goodAttentions = new ArrayList<>();//关注商品的用户的信息列表
    private List<ShopAttention> shopAttentions = new ArrayList<>();//关注店铺的用户信息列表
    private Shop shop;
    private int type;
    private int page;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.good_attention);
        ButterKnife.bind(this);

        //标题栏及返回键
        setSupportActionBar(attToolbar);
        Bundle bundle = getIntent().getExtras();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        attRecycler.setLayoutManager(linearLayoutManager);

        if (getIntent().getExtras().getInt(Commen.ATTENTIONTYPE) == 0) {
            toolbarName.setText("关注商品");
            Log.v("类型", String.valueOf(getIntent().getExtras().getInt(Commen.ATTENTIONTYPE)));
            type = 0;
            commodity = (Commodity) getIntent().getSerializableExtra("commodity");//商品信息
            Glide.with(this).load(commodity.getLogo()).into(attGoodImage);
            attGoodName.setText(commodity.getProductname());
           // attRecycler.setVisibility(View.VISIBLE);
            NetWorks.getGoodAttention(commodity.getCid(), page, new BaseObserver<List<GoodAttention>>() {
                @Override
                public void onHandleSuccess(List<GoodAttention> goodAttentionlist) {
                    goodAttentions = goodAttentionlist;
                    Log.v("类型", String.valueOf(goodAttentions.size()));
                    attGoodAdapter = new AttGoodAdapter(GoodAttentionActivity.this, goodAttentions);
                    attRecycler.setAdapter(attGoodAdapter);
                }

                @Override
                public void onHandleError(int code, String message) {

                }
            });


        } else if (getIntent().getExtras().getInt(Commen.ATTENTIONTYPE) == 1) {
            toolbarName.setText("关注店铺");
            Log.v("类型", String.valueOf(getIntent().getExtras().getInt(Commen.ATTENTIONTYPE)));
            type = 1;
            shop = (Shop) getIntent().getSerializableExtra(Commen.SHOPINFO);
            Log.v("类型", shop.getShopname());
            //attRecycler.setVisibility(View.GONE);
            NetWorks.getShopAttention(shop.getSid(), page, new BaseObserver<List<ShopAttention>>() {
                @Override
                public void onHandleSuccess(List<ShopAttention> shopAttentionlist) {
                    shopAttentions = shopAttentionlist;
                    Log.v("类型", String.valueOf(shopAttentions.size()));
                    attShopAdapter = new AttShopAdapter(GoodAttentionActivity.this, shopAttentions);
                    attRecycler.setAdapter(attShopAdapter);
                }

                @Override
                public void onHandleError(int code, String message) {

                }
            });

        }


        attToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    public List<GoodAttention> getGoodAttentions() {
        NetWorks.getGoodAttention(commodity.getCid(), page, new BaseObserver<List<GoodAttention>>() {
            @Override
            public void onHandleSuccess(List<GoodAttention> goodAttentionlist) {
                goodAttentions = goodAttentionlist;
                // attGoodAdapter=new AttGoodAdapter(GoodAttentionActivity.this,goodAttentions,type);
                //attRecycler.setAdapter(attGoodAdapter);
            }

            @Override
            public void onHandleError(int code, String message) {

            }
        });
        return goodAttentions;
    }

    public Object getShopAttention() {
        NetWorks.getShopAttention(shop.getSid(), page, new BaseObserver<List<ShopAttention>>() {
            @Override
            public void onHandleSuccess(List<ShopAttention> shopAttentionlist) {
                shopAttentions = shopAttentionlist;
            }

            @Override
            public void onHandleError(int code, String message) {

            }
        });

        return shopAttentions;
    }
}
