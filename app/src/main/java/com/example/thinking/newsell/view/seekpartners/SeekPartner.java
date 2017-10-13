package com.example.thinking.newsell.view.seekpartners;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.Category;
import com.example.thinking.newsell.bean.City;
import com.example.thinking.newsell.bean.Partner;
import com.example.thinking.newsell.bean.Province;
import com.example.thinking.newsell.commen.Commen;
import com.example.thinking.newsell.utils.system.SpUtils;

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
    ImageView partnerGoback;//返回键
    @BindView(R.id.partner_main_toolbar)
    Toolbar partnerMainToolbar;//标题栏
    @BindView(R.id.partner_search)
    TextView partnerSearch;//标题栏跳转至搜索页


    @BindView(R.id.partner_recyler)
    RecyclerView partnerRecyler;//合作商品列表展示
    //  GloriousRecyclerView partnerRecyler;
    //

    //关于4个筛选按钮的
    @BindView(R.id.li_btn4)
    LinearLayout liBtn4;

    @BindView(R.id.change_city_btn)
    Button changeCityBtn;//选择城市按钮
    @BindView(R.id.change_category_btn)
    Button changeCategoryBtn;//选择分类按钮


    //三个关于分类的
    @BindView(R.id.recycer_small)
    RecyclerView recycerSmall;//显示中类
    @BindView(R.id.recycer_secend)
    RecyclerView recycerSecend;//显示小类
    @BindView(R.id.re_about_cate)
    RelativeLayout reAboutCate;//分类的布局

    @BindView(R.id.layout_swipe_refresh)
    SwipeRefreshLayout layoutSwipeRefresh;//下拉刷新

    private List<Category> categoryList1 = new ArrayList<>();
    private List<Category> categoryList2 = new ArrayList<>();
    private CategoryAdapter categoryAdapter;
    private CategoryAdapter cateSecendAdapter;

    //关于城市的选择
    @BindView(R.id.re_about_city)
    RelativeLayout reAboutCity;//关于城市的布局
    @BindView(R.id.listview_province)
    ListView listviewProvince;//省的列表
    @BindView(R.id.gridview_city)
    GridView gridviewCity;//城市的gridview
    private List<Province> provincelist = new ArrayList<>();
    private ProvinceAdapter provinceAdapter;
    private List<City> citiylist = new ArrayList<>();
    private CityAdapter cityAdapter;

    //下面那块半透明的
    @BindView(R.id.down_view_black)
    View downViewBlack;
    @BindView(R.id.down_view_black2)
    View downViewBlack2;

    //关于几种不同的分类
    private int seachType;
    private int NameType = 1;
    List<Integer> catelist = new ArrayList<Integer>();//关于类别的
    private int NameCityType = 2;
    private int NameCateType = 3;
    private int NameCityCateType = 4;

    private int page = 1;

    //关于合作商品列表展示的
    private List<Partner> partnerList = new ArrayList<>();
    private GoodPartnerAdapter goodpartnerAdapter;
    private LinearLayoutManager linearLayoutManager;

    String searchcontent = null;//搜索商品名称
    private int first = 0;//是否是第一次点击citybutton，第一次点击时，加载一次城市列表
    int lastVisibleItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.partner_result);
        ButterKnife.bind(this);

        final Intent intent = getIntent();
        searchcontent = intent.getStringExtra(Commen.SEARCHCONTENT);
        partnerSearch.setText(searchcontent);
        /*change.setText("change");
        filter.setText("选择分类");*/
        SpUtils.putInt(SeekPartner.this, Commen.SEARCHCITYID, 0);
        SpUtils.putInt(SeekPartner.this, Commen.SEARCHCATEID, 0);
        linearLayoutManager = new LinearLayoutManager(SeekPartner.this);
        partnerRecyler.setLayoutManager(linearLayoutManager);
        //partnerRecyler的滑动监听，判断滑动停止时，是否是最后一个item，是则加载下一页
        layoutSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                ManyInquire(seachType);
                goodpartnerAdapter.notifyDataSetChanged();
                layoutSwipeRefresh.setRefreshing(false);
            }
        });

        partnerRecyler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == goodpartnerAdapter.getItemCount()) {
                    page = page + 1;
                    Log.v("页数", String.valueOf(page));
                    ManyInquire(seachType);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }
        });


        seachType = NameType;
        ManyInquire(seachType);

        downViewBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reAboutCity.setVisibility(View.GONE);
                downViewBlack.setVisibility(View.GONE);
            }
        });
        downViewBlack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reAboutCate.setVisibility(View.GONE);
                downViewBlack2.setVisibility(View.GONE);
            }
        });

        //关于城市选择按钮
        changeCityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (reAboutCity.getVisibility() == View.GONE) {
                    reAboutCate.setVisibility(View.GONE);
                    downViewBlack2.setVisibility(View.GONE);
                    AboutCity();
                } else {
                    reAboutCity.setVisibility(View.GONE);
                    downViewBlack.setVisibility(View.GONE);
                }
            }
        });

        //关于类别选择按钮
        changeCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (reAboutCate.getVisibility() == View.GONE) {
                    reAboutCity.setVisibility(View.GONE);
                    downViewBlack.setVisibility(View.GONE);
                    AboutCategory();
                } else {
                    reAboutCate.setVisibility(View.GONE);
                    downViewBlack2.setVisibility(View.GONE);
                }

            }
        });

        partnerSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        partnerGoback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    private void AboutCity() {
        reAboutCity.setVisibility(View.VISIBLE);
        downViewBlack.setVisibility(View.VISIBLE);
        NetWorks.getAllProvince(new BaseObserver<List<Province>>() {
            @Override
            public void onHandleSuccess(List<Province> provinces) {
                provincelist = provinces;
                provinceAdapter = new ProvinceAdapter(SeekPartner.this, provincelist);
                listviewProvince.setAdapter(provinceAdapter);
            }

            @Override
            public void onHandleError(int code, String message) {
            }
        });
        if (first == 0) {
            NetWorks.getProvinceCitys(8, new BaseObserver<List<City>>() {
                @Override
                public void onHandleSuccess(List<City> cities) {
                    citiylist = cities;
                    cityAdapter = new CityAdapter(SeekPartner.this, citiylist);
                    gridviewCity.setAdapter(cityAdapter);
                    first = 1;
                    gridviewCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            changeCityBtn.setText(citiylist.get(position).getCityname());
                            reAboutCity.setVisibility(View.GONE);
                            SpUtils.putInt(SeekPartner.this, Commen.SEARCHCITYID, citiylist.get(position).getCid());

                            if (SpUtils.getInt(SeekPartner.this, Commen.SEARCHCATEID) == 0) {
                                seachType = NameCityType;
                                page = 1;
                                ManyInquire(seachType);
                            } else {
                                seachType = NameCityCateType;
                                page = 1;
                                ManyInquire(seachType);
                            }
                        }
                    });

                }

                @Override
                public void onHandleError(int code, String message) {

                }
            });
        }

        //省的列表的点击事件 点击省时刷新对应的城市列表
        listviewProvince.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                citiylist.clear();//城市列表情空
                //根据省的pid查询城市信息
                NetWorks.getProvinceCitys(provincelist.get(position).getPid(), new BaseObserver<List<City>>() {
                    @Override
                    public void onHandleSuccess(List<City> cities) {
                        citiylist = cities;
                        cityAdapter.notifyDataSetChanged();
                        cityAdapter = new CityAdapter(SeekPartner.this, citiylist);
                        gridviewCity.setAdapter(cityAdapter);
                        //城市gridview的点击事件
                        gridviewCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                changeCityBtn.setText(citiylist.get(position).getCityname());
                                reAboutCity.setVisibility(View.GONE);
                                downViewBlack.setVisibility(View.GONE);
                                SpUtils.putInt(SeekPartner.this, Commen.SEARCHCITYID, citiylist.get(position).getCid());

                                if (SpUtils.getInt(SeekPartner.this, Commen.SEARCHCATEID) == 0) {//当分类id不为0时

                                    seachType = NameCityType;//选择商品名称+ 城市id查询
                                    page = 1;
                                    ManyInquire(seachType);

                                } else {
                                    seachType = NameCityCateType;//选择商品名称+ 城市id + 分类id查询
                                    page = 1;
                                    ManyInquire(seachType);
                                }
                            }
                        });
                    }

                    @Override
                    public void onHandleError(int code, String message) {

                    }
                });
            }
        });


    }


    /**
     * 按照类别进行查询
     * 2、按照商品名称模糊查询
     * <p>
     * 5、城市+ 商品名称查询
     * 6、分类+ 商品名称查询
     * 8、城市+ 分类 + 商品名称查询
     */
    public void ManyInquire(int seachType) {
        switch (seachType) {
            case 1:
                //按照搜索商品名称搜索
                NetWorks.get3Partners(searchcontent, 0, 0, page, new BaseObserver<List<Partner>>() {
                    @Override
                    public void onHandleSuccess(List<Partner> partnerList2) {
                        if (page == 1) {
                            partnerList = partnerList2;
                            goodpartnerAdapter = new GoodPartnerAdapter(partnerList, SeekPartner.this);
                            partnerRecyler.setAdapter(goodpartnerAdapter);
                        } else if (page > 1) {
                            partnerList.addAll(partnerList2);
                            goodpartnerAdapter.notifyDataSetChanged();
                        }

                    }

                    @Override
                    public void onHandleError(int code, String message) {
                        Log.v("错误", code + "  " + message);
                    }
                });
                break;
            case 2:
                //按照搜索商品名称 + 城市id搜索
                NetWorks.get3Partners(searchcontent, SpUtils.getInt(SeekPartner.this, Commen.SEARCHCITYID), 0, page, new BaseObserver<List<Partner>>() {
                    @Override
                    public void onHandleSuccess(List<Partner> partnerList5) {
                        if (page == 1) {
                            partnerList.clear();
                            partnerList = partnerList5;
                            Log.v("搜索商品名称 + 城市id", String.valueOf(partnerList.size()));
                            //  goodpartnerAdapter.notifyDataSetChanged();
                            goodpartnerAdapter = new GoodPartnerAdapter(partnerList, SeekPartner.this);
                            partnerRecyler.setAdapter(goodpartnerAdapter);

                        } else if (page > 1) {
                            if (partnerList5.size() != 0) {
                                Log.v("partnerList5的大小",partnerList5.size()+partnerList5.toString());
                                partnerList.addAll(partnerList5);
                                Log.v("page2搜索商品名称 + 城市id", String.valueOf(partnerList.size()));
                                goodpartnerAdapter.notifyDataSetChanged();
                            }else
                                Log.v("page2搜索商品名称 + 城市id", String.valueOf(partnerList.size()));
                        }
                    }

                    @Override
                    public void onHandleError(int code, String message) {
                        Log.v("错误", code + "  " + message);
                    }
                });

                break;
            case 3:
                //按照搜索商品名称 + 分类id搜索
                NetWorks.get3Partners(searchcontent, 0, SpUtils.getInt(SeekPartner.this, Commen.SEARCHCATEID), page, new BaseObserver<List<Partner>>() {
                    @Override
                    public void onHandleSuccess(List<Partner> partnerList6) {

                        if (page == 1) {
                            partnerList.clear();
                            partnerList = partnerList6;
                            goodpartnerAdapter.notifyDataSetChanged();
                            goodpartnerAdapter = new GoodPartnerAdapter(partnerList, SeekPartner.this);
                            partnerRecyler.setAdapter(goodpartnerAdapter);

                        } else if (page > 1) {
                            if (partnerList6.size() != 0) {
                                partnerList.addAll(partnerList6);
                                goodpartnerAdapter.notifyDataSetChanged();
                            }

                        }
                    }

                    @Override
                    public void onHandleError(int code, String message) {
                        Log.v("错误", code + "  " + message);
                    }
                });
                break;
            case 4:
                //按照搜索商品名称 + +城市id + 分类id搜索
                NetWorks.get3Partners(searchcontent, SpUtils.getInt(SeekPartner.this, Commen.SEARCHCITYID), SpUtils.getInt(SeekPartner.this, Commen.SEARCHCATEID), page, new BaseObserver<List<Partner>>() {
                    @Override
                    public void onHandleSuccess(List<Partner> partnerList8) {

                        if (page == 1) {
                            partnerList.clear();
                            partnerList = partnerList8;
                            goodpartnerAdapter.notifyDataSetChanged();
                            goodpartnerAdapter = new GoodPartnerAdapter(partnerList, SeekPartner.this);
                            partnerRecyler.setAdapter(goodpartnerAdapter);

                        } else if (page > 1) {

                            partnerList.addAll(partnerList8);
                            goodpartnerAdapter.notifyDataSetChanged();
                        }

                    }

                    @Override
                    public void onHandleError(int code, String message) {
                        Log.v("错误", code + "  " + message);
                    }
                });
                break;
            default:
                break;
        }

    }

    /*关于类别*/
    public void AboutCategory() {
        reAboutCate.setVisibility(View.VISIBLE);
        downViewBlack2.setVisibility(View.VISIBLE);
        //根据搜索商品名称查询类别
        NetWorks.getNameCategory(searchcontent, new BaseObserver<List<Category>>() {
            @Override
            public void onHandleSuccess(List<Category> categories) {
                //根据Category中的small属性去重
                //中类别
                categoryList1.clear();
                for (int i = 0; i < categories.size(); i++) {
                    if (i == 0) {
                        categoryList1.add(categories.get(i));
                    } else {
                        for (int n = 0; n < categoryList1.size(); n++) {
                            if (!categories.get(i).getSmall().equals(categoryList1.get(n).getSmall())) {
                                categoryList1.add(categories.get(i));
                            }
                        }
                    }
                }
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SeekPartner.this);
                recycerSmall.setLayoutManager(linearLayoutManager);
                categoryAdapter = new CategoryAdapter(SeekPartner.this, categoryList1, 1);
                recycerSmall.setAdapter(categoryAdapter);
                Log.v("中类", String.valueOf(categories.size()));
                //小类别
                categoryList2.clear();
                categoryList2 = categories;
                GridLayoutManager gridLayoutManager = new GridLayoutManager(SeekPartner.this, 2);
                recycerSecend.setLayoutManager(gridLayoutManager);
                cateSecendAdapter = new CategoryAdapter(SeekPartner.this, categoryList2, 2);
                recycerSecend.setAdapter(cateSecendAdapter);
            }

            @Override
            public void onHandleError(int code, String message) {
                Toast.makeText(SeekPartner.this, "类别失败", Toast.LENGTH_SHORT).show();
            }
        });
    }


    //关于分类的adapter
    public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private Context mContext;
        private List<Category> categories;
        private LayoutInflater layoutInflater;
        private int type;

        public CategoryAdapter(Context context, List<Category> categories, int type) {
            this.mContext = context;
            this.categories = categories;
            layoutInflater = LayoutInflater.from(mContext);
            this.type = type;
        }

        @Override
        public int getItemViewType(int position) {
            return type;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == 1) {//相当于中类
                View view = LayoutInflater.from(mContext).inflate(R.layout.category_item, parent, false);
                CategoryHolder holder = new CategoryHolder(view);
                return holder;
            } else {//相当于小类
                View view = LayoutInflater.from(mContext).inflate(R.layout.province_item, parent, false);
                ProvinceHolder holder = new ProvinceHolder(view);
                return holder;
            }
        }

        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            if (holder instanceof CategoryHolder) {
                ((CategoryHolder) holder).categoryName.setText(categories.get(position).getSmall());
                ((CategoryHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //中类的点击事件，刷新小类
                        NetWorks.getsmallCategory(categories.get(position).getSmall(), new BaseObserver<List<Category>>() {
                            @Override
                            public void onHandleSuccess(List<Category> categories) {
                                categoryList2.clear();
                                categoryList2 = categories;
                                GridLayoutManager gridLayoutManager = new GridLayoutManager(SeekPartner.this, 2);
                                recycerSecend.setLayoutManager(gridLayoutManager);
                                cateSecendAdapter = new CategoryAdapter(SeekPartner.this, categoryList2, 2);
                                recycerSecend.setAdapter(cateSecendAdapter);
                            }

                            @Override
                            public void onHandleError(int code, String message) {
                                Toast.makeText(SeekPartner.this, "刷新小类失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

            } else if (holder instanceof ProvinceHolder) {
                ((ProvinceHolder) holder).provinceName.setText(categories.get(position).getSecend());
                ((ProvinceHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //小类的点击事件，刷新数据
                        changeCategoryBtn.setText(categories.get(position).getSecend());
                        reAboutCate.setVisibility(View.GONE);
                        downViewBlack2.setVisibility(View.GONE);
                        SpUtils.putInt(SeekPartner.this, Commen.SEARCHCATEID, categories.get(position).getCgid());
                        if (SpUtils.getInt(SeekPartner.this, Commen.SEARCHCITYID) == 0) {
                            seachType = NameCateType;
                            page = 1;
                            ManyInquire(seachType);
                        } else {
                            seachType = NameCityCateType;
                            page = 1;
                            ManyInquire(seachType);
                        }
                    }
                });
            }
        }

        @Override
        public int getItemCount() {

            return categories.size();
        }

        public class CategoryHolder extends RecyclerView.ViewHolder {//中类
            private TextView categoryName;

            public CategoryHolder(View itemView) {
                super(itemView);
                categoryName = (TextView) itemView.findViewById(R.id.category_name);
            }
        }

        public class ProvinceHolder extends RecyclerView.ViewHolder {//小类
            private TextView provinceName;

            public ProvinceHolder(View itemView) {
                super(itemView);
                provinceName = (TextView) itemView.findViewById(R.id.province_name);
            }
        }

    }


}
