package com.example.thinking.newsell.view.seeStatistics;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.Order;
import com.example.thinking.newsell.bean.User;
import com.example.thinking.newsell.commen.Commen;
import com.example.thinking.newsell.utils.system.SpUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.http.Path;

import static android.R.attr.order;
import static android.provider.Contacts.SettingsColumns.KEY;
import static com.example.thinking.newsell.R.id.more_recycleview;
import static com.example.thinking.newsell.R.id.num;

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

public class OrderFragment extends Fragment {

    private LinearLayout liPointOrder;//没有订单的提示

    private RecyclerView orderRecycleview;
    private LinearLayoutManager linearLayoutManager;
    private OrderAdapter orderAdapter;
    private int statustype;
    private int datetype;
    private List<Order> orderList;

    private int page = 0;

    private int lastVisibleItem;

    public static Fragment newInstance(int date, int status) {
        OrderFragment orderFragment = new OrderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Commen.DATEMARK, date);
        bundle.putInt(Commen.STATUSMARK, status);
        orderFragment.setArguments(bundle);
        return orderFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View Orderview = inflater.inflate(R.layout.shop_allpage, container, false);
        orderRecycleview = (RecyclerView) Orderview.findViewById(R.id.more_recycleview);
        liPointOrder = (LinearLayout) Orderview.findViewById(R.id.point_nullorder);
        return Orderview;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        statustype = bundle.getInt(Commen.STATUSMARK);
        datetype = bundle.getInt(Commen.DATEMARK);

        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        orderRecycleview.setLayoutManager(linearLayoutManager);

        Change(datetype, page);

        orderRecycleview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == orderAdapter.getItemCount()) {
                    page = page + 1;
                    Change(datetype,page);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }
        });


    }

    public void Change(int datetype, int page) {
        switch (datetype) {
            case 0:
                //今天的订单
                if (statustype == 7) {
                    getOrderTodayAll(page);

                } else
                    getOrderTodayStatus(statustype, page);
                break;
            case 7:
                //all订单
                if (statustype == 7) {
                    getAllOrder(page);
                } else
                    getOrderStatus(statustype, page);
                break;
        }

    }

    private void getOrderTodayAll(final int page) {
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyy-MM-dd");
        String date = simpleDate.format(new java.util.Date());
        NetWorks.getSidDateOrder(SpUtils.getInt(getContext(), Commen.SHOPSIDdefault), date, page, new BaseObserver<List<Order>>() {
            @Override
            public void onHandleSuccess(List<Order> orders) {

                if (orders.size()!=0){
                    if (page == 0) {
                        orderList=orders;
                        orderAdapter = new OrderAdapter(getActivity(), orderList);
                        orderRecycleview.setAdapter(orderAdapter);
                    } else {
                        orderList.addAll(orders);
                        orderAdapter.notifyDataSetChanged();
                    }
                }

            }

            @Override
            public void onHandleError(int code, String message) {

            }
        });
    }


    //根据店铺sid、订单状态、订单今天的日期、页数 获取订单信息
    private void getOrderTodayStatus(int statustype, final int page) {
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyy-MM-dd");
        String date = simpleDate.format(new java.util.Date());
        NetWorks.getBySSDOrderAll(SpUtils.getInt(getContext(), Commen.SHOPSIDdefault), statustype, date, page, new BaseObserver<List<Order>>() {
            @Override
            public void onHandleSuccess(List<Order> orders) {
                if (orders.size()!=0){
                    if (page == 0) {
                        orderList=orders;
                        orderAdapter = new OrderAdapter(getActivity(), orderList);
                        orderRecycleview.setAdapter(orderAdapter);
                    } else {
                        orderList.addAll(orders);
                        orderAdapter.notifyDataSetChanged();
                    }

                }
            }

            @Override
            public void onHandleError(int code, String message) {
                //返回为null时显示界面
            }
        });
    }


    //根据店铺sid ,查该店铺所有订单信息
    private void getAllOrder(final int page) {
        NetWorks.getSidAllOrder(SpUtils.getInt(getContext(), Commen.SHOPSIDdefault), page, new BaseObserver<List<Order>>() {
            @Override
            public void onHandleSuccess(List<Order> orders) {
                if (orders.size()!=0){
                    if (page == 0) {
                        orderList=orders;
                        orderAdapter = new OrderAdapter(getActivity(), orderList);
                        orderRecycleview.setAdapter(orderAdapter);
                    } else {
                        orderList.addAll(orders);
                        orderAdapter.notifyDataSetChanged();
                    }

                }
            }

            @Override
            public void onHandleError(int code, String message) {
            }
        });
    }

    //根据店铺sid、订单状态、页数 获取订单信息
    private void getOrderStatus(int statustype, final int page) {

        NetWorks.getBySSOrderAll(SpUtils.getInt(getContext(), Commen.SHOPSIDdefault), statustype, page, new BaseObserver<List<Order>>() {
            @Override
            public void onHandleSuccess(List<Order> orders) {

                if (orders.size()!=0){
                    if (page == 0) {
                        orderList=orders;
                        orderAdapter = new OrderAdapter(getActivity(), orderList);
                        orderRecycleview.setAdapter(orderAdapter);
                    } else {
                        orderList.addAll(orders);
                        orderAdapter.notifyDataSetChanged();
                    }

                }
            }

            @Override
            public void onHandleError(int code, String message) {

            }
        });
    }
}
