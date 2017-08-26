package com.example.thinking.newsell.view.seeStatistics;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thinking.newsell.R;
import com.example.thinking.newsell.bean.Order;
import com.example.thinking.newsell.commen.Commen;

import java.util.List;

import static com.xiaomi.push.service.y.C;

/**
 * *****************************************
 * Created by thinking on 2017/8/24.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class OrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Order> orderlist;

    public OrderAdapter(Context context, List<Order> orderList) {
        this.mContext = context;
        this.orderlist = orderList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.order_item, parent, false);
        OrderHolder orderHolder = new OrderHolder(view);
        return orderHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        OrderHolder orderHolder = (OrderHolder) holder;
        Order order=new Order();
        order=orderlist.get(position);
        orderHolder.OrderId.setText(String.valueOf(order.getNumber()));
        switch (order.getStatue()){
            case 0:
                orderHolder.OrderStatus.setText("待付款");
                orderHolder.OrderStatus.setTextColor(Color.RED);
                break;
            case 1:
                orderHolder.OrderStatus.setText("待发货");
                orderHolder.OrderStatus.setTextColor(Color.RED);
                break;
            case 2:
                orderHolder.OrderStatus.setText("待收货");
                orderHolder.OrderStatus.setTextColor(orderHolder.OrderStatus.getResources().getColor(R.color.colorOrange));
                break;
            case 3:
                orderHolder.OrderStatus.setText("已完成");
                orderHolder.OrderStatus.setTextColor(Color.GREEN);
                break;
            default:
                break;
        }

        orderHolder.OrderUser.setText(String.valueOf(order.getUid()));
        if (!TextUtils.isEmpty(order.getCreatetime())){
            orderHolder.OrderDate.setText(order.getCreatetime().substring(0,10));
        }
        orderHolder.OrderNumber.setText("x"+order.getCount());
        orderHolder.OrderPrice.setText("￥"+order.getTotalprice());
        orderHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,OrderDetailsActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable(Commen.ORDERONE,orderlist.get(position));
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return orderlist.size();
    }


    /*关于订单展示的页面*/
    public class OrderHolder extends RecyclerView.ViewHolder {
        TextView OrderId;
        TextView OrderStatus;
        TextView OrderUser;
        TextView OrderDate;
        TextView OrderNumber;
        TextView OrderPrice;

        public OrderHolder(View itemView) {
            super(itemView);
            OrderId = (TextView) itemView.findViewById(R.id.tv_order_id);
            OrderStatus = (TextView) itemView.findViewById(R.id.tv_order_status);
            OrderUser = (TextView) itemView.findViewById(R.id.tv_order_user);
            OrderDate = (TextView) itemView.findViewById(R.id.tv_order_date);
            OrderNumber = (TextView) itemView.findViewById(R.id.tv_order_number);
            OrderPrice = (TextView) itemView.findViewById(R.id.tv_order_price);
        }
    }
}
