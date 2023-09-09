package com.example.shareplate_final;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class R_home_Fragment extends Fragment {

    View myview;
    String nameList[] = new String[100];
    String dateList[] = new String[100];
    String typeList[] = new String[100];
    String countList[] = new String[100];
    String statList[] = new String[100];
    String locList[] = new String[100];
    String remainsList[] = new String[100];
    String idList[] = new String[100];
    String timeList[] = new String[100];
    String dietList[] = new String[100];

    String contactList[] = new String[100];

    String sdate,sloc="";
    AutoCompleteTextView location;


    ListView lst_donations;

    String HttpUrl = "http://192.168.39.74/SharePlate/rdonatelist.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myview = inflater.inflate(R.layout.fragment_r_home_, container, false);
        lst_donations = myview.findViewById(R.id.lst_items);
        location = myview.findViewById(R.id.location);


        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar c = Calendar.getInstance();
        sdate = sdf.format(c.getTime());
        loadDonateList(HttpUrl);
        String[] countries = getResources().getStringArray(R.array.places);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,countries);
        location.setAdapter(adapter);
        for(int i=0;i<100;i++){
            nameList[i]="";
            typeList[i]="";
            locList[i]="";
            countList[i]="";
            statList[i]="";
        }

        lst_donations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), Donation_Detail.class);
                intent.putExtra("id",idList[i]);
                intent.putExtra("name",nameList[i]);
                intent.putExtra("type",typeList[i]);
                intent.putExtra("remains",remainsList[i]);
                intent.putExtra("date",dateList[i]);
                intent.putExtra("time",timeList[i]);
                intent.putExtra("location",locList[i]);
                intent.putExtra("contact",contactList[i]);
                intent.putExtra("dietary",dietList[i]);
                startActivity(intent);

            }
        });

        location.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                sloc = location.getText().toString();
                for(int i=0;i<100;i++){
                    nameList[i]="";
                    typeList[i]="";
                    locList[i]="";
                    countList[i]="";
                    statList[i]="";
                }
                loadDonateList(HttpUrl);
            }
        });

        return myview;
    }

    public void loadDonateList(String HttpURL){
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
                                    typeList[i] = jsonobject.getString("type");
                                    countList[i] = jsonobject.getString("count");
                                    dateList[i] = jsonobject.getString("date");
                                    statList[i] = jsonobject.getString("status");
                                    remainsList[i] = jsonobject.getString("remains");
                                    locList[i] = jsonobject.getString("location");
                                    idList[i]=jsonobject.getString("id");
                                    timeList[i] = jsonobject.getString("time");
                                    dietList[i] = jsonobject.getString("dietary");
                                    contactList[i] = jsonobject.getString("contact");
                                }
                                DonationAdapter cartAdapter = new DonationAdapter(getContext(), nameList,typeList,locList,remainsList,statList);
                                lst_donations.setAdapter(cartAdapter);
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
                params.put("date",sdate );
                params.put("loc",sloc);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

}