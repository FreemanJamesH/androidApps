package io.jamesfreeman.timestables;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView = (ListView) findViewById(R.id.numberListView);
        final String[] oneToTwelve = {"1","2","3","4","5","6","7","8","9","10","11","12"};

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(12);
        seekBar.setProgress(1);

        final TextView timesBy = (TextView) findViewById(R.id.textView);
        timesBy.setText(Integer.toString(seekBar.getProgress()));

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, oneToTwelve);

        listView.setAdapter(arrayAdapter);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                ArrayList arrList = new ArrayList();

                for (String number : oneToTwelve){
                    String product = Integer.toString((Integer.parseInt(number) * progress));
                    arrList.add(product);
                }

                ArrayAdapter newArrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, arrList);

                listView.setAdapter(newArrayAdapter);
                timesBy.setText(Integer.toString(progress));
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
