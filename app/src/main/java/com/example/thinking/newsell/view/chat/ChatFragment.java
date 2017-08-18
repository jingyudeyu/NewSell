package com.example.thinking.newsell.view.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.Commodity;
import com.example.thinking.newsell.view.seeshop.GoodInfo.GoodActivity;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.widget.chatrow.EaseCustomChatRowProvider;

/**
 * *****************************************
 * Created by thinking on 2017/8/17.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class ChatFragment extends EaseChatFragment implements EaseChatFragment.EaseChatFragmentHelper{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        setChatFragmentHelper(this);

    }


        @Override
        public void onSetMessageAttributes(EMMessage message) {

        }

        @Override
        public void onEnterToChatDetails() {

        }

        @Override
        public void onAvatarClick(String username) {

        }

        @Override
        public void onAvatarLongClick(String username) {

        }

        @Override
        public boolean onMessageBubbleClick(EMMessage message) {
            int type = message.getType().ordinal();
            String mell = message.getBody().toString();

            if (type == EMMessage.Type.TXT.ordinal()) {
                if (mell.indexOf("$") != -1&&mell.indexOf("￥")!=-1) {
                    String goodid = mell.substring(mell.indexOf("$") + 1, mell.indexOf("￥"));

                    NetWorks.getIDgood(Integer.valueOf(goodid), new BaseObserver<Commodity>() {
                        @Override
                        public void onHandleSuccess(Commodity commodity) {
                            Bundle bundle=new Bundle();
                            bundle.putSerializable("commodity",commodity);
                            Intent intent=new Intent(getActivity(), GoodActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }

                        @Override
                        public void onHandleError(int code, String message) {

                        }
                    });

                }
            }

            return true;
        }

        @Override
        public void onMessageBubbleLongClick(EMMessage message) {

        }

        @Override
        public boolean onExtendMenuItemClick(int itemId, View view) {
            return false;
        }

        @Override
        public EaseCustomChatRowProvider onSetCustomChatRowProvider() {
            return null;
        }

}
