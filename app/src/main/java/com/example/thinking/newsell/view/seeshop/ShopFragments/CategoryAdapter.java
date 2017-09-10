package com.example.thinking.newsell.view.seeshop.ShopFragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.Category;
import com.example.thinking.newsell.bean.Commodity;
import com.example.thinking.newsell.bean.User;
import com.example.thinking.newsell.commen.Commen;
import com.example.thinking.newsell.utils.system.SpUtils;
import com.example.thinking.newsell.view.seeshop.CategoryGoods.CategoryGoodsActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.category;
import static com.baidu.location.d.a.i;

/**
 * *****************************************
 * Created by thinking on 2017/8/21.
 * 创建时间：
 * <p>
 * 描述：店铺页面里的分类页面的adapter
 * <p/>
 * <p/>
 * *******************************************
 */

public class CategoryAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Category> categories;
    private static final int TYPEHEAD = 1;//二级分类
    private static final int TYPEBODY = 2;//三级分类

    public CategoryAdapter(Context mContext, List<Category> categories) {
        this.mContext = mContext;
        this.categories = categories;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemViewType(int position) {
        if (categories.get(position).getIshead() == 1) {
            return TYPEHEAD;
        } else
            return TYPEBODY;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPEHEAD:
                HeadHolder headHolder = new HeadHolder(mLayoutInflater.inflate(R.layout.newfragment_item_head, parent, false));
                return headHolder;
            case TYPEBODY:
                SecondHolder secondHolder = new SecondHolder(mLayoutInflater.inflate(R.layout.newfragment_item, parent, false));
                return secondHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof HeadHolder) {
            final HeadHolder headHolder=(HeadHolder)holder;
            ((HeadHolder) holder).tvHead.setText(categories.get(position).getSmall());
            //二级分类查看商品
            ((HeadHolder) holder).liHead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  //  headHolder.tvHead.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
                    final List<Commodity> commodityList = new ArrayList<Commodity>();
                    final List<Integer> integerList = new ArrayList<Integer>();
                    for (int i = position+1; i < categories.size(); i++) {

                        if (categories.get(i).getIshead()== 1) {
                            break;
                        } else  {
                            integerList.add(categories.get(i).getCgid());

                        }
                    }

                    for ( int i=0;i<integerList.size();i++) {
                         /* 根据店铺sid和cgid查找商品list*/
                        final int finalI = i;
                        NetWorks.getSidCgidgoods(SpUtils.getInt(mContext, Commen.SHOPSIDdefault), integerList.get(i), new BaseObserver<List<Commodity>>() {
                            @Override
                            public void onHandleSuccess(List<Commodity> commodities) {

                                    commodityList.addAll(commodities);
                                    if (finalI ==integerList.size()-1){
                                        Intent intent = new Intent(mContext, CategoryGoodsActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putSerializable(Commen.CATEGORYGOODLIST, (Serializable) commodityList);
                                        bundle.putString(Commen.CATEGORYNAME, categories.get(position).getSmall());
                                        intent.putExtras(bundle);
                                        mContext.startActivity(intent);
                                    }
                            }
                            @Override
                            public void onHandleError(int code, String message) {
                                Toast.makeText(mContext, code + message, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                }
            });

        } else if (holder instanceof SecondHolder) {
            ((SecondHolder) holder).tvCategory.setText(categories.get(position).getSecend());
            //小分类查看商品
            ((SecondHolder) holder).tvCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*根据店铺sid和cgid查找商品list*/
                    NetWorks.getSidCgidgoods(SpUtils.getInt(mContext, Commen.SHOPSIDdefault), categories.get(position).getCgid(), new BaseObserver<List<Commodity>>() {
                        @Override
                        public void onHandleSuccess(List<Commodity> commodities) {
                            Intent intent = new Intent(mContext, CategoryGoodsActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(Commen.CATEGORYGOODLIST, (Serializable) commodities);
                            bundle.putString(Commen.CATEGORYNAME, categories.get(position).getSecend());
                            intent.putExtras(bundle);
                            mContext.startActivity(intent);
                        }

                        @Override
                        public void onHandleError(int code, String message) {
                            Toast.makeText(mContext, code + message, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = ((GridLayoutManager) layoutManager);
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    //typehead的时候gridLayoutManager为1，typesecond的时候gridLayoutManager为2
                    return getItemViewType(position) == TYPEHEAD ? gridLayoutManager.getSpanCount() : 1;
                }
            });
        }
    }


    public List<Commodity> getCommditys(int sid,int cgid){
        /*根据店铺sid和cgid查找商品list*/
        final List<Commodity> commodities2 = new ArrayList<Commodity>();
        NetWorks.getSidCgidgoods(sid, cgid, new BaseObserver<List<Commodity>>() {
            @Override
            public void onHandleSuccess(List<Commodity> commodities) {
                commodities2.addAll(commodities);
            }
            @Override
            public void onHandleError(int code, String message) {
                Toast.makeText(mContext, code + message, Toast.LENGTH_SHORT).show();
            }
        });
        return commodities2;
    }

    //二级分类的布局
    public class HeadHolder extends RecyclerView.ViewHolder {
        private TextView tvHead;
        private RelativeLayout liHead;

        public HeadHolder(View itemView) {
            super(itemView);
            tvHead = (TextView) itemView.findViewById(R.id.tv_head);
            liHead=(RelativeLayout)itemView.findViewById(R.id.li_head);
        }
    }

    //小分类的布局
    public class SecondHolder extends RecyclerView.ViewHolder {
        private TextView tvCategory;

        public SecondHolder(View itemView) {
            super(itemView);
            tvCategory = (TextView) itemView.findViewById(R.id.tv_category);
        }
    }
}
