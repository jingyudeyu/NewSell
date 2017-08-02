package com.example.thinking.newsell.view.seekpartners;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thinking.newsell.MainActivity;
import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.Category;
import com.example.thinking.newsell.bean.City;
import com.example.thinking.newsell.bean.Partner;
import com.example.thinking.newsell.bean.Province;
import com.example.thinking.newsell.bean.User;
import com.example.thinking.newsell.commen.Commen;
import com.example.thinking.newsell.utils.system.SpUtils;
import com.example.thinking.newsell.view.seeshop.GoodInfo.AssessInfo.MyGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * *****************************************
 * Created by thinking on 2017/7/28.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class SeekPartner extends AppCompatActivity {
    @BindView(R.id.partner_goback)
    ImageView partnerBack;
    @BindView(R.id.partner_search)
    SearchView partnerSearch;

    @BindView(R.id.change)
    Button change;
    @BindView(R.id.filter)
    Button filter;

    @BindView(R.id.partner_recyler)
    RecyclerView partnerRecyler;


    /*popupwindow中的时间*/
    Button filterLocation;
    MyGridView filterLocationGrid;
    Button filterCategory;
    TextView changedProvince;
    TextView changedCity;
    TextView changedNewcity;
    MyGridView filterCategoryGrid;
    Button reset;
    Button complete;
    PopupWindow popupWindow;
    ArrayAdapter<String> arrayAdapter;
    ArrayAdapter<String> CateAdapter;
    ArrayList<String> citylist;
    private int page = 0;

    private List<Partner> partnerList = new ArrayList<>();
    private List<String> list;
    private GoodPartnerAdapter goodpartnerAdapter;

    private LinearLayoutManager linearLayoutManager;
    private int lastpoint;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.partner_main);
        ButterKnife.bind(this);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        final View view = layoutInflater.inflate(R.layout.filter_popup, null);
        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.MATCH_PARENT, false);

        /*合作商家返回*/
        partnerBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        change.setText("change");
        filter.setText("选择分类");
        linearLayoutManager = new LinearLayoutManager(SeekPartner.this);
        partnerRecyler.setLayoutManager(linearLayoutManager);


        getRawDate(152, page);

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showPopupWindow(view);
            }
        });

    }

    //查询所有合作商品
    private void getRawDate(int i, int page) {

        NetWorks.getCityParner(i, page, new BaseObserver<List<Partner>>() {
            @Override
            public void onHandleSuccess(List<Partner> partners) {
                partnerList = partners.subList(0, 3);
                goodpartnerAdapter = new GoodPartnerAdapter(partnerList, SeekPartner.this);
                partnerRecyler.setAdapter(goodpartnerAdapter);
            }

            @Override
            public void onHandleError(int code, String message) {
                Toast.makeText(SeekPartner.this, "youc", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showPopupWindow(View view) {

        filterLocation = (Button) view.findViewById(R.id.filter_location);
        changedProvince = (TextView) view.findViewById(R.id.changed_province);
        changedCity = (TextView) view.findViewById(R.id.changed_city);
        filterLocationGrid = (MyGridView) view.findViewById(R.id.filter_location_grid);
        filterCategory = (Button) view.findViewById(R.id.filter_category);
        filterCategoryGrid = (MyGridView) view.findViewById(R.id.filter_category_grid);
        reset = (Button) view.findViewById(R.id.reset);
        complete = (Button) view.findViewById(R.id.complete);
        popupWindow.showAtLocation(view, Gravity.RIGHT, 0, 0);
        final User user = (User) SpUtils.getObject(SeekPartner.this, Commen.USERINFO);
        filterLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (filterLocationGrid.getVisibility() == View.GONE) {

                    getFrist();

                } else if (filterLocationGrid.getVisibility() == View.VISIBLE) {
                    filterLocationGrid.setVisibility(View.GONE);
                }
            }
        });

        filterCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (filterCategoryGrid.getVisibility() == View.GONE) {
                    filterCategoryGrid.setVisibility(View.VISIBLE);

                    NetWorks.getAllCategory(new BaseObserver<List<Category>>() {
                        @Override
                        public void onHandleSuccess(List<Category> categories) {
                            List<String> bigCategory = new ArrayList<String>();
                            List<String> smallCategory = new ArrayList<String>();
                            List<String> secendCategory = new ArrayList<String>();
                            for (int i = 0; i < categories.size(); i++) {
                                bigCategory.add(categories.get(i).getBig());
                                smallCategory.add(categories.get(i).getSmall());
                                secendCategory.add(categories.get(i).getSecend());
                            }
                            CateAdapter = new ArrayAdapter<String>(SeekPartner.this, R.layout.province_item, R.id.province_name, bigCategory);
                            filterCategoryGrid.setAdapter(CateAdapter);
                        }

                        @Override
                        public void onHandleError(int code, String message) {

                        }
                    });

                } else if (filterCategoryGrid.getVisibility() == View.VISIBLE) {
                    filterCategoryGrid.setVisibility(View.GONE);
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changedProvince.setText("");
                changedProvince.setVisibility(View.GONE);
                changedCity.setText("");
                changedCity.setVisibility(View.GONE);
                citylist.clear();
            }
        });


        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // NetWorks.getCityParner();

                Toast.makeText(SeekPartner.this, changedProvince.getText().toString() + changedCity.getText().toString(), Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
        });

    }


    /*省的名字*/
    private ArrayList<String> getProvince(List<Province> provinces) {
        ArrayList<String> arrayProvince = new ArrayList<>();
        for (int i = 0; i < provinces.size(); i++) {
            arrayProvince.add(provinces.get(i).getProvince());
        }
        return arrayProvince;
    }

    public void getFrist() {
        NetWorks.getAllProvince(new BaseObserver<List<Province>>() {
            @Override
            public void onHandleSuccess(final List<Province> provinces) {


                filterLocationGrid.setVisibility(View.VISIBLE);
                final List<String> list = getProvince(provinces);
                arrayAdapter = new ArrayAdapter<String>(SeekPartner.this, R.layout.province_item, R.id.province_name, list);
                filterLocationGrid.setAdapter(arrayAdapter);
                filterLocationGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                        v.setBackgroundResource(R.drawable.shape_province_ed);
                        changedProvince.setText(provinces.get(position).getProvince());
                        changedProvince.setVisibility(View.VISIBLE);
                        list.clear();
                        arrayAdapter.notifyDataSetChanged();
                        changFrist(provinces.get(position).getPid());

                    }

                });
            }

            @Override
            public void onHandleError(int code, String message) {
                Toast.makeText(SeekPartner.this, "错了。。。", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void changFrist(int pid) {
        NetWorks.getProvinceCitys(pid, new BaseObserver<List<City>>() {
            @Override
            public void onHandleSuccess(final List<City> cities) {
                citylist = new ArrayList<>();
                for (int i = 0; i < cities.size(); i++) {
                    citylist.add(cities.get(i).getCityname());
                }

                arrayAdapter = new ArrayAdapter<String>(SeekPartner.this, R.layout.province_item, R.id.province_name, citylist);
                filterLocationGrid.setAdapter(arrayAdapter);

                filterLocationGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                        // v.setBackgroundResource(R.drawable.shape_province_ed);
                        changedProvince.setVisibility(View.VISIBLE);
                        //   filterLocationGrid.setVisibility(View.GONE);
                        changedCity.setText(cities.get(position).getCityname());
                        changedCity.setVisibility(View.VISIBLE);
                        NetWorks.getCityParner(cities.get(position).getCid(), 0, new BaseObserver<List<Partner>>() {
                            @Override
                            public void onHandleSuccess(List<Partner> partners) {
                                Log.v("数量", String.valueOf(partnerList.size()));
                            }

                            @Override
                            public void onHandleError(int code, String message) {

                            }
                        });


                    }
                });

            }

            @Override
            public void onHandleError(int code, String message) {

            }
        });

    }
}
