package com.example.thinking.newsell.view.seeStatistics;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.thinking.newsell.R;
import com.example.thinking.newsell.bean.OrderGood;

import java.util.List;

import butterknife.BindView;

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

public class DetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<OrderGood> orderGoodlist;

    public DetailsAdapter(Context context, List<OrderGood> orderGoods) {
        this.mContext = context;
        this.orderGoodlist = orderGoods;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.ordergood_item, parent, false);
        DetailHolder detailHolder = new DetailHolder(view);
        return detailHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DetailHolder detailHolder = (DetailHolder) holder;
        OrderGood orderGood=orderGoodlist.get(position);
        Glide.with(mContext).load(orderGood.getLogo()).into(detailHolder.goodimage);
        detailHolder.goodname.setText(orderGood.getGoodsname());
        detailHolder.goodprice.setText("价格："+orderGood.getTotalprice());
        detailHolder.goodcount.setText("x"+orderGood.getCount());

    }

    @Override
    public int getItemCount() {
        return orderGoodlist.size();
    }

    public class DetailHolder extends RecyclerView.ViewHolder {

        private ImageView goodimage;
        private TextView goodname;
        private TextView goodprice;
        private TextView goodcount;

        public DetailHolder(View itemView) {
            super(itemView);
            goodimage = (ImageView) itemView.findViewById(R.id.goodimage);
            goodname = (TextView) itemView.findViewById(R.id.goodname);
            goodprice = (TextView) itemView.findViewById(R.id.goodprice);
            goodcount = (TextView) itemView.findViewById(R.id.goodcount);
        }
    }
}
