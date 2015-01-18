package com.example.mray.datasources;

import android.os.AsyncTask;

import java.util.List;
import java.util.Random;

/**
 * Created by Aaron Barber on 17/01/15.
 */
public class LinkedInConnection extends AsyncTask<Void, Void, CareerInfo> {
    @Override
    protected CareerInfo doInBackground(Void... params) {
        List<CareerInfo> info = CareerInfo.Dataset;
        Random gen = new Random();
        return info.get(gen.nextInt(info.size()));
    }
}
