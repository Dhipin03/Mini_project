package com.example.shareplate_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Donation_Detail extends AppCompatActivity {

    String sname,stype,sdate,stime,sremains,slocation,scontact,sid,sqty,sdietary;
    TextView name,type,date,remains,location,contact;
    EditText qty;
    int remain,qt;

    Button book,cancel;
    String http,url,st;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_detail);

        http ="http://192.168.39.74/SharePlate/book.php";
        url ="http://192.168.39.74/SharePlate/remainupdate.php";

        name =(TextView)findViewById(R.id.name);
        type =(TextView)findViewById(R.id.type);
        date =(TextView)findViewById(R.id.date);
        remains =(TextView)findViewById(R.id.remains);
        location =(TextView)findViewById(R.id.location);
        contact =(TextView)findViewById(R.id.contact);

        qty =(EditText)findViewById(R.id.qty);

        book =(Button)findViewById(R.id.book);
        cancel =(Button)findViewById(R.id.cancel);


        Intent intent = getIntent();
        sname = intent.getStringExtra("name");
        stype = intent.getStringExtra("type");
        sid = intent.getStringExtra("id");
        sremains = intent.getStringExtra("remains");
        slocation = intent.getStringExtra("location");
        scontact = intent.getStringExtra("contact");
        sdate = intent.getStringExtra("date");
        stime = intent.getStringExtra("time");
        sdietary = intent.getStringExtra("dietary");

        name.setText(sname);
        type.setText(sdietary +"  "+ stype);
        date.setText("Date: "+sdate+" Time: "+stime);
        remains.setText("Remaining Quantity: "+sremains);
        location.setText("Location: "+slocation);
        contact.setText("Contact: "+scontact);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqty = qty.getText().toString();
                if(sqty.equals("")||sqty.equals(" ")){
                    Toast.makeText(Donation_Detail.this, "PLease Enter Quantity You Need..!", Toast.LENGTH_SHORT).show();
                }
                else {
                    qt = Integer.parseInt(sqty);
                    remain = Integer.parseInt(sremains);
                    if(qt>remain){
                        qty.setText("");
                        Toast.makeText(Donation_Detail.this, "Sorry, Please Check Remaining Quantity...!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        bookOrder(http);
                    }
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(),Receiver_Home.class);
                startActivity(in);
            }
        });
    }
    public void bookOrder(String HttpUrl){
        int r = remain-qt;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        qty.setText("");
                        remains.setText("Remains: "+r);
                        Toast.makeText(getApplicationContext(), ServerResponse, Toast.LENGTH_SHORT).show();
                        UpdateRemain(url);
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

                params.put("d_id", sid);
                params.put("r_id", Global_use.global_id);
                params.put("qty", sqty);
                params.put("stat","Booked");
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
    public void UpdateRemain(String HttpUrl){
        int r = remain-qt;
        String sr = r+"";
        if(r!=0)
            st ="Some Remains";
        else
            st ="Finished";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        Intent in = new Intent(getApplicationContext(),Receiver_Home.class);
                        startActivity(in);

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

                params.put("did", sid);
                params.put("remains",sr);
                params.put("stat",st);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }
    @Override
    public void onBackPressed(){

        finish();
        super.onBackPressed();
    }

}