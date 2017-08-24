package com.example.thinking.newsell.view.chat;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thinking.newsell.R;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.ui.EaseBaseActivity;
import com.hyphenate.exceptions.HyphenateException;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * *****************************************
 * Created by thinking on 2017/8/18.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class NewFriendsActivity extends EaseBaseActivity {
    @BindView(R.id.recycler_messages)
    RecyclerView recyclerMessages;
    Map<String, String> list = new ArrayMap<>();
    NewFriendAdapter newFriendAdapter;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.newfriends_recyclerview);
        ButterKnife.bind(this);

        if (getIntent().getStringExtra("username") != null && getIntent().getStringExtra("reason") != null) {
            list.put("username",getIntent().getStringExtra("username"));
            list.put("reason",getIntent().getStringExtra("reason"));
            newFriendAdapter = new NewFriendAdapter(this,list);
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
            recyclerMessages.setLayoutManager(linearLayoutManager);
            recyclerMessages.setAdapter(newFriendAdapter);
        }


    }

    public class NewFriendAdapter extends RecyclerView.Adapter<NewFriendAdapter.FriendHolder> {
        private Context context;
        private LayoutInflater layoutInflater;
        private Map<String, String> list;

        public NewFriendAdapter(Context context, Map<String, String> list) {
            this.context = context;
            this.list = list;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public FriendHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.ask_list_item, parent, false);
            FriendHolder Holder = new FriendHolder(view);
            return Holder;
        }

        @Override
        public void onBindViewHolder(FriendHolder holder, int position) {

            holder.avatar.setImageResource(R.mipmap.service_logo);
            holder.name.setText(list.get("username"));
            holder.message.setText(list.get("reason"));
            holder.yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        EMClient.getInstance().contactManager().declineInvitation(list.get("username"));
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                    }
                }
            });

            holder.no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        EMClient.getInstance().contactManager().declineInvitation(list.get("username"));
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class FriendHolder extends RecyclerView.ViewHolder {
            ImageView avatar;
            TextView name;
            TextView message;
            Button yes;
            Button no;

            public FriendHolder(View itemView) {
                super(itemView);
                avatar = (ImageView) itemView.findViewById(R.id.avatar);
                name = (TextView) itemView.findViewById(R.id.name);
                message = (TextView) itemView.findViewById(R.id.message);
                yes = (Button) itemView.findViewById(R.id.yes);
                no = (Button) itemView.findViewById(R.id.no);
            }
        }

    }

}
