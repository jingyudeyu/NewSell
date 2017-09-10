package com.example.thinking.newsell.view.HomeFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.Partner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * *****************************************
 * Created by thinking on 2017/8/2.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class StatisticsFragment extends BaseFragment {
    @BindView(R.id.recycler_statistics)
    RecyclerView recyclerStatistics;
    Unbinder unbinder;

    LinearLayoutManager linearLayoutManager;
    StatisticsAdapter statisticsAdapter;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.statistics_title)
    TextView statisticsTitle;
    @BindView(R.id.right_title)
    TextView rightTitle;
    @BindView(R.id.re_title)
    RelativeLayout reTitle;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View StatisticsView = inflater.inflate(R.layout.fragment_statistics, container, false);
        unbinder = ButterKnife.bind(this, StatisticsView);
        LinearLayoutManager linearManager = new LinearLayoutManager(getActivity());
        recyclerStatistics.setLayoutManager(linearManager);

        swiperefresh.setProgressViewOffset(true,50,100);
        swiperefresh.setSize(SwipeRefreshLayout.DEFAULT);
        swiperefresh.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                statisticsAdapter.notifyDataSetChanged();
                swiperefresh.setRefreshing(false);
            }
        });

        initView();



        return StatisticsView;
    }

    private void initView() {
        NetWorks.getAllPartners(0, new BaseObserver<List<Partner>>() {
            @Override
            public void onHandleSuccess(List<Partner> partnerList) {
                statisticsAdapter = new StatisticsAdapter(getActivity(), partnerList);
                recyclerStatistics.setAdapter(statisticsAdapter);
            }

            @Override
            public void onHandleError(int code, String message) {
                Toast.makeText(getActivity(), code + message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
