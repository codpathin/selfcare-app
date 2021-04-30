package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.parceler.Parcels;

public class StretchDetailActvity extends AppCompatActivity {

    TextView tvDetailName;
    TextView tvDescription;
    ImageView ivExercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stretch_detail_actvity);

        tvDetailName = findViewById(R.id.tvDetailName);
        tvDescription = findViewById(R.id.tvDescription);
        ivExercise = findViewById(R.id.ivExercise);

        Stretch stretch = Parcels.unwrap(getIntent().getParcelableExtra("stretch"));
        tvDescription.setText(stretch.getDescription());
        tvDetailName.setText(stretch.getName());
        ivExercise.setImageResource(stretch.getDisplay());
    }
}