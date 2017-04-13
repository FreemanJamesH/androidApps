package io.jamesfreeman.showhide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    public void showText(View view){
        System.out.println("Showing");
        textView.setVisibility(textView.VISIBLE);
    }

    public void hideText(View view){
        System.out.println("Hiding");
        textView.setVisibility(textView.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

    }
}
