package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.example.myapplication.fragments.CalendarFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AddNoteActivity extends AppCompatActivity {
    private CalendarView datePicker;
    MyEventDay myEventDay;

    private ArrayList<EventDay> mEventDays = new ArrayList<>();

    private ArrayList<String> dates = new ArrayList<>();
    private ArrayList<String> notes = new ArrayList<>();
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnote);

        datePicker = (CalendarView) findViewById(R.id.datePicker2);
        Button button = (Button) findViewById(R.id.addNoteButton);
        TextView flavorText = findViewById(R.id.flavorText);
        final EditText noteEditText = (EditText) findViewById(R.id.noteEditText);

        datePicker.setHeaderColor(Color.RED);

        TinyDB tinydb = new TinyDB(this);
        dates = tinydb.getListString("dates");



        Date currentDate = Calendar.getInstance().getTime();

        for (int i = 0; i < dates.size(); i++) {
            Calendar calendar = Calendar.getInstance();

            String date = dates.get(i);
            String[] values = date.split(" ");
            int day = Integer.parseInt(values[0]);
            int month = Integer.parseInt(values[1]) - 1;
            int year = Integer.parseInt(values[2]);

            calendar.clone();
            calendar.set(year, month, day);
            try {
                datePicker.setDate(calendar);
            } catch (OutOfDateRangeException e) {
                e.printStackTrace();
            }

            mEventDays.add(new MyEventDay(calendar, R.drawable.ic_message_black_48dp));
            datePicker.setEvents(mEventDays);
        }

        datePicker.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Log.e("event", "in date picker");
                button.setVisibility(View.VISIBLE);
                flavorText.setVisibility(View.VISIBLE);
                noteEditText.setVisibility(View.VISIBLE);
                datePicker.setVisibility(View.GONE);

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                if (!(myEventDay instanceof MyEventDay)) {
                    myEventDay = new MyEventDay(datePicker.getSelectedDate(),
                            R.drawable.ic_message_black_48dp, noteEditText.getText().toString(), Color.parseColor("#228B22"));
                } else {
                    myEventDay.setNote(noteEditText.getText().toString());
                }
                returnIntent.putExtra(CalendarFragment.RESULT, myEventDay);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }
}