package com.example.thinking.newsell.view.seekpartners;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.thinking.newsell.R;
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

public class ProvinceAdapter extends BaseAdapter {

    private Context context;
    private List<Province> provinces;
    private LayoutInflater layoutInfater;

    public ProvinceAdapter(Context context,List<Province> provinces){
        this.context=context;
        layoutInfater=LayoutInflater.from(context);
        this.provinces=provinces;

    }
    @Override
    public int getCount() {
        return provinces.size();
    }

    @Override
    public Object getItem(int position) {
        return provinces.get(position);
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
        textView.setText(provinces.get(position).getProvince());

        return convertView;
    }
}
