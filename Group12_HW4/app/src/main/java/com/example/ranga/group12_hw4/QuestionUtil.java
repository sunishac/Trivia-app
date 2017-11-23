package com.example.ranga.group12_hw4;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/*
Assignment: Homework 04
File Name: QuestionUtil.java
Full Name:Dhenuka Bhargavi Rangam
Full Name:Sunisha Chalasani

*/

public class QuestionUtil {
    public static ArrayList<Question> questionJSONParser(String in) throws JSONException {
        ArrayList<Question> questionArrayList = new ArrayList<Question>();
        JSONObject root = new JSONObject(in);
        JSONArray questionObjectsArray = root.getJSONArray("questions");
        for(int i =0;i<questionObjectsArray.length();i++)
        {
            Question question = new Question();
            JSONObject questionObject = (JSONObject) questionObjectsArray.get(i);
            String id = (String) questionObject.get("id");
            String text = (String) questionObject.get("text");
            String image=null;
            try{
                image = (String) questionObject.get("image");
            }catch(JSONException e){
                image = null;
            }
            Choices choices = new Choices();
            JSONObject choiceObject = (JSONObject) questionObject.get("choices");
                    JSONArray choiceArray = (JSONArray) choiceObject.get("choice");
                    String answer = (String) choiceObject.get("answer");
                    ArrayList<String> optionsArray = new ArrayList<String>();
         //   boolean cO = choiceObject!=null;
         //   boolean cA = choiceArray!=null;
         //   boolean a = answer!=null;
         //   int length = choiceArray.length();
           // Log.d("demo",cO+""+cA+""+a+length);

            for(int j=0;j<choiceArray.length();j++)
            {
              optionsArray.add(choiceArray.getString(j));
              //  Log.d("demo",k+"");
            }
            choices.setChoice(optionsArray);
            choices.setAnswer(answer);
           // Log.d("demo123","answer"+choices.getAnswer());
            question.setId(id);
            question.setText(text);
            question.setImage(image);
            question.setChoiceForThisQuestion(choices);
            questionArrayList.add(question);
        }
        return questionArrayList;
    }
}
