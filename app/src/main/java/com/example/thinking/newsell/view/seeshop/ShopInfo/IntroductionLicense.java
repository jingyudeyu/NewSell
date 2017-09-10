package com.example.thinking.newsell.view.seeshop.ShopInfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.thinking.newsell.R;
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

public class IntroductionLicense extends AppCompatActivity {
    @BindView(R.id.image_license)
    ImageView imageLicense;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduction_license);
        ButterKnife.bind(this);
        toolbar.setTitle(R.string.business_license);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String license=getIntent().getStringExtra(Commen.LICENSE);
        Glide.with(IntroductionLicense.this).load(license).into(imageLicense);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
