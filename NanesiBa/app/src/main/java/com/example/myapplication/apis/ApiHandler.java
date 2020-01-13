package com.example.myapplication.apis;

import android.net.Uri;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ApiHandler {

    public static class Element {
        private String name, value;

        public Element(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }
    }

    public static void getJSON(String url, final ReadDataHandler rdh) {
        AsyncTask<String, Integer, String> task = new AsyncTask<String, Integer, String>() {
            @Override
            protected String doInBackground(String... strings) {

                String response = "";
                try {
                    URL url = new URL(strings[0]);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String red;

                    while ((red = br.readLine()) != null) {
                        response += red;
                    }
                    br.close();
                    con.disconnect();

                } catch (Exception e) {
                    e.printStackTrace();
                    response = "[]";
                }

                return response;
            }

            @Override
            protected void onPostExecute(String response) {
                rdh.setJson(response);
                rdh.sendEmptyMessage(0);
            }
        };

        task.execute(url);


    }

    public static void postDataJSON (String url, final List<Element> data, final ReadDataHandler rdh){
        AsyncTask<String,Void,String> task = new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String response = "";

                //treba nam http konekcija
                try {
                    URL link = new URL(strings[0]);
                    HttpURLConnection con = (HttpURLConnection) link.openConnection();

                    //Koristimo POST metodu
                    con.setRequestMethod("POST");

                    //Postavljamo da koristimo http konekciju za input i za output
                    con.setDoInput (true);
                    con.setDoOutput (true);

                    //Koristimo Uri.Builder za pravljenje upita, odnosno enkodiranje kljuca i vrednosti
                    //u konacan string npr ?id=1 itd...
                    Uri.Builder builder = new Uri.Builder();

                    for (Element elem : data){
                        builder.appendQueryParameter(elem.getName(),elem.getValue());
                    }

                    String postData = builder.build().getEncodedQuery();

                    //Pisemo kreirani upit, odnosno saljemo podatke
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream(), "UTF-8"));

                    bw.write(postData);
                    bw.flush();
                    bw.close();

                    con.getOutputStream().close();

                    con.connect();

                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    //cita se red po red iz br
                    String red;
                    while ((red = br.readLine())!=null){
                        response += red + "\n";
                    }

                    br.close();
                    con.disconnect(); // ne mora se pisati - Android sam ubija konekciju

                } catch (Exception e){
                    response = "[]";
                }
                return response;
            }

            @Override
            protected void onPostExecute(String response) {
                rdh.setJson(response);
                rdh.sendEmptyMessage(0);
            }
        };

        task.execute(url);
    }



}
