package io.jamesfreeman.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    Button startStopButton;
    TextView textView;
    int time;
    SeekBar setTime;
    boolean countingDown = false;
    CountDownTimer timer;
    MediaPlayer mplayer;

    public void startStop(View view){
        if (!countingDown){
          timer =  new CountDownTimer(time * 1000 + 100, 1000){
                public void onTick(long millisecondsTillDone){
                    setTime.setProgress((int) millisecondsTillDone/1000);
                }

                public void onFinish(){
                    textView.setText("0:00");
                    mplayer.start();
                    countingDown = false;

                }
            }.start();
            countingDown = true;
            startStopButton.setText("Stop");
            setTime.setEnabled(false);

        } else {
            timer.cancel();
            countingDown = false;
            startStopButton.setText("Start");
            setTime.setEnabled(true);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startStopButton = (Button) findViewById(R.id.startStopButton);
        textView = (TextView) findViewById(R.id.textView);
        mplayer = MediaPlayer.create(this, R.raw.pan);

        setTime = (SeekBar) findViewById(R.id.seekBar);
        setTime.setProgress(0);
        setTime.setMax(600);


        setTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int minutes = progress / 60;
                int seconds = progress % 60;
                time = progress;
                textView.setText(String.format("%d:%02d", minutes, seconds));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
