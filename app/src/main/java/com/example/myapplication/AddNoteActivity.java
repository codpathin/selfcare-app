package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.example.myapplication.fragments.CalendarFragment;

public class AddNoteActivity extends AppCompatActivity {
    private CalendarView datePicker;
    MyEventDay myEventDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnote);

        datePicker = (CalendarView) findViewById(R.id.datePicker2);
        Button button = (Button) findViewById(R.id.addNoteButton);
        TextView flavorText = findViewById(R.id.flavorText);
        final EditText noteEditText = (EditText) findViewById(R.id.noteEditText);

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
                            R.drawable.ic_message_black_48dp, noteEditText.getText().toString());
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