/*
Assignment: Homework 04
File Name: StatsActivity.java
Full Name:Dhenuka Bhargavi Rangam
Full Name:Sunisha Chalasani

*/
package com.example.ranga.group12_hw4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import static android.hardware.camera2.params.RggbChannelVector.RED;

public class StatsActivity extends AppCompatActivity {
    ScrollView sv;
    ArrayList<Integer> it=new ArrayList<Integer>();
    int p=0,q=0,r=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        ArrayList<Question> questionArrayList = new ArrayList<Question>();
        ArrayList<String>  coAns = new ArrayList<String>();
        HashMap<Integer,String> myAns = new HashMap<Integer, String>();
        questionArrayList = (ArrayList<Question>) getIntent().getExtras().get("questionArrayList");
        if(getIntent().getExtras().containsKey(TriviaActivity.myAnswersKey) && getIntent().getExtras().containsKey(TriviaActivity.correcAnswersKey))
        {
            coAns = (ArrayList<String>) getIntent().getExtras().get(TriviaActivity.correcAnswersKey);
            myAns = (HashMap<Integer, String>) getIntent().getExtras().get(TriviaActivity.myAnswersKey);
        }
        LinearLayout l2 = new LinearLayout(this);
        LinearLayout ll2=(LinearLayout) findViewById(R.id.ll);
        for(int i =0;i<questionArrayList.size();i++) {
            if (coAns.get(i).equalsIgnoreCase(myAns.get(i+1))) {
                it.add(i);
                p++;
                r++;
            }
            else{
                Log.d("demo","ekjw"+coAns.get(0)+"2nd   "+myAns.get(1));
                LinearLayout ll = new LinearLayout(this);
                ll.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                ll.setOrientation(LinearLayout.VERTICAL);
                TextView ques = new TextView(this);
                ques.setText(questionArrayList.get(i).getText().toString());
                TextView myAnstv = new TextView(this);
                myAnstv.setBackgroundColor(0xfff00000);
                myAnstv.setText(myAns.get(i + 1));
                TextView coAnstv = new TextView(this);
                coAnstv.setText(coAns.get(i));
                ll.addView(ques);
                ll.addView(myAnstv);
                ll.addView(coAnstv);
                ll2.addView(ll);
                q++;
                r++;
            }
        }

        ProgressBar s=(ProgressBar)findViewById(R.id.progressBar2);
        TextView w=(TextView)findViewById(R.id.perf_value);

        for(int i =0;i<it.size();i++) {
            LinearLayout ll = new LinearLayout(this);
            ll.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            ll.setOrientation(LinearLayout.VERTICAL);
            TextView ques = new TextView(this);
            ques.setText(questionArrayList.get(it.get(i)).getText().toString());
            TextView myAnstv = new TextView(this);
            myAnstv.setBackgroundColor(0xFF00FF00);
            myAnstv.setText(myAns.get(it.get(i) + 1));
            TextView coAnstv = new TextView(this);
            coAnstv.setText(coAns.get(it.get(i)));
            ll.addView(ques);
            ll.addView(myAnstv);
            ll.addView(coAnstv);
            ll2.addView(ll);
        }
        int z=(100*p)/r;
        s.setProgress(z);
        w.setText(Integer.toString(z)+"%");

        findViewById(R.id.finish_in_stats).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
