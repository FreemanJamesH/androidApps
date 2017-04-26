package io.jamesfreeman.celebrityguesser;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    Button button0;
    Button button1;
    Button button2;
    Button button3;
    ImageView downloadedImage;
    int correctChoice;

    public class Celebrity{
        private String name;
        private String url;

        public Celebrity(String name, String url){
            this.name = name;
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }
    }

    ArrayList<Celebrity> celebArray = new ArrayList<Celebrity>();


    public void loadFourCelebrities(){

        ArrayList<Celebrity> listOfFour = new ArrayList<Celebrity>();

        for (int i = 0; i < 4; i++){
            Random rand = new Random();
            int random = rand.nextInt(100);
            System.out.println(celebArray.get(random).getName());
            listOfFour.add(celebArray.get(random));
        }

        button0.setText(listOfFour.get(0).getName());
        button1.setText(listOfFour.get(1).getName());
        button2.setText(listOfFour.get(2).getName());
        button3.setText(listOfFour.get(3).getName());

        correctChoice = new Random().nextInt(4);
        String celebLink = ("http://cdn.posh24.se/images/:profile" + listOfFour.get(correctChoice).getUrl());
        Bitmap celebPhoto;
        try {
            celebPhoto = new CelebPhotoGetterTask().execute(celebLink).get();
            downloadedImage.setImageBitmap(celebPhoto);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public class CelebPhotoGetterTask extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... urls) {

            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.connect();
                InputStream inputStream = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);
                return myBitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }



    public class CelebGetterTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {

                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();
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

    public void guess(View view){
        String choice = view.getTag().toString();
        System.out.println("Choice: " + choice);
        System.out.println("correctChoice to string: " + Integer.toString(correctChoice));

        if (Integer.parseInt(choice) == correctChoice){
            Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Incorrect!", Toast.LENGTH_SHORT).show();
        }
        loadFourCelebrities();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CelebGetterTask task = new CelebGetterTask();

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        downloadedImage = (ImageView) findViewById(R.id.imageView);

        String result = null;


        try {
            System.out.println("trying");
            result = task.execute("http://www.posh24.se/kandisar").get();
            String escaped = result.replace("\n", "");

            Pattern p = Pattern.compile("<img src=\"http://cdn.posh24.se/images/:profile(.*?)/>");
            Matcher m = p.matcher(escaped);

            while (m.find()){
                int indexFirstQuote = m.group(1).indexOf("\"");
                String imageUrl = m.group(1).substring(0,indexFirstQuote);
                String celebName = m.group(1).substring(indexFirstQuote+7, m.group(1).length()-1);
                Celebrity celeb = new Celebrity(celebName, imageUrl);

                celebArray.add(celeb);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        loadFourCelebrities();

    }
}
