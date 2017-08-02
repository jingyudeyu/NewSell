package com.example.thinking.newsell.view.seeshop.ShopFragments;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.thinking.newsell.R;
import com.example.thinking.newsell.bean.Commodity;

import java.util.List;

/**
 * *****************************************
 * Created by thinking on 2017/7/28.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MainViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<Commodity> commodities;

    public HomeAdapter(Context context,List<Commodity> commodities){
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"我是"+position+"个",Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return commodities.size();
    }

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
