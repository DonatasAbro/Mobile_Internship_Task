package com.example.mobile_internship_task;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String jsonPath = "json/dog_urls.json";
        String dogUrlsStr = readFileContent(jsonPath);
        ArrayList<Bitmap> dogPics = new ArrayList<>();
        ArrayList<String> dogUrls = new ArrayList<>();
        ListView dogUrlsList = findViewById(R.id.dogUrlsList);
        
        try {
            JSONObject dogUrlsJsonObject = new JSONObject(dogUrlsStr);
            JSONArray dogUrlsJsonArray = dogUrlsJsonObject.getJSONArray("urls");
            for (int i = 0; i < 10 /*dogUrlsJsonArray.length()*/; i++) {
                dogUrls.add(dogUrlsJsonArray.get(i).toString());
                dogPics.add(BitmapFactory.decodeStream((InputStream) new URL(dogUrls.get(i)).getContent()));
            }
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

//        ArrayAdapter<String> dogUrlsArrayAdapter = new ArrayAdapter<>(this, R.layout.activity_listview, R.id.SumView, dogUrls);
//        dogUrlsList.setAdapter(dogUrlsArrayAdapter);

        DogListAdapter dogListAdapter = new DogListAdapter(getApplicationContext(), dogPics,dogUrls);
        dogUrlsList.setAdapter(dogListAdapter);
    }

    private String readFileContent(String filepath){
        String returnable = "";
        try {
            InputStream inputStream = getAssets().open(filepath);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            if (inputStream.read(buffer) == -1) {
                throw new IOException();
            }
            returnable = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnable;
    }
}