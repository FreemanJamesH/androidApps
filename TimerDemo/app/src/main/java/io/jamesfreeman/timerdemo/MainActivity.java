package io.jamesfreeman.timerdemo;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new CountDownTimer(10000, 1000){
            public void onTick(long millisecondsUntiDone){
                Log.i("Seconds left", String.valueOf(millisecondsUntiDone/1000));
            }

            public void onFinish(){
                Log.i("Done!", "Done!");
            }
        }.start();

//        final Handler handler = new Handler();
//
//        Runnable run = new Runnable() {
//            @Override
//            public void run() {
//                //Insert code to be run every second
//
//                handler.postDelayed(this, 1000);
//
//                Log.i("Runnable has run!", "A second must have passed...");
//
//            }
//        };
//        handler.post(run);
    }
}

