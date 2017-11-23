package com.example.ranga.group12_hw4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/*
Assignment: Homework 04
File Name: ImageAsync.java
Full Name:Dhenuka Bhargavi Rangam
Full Name:Sunisha Chalasani

*/


public class ImageAsync extends AsyncTask<String,Void,Bitmap> {

    IImage activity;

    public ImageAsync(IImage activity) {
        this.activity = activity;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {

        String in = strings[0];
        Bitmap image=null;
        try {
            URL url = new URL(in);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            image= BitmapFactory.decodeStream(con.getInputStream());
            return image;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }


    @Override
    protected void onPostExecute(Bitmap bitmap) {


        try {
            activity.setUpImage(bitmap);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public interface IImage
    {
            public void setUpImage(Bitmap bitmap) throws InterruptedException;
    }
}
