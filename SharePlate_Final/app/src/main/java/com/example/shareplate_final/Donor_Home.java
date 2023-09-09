package com.example.shareplate_final;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.shareplate_final.databinding.ActivityDonorHomeBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Donor_Home extends AppCompatActivity {
    ActivityDonorHomeBinding binding;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDonorHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());
        fab = (FloatingActionButton)findViewById(R.id.fab_more);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Donor_Home.this);
                builder.setMessage("Do You Want to Rate the App ?");
                builder.setTitle("Rate Us !");
                builder.setCancelable(false);
                builder.setIcon(R.drawable.rate24);
                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                    Intent in = new Intent(getApplicationContext(),RateUs.class);
                    startActivity(in);
                });
                builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                    dialog.cancel();
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.link));
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.black));

            }
        });

        binding.bottomnavigation.setBackground(null);
        binding.bottomnavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home :
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.donate :
                    replaceFragment(new DonateFragment());
                    break;
                case R.id.profile :
                    replaceFragment(new ProfileFragment());
                    break;
                case R.id.sponsor:
                    replaceFragment(new SponsorFragment());
                    break;

            }
            return true;
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();
    }
    @Override
    public void onBackPressed(){

        finish();
        super.onBackPressed();
    }

}