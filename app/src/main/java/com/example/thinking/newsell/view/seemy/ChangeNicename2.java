package com.example.thinking.newsell.view.seemy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chaychan.viewlib.PowerfulEditText;
import com.example.thinking.newsell.MyApplication;
import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.User;
import com.example.thinking.newsell.utils.system.SpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * *****************************************
 * Created by thinking on 2017/7/13.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class ChangeNicename2 extends AppCompatActivity {

    @BindView(R.id.test_nicename)
    PowerfulEditText testNicename;
    @BindView(R.id.sure_nicename)
    Button sureNicename;
    @BindView(R.id.nn2_toolbar)
    Toolbar nn2Toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changenicename2);
        ButterKnife.bind(this);
        nn2Toolbar.setTitle(R.string.change_nickname);
        setSupportActionBar(nn2Toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        sureNicename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!testNicename.getText().toString().equals("")) {
                    User user = (User) SpUtils.getObject(ChangeNicename2.this, "USERINFO");
                    NetWorks.putNewNicename(user.getBid(), testNicename.getText().toString(), new BaseObserver<User>() {
                        @Override
                        public void onHandleSuccess(User user) {
                            user.setNickname(testNicename.getText().toString());
                            User user2 = user;
                            SpUtils.removeKey(ChangeNicename2.this, "USERINFO");
                            SpUtils.putObject(ChangeNicename2.this, "USERINFO", user2);

                            MyselfInfo.instance.finish();
                            Intent intent = new Intent(ChangeNicename2.this, MyselfInfo.class);
                            startActivity(intent);
                            finish();
                        }
                        @Override
                        public void onHandleError(int code, String message) {

                        }
                    });

                } else Toast.makeText(ChangeNicename2.this, "昵称不能为空", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return false;
    }
}
