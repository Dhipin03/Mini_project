package com.example.shareplate_final;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
import com.google.firebase.messaging.FirebaseMessaging;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DonateFragment extends Fragment {



    View myview;
    Spinner dietary,meal;
    Button submit,cancel;
    AutoCompleteTextView location;
    EditText item,qty,contact;
    String sitem,sqty,slocation,scontact,sdietary,smeal,sdid,sdate,stime,msg;

    String phonepattern ="[0-9]{10}";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myview = inflater.inflate(R.layout.fragment_donate, container, false);
        submit = myview.findViewById(R.id.submit);
        cancel = myview.findViewById(R.id.cancel);
        dietary = myview.findViewById(R.id.dietary);
        item = myview.findViewById(R.id.item_name);
        qty = myview.findViewById(R.id.qty);
        location = myview.findViewById(R.id.location);
        contact = myview.findViewById(R.id.contact);
        meal = myview.findViewById(R.id.meal);

        String[] countries = getResources().getStringArray(R.array.places);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,countries);
        location.setAdapter(adapter);

        ArrayAdapter<CharSequence> adp = ArrayAdapter.createFromResource(getContext(),
                R.array.meal, R.layout.selected_item);
        adp.setDropDownViewResource(R.layout.drop_down);
        meal.setAdapter(adp);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getContext(),
                R.array.dietary, R.layout.selected_item);
        adapter1.setDropDownViewResource(R.layout.drop_down);
        dietary.setAdapter(adapter1);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



              getValue();
                if(sitem.equals("")||sqty.equals("")||slocation.equals("")||scontact.equals("")||sdietary.equals("Choose Your Dietary")||smeal.equals("Choose Your Food Type")) {
                    Toast.makeText(getContext(), "Enter All The Details...!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(scontact.matches(phonepattern)){

                        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.39.74/SharePlate/donate.php",
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String ServerResponse) {
                                        item.setText("");
                                        location.setText("");
                                        contact.setText("");
                                        qty.setText("");
                                        meal.setSelection(0);
                                        dietary.setSelection(0);
                                        Toast.makeText(getContext(), ServerResponse, Toast.LENGTH_SHORT).show();
                                        makeNotification();

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

                                params.put("type", smeal);
                                params.put("dietary", sdietary);
                                params.put("itemname", sitem);
                                params.put("location", slocation);
                                params.put("contact", scontact);
                                params.put("qty", sqty);
                                params.put("did",sdid);
                                params.put("status","submitted");
                                params.put("date", sdate);
                                params.put("time",stime);
                                params.put("remains",sqty);
                                return params;
                            }

                        };

                        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                        requestQueue.add(stringRequest);


                    }else{
                        Toast.makeText(getContext(), "Invalid Phonenumber", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setText("");
                location.setText("");
                contact.setText("");
                qty.setText("");
                meal.setSelection(0);
                dietary.setSelection(0);
            }
        });
        return myview;
    }

    public void getValue(){
        sitem = item.getText().toString();
        slocation = location.getText().toString();
        scontact = contact.getText().toString();
        sqty = qty.getText().toString();
        sdietary = dietary.getSelectedItem().toString();
        smeal = meal.getSelectedItem().toString();
        sdid = Global_use.global_id;

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar c = Calendar.getInstance();
        sdate = sdf.format(c.getTime());

        SimpleDateFormat stm = new SimpleDateFormat("HH:mm:ss");
        stime = stm.format(c.getTime());

    }

    public void msg(){
        FirebaseMessaging.getInstance().subscribeToTopic("All");

    }
    public void makeNotification(){
        Intent intent = new Intent(getContext(),Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Intent intent1 = new Intent(getContext(),Donor_Home.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(),0,intent1,PendingIntent.FLAG_MUTABLE);

        PendingIntent actionIntent = PendingIntent.getActivity(getContext(),0,intent,PendingIntent.FLAG_MUTABLE);

        msg = "New Plate Donated at "+slocation+". Login to SHARE PLATE to see more...!";
        String channelID="CHANNEL_ID NOTIFICATION";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(),channelID);
        builder.setSmallIcon(R.drawable.baseline_notifications)
                .setContentTitle("Share_Plate")
                .setContentText("New Plate Donated at "+slocation+".  Login to SHARE PLATE to see more...!")
                .setAutoCancel(true)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setOnlyAlertOnce(true)
                .setColor(Color.GREEN)
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.baseline_login_24,"Login",actionIntent)
                .addAction(R.drawable.baseline_cancel_24,"Cancel",pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build();


        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = notificationManager.getNotificationChannel(channelID);
            if(notificationChannel==null){
                int importance = NotificationManager.IMPORTANCE_HIGH;
                notificationChannel = new NotificationChannel(channelID,"SharePlate",importance);
                notificationChannel.setLightColor(Color.GREEN);
                notificationChannel.enableVibration(true);
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
        notificationManager.notify(0,builder.build());
    }
}