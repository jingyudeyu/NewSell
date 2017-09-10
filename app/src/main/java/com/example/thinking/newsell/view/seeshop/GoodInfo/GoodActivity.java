package com.example.thinking.newsell.view.seeshop.GoodInfo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.Assess;
import com.example.thinking.newsell.bean.Commodity;
import com.example.thinking.newsell.bean.Partner;
import com.example.thinking.newsell.bean.Shop;
import com.example.thinking.newsell.bean.User;
import com.example.thinking.newsell.commen.Commen;
import com.example.thinking.newsell.utils.system.SpUtils;
import com.example.thinking.newsell.view.chat.ChactActivity;
import com.example.thinking.newsell.view.chat.ChatActivity;
import com.example.thinking.newsell.view.seeshop.GoodInfo.Ask.AskActivity;
import com.example.thinking.newsell.view.seeshop.GoodInfo.AssessInfo.AssessActivity;
import com.example.thinking.newsell.view.seeshop.GoodInfo.Attention.GoodAttentionActivity;
import com.example.thinking.newsell.view.seeshop.ShopActivity;
import com.example.thinking.newsell.view.views.StarRating;
import com.github.chrisbanes.photoview.PhotoView;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.baidu.location.d.j.s;
import static com.example.thinking.newsell.R.id.compare;
import static com.example.thinking.newsell.R.id.image_details_listview;
import static com.example.thinking.newsell.R.id.li_title;
import static com.example.thinking.newsell.R.id.tv_good_detail_cate;

