package com.example.shareplate_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.protobuf.LazyStringArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import kotlin.math.UMathKt;

public class Test extends AppCompatActivity {
    Button b1;
    String nameList[]=new String[100];
    String phoneList[]=new String[100];
    String emailList[]=new  String[100];

    Spinner edu;
    EditText phone,name,email;
    String sphone,sname,semail,sedu;

    String phonepattern="[0-9]{10}";
    String namepattern="[ .a-zA-Z]+";
    ListView tst1;
    String HttpURL="http://192.168.39.74/SharePlate/test.php";;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        b1=(Button)findViewById(R.id.click_bt);
        phone =(EditText) findViewById(R.id.txt_phone);
        email =(EditText) findViewById(R.id.txt_email);
        name =(EditText) findViewById(R.id.txt_name);
        edu=(Spinner) findViewById(R.id.txt_education);
        tst1=(ListView) findViewById(R.id.lst_user) ;

        ArrayAdapter<CharSequence> adp = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.education, R.layout.selected_item);
        adp.setDropDownViewResource(R.layout.drop_down);
        edu.setAdapter(adp);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getvalue();
               // loadList(HttpURL);

            }
        });

    }
    public void getvalue(){
        sphone=phone.getText().toString();
        sname=name.getText().toString();
        semail=email.getText().toString();
        sedu=edu.getSelectedItem().toString();

        if(sphone.equals("")||sname.equals("")||sedu.equals("Choose Your Educational Status")||semail.equals("")){
            Toast.makeText(this, " Please Enter", Toast.LENGTH_SHORT).show();
        }
        else{
            if(sphone.matches(phonepattern)){
                if(sname.matches(namepattern)){
                    if(Patterns.EMAIL_ADDRESS.matcher(semail).matches()){

                        register();
                       // Intent click=new Intent(getApplicationContext(),First.class);
                       // startActivity(click);
                    }
                    else{
                        Toast.makeText(this, "Invalid email", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(this, "Invalid name", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Invalid phone", Toast.LENGTH_SHORT).show();
            }

        }

    }


    public void loadList(String HttpURL){
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
                                    phoneList[i] = jsonobject.getString("phone");
                                    emailList[i] = jsonobject.getString("email");
                                }
                                Testadapter Adapter = new Testadapter(getApplicationContext(), nameList,phoneList,emailList);
                                tst1.setAdapter(Adapter);
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
               // params.put("type", rt);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
    public void register(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.39.74/SharePlate/test1.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        name.setText("");
                        email.setText("");
                        phone.setText("");

                        Intent in = new Intent(Test.this, Login.class);
                        startActivity(in);
                        Toast.makeText(Test.this, ServerResponse, Toast.LENGTH_SHORT).show();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
