package com.example.shareplate_final;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

public class Login extends AppCompatActivity {

    String utype,username,pass,ut,semail,saddress,spin,sph,sname,spass,sid,srtype;
    EditText user,pwd;
    Button login;
    RequestQueue requestQueue;
    String HttpUrl = "http://192.168.39.74/SharePlate/login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        requestQueue = Volley.newRequestQueue(Login.this);
        user = (EditText)findViewById(R.id.username);
        pwd = (EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.loginButton);
        TextView signup = (TextView) findViewById(R.id.signupText);
        TextView forgot = (TextView) findViewById(R.id.forgot);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = user.getText().toString().trim();
                pass = pwd.getText().toString().trim();
//                Toast.makeText(MainActivity.this, username+" "+pass, Toast.LENGTH_SHORT).show();
                if (!username.isEmpty()||!pass.isEmpty()){
                    userLogin(username,pass);

                    //       Toast.makeText(getApplicationContext(),mail+" "+pwd,Toast.LENGTH_LONG).show();
                }
                else{
                    user.setError("Please Enter Email");
                    pwd.setError("Please Enter Password");
                }


            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Login.this, Forgotpassword.class);
                startActivity(in);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int[] checkedItem = {-1};
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Login.this);
                alertDialog.setIcon(R.drawable.person);
                alertDialog.setTitle("Sign up as");
                final String[] listItems = new String[]{"Donor", "Receiver"};
                alertDialog.setSingleChoiceItems(listItems, checkedItem[0], (dialog, which) -> {
                    checkedItem[0] = which;
                    utype = listItems[which];
                    if(utype.equals("Donor")){
                        Intent in = new Intent(Login.this, Signup.class);
                        startActivity(in);
                    }
                    else if(utype=="Receiver"){
                        Intent in = new Intent(Login.this, Signupreciever.class);
                        startActivity(in);
                    }

                    dialog.dismiss();
                });
                alertDialog.setNegativeButton("Cancel", (dialog, which) -> {
                });
                AlertDialog customAlertDialog = alertDialog.create();
                customAlertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        customAlertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.purple_700));
                    }
                });
                customAlertDialog.show();

            }
        });

    }


    private void userLogin(final String mail, final String password){

        final StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.equals("1")){
                    user.setText("");
                    pwd.setText("");
                    Toast.makeText(Login.this, "Invalid User Name or Password... Re-Enter the Details", Toast.LENGTH_LONG).show();
                }else{
                    try {
                        JSONArray jsonarray = new JSONArray(response);
                        for (int i = 0; i < jsonarray.length(); i++) {

                            JSONObject jsonobject = jsonarray.getJSONObject(i);
                            ut =jsonobject.getString("utype");
                            sid =jsonobject.getString("id");
                            sname =jsonobject.getString("name");
                            semail =jsonobject.getString("email");
                            spass =jsonobject.getString("pwd");
                            saddress =jsonobject.getString("address");
                            sph =jsonobject.getString("phone");
                            srtype=jsonobject.getString("r_type");
                            spin = jsonobject.getString("pincode");
                            Toast.makeText(Login.this, "Welcome "+ut, Toast.LENGTH_SHORT).show();
                            pwd.setText("");
                            user.setText("");
                            setGlobal();
                        }
                    }
                    catch (JSONException e){
                        Toast.makeText(Login.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }

                    if(ut.equals("donor")){
                        Intent in = new Intent(Login.this, Donor_Home.class);
                        startActivity(in);
                    }
                    else if(ut.equals("receiver")){
                        Intent in = new Intent(Login.this, Receiver_Home.class);
                        startActivity(in);
                    }


                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("email", mail);
                map.put("pwd", password);
                return map;
            }
        };

        requestQueue.add(stringRequest);
    }
    public void setGlobal(){
            Global_use.global_id=sid;
            Global_use.global_name=sname;
            Global_use.global_address=saddress;
            Global_use.global_phone=sph;
            Global_use.global_pincode=spin;
            Global_use.global_pwd=spass;
            Global_use.global_utype=ut;
            Global_use.global_email = semail;
    }
    @Override
    public void onBackPressed(){

        finish();
        super.onBackPressed();
    }

}