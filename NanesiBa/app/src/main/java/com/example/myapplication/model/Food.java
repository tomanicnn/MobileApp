package com.example.myapplication.model;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Food {

    private String name, price, desc, image, id;

    public Food(String id, String name, String price, String desc, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.desc = desc;
        this.image = image;
    }

    public Food() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public static Food fromJson(JSONObject o){
        Food f = new Food();
        try{
            if(o.has("id")){
                f.setId(o.getString("id"));
            }
            if(o.has("name")){
                f.setName(o.getString("name"));
            }
            if(o.has("price")){
                f.setPrice(o.getString("price"));
            }
            if(o.has("desc")){
                f.setDesc(o.getString("desc"));
            }
            if(o.has("image")){
                f.setImage(o.getString("image"));
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return f;
    }

    public static ArrayList<Food> parseJSONArray(JSONArray array){
        ArrayList<Food> list = new ArrayList<>();
        try{
            for(int i = 0; i < array.length(); i++){
                Food model = fromJson(array.getJSONObject(i));
                list.add(model);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static Food convertJson(String json) {
        Food f = new Food();

        f = new Gson().fromJson(json, Food.class);

        return f;
    }
}
