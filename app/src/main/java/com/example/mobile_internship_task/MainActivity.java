package com.example.mobile_internship_task;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        ArrayList<Integer> dogSums = new ArrayList<>();
        ListView dogUrlsList = findViewById(R.id.dogUrlsList);
        
        try {
            JSONObject dogUrlsJsonObject = new JSONObject(dogUrlsStr);
            JSONArray dogUrlsJsonArray = dogUrlsJsonObject.getJSONArray("urls");
            for (int i = 0; i < 10 /*dogUrlsJsonArray.length()*/; i++) {
                String dogUrl = dogUrlsJsonArray.get(i).toString();
                dogSums.add(filterAndAddDigits(dogUrl));
                dogPics.add(BitmapFactory.decodeStream((InputStream) new URL(dogUrl).getContent()));
            }
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        DogListAdapter dogListAdapter = new DogListAdapter(getApplicationContext(), dogPics, dogSums);
        dogUrlsList.setAdapter(dogListAdapter);

        dogUrlsList.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(MainActivity.this, DogPhoto.class);
            Bitmap dogPic = dogPics.get(i);
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            dogPic.compress(Bitmap.CompressFormat.PNG, 50, byteStream);
            intent.putExtra("dogPicStream", byteStream.toByteArray());
            startActivity(intent);
        });
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

    private Integer filterAndAddDigits(String dogUrl) {
        Pattern pattern = Pattern.compile("\\d*[_]\\d*");
        Matcher match = pattern.matcher(dogUrl);

        if (match.find()) {
            String[] digits = match.group().split("_", 0);
            return Integer.parseInt(digits[0]) + Integer.parseInt(digits[1]);
        }
        else {
            return 0;
        }
    }
}