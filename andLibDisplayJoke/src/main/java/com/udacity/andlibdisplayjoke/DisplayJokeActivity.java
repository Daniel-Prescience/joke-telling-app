package com.udacity.andlibdisplayjoke;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DisplayJokeActivity extends AppCompatActivity {
    public final static String JOKE_KEY = "JOKE_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_joke);

        Intent jokeIntent = getIntent();

        if (jokeIntent != null) {
            String joke = jokeIntent.getStringExtra(JOKE_KEY);
            TextView joke_tv = findViewById(R.id.joke_tv);
            joke_tv.setText(joke);
        }
    }
}
