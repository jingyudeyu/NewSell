/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.thinking.newsell.view.chat;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.Shop;
import com.example.thinking.newsell.bean.UserBuyer;
import com.hyphenate.EMContactListener;
import com.hyphenate.chat.EMChatManager;
import com.hyphenate.chat.EMChatService;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroupManager;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.ui.EaseContactListFragment;
import com.hyphenate.exceptions.HyphenateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.logging.LogRecord;

import static android.R.attr.button;
import static com.baidu.location.d.a.i;

/**
 * 联系人列表页
 *
 */
public class ContactListFragment extends EaseContactListFragment {


    private static final String TAG = ContactListFragment.class.getSimpleName();

    private ContactItemView applicationItem;
    private String username=null;
    private String reason=null;
    List<String> usernames;
    Map<String, EaseUser> users;
    @Override
    protected void initView() {
        super.initView();
        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.contacts_fragment, null);
        HeaderItemClickListener clickListener = new HeaderItemClickListener();
        applicationItem = (ContactItemView) headerView.findViewById(R.id.application_item);
        applicationItem.setOnClickListener(clickListener);
        //添加headerview
        listView.addHeaderView(headerView);



      EMClient.getInstance().contactManager().setContactListener(new EMContactListener() {
            @Override
            public void onContactAdded(String s) {

            }

            @Override
            public void onContactDeleted(String s) {

            }

            @Override
            public void onContactInvited(String s, String s1) {
                //收到好友邀请
                applicationItem.showUnreadMsgView();
                username=s;
                reason=s1;
                Log.v("用户名",s);
                Log.v("理由",s1);
                Toast.makeText(getActivity(),"好友消息",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFriendRequestAccepted(String s) {

            }

            @Override
            public void onFriendRequestDeclined(String s) {

            }
        });
        //EMChat.getInstance().setAppInited();






    }










    @Override
    protected void setUpView() {

        //设置联系人数据
        super.setUpView();
   //getList();
       // setContactsMap(getList());
    /*    users = new HashMap<String, EaseUser>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    usernames=new ArrayList<>();
                    usernames = EMClient.getInstance().contactManager().getAllContactsFromServer();
                    Log.v("好于列表;aaaaaaaaaaaaaaaaaaaaa",String.valueOf(usernames.size()));

                    for (int i=0;i<usernames.size();i++){
                        final String fromname=usernames.get(i);
                        final EaseUser user = new EaseUser(usernames.get(i));
                        NetWorks.knowByPhone(usernames.get(i), new BaseObserver<UserBuyer>() {
                            @Override
                            public void onHandleSuccess(UserBuyer userBuyer) {
                                //type 0用户1店主2空
                                if (userBuyer.getType()==0){
                       *//* user.setNick(userBuyer.getUser().getUsername());
                        user.setAvatar(userBuyer.getUser().getHeaderpic());*//*
                                }else if (userBuyer.getType()==1){
                                    //根据商家对话方的得到的店铺id，去查店铺名
                                    NetWorks.getShopInfo(userBuyer.getBoss().getBid(), new BaseObserver<Shop>() {
                                        @Override
                                        public void onHandleSuccess(Shop shop) {
                                            user.setNick(shop.getShopname());
                                            user.setAvatar(shop.getLogo());
                                            users.put(fromname,user);
                                            Log.v("好友店铺名：",user.getNick());
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
                    setContactsMap(users);
                    Log.v("好友有有有有有有有有",String.valueOf(users.size()));

                } catch (HyphenateException e) {
                    e.printStackTrace();
                }

            }
        }).start();*/

       /* new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    users = new HashMap<String, EaseUser>();
                    usernames=new ArrayList<>();
                    usernames = EMClient.getInstance().contactManager().getAllContactsFromServer();
                    Log.v("好于列表;aaaaaaaaaaaaaaaaaaaaa",String.valueOf(usernames.size()));

                      for (int i=0;i<usernames.size();i++){
                    final String fromname=usernames.get(0);
                    final EaseUser user = new EaseUser(fromname);
                    users.put(fromname,user);
                    setContactsMap(users);

                        NetWorks.knowByPhone(usernames.get(i), new BaseObserver<UserBuyer>() {
                            @Override
                            public void onHandleSuccess(UserBuyer userBuyer) {
                                //type 0用户1店主2空
                                if (userBuyer.getType()==0){
                        user.setNick(userBuyer.getUser().getUsername());
                                    user.setAvatar(userBuyer.getUser().getHeaderpic());
                                }else if (userBuyer.getType()==1){
                                    //根据商家对话方的得到的店铺id，去查店铺名
                                    NetWorks.getShopInfo(userBuyer.getBoss().getBid(), new BaseObserver<Shop>() {
                                        @Override
                                        public void onHandleSuccess(Shop shop) {
                                            user.setNick(shop.getShopname());
                                            user.setAvatar(shop.getLogo());
                                            users.put(fromname,user);
                                            Log.v("好友店铺名：",user.getNick());
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


                } catch (HyphenateException e) {
                    e.printStackTrace();
                }

                handler.sendEmptyMessage(1);
            }
        }).start();
*/



        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String username = ((EaseUser) listView.getItemAtPosition(position)).getUsername();
                // demo中直接进入聊天页面，实际一般是进入用户详情页
                startActivity(new Intent(getActivity(), ChatActivity.class).putExtra("userId", username));
            }
        });

    }

    @Override
    public void refresh() {


        super.refresh();

    }

protected class HeaderItemClickListener implements OnClickListener {

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.application_item:
                // 进入申请与通知页面
                applicationItem.hideUnreadMsgView();
                Intent intent = new Intent(getActivity(), NewFriendsActivity.class);
                intent.putExtra("username",username);
                intent.putExtra("reason",reason);

                startActivity(intent);
                break;

            default:
                break;
        }
    }
}

}
