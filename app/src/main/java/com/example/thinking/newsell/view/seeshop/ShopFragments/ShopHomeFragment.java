package com.example.thinking.newsell.view.seeshop.ShopFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.RangeGridLayoutHelper;
import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.Category;
import com.example.thinking.newsell.bean.Commodity;
import com.example.thinking.newsell.bean.User;
import com.example.thinking.newsell.commen.Commen;
import com.example.thinking.newsell.utils.system.SpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.baidu.location.d.j.S;
import static com.xiaomi.push.service.y.C;

/**
 * *****************************************
 * Created by thinking on 2017/7/11.
 * 创建时间：
 * <p>
 * 描述：店铺首页，初次使用vlayout，总是错错错···
 * <p/>
 * <p/>
 * *******************************************
 */

public class ShopHomeFragment extends Fragment {
    @BindView(R.id.more_recycleview)
    RecyclerView moreRecycleview;
    Unbinder unbinder;

    ArrayList<Map<String, Object>> listItem;//selling
    ArrayList<Map<String, Object>> listItem2;//Categorys
    ArrayList<Map<String, Object>> listItem30, listItem31, listItem32;//category+goods
    ArrayList<Map<String, Object>> listItem4;//ItemImage
    SubAdapter Adapter_linearLayout0, Adapter_linearLayout, Adapter_GridLayout, Adapter_GridLayout_0,
            Adapter_GridLayout_1, Adapter_GridLayout_2, Adapter_onePlusNLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View ShopAllView = inflater.inflate(R.layout.shop_allpage, container, false);
        unbinder = ButterKnife.bind(this, ShopAllView);
        final VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(getActivity());
        moreRecycleview.setLayoutManager(virtualLayoutManager);

        /*步骤2:设置组件复用回收池*/
        final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        viewPool.setMaxRecycledViews(0, 5);
        moreRecycleview.setRecycledViewPool(viewPool);

        final RecyclerView.RecycledViewPool viewPool2 = new RecyclerView.RecycledViewPool();
        viewPool2.setMaxRecycledViews(1, 8);
        moreRecycleview.setRecycledViewPool(viewPool2);

        final RecyclerView.RecycledViewPool viewPool3 = new RecyclerView.RecycledViewPool();
        viewPool3.setMaxRecycledViews(3, 12);
        moreRecycleview.setRecycledViewPool(viewPool3);


