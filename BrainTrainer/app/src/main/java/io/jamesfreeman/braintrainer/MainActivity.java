package io.jamesfreeman.braintrainer;

import android.os.CountDownTimer;
import android.support.annotation.IntegerRes;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView play;
    ConstraintLayout gameConstraintLayout;
    TextView countdownTimer, problemSpace, scoreSpace;
    TextView choice1, choice2, choice3,choice4;
    int int1, int2, solution;
    int numCorrect;
    int numGuessed;

    public void play(View view){
        view.setVisibility(View.INVISIBLE);
        gameConstraintLayout.setVisibility(gameConstraintLayout.VISIBLE);
        gameConstraintLayout.setAlpha(1f);
        startGame();

    }

    public void generateProblem(){

        int1 = new Random().nextInt(50)+1;
        int2 = new Random().nextInt(50)+1;
        solution = int1 + int2;

        List answerArray = new ArrayList();

        for (int i = 0; i < 3; i++){
            int randInt = (new Random().nextInt(20) + solution - 10);
            if (randInt != solution){
                answerArray.add(randInt);
            } else {
                i--;
            }

        }

        int index = new Random().nextInt(4);
        System.out.println("Adding to index: " + index);

        answerArray.add(index, solution);



        choice1.setText(answerArray.get(0).toString());
        choice2.setText(answerArray.get(1).toString());
        choice3.setText(answerArray.get(2).toString());
        choice4.setText(answerArray.get(3).toString());

        problemSpace.setText(String.format("%d + %d", int1, int2));
    }

    public void checkAnswer(View view){

        TextView thisView = (TextView) view;
        String userAnswer = (String)thisView.getText();
        int userAnswerInt = Integer.parseInt(userAnswer);

        if (userAnswerInt == solution){
            numCorrect++;
        }
        numGuessed++;
        scoreSpace.setText(String.format("%d/%d", numCorrect, numGuessed));
        generateProblem();

    }


    public void startGame(){
        generateProblem();
        numCorrect = 0;
        numGuessed = 0;
        scoreSpace.setText("0/0");

        new CountDownTimer(30100, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                countdownTimer.setText(Integer.toString((int) millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                gameConstraintLayout.setAlpha(0.4f);
                play.setText("Play again!");
                play.setVisibility(play.VISIBLE);
                play.bringToFront();
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = (TextView) findViewById(R.id.play);
        gameConstraintLayout = (ConstraintLayout) findViewById(R.id.gameConstraintLayout);
        countdownTimer = (TextView) findViewById(R.id.countdownTimer);
        problemSpace = (TextView) findViewById(R.id.problem);
        scoreSpace = (TextView) findViewById(R.id.answered) ;
        choice1 = (TextView) findViewById(R.id.choice1);
        choice2 = (TextView) findViewById(R.id.choice2);
        choice3 = (TextView) findViewById(R.id.choice3);
        choice4 = (TextView) findViewById(R.id.choice4);


    }
}
