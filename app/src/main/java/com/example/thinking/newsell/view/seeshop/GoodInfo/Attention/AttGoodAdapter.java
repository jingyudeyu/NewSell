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

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * *****************************************
 * Created by thinking on 2017/8/15.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public  class AttGoodAdapter extends RecyclerView.Adapter<AttGoodAdapter.GoodAttHolder> {
    private Context context;
    private List<GoodAttention> goodAttentionlist;
    private LayoutInflater layoutInflater;



    public AttGoodAdapter(Context context,List<GoodAttention> goodAttentions) {

            this.context = context;
            this.goodAttentionlist = goodAttentions;
            layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public GoodAttHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GoodAttHolder(layoutInflater.inflate(R.layout.attention_item, parent, false));
    }


    public void onBindViewHolder(GoodAttHolder holder, int position) {
            GoodAttention onegoodAttention= goodAttentionlist.get(position);
            Glide.with(context).load(onegoodAttention.getUserlogo()).bitmapTransform(new CropCircleTransformation(context)).into(holder.attUserImage);
            holder.attUserName.setText(onegoodAttention.getUsername());

    }

    @Override
    public int getItemCount() {
        return goodAttentionlist.size();
    }

    public class GoodAttHolder extends RecyclerView.ViewHolder {
        private ImageView attUserImage;
        private TextView attUserName;

        public GoodAttHolder(View itemView) {
            super(itemView);
            attUserImage = (ImageView) itemView.findViewById(R.id.att_user_image);
            attUserName = (TextView) itemView.findViewById(R.id.att_user_name);
        }
    }
}
