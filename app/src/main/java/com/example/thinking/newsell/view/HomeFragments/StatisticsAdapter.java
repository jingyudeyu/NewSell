package com.example.thinking.newsell.view.HomeFragments;

import android.content.Context;
import android.content.Intent;
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
import com.example.thinking.newsell.view.seeStatistics.OrderAllActivity;
import com.example.thinking.newsell.view.seeStatistics.PartnerActivity;
import com.example.thinking.newsell.view.seeStatistics.SalesActivity;
import com.example.thinking.newsell.view.seeStatistics.SalesChartActivity;
import com.example.thinking.newsell.view.seeshop.GoodInfo.GoodActivity;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.thinking.newsell.R.id.shop;

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
    private static final int TYPEDATA2 = 5;
    private static final int TYPESALES = 6;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    int count = 0;
    int allcount = 0;

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
            return TYPEDATA2;
        if (position == 3)
            return TYPEORDER;
        if (position == 4)
            return TYPESALES;
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
            case TYPEDATA2:
                Data2Holder data2Holder = new Data2Holder(mLayoutInflater.inflate(R.layout.statistics_shop_data2, parent, false));
                return data2Holder;
            case TYPESALES:
                SalesHolder salesHolder = new SalesHolder(mLayoutInflater.inflate(R.layout.statistics_shop_month, parent, false));
                return salesHolder;
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
                getCount(sid, 4, dataHolder.tvOrderToday);
                getSales(sid, dataHolder.tvSalesToday);
                getTraffic(sid, dataHolder.tvTrafficToday);
                getSalesCount(sid, dataHolder.tvTodayPartners);
                dataHolder.llOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putInt(Commen.DATEMARK, 0);// 0 今天
                        bundle.putInt(Commen.STATUSMARK, 7);// 7 全部
                        Intent intent = new Intent(mContext, OrderAllActivity.class);
                        intent.putExtras(bundle);
                        mContext.startActivity(intent);
                    }
                });
                dataHolder.tvTodayPartners.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                dataHolder.llSales.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putInt(Commen.SALESMARK, 0);
                        Intent intent = new Intent(mContext, SalesActivity.class);
                        intent.putExtras(bundle);
                        mContext.startActivity(intent);
                    }
                });
                dataHolder.tvTodayPartners.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putInt(Commen.SALESMARK, 0);
                        Intent intent = new Intent(mContext, SalesActivity.class);
                        intent.putExtras(bundle);
                        mContext.startActivity(intent);
                    }
                });
                break;

            case TYPEDATA2:
                final Data2Holder data2Holder = (Data2Holder) holder;
                NetWorks.getShopAttentionSize(sid, new BaseObserver<Integer>() {
                    @Override
                    public void onHandleSuccess(Integer integer) {
                        data2Holder.tvAttentionAll.setText(String.valueOf(integer));
                    }

                    @Override
                    public void onHandleError(int code, String message) {

                    }
                });
                data2Holder.llPartner.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, PartnerActivity.class);
                        mContext.startActivity(intent);
                    }
                });

                getShopSalesValume(sid, data2Holder.tvSalesvolume);
                getShopSalesTotal(sid, data2Holder.tvSalesAll);

                data2Holder.llSales.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putInt(Commen.SALESMARK, 7);
                        Intent intent = new Intent(mContext, SalesActivity.class);
                        intent.putExtras(bundle);
                        mContext.startActivity(intent);

                    }
                });
                data2Holder.llSalesvolume.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putInt(Commen.SALESMARK, 7);
                        Intent intent = new Intent(mContext, SalesActivity.class);
                        intent.putExtras(bundle);
                        mContext.startActivity(intent);
                    }
                });


                break;

            case TYPEORDER:
                OrderHolder orderHolder = (OrderHolder) holder;
                //查看订单的点击事件
                orderHolder.llViewOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, position + "", Toast.LENGTH_SHORT).show();
                        Bundle bundle = new Bundle();
                        bundle.putInt(Commen.DATEMARK, 7);//7全部
                        bundle.putInt(Commen.STATUSMARK, 7);//7全部
                        Intent intent = new Intent(mContext, OrderAllActivity.class);
                        intent.putExtras(bundle);
                        mContext.startActivity(intent);

                    }
                });
                getAllOrderCount(sid, 0, orderHolder.tvStayMoney);
                getAllOrderCount(sid, 1, orderHolder.tvStaySendgood);
                getAllOrderCount(sid, 3, orderHolder.tvCompleted);
                //待付款的点击事件
                orderHolder.tvStayMoney.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putInt(Commen.DATEMARK, 7);//7全部
                        bundle.putInt(Commen.STATUSMARK, 0);//0待付款
                        Intent intent = new Intent(mContext, OrderAllActivity.class);
                        intent.putExtras(bundle);
                        mContext.startActivity(intent);
                    }
                });
                //待发货的点击事件
                orderHolder.tvStaySendgood.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putInt(Commen.DATEMARK, 7);//7全部
                        bundle.putInt(Commen.STATUSMARK, 1);//1待发货
                        Intent intent = new Intent(mContext, OrderAllActivity.class);
                        intent.putExtras(bundle);
                        mContext.startActivity(intent);
                    }
                });
                //已完成的点击事件
                orderHolder.tvCompleted.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putInt(Commen.DATEMARK, 7);//7全部
                        bundle.putInt(Commen.STATUSMARK, 3);//3已完成
                        Intent intent = new Intent(mContext, OrderAllActivity.class);
                        intent.putExtras(bundle);
                        mContext.startActivity(intent);
                    }
                });
                break;

            case TYPESALES:
                final SalesHolder salesHolder = (SalesHolder) holder;
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM");
                Date date = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);//设置当前时间
                calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);//设置上月时间
                date = calendar.getTime();
                String date1 = simpleDateFormat.format(date);
                Log.v("获取的上月时间为：", date1);

                NetWorks.getSidDateSales(sid, date1, new BaseObserver<Double>() {
                    @Override
                    public void onHandleSuccess(Double aDouble) {
                        salesHolder.tvSalesMonth.setText(aDouble + "元");
                    }

                    @Override
                    public void onHandleError(int code, String message) {

                    }
                });
                salesHolder.llViewSales.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, SalesChartActivity.class);
                        mContext.startActivity(intent);
                    }
                });

                break;

            case TYPE4:
                HotHolder hotHolder = (HotHolder) holder;
                final Partner partner = partnerList.get(position - 5);
                hotHolder.partnerNum.setText(String.valueOf(partner.getCount()));
                hotHolder.partnerGoodname.setText(partner.getGoodsname());
                Glide.with(mContext).load(partner.getGoodslogo()).centerCrop().into(hotHolder.partnerGoodimage);
                hotHolder.partnerGoodprice.setText(partner.getPrice());
                hotHolder.textSales.setText(String.valueOf(partner.getSaleCount()));
                hotHolder.partnerShopname.setText(partner.getShopname());
                hotHolder.partnerCity.setText(partner.getCity());
                hotHolder.intentionNum.setText(String.valueOf(partner.getIntentcount()));
                hotHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NetWorks.getIDgood(partner.getCid(), new BaseObserver<Commodity>() {
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

    private String intNumber(int count) {
        String stringNumber = String.valueOf(count);
        if (count > 10000) {
            stringNumber = count / 10000 + "万";
        }
        return stringNumber;
    }

    private String doubleNumber(double count) {
        String stringNumber = String.valueOf(count);
        if (count >= 10000.00) {
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            stringNumber = decimalFormat.format(count / 10000) + "万";
        }
        return stringNumber;
    }

    //根据店铺id日期查看总销售量
    private void getSalesCount(int sid, final TextView tvTodayPartners) {

        SimpleDateFormat simpleDate = new SimpleDateFormat("yyy-MM-dd");
        String date = simpleDate.format(new java.util.Date());
        NetWorks.getSidDateSalesCount(sid, date, new BaseObserver<Integer>() {
            @Override
            public void onHandleSuccess(Integer integer) {
                tvTodayPartners.setText(intNumber(integer));

            }

            @Override
            public void onHandleError(int code, String message) {
                Toast.makeText(mContext, code + message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //根据店铺id日期查看总销售额
    private void getSales(int sid, final TextView tvSalesToday) {
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyy-MM-dd");
        String date = simpleDate.format(new java.util.Date());
        NetWorks.getSidDateSales(sid, date, new BaseObserver<Double>() {
            @Override
            public void onHandleSuccess(Double aDouble) {
                tvSalesToday.setText(doubleNumber(aDouble));
            }

            @Override
            public void onHandleError(int code, String message) {
                Toast.makeText(mContext, code + message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //获取今日店铺访问量
    private void getTraffic(int sid, final TextView tvTrafficToday) {

        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyy-MM-dd");
        String date = sDateFormat.format(new java.util.Date());
        Log.v("获取当前时间：", date);
        NetWorks.getSidDateCount(sid, date, new BaseObserver<Integer>() {
            @Override
            public void onHandleSuccess(Integer integer) {
                tvTrafficToday.setText(intNumber(integer));
            }

            @Override
            public void onHandleError(int code, String message) {
                Toast.makeText(mContext, code + message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //获取店铺今天订单数
    private void getCount(int sid, int status, final TextView tvOrderToday) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyy-MM-dd");
        String date = sDateFormat.format(new java.util.Date());
        allcount = 0;
        for (int i = 0; i < status; i++) {

            final int finalI = i;
            NetWorks.getBySSDOrderCount(sid, i, date, new BaseObserver<Integer>() {
                @Override
                public void onHandleSuccess(Integer integer) {
                    allcount = allcount + integer;
                    if (finalI == 3) {
                        tvOrderToday.setText(intNumber(allcount));
                    }
                }

                @Override
                public void onHandleError(int code, String message) {
                    Toast.makeText(mContext, code + message, Toast.LENGTH_SHORT).show();
                }
            });
        }


    }

    //获取各种状态订单数量
    private void getAllOrderCount(int sid, final int status, final TextView tvStatus) {

        NetWorks.getBySSOrderCount(sid, status, new BaseObserver<Integer>() {
            @Override
            public void onHandleSuccess(Integer integer) {
                switch (status) {
                    case 0:
                        tvStatus.setText("待付款" + integer);
                        break;
                    case 1:
                        tvStatus.setText("待发货" + integer);
                        break;
                    case 2:
                        tvStatus.setText("待付款" + integer);
                        break;
                    case 3:
                        tvStatus.setText("已完成" + integer);
                        break;
                }
            }

            @Override
            public void onHandleError(int code, String message) {
                Toast.makeText(mContext, code + message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //根据店铺id查看所有销售量
    private void getShopSalesValume(int sid, final TextView tvSalesvolume) {
        NetWorks.getSidSalesVolume(sid, new BaseObserver<Integer>() {
            @Override
            public void onHandleSuccess(Integer integer) {
                tvSalesvolume.setText(intNumber(integer));
            }

            @Override
            public void onHandleError(int code, String message) {
                Toast.makeText(mContext, code + message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //根据店铺id查看所有销售额
    private void getShopSalesTotal(int sid, final TextView tvSalesAll) {
        NetWorks.getSidSalesTotal(sid, new BaseObserver<Double>() {
            @Override
            public void onHandleSuccess(Double aDouble) {
                tvSalesAll.setText(doubleNumber(aDouble));
            }

            @Override
            public void onHandleError(int code, String message) {
                Toast.makeText(mContext, code + message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return partnerList.size() + 5;
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
        LinearLayout llOrder;
        LinearLayout llSales;
        LinearLayout llTraffic;

        TextView tvOrderToday;
        TextView tvSalesToday;
        TextView tvTrafficToday;
        TextView tvTodayPartners;

        public DataHolder(View itemView) {
            super(itemView);
            llOrder = (LinearLayout) itemView.findViewById(R.id.ll_order);
            llSales = (LinearLayout) itemView.findViewById(R.id.ll_sales);
            llTraffic = (LinearLayout) itemView.findViewById(R.id.ll_traffic);

            tvOrderToday = (TextView) itemView.findViewById(R.id.tv_order_today);
            tvSalesToday = (TextView) itemView.findViewById(R.id.tv_sales_today);
            tvTrafficToday = (TextView) itemView.findViewById(R.id.tv_traffic_today);
            tvTodayPartners = (TextView) itemView.findViewById(R.id.tv_today_partners);
        }
    }

    /*店铺总关注人数、今天合作商家数*/
    public class Data2Holder extends RecyclerView.ViewHolder {

        LinearLayout llAttention;
        TextView tvAttentionAll;
        LinearLayout llPartner;
        TextView tvTodayPartner;
        LinearLayout llSales;
        TextView tvSalesAll;
        LinearLayout llSalesvolume;
        TextView tvSalesvolume;

        public Data2Holder(View itemView) {
            super(itemView);
            llAttention = (LinearLayout) itemView.findViewById(R.id.ll_attention);
            tvAttentionAll = (TextView) itemView.findViewById(R.id.tv_attention_all);

            llPartner = (LinearLayout) itemView.findViewById(R.id.ll_partner);
            tvTodayPartner = (TextView) itemView.findViewById(R.id.tv_today_partner);

            llSales = (LinearLayout) itemView.findViewById(R.id.ll_sales);
            tvSalesAll = (TextView) itemView.findViewById(R.id.tv_sales_all);

            llSalesvolume = (LinearLayout) itemView.findViewById(R.id.ll_salesvolume);
            tvSalesvolume = (TextView) itemView.findViewById(R.id.tv_salesvolume);
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


    /*关于店铺的月销售量查看*/
    public class SalesHolder extends RecyclerView.ViewHolder {
        LinearLayout llViewSales;
        TextView tvSalesMonth;

        public SalesHolder(View itemView) {
            super(itemView);
            llViewSales = (LinearLayout) itemView.findViewById(R.id.ll_view_sales);
            tvSalesMonth = (TextView) itemView.findViewById(R.id.tv_sales_month);
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

