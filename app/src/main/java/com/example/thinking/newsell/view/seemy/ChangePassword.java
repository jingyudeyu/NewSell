package com.example.thinking.newsell.view.seemy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.chaychan.viewlib.BankCardNumEditText;
import com.chaychan.viewlib.PowerfulEditText;
import com.example.thinking.newsell.MainActivity;
import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.User;
import com.example.thinking.newsell.utils.system.MD5Util;
import com.example.thinking.newsell.utils.system.SpUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * *****************************************
 * Created by thinking on 2017/7/12.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class ChangePassword extends AppCompatActivity {
    @BindView(R.id.myselfinfo_toolbar)
    Toolbar myselfinfoToolbar;
    @BindView(R.id.old_password)
    PowerfulEditText oldPassword;
    @BindView(R.id.new_password)
    PowerfulEditText newPassword;
    @BindView(R.id.new_password2)
    PowerfulEditText newPassword2;
    @BindView(R.id.change_next)
    Button changeNext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepassword);
        ButterKnife.bind(this);
        setSupportActionBar(myselfinfoToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        changeNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);//软键盘
                if (imm.isActive()) {//如果开启
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);//软键盘消失
                }
                if (!oldPassword.getText().toString().equals("") && !newPassword.getText().toString().equals("") && !newPassword2.getText().toString().equals("")) {
                    if (ispassword(newPassword.getText().toString())) {
                        if (newPassword.getText().toString().equals(newPassword2.getText().toString())) {
                            User user = (User) SpUtils.getObject(ChangePassword.this, "USERINFO");
                            if (user.getPassword().equals(MD5Util.encode(oldPassword.getText().toString().trim()))) {
                                //修改密码
                                NetWorks.putNewPassword(user.getBid(), newPassword.getText().toString(), new BaseObserver<User>() {
                                    @Override
                                    public void onHandleSuccess(User user) {
                                        Toast.makeText(ChangePassword.this, "密码修改成功！", Toast.LENGTH_SHORT).show();
                                        SpUtils.removeKey(ChangePassword.this, "USERINFO");
                                        SpUtils.putObject(ChangePassword.this, "USERINFO", user);
                                        Toast.makeText(ChangePassword.this, user.getPassword(), Toast.LENGTH_SHORT).show();
                                        MyselfInfo.instance.finish();
                                        Intent intent = new Intent(ChangePassword.this, MyselfInfo.class);
                                        startActivity(intent);
                                        finish();
                                    }

                                    @Override
                                    public void onHandleError(int code, String message) {

                                    }
                                });
                            } else {
                                Toast.makeText(ChangePassword.this, "原密码错误，请重新输入", Toast.LENGTH_SHORT).show();
                            }
                        } else
                            Toast.makeText(ChangePassword.this, "两次输入新密码不同，请重新确认", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(ChangePassword.this, "密码格式错误，请重新输入", Toast.LENGTH_SHORT).show();
                } else if (oldPassword.getText().toString().equals(""))
                    Toast.makeText(ChangePassword.this, "原密码不能为空", Toast.LENGTH_SHORT).show();
                else if (newPassword.getText().toString().equals(""))
                    Toast.makeText(ChangePassword.this, "新密码不能为空", Toast.LENGTH_SHORT).show();
                else Toast.makeText(ChangePassword.this, "确认密码不能为空", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*
   判断密码格式是否正确的正则表达式（6~10位字母加数字）
 */
    public static boolean ispassword(String str)
            throws PatternSyntaxException {
        String regExp = "[0-9A-Za-z]{6,10}";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

}
