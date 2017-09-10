package com.example.thinking.newsell.view.seeshop.ShopFragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import static com.example.thinking.newsell.R.id.item_person;

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

public class AllGoodAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private GridLayoutManager mlayoutManager;
    private List<Commodity> commodities;//商品列表
    private static final int typelinear=1;
    private static final int typegrid=2;

    public AllGoodAdapter(Context context, List<Commodity> commodities, GridLayoutManager layoutManager) {
        this.context = context;
        this.commodities = commodities;
        this.mlayoutManager = layoutManager;
        this.layoutInflater = LayoutInflater.from(context);

    }

    public int getItemViewType(int position) {


        int n = mlayoutManager.getSpanCount();
        if (n == 1) {
            return typelinear;
        } else return typegrid;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==typegrid) {
            View view = layoutInflater.inflate(R.layout.good_item, parent, false);
            MainViewHolder holder = new MainViewHolder(view);
            return holder;
        } else if (viewType==typelinear){
            View view = layoutInflater.inflate(R.layout.good_item_v, parent, false);
            VMainViewHolder holder1 = new VMainViewHolder(view);
            return holder1;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (getItemViewType(position)==typegrid){
            MainViewHolder holder1=(MainViewHolder)holder;
            holder1.shopGoodname.setText(commodities.get(position).getProductname());
            holder1.shopGoodprice.setText(String.valueOf(commodities.get(position).getPrice()));
            Glide.with(context).load(commodities.get(position).getLogo()).fitCenter().into(holder1.shopGoodimage);
            //跳转至商品信息页
            holder1.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, GoodActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Commen.COMMODITY, commodities.get(position));
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }else if (getItemViewType(position)==typelinear){
            VMainViewHolder vMainViewHolder=(VMainViewHolder)holder;
            vMainViewHolder.itemName.setText(commodities.get(position).getProductname());
            vMainViewHolder.itemPrice.setText(String.valueOf(commodities.get(position).getPrice()));
            Glide.with(context).load(commodities.get(position).getLogo()).fitCenter().into(vMainViewHolder.itemImage);
            //跳转至商品信息页
            vMainViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, GoodActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Commen.COMMODITY, commodities.get(position));
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });

        }
    }


    @Override
    public int getItemCount() {
        return commodities.size();
    }


    //店铺信息中商品信息的展示 grid
    public static class MainViewHolder extends RecyclerView.ViewHolder {
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

    //店铺信息中商品信息的展示 linear
    public static class VMainViewHolder extends RecyclerView.ViewHolder {
        public ImageView itemImage;
        public TextView itemName;
        public TextView itemPrice;
        public TextView itemPerson;

        public VMainViewHolder(View itemView) {
            super(itemView);
            itemImage = (ImageView) itemView.findViewById(R.id.item_image);
            itemName = (TextView) itemView.findViewById(R.id.item_name);
            itemPrice = (TextView) itemView.findViewById(R.id.item_price);
            itemPerson = (TextView) itemView.findViewById(R.id.item_person);

        }
    }
}
