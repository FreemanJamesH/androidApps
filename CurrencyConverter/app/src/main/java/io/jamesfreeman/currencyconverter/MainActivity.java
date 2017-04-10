package io.jamesfreeman.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public void convertCurrency(View view){

        EditText currency = (EditText) findViewById(R.id.enteredAmount);

        double currencyDouble = Double.parseDouble("0" + currency.getText().toString());
        double times80 = currencyDouble * 80;
        Toast.makeText(MainActivity.this, "¥"+String.format("%.2f", times80), Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