        final List<DelegateAdapter.Adapter> adapters = new LinkedList<>();
        final DelegateAdapter delegateAdapter = new DelegateAdapter(virtualLayoutManager);
        /**
         * 步骤3:设置需要存放的数据
         * */
        listItem = new ArrayList<Map<String, Object>>();
        listItem2 = new ArrayList<Map<String, Object>>();
        listItem4 = new ArrayList<Map<String, Object>>();
        listItem30 = new ArrayList<Map<String, Object>>();
        listItem31 = new ArrayList<Map<String, Object>>();
        listItem32 = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 5; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("shopimage", R.mipmap.ic_launcher);
            listItem4.add(map);
        }
        final User user = (User) SpUtils.getObject(getContext(), Commen.USERINFO);
        final int sid=getArguments().getInt(Commen.SHOPSID);
        NetWorks.getSidCategory(sid, new BaseObserver<List<Category>>() {
            @Override
            public void onHandleSuccess(List<Category> categories) {
                for (int i = 0; i < categories.size(); i++) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("category", categories.get(i));
                    listItem2.add(map);
                    //  Toast.makeText(getActivity(), ">3", Toast.LENGTH_SHORT).show();
                }

                NetWorks.getIDshopgoods(sid, new BaseObserver<List<Commodity>>() {
                    @Override
                    public void onHandleSuccess(List<Commodity> commodities) {
                        if (commodities.size() != 0) {
                            if (commodities.size() > 5) {
                                for (int i = 0; i < 5; i++) {
                                    Map<String, Object> map = new HashMap<String, Object>();
                                    map.put("selling", commodities.get(i));
                                    listItem.add(map);
                                }
                            } else for (int i = 0; i < commodities.size(); i++) {
                                Map<String, Object> map = new HashMap<String, Object>();
                                map.put("selling", commodities.get(i));
                                listItem.add(map);
                            }
                        }
                        ArrayList<Map<String, Object>> listab = new ArrayList<Map<String, Object>>();
                        listab = listItem;
                        if (listItem2.size() > 0) {
                            for (int i = 0; i < listItem2.size(); i++) {
                                for (int n = 0; n < listab.size(); n++) {
                                    Commodity commodity = (Commodity) listab.get(n).get("selling");
                                    if (commodity.getCgid() == ((Category) listItem2.get(i).get("category")).getCgid()) {
                                        if (i == 0) {
                                            if (listItem30.size() == 0) {
                                                Map<String, Object> map = new HashMap<String, Object>();
                                                Commodity commodity1 = new Commodity();
                                                commodity1.setSid(commodity.getSid());
                                                commodity1.setCgid(commodity.getCgid());
                                                commodity1.setProductname(((Category) listItem2.get(i).get("category")).getSecend());
                                                commodity1.setLogo(((Category) listItem2.get(i).get("category")).getLogo());
                                                map.put("categorysell", commodity1);
                                                listItem30.add(map);
                                            } else {
                                                Map<String, Object> map = new HashMap<String, Object>();
                                                map.put("categorysell", listItem.get(n).get("selling"));
                                                listItem30.add(map);
                                            }
                                        }
                                        if (i == 1) {
                                            if (listItem31.size() == 0) {
                                                Map<String, Object> map = new HashMap<String, Object>();
                                                Commodity commodity1 = new Commodity();
                                                commodity1.setSid(commodity.getSid());
                                                commodity1.setCgid(commodity.getCgid());
                                                commodity1.setProductname(((Category) listItem2.get(i).get("category")).getSecend());
                                                commodity1.setLogo(((Category) listItem2.get(i).get("category")).getLogo());
                                                map.put("categorysell", commodity1);
                                                listItem31.add(map);
                                            }
                                            Map<String, Object> map = new HashMap<String, Object>();
                                            map.put("categorysell", listItem.get(n).get("selling"));
                                            listItem31.add(map);
                                        }
                                        if (i == 2) {
                                            if (listItem32.size() == 0) {
                                                Map<String, Object> map = new HashMap<String, Object>();
                                                Commodity commodity1 = new Commodity();
                                                commodity1.setSid(commodity.getSid());
                                                commodity1.setCgid(commodity.getCgid());
                                                commodity1.setProductname(((Category) listItem2.get(i).get("category")).getSecend());
                                                commodity1.setLogo(((Category) listItem2.get(i).get("category")).getLogo());
                                                map.put("categorysell", commodity1);
                                                listItem32.add(map);
                                            }
                                            Map<String, Object> map = new HashMap<String, Object>();
                                            map.put("categorysell", listItem.get(n).get("selling"));
                                            listItem32.add(map);
                                        }
                                    }
                                }
                            }
                        }

                        AddAdapter(delegateAdapter, adapters);
                    }

                    @Override
                    public void onHandleError(int code, String message) {
                        Toast.makeText(getActivity(), "获取商店所有商品出错", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onHandleError(int code, String message) {
                Toast.makeText(getActivity(), "获取商店分类错误", Toast.LENGTH_SHORT).show();
            }
        });
        return ShopAllView;
    }

    public void AddAdapter(DelegateAdapter delegateAdapter, List<DelegateAdapter.Adapter> adapters) {

    /*Adapter_linearLayout*/
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        linearLayoutHelper.setItemCount(listItem.size());
        linearLayoutHelper.setPadding(10, 10, 10, 10);
        linearLayoutHelper.setAspectRatio(2);
        linearLayoutHelper.setDividerHeight(10);
        linearLayoutHelper.setMarginBottom(50);

        linearLayoutHelper.setBgColor(getResources().getColor(R.color.colorBlue));
        Adapter_linearLayout = new SubAdapter(getContext(), linearLayoutHelper, listItem.size(), listItem, 0);
        adapters.add(Adapter_linearLayout);
    /*Adapter_GridLayout*/
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(2);
        gridLayoutHelper.setItemCount(listItem2.size());// 设置布局里Item个数
        gridLayoutHelper.setPadding(10, 10, 10, 10);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        gridLayoutHelper.setAspectRatio(3);// 设置设置布局内每行布局的宽与高的比
        gridLayoutHelper.setBgColor(getResources().getColor(R.color.colorWhite));// 设置背景颜色
        // gridLayoutHelper特有属性
        gridLayoutHelper.setWeights(new float[]{50, 50});//设置每行中 每个网格宽度 占 每行总宽度 的比例
        gridLayoutHelper.setVGap(20);// 控制子元素之间的垂直间距
        gridLayoutHelper.setHGap(20);// 控制子元素之间的水平间距
        gridLayoutHelper.setAutoExpand(false);//是否自动填充空白区域
        gridLayoutHelper.setSpanCount(2);// 设置每行多少个网格
        //gridLayoutHelper2.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        // gridLayoutHelper2.setWeights(new float[]{50,50});//设置每行中 每个网格宽度 占 每行总宽度 的比例
        // 通过自定义SpanSizeLookup来控制某个Item的占网格个数
        Adapter_GridLayout = new SubAdapter(getContext(), gridLayoutHelper, listItem2.size(), listItem2, 1);
        adapters.add(Adapter_GridLayout);

        RangeGridLayoutHelper layoutHelper = new RangeGridLayoutHelper(1);
        layoutHelper.setBgColor(getResources().getColor(R.color.colorBlue));
        layoutHelper.setPadding(15, 15, 15, 15);
        layoutHelper.setMargin(15, 15, 15, 15);
        layoutHelper.setHGap(10);
        layoutHelper.setVGap(10);
        layoutHelper.setAspectRatio(5);
        RangeGridLayoutHelper.GridRangeStyle rangeStyle = new RangeGridLayoutHelper.GridRangeStyle();
        //rangeStyle.setBgColor(Color.WHITE);
        rangeStyle.setSpanCount(2);
        rangeStyle.setWeights(new float[]{46.665f});
        //rangeStyle.setPadding(15, 15, 15, 15);
        rangeStyle.setMargin(15, 15, 15, 15);
        rangeStyle.setHGap(5);
        rangeStyle.setVGap(5);
        rangeStyle.setAspectRatio((float) 1.5);
        layoutHelper.addRangeStyle(1, listItem30.size() - 1, rangeStyle);
        adapters.add(new SubAdapter(getContext(), layoutHelper, listItem30.size(), listItem30, 3));

        if (listItem31.size() > 1) {
            GridLayoutHelper gridLayoutHelper3 = new GridLayoutHelper(2);
            gridLayoutHelper3.setItemCount(listItem31.size());
            gridLayoutHelper3.setPadding(10, 10, 10, 10);
            gridLayoutHelper3.setAspectRatio(2);
            gridLayoutHelper.setAutoExpand(false);//是否自动填充空白区域
            gridLayoutHelper3.setBgColor(getResources().getColor(R.color.colorWhite));
            Adapter_GridLayout_1 = new SubAdapter(getContext(), gridLayoutHelper3, this.listItem31.size(), this.listItem31, 3);
            adapters.add(Adapter_GridLayout_1);
        }

        if (listItem32.size() > 1) {
            GridLayoutHelper gridLayoutHelper4 = new GridLayoutHelper(2);
            gridLayoutHelper4.setItemCount(listItem32.size());
            gridLayoutHelper4.setPadding(10, 10, 10, 10);
            gridLayoutHelper4.setAspectRatio(2);
            gridLayoutHelper.setAutoExpand(false);//是否自动填充空白区域
            gridLayoutHelper4.setBgColor(getResources().getColor(R.color.colorWhite));
            Adapter_GridLayout_2 = new SubAdapter(getContext(), gridLayoutHelper4, this.listItem32.size(), this.listItem32, 3);
            adapters.add(Adapter_GridLayout_2);
        }


        delegateAdapter.addAdapters(adapters);
        moreRecycleview.setAdapter(delegateAdapter);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
