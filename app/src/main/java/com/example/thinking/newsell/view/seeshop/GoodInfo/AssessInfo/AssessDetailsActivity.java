package com.example.thinking.newsell.view.seeshop.GoodInfo.AssessInfo;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daasuu.bl.BubbleLayout;
import com.example.thinking.newsell.MainActivity;
import com.example.thinking.newsell.R;
import com.example.thinking.newsell.api.BaseObserver;
import com.example.thinking.newsell.api.NetWorks;
import com.example.thinking.newsell.bean.Assess;
import com.example.thinking.newsell.bean.Commodity;
import com.example.thinking.newsell.commen.Commen;
import com.example.thinking.newsell.utils.system.SpUtils;
import com.example.thinking.newsell.view.seeshop.GoodInfo.GoodActivity;
import com.example.thinking.newsell.view.seeshop.ShopInfo.ImagesActivity;
import com.example.thinking.newsell.view.views.StarRating;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * *****************************************
 * Created by thinking on 2017/7/25.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class AssessDetailsActivity extends AppCompatActivity {
    @BindView(R.id.one_toolbar)
    Toolbar oneToolbar;
    @BindView(R.id.one_user_image)
    CircleImageView oneUserImage;
    @BindView(R.id.one_user_name)
    TextView oneUserName;
    @BindView(R.id.one_user_time)
    TextView oneUserTime;
    @BindView(R.id.one_user_choice)
    TextView oneUserChoice;
    @BindView(R.id.one_user_assess)
    TextView oneUserAssess;
    @BindView(R.id.one_image_gridview)
    MyGridView oneImageGridview;
    @BindView(R.id.one_reply_textview)
    TextView oneReplyTextview;
    @BindView(R.id.one_reply_bubblelayout)
    BubbleLayout oneReplyBubblelayout;
    @BindView(R.id.one_reply)
    Button oneReply;
    /*对话框*/
    Dialog editDialog;
    EditText editText;
    private static boolean isFirst = true;
    String[] pics;
    @BindView(R.id.one_overview_assess)
    TextView oneOverviewAssess;
    @BindView(R.id.one_goodimage)
    ImageView oneGoodimage;
    @BindView(R.id.one_goodname)
    TextView oneGoodname;
    @BindView(R.id.one_goodprice)
    TextView oneGoodprice;
    @BindView(R.id.one_user_num)
    TextView oneUserNum;
    @BindView(R.id.assess_ratingbar)
    StarRating assessRatingbar;
    @BindView(R.id.one_good_rl)
    RelativeLayout oneGoodRl;
    private ArrayList<String> imagelist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assess_one);
        ButterKnife.bind(this);
        oneToolbar.setTitle(R.string.assess_detail);
        setSupportActionBar(oneToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent().getExtras().getInt(Commen.SHOPSID)!= SpUtils.getInt(AssessDetailsActivity.this,Commen.SHOPSIDdefault)){
            oneReply.setVisibility(View.GONE);
        }

        Assess assess = (Assess) getIntent().getSerializableExtra(Commen.ASSESSONE);
        if (TextUtils.isEmpty(assess.getBossback())) {
            oneReplyBubblelayout.setVisibility(View.GONE);
            oneReply.setEnabled(true);
            oneReply.setTextColor(getResources().getColor(R.color.colorWhite));
            oneReply.setBackground(getResources().getDrawable(R.drawable.shape_attention));
        } else {
            oneReplyTextview.setText(assess.getBossback());
            oneReply.setEnabled(false);
            oneReply.setClickable(false);
            oneReply.setTextColor(getResources().getColor(R.color.colorPrimary));
            oneReply.setText("已回复");
            oneReply.setBackground(getResources().getDrawable(R.drawable.shape_attentioned));

        }
        oneUserName.setText(newName(assess.getUsername()));
        oneUserTime.setText(String.valueOf(assess.getDate().substring(0, 10)));
        oneUserNum.setText("x" + assess.getCount());
        assessRatingbar.setCurrentStarCount((int) assess.getGrade());
        oneUserAssess.setText(assess.getDetail());
        oneOverviewAssess.setText(assess.getHollrall());
        oneUserChoice.setText(assess.getParam());
        if (!TextUtils.isEmpty(assess.getPics())) {
            pics = assess.getPics().split(";");
            imagelist = new ArrayList<>();
            for (int i = 0; i < pics.length; i++) {
                imagelist.add(pics[i]);
            }
            GridAdapter GridAdapter = new GridAdapter(pics, AssessDetailsActivity.this);
            oneImageGridview.setAdapter(GridAdapter);
            oneImageGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList(Commen.SHOWPICS, imagelist);
                    Intent intent = new Intent(AssessDetailsActivity.this, ImagesActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        } else oneImageGridview.setVisibility(View.GONE);

        NetWorks.getIDgood(assess.getCid(), new BaseObserver<Commodity>() {
            @Override
            public void onHandleSuccess(final Commodity commodity) {
                Glide.with(AssessDetailsActivity.this).load(commodity.getLogo()).into(oneGoodimage);
                oneGoodname.setText(commodity.getProductname());
                oneGoodprice.setText(String.valueOf(commodity.getPrice()));
                oneGoodRl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("commodity", commodity);
                        Intent intent = new Intent(AssessDetailsActivity.this, GoodActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onHandleError(int code, String message) {

            }
        });


        //对话框
        editDialog = new Dialog(this, R.style.my_dialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.dialog_edit_text, null);
        root.findViewById(R.id.reply_true).setOnClickListener(dialoglistener);
        editText = (EditText) root.findViewById(R.id.edit_text);
        editDialog.setCanceledOnTouchOutside(true);//点击外部使对话框消失
        editDialog.setContentView(root);
        Window dialogWindow = editDialog.getWindow();
        WindowManager.LayoutParams lp=editDialog.getWindow().getAttributes();
        Display d=getWindowManager().getDefaultDisplay();
        lp.height = (int) (d.getHeight() * 0.25); // 高度设置为屏幕的0.6
        lp.width = (int) (d.getWidth()); // 宽度设置为屏幕的0.95
        dialogWindow.setAttributes(lp);
        // dialogWindow.setGravity(Gravity.BOTTOM);
        //  dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画

        oneReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editDialog.show();
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

    //对话框的点击事件
    private View.OnClickListener dialoglistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.reply_true:
                    String a = editText.getText().toString();
                    if (!a.equals("")) {
                        Assess assess1 = (Assess) getIntent().getSerializableExtra(Commen.ASSESSONE);
                        NetWorks.putBackAssess(assess1.getAid(), a.toString(), new BaseObserver<Assess>() {
                            @Override
                            public void onHandleSuccess(Assess assess) {
                                oneReplyTextview.setText(assess.getBossback());
                                oneReply.setTextColor(getResources().getColor(R.color.colorPrimary));
                                oneReply.setText("已回复");
                                oneReply.setClickable(false);
                                oneReply.setBackground(getResources().getDrawable(R.drawable.shape_attentioned));
                                editDialog.dismiss();
                                oneReplyBubblelayout.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onHandleError(int code, String message) {
                                Toast.makeText(AssessDetailsActivity.this, code+message, Toast.LENGTH_SHORT).show();
                            }
                        });

                    } else {
                        oneReply.setTextColor(getResources().getColor(R.color.colorWhite));
                        oneReply.setBackground(getResources().getDrawable(R.drawable.shape_attention));
                        oneReplyBubblelayout.setVisibility(View.GONE);
                        editDialog.dismiss();
                        Toast.makeText(AssessDetailsActivity.this, "未填写回复", Toast.LENGTH_SHORT).show();
                    }

                    break;
                default:
                    break;
            }
        }
    };

    /**
     * GridView的适配器
     */
    private class GridAdapter extends BaseAdapter {
        private String[] gridData;
        // private int width;
        private Context context;
        private LayoutInflater inflater;

        /*  public GridAdapter(int width, String[] gridData, Context context) {

              this.gridData = gridData;
              this.width = width;
              this.context = context;
              inflater = LayoutInflater.from(context);
          }
  */
        public GridAdapter(String[] gridData, Context context) {

            this.gridData = gridData;
            this.context = context;
            inflater = LayoutInflater.from(context);
        }


        @Override
        public int getCount() {
            return gridData.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolderGrid holer = null;
            View view = inflater.inflate(R.layout.assess_grid_pic, null);
            if (holer == null) {
                holer = new ViewHolderGrid();
                holer.grid_image = (ImageView) view.findViewById(R.id.grid_image);
                view.setTag(holer);

            } else {
                holer = (ViewHolderGrid) view.getTag();
            }
            Glide.with(context)
                    .load(gridData[position])
                    .into(holer.grid_image);
            return view;
        }

        //  适配器中的GridView缓存类
        class ViewHolderGrid {
            ImageView grid_image;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private String newName(String username) {
        String newname;
        int num = username.length();
        newname = username.charAt(0) + "**" + username.charAt(num - 2) + username.charAt(num - 1);
        return newname;
    }

}
