package com.example.thinking.newsell.view.seeshop.GoodInfo.Ask;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.Buyer;
import com.example.thinking.newsell.bean.Quest;
import com.example.thinking.newsell.bean.Shop;
import com.example.thinking.newsell.view.seeshop.GoodInfo.AssessInfo.AssessDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

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

public class ReplyAdapter extends RecyclerView.Adapter<ReplyAdapter.ReplyHolder> {
    private List<Quest.RepliesBean> replyList = new ArrayList<>();
    private Context mContext;
    private LayoutInflater layoutInflater;

    public ReplyAdapter(Context context, List<Quest.RepliesBean> replyList) {
        this.mContext = context;
        this.replyList = replyList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ReplyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ReplyHolder(layoutInflater.inflate(R.layout.show_one_ask_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final ReplyHolder holder, int position) {
        Quest.RepliesBean reply = replyList.get(position);

        if (reply.getBuystatue()!=2){
            NetWorks.getIdInfo(reply.getUid(), new BaseObserver<Buyer>() {
                @Override
                public void onHandleSuccess(Buyer buyer) {
                    if (!TextUtils.isEmpty(buyer.getHeaderpic())) {
                        Glide.with(mContext).load(buyer.getHeaderpic()).bitmapTransform(new CropCircleTransformation(mContext)).into(holder.userImage);
                    }
                }

                @Override
                public void onHandleError(int code, String message) {
                    Toast.makeText(mContext, code+message, Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            NetWorks.getShopInfoById(reply.getUid(), new BaseObserver<Shop>() {
                @Override
                public void onHandleSuccess(Shop shop) {
                  //  if (!TextUtils.isEmpty(shop.getLogo())) {
                        Glide.with(mContext).load(shop.getLogo()).bitmapTransform(new CropCircleTransformation(mContext)).into(holder.userImage);

                //    }
                }

                @Override
                public void onHandleError(int code, String message) {
                    Toast.makeText(mContext, code+message, Toast.LENGTH_SHORT).show();
                }
            });
        }

        holder.userName.setText(reply.getUsername());
        holder.time.setText(reply.getRetime());
        holder.replyText.setText(reply.getContent());
        if (reply.getBuystatue() == 1) {
            holder.buyStatue.setBackgroundColor(Color.parseColor("#737272"));
            holder.buyStatue.setText("未买");
        } else if (reply.getBuystatue() == 2) {

            holder.buyStatue.setBackgroundColor(Color.parseColor("#e9735e"));
            holder.buyStatue.setText("商家");
        }
    }


    @Override
    public int getItemCount() {
        return replyList.size();
    }

    class ReplyHolder extends RecyclerView.ViewHolder {
        private ImageView userImage;
        private TextView userName;
        private TextView time;
        private TextView replyText;
        private TextView buyStatue;

        public ReplyHolder(View itemView) {
            super(itemView);
            buyStatue = (TextView) itemView.findViewById(R.id.one_ask_item_buystatue);
            userImage = (ImageView) itemView.findViewById(R.id.one_ask_item_image);
            userName = (TextView) itemView.findViewById(R.id.one_ask_item_name);
            time = (TextView) itemView.findViewById(R.id.one_ask_item_time);
            replyText = (TextView) itemView.findViewById(R.id.one_ask_item_reply);
        }
    }
}
