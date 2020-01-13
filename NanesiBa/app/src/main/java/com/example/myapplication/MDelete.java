package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.apis.ApiHandler;
import com.example.myapplication.apis.ReadDataHandler;
import com.example.myapplication.model.Restaurant;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MDelete extends AppCompatActivity implements View.OnClickListener{

    EditText inputId;
    Button btnDelete;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mdelete);

        inputId = findViewById(R.id.idInput);
        btnDelete = findViewById(R.id.btnDel);
        btnDelete.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnDel:
                delete();
        }

    }

    @SuppressLint("HandlerLeak")
    private void delete() {
        List<ApiHandler.Element> data = new ArrayList<>();
        data.add(new ApiHandler.Element("id", inputId.getText().toString()));
        ApiHandler.postDataJSON("http://10.0.2.2:5000/removeRest", data, new ReadDataHandler(){
            @Override
            public void handleMessage(Message msg) {
                System.out.println(getJson());
            }
        });
        Toast.makeText(getApplicationContext(), "Rest removed !", Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, AdminPanel.class));
    }
}
