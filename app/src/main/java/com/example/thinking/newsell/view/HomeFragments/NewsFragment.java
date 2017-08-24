package com.example.thinking.newsell.view.HomeFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thinking.newsell.R;
import com.example.thinking.newsell.view.chat.ChatActivity;
import com.example.thinking.newsell.view.chat.ContactListFragment;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseConversationListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * *****************************************
 * Created by thinking on 2017/8/2.
 * 创建时间：
 * <p>
 * 描述：店铺消息页，包括会话消息、及好友列表
 * <p/>
 * <p/>
 * *******************************************
 */

public class NewsFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.tv_a)
    Button tvA;
    @BindView(R.id.tv_b)
    Button tvB;
    @BindView(R.id.framelayout_news)
    FrameLayout framelayoutNews;
    Unbinder unbinder;

    private EaseConversationListFragment conversationListFragment;
    private ContactListFragment contactListFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View NewsView = inflater.inflate(R.layout.fragment_news, container, false);
        unbinder = ButterKnife.bind(this, NewsView);
        conversationListFragment = new EaseConversationListFragment();
        contactListFragment = new ContactListFragment();


        conversationListFragment.setConversationListItemClickListener(new EaseConversationListFragment.EaseConversationListItemClickListener() {

            @Override
            public void onListItemClicked(EMConversation conversation) {

                Intent chat = new Intent(getActivity(), ChatActivity.class);
                chat.putExtra("you", false);
                chat.putExtra(EaseConstant.EXTRA_USER_ID, conversation.conversationId());  //对方账号
                chat.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EMMessage.ChatType.Chat); //单聊模式
                startActivity(chat);

            }
        });

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout_news, conversationListFragment);

        tvA.setOnClickListener(this);
        tvB.setOnClickListener(this);

        fragmentTransaction.commit();

        return NewsView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        switch (v.getId()) {
            case R.id.tv_a:
                tvA.setTextColor(getResources().getColor(R.color.colorPrimary));
                tvB.setTextColor(getResources().getColor(R.color.colorBlack));
                fragmentTransaction.replace(R.id.framelayout_news, conversationListFragment);
                break;
            case R.id.tv_b:
                tvB.setTextColor(getResources().getColor(R.color.colorPrimary));
                tvA.setTextColor(getResources().getColor(R.color.colorBlack));
                fragmentTransaction.replace(R.id.framelayout_news, contactListFragment);

                break;
            default:
                break;
        }
        fragmentTransaction.commit();

    }


}
