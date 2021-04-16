package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.io.File;

public class ComposeActivity extends Fragment {

    public static final String TAG = "ComposeActivity";
    private EditText etName;
    private DatePicker etDate;
    private TimePicker etTime;
    private EditText etNotes;
    private Button btnCreate;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_compose, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Spinner spinner = (Spinner) getView().findViewById(R.id.spinnerTask);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.planets_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        etName = view.findViewById(R.id.etName);
        etDate = view.findViewById(R.id.datePicker);
        etTime = view.findViewById(R.id.timePicker);
        etNotes = view.findViewById(R.id.etNotes);
        btnCreate = view.findViewById(R.id.btnCreate);

        etTime.setCurrentHour(0);
        etTime.setCurrentMinute(0);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                int date = etDate.getDayOfMonth();
                int hour = etTime.getCurrentHour();
                int min = etTime.getCurrentMinute();
                String notes = etNotes.getText().toString();

                if (name.isEmpty()) {
                    Toast.makeText(getContext(), "Name cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                Reminder created = new Reminder(name, date, hour, min, notes);
                Toast.makeText(getContext(), "Reminder created!", Toast.LENGTH_SHORT).show();
                goMainActivity();
            }
        });
    }

    private void goMainActivity() {
        Intent i = new Intent(getContext(), MainActivity.class);
        startActivity(i);
    }
}
