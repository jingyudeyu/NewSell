package com.example.thinking.newsell.view.seemy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.chaychan.viewlib.PowerfulEditText;
import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.User;
import com.example.thinking.newsell.utils.system.SpUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

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

public class ChangePhone2 extends AppCompatActivity {
    @BindView(R.id.test_phone)
    PowerfulEditText testPhone;
    @BindView(R.id.sure_phone)
    Button surePhone;
    @BindView(R.id.p2_toolbar)
    Toolbar p2Toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changephone2);
        ButterKnife.bind(this);
        p2Toolbar.setTitle(R.string.change_phone);
        setSupportActionBar(p2Toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        surePhone.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!testPhone.getText().toString().equals("")) {
                    if (isChinaPhone(testPhone.getText().toString())) {
                        User user = (User) SpUtils.getObject(ChangePhone2.this, "USERINFO");
                        NetWorks.putNewPhone(user.getBid(), testPhone.getText().toString(), new BaseObserver<User>() {
                            @Override
                            public void onHandleSuccess(User user) {
                                user.setPhone(testPhone.getText().toString());
                                User user2 = user;
                                SpUtils.removeKey(ChangePhone2.this, "USERINFO");
                                SpUtils.putObject(ChangePhone2.this, "USERINFO", user2);
                                MyselfInfo.instance.finish();
                                Intent intent = new Intent(ChangePhone2.this, MyselfInfo.class);
                                startActivity(intent);
                                finish();
                            }

                            @Override
                            public void onHandleError(int code, String message) {

                            }
                        });
                    } else
                        Toast.makeText(ChangePhone2.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(ChangePhone2.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return false;
    }

    public static boolean isChinaPhone(String str)
            throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(147,145))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
