package com.example.thinking.newsell.view.chat;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.baidu.platform.comapi.map.A;
import com.bumptech.glide.Glide;
import com.example.thinking.newsell.MainActivity;
import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.Commodity;
import com.example.thinking.newsell.bean.Partner;
import com.example.thinking.newsell.view.seeshop.GoodInfo.GoodActivity;
import com.hyphenate.chat.EMChatManager;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroupManager;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.widget.chatrow.EaseCustomChatRowProvider;
import com.hyphenate.exceptions.HyphenateException;

import static android.R.attr.button;
import static com.example.thinking.newsell.R.id.container;

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


    Commodity commodity;
 AlertDialog alertDialog;
    AlertDialog.Builder builder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        setChatFragmentHelper(this);


        if (getArguments().getBoolean("you")==true){
            commodity=(Commodity) getArguments().getSerializable("commodity");
            tvGoodName.setText(commodity.getProductname());
            tvGoodPrice.setText(String.valueOf(commodity.getPrice()));
            Glide.with(getActivity()).load(commodity.getLogo()).centerCrop().into(imageGood);
            tvSendLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendTextMessage(commodity.getProductname()+" $"+commodity.getCid()+"￥"+" →戳我查看商品详情");
                    rlGoodInfo.setVisibility(View.GONE);//商品信息展示

                    tvWantPartenr.setVisibility(View.VISIBLE);//显示建立合作的点击textview
                   tvWantPartenr.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           inputMenu.setVisibility(View.GONE);

                           creatDialog();
                           //对话框
                            alertDialog = builder.create();
                           alertDialog.show();
                       }

                   });
                }
            });

        }else rlGoodInfo.setVisibility(View.GONE);

    }

    /*对话框*/
    private void creatDialog() {
        final  EditText et = new EditText(getActivity());
        //对话框
        builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("输入合作理由");
        builder.setView(et);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                final String input = et.getText().toString();
                if (input.equals("")) {
                    Toast.makeText(getActivity(), "合作理由不能为空！" + input, Toast.LENGTH_LONG).show();
                }
                else {
                    inputMenu.setVisibility(View.VISIBLE);


                        new Thread(new Runnable() {
                            public void run() {
                                // 调用sdk的同意方法
                                try {
                                    EMClient.getInstance().contactManager().addContact(toChatUsername,input);
                                    tvWantPartenr.setVisibility(View.GONE);
                                } catch (final Exception e) {
                                    e.printStackTrace();
                                        }
                                }
                        }).start();

                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                inputMenu.setVisibility(View.VISIBLE);
            }
        });
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
