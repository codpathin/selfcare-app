package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ComposeFragment extends Fragment {
    TextView tvName, tvDate, tvTime, tvType, tvNotes;
    EditText etName, etNotes;
    DatePicker datePicker;
    TimePicker timePicker;
    Spinner spinnerTask;
    Button btnCreate;

    public ComposeFragment() {
        //rq'd empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_compose, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //binding all elements in layout resrc file
        tvName = getView().findViewById(R.id.tvName);
        tvDate = getView().findViewById(R.id.tvDate);
        tvTime = getView().findViewById(R.id.tvTime);
        tvType = getView().findViewById(R.id.tvType);
        tvNotes = getView().findViewById(R.id.tvNotes);
        etName = getView().findViewById(R.id.etName);
        etNotes = getView().findViewById(R.id.etNotes);
        datePicker = getView().findViewById(R.id.datePicker);
        timePicker = getView().findViewById(R.id.timePicker);
        spinnerTask = getView().findViewById(R.id.spinnerTask);
        btnCreate = getView().findViewById(R.id.btnCreate);

        btnCreate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //TODO: bind name, date, time, type, notes to reminder class
                //TODO: send reminder to be added to list of reminders
                //TODO: create notification based on reminder
                Toast.makeText(getContext(), "Reminder created!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
