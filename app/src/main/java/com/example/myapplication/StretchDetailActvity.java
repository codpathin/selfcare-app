package com.example.myapplication;

import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.parceler.Parcels;

public class StretchDetailActvity extends AppCompatActivity {

    TextView tvDetailName;
    TextView tvDescription;
    TextView timer;
    ImageView ivExercise;
    Button startBtn;
    Button btShare;

    private boolean stop = false;

    private Integer a = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stretch_detail_actvity);

        tvDetailName = findViewById(R.id.tvDetailName);
        tvDescription = findViewById(R.id.tvDescription);
        ivExercise = findViewById(R.id.ivExercise);
        timer = findViewById(R.id.timer);
        startBtn = findViewById(R.id.start);
        btShare = findViewById(R.id.btShare);


        Stretch stretch = Parcels.unwrap(getIntent().getParcelableExtra("stretch"));
        tvDescription.setText(stretch.getDescription());
        tvDetailName.setText(stretch.getName());
        btShare.setVisibility(View.GONE);

        TypedArray imgs = getResources().obtainTypedArray(stretch.getDisplay());
        TypedArray completed = getResources().obtainTypedArray(R.array.completed);


        final Handler handler = new Handler();
        ivExercise.setImageResource(imgs.getResourceId(0, 0));

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBtn.setVisibility(View.GONE);
                final Runnable runnable = new Runnable() {
                    int i=1;
                    public void run() {
                        Integer count = a;
                        String sec = count.toString();

                        timer.setText(sec);

                        //when you go through all the images in the array
                        if(i>imgs.length())
                        {
                            i=0;
                            stop = true;
                            timer.setVisibility(View.GONE);
                            ivExercise.setImageResource(completed.getResourceId(0,0));
                            btShare.setVisibility(View.VISIBLE);
                        }

                        //the countdown timer
                        if (!stop) {
                            a--;
                            handler.postDelayed(this, 1000);  //for interval...
                        }

                        //reset timer when its done cycling through image, a = the amount of time for each image
                        if (a == -1) {
                            ivExercise.setImageResource(imgs.getResourceId(i, 0));
                            i++;
                            a = 8;
                        }
                    }
                };
                handler.postDelayed(runnable, 0); //for initial delay..
            }
        });

        btShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tweet = new Intent(Intent.ACTION_VIEW);
                tweet.setData(Uri.parse("https://twitter.com/intent/tweet?text=" + Uri.encode("Just finished doing my ") + Uri.encode(stretch.getName()) + Uri.encode("through the Self Care app!!!")));
                startActivity(tweet);
            }
        });
    }
}