package com.example.thinking.newsell.view.seekpartners;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.thinking.newsell.R;
import com.example.thinking.newsell.commen.Commen;
import com.example.thinking.newsell.utils.system.SpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;

import static com.example.thinking.newsell.commen.Commen.SEARCHCONTENT;

/**
 * *****************************************
 * Created by thinking on 2017/8/3.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class SearchPartner extends AppCompatActivity {

    @BindView(R.id.search_goback)
    ImageView searchGoback;//搜索返回按钮
    @BindView(R.id.search_btn)
    Button searchBtn;//搜索按钮
    @BindView(R.id.searchview)
    SearchView searchview;//搜索框
    @BindView(R.id.partner_main_toolbar)
    Toolbar partnerMainToolbar;//搜索toolbar
    @BindView(R.id.history_image)
    ImageView historyImage;//搜索历史清除按钮
    @BindView(R.id.histroy_re2)
    RelativeLayout histroyRe2;//布局包括历史记录提示和清楚按钮
    @BindView(R.id.history_taglayout)
    TagContainerLayout historyTaglayout;//历史记录布局
    @BindView(R.id.history_layout)
    LinearLayout historyLayout;//除搜索框的所有布局

    private List<String> historylist = new ArrayList<>();
    private int resultCode = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.partner_main);
        ButterKnife.bind(this);
        initSearchview();//searchview的一些设置
        if (SpUtils.getStringArray(SearchPartner.this, Commen.HISTRORYLIST).size() != 0) {
            historylist = SpUtils.getStringArray(SearchPartner.this, Commen.HISTRORYLIST);
            histroyRe2.setVisibility(View.VISIBLE);
            historyTaglayout.setVisibility(View.VISIBLE);
            historyTaglayout.setTags(historylist);//显示历史记录
        }

        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                GetQuery(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        //搜索按钮
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GetQuery(searchview.getQuery().toString());
            }
        });

        //历史记录historyTaglayout的监听
        historyTaglayout.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {
                //点击选择其中一条历史记录时，跳转搜索
                GetQuery(text);

            }

            @Override
            public void onTagLongClick(int position, String text) {

            }

            @Override
            public void onTagCrossClick(int position) {

            }
        });

        //历史记录的删除键
        historyImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //------------------------------------这里应该有个对话框一会写
                historylist.clear();//historylist清除数据
                historyTaglayout.removeAllTags();//清除页面中的历史
                SpUtils.removeStringArray(SearchPartner.this, Commen.HISTRORYLIST);//sp中清除数据
                Toast.makeText(SearchPartner.this, "已删除", Toast.LENGTH_SHORT).show();
            }
        });

        //返回键
        searchGoback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultCode = 2;
               // Intent intent = new Intent(SearchPartner.this, SeekPartner.class);
                // setResult(resultCode,intent);
                // finish();
                finish();
            }
        });
    }

    private void GetQuery(String query) {
        if (!TextUtils.isEmpty(query)) {
            if (!historylist.contains(query)) {
                historylist.add(query);
                SpUtils.putStringArray(SearchPartner.this, Commen.HISTRORYLIST, historylist);
                historyTaglayout.setTags(historylist);
            }
            Intent intent = new Intent(SearchPartner.this, SeekPartner.class);
            intent.putExtra(Commen.SEARCHCONTENT, query);

            startActivity(intent);
        }

    }


    //searchview的一些设置
    private void initSearchview() {
        searchview.setSubmitButtonEnabled(false);
        searchview.setQueryHint("合作商品");
        searchview.setFocusable(true);//焦点
        searchview.setFocusableInTouchMode(true);
        searchview.onActionViewExpanded();//展开
        searchview.requestFocus();//获取焦点
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(searchview, InputMethodManager.SHOW_FORCED);
    }

  /*  @Override
    public void onBackPressed() {
        resultCode = 2;
        Intent intent = new Intent(SearchPartner.this, SeekPartner.class);
        setResult(resultCode, intent);
        finish();

    }*/
}
