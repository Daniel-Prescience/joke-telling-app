package com.udacity.andlibdisplayjoke;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DisplayJokeActivity extends AppCompatActivity {
    public final static String JOKE_KEY = "JOKE_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_joke);
    }
}
