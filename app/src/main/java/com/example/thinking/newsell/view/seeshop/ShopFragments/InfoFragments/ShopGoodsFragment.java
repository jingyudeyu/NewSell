package com.example.thinking.newsell.view.seeshop.ShopFragments.InfoFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.thinking.newsell.R;
import com.example.thinking.newsell.bean.Partner;
import com.example.thinking.newsell.commen.Commen;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.R.id.list;

/**
 * *****************************************
 * Created by thinking on 2017/8/1.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class ShopGoodsFragment extends Fragment {
    @BindView(R.id.infopage_recyclerview)
    RecyclerView infopageRecyclerview;
    Unbinder unbinder;

    private List<Partner> partnerList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View shopgoodsView = inflater.inflate(R.layout.shop_infopage, container, false);
        unbinder = ButterKnife.bind(this, shopgoodsView);
        Bundle bundle = getArguments();
        partnerList = (List<Partner>) bundle.getSerializable(Commen.SHOPGOODS);
        Log.v("商品",partnerList.size()+"啊啊啊");
        infopageRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        infopageRecyclerview.setAdapter(new ShopGoodsAdapter(getContext(),partnerList));
        return shopgoodsView;
    }

    public class ShopGoodsAdapter extends RecyclerView.Adapter<ShopGoodsAdapter.PartnerHolder> {
        private Context mcontext;
        private List<Partner> partnerList;
        private LayoutInflater layoutInflater;

        public ShopGoodsAdapter(Context context,List<Partner> partnerList){
            this.mcontext=context;
            this.partnerList=partnerList;
            this.layoutInflater=LayoutInflater.from(mcontext);
        }
        @Override
        public PartnerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            PartnerHolder holder=new PartnerHolder(layoutInflater.inflate(R.layout.partner_good,parent,false));
            return holder;
        }

        @Override
        public void onBindViewHolder(PartnerHolder holder, int position) {
            holder.partnerNum.setText(String.valueOf(partnerList.get(position).getCount()));
            holder.partnerGoodname.setText(partnerList.get(position).getGoodsname());
            Glide.with(mcontext).load(partnerList.get(position).getGoodslogo()).centerCrop().into(holder.partnerGoodimage);
            holder.partnerGoodprice.setText(partnerList.get(position).getPrice());
            holder.textSales.setText(String.valueOf(partnerList.get(position).getSaleCount()));
            holder.linear.setVisibility(View.GONE);

        }

        @Override
        public int getItemCount() {
            return partnerList.size();
        }

        public class PartnerHolder extends RecyclerView.ViewHolder {
            private TextView partnerNum;
            private TextView partnerGoodname;
            private ImageView partnerGoodimage;
            private TextView partnerGoodprice;
            private TextView textSales;
            private RelativeLayout linear;
            public PartnerHolder(View itemView) {
                super(itemView);
                partnerNum = (TextView) itemView.findViewById(R.id.partner_num);
                partnerGoodname = (TextView) itemView.findViewById(R.id.partner_goodname);
                partnerGoodimage = (ImageView) itemView.findViewById(R.id.partner_goodimage);
                partnerGoodprice = (TextView) itemView.findViewById(R.id.partner_goodprice);
                textSales = (TextView) itemView.findViewById(R.id.text_sales);
                linear = (RelativeLayout) itemView.findViewById(R.id.linear);

            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
