package io.jamesfreeman.fourfriends;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public void visitSashy(View view){
        Intent intent = new Intent(getApplicationContext(), SashaActivity.class);

        startActivity(intent);
    }

    public void visitLisa(View view){
        Intent intent = new Intent(getApplicationContext(), LisaActivity.class);

        startActivity(intent);
    }

    public void visitMaya(View view){
        Intent intent = new Intent(getApplicationContext(), MayaActivity.class);

        startActivity(intent);
    }

    public void visitCooper(View view){
        Intent intent = new Intent(getApplicationContext(), CooperActivity.class);

        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
