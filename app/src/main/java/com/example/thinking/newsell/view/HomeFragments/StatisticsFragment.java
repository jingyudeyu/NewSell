package com.example.thinking.newsell.view.HomeFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thinking.newsell.R;

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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View StatisticsView = inflater.inflate(R.layout.fragment_shopmanage, container, false);

        return StatisticsView;
    }
}
