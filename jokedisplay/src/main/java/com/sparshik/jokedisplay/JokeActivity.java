package com.sparshik.jokedisplay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class JokeActivity extends AppCompatActivity {

    public static String JOKE_KEY = "Joke key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
    }

    public void goBack(View View) {
        super.onBackPressed();
    }
}
