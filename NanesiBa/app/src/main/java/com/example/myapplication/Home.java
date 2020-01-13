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






//    @SuppressLint("HandlerLeak")
//    public void initRests(){
//
//        LinearLayout mainLinearLayout = findViewById(R.id.mainLinearLayout);
//        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
//
//
//
//        for(Restaurant rest : rests){
//
//            LinearLayout restView = (LinearLayout)inflater.inflate(R.layout.fragment_rest, null);
//            ((TextView)restView.findViewById(R.id.restMinWait)).setText(rest.getMinWait());
//            ((TextView)restView.findViewById(R.id.restName)).setText(rest.getName());
//            ((TextView)restView.findViewById(R.id.restAddress)).setText(rest.getAddress());
//            ((TextView)restView.findViewById(R.id.restPhone)).setText(rest.getPhone());
//
//            mainLinearLayout.addView(restView);
//
//        }
//    }
//
//    @SuppressLint("HandlerLeak")
//    public void initRest(){
//        ApiHandler.getJSON("http://10.0.2.2:5000/restourans.json", new ReadDataHandler(){
//            @Override
//            public void handleMessage(Message msg){
//
//                ArrayList<Restaurant> rests = new ArrayList<>();
//                String odgovor = getJson();
//                Restaurant r;
//
//                try{
//                    JSONArray array = new JSONArray(odgovor);
//
//                    LinearLayout mainLinearLayout = findViewById(R.id.mainLinearLayout);
//                    LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
//
//                    for(int i = 0; i < array.length(); i++){
//
//                        JSONObject object = array.getJSONObject(i);
//
//                        // TODO For loop getting pizza's from listOfFood like JSONObject
//                        JSONArray arrayChild = object.getJSONArray("listOfFood");
//                        for(int y = 0; y < arrayChild.length(); y++){
//                            JSONObject oChild = arrayChild.getJSONObject(y);
//                            System.out.println(oChild.getString("desc"));
//
//                            LinearLayout foodView = (LinearLayout)inflater.inflate(R.layout.fragment_food, null);
//                            ((TextView)foodView.findViewById(R.id.foodName)).setText("Food name : " + oChild.getString("name"));
//                            ((TextView)foodView.findViewById(R.id.foodDesc)).setText("Food name : " + oChild.getString("desc"));
//                            ((TextView)foodView.findViewById(R.id.foodPrice)).setText("Food name : " + oChild.getString("price"));
//                            mainLinearLayout.addView(foodView);
//                        }
//
//                        // Reading Image from file
//                        LinearLayout restView = (LinearLayout)inflater.inflate(R.layout.fragment_rest, null);
//                        ((TextView)restView.findViewById(R.id.restMinWait)).setText("Min of waiting : " + object.getString("minWait"));
//                        ((TextView)restView.findViewById(R.id.restName)).setText("Name : " + object.getString("name"));
//                        ((TextView)restView.findViewById(R.id.restAddress)).setText("Address : "  + object.getString("address"));
//                        ((TextView)restView.findViewById(R.id.restPhone)).setText("Phone : " + object.getString("phone"));
//
//                        mainLinearLayout.addView(restView);
//
////
//                    }
//                } catch (JSONException e){
//                    e.printStackTrace();
//                } catch(Exception e){
//                    e.printStackTrace();
//                }
//            }
//        });
//    }





