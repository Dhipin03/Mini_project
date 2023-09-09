package com.example.shareplate_final;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Forgot2 extends AppCompatActivity {

    EditText currentp,newp,confirmp;
    String scur,snew,scon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot2);
        Button save = (Button) findViewById(R.id.save);
        currentp =(EditText) findViewById(R.id.currentpwd);
        newp =(EditText)findViewById(R.id.newpwd);
        confirmp =(EditText) findViewById(R.id.cpwd);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scur = currentp.getText().toString();
                snew = newp.getText().toString();
                scon = confirmp.getText().toString();
                if(scur.equals(Global_use.global_pwd)){
                    if(snew.equals(scon)){
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.39.74/SharePlate/changepwd.php",
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String ServerResponse) {
                                        currentp.setText("");
                                        newp.setText("");
                                        confirmp.setText("");
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
                                params.put("pwd", snew);
                                params.put("id",Global_use.global_id);
                                return params;
                            }
                        };

                        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(stringRequest);
                    }
                    else {
                        Toast.makeText(Forgot2.this, "Password doesn't match. Try Again..!", Toast.LENGTH_SHORT).show();
                        newp.setText("");
                        confirmp.setText("");
                    }
                }
                else{
                    Toast.makeText(Forgot2.this, "Your Current Password Is Incorrect..!", Toast.LENGTH_SHORT).show();
                    currentp.setText("");
                    newp.setText("");
                    confirmp.setText("");
                }
            }
        });
    }
    @Override
    public void onBackPressed(){

        finish();
        super.onBackPressed();
    }

}