package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminPanel extends AppCompatActivity implements View.OnClickListener {

    Button btnDelete, btnCreate, btnModify, btnAddFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        initComp();
    }

    private void initComp() {
        btnDelete = findViewById(R.id.btnDelete);
        btnCreate = findViewById(R.id.btnCreate);
        btnModify = findViewById(R.id.btnModify);
        btnAddFood = findViewById(R.id.btnAddFood);
        btnAddFood.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnCreate.setOnClickListener(this);
        btnModify.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnCreate:
                startActivity(new Intent(this, MCreate.class));
                break;

            case R.id.btnDelete:
                startActivity(new Intent(this, MDelete.class));
                break;

            case R.id.btnModify:
                startActivity(new Intent(this, MModify.class));
                break;

            case R.id.btnAddFood:
                startActivity(new Intent(this, MFCreate.class));
                break;
        }
    }
}
