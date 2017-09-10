package com.example.thinking.newsell.view.HomeFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.example.thinking.newsell.bean.User;
import com.example.thinking.newsell.commen.Commen;
import com.example.thinking.newsell.utils.system.SpUtils;
import com.example.thinking.newsell.view.seemy.MyselfInfo;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jp.wasabeef.glide.transformations.CropCircleTransformation;


/**
 * *****************************************
 * Created by thinking on 2017/8/2.
 * 创建时间：
 * <p>
 * 描述：个人信息页面
 * <p/>
 * <p/>
 * *******************************************
 */

public class MyFragment extends Fragment {


    Unbinder unbinder;
    @BindView(R.id.my_image)
    ImageView myImage;
    @BindView(R.id.my_tv_name)
    TextView myTvName;
    @BindView(R.id.re_myfragment)
    RelativeLayout reMyfragment;
    @BindView(R.id.my_sales_1)
    TextView mySales1;
    @BindView(R.id.my_sales_2)
    TextView mySales2;
    @BindView(R.id.my_myinfo)
    TextView myMyinfo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View MyView = inflater.inflate(R.layout.fragment_mymanage, container, false);
        unbinder = ButterKnife.bind(this, MyView);


        User user = (User) SpUtils.getObject(getContext(), Commen.USERINFO);
        Glide.with(getContext()).load(user.getPic()).bitmapTransform(new CropCircleTransformation(getContext())).into(myImage);
        myTvName.setText(user.getNickname());
        int sid=SpUtils.getInt(getContext(),Commen.SHOPSIDdefault);
        NetWorks.getSidSalesTotal(sid, new BaseObserver<Double>() {
            @Override
            public void onHandleSuccess(Double aDouble) {
                mySales2.setText(aDouble+"元");
            }

            @Override
            public void onHandleError(int code, String message) {
                Toast.makeText(getContext(), code + message, Toast.LENGTH_SHORT).show();
            }
        });

        SimpleDateFormat simpleDate = new SimpleDateFormat("yyy-MM-dd");
        String date = simpleDate.format(new java.util.Date());
        NetWorks.getSidDateSales(sid, date, new BaseObserver<Double>() {
            @Override
            public void onHandleSuccess(Double aDouble) {
                mySales1.setText(aDouble+"元");
            }

            @Override
            public void onHandleError(int code, String message) {
                Toast.makeText(getContext(), code + message, Toast.LENGTH_SHORT).show();
            }
        });

        myMyinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity(), MyselfInfo.class);
                startActivity(intent1);
            }
        });

        return MyView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
