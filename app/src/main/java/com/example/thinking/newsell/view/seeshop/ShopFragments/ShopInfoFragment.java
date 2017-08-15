package com.example.thinking.newsell.view.seeshop.ShopFragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.Commodity;
import com.example.thinking.newsell.bean.Partner;
import com.example.thinking.newsell.bean.User;
import com.example.thinking.newsell.commen.Commen;
import com.example.thinking.newsell.utils.system.SpUtils;
import com.example.thinking.newsell.view.seeshop.ShopFragments.InfoFragments.InfoShelfGood;
import com.example.thinking.newsell.view.seeshop.ShopFragments.InfoFragments.InfoShopGoods;
import com.example.thinking.newsell.view.seeshop.ShopInfo.ImagesActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * *****************************************
 * Created by thinking on 2017/7/24.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class ShopInfoFragment extends Fragment {


    @BindView(R.id.infopage_recyclerview)
    RecyclerView infopageRecyclerview;
    Unbinder unbinder;

    private String[] imageurl;
    private ArrayList<String> imagelist;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View shopInfoView = inflater.inflate(R.layout.shop_infopage, container, false);
        unbinder = ButterKnife.bind(this, shopInfoView);
        Bundle bundle = getArguments();
        String url = bundle.getString(Commen.SHOWPICS);
        if (!url.equals("")) {
            imageurl = url.split(";");
            imagelist = new ArrayList<>();
            Collections.addAll(imagelist, imageurl);
            infopageRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
            infopageRecyclerview.setAdapter(new ImageAdapter(getContext(), imagelist));
        }


        return shopInfoView;
    }


    /**
     * ImageAdapter
     */
    public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        public Context mContext;
        public LayoutInflater mLayoutInflater;
        private ArrayList<String> imagelist;
        public static final int ITEM_TYPE_HEADER = 0;
        public static final int ITEM_TYPE_CONTENT = 1;
        private int mHeaderCount = 1;//头部View个数

        public ImageAdapter(Context mContext, ArrayList<String> imagelist) {
            this.mContext = mContext;
            this.mLayoutInflater = LayoutInflater.from(mContext);
            this.imagelist = imagelist;
        }

        //判断当前item是否是HeadView
        public boolean isHeaderView(int position) {
            return mHeaderCount != 0 && position < mHeaderCount;
        }

        //判断当前item类型
        @Override
        public int getItemViewType(int position) {
            int dataItemCount = getItemCount();
            if (mHeaderCount != 0 && position < mHeaderCount) {//头部View
                return ITEM_TYPE_HEADER;
            } else {//内容View
                return ITEM_TYPE_CONTENT;
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == ITEM_TYPE_HEADER) {
                return new HeaderViewHolder(mLayoutInflater.inflate(R.layout.shop_infopage_head, parent, false));
            } else if (viewType == ITEM_TYPE_CONTENT) {
                return new ImageHolder(mLayoutInflater.inflate(R.layout.good_listview_item, parent, false));
            }
            return null;
        }


        public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof HeaderViewHolder) {
                final User user = (User) SpUtils.getObject(mContext, Commen.USERINFO);
                ((HeaderViewHolder) holder).attentionNum.setText("1");
                ((HeaderViewHolder) holder).saleNum.setText("2");
                ((HeaderViewHolder) holder).partnersNum.setText("3");



                ((HeaderViewHolder) holder).attention.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (((HeaderViewHolder) holder).attention.getText().toString().equals("关注")) {
                            ((HeaderViewHolder) holder).attention.setText("已关注");
                            ((HeaderViewHolder) holder).attention.setBackground(getResources().getDrawable(R.drawable.shape_attentioned));
                            ((HeaderViewHolder) holder).attention.setTextColor(getResources().getColor(R.color.colorPrimary));
                        }
                    }
                });
            } else if (holder instanceof ImageHolder) {
                //先使用 Glide 把图片的原图请求加载过来，然后再按原图来显示图片
                Glide.with(mContext)
                        .load(imagelist.get(position - mHeaderCount))
                        .asBitmap()
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                ((ImageHolder) holder).itemImage.setImageBitmap(resource);
                            }
                        });
                ((ImageHolder) holder).itemImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putStringArrayList(Commen.SHOWPICS, imagelist);
                        Intent intent = new Intent(mContext, ImagesActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
            }

        }

        @Override
        public int getItemCount() {
            return imagelist.size() + mHeaderCount;
        }

        public class ImageHolder extends RecyclerView.ViewHolder {
            private ImageView itemImage;

            public ImageHolder(View itemView) {
                super(itemView);
                itemImage = (ImageView) itemView.findViewById(R.id.item_image);
            }
        }

        //头部 ViewHolder
        public class HeaderViewHolder extends RecyclerView.ViewHolder {
            private TextView attention;
            private TextView attentionNum;
            private TextView sale;
            private TextView saleNum;
            private TextView partners;
            private TextView partnersNum;
          /*  private Button viewShelvesAbove;
            private Button viewShelvesBelow;
            private Button viewPartnerGoods;*/

            public HeaderViewHolder(View itemView) {
                super(itemView);
                attention = (TextView) itemView.findViewById(R.id.attention);
                attentionNum = (TextView) itemView.findViewById(R.id.attention_num);
                sale = (TextView) itemView.findViewById(R.id.sale);
                saleNum = (TextView) itemView.findViewById(R.id.sale_num);
                partners = (TextView) itemView.findViewById(R.id.partners);
                partnersNum = (TextView) itemView.findViewById(R.id.partners_num);
               /* viewShelvesAbove = (Button) itemView.findViewById(R.id.view_shelves_above);
                viewShelvesBelow = (Button) itemView.findViewById(R.id.view_shelves_below);
                viewPartnerGoods = (Button) itemView.findViewById(R.id.view_partner_goods);*/
            }

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
