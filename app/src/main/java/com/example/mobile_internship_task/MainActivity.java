package com.example.mobile_internship_task;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String jsonPath = "json/dog_urls.json";
        String dogUrlsStr = readFileContent(jsonPath);
        ArrayList<String> dogUrls = new ArrayList<>();
        ListView dogUrlsList = findViewById(R.id.dogUrlsList);
        
        try {
            JSONObject dogUrlsJsonObject = new JSONObject(dogUrlsStr);
            JSONArray dogUrlsJsonArray = dogUrlsJsonObject.getJSONArray("urls");
            for (int i = 0; i < dogUrlsJsonArray.length(); i++) {
                dogUrls.add(dogUrlsJsonArray.get(i).toString());
            }
        } catch (JSONException exception) {
            Log.e("JsonParser error", "unexpected JSON exception", exception);
        }

        ArrayAdapter<String> dogUrlsArrayAdapter = new ArrayAdapter<>(this, R.layout.activity_listview, R.id.textView, dogUrls);
        dogUrlsList.setAdapter(dogUrlsArrayAdapter);
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
        } catch (IOException exception) {
            Log.e("IO error", "unexpected IO exception", exception);
        }
        return returnable;
    }
}