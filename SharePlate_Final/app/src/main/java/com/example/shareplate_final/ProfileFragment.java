package com.example.shareplate_final;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class ProfileFragment extends Fragment {

    View myview;
    EditText name,email,address,phone,pin;
    String sname,semail,saddress,sphone,spin;
    ImageButton edit,clear,save,logout;
    Button change;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myview = inflater.inflate(R.layout.fragment_profile, container, false);

        name = myview.findViewById(R.id.name);
        email = myview.findViewById(R.id.email);
        address = myview.findViewById(R.id.address);
        phone = myview.findViewById(R.id.phone);
        pin = myview.findViewById(R.id.pin);

        edit = myview.findViewById(R.id.edit);
        clear = myview.findViewById(R.id.clear);
        save = myview.findViewById(R.id.save);
        logout = myview.findViewById(R.id.logout);
        change = myview.findViewById(R.id.change);

        disabled();
        setValue();

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in =new Intent(getContext(),Forgot2.class);
                startActivity(in);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enabled();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    getValue();
                    if(sname.equals("")||semail.equals("")||spin.equals("")||saddress.equals("")||sphone.equals("")){
                        Toast.makeText(getContext(), "Please Enter All the Details..!", Toast.LENGTH_SHORT).show();

                    }
                    else {
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.39.74/SharePlate/editprofile.php",
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String ServerResponse) {
                                        name.setText(sname);
                                        email.setText(semail);
                                        address.setText(saddress);
                                        phone.setText(sphone);
                                        pin.setText(spin);
                                        Global_use.global_email=semail;
                                        Global_use.global_name = sname;
                                        Global_use.global_pincode = spin;
                                        Global_use.global_phone = sphone;
                                        Global_use.global_address=saddress;
                                        disabled();
                                        Toast.makeText(getContext(), ServerResponse, Toast.LENGTH_SHORT).show();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError volleyError) {

                                        Toast.makeText(getContext(), volleyError.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                }) {
                            @Override
                            protected Map<String, String> getParams() {

                                Map<String, String> params = new HashMap<String, String>();

                                params.put("name", sname);
                                params.put("email", semail);
                                params.put("phone", sphone);
                                params.put("address", saddress);
                                params.put("pin", spin);
                                params.put("id",Global_use.global_id);
                                return params;
                            }

                        };

                        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                        requestQueue.add(stringRequest);

                    }

            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disabled();
                setValue();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in =new Intent(getContext(),Login.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(in);
            }
        });



        name.setText(Global_use.global_name);
        email.setText(Global_use.global_email);
        address.setText(Global_use.global_address);
        phone.setText(Global_use.global_phone);
        pin.setText(Global_use.global_pincode);

        return myview;
    }
    public void enabled(){
        name.setEnabled(true);
        email.setEnabled(true);
        address.setEnabled(true);
        phone.setEnabled(true);
        pin.setEnabled(true);

    }
    public void disabled(){
        name.setEnabled(false);
        email.setEnabled(false);
        address.setEnabled(false);
        phone.setEnabled(false);
        pin.setEnabled(false);

    }
    public void setValue(){
        name.setText(Global_use.global_name);
        email.setText(Global_use.global_email);
        address.setText(Global_use.global_address);
        phone.setText(Global_use.global_phone);
        pin.setText(Global_use.global_pincode);
    }
    public void getValue(){
        spin = pin.getText().toString();
        semail = email.getText().toString();
        saddress = address.getText().toString();
        sname = name.getText().toString();
        sphone = phone.getText().toString();

    }
}