package com.example.shareplate_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Test1 extends AppCompatActivity {

    EditText name,email,phone;
    String sname,sedu,semail,sphone;
    Button b1;
    Spinner edu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
         name =(EditText) findViewById(R.id.name);
         email=(EditText) findViewById(R.id.email);
         phone=(EditText) findViewById(R.id.phone);
         b1 =(Button) findViewById(R.id.button);
         edu=(Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adp = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.education, R.layout.selected_item);
        adp.setDropDownViewResource(R.layout.drop_down);
        edu.setAdapter(adp);
         b1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 sname = name.getText().toString();
                 sphone = phone.getText().toString();
                 semail = email.getText().toString();
                 sedu=edu.getSelectedItem().toString();

                 //Toast.makeText(Test1.this, ""+sname, Toast.LENGTH_SHORT).show();
                 register();
             }
         });







    }
    public void register(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.39.74/SharePlate/test1.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        name.setText("");
                        email.setText("");
                        phone.setText("");

                        Intent in = new Intent(Test1.this, Login.class);
                        startActivity(in);
                        Toast.makeText(Test1.this, ServerResponse, Toast.LENGTH_SHORT).show();
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
                params.put("name", sname);
                params.put("email", semail);
                params.put("phone", sphone);

                params.put("education",sedu);

                return params;
            }

        };

        // Creating RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);
    }
}