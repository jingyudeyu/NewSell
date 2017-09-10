package com.example.thinking.newsell.view.seeshop.GoodInfo.AssessInfo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.Assess;
import com.example.thinking.newsell.commen.Commen;

import java.util.ArrayList;
import java.util.List;


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

public class AssessActivity extends AppCompatActivity {

    RadioGroup radioGroupID;
    RadioButton all_assess;
    RadioButton total_assess;
    RadioButton good_assess;
    RadioButton second_assess;
    RadioButton bad_assess;
    RadioButton pic_assess;
    RecyclerView assess_listview;
    TextView assess_null;
    Toolbar toolbar;
    List<Assess> assessList = new ArrayList<Assess>();
    private AssessRecyclerViewAdapter assessRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.good_assess);
        toolbar = (Toolbar) findViewById(R.id.ases_toolbar);
        radioGroupID = (RadioGroup) findViewById(R.id.radioGroupID);
        all_assess = (RadioButton) findViewById(R.id.all_assess);
        total_assess = (RadioButton) findViewById(R.id.total_assess);
        good_assess = (RadioButton) findViewById(R.id.good_assess);
        second_assess = (RadioButton) findViewById(R.id.second_assess);
        bad_assess = (RadioButton) findViewById(R.id.bad_assess);
        pic_assess = (RadioButton) findViewById(R.id.pic_assess);
        assess_listview = (RecyclerView) findViewById(R.id.assess_listview);
        assess_null=(TextView)findViewById(R.id.assess_null);
        toolbar.setTitle(R.string.good_assess);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        radioGroupID.setVisibility(View.GONE);
        int cid = Integer.valueOf(getIntent().getStringExtra("Cid"));


        NetWorks.getAllAssess(cid, new BaseObserver<List<Assess>>(this, new ProgressDialog(this)) {
            @Override
            public void onHandleSuccess(List<Assess> assesses) {
                assessList = assesses;
                if (assessList.size() == 0) {
                    radioGroupID.setVisibility(View.GONE);
                    assess_null.setVisibility(View.VISIBLE);

                } else if (assessList.size() != 0) {
                    radioGroupID.setVisibility(View.VISIBLE);
                    all_assess.setText("全部评论(" + assesses.size() + ")");
                    int total = 0, good = 0, second = 0, bad = 0, pic = 0;
                    for (int i = 0; i < assesses.size(); i++) {
                        switch ((int) assesses.get(i).getGrade()) {
                            case 0:
                                bad++;
                                break;
                            case 1:
                                bad++;
                                break;
                            case 2:
                                second++;
                                break;
                            case 3:
                                second++;
                                break;
                            case 4:
                                good++;
                                break;
                            case 5:
                                good++;
                                break;
                            default:
                                break;
                        }
                        if (assesses.get(i).getPics() != null) {
                            pic++;
                        }
                        if (assesses.get(i).getHollrall() != null) {
                            total++;
                        }
                    }
                    if (total != 0) {
                        total_assess.setText("总评(" + total + ")");
                    } else total_assess.setVisibility(View.GONE);

                    if (good != 0) {
                        good_assess.setText("好评(" + good + ")");
                    } else good_assess.setVisibility(View.GONE);

                    if (second != 0) {
                        second_assess.setText("中评(" + second + ")");
                    } else second_assess.setVisibility(View.GONE);

                    if (bad != 0) {
                        bad_assess.setText("差评(" + bad + ")");
                    } else bad_assess.setVisibility(View.GONE);

                    if (pic != 0) {
                        pic_assess.setText("有图(" + pic + ")");
                    } else pic_assess.setVisibility(View.GONE);

                    LinearLayoutManager layoutManager = new LinearLayoutManager(AssessActivity.this);//设置布局管理器
                    assess_listview.setLayoutManager(layoutManager);
                    all_assess.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            all_assess.setChecked(true);
                            total_assess.setChecked(false);
                            good_assess.setChecked(false);
                            second_assess.setChecked(false);
                            bad_assess.setChecked(false);
                            pic_assess.setChecked(false);
                            // all_assess.setTextColor(getResources().getColor(R.color.colorPrimary));
                            assessRecyclerViewAdapter.notifyDataSetChanged();
                            assessRecyclerViewAdapter = new AssessRecyclerViewAdapter(AssessActivity.this, assessList, getIntent().getExtras().getInt(Commen.SHOPSID));
                            assess_listview.setAdapter(assessRecyclerViewAdapter);//设置Adapter
                        }
                    });
                    total_assess.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //  total_assess.setTextColor(getResources().getColor(R.color.colorPrimary));
                            all_assess.setChecked(false);
                            total_assess.setChecked(true);
                            good_assess.setChecked(false);
                            second_assess.setChecked(false);
                            bad_assess.setChecked(false);
                            pic_assess.setChecked(false);
                            List<Assess> assesstotal = new ArrayList<Assess>();
                            for (int i = 0; i < assessList.size(); i++) {
                                if (assessList.get(i).getHollrall() != null) {
                                    assesstotal.add(assessList.get(i));
                                }
                            }
                            assessRecyclerViewAdapter = new AssessRecyclerViewAdapter(AssessActivity.this, assesstotal,getIntent().getExtras().getInt(Commen.SHOPSID));
                            assess_listview.setAdapter(assessRecyclerViewAdapter);//设置Adapter
                        }
                    });

                    good_assess.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            all_assess.setChecked(false);
                            total_assess.setChecked(false);
                            good_assess.setChecked(true);
                            second_assess.setChecked(false);
                            bad_assess.setChecked(false);
                            pic_assess.setChecked(false);
                            // good_assess.setTextColor(getResources().getColor(R.color.colorPrimary));
                            List<Assess> assessgood = new ArrayList<Assess>();
                            for (int i = 0; i < assessList.size(); i++) {
                                if ((int) assessList.get(i).getGrade() == 4 || (int) assessList.get(i).getGrade() == 5) {
                                    assessgood.add(assessList.get(i));
                                }
                            }
                            assessRecyclerViewAdapter = new AssessRecyclerViewAdapter(AssessActivity.this, assessgood,getIntent().getExtras().getInt(Commen.SHOPSID));
                            assess_listview.setAdapter(assessRecyclerViewAdapter);//设置Adapter
                        }
                    });
                    second_assess.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            all_assess.setChecked(false);
                            total_assess.setChecked(false);
                            good_assess.setChecked(false);
                            second_assess.setChecked(true);
                            bad_assess.setChecked(false);
                            pic_assess.setChecked(false);
                            //   second_assess.setTextColor(getResources().getColor(R.color.colorPrimary));
                            List<Assess> assesssecond = new ArrayList<Assess>();
                            for (int i = 0; i < assessList.size(); i++) {
                                if ((int) assessList.get(i).getGrade() == 3 || (int) assessList.get(i).getGrade() == 2) {
                                    assesssecond.add(assessList.get(i));
                                }
                            }
                            assessRecyclerViewAdapter = new AssessRecyclerViewAdapter(AssessActivity.this, assesssecond,getIntent().getExtras().getInt(Commen.SHOPSID));
                            assess_listview.setAdapter(assessRecyclerViewAdapter);//设置Adapter
                        }
                    });
                    bad_assess.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            all_assess.setChecked(false);
                            total_assess.setChecked(false);
                            good_assess.setChecked(false);
                            second_assess.setChecked(false);
                            bad_assess.setChecked(true);
                            pic_assess.setChecked(false);
                            //   bad_assess.setTextColor(getResources().getColor(R.color.colorPrimary));
                            List<Assess> assessbad = new ArrayList<Assess>();
                            for (int i = 0; i < assessList.size(); i++) {
                                if ((int) assessList.get(i).getGrade() == 0 || (int) assessList.get(i).getGrade() == 1) {
                                    assessbad.add(assessList.get(i));
                                }
                            }
                            assessRecyclerViewAdapter = new AssessRecyclerViewAdapter(AssessActivity.this, assessbad,getIntent().getExtras().getInt(Commen.SHOPSID));
                            assess_listview.setAdapter(assessRecyclerViewAdapter);//设置Adapter
                        }
                    });
                    pic_assess.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            total_assess.setChecked(false);
                            all_assess.setChecked(false);
                            good_assess.setChecked(false);
                            second_assess.setChecked(false);
                            bad_assess.setChecked(false);
                            pic_assess.setChecked(true);
                            List<Assess> assesspic = new ArrayList<Assess>();
                            for (int i = 0; i < assessList.size(); i++) {
                                if (assessList.get(i).getPics() != null) {
                                    assesspic.add(assessList.get(i));
                                }
                            }
                            assessRecyclerViewAdapter = new AssessRecyclerViewAdapter(AssessActivity.this, assesspic,getIntent().getExtras().getInt(Commen.SHOPSID));
                            assess_listview.setAdapter(assessRecyclerViewAdapter);//设置Adapter
                        }
                    });
                    assessRecyclerViewAdapter = new AssessRecyclerViewAdapter(AssessActivity.this, assesses, getIntent().getExtras().getInt(Commen.SHOPSID));
                    assess_listview.setAdapter(assessRecyclerViewAdapter);//设置Adapter
                }
            }

            @Override
            public void onHandleError(int code, String message) {

            }
        });
    }

    /*返回键*/
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return false;
    }

    @Override
    protected void onResume() {

        if (assessRecyclerViewAdapter != null) {

            NetWorks.getAllAssess(Integer.valueOf(getIntent().getStringExtra("Cid")), new BaseObserver<List<Assess>>() {
                @Override
                public void onHandleSuccess(List<Assess> assesses) {
                    assessList.clear();
                    assessList.addAll(assesses);
                    assessRecyclerViewAdapter.notifyDataSetChanged();

                }

                @Override
                public void onHandleError(int code, String message) {

                }
            });
        }
        super.onResume();
    }

}
