package io.jamesfreeman.numbershapes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void showToast(String message){
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    public void tellMe(View view){

        EditText number = (EditText) findViewById(R.id.editNumber);
        double numberDouble = Integer.parseInt(number.getText().toString());
        int numberInt = (int) numberDouble;

        double sqrRoot = Math.pow(numberDouble, 0.5);

        boolean sqrRootBool = (Math.floor(sqrRoot) == sqrRoot);
        boolean triangleBool = false;

        int i = 1;
        int y = 0;

        while (y <= numberInt){
            if (numberInt == y){
                Log.i("Its a triangle", "Triangle!");
                triangleBool = true;
            }
            y+= i;
            Log.i("Y", "" + y);
            i++;
        };

        if (sqrRootBool && triangleBool){
            showToast("It's a square and a triangle!");
        } else if (sqrRootBool) {
            showToast ("Ooooh. A square!");
        } else if (triangleBool){
            showToast("Ahhh. A triangle!");
        } else {
            showToast("It's something. But not a square or a triangle.");
        }



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
