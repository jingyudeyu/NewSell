package com.example.thinking.newsell.view.seeshop.ShopFragments;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thinking.newsell.R;

/**
 * *****************************************
 * Created by thinking on 2017/8/29.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class ManyHomeAdapter extends RecyclerView.Adapter {
private Context mContext;


    public ManyHomeAdapter(Context context){
        this.mContext=context;
    }
    @Override
    public int getItemViewType(int position) {


        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    /*    @Override
          public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
          /*   return new MyHolder(LayoutInflater.from(mContext).inflate(R.layout.item, parent, false));
         if (viewType == 1) {
            return new SubAdapter.CategoryHolder(LayoutInflater.from(mContext).inflate(R.layout.horscrollview_item, parent, false));
          }
          else if (viewType == 2) {
              return new SubAdapter.ImageHolder(LayoutInflater.from(mContext).inflate(R.layout.vlayout_linear_image, parent, false));
              View view=LayoutInflater.from(mContext).inflate(R.layout.good_item,parent,false);
          }
      }*/
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }




    //广告栏的展示


    //优惠券的展示


    //推荐商品的展示
    static class MyHolder extends RecyclerView.ViewHolder {
        public TextView selling_name;
        public ImageView selling_image;
        public TextView selling_money;

        public MyHolder(View itemView) {
            super(itemView);
            selling_image = (ImageView) itemView.findViewById(R.id.selling_image);
            selling_name = (TextView) itemView.findViewById(R.id.selling_name);
            selling_money = (TextView) itemView.findViewById(R.id.selling_money);
        }
    }

    //类别信息的展示
    static class CategoryHolder extends RecyclerView.ViewHolder {
        public TextView coupon_textview;
        public ImageView coupon_imageview;

        public CategoryHolder(View itemView) {
            super(itemView);
            coupon_textview = (TextView) itemView.findViewById(R.id.coupon_textview);
            coupon_imageview = (ImageView) itemView.findViewById(R.id.coupon_imageview);
        }
    }

    //商品信息的展示
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
