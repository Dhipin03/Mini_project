package com.example.shareplate_final;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Forgotpassword extends AppCompatActivity {
    Button submit;
    EditText email;
    String semail,sph,spass;
   // String ss="getmedhipin007@gmail.com";
    RequestQueue requestQueue;
    String HttpUrl = "http://192.168.39.74/SharePlate/forgot.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        requestQueue = Volley.newRequestQueue(Forgotpassword.this);
        submit =(Button) findViewById(R.id.submit);
        email =(EditText) findViewById(R.id.email);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                semail = email.getText().toString().trim();
                if(semail.isEmpty()){
                    email.setError("Please Enter Email..!");
                }
                else {
                   getPass(semail);
//                       sendEmail();
                }
            }
        });
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
    public void sendEmail(){
        final String user="shareplateofficial@gmail.com";
        final String pwd="kooqfwrkitjsdymh";
        String message ="Hi User,\n\nYour Account Password is : "+spass+"\n\n\nGreetings From\nSharePlate";
        Properties props = new Properties();
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port","587");
        Session session = Session.getInstance(props,new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(user,pwd);
            }
        });
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(user));
            msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(semail));
            msg.setSubject("Password Recovery");
            msg.setText(message);
            Transport.send(msg);
            Toast.makeText(Forgotpassword.this, "Email send successfully...!", Toast.LENGTH_SHORT).show();
            email.setText("");
        }
        catch (MessagingException e){
            throw new RuntimeException(e);
        }
    }
    private void getPass(final String mail){

        final StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.equals("1")){
                    email.setText("");
                    Toast.makeText(Forgotpassword.this, "No Account Registered with this Email...!", Toast.LENGTH_LONG).show();
                }else{
                    try {
                        JSONArray jsonarray = new JSONArray(response);
                        for (int i = 0; i < jsonarray.length(); i++) {

                            JSONObject jsonobject = jsonarray.getJSONObject(i);
                            semail =jsonobject.getString("email");
                            spass =jsonobject.getString("pwd");
                            sph =jsonobject.getString("phone");
                            Toast.makeText(Forgotpassword.this, "pwd: "+spass, Toast.LENGTH_SHORT).show();
                            sendEmail();
                        }
                    }
                    catch (JSONException e){
                        Toast.makeText(Forgotpassword.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Forgotpassword.this, error.toString(), Toast.LENGTH_LONG).show();
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
//        sendEmail();
    }
    @Override
    public void onBackPressed(){

        finish();
        super.onBackPressed();
    }

}