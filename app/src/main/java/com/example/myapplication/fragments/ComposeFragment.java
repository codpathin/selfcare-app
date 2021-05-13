package com.example.myapplication.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import androidx.fragment.app.Fragment;

import com.allyants.notifyme.NotifyMe;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.Reminder;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class ComposeFragment extends Fragment {
    public interface OnDataPass {
        public void onDataPass(Reminder reminder);
    }

    OnDataPass dataPasser;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataPasser = (OnDataPass) context;
    }

    public void passData(Reminder reminder) {
        dataPasser.onDataPass(reminder);
    }

    public static final String TAG = "ComposeFragment";
    private EditText etName;
    private DatePicker etDate;
    private TimePicker etTime;
    private EditText etNotes;
    private Button btnCreate;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compose, container, false);

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
                String date = (etDate.getMonth() + " "+etDate.getDayOfMonth()+" "+etDate.getYear());
                int hour = etTime.getCurrentHour();
                int min = etTime.getCurrentMinute();
                String notes = etNotes.getText().toString();

                if (name.isEmpty()) {
                    Toast.makeText(getContext(), "Name cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                Reminder created = new Reminder(name, date, hour, min, notes);

                Calendar now = Calendar.getInstance();
                now.set(Calendar.HOUR_OF_DAY, hour);
                now.set(Calendar.MINUTE, min);
                now.set(Calendar.YEAR, etDate.getYear());
                now.set(Calendar.MONTH, etDate.getMonth());
                now.set(Calendar.DAY_OF_MONTH, etDate.getDayOfMonth());
                now.set(Calendar.SECOND, 1);

                Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);

                //create notification
                NotifyMe notifyMe = new NotifyMe.Builder(getActivity().getApplicationContext()).
                        title(name)
                        .content(notes)
                        .color(255,0,0,255)
                        .led_color(255,255,255,255)
                        .time(now)
                        .large_icon(R.mipmap.ic_launcher_round)
                        .addAction(new Intent(),"Dismiss",true,false)
                        .addAction(intent,"Done")
                        .build();

                Toast.makeText(getContext(), "Reminder created!", Toast.LENGTH_SHORT).show();
                passData(created);
            }
        });
    }
}
