package io.jamesfreeman.tic_tac_toe;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    boolean gameActive = true;
    int playerTurn = 0;
    int[] values ={2,2,2,2,2,2,2,2,2};
    int[][] winningThrees = {{0,1,2},{3,4,5},{6,7,8}, {0,3,6},{1,4,7},{2,5,8},{0,4,8}, {2,4,6}};
    int winner = 2;

    public void newGame(View view){
        findViewById(R.id.winnerContainer).setVisibility(View.INVISIBLE);
        playerTurn = 0;
        winner = 2;
        for (int i = 0; i < values.length; i++){
            values[i] = 2;
        }

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++){
            ImageView gridSquare = (ImageView) gridLayout.getChildAt(i);
            gridSquare.setImageResource(0);
        }
        gameActive = true;
    }

    public void dropIn(View view){

        ImageView counter = (ImageView) view;
        int location = Integer.parseInt(counter.getTag().toString()) - 1;

        if (gameActive){
            if (values[location] == 2){
                counter.setTranslationY(-1000f);
                if (playerTurn == 0){
                    values[location] = 0;
                    playerTurn = 1;
                    counter.setImageResource(R.drawable.bluepiece);
                } else {
                    values[location] = 1;
                    playerTurn = 0;
                    counter.setImageResource(R.drawable.redpiece);
                }
                counter.animate().translationYBy(1000f).setDuration(300);
            }

            for (int i = 0; i < 8; i++){
                int[] winningPositions = winningThrees[i];
                if ((values[winningPositions[0]] == values[winningPositions[1]]) && (values[winningPositions[1]] == values[winningPositions[2]]) && values[winningPositions[0]] != 2 ){
                    System.out.println("We have a winner.");
                    winner = values[winningPositions[0]];
                    break;
                }
            }

            boolean gameDraw = true;

            for (int i = 0; i < 9; i++){
                if (values[i] == 2){
                    gameDraw = false;
                }
            }

            if (gameDraw) winner = 3;

            System.out.println(Arrays.toString(values));

            if (winner != 2){
                String winnerText;
                if (winner == 0){
                    winnerText = "Blue wins!";
                } else if (winner == 1) {
                    winnerText = "Red wins!";
                } else {
                    winnerText = "It's a draw!";
                }

                TextView winningMessage = (TextView) findViewById(R.id.winnerBanner);
                LinearLayout winner = (LinearLayout) findViewById(R.id.winnerContainer);
                winningMessage.setText(winnerText);
                winner.setVisibility(View.VISIBLE);
                gameActive = false;

            }

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
