package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.applandeo.materialcalendarview.CalendarView;
import com.example.myapplication.fragments.CalendarFragment;

public class AddNoteActivity extends AppCompatActivity {
    MyEventDay myEventDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnote);

        final CalendarView datePicker = (CalendarView) findViewById(R.id.datePicker);
        Button button = (Button) findViewById(R.id.addNoteButton);
        final EditText noteEditText = (EditText) findViewById(R.id.noteEditText);

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