package com.example.thinking.newsell.view.seeshop.GoodInfo.Ask;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.thinking.newsell.R;
import com.example.thinking.newsell.bean.Quest;

import java.util.List;

import butterknife.BindView;

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

public class AskAdapter extends RecyclerView.Adapter<AskAdapter.AskHolder > {


    private Context context;
    private LayoutInflater layoutInflater;
    private List<Quest> questList;

    public AskAdapter(Context context, List<Quest> questList) {
        this.context = context;
        this.questList = questList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public AskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ask_list_item, parent, false);
        AskHolder Holder = new AskHolder(view);
        return Holder;

    }

    public void onBindViewHolder(AskHolder holder,  int position) {
        final Quest quest=questList.get(position);
        holder.askItemAsktext.setText(quest.getTitle());
        if(quest.getReplies().size()!=0) {
            holder.askItemAnswertext.setText(quest.getReplies().get(0).getContent());
        }else {
            holder.askItemAnswertext.setText("(该问题还没有人回答,快来回答他吧~)");
        }
        holder.askItemMore.setText("查看"+quest.getReplies().size()+"个回答");
        holder.askItemTime.setText(quest.getAsktime());
        holder.llAskItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                //商品信息
                bundle.putInt("commodityId",quest.getCid());
                //被点击的问题信息
                bundle.putSerializable("quest",quest);
                Intent intent=new Intent(context,ShowOneAskActivity.class);
                intent.putExtra("bundle",bundle);
               context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return questList.size();
    }

    class AskHolder extends RecyclerView.ViewHolder {
        TextView askItemAsktext;
        TextView askItemAnswertext;
        TextView askItemMore;
        TextView askItemTime;
        LinearLayout llAskItem;

        public AskHolder(View itemView) {
            super(itemView);
            askItemAsktext=(TextView)itemView.findViewById(R.id.ask_item_asktext);
            askItemAnswertext=(TextView)itemView.findViewById(R.id.ask_item_answertext);
            askItemMore=(TextView)itemView.findViewById(R.id.ask_item_more);
            askItemTime=(TextView)itemView.findViewById(R.id.ask_item_time);
            llAskItem=(LinearLayout)itemView.findViewById(R.id.ll_ask_item);
        }
    }
}
