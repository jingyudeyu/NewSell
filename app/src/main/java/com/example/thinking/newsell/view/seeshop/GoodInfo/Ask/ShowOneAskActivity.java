package com.example.thinking.newsell.view.seeshop.GoodInfo.Ask;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.Commodity;
import com.example.thinking.newsell.bean.Quest;
import com.example.thinking.newsell.bean.Shop;
import com.example.thinking.newsell.bean.User;
import com.example.thinking.newsell.commen.Commen;
import com.example.thinking.newsell.utils.system.SpUtils;
import com.example.thinking.newsell.view.seeshop.GoodInfo.GoodActivity;

import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.thinking.newsell.R.id.toolbar;
import static com.example.thinking.newsell.R.id.useLogo;

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

public class ShowOneAskActivity extends AppCompatActivity {
    @BindView(R.id.show_one_ask_toolbar)
    Toolbar showOneAskToolbar;
    @BindView(R.id.show_one_ask_image)
    ImageView showOneAskImage;
    @BindView(R.id.show_one_ask_goodsname)
    TextView showOneAskGoodsname;
    @BindView(R.id.show_one_ask_goodsinfo_layout)
    RelativeLayout showOneAskGoodsinfoLayout;
    @BindView(R.id.show_one_ask_ask)
    TextView showOneAskAsk;

    @BindView(R.id.show_one_ask_count)
    TextView showOneAskCount;
    @BindView(R.id.main_info_linearlayout)
    LinearLayout mainInfoLinearlayout;
    @BindView(R.id.show_one_ask_reply_edit)
    EditText showOneAskReplyEdit;
    @BindView(R.id.show_one_ask_send)
    TextView showOneAskSend;
    @BindView(R.id.show_one_ask_reply_layout)
    LinearLayout showOneAskReplyLayout;
    @BindView(R.id.show_one_ask_recyclerview)
    RecyclerView showOneAskRecyclerview;

    private ReplyAdapter replydapter;
    private Quest quest;
    private int commodityId;
    private User user;
    private List<Quest.RepliesBean> replieList = new ArrayList<>();
    private Commodity mCommodity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ask_show_one);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getBundleExtra("bundle");
        Log.v("默认sid", SpUtils.getInt(ShowOneAskActivity.this, Commen.SHOPSIDdefault) + "");
        Log.v("传来的sid", getIntent().getExtras().getInt(Commen.SHOPSID) + "");
        if (SpUtils.getInt(ShowOneAskActivity.this, Commen.SHOPSIDdefault) !=bundle.getInt(Commen.SHOPSID)) {
            showOneAskReplyLayout.setVisibility(View.GONE);
        }else {
            showOneAskReplyLayout.setVisibility(View.VISIBLE);
        }


        quest = (Quest) bundle.getSerializable("quest");
        commodityId = bundle.getInt("commodityId");
        user = (User) SpUtils.getObject(this, Commen.USERINFO);
        setSupportActionBar(showOneAskToolbar);
        replieList = quest.getReplies();
        //根据传过来的id获取商品信息
        getGoodsInfo();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        replydapter = new ReplyAdapter(this, replieList);
        showOneAskRecyclerview.setAdapter(replydapter);
        showOneAskRecyclerview.setLayoutManager(manager);

        /*返回键监听*/
        showOneAskToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

/**
 * 提交问题回复的点击监听
 */
        showOneAskSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = showOneAskReplyEdit.getText().toString().trim();
                //提交成功前不可点击
                showOneAskSend.setEnabled(false);
                showOneAskSend.setClickable(false);
                if (!TextUtils.isEmpty(content)) {
                    // Log.v("提交内容",content);
                    sendReplyPre(content);
                }
            }
        });


    }


    /**
     * 自定义排序，按照订单创建的时间由晚到早进行排序
     */
    class orderComparator implements Comparator<Quest.RepliesBean> {
        @Override
        public int compare(Quest.RepliesBean o1, Quest.RepliesBean o2) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date o1Date = null, o2Date = null;
            try {
                o1Date = format.parse(o1.getRetime());
                o2Date = format.parse(o2.getRetime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return 1;
        }
    }

    /**
     * 提交回复信息之前的信息组装
     */
    private void sendReplyPre(final String content) {
        final Map<String, String> map = new HashMap<>();

        NetWorks.getShopInfo(user.getBid(), new BaseObserver<List<Shop>>() {
            @Override
            public void onHandleSuccess(List<Shop> shop) {
                map.put("username", shop.get(0).getShopname());
                map.put("qid", quest.getQid() + "");
                map.put("uid", getIntent().getExtras().getInt(Commen.SHOPSID) + "");
                map.put("up", 0 + "");
                map.put("content", content);
                //联网获取时间
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        URL url = null;//取得资源对象
                        try {
                            url = new URL("http://www.taobao.com");
                            URLConnection uc = url.openConnection();//生成连接对象
                            uc.connect(); //发出连接
                            final long ld = uc.getDate(); //取得网站日期时间
                            final Date currentDate = new Date(ld); //转换为标准时间对象
                            final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    map.put("retime", format.format(currentDate));
                                    map.put("buystatue", 2 + "");

                                    sendReply(map);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }

            @Override
            public void onHandleError(int code, String message) {

            }
        });


    }

    /**
     * 真正提交回复信息
     */
    private void sendReply(Map<String, String> map) {
        NetWorks.commitQuestReply(map, new BaseObserver<Quest.RepliesBean>() {
            @Override
            public void onHandleSuccess(Quest.RepliesBean repliesBean) {
                showOneAskReplyEdit.setText("");
                if (getCurrentFocus() != null) {
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }

                replieList.add(repliesBean);
                Collections.sort(replieList, new orderComparator());
                replydapter.notifyDataSetChanged();
                initInfo();
                showOneAskSend.setEnabled(true);
                showOneAskSend.setClickable(true);
            }

            @Override
            public void onHandleError(int code, String message) {
                Toast.makeText(ShowOneAskActivity.this, "错了", Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * 初始化信息
     */
    private void initInfo() {
        showOneAskGoodsname.setText(mCommodity.getProductname());
        Glide.with(this).load(mCommodity.getLogo()).into(showOneAskImage);
        showOneAskAsk.setText(quest.getTitle());
        showOneAskCount.setText("共" + quest.getReplies().size() + "条回答");
    }

    //获取商品信息
    public void getGoodsInfo() {
        NetWorks.getIDgood(commodityId, new BaseObserver<Commodity>() {
            @Override
            public void onHandleSuccess(Commodity commodity) {
                mCommodity = commodity;
                initInfo();
                showOneAskGoodsinfoLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ShowOneAskActivity.this, GoodActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("commodity", mCommodity);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onHandleError(int code, String message) {
                Toast.makeText(ShowOneAskActivity.this, code + message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
