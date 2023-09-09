package com.example.shareplate_final;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

public class HomeFragment extends Fragment {

    View myview;
    ListView lst_donate;
    Button sponsor,donate;
    TextView head;

    String nameList[] = new String[100];
    String dateList[] = new String[100];
    String typeList[] = new String[100];
    String countList[] = new String[100];
    String statList[] = new String[100];
    String amountList[] = new String[100];
    String descList[] = new String[100];
    String remainsList[] = new String[100];
    String idList[] = new String[100];


    String HttpUrl = "http://192.168.39.74/SharePlate/donatelist.php";
    String Url = "http://192.168.39.74/SharePlate/sponsorlist.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myview = inflater.inflate(R.layout.fragment_home, container, false);
        lst_donate = myview.findViewById(R.id.lst_items);
        sponsor = myview.findViewById(R.id.btn_sponsored);
        donate = myview.findViewById(R.id.btn_donated);
        head = myview.findViewById(R.id.txt_head);
        loadDonateList(HttpUrl);

        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int k=0;k<100;k++){
                    nameList[k] = "";
                    dateList[k] = "";
                    typeList[k] = "";
                    countList[k]="";
                    statList[k]="";
                    idList[k]="";
                    remainsList[k]="";
                }
                head.setText("Your Donation History");
                loadDonateList(HttpUrl);
            }
        });
        sponsor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                head.setText("Your Sponsored History");
                for(int k=0;k<100;k++){
                    nameList[k] = "";
                    dateList[k] = "";
                    amountList[k] = "";
                    descList[k]="";
                }
                head.setText("Your Sponsor History");
                loadSponsorList(Url);
            }
        });
        lst_donate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String shead = head.getText().toString();
                if(shead.equals("Your Donation History")){
                    Intent intent = new Intent(getContext(), Donor_donate_detail.class);
                    intent.putExtra("id",idList[i]);
                    intent.putExtra("name",nameList[i]);
                    intent.putExtra("type",typeList[i]);
                    intent.putExtra("remains",remainsList[i]);
                    startActivity(intent);
                }
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
                                    idList[i]=jsonobject.getString("id");
                                }
                                DonationAdapter cartAdapter = new DonationAdapter(getContext(), nameList,typeList,dateList,countList,statList);
                                lst_donate.setAdapter(cartAdapter);
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
                params.put("did", Global_use.global_id);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
    public void loadSponsorList(String HttpURL){
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
                                    amountList[i] = jsonobject.getString("amount");
                                    dateList[i] = jsonobject.getString("date");
                                    descList[i] = jsonobject.getString("descr");
                                    amountList[i] ="Rs: "+amountList[i]+"/-";
                                }
                                SponsorAdapter cartAdapter = new SponsorAdapter(getContext(), nameList, amountList, dateList, descList);
                                lst_donate.setAdapter(cartAdapter);
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
                params.put("did", Global_use.global_id);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

}