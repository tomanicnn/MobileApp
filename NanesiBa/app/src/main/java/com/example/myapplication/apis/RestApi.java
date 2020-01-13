package com.example.myapplication.apis;


import android.annotation.SuppressLint;
import android.os.Message;

import com.example.myapplication.model.Restaurant;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class RestApi {

    @SuppressLint("HandlerLeak")
    public static ArrayList getAllRests(){

        final ArrayList<Restaurant> rests = new ArrayList<>();

        ApiHandler.getJSON("http://192.168.91.75:5000/restourans.json", new ReadDataHandler(){
            @Override
            public void handleMessage(Message msg){

                String odgovor = getJson();
                Restaurant r;

                try{

                    JSONArray array = new JSONArray(odgovor);


                    for(int i = 0; i < array.length(); i++){

                        JSONObject object = array.getJSONObject(i);
                        Restaurant fromJson = Restaurant.fromJson(object);
                        rests.add(fromJson);
                    }

                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        return rests;
    }


}
