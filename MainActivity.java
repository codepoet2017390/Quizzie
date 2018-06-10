package com.rohan.quizzie;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SyncStatusObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView questions;
    Button truebutton;
    Button falseButton;
    ProgressBar mProgressBar;
    int score=0;
    int count=0;
    TextView ScoreView;
    Random randomnumgen = new Random();
    int number = randomnumgen.nextInt(7);
    TrueFalse[] QuestionBank = new TrueFalse[]
            {
                    new TrueFalse(R.string.Question1, false),
                    new TrueFalse(R.string.Question2, true),
                    new TrueFalse(R.string.Question3, false),
                    new TrueFalse(R.string.Question4, true),
                    new TrueFalse(R.string.Question5, true),
                    new TrueFalse(R.string.Question6, false),
                    new TrueFalse(R.string.Question7, true),
            };

    boolean[] qcheck = new boolean[QuestionBank.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questions = findViewById(R.id.questionView);
        truebutton = findViewById(R.id.truebutton);
        falseButton = findViewById(R.id.falsebutton);
        mProgressBar = findViewById(R.id.progressBar);
        ScoreView = findViewById(R.id.score);
        ScoreView.setText("0/"+QuestionBank.length);

        questions.setText(QuestionBank[number].getQuestionId());
        qcheck[number] = true;
        count++;
        truebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              checkans(true);
                Log.d("Quizzie","TRUE pressed");
              updateq();

            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkans(false);
                updateq();
                Log.d("Quizzie","False pessed");
            }
        });

    }

    private void updateq() {
         number = randomnumgen.nextInt(7);

         if(count==qcheck.length)
         {
             AlertDialog.Builder alert=new AlertDialog.Builder(this);
             alert.setTitle("GAME OVER");
             alert.setCancelable(false);
             alert.setMessage("You Scored "+score+"points!!!");
             alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                     finish();
                 }

             });
             alert.setPositiveButton("PLAY AGAIN", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                       finish();
                       startActivity(getIntent());
                 }
             });
             alert.show();
         }
        Log.d("Quizzie"," " +number);
        while (qcheck[number]) {
            number = randomnumgen.nextInt(7);
        }

        Log.d("Quizzie"," "+number);
        questions.setText(QuestionBank[number].getQuestionId());
        qcheck[number] = true;
        count++;
        mProgressBar.incrementProgressBy(14);

    }

    private void checkans(boolean ans) {
        if (QuestionBank[number].getAnswer()==ans)
        {
            score++;
            ScoreView.setText(score+"/"+QuestionBank.length);
            Toast.makeText(getApplicationContext(),"Thats right",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"BOO BOO",Toast.LENGTH_SHORT).show();
        }


    }


}
