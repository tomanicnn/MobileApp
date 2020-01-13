package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.apis.ApiHandler;
import com.example.myapplication.apis.ReadDataHandler;
import com.example.myapplication.model.Restaurant;
import com.example.myapplication.model.User;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MCreate extends AppCompatActivity implements View.OnClickListener{

    EditText restName, restPhone, restAddress, restMinWait;
    Button btnAdd;

    String indexOfRest;
    String i;
    int a;
    int b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcreate);
        initComp();
        initId();
    }

    @SuppressLint("HandlerLeak")
    private void initId() {
        ApiHandler.getJSON("http://10.0.2.2:5000/restourans.json", new ReadDataHandler() {
            @Override
            public void handleMessage(Message msg) {
                String odgovor = getJson();

                try {
                    JSONArray array = new JSONArray(odgovor);
                    ArrayList<Restaurant> rests = Restaurant.parseJSONArray(array);

                    for (Restaurant r : rests) {
                        i = r.getId();
                    }
                    a = Integer.parseInt(i);
                    b = a + 1;
                    indexOfRest = Integer.toString(b);
                } catch (Exception e) {

                }


            }
        });
    }


    public void initComp(){
        restName = findViewById(R.id.restName);
        restPhone = findViewById(R.id.restPhone);
        restAddress = findViewById(R.id.restAddress);
        restMinWait = findViewById(R.id.restMinWait);

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
    }


    @Override
    @SuppressLint("HandlerLeak")
    public void onClick(View v) {
        List<ApiHandler.Element> data = new ArrayList<>();
        data.add(new ApiHandler.Element("id", indexOfRest));
        data.add(new ApiHandler.Element("name", restName.getText().toString()));
        data.add(new ApiHandler.Element("phone", restPhone.getText().toString()));
        data.add(new ApiHandler.Element("address", restAddress.getText().toString()));
        data.add(new ApiHandler.Element("minWait", restMinWait.getText().toString()));
        ApiHandler.postDataJSON("http://10.0.2.2:5000/addRest", data, new ReadDataHandler(){
            @Override
            public void handleMessage(Message msg) {
                System.out.println(getJson());
            }
        });
        Toast.makeText(getApplicationContext(), "Rest created !", Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, AdminPanel.class));
    }
}
