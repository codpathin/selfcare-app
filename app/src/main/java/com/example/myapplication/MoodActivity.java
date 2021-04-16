package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MoodActivity extends AppCompatActivity {
    RatingBar rb;
    Button btn;
    public static final String TAG = "MoodActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        rb = findViewById(R.id.moodBar);
        btn = findViewById(R.id.submit);

        SharedPreferences settings = getSharedPreferences("PREFS", 0);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rating = "" + rb.getRating();
                //Toast.makeText(getApplicationContext(), totalStars + "\n" + rating, Toast.LENGTH_LONG).show();

                SharedPreferences.Editor editor = settings.edit();
                editor.putString("rating", rating);
                editor.commit();

                Log.i(TAG, rating);

                startActivity(new Intent(MoodActivity.this, MainActivity.class));
            }
        });

    }
}
