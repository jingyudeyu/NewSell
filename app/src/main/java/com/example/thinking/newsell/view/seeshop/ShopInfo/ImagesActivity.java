package com.example.thinking.newsell.view.seeshop.ShopInfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.thinking.newsell.R;
import com.example.thinking.newsell.commen.Commen;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.thinking.newsell.R.id.viewPager;

/**
 * *****************************************
 * Created by thinking on 2017/7/24.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class ImagesActivity extends AppCompatActivity {
    @BindView(R.id.image_viewpager)
    ViewPager imageViewpager;
    @BindView(R.id.page_text)
    TextView pageText;
    private ArrayList<String> imagelist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_scan_shop);
        ButterKnife.bind(this);

        imagelist=new ArrayList<>();
        imagelist=getIntent().getStringArrayListExtra(Commen.SHOWPICS);

        imageViewpager.setAdapter(new PictureSlidePagerAdapter(getSupportFragmentManager()));
        imageViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                pageText.setText(String.valueOf(position+1)+"/"+imagelist.size());
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    private  class PictureSlidePagerAdapter extends FragmentStatePagerAdapter {

        public PictureSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PictureSlideFragment.newInstance(imagelist.get(position));
        }

        @Override
        public int getCount() {
            return imagelist.size();
        }
    }
}
