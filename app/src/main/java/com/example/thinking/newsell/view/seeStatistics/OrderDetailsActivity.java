package com.example.thinking.newsell.view.seeStatistics;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.Order;
import com.example.thinking.newsell.bean.OrderAddress;
import com.example.thinking.newsell.bean.OrderGood;
import com.example.thinking.newsell.commen.Commen;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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

public class OrderDetailsActivity extends AppCompatActivity {
   /* @BindView(R.id.details_status)
    TextView detailsStatus;
    @BindView(R.id.details_receiver)
    TextView detailsReceiver;
    @BindView(R.id.details_tell)
    TextView detailsTell;
    @BindView(R.id.details_address)
    TextView detailsAddress;


    @BindView(R.id.dt_order_id)
    TextView dtOrderId;
    @BindView(R.id.dt_order_createtime)
    TextView dtOrderCreatetime;
    @BindView(R.id.dt_order_paytime)
    TextView dtOrderPaytime;
    @BindView(R.id.dt_order_shiptime)
    TextView dtOrderShiptime;
    @BindView(R.id.dt_order_finishtime)
    TextView dtOrderFinishtime;

    @BindView(R.id.re_status)
    RelativeLayout reStatus;
    @BindView(R.id.details_totalprice)
    TextView detailsTotalprice;
    @BindView(R.id.details_payway)
    TextView detailsPayway;
    @BindView(R.id.details_prices)
    TextView detailsPrices;  */



    @BindView(R.id.details_recycler)
    RecyclerView detailsRecycler;

    DetailsAdapter detailsAdapter;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_details);
        ButterKnife.bind(this);
        final Order order = (Order) getIntent().getSerializableExtra(Commen.ORDERONE);

        //标题的返回键
        tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    /*    //订单状态
        switch (order.getStatue()) {
            case 0:
                detailsStatus.setText("待付款");
                reStatus.setBackgroundResource(R.color.colorRed0);
                dtOrderCreatetime.setText("创建时间：" + order.getCreatetime());
                dtOrderPaytime.setVisibility(View.GONE);
                dtOrderShiptime.setVisibility(View.GONE);
                dtOrderFinishtime.setVisibility(View.GONE);
                break;
            case 1:
                detailsStatus.setText("待发货");
                reStatus.setBackgroundResource(R.color.colorOrange0);
                dtOrderCreatetime.setText("创建时间：" + order.getCreatetime());
                dtOrderPaytime.setText("支付时间：" + order.getPaytime());
                dtOrderShiptime.setVisibility(View.GONE);
                dtOrderFinishtime.setVisibility(View.GONE);
                break;
            case 2:
                detailsStatus.setText("待收货");
                reStatus.setBackgroundResource(R.color.colorYellow0);
                dtOrderCreatetime.setText("创建时间：" + order.getCreatetime());
                dtOrderPaytime.setText("支付时间：" + order.getPaytime());
                dtOrderShiptime.setText("发货时间：" + order.getShiptime());
                dtOrderFinishtime.setVisibility(View.GONE);
                break;
            case 3:
                detailsStatus.setText("已完成");
                reStatus.setBackgroundResource(R.color.colorGreen0);
                dtOrderCreatetime.setText("创建时间：" + order.getCreatetime());
                dtOrderPaytime.setText("支付时间：" + order.getPaytime());
                dtOrderShiptime.setText("发货时间：" + order.getShiptime());
                dtOrderFinishtime.setText("完成时间：" + order.getDealtime());
                break;
            default:
                break;
        }
        dtOrderId.setText("订单编号：" + order.getNumber());


        detailsTotalprice.setText("总计：" + String.valueOf(order.getTotalprice()) + "元");
        detailsPayway.setText(order.getPaytype());
        detailsPrices.setText("合计：" + String.valueOf(order.getTotalprice()));
        NetWorks.getBySaidAddress(order.getSaid(), new BaseObserver<OrderAddress>() {
            @Override
            public void onHandleSuccess(OrderAddress orderAddress) {
                detailsAddress.setText(orderAddress.getAddress());//地址
                detailsReceiver.setText(orderAddress.getName());//收货人姓名
                detailsTell.setText(orderAddress.getPhone());//电话
            }

            @Override
            public void onHandleError(int code, String message) {
                Toast.makeText(OrderDetailsActivity.this, code + message, Toast.LENGTH_SHORT).show();
            }
        });*/

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        detailsRecycler.setLayoutManager(linearLayoutManager);

        //根据订单号查商品信息
        NetWorks.getByOidGoods(order.getOid(), new BaseObserver<List<OrderGood>>() {
            @Override
            public void onHandleSuccess(List<OrderGood> orderGoods) {
                detailsAdapter = new DetailsAdapter(OrderDetailsActivity.this, order,orderGoods);
                detailsRecycler.setAdapter(detailsAdapter);
            }

            @Override
            public void onHandleError(int code, String message) {
                Toast.makeText(OrderDetailsActivity.this, code + message, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
