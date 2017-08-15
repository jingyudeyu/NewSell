package com.example.thinking.newsell.view.seekpartners;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.thinking.newsell.R;
import com.example.thinking.newsell.bean.City;
import com.example.thinking.newsell.bean.Province;

import java.util.List;

/**
 * *****************************************
 * Created by thinking on 2017/8/2.
 * 创建时间：
 * <p>
 * 描述：
 * <p/>
 * <p/>
 * *******************************************
 */

public class CityAdapter extends BaseAdapter {
    private Context context;
    private List<City> cities;
    private LayoutInflater layoutInfater;

    public CityAdapter(Context context,List<City> cities){
        this.context=context;
        layoutInfater=LayoutInflater.from(context);
        this.cities=cities;

    }

    @Override
    public int getCount() {
        return cities.size();
    }

    @Override
    public Object getItem(int position) {
        return cities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView=layoutInfater.inflate(R.layout.province_item,null);
        }
        TextView textView=(TextView)convertView.findViewById(R.id.province_name);
        textView.setText(cities.get(position).getCityname());

        return convertView;
    }
}
