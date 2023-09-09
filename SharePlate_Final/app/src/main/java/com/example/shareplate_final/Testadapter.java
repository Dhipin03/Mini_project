package com.example.shareplate_final;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

    public class Testadapter extends BaseAdapter {
        Context context;
        String nameList[];
        String emailList[];
        String phoneList[];
        LayoutInflater inflter;

        public Testadapter(Context applicationContext, String[] nameList,String[] phonelist ,String[] emailList) {
            this.context = context;
            this.nameList=nameList;
            this.phoneList=phonelist;
            this.emailList = emailList;
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
            view = inflter.inflate(R.layout.list_user, null);
            TextView name =(TextView) view.findViewById(R.id.txt_name);
            TextView phone =(TextView) view.findViewById(R.id.txt_type);
            TextView email =(TextView) view.findViewById(R.id.txt_email);

            name.setText(nameList[i]);
            phone.setText(phoneList[i]);
            email.setText(emailList[i]);

            return view;
        }

    }

