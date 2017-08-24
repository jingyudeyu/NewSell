package com.example.thinking.newsell.view.HomeFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.thinking.newsell.R;
import com.example.thinking.newsell.view.HomeActivity;
import com.example.thinking.newsell.view.seemy.MyselfInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

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
    @BindView(R.id.btn)
    Button btn;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View MyView = inflater.inflate(R.layout.fragment_mymanage, container, false);
        unbinder = ButterKnife.bind(this, MyView);

        btn.setOnClickListener(new View.OnClickListener() {
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
