package com.example.shareplate_final;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RateAdapter extends BaseAdapter {
    Context context;
    String nameList[];
    String rateList[];
    String feedList[];
    LayoutInflater inflter;

    public RateAdapter(Context applicationContext, String[] nameList,String[] ratelist ,String[] feedList) {
        this.context = context;
        this.nameList=nameList;
        this.rateList=ratelist;
        this.feedList = feedList;
        inflter = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
        return nameList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.lst_rate, null);
        TextView name =(TextView) view.findViewById(R.id.txt_name);
        TextView rate =(TextView) view.findViewById(R.id.txt_rate);
        TextView feed =(TextView) view.findViewById(R.id.txt_feed);

        name.setText(nameList[i]);
        rate.setText(rateList[i]);
        feed.setText(feedList[i]);

        return view;
    }

}
