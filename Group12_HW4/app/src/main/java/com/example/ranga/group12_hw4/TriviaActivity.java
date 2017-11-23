package com.example.ranga.group12_hw4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


public class TriviaActivity extends AppCompatActivity implements ImageAsync.IImage {
    ArrayList<Question> questionArrayList;
    ArrayList<String> correctAnswers;
    TextView questionNum,timeleft,question;
    ImageView questionImage;
    ProgressBar pg;
    Button prev,next;
    int x=0;
    int qNum;
    int qNuminArry;
    HashMap<Integer,Integer> answers;
    HashMap<Integer,String> answersForStats;
    RadioGroup rg;
    TextView xt;
    int temp=0;
    final static String myAnswersKey ="myAnswers";
    final static String correcAnswersKey ="correctAns";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);
        answersForStats = new HashMap<Integer,String>();
        questionArrayList = (ArrayList<Question>) getIntent().getExtras().getSerializable("questionArrayList");
        correctAnswers = new ArrayList<String>();
        questionNum = (TextView) findViewById(R.id.questionNoID);
        timeleft = (TextView) findViewById(R.id.timeleft);
        question = (TextView) findViewById(R.id.question);
        questionImage = (ImageView) findViewById(R.id.imagePerQuestion);
        pg= (ProgressBar) findViewById(R.id.pgimage);
        pg.setVisibility(View.VISIBLE);
        xt=(TextView) findViewById(R.id.load);
        xt.setText("Loading Image");
        prev= (Button) findViewById(R.id.prevID);
        next= (Button) findViewById(R.id.nextBID);
        rg= (RadioGroup) findViewById(R.id.radioGroup);
        answers=new HashMap<Integer,Integer>();
        rg = (RadioGroup) findViewById(R.id.radioGroup);
        for(int i=0;i<questionArrayList.size();i++)
        {
            String j = questionArrayList.get(i).getChoiceForThisQuestion().getAnswer();
            correctAnswers.add(questionArrayList.get(i).getChoiceForThisQuestion().getChoice().get(Integer.parseInt(j)-1));
        }



        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                temp=i;
                if(answers!=null){
                    if(answers.containsKey(qNum)) {
                        answers.remove(qNum);
                        answers.put(qNum, i);
                        answersForStats.remove(qNum);
                        answersForStats.put(qNum,questionArrayList.get(qNuminArry).getChoiceForThisQuestion().getChoice().get(i-(qNum*100)));
                    }else{
                        answers.put(qNum, i);
                        answersForStats.put(qNum,questionArrayList.get(qNuminArry).getChoiceForThisQuestion().getChoice().get(i-(qNum*100)));
                    }
                }else{
                    answers.put(qNum, i);
                    answersForStats.put(qNum,questionArrayList.get(qNuminArry).getChoiceForThisQuestion().getChoice().get(i-(qNum*100)));
                }

            }
        });
        if(questionArrayList.size()!=0) {
            qNum = 1;
            qNuminArry=0;
            prev.setEnabled(false);
            if(questionArrayList.size()==qNum)
            {
                next.setText("Finish");
            }
            questionNum.setText("Q"+qNum);
            question.setText(questionArrayList.get(0).getText());
            addRadioButtons(questionArrayList.get(0).choiceForThisQuestion);
            String image = questionArrayList.get(0).getImage();
            if (image != null) {
                new ImageAsync(this).execute(image);
            }else
            {
                pg.setVisibility(View.INVISIBLE);
                xt.setText("");
                questionImage.setImageBitmap(null);
            }
            new CountDownTimer(120000, 1000) {

                public void onTick(long millisUntilFinished) {
                    timeleft.setText("Time Left:" + millisUntilFinished / 1000 +"seconds");
                   /* if((qNum==questionArrayList.size()) *//*&& answers!=null && answers.size()==questionArrayList.size()*//*)
                    {
                        next.setText("Finish");
                        next.setEnabled(true);
                    }*/
                }

                public void onFinish() {
                    startNewActivity();

                }
            }.start();
        }

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(prev.isEnabled())
                {
                    questionImage.setImageBitmap(null);
                    pg.setVisibility(View.VISIBLE);
                    xt.setText("Loading Image");
                    qNuminArry= qNuminArry-1;
                    qNum=qNum-1;
                    questionNum.setText("Q"+qNum);
                    String image = questionArrayList.get(qNuminArry).getImage();
                    if (image != null) {
                        new ImageAsync(TriviaActivity.this).execute(image);
                    }else
                    {
                        pg.setVisibility(View.INVISIBLE);
                        xt.setText("");
                        questionImage.setImageBitmap(null);
                    }

                    question.setText(questionArrayList.get(qNuminArry).getText());
                    addRadioButtons(questionArrayList.get(qNuminArry).choiceForThisQuestion);
                    if(next.getText().equals("Finish"))
                    {
                        next.setText("Next");
                    }
                    if(qNuminArry==0)
                    {
                        prev.setEnabled(false);
                    }
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    if (next.getText().equals("Finish")) {
                           startNewActivity();
                    }else{
                            questionImage.setImageBitmap(null);
                            pg.setVisibility(View.VISIBLE);
                            xt.setText("Loading Image");
                            qNuminArry = qNuminArry + 1;
                            qNum = qNum + 1;
                            questionNum.setText("Q" + qNum);
                            String image = questionArrayList.get(qNuminArry).getImage();
                            if (image != null) {
                                new ImageAsync(TriviaActivity.this).execute(image);
                            } else {
                                 pg.setVisibility(View.INVISIBLE);
                                 xt.setText("");
                                 questionImage.setImageBitmap(null);
                            }
                            question.setText(questionArrayList.get(qNuminArry).getText());
                            addRadioButtons(questionArrayList.get(qNuminArry).choiceForThisQuestion);
                            prev.setEnabled(true);
                            if (qNuminArry == questionArrayList.size() - 1) {
                                next.setText("Finish");

                            }
                        }
                    }
        });








    }


    public void addRadioButtons (Choices choice)
    {
        ArrayList<String> choiceArray = choice.getChoice();
        if(choiceArray.size()!=0) {
            for (int row = 0; row < 1; row++) {
                if(x>0){
                    rg.removeAllViews();
                }
                x++;
                rg.setOrientation(LinearLayout.VERTICAL);
                RadioButton[] rdbt = new RadioButton[choiceArray.size()];
                for (int i = 0; i < choiceArray.size(); i++) {
                    rdbt[i]=new RadioButton(this);
                    rdbt[i].setId((qNum * 100) + i);
                    if(answers!=null){
                        if(answers.containsKey(qNum)){
                            if(rdbt[i].getId()==answers.get(qNum)){
                                rdbt[i].setChecked(true);
                            }
                        }
                    }
                    rdbt[i].setText(choiceArray.get(i));
                    rg.addView(rdbt[i]);
                }



            }
        }

    }


    @Override
    public void setUpImage(Bitmap bitmap) throws InterruptedException {
       if(bitmap!=null) {
           pg.setVisibility(View.INVISIBLE);
           xt.setText("");
           questionImage.setImageBitmap(bitmap);
       }else{
           pg.setVisibility(View.INVISIBLE);
           xt.setText("");
           questionImage.setImageBitmap(null);
       }
    }

    public void startNewActivity()
    {
        Intent intent = new Intent(TriviaActivity.this,StatsActivity.class);
        if(answers.size()!=questionArrayList.size()){
            for(int i=1;i<questionArrayList.size()+1;i++)
            {
                if(answers.containsKey(i))
                {
                    continue;
                }else
                {
                    answersForStats.put(i,"Incorrect Answer");

                }
            }
        }
        intent.putExtra(myAnswersKey,answersForStats);
        intent.putExtra(correcAnswersKey,correctAnswers);
        intent.putExtra("questionArrayList",questionArrayList);
        startActivity(intent);
        finish();
    }
}
