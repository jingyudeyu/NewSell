package com.example.thinking.newsell.view.seeStatistics;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.Commodity;
import com.example.thinking.newsell.bean.Partner;
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

public class PartnerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<Partner> partnerList;

    public PartnerAdapter(Context context,List<Partner> partnerList) {
        this.mContext=context;
        this.partnerList=partnerList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PartnerHolder holder = new PartnerHolder(LayoutInflater.from(mContext).inflate(R.layout.partner_good, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        PartnerHolder partnerHolder=(PartnerHolder)holder;
        Partner partner=partnerList.get(position);
        partnerHolder.partnerNum.setText(String.valueOf(partner.getCount()));
        partnerHolder.partnerGoodname.setText(partner.getGoodsname());
        Glide.with(mContext).load(partner.getGoodslogo()).centerCrop().into(partnerHolder.partnerGoodimage);
        partnerHolder.partnerGoodprice.setText(partner.getPrice());
        partnerHolder.textSales.setText(String.valueOf(partner.getSaleCount()));
        partnerHolder.partnerShopname.setText(partner.getShopname());
        partnerHolder.partnerCity.setText(partner.getCity());
        partnerHolder.intentionNum.setText(String.valueOf(partner.getIntentcount()));
/*        partnerHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetWorks.getIDgood(partnerList.get(position).getCid(), new BaseObserver<Commodity>() {
                    @Override
                    public void onHandleSuccess(final Commodity commodity) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("commodity", commodity);
                        Intent intent = new Intent(mContext, GoodActivity.class);
                        intent.putExtras(bundle);
                        mContext.startActivity(intent);
                    }
                    @Override
                    public void onHandleError(int code, String message) {

                    }
                });
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return partnerList.size();
    }

    public class PartnerHolder extends RecyclerView.ViewHolder {

        private TextView partnerNum;
        private TextView partnerGoodname;
        private ImageView partnerGoodimage;
        private TextView partnerGoodprice;
        private TextView textSales;
        private TextView partnerShopname;
        private TextView partnerCity;
        private TextView intentionNum;

        public PartnerHolder(View itemView) {
            super(itemView);
            partnerNum = (TextView) itemView.findViewById(R.id.partner_num);
            partnerGoodname = (TextView) itemView.findViewById(R.id.partner_goodname);
            partnerGoodimage = (ImageView) itemView.findViewById(R.id.partner_goodimage);
            partnerGoodprice = (TextView) itemView.findViewById(R.id.partner_goodprice);
            textSales = (TextView) itemView.findViewById(R.id.text_sales);
            partnerShopname = (TextView) itemView.findViewById(R.id.partner_shopname);
            partnerCity = (TextView) itemView.findViewById(R.id.partner_city);
            intentionNum = (TextView) itemView.findViewById(R.id.intention_num);
        }

    }
}
