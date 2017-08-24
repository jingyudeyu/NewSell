package com.example.thinking.newsell.view.HomeFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class StatisticsFragment extends Fragment {
    @BindView(R.id.recycler_statistics)
    RecyclerView recyclerStatistics;
    Unbinder unbinder;

    LinearLayoutManager linearLayoutManager;
    StatisticsAdapter statisticsAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View StatisticsView = inflater.inflate(R.layout.fragment_statistics, container, false);
        unbinder = ButterKnife.bind(this, StatisticsView);
      //  linearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false);
        LinearLayoutManager linearManager = new LinearLayoutManager(getActivity());
       recyclerStatistics.setLayoutManager(linearManager);

        NetWorks.getAllPartners(0, new BaseObserver<List<Partner>>() {
            @Override
            public void onHandleSuccess(List<Partner> partnerList) {
                statisticsAdapter=new StatisticsAdapter(getActivity(),partnerList);
                recyclerStatistics.setAdapter(statisticsAdapter);
            }
            @Override
            public void onHandleError(int code, String message) {
                Toast.makeText(getActivity(),code+message,Toast.LENGTH_SHORT).show();
            }
        });




        return StatisticsView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
