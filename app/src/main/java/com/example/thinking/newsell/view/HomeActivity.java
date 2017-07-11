package com.example.thinking.newsell.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.thinking.newsell.R;
import com.example.thinking.newsell.utils.system.SpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * *****************************************
 * Created by thinking on 2017/7/10.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.sp_clear)
    Button spClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        spClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpUtils.putString(HomeActivity.this,"USERPHONE","");
                SpUtils.putString(HomeActivity.this,"USERNAME","");
                SpUtils.putString(HomeActivity.this,"USERPASSWORD","");
            }
        });
    }
}
