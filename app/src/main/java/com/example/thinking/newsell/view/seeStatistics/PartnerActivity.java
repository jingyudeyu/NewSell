package com.example.thinking.newsell.view.seeStatistics;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.Partner;
import com.example.thinking.newsell.commen.Commen;
import com.example.thinking.newsell.utils.system.SpUtils;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.baidu.location.d.j.s;

/**
 * *****************************************
 * Created by thinking on 2017/8/25.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class PartnerActivity extends AppCompatActivity {

    @BindView(R.id.statistics_title)
    TextView statisticsTitle;
    @BindView(R.id.recycler_statistics)
    RecyclerView recyclerPartner;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.right_title)
    TextView rightTitle;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    LinearLayoutManager linearLayoutManager;
    PartnerAdapter partnerAdapter;
    int page = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_statistics);
        ButterKnife.bind(this);
        init();
        SimpleDateFormat simple = new SimpleDateFormat("yyy-MM-dd");
        String date = simple.format(new java.util.Date());
        NetWorks.getBySDPartenrs(SpUtils.getInt(this, Commen.SHOPSIDdefault), date, page, new BaseObserver<List<Partner>>() {
            @Override
            public void onHandleSuccess(List<Partner> partnerList) {
                partnerAdapter = new PartnerAdapter(PartnerActivity.this, partnerList);
                recyclerPartner.setAdapter(partnerAdapter);
            }

            @Override
            public void onHandleError(int code, String message) {

            }
        });

    }

    private void init() {
        statisticsTitle.setText("合作商品");
        titleBack.setVisibility(View.VISIBLE);
        rightTitle.setVisibility(View.VISIBLE);
        reTitle.setBackgroundResource(R.color.colorbg);//设置title的背景颜色
        //返回键
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerPartner.setLayoutManager(linearLayoutManager);

    }
}
