package com.example.thinking.newsell;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.User;
import com.example.thinking.newsell.commen.Commen;
import com.example.thinking.newsell.utils.system.MD5Util;
import com.example.thinking.newsell.utils.system.SpUtils;
import com.example.thinking.newsell.view.HomeActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    @BindView(R.id.login_user_name)
    EditText phone;
    @BindView(R.id.login_user_password)
    EditText password;
    @BindView(R.id.login_password_image)
    CheckBox password_image;
    @BindView(R.id.login)
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);//BindView

        if (SpUtils.getBoolean(MainActivity.this,Commen.USERLOGIN)) {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    /*    if (SpUtils.getBoolean(MainActivity.this, "USERLOGIN")) {
            final User user_old = (User) SpUtils.getObject(MainActivity.this, "USERINFO");
            NetWorks.getUserInfo(user_old.getBid(), new BaseObserver<User>() {
                @Override
                public void onHandleSuccess(User user) {
                    if (!user_old.getPassword().equals(user.getPassword()) || !user_old.getNickname().equals(user.getNickname()) || !user_old.getPhone().equals(user.getPhone())) {
                        Toast.makeText(MainActivity.this, "？？？？？？", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                @Override
                public void onHandleError(int code, String message) {

                }
            });
        }*/
        /*CheckBox password_image控制密码的明文和暗文*/
        password_image.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                }
            }
        });
         /*登录*/
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);//软键盘
                if (imm.isActive()) {//如果开启
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);//软键盘消失
                }
                if (ispassword(password.getText().toString())) {//是否符合密码的正则表达式
                    NetWorks.postLogin(phone.getText().toString(), password.getText().toString(), new BaseObserver<User>() {
                        @Override
                        public void onHandleSuccess(User user) {
                            //SharedPreferences存放用户
                            SpUtils.removeKey(MainActivity.this, Commen.USERINFO);
                            SpUtils.putObject(MainActivity.this, Commen.USERINFO, user);
                            SpUtils.putBoolean(MainActivity.this, Commen.USERLOGIN, true);
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                            finish();
                        }
                        @Override
                        public void onHandleError(int code, String message) {
                            if (code == 2||code==1)
                                Toast.makeText(MainActivity.this, "用户名或密码错误，请重新输入", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else
                    Toast.makeText(MainActivity.this, "请输入正确格式的密码", Toast.LENGTH_SHORT).show();

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
