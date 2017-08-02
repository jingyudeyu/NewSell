package com.example.thinking.newsell.view.seekpartners;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.thinking.newsell.R;
import com.example.thinking.newsell.bean.Partner;

import java.util.List;

import static android.R.id.list;

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
   // private List<List<String>> all;
    private Context context;
 //   private GridLayoutManager gridLayoutManager;

    private static final int VIEWTYPE1 = 1;
    private static final int VIEWTYPE2 = 2;

  /*  public GoodPartnerAdapter(List<String> list, Context context, GridLayoutManager gridLayoutManager) {
        this.list = list;
        this.context = context;
        this.gridLayoutManager = gridLayoutManager;
    }
*/
/*  public GoodPartnerAdapter(List<List<String>> all, Context context) {
      this.all = all;
      this.context = context;
  }*/
public GoodPartnerAdapter(List<Partner> list,Context context){
    this.list=list;
    this.context=context;

}

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public GoodPartnerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      //  View view = LayoutInflater.from(context).inflate(R.layout.partner_good, parent, false);
      //  GoodPartnerHolder holder = new GoodPartnerHolder(view);
        GoodPartnerHolder holder = new GoodPartnerHolder(LayoutInflater.from(context).inflate(R.layout.partner_good, parent, false));
        return holder;
    }


    public void onBindViewHolder(GoodPartnerHolder holder, int position) {
        holder.partnerNum.setText(String.valueOf(list.get(position).getCount()));
        holder.partnerGoodname.setText(list.get(position).getGoodsname());
        Glide.with(context).load(list.get(position).getGoodslogo()).centerCrop().into(holder.partnerGoodimage);
        holder.partnerGoodprice.setText(list.get(position).getPrice());
        holder.textSales.setText(String.valueOf(list.get(position).getSaleCount()));
        holder.partnerShopname.setText(list.get(position).getShopname());
        holder.partnerCity.setText(list.get(position).getCity());

    }

    /*    @Override
    public int getItemViewType(int position) {


        int n = gridLayoutManager.getSpanCount();
        if (n == 1) {
            return VIEWTYPE1;
        } else return VIEWTYPE2;
    }*/

  /*  @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEWTYPE1) {
            View view = LayoutInflater.from(context).inflate(R.layout.partner_good, parent, false);
            GoodPartnerHolder holder = new GoodPartnerHolder(view);
            return holder;
        }else {
            View view = LayoutInflater.from(context).inflate(R.layout.good_item, parent, false);
            GoodPartnerHolder holder = new GoodPartnerHolder(view);
            return holder;
        }
    }*/

 /*   @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((GoodPartnerHolder) holder).partnerGoodname.setText(list.get(position));
    }*/

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

        public GoodPartnerHolder(View itemView) {
            super(itemView);
            partnerNum = (TextView) itemView.findViewById(R.id.partner_num);
            partnerGoodname = (TextView) itemView.findViewById(R.id.partner_goodname);
            partnerGoodimage = (ImageView) itemView.findViewById(R.id.partner_goodimage);
            partnerGoodprice = (TextView) itemView.findViewById(R.id.partner_goodprice);
            textSales = (TextView) itemView.findViewById(R.id.text_sales);
            partnerShopname = (TextView) itemView.findViewById(R.id.partner_shopname);
            partnerCity = (TextView) itemView.findViewById(R.id.partner_city);
        }
    }
}
