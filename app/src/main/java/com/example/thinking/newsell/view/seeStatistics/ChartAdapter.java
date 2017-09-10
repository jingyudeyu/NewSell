package com.example.thinking.newsell.view.seeStatistics;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.thinking.newsell.R;
import com.example.thinking.newsell.bean.Commodity;
import com.example.thinking.newsell.bean.OrderGood;
import com.example.thinking.newsell.view.views.HorizontalProgressBar;

import java.text.DecimalFormat;
import java.util.List;

/**
 * *****************************************
 * Created by thinking on 2017/8/30.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class ChartAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<Commodity> commodityList;
    private Double aDouble;

    public ChartAdapter(Context context, List<Commodity> commodities, Double double1) {
        this.mContext = context;
        this.commodityList = commodities;
        this.aDouble=double1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.sales_item, parent, false);
        ChartHolder chartHolder = new ChartHolder(view);
        return chartHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ChartHolder chartHolder = (ChartHolder) holder;
        Glide.with(mContext).load(commodityList.get(position).getLogo()).centerCrop().into(chartHolder.salesGoodimage);
        chartHolder.salesGoodname.setText(commodityList.get(position).getProductname());
     //   chartHolder.progressBar.setProgress(new Double(commodityList.get(position).getPriceTotal()/aDouble*100).intValue());
        DecimalFormat decimalFormat=new DecimalFormat("#.##");
        chartHolder.salesMoney.setText("销售额："+decimalFormat.format(commodityList.get(position).getPriceTotal()));

        chartHolder.prcgressbarH.setProgress(new Double(commodityList.get(position).getPriceTotal()/aDouble*100).intValue());
     //   chartHolder.salesWeight.setText(decimalFormat.format(commodityList.get(position).getPriceTotal()/aDouble*100)+"%");
    }

    @Override
    public int getItemCount() {
        return commodityList.size();
    }

    public class ChartHolder extends RecyclerView.ViewHolder {
        ImageView salesGoodimage;
      //  ProgressBar progressBar;
        TextView salesGoodname;
      //  TextView salesWeight;
        HorizontalProgressBar prcgressbarH;
        TextView salesMoney;

        public ChartHolder(View itemView) {
            super(itemView);
            salesGoodimage = (ImageView) itemView.findViewById(R.id.sales_goodimage);
         //   progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
            salesGoodname = (TextView) itemView.findViewById(R.id.sales_goodname);
          //  salesWeight = (TextView) itemView.findViewById(R.id.sales_weight);
            prcgressbarH=(HorizontalProgressBar)itemView.findViewById(R.id.prcgressbar_h);
            salesMoney=(TextView)itemView.findViewById(R.id.sales_money);
        }
    }
}
