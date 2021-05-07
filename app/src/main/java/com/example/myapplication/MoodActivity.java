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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MoodActivity extends AppCompatActivity {
    RatingBar rb;
    Button btn;
    public static final String TAG = "MoodActivity";
    private ArrayList<String> moods = new ArrayList<>();
    private String color = "#000000";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        rb = findViewById(R.id.moodBar);
        btn = findViewById(R.id.submit);

        SharedPreferences settings = getSharedPreferences("PREFS", 0);

        Calendar cal = Calendar.getInstance();

        TinyDB tinydb = new TinyDB(this);
        moods = tinydb.getListString("moods");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rating = "" + rb.getRating();
                //Toast.makeText(getApplicationContext(), totalStars + "\n" + rating, Toast.LENGTH_LONG).show();

                SharedPreferences.Editor editor = settings.edit();
                editor.putString("rating", rating);
                editor.commit();

                if(rating.equals("5.0")) {
                    color = "#f2ff00";
                }
                if(rating.equals("4.0")) {
                    color = "#00ff51";
                }

                moods.add(getFormattedDate(cal.getTime()));
                moods.add(color);

                tinydb.putListString("moods", moods);

                Log.i(TAG, rating);

                startActivity(new Intent(MoodActivity.this, MainActivity.class));
            }
        });

    }

    public static String getFormattedDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MM yyyy", Locale.getDefault());
        return simpleDateFormat.format(date);
    }
}
