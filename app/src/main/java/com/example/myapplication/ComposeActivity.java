package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ComposeActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    private EditText etName;
    private DatePicker etDate;
    private TimePicker etTime;
    private EditText etNotes;
    private Button btnCreate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etDate = findViewById(R.id.datePicker);
        etTime = findViewById(R.id.timePicker);
        etNotes = findViewById(R.id.etNotes);
        btnCreate = findViewById(R.id.btnCreate);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Reminder created = new Reminder(etName.getText().toString(), etDate.getDayOfMonth(), etTime.getCurrentHour(),);
            }
        }
    }
}
