package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.apis.ApiHandler;
import com.example.myapplication.apis.ReadDataHandler;
import com.example.myapplication.model.Restaurant;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MFCreate extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    public ArrayList<String> nameOfRests = new ArrayList<>();


    TextView selectedItem;
    EditText inputFName, inputFPrice, inputFDesc;
    Button btnAddFood;

    Spinner s;
    Object item;

    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mfcreate);
        getData();
        initComp();

        selectedItem = findViewById(R.id.selectedItem);
        s = findViewById(R.id.restNameSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nameOfRests);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
        s.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (s.getSelectedItem() == null) {
            return ;
        }
        Toast.makeText(this, "Chosen", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void initComp() {
        inputFName = findViewById(R.id.inputFName);
        inputFDesc = findViewById(R.id.inputFDesc);
        inputFPrice = findViewById(R.id.inputFPrice);


        btnAddFood = findViewById(R.id.btnAddFood);
        btnAddFood.setOnClickListener(this);
    }


    @SuppressLint("HandlerLeak")
    private void getData() {
        ApiHandler.getJSON("http://10.0.2.2:5000/restourans.json", new ReadDataHandler() {
            @Override
            public void handleMessage(Message msg) {
                String odgovor = getJson();

                try {
                    JSONArray array = new JSONArray(odgovor);
                    ArrayList<Restaurant> restaurants = Restaurant.parseJSONArray(array);

                    for (int i = 0; i < restaurants.size(); i++) {
                        nameOfRests.add(restaurants.get(i).getName());
                    }

                } catch (Exception e) {

                }
            }
        });
    }


    @Override
    @SuppressLint("HandlerLeak")
    public void onClick(View v) {
        List<ApiHandler.Element> data = new ArrayList<>();
        data.add(new ApiHandler.Element("nameOfRest", value));
        data.add(new ApiHandler.Element("name", inputFName.getText().toString()));
        data.add(new ApiHandler.Element("price", inputFPrice.getText().toString()));
        data.add(new ApiHandler.Element("desc", inputFDesc.getText().toString()));
        ApiHandler.postDataJSON("http://10.0.2.2:5000/addFood", data, new ReadDataHandler() {
            @Override
            public void handleMessage(Message msg) {
                System.out.println(getJson());
            }
        });
        Toast.makeText(getApplicationContext(), "Food added !", Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, AdminPanel.class));
    }
}
