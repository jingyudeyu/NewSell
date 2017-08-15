package com.example.thinking.newsell.view.seeshop.GoodInfo.Ask;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

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
    private AskAdapter askAdapter;
    private Commodity commodity;
    private List<Quest> questList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.good_ask);
        ButterKnife.bind(this);
        commodity= (Commodity) getIntent().getSerializableExtra("commodity");
        setSupportActionBar(askToolbar);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        askRecyclerview.setLayoutManager(linearLayoutManager);
        NetWorks.getCidQuest(commodity.getCid(), new BaseObserver<List<Quest>>() {
            @Override
            public void onHandleSuccess(List<Quest> quests) {
                questList=quests;
                askAdapter = new AskAdapter(AskActivity.this, questList);
                Log.v("列表长：",String.valueOf(questList.size()));
                askRecyclerview.setAdapter(askAdapter);
            }

            @Override
            public void onHandleError(int code, String message) {

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



    private void getCidQuest(){
        NetWorks.getCidQuest(commodity.getCid(), new BaseObserver<List<Quest>>() {
            @Override
            public void onHandleSuccess(List<Quest> quests) {
                questList=quests;
               /* for(Quest quest:quests){
                    questList.add(quest);
                }
                askAdapter.notifyDataSetChanged();*/
            }

            @Override
            public void onHandleError(int code, String message) {

            }
        });
    }
}
