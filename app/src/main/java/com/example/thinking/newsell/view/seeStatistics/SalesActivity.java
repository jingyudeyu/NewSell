package com.example.thinking.newsell.view.seeStatistics;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.Commodity;
import com.example.thinking.newsell.commen.Commen;
import com.example.thinking.newsell.utils.system.SpUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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

public class SalesActivity extends AppCompatActivity {
    @BindView(R.id.total_back)
    ImageView totalBack;
    @BindView(R.id.total_title)
    TextView totalTitle;
    @BindView(R.id.total_right)
    TextView totalRight;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    @BindView(R.id.recycler_total)
    RecyclerView recyclerTotal;
    @BindView(R.id.nullorder)
    LinearLayout nullorder;
    LinearLayoutManager linearLayoutManager;
    int page = 0;
    SalesAdapter salesAdapter;

    private int lastVisibleItem;

    private List<Commodity> commoditys=new ArrayList<>();

    //salesmark 0 代表今天、7 代表所有
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sales_total);
        ButterKnife.bind(this);
        final int salesmark = getIntent().getIntExtra(Commen.SALESMARK, 0);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerTotal.setLayoutManager(linearLayoutManager);

        initView(salesmark);

    }

    //初始化 title的内容
    private void initView(int salesmark) {
        if (salesmark == 0) {
            totalTitle.setText("今日销售商品");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd");
            final String date = simpleDateFormat.format(new Date());
            if (page == 0) {
                getTodaySales(date, page);
            }
            recyclerTotal.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if (newState==RecyclerView.SCROLL_STATE_IDLE&&lastVisibleItem+1==salesAdapter.getItemCount()){
                        page=page+1;
                        getTodaySales(date, page);
                    }
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    lastVisibleItem=linearLayoutManager.findLastVisibleItemPosition();
                }
            });

        } else if (salesmark == 7) {
            totalTitle.setText("全部销售商品");

            if (page == 0) {
                getAllSales(page);
            }
            recyclerTotal.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if (newState==RecyclerView.SCROLL_STATE_IDLE&&lastVisibleItem+1==salesAdapter.getItemCount()){
                        page=page+1;
                        getAllSales(page);
                    }
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    lastVisibleItem=linearLayoutManager.findLastVisibleItemPosition();
                }
            });

        }

        totalBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    //获得今天的销售商品
    public void getTodaySales(String date, final int page) {
        NetWorks.getBySDGoods(SpUtils.getInt(this, Commen.SHOPSIDdefault, 0), date, page, new BaseObserver<List<Commodity>>() {
            @Override
            public void onHandleSuccess(List<Commodity> commodityList) {
                if (page == 0) {
                    if (commodityList.size() != 0) {
                        commoditys = commodityList;
                        salesAdapter = new SalesAdapter(SalesActivity.this, commoditys);
                        recyclerTotal.setAdapter(salesAdapter);
                    } else {
                        nullorder.setVisibility(View.VISIBLE);
                        recyclerTotal.setVisibility(View.GONE);
                    }
                } else {
                    if (commodityList.size() != 0) {
                        commoditys.addAll(commodityList);
                        salesAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onHandleError(int code, String message) {

              //  Toast.makeText(SalesActivity.this, code + message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //获得全部的销售商品
    public void getAllSales(final int page) {
        NetWorks.getGoodSales(SpUtils.getInt(SalesActivity.this, Commen.SHOPSIDdefault), page, new BaseObserver<List<Commodity>>() {
            @Override
            public void onHandleSuccess(List<Commodity> commodities) {
                if (page == 0) {
                    if (commodities.size() != 0) {
                        commoditys = commodities;
                        salesAdapter = new SalesAdapter(SalesActivity.this, commoditys);
                        recyclerTotal.setAdapter(salesAdapter);
                    } else {
                        nullorder.setVisibility(View.VISIBLE);
                        recyclerTotal.setVisibility(View.GONE);
                    }
                } else {
                    if (commodities.size() != 0) {
                        commoditys.addAll(commodities);
                        salesAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onHandleError(int code, String message) {
               //     Toast.makeText(SalesActivity.this, code + message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
