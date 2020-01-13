package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.apis.ApiHandler;
import com.example.myapplication.apis.ReadDataHandler;
import com.example.myapplication.model.User;

import org.json.JSONArray;

import java.util.ArrayList;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<User> users = new ArrayList<>();

    @Override
    @SuppressLint("HandlerLeak")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        ApiHandler.getJSON("http://10.0.2.2:5000/users.json", new ReadDataHandler() {
            @Override
            public void handleMessage(Message msg) {
                String odgovor = getJson();

                try {
                    JSONArray array = new JSONArray(odgovor);
                    //odgovor spakujemo u JSONArray, i onda parsiramo da bismo dobili linked list
                    users = User.parseJSONArray(array);
                    //postavljamo dobijeni povratni tekst kao tekst labele (ručno formatirano)
                } catch (Exception e) {

                }
            }
        });

        final TextView regText = findViewById(R.id.regText);
        regText.setOnClickListener(this);

        final EditText inputPassword = (EditText) findViewById(R.id.ddddd);
        inputPassword.setEnabled(false);

        final EditText inputUsername = (EditText) findViewById(R.id.inputUsername);
        inputUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String id = inputUsername.getText().toString();
                if (id.isEmpty())
                    inputPassword.setEnabled(false);
                else
                    inputPassword.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        inputPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String username = inputUsername.getText().toString();
                String password = inputPassword.getText().toString();
                Boolean isAdmin = true;
                if (password.isEmpty() || username.isEmpty())
                    return;
                for (User user : users) {
                    if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                        if (isAdmin.equals(user.isAdmin())) {
                            Toast.makeText(getApplicationContext(), "Dobro došli admine, " + user.getFirstName() + " " + user.getLastName() + "!", Toast.LENGTH_LONG).show();
                            inputUsername.setText("");
                            inputPassword.setText("");
                            startActivity(new Intent(Login.this, AdminPanel.class));
                        } else {
                            startActivity(new Intent(Login.this, Home.class));
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, Register.class);
        startActivity(i);
    }
}
