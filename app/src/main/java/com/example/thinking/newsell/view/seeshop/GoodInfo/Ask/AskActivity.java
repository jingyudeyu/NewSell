package com.example.thinking.newsell.view.seeshop.GoodInfo.Ask;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.Commodity;
import com.example.thinking.newsell.bean.Quest;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * *****************************************
 * Created by thinking on 2017/8/14.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class AskActivity extends AppCompatActivity {
    @BindView(R.id.ask_toolbar)
    Toolbar askToolbar;
    @BindView(R.id.ask_recyclerview)
    RecyclerView askRecyclerview;
    @BindView(R.id.re_nullask)
    RelativeLayout reNullask;
    private AskAdapter askAdapter;
    private Commodity commodity;
    private List<Quest> questList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.good_ask);
        ButterKnife.bind(this);
        commodity = (Commodity) getIntent().getSerializableExtra("commodity");
        setSupportActionBar(askToolbar);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        askRecyclerview.setLayoutManager(linearLayoutManager);
        NetWorks.getCidQuest(commodity.getCid(), new BaseObserver<List<Quest>>() {
            @Override
            public void onHandleSuccess(List<Quest> quests) {
                if (quests.size()!=0){
                    questList = quests;
                    askAdapter = new AskAdapter(AskActivity.this, questList,commodity.getSid());
                    Log.v("源头sid：", commodity.getSid()+"");
                    Log.v("列表长：", String.valueOf(questList.size()));
                    askRecyclerview.setAdapter(askAdapter);
                }else {
                    askRecyclerview.setVisibility(View.GONE);
                    reNullask.setVisibility(View.VISIBLE);
                }

            }
            @Override
            public void onHandleError(int code, String message) {
                Toast.makeText(AskActivity.this,code+message,Toast.LENGTH_SHORT).show();
            }
        });


        /*标题栏的返回按钮*/
        askToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
