package com.example.thinking.newsell.view.HomeFragments;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.Commodity;
import com.example.thinking.newsell.bean.Partner;
import com.example.thinking.newsell.bean.Shop;
import com.example.thinking.newsell.commen.Commen;
import com.example.thinking.newsell.utils.system.SpUtils;
import com.example.thinking.newsell.view.seeshop.GoodInfo.GoodActivity;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * *****************************************
 * Created by thinking on 2017/8/23.
 * 创建时间：
 * <p>
 * 描述：关于统计的adapter
 * <p/>
 * <p/>
 * *******************************************
 */

public class StatisticsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPECARD = 1;
    private static final int TYPEDATA = 2;
    private static final int TYPEORDER = 3;
    private static final int TYPE4 = 4;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    int count = 0;
    private List<Partner> partnerList;//合作商品列表

    public StatisticsAdapter(Context context, List<Partner> partnerList) {
        this.mContext = context;
        this.partnerList = partnerList;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPECARD;
        if (position == 1)
            return TYPEDATA;
        if (position == 2)
            return TYPEORDER;
        else return TYPE4;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPECARD:
                CardHolder cardHolder = new CardHolder(mLayoutInflater.inflate(R.layout.statistics_shop_card, parent, false));
                return cardHolder;
            case TYPEDATA:
                DataHolder dataHolder = new DataHolder(mLayoutInflater.inflate(R.layout.statistics_shop_data, parent, false));
                return dataHolder;
            case TYPEORDER:
                OrderHolder orderHolder = new OrderHolder(mLayoutInflater.inflate(R.layout.statistics_shop_order, parent, false));
                return orderHolder;

            case TYPE4:
                HotHolder holder = new HotHolder(mLayoutInflater.inflate(R.layout.partner_good, parent, false));
                return holder;
            default:
                break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        int sid = SpUtils.getInt(mContext, Commen.SHOPSIDdefault);
        switch (getItemViewType(position)) {
            case TYPECARD:
                final CardHolder cardHolder = (CardHolder) holder;
                /* 获取店铺信息(名称、logo)*/
                NetWorks.getShopInfoById(sid, new BaseObserver<Shop>() {
                    @Override
                    public void onHandleSuccess(Shop shop) {
                        cardHolder.tvShopName.setText(shop.getShopname());
                        Glide.with(mContext).load(shop.getLogo()).into(cardHolder.imageShopLogo);
                    }

                    @Override
                    public void onHandleError(int code, String message) {

                    }
                });

                break;
            case TYPEDATA:
                DataHolder dataHolder = (DataHolder) holder;

                int status = 4;
                dataHolder.tvOrderToday.setText(String.valueOf(getCount(sid, 0)+getCount(sid,1)+getCount(sid,2)+getCount(sid,3)));
                dataHolder.tvSalesToday.setText("1111");
                dataHolder.tvTrafficToday.setText(String.valueOf(getTraffic(sid)));
                dataHolder.tvTodayPartners.setText("1");

                break;
            case TYPEORDER:
                OrderHolder orderHolder = (OrderHolder) holder;
                //查看订单的点击事件
                orderHolder.llViewOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, position + "", Toast.LENGTH_SHORT).show();
                    }
                });
                orderHolder.tvStayMoney.setText("待付款" + getAllOrderCount(sid, 0));
                orderHolder.tvStaySendgood.setText("待发货" + getAllOrderCount(sid, 1));
                orderHolder.tvCompleted.setText("已完成" + getAllOrderCount(sid, 3));

                break;
            case TYPE4:
                HotHolder hotHolder = (HotHolder) holder;
                hotHolder.partnerNum.setText(String.valueOf(partnerList.get(position).getCount()));
                hotHolder.partnerGoodname.setText(partnerList.get(position).getGoodsname());
                Glide.with(mContext).load(partnerList.get(position).getGoodslogo()).centerCrop().into(hotHolder.partnerGoodimage);
                hotHolder.partnerGoodprice.setText(partnerList.get(position).getPrice());
                hotHolder.textSales.setText(String.valueOf(partnerList.get(position).getSaleCount()));
                hotHolder.partnerShopname.setText(partnerList.get(position).getShopname());
                hotHolder.partnerCity.setText(partnerList.get(position).getCity());
                hotHolder.intentionNum.setText(String.valueOf(partnerList.get(position).getIntentcount()));
                hotHolder.itemView.setOnClickListener(new View.OnClickListener() {
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
                                Toast.makeText(mContext, code + message, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                break;
            default:
                break;
        }

    }

    private int getTraffic(int sid) {
        count = 0;
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = sDateFormat.format(new java.util.Date());
        Log.v("获取当前时间：", date);
        NetWorks.getSidDateCount(sid, date, new BaseObserver<Integer>() {
            @Override
            public void onHandleSuccess(Integer integer) {
                count = integer;
            }

            @Override
            public void onHandleError(int code, String message) {

            }
        });

        return count;
    }

    private int getCount(int sid, int status) {
        count = 0;
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = sDateFormat.format(new java.util.Date());
        Log.v("获取当前时间：", date);
        NetWorks.getBySSDOrderCount(sid, status, date, new BaseObserver<Integer>() {
            @Override
            public void onHandleSuccess(Integer integer) {
                count = integer;
            }

            @Override
            public void onHandleError(int code, String message) {
                Toast.makeText(mContext, code + message, Toast.LENGTH_SHORT).show();
            }
        });
        return count;
    }

    private int getAllOrderCount(int sid, int status) {
        count = 0;
        NetWorks.getBySSOrderCount(sid, status, new BaseObserver<Integer>() {
            @Override
            public void onHandleSuccess(Integer integer) {
                count = integer;
            }

            @Override
            public void onHandleError(int code, String message) {
                Toast.makeText(mContext, code + message, Toast.LENGTH_SHORT).show();
            }
        });
        return count;
    }

    @Override
    public int getItemCount() {
        return partnerList.size();
    }

    /*店铺信息的介绍 logo、名字*/
    public class CardHolder extends RecyclerView.ViewHolder {
        ImageView imageShopLogo;
        TextView tvShopName;

        public CardHolder(View itemView) {
            super(itemView);
            imageShopLogo = (ImageView) itemView.findViewById(R.id.image_shop_logo);
            tvShopName = (TextView) itemView.findViewById(R.id.tv_shop_name);
        }
    }

    /*今天的统计数据概览、包括订单数、销售额、访问数、合作商家数*/
    public class DataHolder extends RecyclerView.ViewHolder {
        TextView tvOrderToday;
        TextView tvSalesToday;
        TextView tvTrafficToday;
        TextView tvTodayPartners;

        public DataHolder(View itemView) {
            super(itemView);
            tvOrderToday = (TextView) itemView.findViewById(R.id.tv_order_today);
            tvSalesToday = (TextView) itemView.findViewById(R.id.tv_sales_today);
            tvTrafficToday = (TextView) itemView.findViewById(R.id.tv_traffic_today);
            tvTodayPartners = (TextView) itemView.findViewById(R.id.tv_today_partners);
        }
    }

    /*关于订单的部分 待付款、待发货、已完成*/
    public class OrderHolder extends RecyclerView.ViewHolder {

        LinearLayout llViewOrder;
        TextView tvStayMoney;
        TextView tvStaySendgood;
        TextView tvCompleted;

        public OrderHolder(View itemView) {
            super(itemView);
            llViewOrder = (LinearLayout) itemView.findViewById(R.id.ll_view_order);
            tvStayMoney = (TextView) itemView.findViewById(R.id.tv_stay_money);
            tvStaySendgood = (TextView) itemView.findViewById(R.id.tv_stay_sendgood);
            tvCompleted = (TextView) itemView.findViewById(R.id.tv_completed);
        }
    }


    /*首页展示合作商品的holder*/
    public class HotHolder extends RecyclerView.ViewHolder {
        private TextView partnerNum;
        private TextView partnerGoodname;
        private ImageView partnerGoodimage;
        private TextView partnerGoodprice;
        private TextView textSales;
        private TextView partnerShopname;
        private TextView partnerCity;
        private TextView intentionNum;

        public HotHolder(View itemView) {
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

