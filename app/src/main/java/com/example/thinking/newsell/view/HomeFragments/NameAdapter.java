package com.example.thinking.newsell.view.HomeFragments;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.thinking.newsell.R;
import com.example.thinking.newsell.bean.Shop;
import com.example.thinking.newsell.commen.Commen;
import com.example.thinking.newsell.utils.system.SpUtils;
import com.example.thinking.newsell.view.seeshop.ShopFragments.FragmentHomeRecyclerViewAdapter;

import java.util.List;

/**
 * *****************************************
 * Created by thinking on 2017/8/22.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class NameAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<Shop> shopList;
    private LayoutInflater mLayoutInflater;
    MyItemOnClickListener mMyItemOnClickListener;

    public NameAdapter(Context context, List<Shop> shoplist) {
        this.mContext = context;
        this.shopList = shoplist;
        mLayoutInflater=LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.shop_popupwindow_item, parent, false);

        NameHolder nameHolder = new NameHolder(view,mMyItemOnClickListener);

        return nameHolder;
    }

    public void setItemOnClickListener(MyItemOnClickListener listener){
        mMyItemOnClickListener=listener;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        NameHolder nameHolder = (NameHolder) holder;
        Glide.with(mContext).load(shopList.get(position).getLogo()).into(nameHolder.popuShopImage);
        nameHolder.popuShopName.setText(shopList.get(position).getShopname());
        nameHolder.popuShopLocation.setText(shopList.get(position).getSaddress());
     //   nameHolder.itemView.setTag(position);
        nameHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(mContext,shopList.get(position).getShopname(),Toast.LENGTH_SHORT).show();
                SpUtils.removeKey(mContext, Commen.SHOPSIDdefault);
                SpUtils.putInt(mContext, Commen.SHOPSIDdefault,shopList.get(position).getSid());
            }
        });
    }

    public interface MyItemOnClickListener {
        public void onItemOnClick(View view,int postion);
    }

    @Override
    public int getItemCount() {
        return shopList.size();
    }


    public class NameHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

        ImageView popuShopImage;
        TextView popuShopName;
        TextView popuShopIschain;
        TextView popuShopLocation;

        MyItemOnClickListener mListener;

        public NameHolder(View itemView,MyItemOnClickListener  myItemOnClickListener) {
            super(itemView);
            popuShopImage = (ImageView) itemView.findViewById(R.id.popu_shop_image);
            popuShopName = (TextView) itemView.findViewById(R.id.popu_shop_name);
            popuShopIschain = (TextView) itemView.findViewById(R.id.popu_shop_ischain);
            popuShopLocation = (TextView) itemView.findViewById(R.id.popu_shop_location);

            this.mListener=myItemOnClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mListener!=null){
                mListener.onItemOnClick(v,getPosition());
            }
        }
    }
}
