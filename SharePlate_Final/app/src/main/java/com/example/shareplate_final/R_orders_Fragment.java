package com.example.shareplate_final;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class R_orders_Fragment extends Fragment {

    View myview;
    ListView lst_book;

    String idList[] = new String[50];
    String nameList[] = new String[50];
    String typeList[] = new String[50];
    String statList[] = new String[50];
    String qtyList[] = new String[50];
    String locationList[] = new String[50];
    String dateList[] = new String[50];

    String HttpUrl = "http://192.168.39.74/SharePlate/rhistory.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myview = inflater.inflate(R.layout.fragment_r_orders_, container, false);
        lst_book = myview.findViewById(R.id.lst_items);
        loadOrderList(HttpUrl);

        return myview;
    }
    public void loadOrderList(String HttpURL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,HttpURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        if(ServerResponse.equals("1")){
                            Toast.makeText(getContext(), "No Data Found!", Toast.LENGTH_LONG).show();
                        }else{
                            try {
                                JSONArray jsonarray = new JSONArray(ServerResponse);
                                for (int i = 0; i < jsonarray.length(); i++) {
                                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                                    nameList[i] = jsonobject.getString("name");
                                    typeList[i] = jsonobject.getString("type");
                                    qtyList[i] = jsonobject.getString("qty");
                                    locationList[i] = jsonobject.getString("loc");
                                    dateList[i] = jsonobject.getString("date");
                                    idList[i]=jsonobject.getString("id");
                                    statList[i]=jsonobject.getString("stat");
                                    typeList[i]=typeList[i]+": "+qtyList[i];
                                }
                                BookingsAdapter cartAdapter = new BookingsAdapter(getContext(), nameList,typeList,statList,dateList,locationList);
                                lst_book.setAdapter(cartAdapter);
                            }
                            catch (JSONException e){
                                Toast.makeText(getContext(),"Ooopss...! Error...!",Toast.LENGTH_LONG).show();
                            }

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getContext(), volleyError.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("did", Global_use.global_id);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

}