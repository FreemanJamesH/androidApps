package io.jamesfreeman.whatstheweather;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textDescription;
    TextView textCurrentTempt;
    TextView textTempHigh;
    TextView textTempLow;


    public class WeatherDownload extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {

            String result = "";

            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.connect();
                InputStream in = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1){
                    char current = (char) data;
                    result += current;

                    data = reader.read();

                }

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }


    public void getWeather(View view){
        System.out.println("Button clicked");
        WeatherDownload task = new WeatherDownload();
        String city = editText.getText().toString();
        System.out.println("City: "+ city);

        try {
            String weatherData = task.execute("http://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=4066ecaf6e81ebad1d9583b74f0c94cc").get();
            System.out.println(weatherData);
            JSONObject jsonObject = new JSONObject(weatherData);
            JSONArray weatherArray = new JSONArray(jsonObject.getString("weather"));
            JSONObject weather = weatherArray.getJSONObject(0);
            String description = weather.getString("main");


            JSONObject main = jsonObject.getJSONObject("main");
            String temp = main.getString("temp");
            String tempLow = main.getString("temp_min");
            String tempHigh = main.getString("temp_max");

            Double tempAsDouble = (Float.parseFloat(temp) - 272.15)* 9/5 + 32;
            Double highTempAsDouble = (Float.parseFloat(tempHigh) - 272.15)* 9/5 + 32;
            Double lowTempAsDouble = (Float.parseFloat(tempLow) - 272.15)* 9/5 + 32;

            textDescription.setText("Weather: " + description);
            textCurrentTempt.setText("Current Temp: " + tempAsDouble.intValue());
            textTempHigh.setText("High temperature: " + highTempAsDouble.intValue());
            textTempLow.setText("Low temperature: " + lowTempAsDouble.intValue());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        textDescription = (TextView) findViewById(R.id.description);
        textCurrentTempt = (TextView) findViewById(R.id.currentTemp);
        textTempHigh = (TextView) findViewById(R.id.highTemp);
        textTempLow = (TextView) findViewById(R.id.lowTemp);
    }
}
