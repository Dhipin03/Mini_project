package com.example.shareplate_final;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BookAdapter extends BaseAdapter {
    Context context;
    String nameList[], amountList[], dateList[],descList[];
    LayoutInflater inflter;

    public BookAdapter(Context applicationContext, String[] nameList, String[] amountList, String[] dateList, String[] descList) {
        this.context = applicationContext;
        this.nameList=nameList;
        this.dateList = dateList;
        this.amountList = amountList;
        this.descList = descList;
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
        view = inflter.inflate(R.layout.lst_sponsor, null);
        TextView order =(TextView) view.findViewById(R.id.txt_name);
        TextView name =(TextView) view.findViewById(R.id.txt_amount);
        TextView contact =(TextView) view.findViewById(R.id.txt_desc);
        TextView stat =(TextView) view.findViewById(R.id.txt_date);
        order.setText(nameList[i]);
        name.setText(amountList[i]);
        contact.setText(dateList[i]);
        stat.setText(descList[i]);
        return view;
    }

}
