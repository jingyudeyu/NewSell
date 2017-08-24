package com.example.thinking.newsell.view.chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


import com.example.thinking.newsell.MainActivity;
import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.Shop;
import com.example.thinking.newsell.bean.User;
import com.example.thinking.newsell.bean.UserBuyer;
import com.example.thinking.newsell.commen.Commen;
import com.example.thinking.newsell.utils.system.SpUtils;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMChatManager;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseBaseActivity;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.widget.EaseChatMessageList;
import com.hyphenate.easeui.widget.EaseTitleBar;

import java.util.List;

import static android.R.id.content;
import static com.example.thinking.newsell.R.id.shop;
import static com.hyphenate.easeui.model.EaseDefaultEmojiconDatas.getData;

public class ChatActivity extends EaseBaseActivity  {
    public static ChatActivity activityInstance;
    private EaseChatFragment chatFragment;
    String toChatUsername;
    EaseChatMessageList easeChatMesssgeList;
    EaseChatMessageList.MessageListItemClickListener llll;
   // private EaseTitleBar titleBar;
    User user;
    EaseChatFragment.EaseChatFragmentHelper ease;
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_chat);
        activityInstance = this;
        //聊天人或群id
        toChatUsername = getIntent().getExtras().getString(EaseConstant.EXTRA_USER_ID);
       // chatFragment = new EaseChatFragment();
        chatFragment=new ChatFragment();
        //传入参数

        chatFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.container, chatFragment).commit();


        user = (User)SpUtils.getObject(this,Commen.USERINFO);
        GoName();
    }

    /*设置标题栏*/
    private void GoName() {

        NetWorks.knowByPhone(getToChatUsername(), new BaseObserver<UserBuyer>() {
            @Override
            public void onHandleSuccess(UserBuyer userBuyer) {
                //type 0用户1店主2空
                if (userBuyer.getType()==0){
                    chatFragment.setTitle("用户:"+userBuyer.getUser().getUsername());

                }else if (userBuyer.getType()==1){
                    //根据商家对话方的得到的店铺id，去查店铺名
                    NetWorks.getShopInfo(userBuyer.getBoss().getBid(), new BaseObserver<List<Shop>>() {

                        @Override
                        public void onHandleSuccess(List<Shop> shops) {
                            chatFragment.setTitle("商家:"+shops.get(0).getShopname());
                        }

                        @Override
                        public void onHandleError(int code, String message) {

                        }
                    });


                }else if (userBuyer.getType()==2){

                }
            }

            @Override
            public void onHandleError(int code, String message) {

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityInstance = null;
    }
    
    @Override
    protected void onNewIntent(Intent intent) {
        // 点击notification bar进入聊天页面，保证只有一个聊天页面
        String username = intent.getStringExtra("userId");
        if (toChatUsername.equals(username))
            super.onNewIntent(intent);
        else {
            finish();
            startActivity(intent);
        }

    }
    @Override
    public void onBackPressed() {
        chatFragment.onBackPressed();
    }
    
    public String getToChatUsername(){
        return toChatUsername;
    }
}
