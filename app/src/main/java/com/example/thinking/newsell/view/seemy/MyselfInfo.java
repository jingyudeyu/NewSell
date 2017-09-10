package com.example.thinking.newsell.view.seemy;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.thinking.newsell.MainActivity;
import com.example.thinking.newsell.MyApplication;
import com.example.thinking.newsell.R;
import com.example.thinking.newsell.bean.User;
import com.example.thinking.newsell.utils.system.SpUtils;
import com.hyphenate.chat.EMClient;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static android.R.attr.path;
import static android.R.attr.width;
import static android.graphics.Bitmap.Config.ARGB_8888;
import static android.graphics.PorterDuff.Mode.SRC_IN;
import static android.os.Environment.getExternalStorageDirectory;

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

public class MyselfInfo extends AppCompatActivity {

    @BindView(R.id.user_nicename)
    TextView userNicename;
    @BindView(R.id.rl_user_nicename)
    RelativeLayout rlUserNicename;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.rl_user_name)
    RelativeLayout rlUserName;
    @BindView(R.id.user_phone)
    TextView userPhone;
    @BindView(R.id.rl_user_phone)
    RelativeLayout rlUserPhone;
    @BindView(R.id.user_idcard)
    TextView userIdcard;
    @BindView(R.id.rl_user_idcard)
    RelativeLayout rlUserIdcard;
    @BindView(R.id.user_password)
    TextView userPassword;
    @BindView(R.id.rl_user_password)
    RelativeLayout rlUserPassword;
    @BindView(R.id.sign_out)
    Button signOut;
/*    @BindView(R.id.myself_toolbar)
    Toolbar toolbar;*/
    @BindView(R.id.user_image)
    ImageView userImage;
    @BindView(R.id.rl_user_image)
    RelativeLayout rlUserImage;
    public static MyselfInfo instance = null;//在A里面设置一个静态的变量instance,初始化为this在B 里面,A.instance.finish();
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    protected static Uri tempUri;
    private static final int CROP_SMALL_PICTURE = 2;
    private Bitmap mBitmap;
    private static File tempFile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfo_myselfinfo);
        ButterKnife.bind(this);
        instance = this;
        MyApplication.getInstance().addActivity(this);
       // setSupportActionBar(toolbar);
      //  ActionBar actionBar = getSupportActionBar();
     //   actionBar.setDisplayHomeAsUpEnabled(true);
        User user0 = (User) SpUtils.getObject(MyselfInfo.this, "USERINFO");
        Glide.with(this).load(user0.getPic()).bitmapTransform(new CropCircleTransformation(this)).into(userImage);
        userNicename.setText(user0.getNickname());//昵称
        userName.setText(user0.getName());//用户名
        userPhone.setText(user0.getPhone());//手机号
        String str = user0.getId().substring(0, 6);
        userIdcard.setText(str + "************");//身份证号


      /*  Bitmap bt = BitmapFactory.decodeFile(path + "head.jpg");// 从SD卡中找头像，转换成Bitmap
        if (bt != null) {
            @SuppressWarnings("deprecation")
            Drawable drawable = new BitmapDrawable(bt);// 转换成drawable
            userImage.setImageDrawable(drawable);
        } else {
            *//**
         * 如果SD里面没有则需要从服务器取头像，取回来的头像再保存在SD中
         *
         *//*
        }*/
        rlUserImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //修改头像
                showChoosePicDialog();
            }
        });
        rlUserNicename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MyselfInfo.this, ChangeNicename.class);
                startActivity(intent);
            }
        });

        rlUserPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyselfInfo.this, ChangePhone.class);
                startActivity(intent);
            }
        });


        rlUserPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyselfInfo.this, ChangePassword.class);
                startActivity(intent);
            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpUtils.removeSP(MyselfInfo.this);
                EMClient.getInstance().logout(true);
                Intent intentout = new Intent(MyselfInfo.this, MainActivity.class);
                startActivity(intentout);
            }
        });
    }

    /**
     * 显示修改图片的对话框
     */
    protected void showChoosePicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MyselfInfo.this);
        builder.setTitle("添加图片");
        String[] items = {"选择本地照片", "拍照"};
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case CHOOSE_PICTURE: // 选择本地照片
                        Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
                        openAlbumIntent.setType("image/*");
                        //用startActivityForResult方法，待会儿重写onActivityResult()方法，拿到图片做裁剪操作


                      /*  tempFile=new File("/sdcard/newbuy/"+"head"+".jpg"); // 以时间秒为文件名
                        File temp = new File("/sdcard/newbuy/");//自已项目 文件夹
                        if (!temp.exists()) {
                            temp.mkdir();
                        }
                        openAlbumIntent.putExtra("output", Uri.fromFile(tempFile));  // 专入目标文件
                        openAlbumIntent.putExtra("outputFormat", "JPEG"); //输入文件格式*/



                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        break;
                    case TAKE_PICTURE: // 拍照
                        Intent openCameraIntent = new Intent(
                                MediaStore.ACTION_IMAGE_CAPTURE);
                        tempUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "temp_image.jpg"));
                        // 将拍照所得的相片保存到SD卡根目录
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                        startActivityForResult(openCameraIntent, TAKE_PICTURE);
                        break;
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == MainActivity.RESULT_OK) {
            switch (requestCode) {
                case TAKE_PICTURE:
                    cutImage(tempUri); // 对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    cutImage(data.getData()); // 对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
        }
    }

    /**
     * 裁剪图片方法实现
     */
    protected void cutImage(Uri uri) {
        if (uri == null) {
            Log.i("alanjet", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        //com.android.camera.action.CROP这个action是用来裁剪图片用的
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    /**
     * 保存裁剪之后的图片数据
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            mBitmap = extras.getParcelable("data");
            userImage.setImageBitmap(mBitmap);//显示图片
            //在这个地方可以写上上传该图片到服务器的代码
        }
    }
}
