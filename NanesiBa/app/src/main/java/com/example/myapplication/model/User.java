package com.example.myapplication.model;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class User {

    private int id;
    private String firstName, lastName, phone,
            email, password, username, image;
    private boolean isAdmin;

    public User(int id, String firstName, String lastName, String phone,
                String email, String password, String username, String image,
                boolean isAdmin) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.username = username;
        this.image = image;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }


    public User() {
    }

    public static User fromJson(JSONObject o){
        User f = new User();
        try{
            if(o.has("id")){
                f.setId(o.getInt("id"));
            }
            if(o.has("firstName")){
                f.setFirstName(o.getString("firstName"));
            }
            if(o.has("lastName")){
                f.setLastName(o.getString("lastName"));
            }
            if(o.has("phone")){
                f.setPhone(o.getString("phone"));
            }
            if(o.has("image")){
                f.setImage(o.getString("image"));
            }
            if(o.has("email")){
                f.setEmail(o.getString("email"));
            }
            if(o.has("password")){
                f.setPassword(o.getString("password"));
            }
            if(o.has("username")){
                f.setUsername(o.getString("username"));
            }
            if(o.has("isAdmin")){
                f.setAdmin(o.getBoolean("isAdmin"));
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return f;
    }

    public static ArrayList<User> parseJSONArray(JSONArray array){
        ArrayList<User> list = new ArrayList<>();
        try{
            for(int i = 0; i < array.length(); i++){
                User model = fromJson(array.getJSONObject(i));
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
