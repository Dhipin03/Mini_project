package com.example.shareplate_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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

public class Signup extends AppCompatActivity {
    Button signup;
    EditText name,email,ph,add,pin,pwd,cpwd;
    int status=0;
    RequestQueue requestQueue;
    String sname,semail,sph,sadd,spin,spwd,scpwd,utype="donor";
    String MobilePattern = "[0-9]{10}";
    String namePattern = "[ .a-zA-Z]+";

    String pinPattern = "[0-9]{6}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        TextView log = (TextView)findViewById(R.id.log);
        requestQueue = Volley.newRequestQueue(Signup.this);
        name = (EditText) findViewById(R.id.uname);
        email = (EditText) findViewById(R.id.email);
        ph = (EditText) findViewById(R.id.phone);
        add = (EditText) findViewById(R.id.address);
        pin = (EditText) findViewById(R.id.pin);
        pwd = (EditText) findViewById(R.id.pwd);
        cpwd = (EditText) findViewById(R.id.cpwd);
        signup =(Button)findViewById(R.id.signup);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Signup.this, Login.class);
                startActivity(in);
                finish();

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getValue();
            }
        });

    }
    @Override
    public void onBackPressed(){
        finish();
    }


    public void getValue(){
        sname = name.getText().toString();
        semail = email.getText().toString();
        sph = ph.getText().toString();
        sadd = add.getText().toString();
        spin = pin.getText().toString();
        spwd = pwd.getText().toString();
        scpwd = cpwd.getText().toString();
        if (sname.matches(namePattern)) {
            if(Patterns.EMAIL_ADDRESS.matcher(semail).matches())
            {
                if(sph.matches(MobilePattern)){
                    if(spin.matches(pinPattern)){
                        signupdonor();
                    }
                    else {
                        pin.setError("Pin Number is Not Valid..!");
                    }
                }
                else {
                    ph.setError("Phone Number Is Not Valid..!");
                }
            }
            else {
                email.setText("");
                email.setError("Email ID Is Not Valid..!");
            }
        }
        else {
//            name.setText("");
            name.setError("Name Is Not Valid..!");
        }
    }
    public void signupdonor(){
        if(sname.equals("")||semail.equals("")||sph.equals("")||sadd.equals("")||spin.equals("")||spwd.equals("")||scpwd.equals("")){
            Toast.makeText(Signup.this, "Enter All The Details...! ", Toast.LENGTH_SHORT).show();
        }
        else{
            if (spwd.equals(scpwd)) {
                checkEmail(semail);
            }
            else {
                Toast.makeText(getApplicationContext(),"Password Doesn't Match, Re-Enter Password...!",Toast.LENGTH_LONG).show();
                pwd.setText("");
                cpwd.setText("");
            }
        }

    }
    private void checkEmail(final String mail){

        final StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.39.74/SharePlate/email.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.equals("1")){
                    status=1;
                    register();
                }else{
                    status=0;
                    Toast.makeText(Signup.this, "This Email Id is Already Registered...!", Toast.LENGTH_SHORT).show();
                    email.setText("");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Signup.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("email", mail);
                return map;
            }
        };

        requestQueue.add(stringRequest);
    }
    public void register(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.39.74/SharePlate/register.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        name.setText("");
                        email.setText("");
                        add.setText("");
                        pwd.setText("");
                        cpwd.setText("");
                        ph.setText("");
                        pin.setText("");
                        Intent in = new Intent(Signup.this, Login.class);
                        startActivity(in);
                        Toast.makeText(Signup.this, ServerResponse, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        Toast.makeText(Signup.this, volleyError.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.
                params.put("name", sname);
                params.put("email", semail);
                params.put("phone", sph);
                params.put("address", sadd);
                params.put("pin", spin);
                params.put("pwd", spwd);
                params.put("utype",utype);
                params.put("type","d");
                params.put("upi","NIL");
                return params;
            }

        };

        // Creating RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(Signup.this);

        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);
    }

}