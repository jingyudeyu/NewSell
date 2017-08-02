package com.example.thinking.newsell.view.seeshop.ShopInfo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.thinking.newsell.R;
import com.example.thinking.newsell.bean.Shop;
import com.example.thinking.newsell.commen.Commen;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * *****************************************
 * Created by thinking on 2017/7/25.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class IntroductionShop extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.image_logo)
    ImageView imageLogo;
    @BindView(R.id.text_type)
    TextView textType;
    @BindView(R.id.text_shopname)
    TextView textShopname;
    @BindView(R.id.text_location)
    TextView textLocation;
    @BindView(R.id.text_line)
    TextView textLine;
    @BindView(R.id.rel_license)
    RelativeLayout relLicense;
    @BindView(R.id.text_introduction)
    TextView textIntroduction;
    @BindView(R.id.rel_line)
    RelativeLayout relLine;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_introduction);
        ButterKnife.bind(this);
        toolbar.setTitle(R.string.shop_introduction);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Shop shop = (Shop) getIntent().getSerializableExtra(Commen.SHOPINFO);
        Glide.with(IntroductionShop.this).load(shop.getLogo()).into(imageLogo);
        textType.setText(shop.getType());
        textShopname.setText(shop.getShopname());
        textLocation.setText(shop.getSaddress());
        textLine.setText(shop.getStel());
        textIntroduction.setText(shop.getSubscrib());

        /*店铺电话*/
        relLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + shop.getStel()));
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
            }
        });

        /*营业执照查看*/
        relLicense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString(Commen.LICENSE,shop.getPermitpic());
                Intent intent = new Intent(IntroductionShop.this, IntroductionLicense.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return false;
    }
}
