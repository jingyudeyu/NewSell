package com.example.thinking.newsell.view.seeshop.GoodInfo.Attention;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.thinking.newsell.R;
import com.example.thinking.newsell.bean.Commodity;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

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
    private Commodity commodity;
    private AttGoodAdapter attGoodAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.good_attention);
        ButterKnife.bind(this);

        //标题栏及返回键
        setSupportActionBar(attToolbar);
        commodity = (Commodity) getIntent().getSerializableExtra("commodity");//商品信息
        Glide.with(this).load(commodity.getLogo()).bitmapTransform(new CropCircleTransformation(this)).into(attGoodImage);
        attGoodName.setText(commodity.getProductname());

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        attRecycler.setLayoutManager(linearLayoutManager);
       // attGoodAdapter=new AttGoodAdapter(this,);
        attRecycler.setAdapter(attGoodAdapter);


        attToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
