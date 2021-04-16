package com.example.myapplication;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class ComposeActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    private EditText etName;
    private EditText etDate;
    private EditText etTime;
    private EditText etNotes;
    private Button btnCreate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etDate = findViewById(R.id.etDate);
        etTime = findViewById(R.id.etTime);
        etNotes = findViewById(R.id.etNotes);
        btnCreate = findViewById(R.id.btnCreate);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new Reminder(etName.getText().toString(), etDate.getText().toString(), etTime.getText().toString(), etNotes.getText().toString());
            }
        }
    }
}
