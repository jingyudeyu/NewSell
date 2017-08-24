package com.example.thinking.newsell.view.seeshop.ShopFragments.InfoFragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.Commodity;
import com.example.thinking.newsell.bean.Partner;
import com.example.thinking.newsell.commen.Commen;
import com.example.thinking.newsell.view.seeshop.GoodInfo.GoodActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;

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

public class InfoShopGoods extends AppCompatActivity {

    @BindView(R.id.infopage_recyclerview)
    RecyclerView infopageRecyclerview;
    @BindView(R.id.toolbar_logo)
    ImageView toolbarLogo;
    @BindView(R.id.toolbar_shopname)
    TextView toolbarShopname;
    @BindView(R.id.toolbar_toolbar)
    TextView toolbarToolbar;
    @BindView(R.id.toolbar_rel)
    RelativeLayout toolbarRel;
    @BindView(R.id.toolbar_back)
    ImageView toolbarBack;
    private List<Partner> partnerList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_infopage);
        ButterKnife.bind(this);
      /*  shopgoodsToolbar.setTitle("合作商品查看");
        setSupportActionBar(shopgoodsToolbar);*/

        //获取ShopInfoFragment页面传来的所有合作商品信息
        partnerList = (List<Partner>) getIntent().getSerializableExtra(Commen.SHOPGOODS);


        if (partnerList.size()!=0){
            Glide.with(InfoShopGoods.this).load(partnerList.get(0).getShoplogo()).bitmapTransform(new BlurTransformation(this,10)).into(toolbarLogo);
            toolbarShopname.setText(partnerList.get(0).getShopname());
            toolbarToolbar.setText("合作商品查看");
            toolbarRel.setVisibility(View.VISIBLE);
            toolbarBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            infopageRecyclerview.setLayoutManager(new LinearLayoutManager(this));
            infopageRecyclerview.setAdapter(new ShopGoodsAdapter(this, partnerList));

        }


     /*   Log.v("合作的商品",partnerList.size()+"aaaaa");
        beenPartners = new ArrayList<>();
        noPartners = new ArrayList<>();
        for (int i = 0; i < partnerList.size(); i++) {
            if (partnerList.get(i).getCount() == 0) {
                //尚未有合作的商品
                noPartners.add(partnerList.get(i));
            } else {
                //已有合作的商品
                beenPartners.add(partnerList.get(i));
            }
        }

        Log.v("尚未有合作的商品",noPartners.size()+"aaaaa");
        Log.v("已有合作的商品",beenPartners.size()+"aaaaa");
        Bundle bundleno = new Bundle();
        bundleno.putSerializable(Commen.SHOPGOODS, (Serializable) noPartners);
        shopGoodsFragment1.setArguments(bundleno);

       *//* Bundle bundlebeen = new Bundle();
        bundlebeen.putSerializable(Commen.SHOPGOODS, (Serializable) beenPartners);
        shopGoodsFragment2.setArguments(bundlebeen);
*//*
        fragments.add(shopGoodsFragment1);
      //  fragments.add(shopGoodsFragment2);
        fragments.add(shopInfoFragment);

        titles = getResources().getStringArray(R.array.tab_titles);
        shopFragmentAdapter = new ShopFragmentAdapter(getSupportFragmentManager(), this, fragments, titles);
        shopgoodsTablayout.addTab(shopgoodsTablayout.newTab().setText(titles[0]));
        shopgoodsTablayout.addTab(shopgoodsTablayout.newTab().setText(titles[1]));

        shopgoodsViewpager.setOffscreenPageLimit(2);
        shopgoodsTablayout.setupWithViewPager(shopgoodsViewpager);
        shopgoodsTablayout.setTabMode(TabLayout.MODE_FIXED);
        shopgoodsTablayout.setTabsFromPagerAdapter(shopFragmentAdapter);*/


    }

    public class ShopGoodsAdapter extends RecyclerView.Adapter<ShopGoodsAdapter.PartnerHolder> {
        private Context mcontext;
        private List<Partner> partnerList;
        private LayoutInflater layoutInflater;

        public ShopGoodsAdapter(Context context, List<Partner> partnerList) {
            this.mcontext = context;
            this.partnerList = partnerList;
            this.layoutInflater = LayoutInflater.from(mcontext);
        }

        @Override
        public PartnerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            PartnerHolder holder = new PartnerHolder(layoutInflater.inflate(R.layout.partner_good, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(PartnerHolder holder, final int position) {
            holder.partnerNum.setText(String.valueOf(partnerList.get(position).getCount()));
            holder.partnerGoodname.setText(partnerList.get(position).getGoodsname());
            Glide.with(mcontext).load(partnerList.get(position).getGoodslogo()).centerCrop().into(holder.partnerGoodimage);
            holder.partnerGoodprice.setText(partnerList.get(position).getPrice());
            holder.textSales.setText(String.valueOf(partnerList.get(position).getSaleCount()));
            holder.linear.setVisibility(View.GONE);
            holder.intentionNum.setText(String.valueOf(partnerList.get(position).getIntentcount()));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NetWorks.getIDgood(partnerList.get(position).getCid(), new BaseObserver<Commodity>() {
                        @Override
                        public void onHandleSuccess(final Commodity commodity) {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("commodity", commodity);
                            Intent intent = new Intent(mcontext, GoodActivity.class);
                            intent.putExtras(bundle);
                            mcontext.startActivity(intent);
                        }

                        @Override
                        public void onHandleError(int code, String message) {

                        }
                    });
                }
            });
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
            private TextView intentionNum;

            public PartnerHolder(View itemView) {
                super(itemView);
                partnerNum = (TextView) itemView.findViewById(R.id.partner_num);
                partnerGoodname = (TextView) itemView.findViewById(R.id.partner_goodname);
                partnerGoodimage = (ImageView) itemView.findViewById(R.id.partner_goodimage);
                partnerGoodprice = (TextView) itemView.findViewById(R.id.partner_goodprice);
                textSales = (TextView) itemView.findViewById(R.id.text_sales);
                linear = (RelativeLayout) itemView.findViewById(R.id.linear);
                intentionNum = (TextView) itemView.findViewById(R.id.intention_num);

            }
        }
    }

}
