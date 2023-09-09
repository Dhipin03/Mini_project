package com.example.shareplate_final;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BookingsAdapter extends BaseAdapter {
    Context context;
    String nameList[], typeList[], contactList[],statList[],locList[];
    LayoutInflater inflter;

    public BookingsAdapter(Context applicationContext, String[] nameList, String[] typeList, String[] statList, String[] contactList, String[] locList) {
        this.context = context;
        this.nameList=nameList;
        this.typeList = typeList;
        this.contactList = contactList;
        this.statList = statList;
        this.locList=locList;
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
        view = inflter.inflate(R.layout.lst_donate, null);
        TextView name =(TextView) view.findViewById(R.id.txt_name);
        TextView type =(TextView) view.findViewById(R.id.txt_type);
        TextView status =(TextView) view.findViewById(R.id.txt_stat);
        TextView date =(TextView) view.findViewById(R.id.txt_date);
        TextView count =(TextView) view.findViewById(R.id.txt_count);
        name.setText(locList[i]);
        type.setText(nameList[i]);
        date.setText(contactList[i]);
        count.setText(typeList[i]);
        status.setText(statList[i]);
        return view;
    }

}
