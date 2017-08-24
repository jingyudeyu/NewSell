package com.example.thinking.newsell.view.seeshop.CategoryGoods;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thinking.newsell.R;
import com.example.thinking.newsell.bean.Commodity;
import com.example.thinking.newsell.commen.Commen;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * *****************************************
 * Created by thinking on 2017/7/21.
 * 创建时间：
 * <p>
 * 描述：店铺分类查看商品的页面
 * <p/>
 * <p/>
 * *******************************************
 */

public class CategoryGoodsActivity extends AppCompatActivity {

    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.category_toolbar)
    Toolbar categoryToolbar;
    @BindView(R.id.category_goods_recyclerview)
    RecyclerView categoryGoodsRecyclerview;

    private CateGoodsAdapter mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_goods);
        ButterKnife.bind(this);
        final List<Commodity> goodscate=(List<Commodity>) getIntent().getSerializableExtra(Commen.CATEGORYGOODLIST);
        final String name_small=(String)getIntent().getSerializableExtra(Commen.CATEGORYNAME);
        if(goodscate!=null) {
            titleName.setText(name_small);
            categoryGoodsRecyclerview.setLayoutManager(new LinearLayoutManager(CategoryGoodsActivity.this));
            mAdapter = new CateGoodsAdapter(CategoryGoodsActivity.this, goodscate);
            categoryGoodsRecyclerview.setAdapter(mAdapter);
            categoryGoodsRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        }
        else
            Toast.makeText(this, "该分类暂无商品", Toast.LENGTH_SHORT).show();
    }
}
