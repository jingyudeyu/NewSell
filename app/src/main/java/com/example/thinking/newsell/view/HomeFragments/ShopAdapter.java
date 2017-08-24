package com.example.thinking.newsell.view.HomeFragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.Commodity;
import com.example.thinking.newsell.bean.Partner;
import com.example.thinking.newsell.bean.Shop;
import com.example.thinking.newsell.bean.User;
import com.example.thinking.newsell.commen.Commen;
import com.example.thinking.newsell.utils.system.SpUtils;
import com.example.thinking.newsell.view.seeshop.GoodInfo.GoodActivity;
import com.example.thinking.newsell.view.seeshop.ShopActivity;
import com.example.thinking.newsell.view.seeshop.ShopFragments.FragmentHomeRecyclerViewAdapter;
import com.example.thinking.newsell.view.seeshop.ShopFragments.InfoFragments.InfoShelfGood;
import com.example.thinking.newsell.view.seeshop.ShopFragments.InfoFragments.InfoShopGoods;

import java.io.Serializable;
import java.util.List;

import static com.xiaomi.push.service.y.n;
import static com.xiaomi.push.service.y.s;

/**
 * *****************************************
 * Created by thinking on 2017/8/20.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class ShopAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPEONE = 1;//代表type1 首页的头部分
    private static final int TYPETWO = 2;//代表type2 首页下方的合作商品部分
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Partner> partnerList;//合作商品列表
    private PopupWindow popupWindow;
    private RecyclerView moreRecycleview;
    private NameAdapter nameAdapter;

    public ShopAdapter(Context context, List<Partner> partnerList) {
        this.mContext = context;
        this.partnerList = partnerList;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPEONE;
        } else return TYPETWO;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPEONE:
                HeadHolder holder1 = new HeadHolder(mLayoutInflater.inflate(R.layout.shopmanage_head, parent, false));
                return holder1;

            case TYPETWO:
                HotHolder holder = new HotHolder(mLayoutInflater.inflate(R.layout.partner_good, parent, false));
                return holder;
            default:
                break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        switch (getItemViewType(position)) {
            case TYPEONE:


                //    popupWindow.showAsDropDown(((HeadHolder)holder).headShopname);//设置位置

                final User user = (User) SpUtils.getObject(mContext, Commen.USERINFO);
                final HeadHolder headHolder = (HeadHolder) holder;
                headHolder.headShopname.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //点击更换店铺
                        Log.v("点击更换店铺", "1");
                        initPopupWindow(mContext);
                        popupWindow.showAsDropDown(headHolder.headShopname);//设置位置
                        Log.v("点击更换店铺", "2");
                    }
                });


                //查看店铺
                headHolder.tvShopmanage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //通过sp中保存的默认店铺sid去查看商品
                        NetWorks.getShopInfoById(SpUtils.getInt(mContext, Commen.SHOPSIDdefault), new BaseObserver<Shop>() {
                            @Override
                            public void onHandleSuccess(Shop shop) {
                                //  headHolder.headShopname.setText(shop.getShopname());
                                Bundle bundle = new Bundle();
                                bundle.putSerializable(Commen.SHOPINFO, shop);
                                Intent intent = new Intent(mContext, ShopActivity.class);
                                intent.putExtras(bundle);
                                mContext.startActivity(intent);
                            }

                            @Override
                            public void onHandleError(int code, String message) {
                                Toast.makeText(mContext, code + message, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                //查看上架商品
                headHolder.tvsee1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        NetWorks.getGoodStatus(SpUtils.getInt(mContext, Commen.SHOPSIDdefault), 0, new BaseObserver<List<Commodity>>() {
                            @Override
                            public void onHandleSuccess(List<Commodity> commodities) {
                                if (commodities.size() == 0) {
                                    Toast.makeText(mContext, "暂无上架商品", Toast.LENGTH_SHORT).show();
                                } else if (commodities.size() != 0) {
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable(Commen.ABOVEGOODS, (Serializable) commodities);
                                    Intent intent = new Intent(mContext, InfoShelfGood.class);
                                    intent.putExtras(bundle);
                                    mContext.startActivity(intent);
                                }
                            }

                            @Override
                            public void onHandleError(int code, String message) {
                                Toast.makeText(mContext, code + message, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                //查看下架商品
                headHolder.tvsee2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NetWorks.getGoodStatus(SpUtils.getInt(mContext, Commen.SHOPSIDdefault), 1, new BaseObserver<List<Commodity>>() {
                            @Override
                            public void onHandleSuccess(List<Commodity> commodities) {
                                if (commodities.size() == 0) {
                                    Toast.makeText(mContext, "暂无下架商品", Toast.LENGTH_SHORT).show();
                                } else if (commodities.size() != 0) {
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable(Commen.ABOVEGOODS, (Serializable) commodities);
                                    Intent intent = new Intent(mContext, InfoShelfGood.class);
                                    intent.putExtras(bundle);
                                    mContext.startActivity(intent);
                                }
                            }

                            @Override
                            public void onHandleError(int code, String message) {
                                Toast.makeText(mContext, code + message, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                //查看自己店铺的合作商品
                headHolder.tvsee3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NetWorks.getShopGoods(SpUtils.getInt(mContext, Commen.SHOPSIDdefault), new BaseObserver<List<Partner>>() {
                            @Override
                            public void onHandleSuccess(List<Partner> partnerList) {
                                if (partnerList.size() == 0) {
                                    Toast.makeText(mContext, "暂无合作商品", Toast.LENGTH_SHORT).show();
                                } else if (partnerList.size() != 0) {
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable(Commen.SHOPGOODS, (Serializable) partnerList);
                                    Intent intent = new Intent(mContext, InfoShopGoods.class);
                                    intent.putExtras(bundle);
                                    mContext.startActivity(intent);
                                }
                            }

                            @Override
                            public void onHandleError(int code, String message) {
                                Toast.makeText(mContext, code + message, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                break;

            case TYPETWO:
                HotHolder hotHolder = (HotHolder) holder;
                hotHolder.partnerNum.setText(String.valueOf(partnerList.get(position).getCount()));
                hotHolder.partnerGoodname.setText(partnerList.get(position).getGoodsname());
                Glide.with(mContext).load(partnerList.get(position).getGoodslogo()).centerCrop().into(hotHolder.partnerGoodimage);
                hotHolder.partnerGoodprice.setText(partnerList.get(position).getPrice());
                hotHolder.textSales.setText(String.valueOf(partnerList.get(position).getSaleCount()));
                hotHolder.partnerShopname.setText(partnerList.get(position).getShopname());
                hotHolder.partnerCity.setText(partnerList.get(position).getCity());
                hotHolder.intentionNum.setText(String.valueOf(partnerList.get(position).getIntentcount()));
                hotHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NetWorks.getIDgood(partnerList.get(position).getCid(), new BaseObserver<Commodity>() {
                            @Override
                            public void onHandleSuccess(final Commodity commodity) {
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("commodity", commodity);
                                Intent intent = new Intent(mContext, GoodActivity.class);
                                intent.putExtras(bundle);
                                mContext.startActivity(intent);
                            }

                            @Override
                            public void onHandleError(int code, String message) {
                                Toast.makeText(mContext, code + message, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                break;
        }
    }

    private void initPopupWindow(final Context context) {

        final View view = LayoutInflater.from(context).inflate(R.layout.shop_allpage, null);
        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.MATCH_PARENT, false);
        moreRecycleview = (RecyclerView) view.findViewById(R.id.more_recycleview);
        LinearLayoutManager linearManager = new LinearLayoutManager(context);
        moreRecycleview.setLayoutManager(linearManager);
        User user = (User) SpUtils.getObject(context, Commen.USERINFO);
        NetWorks.getShopInfo(user.getBid(), new BaseObserver<List<Shop>>() {
            @Override
            public void onHandleSuccess(List<Shop> shops) {
                nameAdapter = new NameAdapter(context, shops);
                moreRecycleview.setAdapter(nameAdapter);
            }

            @Override
            public void onHandleError(int code, String message) {
                Toast.makeText(mContext, code + message, Toast.LENGTH_SHORT).show();
            }
        });

        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);//设置获取焦点
        popupWindow.setOutsideTouchable(true);//设置外部可点击消失
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x50ffffff));//设置背景

    }

    @Override
    public int getItemCount() {
        return partnerList.size();
    }


    /*头部的holder*/
    public class HeadHolder extends RecyclerView.ViewHolder {

        private TextView headShopname;
        private TextView tvShopmanage;
        private TextView tvsee1;
        private TextView tvsee2;
        private TextView tvsee3;

        public HeadHolder(View itemView) {
            super(itemView);
            headShopname = (TextView) itemView.findViewById(R.id.head_shopname);
            tvShopmanage = (TextView) itemView.findViewById(R.id.tv_shopmanage);
            tvsee1 = (TextView) itemView.findViewById(R.id.tvsee1);
            tvsee2 = (TextView) itemView.findViewById(R.id.tvsee2);
            tvsee3 = (TextView) itemView.findViewById(R.id.tvsee3);
        }

    }

    /*首页展示合作商品的holder*/
    public class HotHolder extends RecyclerView.ViewHolder {
        private TextView partnerNum;
        private TextView partnerGoodname;
        private ImageView partnerGoodimage;
        private TextView partnerGoodprice;
        private TextView textSales;
        private TextView partnerShopname;
        private TextView partnerCity;
        private TextView intentionNum;

        public HotHolder(View itemView) {
            super(itemView);
            partnerNum = (TextView) itemView.findViewById(R.id.partner_num);
            partnerGoodname = (TextView) itemView.findViewById(R.id.partner_goodname);
            partnerGoodimage = (ImageView) itemView.findViewById(R.id.partner_goodimage);
            partnerGoodprice = (TextView) itemView.findViewById(R.id.partner_goodprice);
            textSales = (TextView) itemView.findViewById(R.id.text_sales);
            partnerShopname = (TextView) itemView.findViewById(R.id.partner_shopname);
            partnerCity = (TextView) itemView.findViewById(R.id.partner_city);
            intentionNum = (TextView) itemView.findViewById(R.id.intention_num);
        }
    }

}
