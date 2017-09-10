package com.example.thinking.newsell.view.seeshop.ShopFragments.InfoFragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.Commodity;
import com.example.thinking.newsell.commen.Commen;
import com.example.thinking.newsell.view.seeshop.GoodInfo.GoodActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * *****************************************
 * Created by thinking on 2017/7/31.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class InfoShelfGood extends AppCompatActivity {
    @BindView(R.id.infopage_recyclerview)
    RecyclerView infopageRecyclerview;

    LinearLayoutManager linearLayoutManager;
    @BindView(R.id.shelf_toolbar)
    Toolbar shelfToolbar;
    private List<Commodity> commodityList;
    private ItemAdapter itemAdapter;
    private int statue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_infopage);
        ButterKnife.bind(this);

        Bundle bundle = new Bundle();
        commodityList = (List<Commodity>) getIntent().getSerializableExtra(Commen.ABOVEGOODS);
        statue=commodityList.get(0).getStatue();
        if (statue==0){//判断已上架状态
            shelfToolbar.setTitle(getResources().getString(R.string.shelf_statue0));
            shelfToolbar.setVisibility(View.VISIBLE);

        }else if (statue==1){//判断已下架状态
            shelfToolbar.setTitle(getResources().getString(R.string.shelf_statue1));
            shelfToolbar.setVisibility(View.VISIBLE);
        }
       setSupportActionBar(shelfToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//返回键

        linearLayoutManager = new LinearLayoutManager(InfoShelfGood.this);
        infopageRecyclerview.setLayoutManager(linearLayoutManager);
        itemAdapter = new ItemAdapter(InfoShelfGood.this, commodityList);
        infopageRecyclerview.setAdapter(itemAdapter);

    }

    //标题栏返回键
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return false;
    }


    public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {
        private Context mcontext;
        private LayoutInflater mLayoutInflater;
        private List<Commodity> commodityList;

        public ItemAdapter(Context mcontext, List<Commodity> commodityList) {
            this.mcontext = mcontext;
            this.commodityList = commodityList;
            this.mLayoutInflater = LayoutInflater.from(mcontext);

        }

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ItemHolder(mLayoutInflater.inflate(R.layout.good_item_v, parent, false));
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, final int position) {
            Glide.with(mcontext).load(commodityList.get(position).getLogo()).centerCrop().into(holder.itemImage);
            holder.itemName.setText(commodityList.get(position).getProductname());
            holder.itemPrice.setText(String.valueOf(commodityList.get(position).getPrice()));
            holder.itemPerson.setText(commodityList.get(position).getSalesvolu()+"人已购买");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NetWorks.getIDgood(commodityList.get(position).getCid(), new BaseObserver<Commodity>() {
                        @Override
                        public void onHandleSuccess(Commodity commodity) {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("commodity", commodity);
                            Intent intent = new Intent(mcontext, GoodActivity.class);
                            intent.putExtras(bundle);
                            mcontext.startActivity(intent);
                        }

                        @Override
                        public void onHandleError(int code, String message) {

                        }
                    });
                }
            });
        }

        @Override
        public int getItemCount() {
            return commodityList.size();
        }

        public class ItemHolder extends RecyclerView.ViewHolder {

            private ImageView itemImage;
            private TextView itemName;
            private TextView itemPrice;
            private TextView itemPerson;

            public ItemHolder(View itemView) {
                super(itemView);
                itemImage = (ImageView) itemView.findViewById(R.id.item_image);
                itemName = (TextView) itemView.findViewById(R.id.item_name);
                itemPrice = (TextView) itemView.findViewById(R.id.item_price);
                itemPerson = (TextView) itemView.findViewById(R.id.item_person);

            }
        }
    }
}
