package com.example.thinking.newsell.view.seeStatistics;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.Commodity;
import com.example.thinking.newsell.bean.OrderGood;
import com.example.thinking.newsell.view.seeshop.GoodInfo.GoodActivity;

import java.util.List;

/**
 * *****************************************
 * Created by thinking on 2017/8/25.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class SalesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<Commodity> commodityList;

    public SalesAdapter(Context context, List<Commodity> commodities) {
        this.mContext = context;
        this.commodityList = commodities;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.ordergood_item, parent, false);
        SalesHolder salesHolder = new SalesHolder(view);
        return salesHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SalesHolder salesHolder = (SalesHolder) holder;
        final Commodity commodity = commodityList.get(position);
        Glide.with(mContext).load(commodity.getLogo()).centerCrop().into(salesHolder.goodimage);
        salesHolder.goodname.setText(commodity.getProductname());
        salesHolder.goodprice.setText(String.valueOf(commodity.getPrice()));
        salesHolder.goodcount.setText("x"+commodity.getCount());
        salesHolder.tvPlus.setText("合计："+commodity.getPriceTotal());
        salesHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                        Toast.makeText(mContext, code + message, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return commodityList.size();
    }

    public class SalesHolder extends RecyclerView.ViewHolder {
        private ImageView goodimage;
        private TextView goodname;
        private TextView goodprice;
        private TextView goodcount;
        private RelativeLayout rePlus;
        private TextView tvPlus;

        public SalesHolder(View itemView) {
            super(itemView);
            goodimage = (ImageView) itemView.findViewById(R.id.goodimage);
            goodname = (TextView) itemView.findViewById(R.id.goodname);
            goodprice = (TextView) itemView.findViewById(R.id.goodprice);
            goodcount = (TextView) itemView.findViewById(R.id.goodcount);
            rePlus = (RelativeLayout) itemView.findViewById(R.id.re_plus);
            rePlus.setVisibility(View.VISIBLE);
            tvPlus = (TextView) itemView.findViewById(R.id.tv_plus);
        }
    }
}
