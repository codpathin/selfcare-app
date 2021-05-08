package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.applandeo.materialcalendarview.EventDay;
import com.example.myapplication.fragments.CalendarFragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NotePreviewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepreview);

        Intent intent = getIntent();

        TextView note = (TextView) findViewById(R.id.tvRNote);
        TextView title = (TextView) findViewById(R.id.tvNotesTitle);
        if (intent != null) {
            Object event = intent.getParcelableExtra(CalendarFragment.EVENT);

            if(event instanceof MyEventDay){
                MyEventDay myEventDay = (MyEventDay)event;

                title.setText(getFormattedDate(myEventDay.getCalendar().getTime()) + " Notes");
                //getSupportActionBar().setTitle(getFormattedDate(myEventDay.getCalendar().getTime()));
                note.setText(myEventDay.getNote());
                return;
            }

            String date = intent.getStringExtra(CalendarFragment.DATE);
            title.setText(date + " Notes");

            if(event instanceof EventDay){
                EventDay eventDay = (EventDay)event;
                title.setText(date + " Notes");
                //getSupportActionBar().setTitle(getFormattedDate(eventDay.getCalendar().getTime()));
            }
        }
    }

    public static String getFormattedDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
        return simpleDateFormat.format(date);
    }
}