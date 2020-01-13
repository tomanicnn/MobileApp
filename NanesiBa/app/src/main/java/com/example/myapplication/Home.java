package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;




public class Home extends AppCompatActivity implements RestFragment.OnFragmentInteractionListener, View.OnClickListener {

    Button btnLogOut;

    @SuppressWarnings("unchecked")
    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RestFragment ff = RestFragment.newInstance();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.mainLinearLayout, ff);
        ft.commit();

        btnLogOut = findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(this);

        }


    @Override
    public void odabraniRestoran(String rest) {
        FoodFragment ff = FoodFragment.newInstance(rest);
        FragmentTransaction pt = getSupportFragmentManager().beginTransaction();
        pt.replace(R.id.frame, ff);
        pt.addToBackStack(null);
        pt.commit();
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, Login.class));
    }
}





