package com.example.shareplate_final;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SponsorAdapter extends BaseAdapter {

    Context context;
    String nameList[], amountList[], dateList[],descList[];
    LayoutInflater inflter;

    public SponsorAdapter(Context applicationContext, String[] nameList, String[] amountList, String[] dateList, String[] descList) {
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
        TextView name =(TextView) view.findViewById(R.id.txt_name);
        TextView amount =(TextView) view.findViewById(R.id.txt_amount);
        TextView desc =(TextView) view.findViewById(R.id.txt_desc);
        TextView date =(TextView) view.findViewById(R.id.txt_date);
        name.setText(nameList[i]);
        amount.setText(amountList[i]);
        date.setText(dateList[i]);
        desc.setText(descList[i]);
        return view;
    }
}
