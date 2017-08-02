/*
 *
 *  *
 *  *  *
 *  *  *  * ===================================
 *  *  *  * Copyright (c) 2016.
 *  *  *  * 作者：安卓猴
 *  *  *  * 微博：@安卓猴
 *  *  *  * 博客：http://sunjiajia.com
 *  *  *  * Github：https://github.com/opengit
 *  *  *  *
 *  *  *  * 注意**：如果您使用或者修改该代码，请务必保留此版权信息。
 *  *  *  * ===================================
 *  *  *
 *  *  *
 *  *
 *
 */

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Monkey on 2015/6/29.
 */
public class FragmentHomeRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {


    public enum ITEM_TYPE {
        ITEM_TYPE_IMAGE,
        ITEM_TYPE_TEXT
    }


    private TextView HeadtextView;
    public Context mContext;
    public LayoutInflater mLayoutInflater;
    private List<Commodity> goods;
    List<String> categorys = new ArrayList<String>();
    Map<String, List<Commodity>> map = new HashMap<String, List<Commodity>>();
    private OnItemClickListener mOnItemClickListener = null;


    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


 /*   public FragmentHomeRecyclerViewAdapter(Context mContext, Map<String, List<Commodity>> map, List<String> categorys) {
        this.mContext = mContext;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.map=map;
        this.categorys=categorys;
        //this.goods = goods;
    }*/

  public FragmentHomeRecyclerViewAdapter(Context mContext, List<Commodity> goods) {
        this.mContext = mContext;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.goods = goods;
    }

    /*shishi*/
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal()) {
            return new HeadHolder(mLayoutInflater.inflate(R.layout.homepage_reit_head, parent, false));
        } else {
            return new ContentHolder(mLayoutInflater.inflate(R.layout.homepage_reit_content, parent, false));
        }
    }


    public int getItemViewType(int position) {

        return position % 2 == 0 ? ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal() : ITEM_TYPE.ITEM_TYPE_TEXT.ordinal();
    }


    /**
     * 创建ViewHolder
     */
  /*  @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
     *//*   View mView = mLayoutInflater.inflate(R.layout.homepage_reit_content, parent, false);

        ShopRecyclerViewHolder mViewHolder = new ShopRecyclerViewHolder(mView);
        mView.setOnClickListener(this);
        return mViewHolder;*//*
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case 0:
                ViewGroup vHead = (ViewGroup) mInflater.inflate(R.layout.homepage_reit_head, parent, false);
                HeadHolder vhHead = new HeadHolder(vHead);
                return vhHead;
            case 1:
                ViewGroup vContent = (ViewGroup) mInflater.inflate(R.layout.homepage_reit_content, parent, false);
                ContentHolder vhContent = new ContentHolder(vContent);
                return vhContent;
        }
        return null;

      *//*  for (int i=0;i<categorys.size();i++) {
            map.get(categorys.get(i))

        }*//*
            *//*if (viewType == ITEM_TYPE_head.ordinal()) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homepage_reit_head, parent, false);
                return new HeadHolder(view);
            } else if (viewType == ITEM_TYPE_content.ordinal()) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homepage_reit_content, parent, false);
                return new ContentHolder(view);
            }
            return null;*//*
        }
*/

    /**
     * 绑定ViewHoler，给item中的控件设置数据
     */
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof HeadHolder) {
            HeadtextView.setText("A");
        } else if (holder instanceof ContentHolder) {

            ((ContentHolder) holder).good_textview.setText(goods.get(position).getProductname());
            ((ContentHolder) holder).money_textview.setText(String.valueOf(goods.get(position).getPrice()));
            //使用Glide加载图片
            Glide.with(mContext)
                    .load(goods.get(position).getLogo())
                    .error(R.mipmap.ic_launcher)
                    .fitCenter()
                    .into(((ContentHolder) holder).good_imageview);
        }
        if (mOnItemClickListener != null) {
            ((ContentHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  /*  第一个 mOnItemClickListener.onItemClick(((VideoViewHolder)holder).videologo, position);
                   第二个 int position = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, position);*/
                    Toast.makeText(mContext, "点击了" + ((ContentHolder) holder).good_textview.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
            ((HeadHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*  第一个 mOnItemClickListener.onItemClick(((VideoViewHolder)holder).videologo, position);
                   第二个 int position = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, position);*/
                    Toast.makeText(mContext, "点击了...", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /*  public int getItemViewType(int position) {
          return 0;
          //return position % 5 == 0 ? ITEM_TYPE.ITEM_TYPE_Theme.ordinal() : ITEM_TYPE.ITEM_TYPE_Video.ordinal();
      }
  */
 /* @Override
  public int getItemCount() {
      return mTitles == null ? 0 : mTitles.length;
  }*/
    @Override
    public int getItemCount() {
        return goods.size();
    }

    /*HeadHolder*/
    public class HeadHolder extends RecyclerView.ViewHolder {

        public HeadHolder(View itemView) {
            super(itemView);
            HeadtextView = (TextView) itemView.findViewById(R.id.item_head);

        }
    }

    /*ContentHolder*/
    public class ContentHolder extends RecyclerView.ViewHolder {
        public ImageView good_imageview;
        public TextView good_textview;
        public TextView money_textview;

        public ContentHolder(View itemView) {
            super(itemView);
            good_imageview = (ImageView) itemView.findViewById(R.id.good_imageview);
            good_textview = (TextView) itemView.findViewById(R.id.good_textview);
            money_textview = (TextView) itemView.findViewById(R.id.money_textview);
        }
    }
}

