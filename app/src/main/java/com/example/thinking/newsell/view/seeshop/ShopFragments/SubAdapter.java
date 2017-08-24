package com.example.thinking.newsell.view.seeshop.ShopFragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.Category;
import com.example.thinking.newsell.bean.Commodity;
import com.example.thinking.newsell.bean.User;
import com.example.thinking.newsell.commen.Commen;
import com.example.thinking.newsell.utils.system.SpUtils;
import com.example.thinking.newsell.view.seeshop.CategoryGoods.CategoryGoodsActivity;
import com.example.thinking.newsell.view.seeshop.GoodInfo.GoodActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static okhttp3.Protocol.get;

/**
 * *****************************************
 * Created by thinking on 2017/7/19.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class SubAdapter extends DelegateAdapter.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<Map<String, Object>> listItem;
    private LayoutInflater inflater;
    private Context mContext;
    private LayoutHelper mLayoutHelper;
    private VirtualLayoutManager.LayoutParams mLayoutParams;
    private int mCount = 0;
    private int type;
private static SubItemClickListener subItemClickListener;



    public SubAdapter(Context context, LayoutHelper layoutHelper, int count, ArrayList<Map<String, Object>> listItem, int type) {
        this(context, layoutHelper, count, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300), listItem, type);
    }

    public SubAdapter(Context context, LayoutHelper layoutHelper, int count, @NonNull VirtualLayoutManager.LayoutParams layoutParams, ArrayList<Map<String, Object>> listItem, int type) {
        this.mContext = context;
        this.mLayoutHelper = layoutHelper;
        this.mCount = count;
        this.mLayoutParams = layoutParams;
        inflater = LayoutInflater.from(context);
        this.listItem = listItem;
        this.type = type;
    }


    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    public int getItemViewType(int position) {
        return type;
    }

 // 设置Item的点击事件
    // 绑定MainActivity传进来的点击监听器
    public void setOnItemClickListener(SubItemClickListener listener) {
        subItemClickListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
         return new MyHolder(LayoutInflater.from(mContext).inflate(R.layout.item, parent, false));
        } else if (viewType == 1) {
           return new CategoryHolder(LayoutInflater.from(mContext).inflate(R.layout.horscrollview_item, parent, false));
        } else if (viewType == 2) {
            return new ImageHolder(LayoutInflater.from(mContext).inflate(R.layout.vlayout_linear_image, parent, false));
        } else {
            return new CardHolder(LayoutInflater.from(mContext).inflate(R.layout.homepage_reit_content, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyHolder) {
            Commodity commodity = (Commodity) listItem.get(position).get("selling");
            ((MyHolder) holder).selling_name.setText(commodity.getProductname());
            ((MyHolder) holder).selling_money.setText(String.valueOf(commodity.getPrice()));
            Glide.with(mContext).load(commodity.getLogo()).centerCrop().diskCacheStrategy(DiskCacheStrategy.RESULT).into(((MyHolder) holder).selling_image);
            ((MyHolder)holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Commodity commodity = (Commodity) listItem.get(position).get("selling");
                    NetWorks.getIDgood(commodity.getCid(), new BaseObserver<Commodity>() {
                        @Override
                        public void onHandleSuccess(Commodity commodity) {
                            Bundle bundle=new Bundle();
                            bundle.putSerializable("commodity",commodity);
                            Intent intent=new Intent(mContext,GoodActivity.class);
                            intent.putExtras(bundle);
                            mContext.startActivity(intent);
                        }

                        @Override
                        public void onHandleError(int code, String message) {

                        }
                    });

                }
            });

        } else if (holder instanceof CategoryHolder) {
            final Category category = (Category) listItem.get(position).get("category");
            ((CategoryHolder) holder).coupon_textview.setText(category.getSecend());
            Glide.with(mContext).load(category.getLogo()).centerCrop().diskCacheStrategy(DiskCacheStrategy.RESULT).into(((CategoryHolder) holder).coupon_imageview);

            ((CategoryHolder)holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 //   final User user = (User) SpUtils.getObject(mContext, Commen.USERINFO);
                    NetWorks.getSidCgidgoods(SpUtils.getInt(mContext,Commen.SHOPSIDdefault), category.getCgid(), new BaseObserver<List<Commodity>>() {
                        @Override
                        public void onHandleSuccess(List<Commodity> commodities) {

                            Intent intent = new Intent(mContext, CategoryGoodsActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("LISTGOODS", (Serializable) commodities);
                            bundle.putString("SORTNAME",category.getSecend());
                            intent.putExtras(bundle);
                            mContext.startActivity(intent);
                        }
                        @Override
                        public void onHandleError(int code, String message) {
                            Toast.makeText(mContext, ((Commodity) listItem.get(0).get("categorysell")).getProductname(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            });
        } else if (holder instanceof ImageHolder) {
            Glide.with(mContext).load(listItem.get(position).get("shopimage")).diskCacheStrategy(DiskCacheStrategy.RESULT).into(((ImageHolder) holder).linear_imageview);
        } else if (holder instanceof CardHolder) {

            if (position == 0) {
                final Commodity commodity = (Commodity) listItem.get(0).get("categorysell");
                ((CardHolder) holder).category_name.setText(commodity.getProductname());
              //  Glide.with(mContext).load(commodity.getLogo()).into(((CardHolder) holder).good_imageview);
                ((CardHolder)holder).itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NetWorks.getSidCgidgoods(commodity.getSid(), commodity.getCgid(), new BaseObserver<List<Commodity>>() {
                            @Override
                            public void onHandleSuccess(List<Commodity> commodities) {

                                Intent intent = new Intent(mContext, CategoryGoodsActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("LISTGOODS", (Serializable) commodities);
                                bundle.putString("SORTNAME",commodity.getProductname());
                                intent.putExtras(bundle);
                                mContext.startActivity(intent);

                            }
                            @Override
                            public void onHandleError(int code, String message) {
                                Toast.makeText(mContext, ((Commodity) listItem.get(0).get("categorysell")).getProductname(), Toast.LENGTH_SHORT).show();
                            }
                        });
                       // Toast.makeText(mContext, ((Commodity) listItem.get(0).get("categorysell")).getProductname(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                final Commodity commodity1 = (Commodity) listItem.get(position).get("categorysell");
                ((CardHolder) holder).good_textview.setText(commodity1.getProductname());
                ((CardHolder) holder).t1.setText("￥");
                ((CardHolder) holder).money_textview.setText(String.valueOf(commodity1.getPrice()));
                Glide.with(mContext).load(commodity1.getLogo()).into(((CardHolder) holder).good_imageview);
                ((CardHolder)holder).itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        NetWorks.getIDgood(commodity1.getCid(), new BaseObserver<Commodity>() {
                            @Override
                            public void onHandleSuccess(Commodity commodity) {
                                Bundle bundle=new Bundle();
                                bundle.putSerializable("commodity",commodity);
                                Intent intent=new Intent(mContext,GoodActivity.class);
                                intent.putExtras(bundle);
                                mContext.startActivity(intent);
                            }

                            @Override
                            public void onHandleError(int code, String message) {

                            }
                        });

                    }
                });
            }
        }
    }

    public int getItemCount() {
        return mCount;
    }

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

    static class CategoryHolder extends RecyclerView.ViewHolder {
        public TextView coupon_textview;
        public ImageView coupon_imageview;

        public CategoryHolder(View itemView) {
            super(itemView);
            coupon_textview = (TextView) itemView.findViewById(R.id.coupon_textview);
            coupon_imageview = (ImageView) itemView.findViewById(R.id.coupon_imageview);
        }
    }

    static class ImageHolder extends RecyclerView.ViewHolder {
        public ImageView linear_imageview;

        public ImageHolder(View itemView) {
            super(itemView);
            linear_imageview = (ImageView) itemView.findViewById(R.id.linear_imageview);
        }
    }

    static class CardHolder extends RecyclerView.ViewHolder {

        public ImageView good_imageview;
        public TextView good_textview;
        public TextView money_textview;
        public TextView category_name;
        public TextView t1;

        public CardHolder(View itemView) {
            super(itemView);
            good_imageview = (ImageView) itemView.findViewById(R.id.good_imageview);
            good_textview = (TextView) itemView.findViewById(R.id.good_textview);
            money_textview = (TextView) itemView.findViewById(R.id.money_textview);
            category_name = (TextView) itemView.findViewById(R.id.category_name);
            t1 = (TextView) itemView.findViewById(R.id.t1);
        }
    }
    public interface SubItemClickListener {
        public void onItemClick(View view, int postion);
    }

}