/**
 * *****************************************
 * Created by thinking on 2017/3/20.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class GoodActivity extends Activity implements GradationScrollView.ScrollViewListener {
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.indicator)
    MaterialIndicator indicator;
    @BindView(R.id.relaativelayout_image)
    RelativeLayout relaativelayoutImage;

    @BindView(R.id.goodsprice0)
    TextView goodsprice0;
    @BindView(R.id.goodsprice2)
    TextView goodsprice2;

    @BindView(R.id.ll_offset)
    LinearLayout llOffset;
    @BindView(R.id.store1)
    RelativeLayout store1;
    @BindView(R.id.store_month_number_name)
    TextView storeMonthNumberName;
    @BindView(R.id.ll_good_detail_service)
    LinearLayout llGoodDetailService;
    @BindView(tv_good_detail_cate)
    TextView tvGoodDetailCate;
    @BindView(R.id.tuwen)
    TextView tuwen;

    @BindView(image_details_listview)
    NoScrollListView imageDetailsListview;

    @BindView(R.id.scrollview_good)
    GradationScrollView scrollviewGood;
    @BindView(R.id.turnback)
    ImageView turnback;
    @BindView(R.id.keep)
    ImageView keep;
    @BindView(R.id.textview_good)
    TextView textviewGood;
    @BindView(li_title)
    RelativeLayout liTitle;


    @BindView(R.id.cooperation)
    Button cooperation;//合作

    @BindView(R.id.onshelf)
    Button onshelf;//上下架
    @BindView(R.id.l1)
    LinearLayout l1;
    @BindView(R.id.view1)
    View view1;


    @BindView(R.id.store_logo)
    ImageView storeLogo;
    @BindView(R.id.store2)
    LinearLayout store2;


    private int imageHeight;

    @BindView(R.id.assess_btn)
    Button assess_btn;
    @BindView(R.id.goodsname)
    TextView goodsName;
    @BindView(R.id.goodsprice)
    TextView goodsPrice;
    @BindView(R.id.goodsalvo)
    TextView goodsalevo;
    @BindView(R.id.if_online_store)
    TextView if_online_store;
    @BindView(R.id.store_name)
    TextView store_name;
    @BindView(R.id.store_location)
    TextView store_location;
    @BindView(R.id.store_month_number)
    TextView store_month_number;
    @BindView(R.id.about_shop)
    RelativeLayout aboutShop;
    @BindView(R.id.user_ratingbar)
    StarRating userRatingbar;
    @BindView(R.id.user_name)
    TextView user_name;
    @BindView(R.id.user_time)
    TextView user_time;
    @BindView(R.id.user_assess)
    TextView user_assess;
    @BindView(R.id.assess_num)
    TextView assess_num;

    @BindView(R.id.compare)
    Button compare;
    @BindView(R.id.shop)
    Button shop;

    private Button wantGood;
    private LinearLayout l2;

    private RelativeLayout relativeLayout;
    private FloatingActionButton askButton;
    //问答动画是否执行
    private boolean isAnmi = true;

    private LinearLayout liAboutPartner;
    private TextView liPartnerNum;
    private TextView liIntentionNum;
    private LinearLayout assessLi;

    /*对话框*/
    Dialog parameterDialog;
    ListView dialog_listview;
    final String[] parameter_name = new String[]{"包装尺寸 ",
            "品牌", "屏幕尺寸", "分辨率", "背光灯类型",
            "类型", "商品名称", "整机功率", "对比度", "互联网电视", "重量", "操作系统", "USB支持视频格式"};
    final String[] parameter = new String[]{"1335*829*196MM",
            "Sony/索尼", "55INC",
            "3840*2160", "LED", "LED液晶电视机", "KD-55X6000D", "195W", "500:1", "是", "16.8KG(不含底座)", "Linux", "MPEG1/MPEG2PS/MPEG2TS/AVCHD/MP4Part10/MP4Part2/AVI/MOV/WMV/MKV/RMVB/WEBM/3GPP"};

    Commodity commodity;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.good_information);
        ButterKnife.bind(this);

        askButton = (FloatingActionButton) findViewById(R.id.good_info_ask_button);
        liAboutPartner = (LinearLayout) findViewById(R.id.li_about_partner);
        liPartnerNum = (TextView) findViewById(R.id.li_partner_num);
        liIntentionNum = (TextView) findViewById(R.id.li_intention_num);
        assessLi = (LinearLayout) findViewById(R.id.assess_li);

        viewPager.setFocusable(true);
        viewPager.setFocusableInTouchMode(true);
        viewPager.requestFocus();

        wantGood = (Button) findViewById(R.id.want_good);
        l2 = (LinearLayout) findViewById(R.id.l2);

        commodity = (Commodity) getIntent().getSerializableExtra("commodity");//商品信息
        //商品的名称、价格、销售量
        goodsName.setText(commodity.getProductname());
        goodsPrice.setText(commodity.getPrice() + "");
        goodsalevo.setText(commodity.getSalesvolu() + "人购买");
        textviewGood.setText(commodity.getProductname());//标题栏的商品名字

        User user = (User) SpUtils.getObject(this, Commen.USERINFO);
        if (commodity.getSid() == SpUtils.getInt(GoodActivity.this, Commen.SHOPSIDdefault)) {
            l1.setVisibility(View.VISIBLE);
            //  RelativeLayout.LayoutParams lp=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            //  lp.addRule(RelativeLayout.ABOVE,R.id.l1);
            //   relativeLayout.addView(askButton,lp);

            //商品的状态0上架1下架
            if (commodity.getStatue() == 0) {
                onshelf.setText(Commen.ONSHELF);
                onshelf.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            } else if (commodity.getStatue() == 1) {
                onshelf.setText(Commen.OFFSHELF);
                onshelf.setBackgroundColor(Color.GRAY);
            }

            //商品的合作状态0未合作1已合作
            if (commodity.getPartstatue() == 0) {
                cooperation.setText(getResources().getString(R.string.part_statue0));
                liAboutPartner.setVisibility(View.GONE);
            } else if (commodity.getPartstatue() == 1) {
                cooperation.setText(getResources().getString(R.string.part_statue1));
                liAboutPartner.setVisibility(View.VISIBLE);//linearlayout关于合作的

                NetWorks.getPartnerGood(commodity.getCid(), new BaseObserver<Partner>() {
                    @Override
                    public void onHandleSuccess(Partner partner) {

                        liPartnerNum.setText(getResources().getString(R.string.partner_num) + partner.getCount());//合作商家数量
                        liIntentionNum.setText(getResources().getString(R.string.intention_num) + partner.getIntentcount());//意向人数
                    }

                    @Override
                    public void onHandleError(int code, String message) {

                    }
                });
                cooperation.setTextColor(getResources().getColor(R.color.colorBlack));
                cooperation.setEnabled(false);
            }

            initlistener();
        } else {

            l2.setVisibility(View.VISIBLE);
            //商品的合作状态0未合作1已合作
            if (commodity.getPartstatue() == 0) {
                liAboutPartner.setVisibility(View.GONE);
            } else if (commodity.getPartstatue() == 1) {
                liAboutPartner.setVisibility(View.VISIBLE);//linearlayout关于合作的
                NetWorks.getPartnerGood(commodity.getCid(), new BaseObserver<Partner>() {
                    @Override
                    public void onHandleSuccess(Partner partner) {
                        liPartnerNum.setText(getResources().getString(R.string.partner_num) + partner.getCount());//合作商家数量
                        liIntentionNum.setText(getResources().getString(R.string.intention_num) + partner.getIntentcount());//意向人数
                    }

                    @Override
                    public void onHandleError(int code, String message) {

                    }
                });
            }
            otherLitener();

        }

        // 店铺信息的展示
        initShopInfo(commodity);

        /**问答的按钮监听*/
        askButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GoodActivity.this, AskActivity.class);
                intent.putExtra("commodity", commodity);
                startActivity(intent);
            }
        });

        /*查看所有评价*/
        assess_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GoodActivity.this, AssessActivity.class);
                Bundle bundle = new Bundle();
                bundle.getInt(Commen.SHOPSID,commodity.getSid());
                bundle.putString("Cid", String.valueOf(commodity.getCid()));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        //产品参数
        tvGoodDetailCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog();
                parameterDialog.show();
            }
        });

        /**
         *  商品详情listview
         */
        final String[] a = commodity.getDetailshow().split(";");
        imageDetailsListview.setAdapter(new ImageListAdapter(GoodActivity.this, a));
        MaterialIndicator indicator = (MaterialIndicator) findViewById(R.id.indicator);
        viewPager.setAdapter(new MyPagerAdapter());
        viewPager.addOnPageChangeListener(indicator);

        indicator.setAdapter(viewPager.getAdapter());
        initListeners();
        oncreateListener();
    }

    //店铺信息的展示
    private void initShopInfo(Commodity commodity) {
        final Commodity commodity_1 = commodity;

        NetWorks.getShopInfo(commodity.getSid(), new BaseObserver<List<Shop>>() {
            @Override
            public void onHandleSuccess(final List<Shop> shops) {
                Shop shop = shops.get(0);
                if (shop.getType().toString().contains(Commen.LINESHOP)) {
                    Glide.with(GoodActivity.this).load(shop.getLogo()).into(storeLogo);
                    if_online_store.setText(shop.getType());
                    store_name.setText(shop.getShopname());
                    store_location.setText(shop.getSaddress());
                    store_month_number.setText("暂无数据");
                } else if (shop.getType().toString().contains(Commen.ONLINESHOP)) {
                    store2.setVisibility(View.GONE);
                    Glide.with(GoodActivity.this).load(shop.getLogo()).into(storeLogo);
                    if_online_store.setText(shop.getType());
                    store_name.setText(shop.getShopname());
                }

                /*获取评价*/
                NetWorks.getNewAssess(commodity_1.getCid(), new BaseObserver<Assess>() {

                    @Override
                    public void onHandleSuccess(Assess assess) {
                        if (assess != null) {
                            Log.v("评价：", assess.getUsername());
                            user_name.setText(newName(assess.getUsername()));
                            user_time.setText(assess.getDate().substring(0, 10));
                            user_assess.setText(assess.getDetail());
                            userRatingbar.setCurrentStarCount((int) assess.getGrade());

                            //获取评价的总条数
                            NetWorks.getAssessCount(commodity_1.getCid(), new BaseObserver<Integer>() {
                                @Override
                                public void onHandleSuccess(Integer integer) {
                                    assess_num.setText("全部评价(" + integer + ")");
                                }

                                @Override
                                public void onHandleError(int code, String message) {

                                }
                            });

                        }
                    }

                    @Override
                    public void onHandleError(int code, String message) {
                        if (code == -1 && message == null) {
                            assessLi.setVisibility(View.GONE);
                            assess_num.setText("暂无评价");
                        }
                        Log.v("评价：", "shibai");
                    }
                });
            }

            @Override
            public void onHandleError(int code, String message) {

            }
        });

    }

    private void createDialog() {
        //对话框 参数
        parameterDialog = new Dialog(this, R.style.map_dialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.layout_parameter_dialog, null);
        root.findViewById(R.id.dialog_button).setOnClickListener(dialoglistener);
        dialog_listview = (ListView) root.findViewById(R.id.dialog_listview);
        List<Map<String, Object>> listItem1 = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < parameter_name.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("ParameterName", parameter_name[i]);
            map.put("Parameter", parameter[i]);
            listItem1.add(map);
        }
        // 声明适配器，并将其绑定到ListView控件
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItem1,
                R.layout.dialog_listview_item, new String[]{"ParameterName", "Parameter"}, new int[]{R.id.parameeter_name, R.id.parameeter});
        dialog_listview.setAdapter(simpleAdapter);
        parameterDialog.setCanceledOnTouchOutside(true);//点击外部使对话框消失
        parameterDialog.setContentView(root);
        Window dialogWindow = parameterDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = -20; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();
        dialogWindow.setAttributes(lp);

    }

    //对话框的点击事件
    private View.OnClickListener dialoglistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.dialog_button:
                    parameterDialog.dismiss();
                    break;
                default:
                    break;
            }
        }
    };


    //非店主查看商品信息时、返回键、比较键、店铺键
    private void otherLitener() {
        final Commodity commodity2 = (Commodity) getIntent().getSerializableExtra("commodity");
        //比较键
        compare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Intent intent2 = new Intent(GoodActivity.this, CompareActivity.class);
                // startActivity(intent2);
            }
        });

        //店铺键
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Commodity commodity = (Commodity) getIntent().getSerializableExtra("commodity");

                //通过商品sid去查看店铺
                NetWorks.getShopInfoById(commodity.getSid(), new BaseObserver<Shop>() {
                    @Override
                    public void onHandleSuccess(Shop shop) {
                        //  headHolder.headShopname.setText(shop.getShopname());
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(Commen.SHOPINFO, shop);
                        Intent intent = new Intent(GoodActivity.this, ShopActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }

                    @Override
                    public void onHandleError(int code, String message) {
                        Toast.makeText(GoodActivity.this, code + message, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        wantGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Commodity commodity = (Commodity) getIntent().getSerializableExtra("commodity");
                NetWorks.getUserInfoBySiD(commodity.getSid(), new BaseObserver<User>() {
                    @Override
                    public void onHandleSuccess(User user) {
                        Intent chat = new Intent(GoodActivity.this, ChatActivity.class);
                        chat.putExtra("commodity", commodity);
                        chat.putExtra("you", true);
                        chat.putExtra(EaseConstant.EXTRA_USER_ID, user.getPhone());  //对方账号
                        chat.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EMMessage.ChatType.Chat); //单聊模式
                        startActivity(chat);

                    }

                    @Override
                    public void onHandleError(int code, String message) {

                    }
                });

            }
        });

    }

    /**
     * 求合作、上下架、看关注的点击事件
     */
    private void initlistener() {

        final Commodity commodity2 = (Commodity) getIntent().getSerializableExtra("commodity");
        //上下架的点击事件
        onshelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onshelf.getText().toString().equals(Commen.ONSHELF)) {
                    final AlertDialog.Builder normalDialog = new AlertDialog.Builder(GoodActivity.this);
                    normalDialog.setMessage("要把我下架吗?");
                    normalDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            NetWorks.putStatueGood(commodity2.getCid(), 1, new BaseObserver<Commodity>() {
                                @Override
                                public void onHandleSuccess(Commodity commodity) {
                                    onshelf.setText(Commen.OFFSHELF);
                                    onshelf.setBackgroundColor(Color.GRAY);
                                }

                                @Override
                                public void onHandleError(int code, String message) {
                                    Toast.makeText(GoodActivity.this, "错了", Toast.LENGTH_SHORT).show();
                                }
                            });


                        }
                    });
                    normalDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    normalDialog.show();

                } else if (onshelf.getText().toString().equals(Commen.OFFSHELF)) {
                    final AlertDialog.Builder normalDialog = new AlertDialog.Builder(GoodActivity.this);
                    normalDialog.setMessage("要把我上架吗?");
                    normalDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            NetWorks.putStatueGood(commodity2.getCid(), 0, new BaseObserver<Commodity>() {
                                @Override
                                public void onHandleSuccess(Commodity commodity) {
                                    onshelf.setText(Commen.ONSHELF);
                                    onshelf.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                                }

                                @Override
                                public void onHandleError(int code, String message) {
                                    Toast.makeText(GoodActivity.this, "错了", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    });
                    normalDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    normalDialog.show();
                }
            }
        });

        //商品的合作状态0未合作1已合作的点击事件
        cooperation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cooperation.getText().toString().equals(getResources().getString(R.string.part_statue0))) {
                    final AlertDialog.Builder cooperationDialog = new AlertDialog.Builder(GoodActivity.this);
                    cooperationDialog.setTitle("请输入合作理由");
                    View viewEdittext = LayoutInflater.from(GoodActivity.this).inflate(R.layout.dialog_edit_text, null);

                    cooperationDialog.setView(viewEdittext);
                    final EditText editText = (EditText) viewEdittext.findViewById(R.id.edit_text);
                    //限制edittext的最大可输入字符数
                    InputFilter[] inputFilters = {new InputFilter.LengthFilter(50)};
                    editText.setFilters(inputFilters);
                    final FrameLayout frame = (FrameLayout) viewEdittext.findViewById(R.id.frame);
                    frame.setVisibility(View.GONE);
                    editText.setHint("");

                    cooperationDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(final DialogInterface dialog, int which) {
                            if (!TextUtils.isEmpty(editText.getText().toString())) {
                                Map<String, Object> partnerMap = new HashMap<String, Object>();
                                partnerMap.put("cid", commodity2.getCid());
                                partnerMap.put("cgid", commodity2.getCgid());
                                partnerMap.put("reason", editText.getText().toString());
                                partnerMap.put("count", 0);
                                partnerMap.put("star", 0);
                                partnerMap.put("intentcount", 0);
                                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                String newdate = df.format(new Date());
                                partnerMap.put("pubtime", newdate);
                                User user = (User) SpUtils.getObject(GoodActivity.this, Commen.USERINFO);
                                partnerMap.put("sid", user.getSid());
                                partnerMap.put("ctid", SpUtils.getInt(GoodActivity.this, Commen.SHOPCITYID));
                                NetWorks.postAddGood(partnerMap, new BaseObserver<Partner>() {
                                    @Override
                                    public void onHandleSuccess(Partner partner) {
                                        Toast.makeText(GoodActivity.this, "商品已添加到合作商品中", Toast.LENGTH_SHORT).show();
                                        cooperation.setText(getResources().getString(R.string.part_statue1));
                                        cooperation.setTextColor(getResources().getColor(R.color.colorBlack));
                                        cooperation.setEnabled(false);
                                        dialog.dismiss();

                                    }

                                    @Override
                                    public void onHandleError(int code, String message) {

                                    }
                                });
                            } else {
                                Toast.makeText(GoodActivity.this, "请输入合作理由", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                    cooperationDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            editText.setText("");
                            dialog.dismiss();
                        }
                    });

                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                    cooperationDialog.show();
                }
            }
        });

        //跳转查看关注商品的用户
        keep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("commodity", commodity);
                bundle.putInt(Commen.ATTENTIONTYPE, 0);
                Intent intent = new Intent(GoodActivity.this, GoodAttentionActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void oncreateListener() {
        //返回键
        turnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        /**
         * 图片展示列表的每一项点击监听，用于展示大图
         */
        imageDetailsListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView imageView = (ImageView) view;
                final Dialog dialog = new Dialog(GoodActivity.this, R.style.map_dialog);
                RelativeLayout root = (RelativeLayout) LayoutInflater.from(GoodActivity.this).inflate(R.layout.assess_dialog, null);
                PhotoView dialog_image = (PhotoView) root.findViewById(R.id.dialog_image);
                dialog_image.setImageDrawable(imageView.getDrawable());
                dialog.setContentView(root);
                dialog.show();
                //取消按钮监听
                root.findViewById(R.id.re_diaog).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    /**
     * viewpager适配器头部商品图片的展示
     */
    private class MyPagerAdapter extends PagerAdapter {
        Commodity commodity = (Commodity) getIntent().getSerializableExtra("commodity");
        String[] a = commodity.getDetailshow().split(";");
        String[] only4 = Arrays.copyOfRange(a, 0, 4);

        @Override
        public int getCount() {
            return only4.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return object == view;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            Glide.with(GoodActivity.this).load(only4[position]).into(view);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(((View) object));
        }
    }

    /**
     * 获取顶部图片高度后，设置滚动监听
     */
    private void initListeners() {
        //设置滚动监听
        ViewTreeObserver vto = viewPager.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                liTitle.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                imageHeight = viewPager.getHeight();
                scrollviewGood.setScrollViewListener(GoodActivity.this);
            }
        });
    }

    /**
     * 滑动监听,设置标题栏
     *
     * @param scrollView
     * @param x
     * @param y
     * @param oldx
     * @param oldy
     */
    public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy) {
        // TODO Auto-generated method stub
        if (y <= 0) {   //设置标题的背景颜色
            liTitle.setBackgroundColor(Color.argb((int) 0, 144, 151, 166));
        } else if (y > 0 && y <= imageHeight) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) y / imageHeight;
            float alpha = (255 * scale);
            textviewGood.setTextColor(Color.argb((int) alpha, 255, 255, 255));
            liTitle.setBackgroundColor(Color.argb((int) alpha, 144, 151, 166));
        } else {    //滑动到banner下面设置普通颜色

            askButton.setVisibility(View.VISIBLE);
            //如果刚拉入下面执行动画
            if (isAnmi) {
                ObjectAnimator scaleX = ObjectAnimator.ofFloat(askButton, "scaleX", 0.7f, 1f);
                ObjectAnimator scaleY = ObjectAnimator.ofFloat(askButton, "scaleY", 0.7f, 1f);
                scaleX.setRepeatMode(ValueAnimator.REVERSE);
                scaleX.setRepeatCount(0);
                scaleY.setRepeatMode(ValueAnimator.REVERSE);
                scaleY.setRepeatCount(0);
                scaleX.setInterpolator(new OvershootInterpolator());
                scaleX.setDuration(700);
                scaleY.setDuration(500);
                //set把两个动画加进来一起执行
                AnimatorSet set = new AnimatorSet();
                set.playTogether(scaleX, scaleY);
                set.start();
                isAnmi = false;
            }

            liTitle.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
            textviewGood.setTextColor(Color.argb((int) 255, 0, 0, 0));
        }
    }

    /**
     * listview 图片ImageListAdapter
     */
    class ImageListAdapter extends ArrayAdapter {
        private Context context;
        private LayoutInflater inflater;
        private String[] imageUrls;

        public ImageListAdapter(Context context, String[] imageUrls) {
            super(context, R.layout.good_listview_item, imageUrls);
            this.context = context;
            this.imageUrls = imageUrls;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (null == convertView) {
                convertView = inflater.inflate(R.layout.good_listview_item, parent, false);
            }
            final ImageView imageView = (ImageView) convertView;
            //先使用 Glide 把图片的原图请求加载过来，然后再按原图来显示图片
            Glide.with(context)
                    .load(imageUrls[position])
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            imageView.setImageBitmap(resource);
                        }
                    });

            return convertView;
        }

    }


    //名字的 某**某
    private String newName(String username) {
        String newname;
        int num = username.length();
        newname = username.charAt(0) + "**" + username.charAt(num - 2) + username.charAt(num - 1);
        return newname;
    }
}

