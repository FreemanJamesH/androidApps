package io.jamesfreeman.phrasesgridlayout;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {



    public void sayPhrase(View view){
        int id = view.getId();
        String ourId = view.getResources().getResourceEntryName(id);
        int resourceId = getResources().getIdentifier(ourId, "raw", "io.jamesfreeman.phrasesgridlayout");

        MediaPlayer mplayer = MediaPlayer.create(this, resourceId);

        mplayer.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
