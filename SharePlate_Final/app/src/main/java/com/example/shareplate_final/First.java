package com.example.shareplate_final;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class First extends AppCompatActivity {
    String utype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Button login = (Button) findViewById(R.id.login);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU){
            if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.POST_NOTIFICATIONS)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(First.this,new String[]{Manifest.permission.POST_NOTIFICATIONS},101);
            }

        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(First.this, Login.class);
                startActivity(in);
            }
        });

        Button signup = (Button) findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int[] checkedItem = {-1};
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(First.this);
                alertDialog.setIcon(R.drawable.person);
                alertDialog.setTitle("Sign up as");
                final String[] listItems = new String[]{"Donor", "Receiver"};
                alertDialog.setSingleChoiceItems(listItems, checkedItem[0], (dialog, which) -> {
                    checkedItem[0] = which;
                    utype = listItems[which];
                    if(utype.equals("Donor")){
                        Intent in = new Intent(First.this, Signup.class);
                        startActivity(in);
                    }
                    else if(utype=="Receiver"){
                        Intent in = new Intent(First.this, Signupreciever.class);
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
    @Override
    public void onBackPressed(){

        finish();
        super.onBackPressed();
    }
}