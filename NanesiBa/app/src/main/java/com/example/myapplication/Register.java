package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.apis.ApiHandler;
import com.example.myapplication.apis.ReadDataHandler;
import com.example.myapplication.model.Food;
import com.example.myapplication.model.Restaurant;
import com.example.myapplication.model.User;

import org.json.JSONArray;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Register extends AppCompatActivity implements View.OnClickListener{

    EditText inputFirstName, inputLastName, inputEmail, inputPhone, inputPassword, inputUsername;
    Button btnReg;
    String listOfFood;
    String index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initComponents();
    }


    public void initComponents(){
        inputFirstName = findViewById(R.id.inputFirstName);
        inputLastName = findViewById(R.id.inputLastName);
        inputEmail = findViewById(R.id.inputEmail);
        inputPhone = findViewById(R.id.inputPhone);
        inputPassword = findViewById(R.id.inputPassword);
        inputUsername = findViewById(R.id.inputUsername);
        btnReg = findViewById(R.id.btnReg);
        btnReg.setOnClickListener(this);

    }

    @Override
    @SuppressLint("HandlerLeak")
    public void onClick(View v) {
        List<ApiHandler.Element> data = new ArrayList<>();
        data.add(new ApiHandler.Element("firstName", inputFirstName.getText().toString()));
        data.add(new ApiHandler.Element("lastName", inputLastName.getText().toString()));
        data.add(new ApiHandler.Element("phone", inputPhone.getText().toString()));
        data.add(new ApiHandler.Element("email", inputEmail.getText().toString()));
        data.add(new ApiHandler.Element("password", inputPassword.getText().toString()));
        data.add(new ApiHandler.Element("username", inputUsername.getText().toString()));
        data.add(new ApiHandler.Element("isAdmin", "False"));
        ApiHandler.postDataJSON("http://10.0.2.2:5000/register", data, new ReadDataHandler(){
            @Override
            public void handleMessage(Message msg) {
                System.out.println(getJson());
            }
        });
        startActivity(new Intent(this, Login.class));
    }

}
