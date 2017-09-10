package com.example.thinking.newsell.view.seeStatistics;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.widget.Toast;

import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.Commodity;
import com.example.thinking.newsell.commen.Commen;
import com.example.thinking.newsell.utils.system.SpUtils;
import com.example.thinking.newsell.view.views.SimpleLineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.xiaomi.push.service.y.d;


/**
 * *****************************************
 * Created by thinking on 2017/8/30.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class SalesChartActivity extends AppCompatActivity {
/*    @BindView(R.id.simplechart)
    SimpleLineChart simplechart;*/

    String[] xItem = new String[5];
    String[] datelist = new String[5];
    int[] points = new int[5];
    @BindView(R.id.chart_recycler)
    RecyclerView chartRecycler;


    PieChart picchart;


    private HashMap<Integer, Integer> pointMap = new HashMap();

    private ChartAdapter chartAdapter;
    private LinearLayoutManager layoutManager;
    int page = 0;
    private int sid;
    private Double double1;
    private List<Commodity> commoditys;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sales_chart);
        ButterKnife.bind(this);
        picchart = (PieChart) findViewById(R.id.picchart);
        sid = SpUtils.getInt(SalesChartActivity.this, Commen.SHOPSIDdefault);




        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM");
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        date = calendar.getTime();
        final String date1 = simpleDateFormat.format(date);


        //销售额总
        NetWorks.getSidDateSales(sid, date1, new BaseObserver<Double>() {
            @Override
            public void onHandleSuccess(Double aDouble) {
                double1 = aDouble;




                //销售额商品信息
                NetWorks.getBySDGoods(sid, date1, page, new BaseObserver<List<Commodity>>() {
                    @Override
                    public void onHandleSuccess(List<Commodity> commodities) {
                     /*   Collections.sort(commodities, new Comparator<Commodity>() {
                            @Override
                            public int compare(Commodity o1, Commodity o2) {
                                if (o1.getPrice() < o2.getPrice()) {
                                    return 1;
                                }
                                if (o1.getPrice() == o2.getPrice()) {
                                    return 0;
                                }
                                return -1;
                            }
                        });*/

                        commoditys = commodities;
                        //设置饼状图数据
                        picchart.setData(getpicChartData(commoditys,double1));

                        picchart.setCenterText(generateCenterSpannableText(double1));
                        //设置描述
                        picchart.getDescription().setEnabled(false);
                        picchart.setExtraOffsets(5, 10, 5, 5);
                        //设置中心说明文字
                        picchart.setCenterTextSize(12);
                        picchart.setCenterTextColor(Color.BLACK);
                        picchart.animateXY(3000, 3000);
                        picchart.setUsePercentValues(true);
                        picchart.setRotationEnabled(false);

                        Legend legend=picchart.getLegend();
                      //  legend.setDrawInside(false);
                        //legend.setWordWrapEnabled(false);
                        legend.setEnabled(false);


                        picchart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                            @Override
                            public void onValueSelected(Entry e, Highlight h) {
                                Toast.makeText(SalesChartActivity.this,e.toString(),Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNothingSelected() {

                            }
                        });

                    }

                    @Override
                    public void onHandleError(int code, String message) {

                    }
                });


            }

            @Override
            public void onHandleError(int code, String message) {

            }
        });


    }





    @Override
    protected void onResume() {
        super.onResume();
        //  getTotal();
    }

    public void getTotal() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM");
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        date = calendar.getTime();
        String date1 = simpleDateFormat.format(date);

        NetWorks.getSidDateSales(sid, date1, new BaseObserver<Double>() {
            @Override
            public void onHandleSuccess(Double aDouble) {
                double1 = aDouble;
            }

            @Override
            public void onHandleError(int code, String message) {

            }
        });

        NetWorks.getBySDGoods(sid, date1, page, new BaseObserver<List<Commodity>>() {
            @Override
            public void onHandleSuccess(List<Commodity> commodities) {
                Collections.sort(commodities, new Comparator<Commodity>() {
                    @Override
                    public int compare(Commodity o1, Commodity o2) {
                        if (o1.getPrice() < o2.getPrice()) {
                            return 1;
                        }
                        if (o1.getPrice() == o2.getPrice()) {
                            return 0;
                        }
                        return -1;
                    }
                });

                commoditys = commodities;

            }

            @Override
            public void onHandleError(int code, String message) {

            }
        });
    }

    private void getSales(final int i, String date) {

        NetWorks.getSidDateSales(SpUtils.getInt(SalesChartActivity.this, Commen.SHOPSIDdefault), date, new BaseObserver<Double>() {
            @Override
            public void onHandleSuccess(Double aDouble) {
                pointMap.put(i, new Double(aDouble / 100).intValue());
                Log.v("pointMap的一开始", i + "" + pointMap.get(i));
                if (i < 5) {
                    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyy-MM");
                    Calendar calendar2 = Calendar.getInstance();
                    Date date2 = new Date();
                    calendar2.setTime(date2);
                    Log.v("i的一开始", i + "");
                    calendar2.add(Calendar.MONTH, -(4 - i));
                    date2 = calendar2.getTime();
                    String date22 = simpleDateFormat2.format(date2);
                    getSales(i + 1, date22);
                    if (i == 4) {
                        //  simplechart.setData(pointMap);
                    }
                }
            }

            @Override
            public void onHandleError(int code, String message) {

            }
        });
    }



    //中间显示的文字数据
    private SpannableString generateCenterSpannableText(Double double1) {
        SpannableString s = new SpannableString("销售额\n"+double1+"元");
        s.setSpan(new RelativeSizeSpan(1.2f), 0, 3, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 0, 3, 0);
        s.setSpan(new ForegroundColorSpan(Color.BLACK), 0, 3, 0);

        s.setSpan(new RelativeSizeSpan(2.0f), 3, s.length(), 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 3, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), 3, s.length(), 0);

        return s;
    }

    public PieData getpicChartData(List<Commodity> commoditys, Double double1) {

        List<PieEntry> list = new ArrayList<>();

        Log.v("double1总数据",double1+"");

        if (commoditys.size() > 8) {
            for (int i = 0; i < 8; i++) {
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                //一个Entry就是一个饼块
                PieEntry entry = new PieEntry(Float.parseFloat(decimalFormat.format(commoditys.get(i).getPriceTotal())),"");
                list.add(entry);

            }

        }else {
            for (int i = 0; i < commoditys.size(); i++) {
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                Log.v("SH数据是xxxxxxxxxxxxxxxxx",commoditys.get(i).getPriceTotal()+"");
                Log.v("SH数据是xxxxxxxxxxxxxxxxx",commoditys.get(i).getPriceTotal()/double1+"");
                //一个Entry就是一个饼块
                Log.v("SH数据是哈哈哈哈哈哈哈哈哈哈哈哈哈哈",decimalFormat.format(commoditys.get(i).getPriceTotal()/double1)+"");
                PieEntry entry = new PieEntry(Float.valueOf(decimalFormat.format(commoditys.get(i).getPriceTotal())),"");
                list.add(entry);
            }
        }






        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);


        //创建一组饼块的数据
        PieDataSet pieDataSet = new PieDataSet(list, "b");


        pieDataSet.setValueTextSize(14);

        pieDataSet.setSliceSpace(4);//设置饼块的间距
        pieDataSet.setColors(colors);//设置饼快颜色


        PieData pieData = new PieData(pieDataSet);
        return pieData;

    }
}
