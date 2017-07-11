package com.example.thinking.newsell;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
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
        /*CheckBox password_image控制密码的明文和暗文*/
        if (SpUtils.getString(MainActivity.this,"USERPHONE").equals("")&&SpUtils.getString(MainActivity.this,"USERPASSWORD").equals("")){
            //phone.setText(SpUtils.getString(MainActivity.this,"USERPHONE"));
          //  password.setText(SpUtils.getString(MainActivity.this,"USERPASSWORD"));
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
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

                if (!phone.getText().toString().equals("") && !password.getText().toString().equals("")) {
                    if (isChinaPhone(phone.getText().toString().trim())) {//是否符合手机号的正则表达式
                        if (ispassword(password.getText().toString().trim())) {//是否符合密码的正则表达式
                            /*用手机号获取用户信息*/
                            NetWorks.getUserInfo(phone.getText().toString().trim(), new BaseObserver<User>() {//根据手机号获取user信息
                                @Override
                                public void onHandleSuccess(User user) {
                                    if (user == null) {
                                        isusername(phone.getText().toString().trim());//假如返回为空，用用户名判断
                                    } else {
                                        if (user.getPassword().equals(MD5Util.encode(password.getText().toString().trim()))) {//比较该用户密码是否正确
                                           //SharedPreferences存放用户用户名、手机号、密码
                                            SpUtils.putString(MainActivity.this,"USERNAME",user.getName());
                                            SpUtils.putString(MainActivity.this,"USERPHONE",user.getPhone());
                                            SpUtils.putString(MainActivity.this,"USERPASSWORD",MD5Util.encode(user.getPassword()));

                                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                            startActivity(intent);
                                            finish();
                                        } else
                                            Toast.makeText(MainActivity.this, "用户名或密码错误，请重新输入", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else
                            Toast.makeText(MainActivity.this, "请输入正确格式的密码", Toast.LENGTH_SHORT).show();
                    } else {
                        isusername(phone.getText().toString().trim());//假如不是手机号的格式，用用户名判断
                        // Toast.makeText(MainActivity.this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                    }
                } else Toast.makeText(MainActivity.this, "用户名和密码不能为空！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*
      判断是否是用户名
    */
    public void isusername(String str) {
        if (ispassword(password.getText().toString().trim())) {
            NetWorks.getUserInfoByname(str, new BaseObserver<User>() {
                @Override
                public void onHandleSuccess(User user) {
                    if (user == null) {
                        Toast.makeText(MainActivity.this, "用户名或密码错误，请重新输入", Toast.LENGTH_SHORT).show();
                    } else {
                        if (user.getPassword().equals(MD5Util.encode(password.getText().toString().trim()))) {//比较该用户密码是否正确

                            //SharedPreferences存放用户用户名、手机号、密码
                            SpUtils.putString(MainActivity.this,"USERNAME",user.getName());
                            SpUtils.putString(MainActivity.this,"USERPHONE",user.getPhone());
                            SpUtils.putString(MainActivity.this,"USERPASSWORD",MD5Util.encode(user.getPassword()));
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        } else
                            Toast.makeText(MainActivity.this, "用户名或密码错误，请重新输入", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else Toast.makeText(MainActivity.this, "请输入正确格式的密码", Toast.LENGTH_SHORT).show();
    }

    /*
     判断手机号码格式是否正确的正则表达式
     */
    public static boolean isChinaPhone(String str)
            throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(147,145))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
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
