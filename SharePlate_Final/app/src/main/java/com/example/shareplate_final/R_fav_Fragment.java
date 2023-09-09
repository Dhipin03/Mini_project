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

public class R_fav_Fragment extends Fragment {

    View myview;

    ListView lst_book;
    String sdate,st;
    String http,url;
    String bid,sr,did;
    int bq,remain;

    String idList[] = new String[100];
    String didList[] = new String[100];
    String nameList[] = new String[100];
    String typeList[] = new String[100];
    String statList[] = new String[100];
    String qtyList[] = new String[100];
    String locationList[] = new String[100];
    String contactList[] = new String[100];
    String remainsList[] = new String[100];

    String HttpUrl = "http://192.168.39.74/SharePlate/rbooklist.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myview = inflater.inflate(R.layout.fragment_r_fav_, container, false);
        lst_book = myview.findViewById(R.id.lst_items);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar c = Calendar.getInstance();
        sdate = sdf.format(c.getTime());

        http ="http://192.168.39.74/SharePlate/cancel_book.php";
        url ="http://192.168.39.74/SharePlate/remainupdate.php";

        lst_book.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                bid=idList[i];
                sr = qtyList[i];
                did = didList[i];
                bq = Integer.parseInt(sr);
                remain = Integer.parseInt(remainsList[i]);


                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Do you want to cancel ?");
                builder.setTitle("Cancellation !");
                builder.setCancelable(false);
                builder.setIcon(R.drawable.cancel24);
                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                        cancelOrder(http);
                });
                builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                    dialog.cancel();
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.red));
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.link));

//                Toast.makeText(getContext(), ""+nameList[i], Toast.LENGTH_SHORT).show();

            }
        });

        loadOrderList(HttpUrl);
        return myview;
    }

    public void cancelOrder(String HttpUrl){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        UpdateRemain(url);
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

                params.put("bid", bid);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
    public void UpdateRemain(String HttpUrl){

        remain = remain+bq;
        String sr = remain+"";
        if(remain!=0)
            st ="Some Remains";
        else
            st ="Finished";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        Intent in = new Intent(getContext(),Receiver_Home.class);
                        startActivity(in);

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
                params.put("did", did);
                params.put("stat",st);
                params.put("remains",sr);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

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
                                    contactList[i] = jsonobject.getString("contact");
                                    idList[i]=jsonobject.getString("id");
                                    didList[i] = jsonobject.getString("did");
                                    statList[i]=jsonobject.getString("stat");
                                    remainsList[i] = jsonobject.getString("remains");
                                    typeList[i]=typeList[i]+": "+qtyList[i];
                                }
                                BookingsAdapter cartAdapter = new BookingsAdapter(getContext(), nameList,typeList,statList,contactList,locationList);
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
                params.put("date", sdate);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

}