package com.example.ranga.group12_hw4;

import java.io.Serializable;

/**
 * Created by ranga on 2/7/2017.
 */

public class Question implements Serializable{

    String id,text,image;
    Choices choiceForThisQuestion;

    public Choices getChoiceForThisQuestion() {
        return choiceForThisQuestion;
    }

    public void setChoiceForThisQuestion(Choices choiceForThisQuestion) {
        this.choiceForThisQuestion = choiceForThisQuestion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
