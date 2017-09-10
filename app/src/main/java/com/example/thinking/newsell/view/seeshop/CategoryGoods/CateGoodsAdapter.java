package com.example.thinking.newsell.view.seeshop.CategoryGoods;

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

import java.util.ArrayList;
import java.util.List;

/**
 * *****************************************
 * Created by thinking on 2017/5/30.
 * 创建时间：
 * <p>
 * 描述：分类查看的adapter
 * <p/>
 * <p/>
 * *******************************************
 */

public class CateGoodsAdapter extends RecyclerView.Adapter<CateGoodsAdapter.CategoodViewHolder> {

    private List<Commodity> Dates = new ArrayList<>();
    private Context context;

    public CateGoodsAdapter(Context context, List<Commodity> Datas) {
        this.context = context;
        this.Dates = Datas;

    }

    @Override
    public CategoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CategoodViewHolder holder = new CategoodViewHolder(LayoutInflater.from(context).inflate(R.layout.category_list0, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final CategoodViewHolder holder, final int position) {
        /* 得到item的LayoutParams布局参数*/
        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
        holder.itemView.setLayoutParams(params);//把params设置item布局
        holder.cate_good_name.setText(Dates.get(position).getProductname());//为控件绑定数据
        holder.cate_good_num.setText(Dates.get(position).getSalesvolu()+"人付款");
      /*  NetWorks.getIDshop(Dates.get(position).getSid(), new BaseObserver<Shop>() {
            @Override
            public void onHandleSuccess(Shop shop) {
                holder.cate_good_location.setText(shop.getSaddress().substring(0,3));
            }
        });*/
       holder.cate_good_location.setVisibility(View.GONE);
        holder.cate_good_money.setText(String.valueOf(Dates.get(position).getPrice()));
        Glide.with(context).load(Dates.get(position).getLogo()).fitCenter().into(holder.imageView);
        /* item点击跳转至商品详情页*/
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putSerializable(Commen.COMMODITY,Dates.get(position));
                Intent intent=new Intent(context,GoodActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return Dates.size();
    }


    public class CategoodViewHolder extends RecyclerView.ViewHolder {
        TextView cate_good_name;
        TextView  cate_good_location;
        TextView cate_good_money;
        TextView cate_good_num;
        ImageView imageView;

        public CategoodViewHolder(final View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageview_cate_good);
            cate_good_name = (TextView) itemView.findViewById(R.id.cate_good_name);
            cate_good_location = (TextView) itemView.findViewById(R.id.cate_good_location);
            cate_good_num = (TextView) itemView.findViewById(R.id.cate_good_num);
            cate_good_money = (TextView) itemView.findViewById(R.id.cate_good_money);

        }
    }

}
