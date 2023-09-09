package com.example.shareplate_final;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ReceiverAdapter extends BaseAdapter {
    Context context;
    String nameList[];
    String locList[];
    LayoutInflater inflter;

    public ReceiverAdapter(Context applicationContext, String[] nameList, String[] locList) {
        this.context = context;
        this.nameList=nameList;
        this.locList = locList;
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
        view = inflter.inflate(R.layout.lst_receiver, null);
        TextView name =(TextView) view.findViewById(R.id.txt_name);
        TextView type =(TextView) view.findViewById(R.id.txt_loc);
        name.setText(nameList[i]);
        type.setText(locList[i]);
        return view;
    }

}
