package com.example.thinking.newsell.view.seeshop.GoodInfo.AssessInfo;

import android.app.Dialog;
import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daasuu.bl.BubbleLayout;
import com.example.thinking.newsell.R;
import com.example.thinking.newsell.bean.Assess;
import com.example.thinking.newsell.commen.Commen;
import com.example.thinking.newsell.view.views.StarRating;
import com.github.chrisbanes.photoview.PhotoView;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.thinking.newsell.R.id.reply_textview;


/**
 * *****************************************
 * Created by thinking on 2017/6/13.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class AssessRecyclerViewAdapter extends RecyclerView.Adapter<AssessRecyclerViewAdapter.AssessViewHolder> implements View.OnClickListener {
    public Context mContext;
    public List<Assess> mDatas;
    public LayoutInflater mLayoutInflater;
    String[] pics;
    /*对话框*/
    Dialog imageDialog;
    int sid;


    public AssessRecyclerViewAdapter(Context mContext, List<Assess> assesses, int sid) {
        this.mContext = mContext;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.mDatas = assesses;
        this.sid=sid;
    }

    @Override
    public AssessViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View mView = mLayoutInflater.inflate(R.layout.assess_item, parent, false);
        AssessViewHolder mViewHolder = new AssessViewHolder(mView);
        mView.setOnClickListener(this);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(final AssessViewHolder holder, final int position) {

        holder.assess_user_time.setText(String.valueOf(mDatas.get(position).getDate().substring(0,10)));
        holder.assess_text_view.setText(mDatas.get(position).getDetail());
        holder.overviewAssess.setText(mDatas.get(position).getHollrall());
        holder.assess_user_name.setText(newName(mDatas.get(position).getUsername()));
        holder.assess_user_num.setText("x" + mDatas.get(position).getCount());
        holder.assess_user_choice.setText(mDatas.get(position).getParam());
        holder.assess_ratingbar.setCurrentStarCount((int) mDatas.get(position).getGrade());
        if (mDatas.get(position).getBossback()!=null) {
            if (!mDatas.get(position).getBossback().equals("")) {
                holder.reply_bubblelayout.setVisibility(View.VISIBLE);
                holder.reply_textview.setText(mDatas.get(position).getBossback());
            }
        }
        if (mDatas.get(position).getPics() == null) {
            holder.assessGridview.setVisibility(View.GONE);
        }
        if (mDatas.get(position).getPics() != null) {
            pics = mDatas.get(position).getPics().split(";");
            holder.assessGridview.setVisibility(View.VISIBLE);
            int size = pics.length;
            int length = 100;
            WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dm = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(dm);
            float density = dm.density;
            int gridviewWidth = (int) (size * (length + 4) * density);
            int itemWidth = (int) (length * density);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(gridviewWidth, LinearLayout.LayoutParams.FILL_PARENT);
            holder.assessGridview.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
            holder.assessGridview.setColumnWidth(itemWidth);
            holder.assessGridview.setHorizontalSpacing(5); // 设置列表项水平间距
            holder.assessGridview.setStretchMode(GridView.NO_STRETCH);
            holder.assessGridview.setNumColumns(size); // 设置列数量=列表集合数
            MyHorizontalListAdapter HorizontalAdapter = new MyHorizontalListAdapter(pics, mContext);
            holder.assessGridview.setAdapter(HorizontalAdapter);
            holder.assessGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //对话框
                    imageDialog = new Dialog(mContext, R.style.map_dialog);
                    RelativeLayout root = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.assess_dialog, null);
                    root.findViewById(R.id.re_diaog).setOnClickListener(dialoglistener);
                    PhotoView dialog_image = (PhotoView) root.findViewById(R.id.dialog_image);
                    Glide.with(mContext).load(pics[position]).into(dialog_image);
                    imageDialog.setContentView(root);
                    imageDialog.show();

                }
            });
        }

        holder.itemView.setOnClickListener(this);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt(Commen.SHOPSID,sid);
                bundle.putSerializable(Commen.ASSESSONE, mDatas.get(position));
                Intent intent = new Intent(mContext, AssessDetailsActivity.class);
                intent.putExtras(bundle);
               mContext.startActivity(intent);
            }
        });

    }

    private String newName(String username) {
        String newname;
      int num=username.length();
        newname=username.charAt(0)+"**"+username.charAt(num-2)+username.charAt(num-1);
        return newname;
    }


    //对话框的点击事件
    private View.OnClickListener dialoglistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.re_diaog:
                    imageDialog.dismiss();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public void onClick(View v) {

    }


    class AssessViewHolder extends RecyclerView.ViewHolder {
        TextView assess_user_name;
        TextView assess_user_time;
        TextView assess_user_choice;
        TextView assess_user_num;
        StarRating assess_ratingbar;
        TextView overviewAssess;
        ExpandableTextView assess_text_view;
        MyGridView assessGridview;
        CircleImageView assess_user_image;
        BubbleLayout reply_bubblelayout;
        TextView reply_textview;


        public AssessViewHolder(View itemView) {
            super(itemView);
            assess_user_name = (TextView) itemView.findViewById(R.id.assess_user_name);
            assess_user_time = (TextView) itemView.findViewById(R.id.assess_user_time);
            assess_user_choice = (TextView) itemView.findViewById(R.id.assess_user_choice);
            assess_user_num = (TextView) itemView.findViewById(R.id.assess_user_num);

            assess_ratingbar = (StarRating) itemView.findViewById(R.id.assess_ratingbar);
            assess_text_view = (ExpandableTextView) itemView.findViewById(R.id.assess_text_view);
            overviewAssess = (TextView) itemView.findViewById(R.id.overview_assess);
            assessGridview = (MyGridView) itemView.findViewById(R.id.assess_gridview);
            assess_user_image = (CircleImageView) itemView.findViewById(R.id.assess_user_image);
            reply_bubblelayout = (BubbleLayout) itemView.findViewById(R.id.reply_bubblelayout);
            reply_textview = (TextView) itemView.findViewById(R.id.reply_textview);
        }
    }

    /*HorizontalAdapter*/
    public class MyHorizontalListAdapter extends BaseAdapter {
        private String[] pics;
        private Context context;
        private LayoutInflater inflater;

        public MyHorizontalListAdapter(String[] pics, Context mContext) {
            this.context = mContext;
            this.pics = pics;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return pics.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(mContext, R.layout.assess_grid_pic, null);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.grid_image);
            Glide.with(mContext).load(pics[position]).into(imageView);
            return convertView;
        }
    }
}
