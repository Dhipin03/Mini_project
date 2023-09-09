package com.example.shareplate_final;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
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


public class Donor_donate_detail extends AppCompatActivity {
    TextView name,type,remains;
    Button cancel;
    String bid;
    String HttpUrl = "http://192.168.39.74/SharePlate/booklist.php";
    String http = "http://192.168.39.74/SharePlate/confirm.php";

    String nameList[] = new String[100];
    String idList[] = new String[100];
    String qtyList[] = new String[100];
    String snameList[] = new String[100];
    String sidList[] = new String[100];
    String sqtyList[] = new String[100];
    String statList[] = new String[100];
    String sname,stype,sremains,sid;
    ListView lst_orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_donate_detail);
        name =(TextView)findViewById(R.id.name);
        type =(TextView)findViewById(R.id.type);
        remains=(TextView)findViewById(R.id.remains);
        lst_orders =(ListView) findViewById(R.id.lst_orders);
        cancel =(Button)findViewById(R.id.cancel);

        Intent intent = getIntent();
        sname = intent.getStringExtra("name");
        stype = intent.getStringExtra("type");
        sremains = intent.getStringExtra("remains");
        sid = intent.getStringExtra("id");
       // Toast.makeText(getApplicationContext(), ""+sid, Toast.LENGTH_SHORT).show();

        name.setText(sname);
        type.setText(stype);
        remains.setText("Remaining Qty: "+sremains);
        loadDonateList(HttpUrl);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(),Donor_Home.class);
                startActivity(in);
            }
        });


        lst_orders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                bid = idList[i];
                AlertDialog.Builder builder = new AlertDialog.Builder(Donor_donate_detail.this);
                builder.setMessage("Order Picked ?");
                builder.setTitle("Confirmation !");
                builder.setCancelable(false);
                builder.setIcon(R.drawable.confirm);
                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                        orderUpdate(http);
                });
                builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                    dialog.cancel();
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.green));
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.black));

            }
        });

    }
    public void orderUpdate(String HttpUrl){


        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        Intent in = new Intent(getApplicationContext(),Donor_Home.class);
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
                params.put("bid", bid);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }

    public void loadDonateList(String HttpURL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,HttpURL,
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
                                    statList[i] = jsonobject.getString("stat");
                                    qtyList[i] = jsonobject.getString("qty");
                                    idList[i]=jsonobject.getString("id");
                                    snameList[i]= "Receiver: "+nameList[i];
                                    sidList[i]="Booking ID: "+idList[i];
                                    sqtyList[i]="Quantity: "+qtyList[i];
                                }
                                BookAdapter cartAdapter = new BookAdapter(getApplicationContext() ,snameList,sqtyList, sidList ,statList);
                                lst_orders.setAdapter(cartAdapter);
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
                params.put("did", sid);
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