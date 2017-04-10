package io.jamesfreeman.higherorlower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int OneToTwenty;

    public void showToast(String toastMessage){
        Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
    }

    public void guess(View view){

        EditText userGuess = (EditText) findViewById(R.id.numberInput);
        String userGuessString = userGuess.getText().toString();
        int userGuessInt = Integer.parseInt(userGuessString);


        if (userGuessInt > OneToTwenty){
            showToast("Too high!");
        } else if (userGuessInt < OneToTwenty) {
            showToast ("Too low!");
        } else {
            showToast("Ding ding ding! New number loaded.");
            OneToTwenty = (new Random().nextInt(20) + 1);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random rand = new Random();
        OneToTwenty =  (rand.nextInt(20) + 1);
    }
}
