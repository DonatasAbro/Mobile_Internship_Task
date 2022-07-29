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
        String dogUrlsStr = file2String(jsonPath);
        ArrayList<String> dogUrlsArray = new ArrayList<>();
        ListView dogUrlsList = findViewById(R.id.dogUrlsList);
        
        try {
            JSONObject jsonObject = new JSONObject(dogUrlsStr);
            JSONArray jsonArray = jsonObject.getJSONArray("urls");
            for (int i = 0; i < jsonArray.length(); i++) {
                dogUrlsArray.add(jsonArray.get(i).toString());
            }
        } catch (JSONException exception) {
            Log.e("JsonParser error", "unexpected JSON exception", exception);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.activity_listview, R.id.textView, dogUrlsArray);
        dogUrlsList.setAdapter(arrayAdapter);
    }

    private String file2String(String filepath){
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