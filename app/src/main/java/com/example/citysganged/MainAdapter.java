package com.example.citysganged;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Lyudony on 2020/4/20 20:00
 * E-Mail Address：lyudony.outlook@qq.com
 */
public class MainAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Area> mList;
    private LayoutInflater  mInflater;
    private TextView mTextView;

    public MainAdapter(Context context,ArrayList<Area>list){
        this.mContext =context;
        this.mList =list;
        this.mInflater =LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //非空判断
        if (convertView==null) {
            mTextView = new TextView(mContext);
        }else {
            mTextView = (TextView) convertView;
        }
        mTextView.setText(mList.get(position).getName());
        mTextView.setTextSize(18);
        mTextView.setGravity(Gravity.CENTER);
        mTextView.setPadding(0,20,0,20);
        mTextView.setTextColor(Color.BLACK);
        return mTextView;
    }
}
