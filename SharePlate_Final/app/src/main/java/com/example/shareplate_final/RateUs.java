package com.example.shareplate_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class RateUs extends AppCompatActivity {

    Button submit,cancel;
    Button bt1,bt2,bt3,bt4,bt5;
    EditText feed;
    String rate,rev;
    String rateList[]=new String[50];
    String nameList[]=new String[50];
    String feedList[]=new String[50];
    ListView lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_us);
        submit = findViewById(R.id.btn_submit);
        cancel = findViewById(R.id.btn_cancel);
        bt1= findViewById(R.id.btn_1);
        bt2= findViewById(R.id.btn_2);
        bt3= findViewById(R.id.btn_3);
        bt4= findViewById(R.id.btn_4);
        bt5= findViewById(R.id.btn_5);
        feed=findViewById(R.id.txt_feed);
        lst = findViewById(R.id.lst_rate);
        LoadReview();

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt1.setBackgroundColor(Color.parseColor("#FFCA0919"));
                bt2.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                bt3.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                bt4.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                bt5.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                rate=bt1.getText().toString();

            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt1.setBackgroundColor(Color.parseColor("#E25D22"));
                bt2.setBackgroundColor(Color.parseColor("#E25D22"));
                bt3.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                bt4.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                bt5.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                rate=bt2.getText().toString();

            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt1.setBackgroundColor(Color.parseColor("#FFECCF29"));
                bt2.setBackgroundColor(Color.parseColor("#FFECCF29"));
                bt3.setBackgroundColor(Color.parseColor("#FFECCF29"));
                bt4.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                bt5.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                rate=bt3.getText().toString();

            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt1.setBackgroundColor(Color.parseColor("#FF1EA727"));
                bt2.setBackgroundColor(Color.parseColor("#FF1EA727"));
                bt3.setBackgroundColor(Color.parseColor("#FF1EA727"));
                bt4.setBackgroundColor(Color.parseColor("#FF1EA727"));
                bt5.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                rate=bt4.getText().toString();

            }
        });
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt1.setBackgroundColor(Color.parseColor("#FF04790C"));
                bt2.setBackgroundColor(Color.parseColor("#FF04790C"));
                bt3.setBackgroundColor(Color.parseColor("#FF04790C"));
                bt4.setBackgroundColor(Color.parseColor("#FF04790C"));
                bt5.setBackgroundColor(Color.parseColor("#FF04790C"));
                rate=bt5.getText().toString();

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Global_use.global_utype.equals("receiver")){
                    Intent intent = new Intent(RateUs.this,Receiver_Home.class);
                    startActivity(intent);
                }
                if(Global_use.global_utype.equals("donor")){
                    Intent intent = new Intent(RateUs.this,Donor_Home.class);
                    startActivity(intent);

                }
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rev = feed.getText().toString();
                submitReview();
            }
        });

    }
    public void LoadReview(){

            StringRequest stringRequest = new StringRequest(Request.Method.POST,"http://192.168.39.74/SharePlate/ratelist.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String ServerResponse) {
                            if(ServerResponse.equals("1")){
                                Toast.makeText(getApplicationContext(), "No Data Found!", Toast.LENGTH_LONG).show();
                            }else{
                                try {
                                    JSONArray jsonarray = new JSONArray(ServerResponse);
                                    for (int i = 0; i < jsonarray.length(); i++) {
                                        JSONObject jsonobject = jsonarray.getJSONObject(i);
                                        nameList[i] = jsonobject.getString("name");
                                        rateList[i] = jsonobject.getString("rate");
                                        feedList[i] = jsonobject.getString("feed");
                                    }
                                    RateAdapter cartAdapter = new RateAdapter(getApplicationContext() ,nameList,rateList,feedList);
                                    lst.setAdapter(cartAdapter);
                                }
                                catch (JSONException e){
                                    Toast.makeText(getApplicationContext(),"Ooopss...! Error...!",Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Toast.makeText(getApplicationContext(), volleyError.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("r","hello");
                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);

    }
    public void submitReview(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.39.74/SharePlate/rating.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        feed.setText("");
                        bt1.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                        bt2.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                        bt3.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                        bt4.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                        bt5.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                        if(Global_use.global_utype.equals("receiver")){
                            Intent intent = new Intent(RateUs.this,Receiver_Home.class);
                            startActivity(intent);
                        }
                        if(Global_use.global_utype.equals("donor")){
                            Intent intent = new Intent(RateUs.this,Donor_Home.class);
                            startActivity(intent);

                        }

                        Toast.makeText(getApplicationContext(), ServerResponse, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        Toast.makeText(getApplicationContext(), volleyError.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.
                params.put("rate", rate);
                params.put("feed", rev);
                params.put("uid", Global_use.global_id);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }
}