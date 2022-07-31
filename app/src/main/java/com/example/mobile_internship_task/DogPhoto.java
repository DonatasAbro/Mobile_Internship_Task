package com.example.mobile_internship_task;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class DogPhoto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_photo);

        if(getIntent().hasExtra("dogPicStream")) {
            ImageView viewDogPic = findViewById(R.id.dogPicView);
            Bitmap dogPic = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("dogPicStream"),0,getIntent().getByteArrayExtra("dogPicStream").length);
            viewDogPic.setImageBitmap(dogPic);
        }
    }
}