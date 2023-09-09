package com.example.shareplate_final;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SponsorFragment extends Fragment {

    View myview;
    ListView lst_names;
    Spinner rtype;

    String rt="";
    int l;
    String nameList[] = new String[20];
    String pinList[] = new String[20];
    String phoneList[] = new String[20];
    String emailList[] = new String[20];
    String idList[] = new String[20];
    String upiList[] = new String[20];
    String locList[] = new String[200];


    String HttpUrl = "http://192.168.39.74/SharePlate/receiver.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myview = inflater.inflate(R.layout.fragment_sponsor, container, false);

        rtype = myview.findViewById(R.id.rtype);
        lst_names = myview.findViewById(R.id.lst_names);

        ArrayAdapter<CharSequence> adp = ArrayAdapter.createFromResource(getContext(),
                R.array.rtype, R.layout.selected_item);
        adp.setDropDownViewResource(R.layout.drop_down);
        rtype.setAdapter(adp);

        rtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                rt = rtype.getSelectedItem().toString();
                if(rt.equals("Choose Whom you Want to Sponsor?")){
                    Toast.makeText(getContext(), "Please Select A Receiver Type...!", Toast.LENGTH_SHORT).show();
                }
                for(int k=0;k<20;k++){
                    nameList[k] = "";
                    locList[k] = "";
                    idList[k] = "";
                    phoneList[k]="";
                    pinList[k]="";
                    upiList[k]="";
                    emailList[k]="";
                }
                loadList(HttpUrl);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getContext(), "Please Select Receiver Type...!", Toast.LENGTH_SHORT).show();
            }
        });
        lst_names.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(rt.equals("Choose Whom you Want to Sponsor?")){
                    Toast.makeText(getContext(), "Please Select A Receiver Type...!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(getContext(), Receiver_Detail.class);
                    intent.putExtra("id",idList[i]);
                    intent.putExtra("name",nameList[i]);
                    intent.putExtra("address",locList[i]);
                    intent.putExtra("phone",phoneList[i]);
                    intent.putExtra("pin",pinList[i]);
                    intent.putExtra("mail",emailList[i]);
                    intent.putExtra("upi",upiList[i]);
                    intent.putExtra("head",rt);
                    startActivity(intent);
                }
            }
        });

        return myview;
    }
    public void loadList(String HttpURL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,HttpURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        if(ServerResponse.equals("1")){
                            Toast.makeText(getContext(), "No Data Found!", Toast.LENGTH_LONG).show();
                        }else{
                            try {
                                JSONArray jsonarray = new JSONArray(ServerResponse);
                                for (int i = 0; i < jsonarray.length(); i++) {
                                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                                    nameList[i] = jsonobject.getString("name");
                                    locList[i] = jsonobject.getString("address");
                                    upiList[i] = jsonobject.getString("upi");
                                    pinList[i] = jsonobject.getString("pincode");
                                    idList[i] = jsonobject.getString("id");
                                    phoneList[i] = jsonobject.getString("phone");
                                    emailList[i] = jsonobject.getString("email");
                                }
                                ReceiverAdapter cartAdapter = new ReceiverAdapter(getContext(), nameList,locList);
                                lst_names.setAdapter(cartAdapter);
                            }
                            catch (JSONException e){
                                Toast.makeText(getContext(),"Ooopss...! Error...!",Toast.LENGTH_LONG).show();
                            }

                        }
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
                params.put("type", rt);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    }
