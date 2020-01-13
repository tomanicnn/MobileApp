package com.example.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Restaurant {

    private String minWait, name, address, image, phone, id;
    private List<Food> listOfFood;

    public Restaurant(String id, String minWait, String name, String address, String image, String phone, ArrayList<Food> listOfFood) {
        this.id = id;
        this.minWait = minWait;
        this.name = name;
        this.address = address;
        this.image = image;
        this.phone = phone;
        this.listOfFood = new ArrayList<Food>();
    }


    protected Restaurant(Parcel in) {
        id = in.readString();
        minWait = in.readString();
        name = in.readString();
        address = in.readString();
        image = in.readString();
        phone = in.readString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMinWait() {
        return minWait;
    }

    public void setMinWait(String minWait) {
        this.minWait = minWait;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Food> getListOfFood() {
        return listOfFood;
    }

    public void setListOfFood(List<Food> listOfFood) {
        this.listOfFood = listOfFood;
    }

    public void setListOfFood(ArrayList<Food> listOfFood) {
        this.listOfFood = listOfFood;
    }

    public Restaurant() {
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", minWait='" + minWait + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", image='" + image + '\'' +
                ", phone='" + phone + '\'' +
                ", listOfFood=" + listOfFood +
                '}';
    }

    // Add Food food in the array list
    public void addFood(Food food) {
        this.listOfFood.add(food);
    }

    // Getting single food from the list
    public Food getFood(int index) {
        return this.listOfFood.get(index);
    }


    public static Restaurant fromJson(JSONObject o){
        Restaurant r = new Restaurant();
        try{
            if(o.has("id")){
                r.setId(o.getString("id"));
            }
            if(o.has("minWait")){
                r.setMinWait(o.getString("minWait"));
            }
            if(o.has("name")){
                r.setName(o.getString("name"));
            }
            if(o.has("address")){
                r.setAddress(o.getString("address"));
            }
            if(o.has("image")){
                r.setImage(o.getString("image"));
            }
            if(o.has("phone")){
                r.setPhone(o.getString("phone"));
            }
            if(o.has("listOfFood")){
                JSONArray array = o.getJSONArray("listOfFood");
                ArrayList<Food> listOfFoodFromJson = new ArrayList<>();

                for(int i = 0; i < array.length(); i++){

                    Food f = Food.fromJson(array.getJSONObject(i));
                    listOfFoodFromJson.add(f);
                }

                r.setListOfFood(listOfFoodFromJson);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return r;
    }

    public static Restaurant convertJson(String json) {
        Restaurant r = new Restaurant();

        r = new Gson().fromJson(json, Restaurant.class);

        return r;
    }

    // Parsing the list
    public static ArrayList<Restaurant> parseJSONArray (JSONArray array){
        ArrayList<Restaurant> list = new ArrayList<>();

        try {
            for (int i = 0; i < array.length(); i++) {
                Restaurant restaurant = fromJson(array.getJSONObject(i));
                list.add(restaurant);
            }
        }catch (Exception e){

        }

        return list;
    }
}
