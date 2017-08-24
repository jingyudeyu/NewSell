package com.example.thinking.newsell.view.seeshop.ShopFragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.thinking.newsell.R;
import com.example.thinking.newsell.bean.Commodity;
import com.example.thinking.newsell.commen.Commen;
import com.example.thinking.newsell.view.seeshop.GoodInfo.GoodActivity;

import java.util.List;

/**
 * *****************************************
 * Created by thinking on 2017/7/28.
 * 创建时间：
 * <p>
 * 描述：店铺的全部商品页面布局的adapter
 * <p/>
 * <p/>
 * *******************************************
 */

public class AllGoodAdapter extends RecyclerView.Adapter<AllGoodAdapter.MainViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<Commodity> commodities;//商品列表

    public AllGoodAdapter(Context context, List<Commodity> commodities){
        this.context=context;
        this.commodities=commodities;
        this.layoutInflater=LayoutInflater.from(context);

    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=layoutInflater.inflate(R.layout.good_item,parent,false);
        MainViewHolder holder=new MainViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, final int position) {
        holder.shopGoodname.setText(commodities.get(position).getProductname());
        holder.shopGoodprice.setText(String.valueOf(commodities.get(position).getPrice()));
        Glide.with(context).load(commodities.get(position).getLogo()).fitCenter().into(holder.shopGoodimage);

        //跳转至商品信息页
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, GoodActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable(Commen.COMMODITY,commodities.get(position));
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return commodities.size();
    }


    //店铺信息中商品信息的展示
    static class MainViewHolder extends RecyclerView.ViewHolder {
        public ImageView shopGoodimage;
        public TextView shopGoodname;
        public TextView shopGoodprice;

        public MainViewHolder(View itemView) {
            super(itemView);
            shopGoodimage = (ImageView) itemView.findViewById(R.id.shop_goodimage);
            shopGoodname = (TextView) itemView.findViewById(R.id.shop_goodname);
            shopGoodprice = (TextView) itemView.findViewById(R.id.shop_goodprice);

        }
    }
}
