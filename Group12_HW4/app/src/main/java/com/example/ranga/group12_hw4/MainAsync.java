package com.example.ranga.group12_hw4;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/*
Assignment: Homework 04
File Name: MainAsync.java
Full Name:Dhenuka Bhargavi Rangam
Full Name:Sunisha Chalasani

*/

public class MainAsync extends AsyncTask<String,Void,ArrayList<Question>> {
    IData activity;


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        activity.startProgressBar();

    }

    public MainAsync(IData activity) {
        this.activity = activity;
    }

    @Override
    protected ArrayList<Question> doInBackground(String... strings) {
        ArrayList<Question> questionArrayList = new ArrayList<Question>();
        String in = strings[0];
        try {
            URL url =new URL(in);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line="";
            StringBuilder sb = new StringBuilder();
            while((line=reader.readLine())!=null)
            {
                sb.append(line+"\n");
            }
            questionArrayList = QuestionUtil.questionJSONParser(sb.toString());
            return questionArrayList;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return questionArrayList;
    }

    @Override
    protected void onPostExecute(ArrayList<Question> questions) {
        try {
            activity.setUpArrayList(questions);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

   public interface IData{
      public void startProgressBar();
      public void setUpArrayList(ArrayList<Question> arrayList) throws InterruptedException;
   }


}
