package com.example.mobile_internship_task;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DogListAdapter extends BaseAdapter {
    Context context;
    ArrayList<Bitmap> dogPics;
    ArrayList<String> dogSums;
    LayoutInflater inflater;

    public DogListAdapter(Context applicationContext, ArrayList<Bitmap> dogPics, ArrayList<String> dogSums) {
        this.context = applicationContext;
        this.dogPics = dogPics;
        this.dogSums = dogSums;
        inflater = LayoutInflater.from(applicationContext);
    }

    @Override
    public int getCount() {
        return dogPics.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_listview, null);
        ImageView dogPic = (ImageView) view.findViewById(R.id.dogImageView);
        TextView dogSum = (TextView) view.findViewById(R.id.SumView);
        dogPic.setImageBitmap(dogPics.get(i));
        dogSum.setText(dogSums.get(i));
        return view;
    }
}
