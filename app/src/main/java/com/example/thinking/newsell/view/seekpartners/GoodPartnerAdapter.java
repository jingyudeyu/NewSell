package com.example.thinking.newsell.view.seekpartners;

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
 * Created by thinking on 2017/7/28.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class GoodPartnerAdapter extends RecyclerView.Adapter<GoodPartnerAdapter.GoodPartnerHolder> {
    private List<Partner> list;
    private Context context;
    public static final int TYPE_OTHER = 1;
    public static final int TYPE_BOTTOM = 2;

public GoodPartnerAdapter(List<Partner> list,Context context){
    this.list=list;
    this.context=context;

}

/*    @Override
    public int getItemViewType(int position) {

            if (!list.isEmpty() && list.size() < position ) {
                return TYPE_OTHER;
            } else {
                return TYPE_BOTTOM;
            }
    }*/

    @Override
    public GoodPartnerHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        GoodPartnerHolder holder = new GoodPartnerHolder(LayoutInflater.from(context).inflate(R.layout.partner_good, parent, false));
        return holder;
    }


    public void onBindViewHolder(GoodPartnerHolder holder, final int position) {
        holder.partnerNum.setText(String.valueOf(list.get(position).getCount()));
        holder.partnerGoodname.setText(list.get(position).getGoodsname());
        Glide.with(context).load(list.get(position).getGoodslogo()).centerCrop().into(holder.partnerGoodimage);
        holder.partnerGoodprice.setText(list.get(position).getPrice());
        holder.textSales.setText(String.valueOf(list.get(position).getSaleCount()));
       // holder.partnerShopname.setText(list.get(position).getShopname());
        holder.partnerShopname.setText(list.get(position).getReason());
        holder.partnerCity.setText(list.get(position).getCity());
        holder.intentionNum.setText(String.valueOf(list.get(position).getIntentcount()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetWorks.getIDgood(list.get(position).getCid(), new BaseObserver<Commodity>() {
                    @Override
                    public void onHandleSuccess(final Commodity commodity) {
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("commodity", commodity);
                                Intent intent = new Intent(context, GoodActivity.class);
                                intent.putExtras(bundle);
                                context.startActivity(intent);
                    }
                    @Override
                    public void onHandleError(int code, String message) {

                    }
                });

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class GoodPartnerHolder extends RecyclerView.ViewHolder {

        private TextView partnerNum;
        private TextView partnerGoodname;
        private ImageView partnerGoodimage;
        private TextView partnerGoodprice;
        private TextView textSales;
        private TextView partnerShopname;
        private TextView partnerCity;
        private TextView intentionNum;
        public GoodPartnerHolder(View itemView) {
            super(itemView);
            partnerNum = (TextView) itemView.findViewById(R.id.partner_num);
            partnerGoodname = (TextView) itemView.findViewById(R.id.partner_goodname);
            partnerGoodimage = (ImageView) itemView.findViewById(R.id.partner_goodimage);
            partnerGoodprice = (TextView) itemView.findViewById(R.id.partner_goodprice);
            textSales = (TextView) itemView.findViewById(R.id.text_sales);
            partnerShopname = (TextView) itemView.findViewById(R.id.partner_shopname);
            partnerCity = (TextView) itemView.findViewById(R.id.partner_city);
            intentionNum=(TextView)itemView.findViewById(R.id.intention_num);
        }
    }
}
