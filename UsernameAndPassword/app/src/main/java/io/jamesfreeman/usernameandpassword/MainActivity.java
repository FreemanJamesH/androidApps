package io.jamesfreeman.usernameandpassword;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void loginClick(View view){

        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);

        String usernameString = username.getText().toString();

        Toast.makeText(MainActivity.this, "Hi there, " + usernameString + "!", Toast.LENGTH_LONG).show();

        Log.i("Info", "Login button clicked!");
        Log.i("Username:", username.getText().toString());
        Log.i("Password:", password.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
