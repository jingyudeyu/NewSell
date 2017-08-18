package com.example.thinking.newsell.view.chat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.example.thinking.newsell.R;
import com.hyphenate.easeui.ui.EaseChatFragment;

/**
 * 说明：
 * Date: 2017/8/13
 * Created by lovegod .
 * Email:dx96_j@163.com
 */

public class ChactActivity extends AppCompatActivity {
    EaseChatFragment easeChatFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.chat_activity_into);

         easeChatFragment = new EaseChatFragment();  //环信聊天界面
        easeChatFragment.setArguments(getIntent().getExtras()); //需要的参数
        getSupportFragmentManager().beginTransaction().add(R.id.layout_chat,easeChatFragment).commit();  //Fragment切换

    }
}
