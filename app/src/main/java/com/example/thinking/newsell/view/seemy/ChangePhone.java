package com.example.thinking.newsell.view.seemy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.chaychan.viewlib.PowerfulEditText;
import com.example.thinking.newsell.R;
import com.example.thinking.newsell.bean.User;
import com.example.thinking.newsell.utils.system.MD5Util;
import com.example.thinking.newsell.utils.system.SpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * *****************************************
 * Created by thinking on 2017/7/14.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class ChangePhone extends AppCompatActivity {

    @BindView(R.id.test_password)
    PowerfulEditText testPassword;
    @BindView(R.id.next)
    Button next;
    @BindView(R.id.nn1_toolbar)
    Toolbar nn1Toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changenicename);
        ButterKnife.bind(this);
        nn1Toolbar.setTitle(R.string.change_phone);
        setSupportActionBar(nn1Toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = (User) SpUtils.getObject(ChangePhone.this, "USERINFO");
                if (MD5Util.encode(testPassword.getText().toString()).equals(user.getPassword())) {
                    Intent intent = new Intent(ChangePhone.this, ChangePhone2.class);
                    startActivity(intent);
                    finish();
                } else Toast.makeText(ChangePhone.this, "请输入正确的密码", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
