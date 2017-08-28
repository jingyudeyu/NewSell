package com.example.thinking.newsell.view.seeStatistics;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.example.thinking.newsell.bean.Order;
import com.example.thinking.newsell.bean.OrderAddress;
import com.example.thinking.newsell.bean.OrderGood;
import com.example.thinking.newsell.view.HomeFragments.ShopAdapter;

import java.util.IllegalFormatCodePointException;
import java.util.List;

import butterknife.BindView;

import static android.R.attr.order;

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

    private static final int HEADTYPE = 0;
    private static final int BODYTYPE = 1;
    private static final int FOOTERTYPE = 2;

    private Context mContext;
    private List<OrderGood> orderGoodlist;
    private Order order;
    private int headCount = 1;
    private int footerCount = 1;

    public DetailsAdapter(Context context, Order order, List<OrderGood> orderGoods) {
        this.mContext = context;
        this.orderGoodlist = orderGoods;
        this.order = order;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            Log.v("TYPE第一个", String.valueOf(position));
            return HEADTYPE;
        } else if (position > 0 && position < getItemCount() - 1) {
            Log.v("TYPE第二个", String.valueOf(position));
            return BODYTYPE;
        } else if (position == getItemCount() - 1) {
            return FOOTERTYPE;
        }
        return BODYTYPE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case HEADTYPE:
                View view = LayoutInflater.from(mContext).inflate(R.layout.order_details_head, parent, false);
                HeardHolder heardHolder = new HeardHolder(view);
                return heardHolder;

            case BODYTYPE:
                View view1 = LayoutInflater.from(mContext).inflate(R.layout.ordergood_item, parent, false);
                DetailHolder detailHolder = new DetailHolder(view1);
                return detailHolder;

            case FOOTERTYPE:
                View view2 = LayoutInflater.from(mContext).inflate(R.layout.order_details_footer, parent, false);
                FooterHolder footerHolder = new FooterHolder(view2);
                return footerHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof DetailHolder) {

            DetailHolder detailHolder = (DetailHolder) holder;
            OrderGood orderGood = orderGoodlist.get(position - 1);
            Glide.with(mContext).load(orderGood.getLogo()).into(detailHolder.goodimage);
            detailHolder.goodname.setText(orderGood.getGoodsname());
            detailHolder.goodprice.setText("价格：" + orderGood.getTotalprice());
            detailHolder.goodcount.setText("x" + orderGood.getCount());

        } else if (holder instanceof HeardHolder) {
            final HeardHolder heardHolder = (HeardHolder) holder;
            switch (order.getStatue()) {
                case 0:
                    heardHolder.detailsStatus.setText("待付款");
                    heardHolder.reStatus.setBackgroundResource(R.color.colorRed0);

                    break;
                case 1:
                    heardHolder.detailsStatus.setText("待发货");
                    heardHolder.reStatus.setBackgroundResource(R.color.colorOrange0);

                    break;
                case 2:
                    heardHolder.detailsStatus.setText("待收货");
                    heardHolder.reStatus.setBackgroundResource(R.color.colorYellow0);

                    break;
                case 3:
                    heardHolder.detailsStatus.setText("已完成");
                    heardHolder.reStatus.setBackgroundResource(R.color.colorGreen0);

                    break;
                default:
                    break;
            }
            heardHolder.detailsTotalprice.setText("￥" + order.getTotalprice());
            NetWorks.getBySaidAddress(order.getSaid(), new BaseObserver<OrderAddress>() {
                @Override
                public void onHandleSuccess(OrderAddress orderAddress) {
                    heardHolder.detailsAddress.setText(orderAddress.getAddress());//地址
                    heardHolder.detailsReceiver.setText(orderAddress.getName());//收货人姓名
                    heardHolder.detailsTell.setText(orderAddress.getPhone());//电话
                }

                @Override
                public void onHandleError(int code, String message) {
                    Toast.makeText(mContext, code + message, Toast.LENGTH_SHORT).show();
                }
            });

        } else if (holder instanceof FooterHolder) {
            //订单状态
            FooterHolder footerHolder = (FooterHolder) holder;
            switch (order.getStatue()) {
                case 0:
                    footerHolder.dtOrderCreatetime.setText("创建时间：" + order.getCreatetime());
                    footerHolder.dtOrderPaytime.setVisibility(View.GONE);
                    footerHolder.dtOrderShiptime.setVisibility(View.GONE);
                    footerHolder.dtOrderFinishtime.setVisibility(View.GONE);
                    break;
                case 1:
                    footerHolder.dtOrderCreatetime.setText("创建时间：" + order.getCreatetime());
                    footerHolder.dtOrderPaytime.setText("支付时间：" + order.getPaytime());
                    footerHolder.dtOrderShiptime.setVisibility(View.GONE);
                    footerHolder.dtOrderFinishtime.setVisibility(View.GONE);
                    break;
                case 2:
                    footerHolder.dtOrderCreatetime.setText("创建时间：" + order.getCreatetime());
                    footerHolder.dtOrderPaytime.setText("支付时间：" + order.getPaytime());
                    footerHolder.dtOrderShiptime.setText("发货时间：" + order.getShiptime());
                    footerHolder.dtOrderFinishtime.setVisibility(View.GONE);
                    break;
                case 3:
                    footerHolder.dtOrderCreatetime.setText("创建时间：" + order.getCreatetime());
                    footerHolder.dtOrderPaytime.setText("支付时间：" + order.getPaytime());
                    footerHolder.dtOrderShiptime.setText("发货时间：" + order.getShiptime());
                    footerHolder.dtOrderFinishtime.setText("完成时间：" + order.getDealtime());
                    break;
                default:
                    break;
            }
            footerHolder.dtOrderId.setText("订单编号：" + order.getNumber());
            footerHolder.detailsPrices.setText("总计：" + order.getTotalprice());

        }

    }

    @Override
    public int getItemCount() {

        return footerCount + orderGoodlist.size() + headCount;
    }

    public class HeardHolder extends RecyclerView.ViewHolder {
        RelativeLayout reStatus;
        TextView detailsTotalprice;
        TextView detailsPayway;
        TextView detailsStatus;
        TextView detailsReceiver;
        TextView detailsTell;
        TextView detailsAddress;

        public HeardHolder(View itemView) {
            super(itemView);
            reStatus = (RelativeLayout) itemView.findViewById(R.id.re_status);
            detailsTotalprice = (TextView) itemView.findViewById(R.id.details_totalprice);
            detailsPayway = (TextView) itemView.findViewById(R.id.details_payway);
            detailsStatus = (TextView) itemView.findViewById(R.id.details_status);
            detailsReceiver = (TextView) itemView.findViewById(R.id.details_receiver);
            detailsTell = (TextView) itemView.findViewById(R.id.details_tell);
            detailsAddress = (TextView) itemView.findViewById(R.id.details_address);

        }
    }

    public class FooterHolder extends RecyclerView.ViewHolder {

        TextView dtOrderId;
        TextView dtOrderCreatetime;
        TextView dtOrderPaytime;
        TextView dtOrderShiptime;
        TextView dtOrderFinishtime;
        TextView detailsPrices;

        public FooterHolder(View itemView) {
            super(itemView);
            dtOrderId = (TextView) itemView.findViewById(R.id.dt_order_id);
            dtOrderCreatetime = (TextView) itemView.findViewById(R.id.dt_order_createtime);
            dtOrderPaytime = (TextView) itemView.findViewById(R.id.dt_order_paytime);
            dtOrderShiptime = (TextView) itemView.findViewById(R.id.dt_order_shiptime);
            dtOrderFinishtime = (TextView) itemView.findViewById(R.id.dt_order_finishtime);
            detailsPrices = (TextView) itemView.findViewById(R.id.details_prices);

        }
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
