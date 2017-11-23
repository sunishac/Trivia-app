package com.example.ranga.group12_hw4;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ranga on 2/7/2017.
 */

public class Choices implements Serializable{

    ArrayList<String> choice;
    String answer;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public ArrayList<String> getChoice() {
        return choice;
    }

    public void setChoice(ArrayList<String> choice) {
        this.choice = choice;
    }
}
