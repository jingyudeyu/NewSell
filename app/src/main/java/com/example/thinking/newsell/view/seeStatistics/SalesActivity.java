package com.example.thinking.newsell.view.seeStatistics;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.Commodity;
import com.example.thinking.newsell.bean.OrderGood;
import com.example.thinking.newsell.commen.Commen;
import com.example.thinking.newsell.utils.system.SpUtils;

import java.text.SimpleDateFormat;
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
    @BindView(R.id.statistics_title)
    TextView salesTitle;
    @BindView(R.id.recycler_statistics)
    RecyclerView recyclerSales;
    LinearLayoutManager linearLayoutManager;
    int page = 0;
    SalesAdapter salesAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_statistics);
        ButterKnife.bind(this);
        salesTitle.setText("今日销售商品");
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerSales.setLayoutManager(linearLayoutManager);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd");
        String date = simpleDateFormat.format(new java.util.Date());

        NetWorks.getBySDGoods(SpUtils.getInt(this, Commen.SHOPSIDdefault, 0), date, page, new BaseObserver<List<Commodity>>() {
            @Override
            public void onHandleSuccess(List<Commodity> commodityList) {
                if (commodityList.size()!=0){
                    salesAdapter = new SalesAdapter(SalesActivity.this, commodityList);
                    recyclerSales.setAdapter(salesAdapter);
                }

            }

            @Override
            public void onHandleError(int code, String message) {
                Toast.makeText(SalesActivity.this, code + message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
