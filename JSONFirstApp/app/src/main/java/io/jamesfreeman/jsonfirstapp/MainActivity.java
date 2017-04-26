package io.jamesfreeman.jsonfirstapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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


    public class JSONDownloader extends AsyncTask<String, Void, String>{

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String weatherData;

        JSONDownloader task = new JSONDownloader();
        try {
            weatherData = task.execute("http://api.openweathermap.org/data/2.5/weather?q=London,uk&appid=4066ecaf6e81ebad1d9583b74f0c94cc").get();
            System.out.println(weatherData);

            JSONObject jObject = new JSONObject(weatherData);

            String weatherInfo = jObject.getString("weather");

            JSONArray arr = new JSONArray(weatherInfo);

            for (int i = 0; i < arr.length(); i++){

                JSONObject jsonPart = arr.getJSONObject(i);

                Log.i("main", jsonPart.getString("main"));
                Log.i("description", jsonPart.getString("description"));

            }

            Log.i("Weather content", weatherInfo);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
