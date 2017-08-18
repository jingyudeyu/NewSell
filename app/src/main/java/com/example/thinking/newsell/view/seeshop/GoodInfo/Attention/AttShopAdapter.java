package com.example.thinking.newsell.view.seeshop.GoodInfo.Attention;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.thinking.newsell.R;
import com.example.thinking.newsell.bean.GoodAttention;
import com.example.thinking.newsell.bean.ShopAttention;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * *****************************************
 * Created by thinking on 2017/8/16.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class AttShopAdapter extends RecyclerView.Adapter<AttShopAdapter.ShopAttHolder> {
    private Context context;
    private List<ShopAttention> shopAttentionlist;
    private LayoutInflater layoutInflater;

    public AttShopAdapter(Context context, List<ShopAttention> shopAttentionlist) {
            this.context = context;
            this.shopAttentionlist = shopAttentionlist;
            layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public ShopAttHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShopAttHolder(layoutInflater.inflate(R.layout.attention_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ShopAttHolder holder, int position) {
        ShopAttention oneshopAttention= shopAttentionlist.get(position);
        Glide.with(context).load(oneshopAttention.getUserlogo()).bitmapTransform(new CropCircleTransformation(context)).into(holder.attUserImage);
        holder.attUserName.setText(oneshopAttention.getUsername());
    }

    @Override
    public int getItemCount() {
        return shopAttentionlist.size();
    }

    public class ShopAttHolder extends RecyclerView.ViewHolder {
        private ImageView attUserImage;
        private TextView attUserName;

        public ShopAttHolder(View itemView) {
            super(itemView);
            attUserImage = (ImageView) itemView.findViewById(R.id.att_user_image);
            attUserName = (TextView) itemView.findViewById(R.id.att_user_name);
        }
    }
}
