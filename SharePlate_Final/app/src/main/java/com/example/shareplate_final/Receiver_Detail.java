package com.example.shareplate_final;

import androidx.appcompat.app.AlertDialog;
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
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class Receiver_Detail extends AppCompatActivity implements PaymentResultListener {

    TextView name,head,mail,phone,upi,address;
    EditText amount,desc;
    Button submit,cancel;
    int amt;
    String sname,saddress,shead,smail,sphone,supi,samount,spin,srid,sdesc,sdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver_detail);

        name =(TextView)findViewById(R.id.name);
        head =(TextView)findViewById(R.id.txt_head);
        mail =(TextView)findViewById(R.id.mail);
        phone =(TextView)findViewById(R.id.phone);
        upi =(TextView)findViewById(R.id.txt_upi);
        address =(TextView)findViewById(R.id.address);

        amount =(EditText) findViewById(R.id.amount);
        desc =(EditText) findViewById(R.id.desc);

        submit =(Button) findViewById(R.id.submit);
        cancel =(Button) findViewById(R.id.cancel);

        Intent intent = getIntent();
        sname = intent.getStringExtra("name");
        saddress = intent.getStringExtra("address");
        shead = intent.getStringExtra("head");
        smail = intent.getStringExtra("mail");
        sphone = intent.getStringExtra("phone");
        supi = intent.getStringExtra("upi");
        spin = intent.getStringExtra("pin");
        srid = intent.getStringExtra("id");

        name.setText(sname);
        head.setText(shead+ " Detail" );
        mail.setText("Email : "+ smail);
        phone.setText("Phone : "+sphone);
        upi.setText("UPI : "+supi);
        address.setText(saddress+"\nPincode : "+spin);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount.setText("");
                desc.setText("");
                Intent in = new Intent(getApplicationContext(),Donor_Home.class);
                startActivity(in);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                samount = amount.getText().toString();
                sdesc = desc.getText().toString();
                if(samount.equals("")){
                    amount.setError("Enter the Amount...!");
                }
                else {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    Calendar c = Calendar.getInstance();
                    sdate = sdf.format(c.getTime());
                    amt = Math.round(Float.parseFloat(samount) * 100);

                    Checkout checkout = new Checkout();
                    checkout.setKeyID("rzp_test_0ROZ6EdYZptC01");
                    checkout.setImage(com.razorpay.R.drawable.rzp_logo);
                    JSONObject object = new JSONObject();
                    try {
                        object.put("name",sname);
                        object.put("desc",sdesc);
                        object.put("theme.color","#A7B5D9");
                        object.put("currency","INR");
                        object.put("amount",amt);
                        object.put("prefill.contact",sphone);
                        object.put("prefill.email",supi);
                        checkout.open(Receiver_Detail.this,object);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        });

    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "Payment Successful!\nYour Payment ID: "+s, Toast.LENGTH_SHORT).show();
        amount.setText("");
        desc.setText("");
        sponsor();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment Failed...!", Toast.LENGTH_SHORT).show();
        amount.setText("");
        desc.setText("");
    }
    public void sponsor(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.39.74/SharePlate/sponsor.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        Toast.makeText(getApplicationContext(), ServerResponse, Toast.LENGTH_SHORT).show();
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

                params.put("sp_id", Global_use.global_id);
                params.put("rec_id", srid);
                params.put("amount", samount);
                params.put("desc", sdesc);
                params.put("date", sdate);
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