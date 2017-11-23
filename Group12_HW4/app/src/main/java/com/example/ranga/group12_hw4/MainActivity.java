/*
Assignment: Homework 04
File Name: Main Activity.java
Full Name:Dhenuka Bhargavi Rangam
Full Name:Sunisha Chalasani

*/

package com.example.ranga.group12_hw4;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity implements MainAsync.IData,ImageAsync.IImage {
    ArrayList<Question> questionArrayList = new ArrayList<Question>();

    Bitmap image = null;
    Button exit,start_trivia;
    ImageView triviaImage;
    ProgressBar pg;
    TextView tv ,loadText;

    @Override
    public void startProgressBar() {
        pg.setVisibility(View.VISIBLE);
        loadText.setText("Loading Trivia");
    }

    public void setUpArrayList(ArrayList<Question> myArrayList) throws InterruptedException {
        questionArrayList = myArrayList;
         if(questionArrayList.size()!=0)
        {
            pg.setVisibility(View.INVISIBLE);
            loadText.setText("");
            triviaImage.setVisibility(View.VISIBLE);
            tv.setVisibility(View.VISIBLE);
            start_trivia.setEnabled(true);

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        exit = (Button) findViewById(R.id.exit);
        start_trivia = (Button) findViewById(R.id.start_trivia);
        start_trivia.setEnabled(false);
        triviaImage = (ImageView) findViewById(R.id.triviaImage);
        loadText = (TextView)findViewById(R.id.loadTriID);
        tv= (TextView) findViewById(R.id.triviaready);
        tv.setVisibility(View.INVISIBLE);
        triviaImage.setVisibility(View.INVISIBLE);
        pg = (ProgressBar) findViewById(R.id.progressBar);



        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

            start_trivia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(start_trivia.isEnabled()) {
                        Intent intent = new Intent(MainActivity.this, TriviaActivity.class);
                       intent.putExtra("questionArrayList",questionArrayList);

                        startActivity(intent);
                    }
                }
            });

        if(isConnectionOnline())
        {
            pg.setVisibility(View.VISIBLE);
            loadText.setText("Loading Trivia");
            new MainAsync(this).execute("http://dev.theappsdr.com/apis/trivia_json/index.php");

        }



    }

    private boolean isConnectionOnline()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ninfo = cm.getActiveNetworkInfo();
        if(ninfo!=null && ninfo.isConnected())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public void setUpImage(Bitmap bitmap) {
            image=bitmap;
    }
}
