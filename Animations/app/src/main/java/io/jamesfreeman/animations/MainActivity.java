package io.jamesfreeman.animations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public void fade(View view){
        Log.i("Info", "fade clicked!");

        ImageView morty = (ImageView) findViewById(R.id.morty);
        morty.animate()
                .translationXBy(1500f)
                .scaleX(1f).scaleY(1f)
                .rotation(720)
                .setDuration(2000);


//        ImageView rick = (ImageView) findViewById(R.id.rick);
//
//        Float mortyAlpha = morty.getAlpha();
//
//        if (mortyAlpha == 1){
//            morty.animate().alpha(0f).setDuration(1000);
//            rick.animate().alpha(1f).setDuration(1000);
//        } else if (mortyAlpha == 0){
//            morty.animate().alpha(1f).setDuration(1000);
//            rick.animate().alpha(0f).setDuration(1000);
//        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView morty = (ImageView) findViewById(R.id.morty);
        morty.setTranslationX(-1500f);
        morty.setScaleX(0.5f);
        morty.setScaleY(0.5f);

    }
}
